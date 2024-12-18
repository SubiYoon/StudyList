package Algorithm.ProgrammersLv1.Day22;

/*
 조카가 발음 할 수 있는 단어의 갯수를 반환
 "aya", "ye", "woo", "ma"
 연속 발음 안됨 ex) "yeye", "mama"
 두단어가 연결된 단어는 가능 ex) "aya+ye"
 */
public class Ex02 {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] pass = {"aya", "ye", "woo", "ma"};
        String[] nonPass = {"ayaaya" , "yeye", "woowoo", "mama"};
        
        //각 배열 하나하나씩 체크
        for(String bab : babbling){
            
            //같은 음절 반복하는거 ?로 체크
            for(String n : nonPass){
                bab = bab.replace(n, "?");
            }

            //발음 가능한거 !로 체크
            for(String p : pass){
                bab = bab.replace(p, "!");
            }

            //해당 문자열에 !가 아닌 것들이 있으면 count에 1을 대입하고 빠져나감
            int count=0;
            for(int i=0; i<bab.length(); i++){
                if(!(bab.charAt(i)=='!')){
                    count=1;
                    break;
                }
            }

            //반복문 끝나고 전부 !일시 answer을 1증가
            if(count==0){
                answer++;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        Ex02 ex = new Ex02();
        String[] arr1 = {"aya", "yee", "u", "maa"}; 
        String[] arr2 = {"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
        System.out.println(ex.solution(arr1));  //1
        System.out.println(ex.solution(arr2));  //2
    }
}
