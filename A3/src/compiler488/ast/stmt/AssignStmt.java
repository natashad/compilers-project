package compiler488.ast.stmt;

import compiler488.ast.decl.ArrayDeclPart;
import compiler488.ast.decl.DeclarationPart;
import compiler488.ast.decl.ScalarDeclPart;
import compiler488.ast.expn.Expn;
import compiler488.ast.expn.IdentExpn;
import compiler488.ast.expn.SubsExpn;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

/**
 * Holds the assignment of an expression to a variable.
 */
public class AssignStmt extends Stmt {
	/*
	 * lval is the location being assigned to, and rval is the value being
	 * assigned.
	 */
	private Expn rval;
	private Expn lval;
	
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
		if (this.lval.getType() == null || this.rval.getType() == null) {
			SemanticError error = new SemanticError("Invalid Assignment statement.", this.getLineNumber());
			semantic.errorList.add(error);
			return;
		}
		
		
		if (!this.lval.getType().getClass().equals(this.rval.getType().getClass())) {
			SemanticError error = new SemanticError("Assignment statement types don't match.", this.getLineNumber());
			semantic.errorList.add(error);
		}
		
		
	}
}
