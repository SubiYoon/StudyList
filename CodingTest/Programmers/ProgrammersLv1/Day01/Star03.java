package Algorithm.ProgrammersLv1.Day01;

public class Star03 {

	public static void main(String[] args) {
		
		int lc =6;
			
		for(int i=1; i<=lc; i++) {
			for(int k=lc; k>i;k--) {
				System.out.print(" ");
			}
			
			for(int j=0;j<i;j++) {
				System.out.print("*");
			}
			
			for(int t=1; t<i; t++) {
				System.out.print("*");
			}
		System.out.println();
		}
	}

}
