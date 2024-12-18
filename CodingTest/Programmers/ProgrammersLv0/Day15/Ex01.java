package Algorithm.ProgrammersLv0.Day15;

public class Ex01 {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = {0,0};
        
        //keyinput만큼의 데이터 처리 상하좌우 기준은 좌표평면
        for(int i=0; i<keyinput.length; i++){
            if(keyinput[i].equals("up")){
                if(answer[1]<0){
                    answer[1]++;
                }
                else if(maxboard2(answer, board)){
                    answer[1]++;
                }else continue;
            }
            else if(keyinput[i].equals("down")){
                if(answer[1]>0){
                    answer[1]--;
                }
                else if(maxboard2(answer, board)){
                    answer[1]--;
                }else continue;
            }
            else if(keyinput[i].equals("right")){
                if(answer[0]<0){
                    answer[0]++;
                }
                else if(maxboard1(answer, board)){
                    answer[0]++;
                }else continue;
            }
            else if(keyinput[i].equals("left")){
                if(answer[0]>0){
                    answer[0]--;
                }
                else if(maxboard1(answer, board)){
                    answer[0]--;
                }else continue;
            }
        }
        
        return answer;
    }
    //board의 좌우 맥스
    public boolean maxboard1(int[] location, int[] board){
        boolean result = true;

        if(Math.abs(location[0]) == (board[0]-1)/2){
            return false;
        }
        
        return result;
    }
    //board의 상하 맥스
    public boolean maxboard2(int[] location, int[] board){
        boolean result = true;

        if(Math.abs(location[1]) == (board[1]-1)/2){
            return false;
        }
        
        return result;
    }
}
