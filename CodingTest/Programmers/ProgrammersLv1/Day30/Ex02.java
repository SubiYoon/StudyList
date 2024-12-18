package Algorithm.ProgrammersLv1.Day30;

/*
둘만의 암호
s에있는 String에 알파벳당 index를 더함
만약 중간에 skip이 있을 경우 index에 ++을 해서 return
 */
public class Ex02 {
    public String solution(String s, String skip, int index) {
        char[] charList = new char[s.length()];

        //s를 char배열에 담음
        for(int i=0; i<s.length(); i++){
            charList[i]=s.charAt(i);
        }

        for(int i = 0 ; i < charList.length ; i++){
            //index만큼 한칸씩 밀어줌
            for(int j = 0 ; j < index ; j++){
                do{
                    charList[i]++;
                    
                    //'z'를 넘기면 'a'로 내려감
                    if(charList[i] > 'z'){
                        charList[i] -= 26;
                    }
                }while(skip.contains(String.valueOf(charList[i]))); //skip에 해당 문자가 존재하면 계속 밀어줌
            }
        }
        String answer = String.valueOf(charList);
        return answer;
    }
}
