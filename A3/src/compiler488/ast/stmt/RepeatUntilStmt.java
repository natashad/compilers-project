package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.expn.Expn;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.codegen.LabelInstruction;
import compiler488.runtime.Machine;

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
	
	@Override
	public void codeGen(CodeGen codeGen) {
		LabelInstruction startLabel = new LabelInstruction("REPEAT_START");
		LabelInstruction endLabel = new LabelInstruction("REPEAT_END");
		
		codeGen.generateCode(startLabel);
		
		ListIterator<Stmt> body = this.body.listIterator();

		while (body.hasNext()) {
			Stmt statement = body.next();
			if (statement instanceof ExitStmt) {
				((ExitStmt)statement).setExitLabel(endLabel);
			}
			statement.codeGen(codeGen);
		}
		
		expn.codeGen(codeGen);
		

		Instruction pushStartInstr = new Instruction(Machine.PUSH, "PUSH" , startLabel.getLabelId());
		codeGen.generateCode(pushStartInstr);
		
		Instruction bfInstr = new Instruction(Machine.BF, "BF");
		codeGen.generateCode(bfInstr);
		
		codeGen.generateCode(endLabel);
	}
}
