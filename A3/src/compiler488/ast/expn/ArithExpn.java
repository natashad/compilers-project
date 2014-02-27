package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands must be integer
 * expressions.
 */
public class ArithExpn extends BinaryExpn {

	private Type type;
	
	public ArithExpn(String opSymbol, Expn left, Expn right, int lineNum) {
		super(opSymbol, left, right, lineNum);
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
