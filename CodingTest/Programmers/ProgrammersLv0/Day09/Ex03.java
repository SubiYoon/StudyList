package Algorithm.ProgrammersLv0.Day09;

public class Ex03 {
    public int solution(int[] numbers) {
        int answer = numbers[0] * numbers[1];

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 2; j < numbers.length; j++) {
                if (i == j) {
                    continue;
                } else if (numbers[i] * numbers[j] > answer) {
                    answer = numbers[i] * numbers[j];
                }
            }
        }
        return answer;
    }
}
