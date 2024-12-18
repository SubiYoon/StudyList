package Algorithm.ProgrammersLv1.Day32;

/*
바탕화면 정리
바탕화면 격자칸에 파일들이 위치해 있음
(0,0)으로 시작 (세로좌표, 가로좌표)로 표현
빈칸은 "."으로 파일이 있는 칸은 "#"으로 표현
드래그하면 파일 선택 -> 삭제가능
최소한의 이동거리를 갖는 한번의 드래그로 모든 파일을 삭제
드래그 격자 시작점 S(lux, luy), 격자 끝점 E(rdx,rdy)로 이동
드래그한 거리 = |rdx - lux| + |rdy - luy|
거리가 가장 작은 S E 점을 return
{lux, luy, rdx, dry} 형식으로 return
 */
public class Ex01 {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];

        // answer[2] 맨위에서 "#"이 포함된 첫번째 [v][]
        for (int i = 0; i < wallpaper.length; i++) {
            if (wallpaper[i].contains("#")) {
                answer[2] = i+1;
            }
        }

        // answer[3] 맨왼쪽에서 "#"이 포함된 첫번째 [][v]
        for (int i = 0; i < wallpaper[0].length(); i++) {
            for (int j = 0; j < wallpaper.length; j++) {
                if (wallpaper[j].charAt(i) == '#') {
                    answer[3] = i+1;
                }
            }
        }

        // answer[0] 맨밑에서 "#"이 포함된 첫번째 [v][]
        for (int i = wallpaper.length - 1; i >= 0; i--) {
            if (wallpaper[i].contains("#")) {
                answer[0] = i;
            }
        }

        // answer[1] 맨오른쪽에서 "#"이 포함된 첫번째 [][v]
        for (int i = wallpaper[0].length() - 1; i >= 0; i--) {
            for (int j = wallpaper.length - 1; j >= 0; j--) {
                if (wallpaper[j].charAt(i) == '#') {
                    answer[1] = i;
                }
            }
        }

        return answer;
    }
}
