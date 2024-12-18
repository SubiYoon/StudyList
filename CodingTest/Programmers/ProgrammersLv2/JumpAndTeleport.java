package Algorithm.ProgrammersLv2;

/*
점프와 순간이동
한번에 k칸만큼 점프 or 현재까지 온 거리의 두배위치로 순간이동
순간이동 사용시 건전지 사용량 줄지 않음
k칸 점프시 k만큼 사용량 소모
n만큼 떨어져 있는 장소에 도달이 목적
사용해야하는 최소한의 컨전지 소모량을 return
 */
public class JumpAndTeleport {
    public int solution(int n) {
        int ans = 1;

        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                ans++;
                n = (n - 1) / 2;
            }
        }
        return ans;
    }
}