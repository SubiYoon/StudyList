package Algorithm.ProgrammersLv2;

import java.util.StringTokenizer;

/*
JadenCase 문자열 만들기
JadenCase 문자열이란 단어의 첫 문자가 대문자, 나머지는 소문자로 표기하는 문자열
첫문자가 문자가 아닌경우 다음 문자는 소문자로 표기
1. 공백문자 연속으로 나올 수 있음
2. 숫자로만 이루어진 단어는 없음
3. 숫자는 단어의 첫 문자로만 나옴
 */
public class MakeJadenCaseString {
    public String solution(String s) {
        String str = s.toLowerCase();

        StringTokenizer st = new StringTokenizer(str, " ", true);
        StringBuilder sb = new StringBuilder();

        while(st.hasMoreTokens()){
            String elements = st.nextToken();
            if(elements.startsWith(" ")){
                sb.append(elements);
                continue;
            }
            
            if('0'<=elements.charAt(0) && elements.charAt(0)<='9'){
                sb.append(elements.charAt(0));
            }else {
                sb.append((char)((int)elements.charAt(0)-32));
            }

            for(int i=1; i<elements.length(); i++){
                sb.append(elements.charAt(i));
            }
        }
        
        return sb.toString();
    }
}
