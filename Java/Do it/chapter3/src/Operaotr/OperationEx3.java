package Operaotr;

public class OperationEx3 {

	public static void main(String[] args) {
		int num1 = 10;
		int i = 2;
		
		boolean value = ((num1 = num1 + 10) < 10) && ((i = i+2) < 10);
		//논리곱에서 앞 항의 결과값이 거짓이므로 논리곱 뒷부분은 실행되지 않음
		System.out.println(value);
		System.out.println(num1);
		System.out.println(i);
		
		value = ((num1 = num1 + 10) > 10) || ((i = i+2) < 10);
		//논리합에서 앞항의 결과값이 참이므로 논리합 뒷부분은 실행되지 않음
		System.out.println(value);
		System.out.println(num1);
		System.out.println(i);
	}

}
