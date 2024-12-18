package Algorithm.ProgrammersLv0.Day17;

/*
다항식 더하기
미수는 x만 존재
0으로 시작하는 수는 없음
문자와 숫자사이의 곱은 생략
계수 1은 생략
ex) 3x + 7 + x -> 4x + 7
*/
public class Ex01 {
    public String solution(String polynomial) {
        String answer = "";
        int varNum = 0;
        int c = 0;

        // 공백 제거
        polynomial = polynomial.replace(" ", "");

        // +를 기준으로 나눔
        String[] elements = polynomial.split("\\+");

        // 나눈 것중 숫자와 문자를 구분하여 숫자만 추출
        for (int i = 0; i < elements.length; i++) {
            if (!elements[i].contains("x")) {
                c += Integer.parseInt(elements[i]);
            } else {
                if (elements[i].length() == 1) {
                    varNum += 1;
                } else {
                    varNum += Integer.parseInt(elements[i].split("x")[0]);
                }
            }
        }

        // 맞는 조건식으로 answer에 대입
        if (c == 0) {
            if (varNum != 0) {
                if (varNum != 1) {
                    answer = varNum + "x";
                }else {
                    answer = "x";
                }
            } else {
                answer += 0;
            }
        } else {
            if (varNum != 0) {
                if (varNum != 1) {
                    answer = varNum + "x" + " + " + c;
                }else {
                    answer = "x" + " + " + c;
                }
            } else {
                answer += c;
            }
        }

        return answer;
    }
}
