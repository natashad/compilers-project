package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

/**
 *  References to a scalar variable.
 */
public class IdentExpn extends Expn implements Readable
    {
    private String ident;  	// name of the identifier
    private Type type;
    
    public IdentExpn(String ident) {
    	super();
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
		this.type = entry.getType();
	}
	
	/** 
	 * Set the type to the variable in the symbol table.
	 * */
	@Override
	public Type getType() {
		return this.type;
	}
}
