package compiler488.ast.stmt;

import compiler488.ast.AST;
import compiler488.ast.ASTList;
import compiler488.ast.expn.Expn;
import compiler488.ast.type.BooleanType;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.symbol.SymbolTable;


/**
 * Represents the common parts of loops.
 */
public abstract class LoopingStmt extends Stmt
{
    protected ASTList<Stmt> body ;	  // body of ther loop
    protected Expn expn;          // Loop condition

    public LoopingStmt(Expn expn, ASTList<Stmt> body, int lineNum) {
    	super(lineNum);
    	this.expn = expn;
    	this.body = body;
    }
    
	public Expn getExpn() {
		return expn;
	}

	public void setExpn(Expn expn) {
		this.expn = expn;
	}

	public ASTList<Stmt> getBody() {
		return body;
	}

	public void setBody(ASTList<Stmt> body) {
		this.body = body;
	}
	
	@Override
	public void semanticCheck(Semantics semantic) {
		
		if (! (expn.getType() instanceof BooleanType) ) {
			SemanticError error = new SemanticError("Looping Condition must evaluate to a boolean.", getLineNumber());
			semantic.errorList.add(error);
		}
		
		expn.semanticCheck(semantic);
		
		SymbolTable symTable = new SymbolTable();
		semantic.openScope(symTable, Semantics.ScopeType.Loop);
		
		for(AST a : body) {
			a.semanticCheck(semantic);
		}
		
		semantic.closeScope();
	}

}
