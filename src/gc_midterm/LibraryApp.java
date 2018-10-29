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
import java.util.stream.Collectors;
//author @ Marque Burgess, Mariah Hall, and Twani Dhar

//Description: Welcome to the Grand Chirpus Library! This libraryApp will allow the user to 
//checkout books, donate or add books to the library, and allow the user to return books.
//This application will also allow users to search for an author or title of a book. Have fun reading! 

public class LibraryApp {

	private static BookTextFile btf = new BookTextFile();
	private static Scanner userInput = new Scanner(System.in);
	private static final LocalDate PRINCE = LocalDate.of(1999, Month.JANUARY, 1);

	public static void main(String[] args) throws IOException, ParseException {

		List<Book> completeList = btf.showBooks();
		int userResponse;

		System.out.println("Welcome to Grand Chirpus library!");
		System.out.println();

		do {
			System.out.println("How can we help you today?");
			System.out.println();
			System.out.println(" 1. See a list of books.");
			System.out.println(" 2. Select a genre.");
			System.out.println(" 3. Search by author.");
			System.out.println(" 4. Search by keyword.");
			System.out.println(" 5. Select a book to checkout.");
			System.out.println(" 6. Return a book.");
			System.out.println(" 7. Add/donate a book.");
			System.out.println(" 8. Exit Grand Chirpus Directory.");
			System.out.println();

			System.out.print("Please select the number that you would like explore: ");
			userResponse = Validator.getInt(userInput);
			System.out.println();

			switch (userResponse) {// the value being checked for
			case 1:
				// method to list books
				printOutBooks(completeList);
				System.out.println();
				break;
			case 2:
				// method for searching books by genre
				genreList(completeList);
				break;
			case 3:
				// calls method to search books by author
				searchByAuthor(completeList, userInput);
				break;
			case 4:
				// calls method to find keywords
				keywordInBooklist(completeList);
				break;
			case 5:
				// calls method to check out book
				completeList = checkOutBook(completeList, userInput);
				break;
			case 6:
				// allows user to return a book
				completeList = returnBook(completeList, userInput);
				break;
			case 7:
				// allows user to add a book to the list/donate a book.
				addBooks(completeList);
				break;
			case 8:
				System.out.println("Enjoy reading! We hope to see you soon.");
				btf.rewriteFile(completeList);
				System.exit(0);
				break;
			}
		} while (userResponse != 8);

		userInput.close();
	}

	public static void printOutBooks(List<Book> completeList) throws IOException, ParseException {

		System.out.println("Grand Chirpus Inventory: ");
		System.out.println("==================================================");
		System.out.println(String.format("    %-25s %-20s", "Title", "Author"));

		int i = 1;
		for (Book book : completeList) {
			System.out.println(String.format("%2d. %-25s %-20s", (i++), book.getTitle(), book.getAuthor()));
		}
	}

	public static void genreList(List<Book> completeList) {
		// adding a book
		String[] genresIncluded = new String[5];
		String singleGenre = null;
		Set<String> setOfGenres = new HashSet<>(); // hashset to get rid of duplicate genres (and to account for user
													// entry genre that may not be included in original list)
		int i = 1; // variable for number options on genre menu

		// creates a string array for all possible genres (up to 5) for each instance of
		// book
		for (Book book : completeList) {
			singleGenre = book.getGenre();
			genresIncluded = singleGenre.split(", "); // validator allows for 5 possible genres per book

			// sorts through the string array to add possible genres (unless they're null)
			// to a hashset to account for duplicates
			for (String genre : genresIncluded) {
				if (genre != null) {
					setOfGenres.add(genre);
				}
			}
		}

		// prints out list itself
		for (String genre : setOfGenres) {
			System.out.println(String.format("%2d. %-20s", i, genre));
			i++;
		}

		List<String> listOfGenres = setOfGenres.stream().collect(Collectors.toList());
		System.out.println();
		System.out.print("Enter a whole number associated with the genre: ");
		int userSearch = Validator.getGenre(userInput);
		String genre = listOfGenres.get(userSearch - 1);
		System.out.print("Books in " + genre + ": \n");
		searchBooks(completeList, genre);

		// calls method again if user answers starting with y
		System.out.print("Search by another genre? (Y for yes, any other key returns to main menu) ");
		String userContinue = userInput.next();
		userInput.nextLine();

		if (userContinue.toLowerCase().startsWith("y")) {
			genreList(completeList);
		}

	}

	public static void keywordInBooklist(List<Book> completeList) {
		List<Book> keywordIncluded = new ArrayList<>();

		boolean isValid = false;
		do {
			try {
				System.out.print("Please enter a keyword to search: ");
				String userKeyword = Validator.getStringTitleRegex(userInput);

				// creates a list to cycle through
				completeList = btf.showBooks();

				// create an option to sort through authors or books with keyword
				System.out.print("Would you like to search through author or title? ");
				String userResponse = Validator.getStringNameRegex(userInput);
				// cycles through the list to add to a new list of just books with that key word
				for (Book book : completeList) {
					// sorts either authors or titles
					if (userResponse.toLowerCase().contains("author")) {
						if (book.getAuthor().toLowerCase().contains(userKeyword.toLowerCase())) {
							keywordIncluded.add(book);
							System.out.println("\n" + book.getTitle() + " by " + book.getAuthor() + "\n");
						}
					} else if (userResponse.toLowerCase().contains("title")) {
						if (book.getTitle().toLowerCase().contains(userKeyword.toLowerCase())) {
							keywordIncluded.add(book);
							System.out.println("\n" + book.getTitle() + " by " + book.getAuthor() + "\n");
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

		// calls method again if user answers starting with y
		System.out.print("Perform another search? (Y for yes, any other key returns to main menu) ");
		String userContinue = userInput.next();
		userInput.nextLine();

		if (userContinue.toLowerCase().startsWith("y")) {
			keywordInBooklist(completeList);
		}

	}

	public static void searchBooks(List<Book> completeList, String search) {
		List<Book> searchedBookList = new ArrayList<>();

		for (Book sortBook : completeList) {
			if (sortBook.getGenre().contains(search) || sortBook.getAuthor().contains(search)
					|| sortBook.getTitle().contains(search)) {
				searchedBookList.add(sortBook);
			}
		}

		System.out.println(String.format("    %-25s %-20s", "Title", "Author"));
		int i = 1;
		for (Book book : searchedBookList) {
			System.out.println(String.format("%2d. %-25s %-20s", (i++), book.getTitle(), book.getAuthor()));
		}
		System.out.println();

	}

	public static void searchByAuthor(List<Book> completeList, Scanner userInput) throws IOException, ParseException {

		for (Book b : completeList) {
			System.out.println(b.getAuthor());
		}

		System.out.print("\nEnter author's name: ");
		String authorName = Validator.getStringNameRegex(userInput);

		System.out.println("Books by " + authorName + ": \n");
		completeList.stream().filter(b -> b.getAuthor().toLowerCase().contains(authorName.toLowerCase()))
				.forEach(b -> System.out.println(b.toString()));

		// calls method again if user answers starting with y
		System.out.print("Search by another author? (Y for yes, any other key returns to main menu) ");
		String userContinue = userInput.next();
		userInput.nextLine();

		if (userContinue.toLowerCase().startsWith("y")) {
			searchByAuthor(completeList, userInput);
		}
	}

	public static List<Book> checkOutBook(List<Book> completeList, Scanner userInput)
			throws IOException, ParseException {
		// sorts through list of all books to find the book requested by user and sets a
		// due date if book is on shelf, if book is already checked out, it lets the
		// user know
		printOutBooks(completeList);

		System.out.print("\nWhich book would you like to check out? (Please select a number) ");
		int choice = Validator.getChoice(userInput);
		while (choice < 1 || choice > completeList.size()) {
			System.out.print("Enter valid entry from book list: ");
			choice = Validator.getChoice(userInput);
		}

		Book ourBook = completeList.get(choice - 1);

		if (ourBook.getAvailability().equals(BookStatus.ONSHELF)) {
			ourBook.setAvailability(BookStatus.CHECKEDOUT);
			LocalDate today = LocalDate.now();
			LocalDate dueDate = today.plus(14, ChronoUnit.DAYS);
			ourBook.setDueDate(dueDate);
			System.out.println("\nYou have checked out: \n" + completeList.get(choice - 1).toString());
		} else if (completeList.get(choice - 1).getAvailability().equals(BookStatus.CHECKEDOUT)) {
			System.out.println("Sorry, that book has already been checked out. It is due back to the library "
					+ ourBook.getDueDate() + ".");
		}
		// calls method again if user answers starting with y

		System.out.println("");
		System.out
				.println("Would you like to check out another book? (Y for yes, any other key returns to main menu) ");

		String userContinue = userInput.next();
		userInput.nextLine();

		if (userContinue.toLowerCase().startsWith("y")) {
			checkOutBook(completeList, userInput);
		}

		return completeList;
	}

	public static List<Book> returnBook(List<Book> completeList, Scanner userInput) throws IOException, ParseException {
		// checks book at index and sets to onshelf if it was checked out
		printOutBooks(completeList);

		System.out.print("Which book would you like to return? ");
		int choice = Validator.getChoice(userInput);
		while (choice < 1 || choice > completeList.size()) {
			System.out.print("Enter valid entry from book list: ");
			choice = Validator.getChoice(userInput);
		}

		Book ourBook = completeList.get(choice - 1);

		if (ourBook.getAvailability().equals(BookStatus.CHECKEDOUT)) {
			ourBook.setAvailability(BookStatus.ONSHELF);
			ourBook.setDueDate(PRINCE);
			System.out.println("Thank you for your return.");
		} else if (completeList.get(choice - 1).getAvailability().equals(BookStatus.ONSHELF)) {
			System.out.println("That book is already available!");
		}

		// calls method again if user answers starting with y
		System.out.print("Would you like to return another book? (Y for yes, any other key returns to main menu) ");
		String userContinue = userInput.next();
		userInput.nextLine();

		if (userContinue.toLowerCase().startsWith("y")) {
			returnBook(completeList, userInput);
		}

		return completeList;
	}

	// method to update book i/o file when user quits the program
	public static void updatedBooks(List<Book> completeList) throws IOException {
		List<Book> updatedBooks = new ArrayList<>();

		for (Book b : completeList) {
			updatedBooks.add(b);
		}

		btf.rewriteFile(updatedBooks);
	}

	public static void addBooks(List<Book> completeList) {
		String[] titleArray = new String[20];
		List<String> titleComponentList = new ArrayList<>();
		String[] authorArray = new String[5];
		List<String> authorComponentList = new ArrayList<>();
		String[] genreArray = new String[3];
		List<String> genreComponentList = new ArrayList<>();

		System.out.print("Please enter the book's title: ");
		String title = Validator.getStringTitleRegex(userInput);

		titleArray = title.split(" ");

		// assigns proper capitalization
		for (String titlePart : titleArray) {
			if (!titlePart.equals(" a ") && !titlePart.equals(" the ") && !titlePart.equals(" for ")
					&& !titlePart.equals(" an ") && !titlePart.equals(" and ") && !titlePart.equals(" of ")
					&& !titlePart.equals(" in ") && !titlePart.equals(" but ") && !titlePart.equals(" by ")) {
				titlePart = titlePart.substring(0, 1).toUpperCase() + titlePart.substring(1).toLowerCase();
				titleComponentList.add(titlePart);
			} else {
				// set to lower case
				titlePart = titlePart.toLowerCase();
				titleComponentList.add(titlePart);
			}
		}

		String titleCaps = "";

		// takes parts of the title that have been properly capitalized and puts them
		// into the list properly
		for (String titlePart : titleComponentList) {
			titleCaps += titlePart + " ";
		}

		System.out.print("Please enter the Author's full name: ");
		String author = Validator.getStringNameRegex(userInput);

		authorArray = author.split(" ");

		// assigns proper capitalization
		for (String authorPart : authorArray) {
			authorPart = authorPart.substring(0, 1).toUpperCase() + authorPart.substring(1).toLowerCase();
			authorComponentList.add(authorPart);
		}

		String authorCaps = "";
		// takes parts of the title that have been properly capitalized and puts them
		// into the list properly
		for (String authorPart : authorComponentList) {
			authorCaps += authorPart + " ";
		}

		System.out.print("Please enter the book's genre(s)? Please put a \", \" in-between genres. ");
		String genre = Validator.getStringGenreRegex(userInput);

		genreArray = genre.split(" ");

		// assigns proper capitalization
		for (String genrePart : genreArray) {
			genrePart = genrePart.substring(0, 1).toUpperCase() + genrePart.substring(1).toLowerCase();
			genreComponentList.add(genrePart);
		}

		String genreCaps = "";
		// takes parts of the title that have been properly capitalized and puts them
		// into the list properly
		for (String genrePart : genreComponentList) {
			genreCaps += genrePart + " ";
		}

		BookStatus availability = BookStatus.ONSHELF;
		LocalDate dueDate = PRINCE;

		completeList.add(new Book(titleCaps.trim(), authorCaps.trim(), availability, dueDate, genreCaps.trim()));

		// calls method again if user answers starting with y
		System.out.print(
				"Would you like to add another book to donate? (Y for yes, any other key returns to main menu) ");
		String userContinue = userInput.next();
		userInput.nextLine();

		if (userContinue.toLowerCase().startsWith("y")) {
			addBooks(completeList);
		}

	}
}
