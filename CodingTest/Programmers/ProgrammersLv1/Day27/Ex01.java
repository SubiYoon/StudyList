package Algorithm.ProgrammersLv1.Day27;

import java.util.ArrayList;
import java.util.List;

/*
 햄버거 만들기
 빵(1) 야채(2) 고기(3) 빵(1)
 순으로 되어 있어야 햄버거 1개
 햄버거의 total을 return
 */
public class Ex01 {
    public int solution(int[] ingredient) {
        int answer = 0;
        int count=0;

        List<Integer> list = new ArrayList<>();
        for(int i=0; i<ingredient.length; i++){
            list.add(ingredient[i]);
        }

        do{
            if(count+3>list.size()-1){
                break;
            }
            
            if(list.get(count)==1 && list.get(count+1)==2 && list.get(count+2)==3 &&list.get(count+3)==1){
                answer++;
                for(int i=0; i<4; i++){
                    list.remove(count);
                }
                
                if(count>=3){
                    count-=3;
                }else count=0;

            }else count++;
            
        }while(true);

        return answer;
    }    
    public static void main(String[] args) {
        Ex01 ex = new Ex01();
        int[] arr1 = {2,1,1,2,3,1,2,3,1};
        int[] arr2 = {1,3,2,1,2,1,3,1,2};
        System.out.println(ex.solution(arr1));  //2
        System.out.println(ex.solution(arr2));    //0
    }
}
