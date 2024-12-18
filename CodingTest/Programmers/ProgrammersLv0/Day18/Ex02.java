package Algorithm.ProgrammersLv0.Day18;

/*
안전지대
주뢰 주변 8칸은 모두 위험지역으로 분류
지뢰는 2차원 배열에서 1로 표시
안전 지역의 갯수를 return 하는 함수
 */
public class Ex02 {
    public int solution(int[][] board) {
        int answer = board.length * board.length;

        // 상하좌우 한칸 더 큰 지뢰 배열을 생성
        int[][] mine = new int[board.length + 2][board.length + 2];

        // 새로운 배열안에 기존 지뢰를 복사
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    mine[i + 1][j + 1] = 100;
                }
            }
        }

        // 치환한 숫자 100 이상인 곳 주변의 숫자들의 숫자를 ++
        for (int i = 1; i < mine.length - 1; i++) {
            for (int j = 1; j < mine.length - 1; j++) {
                if (mine[i][j] >= 100) {
                    mine[i - 1][j - 1]++;
                    mine[i - 1][j]++;
                    mine[i][j - 1]++;
                    mine[i + 1][j + 1]++;
                    mine[i + 1][j]++;
                    mine[i][j + 1]++;
                    mine[i - 1][j + 1]++;
                    mine[i + 1][j - 1]++;
                }
            }
        }

        //mine의 최외곽을 제외한 부분의 1이상이 되는 숫자를 카운트
        int count = 0;
        for (int i = 1; i < mine.length - 1; i++) {
            for (int j = 1; j < mine.length - 1; j++) {
                if (mine[i][j] >= 1) {
                    count++;
                }
            }
        }

        return answer - count;
    }
}
