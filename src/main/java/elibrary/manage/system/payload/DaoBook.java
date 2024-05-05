package elibrary.manage.system.payload;

import java.util.List;

import elibrary.manage.system.entities.Transaction;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class DaoBook {

	private int id;

	@NotEmpty(message="Title of the book cannot be empty")
	private String title;
	
	@NotEmpty(message="Author of the book cannot be empty")
	private String author;
	
	@Min(value=0, message="cost cannot be negative")
	private double cost;
	
	@Min(value=0, message="enter the correct price")
	private long quantity;
	
	private List<Transaction> transaction;

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
