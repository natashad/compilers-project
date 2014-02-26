package compiler488.ast.stmt;

import compiler488.semantics.Semantics;


/**
 * Placeholder for the scope that is the entire program
 */
public class Program extends Scope {

	public Program(Scope scope) {
		super(scope.getDeclarations(), scope.getStatements());
	}
	
	@Override
	public void semanticCheck(Semantics semantic) {
		semantic.scopeStack.push(Semantics.ScopeType.Program);
		super.semanticCheck(semantic);
		
	}
	
}
