package interfaceex;

public class CalculatorTest {
	public static void main(String[] args) {
		int num = 300;
		int num1 = 10;
		int num2 = 0;
		
		CompleteCalc calc = new CompleteCalc();
		System.out.println(calc.add(num1, num2));
		System.out.println(calc.substract(num1, num2));
		System.out.println(calc.times(num1, num2));
		System.out.println(calc.divide(num1, num2));
		System.out.println(calc.square(num));
		calc.showInfo();
		
		calc.description();
		
		int[] arr = {1, 2, 3, 4 , 5};
		System.out.println(Calc.total(arr));
		
		Calc newcalc = calc;
		// calc.  으로 .이후에 showInfo를 사용 할 수 없다.[showInfo는 CompleteCalc에 정의된 메서드 이기 때문!]
		
	}

}
