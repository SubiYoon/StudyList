package Algorithm.ProgrammersLv0.Day16;

import java.util.HashMap;

/*
저주받은 숫자 3
숫자 3이 들어간 숫자는 jump
3의 배수도 jump
해당 숫자 값에 맞는 숫자는??
ex) 1=1, 2=2, 3=4, 4=5, 5=7, 6=8, 7=10 ...
 */
public class Ex05 {
    public int solution(int n) {
        int answer = 1;

        //key와 value로 나눔
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=1; i<=n; i++){
            map.put(i, answer);
            String strNum = answer + "";
            
            if(answer%3==0){
                answer++;
                i--;
            }else if(strNum.contains("3")){
                answer++;
                i--;
            }else{
                answer++;    
            }
            
        }
        
        answer = map.get(n);
        return answer;
    }
}
