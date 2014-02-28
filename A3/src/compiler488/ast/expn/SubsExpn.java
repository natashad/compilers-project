package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.ast.decl.ArrayDeclPart;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

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
		
		this.subscript1.semanticCheck(semantics);
		if (subscript2 != null) {
			this.subscript2.semanticCheck(semantics);
		}
		
		//S31
		//Check type of expressions is integer
		if (subscript1.getType() == null || subscript1.getType().toString().equals("integer")) {
			SemanticError error = new SemanticError("Type of expression for index " + this.subscript1.toString() + " is not Integer", getLineNumber());
			semantics.errorList.add(error);
		}
		
		if (subscript2 != null) {
			if (subscript2.getType() == null || subscript2.getType().toString().equals("integer")) {
				SemanticError error = new SemanticError("Type of expression for index " + this.subscript2.toString() + " is not Integer", getLineNumber());
				semantics.errorList.add(error);
			}
		}
		
	
		Entry entry = semantics.allScopeLookup(this.variable);
		if (entry == null) {
			//S39
			//Check if array is visible according to scope rule.
			SemanticError error = new SemanticError("Variable " + this.variable + " is not declared in the scope", getLineNumber());
			semantics.errorList.add(error);
		} else {
			//S38 and S55
			//Check array been declared as one dimensional or two dimensional
			if (entry.getKind() != Kind.Array) {
				SemanticError error = new SemanticError("Variable " + this.variable + " is not declared as array type", getLineNumber());
				semantics.errorList.add(error);
			} else {
				if (subscript2 != null && !((ArrayDeclPart)entry.getNode()).getIsTwoDimensional()) {
					SemanticError error = new SemanticError("Variable " + this.variable + " is not declared as a 2-D array type", getLineNumber());
					semantics.errorList.add(error);
				}
			}
			
			//S39
			//Set result type to type of array element
			this.setType(entry.getType());
		}
		
	}
}
