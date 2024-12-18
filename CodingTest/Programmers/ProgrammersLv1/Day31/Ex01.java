package Algorithm.ProgrammersLv1.Day31;

/*
대충 만든 자판
옛날 폴더폰의 키보드 자판(ex. 1번을 1번누르면 A, 2번누르면 B, 3번누르면 C)
keymap에 있는 키보드 자판으로 targets을 만들 때 최소 눌러야하는 수를 return
 */
public class Ex01 {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        // 최소한의 버튼을 누르는 수의 비교군
        int count = 3000;

        // indexOf를 사용하여 가까운 인덱스를 더해주는 방식
        for (int i = 0; i < targets.length; i++) {
            for (int j = 0; j < targets[i].length(); j++) {
                for (int k = 0; k < keymap.length; k++) {
                    //count가 작으면 count로 계속
                    if (keymap[k].indexOf(targets[i].charAt(j)) > count) {
                        continue;
                    
                    //indexOf가 작으면 count에 indexOf를 대입
                    } else if(0 <= keymap[k].indexOf(targets[i].charAt(j))){
                        count = keymap[k].indexOf(targets[i].charAt(j));
                    }
                }
                //인덱스는 0부터 따라서 1을 더해준 값을 더함
                answer[i] += count+1;
                count=3000;
            }

            //3000을 넘기면 만들 수 없는 수이기 때문에 -1을 반환
            if (answer[i] >= 3000) {
                answer[i] = -1;
            }
        }
        return answer;
    }
}
