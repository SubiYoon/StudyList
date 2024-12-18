package Algorithm.ProgrammersLv1.Day09;
//가운데 글자 가져오기
public class Ex01 {

	public static String solution(String s) {
        String answer = "";
        
        //배열에 넣어 인덱스값 주기
        String[] str = s.split("");
        
        //배열갯수 홀수면 가운데, 짝수면 가운데 두개 반환하기
        if(str.length%2!=0) {
        	answer = str[str.length/2];
        }else {
        	answer = str[str.length/2-1] + str[str.length/2];
        }
        
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		System.out.println(solution("qwer"));
	}

}
