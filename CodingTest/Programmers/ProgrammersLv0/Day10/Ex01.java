package Algorithm.ProgrammersLv0.Day10;

public class Ex01{
    public int solution(int num, int k) {
        int answer = 0;
        String str = ""+num;
        String[] str_num = str.split("");
        String str_k =""+k;

        for(int i=0; i<str_num.length; i++){
            if(str_num[i].equals(str_k)){
                answer = i+1;
                break;
            }else answer = -1;
        }

        return answer;
    }
}