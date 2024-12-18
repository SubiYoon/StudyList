package Algorithm.ProgrammersLv2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
위장
배열안에 각행은 [의상이름, 의상의 종류]로 이루어짐
같은 의상종류 동시에 착용 불가능
의상을 조합 할 수 있는 경우의 수를 return
 */
public class Camouflage {
    public int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            if (map.containsKey(clothes[i][1])) {
                map.replace(clothes[i][1], map.get(clothes[i][1]), map.get(clothes[i][1]) + 1);
            } else {
                map.put(clothes[i][1], 1);
            }
        }

        Set<String> set = new HashSet<>();
        for (int i = 0; i < clothes.length; i++) {
            set.add(clothes[i][1]);
        }

        // 종북되는 의상종류 중 착용 안하는 경우가 있기에 +1을 하여 경우의 수 추가
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            int num = map.get(it.next());
            answer *= num + 1;
        }

        return answer - 1; // 모든 의상을 착용안하는 경우는 없으므로 -1을 해줌
    }
}