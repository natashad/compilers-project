package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;

/**
 * Boolean literal constants.
 */
public class BoolConstExpn extends ConstExpn
    {
    private boolean  value ;	/* value of the constant */
    private Type type;
    
    public BoolConstExpn(boolean value) {
    	super();
    	this.value = value;
    }
    
    /** Returns the value of the boolean constant */
    @Override
	public String toString () { 
	return ( value ? "(true)" : "(false)" );
    }

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//S20
		//Set type to Integer.
		this.type = new BooleanType();
	}
	
	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}
}
