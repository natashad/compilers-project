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
    
    public IntConstExpn(Integer value, int lineNum) {
    	super(lineNum);
    	this.value = value;
    	this.setType(new IntegerType());
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
		this.setType(new IntegerType());
	}
	
}
