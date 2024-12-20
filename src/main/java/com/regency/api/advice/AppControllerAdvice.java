package com.regency.api.advice;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.regency.api.entity.ErrorEntity;
import com.regency.api.exception.DoctorException;

@ControllerAdvice
public class AppControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DoctorException.class)
	public ResponseEntity<ErrorEntity> doctorExceptionHandler(Exception ex, WebRequest web) {
		ErrorEntity ee = new ErrorEntity();
		ee.setMessage(ex.getMessage());
		ee.setTimeStamp(new Timestamp(System.currentTimeMillis()));
		ee.setStatus(400);
		ee.setError("BAD_REQUEST");
		ee.setPath(web.getDescription(false));
		return new ResponseEntity<ErrorEntity>(ee, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<ErrorEntity> handleInternalServerErrorExceptionHandler(Exception ex, WebRequest web) {
		ErrorEntity ee = new ErrorEntity();
		ee.setMessage(ex.getMessage());
		ee.setTimeStamp(new Timestamp(System.currentTimeMillis()));
		ee.setStatus(500);
		ee.setError("INTERNAL SERVER ERROR");
		ee.setPath(web.getDescription(false));
		return new ResponseEntity<ErrorEntity>(ee, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		for(ObjectError obj : allErrors) {
			map.put(((FieldError) obj).getField(), obj.getDefaultMessage());
		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_GATEWAY);

	}
}
