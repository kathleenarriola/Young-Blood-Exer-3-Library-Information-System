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
		
	}
	
	public static void returnBook(){
		
	}
	
	public static void viewAllBooks(){
		
	}
	
	public static void viewBorrowedBooks(){
		
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
