package Algorithm.ProgrammersLv1.Day16;

import java.util.ArrayList;
import java.util.Arrays;
//실패율
/*
 실패율 = 클리어하지 못함 /스테이지 도달
 N = total stage ( 1<= N N=500 )
 stages[] = 사용자가 멈춰있는 스테이지의 번호 (length <= 200000, 1 <= stages[i] <= N+1)
 
 실패율이 같으면 낮은순으로 출력
 
 N+1은 마지막 스테이지 까지 클리
 
 실패율이 높은 스테이지부터 내림차순으로 스테이지 번호가 담겨있는 배열 리턴
 
 스테이지에 도달한적이 없으면 실패율은 0으로 간주함
 
 */
public class Ex02 {
	public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        
                
        ArrayList<Integer> al = new ArrayList<>();
        
        //각 스테이지에 남아있는 인원수
        for(int i=1; i<=N+1; i++) {
        	int x =0;
        	for(int j=0; j<stages.length; j++) {
        		if(i==stages[j]) {
        			x++;
        		}
        	}
        	al.add(x);
        }
        
        //각 스테이지를 통과한 인원수
        int x = 0;
        int countx = stages.length;
        ArrayList<Integer> arr = new ArrayList<>();
        while(true) {
        	if(x==al.size()) break;
        	int county = al.get(x);
        	int count = countx - county;
        	x++;
        	arr.add(count);
        	countx=count;

        }
        
        System.out.println(al);
        System.out.println(arr);
        
        //기준이 될 실패할 확률 계산(남아있는수/통과한수 + 남아있는수)
        double[] p = new double[arr.size()-1];
        for(int i=0; i<p.length; i++) {
        	if((al.get(i)+arr.get(i))>0) {
        		p[i] = (double)al.get(i)/(al.get(i)+arr.get(i));
        	}else if((al.get(i)+arr.get(i))==0) {
        		p[i]=0;
        	}
        }

        
        //배교할 대상 배열인 dp생성
        double[] dp = new double[p.length];
        for(int i=0; i<p.length; i++) {
        	dp[i]=p[i];
        }
                
        //기준을 잡기위해 실패율이 높은 순으로 정렬
        answer = new int[p.length];
        for(int i=0; i<p.length-1; i++) {
        	for(int j=i+1; j<p.length; j++) {
        		if(p[i]<p[j]){
        			double temp = p[i];
        			p[i] = p[j];
        			p[j] = temp;
        		}
        	}
        }
       
        // 기준이될 배열 p와 비교할대상인 dp배열을 비교하여 인덱스 값을 추출
        // 해당과정에서 값이 겹쳐 인덱스값이 중복될 수 있음
        // 따라서 answer배열에 대입한 일덱스의 값을 100으로 만들어 비교할 수 없게 만듬
        for(int i=0; i<p.length; i++) {
       		for(int j=0; j<p.length; j++) {
           		if(p[i]==dp[j]) {
           			answer[i]=j+1;
           			dp[j]=100;
            		break;
            		}
        	}
        }
        return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		int[] arr1 = {2,1,2,6,2,4,3,3};
		int[] arr2 = {4,4,4,4,4};
		
		System.out.println(Arrays.toString(solution(5, arr1)));
		System.out.println(Arrays.toString(solution(4, arr2)));
	}

}
