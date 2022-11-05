package constructor;

public class PersonTest {
	public static void man(String [] args) {
		Person personKim = new Person( );
			personKim.name = "김유신";
			personKim.weight = 85.5F;
			personKim.hight = 180.0F;
			
		Person personLee = new Person("이순신", 175F, 75);
	}
}