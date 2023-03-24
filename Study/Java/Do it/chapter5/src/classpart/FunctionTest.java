package classpart;

public class FunctionTest {
	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 20;
		
		int sum = add(num1, num2);
		System.out.println(num1 + "+" + num2 + "=" + sum + "입니다.");
		
		int subtraction = subtraction(num1, num2);
		System.out.println(num1 + "-" + num2 + "=" + subtraction + "입니다.");
		
		int multiplication = multiplication(num1, num2);
		System.out.println(num1 + "*" + num2 + "=" + multiplication + "입니다.");
		
		double divide = divide(num1, num2);
		System.out.println(num1 + "/" + num2 + "=" + divide + "입니다.");
		}
	
	public static int add(int n1, int n2) {
		int result = n1 +n2;
		return result;
	}
		
	public static int subtraction(int n1, int n2) {
		int result = n1 -n2;
		return result;
	}	
		
	public static int multiplication(int n1, int n2) {
		int result = n1 *n2;
		return result;
	}	
		
	public static double divide(double n1, double n2) {
		double result = n1 /n2;
		return result;
		}

}
