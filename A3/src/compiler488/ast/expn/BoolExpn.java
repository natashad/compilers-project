package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;

import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands must be boolean
 * expressions.
 */
public class BoolExpn extends BinaryExpn {

	private Type type;
	
	public BoolExpn(String opSymbol, Expn left, Expn right) {
		super(opSymbol, left, right);
	}
	
	public void semanticCheck(Semantics semantic) {
		left.semanticCheck(semantic);
		right.semanticCheck(semantic);
		if (left.getType().toString() != "boolean" || left.getType().toString() != right.getType().toString()) {
			//TODO Add error message
		}
		this.type = new BooleanType();
	}

	
	
	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}

}
