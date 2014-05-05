package compiler488.codegen;

import compiler488.runtime.Machine;

public class PushLabelInstruction extends Instruction {

	String labelName;
	
	public PushLabelInstruction(String labelName) {
		super(Machine.PUSH, "PUSH");
		this.labelName = labelName;
	}

	public String getLabelName() {
		return this.labelName;
	}
}
