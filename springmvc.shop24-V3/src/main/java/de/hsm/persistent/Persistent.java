package de.hsm.persistent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.hsm.logic.Author;
import de.hsm.logic.Book;

/**
 *
 * @author neuhardt
 */
public class Persistent {

	private static Persistent instance;
	private static int id = 1;
	private List<Author> authors;
	private List<Book> books;

	private Persistent() {
		authors = new ArrayList<>();
		Author author = new Author("Bill", "Burke");
		author.setId(id);
		id++;
		authors.add(author);
		Author author2 = new Author("Leonard", "Richardson");
		author2.setId(id);
		id++;
		authors.add(author2);
		Author author3 = new Author("Mike", "Amundsen");
		author3.setId(id);
		id++;
		authors.add(author3);
		Author author4 = new Author("Sam", "Ruby");
		author4.setId(id);
		id++;
		authors.add(author4);
		books = new ArrayList<>();
		Book book = new Book("RESTful Java with JAX-RS 2.0", "978-1449361341", "O'Reilly and Associates", 390,
				LocalDate.of(2013, 11, 22), "English", new BigDecimal("26.82"));
		book.addAuthor(author);
		books.add(book);
		Book book2 = new Book("RESTful Web APIs", "978-1449358068", "O'Reilly and Associates", 404,
				LocalDate.of(2013, 9, 27), "English", new BigDecimal("33.96"));
		book2.addAuthor(author2);
		book2.addAuthor(author3);
		books.add(book2);
		Book book3 = new Book("RESTful Web Services", "978-0596529260", "O'Reilly and Associates", 440,
				LocalDate.of(207, 5, 22), "English", new BigDecimal("11.16"));
		book3.addAuthor(author2);
		book3.addAuthor(author4);
		books.add(book3);
	}

	public static Persistent getInstance() {
		if (instance == null) {
			instance = new Persistent();
		}
		return instance;
	}

	public void persist(Author author) {
		author.setId(id);
		id++;
		authors.add(author);
	}

	public Author findAuthor(int id) {
		List<Author> authors1 = authors.stream().filter(author1 -> author1.getId() == id).collect(Collectors.toList());
		if (!authors1.isEmpty()) {
			return authors1.get(0);
		}
		throw new AuthorNotFoundException();
	}

	public List<Author> getAllAuthors() {
		return authors;
	}

	public Book findBook(String isbn13) {
		List<Book> filteredBooks = books.stream().filter(book1 -> book1.getIsbn13().equals(isbn13))
				.collect(Collectors.toList());
		if (filteredBooks.isEmpty()) {
			throw new BookNotFoundException();
		}
		return filteredBooks.get(0);
	}

	public void persist(Book book) {
		try {
			Book book1 = findBook(book.getIsbn13());
			throw new BookAlreadyExistsException();
		} catch (BookNotFoundException ex) {
			books.add(book);
		}
	}

	public List<Book> searchBook(String searchString, boolean includeAuthor, boolean includeTitle) {
		return books.parallelStream().filter(book1 -> predicate(book1, searchString, includeAuthor, includeTitle))
				.collect(Collectors.toList());
	}

	private boolean predicate(Book book, String searchString, boolean includeAuthor, boolean includeTitle) {
		if (includeAuthor) {
			List<Author> author1 = book.getAuthors().stream()
					.filter(author2 -> author2.getLastName().toLowerCase().contains(searchString.toLowerCase()))
					.collect((Collectors.toList()));
			if (!author1.isEmpty()) {
				return true;
			}
		}
		if (includeTitle) {
			return book.getTitle().toLowerCase().contains(searchString.toLowerCase());
		}
		return false;
	}

}
