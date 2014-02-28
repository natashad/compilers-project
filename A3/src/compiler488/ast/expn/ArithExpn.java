package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
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
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		
		left.semanticCheck(semantics);
		right.semanticCheck(semantics);
		
		//Set type to Integer.
		//S21
		this.type = new IntegerType();
		
		//S31
		//Check that type of expression or variable is integar.
		if (!this.left.getType().toString().equals(type.toString())) {
			SemanticError error = new SemanticError("Type of expression " + this.left.toString() + " is not Integer", getLineNumber());
			semantics.errorList.add(error);
		}
		
		if (!this.right.getType().toString().equals(type.toString())) {
			SemanticError error = new SemanticError("Type of expression " + this.right.toString() + " is not Integer", getLineNumber());
			semantics.errorList.add(error);
		}
	}
	
	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}
}
