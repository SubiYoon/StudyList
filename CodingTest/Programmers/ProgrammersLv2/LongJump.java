package Algorithm.ProgrammersLv2;

/*
멀리 뛰기
효진이는 한번에 한칸 or 두칸 뜀
n칸이 주어졌을 때 n까지 뛰는 방법의 수를 1234567로 나눈 나머지 return
 */
public class LongJump {
    public long solution(int n) {
        //규칙이 피보나치수와 같음
        int[] arr = new int[n+1];

        arr[0] = 1;
        arr[1] = 1;

        for(int i=2; i<=n; i++){
            arr[i] = (arr[i-1] + arr[i-2])%1234567;
        }
        
        return arr[n];
    }
}
