package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.BookService;
import com.example.demo.Service.RentalService;
import com.example.demo.Service.auth.CustomDetails;


@Controller
@RequestMapping("/rental")
public class RentalController {

	@Autowired
	RentalService rentalService;
	@Autowired
	BookService bookService;
	
	
	@GetMapping("/rentalview")
	public String findRentalBook(@AuthenticationPrincipal CustomDetails customDetails, Model model) {
		int rental_key_id = customDetails.getUserList().getUser_id();
		model.addAttribute("RentalBooks", rentalService.findRentalBook(rental_key_id));
		return "/rental";
	}
	
	@PostMapping("/{book_id}")
	public String rentalBook(@AuthenticationPrincipal CustomDetails customDetails,@PathVariable int book_id) {
		int rental_key_id = customDetails.getUserList().getUser_id();

		System.out.println("rental_id："+rental_key_id);
		System.out.println("book_id："+book_id);
		rentalService.rentalBook(rental_key_id, book_id);
		bookService.rentalBook(book_id);
		return "redirect:/book/list";
	}
	
	@GetMapping("/return/{book_id}")
	public String returnBook(@AuthenticationPrincipal CustomDetails customDetails,@PathVariable int book_id) {
		int rental_key_id = customDetails.getUserList().getUser_id();

		System.out.println("rental_id："+rental_key_id);
		System.out.println("book_id："+book_id);
		rentalService.returnBook(rental_key_id, book_id);
		bookService.returnBook(book_id);
		return "redirect:/rental/rentalview";
	}

}
