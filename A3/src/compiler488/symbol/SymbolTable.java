package compiler488.symbol;

import java.util.HashMap;
/** Symbol Table
 *  This almost empty class is a framework for implementing
 *  a Symbol Table class for the CSC488S compiler
 *  
 *  Each implementation can change/modify/delete this class
 *  as they see fit.
 *
 *  @author  <B> PUT YOUR NAMES HERE </B>
 */
/* extending hash map gives us all the methods needed*/
public class SymbolTable extends HashMap<String, Entry> {

	/** Symbol Table  constructor
         *  Create and initialize a symbol table 
	 */
	private Integer orderNumber;
	private Integer lexicLevel;
	public SymbolTable (){
		super();
		this.orderNumber = 0;
	}

	@Override
	public Entry put(String key, Entry value) {
		value.setOrderNumber(this.orderNumber);
		value.setLexicLevel(this.lexicLevel);
		this.orderNumber += 1;
		return super.put(key, value);
	}

	
	public Integer getLexicLevel() {
		return lexicLevel;
	}

	public void setLexicLevel(Integer lexicLevel) {
		this.lexicLevel = lexicLevel;
	}

	/**  Initialize - called once by semantic analysis  
	 *                at the start of  compilation     
	 *                May be unnecessary if constructor
 	 *                does all required initialization	
	 */
	public void Initialize() {
	   /**   Initialize the symbol table             
	    *	Any additional symbol table initialization
	    *  GOES HERE                                	
	    */
		
		
	}

	/**  Finalize - called once by Semantics at the end of compilation
	 *              May be unnecessary 		
	 */
	public void Finalize(){
	
	  /**  Additional finalization code for the 
	   *  symbol table  class GOES HERE.
	   *  
	   */
	}
	/** The rest of Symbol Table
	 *  Data structures, public and private functions
 	 *  to implement the Symbol Table
	 *  GO HERE.				
	 */
}
