package gc_midterm;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

	private static BookTextFile btf = new BookTextFile();
	private static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ParseException {

		List<Book> ourBooks = btf.showBooks();
		System.out.println("Welcome to Grand Chirpus library!");
		System.out.println("How can we help you today?");

		int userResponse;

		do {

			System.out.println("");
			System.out.println(" 1. See a list of books.");
			System.out.println(" 2. Select a genre.");
			System.out.println(" 3. Search by author.");
			System.out.println(" 4. Search by keyword.");
			System.out.println(" 5. Select a book to checkout.");
			System.out.println(" 6. Return a book.");
			System.out.println(" 7. Exit Grand Chirpus Directory.");
			System.out.println();

			System.out.print("Please select the number that you would like explore: ");
			userResponse = userInput.nextInt();
			userInput.nextLine();

			switch (userResponse) {// the value being checked for
			case 1:
				// method to list books
				System.out.println("Here is a list of our books--");
				System.out.println("==============================");
				printOutBooks(ourBooks);
				break;
			case 2:
				// TODO method for genres: Self-Help, Fiction, Non-Fiction
				// may need to check how we specify txt genre--may need one genre
				System.out.println("What would you like to search by: ");
				String userSearch = userInput.nextLine();
				System.out.println("Results--");
				searchBooks(ourBooks, userSearch);
				break;
			case 3:
				// TODO Author
				System.out.println("Enter author's name: ");
				String authorName = userInput.nextLine();
				System.out.println("Results--");
				searchByAuthor(ourBooks, authorName);
				break;
			case 4:
				// calls method to find keywords
				keywordInBooklist();
				break;
			case 5:
				// calls method to check out book
				printOutBooks(ourBooks);
				System.out.println("Which book");
				int choice = userInput.nextInt();
				checkOutBook(ourBooks, choice);
				break;
			case 6:
				// TODO return a book
				break;
			case 7:
				System.out.println("Enjoy reading!");
				System.exit(0);
				break;
			}

		} while (userResponse != 7);

		userInput.close();
	}/*
		 * if (Arrays.equals(array1, array2)) { System.out.println("EQUAL"); } else {
		 * System.out.println("NOT EQUAL");
		 */// layout we can use for book availability

	public static void printOutBooks(List<Book> completeList) throws IOException, ParseException {
		int i = 1;

		for (Book book : completeList) {
			System.out.println((i++) + ". " + book.getTitle() + " by " + book.getAuthor());
		}

	}

	public static void keywordInBooklist() {
		List<Book> completeList = new ArrayList<>();
		List<Book> keywordIncluded = new ArrayList<>();

		boolean isValid = false;
		do {
			try {
				// creates a list to cycle through
				completeList = btf.showBooks();

				userInput.nextLine();
				System.out.print("Please enter a keyword to search: ");
				String userKeyword = userInput.nextLine();

				// creates a list to cycle through
				completeList = btf.showBooks();

				// create an option to sort through authors or books with keyword
				System.out.print("Would you like to search through authors or titles? ");
				String userResponse = userInput.nextLine();

				// cycles through the list to add to a new list of just books with that key word
				for (Book book : completeList) {
					// sorts either authors or titles
					if (userResponse.toLowerCase().contains("author")) {
						if (book.getAuthor().toLowerCase().contains(userKeyword)) {
							keywordIncluded.add(book);
							System.out.println(book.getTitle() + book.getAuthor());
						}

					} else if (userResponse.toLowerCase().contains("title")) {
						if (book.getTitle().toLowerCase().contains(userKeyword)) {
							keywordIncluded.add(book);
							System.out.println(book.getTitle() + book.getAuthor());
						}
					}

					if (keywordIncluded == null) {
						System.out.println("Sorry, there are no authors containing that keyword.");
					}

				}
				// allows user to quit loop
				isValid = true;
			} catch (IOException | ParseException e) {
				// prints error and has user try again
				e.printStackTrace();
				// allows user to repeat loop in order to enter valid input
				isValid = false;
			}
		} while (!isValid);
	}

	public static void searchBooks(List<Book> ourBooks, String search) {
		List<Book> searchedBookList = new ArrayList<>();

		for (Book b : ourBooks) {
			if (b.getGenre().equals(search) || b.getAuthor().equals(search) || b.getTitle().equals(search)) {
				searchedBookList.add(b);
			}
		}

		int i = 1;
		for (Book book : searchedBookList) {
			System.out.println((i++) + ". " + book.getTitle() + " by " + book.getAuthor());
		}

	}

	public static void searchByAuthor(List<Book> ourBooks, String search) {
		List<Book> searchedBookList = new ArrayList<>();

		for (Book b : ourBooks) {
			if (b.getAuthor().equals(search)) {
				searchedBookList.add(b);
			}
		}

		int i = 1;
		for (Book book : searchedBookList) {
			System.out.println((i++) + ". " + book.getTitle() + " by " + book.getAuthor());
		}

	}

	public static void checkOutBook(List<Book> ourBooks, int choice) throws IOException, ParseException {
		ourBooks.get(choice - 1).setAvailability(BookStatus.CHECKEDOUT);
		System.out.println(ourBooks.get(choice - 1).toString());
		// method incomplete only returns changed object in text file
		btf.rewriteFile(ourBooks);
	}

//	public List<Book> searchGenre(List<Book> ourBooks) {
//		List<Book> booklist = new ArrayList<>();
//		booklist = btf.showBooks();
//
//		// TODO create a menu with genre titles
//		for (int i = 0; i < booklist.size(); i++) {

//		List of Books		
//		booklist.get(0);
//		booklist.get(1);
//		booklist.get(2);
//		booklist.get(3);
//		booklist.get(4);
//		booklist.get(5);
//		booklist.get(6);
//		booklist.get(7);
//		booklist.get(8);
//		booklist.get(9);
//		booklist.get(10);
//		booklist.get(11);

//			boolean userResponse;
//		}
//
//	}

}
