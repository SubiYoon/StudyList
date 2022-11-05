package chapter4;

public class Alone2 {

	public static void main(String[] args) {
		int floor = 5;
		String ment ;
		
		switch (floor) {
		case 1 : ment = "약국";
			break;
		case 2 : ment = "정형외과";
			break;
		case 3 : ment = "피부과";
			break;
		case 4 : ment = "치과";
			break;
		case 5 : ment = "헬스 클럽";
			break;
		default : ment = "불가능";
			break;
		
		}
		System.out.println(floor + "층은 " + ment + "입니다.");
	}

}
