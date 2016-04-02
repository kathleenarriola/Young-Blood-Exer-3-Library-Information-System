/* 
 * Abstract Class Name: Book
 * Abstract Class Description: Defines the Book Abstract Class and what methods should the implementing classes should inherit.
 * 
 */

import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable{

	private String title, author, type, id;
	private int year;

	public Book(String title, String author, int year, String type, String id){
		this.title = title;
		this.author = author;
		this.year = year;
		this.type = type;
		this.id = id;
	}


	/* GETTERS & SETTERS*/
	public String getID() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public int getYear() {
		return this.year;
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
