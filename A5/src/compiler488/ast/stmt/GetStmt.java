package compiler488.ast.stmt;

import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Readable;
import compiler488.ast.expn.Expn;
import compiler488.ast.expn.IdentExpn;
import compiler488.ast.expn.SubsExpn;
import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * The command to read data into one or more variables.
 */
public class GetStmt extends Stmt {
	
	private ASTList<IdentExpn> inputs; // A list of locations to put the values read.

	public GetStmt (ASTList<IdentExpn> inputs, int lineNum) {
		super(lineNum);
		this.inputs = inputs;
	}
	
	/** Returns a string describing the <b>get</b> statement. */
	@Override
	public String toString() {
		return "get " + inputs;
	}

	public ASTList<IdentExpn> getInputs() {
		return inputs;
	}

	public void setInputs(ASTList<IdentExpn> inputs) {
		this.inputs = inputs;
	}
	
	@Override
	public void semanticCheck(Semantics semantics) {
		ListIterator<IdentExpn> inputs = this.inputs.listIterator();
		while (inputs.hasNext()) {
			IdentExpn input = inputs.next();
			input.semanticCheck(semantics);
			Type type = new IntegerType(this.getLineNumber());
			if (!input.getType().getClass().equals(type.getClass())) {
				SemanticError error = new SemanticError("Get Statement variable not of type Integer.", this.getLineNumber());
				semantics.errorList.add(error);
			}
		}
	}
}
