package Algorithm.ProgrammersLv2;

/*
예상 대진표
총인원수 n
토터먼트 형식으로 승부를 가림
a,b가 각자 부여받은 번호일 때 두 선수가 대결하는 라운드는 몇번째 인지 return
 */
public class ListOfMatches {
    public int solution(int n, int a, int b){
        int answer = 0;
        int count = 1;
        for(int i=0; i<n/2; i++){
            if(a%2!=0){
                a+=1;
            }
            if(b%2!=0){
                b+=1;
            }
            if(a==b){
                answer = count;
                break;
            }
            a/=2;
            b/=2;
            count++;
        }

        return answer;
    }
}
