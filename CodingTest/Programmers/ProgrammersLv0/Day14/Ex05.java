package Algorithm.ProgrammersLv0.Day14;

import java.util.Arrays;

public class Ex05 {
    public int solution(String[] spell, String[] dic) {
        int answer = 2;

        Arrays.sort(spell);

        //dic의 요소와 spell의 갯수가 같은지 확인 후 실행
        first:for(int i=0; i<dic.length; i++){
            if(spell.length == dic[i].length()){
                dic check = new dic(dic[i]);

                Arrays.sort(check.getSpell());

                for(int j=0; j<spell.length; j++){
                    if(!spell[j].equals(check.getSpell()[j])){
                        answer = 2;
                        continue first;
                    }
                    answer = 1;
                }
            }
        }

        return answer;
    }

    class dic{
        String[] spell;

        public dic(String dic){
            this.spell = new String[dic.length()];

            for(int i=0; i<dic.length(); i++){
                this.spell[i] = dic.split("")[i];
            }
        }

        public String[] getSpell(){
            return spell;
        }
    }
}
