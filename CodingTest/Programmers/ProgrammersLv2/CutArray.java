package Algorithm.ProgrammersLv2;

/*
n^2 배열 자르기
 */
public class CutArray {
    // 실패 풀이
    // public int[] solution(int n, long left, long right) {
    //     long size = right - left;
    //     int[] answer = new int[(int) size + 1];
    //     int[][] arr = new int[n][n];
    //     long indexCount = 0;
    //     int indexAnswer = 0;

    //     // n*n배열 만들기
    //     // TODO:메모리초과로 실패
    //     for (int i = 0; i < n; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (i == j) {
    //                 arr[i][j] = i + 1;
    //             } else if (i > j) {
    //                 arr[i][j] = i + 1;
    //             } else if (i < j) {
    //                 arr[i][j] = j + 1;
    //             }
    //             if (left <= indexCount && indexCount <= right) {
    //                 answer[indexAnswer] = arr[i][j];
    //                 indexAnswer++;
    //             }
    //             indexCount++;
    //         }
    //     }

    //     return answer;
    // }

    //정답풀이
    public int[] solution(int n, long left, long right) {
        long size = right - left;
        int[] answer = new int[(int) size + 1];

        for (int i = 0; i < answer.length; i++) {
            int x = (int) (left / n) + 1;
            int y = (int) (left % n) + 1;
            answer[i] = Math.max(x, y);
            left++;
        }

        return answer;
    }
}
