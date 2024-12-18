package Algorithm.ProgrammersLv0.Day04;

/*
 장군개미 공격력 : 5
 병정개미 공격력 : 3
 일개미 공격력 : 1
 최소한의 필요 개미 수 리턴
 */

public class Ex08 {
    public int solution(int hp) {
        int answer = 0;
        
        answer += hp/5;
        hp%=5;
        answer += hp/3;
        hp%=3;
        answer += hp;
        
        return answer;
    }
}
