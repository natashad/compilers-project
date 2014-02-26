package compiler488.semantics;

import java.io.*;
import java.util.LinkedList;

import compiler488.ast.stmt.Program;
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
     
     
     /** SemanticAnalyzer constructor */
	public Semantics (){
	}

	/**  semanticsInitialize - called once by the parser at the      */
	/*                        start of  compilation                 */
	void Initialize() {
	
	   /*   Initialize the symbol table             */
		symbolTableList = new LinkedList<SymbolTable>();
	   //Symbol.Initialize();
	   
	   /*********************************************/
	   /*  Additional initialization code for the   */
	   /*  semantic analysis module                 */
	   /*  GOES HERE                                */
	   /*********************************************/
	   
	}
	
	public void openScope(SymbolTable symtable) {
		symbolTableList.addLast(symtable); 
	}
	
	public void closeScope() {
		symbolTableList.removeLast();
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
