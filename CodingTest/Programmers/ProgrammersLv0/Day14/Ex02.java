package Algorithm.ProgrammersLv0.Day14;

public class Ex02 {
    public int solution(String my_string) {
        String[] num = my_string.split(" ");
        int answer = Integer.parseInt(num[0]);

        // 공백을 기준으로 가운데는 + or - 에 조건식을 걸어 수식으로 계산
        for (int i = 1; i < num.length - 1; i++) {
            if (num[i].equals("+")) {
                answer += Integer.parseInt(num[i + 1]);
            } else if (num[i].equals("-")) {
                answer -= Integer.parseInt(num[i + 1]);
            }
        }

        return answer;
    }
}
