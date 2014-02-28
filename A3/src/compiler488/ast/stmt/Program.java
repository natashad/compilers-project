package compiler488.ast.stmt;

import compiler488.semantics.Semantics;
import compiler488.semantics.Semantics.ScopeType;
import compiler488.symbol.SymbolTable;


/**
 * Placeholder for the scope that is the entire program
 */
public class Program extends Scope {
	
	public Program(Scope scope, int lineNum) {
		super(scope.getDeclarations(), scope.getStatements(), lineNum);
		this.setSymtable(new SymbolTable());
		this.setScopeType(ScopeType.Program);
	}
	
	@Override
	public void semanticCheck(Semantics semantic) {
		super.semanticCheck(semantic);
	}
	
}
