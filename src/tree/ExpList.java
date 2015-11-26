package tree;

public class ExpList extends Node {
	
	public ExpList(Exp e1, ExpList e2) {
		this.left=e1;
		this.right=e2;
	}

	@Override
	public void cgen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() {
		System.out.print("(EXPLIST ");
        left.print();
        if(this.right!=null){
	        System.out.print(", ");
	        right.print();
        }
        System.out.print(")");
		
	}

}