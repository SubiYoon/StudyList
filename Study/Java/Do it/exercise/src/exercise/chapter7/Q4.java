package exercise.chapter7;

public class Q4 {

	public static void main(String[] args) {
		Dog[] dog = new Dog[5];
		
		dog[0] = new Dog("하나", "풍산개");
		dog[1] = new Dog("둘", "시츄");
		dog[2] = new Dog("셋", "진돗개");
		dog[3] = new Dog("넷", "말티즈");
		dog[4] = new Dog("다섯", "불독");
		
		for(int i = 0; i < dog.length; i++) {
			System.out.println(dog[i].showDogInfo());
		}
		System.out.println();
		
		for(Dog s : dog ){
			System.out.println(s.showDogInfo());
		}
	}

}
