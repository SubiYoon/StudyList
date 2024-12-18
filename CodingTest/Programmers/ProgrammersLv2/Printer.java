package Algorithm.ProgrammersLv2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
프린터
location은 현재 대기목록의 위치
숫자가 클수록 중요도가 높음
중요도가 높은 숫자앞에 있는 숫자는 다 뒤로보냄
index는 0번부터(location도 포함)
location의 위치에 있는 요소가 언제 인쇄되는지 return
 */
public class Printer {
  public int solution(int[] priorities, int location) {
    int answer = 0;

    // 우선순위가 가장 높은 녀석을 찾기위한 List
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < priorities.length; i++) {
      list.add(priorities[i]);
    }

    // 넣고 빼고를 할 Queue
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < priorities.length; i++) {
      q.add(priorities[i]);
    }

    // location의 위치를 확인해줄 locationIndex
    int locationIndex = location;

    // 인쇄를 할 반복문
    while (true) {
      MaxValue max = maxNum(list);
      int qPoll = q.poll();
      if (qPoll != max.getMax()) {
        q.add(qPoll);
      } else {
        answer++;
        list.remove(max.getIndex());
        if (locationIndex == 0) {
          break;
        }
      }

      // Queue에서의 location 위치
      locationIndex--;
      if (locationIndex == -1) {
        locationIndex = q.size() - 1;
      }
    }
    return answer;
  }

  // 최댓값과 그 인덱스를 확인할 함수
  private MaxValue maxNum(List<Integer> list) {
    MaxValue mv = new MaxValue(0, 0);

    for (int i = 0; i < list.size(); i++) {
      if (mv.getMax() < list.get(i)) {
        mv.setMax(list.get(i));
        mv.setIndex(i);
      }
    }
    return mv;
  }

  // 최댓값과 그 인덱스를 담을 객체
  class MaxValue {
    int max;
    int index;

    public MaxValue(int max, int index) {
      this.max = max;
      this.index = index;
    }

    public int getMax() {
      return max;
    }

    public void setMax(int max) {
      this.max = max;
    }

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }
  }
}