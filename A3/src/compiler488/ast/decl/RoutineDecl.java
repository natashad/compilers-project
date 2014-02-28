package compiler488.ast.decl;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.semantics.Semantics.ScopeType;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;
import compiler488.symbol.SymbolTable;

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
	private DeclarationHead head;
	public RoutineDecl(DeclarationHead head, DeclarationPart declPart, Type type, RoutineBody routineBody, int lineNum ) {
		this(head, declPart, type, routineBody, false, lineNum);
	}
	
	public RoutineDecl(DeclarationHead head, DeclarationPart declPart, Type type, RoutineBody routineBody, boolean isForward, int lineNum ) {
		super(declPart.getName(), type, lineNum);
		this.head = head;
		this.routineBody = routineBody;
		if (type == null) {
			this.routineBody.getBody().setScopeType(ScopeType.Procedure);
		}else {
			this.routineBody.getBody().setScopeType(ScopeType.Function);
		}
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
	
	  String ret = "";
	  if(isForward()) {
		  ret += "Forward: ";
	  }
	  if(type==null)
	    {
	      ret += " proc " + name;
	    }
	  else
	    {
	      ret += " func "  + name  + " : " + type ;
	    }
	  return ret;
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
	
	public DeclarationHead getDeclarationHead() {
		return this.head;
	}

	public void setDeclarationHead(DeclarationHead head) {
		this.head = head;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics) {
		Entry paramEntry;
		ListIterator<ScalarDecl> listParams = this.getDeclarationHead().getParameters().listIterator();
		ScalarDecl param;
		SymbolTable symTable = new SymbolTable();
		while (listParams.hasNext()) {
			param = listParams.next();
			paramEntry = new Entry(Kind.Variable, param.getName(), param);
			paramEntry.setType(param.type);
			symTable.put(param.getName(), paramEntry);
		}
		this.routineBody.getBody().setSymtable(symTable);
		this.routineBody.semanticCheck(semantics);
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
		Entry prevDecl = semantics.curScopeLookup(this.name);
		if (prevDecl == null) {
			semantics.addToCurrScope(this.name, entry, getLineNumber());
		}else if ( ( prevDecl.getKind() == Kind.ForwardFunction
						&& kind == Kind.Function) 
					|| (prevDecl.getKind() == Kind.ForwardProcedure
						&& kind == Kind.Procedure) ) {
			
			ASTList<ScalarDecl> forwardDeclParams = ((RoutineDecl) prevDecl.getNode()).getRoutineBody().getParameters();
			ASTList<ScalarDecl> currDeclParams = this.getRoutineBody().getParameters();
			
			if (forwardDeclParams.size() != currDeclParams.size()) {
				SemanticError error = new SemanticError("Forward Declaration of function " + 
														this.getName() + " specifies " + forwardDeclParams.size()
														+ " parameters (Given: " + currDeclParams.size() + ").", 
														getLineNumber());
				semantics.errorList.add(error);
			}
			ListIterator<ScalarDecl> listPrevParams = forwardDeclParams.listIterator();
			ListIterator<ScalarDecl> listCurrParams = currDeclParams.listIterator();
			
			while (listPrevParams.hasNext()) {
				Type forwardType = listPrevParams.next().getType();
				Type currType = listCurrParams.next().getType();
				if (forwardType != currType){
					SemanticError error = new SemanticError("Expected Parameter Type: " + forwardType + 
															", Given Parameter Type: " + currType + ".", getLineNumber());
					semantics.errorList.add(error);
				}	
			}
			if (this.getType() != ((RoutineDecl) prevDecl.getNode()).getType()) {
				SemanticError error = new SemanticError("Forward Declaration specifies return type of function " + this.getName() 
														+ " to be " + ((RoutineDecl) prevDecl.getNode()).getType() + 
														" (Given: " + this.getType() + ").", getLineNumber());
				semantics.errorList.add(error);
			}
			semantics.remove(name); //remove forward decl
			semantics.addToCurrScope(this.name, entry, getLineNumber());  //add new declaration
		}else {
			SemanticError error = new SemanticError("Cannot declare function " + this.getName() + 
													". Variable name already used.", getLineNumber());
			semantics.errorList.add(error);
		}
		
	}
	
	
}
