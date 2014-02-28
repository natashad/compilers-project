package compiler488.ast.stmt;

import compiler488.ast.ASTList;
import compiler488.ast.Printable;
import compiler488.ast.expn.Expn;
import compiler488.ast.expn.NewlineConstExpn;
import compiler488.ast.expn.TextConstExpn;
import compiler488.ast.type.IntegerType;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * The command to write data on the output device.
 */
public class PutStmt extends Stmt {
	private ASTList<Printable> outputs; // The objects to be printed.

	public PutStmt (ASTList<Printable> outputs, int lineNum) {
		super(lineNum);
		this.outputs = outputs;
	}
	
	/** Returns a description of the <b>put</b> statement. */
	@Override
	public String toString() {
		return "put " + outputs;
	}

	public ASTList<Printable> getOutputs() {
		return outputs;
	}

	public void setOutputs(ASTList<Printable> outputs) {
		this.outputs = outputs;
	}
	
	@Override
	public void semanticCheck(Semantics semantics) {
		//Text, newline, expn --> 
		for (Printable p : outputs) {
			if (p instanceof Expn) {
				((Expn) p).semanticCheck(semantics);
				if ((p instanceof TextConstExpn) || (p instanceof NewlineConstExpn)) {
					continue;
				}else if ( ! (((Expn)p).getType() instanceof IntegerType)) {
					System.out.println(((Expn)p).getType());
					SemanticError error = new SemanticError("Variable output of Put must evaluate to an integer.", getLineNumber());
					semantics.errorList.add(error);
				}
				((Expn)p).semanticCheck(semantics);
			}
		}
	}
}
