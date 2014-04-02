package compiler488.ast.decl;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;

/**
 * The common features of declarations.
 */
public class Declaration extends Indentable {
	/** The type of thing being declared. */
	protected Type type = null;

	/** The name of the thing being declared. */
	protected String name;
	
	public Declaration(String name, Type type, int lineNum) {
		super(lineNum);
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	
	
}
