package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Place holder for all binary expression where both operands must be integer
 * expressions.
 */
public class ArithExpn extends BinaryExpn {

	private Type type;
	
	public ArithExpn(String opSymbol, Expn left, Expn right, int lineNum) {
		super(opSymbol, left, right, lineNum);
		this.setType(new IntegerType());
	}
	

	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}
	
	@Override
	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public void semanticCheck(Semantics semantics){
		IntegerType type = new IntegerType();
		this.left.semanticCheck(semantics);
		this.right.semanticCheck(semantics);


		if (this.left.getType() == null || !this.left.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Left side of arithmetic expression not of type integer.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		if (this.right.getType() == null || !this.right.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Right side of arithmetic expression not of type integer.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		this.setType(type);
		
	} 
	
	@Override
	public void codeGen(CodeGen codeGen) {
		this.left.codeGen(codeGen);
		this.right.codeGen(codeGen);
		Instruction i;
		if ("+".equals(this.opSymbol)) {
			i = new Instruction(14, "ADD");
		} else if ("-".equals(this.opSymbol)) {
			i = new Instruction(15, "SUB");
		} else if ("*".equals(this.opSymbol)) {
			i = new Instruction(16, "MUL");
		} else if ("/".equals(this.opSymbol))  {
			i = new Instruction(17, "DIV");
		} else {
			i = new Instruction(-1, "");
			System.err.println("Trying to generate code for a non existant operation: " + this.opSymbol);
		}
		codeGen.generateCode(i);
	}
}
