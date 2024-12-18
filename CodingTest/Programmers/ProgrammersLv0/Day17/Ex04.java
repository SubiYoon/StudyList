package Algorithm.ProgrammersLv0.Day17;

/*
다음에 올 숫자
등차 or 등비 수열이 주어질때 마지막 원소 다음 숫자를 return
 */
public class Ex04 {
    public int solution(int[] common) {
        int answer = 0;

        //등비수열과 등차수열 구분
        if(common[1]-common[0] == common[common.length-1]-common[common.length-2]){
            //등차수열의 경우 
            answer = common[common.length-1] + common[1]-common[0];
        }else{
            //등비수열의 경우
            answer = common[common.length-1] * common[1]/common[0];
        }

        return answer;
    }
}
