package Algorithm.ProgrammersLv2;

import java.util.HashSet;
import java.util.Set;

/*
연속 부분 수열 합의 개수
elements의 요소를 원형으로 배치
1 ~ elements.length 만큼의 요소수의 합을 나열
나열한 숫자의 중복을 제거하고 갯수를 count하여 return
 */
public class NumberOfConsecutivePartialSequenceSums {
  public int solution(int[] elements) {
    int answer = 0;
    int sum = 0;
    int index = 0; // 시작 위치
    int count = 1; // 몇개씩 더할지 카운팅

    // 더한 값의 중복을 없애기 위한 HashSet
    Set<Integer> set = new HashSet<>();

    // 더한 값을 Set에 추가
    while (true) {
      //더하는 갯수가 elements의 길이를 초과하면 break
      if (count > elements.length) {
        break;
      }
      for (int i = 0; i < count; i++) {
        //index가 elements의 길이를 초과하면 길이만큼 빼주어 앞부분부터 연산
        if (index + i >= elements.length) {
          sum += elements[index + i - elements.length];
        } else {
          sum += elements[index + i];
        }
      }

      index++;

      //인덱스가 elements의 길이를 초과하면 더할 갯수를 ++하고 시작 인덱스 0으로 초기화
      if (index >= elements.length) {
        count++;
        index = 0;
      }

      set.add(sum);

      sum = 0;
    }

    // Set의 size를 return
    answer = set.size();

    return answer;
  }
}