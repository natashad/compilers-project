package compiler488.ast.expn;

/**
 * Place holder for all binary expression where both operands must be boolean
 * expressions.
 */
public class BoolExpn extends BinaryExpn {

	public BoolExpn(String opSymbol, Expn left, Expn right) {
		super(opSymbol, left, right);
	}
}
