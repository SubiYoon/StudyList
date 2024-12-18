package Algorithm.ProgrammersLv2;

import java.util.HashMap;

/*
영어 끝말잇기
사람 수 총 n명
words배열은 대답한 단어들
똑같은 단어 2번 등장시 탈락
끝단어와 첫단어가 일치하지 않으면 탈락
탈락자 번호와 탈락자가 탈락한 대답차수를 return
탈락자가 없을경우 0,0 return
 */
public class EnglishWordChain {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        // 나중에 1을 뺴주어야함
        int personNum = 1;
        //몇번째 차례인지 카운트
        int[] personNumCount = new int[n];

        for (int i = 0; i < n; i++) {
            personNumCount[i] = 0;
        }

        //중복단어 처리를 위한 HashMap
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], 0);
        }

        //로직
        for (int i = 0; i < words.length; i++) {
            map.replace(words[i], map.get(words[i]) + 1);
            personNumCount[personNum - 1]++;

            if(words[i].length()==1){   //한글자인 단어 사용불가
                answer[0] = personNum;
                answer[1] = personNumCount[personNum - 1];
                break;
            }else if (map.get(words[i]) == 2) { //중복단어 사용 불가
                answer[0] = personNum;
                answer[1] = personNumCount[personNum - 1];
                break;
            }else if (i < words.length - 1 && words[i].charAt(words[i].length() - 1) != words[i + 1].charAt(0)) {   //끝말잇기 실패시 사용 불가
                personNum++;
                if (personNum > n) {
                    personNum = 1;
                }
                answer[0] = personNum;
                answer[1] = personNumCount[personNum - 1] + 1;  //다음차례에 끝말잇기가 실패이므로 +1
                break;
            }

            //사람 번호 인원수 초과시 다시 처음사람이 시작
            personNum++;
            if (personNum > n) {
                personNum = 1;
            }
        }

        return answer;
    }
}