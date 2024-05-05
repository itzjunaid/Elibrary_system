package elibrary.manage.system.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

	@Entity
	@Table(name="Book")
	public class Book {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		
		private String title;
		
		
		private String author;
		
		
		private double cost;
		
		
		private long quantity;
		
	    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	    private List<Transaction> transaction;
	    
	    
		public Book() {
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public double getCost() {
			return cost;
		}

		public void setCost(double cost) {
			this.cost = cost;
		}

		public long getQuantity() {
			return quantity;
		}

		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}


		public List<Transaction> getTransaction() {
			return transaction;
		}

		public void setTransaction(List<Transaction> transaction) {
			this.transaction = transaction;
		}

        
		
		
		
	
}