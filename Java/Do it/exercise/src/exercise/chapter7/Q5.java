package exercise.chapter7;

import java.util.ArrayList;

public class Q5 {

	public static void main(String[] args) {
		ArrayList<Dog> dog = new ArrayList<Dog>();
		
		dog.add(new Dog("하나", "허스키"));
		dog.add(new Dog("둘", "시츄"));
		dog.add(new Dog("셋", "진돗개"));
		dog.add(new Dog("넷", "말티즈"));
		dog.add(new Dog("다섯", "불독"));
		
		for(int i = 0; i < dog.size(); i++) {
			System.out.println(dog.get(i).showDogInfo());
		}
		
		System.out.println();
		
		for(Dog list : dog) {
			System.out.println(list.showDogInfo());
		}
	}

}
