package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

/**
 *  References to a scalar variable.
 */
public class IdentExpn extends Expn implements Readable
    {
    private String ident;  	// name of the identifier
    
    public IdentExpn(String ident, int lineNum) {
    	super(lineNum);
    	this.ident = ident;
    }
    
    /**
     * Returns the name of the variable or function.
     */
    @Override
	public String toString () { return ident; }

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//S26
		//Set type to the the type of variable in the entry table.
		Entry entry = semantics.allScopeLookup(this.ident);
		if (entry != null ) {
			this.setType(entry.getType());
		} else {
			SemanticError error = new SemanticError(this.ident + " has not been declared.", getLineNumber());
			semantics.errorList.add(error);
		}
	}

}
