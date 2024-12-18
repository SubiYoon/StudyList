package Algorithm.ProgrammersLv2;

/*
이진 변환 반복하기
이진수 s에 대하여 0을 제거
제거하고 남은 String의 길이 = c
c를 이진수로 표현한 String을 다시 s라고 함
s에 대하여 0을 제거 ... (반복)
마지막에 1이 남을때까지 반복하여 반복한 횟수를 index 0에 제거한 0의 갯수를 index 1에 담아 return
 */
public class ChangeBinaryRoop {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int zeroCount = 0;
        int roopCount = 0;

        StringBuilder sb = new StringBuilder(s);
        
        while(!sb.toString().equals("1")){
            for(int i=0; i<sb.length(); i++){
                if(sb.charAt(i)=='0'){
                    zeroCount++;
                    sb.deleteCharAt(i);
                    i--;
                }
            }
            String strLength = "";
            strLength = Integer.toBinaryString(sb.length());
            sb.delete(0, sb.length());
            sb.append(strLength);

            roopCount++;
        }

        answer[0] = roopCount;
        answer[1] = zeroCount;

        return answer;
    }
}
