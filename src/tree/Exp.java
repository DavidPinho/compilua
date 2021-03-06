package tree;
public class Exp extends Node {
	
	public Exp(){
		
	}
	public Exp(Exp exp) {
		this.left = exp;
		this.right = null;
	}
	
	public Exp(int unop, Exp exp){
		this.left = new OpUnaria(unop,exp);
		this.right = null;
	}
	
	public Exp(Exp exp1,int op,Exp exp2 ){
		this.left = new Opbin(exp1, op, exp2);
		this.right = null;
	}
	
	public void cgen() {
		if(this.left != null) {
			this.left.cgen();
		}
		if(this.right != null) {
			this.right.cgen();
		}
	}
	
	@Override
	public void print() {
		if(right!=null){
		  System.out.print("(EXP ");
          left.print();
          System.out.print(", ");
          right.print();
          System.out.print(")");
		}else{
			System.out.print("(EXP ");
	        left.print();	          
	        System.out.print(")");
		}
		
		
	}
	
}