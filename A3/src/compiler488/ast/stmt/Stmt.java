package compiler488.ast.stmt;

import compiler488.ast.Indentable;

/**
 * A placeholder for statements.
 */
public class Stmt extends Indentable {
	
	private int lineNumber;
	
	public Stmt(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
}
