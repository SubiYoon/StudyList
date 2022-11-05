package bookshelf;

public interface Queue {
	void enqueue(String title);
	String deQueue();
	int getSize();
}
