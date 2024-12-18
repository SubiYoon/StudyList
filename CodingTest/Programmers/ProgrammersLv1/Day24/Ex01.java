package Algorithm.ProgrammersLv1.Day24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
매일 1명의 가수가 노래를 부름
상위 k번째 이내이면 명예의 전당에 올라감
즉 k일까지는 모든 가수가 명예의 전당에 올라감
k일 이후에는 점수순으로 밀려남
명예의 전당 최하위 점수를 반환
 */
public class Ex01 {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<score.length; i++){
            list.add(score[i]);
            Collections.sort(list);
            Collections.reverse(list);

            if(i<k){
                answer[i]=list.get(i);
            }else{
                answer[i]=list.get(k-1);
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        int[] score1 = {10,100,20,150,1,100,200};
        int[] score2 = {0,300,40,300,20,70,150,50,500,1000};
        Ex01 ex = new Ex01();
        System.out.println(Arrays.toString(ex.solution(3, score1)));
        System.out.println(Arrays.toString(ex.solution(4, score2)));
    }
}
