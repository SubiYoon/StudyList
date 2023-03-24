package exercise.chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Book{
	private String name;
	private int price;
	
	public Book(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}

public class LibraryTest {
	public static void main(String[] args) {
		List<Book> bookList = new ArrayList<>();

		bookList.add(new Book("자바", 25000));
		bookList.add(new Book("파이썬", 15000));
		bookList.add(new Book("안드로이드", 30000));
		
		System.out.println(bookList.stream().mapToInt(c->c.getPrice()).sum());
		bookList.stream().filter(b->b.getPrice() >= 20000).map(b->b.getName()).sorted().forEach(s->System.out.println(s));
	}

}
