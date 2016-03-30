/* 
 * Class Name: Textbook
 * Class Description: Defines the Textbook class, which extends the Book Abstract Class.
 * 
 */

package bookTypes;

import main.Book;

public class Textbook extends Book {
	private String type;
	
	public Textbook(String id, String title, String author, int year) {
		super(id,title,author,year);
		this.type = "Textbook";
	}
	
	public String getType(){
		return this.type;
	}

}
