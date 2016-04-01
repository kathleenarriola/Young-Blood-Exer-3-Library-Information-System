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
		System.out.println(" [0] - Exit");
		System.out.print("Choice: ");
		
		try{
			//System.out.println("sdasda");
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
		input.nextLine();
		title = input.nextLine();
		if (lib.hasTitle(title) && (book = lib.removeBook(title)) != null){
			user.borrowBooks(book);
			System.out.println("Borrowed \"" + title + "\"!\n");
		}
		else System.out.println("The library does not have a book with that title.\n");
	}
	
	//pakiayos po thx
	public static void returnBook(User user){
		String title = "";
		user.viewBorrowedBooks();
		Book toBeReturned;

		System.out.print("Enter title of book to be returned: ");
		input.nextLine();
		title = input.nextLine();

		toBeReturned = user.returnBooks(title);
		if(toBeReturned == null){
			System.out.println("A book with the title " + title + " has not been borrowed by the user.\n");
		}else{
			lib.addBook(toBeReturned);
			System.out.println("Returned \"" + title + "\" to the Library!\n");
		}
		
	}
	
	public static User loginMenu(){
		int choice = -1;

		do{
			System.out.println(" [1] - Login to an existing account");
			System.out.println(" [2] - Create a new account");
			System.out.println(" [0] - Exit\n");

			try{
				System.out.print("Enter choice: ");
				choice = input.nextInt();
			}catch(Exception e){
				input.nextLine();
				System.out.println("Please enter a valid choice");
				continue;
			}
		
		}while(choice < 0 || choice > 2);

		switch(choice){
			case 0: System.exit(0);
			case 1: return login();
			case 2: return newUser();
		}
		return null;
	}

	public static User login(){
		String username, password;
		boolean isValid = true;
		User user;

		do{
			if(isValid)System.out.print("Enter username: ");
			else System.out.print("Enter existing username: ");

			input.nextLine();
			username = input.nextLine();
			isValid = false;
		}while(!User.isExist(username));

		user = User.load(username);

		isValid = true;
		do{
			if(isValid)System.out.print("Enter password: ");
			else System.out.print("Enter valid password: ");

			password = input.nextLine();
			isValid = false;
		}while(!user.isPass(password));
		user.loadBooks();

		return user;
	}

	public static User newUser(){
		String username, password, rePass;
		boolean isValid = true;
		User user;
		
		do{
			if(isValid)System.out.print("Enter username: ");
			else System.out.print("Enter non-existing username: ");

			input.nextLine();
			username = input.nextLine();
			isValid = false;
		}while(User.isExist(username));


		do{
			System.out.print("Enter password: ");
			password = input.nextLine();
			System.out.print("Confirm password: ");
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
		String username = "";
		Scanner input = new Scanner(System.in);

		System.out.println("\n\nWelcome to the library!\n");
		//login and friends
		/*
		System.out.print("Enter username: ");
		username = input.nextLine();
		while(true){
			if (User.isExist(username)){
				user = User.load(username);
				System.out.println("Enter password:");
				user.loadBooks();
			}
			else {
				System.out.println("Glad to have you back, " + username + " !\n");
				user = new User(username, "password");

				break;
			}
		}
		*/
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
			default: System.out.println("Invalid input.");
				break;
			}
		} while(menuChoice != 0);
		
		user.saveBooks();
		user.save();
		lib.saveMAP();
		input.close();
		System.out.println("Thank you for using the Library!");
	}

}
