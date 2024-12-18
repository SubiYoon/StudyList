package Algorithm.ProgrammersLv0.Day17;

/*
분수의 덧셈
numer는 분자
denom은 분모
두수의 더한 값을 return
answer[0] = 분자, answer[1] = 분모
 */
public class Ex05 {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        //통분해서 더함
        answer[0] = numer1*denom2 + numer2*denom1;
        answer[1] = denom1*denom2;

        answer = finalNum(answer);

        return answer;
    }

    //기약분수 구하기
    public int[] finalNum(int[] arr){
        
        if(arr[0]<arr[1]){
            for(int i=arr[0]; i>=2; i--){
                if(arr[0]%i==0 && arr[1]%i==0){
                    arr[0]/=i;
                    arr[1]/=i;
                }
            }
        }else {
            for(int i=arr[1]; i>=2; i--){
                if(arr[0]%i==0 && arr[1]%i==0){
                    arr[0]/=i;
                    arr[1]/=i;
                }
            }
        }

        return arr;
    }
}
