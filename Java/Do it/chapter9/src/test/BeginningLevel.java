package test;

public class BeginningLevel extends PlayerLevel {
	@Override
	public void run() {
		System.out.println("달립니다.");
	}

	@Override
	public void jump() {
		System.out.println("Jump할 수 없지뤙~");
	}
	
	@Override
	public void turn() {
		System.out.println("Turn할 수 없지뤙~");
	}
	
	@Override
	public void showLevelMessage() {
		System.out.println("***** 초보자 레벨 *****");
	}
}
