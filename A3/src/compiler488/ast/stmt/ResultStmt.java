package compiler488.ast.stmt;

import java.io.PrintStream;

import compiler488.ast.Indentable;
import compiler488.ast.expn.Expn;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * The command to return from a function or procedure.
 */
public class ResultStmt extends Stmt {
	// The value to be returned by a function.
	private Expn value = null;
	
	public ResultStmt(Expn value, int lineNum) {
		super(lineNum);
		this.value = value;
	}

	/**
	 * Print <b>result</b>  expression on a line, by itself.
	 * 
	 * @param out
	 *            Where to print.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOn(out, depth);
		out.println("result " + value );
	}

	public Expn getValue() {
		return value;
	}

	public void setValue(Expn value) {
		this.value = value;
	}
	
	public void semanticCheck(Semantics semantic) {
		if (semantic.getCurrScopeType() == Semantics.ScopeType.Function) {
			SemanticError error = new SemanticError("Cannot return result from outside a function body", getLineNumber());
			semantic.errorList.add(error);
		}
	}
}
