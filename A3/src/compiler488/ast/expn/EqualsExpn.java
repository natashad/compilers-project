package compiler488.ast.expn;

import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands could be either
 * integer or boolean expressions. e.g. = and not = comparisons
 */
public class EqualsExpn extends BinaryExpn {
	
	public EqualsExpn(String opSymbols, Expn left, Expn right) {
		super(opSymbols, left, right);
	}
	
	public void semanticCheck(Semantics semantic) {
		left.semanticCheck(semantic);
		right.semanticCheck(semantic);
		if (left.getType().toString() != right.getType().toString()) {
			//TODO: Add error
		}
	}
	

}
