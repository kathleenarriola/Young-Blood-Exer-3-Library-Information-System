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
		
		System.out.println("\n\tWhat would you like to do? ");
		System.out.println("\t\t[1] - Borrow a book");
		System.out.println("\t\t[2] - Return a book");
		System.out.println("\t\t[3] - View all books");
		System.out.println("\t\t[4] - View all the books you've borrowed");
		System.out.println("\t\t[0] - Exit");
		System.out.print("\tChoice: ");
		
		try{
			menuChoice = input.nextInt();
		}catch(InputMismatchException e){
			System.out.println("\tYou've entered characters that are not numbers. Try again.");
			menuChoice = 0;
		}
		
		return menuChoice;
	}
	
	//method used for the borrowing function in the library
	public static void borrowBook(User user){
		String title = "";
		Book book;
		
		lib.viewAllBooks();
		System.out.print("\n\tEnter title of book to be borrowed: ");
		input.nextLine();
		title = input.nextLine();
		if (lib.hasTitle(title) && (book = lib.removeBook(title)) != null){
			user.borrowBooks(book);
			System.out.println("\tBorrowed \"" + title + "\"!\n");
		}
		else System.out.println("\tThe library does not have a book with that title.\n");
	}
	
	public static void returnBook(User user){
		String title = "";
		user.viewBorrowedBooks();
		Book toBeReturned;

		System.out.print("\n\tEnter title of book to be returned: ");
		input.nextLine();
		title = input.nextLine();

		toBeReturned = user.returnBooks(title);
		if(toBeReturned == null){
			System.out.println("\tA book with the title " + title + " has not been borrowed by the user.\n");
		}else{
			lib.addBook(toBeReturned);
			System.out.println("\tReturned \"" + title + "\" to the Library!\n");
		}
		
	}
	
	public static User loginMenu(){
		int choice = -1;

		do{
			System.out.println("\t\t\t[1] - Login to an existing account");
			System.out.println("\t\t\t[2] - Create a new account");
			System.out.println("\t\t\t[0] - Exit\n");

			try{
				System.out.print("\t\t\t\tEnter choice: ");
				choice = input.nextInt();
			}catch(Exception e){
				input.nextLine();
				System.out.println("\tPlease enter a valid choice");
				continue;
			}
		
		}while(choice < 0 || choice > 2);
		input.nextLine();
		switch(choice){
			case 0: System.exit(0);
			case 1: return login();
			case 2: return newUser();
		}
		return null;
	}

	//module for the login function in the program
	public static User login(){
		String username;
		char[] password;
		boolean isValid = true;
		User user;

		do{
			if(isValid)System.out.print("\n\tEnter username: ");
			else System.out.print("\tEnter existing username: ");

			username = input.nextLine();
			isValid = false;
		}while(!User.isExist(username));						//loop for checking if username exists in bin

		user = User.load(username);

		isValid = true;
		do{
			if(isValid)System.out.print("\tEnter password: ");
			else System.out.print("\tEnter valid password: ");

			password = System.console().readPassword();			//invokes the readPassword method in console
			isValid = false;
		}while(!user.isPass(password));							//loop while password entered is not equal to password entered before
		user.loadBooks();

		return user;
	}

	//module for the create a user account function in program
	public static User newUser(){
		String username, password, rePass;
		boolean isValid = true;
		
		do{
			if(isValid)System.out.print("\n\tEnter username: ");
			else System.out.print("\tEnter non-existing username: ");			//if username entered does not exist in serialized files
			username = input.nextLine();
			isValid = false;
		}while(User.isExist(username));


		do{
			System.out.print("\tEnter password: ");
			password = input.nextLine();
			System.out.print("\tConfirm password: ");
			rePass = input.nextLine();

			if(password.equals(rePass)) break;
			else{
				System.out.println("\n\nPasswords do not match!\n\n");
				continue;
			}

		}while(true);

		return new User(username, password);
	}

	public static void main(String[] args) {
		int menuChoice;
		User user = null;
		Scanner input = new Scanner(System.in);

		System.out.println("\n\n\t\t\t\tWelcome to the library!\n");
		
		user = loginMenu();
		do{
			menuChoice = menu();
			System.out.println();
			switch(menuChoice){
			case 0: break; //exit
			case 1:
				borrowBook(user);
				break;
			case 2:
				returnBook(user);
				break;
			case 3:
				lib.viewAllBooks();
				break;
			case 4:
				user.viewBorrowedBooks();
				break;
			default: System.out.println("\tInvalid input.");
				break;
			}
		} while(menuChoice != 0);
		
		user.saveBooks();				//serializes the book cart of the user. filename: (username)Books.ser
		user.save();					//serializes the user. filename: (username).ser
		lib.saveMAP();					//serializes the hashmap
		input.close();					//closes the input buffer
		System.out.println("\n\t\t\t\tThank you for using the Library!");
	}

}
