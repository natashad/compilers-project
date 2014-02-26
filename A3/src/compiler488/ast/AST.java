package compiler488.ast;

import compiler488.semantics.Semantics;

/**
 * This is a placeholder at the top of the Abstract Syntax Tree hierarchy. It is
 * a convenient place to add common behaviour.
 * @author  Dave Wortman, Marsha Chechik, Danny House
 */
public class AST {
	
	// override this for child classes 
	public void semanticCheck(Semantics semantics) {
		
	}
 }
