package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands must be boolean
 * expressions.
 */
public class BoolExpn extends BinaryExpn {

	public BoolExpn(String opSymbol, Expn left, Expn right, int lineNum) {
		super(opSymbol, left, right, lineNum);
		this.setType(new BooleanType());
	}
	
	public void semanticCheck(Semantics semantics) {
		this.left.semanticCheck(semantics);
		this.right.semanticCheck(semantics);
		
		BooleanType type = new BooleanType();
		if (this.left.getType() == null || !this.left.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Left side of boolean expression not of type boolean.", this.getLineNumber());
			semantics.errorList.add(error);
		}

		if (this.right.getType() == null || !this.right.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Right side of boolean expression not of type boolean.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		this.setType((new BooleanType()));
	}

}
