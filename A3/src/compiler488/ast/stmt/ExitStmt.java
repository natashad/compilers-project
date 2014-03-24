package compiler488.ast.stmt;

import compiler488.ast.expn.*;
import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.semantics.Semantics.ScopeType;

/**
 * Represents the command to exit from a loop.
 */

public class ExitStmt extends Stmt {

	// condition for 'exit when'
    private Expn expn = null;
    
    public ExitStmt(Expn expn, int lineNum) {
    	super(lineNum);
    	this.expn = expn;
    }

	/** Returns the string <b>"exit"</b> or <b>"exit when e"</b>" 
	*/
	@Override
	public String toString() {
		  {
		    String stmt = "exit " ;
                    if( expn != null )
		        stmt = stmt + "when " + expn + " " ;
		    return stmt ;
		  }
	}

	public Expn getExpn() {
		return expn;
	}

	public void setExpn(Expn expn) {
		this.expn = expn;
	}
	public void semanticCheck(Semantics semantic) {
		if (!semantic.inLoop()) {
			SemanticError error = new SemanticError("Exit statement outside of loop", this.getLineNumber());
			semantic.errorList.add(error);
		}
		if (this.expn != null) {
			Type type = new BooleanType(this.getLineNumber());
			expn.semanticCheck(semantic);
			if (!this.expn.getType().getClass().equals(type.getClass())) {
				SemanticError error = new SemanticError("Exit condition is not of type Boolean", this.getLineNumber());
				semantic.errorList.add(error);
			}
		}
		
	}
}
