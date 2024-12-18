package Algorithm.ProgrammersLv0.Day17;

import java.util.Arrays;

/*
최빈값 구하기
최값이 여러개면 -1리턴
최빈값이 한개면 해당 값 리턴
*/
public class Ex02 {
    public int solution(int[] array) {
        int answer = 0;

        //오름차순 정렬
        Arrays.sort(array);
        
        //array의 숫자를 인덱스로 기반하여 새로운 Counting Array 생성
        int[] arrCount = new int[array[array.length-1]+1];

        //counting
        for(int i=0; i<array.length; i++){
            arrCount[array[i]]++;
        }

        //가장 count가 높은 index 추출
        int index = 0;
        for(int i=0; i<arrCount.length; i++){
            index = arrCount[index]>arrCount[i] ? index : i;
        }

        answer = index;

        //최빈값이 여러개일 경우 -1리턴
        int etc = 0;
        for(int i=0; i<arrCount.length; i++){
            if(arrCount[index] == arrCount[i]){
                etc++;
            }
            if(etc>1){
                return -1;
            }
        }

        return answer;
    }
}
