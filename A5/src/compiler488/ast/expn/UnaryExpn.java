package compiler488.ast.expn;


/**
 * The common features of unary expressions.
 */
public class UnaryExpn extends Expn
    {
    Expn  operand ;	/* operand of the unary  operator. */
    String opSymbol;	/* Name of the operator. */

    public UnaryExpn (Expn operand, String opSymbol, int lineNum) {
    	super(lineNum);
    	this.operand = operand;
    	this.opSymbol = opSymbol;
    }
    
    /** Returns a string that represents the unary expression. */
    @Override
	public String toString ()
	{
	return ( opSymbol + "(" + operand + ")");
    }

	public Expn getOperand() {
		return operand;
	}

	public void setOperand(Expn operand) {
		this.operand = operand;
	}

	public String getOpSymbol() {
		return opSymbol;
	}

	public void setOpSymbol(String opSymbol) {
		this.opSymbol = opSymbol;
	}
}
