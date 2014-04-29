package compiler488.ast.expn;


import compiler488.ast.type.BooleanType;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands could be either
 * integer or boolean expressions. e.g. = and not = comparisons
 */
public class EqualsExpn extends BinaryExpn {
	
	
	public EqualsExpn(String opSymbols, Expn left, Expn right, int lineNum) {
		super(opSymbols, left, right, lineNum);
		this.setType(new BooleanType());
	}
	
	
	/**
	 * Do semantic analysis
	 */
	public void semanticCheck(Semantics semantics) {
		this.left.semanticCheck(semantics);
		this.right.semanticCheck(semantics);
		
		if (this.left.getType() == null) {
			SemanticError error = new SemanticError("Left side of equals expression of invalid type.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		if (this.right.getType() == null) {
			SemanticError error = new SemanticError("Right side of equals expression of invalid type.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		if (!this.left.getType().getClass().equals(this.right.getType().getClass())) {
			SemanticError error = new SemanticError("Left side expression not of same type as right side expression.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		this.setType((new BooleanType()));

	}
	
	@Override
	public void codeGen(CodeGen codeGen) {
		this.left.codeGen(codeGen);
		this.right.codeGen(codeGen);
		
		Instruction equalsInstr = new Instruction(18, "EQ");
		codeGen.generateCode(equalsInstr);
		
		if (Expn.OpSymbols.NotEquals.equals(this.opSymbol)) {
			Instruction pushInstr = new Instruction(4, "PUSH", 1);
			codeGen.generateCode(pushInstr);
			
			Instruction swapInstr = new Instruction(21, "SWAP");
			codeGen.generateCode(swapInstr);
			
			Instruction subInstr = new Instruction(15, "SUB");
			codeGen.generateCode(subInstr);
		}
		
		
	}
	
	
}
