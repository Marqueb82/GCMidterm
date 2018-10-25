package gc_midterm;

import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner userInput = new Scanner (System.in);
	
		
		System.out.println("Welcome to Grand Chirpus library!");
		System.out.println("What can we do for you today?");
		
		boolean valid = false;
		
		do {
			System.out.println(" 1.	See a list of books.");
			System.out.println(" 2. Select a genre.");
			System.out.println(" 3. Search by author.");
			System.out.println(" 4. Search by title keyword.");
			System.out.println(" 5. Select a book to checkout.");
			System.out.println(" 6. Return a book.");
			System.out.println(" 7. Exist Grand Chirpus Directory.");
			
			int userResponse = userInput.nextInt();
			
			
			
		}while (!valid);
		
		
		userInput.close();
	}

}
