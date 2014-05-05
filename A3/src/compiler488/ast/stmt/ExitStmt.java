package compiler488.ast.stmt;

import compiler488.ast.expn.Expn;
import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.codegen.CodeGen;
import compiler488.codegen.Instruction;
import compiler488.codegen.LabelInstruction;
import compiler488.codegen.PushLabelInstruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticError;
import compiler488.semantics.Semantics;

/**
 * Represents the command to exit from a loop.
 */

public class ExitStmt extends Stmt {

	// condition for 'exit when'
    private Expn expn = null;
    private LabelInstruction exitLabel;
    
    public ExitStmt(Expn expn, int lineNum) {
    	super(lineNum);
    	this.expn = expn;
    }
    
    public void setExitLabel(LabelInstruction exitLabel) {
    	this.exitLabel = exitLabel;
    }
    
    public LabelInstruction getExitLabel() {
    	return this.exitLabel;
    }

	/** Returns the string <b>"exit"</b> or <b>"exit when e"</b>" 
	*/
	@Override
	public String toString() {
		  {
		    String stmt = "exit " ;
                    if( expn != null )
		        stmt = stmt + "when " + expn + " " ;
		    return stmt ;
		  }
	}

	public Expn getExpn() {
		return expn;
	}

	public void setExpn(Expn expn) {
		this.expn = expn;
	}
	public void semanticCheck(Semantics semantic) {
		if (!semantic.inLoop()) {
			SemanticError error = new SemanticError("Exit statement outside of loop", this.getLineNumber());
			semantic.errorList.add(error);
		}
		if (this.expn != null) {
			Type type = new BooleanType(this.getLineNumber());
			expn.semanticCheck(semantic);
			if (!this.expn.getType().getClass().equals(type.getClass())) {
				SemanticError error = new SemanticError("Exit condition is not of type Boolean", this.getLineNumber());
				semantic.errorList.add(error);
			}
		}
		
	}
	
	@Override
	public void codeGen(CodeGen codeGen) {
		if (expn != null) {
			expn.codeGen(codeGen);
			
			Instruction pushInstr = new Instruction(Machine.PUSH, "PUSH", 1);
			codeGen.generateCode(pushInstr);
			
			Instruction swapInstr = new Instruction(Machine.SWAP, "SWAP");
			codeGen.generateCode(swapInstr);
			
			Instruction subInstr = new Instruction(Machine.SUB, "SUB");
			codeGen.generateCode(subInstr);
			
			if (exitLabel == null) {
				System.err.println("NO EXIT LABEL ASSIGNMENT");
			}
			
			Instruction pushLabelInstr = new PushLabelInstruction(exitLabel.getName());
			codeGen.generateCode(pushLabelInstr);
			
			Instruction bfInstr = new Instruction(Machine.BF, "BF");
			codeGen.generateCode(bfInstr);
			
		} else {
			if (exitLabel == null) {
				System.err.println("NO EXIT LABEL ASSIGNMENT");
			}
			
			Instruction pushLabelInstr = new PushLabelInstruction(exitLabel.getName());
			codeGen.generateCode(pushLabelInstr);
			
			Instruction bfInstr = new Instruction(Machine.BF, "BF");
			codeGen.generateCode(bfInstr);
		}
	}
	
}
