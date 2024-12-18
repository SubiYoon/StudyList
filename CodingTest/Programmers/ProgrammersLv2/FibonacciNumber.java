package Algorithm.ProgrammersLv2;

/*
n번째 피보나치수를 1234567로 나눈 값을 return
 */
public class FibonacciNumber {
    public int solution(int n) {
        int[] fibonacci = new int[n+1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        fibonacci[2] = 1;

        for(int i=3; i<n+1; i++){
            fibonacci[i] = fibonacci[i-1]%1234567 + fibonacci[i-2]%1234567;
        }
        
        return fibonacci[n]%1234567;
    }
}
