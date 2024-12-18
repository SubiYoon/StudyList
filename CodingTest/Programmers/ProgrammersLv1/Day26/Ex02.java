package Algorithm.ProgrammersLv1.Day26;

/*
 문자열 분해
 주어진 문자열의 첫번째 글자를 기준
 0번째 부터 같은문자가 있을시 카운트 (a)
 0번쨰 부터 다른문자가 있을시 카운트 (b)
 a=b일때 그 문자까지 자르고 같은일 반복 수행
 마지막에 다다르면 그걸 통째로 사용해서 몇등분인지 count를 return
 */
public class Ex02 {
    public int solution(String s) {
        int answer = 0;
        int end=0;
        int equal=0;
        int notEqual=0;
        
        do{
            if(s.charAt(0)==s.charAt(end)){
                equal++;
            }
            if(s.charAt(0)!=s.charAt(end)){
                notEqual++;
            }
            
            end++;

            if(end==s.length()){
                answer++;
                break;
            }

            if(equal==notEqual){
                s = s.substring(end);
                end=0;
                equal=0;
                notEqual=0;
                answer++;
            }
        }while(end<s.length());

        return answer;
    }
    public static void main(String[] args) {
        Ex02 ex = new Ex02();
        System.out.println(ex.solution("banana"));  //3
        System.out.println(ex.solution("abracadabra")); //6
        System.out.println(ex.solution("aaabbaccccabba"));  //3
    }
}
