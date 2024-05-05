package elibrary.manage.system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elibrary.manage.system.entities.Book;
import elibrary.manage.system.entities.Fine;
import elibrary.manage.system.entities.Student;
import elibrary.manage.system.entities.Transaction;
import elibrary.manage.system.payload.DaoClass;
import elibrary.manage.system.repositieris.TransactionRepo;
import elibrary.manage.system.services.BookService;
import elibrary.manage.system.services.LibraryService;
import elibrary.manage.system.services.StudentService;
import jakarta.validation.Valid;

    @ComponentScan
    @RestController
	@RequestMapping("/library")
public class LibraryController {
	    
	    @Autowired
	    private  TransactionRepo transactionRepo;

		@Autowired
	    private LibraryService libraryService;
	    
	    @Autowired
	    private StudentService studentService;
	    
	    @Autowired
	    private BookService bookService;
	    
	    

		@PostMapping("/order")
	    public ResponseEntity<String> orderBook(@Valid @RequestBody DaoClass daoClass) {

			
	        Student student = this.studentService.getStudentById(daoClass.getStudentid().longValue());       
	        Book book = this.bookService.searchBookById(daoClass.getBookid().longValue());

	       
	        if (student == null || book == null) {
	            return ResponseEntity.badRequest().body("Invalid user or book");
	        }
     
	        // Check book availability
	        if (book.getQuantity()==0) {
	            return ResponseEntity.badRequest().body("Book with "+book.getId()+" is not available for order");
	        }

	        // Place order
	        libraryService.orderBook(student, book);
	        return ResponseEntity.ok("Book with book_id:"+book.getId()+" ordered successfully");
	    }

	    @PostMapping("/return")
	    public ResponseEntity<String> returnBook(@Valid @RequestBody DaoClass daoClass) {
	        Student student = this.studentService.getStudentById(daoClass.getStudentid().longValue()); 
	        Book book = this.bookService.searchBookById(daoClass.getBookid().longValue());

	       
	        if (student == null || book == null) {
	            return ResponseEntity.badRequest().body("Invalid user or book");
	        }

	        // Check if the book is borrowed by the user
	        Transaction transaction=new Transaction();
	        boolean bookBorrowedByStudent =this.transactionRepo.
	        		existsByTransactionTypeAndBookIdAndStudentId("Ordered",book.getId(),student.getId());
	        
	      if (!bookBorrowedByStudent) {
	            return ResponseEntity.badRequest().body("The book is not borrowed by the user");
	        }

	        // Return book
	        libraryService.returnBook(student, book);
	        return ResponseEntity.ok("Book returned successfully");
	    }

	    @GetMapping("/fines")
	    public ResponseEntity<List<Fine>> calculateFines(@Valid @RequestBody DaoClass daoClass) {
	        // Calculate fines
	    	long studentId=daoClass.getStudentid().longValue();
	    	long bookId=daoClass.getBookid().longValue();
	        List<Fine> fines = libraryService.calculateFines(studentId,bookId);
	        return ResponseEntity.ok(fines);
	    }
	    
	    
	    
	}


