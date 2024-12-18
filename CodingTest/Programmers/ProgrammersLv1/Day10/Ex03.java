package Algorithm.ProgrammersLv1.Day10;
//부족한 금액 계산하기
public class Ex03 {
	public static long solution(int price, int money, int count) {
        long answer = -1;
        
        //등차수열 공식 이용
        answer = money - (count*((long)count+1)/2)*price;
        
        //금액이 부족하지 않으면 0을 반환
        if(answer>0) {
        	return 0;
        }
        
        
        return -answer;
    }
	
	//테스트
	public static void main(String[] args) {
		System.out.println(solution(3,20,4));

	}

}
