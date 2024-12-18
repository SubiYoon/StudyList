package Algorithm.ProgrammersLv2;

/*
카펫
테두리는 갈색, 안쪽은 노란색으로 되어 있는 카펫
집으로 돌아와 본 카펫의 노란색, 갈색 카펫의 갯수만 기억남
가로 >= 세로
가로, 세로 크기를 순서대로 배열에 담아 return
 */
public class Carpet {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        for(int i=1; i<=yellow; i++){
            if(yellow%i==0){
                answer[0] = Math.max(i, yellow/i) + 2;
                answer[1] = Math.min(i, yellow/i) + 2;
            }
            if((answer[0]) * (answer[1]) == brown + yellow){
                break;
            }
        }

        return answer;
    }
}
