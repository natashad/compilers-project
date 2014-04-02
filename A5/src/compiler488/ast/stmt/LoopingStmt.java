package compiler488.ast.stmt;

import java.util.ListIterator;

import compiler488.ast.AST;
import compiler488.ast.ASTList;
import compiler488.ast.expn.Expn;
import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
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
	public void semanticCheck(Semantics semantics) {
		semantics.scopeStack.push(Semantics.ScopeType.Loop);
		this.expn.semanticCheck(semantics);
		ListIterator<Stmt> body = this.body.listIterator();

		while (body.hasNext()) {
			Stmt statement = body.next();
			statement.semanticCheck(semantics);
		}
		
		Type type = new BooleanType(this.getLineNumber());
		if (!this.expn.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Loop condition is not of type Boolean", this.getLineNumber());
			semantics.errorList.add(error);
		}
		
		
		semantics.scopeStack.pop();

	}

}
