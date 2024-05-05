package elibrary.manage.system.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Transaction")
public class Transaction {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	  
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "student_id")
	    private Student student;
	    
	
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "book_id")
	    private Book book;
	    
	    private String transactionType;
	    
	    private LocalDateTime transactionDate;

		private LocalDateTime returnDate;
		
		private LocalDateTime expectedReturnDate;
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public Student getStudent() {
			return student;
		}
		public void setStudent(Student student) {
			this.student = student;
		}
		
		
		public Book getBook() {
			return book;
		}
		public void setBook(Book book) {
			this.book = book;
		}
		
		
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		
		
		public LocalDateTime getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(LocalDateTime transactionDate) {
			this.transactionDate = transactionDate;
		}
		
		
		public LocalDateTime getReturnDate() {
			return returnDate;
		}
		public void setReturnDate(LocalDateTime returnDate) {
			this.returnDate = returnDate;
		}
		
		
		public LocalDateTime getExpectedReturnDate() {
			return expectedReturnDate;
		}
		public void setExpectedReturnDate(LocalDateTime expectedReturnDate) {
			this.expectedReturnDate = expectedReturnDate;
		}
		
		
	    
	    
	

}
