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
    private int lineNum;
	public ScalarDeclPart(String name, int lineNum) {
		super(name, lineNum);
		this.lineNum = lineNum;

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
	public void semanticCheck(Semantics semantics) {
		Entry entry = new Entry(Entry.Kind.Scalar, this.getName(), this);
		semantics.addToCurrScope(this.getName(), entry, this.lineNum);
	}
}
