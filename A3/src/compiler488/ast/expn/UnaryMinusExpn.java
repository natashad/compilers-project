package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Represents negation of an integer expression
 */
public class UnaryMinusExpn extends UnaryExpn {
	
	public UnaryMinusExpn(Expn factor, int lineNum) {
		super(factor, "-", lineNum);
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//S21
		//Set type to Integer.
		this.setType(new IntegerType());
		
		//S31
		//Check the type of expression is Integer
		if (this.getOperand().getType() == null || !(this.getOperand().getType() instanceof IntegerType)) {
			SemanticError error = new SemanticError("Expression " + this.getOperand() + " is not Integer type", getLineNumber());
			semantics.errorList.add(error);
		}
	}

}
