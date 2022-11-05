package exercise.chapter4;

public class PracticeQ1_2 {

	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 2;
		int result = 0;
		char operator = '+';
		
		switch (operator) {
		case '+' : result = num1 + num2;
		break;
		case '-' : result = num1 - num2;
		break;
		case '*' : result = num1 * num2;
		break;
		case '/' : result = num1 / num2;
		break;
		}
		System.out.println(result);
	}

}
