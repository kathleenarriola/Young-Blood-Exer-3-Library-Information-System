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
	//private static final String SER_LIST_FILE = "borrowedBooks.ser";	//tentative, IDK that file path

	private String username;
	private String password;
	public ArrayList<Book> borrowedBooks;

	public User(String username) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.borrowedBooks = new ArrayList<Book>();
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
		String filename = this.username + "Books.ser";
		try{
			File borrowedFile = new File(filename);
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
		String filename = this.username + "Books.ser";
		try{
			File borrowedFile = new File(filename);
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
		System.out.println();
		for (int i = 0; i < this.borrowedBooks.size(); i++){
			System.out.println("ID\t: " + this.borrowedBooks.get(i).getID());
			System.out.println("Title\t: " + this.borrowedBooks.get(i).getTitle());
			System.out.println("Author\t: " + this.borrowedBooks.get(i).getAuthor());
			System.out.println("Year\t: " + this.borrowedBooks.get(i).getYear());
			System.out.println();
		}
		System.out.println("==========================================================");
		System.out.println();
	}

	//saves the serialized User
	public void save(){
		String filename = this.username + ".ser";
		try{
			File userFile = new File(filename);
			FileOutputStream fos = new FileOutputStream(userFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(this);
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")

	//deserializing object User
	public static User load(String username){
		String filename = username + ".ser";
		User u = null;
		try{
			File userFile = new File(filename);
			FileInputStream fis = new FileInputStream(userFile);
			ObjectInputStream ois = new ObjectInputStream(fis);

			u = (User)ois.readObject();
			ois.close();
			return (u);
		}catch(ClassNotFoundException c){
			c.printStackTrace();
			System.out.println("Class not found!");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Cannot read " + filename);
		}
		return null;
	}

	public static boolean isExist(String username){
		File file = new File(username + ".ser");
		if(file.exists()) return true;
		return false;

	}

	//GETTERS
	public ArrayList getList(){
		return this.borrowedBooks;
	}

	public String getUsername(){
		return this.username;
	}
}
