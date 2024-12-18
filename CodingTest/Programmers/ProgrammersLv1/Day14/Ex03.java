package Algorithm.ProgrammersLv1.Day14;
//삼총사
//각 배열의 세자리 숫자를 더했을 때 0이 되는 경우의 수를 구하여 반환
public class Ex03 {
	public static int solution(int[] number) {
        int answer = 0;
        
        for(int i=0; i<number.length-2; i++) {
        	for(int j=i+1; j<number.length-1; j++) {
        		for(int k=j+1; k<number.length; k++) {
        			if(number[i] + number[j] + number [k] == 0) {
        				answer++;
        			}
        		}
        	}
        
        }
        
        return answer;
    }
	public static void main(String[] args) {
		int[] arr1 = {-2,3,0,2,-5};
		int[] arr2 = {-3,-2,-1,0,1,2,3};
		int[] arr3 = {-1,1,-1,1};
		System.out.println(solution(arr1));
		System.out.println(solution(arr2));
		System.out.println(solution(arr3));

	}

}
