package tree;

import main.MIPSPrinter;

public class Opbin extends Node {
	private int opBin = -1;
	public Opbin(Exp e1, int op, Exp e2) {
		this.left = e1;
		this.right = e2;
		opBin = op;
	}
	
	public int getOpBin(){
		return opBin;
	}
	
	
	@Override
	public void cgen() {
		switch (opBin) {
		case 19:
			//PLUS_OP
			printArithmetic("add");		
			break;
		case 20:
			//MINUS_OP
			printArithmetic("sub");
			break;
		case 21:
			//TIMES_OP
			printArithmetic("mul");
			break;
		case 22: 
			//SLASH_OP
			printArithmetic("div");
			break;
		case 27:
			//LESS_OP
			printComparison("blt");
			break;
		case 25:
			//LESSEQUALS_OP
			printComparison("ble");
			break;
		case 28:
			//MORE_OP
			printComparison("bgt");
			break;
		case 26:
			//MOREEQUALS_OP
			printComparison("bge");
			break;
		case 29:
			//EQUALS_OP
			printComparison("beq");
			break;
		case 30:
			//DIFFERENT_OP
			printComparison("bne");
			break;
		case 31:
			//AND_KWORD
			this.left.left.left.cgen();
			MIPSPrinter.print("sw $a0, 0($sp)", 't');
			MIPSPrinter.print("addiu $sp, $sp, -4", 't');
			this.left.left.right.cgen();
			MIPSPrinter.print("lw $t1, 4($sp)", 't');
			MIPSPrinter.print("addiu $sp, $sp, 4", 't');				
			MIPSPrinter.print(getCommand(((Opbin)this.left.left).getOpBin())+" $t1, $a0, CONTINUEAND",'t');
			MIPSPrinter.print("b ENDAND", 't');
			MIPSPrinter.print("", 't');
			MIPSPrinter.print("CONTINUEAND:", 't');
			MIPSPrinter.print("", 't');
			this.right.left.left.cgen();
			MIPSPrinter.print("sw $a0, 0($sp)", 't');
			MIPSPrinter.print("addiu $sp, $sp, -4", 't');
			this.right.left.right.cgen();
			MIPSPrinter.print("lw $t1, 4($sp)", 't');
			MIPSPrinter.print("addiu $sp, $sp, 4", 't');		
			int blocoNumber=0;
			if(MIPSPrinter.labelJump.equals("TRUE"))
				blocoNumber= MIPSPrinter.ifCount;
			else if (MIPSPrinter.labelJump.equals("WHILE")) {
				blocoNumber= MIPSPrinter.whileCount;
			}				
			MIPSPrinter.print(getCommand(((Opbin)this.right.left).getOpBin())+" $t1, $a0, "+MIPSPrinter.labelJump+Integer.toString(blocoNumber),'t');
			MIPSPrinter.print("", 't');
			MIPSPrinter.print("ENDAND:", 't');
			
			break;
		case 32:
			//OR_KWORD
			this.left.cgen();
			MIPSPrinter.print("sw $a0, 0($sp)", 't');
			MIPSPrinter.print("addiu $sp, $sp, -4", 't');
			this.right.cgen();
			MIPSPrinter.print("lw $t1, 4($sp)", 't');
			MIPSPrinter.print("addiu $sp, $sp, 4", 't');				
			MIPSPrinter.print("or $a0, $t1, $a0",'t');
			break;

		default:
			break;
		}
		
	}
	
	@Override
	public void print() {
		switch (opBin) {
		case 19:
			//PLUS_OP
			System.out.print("(PLUS ");	        
			break;
		case 20:
			//MINUS_OP
			System.out.print("(MINUS ");	
			break;
		case 21:
			//TIMES_OP
			System.out.print("(TIMES ");	
			break;
		case 22: 
			//SLASH_OP
			System.out.print("(SLASH ");	
			break;
		case 27:
			//LESS_OP
			System.out.print("(LESS_OP ");	
			break;
		case 25:
			//LESSEQUALS_OP
			System.out.print("(LESSEQUALS_OP ");	
			break;
		case 28:
			//MORE_OP
			System.out.print("(MORE_OP ");
			break;
		case 26:
			//MOREEQUALS_OP
			System.out.print("(MOREEQUALS_OP ");
			break;
		case 29:
			//EQUALS_OP
			System.out.print("(EQUALS_OP ");
			break;
		case 30:
			//DIFFERENT_OP
			System.out.print("(DIFFERENT_OP ");
			break;
		case 31:
			//AND_KWORD
			System.out.print("(AND_KWORD ");
			break;
		case 32:
			//OR_KWORD
			System.out.print("(OR_KWORD ");
			break;

		default:
			break;
		}
		left.print();
        System.out.print(", ");
        right.print();
        System.out.print(")");
		
	}
	
	private void printArithmetic(String op){
		this.left.cgen();
		MIPSPrinter.print("sw $a0, 0($sp)", 't');
		MIPSPrinter.print("addiu $sp, $sp, -4", 't');
		this.right.cgen();
		MIPSPrinter.print("lw $t1, 4($sp)", 't');
		MIPSPrinter.print(op+" $a0, $t1, $a0", 't');
		MIPSPrinter.print("addiu $sp, $sp, 4", 't');	
	}
	
	private void printComparison(String op){
		this.left.cgen();
		MIPSPrinter.print("sw $a0, 0($sp)", 't');
		MIPSPrinter.print("addiu $sp, $sp, -4", 't');
		this.right.cgen();
		MIPSPrinter.print("lw $t1, 4($sp)", 't');
		MIPSPrinter.print("addiu $sp, $sp, 4", 't');
		int blocoNumber=0;
		if(MIPSPrinter.labelJump.equals("TRUE"))
			blocoNumber= MIPSPrinter.ifCount;
		else if (MIPSPrinter.labelJump.equals("WHILE")) {
			blocoNumber= MIPSPrinter.whileCount;
		}
			
		MIPSPrinter.print(op+" $t1, $a0, "+MIPSPrinter.labelJump+Integer.toString(blocoNumber), 't');
	}
	
	private String getCommand(int opBin){
		switch (opBin) {
		case 19:
			//PLUS_OP
			return "add";		
		case 20:
			//MINUS_OP
			return "sub";			
		case 21:
			//TIMES_OP
			return "mul";			
		case 22: 
			//SLASH_OP
			return "div";			
		case 27:
			//LESS_OP
			return "blt";			
		case 25:
			//LESSEQUALS_OP
			return "ble";
		case 28:
			//MORE_OP
			return "bgt";			
		case 26:
			//MOREEQUALS_OP
			return "bge";			
		case 29:
			//EQUALS_OP
			return "beq";			
		case 30:
			//DIFFERENT_OP
			return "bne";
		
		default:
			break;
		}
		return null;
	}
	
	
}
