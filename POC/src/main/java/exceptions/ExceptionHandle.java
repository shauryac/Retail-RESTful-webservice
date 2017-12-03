package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ExceptionHandle {
	
	// exception thrown when productId is different in URI and body in case of put request
	@ExceptionHandler(ProductIdMismatchException.class)
	public ResponseEntity<ExceptionResponse> productIdMismatch (ProductIdMismatchException ex){
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorMsg(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	// exception thrown when wrong data format is provided in json body for put request
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ExceptionResponse> InvalidFormat (InvalidFormatException ex){
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorMsg("Please check the datatype of supplied body");
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	// exception thrown when there is no information for the productId in the database
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ExceptionResponse> ProductNotFound (ProductNotFoundException ex){
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorMsg(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	//exception thrown when redskytarget give 4** exceptions
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ExceptionResponse> HttpClientError (HttpClientErrorException ex){
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorMsg(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, ex.getStatusCode());
	}
	

}
