package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;


/** Represents a conditional expression (i.e., x>0?3:4). */
public class ConditionalExpn extends Expn {
	private Expn condition; // Evaluate this to decide which value to yield.

	private Expn trueValue; // The value is this when the condition is true.

	private Expn falseValue; // Otherwise, the value is this.
	
	private Type type;
	
	public ConditionalExpn(Expn cond, Expn trueVal, Expn falseVal, int lineNum) {
		super(lineNum);
		this.condition = cond;
		this.trueValue = trueVal;
		this.falseValue = falseVal;
	}

	/** Returns a string that describes the conditional expression. */
	@Override
	public String toString() {
		return "(" + condition + " ? " + trueValue + " : " + falseValue + ")";
	}

	public Expn getCondition() {
		return condition;
	}

	public void setCondition(Expn condition) {
		this.condition = condition;
	}

	public Expn getFalseValue() {
		return falseValue;
	}

	public void setFalseValue(Expn falseValue) {
		this.falseValue = falseValue;
	}

	public Expn getTrueValue() {
		return trueValue;
	}

	public void setTrueValue(Expn trueValue) {
		this.trueValue = trueValue;
	}
	public void semanticCheck(Semantics semantic) {
		
		condition.semanticCheck(semantic);
		trueValue.semanticCheck(semantic);
		falseValue.semanticCheck(semantic);
		
		//S24
		//Setting result type to type of conditional expression.
		this.type = trueValue.getType();
		
		//S30
		//Checking the type of condition expression is boolean
		if (!(condition.getType() instanceof BooleanType)) {
			SemanticError error = new SemanticError("Type of expression " + this.condition.toString() + " is not Boolean", getLineNumber());
			semantic.errorList.add(error);
		}
		
		//S33
		//Check both expression in condition are same type.
		if (trueValue.getType() == null || 
				falseValue.getType() == null || 
				trueValue.getType().toString() != falseValue.getType().toString()) {
			SemanticError error = new SemanticError("Type of expression " + this.trueValue.toString() + " does not match"
					+ " the type of expression " + this.falseValue.toString(), getLineNumber());
			semantic.errorList.add(error);
			
			//Setting it to type of Integer even if the type doesn't match because it can be null too
			this.type = new IntegerType();
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
