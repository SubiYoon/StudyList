package Algorithm.ProgrammersLv1.Day17;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex01 {
/*
 다트는 총 3번
 0점 ~ 10점
 S = ^1, D = ^2, T = ^3
 * = n, n-1 점수 2배 (처음에도 가능 처음만 2배점수), (중첩가능)
 # = -n  (*과 중첩가능 해당 점수는 -2배)
  
 */
	public static int solution(String dartResult) {
        int answer = 0;

        char[] score = new char[dartResult.length()];

        for(int i=0; i<dartResult.length(); i++) {
            score[i]= dartResult.charAt(i);
        }

        System.out.println(Arrays.toString(score));

        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        int[] num = {0,0,0};
        int x=0;
        // arr의 인덱스값을 정해줄 count
        int count = 0;
        for(int i=0; i<score.length; i++) {
            if('0'<=score[i] && score[i]<='9') {
                switch(score[i]) {
                    case '0' :
                        if(arr1.isEmpty()) {			//arr가 비었으면 0을 추가 안 비었으면 계속진행
                            arr1.add(0);
                            count++;
                        }else if(!('0'<=score[i-1] && score[i-1]<='9')) {
                            arr1.add(0);
                            count++;
                        }
                        break;
                    case '1' :
                        arr1.add(1);
                        if(score[i+1]=='0') {		//다음 배열에 0이 있으면 10을 추가
                            arr1.set(count, 10);	//이내석 때문에 count가 0으로 시작
                        }
                        count++;
                        break;
                    case '2' : arr1.add(2); count++; break;
                    case '3' : arr1.add(3); count++; break;
                    case '4' : arr1.add(4); count++; break;
                    case '5' : arr1.add(5); count++; break;
                    case '6' : arr1.add(6); count++; break;
                    case '7' : arr1.add(7); count++; break;
                    case '8' : arr1.add(8); count++; break;
                    case '9' : arr1.add(9); count++; break;
                }
            }

            // 알파벳별 연산하여 arr2에 계산값 대입
            if(score[i] == 'S' || score[i] == 'D' || score[i] == 'T') {
                switch(score[i]) {
                    case 'S' : arr2.add(arr1.get(count-1)); break;
                    case 'D' : arr2.add(arr1.get(count-1)*arr1.get(count-1)); break;
                    case 'T' : arr2.add(arr1.get(count-1)*arr1.get(count-1)*arr1.get(count-1)); break;
                }

                if(i!=score.length-1 && (score[i+1]=='*' || score[i+1]=='#')){
                    if(score[i+1]=='*'){
                        if(count==1){
                            x=arr2.get(count-1)*2;
                        }else {
                            x=arr2.get(count-1)*2;
                            num[count-2]=num[count-2]*2;
                        }
                    }else if(score[i+1]=='#'){

                        x=-arr2.get(count-1);
                    }
                }else  x=arr2.get(count-1);



            }
            num[count-1]=x;
        }
        for(int i=0; i<num.length; i++){
            answer+=num[i];
        }

        System.out.println(arr1);
        System.out.println(arr2);
        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("1S2D*3T"));	//37
        System.out.println(solution("1S*2T*3S"));	//23
        System.out.println(solution("1T2D3D#"));	//-4
        System.out.println(solution("1D2S#10S"));	//9
        System.out.println(solution("1D#2S*3S"));	//5
        System.out.println(solution("10S1S0S*"));//-93
        //System.out.println((int)'*'); //58 = : , 57 = 9
    }

}
