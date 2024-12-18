package Algorithm.ProgrammersLv0.Day12;

public class Ex01 {
    public int solution(int i, int j, int k) {
        int answer = 0;
        
        for(int t=i; t<=j; t++){
            String str = "";
            str += t;
            
            for(int c=0; c<str.length(); c++){
                if((int)str.charAt(c)==48+k){
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
