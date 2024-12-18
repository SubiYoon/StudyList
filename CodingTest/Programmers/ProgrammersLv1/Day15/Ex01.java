package Algorithm.ProgrammersLv1.Day15;
//2016년 요일계산
public class Ex01 {
	public static String solution(int a, int b) {
	       String answer = "";
	       int date =0;
	       
	       //달별 날짜 카운트
	       switch(a) {
	       case 1 : break;
	       case 2 :	date=31; break;
	       case 3 : date=60; break;
	       case 4 : date=91; break;
	       case 5 : date=121; break;
	       case 6 : date=152; break;
	       case 7 : date=182; break;
	       case 8 : date=213; break;
	       case 9 : date=244; break;
	       case 10 : date=274; break;
	       case 11 : date=305; break;
	       case 12 : date=335; break;
	    	   
	       }
	       //총 날짜 카운트
	       date+=(b-1);
	       
	       //7로 나눠서 나머지 계산
	       switch(date%7) {
	       case 0 : answer = "FRI"; break;
	       case 1 : answer = "SAT"; break;
	       case 2 : answer = "SUN"; break;
	       case 3 : answer = "MON"; break;
	       case 4 : answer = "TUE"; break;
	       case 5 : answer = "WED"; break;
	       case 6 : answer = "THU"; break;
	       }

	
	       return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		System.out.println(solution(5,24));

	}

}
