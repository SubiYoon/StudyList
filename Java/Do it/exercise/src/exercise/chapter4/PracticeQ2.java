package exercise.chapter4;;

public class PracticeQ2 {

	public static void main(String[] args) {
		int dan;
		int times;
		for (dan = 2; dan <= 9;  dan++) {
				for (times = 1; times <= 9; times++) {
					
					if (dan % 2 != 0)
						continue;
					
				System.out.println(dan + "X" + times + "=" + dan * times);
			}
			System.out.println();
		}
	}

}
