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
		int count = 3;
		List<BookList> lists = bookService.findBook();
		System.out.println(lists.size());
		assertEquals(count, lists.size());
	}
	
	//book_idを使用して検索の一致を確認する
	@Test
	@Disabled
	void targetBookTest(){
		BookList lists = bookService.targetBook(2);
		
		assertEquals(1, lists.getBook_id());
		assertEquals("キングダム100巻", lists.getBook_name());
		assertEquals("原泰久", lists.getBook_author());
		assertEquals("集英社", lists.getBook_publisher());
		assertEquals("2023-06-23", lists.getBook_releasetime());
		assertEquals("2023-06-24 21:41:41", lists.getCreate_time());
		assertEquals("2023-06-24 21:42:03", lists.getUpdate_time());
		assertEquals("2.jpg", lists.getFile_name());
		assertEquals(1, lists.getStock());
	}
}
