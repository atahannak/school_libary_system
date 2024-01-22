package de.hsm.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Item> items = new ArrayList<>();

	public void addItem(Book book, int quantity) {
		List<Item> items1 = items.parallelStream()
				.filter(item -> item.getBook().getIsbn13().contentEquals(book.getIsbn13()))
				.collect(Collectors.toList());
		if (items1.isEmpty()) {
			Item item = new Item(book, quantity);
			items.add(item);
		} else {
			Item item = items1.get(0);
			item.setQuantity(quantity + item.getQuantity());
		}
	}

	public void removeItem(Book book) {
		List<Item> items1 = items.stream()
				.filter(item -> item.getBook().getIsbn13().contentEquals(book.getIsbn13()))
				.collect(Collectors.toList());
		if (!items1.isEmpty()) {
			Item item = items1.get(0);
			items.remove(item);			
		}
	}

	public List<Item> getItems() {
		return items;
	}
}
