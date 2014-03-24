package compiler488.ast;

import compiler488.semantics.Semantics;

/**
 * This is a placeholder at the top of the Abstract Syntax Tree hierarchy. It is
 * a convenient place to add common behaviour.
 * @author  Dave Wortman, Marsha Chechik, Danny House
 */
public class AST {
	
	private int lineNumber;
	
	public AST(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	//TODO: THIS SHOULD BE AN ABSTRACT METHOD IN AN ABSTRACT CLASS.
	// override this for child classes 
	public void semanticCheck(Semantics semantics) {
		; // Do nothing
	}
 }
