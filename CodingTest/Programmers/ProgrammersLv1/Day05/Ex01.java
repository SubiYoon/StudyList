package Algorithm.ProgrammersLv1.Day05;
//하샤드수 판별
public class Ex01 {
	    public boolean solution(int x) {
	        boolean answer = true;
	        int init = x;
	        int sum=0;
	        
	        if(x>=1 && x<=10000){
	            do{
	                sum+=x%10;
	                x/=10;
	            }while(x!=0);
	            
	            if(init%sum!=0){
	                answer = false;
	            }
	        }
	        return answer;
	    }
	}