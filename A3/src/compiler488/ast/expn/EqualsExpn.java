package compiler488.ast.expn;


import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands could be either
 * integer or boolean expressions. e.g. = and not = comparisons
 */
public class EqualsExpn extends BinaryExpn {
	
	
	public EqualsExpn(String opSymbols, Expn left, Expn right, int lineNum) {
		super(opSymbols, left, right, lineNum);
		this.setType(new BooleanType());
	}
	
	
	/**
	 * Do semantic analysis
	 */
	public void semanticCheck(Semantics semantics) {
		this.left.semanticCheck(semantics);
		this.right.semanticCheck(semantics);
		
		if (this.left.getType() == null) {
			SemanticError error = new SemanticError("Left side of equals expression of invalid type.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		if (this.right.getType() == null) {
			SemanticError error = new SemanticError("Right side of equals expression of invalid type.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		if (!this.left.getType().getClass().equals(this.right.getType().getClass())) {
			SemanticError error = new SemanticError("Left side expression not of same type as right side expression.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		this.setType((new BooleanType()));
	
		
	}
	
	
}
