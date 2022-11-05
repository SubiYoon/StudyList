package warpper;

public class Warpper {
	public static void main (String[] arg) {
		Integer iValue = new Integer(100);
		int myValue = iValue.intValue();
	
		System.out.println(myValue);
		
		Integer number1 = Integer.valueOf(10);
		Integer number2 = Integer.valueOf(1000);
		
		System.out.println(number1);
		System.out.println(number2);
		
		int num = Integer.parseInt("100");
		
		System.out.println(num);
		
		Integer num1 = new Integer(100);
		int num2 = 200;
		int sum = num1 + num2;
		Integer num3 = num2;
		
		System.out.println(sum);
		System.out.println(num3);
	}
}