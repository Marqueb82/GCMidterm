package gc_midterm;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LibraryApp {

	private static BookTextFile btf = new BookTextFile();
	private static Scanner userInput = new Scanner(System.in);
	private static final LocalDate PRINCE = LocalDate.of(1999, Month.JANUARY, 1);

	public static void main(String[] args) throws IOException, ParseException {

		List<Book> completeList = btf.showBooks();
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
			System.out.println();
			userInput.nextLine();

			switch (userResponse) {// the value being checked for
			case 1:
				// method to list books
				System.out.println("Here is a list of our books--");
				System.out.println("==============================");
				printOutBooks(completeList);
				break;
			case 2:
				// TODO method for genres: Self-Help, Fiction, Non-Fiction
				// may need to check how we specify txt genre--may need one genre
				// TODO was thinking that we could use the split method using ", " to make a set
				// that gets rid of duplicates of the genres? -MH
				System.out.println("What genre would you like to search by: ");
				genreList(completeList);
				// String userSearch = userInput.nextLine();
				// System.out.println("Results--");
				// searchBooks(completeList, userSearch);
				break;
			case 3:
				// calls method to search books by author
				System.out.print("Enter author's name: ");
				String authorName = userInput.nextLine();
				System.out.println("Results--");
				searchByAuthor(completeList, authorName);
				break;
			case 4:
				// calls method to find keywords
				keywordInBooklist(completeList);
				break;
			case 5:
				// TODO possibly just make a list of books that are currently onshelf??
				// calls method to check out book
				printOutBooks(completeList);
				System.out.print("\nWhich book would you like to check out? ");
				int choice = userInput.nextInt();
				completeList = checkOutBook(completeList, choice);
				break;
			case 6:
				// TODO possibly just make a list of books that are currently check out??
				// allows user to return a book
				printOutBooks(completeList);
				System.out.print("\nWhich book would you like to return? ");
				choice = userInput.nextInt();
				completeList = returnBook(completeList, choice);
				break;
			case 7:
				System.out.println("Enjoy reading! We hope to see you soon.");
				btf.rewriteFile(completeList);
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

	public static void genreList(List<Book> completeList) {
		// TODO create a validator in user input that user may only enter 5 genres if
		// adding a book
		String[] genresIncluded = new String[5];
		String singleGenre = null;
		Set<String> listOfGenres = new HashSet<>(); // hashset to get rid of duplicate genres (and to account for user
													// entry genre that may not be included in original list)
		int i = 1; // variable for number options on genre menu

		// creates a string array for all possible genres (up to 5) for each instance of
		// book
		for (Book book : completeList) {
			genresIncluded = book.getGenre().split(","); // allows for 5 possible genres per book

			System.out.println("Book: " + book);
			System.out.println("Single Genre: " + singleGenre);
			System.out.println("Genres Included: " + genresIncluded.toString());
		}

		// sorts through the string array to add possible genres (unless they're null)
		// to a hashset to account for duplicates
		for (String genre : genresIncluded) {
//			System.out.println("genre before if: " + genre);
			if (genre != null) {
				listOfGenres.add(genre);
//				System.out.println("inside genre loop" + listOfGenres);
			}
		}
//		System.out.println("Out of genre loop" + listOfGenres);

		// prints out list itself
		for (int j = 0; j < listOfGenres.size(); j++) {
			System.out.println(i + ". " + listOfGenres.toString() + "\n");
		}
	}

	public static void keywordInBooklist(List<Book> completeList) {
		List<Book> keywordIncluded = new ArrayList<>();

		boolean isValid = false;
		do {
			try {
				System.out.print("Please enter a keyword to search: ");
				String userKeyword = userInput.nextLine();

				// creates a list to cycle through
				completeList = btf.showBooks();

				// create an option to sort through authors or books with keyword
				System.out.print("Would you like to search through authors or titles? ");
				String userResponse = userInput.nextLine();
				userInput.nextLine();
				// cycles through the list to add to a new list of just books with that key word
				for (Book book : completeList) {
					// sorts either authors or titles
					if (userResponse.toLowerCase().contains("author")) {
						if (book.getAuthor().toLowerCase().contains(userKeyword)) {
							keywordIncluded.add(book);
							System.out.println(book.getTitle() + " " + book.getAuthor());
						}
					} else if (userResponse.toLowerCase().contains("title")) {
						if (book.getTitle().toLowerCase().contains(userKeyword)) {
							keywordIncluded.add(book);
							System.out.println(book.getTitle() + " " + book.getAuthor());
						}
					}
					// if no books were added to the list, it lets the user know
					if (keywordIncluded == null) {
						System.out.println("Sorry, we don't have any inventory containing that keyword.");
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

	public static void searchByAuthor(List<Book> completeList, String authorName) {

		completeList.stream().filter(b -> b.getAuthor().equals(authorName))
				.forEach(b -> System.out.println(b.toString()));

	}

	public static List<Book> checkOutBook(List<Book> completeList, int choice) throws IOException, ParseException {
		// sorts through list of all books to find the book requested by user and sets a
		// due date if book is on shelf, if book is already checked out, it lets the
		// user know
		Book ourBook = completeList.get(choice - 1);

		if (ourBook.getAvailability().equals(BookStatus.ONSHELF)) {
			ourBook.setAvailability(BookStatus.CHECKEDOUT);
			LocalDate today = LocalDate.now();
			LocalDate dueDate = today.plus(14, ChronoUnit.DAYS);
			ourBook.setDueDate(dueDate);
			System.out.println("\nYour checking out " + completeList.get(choice - 1).toString());
		} else if (completeList.get(choice - 1).getAvailability().equals(BookStatus.CHECKEDOUT)) {
			System.out.println("Sorry, that book has already been checked out. It is due back to the library "
					+ ourBook.getDueDate() + ".");
		}

		return completeList;
	}

	public static List<Book> returnBook(List<Book> completeList, int choice) throws IOException, ParseException {
		// checks book at index and sets to onshelf if it was checked out
		Book ourBook = completeList.get(choice - 1);

		if (ourBook.getAvailability().equals(BookStatus.CHECKEDOUT)) {
			ourBook.setAvailability(BookStatus.ONSHELF);
			ourBook.setDueDate(PRINCE);
			System.out.println("Thank you for your return.");
		} else if (completeList.get(choice - 1).getAvailability().equals(BookStatus.ONSHELF)) {
			System.out.println("That book is already available!");
		}
		return completeList;
	}

	public static void updatedBooks(List<Book> completeList) throws IOException {
		List<Book> updatedBooks = new ArrayList<>();

		for (Book b : completeList) {
			updatedBooks.add(b);
		}

		btf.rewriteFile(updatedBooks);
	}
}
