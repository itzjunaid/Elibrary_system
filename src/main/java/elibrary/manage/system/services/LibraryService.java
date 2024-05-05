package elibrary.manage.system.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elibrary.manage.system.entities.Book;
import elibrary.manage.system.entities.Fine;
import elibrary.manage.system.entities.Student;
import elibrary.manage.system.entities.Transaction;
import elibrary.manage.system.repositieris.BooksRepo;
import elibrary.manage.system.repositieris.TransactionRepo;
import elibrary.manage.system.repositieris.FineRepo;


@Service
public class LibraryService {
	
	int sum;
	private long daysLate;
	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private FineRepo fineRepo;

	@Autowired
	private BooksRepo bookRepo;

    public void orderBook(Student student, Book book) {
        Transaction transaction = new Transaction();
        transaction.setStudent(student);
        transaction.setBook(book);
        transaction.setTransactionType("Ordered");
        transaction.setTransactionDate(LocalDateTime.now());

        // expected return date for the transaction
        LocalDateTime expectedReturnDate = LocalDateTime.now().plusDays(7);
        transaction.setExpectedReturnDate(expectedReturnDate);

        transactionRepo.save(transaction);
        // Update book availability
        book.setQuantity(book.getQuantity()-1);
        this.bookRepo.save(book);
    }

    public void returnBook(Student student, Book book) {
        // Find the transaction for the user and book
        Transaction transaction = transactionRepo.findByStudentAndBookAndTransactionType(student, book, "Ordered")
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found for given book"));

        //  return date  and  transaction type updated
        transaction.setTransactionType("Returned");
        transaction.setTransactionDate(LocalDateTime.now().plusDays(25));
        transaction.setReturnDate(LocalDateTime.now().plusDays(25));       // Set return date
        transactionRepo.save(transaction);

        // Updated book availability
        book.setQuantity(book.getQuantity()+1);
        this.bookRepo.save(book);

        // Check if the book was returned late and handle fines
        LocalDateTime expectedReturnDate = transaction.getExpectedReturnDate();
        if (expectedReturnDate != null && transaction.getReturnDate().isAfter(expectedReturnDate)) {
            double fineAmount = this.calculateFine(transaction);
            Fine fine = new Fine();
            fine.setStudent(student);
            fine.setBook(book);
            fine.setFineAmount(fineAmount);
            fine.setDaysLate(daysLate);
            fine.setFineReason("Overdue book return");
            fineRepo.save(fine);
        }
    }

    private double calculateFine(Transaction transaction) {
        // Logic to calculate fine
        double finePerDay = 10; // Example: per day
        LocalDateTime expectedReturnDate = transaction.getExpectedReturnDate();
       // long daysLate = transaction.getReturnDate().until(expectedReturnDate).getDays();
        Duration duration = Duration.between(expectedReturnDate,transaction.getReturnDate());
        daysLate = duration.toDays();

        return finePerDay * daysLate;
    }

    public List<Fine> calculateFines(long studentId , long bookId) {
        List<Fine> fines=fineRepo.findByStudentIdAndBookId(studentId,bookId);
        return fines;
    }
    
   

	
}

