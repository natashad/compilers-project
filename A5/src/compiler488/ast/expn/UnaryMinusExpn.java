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
	
	private Expn factor;
	public UnaryMinusExpn(Expn factor, int lineNum) {
		super(factor, "-", lineNum);
		this.factor = factor;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		IntegerType type = new IntegerType();
		factor.semanticCheck(semantics);
		if (this.factor.getType() == null || !this.factor.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Unary minus expression type not integer.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		this.setType(type);
		
	}

}
