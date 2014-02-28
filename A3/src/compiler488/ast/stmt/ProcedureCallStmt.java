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
		
		Entry entry = semantic.curScopeLookup(this.name);
		if (entry != null &&  entry.getKind() == Entry.Kind.Procedure) {
			RoutineDecl routine = (RoutineDecl)entry.getNode();
			if (this.getArguments().size() != routine.getRoutineBody().getParameters().size()) {
				SemanticError error = new SemanticError("Invalid number of arguments to Procedure " + this.name, getLineNumber());
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
		}else {
			SemanticError error = new SemanticError("Procedure does not exist", getLineNumber());
			semantic.errorList.add(error);
		}
	}

}
