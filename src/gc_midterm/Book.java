package gc_midterm;

import java.time.LocalDate;

public class Book {

	private String title;
	private String author;
	private BookStatus availability;
	private LocalDate dueDate;
	private String genre;

	public Book() {
	}

	public Book(String title, String author, BookStatus availability, LocalDate dueDate, String genre) {
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

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
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
		// TM AM SM DDM GM
		return String.format("%-15s %15s %15s %15s %15s%n %-25s %25s %20s %10s %15s", "Title: ", "Author: ",
				"Book Status (Available or checked out): ", "Due Date: ", "Genre: ", getTitle(), getAuthor(),
				getAvailability(), getDueDate(), getGenre());
	}

}
