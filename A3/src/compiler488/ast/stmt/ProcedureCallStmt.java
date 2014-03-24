package compiler488.ast.stmt;

import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.decl.RoutineDecl;
import compiler488.ast.decl.ScalarDecl;
import compiler488.ast.decl.ScalarDeclPart;
import compiler488.ast.expn.Expn;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;

/**
 * Represents calling a procedure.
 */
public class ProcedureCallStmt extends Stmt {
	private String name; // The name of the procedure being called.

	private ASTList<Expn> arguments; // The arguments passed to the procedure.
	
	public ProcedureCallStmt(String name, ASTList<Expn> args, int lineNum) {
		super(lineNum);
		this.name = name;
		this.arguments = args;
	}
	
	/** Returns a string describing the procedure call. */
	@Override
	public String toString() {
		if (arguments!=null)
			return "Procedure call: " + name + " (" + arguments + ")";
		else
			return "Procedure call: " + name + " ( ) ";
	}

	public ASTList<Expn> getArguments() {
		return arguments;
	}

	public void setArguments(ASTList<Expn> args) {
		this.arguments = args;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void semanticCheck(Semantics semantic) {
		Entry entry = semantic.allScopeLookup(this.getName());
		if (entry == null) {
			SemanticError error = new SemanticError("Procedure called but not previously declared.", this.getLineNumber());
			semantic.errorList.add(error);
		}else {
			RoutineDecl proc = (RoutineDecl) entry.getNode();
			if (proc.getType() != null) {
				SemanticError error = new SemanticError("Procedure call statement made with function name.", this.getLineNumber());
				semantic.errorList.add(error);
			
			}else {
				ListIterator<ScalarDecl> params = proc.getRoutineBody().getParameters().listIterator();
				ListIterator<Expn> args = this.arguments.listIterator();
				if (proc.getRoutineBody().getParameters().size() != this.arguments.size()) {
					SemanticError error = new SemanticError("Different number of parameters and arguments.", this.getLineNumber());
					semantic.errorList.add(error);
				}else {
					while (args.hasNext()) {
						Expn arg = args.next();	
						arg.semanticCheck(semantic);
						ScalarDecl param = params.next();
						if (arg.getType()==null || !arg.getType().getClass().equals(param.getType().getClass())) {
							SemanticError error = new SemanticError("Argument does not match parameter type.", this.getLineNumber());
							semantic.errorList.add(error);
						}
						
					}
				}	
			}
		}
		
	}

}
