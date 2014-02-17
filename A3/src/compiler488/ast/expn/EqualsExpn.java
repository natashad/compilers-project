package compiler488.ast.expn;

/**
 * Place holder for all binary expression where both operands could be either
 * integer or boolean expressions. e.g. = and not = comparisons
 */
public class EqualsExpn extends BinaryExpn {
	
	public EqualsExpn(String opSymbols, Expn left, Expn right) {
		super(opSymbols, left, right);
	}

}
