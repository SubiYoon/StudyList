package Algorithm.ProgrammersLv1.Day29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/*
 개인정보 수집 유효기간
 1~n번으로 분류된 n개의 개인정보
 terms는 약간당 개인정보 유효기간 ex)A는 6개월
 privacies -> "수집일자 약관종류"
 파기해야할 개인정보 번호를 리턴(index(0)은 1번)
 */
public class Ex01 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int today_year = Integer.parseInt(today.split("[.]")[0]);
        int today_month = Integer.parseInt(today.split("[.]")[1]);
        int today_date = Integer.parseInt(today.split("[.]")[2]);
        int today_result = today_year*10000 + today_month*100 + today_date;
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<terms.length; i++){
            map.put(terms[i].charAt(0), Integer.parseInt(terms[i].split(" ")[1]));
        }
        
        List<Integer> list = new ArrayList<>();
    
        for(int i=0; i<privacies.length; i++){
            int year = Integer.parseInt(privacies[i].split("[.]")[0]);
            int month = Integer.parseInt(privacies[i].split("[.]")[1]);
            int date = Integer.parseInt(privacies[i].split("[.]")[2].split(" ")[0]);
            int sum = month+map.get(privacies[i].charAt(11));
            
            if(sum>12 && sum%12!=0){
                year += sum/12;
                sum -= (sum/12)*12;
            }else if(sum>12 && sum%12==0){
                year += (sum/12)-1;
                sum = 12;
            }

            int deadLine = year*10000 + sum*100 + date;

            if(deadLine <= today_result){
                list.add(i+1);
            }
        }

        int[] answer = new int[list.size()];

        for(int i=0; i<answer.length; i++){
            answer[i]=list.get(i);
        }

        return answer;
    }
    public static void main(String[] args) {
        Ex01 ex = new Ex01();
        String today1 = "2022.05.19";
        String today2 = "2020.01.01";
        String[] terms1 = {"A 6", "B 12", "C 3"};
        String[] terms2 = {"Z 3", "D 5"};
        String[] privacies1 = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        String[] privacies2 = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
        System.out.println(Arrays.toString(ex.solution(today1, terms1, privacies1)));   //1,3
        System.out.println(Arrays.toString(ex.solution(today2, terms2, privacies2)));   //1,4,5
    }
}
