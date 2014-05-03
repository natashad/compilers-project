package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Place holder for all ordered comparisions expression where both operands must
 * be integer expressions. e.g. < , > etc. comparisons
 */
public class CompareExpn extends BinaryExpn {
	
	
	private Type type;
	
	public CompareExpn(String opSymbol, Expn left, Expn right, int lineNum) {
		super(opSymbol, left, right, lineNum);
		this.setType(new BooleanType());
	}

	
	
	@Override
	public Type getType() {
		return this.type;
	}
	/** 
	 * Set the type 
	 * */
	@Override
	public void setType(Type type) {
		this.type = type;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		this.left.semanticCheck(semantics);
		this.right.semanticCheck(semantics);
		
		IntegerType type = new IntegerType();
		if (this.left.getType() == null || !this.left.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Left side of compare expression not of type integer.", this.getLineNumber());
			semantics.errorList.add(error);
		}

		if (this.right.getType() == null || !this.right.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Right side of compare expression not of type integer.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		this.setType((new BooleanType()));
	}
	
	@Override
	public void codeGen(CodeGen codeGen) {
		this.left.codeGen(codeGen);
		this.right.codeGen(codeGen);
		
		if (Expn.OpSymbols.LessThan.equals(this.opSymbol)) {
			Instruction ltInstr = new Instruction(Machine.LT, "LT");
			codeGen.generateCode(ltInstr);
			
		} else if (Expn.OpSymbols.LessEqual.equals(this.opSymbol)) {
			Instruction swapInstr = new Instruction(Machine.SWAP, "SWAP");
			codeGen.generateCode(swapInstr);
			
			Instruction ltInstr = new Instruction(Machine.LT, "LT");
			codeGen.generateCode(ltInstr);
			
			Instruction pushInstr = new Instruction(Machine.PUSH, "PUSH", 1);
			codeGen.generateCode(pushInstr);
			
			Instruction swapInstr2 = new Instruction(Machine.SWAP, "SWAP");
			codeGen.generateCode(swapInstr2);
			
			Instruction subInstr = new Instruction(Machine.SUB, "SUB");
			codeGen.generateCode(subInstr);
			
		} else if (Expn.OpSymbols.GreaterThan.equals(this.opSymbol)) {
			
			Instruction swapInstr = new Instruction(Machine.SWAP, "SWAP");
			codeGen.generateCode(swapInstr);
			
			Instruction ltInstr = new Instruction(Machine.LT, "LT");
			codeGen.generateCode(ltInstr);
			
		} else if (Expn.OpSymbols.GreaterEqual.equals(this.opSymbol)) {
			
			Instruction ltInstr = new Instruction(Machine.LT, "LT");
			codeGen.generateCode(ltInstr);
			
			Instruction pushInstr = new Instruction(Machine.PUSH, "PUSH", 1);
			codeGen.generateCode(pushInstr);
			
			Instruction swapInstr = new Instruction(Machine.SWAP, "SWAP");
			codeGen.generateCode(swapInstr);
			
			Instruction subInstr = new Instruction(Machine.SUB, "SUB");
			codeGen.generateCode(subInstr);
		}
		
	}

}
