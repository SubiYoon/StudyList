package Algorithm.ProgrammersLv1.Day25;
/*
 p의 숫자와 같은 자릿수의 숫자로 t와 비교할때,
 작거나 같으면 1을 증가시켜 총 몇개가 있는지 리턴
 */
public class Ex01 {
    public int solution(String t, String p) {
        int answer = 0;
        long num = Long.parseLong(p);

        for(int i=0; i<t.length()-p.length()+1; i++){
            if(Long.parseLong(t.substring(i, i+p.length()))<=num){
                answer++;
            }
        }
        return answer;
    }


    public static void main(String[] args) {
        Ex01 ex = new Ex01();
        System.out.println(ex.solution("3141592", "271"));
        System.out.println(ex.solution("500220839878", "7"));
        System.out.println(ex.solution("10203", "15"));
        }
    
}
