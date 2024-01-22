package de.hsm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.hsm.logic.Author;
import de.hsm.logic.ShopService;
import de.hsm.model.BookBean;

@Controller
public class AdminController {

	@GetMapping("/addBook")
	public ModelAndView addBook(BookBean bookBean, BindingResult bindingResult, HttpSession session) {
		List<Author> authors = new ArrayList<>();
		session.setAttribute("authors", authors);
		ModelAndView mv = new ModelAndView("book");
		mv.addObject("bookBean", bookBean);
		mv.addObject("authors", getAllAuthors());
		mv.addObject("selectedAuthors", authors);
		return mv;
	}

	private Map<String, String> getAllAuthors() {
		Map<String, String> authorMap = new HashMap<>();
		ShopService shopService = new ShopService();
		List<Author> list = shopService.getAllAuthors();
		for (Author author : list) {
			authorMap.put(Integer.toString(author.getId()), author.getName());
		}
		return authorMap;
	}

	@PostMapping("/addBook")
	public ModelAndView addBookPost(@RequestParam(value = "button") String selectedButton, @Valid BookBean bookBean,
			BindingResult bindingResult, HttpSession session) {
		ShopService shopService = new ShopService();
		ModelAndView mv = new ModelAndView("book");
		if (selectedButton.equals("addAuthor")) {
			List<Author> authors = (List<Author>) session.getAttribute("authors");
			int authorId = bookBean.getAuthorId();
			authors.add(shopService.findAuthor(authorId));
			mv.addObject("bookBean", bookBean);
			mv.addObject("authors", getAllAuthors());
			mv.addObject("selectedAuthors", authors);
			mv.addObject("successMessage", "Author added");
		} else if (selectedButton.equals("addBook")) {
			// TODO: add code here
		} else { // remove author
			List<Author> authors = (List<Author>) session.getAttribute("authors");
			removeAuthor(Integer.parseInt(selectedButton), authors);
			mv.addObject("successMessage", "Author removed");
			mv.addObject("bookBean", new BookBean());
			mv.addObject("authors", getAllAuthors());
			mv.addObject("selectedAuthors", authors);
		}
		return mv;
	}

	public void removeAuthor(int id, List<Author> authors) {
		for (Author author : authors) {
			if (author.getId() == id) {
				authors.remove(author);
				break;
			}
		}
	}

}
