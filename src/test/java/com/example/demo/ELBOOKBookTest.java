package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entity.BookList;
import com.example.demo.Service.BookService;


@SpringBootTest
public class ELBOOKBookTest {

	@Autowired
	private BookService bookService;
	
	//件数取得処理のテスト
	@Test
	@Disabled
	void findBookTest() {
		 //DBの件数を入力して一致しているかどうか？
		int count = 4;
		List<BookList> lists = bookService.findBook();
		System.out.println(lists.size());
		assertEquals(count, lists.size());
	}
	
	//book_idを使用して検索の一致を確認する
	@Test
	@Disabled
	void targetBookTest(){
		BookList lists = bookService.targetBook(2);
		
		assertEquals(2, lists.getBook_id());
		assertEquals("キングダム100巻", lists.getBook_name());
		assertEquals("原泰久", lists.getBook_author());
		assertEquals("集英社", lists.getBook_publisher());
		assertEquals("2023-06-23", lists.getBook_releasetime());
		assertEquals("2.jpg", lists.getFile_name());
		assertEquals(1, lists.getStock());
	}
	

	@Test
	@Disabled
	void createBook() {
		BookList bookList = new BookList();
		bookList.setBook_name("深田恭子写真集 AKUA");
		bookList.setBook_author("深田恭子");
		bookList.setBook_publisher("集英社");
		bookList.setBook_releasetime("2023-07-16");
		bookList.setStock(1);
		bookService.createBook(bookList);

		BookList targetlistBookLists = bookService.targetBook(9);
		
		assertEquals(9, targetlistBookLists.getBook_id());
		assertEquals("深田恭子写真集 AKUA", targetlistBookLists.getBook_name());
		assertEquals("深田恭子", targetlistBookLists.getBook_author());
		assertEquals("集英社", targetlistBookLists.getBook_publisher());
		assertEquals("2023-07-16", targetlistBookLists.getBook_releasetime());
		assertEquals(1, targetlistBookLists.getStock());

	}
	

	@Test
	//@Disabled
	void editBook() {
		BookList bookList = new BookList();
		bookList.setBook_id(3);
		bookList.setBook_name("キングダム2000巻");
		bookList.setBook_author("原泰久XXX");
		bookList.setBook_publisher("集英社☆");
		bookList.setBook_releasetime("2023-07-17");
		bookList.setStock(10);

		bookService.editBook(bookList);
		

		BookList targetlistBookLists = bookService.targetBook(3);
		

		assertEquals(3, targetlistBookLists.getBook_id());
		assertEquals("キングダム2000巻", targetlistBookLists.getBook_name());
		assertEquals("原泰久XXX", targetlistBookLists.getBook_author());
		assertEquals("集英社☆", targetlistBookLists.getBook_publisher());
		assertEquals("2023-07-17", targetlistBookLists.getBook_releasetime());
		assertEquals(10, targetlistBookLists.getStock());

	}
	

	@Test
	@Disabled
	void deleteBook() {
		bookService.deleteBook(4);
	}
	

	@Test
	@Disabled
	void rentalreturnBook() {
		int book_id = 3;
		BookList lists = bookService.targetBook(book_id);
		System.out.println("在庫数" + lists.getStock());

		bookService.rentalBook(book_id);
		BookList rentald_lists = bookService.targetBook(book_id);
		System.out.println("貸し出し処理在庫数" + rentald_lists.getStock());

		System.out.println(lists.getStock() - 1);

		assertEquals(lists.getStock() - 1, rentald_lists.getStock());
		System.out.println("-----------------------------------");

		bookService.returnBook(book_id);
		BookList returnd_lists = bookService.targetBook(book_id);
		System.out.println("返却処理在庫数" + returnd_lists.getStock());

		assertEquals(lists.getStock(), returnd_lists.getStock());
		System.out.println("-----------------------------------");

	}



}
