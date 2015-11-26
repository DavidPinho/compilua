package tree;

import main.MIPSPrinter;

public class Assign extends Comando {


	public Assign(String id, Exp exp) {
		this.left = new Identifier(id);
		this.right = exp;
	}
	
	public void cgen() {
		this.left.cgen();//declaracao da variavel
		this.right.cgen();//exp
		//atribuicao do valor da exp na variavel
		MIPSPrinter.print("sw $a0, "+
		((Identifier)this.left).getValue(), 't');
	}

	@Override
	public void print() {
		System.out.print("(ASSIGNMENT ");
		this.left.print();
		System.out.print(",");
        this.right.print();
        System.out.print(")");
		
	}
}