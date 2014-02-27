package compiler488.semantics;

public class SemanticError extends Exception {

	private int lineNum;
	
	public SemanticError(String msg, int lineNum) {
		super(msg);
		this.lineNum = lineNum;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	
	
}
