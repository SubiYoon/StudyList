package Algorithm.ProgrammersLv2;
/*
다음 큰 숫자
자연수 n이 주어졌을 때 
조건 1. n의 다음 큰 숫자는 n보다 큼
조건 2. n의 다음 큰 숫자와 n은 2진수로 변환시 1의 갯수가 같음
조건 3. 조건 1,2를 만족하는 가장 작은 수
ex) 78(1001110)의 다음 큰 숫자는 83(1010011)
 */
public class NextBiggerNumber {
    public int solution(int n) {
        //n에 대한 다음 변수 1의 갯수가 같아야하므로 최소+2
        int nextNum = n+1;
        //n에 대한 필요 변수들
        String originalNumStr = Integer.toBinaryString(n);
        int originalNumStrCountOne = 0;

        //n을 이진수로 변경하여 1의 갯수를 count
        for(int i=0; i<originalNumStr.length(); i++){
            if(originalNumStr.charAt(i)=='1'){
                originalNumStrCountOne++;
            }
        }

        while(true){
            String nextNumStr = Integer.toBinaryString(nextNum);
            int nextNumStrCountOne = 0;

            for(int i=0; i<nextNumStr.length(); i++){
                if(nextNumStr.charAt(i)=='1'){
                    nextNumStrCountOne++;
                }
            }

            if(originalNumStrCountOne==nextNumStrCountOne){
                return nextNum;
            }else{
                nextNum++;
            }
        }
    }
}
