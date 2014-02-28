package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Represents the boolean negation of an expression.
 */
public class NotExpn extends UnaryExpn {

	public NotExpn(String opSymbol, Expn operand, int lineNum) {
		super(operand, opSymbol, lineNum);
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//Set type to Boolean.
		//S20
		this.setType(new BooleanType());
		
		//S30
		//Check type of expression is boolean
		if (!(operand.getType() instanceof BooleanType)) {
			SemanticError error = new SemanticError("Expression " + this.getOperand() + " must be boolean type", getLineNumber());
			semantics.errorList.add(error);
		}
	}

}
