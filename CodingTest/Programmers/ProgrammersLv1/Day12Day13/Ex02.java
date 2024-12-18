package Algorithm.ProgrammersLv1.Day12Day13;

import java.util.Arrays;

//예산
public class Ex02 {
	public static int solution(int[] d, int budget) {
        int answer = 0;
        int count = budget;//budget count
        int index = 0;//d count
        boolean check = true;
        
        Arrays.sort(d);
        
        if(d[0] <= budget){
            do{
                if(index==d.length){
                    return answer;
                }else if(d[index]<=count){
                    count-=d[index];
                    answer++;
                    index++;
                }else{
                    return answer;
                }

            }while(check);
        }
        
        return answer;
    }

	public static void main(String[] args) {
		int[] arr = {1,3,2,4,5};
		System.out.println(solution(arr, 9));
	}
}
