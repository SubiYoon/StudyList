package Algorithm.ProgrammersLv1.Day06;
//콜라츠 추측
public class Ex01 {

	 public static int solution(int num) {
	       int answer=0;
	       
	       //num을 long으로 형변환
	       
	       long n1 = num;
	       
	       //num이 1이면 0 출력
	       
	       if(n1==1) return answer;
	       
	       //연산
	       
	       while(answer<=500) {
	    	   n1 = (n1%2==0) ? n1/2 : (n1*3)+1;
	    	   answer++;
	    	   
	    	   //500회 넘지 않아서 작업한 횟수 리턴
	    	   
	    	   if(n1==1) {
	    		   return answer;	    	   
	    	   }
	       }
	       
	       //500회 넘어서 -1 리턴
	       
	       return -1;
	 }
	 
	 //실험
	 
	public static void main(String[] args) {
	
		System.out.println(solution(626331));
	}
}
