package arraylist;

import java.util.ArrayList;

public class Student {

	String studentName;
	int studentID;
		ArrayList<Subject> subjectList;
	
	public Student (String studentName, int studentID) {
		this.studentID = studentID;
		this.studentName = studentName;
		
		subjectList = new ArrayList<Subject>();
	}
	
	public void addSubject(String name, int score) {
		Subject subject = new Subject();
		
		subject.setName(name);
		subject.setScorePoint(score);
		subjectList.add(subject);
	}
	
	public void showStudentInfo() {
		int total = 0;
		
		for(Subject s : subjectList) {
			
			total += s.getScorePoint();
			System.out.println("학생 " + studentName + "의 " + s.getName() + "과목 성적은 " + s.getScorePoint() + "점 입니다.");
		}
		System.out.println("학생 " + studentName + "의 총점은 " + total + "점 입니다.");
	}
}
