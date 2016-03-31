/* 
 * Class Name: User
 * Class Description: Defines the User class, which borrows books from the library.
 * 
 */

package main;

import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;

public class User {
	private static final String SER_LIST_FILE = "borrowedBooks.ser";	//tentative, IDK that file path

	private String username;
	private String password;
	public ArrayList<Book> borrowedBooks;

	public User(String username) {
		// TODO Auto-generated constructor stub
		this.username = username;
	}

	public void borrowBooks(Book title){
		//adds the borrowed book to the user's borrowed books cart
		borrowedBooks.add(title);
	}

	public void returnBooks(Book title){
		//removed the book from the user's borrowed books cart
		borrowedBooks.remove(title);
	}

	public void saveBooks(){
		//serializing arrayList containing borrowed books
		try{
			File borrowedFile = new File(SER_LIST_FILE);
			FileOutputStream fos = new FileOutputStream(borrowedFile); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(this.borrowedBooks);
			oos.close();
		}catch (Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	public void load(){
		//deserializing and adding the borrowed books in the user's cart of borrowed books
		try{
			File borrowedFile = new File(SER_LIST_FILE);
			FileInputStream fis = new FileInputStream(borrowedFile);
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.borrowedBooks = ((ArrayList<Book>)ois.readObject());
			ois.close();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}

	//GETTERS
	public ArrayList getList(){
		return this.borrowedBooks;
	}

	public String getUsername(){
		return this.username;
	}
}
