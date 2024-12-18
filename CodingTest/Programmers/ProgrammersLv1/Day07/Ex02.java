package Algorithm.ProgrammersLv1.Day07;
//서울에서 김서방 찾기
public class Ex02 {
	 public static String solution(String[] seoul) {
	        String answer = "";
	        //String은 equals() 메서드로 동일한지 확인
	        for(int i=0; i<seoul.length; i++) {
	        	if(seoul[i].equals("Kim")) {
	        		answer = "김서방은 " + i + "에 있다";
	        	}
	        }
	        
	        return answer;
	    }
	 
	 // 테스트
	public static void main(String[] args) {
		String[] str = {"Jame", "Kim"};
		System.out.println(solution(str));
	}

}
