package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Place holder for all ordered comparisions expression where both operands must
 * be integer expressions. e.g. < , > etc. comparisons
 */
public class CompareExpn extends BinaryExpn {
	
	
	private Type type;
	
	public CompareExpn(String opSymbol, Expn left, Expn right, int lineNum) {
		super(opSymbol, left, right, lineNum);
		this.setType(new BooleanType());
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		

		right.semanticCheck(semantics);
		left.semanticCheck(semantics);
		//Set type to Boolean.
		//S20
		this.type = new BooleanType();
	
		//S31
		//Check that type of expression or variable is integar.
		if (left.getType() == null || left.getType() instanceof IntegerType) {
			SemanticError error = new SemanticError("Type of expression " + this.left.toString() + " is not Integer", getLineNumber());
			semantics.errorList.add(error);
		}
		
		if (right.getType() == null || right.getType() instanceof IntegerType) {
			SemanticError error = new SemanticError("Type of expression " + this.right.toString() + " is not Integer", getLineNumber());
			semantics.errorList.add(error);
		}
	}
	
	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}

}
