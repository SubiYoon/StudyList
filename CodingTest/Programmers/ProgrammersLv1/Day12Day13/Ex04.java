package Algorithm.ProgrammersLv1.Day12Day13;
//시저 암호
public class Ex04 {
	public static String solution(String s, int n) {
		String answer = "";
		
		for(int i=0; i<s.length(); i++) {
			//공백이면 공백삽입
			if(s.charAt(i)==' ') {
				answer += s.charAt(i);
			//문자가 대문자일때
			}else if(Character.isLowerCase(s.charAt(i))) {
				if((int)s.charAt(i)+n<=122 && (int)s.charAt(i)+n>=97){
					answer += (char)((int)s.charAt(i)+n);
				}else if((int)s.charAt(i)+n>122) {
					answer += (char)((int)96+((int)s.charAt(i))+n-122);
				}
			//문자가 소문자일때
			}else if(Character.isUpperCase(s.charAt(i))) {
				if((int)s.charAt(i)+n<=90 && (int)s.charAt(i)+n>=65){
					answer += (char)((int)s.charAt(i)+n);
				}else if((int)s.charAt(i)+n>90) {
					answer += (char)((int)64+((int)s.charAt(i)+n-90));
				}
			}
		}
		
		return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		System.out.println(solution("AZ", 7));
		System.out.println((int)'a');
		System.out.println((int)'Z');


	}

}
