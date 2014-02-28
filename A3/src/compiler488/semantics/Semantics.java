package compiler488.semantics;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
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
		this.errorList = new LinkedList<SemanticError>();
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
	public Entry curScopeLookup(String name) {
		ScopeType curScope = this.scopeStack.lastElement();
		if (curScope == ScopeType.Function || curScope == ScopeType.Procedure || curScope == ScopeType.Program) {
			return this.symbolTableList.getLast().get(name);
		}
		Integer count = this.scopeStack.size() - 2;
		while (count >= 0) {
			ScopeType scope = this.scopeStack.get(count);
			if (scope == ScopeType.Function || scope == ScopeType.Procedure || scope == ScopeType.Program) {
				if (this.symbolTableList.get(count).containsKey(name)) {
					return this.symbolTableList.get(count).get(name);
				}else {
					break;
				}
			}
			count -= 1;
		}
		return null;	
	}
	
	/** Iterate through all the scopes to find Entry. If no entry return null. */
	public Entry allScopeLookup(String name) {
		Iterator<SymbolTable> listTables = this.symbolTableList.descendingIterator();
		while (listTables.hasNext()) {
				SymbolTable symtable =  listTables.next();
				if (symtable.containsKey(name)) {
					return symtable.get(name);
				}
		}
		return null;
	}
	
	public Semantics.ScopeType getCurrScopeType() {
		return this.scopeStack.lastElement();
	}
	
	
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
	
	
	public void openScope(SymbolTable symtable, ScopeType type) {
		this.scopeStack.push(type);
		this.symbolTableList.addLast(symtable); 
		
	
	}
	
	public void closeScope() {
		this.scopeStack.pop();
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
	
	
	/**
	 *  Perform one semantic analysis action
         *  @param  actionNumber  semantic analysis action number
         */
//	void semanticAction( int actionNumber ) {
//
//		if( traceSemantics ){
//			if(traceFile.length() > 0 ){
//		 		//output trace to the file represented by traceFile
//		 		try{
//		 			//open the file for writing and append to it
//		 			File f = new File(traceFile);
//		 		    Tracer = new FileWriter(traceFile, true);
//		 				          
//		 		    Tracer.write("Sematics: S" + actionNumber + "\n");
//		 		    //always be sure to close the file
//		 		    Tracer.close();
//		 		}
//		 		catch (IOException e) {
//		 		  System.out.println(traceFile + 
//					" could be opened/created.  It may be in use.");
//		 	  	}
//		 	}
//		 	else{
//		 		//output the trace to standard out.
//		 		System.out.println("Sematics: S" + actionNumber );
//		 	}
//		 
//		}
//	                     
//	   /*************************************************************/
//	   /*  Code to implement each semantic action GOES HERE         */
//	   /*  This stub semantic analyzer just prints the actionNumber */   
//	   /*                                                           */
//       /*  FEEL FREE TO ignore or replace this procedure            */
//	   /* *********************************************************** */
//	                     
//	   System.out.println("Semantic Action: S" + actionNumber  );
//	   return ;
//	} 

	// ADDITIONAL FUNCTIONS TO IMPLEMENT SEMANTIC ANALYSIS GO HERE

}
