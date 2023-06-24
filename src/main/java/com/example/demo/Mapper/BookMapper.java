package com.example.demo.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Entity.BookList;

@Mapper
public interface BookMapper {
	
//
	void createBook(BookList bookList);
	
	List<BookList> findBook();
	
	void getBookList(int book_id);
	
	BookList targetBook(int book_id);
	
	void editBook(BookList bookList);

	void deleteBook(int book_id);
//
	int getMaxNumberBook();
//

}
