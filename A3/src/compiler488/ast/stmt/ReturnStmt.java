package compiler488.ast.stmt;

import java.io.PrintStream;

import compiler488.ast.Indentable;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * The command to return from a procedure.
 */
public class ReturnStmt extends Stmt {

	public ReturnStmt(int lineNum) {
		super(lineNum);
	}
	
	/**
	 * Print <b>return</b> on a line, by itself.
	 * 
	 * @param out
	 *            Where to print.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOn(out, depth);
		out.println("return ");
	}

	public void semanticCheck(Semantics semantic) {
		if (semantic.getCurrMajorScope() != Semantics.ScopeType.Procedure) {
			SemanticError error = new SemanticError("Return statement not in Procedure", this.getLineNumber());
			semantic.errorList.add(error);
		}
	}
}
