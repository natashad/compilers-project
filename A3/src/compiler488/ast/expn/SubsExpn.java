package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;

/**
 * References to an array element variable
 * 
 */
public class SubsExpn extends Expn implements Readable {

	private String variable; // name of the array variable
	private Expn   subscript1  ;	 // first subscript
    private Expn   subscript2 = null ;	// second subscript (if any)

    public SubsExpn(String variable, Expn sub1, Expn sub2, int lineNum) {
    	super(lineNum);
    	this.variable = variable;
    	this.subscript1 = sub1;
    	this.subscript2 = sub2;
    }
    
	/** Returns a string that represents the array subscript. */
	@Override
	public String toString() {
		return (variable + "[" + subscript1 +
		   ( subscript2 != null ? "," + subscript2 : "" )
		    + "]");
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Expn getSubscript1() {
		return subscript1 ;
	}

	public void setSubscript1(Expn subscript1) {
		this.subscript1 = subscript1;
	}

	public Expn getSubscript2() {
		return subscript2;
	}

	public void setSubscript2(Expn subscript2) {
		this.subscript2 = subscript2;
	}
	
	@Override
	public void semanticCheck(Semantics semantics) {
		Entry entry = semantics.allScopeLookup(this.variable);
		this.setType(entry.getType());
	}
}
