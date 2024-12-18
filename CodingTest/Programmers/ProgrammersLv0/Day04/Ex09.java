package Algorithm.ProgrammersLv0.Day04;

public class Ex09 {
    public int solution(String my_string) {
        int answer = 0;
        String[] str = my_string.replaceAll("[^0-9]", "").split("");

        for(int i=0; i<str.length; i++){
            answer += Integer.parseInt(str[i]);
        }

        return answer;
    }
}
