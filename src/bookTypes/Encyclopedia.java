/* 
 * Class Name: Encyclopedia
 * Class Description: Defines the Encyclopedia class, which extends the Book Abstract Class.
 * 
 */

package bookTypes;

import main.Book;

public class Encyclopedia extends Book {
	private String type;
	
	public Encyclopedia(String id, String title, String author, int year) {
		super(id,title,author,year);
		this.type = "Encyclopedia";
	}
	
	public String getType(){
		return this.type;
	}

}
