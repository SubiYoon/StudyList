package Algorithm.ProgrammersLv1.Day11;

public class Ex04 {
	public static String solution(String s) {
		String answer = "";
		
		//" " 인덱스 초기화 해줄 count
		int count = 0;
		
		for(int i=0; i<s.length(); i++) {
			// " " 있으면 빈공간 삽입 후 인덱스 초기화 
			if(s.charAt(i)==' ') {
				answer += ' ';
				count =0;
				
			//소문자 만나면 조건에 맞게 대문자로 변경
			}else if(s.charAt(i)>=97) {
				answer += count%2==0 ? (char)((int)s.charAt(i)-32) : s.charAt(i);
				count++;	//문자만나면 인덱스 증가
				
			//대문자 만나면 조건에 맞게 소문자 변경
			}else {
				answer += count%2==0 ? s.charAt(i) : (char)((int)s.charAt(i)+32);
				count++;	//문자만나면 인덱스 증가
			}
		}

		
		return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		System.out.println(solution("tRY   hE  LLo WoRLD "));
		System.out.println((int)'Z'); 	//65 ~ 90
		System.out.println((int)'a');	//97
	}

}