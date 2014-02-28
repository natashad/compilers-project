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

		this.rval.semanticCheck(semantic);
		this.lval.semanticCheck(semantic);
		if (lval != null && rval != null) {
			if (lval.getClass() == IdentExpn.class) {
				IdentExpn lval = (IdentExpn) this.lval;
				if (semantic.allScopeLookup(lval.getIdent()) == null || semantic.allScopeLookup(lval.getIdent()).getKind() != Kind.Variable) {
					SemanticError error = new SemanticError("Variable assignment incorrect", getLineNumber());
					semantic.errorList.add(error);
				}
			}else if (lval.getClass() == SubsExpn.class) {
				SubsExpn lval = (SubsExpn) this.lval;
				if (semantic.allScopeLookup(lval.getVariable()) == null || semantic.allScopeLookup(lval.getVariable()).getKind() != Kind.Array) {
					SemanticError error = new SemanticError("Variable assignment incorrect", getLineNumber());
					semantic.errorList.add(error);
				}
			}else {
				SemanticError error = new SemanticError("Variable assignment incorrect", getLineNumber());
				semantic.errorList.add(error);
			}
			if (lval.getType().getClass() != rval.getType().getClass()) {
				SemanticError error = new SemanticError("Assigning incompatible type (" + rval.getType() + ") to variable " + lval, getLineNumber());
				semantic.errorList.add(error);
			}
		} else {
			SemanticError error = new SemanticError("Incorrect assignment statement, getLineNumber());
			semantic.errorList.add(error);
		}
		
	}
}
