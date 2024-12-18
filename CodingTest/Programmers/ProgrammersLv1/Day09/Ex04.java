package Algorithm.ProgrammersLv1.Day09;
//문자열 내림차순으로 정렬
public class Ex04 {
	public static String solution(String s) {
		String answer = "";
		
		//아스키 코드값을 비교하기위해 문자배열 생성
		char[] c = new char[s.length()];
		
		//문자배열에 담기
		for(int i=0; i<s.length(); i++) {
			c[i] = s.charAt(i);
		}
		
		//스와핑
		for(int i=0; i<c.length-1; i++) {
			for(int j=i; j<c.length; j++) {
				if(c[i]<c[j]) {
					char imsi = c[i];
					c[i] = c[j];
					c[j] = imsi;
				}
			}
		}
		
		//answer에 삽입
		for(int i=0; i<c.length; i++) {
			answer += c[i];
		}
		
		return answer;
  	}
	
	//테스트
	public static void main(String[] args) {
		System.out.println(solution("Zbcdefg"));

	}

}
