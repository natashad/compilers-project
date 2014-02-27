package compiler488.ast.stmt;

import compiler488.ast.expn.Expn;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Holds the assignment of an expression to a variable.
 */
public class AssignStmt extends Stmt {
	/*
	 * lval is the location being assigned to, and rval is the value being
	 * assigned.
	 */
	private Expn lval, rval;
	
	public AssignStmt(Expn lval, Expn rval, int lineNum) {
		super(lineNum);
		this.lval = lval; 
		this.rval = rval;
	}

	/** Returns a string that describes the assignment statement. */
	@Override
	public String toString() {
		return "Assignment: " + lval + " := " + rval;
	}

	public Expn getLval() {
		return lval;
	}

	public void setLval(Expn lval) {
		this.lval = lval;
	}

	public Expn getRval() {
		return rval;
	}

	public void setRval(Expn rval) {
		this.rval = rval;
	}
	@Override 
	public void semanticCheck(Semantics semantic){
		this.lval.semanticCheck(semantic);
		this.rval.semanticCheck(semantic);
		if (lval.getType().toString() != rval.getType().toString()) {
			//TODO: Should we be checking tht lval is a variable here as opposed to some other type of epression?
			SemanticError error = new SemanticError("Assigning incompatible type (" + rval.getType() + ") to variable " + lval, getLineNumber());
			semantic.errorList.add(error);
		}
	}
}
