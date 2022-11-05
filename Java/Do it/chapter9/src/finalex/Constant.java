package finalex;

public class Constant {
	int num = 10;
	final int NUM = 100;
	
	public static void main(String[] args) {
		Constant cons = new Constant();
		cons.num = 50;
		cons.NUM = 200;		//NUM값은 final을 통해 상수로 선언하여 다른 값을 대입할 수 없다.
		
		System.out.println(cons.num);
		System.out.println(cons.NUM);
	}

}
