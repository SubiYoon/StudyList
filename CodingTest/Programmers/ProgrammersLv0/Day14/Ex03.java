package Algorithm.ProgrammersLv0.Day14;

public class Ex03 {
    public int solution(int balls, int share) {
        return combination(balls, share);
    }
    
    //조합공식
    public int combination(int n, int r) {
		if(n == r || r == 0) 
			return 1; 
		else 
			return combination(n - 1, r - 1) + combination(n - 1, r); 
	}
}
