package Algorithm.ProgrammersLv0.Day10;

public class Ex02 {
    public int solution(int order) {
        int answer = 0;
        int count = 10;
        while(true){
            if(order<=0){
                break;
            }

            if(order%count==3 || order%count==6 || order%count==9){
                answer++;
            }
            
            order = (order - (order % count))/10;
        }
        
        return answer;
    }
}
