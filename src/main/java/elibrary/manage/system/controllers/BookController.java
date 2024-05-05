package elibrary.manage.system.controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import elibrary.manage.system.entities.Book;
import elibrary.manage.system.payload.ApiResponse;
import elibrary.manage.system.payload.DaoBook;
import elibrary.manage.system.services.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/books")
public class BookController {
	
	
	
	@Autowired
	private BookService bookService;
		
    public BookController() {
	
	}
    
	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable long bookId){
		Book gotBook=this.bookService.searchBookById(bookId);
		return new ResponseEntity<Book>(gotBook,HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<DaoBook>> getBooks(){
		
		List<DaoBook> listOfBooks=this.bookService.searchAllBooks();
		return new ResponseEntity<List<DaoBook>>(listOfBooks,HttpStatus.OK);
		
	}

	@PostMapping
	public ResponseEntity<DaoBook> createBook(@Valid @RequestBody DaoBook book){
		DaoBook createdBook=this.bookService.insertBook(book);
		return new ResponseEntity<DaoBook>(createdBook,HttpStatus.CREATED);
	}
	
	@PutMapping("/{bookid}")
	public ResponseEntity<DaoBook> updateBookResource(@PathVariable long bookid, @Valid @RequestBody DaoBook book){
		
		DaoBook updatedBook=this.bookService.updateBook(bookid,book);
		return new ResponseEntity<DaoBook>(updatedBook,HttpStatus.OK);
	}
	
	@DeleteMapping("/{bookid}")
	public ResponseEntity<ApiResponse> deleteBookResource(@PathVariable long bookid){
		if(this.bookService.deleteRecordById(bookid)) {
			ApiResponse response=new ApiResponse("book with bookId: "+bookid+ " has been deleted successfully",true);
			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ApiResponse>(new ApiResponse("No such a record found",false),HttpStatus.BAD_REQUEST);
		}
	
		
	}
	
	
}
