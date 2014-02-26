package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;

/**
 * Place holder for all ordered comparisions expression where both operands must
 * be integer expressions. e.g. < , > etc. comparisons
 */
public class CompareExpn extends BinaryExpn {
	
	private Type type;
	
	public CompareExpn(String opSymbol, Expn left, Expn right) {
		super(opSymbol, left, right);
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//Set type to Integer.
		this.type = new IntegerType();
		
		//TODO: Error checking if both expr type are same.
	}
	
	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}
}
