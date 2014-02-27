package compiler488.ast.expn;

import compiler488.ast.AST;
import compiler488.ast.Printable;
import compiler488.ast.type.Type;

/**
 * A placeholder for all expressions.
 */
public class Expn extends AST implements Printable {
	
	public Expn(int lineNum) {
		super(lineNum);
	}
	
	//Return the type of variable.
	public Type getType() {
		return null;
	}
	
	
}
