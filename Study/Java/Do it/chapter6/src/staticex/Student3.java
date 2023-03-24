package staticex;

public class Student3 {
	private static int serialNum = 1000;
	int studentID;
	String studentName;
	int grade;
	String address;
	int studentCardNum;
	
	public Student3(String name) {
		serialNum++;
		studentName = name;
		studentCardNum = serialNum + 100;
	}
	
	public int getSerialNum( ) {
		return serialNum;
	}
	
	public void setSerialNum(int num) {
		int i = 10;
		serialNum = num;
	}
	
		public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String name) {
		studentName = name;
	}
}
