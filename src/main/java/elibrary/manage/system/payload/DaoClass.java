package elibrary.manage.system.payload;

import jakarta.validation.constraints.NotNull;

public class DaoClass {

	
	 @NotNull(message = "studentid should not be empty")
	 private Number studentid;
	 
	 private String email;
	 
	 @NotNull(message = "bookid should not be empty")
	 private Number bookid;
	 
	 
	public DaoClass() {
		
	}


	public Number getStudentid() {
		return studentid;
	}


	public void setStudentid(Number studentid) {
		this.studentid = studentid;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Number getBookid() {
		return bookid;
	}


	public void setBookid(Number bookid) {
		this.bookid = bookid;
	}

	
	
	 
	 
}
