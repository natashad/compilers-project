package compiler488.ast.expn;

import compiler488.ast.Printable;

/**
 * Represents the special literal constant associated with writing a new-line
 * character on the output device.
 */
public class NewlineConstExpn extends ConstExpn implements Printable {
	
	public NewlineConstExpn(int lineNum) {
		super(lineNum);
	}
	
	
	/** Returns the string <b>"newline"</b>. */
	@Override
	public String toString() {
		return " newline ";
	}
}
