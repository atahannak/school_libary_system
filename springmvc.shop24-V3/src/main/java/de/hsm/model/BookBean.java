package de.hsm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.hsm.logic.Author;

public class BookBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String isbn13;
	private String publisher;
	private Integer numberOfPages;
	private LocalDate publishedDate;
	private String language;
	private BigDecimal price;
	private List<Author> authors = new ArrayList<>();
	private int authorId;

	public BookBean(String title, String isbn13, String publisher, Integer numberOfPages, 
			LocalDate publishedDate, String language, BigDecimal price) {
		this.title = title;
		this.isbn13 = isbn13;
		this.publisher = publisher;
		this.numberOfPages = numberOfPages;
		this.publishedDate = publishedDate;
		this.language = language;
		this.price = price;
	}

	public BookBean() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void addAuthor(Author author) {
		this.authors.add(author);
	}
	
	public Author getFirstAuthor() {
		if (!authors.isEmpty()) {
			return authors.get(0);
		}
		return new Author();
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

}
