package Algorithm.ProgrammersLv0.Day15;

//좌표평면 직사각형 넓이
public class Ex03 {
    public int solution(int[][] dots) {
        int x= 0;
        int y= 0;

        //가로의 길이 : x
        for(int i=0; i<dots.length-1; i++){
            for(int j=i+1; j<dots.length; j++){
                if(dots[i][1]==dots[j][1]){
                    x = Math.abs(dots[i][0]-dots[j][0]);
                }
            }
        }

        //세로의 길이 : y
        for(int i=0; i<dots.length-1; i++){
            for(int j=i+1; j<dots.length; j++){
                if(dots[i][0]==dots[j][0]){
                    y = Math.abs(dots[i][1]-dots[j][1]);
                }
            }
        }

        return x*y;
    }
}
