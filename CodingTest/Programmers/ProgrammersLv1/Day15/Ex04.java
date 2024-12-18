package Algorithm.ProgrammersLv1.Day15;
//모의고사 (찍기문제)
import java.util.Arrays;

public class Ex04 {
	public static int[] solution(int[] answers) {
		int[] answer = {};
       
		int[] po1 = {1,2,3,4,5};
		int[] po2 = {2,1,2,3,2,4,2,5};
		int[] po3 = {3,3,1,1,2,2,4,4,5,5};
		
		//점수 카운트
		int po1s = 0;
		int po2s = 0;
		int po3s = 0;
	       
	     
	    //찍는번호 인덱스값 초기화
		int x=0;
		
		//수포자1 채점
		for(int i=0; i<answers.length; i++) {
			if(x==5) {
				x=0;
			}
			if(answers[i]==po1[x]) {
				po1s++;
			}
			x++;   
		}
		
		//찍는번호 인덱스값 초기화
		x= 0;
		
		//수포자2 채점
		for(int i=0; i<answers.length; i++) {
			if(x==8) {
				x=0;
			}	
			if(answers[i]==po2[x]) {
				po2s++;
			}
			x++;   
		}
		
		//찍는번호 인덱스값 초기화
		x=0;
		
		//수포자3 채점
		for(int i=0; i<answers.length; i++) {
	       if(x==10) {
	    	   x=0;
	       }
	       if(answers[i]==po3[x]) {
	    	   po3s++;
	       }
	       x++;   
		}
		
		
		//채점 결과로 배열에 집어넣기
	    if(po1s > po2s && po1s > po3s) {
	    	answer = new int[1];
	    	answer[0] = 1;
	    }else if(po1s < po2s && po2s > po3s) {
	    	answer = new int[1];
	    	answer[0] = 2;
	    }else if(po3s > po2s && po1s < po3s) {
	    	answer = new int[1];
	    	answer[0] = 3;
	    }else if(po1s == po2s && po1s > po3s) {
	    	answer = new int[2];
	    	answer[0] = 1;
	    	answer[1] = 2;
	    }else if(po1s > po2s && po1s == po3s) {
	    	answer = new int[2];
	    	answer[0] = 1;
	    	answer[1] = 3;
	    }else if(po3s == po2s && po1s < po3s) {
	    	answer = new int[2];
	    	answer[0] = 2;
	    	answer[1] = 3;
	    }else if(po3s == po2s && po1s == po3s) {
	    	answer = new int[3];
	    	answer[0] = 1;
	    	answer[1] = 2;
	    	answer[2] = 3;
	    }
	
	    return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		System.out.println(Arrays.toString(solution(arr)));

	}

}
