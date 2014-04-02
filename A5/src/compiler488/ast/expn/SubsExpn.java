package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.ast.decl.ArrayDeclPart;
import compiler488.ast.type.IntegerType;
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
		IntegerType type = new IntegerType();
		if (!this.subscript1.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Subscript expression not of type integer", this.getLineNumber());
			semantics.errorList.add(error);
		}
		if (this.subscript2 != null ) {
			this.subscript2.semanticCheck(semantics);

			if (!this.subscript2.getType().getClass().equals(type.getClass())) {
				SemanticError error = new SemanticError("Subscript expression not of type integer", this.getLineNumber());
				semantics.errorList.add(error);
			}
		}
		Entry entry = semantics.allScopeLookup(this.variable);
		if (entry == null ) {
			SemanticError error = new SemanticError("Variable not previously declared", this.getLineNumber());
			semantics.errorList.add(error);
			
		}else if (entry.getKind() != Kind.Array) { 
			SemanticError error = new SemanticError("Variable not of type array", this.getLineNumber());
			semantics.errorList.add(error);
		}else{
			ArrayDeclPart array = (ArrayDeclPart) entry.getNode();
			if (array.getIsTwoDimensional() && this.subscript2 == null) {
				SemanticError error = new SemanticError("Array variable is not 2 dimensional.", this.getLineNumber());
				semantics.errorList.add(error);
			}
 			this.setType(entry.getType());
		}		
	}
}
