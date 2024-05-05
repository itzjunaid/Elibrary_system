package elibrary.manage.system.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DaoStudent {

	private long id;
	
	@NotEmpty(message = "name should not be empty")
	@Size(min = 4,max=12 ,message = "name must have between 3 to 12 characters")
	private String name;
	
	@Email(message = "Email is not Valid!")
	private String email;
	
	@NotEmpty(message = "name should not be empty")
    @Size(min = 4,max = 10 , message = "password length must be between 4 to 10")
	private String password;
	
	
	
	public DaoStudent() {
		
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
