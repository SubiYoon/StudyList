package Algorithm.ProgrammersLv1.Day14;
//K번째수
import java.util.Arrays;

public class Ex02 {
	public static int[] solution(int[] array, int[][] commands) {
        //행의 수 만큼 배열 생성
		int[] answer = new int[commands.length];
        
        
        for(int i=0; i<commands.length; i++) {
        	int[] arr = new int[commands[i][1]-commands[i][0]+1];
        	
        	//배열복사 System.arraycopy(복사할배열, 복사시작 위치, 붙여넣기 할 배열, 붙여넣기 시작 위치, 붙여넣기 갯수)
        	System.arraycopy(array, commands[i][0]-1, arr, 0, commands[i][1]-commands[i][0]+1);
        	Arrays.sort(arr);
        	answer[i] = arr[commands[i][2]-1];
        }
        
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		int[] arr1 = {1,5,2,6,3,7,4};
		int[][] arr2 = {{2,5,3},{4,4,1},{1,7,3}};
		System.out.println(Arrays.toString(solution(arr1, arr2)));
		
	}

}
