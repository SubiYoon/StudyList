package exercise.chapter12;

public class Student {
	private String studentId;
	private String studentName;
	
	public Student() {}
	
	public Student(String studentId, String studentName) {
		this.studentId = studentId;
		this.studentName = studentName;
	}

	@Override
	public String toString() {
		return studentId + ':' + studentName;
	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt(studentId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student student = (Student) obj;
			if(this.studentId == student.studentId) 
			return true;
			else 
			return false;
		}
		return false;
		
	}

}
