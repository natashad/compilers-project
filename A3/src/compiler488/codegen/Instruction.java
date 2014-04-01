package compiler488.codegen;

import java.util.ArrayList;

public class Instruction {

	private int opCode;
	private String name;
	private int arg1 = -1;
	private int arg2 = -1;
	
	public Instruction(int opCode, String name, int arg1, int arg2) {
		this.opCode = opCode;
		this.name = name;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	public int getOpCode() {
		return opCode;
	}

	public void setOpCode(int opCode) {
		this.opCode = opCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArg1() {
		return arg1;
	}
	
	public void setArg1(int arg1) {
		this.arg1 = arg1;
	}

	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}
	
	public int getArg2() {
		return arg2;
	}
	
	
	
	@Override
	public String toString() {
		String argStr = "";
		if (arg1 > -1) {
			argStr += arg1;
		}
		if (arg2 > -1) {
			argStr += arg2;
		}
		return name + argStr;
	}
}
