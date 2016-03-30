/* 
 * Class Name: Library
 * Class Description: Defines the Library class, where books are stored using a hashmap.
 * 
 */

package main;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Library {

	public Library() {
		ArrayList<Book> library = new ArrayList<Book>();
	}
	
	public void readFromFile(){
		try{
			File file = new File("input.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null){
				System.out.println(line);
				}
			reader.close( );
			}
		catch(Exception ex) {
			ex.printStackTrace( );  //prints the error on the screen 
			System.out.println(" File \"input.txt\" not found.");
			}
	}

}
