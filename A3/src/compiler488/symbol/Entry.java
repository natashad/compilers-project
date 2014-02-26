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
		Procedure
	}
	
	Kind kind;
	String name;
	AST node;
	Integer orderNumber;
	
	public Entry(Kind kind, String name, AST node) {
		this.kind = kind;
		this.name = name;
		this.node = node;
	}
	
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
}