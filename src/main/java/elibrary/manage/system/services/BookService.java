package elibrary.manage.system.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import elibrary.manage.system.entities.Book;
import elibrary.manage.system.exception.ResourceNotFoundException;
import elibrary.manage.system.payload.DaoBook;
import elibrary.manage.system.repositieris.BooksRepo;

@Service
public class BookService {
	
	@Autowired
	private BooksRepo bookRepo;

	public BookService() {
		
	}
	
	public Book searchBookById(long bookid) {
	
		Book gotBook=this.bookRepo.findById(bookid)
				.orElseThrow(()-> new ResourceNotFoundException("book","bookid",Long.toString(bookid)));	
		return gotBook;
	}


	public List<DaoBook> searchAllBooks() {
		
		List<Book> listOfBooks=this.bookRepo.findAll();
		List<DaoBook> daoBooks=listOfBooks.stream().map(book->this.bookToDaoBook(book)).collect(Collectors.toList());
		return daoBooks;
	}

	public DaoBook insertBook(DaoBook book) {
		Book bookToSave=this.DaoBookToBook(book);
		Book createdBook=this.bookRepo.save(bookToSave);
		return this.bookToDaoBook(createdBook);
	}

	public DaoBook updateBook(long bookid, DaoBook book) {
		
		Book booktoUpdate=this.bookRepo.findById(bookid)
				.orElseThrow(()-> new ResourceNotFoundException("book","bookid",Long.toString(bookid)));
		booktoUpdate.setAuthor(book.getAuthor());
		booktoUpdate.setCost(book.getCost());
		booktoUpdate.setQuantity(book.getQuantity());
		booktoUpdate.setTitle(book.getTitle());
		Book updatedBook=this.bookRepo.save(booktoUpdate);
		return this.bookToDaoBook(updatedBook);
	}

	public boolean deleteRecordById(long bookid) {
		// TODO Auto-generated method stub
         if(this.bookRepo.existsById(bookid)) {
        	 this.bookRepo.deleteById(bookid);
        	 return true;
         }
         else {
        	 return false;
         }
	}
	
	public Book DaoBookToBook(DaoBook daoBook) {
		Book book=new Book();
		book.setId(daoBook.getId());
		book.setTitle(daoBook.getTitle());
		book.setAuthor(daoBook.getAuthor());
		book.setCost(daoBook.getCost());
		book.setQuantity(daoBook.getQuantity());
		return book;
	}
	
	public DaoBook bookToDaoBook(Book book) {
		DaoBook daoBook=new DaoBook();
		daoBook.setId(book.getId());
		daoBook.setTitle(book.getTitle());
		daoBook.setAuthor(book.getAuthor());
		daoBook.setCost(book.getCost());
		daoBook.setQuantity(book.getQuantity());
		return daoBook;
	}
	
	
	

}
