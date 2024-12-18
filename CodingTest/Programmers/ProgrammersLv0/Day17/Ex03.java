package Algorithm.ProgrammersLv0.Day17;

/*
OX퀴즈
배열안에 연산이 주어졌을 때 해당 문제가 정답이면 O, 틀리면 X리턴
ex) ["3 - 4 = -3", "5 + 6 = 11"]	["X", "O"]
 */
public class Ex03 {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        // 공백을 기준으로 나눔
        for (int i = 0; i < quiz.length; i++) {
            String[] function = quiz[i].split(" ");

            //계산
            int result = 0;
            switch (function[1]) {
            case "+":
                result = Integer.parseInt(function[0]) + Integer.parseInt(function[2]);
                if (result == Integer.parseInt(function[4])) {
                    answer[i] = "O";
                } else
                    answer[i] = "X";
                break;

            default:
                result = Integer.parseInt(function[0]) - Integer.parseInt(function[2]);
                if (result == Integer.parseInt(function[4])) {
                    answer[i] = "O";
                } else
                    answer[i] = "X";
                break;
            }
        }

        return answer;
    }
}
