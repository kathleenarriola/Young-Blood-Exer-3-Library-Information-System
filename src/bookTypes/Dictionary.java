/* 
 * Class Name: Dictionary
 * Class Description: Defines the Dictionary class, which extends the Book Abstract Class.
 * 
 */

package bookTypes;

import main.Book;

public class Dictionary extends Book {
	private String type;
	
	public Dictionary(String id, String title, String author, int year) {
		super(id,title,author,year);
		this.type = "Dictionary";
	}
	
	public String getType(){
		return this.type;
	}

}
