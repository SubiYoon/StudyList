package Algorithm.ProgrammersLv1.Day19;
/*
 푸드 파이터 대회
 
 배열의 첫번째 원소는 항상 1 = 의미 없음
 food의 길이 = 2~9, food의 각 원소 = 1~1000
 각 인덱스번호의 음식이 각 인덱스의 원소값인 갯수만큼 준비되어 있음
 음식의 갯수가 홀수면 1개는 사용하지 못함
 항상 1:1로 대결
 
 */
public class Ex02 {
	public static String solution(int[] food) {
        String answer = "";
        
        for(int i=1; i<food.length; i++) {
        	for(int j=0; j<food[i]/2; j++){
        		answer+=i;
        	}
        }
        answer += 0;
        
        for(int i=food.length-1; i>=1; i--) {
        	for(int j=food[i]/2-1; j>=0; j--){
        		answer+=i;
        	}
        }
        
        return answer;
    }
	public static void main(String[] args) {
		int[] arr = {1,3,4,6};
		int[] arr2= {1,7,1,2};
		
		System.out.println(solution(arr));
		System.out.println(solution(arr2));
	}

}
