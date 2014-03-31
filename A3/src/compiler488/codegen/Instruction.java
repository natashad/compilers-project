package compiler488.codegen;

import java.util.ArrayList;

public class Instruction {

	private short opCode;
	private String name;
	private ArrayList<Short> args = new ArrayList<Short> ();
	
	public Instruction(short opCode, String name, ArrayList<Short> args) {
		this.opCode = opCode;
		this.name = name;
		if (args != null) {
			this.args = args;
		}
	}
	
	public short getOpCode() {
		return opCode;
	}

	public void setOpCode(short opCode) {
		this.opCode = opCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Short> getArgs() {
		return args;
	}

	public void setArgs(ArrayList<Short> args) {
		this.args = args;
	}
	
	@Override
	public String toString() {
		String argStr = "";
		for (int i = 0; i < args.size(); i++) {
			argStr += args.get(i);
		}
		return name + argStr;
	}
}
