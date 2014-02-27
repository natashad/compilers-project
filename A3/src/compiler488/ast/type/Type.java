package compiler488.ast.type;

import compiler488.ast.AST;

/**
 * A placeholder for types.
 */
public class Type extends AST {
	
	private int lineNumber;
	
	public Type(int lineNum) {
		this.lineNumber = lineNum;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
