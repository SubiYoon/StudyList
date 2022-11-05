package test;

public class SuperLevel extends PlayerLevel{
	@Override
	public void run() {
		System.out.println("매우 빠르게 달립니다.");
	}

	@Override
	public void jump() {
		System.out.println("더 높게 Jump합니다.");
	}
	
	@Override
	public void turn() {
		System.out.println("Turn 합니다.");
	}
	
	@Override
	public void showLevelMessage() {
		System.out.println("***** 상급자 레벨 *****");
	}
}
