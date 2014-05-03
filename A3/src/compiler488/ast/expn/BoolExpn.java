package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.codegen.LabelInstruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands must be boolean
 * expressions.
 */
public class BoolExpn extends BinaryExpn {

	public BoolExpn(String opSymbol, Expn left, Expn right, int lineNum) {
		super(opSymbol, left, right, lineNum);
		this.setType(new BooleanType());
	}
	
	public void semanticCheck(Semantics semantics) {
		this.left.semanticCheck(semantics);
		this.right.semanticCheck(semantics);
		
		BooleanType type = new BooleanType();
		if (this.left.getType() == null || !this.left.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Left side of boolean expression not of type boolean.", this.getLineNumber());
			semantics.errorList.add(error);
		}

		if (this.right.getType() == null || !this.right.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Right side of boolean expression not of type boolean.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		this.setType((new BooleanType()));
	}
	
	@Override
	public void codeGen(CodeGen codeGen) {
		if (Expn.OpSymbols.And.equals(this.opSymbol)) {
			LabelInstruction label = new LabelInstruction("AND_BRANCHFALSE");
			this.left.codeGen(codeGen);
			
			Instruction dupInstr = new Instruction(Machine.DUP, "DUP");
			codeGen.generateCode(dupInstr);
			
			Instruction pushInstr = new Instruction(Machine.PUSH, "PUSH", label.getLabelId());
			codeGen.generateCode(pushInstr);
			
			Instruction bfInstr = new Instruction(Machine.BF, "BF");
			codeGen.generateCode(bfInstr);
			
			Instruction popInstr = new Instruction(Machine.POP, "POP");
			codeGen.generateCode(popInstr);
			
			this.right.codeGen(codeGen);

			codeGen.generateCode(label);
						
		} else if (Expn.OpSymbols.Or.equals(this.opSymbol)) {
			
			LabelInstruction firstFalseLabel = new LabelInstruction("OR_FIRSTFALSE");
			LabelInstruction endOrLabel = new LabelInstruction("OR_END");
			
			this.left.codeGen(codeGen);
			
			Instruction dupInstr = new Instruction(Machine.DUP, "DUP");
			codeGen.generateCode(dupInstr);
			
			Instruction pushInstr = new Instruction(Machine.PUSH, "PUSH", firstFalseLabel.getLabelId());
			codeGen.generateCode(pushInstr);
			
			Instruction bfInstr = new Instruction(Machine.BF, "BF");
			codeGen.generateCode(bfInstr);
			
			Instruction pushInstr2 = new Instruction(Machine.PUSH, "PUSH", endOrLabel.getLabelId());
			codeGen.generateCode(pushInstr2);
			
			Instruction brInstr = new Instruction(Machine.BR, "BR");
			codeGen.generateCode(brInstr);
			
			codeGen.generateCode(firstFalseLabel);
			
			Instruction popInstr = new Instruction(Machine.POP, "POP");
			codeGen.generateCode(popInstr);
			
			this.right.codeGen(codeGen);
			
			codeGen.generateCode(endOrLabel);
			
		}
	}

}
