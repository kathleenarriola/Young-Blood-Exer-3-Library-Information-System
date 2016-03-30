/* 
 * Abstract Class Name: Book
 * Abstract Class Description: Defines the Book Abstract Class and what methods should the implementing classes should inherit.
 * 
 */

package main;

public abstract class Book {
	private String id;
	private String title;
	private String author;
	private int year;
	
	public Book(String id, String title, String author, int year){
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
	}
	

	/* GETTERS & SETTERS*/
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
