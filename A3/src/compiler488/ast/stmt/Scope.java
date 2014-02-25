package compiler488.ast.stmt;

import java.io.PrintStream;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.decl.Declaration;

/**
 * Represents the declarations and instructions of a scope construct.
 */
public class Scope extends Stmt {
	private ASTList<Declaration> declarations; // The declarations at the top.

	private ASTList<Stmt> statements; // The statements to execute.

	public Scope(ASTList<Declaration> declarations, ASTList<Stmt> stmts) {
		this.declarations = declarations;
		this.statements = stmts;
	}
	
	public Scope() {
		this.declarations = new ASTList<Declaration>();
		this.statements = new ASTList<Stmt>();
	}
	
	public Scope(ASTList<Stmt> stmt) {
		this.declarations = new ASTList<Declaration>();
		this.statements = stmt;
	}

	/**
	 * Print a description of the <b>scope</b> construct.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOnLn(out, depth, "{");
		Indentable.printIndentOnLn(out, depth, "declarations");

		declarations.printOnSeperateLines(out, depth + 1);

		Indentable.printIndentOnLn(out, depth, "statements");

		statements.printOnSeperateLines(out, depth + 1);

		Indentable.printIndentOnLn(out, depth, "}");
	}

	public ASTList<Declaration> getDeclarations() {
		return declarations;
	}

	public ASTList<Stmt> getStatements() {
		return statements;
	}

	public void setDeclarations(ASTList<Declaration> declarations) {
		this.declarations = declarations;
	}

	public void setStatements(ASTList<Stmt> statements) {
		this.statements = statements;
	}
	
	/* Run semantic analysis */
	public void semanticCheck(){
		//Add semantic analysis code here
		//Initialize symbol table too (I think)
	}

}
