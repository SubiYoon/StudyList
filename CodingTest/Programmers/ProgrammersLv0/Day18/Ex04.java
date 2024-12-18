package Algorithm.ProgrammersLv0.Day18;

/*
평행
배열에 네개의 좌표가 주어짐
해당 좌표로 만든 두 직선이 평행 여부 판별
가능하면 return 1
불가능하면 return 0
 */
public class Ex04 {
    public int solution(int[][] dots) {
        int answer = 0;
        
        //기울기를 비교
        if(top(dots[0],dots[1])/bottom(dots[0],dots[1])==top(dots[2],dots[3])/bottom(dots[2],dots[3])){
            return 1;
        }else if(top(dots[0],dots[2])/bottom(dots[0],dots[2])==top(dots[1],dots[3])/bottom(dots[1],dots[3])){
            return 1;
        }else if(top(dots[0],dots[3])/bottom(dots[0],dots[3])==top(dots[1],dots[2])/bottom(dots[1],dots[2])){
            return 1;
        }

        return answer;
    }
    //분자 구하는 함수
    public double top(int[] arr1, int[] arr2){
        double result = 0;

        if(arr1[0]>arr2[0]){
            result = arr1[0]-arr2[0];
        }else {
            result = arr2[0]-arr1[0];
        }
        
        return result;
    }
    //분모 구하는 함수
    public double bottom(int[] arr1, int[] arr2){
        double result = 0;

        if(arr1[1]>arr2[1]){
            result = arr1[1]-arr2[1];
        }else {
            result = arr2[1]-arr1[1];
        }

        return result;
    }
}
