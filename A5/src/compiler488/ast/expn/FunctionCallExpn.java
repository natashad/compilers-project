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
		Entry entry = semantic.allScopeLookup(ident);
		if (entry == null) {
			SemanticError error = new SemanticError("Function called but not previously declared.", this.getLineNumber());
			semantic.errorList.add(error);
		}else {
			RoutineDecl function = (RoutineDecl) entry.getNode();
			if (function.getType() == null) {
				SemanticError error = new SemanticError("Function call expression made with procedure.", this.getLineNumber());
				semantic.errorList.add(error);
			
			}else {
				this.setType(function.getType());
				ListIterator<ScalarDecl> params = function.getRoutineBody().getParameters().listIterator();
				ListIterator<Expn> args = this.arguments.listIterator();
				if (function.getRoutineBody().getParameters().size() != this.arguments.size()) {
					SemanticError error = new SemanticError("Different number of parameters and arguments.", this.getLineNumber());
					semantic.errorList.add(error);
				}else {
					while (args.hasNext()) {
						Expn arg = args.next();	
						arg.semanticCheck(semantic);
						ScalarDecl param = params.next();
						if (!arg.getType().getClass().equals(param.getType().getClass())) {
							SemanticError error = new SemanticError("Argument does not match parameter type.", this.getLineNumber());
							semantic.errorList.add(error);
						}
					}
					
				}	
			}
		}
		
	}

}
