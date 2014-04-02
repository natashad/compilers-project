package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.decl.Declaration;
import compiler488.ast.decl.RoutineDecl;
import compiler488.ast.type.Type;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.codegen.LabelInstruction;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.semantics.Semantics.ScopeType;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;
import compiler488.symbol.SymbolTable;

/**
 * Represents the declarations and instructions of a scope construct.
 */
public class Scope extends Stmt {
	private ASTList<Declaration> declarations; // The declarations at the top.
    public LinkedList<String> forwardDeclarations;
	private ASTList<Stmt> statements; // The statements to execute.	
	private SymbolTable symtable = new SymbolTable();
	private boolean isMajor = false;
	private ScopeType scopeType = ScopeType.Stmt;
	private Type functionScopeType = null;	
	private boolean isProgramScope = false;

	public Scope(ASTList<Declaration> declarations, ASTList<Stmt> stmts, int lineNumber) {
		super(lineNumber);
		this.declarations = declarations;
		this.statements = stmts;
		this.forwardDeclarations = new LinkedList<String>();
	}
	
	public LinkedList<String> getForwardDeclarations() {
		return forwardDeclarations;
	}

	public void setForwardDeclarations(LinkedList<String> forwardDeclarations) {
		this.forwardDeclarations = forwardDeclarations;
	}

	
	public Type getFunctionScopeType() {
		return functionScopeType;
	}

	public void setFunctionScopeType(Type functionScopeType) {
		this.functionScopeType = functionScopeType;
	}
	
	
	public ScopeType getScopeType() {
		return scopeType;
	}

	
	
	public void setMajor(boolean isMajor) {
		this.isMajor = isMajor;
	}

	public boolean isProgramScope() {
		return isProgramScope;
	}

	public void setProgramScope(boolean isProgramScope) {
		this.isProgramScope = isProgramScope;
	}

	public Scope(int lineNumber) {
		this(new ASTList<Declaration>(), new ASTList<Stmt>(), lineNumber);
	}
	

	public Scope(ASTList<Stmt> stmt, int lineNumber) {
		this(new ASTList<Declaration>(), stmt, lineNumber);
	}

	
	public void setScopeType(ScopeType type) {
		this.scopeType = type;
	
	}
	
	public SymbolTable getSymtable() {
		return symtable;
	}

	public void setSymtable(SymbolTable symtable) {
		this.symtable = symtable;
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
	
	public boolean getIsMajor() {
		return isMajor;
	}
	
	public void setIsMajor(boolean scopeType) {
		this.isMajor= scopeType; 
	}
	
	/* Run semantic analysis */
	@Override
	public void semanticCheck(Semantics semantics) {
		semantics.openScope(symtable, this.scopeType, this);
		symtable = semantics.symbolTableList.getLast(); //make sure we are editing the master symbol table list! (pass by reference/value)
		ListIterator<Declaration> declarations = this.declarations.listIterator();
		ListIterator<Stmt> statements = this.statements.listIterator();
		
		while (declarations.hasNext()) {
			Declaration decl = declarations.next();
			decl.semanticCheck(semantics);
		}
		while (statements.hasNext()) {
			Stmt statement = statements.next();
			statement.semanticCheck(semantics);
		}

		
		// add code for forward declarations!
		if (this.forwardDeclarations.size() != 0) {
			SemanticError error = new SemanticError("Forward declared function/procedure never actually declared.", this.getLineNumber());
			semantics.errorList.add(error);
		}
		
		semantics.closeScope();
	}

	public void codeGen(CodeGen codeGen) {
		
		LinkedList<SymbolTable> tables = codeGen.getScopeSymbolTables();  //Get the current list of scopes
		tables.addLast(this.symtable);  		// Add the current scope to the list of scopes
		codeGen.setScopeSymbolTables(tables); // Set the codeGen scope array to this new array
		LabelInstruction label = new LabelInstruction("Scope");
		
		ListIterator<Declaration> declarations = this.declarations.listIterator();
		while (declarations.hasNext()) {
			Declaration decl = declarations.next();
			if (decl instanceof RoutineDecl) {
				((RoutineDecl) decl).getRoutineBody().getBody().codeGen(codeGen);
			}
		}
		
		if (isMajor) {
			if (isProgramScope()) {
				//Set MSP
				label.setName("Start");
			}
			codeGen.generateCode(label);
		}
		
		// Do this to reset the "next" pointer
		declarations = this.declarations.listIterator();
		while (declarations.hasNext()) {
			Declaration decl = declarations.next();
			if ( !(decl instanceof RoutineDecl) ) {
				decl.codeGen(codeGen);
			}
		}
		
		ListIterator<Stmt> statements = this.statements.listIterator();
		while (statements.hasNext()) {
			Stmt statement = statements.next();
			statement.codeGen(codeGen);
		}
		
		if (!isProgramScope() && isMajor) {
			Set<Entry> allEntries = (Set<Entry>) symtable.values();
			Iterator<Entry> entryIterator = allEntries.iterator();
			int countOfEntries = 0;
			while(entryIterator.hasNext()) {
				Entry e = entryIterator.next();
				if (e.getKind() == Kind.Scalar ||
					e.getKind() == Kind.Array) {
					countOfEntries++;
				}
			}
			ArrayList<Short> args = new ArrayList<Short>();
			args.add((short)countOfEntries);
			Instruction pushNInstruction = new Instruction(4, "PUSH", -1, -1);
			Instruction popNInstruction = new Instruction(8, "POPN", -1, -1);
			codeGen.generateCode(pushNInstruction);
			codeGen.generateCode(popNInstruction);
		}
		tables = codeGen.getScopeSymbolTables();
		tables.removeLast();
		codeGen.setScopeSymbolTables(tables);
		
		
	}
	
	
}
