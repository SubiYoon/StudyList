package bookshelf;

public class BookShelfTest {
	public static void main(String[] args) {
		Queue shelfQueue = new BookShelf();
		shelfQueue.enqueue("태백산맥 1");
		shelfQueue.enqueue("태백산맥 2");
		shelfQueue.enqueue("태백산맥 3");
		
		System.out.println(shelfQueue.deQueue());
		System.out.println(shelfQueue.deQueue());
		//System.out.println(shelfQueue.deQueue());
		
		System.out.println(shelfQueue.getSize());
	}

}
