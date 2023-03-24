package chapter4;

public class Alone1 {

	public static void main(String[] args) {
		int score = 54;
		char grade;
		if (score >= 90) {
			grade = 'A';
			System.out.println(grade);
		}
		else if (score >= 80) {
			grade = 'B';
			System.out.println(grade);
		}
		else if (score >= 70) {
			grade = 'C';
			System.out.println(grade);
		}
		else if (score >= 60) {
			grade = 'D';
			System.out.println(grade);
		}
		else {
			grade = 'F';
			System.out.println(grade);
		}
	//각 조건문에 System.out.println(grade);는 중복이므로 마지막에 하나만 작성해도 무관
	}

}
