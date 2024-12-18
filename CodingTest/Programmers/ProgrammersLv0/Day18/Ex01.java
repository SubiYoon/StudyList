package Algorithm.ProgrammersLv0.Day18;

/*
연속된 숫자의 합
num은 숫자의 갯수
total은 모든 수를 더한 값
요소들을 return
*/

public class Ex01 {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        //모든 요소를 더한 상수값
        int c = (num)*(num-1)/2 ;
        //초항 값
        int n = (total - c)/num;
        //1씩 더해줄 값
        int etc = 0;

        for(int i=0; i<num; i++){
            answer[i] = n + etc;
            etc++;
        }

        return answer;
    }
}
