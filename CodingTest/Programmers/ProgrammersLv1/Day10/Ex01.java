package Algorithm.ProgrammersLv1.Day10;
//문자열 다루기 기본
public class Ex01 {
	public static boolean solution(String s) {
        boolean answer = true;
        // s의 길이가 4 or 6 아니면 false값 반환
        if(s.length() != 4 && s.length() !=6) {
        	return false;
        }
        
        //각 배열의 요소가 아스키코드값이 65이상이면 반환
        //숫자 9는 아스키코드값 57
        for(int i=0; i<s.length(); i++) {
	       	if(65<=(int)s.charAt(i)) {
		       	return false;
		    }
	    }
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		String str1 = "a234";
		String str2 = "1234";
		System.out.println(solution(str1));
		System.out.println(solution(str2));
		System.out.println((int)'9');
	}

}
