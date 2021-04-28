package pe.gob.indecopi.exception;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ClsExceptionControllerAdvice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5896583566678668342L;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ClsErrorResponse> handleAll(Exception e) {
		LOGGER.error("handleAll: " + e.getMessage());
		ClsErrorResponse error = new ClsErrorResponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setError("ERROR");
		error.setError_description(e.getMessage());
		return new ResponseEntity<ClsErrorResponse>(error, error.getStatus());
	}
	
	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<ClsErrorResponse> handleFileUploadingError(Exception e) {
		LOGGER.error("handleAll: " + e.getMessage());
		ClsErrorResponse error = new ClsErrorResponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setError("ERROR");
		error.setError_description(e.getMessage());
		return new ResponseEntity<ClsErrorResponse>(error, error.getStatus());
	}

}
