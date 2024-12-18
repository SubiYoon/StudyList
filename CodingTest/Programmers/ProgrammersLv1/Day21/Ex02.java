package Algorithm.ProgrammersLv1.Day21;

/*
 숫자 짝꿍
 
 x,y 두수의 공통으로 나타나는 정수로 이용하여 만들 수 있는 가장 큰 정수를 두 수의 짝꿍이라함
 짝꿍이 존재하지않으면 -1을 반
 
 
 */
public class Ex02 {
	public String solution(String X, String Y) {
        String answer = "";
        int[] numCount = new int[10];
        StringBuilder sb = new StringBuilder();
        Count x = new Count(X);
        Count y = new Count(Y);
	    
        //갯수가 존재하는 것중에 작은 수만 골라 중복갯수를 찾는 반복문
        for(int i=0; i<10; i++) {
        	if(x.num[i]>0 && y.num[i]>0) {
        		numCount[i] = (x.num[i]<y.num[i]) ? x.num[i] : y.num[i];
        	}
        }
        
        //찾은 갯수만큼 해당문자를 StringBuilder에 넣어주는 반복문
        for(int i=0; i<10; i++) {
        	for(int j=0; j<numCount[i]; j++) {
        		sb.append(i);
        	}
		}
        //내림차순 정렬
        sb.reverse();
        
        //문자가 0만 있을경우 0이 sb의 길이와 count가 같으면 0이 하나로만 나오게하기 위한 카운트
        int count = 0;
       	
        //answer에 대입
        if(sb.length()>0) {
       		answer=sb.toString();
       		for(int i=0; i<sb.length(); i++) {
       			if(sb.charAt(i)=='0') {
       				count++;
       			}
       		}
       		if(count==sb.length()) {
       			answer="0";
       		}
       	}else answer="-1";
         
       	
        return answer;
    }
	
	//String에 해당하는 문자의 갯수를 담을 배열 클래스
	class Count{
		int[] num = new int[10];

		//선언과 동시에 배열을 생성하게하는 생성자
		public Count(String x) {
			String[] x1 = x.split("");
			
			
			for(int i=0; i<x1.length; i++) {
				switch(x1[i]) {
				case "0" : num[0]++;break;
				case "1" : num[1]++;break;
				case "2" : num[2]++;break;
				case "3" : num[3]++;break;
				case "4" : num[4]++;break;
				case "5" : num[5]++;break;
				case "6" : num[6]++;break;
				case "7" : num[7]++;break;
				case "8" : num[8]++;break;
				case "9" : num[9]++;break;
				}
			}
		}
		

	}
	//테스트
	public static void main(String[] args) {
		Ex02 ex = new Ex02();
		
		System.out.println(ex.solution("100", "2345"));			//-1
		System.out.println(ex.solution("100", "123450"));		//10
		System.out.println(ex.solution("100", "203045"));		//0
		System.out.println(ex.solution("12321", "42531"));		//321
		System.out.println(ex.solution("00000100000", "0001110000"));	//10000000

	}

}
