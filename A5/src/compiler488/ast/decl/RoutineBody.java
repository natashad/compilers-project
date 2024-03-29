package compiler488.ast.decl;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.stmt.Scope;
import compiler488.codegen.CodeGen;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;
import compiler488.symbol.SymbolTable;

/**
 * Represents the parameters and instructions associated with a
 * function or procedure.
 */
public class RoutineBody extends Indentable {
	private ASTList<ScalarDecl> parameters; // The formal parameters of the routine.

	private Scope body; // Execute this scope when routine is called.
	
	public RoutineBody(ASTList<ScalarDecl> parameters, Scope body, int lineNum) {
		super(lineNum);
		this.parameters = parameters;
		this.body = body;
		SymbolTable symTable = new SymbolTable();
		ListIterator<ScalarDecl> declarations = this.parameters.listIterator();
		Entry entry;
		while (declarations.hasNext()) {
			ScalarDecl decl = declarations.next();
			entry = new Entry(Kind.Parameter, decl.name, decl);
			entry.setType(decl.getType());
			symTable.put(decl.name, entry);
		}
		this.body.setSymtable(symTable);
		
	}
	
	/**
	 * Print a description of the formal parameters and the scope for this
	 * routine.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		if (parameters != null)
			out.println("(" + parameters + ")");
		else
			out.println(" ( ) ");
		body.printOn(out, depth);
	}

	public Scope getBody() {
		return body;
	}

	public void setBody(Scope body) {
		this.body = body;
	}

	public ASTList<ScalarDecl> getParameters() {
		return parameters;
	}

	public void setParameters(ASTList<ScalarDecl> parameters) {
		this.parameters = parameters;
	}

	
}
