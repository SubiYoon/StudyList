package reference;

public class StudentTest {

	public static void main(String[] args) {
		
		Student studentjames = new Student(100, "James");
		studentjames.setKorea( 100);
		studentjames.setMath( 100);
		
		Student studenttomas = new Student(101, "tomas");
		studenttomas.setKorea( 40);
		studenttomas.setMath( 80);
		
		studentjames.showStudentInfo();
		studenttomas.showStudentInfo();
	}

}
