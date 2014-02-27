package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;

/**
 * Represents negation of an integer expression
 */
public class UnaryMinusExpn extends UnaryExpn {
	
	private Type type;
	public UnaryMinusExpn(Expn factor, int lineNum) {
		super(factor, "-", lineNum);
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//Set type to Integer.
		this.type = new IntegerType();
		
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
