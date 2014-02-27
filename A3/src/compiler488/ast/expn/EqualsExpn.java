package compiler488.ast.expn;


import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands could be either
 * integer or boolean expressions. e.g. = and not = comparisons
 */
public class EqualsExpn extends BinaryExpn {
	
	private Type type;
	
	public EqualsExpn(String opSymbols, Expn left, Expn right, int lineNum) {
		super(opSymbols, left, right, lineNum);
	}
	/**
	 * Do semantic analysis
	 */
	public void semanticCheck(Semantics semantic) {
		
		left.semanticCheck(semantic);
		right.semanticCheck(semantic);
		
		if (left.getType().toString() != right.getType().toString()) {
			//TODO: Add error
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
