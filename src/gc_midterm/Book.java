package gc_midterm;

import java.util.Date;

public class Book {

	String title;
	String author;
	BookStatus availability;
	Date dueDate;
	String genre;

	public Book() {
	}

	public Book(String title, String author, BookStatus checkedOut, Date dueDate, String genre) {
		this.title = title;
		this.author = author;
		this.availability = checkedOut;
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

	public boolean isCheckedOut() {
		return availability;
	}

	public void setCheckedOut(boolean checkedOut) {
		this.availability = checkedOut;
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

	// TODO toString

}
