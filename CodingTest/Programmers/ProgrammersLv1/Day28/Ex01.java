package Algorithm.ProgrammersLv1.Day28;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/*
 신고 결과 받기
 각 유저(id_list)는 한번에 한명 신고(report)
 신고 횟수 제한 없음, 같은 유저가 같은 유저에게 연속신고해도 1회로 반영
 k번 이상 신고시 이용 정지 -> 신고한 유저에게 메일 발송(마지막에 한꺼번에)
 각 유저별로 처리결과 받은 메일의 총 갯수를 return
 */
public class Ex01 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer =new int[id_list.length];
        int[] count = new int[id_list.length];

        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<id_list.length; i++){
            map.put(id_list[i], i);
        }

        HashSet<String> set = new HashSet<>();
        for(int i=0; i<report.length; i++){
            set.add(report[i]);
        }        

        String[] str = new String[set.size()];
        String[] str2 = new String[set.size()];
        Iterator<String> it = set.iterator();
        int str_count =0;
        while(it.hasNext()){
            String[] str3 = it.next().split(" ");
            str[str_count] = str3[1];
            str2[str_count] = str3[0];
            str_count++;
        }

        for(int i=0; i<str.length; i++){
            if(count[map.get(str[i])]<k){
                count[map.get(str[i])]++;
            }
        }

        for(int i=0; i<count.length; i++){
            if(count[i]>=k){
                for (int j = 0; j < str.length; j++) {
                    if (id_list[i].equals(str[j])) {
                        answer[map.get(str2[j])]++;
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Ex01 ex = new Ex01();
        String[] id1 = { "muzi", "frodo", "apeach", "neo" }; // 1 2 0 2
        String[] id2 = { "con", "ryan" };
        String[] report1 = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
        String[] report2 = { "ryan con", "ryan con", "ryan con", "ryan con" };
        System.out.println(Arrays.toString(ex.solution(id1, report1, 2))); // 2,1,1,0
        System.out.println(Arrays.toString(ex.solution(id2, report2, 3))); // 0,0
    }

}