package compiler488.ast.stmt;

import compiler488.ast.expn.*;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

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
		if (semantic.getCurrScopeType() == Semantics.ScopeType.Loop) {
			SemanticError error = new SemanticError("Exit statement is being used outside of a loop.", getLineNumber());
			semantic.errorList.add(error);
		}
		//TODO: Ensure tat if expn is not null, it is a booeanExpn
	}
}
