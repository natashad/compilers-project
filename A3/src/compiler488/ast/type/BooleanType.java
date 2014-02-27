package compiler488.ast.type;

/**
 * The type of things that may be true or false.
 */
public class BooleanType extends Type {
	
	public BooleanType(int lineNum) {
		super(lineNum);
	}
	
	//line number for this shouldn't actually matter
	public BooleanType() {
		this(-1);
	}

	
	/** Returns the string <b>"boolean"</b>. */
	@Override
	public String toString() {
		return "boolean";
	}
}
