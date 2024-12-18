package Algorithm.ProgrammersLv1.Day11;
//같은 숫자는 싫어
import java.util.Arrays;
import java.util.Stack;

public class Ex03 {
	public static int[] solution(int []arr) {		
		//스택생성
		Stack<Integer> stack = new Stack<>();
		
		//초기값 녀석넣기
		stack.push(arr[0]);
		
		//넣을 요소가 마지막에 들어간 요소랑 같으면 들어가있는녀석 빼고 추가
		for(int i=1; i<arr.length; i++) {
			if(stack.lastElement()==arr[i]) {
				stack.pop();
			}
			stack.push(arr[i]);
			
		}
		
		//스택사이즈에 맞는 배열 생성
		int[] answer = new int[stack.size()];
		
		//배열에 집어 넣기...
		for(int i=0; i<stack.size(); i++) {
			answer[i] = stack.get(i);
		}
		
		return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		int[] arr = {1,1,3,3,0,1,1};
		System.out.println(Arrays.toString(solution(arr))); 

	}

}
