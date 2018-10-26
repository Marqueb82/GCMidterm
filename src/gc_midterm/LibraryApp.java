package gc_midterm;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

public class LibraryApp {

	BookTextFile btf = new BookTextFile();
	Book book = new Book();
	Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		

		System.out.println("Welcome to Grand Chirpus library!");
		System.out.println("What can we do for you today?");

		int userResponse;

		// will show book list
		List<Book> ourBooks = btf.showBooks();
		for (Book b : ourBooks) {
			System.out.println(b.getTitle() + " " + b.getAuthor() + " " + b.getAvailability() + " " + b.getDueDate()
					+ " " + b.getGenre());
		}

		/*
		 * Adding book testing from BookTextFile
		 */
		System.out.println("\nEnter book title");
		String title = userInput.next();
		System.out.println("\nEnter book author");
		String author = userInput.nextLine();
		userInput.nextLine();
		System.out.println("\nEnter book status");
		BookStatus available = BookStatus.valueOf(userInput.next());
		System.out.println("\nEnter book due date");
		Date dueDate = Date.valueOf(userInput.next());
		System.out.println("\nEnter book genre");
		String bookGenre = userInput.next();

		Book b = new Book(title, author, available, dueDate, bookGenre);

		btf.appendToFile(b);

		String name = "As a Man Thinketh";
		btf.removeFromFile(name);

		do {

			System.out.println(" 1.	See a list of books.");
			System.out.println(" 2. Select a genre.");
			System.out.println(" 3. Search by author.");
			System.out.println(" 4. Search by title keyword.");
			System.out.println(" 5. Select a book to checkout.");
			System.out.println(" 6. Return a book.");
			System.out.println(" 7. Exist Grand Chirpus Directory.");

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
				// TODO title keyword
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
		 */// layout we can use for bookavailability

	}

	public List<Book> keywordInBooklist() {
		List<Book> completeList = new ArrayList<>();
		List<Book> keywordIncluded = new ArrayList<>();

		boolean isValid = false;
		do {
			try {
				System.out.println("Please enter a keyword to search: ");
				String userKeyword = userInput.nextLine();

				// creates a list to cycle through
				completeList = btf.showBooks();
				// cycles through the list to add to a new list of just books with that key word
				for (Book book : completeList) {
					if (book.getAuthor().contains(userKeyword) || book.getTitle().contains(userKeyword)) {
						keywordIncluded.add(book);
					} else {
						System.out.println("Sorry there are no books containing that keyword.");
					}
				}
				isValid = true;
			} catch (IOException | ParseException e) {
				// prints error and has user try again
				e.printStackTrace();
				isValid = false;
			}
		} while (!isValid);

		return keywordIncluded;
	}

}
