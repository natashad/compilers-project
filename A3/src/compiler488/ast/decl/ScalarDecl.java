package compiler488.ast.decl;

import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

/**
 * Represents the declaration of a variable when passed as parameter to function or procedure
 */

public class ScalarDecl extends Declaration {

	public ScalarDecl(String name, Type type) {
		super(name, type);
	}
	
	/**
	 * Returns a string describing the name and type of the object being
	 * declared.
	 */
	@Override
	public String toString() {
		return   name + " : " + type ;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics) throws Exception{
		Entry entry = new Entry(Kind.Scalar, this.name, this);
		semantics.addToCurrScope(this.name, entry);
	}
}
