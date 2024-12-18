package Algorithm.ProgrammersLv1.Day14;
//두개 뽑아서 더하기
//배열에서 두 수를 뽑아서 더했을 때 나올수 있는 수를 오름차순으로 담아 반환
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
public class Ex04 {
	public static int[] solution(int[] numbers) {
        //중복불가와 오름차순정렬을 위한 TreeSet
		TreeSet<Integer> ts = new TreeSet<>(); 
        
		//TreeSet에 데이터 삽입
        for(int i=0; i<numbers.length ; i++) {
        	for(int j=i+1; j<numbers.length; j++) {
        		 ts.add(numbers[i]+numbers[j]);
        	}
        }
        
        int[] answer = new int[ts.size()];
        
        //Iterator을 사용, 순회하여 answer에 데이터 삽입
        Iterator<Integer> it = ts.iterator();
        int i=0;
        while(it.hasNext()) {
        	answer[i] = it.next();
        	i++;
        }
        
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		int[] numbers = {2,1,3,4,1};
		System.out.println(Arrays.toString(solution(numbers)));
	}

}
