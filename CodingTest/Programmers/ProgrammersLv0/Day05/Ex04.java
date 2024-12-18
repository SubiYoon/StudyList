package Algorithm.ProgrammersLv0.Day05;

public class Ex04 {
    public String solution(String my_string) {
        String answer = "";

        for(int i=0; i<my_string.length(); i++){
            if(65<=(int)my_string.charAt(i) && (int)my_string.charAt(i)<=90){
                answer+=(char)((int)my_string.charAt(i)+32);
            }else if(97<=(int)my_string.charAt(i) && (int)my_string.charAt(i)<=122){
                answer+=(char)((int)my_string.charAt(i)-32);
            }
        }

        return answer;
    }
}
