/* 
 * Class Name: Novel
 * Class Description: Defines the Novel class, which extends the Book Abstract Class.
 * 
 */

package bookTypes;

import main.Book;

public class Novel extends Book {
	private String type;
	
	public Novel(String id, String title, String author, int year) {
		super(id,title,author,year);
		this.type = "Novel";
	}
	
	public String getType(){
		return this.type;
	}

}
