package elibrary.manage.system.entities;

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
@Table(name="fine")
public class Fine {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @JsonBackReference
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "student_id" )
	    private Student student;
	    
	   // @JsonBackReference
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "book_id")
	    private Book book;
	    
	    private double fineAmount;
	    
	    private long   daysLate;
	    
	    private String fineReason;
	    
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
		public double getFineAmount() {
			return fineAmount;
		}
		public void setFineAmount(double fineAmount) {
			this.fineAmount = fineAmount;
		}
		public String getFineReason() {
			return fineReason;
		}
		public void setFineReason(String fineReason) {
			this.fineReason = fineReason;
		}
		public long getDaysLate() {
			return daysLate;
		}
		public void setDaysLate(long daysLate) {
			this.daysLate = daysLate;
		}
		
		
	    
	    

}
