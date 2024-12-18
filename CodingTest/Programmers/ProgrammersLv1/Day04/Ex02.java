package Algorithm.ProgrammersLv1.Day04;

public class Ex02 {
	public int[] solution(long n) {
	    String str = String.valueOf(n);

	    String[] arr = str.split("");
		
	    int[] answer = new int[arr.length];
		
	    for(int i=0; i<arr.length; i++) {
			 answer[(arr.length-1)-i] = Integer.valueOf(arr[i]);
		}
	       return answer;
    }
}