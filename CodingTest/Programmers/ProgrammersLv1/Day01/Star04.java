package Algorithm.ProgrammersLv1.Day01;

public class Star04 {

	public static void main(String[] args) {
		int lc = 6;
				
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
		
		for(int i=lc;i>1;i--) {
			for(int k=i; k<=lc; k++) {
				System.out.print(" ");
			}
			
			for(int j=i;j>2;j--) {
				System.out.print("*");
			}
			
			for(int t=i; t>1; t--) {
				System.out.print("*");
			}
			
			System.out.println();
		}
	}

}
