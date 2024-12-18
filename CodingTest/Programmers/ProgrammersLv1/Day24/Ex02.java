package Algorithm.ProgrammersLv1.Day24;

/*
7개의 선택지가 존재(점수)
매우 비동의(3), 비동의(2), 약간 비동의(1)
모름(0)
약간 동의(1), 동의(2), 매우 동의(3)
모름(0)을 기준으로 survey에 비동의면 charAt(0)의 점수 추가
모름(0)을 기준으로 survey에 동의면 charAt(1)의 점수 추가
*/
public class Ex02 {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Test r = new Test('R', 0);
        Test t = new Test('T', 0);
        Test c = new Test('C', 0);
        Test f = new Test('F', 0);
        Test j = new Test('J', 0);
        Test m = new Test('M', 0);
        Test a = new Test('A', 0);
        Test n = new Test('N', 0);


        for(int i=0; i<choices.length; i++){
            if(choices[i]==4) continue;
            if(choices[i]<4){
                switch(survey[i].charAt(0)){
                    case 'R' : r.score+=score(choices[i]); break;
                    case 'T' : t.score+=score(choices[i]); break;
                    case 'C' : c.score+=score(choices[i]); break;
                    case 'F' : f.score+=score(choices[i]); break;
                    case 'J' : j.score+=score(choices[i]); break;
                    case 'M' : m.score+=score(choices[i]); break;
                    case 'A' : a.score+=score(choices[i]); break;
                    case 'N' : n.score+=score(choices[i]); break;
                }
            }else {
                switch(survey[i].charAt(1)){
                    case 'R' : r.score+=(choices[i]-4); break;
                    case 'T' : t.score+=(choices[i]-4); break;
                    case 'C' : c.score+=(choices[i]-4); break;
                    case 'F' : f.score+=(choices[i]-4); break;
                    case 'J' : j.score+=(choices[i]-4); break;
                    case 'M' : m.score+=(choices[i]-4); break;
                    case 'A' : a.score+=(choices[i]-4); break;
                    case 'N' : n.score+=(choices[i]-4); break;
                }
            }
        }

        if(r.score >= t.score){
            answer += r.abc;
        }else answer += t.abc;
        if(c.score >= f.score){
            answer += c.abc;
        }else answer += f.abc;
        if(j.score >= m.score){
            answer += j.abc;
        }else answer += m.abc;
        if(a.score >= n.score){
            answer += a.abc;
        }else answer += n.abc;
        return answer;
    }

    public int score(int choices){
        int score = 0;
        if(choices==1){
            score=3;
        }else if(choices==2){
            score=2;
        }else if(choices==3){
            score=1;
        }
        return score;
    }

    class Test{
        char abc;
        int score;

        public Test(char abc, int score){
            this.abc = abc;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Ex02 ex = new Ex02();
        String[] survey1 = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choice1 = {5,3,2,7,5};
        String[] survey2 = {"TR", "RT", "TR"};
        int[] choice2 = {7,1,3};

        System.out.println(ex.solution(survey1, choice1));  //TCMA
        System.out.println(ex.solution(survey2, choice2));  //RCJA
    }
}
