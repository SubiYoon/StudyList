package Algorithm.ProgrammersLv1.Day05;
//평균구하기
public class Ex02 {
	    public double solution(int[] arr) {
	        double answer = 0;
	        double sum=0;
	        if(1<=arr.length && arr.length<=100){
	     	   for(int i=0; i<arr.length; i++){
	        	    sum+=arr[i];
	        	}
		    }
	        answer = sum/arr.length;
	        return answer;
	    }
	}