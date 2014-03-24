package compiler488.semantics;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import compiler488.ast.stmt.Scope;
import compiler488.symbol.Entry;
import compiler488.symbol.SymbolTable;

/** Implement semantic analysis for compiler 488 
 *  @author  <B> Put your names here </B>
 */
public class Semantics {
	
        /** flag for tracing semantic analysis */
	private boolean traceSemantics = false;
	/** file sink for semantic analysis trace */
	private String traceFile = new String();
	public FileWriter Tracer;
	public File f;
    
	//List of symbol tables (one for each scope)
	public LinkedList<SymbolTable> symbolTableList; 
    public LinkedList<SemanticError> errorList;
	public Stack<ScopeType> scopeStack;
	//Stack that holds the scope objects
	public Stack<Scope> scopeObjStack;
	private Integer lexicLevel;
    
    public enum ScopeType {
		Function, 
		If,
		Procedure,
		Loop,
		Stmt,
		Program
	}
     /** SemanticAnalyzer constructor */
	public Semantics (){
		this.symbolTableList = new LinkedList<SymbolTable>();
		this.scopeStack = new Stack<ScopeType>();
		this.scopeObjStack = new Stack<Scope>();
		this.errorList = new LinkedList<SemanticError>();
		this.lexicLevel = 0;
	}

	/**  semanticsInitialize - called once by the parser at the      */
	/*                        start of  compilation                 */
	public void Initialize() {
	
	   /*   Initialize the symbol table             */
		
	   //Symbol.Initialize();
	   
	   /*********************************************/
	   /*  Additional initialization code for the   */
	   /*  semantic analysis module                 */
	   /*  GOES HERE                                */
	   /*********************************************/
	   
	}
	
	
	/** Returns the symbol table entry if found in the major scope otherwise null */
	public Entry curMajorScopeLookup(String name) {
		Integer count = this.scopeStack.size() - 1;
		SymbolTable symtable;
		Entry entry;
		while (count >= 0) {
			ScopeType scope = this.scopeStack.get(count);
			symtable = this.symbolTableList.get(count);
			entry = symtable.get(name);
			if (entry != null) {
				return entry;
			}
			if (scope == ScopeType.Function || scope == ScopeType.Program || scope == ScopeType.Procedure) {
				return null;
			}
			count -= 1;
		}
		return null;
	}
	
	/** Iterate through all the scopes to find Entry. If no entry return null. */
	public Entry allScopeLookup(String name) {
		Integer count = this.symbolTableList.size() - 1;
		SymbolTable symtable;
		Entry entry;
		while (count >= 0) {
			ScopeType scope = this.scopeStack.get(count);
			symtable = this.symbolTableList.get(count);
			entry = symtable.get(name);
			if (entry != null) {
				return entry;
			}
			count -= 1;
		}
		return null;
	}
	/** Return the type of the current scope*/
	public Semantics.ScopeType getCurrScopeType() {
		return this.scopeStack.lastElement();
	}
	
	/** Return the scope Object of the current scope*/
	public Scope getCurrScopeObj() {
		return this.scopeObjStack.lastElement();
	}
	
	
	/** Return whether you are currently in a loop */
	public boolean inLoop() {
		Integer count = this.scopeStack.size() - 1;
		while (count >= 0) {
			ScopeType scope = this.scopeStack.get(count);
			if (scope == ScopeType.Function || scope == ScopeType.Program || scope == ScopeType.Procedure) {
				return false;
			}else if (scope == ScopeType.Loop) {
				return true;
			}
			count -= 1;
		}
		return false;
	}
	
	
	/** Return the type of the current major scope */
	public Semantics.ScopeType getCurrMajorScope() {
		Integer count = this.scopeStack.size() - 1;
		while (count >= 0) {
			ScopeType scope = this.scopeStack.get(count);
			if (scope == ScopeType.Function || scope == ScopeType.Program || scope == ScopeType.Procedure) {
				return scope;
			}
			count -= 1;
		}
		return null;
	}
	
	/** Return the object of the current major scope */
	public Scope getCurrMajorScopeObj() {
		Integer count = this.scopeStack.size() - 1;
		while (count >= 0) {
			ScopeType scope = this.scopeStack.get(count);
			if (scope == ScopeType.Function || scope == ScopeType.Program || scope == ScopeType.Procedure) {
				Scope scopeObj = this.scopeObjStack.get(count);
				return scopeObj;
			}
			count -= 1;
		}
		return null;
	}
	
	/** Remove the first symbol table entry found starting from the current scope
	 * and moving up to parent scopes  */
	public void remove(String name) {
		if (this.allScopeLookup(name) != null) {
			Iterator<SymbolTable> listTables = this.symbolTableList.descendingIterator();
			while (listTables.hasNext()) {
					SymbolTable symtable =  listTables.next();
					if (symtable.containsKey(name)) {
						symtable.remove(name);
						return;
					}
			}
		}
	}
	
	
	/** Add symbol to the current scope*/
	public void addToCurrScope(String name, Entry entry, int lineNum) {
		if (this.symbolTableList.getLast().containsKey(name)) {
			SemanticError error = new SemanticError("Symbol " + name + " has a previous declaration.", lineNum);
			this.errorList.add(error);
			return;
		} else {
			this.symbolTableList.getLast().put(name, entry);
		}
	}
	
	/** Open a scope */
	public void openScope(SymbolTable symtable, ScopeType type, Scope scopeObj) {
		this.lexicLevel += 1;
		this.scopeStack.push(type);
		this.scopeObjStack.push(scopeObj);
		symtable.setLexicLevel(this.lexicLevel);
		this.symbolTableList.addLast(symtable); 
		
	
	}
	/** Close a scope */
	public void closeScope() {
		this.lexicLevel -= 1;
		this.scopeStack.pop();
		this.scopeObjStack.pop();
		this.symbolTableList.removeLast();
	}
	
	/**  semanticsFinalize - called by the parser once at the        */
	/*                      end of compilation                      */
	void Finalize(){
	
	  /*  Finalize the symbol table                 */
	
	  // Symbol.Finalize();
	  
	   /*********************************************/
	  /*  Additional finalization code for the      */
	  /*  semantics analysis module                 */
	  /*  GOES here.                                */
	  /**********************************************/
	  
	}

}
