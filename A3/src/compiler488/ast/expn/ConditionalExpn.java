package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.codegen.LabelInstruction;
import compiler488.codegen.PushLabelInstruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;


/** Represents a conditional expression (i.e., x>0?3:4). */
public class ConditionalExpn extends Expn {
	private Expn condition; // Evaluate this to decide which value to yield.

	private Expn trueValue; // The value is this when the condition is true.

	private Expn falseValue; // Otherwise, the value is this.
		
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
		this.condition.semanticCheck(semantic);
		BooleanType type = new BooleanType();
		if (this.condition.getType() == null || !this.condition.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Conditional expression condition not of type Boolean", this.getLineNumber());
			semantic.errorList.add(error);
		}
		this.trueValue.semanticCheck(semantic);
		this.falseValue.semanticCheck(semantic);
		if (this.trueValue.getType() == null || this.falseValue.getType() == null) {
			return;
		}
		if (!this.trueValue.getType().getClass().equals(this.falseValue.getType().getClass())) {
			SemanticError error = new SemanticError("Expressions in conditional expression not of the same type", this.getLineNumber());
			semantic.errorList.add(error);
		}else {
			this.setType(this.trueValue.getType());
		}
	}
	
	@Override
	public void codeGen(CodeGen codeGen) {
		this.condition.codeGen(codeGen);
		
		LabelInstruction falseLabel = new LabelInstruction("FALSEVALUE");
		LabelInstruction endLabel = new LabelInstruction("ENDCONDITIONAL");
		
		Instruction pushInstr = new PushLabelInstruction(falseLabel.getName());
		codeGen.generateCode(pushInstr);
		
		Instruction bfInstr = new Instruction(Machine.BF, "BF");
		codeGen.generateCode(bfInstr);
		
		this.trueValue.codeGen(codeGen);
		
		Instruction pushInstr2 = new PushLabelInstruction(endLabel.getName());
		codeGen.generateCode(pushInstr2);
		
		Instruction brInstr = new Instruction(Machine.BR, "BR");
		codeGen.generateCode(brInstr);
		
		codeGen.generateCode(falseLabel);
		falseValue.codeGen(codeGen);
		
		codeGen.generateCode(endLabel);
		
	}
	
}
