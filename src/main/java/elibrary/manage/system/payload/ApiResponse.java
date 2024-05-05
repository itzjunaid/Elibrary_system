package elibrary.manage.system.payload;

public class ApiResponse {
	
	private String message;
	private boolean  status;
	
	public ApiResponse(String message, boolean success) {
		
		this.message = message;
		this.status = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return status;
	}

	public void setSuccess(boolean success) {
		this.status = success;
	}
	
	
	
	

}
