package Algorithm.ProgrammersLv1.Day17;
//콜라병 회수하면 콜라주기
public class Ex02 {
	public static int solution(int a, int b, int n) {
        int answer=0;
        int etc=0;
        while(true){
            answer+=b*(n/a);
            etc=n%a;
            n=b*(n/a);
            if(etc!=0){
                n+=etc;
            }
            if(n<a) break;
        }
        return answer;
    }
    public static void main(String[] args){
        System.out.println(solution(2,1,20));
    }

}
