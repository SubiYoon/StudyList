package Algorithm.ProgrammersLv1.Day14;
//숫자 문자열과 영단어
public class Ex01 {
	public static int solution(String s) {
        int answer = 0;
        String word = "";
        String num ="";
        String[] str = s.split("");
        
        for(int i=0; i<str.length; i++) {
        	word += str[i];
        	
        	switch(word) {
        	case "one" : num+=1; word=""; break; 
        	case "two" : num+=2; word=""; break;
        	case "three" : num+=3; word=""; break;
        	case "four" : num+=4; word=""; break;
        	case "five" : num+=5; word=""; break;
        	case "six" : num+=6; word=""; break;
        	case "seven" : num+=7; word=""; break;
        	case "eight" : num+=8; word=""; break;
        	case "nine" : num+=9; word=""; break;
        	case "zero" : num+=0; word=""; break;
        	}
        	
        	switch(word) {
        	case "1" : num+=1; word=""; break; 
        	case "2" : num+=2; word=""; break;
        	case "3" : num+=3; word=""; break;
        	case "4" : num+=4; word=""; break;
        	case "5" : num+=5; word=""; break;
        	case "6" : num+=6; word=""; break;
        	case "7" : num+=7; word=""; break;
        	case "8" : num+=8; word=""; break;
        	case "9" : num+=9; word=""; break;
        	case "0" : num+=0; word=""; break;
        	}
        }
        
        answer = Integer.parseInt(num);
        
        return answer;
    }
	public static void main(String[] args) {
		System.out.println(solution("one4seveneight"));
		System.out.println(solution("2three45sixseven"));

	}

}
