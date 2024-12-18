package Algorithm.ProgrammersLv1.Day12Day13;
//최소직사각형 (명함)
public class Ex01 {
	public static int solution(int[][] sizes) {
		 int answer = 0;
	     
		 //배열중 큰숫자가 앞으로 스와핑
		 for(int i = 0; i<sizes.length; i++) {
			 if(sizes[i][0]<sizes[i][1]) {
			 	int temp = sizes[i][0];
			 	sizes[i][0] = sizes[i][1];
			 	sizes[i][1] = temp;
			 }
		 }
		 //모든 숫자에 대해 큰수가 가로, 작은수가 세로로 통일
	     //앞이 가로, 뒤가 세로
		 int ga=sizes[0][0];
		 int se=sizes[0][1];
		 
		 //가로, 세로 중 가장 큰수를 뽑아냄
		 for(int i=1; i<sizes.length; i++) {
			 ga=(sizes[i][0]>ga) ? sizes[i][0] : ga;
			 se=(sizes[i][1]>se) ? sizes[i][1] : se;
		 }

		 answer = ga*se;

	    return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		int[][] arr1 = {{60,50}, {30,70}, {60,30}, {80,40}};
		
		System.out.println(solution(arr1));
	
		
	}

}
