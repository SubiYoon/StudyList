package Algorithm.ProgrammersLv1.Day23;

import java.util.Arrays;

/*
 해당 알벳이 처음나오면 -1
 두번째 이상 나왔을 때 같은 알파벳이 앞에 몇번째에 위치하는지 반환
 */
public class Ex01 {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        String[] str = s.split("");
        String strPlus ="";

        for(int i=0; i<str.length; i++){
            if(!strPlus.contains(str[i])){
                answer[i]=-1;
            }else if(strPlus.contains(str[i])){
                answer[i]=i-strPlus.lastIndexOf(str[i]);
            }
            strPlus += str[i];
        }
        return answer;
    }
 
    public int[] solution2(String str) {
        int[] answer = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (str.indexOf(c) == i) {
                answer[i] = -1;
            } else {
                answer[i] = i - str.indexOf(c);
                str = str.replaceFirst(""+c, "*");
            }
        }
        return answer;
    }
 
    public static void main(String[] args) {
        Ex01 ex = new Ex01();
        System.out.println(Arrays.toString(ex.solution("banana")));  //-1 -1 -1 2 2 2
        System.out.println(Arrays.toString(ex.solution("foobar")));  //-1 -1 1 -1 -1 -1

    }
}
