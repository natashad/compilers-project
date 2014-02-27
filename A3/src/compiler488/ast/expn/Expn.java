package compiler488.ast.expn;

import compiler488.ast.AST;
import compiler488.ast.Printable;
import compiler488.ast.type.Type;

/**
 * A placeholder for all expressions.
 */
public class Expn extends AST implements Printable {
	
	private int lineNumber;
	
	public Expn(int lineNum) {
		this.lineNumber = lineNum;
	}
	
	//Return the type of variable.
	public Type getType() {
		return null;
	}
	
	public void setLineNumber(int lineNum) {
		this.lineNumber = lineNum;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
}
