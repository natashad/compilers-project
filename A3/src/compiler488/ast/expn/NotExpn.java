package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Represents the boolean negation of an expression.
 */
public class NotExpn extends UnaryExpn {

	public NotExpn(String opSymbol, Expn operand, int lineNum) {
		super(operand, opSymbol, lineNum);
		this.setType(new BooleanType());
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		BooleanType type = new BooleanType();
		this.operand.semanticCheck(semantics);
		if (this.operand.getType() == null || !this.operand.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Not expression type not boolean.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		this.setType(type);
		
	}
	
	@Override
	public void codeGen(CodeGen codeGen) {
		Instruction pushInstr = new Instruction(Machine.PUSH, "PUSH", 1);
		codeGen.generateCode(pushInstr);
		
		this.operand.codeGen(codeGen);
		
		Instruction subInstr = new Instruction(Machine.SUB, "SUB");
		codeGen.generateCode(subInstr);
		
	}

}
