package compiler488.ast.stmt;

import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Printable;
import compiler488.ast.decl.Declaration;
import compiler488.ast.expn.Expn;
import compiler488.ast.expn.NewlineConstExpn;
import compiler488.ast.expn.OutputExpn;
import compiler488.ast.expn.TextConstExpn;
import compiler488.ast.type.IntegerType;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * The command to write data on the output device.
 */
public class PutStmt extends Stmt {
	private ASTList<Expn> outputs; // The objects to be printed.

	public PutStmt (ASTList<Expn> outputs, int lineNum) {
		super(lineNum);
		this.outputs = outputs;
	}
	
	/** Returns a description of the <b>put</b> statement. */
	@Override
	public String toString() {
		return "put " + outputs;
	}

	public ASTList<Expn> getOutputs() {
		return outputs;
	}

	public void setOutputs(ASTList<Expn> outputs) {
		this.outputs = outputs;
	}
	
	@Override
	public void semanticCheck(Semantics semantics) {
		ListIterator<Expn> outputs = this.outputs.listIterator();
		while (outputs.hasNext()) {
			Expn output = outputs.next();
			if (output != null) {
				output.semanticCheck(semantics);
			}
		}
	}
}
