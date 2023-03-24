package constructor;

public class Person {
	String name;
	float hight;
	float weight;
	
	public Person() {}
	
	public Person(String pname) {
		name = pname;
	}
	public Person(String pname, float phight, float pweight) {
			hight = phight;
			name= pname;
			weight = pweight;
	}
	} 
