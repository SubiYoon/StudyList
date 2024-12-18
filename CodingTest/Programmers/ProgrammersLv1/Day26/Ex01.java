package Algorithm.ProgrammersLv1.Day26;

/*
 1번 ~ number번 까지 지정된 기사가 있다.
 자신 번호의 약수 개수에 해당하는 공격력을 가진 무기를 구매하려 함
 무기 공격력에는 limit가 있음 limit초과시 power를 사야함
 무기 공격력당 1kg의 철이 필요
 총 필요한 철의 무게는??
 */
public class Ex01 {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        int[] weapon = new int[number];
        weapon[0] = 1;
        if(number>=2){
            weapon[1] = 2;
        }
        if(number>=3){
            weapon[2] = 2;
        }
        for(int i=3; i<number; i++){
            if(Math.sqrt(i+1)%1==0){
                for(int j=1; j<Math.sqrt(i); j++){
                    if((i+1)%j==0){
                        weapon[i]++;
                    }
                }
                weapon[i]=weapon[i]*2+1;
            }else{
                for(int j=1; j<=Math.sqrt(i); j++){
                    if((i+1)%j==0){
                        weapon[i]++;
                    }
                }
                weapon[i]*=2;
            }
        }

        for(int i=0; i<number; i++){
            if(weapon[i]>limit){
                weapon[i]=power;
            }
            answer+=weapon[i];
        }

        return answer;
    }
    public static void main(String[] args) {
        Ex01 ex = new Ex01();
        System.out.println(ex.solution(5, 3, 2));   //10
        System.out.println(ex.solution(10, 3, 2));  //21
    }
}
