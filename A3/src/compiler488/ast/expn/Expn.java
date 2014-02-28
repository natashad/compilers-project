package compiler488.ast.expn;

import compiler488.ast.AST;
import compiler488.ast.Printable;
import compiler488.ast.type.Type;

/**
 * A placeholder for all expressions.
 */
public class Expn extends AST implements Printable {
	
	public enum OpSymbols {
		And("and"),
		Or("or"), 
		Equals("="),
		NotEquals("nxot ="),
		LessThan("<"),
		GreaterThan(">"),
		GreaterEqual(">="),
		LessEqual("<="),
		Not("not");
		
	    private final String name;       

	    private OpSymbols(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName){
	        return (otherName == null)? false:name.equals(otherName);
	    }

	    public String toString(){
	       return name;
	    }
	}
	
	public Expn(int lineNum) {
		super(lineNum);
	}
	
	//Return the type of variable.
	public Type getType() {
		return null;
	}
	
	
}
