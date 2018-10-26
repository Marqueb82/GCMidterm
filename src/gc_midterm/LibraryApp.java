package gc_midterm;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

	private static BookTextFile btf = new BookTextFile();
	private static Book book = new Book();
	private static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ParseException {

		System.out.println("Welcome to Grand Chirpus library!");
		System.out.println("How can we help you today?");

		int userResponse;

//		// will show book list
//		List<Book> ourBooks = btf.showBooks();
//		for (Book b : ourBooks) {
//			System.out.println(b.getTitle() + " " + b.getAuthor() + " " + b.getAvailability() + " " + b.getDueDate()
//					+ " " + b.getGenre());
//		}

		/*
		 * Adding book testing from BookTextFile
		 */
//		System.out.println("\nEnter book title");
//		String title = userInput.next();
//		System.out.println("\nEnter book author");
//		String author = userInput.nextLine();
//		userInput.nextLine();
//		System.out.println("\nEnter book status");
//		BookStatus available = BookStatus.valueOf(userInput.next());
//		System.out.println("\nEnter book due date");
//		Date dueDate = Date.valueOf(userInput.next());
//		System.out.println("\nEnter book genre");
//		String bookGenre = userInput.next();

//		Book b = new Book(title, author, available, dueDate, bookGenre);
//
//		btf.appendToFile(b);
//
//		String name = "As a Man Thinketh";
//		btf.removeFromFile(name);

		do {

			System.out.println("");
			System.out.println(" 1. See a list of books.");
			System.out.println(" 2. Select a genre.");
			System.out.println(" 3. Search by author.");
			System.out.println(" 4. Search by keyword.");
			System.out.println(" 5. Select a book to checkout.");
			System.out.println(" 6. Return a book.");
			System.out.println(" 7. Exit Grand Chirpus Directory.");
			System.out.println("\n Please select the number that you would like explore: ");

			userResponse = userInput.nextInt();
			switch (userResponse) {// the value being checked for
			case 1:// trying a for loop out.
					// for(int i = 0; i < bookList.Size(); i++){
					// System.out.println(booklist.get(i).toString();
					// }
					// TODO method for book list
				break;
			case 2:

				// TODO method for genres: Self-Help, Fiction, Non-Fiction
				break;
			case 3:
				// TODO Author
				break;
			case 4:
				// calls method to find keywords
				keywordInBooklist();
				break;
			case 5:
				// TODO book to checkout
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

	public static void keywordInBooklist() {
		List<Book> completeList = new ArrayList<>();
		List<Book> keywordIncluded = new ArrayList<>();

		boolean isValid = false;
		do {
			try {
				System.out.println("Please enter a keyword to search: ");
				String userKeyword = userInput.nextLine();

				// creates a list to cycle through
				completeList = btf.showBooks();

				// create an option to sort through authors or books with keyword
				System.out.println("Would you like to search through authors or titles? ");
				String userResponse = userInput.nextLine();

				// cycles through the list to add to a new list of just books with that key word
				for (Book book : completeList) {
					// sorts either authors or titles
					if (userResponse.toLowerCase().equals("authors")) {
						if (book.getAuthor().contains(userKeyword)) {
							keywordIncluded.add(book);
						} else {
							System.out.println("Sorry, there are no authors containing that keyword.");
						}
					} else if (userResponse.toLowerCase().contains("title")) {
						if (book.getTitle().contains(userKeyword)) {
							keywordIncluded.add(book);
						} else {
							System.out.println("Sorry, there are no authors containing that keyword.");
						}
					}
				}
				isValid = true;
			} catch (IOException | ParseException e) {
				// prints error and has user try again
				e.printStackTrace();
				isValid = false;
			}
		} while (!isValid);

		for (Book book : keywordIncluded) {
			System.out.println(String.format("Books: %-25s Author: %-25s", book.getTitle() + book.getAuthor()));
		}
	}

}
