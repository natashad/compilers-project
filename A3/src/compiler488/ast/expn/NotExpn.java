package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;

/**
 * Represents the boolean negation of an expression.
 */
public class NotExpn extends UnaryExpn {

	private Type type;
	public NotExpn(String opSymbol, Expn operand) {
		super(operand, opSymbol);
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//Set type to Integer.
		this.type = new BooleanType();
		
		//TODO: Error checking if both expr type are Integer.
	}
	
	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}
}
