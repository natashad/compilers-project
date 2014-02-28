package compiler488.ast.expn;

import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.decl.RoutineDecl;
import compiler488.ast.decl.ScalarDecl;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;

/**
 * Represents a function call with or without arguments.
 */
public class FunctionCallExpn extends Expn {
	private String ident; // The name of the function.

	private ASTList<Expn> arguments; // The arguments passed to the function.
	
	public FunctionCallExpn(String ident, ASTList<Expn> args, int lineNum) {
		super(lineNum);
		this.ident = ident;
		this.arguments = args;
	}

	/** Returns a string describing the function call. */
	@Override
	public String toString() {
		if (arguments!=null) {
			return ident + " (" + arguments + ")";
		}
		else
			return ident + " ( ) " ;
	}

	public ASTList<Expn> getArguments() {
		return arguments;
	}

	public void setArguments(ASTList<Expn> args) {
		this.arguments = args;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	public void semanticCheck(Semantics semantic) {
		
		Entry entry = semantic.curScopeLookup(this.ident);
		if (entry != null &&  entry.getKind() == Entry.Kind.Function) {
			RoutineDecl routine = (RoutineDecl)entry.getNode();
			if (this.getArguments().size() != routine.getRoutineBody().getParameters().size()) {
				SemanticError error = new SemanticError("Invalid number of arguments to Function " + this.ident, getLineNumber());
				semantic.errorList.add(error);
			}else {
					ListIterator<ScalarDecl> params = routine.getRoutineBody().getParameters().listIterator();
					ListIterator<Expn> args = this.arguments.listIterator();
					Entry argEntry;
					while (params.hasNext()) {
						Expn arg = args.next();
						ScalarDecl param = params.next();
						arg.semanticCheck(semantic);
						if (!arg.getType().toString().equals(param.getType().toString())) {
							SemanticError error = new SemanticError("Invalid argument type ", getLineNumber());
							semantic.errorList.add(error);
						}
					}
			}
			
	
			this.setType(entry.getType());
		}else {
			SemanticError error = new SemanticError("Function does not exist", getLineNumber());
			semantic.errorList.add(error);
		}
	}

}
