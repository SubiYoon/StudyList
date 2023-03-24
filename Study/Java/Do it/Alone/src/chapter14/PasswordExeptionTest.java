package chapter14;

public class PasswordExeptionTest {
	
	private String password;
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) throws PasswordException{
		if(password == null) {
			throw new PasswordException("비밀번호는 null일 수 없습니다.");
		}
		else if(password.length() < 5) {
			throw new PasswordException("비밀번호는 5자 미만일 수 없습니다.");
		}
		else if(password.matches("[a-zA-Z]+"))
			throw new PasswordException("비밀번호는 숫자를 포함해야 합니다.");
		
		this.password = password;
	}


	public static void main(String[] args) {
		String password = new String("abc");
		System.out.println(password.matches("[a-zA-Z]+"));
		
		String password2 = new String("abc1");
		System.out.println(password2.matches("[a-zA-Z]+"));

		
		PasswordExeptionTest test = new PasswordExeptionTest();
		password = null;
		try {
			test.setPassword(password);
			System.out.println("오류 없음1");
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		}
		
		password = "abcd";
		try {
			test.setPassword(password);
			System.out.println("오류 없음2");
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		}
		
		password = "abcde";
		try {
			test.setPassword(password);
			System.out.println("오류 없음3");
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		}
		
		password = "abcde1";
		try {
			test.setPassword(password);
			System.out.println("오류 없음4");
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		}
	}

}
