package Algorithm.ProgrammersLv0.Day18;

/*
겹치는 선분의 길이
배열로 선분의 시작과 끝의 좌표가 주어졌을때 겹치는 부분의 길이의 총합을 return
 */
public class Ex03 {
    public int solution(int[][] lines) {
        int answer = 0;
        //겹치는 부분을 카운트 해줄 새로운 배열
        int[] count = new int[200];

        //lines의 음수부분을 없애기 위해 100을 더하고 count에 해당 인덱스 증가
        for(int i=0; i<lines.length; i++){
            for(int j=lines[i][0]+100; j<lines[i][1]+100; j++){
                count[j]++;
            }
        }
        
        //count가 2이상인 배열이 있을 시 answer++
        for(int i=0; i<count.length; i++){
            if(count[i]>=2){
                answer++;
            }
        }

        return answer;
    }
}
