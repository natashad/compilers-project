package compiler488.ast.expn;

import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands must be boolean
 * expressions.
 */
public class BoolExpn extends BinaryExpn {

	public BoolExpn(String opSymbol, Expn left, Expn right) {
		super(opSymbol, left, right);
	}
	
	public void semanticCheck(Semantics semantic) {
		left.semanticCheck(semantic);
		right.semanticCheck(semantic);
		if (left.getType().toString() != "boolean" || left.getType().toString() != right.getType().toString()) {
			//TODO Add error message
		}
	}
}
