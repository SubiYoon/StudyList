package Algorithm.ProgrammersLv1.Day20;

import java.util.ArrayList;

/*
 크레인 인형뽑기
 board에 데이터는 숫자가 다르면 다른 인형
 moves 인형을 뽑는 열의 위치를 나타냄
 0은 빈공간을 뜻함
 바구니에 담는과정에서 같은 인형이 연속으로 2개 담아지면 터트려짐
 터진 인형의 갯수를 반환하여 출력
 
 */
public class Ex02 {
	public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        //바구니 list
        ArrayList<Integer> list = new ArrayList<>();
        
        //인형위치 확인용 출력
//        for(int i=0; i<board.length; i++) {
//        	for(int j=0; j<board[i].length; j++) {
//        		System.out.print(board[i][j]+ "\t");
//        	}
//        	System.out.println();
//        }
//        System.out.println("==================================");
        
        //인형을 뽑는 과정
        for(int i=0; i<moves.length; i++) {
        	for(int j=0; j<board.length; j++) {
        		if(board[j][moves[i]-1]!=0) {
        			list.add(board[j][moves[i]-1]);
        			board[j][moves[i]-1]=0;
        			break;
        		}
        	}
        }
        //리스트 확인용 출력
//        System.out.println(list);
        
        //뽑힌 인형들이 연속으로 2개 일시 제거하고 answer에 2를 추가하는 과정
        while(true) { 
        	ArrayList<Integer> list2= new ArrayList<Integer>();
        	for(int i=0;i<list.size(); i++) {
	    	   list2.add(list.get(i));
        	}
	       
        	for(int i=0; i<list.size()-1; i++) {
        		if(list.get(i)==list.get(i+1)) {
        			list.remove(i);
        			list.remove(i);
        			answer+=2;
        		}
        	}
        	//두 리스트 비교용 출력
//        	System.out.println(list2);
//        	System.out.println(list);
        	
        	//터지는 인형이 없을때까지 반복
	        if(list2.equals(list)) {
	        	break;
	        }
        }
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		int[][] arr1 = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] arr2 = {1,5,3,5,1,2,1,4};
		System.out.println(solution(arr1, arr2));

	}

}
