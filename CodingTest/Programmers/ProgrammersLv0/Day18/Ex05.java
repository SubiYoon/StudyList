package Algorithm.ProgrammersLv0.Day18;

/*
옹알이(1)
조카 가능한 발음 "aya","ye","woo","ma"
최대 한번씩 사용해서 발음 가능
다음 배열에서 발음 가능한 배열의 갯수를 return
 */
public class Ex05 {
    public int solution(String[] babbling) {
        int answer = 0;
        String str1 = "aya";
        String str2 = "ye";
        String str3 = "woo";
        String str4 = "ma";

        //해당문자 한칸공백으로 대치
        for(int i=0; i<babbling.length; i++){
            babbling[i] = babbling[i].replaceFirst(str1, " ");
            babbling[i] = babbling[i].replaceFirst(str2, " ");
            babbling[i] = babbling[i].replaceFirst(str3, " ");
            babbling[i] = babbling[i].replaceFirst(str4, " ");
            
            //한칸 공백인것들을 공백으로 대치
            babbling[i] = babbling[i].replace(" ", "");
        }

        //해당 인덱스의 String이 공백이면 answer++
        for(int i=0; i<babbling.length; i++){
            if(babbling[i].equals("")){
                answer++;
            }
        }

        return answer;
    }
}
