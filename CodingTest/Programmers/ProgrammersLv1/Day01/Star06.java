package Algorithm.ProgrammersLv1.Day01;

public class Star06 {

	public static void main(String[] args) {
		//11 1 11
		
		for(int i=1; i<7; i++) {
			for(int j=12; j>i; j--) {
				System.out.print(" ");
			}
			
			for(int k=0; k<2*i-1; k++) {
				System.out.print("*");
			}
			
			System.out.println();
		}
		
		for(int i=1; i<7; i++) {
			for(int j=6; j>i; j--) {
				System.out.print(" ");
			}
			
			for(int k=0; k<2*i-1; k++) {
				System.out.print("*");
			}
			
			for(int t=12; t>2*i-1; t--) {
				System.out.print(" ");
			}
			
			for(int k=0; k<2*i-1; k++) {
				System.out.print("*");
			}
			
			System.out.println();
		}
	}

}
