package gc_midterm;

import java.util.ArrayList;
import java.util.List;

public class Methods {

		// TODO Auto-generated method stub
//this page is to create some methods for the libraryApp, to avoid interference. 
	
	
	public static List<Book> searchBooks(List<Book> ourBooks, String genre, String author, String title) {
		List<Book> booklist = new ArrayList<>();
		
	//	booklist = btf.showBooks();
		for(Book b : ourBooks) {
			if (b.getGenre().toLowerCase().equals(genre) || b.getAuthor().equals(author) || b.getTitle().equals(title)) {
				
				booklist.add(b);
			}
		}
		
		return booklist;
	}
//		
//	}//command slash to comment out
//	//public List<Book> searchTitle (List<Book> ourBooks, String title) {
	//	List<Book> booklist = new ArrayList<>();
	//	for (Book c: ourBooks)
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

			//boolean userResponse;
			//return genre;
		


}
