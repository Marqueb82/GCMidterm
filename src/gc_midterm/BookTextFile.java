package gc_midterm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookTextFile {

	private static Path filePath = Paths.get("src/gc_midterm/booklist.txt");
	Book book;

	BookTextFile() {
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<Book> showBooks() throws IOException, ParseException {
		try {
			List<String> lines = Files.readAllLines(filePath);
			List<Book> books = new ArrayList<>();
			for (String line : lines) {
				String[] parts = line.split("///");
				Book b = new Book();
				b.setTitle(parts[0]);
				b.setAuthor(parts[1]);
				b.setAvailability(BookStatus.valueOf(parts[2]));
				b.setDueDate(LocalDate.parse(parts[3]));
				b.setGenre(parts[4]);
				books.add(b);
			}
			return books;
		} catch (NoSuchFileException ex) {
			return new ArrayList<>();
		} catch (IOException ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}

	}

	/**
	 * 
	 * @param item
	 * @param filePath
	 * @throws IOException
	 */
	public void appendToFile(Book item) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}

		// Create a list with the user's book in it
		String line = item.getTitle() + "///" + item.getAuthor() + "///" + item.getAvailability() + "///"
				+ item.getDueDate() + "///" + item.getGenre();
		List<String> linesToAdd = Arrays.asList(line);
		Files.write(filePath, linesToAdd, StandardOpenOption.APPEND);
	}

	/**
	 * 
	 * @param item
	 * @throws IOException
	 */
	public void removeFromFile(String item) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}

		// lambda--will read then filter any lines that have a matching string value
		// and return new list
		List<String> leftovers = Files.lines(filePath).filter(line -> !line.contains(item))
				.collect(Collectors.toList());
		Files.write(filePath, leftovers, StandardOpenOption.TRUNCATE_EXISTING);
	}

	/**
	 * 
	 * @param items
	 * @throws IOException
	 */
	public void rewriteFile(List<Book> completeList) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}

		// ** Example of rewriting a whole file
		List<String> newLines = new ArrayList<>();

		for (Book item : completeList) {
			String line = item.getTitle() + "///" + item.getAuthor() + "///" + item.getAvailability() + "///"
					+ item.getDueDate() + "///" + item.getGenre();
			newLines = Arrays.asList(line);
		}

		newLines = Arrays.asList(new String[] { completeList.toString() });
		Files.write(filePath, newLines, StandardOpenOption.TRUNCATE_EXISTING);
	}

}
