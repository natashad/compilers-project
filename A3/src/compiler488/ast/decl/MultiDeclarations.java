package compiler488.ast.decl;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.type.Type;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

/**
 * Holds the declaration of multiple elements.
 */
public class MultiDeclarations extends Declaration {
	/* The elements being declared */
	private ASTList<DeclarationPart> elements;
//	private Type type;

	public MultiDeclarations (ASTList<DeclarationPart> declParts, Type type) {
		super(null, type);
//		this.type = type;
		elements = declParts;
	}
	
	/**
	 * Returns a string that describes the array.
	 */
	@Override
	public String toString() {
		
		return  " : " + type ;
	}


	/**
	 * Print the multiple declarations of the same type.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		out.println(elements);
		Indentable.printIndentOn (out, depth, this + " ");
	}


	public ASTList<DeclarationPart> getElements() {
		return elements;
	}

	public void setElements(ASTList<DeclarationPart> elements) {
		this.elements = elements;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		//S47 - Associating type with variables.
		ListIterator<DeclarationPart> declarations = elements.listIterator();
		while (declarations.hasNext()) {
			DeclarationPart decl = declarations.next();
			Entry entry = semantics.curScopeLookup(decl.getName());
			entry.setType(type);
		}
	}
}
