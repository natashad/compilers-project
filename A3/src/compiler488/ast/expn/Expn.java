package compiler488.ast.expn;

import compiler488.ast.AST;
import compiler488.ast.Printable;
import compiler488.ast.type.Type;

/**
 * A placeholder for all expressions.
 */
public class Expn extends AST implements Printable {
	
	private Type type;
	public Expn(int lineNum) {
		super(lineNum);
		type = null;
	}
	
	//Return the type of variable.
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	

	
}
