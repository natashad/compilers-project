package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;

/**
 * Represents a literal integer constant.
 */
public class IntConstExpn extends ConstExpn
    {
    private Integer value;	// The value of this literal.
    private Type type;
    
    public IntConstExpn(Integer value) {
    	super();
    	this.value = value;
    }

    /** Returns a string representing the value of the literal. */
    @Override
	public String toString () { return value.toString (); }

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//S21
		//Set type to Integer.
		this.type = new IntegerType();
	}
	
	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}
}
