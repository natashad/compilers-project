package compiler488.symbol;
import compiler488.ast.*;
/* Class representing a symbol table entry */

public class Entry {
	
//	String kind {};
	public enum Kind {
		Scalar, 
		Variable,
		Array,
		Function,
		Procedure,
		ForwardFunction, 
		ForwardProcedure
	}
	
	private Kind kind;
	private String name;
	private AST node;
	private Integer orderNumber;
	
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
}
