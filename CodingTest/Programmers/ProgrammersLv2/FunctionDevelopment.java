package Algorithm.ProgrammersLv2;

import java.util.ArrayList;
import java.util.List;

/*
기능개발
각 기능은 진도가 100%일 때 서비스에 반영
각 기능별로 개발 속도가 다름, 하지만 기능은 앞에있는 기능이 배포 될 때 배포됨
각 배포마다 몇개의 기능이 배포가 되는지를 배열로 return
 */
public class FunctionDevelopment {
  public int[] solution(int[] progresses, int[] speeds) {
    int[] answer = {};
    int count = 0;
    int countIndex = 0;

    //배포마다 기능의 수를 담을 list
    List<Integer> list = new ArrayList<>();

    while(true){
      //전체적으로 속도를 더함
      for(int i=countIndex; i<progresses.length; i++){
        progresses[i] += speeds[i];
      }

      //100%가 넘을 때 카운트 up을하고 i시작 위치를 변경
      for(int i=countIndex; i<progresses.length; i++){
        if(progresses[i]>=100){
          count++;
          countIndex = i+1;
        }else break;  //100이상이 안되는 index가 있을 시 반복 종료
      }

      //카운트가 하나이상 있을시 list에 담음
      if(count>0){
        list.add(count);
        count = 0;
      }

      //시작 인덱스 i가 progreesses의 길이를 넘을 때 while문 종료
      if(countIndex==progresses.length){
        break;
      }
    }

    //리턴하는 배열의 갯수를 list사이즈에 맞게 재설정
    answer = new int[list.size()];

    //list의 value를 배열에 담음
    for(int i=0; i<list.size(); i++){
      answer[i] = list.get(i);
    }

    return answer;
  }
}
