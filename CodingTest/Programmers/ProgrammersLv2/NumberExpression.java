package Algorithm.ProgrammersLv2;

/*
숫자 n을 연속된 자연수로 표현할 수 있는 방법의 수를 return
ex) 15 -> 1+2+3+4+5, 4+5+6, 7+8, 15
 */
public class NumberExpression {
    public int solution(int n) {
        int answer = 0;
        int sum = 0;

        //더했을 때 n이랑 값이 같으면 answer++
        for(int i=1; i<=n; i++){
            for(int j=i; j<=n; j++){
                sum += j;
                if(sum==n){
                    answer++;
                    break;
                }else if(sum>n){
                    break;
                }
            }
            sum = 0;
        }

        return answer;
    }
}
