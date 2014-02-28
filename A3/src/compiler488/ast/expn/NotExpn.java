package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;

/**
 * Represents the boolean negation of an expression.
 */
public class NotExpn extends UnaryExpn {

	public NotExpn(String opSymbol, Expn operand, int lineNum) {
		super(operand, opSymbol, lineNum);
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//Set type to Integer.
		this.setType(new BooleanType());
		
		//TODO: Error checking if both expr type are Integer.
	}

}
