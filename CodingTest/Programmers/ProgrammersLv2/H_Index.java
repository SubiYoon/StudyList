package Algorithm.ProgrammersLv2;

import java.util.Arrays;

/*
H-Index(과학자의 생산성과 영향력을 나타내는 지표)
1<= 발표한 논문의 수 <= 1000
0<= 인용횟수 <= 10000
논문 n편중 h번 이상 인용된 논문이 h편 이상
&& 나머지 논문이 h번 이하 인용되었다면 H의 최댓값이 H-index
발표한 논문의 인용 횟수를 담은 배열 citations 이 과학자의 H-Index를 return
 */
public class H_Index {
    public int solution(int[] citations) {
        int answer = 0;

        //1. 정렬하여 역순으로 인용된 횟수를 기준으로 비교
        Arrays.sort(citations);
        for(int i=citations[citations.length-1]; i>=0; i--){
            int num = i;
            int count = 0;
            //2. h번 이상으로 인용된 논문이 h편이상인지 count
            for(int j=citations.length-1; j>=0; j--){
                if(num<=citations[j]){
                    count++;
                }
            }
            //3. h<=count 이면 num을 return
            if(num<=count){
                return num;
            }
        }

        return answer;
    }
}
