package gc_midterm;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TestTextClass {
	private static Path filePath = Paths.get("src/gc_midterm/booklist.txt");
	private static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ParseException {
		BookTextFile btf = new BookTextFile();
		List<Book> completeList = btf.showBooks();

		for (Book b : completeList) {
			System.out.println(b.toString());
		}

		System.out.println("\ntitle");
		String title = scnr.nextLine();
		System.out.println("\nauthor");
		String author = scnr.nextLine();
		System.out.println("check/uncheck");
		BookStatus status = BookStatus.valueOf(scnr.next());
		System.out.println("date");
		LocalDate date = LocalDate.parse(scnr.next());
		System.out.println("genre");
		String genre = scnr.next();

		Book book = new Book(title, author, status, date, genre);
		btf.appendToFile(book);

	}
}
