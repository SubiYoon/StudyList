package lambda;

interface PrintString{
	void showString(String str);
}

public class TestLambda {
	public static void main(String[] args) {
		PrintString lambda = s -> System.out.println(s);
		lambda.showString("hello lambda_1");
		showMyString(lambda);
		
		PrintString reStr = returnString();
		reStr.showString("Hello ");
	}
	
	public static void showMyString(PrintString p) {
		p.showString("hello lambda_2");
	}

	public static PrintString returnString() {
		return s -> System.out.println(s + "World");
	}
}
