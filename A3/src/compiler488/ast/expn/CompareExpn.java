package compiler488.ast.expn;

/**
 * Place holder for all ordered comparisions expression where both operands must
 * be integer expressions. e.g. < , > etc. comparisons
 */
public class CompareExpn extends BinaryExpn {
	
	public CompareExpn(String opSymbol, Expn left, Expn right) {
		super(opSymbol, left, right);
	}

}
