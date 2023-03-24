package exercise.chapter10;

public class HeapSort implements Sort{

	@Override
	public void ascending(int[] arr) {
		System.out.println("HeapSort ascending");
		
	}

	@Override
	public void descending(int[] arr) {
		System.out.println("HeapSort deScending");
		
	}
	
	@Override
	public void description() {
		Sort.super.description();
		System.out.println("HeapSort 입니다.");
	}
}