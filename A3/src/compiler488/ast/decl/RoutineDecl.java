package compiler488.ast.decl;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

/**
 * Represents the declaration of a function or procedure.
 */
public class RoutineDecl extends Declaration {
	/*
	 * The formal parameters of the function/procedure and the
	 * statements to execute when the procedure is called.
	 */
	private RoutineBody routineBody;
	private boolean isForward = false;
	
	public RoutineDecl( DeclarationPart declPart, Type type, RoutineBody routineBody ) {
		this(declPart, type, routineBody, false);
	}
	
	public RoutineDecl( DeclarationPart declPart, Type type, RoutineBody routineBody, boolean isForward ) {
		super(declPart.getName(), type);
		this.routineBody = routineBody;
		this.isForward = isForward;
	}

	public boolean isForward() {
		return isForward;
	}

	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

	/**
	 * Returns a string indicating that this is a function with
	 * return type or a procedure, name, Type parameters, if any,
	 * are listed later by routineBody
	 */
	@Override
	public String toString() {
	  if(type==null)
	    {
	      return " proc " + name;
	    }
	  else
	    {
	      return " func "  + name  + " : " + type ;
	    }
	}

	/**
	 * Prints a description of the function/procedure.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOn(out, depth, this + " ");
		routineBody.printOn(out, depth);
	}

	public RoutineBody getRoutineBody() {
		return routineBody;
	}

	public void setRoutineBody(RoutineBody routineBody) {
		this.routineBody = routineBody;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics) {
		Entry entry;
		Kind kind;
		
		if (isForward) {
			if (this.type == null) {
				kind = Kind.ForwardProcedure;	
			} else {
				kind = Kind.ForwardFunction;
			}
			
		} else {
			if (this.type == null) {
				kind = Kind.Procedure;
			} else {
				kind = Kind.Function;
			}
		}
		entry = new Entry(kind, this.name, this);
		
		//TODO: If function and in symbol but not forward. then do check.
		
		Entry prevDecl = semantics.allScopeLookup(this.name);
		if (prevDecl == null) {
			semantics.addToCurrScope(this.name, entry);
		}else if ( (prevDecl.getKind() == Kind.ForwardFunction) || (prevDecl.getKind() == 
				Kind.ForwardProcedure) && kind == prevDecl.getKind()) {
			
			ASTList<ScalarDecl> forwardDeclParams = ((RoutineDecl) prevDecl.getNode()).getRoutineBody().getParameters();
			ASTList<ScalarDecl> currDeclParams = this.getRoutineBody().getParameters();
			
			if (forwardDeclParams.size() != currDeclParams.size()) {
				//TODO: Add error message
			}
			ListIterator<ScalarDecl> listPrevParams = forwardDeclParams.listIterator();
			ListIterator<ScalarDecl> listCurrParams = currDeclParams.listIterator();
			
			while (listPrevParams.hasNext()) {
				if (listPrevParams.next().getType() != listCurrParams.next().getType()){
					//TODO: Add error message
				}	
			}
			if (this.getType() != ((RoutineDecl) prevDecl.getNode()).getType()) {
				//error
			}
			semantics.remove(name); //remove forward decl
			semantics.addToCurrScope(this.name, entry);  //add new declaration
		}else {
			//Error
		}
		
	}
	
	
}
