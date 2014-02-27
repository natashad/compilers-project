package compiler488.ast.stmt;

import java.io.PrintStream;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.expn.Expn;

/**
 * Represents a loop in which the exit condition is evaluated after each pass.
 */
public class RepeatUntilStmt extends LoopingStmt {
	
	public RepeatUntilStmt(Expn expn, ASTList<Stmt> statements, int lineNum) {
		super(expn, statements, lineNum);
	}
	
	/**
	 * Print a description of the <b>repeat-until</b> construct.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOnLn(out, depth, "repeat");
		body.printOnSeperateLines(out, depth + 1);
		Indentable.printIndentOnLn(out, depth, " until "  + expn );

	}
}
