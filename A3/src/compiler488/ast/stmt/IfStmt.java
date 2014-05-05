package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.expn.Expn;
import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.codegen.LabelInstruction;
import compiler488.codegen.PushLabelInstruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Represents an if-then or an if-then-else construct.
 */
public class IfStmt extends Stmt {
	// The condition that determines which branch to execute.
	private Expn condition;

	// Represents the statement to execute when the condition is true.
	private ASTList<Stmt> whenTrue;

	// Represents the statement to execute when the condition is false.
	private ASTList<Stmt> whenFalse = new ASTList<Stmt>();
	
	public IfStmt(Expn cond, ASTList<Stmt> whenTrue, ASTList<Stmt> whenFalse, int lineNum) {
		super(lineNum);
		this.condition = cond;
		this.whenFalse = whenFalse;
		this.whenTrue = whenTrue;
	}
	
	public IfStmt(Expn cond, ASTList<Stmt> whenTrue, int lineNum) {
		this(cond, whenTrue, new ASTList<Stmt>(), lineNum);
	}

	/**
	 * Print a description of the <b>if-then-else</b> construct. If the
	 * <b>else</b> part is empty, just print an <b>if-then</b> construct.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOnLn(out, depth, "if " + condition + " then ");
		
		whenTrue.printOnSeperateLines(out, 	depth + 1);
		if (whenFalse != null) {
			Indentable.printIndentOnLn(out, depth, "else");
			whenFalse.printOnSeperateLines(out, depth + 1);
		}
		Indentable.printIndentOnLn(out, depth, "fi");
	}

	public Expn getCondition() {
		return condition;
	}

	public void setCondition(Expn condition) {
		this.condition = condition;
	}

	public ASTList<Stmt> getWhenFalse() {
		return whenFalse;
	}

	public void setWhenFalse(ASTList<Stmt> whenFalse) {
		this.whenFalse = whenFalse;
	}

	public ASTList<Stmt> getWhenTrue() {
		return whenTrue;
	}

	public void setWhenTrue(ASTList<Stmt> whenTrue) {
		this.whenTrue = whenTrue;
	}
	
	public void semanticCheck(Semantics semantics)  {
		semantics.scopeStack.push(Semantics.ScopeType.If);
		this.condition.semanticCheck(semantics);
		
		ListIterator<Stmt> trueStatements = this.whenTrue.listIterator();
		ListIterator<Stmt> falseStatements = this.whenFalse.listIterator();

		while (trueStatements.hasNext()) {
			Stmt statement = trueStatements.next();
			statement.semanticCheck(semantics);
		}

		while (falseStatements.hasNext()) {
			Stmt statement = falseStatements.next();
			statement.semanticCheck(semantics);
		}
		
		Type type = new BooleanType(this.getLineNumber());
		if (this.condition.getType() == null || !this.condition.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("If condition is not of type Boolean", this.getLineNumber());
			semantics.errorList.add(error);
		}
		semantics.scopeStack.pop();
	}
	
	@Override
	public void codeGen(CodeGen codeGen) {
		LabelInstruction falseLabel = new LabelInstruction("FalseBranchLabel");
		LabelInstruction endLabel = new LabelInstruction("EndIfLabel");
		
		condition.codeGen(codeGen);
		
		Instruction pushInstr = new PushLabelInstruction(falseLabel.getName());
		codeGen.generateCode(pushInstr);
		
		Instruction bfInstruction = new Instruction(Machine.BF, "BF");
		codeGen.generateCode(bfInstruction);
		
		ListIterator<Stmt> trueStatements = this.whenTrue.listIterator();
		ListIterator<Stmt> falseStatements = this.whenFalse.listIterator();

		while (trueStatements.hasNext()) {
			Stmt statement = trueStatements.next();
			statement.codeGen(codeGen);
		}
		
		Instruction pushInstr2 = new PushLabelInstruction(endLabel.getName());
		codeGen.generateCode(pushInstr2);
		
		Instruction brInstr = new Instruction(Machine.BR, "BR");
		codeGen.generateCode(brInstr);
		
		codeGen.generateCode(falseLabel);
		
		while (falseStatements.hasNext()) {
			Stmt statement = falseStatements.next();
			statement.codeGen(codeGen);
		}
		
		codeGen.generateCode(endLabel);
		
		
	}
	
}
