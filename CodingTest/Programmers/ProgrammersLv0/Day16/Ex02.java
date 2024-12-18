package Algorithm.ProgrammersLv0.Day16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//등수 매기기
public class Ex02 {
    public int[] solution(int[][] score) {
        int[] answer = new int[score.length];

        //평균 순서 List
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            list.add(sum(score[i]));
        }
        list.sort(Comparator.reverseOrder());

        //등수 매기기
        for(int i=0; i<answer.length; i++){
            answer[i] = list.indexOf(score[i][0] + score[i][1]) + 1;
        }

        return answer;
    }

    public int sum(int[] arr) {
        return arr[0] + arr[1];
    }
}
