package compiler488.symbol;
import compiler488.ast.*;
import compiler488.ast.type.Type;
/* Class representing a symbol table entry */

public class Entry {
	
//	String kind {};
	public enum Kind {
		Scalar, 
		Array,
		Function,
		Procedure,
		ForwardFunction,
		Parameter,
		ForwardProcedure
	}
	
	private Kind kind;
	private String name;
	private AST node;
	private Integer orderNumber;
	private Integer lexicLevel;
	//Associate type with entry if possible.
	private Type type; //Integer Boolean 
	
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AST getNode() {
		return node;
	}

	public void setNode(AST node) {
		this.node = node;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public Entry(Kind kind, String name, AST node) {
		this.kind = kind;
		this.name = name;
		this.node = node;
	}
	
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getLexicLevel() {
		return lexicLevel;
	}

	public void setLexicLevel(Integer lexicLevel) {
		this.lexicLevel = lexicLevel;
	}
}
