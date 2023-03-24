package exercise.chapter7;

public class Q3 {

	public static void main(String[] args) {
		int total = 0;
		int[] num = new int[] {2, 4, 6, 8, 10};
		
		for(int i = 0; i < num.length; i++) {
			total += num[i];
		}
		
		System.out.println("1~10 중 짝수의 합은 " + total + "입니다.");
		}

}
