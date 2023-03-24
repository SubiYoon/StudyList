package chapter11;

public class Student {
	int studentNum;
	String studentName;
	
	public Student(int num, String name) {
		this.studentNum = num;
		this.studentName = name;
	}
	
	public String toString() {
		return studentNum + ", " + studentName;
	}
}
