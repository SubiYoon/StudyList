package Algorithm.ProgrammersLv2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
튜플 : 셀수 있는 수량의 순서있는 열거 or 어떤 순서를 따르는 요소들의 모음
n개의 요소를 가진 튜플을 n-튜플이라고 함
성질
1. 중복된 원소가 있을 수 있음
순서가 정해져있고, 순서가 다르면 서로 다른 튜플임
튜플의 원소의 개수는 유한함
문자열 s가 나타내는 튜플을 배열에 담아 return
 */
public class Tuple {
    public int[] solution(String s) {
        int[] answer = {};
        String str = s.replace("{", "");
        str = str.replace("}", "");
        String[] strNum = str.split(",");

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < strNum.length; i++) {
            if (map.containsKey(strNum[i])) {
                map.replace(strNum[i], map.get(strNum[i]), map.get(strNum[i]) + 1);
            } else {
                map.put(strNum[i], 1);
            }
        }

        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        answer = new int[entryList.size()];
        for (int i = 0; i < entryList.size(); i++) {
            answer[i] = Integer.parseInt(entryList.get(entryList.size() - 1 - i).getKey());
        }

        return answer;
    }
}
