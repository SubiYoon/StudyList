package Algorithm.ProgrammersLv0.Day15;

//치킨 쿠폰
public class Ex05 {
    public int solution(int chicken) {
        int answer = 0;
        
        while(true){
            if(chicken<10){
                break;
            }
            
            answer += chicken/10;

            int extra = chicken/10;

            chicken = chicken%10 + extra;
        }

        return answer;
    }
}
