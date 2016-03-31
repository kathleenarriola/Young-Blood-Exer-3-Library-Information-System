/* 
 * Class Name: LibraryInformationSystem
 * Class Description: Included here is the main method.
 * 
 */

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

	//public static int userLogin(); make a user login function
	
	public static void borrowBook(User user){//fix
		String title = "";
		Book book;
		
		lib.viewAllBooks();
		System.out.print("Enter title of book to be borrowed: ");

		title = input.nextLine();
		if (lib.hasTitle(title) && (book = lib.removeBook(title)) != null){
			user.borrowBooks(book);
		}
	}
	
	//pakiayos po thx
	public static void returnBook(User user){
		String title = "";
		user.viewBorrowedBooks();
		Book toBeReturned;

		System.out.println("Enter title of book to be returned: ");
		title = input.nextLine();

		toBeReturned = user.returnBooks(title);
		if(toBeReturned == null){
			System.out.println("A book with the title " + title + " has not been borrowed by the user.");
		}else{
			lib.addBook(toBeReturned);
		}
		
	}
	

	
	public static void main(String[] args) {
		int menuChoice;
		User user = null;

		System.out.println("Welcome to the library!");
		do{
			menuChoice = menu();							//make user login first please
			if(menuChoice < 1 || menuChoice > 4){
				System.out.println("Wrong inputs.");
			}
		}while(menuChoice < 0 || menuChoice > 4);
		
		switch(menuChoice){
		case 1:
			borrowBook(user);
			break;
		case 2:
			//returnBook();
			break;
		case 3:
			//viewAllBooks();
			break;
		case 4:
			user.viewBorrowedBooks();
			break;
		}
		
	}

}
