package Algorithm.ProgrammersLv1.Day12Day13;
//비밀지도

//지도1과 지도2 중 둘중하나라도 벽이면 벽, 둘다공백이면 공백
//지도1과 지도2는 각각 정수 배열 암호화
//각 지도는 잉진수로 암호화 벽(1), 공백(0)
//배열의 숫자 -> 이진수로 변경 -> 1을 벽으로 0을 공백으로 만듬
//1을 '#', 0을 ' '로 변경하여 행단위로 출력
import java.util.Arrays;

public class Ex05 {
	public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        int[] x = new int[n];
        String[] y = new String[n];
        
        //이진수 변환 함수
        for(int i=0; i<n; i++) {
        	x[i] = arr1[i] | arr2[i];
        	y[i] = Integer.toBinaryString(x[i]);
        }
        //0이 부족하면 0을 채워줌
        for(int i=0; i<n; i++) {
        	if(y[i].length()!=n) {
        		String s = y[i];
        		y[i]="";
        		for(int j=0; j<n-s.length(); j++) {
        			y[i]+=0;
        		}
        		y[i]+=s;
        	}
        	//1을 #으로, 0을 공백으로 변경
       		y[i] = y[i].replace('1', '#');
    		y[i] = y[i].replace('0', ' ');
        }
        answer = y;
        return answer;
    }
	//테스트
	public static void main(String[] args) {
		int[] arr1 = {9,20,28,18,11};
		int[] arr2 = {30,1,21,17,28};
		
		System.out.println(Arrays.toString(solution(5, arr1, arr2)));

	}

}
