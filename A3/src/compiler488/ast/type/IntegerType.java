package compiler488.ast.type;

import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;

/**
 * Used to declare objects that yield integers.
 */
public class IntegerType extends Type {
	
	public IntegerType(int lineNum) {
		super(lineNum);
	}
	
	//line number for this shouldn't actually matter
	public IntegerType() {
		super(-1);
	}
	
	/** Returns the string <b>"Integer"</b>. */
	@Override
	public String toString() {
		return "integer";
	}
	
}
