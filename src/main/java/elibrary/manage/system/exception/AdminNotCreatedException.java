package elibrary.manage.system.exception;

public class AdminNotCreatedException extends RuntimeException {
	
	String email;

	public AdminNotCreatedException(String email) {
		super(String.format("email for %s is already available ",email));
		this.email = email;
	}
	
	
	

}
