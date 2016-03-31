/* 
 * Class Name: LibraryInformationSystem
 * Class Description: Included here is the main method.
 * 
 */

package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryInformationSystem {
	
	private static Scanner input = new Scanner(System.in);
	private static Library lib = new Library();

	public static int menu(){
		int menuChoice = 0;
		
		System.out.println("What would you like to do? ");
		System.out.println(" [1] - Borrow a book");
		System.out.println(" [2] - Return a book");
		System.out.println(" [3] - View all books");
		System.out.println(" [4] - View all the books you've borrowed");
		
		try{
			System.out.println("sdasda");
			menuChoice = input.nextInt();
		}catch(InputMismatchException e){
			System.out.println("You've entered characters that are not numbers. Try again.");
			menuChoice = 0;
		}
		
		return menuChoice;
	}
	
	public static void borrowBook(){
		String title = "";
		user.borrowBooks(Book);
		viewAllBooks();
		System.out.print("Enter title of book to be borrowed: ");
		title = input.nextLine();
		if (books.containsKey(title)){
			user.borrowBooks(books.get(title).remove(0));
		}
	}
	
	//pakiayos po thx
	/*public static void returnBook(){
		String title = "";
		ArrayList<Book> = bb;
		user.viewBorrowedBooks();
		System.out.println("Enter title of book to be returned: ");
		title = input.nextLine();

		bb = user.getList();
		for(int i = 0; i < bb.size(); i++){
			if (bb.get(i).getTitle().equals(title)) {
				books.put(title, );
			}
		}
	}*/
	

	public static void viewAllBooks(){
		for(String key : lib.keySet()){
			books.get(title).get(0);
		}
	}
	
	public static void viewBorrowedBooks(){
		System.out.println("===================== BORROWED BOOKS =====================");
		for (int i = 0; i < user.borrowedBooks.size(); i++){
			System.out.println(user.borrowedBooks.get(i).getID());
			System.out.println(user.borrowedBooks.get(i).getTitle());
			System.out.println(user.borrowedBooks.get(i).getAuthor());
			System.out.println(user.borrowedBooks.get(i).getYear());
		}
		System.out.println("==========================================================");
	}
	
	public static void main(String[] args) {
		int menuChoice;
		
		System.out.println("Welcome to the library!");
		do{
			menuChoice = menu();
			if(menuChoice < 1 || menuChoice > 4){
				System.out.println("Wrong inputs.");
			}
		}while(menuChoice < 0 || menuChoice > 4);
		
		switch(menuChoice){
		case 1:
			borrowBook();
			break;
		case 2:
			returnBook();
			break;
		case 3:
			viewAllBooks();
			break;
		case 4:
			viewBorrowedBooks();
			break;
		}
		
	}

}
