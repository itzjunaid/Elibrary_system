package elibrary.manage.system.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import elibrary.manage.system.payload.ApiResponse;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse  apiResponse=new ApiResponse(message, false);	
		return new ResponseEntity<ApiResponse>(apiResponse ,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgNotValidExceptionHandler(MethodArgumentNotValidException ex){
		Map<String,String> res=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)-> {
		      String fieldName= ((FieldError) error).getField();
		      String message=error.getDefaultMessage();
		      res.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(res,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AdminNotCreatedException.class)
	public ResponseEntity<String> AdminNotCreatedExceptionHandler(AdminNotCreatedException ex){
		String  message=ex.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> constraintViolationExceptionHandler(ConstraintViolationException ex)
	{
		                
		  String cause= ex.getMessage();
		  return new ResponseEntity<String>(cause,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex)
	{
		                
		  String cause= ex.getMessage();
		  return new ResponseEntity<String>("Bad request",HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex)
	{
		                
		  String cause= ex.getMessage();
		  return new ResponseEntity<String>(cause,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> IllegalArgumentExceptionHandler(IllegalArgumentException ex){
		String message=ex.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex){
		String message=ex.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
	}
	

}
