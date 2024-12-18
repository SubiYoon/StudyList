package Algorithm.ProgrammersLv0.Day13;

public class Ex01 {
    public int solution(int[] array) {
        int answer = 0;
        String str_num = "";
        
        for(int i=0; i<array.length; i++){
            str_num += array[i];
        }
        
        String[] num = str_num.split("");
        
        for(int i=0; i<num.length; i++){
            if(num[i].equals("7")){
                answer++;
            }
        }
        
        return answer;
    }
}
