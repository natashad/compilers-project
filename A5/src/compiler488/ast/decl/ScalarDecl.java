package compiler488.ast.decl;

import compiler488.ast.type.Type;
import compiler488.codegen.CodeGen;
import compiler488.semantics.Semantics;

/**
 * Represents the declaration of a variable when passed as parameter to function or procedure
 */

public class ScalarDecl extends Declaration {

	public ScalarDecl(String name, Type type, int lineNum) {
		super(name, type, lineNum);
	}
	
	/**
	 * Returns a string describing the name and type of the object being
	 * declared.
	 */
	@Override
	public String toString() {
		return   name + " : " + type ;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics) {
	
	}
	
	public void codeGen(CodeGen codeGen) {
	
	}
}
