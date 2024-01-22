package de.hsm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hsm.logic.Book;
import de.hsm.logic.Item;
import de.hsm.logic.ShopService;
import de.hsm.logic.ShoppingCart;

@Controller
public class ShopController {

	@GetMapping("/products")
	public ModelAndView searchProducts() {
		List<Book> products = new ShopService().searchBook("", true, true);
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("products", products);
		return mv;
	}

	@PostMapping("/addToCart")
	public ModelAndView addToCart(@RequestParam(value = "selectedProduct") String selectedProduct,
			HttpSession session) {
		String[] parts = selectedProduct.split(" ");
		String action = parts[0];
		String isbn13 = parts[1];
		ModelAndView mv = new ModelAndView();
		if (action.equals("show")) {
			mv.setViewName("redirect:/bookDetails/" + isbn13);
			return mv;
		}
		mv.setViewName("products");
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("shoppingCart", shoppingCart);
		}
		ShopService shopService = new ShopService();
		Book book = shopService.findBook(isbn13);
		shoppingCart.addItem(book, 1);
		List<Book> products = shopService.searchBook("", true, true);
		mv.addObject("products", products);
		mv.addObject("successMessage", "Book added to shopping cart");
		return mv;
	}

	@GetMapping("/shoppingCart")
	public ModelAndView showShoppingCart(HttpSession session) {
		ModelAndView mv = new ModelAndView("shoppingCart");
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("shoppingCart", shoppingCart);
		}
		List<Item> items = shoppingCart.getItems();
		if (items.isEmpty()) {
			mv.addObject("errorMessage", "Shopping cart is empty");
		}
		mv.addObject("items", items);
		return mv;
	}

	@PostMapping("/deleteItem")
	public ModelAndView deleteItem(@RequestParam(value = "deleteItem") int deletedItem, HttpSession session) {
		ModelAndView mv = new ModelAndView("shoppingCart");
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<Item> items = shoppingCart.getItems();
		if (items.isEmpty() || deletedItem < 0 || deletedItem >= items.size()) {
			mv.addObject("errorMessage", "No product deleted");
		} else {
			items.remove(deletedItem);
			mv.addObject("successMessage", "Product deleted from shopping cart");
		}
		mv.addObject("items", items);
		return mv;
	}

	@GetMapping("/bookDetails/{isbn13}")
	public ModelAndView showBookDetails(@PathVariable("isbn13") String isbn13) {
		ModelAndView mv = new ModelAndView("bookDetails");
		try {
			Book book = new ShopService().findBook(isbn13);
			mv.addObject("book", book);
			mv.addObject("authors", book.getAuthors());
			return mv;
		} catch (Exception e) {
			mv.addObject("errorMessage", "Unknown error occurred");
			mv.addObject("book", new Book());
			return mv;
		}
	}

	@PostMapping("/backToProducts")
	public String backToProducts() {
		return "redirect:/products";
	}
}
