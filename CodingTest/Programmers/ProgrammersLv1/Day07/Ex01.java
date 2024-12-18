package Algorithm.ProgrammersLv1.Day07;
// 핸드폰 번호 가리기
public class Ex01 {
	public static String solution(String phone_number) {
        String answer = "";
        //버놓 문자열 배열로 쪼개기
        String[] str = phone_number.split("");
        StringBuffer sb = new StringBuffer();
        
        	//뒷자리 4자리를 제외한 나머지 번호 *로 표시
        	for(int i=0; i<str.length-4; i++) {
        		str[i] = "*";
        	}
        	
        	//스팅링 버퍼 이용해서 문자 합치기
        	for(int i=0; i<str.length; i++) {
        		sb.append(str[i]);
        	}
        	
        	// 형변환
        	answer =String.valueOf(sb);
         
        	return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(solution("01033334444"));

	}

}
