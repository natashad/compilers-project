package compiler488.codegen;

public class LabelInstruction extends Instruction {
	
	private static int count = 0;
	
	private String labelName;

	
	public LabelInstruction(String name) {
		super(-1, name, -1, -1);
		this.labelName = name;
		this.labelName += count++;
	}
	

}
