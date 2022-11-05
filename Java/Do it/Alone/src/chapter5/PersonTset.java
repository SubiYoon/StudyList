package chapter5;

public class PersonTset {

	public static void main(String[] args) {
		Person man = new Person();
		man.age = 40;
		man.childrenMembers = 3;
		man.isMarried = true;
		man.name = "James";
		
			System.out.println("이름" + " : " + man.showName());
			System.out.println("나이" + " : " + man.showAge());
			System.out.println("결혼여부" + " : " + man.showIsMarried());
			System.out.println("자녀수" + " : " + man.showChildrenMembers());
	}

}
