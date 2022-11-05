package staticex;

public class StudentTest1 {

	public static void main(String[] args) {
		Student studentlee = new Student();
		studentlee.setStudentName("이지원");
		System.out.println(studentlee.serialNum);
		studentlee.serialNum++;
		
		Student studentSon = new Student();
		studentSon.setStudentName("손흥민");
		System.out.println(studentSon.serialNum);
		System.out.println(studentSon.serialNum);
	}

}
