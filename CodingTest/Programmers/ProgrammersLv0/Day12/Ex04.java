package Algorithm.ProgrammersLv0.Day12;

public class Ex04 {
    public int solution(String my_string) {
        int answer = 0;
        
        //숫자와 문자 구분하여 배열로 만듬
        String[] num = my_string.split("[a-zA-Z]");

        //배열로 만든 String을 숫자화 후 더함
        for (int i = 0; i < num.length; i++) {
            if(num[i].length() != 0){
                answer += Integer.parseInt(num[i]);
            }
        }

        return answer;
    }
}
