package Algorithm.ProgrammersLv0.Day02;

public class Ex04 {
    public int[] solution(int[] num_list) {
        int[] answer = new int[num_list.length];
        
        for(int j=0; j<num_list.length; j++){
            answer[j] = num_list[num_list.length-1-j];
        }

        return answer;
    }
}
