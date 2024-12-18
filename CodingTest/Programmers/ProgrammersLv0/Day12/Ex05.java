package Algorithm.ProgrammersLv0.Day12;

public class Ex05 {
    public String solution(String bin1, String bin2) {
        String answer = "";
        int count = 1;
        int num1 = 0;
        int num2 = 0;
        int result = 0;

        if(bin1.charAt(bin1.length()-1)=='1'){
            num1 = 1;
        }
        if(bin2.charAt(bin2.length()-1)=='1'){
            num2 = 1;
        }

        //이진수 십진수로 변경
        for (int i = bin1.length()-2; i >= 0; i--) {
            if(bin1.charAt(i)=='1'){
                num1 += 2*count;
            }
            count*=2;
        }

        count = 1;

        for (int i = bin2.length()-2; i >= 0; i--) {
            if(bin2.charAt(i)=='1'){
                num2 += 2*count;
            }
            count*=2;
        }
        
        result = num1 + num2;

        answer = Integer.toBinaryString(result);
        
        return answer;
    }
}
