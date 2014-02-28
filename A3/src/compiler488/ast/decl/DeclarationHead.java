package compiler488.ast.decl;

import compiler488.ast.ASTList;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;


public class DeclarationHead {

	DeclarationPart declarationPart;
	ASTList<ScalarDecl> parameters;
	Type type;
	
	public DeclarationHead(DeclarationPart declPart, ASTList<ScalarDecl> params, Type type) {
		this.declarationPart = declPart;
		this.parameters = params;
		this.type = type;
	}
	
	public DeclarationPart getDeclarationPart() {
		return declarationPart;
	}

	public void setDeclarationPart(DeclarationPart declarationPart) {
		this.declarationPart = declarationPart;
	}

	public ASTList<ScalarDecl> getParameters() {
		return parameters;
	}

	public void setParameters(ASTList<ScalarDecl> parameters) {
		this.parameters = parameters;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
