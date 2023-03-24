package Operaotr;

public class OperationEx2 {

	public static void main(String[] args) {
		int gameScore = 150;
		
		int lastScore = ++gameScore;
		System.out.println(lastScore);
		//gameScore를 먼저 lastScore에 대입 후 gameScore의 값에 1을 더해서 gameScore값을 151로 만듬
		
		int lastScore2 = gameScore--;
		System.out.println(lastScore2);
		//151인 gameScore를 먼저 lastScore2에 대입 후 gameScore의 값에 1을 빼서 gameScore값을 150을 만듬
		
		int p = gameScore;
		System.out.println(p);
		
		var myAge = 27;
		var value = (myAge > 25);
		System.out.println(value);

	}

}