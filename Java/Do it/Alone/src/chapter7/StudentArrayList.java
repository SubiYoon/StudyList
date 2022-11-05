package chapter7;

import java.util.ArrayList;

public class StudentArrayList {

	public static void main(String[] args) {
		ArrayList<Student> studentp = new ArrayList<Student>();
		
		studentp.add(new Student(1001, "James"));
		studentp.add(new Student(1002, "Tomas"));
		studentp.add(new Student(1003, "Edward"));
		
		for (int i = 0; i < studentp.size(); i++) {
			Student student = studentp.get(i);
			student.showStudentInfo();
		}
	}

}
