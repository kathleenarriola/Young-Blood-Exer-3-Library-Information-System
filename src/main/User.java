/* 
 * Class Name: User
 * Class Description: Defines the User class, which borrows books from the library.
 * 
 */

import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.File;

public class User implements Serializable{								//mad it implement serializable
	private static final String SER_LIST_FILE = "borrowedBooks.ser";	//tentative, IDK that file path

	private String username;
	private String password;
	public ArrayList<Book> borrowedBooks;

	public User(String username) {
		// TODO Auto-generated constructor stub
		this.username = username;
	}

	public void borrowBooks(Book book){
		//adds the borrowed book to the user's borrowed books cart
		this.borrowedBooks.add(book);
	}

	public Book returnBooks(String title){
		//removed the book from the user's borrowed books cart
		for(int i = 0; i < this.borrowedBooks.size(); i++){
			if(borrowedBooks.get(i).getTitle().equals(title)) return this.borrowedBooks.remove(i);
		}
		return null;
	}
	
	//add save and load of User class

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
	public void loadBooks(){
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


	
	public void viewBorrowedBooks(){			//move this to User nalang


		System.out.println("===================== BORROWED BOOKS =====================");
		for (int i = 0; i < this.borrowedBooks.size(); i++){
			System.out.println(this.borrowedBooks.get(i).getID());
			System.out.println(this.borrowedBooks.get(i).getTitle());
			System.out.println(this.borrowedBooks.get(i).getAuthor());
			System.out.println(this.borrowedBooks.get(i).getYear());
		}
		System.out.println("==========================================================");
	}


	//GETTERS
	public ArrayList getList(){
		return this.borrowedBooks;
	}

	public String getUsername(){
		return this.username;
	}
}
