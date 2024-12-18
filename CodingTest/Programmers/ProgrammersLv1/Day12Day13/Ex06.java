package Algorithm.ProgrammersLv1.Day12Day13;
//문자열 내마음대로 정렬하기
import java.util.Arrays;

public class Ex06 {
	 public static String[] solution(String[] strings, int n) {
			String[] answer = {};

			for (int i=0; i<strings.length-1; i++) {
				for (int j=strings.length-1; j>i; j--)	{
					if(strings[j-1].charAt(n)>strings[j].charAt(n)){
						String tmp=strings[j-1];
						strings[j-1]=strings[j];
						strings[j]=tmp;
					}
					else if(strings[j-1].charAt(n)==strings[j].charAt(n)){
						if(strings[j-1].compareTo(strings[j])>0){ 
							String tmp=strings[j-1];
							strings[j-1]=strings[j];
							strings[j]=tmp;
						}
					}
				}
			}

			answer=strings;

			return answer;
		}
	public static void main(String[] args) {
		String[] str1 = {"sun", "bed", "car"};
		String[] str2 = {"abce", "abcd", "cdx"};
		System.out.println(Arrays.toString(solution(str1,1)));
		System.out.println(Arrays.toString(solution(str2,2)));
	}

}
