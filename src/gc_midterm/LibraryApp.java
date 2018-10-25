package gc_midterm;

import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner userInput = new Scanner (System.in);
	
		
		System.out.println("Welcome to Grand Chirpus library!");
		System.out.println("What can we do for you today?");
		
		int userResponse;
		
		do {
			
			System.out.println(" 1.	See a list of books.");
			System.out.println(" 2. Select a genre.");
			System.out.println(" 3. Search by author.");
			System.out.println(" 4. Search by title keyword.");
			System.out.println(" 5. Select a book to checkout.");
			System.out.println(" 6. Return a book.");
			System.out.println(" 7. Exist Grand Chirpus Directory.");
			
			 userResponse = userInput.nextInt();
			switch(userResponse) {//the value being checked for
			case 1: 
				//TODO method for booklist
				break;
			case 2: 
				//TODO method for genre 
				break;
			case 3:
				//TODO Author 
				break;
			case 4:
				//TODO title keyword 
				break;
			case 5:
				//TODO book to checkout
				break;
			case 6:
				//TODO return a book
				break;
			case 7: 
				System.out.println("Enjoy reading!");
				System.exit(0);
				break;
				
			}
	
			
			
		}while (userResponse != 7);
		
		
		userInput.close();
	}

}
