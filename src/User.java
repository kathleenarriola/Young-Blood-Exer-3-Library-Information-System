/* 
 * Class Name: User
 * Class Description: Defines the User class, which borrows books from the library.
 * 
 */

import java.util.ArrayList;
import java.security.MessageDigest;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.io.File;

@SuppressWarnings("serial")
public class User implements Serializable{

	private String username;
	private byte[] password;
	
	
	public ArrayList<Book> borrowedBooks;

	public User(String username, String password) {
		this.username = username;
		this.password = encrypt(password);
		this.borrowedBooks = new ArrayList<Book>();
	
	}
	
	//encrypts the inputted password by the User
	public byte[] encrypt(String password){
		byte[] passByte;
		
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			passByte = password.getBytes("UTF-8");
			return md.digest(passByte);		
		} catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}

		return null;
	}

	//checks if inputted password is equal to the encrypted password on file
	public boolean isPass(char[] password){
		byte[] passByte;
		String passwordString;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			passwordString = String.valueOf(password);
			passByte = md.digest(passwordString.getBytes(StandardCharsets.UTF_8));
			
			return MessageDigest.isEqual(this.password, passByte);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
	
	//adds the borrowed book to the user's borrowed books cart
	public void borrowBooks(Book book){
		
		this.borrowedBooks.add(book);
	}

	//removes the book from the user's borrowed books cart
	public Book returnBooks(String title){
		for(int i = 0; i < this.borrowedBooks.size(); i++){
			if(borrowedBooks.get(i).getTitle().equals(title)) return this.borrowedBooks.remove(i);
		}
		return null;
	}
	
	//serializing arrayList containing borrowed books
	public void saveBooks(){
		String filename = "../bin/" + this.username + "Books.ser";
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
	//deserializing and adding the borrowed books in the user's cart of borrowed books
	public void loadBooks(){
		String filename = "../bin/" + this.username + "Books.ser";
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

	//views all the borrowed books of user
	public void viewBorrowedBooks(){
		System.out.println("\n\t\t===================== BORROWED BOOKS =====================");
		System.out.println();
		for (int i = 0; i < this.borrowedBooks.size(); i++){
			System.out.println("\t\t\tID\t: " + this.borrowedBooks.get(i).getID());
			System.out.println("\t\t\tTitle\t: " + this.borrowedBooks.get(i).getTitle());
			System.out.println("\t\t\tAuthor\t: " + this.borrowedBooks.get(i).getAuthor());
			System.out.println("\t\t\tYear\t: " + this.borrowedBooks.get(i).getYear());
			System.out.println();
		}
		System.out.println("\n\t\t==========================================================");
		System.out.println();
	}

	//saves the serialized User
	public void save(){
		String filename = "../bin/" + this.username + ".ser";
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

	//deserializing object User
	public static User load(String username){
		String filename = "../bin/" + username + ".ser";
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
			System.out.println("\tClass not found!");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("\tCannot read " + filename);
		}
		return null;
	}

	//checks if serialized User file exists
	public static boolean isExist(String username){
		File file = new File("../bin/" + username + ".ser");
		if(file.exists()) return true;
		return false;

	}

	//GETTERS
	public ArrayList<Book> getList(){
		return this.borrowedBooks;
	}

	public String getUsername(){
		return this.username;
	}
}
