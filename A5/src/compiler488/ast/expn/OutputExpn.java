package compiler488.ast.expn;

import java.util.ListIterator;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

public class OutputExpn extends Expn {
	private Expn expn;
	public OutputExpn(Expn expn, int lineNum) {
		super(lineNum);
		this.expn = expn;
	}
	
	@Override
	public void semanticCheck(Semantics semantics) {
	
		this.expn.semanticCheck(semantics);
		Type type = new IntegerType(this.getLineNumber());
		if (this.expn.getType() == null || !this.expn.getType().getClass().equals(type.getClass())) {
			SemanticError error = new SemanticError("Output expression not of type integer.", this.getLineNumber());
			semantics.errorList.add(error);
		}
	}
	
	@Override
	public String toString() {
		return "\"" + this.expn.toString() + "\"";
	}

}
