package compiler488.ast.expn;


import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
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
		
		//S32
		//Check that left and right expressions are the same type.
		if (left.getType() == null ||
				right.getType() == null ||
				left.getType().toString() != right.getType().toString()) {
			SemanticError error = new SemanticError("Type of expression " + this.left.toString() + " does not match"
					+ " the type of expression " + this.right.toString(), getLineNumber());
			semantic.errorList.add(error);
		}
		
		//S20
		//Setting the result type to boolean
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
