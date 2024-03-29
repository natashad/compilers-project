package compiler488.ast.decl;

import compiler488.ast.expn.IntConstExpn;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;
import compiler488.symbol.Entry;
import compiler488.symbol.Entry.Kind;

/**
 * Holds the declaration part of an array.
 */
public class ArrayDeclPart extends DeclarationPart {

	/* The lower and upper boundaries of the array. */
    private Integer lb1, ub1, lb2, ub2;
	private Boolean isTwoDimensional = false ;


	/* The number of objects the array holds. */
	private Integer size;
	
	public ArrayDeclPart(String name, IntConstExpn[] firstBounds, IntConstExpn[] secondBounds, int lineNum) {
		super(name, lineNum);
		
		this.lb1 = firstBounds[0].getValue();
		if (firstBounds.length == 2) {
			this.ub1 = firstBounds[1].getValue();
		}
		
		if (secondBounds.length == 2) {
			this.lb2 = secondBounds[0].getValue();
			this.ub2 = secondBounds[1].getValue();
		}
		else if (secondBounds.length == 1) {
			this.lb2 = secondBounds[0].getValue();
		}
		
		// If we provide a second lower bounds, then it 
		// is 2 dimensional
		if (lb2 != null) {
			isTwoDimensional = true;
		}
				
		if (isTwoDimensional) {
			size = getArrayDimension(lb1, ub1) * getArrayDimension(lb2, ub2);
		} else {
			size = getArrayDimension(lb1, ub1);
		}
		
	}
	
	private Integer getArrayDimension(Integer lb1, Integer ub1) {
		Integer dim;
		if (ub1 == null) {
			// if we only provide one bound then that bound == size.
			dim = lb1;
		} else {
			// e.g. a[-2..1] has size 4.
			dim = Math.abs(ub1 - lb1) + 1;
		}
		return dim;
	}

	/**
	 * Returns a string that describes the array.
	 */
	@Override
	public String toString() {
		return name + "[" + lb1 + ".." + ub1 +
		( isTwoDimensional ?  "," + lb2 + ".." + ub2 : "" )
		+ "]";
	}

	public Integer getSize() {
		return size;
	}

	public Boolean getIsTwoDimensional() {
		return this.isTwoDimensional;
	}
	
	public Integer getLowerBoundary1() {
		return lb1;
	}

	public Integer getUpperBoundary1() {
		return ub1;
	}

        public void setLowerBoundary1(Integer lb1) {
		this.lb1 = lb1;
	}

        public void setUpperBoundary1(Integer ub1) {
		this.ub1 = ub1;
	}

	public Integer getLowerBoundary2() {
		assert isTwoDimensional ;	// check for misuse
		return lb2;
	}

	public Integer getUpperBoundary2() {
		assert isTwoDimensional ;       // check for misuse
		return ub2;
	}

        public void setLowerBoundary2(Integer lb2) {
                this.isTwoDimensional = true ;
		this.lb2 = lb2;
	}

        public void setUpperBoundary2(Integer ub2) {
	        this.isTwoDimensional = true ;
		this.ub2 = ub2 ;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	/** 
	 * Do semantic analysis
	 * */
	@Override
	public void semanticCheck(Semantics semantics){
		
		
		//S46 - Check the array bounds.
		if (this.ub1 != null && this.lb1 > this.ub1) {
			SemanticError error = new SemanticError("Lower bound of array " + this.name + " is bigger then upper bound", getLineNumber());
			semantics.errorList.add(error);
		}
		if (isTwoDimensional) {
			//S48
			if (this.ub2 != null && this.lb2 > this.ub2) {
				SemanticError error = new SemanticError("Lower bound of array " + this.name + " is bigger then upper bound", getLineNumber());
				semantics.errorList.add(error);
			}
		}
		//S19
		Entry entry = new Entry(Kind.Array, this.name, this);
		semantics.addToCurrScope(this.name, entry, getLineNumber());
				
	}
}
