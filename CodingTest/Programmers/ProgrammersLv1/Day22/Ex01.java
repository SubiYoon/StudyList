package Algorithm.ProgrammersLv1.Day22;

import java.util.ArrayList;
import java.util.Collections;

/*
 한 상자에 과일을 m개씩 포장
 담긴 사과 중 가장 낮은 점수(p)가 1~k인경우 사과 상자 가격은 p*m
 과일장수가 가능한 많은 사과를 팔았을때 최대 이익은?
 사과 최대점수 k, 상자안 사과 갯수 m, scroe가 arr

 
 */
public class Ex01 {
    public int solution(int k, int m, int[] score) {
        int answer = 0;

        //k값 이상인것들 제거하기위한 list
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<score.length; i++){
            if(score[i]<=k){
                list.add(score[i]);
            }
        }

        //내림차순 정렬
        list.sort(Collections.reverseOrder());

        //박스갯수 산출
        int box = list.size()/m;

        //answer에 담음
        //길이가 box*m인 이유는 그냥 size()를 넣으면 오버플로우 발생
        for(int i=0; i<box*m; i+=m){
            answer += list.get((m-1)+i)*m;
        }
        return answer;
    }

    public static void main(String[] args) {
        Ex01 ex = new Ex01();
        int[] arr1 = {1,2,3,1,2,3,1};
        int[] arr2 = {4,1,2,2,4,4,4,4,1,2,4,2};
        System.out.println(ex.solution(3, 4, arr1));
        System.out.println(ex.solution(4,3,arr2));
    }
}
