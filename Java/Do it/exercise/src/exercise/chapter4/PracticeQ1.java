package exercise.chapter4;;

public class PracticeQ1 {

	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 2;
		int result = 0;
		char operator = '+';
		
		if (operator == '+') {
			result = num1 + num2;
			
		}
		else if (operator == '-') {
			result = num1 - num2;
		}
		else if (operator == '*') {
			result = num1 * num2;
		}
		else if (operator == '/') {
			result = num1 / num2;
		}
		/* else {
			String result = "'+', '-', '*', '/'기호를 사용해주세요."; 
		} */
		System.out.println(result);
	}

}
