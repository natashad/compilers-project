package compiler488.ast.stmt;

import compiler488.ast.ASTList;
import compiler488.ast.Readable;
import compiler488.ast.expn.IdentExpn;
import compiler488.ast.expn.SubsExpn;
import compiler488.ast.type.IntegerType;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * The command to read data into one or more variables.
 */
public class GetStmt extends Stmt {
	
	private ASTList<Readable> inputs; // A list of locations to put the values read.

	public GetStmt (ASTList<Readable> inputs, int lineNum) {
		super(lineNum);
		this.inputs = inputs;
	}
	
	/** Returns a string describing the <b>get</b> statement. */
	@Override
	public String toString() {
		return "get " + inputs;
	}

	public ASTList<Readable> getInputs() {
		return inputs;
	}

	public void setInputs(ASTList<Readable> inputs) {
		this.inputs = inputs;
	}
	
	@Override
	public void semanticCheck(Semantics semantics) {
		SemanticError error = new SemanticError("Input to Get must evaluate to integer", getLineNumber());
		for (Readable r: inputs) {
			if (r instanceof IdentExpn) {
				((IdentExpn)r).semanticCheck(semantics);
				if ( ! (((IdentExpn)r).getType() instanceof IntegerType) ) {
					semantics.errorList.add(error);
				}
			} else if (r instanceof SubsExpn) {
				((SubsExpn)r).semanticCheck(semantics);
				if ( ! (((SubsExpn)r).getType() instanceof IntegerType) ) {
					semantics.errorList.add(error);
				}
			}
		}
	}
}
