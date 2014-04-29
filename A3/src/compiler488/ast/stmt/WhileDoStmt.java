package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.expn.Expn;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.codegen.LabelInstruction;

/**
 * Represents a loop in which the exit condition is evaluated before each pass.
 */
public class WhileDoStmt extends LoopingStmt {
	
	public WhileDoStmt(Expn expn, ASTList<Stmt> statements, int lineNum) {
		super(expn, statements, lineNum);
		
	}
	
	/**
	 * Print a description of the <b>while-do</b> construct.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOnLn(out, depth, "while " + expn + " do");
		body.printOnSeperateLines(out, depth + 1);
		Indentable.printIndentOnLn(out, depth, "end");
	}

	@Override
	public void codeGen(CodeGen codeGen) {
		
		LabelInstruction startLabel = new LabelInstruction("WHILE_START");
		codeGen.generateCode(startLabel);
		
		expn.codeGen(codeGen);
		
		LabelInstruction endLabel = new LabelInstruction("WHILE_END");
		Instruction pushEndInstr = new Instruction(4, "PUSH", endLabel.getLabelId());
		codeGen.generateCode(pushEndInstr);
		
		Instruction bfInstr = new Instruction(12, "BF");
		codeGen.generateCode(bfInstr);
		
		ListIterator<Stmt> body = this.body.listIterator();

		while (body.hasNext()) {
			Stmt statement = body.next();
			if (statement instanceof ExitStmt) {
				((ExitStmt)statement).setExitLabel(endLabel);
			}
			statement.codeGen(codeGen);
		}
		
		Instruction pushStartInstr = new Instruction(4, "PUSH" , startLabel.getLabelId());
		codeGen.generateCode(pushStartInstr);
		
		Instruction brInstr = new Instruction(11, "BR");
		codeGen.generateCode(brInstr);
		
		codeGen.generateCode(endLabel);
		
	}
}
