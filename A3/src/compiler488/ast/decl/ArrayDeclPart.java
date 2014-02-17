package compiler488.ast.decl;

import compiler488.ast.expn.IntConstExpn;

/**
 * Holds the declaration part of an array.
 */
public class ArrayDeclPart extends DeclarationPart {

	/* The lower and upper boundaries of the array. */
    private Integer lb1, ub1, lb2, ub2;
	private Boolean isTwoDimensional = false ;


	/* The number of objects the array holds. */
	private Integer size;
	
	public ArrayDeclPart(String name, IntConstExpn[] firstBounds, IntConstExpn[] secondBounds) {
		super(name);
		
		int totalLength = firstBounds.length + secondBounds.length;
		switch (totalLength) {
		case 1:
			this.lb1 = firstBounds[0].getValue();
		case 2:
			this.ub1 = firstBounds[1].getValue();
		case 3:
			this.lb2 = secondBounds[0].getValue();
		case 4:
			this.ub2 = secondBounds[1].getValue();
		default:
			break;
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
}
