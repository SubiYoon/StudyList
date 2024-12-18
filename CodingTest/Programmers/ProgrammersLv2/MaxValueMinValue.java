package Algorithm.ProgrammersLv2;

/*
문자열 s에서 공백으로 구분되는 숫자에서
최솟값, 최댓값 return
 */
public class MaxValueMinValue {
    public String solution(String s) {
        String answer = "";
        //숫자만 추출한 문자열 배열
        String[] numList = s.split(" ");

        //해당 배열에서 가장 작은 숫자 answer에 더해줌
        int min = Integer.parseInt(numList[0]);
        for(int i=1; i<numList.length; i++){
            if(min < Integer.parseInt(numList[i])){
                continue;
            } else{
                min = Integer.parseInt(numList[i]);
            }
        }

        answer += min + " ";

        //배열에서 가장 큰 숫자를 answer에 더해줌
        int max = Integer.parseInt(numList[0]);
        for(int i=1; i<numList.length; i++){
            if(max > Integer.parseInt(numList[i])){
                continue;
            } else{
                max = Integer.parseInt(numList[i]);
            }
        }
        
        answer += max;

        return answer;
    }
}
