package Algorithm.ProgrammersLv1.Day06;
//숫자 내림차순 만들기
public class Ex02 {

	    public static  long solution(long n) {
	        long answer = 0;
		    if(1<=n && n<=8000000000L) {
		       
		    	//문자열 배열에 넣고 정수 배열에 다시 집어 넣기
		    	
		    	String str = String.valueOf(n);
		        String[] data = str.split("");
		        long[] num = new long[data.length];
		        for(int i=0; i<num.length; i++){
		        	num[i] = Integer.parseInt(data[i]);
		        }
		        
		        //내림차순으로 정렬
		        
		        for(int i=0; i<num.length-1; i++) {
		        	for(int j=i; j<num.length; j++) {
		        		if(num[i]>=num[j]) {
		        			continue;
		        		}else {
		        			long temp = num[i];
		        			num[i] = num[j];
		        			num[j] = temp;
		        		}
		        	}
		        }
		        
		        //배열 없애기
		        
		       StringBuffer sb = new StringBuffer();
		       
		       for(int i=0; i<num.length; i++) {
		    	   data[i] = String.valueOf(num[i]);
		    	   sb.append(data[i]);
		       }
		       
		       //long형으로 변환
		       
		       answer = Long.valueOf(sb.toString());
		       
		    }
	        
		    //반환
	        
	        return answer;
	    }
	
	//실험
	    
	public static void main(String[] args) {
		System.out.println(solution(123584783));

	}

}
