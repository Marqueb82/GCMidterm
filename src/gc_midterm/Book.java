package gc_midterm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {

	private String title;
	private String author;
	private BookStatus availability;
	private Date dueDate;
	private String genre;

	public Book() {
	}

	public Book(String title, String author, BookStatus availability, Date dueDate, String genre) {
		this.title = title;
		this.author = author;
		this.availability = availability;
		this.dueDate = dueDate;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookStatus getAvailability() {
		return availability;
	}

	public void setAvailability(BookStatus availability) {
		this.availability = availability;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
		return "Book [title=" + title + ", author=" + author + ", availability=" + availability + ", dueDate="
				+ sm.format(dueDate) + ", genre=" + genre + "]";
	}

}
