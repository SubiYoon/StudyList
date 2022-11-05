package exercise.chapter4;;

public class PracticeQ3 {

	public static void main(String[] args) {
		int dan = 2;
		int times = 1;
		
		for (dan = 2; dan <= 9; dan++) {
			for (times = 1; times <= 9; times++) {
				if (times > dan)
					break;
				
				System.out.println(dan + "X" + times + "=" + dan * times);
			}
			System.out.println();
		}
	}

}
