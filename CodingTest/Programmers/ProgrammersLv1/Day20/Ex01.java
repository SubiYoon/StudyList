package Algorithm.ProgrammersLv1.Day20;
/*
 키패드 무슨손으로 눌렀니??
 
 1,4,7은 왼손(왼손의 시작은 *)
 3,6,9는 오른손(오른손의 시작은 #)
 2,5,8,0은 현재위치에서 가장 가까운 손
 번호의 거리가 같으면 오른손잡이는 오른손 왼손잡이는 왼손을 씀
 */


public class Ex01 {
	Position left;
	Position right;
	Position num;
	public String solution(int[] numbers, String hand) {
		String answer = "";
		
		left = new Position(3,0);
		right = new Position(3,2);
	    
		for(int x : numbers) {
			num = new Position((x-1)/3, (x-1)%3);
			if(x==0) {
				num = new Position(3,1);
			}
			if(num.ga==0) {
				answer+="L";
				left=num;
			}else if(num.ga==2) {
				answer+="R";
				right=num;
			}else {
				int leftDis =Math.abs(num.se-left.se)+Math.abs(num.ga-left.ga);
				int rightDis =Math.abs(num.se-right.se)+Math.abs(num.ga-right.ga);
				
				if(rightDis<leftDis) {
					answer+="R";
					right=num;
				}else if(rightDis>leftDis) {
					answer+="L";
					left=num;
				}else if(hand.equals("left")){
					answer+="L";
					left=num;
				}else if(hand.equals("right")) {
					answer+="R";
					right=num;
				}
			}
		}
	       
	        
		return answer;
	}
	 
	class Position{
		int ga;
		int se;
		 
		Position(int se, int ga){
			this.se=se;
			this.ga=ga;
		}
	}
	public static void main(String[] args) {
		Ex01 ex = new Ex01();
		int[] arr = {1,3,4,5,8,2,1,4,5,9,5};
		int[] arr1 = {7,0,8,2,8,3,1,5,7,6,2};
		int[] arr2 = {1,2,3,4,5,6,7,8,9,0};
		System.out.println(ex.solution(arr, "right")); // "LRLLLRLLRRL"
		System.out.println(ex.solution(arr1, "left")); // "LRLLLRLLRRL"
		System.out.println(ex.solution(arr2, "right")); // "LRLLLRLLRRL"

	}

}
