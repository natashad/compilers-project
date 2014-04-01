package compiler488.codegen;

public class LabelInstruction extends Instruction {
	
	private static int count = 0;
	
	private String labelName;

	
	public LabelInstruction(String name) {
		super((short)-1, name, null);
		this.labelName = name;
		this.labelName += count++;
	}
	

}
