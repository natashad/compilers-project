package compiler488.ast.decl;

import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

/**
 * Represents the declaration of a simple variable.
 * This represent left side of declaration ..example: var variable := type
 * 
 */
public class ScalarDeclPart extends DeclarationPart {

	public ScalarDeclPart(String name) {
		super(name);
	}
	
	/**
	 * Returns a string describing the name of the object being
	 * declared.
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics) throws Exception{
		
		//S10
		Entry entry = new Entry(Kind.Variable, this.name, this);
		semantics.addToCurrScope(this.name, entry);
	}
}
