 package com.mitocode.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@RestController//<=====
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)//solo cuando encuentre modelo tipo excepcion
	public final ResponseEntity<Object> manejarTodasExcepciones(Exception ex,WebRequest request){
		//ExceptionResponse es una instancia
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),//se le manda el mensaje del error que depende del request
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);//retorn en formato de servicio rest la excepcion que acaba de instantiar la respuesta 
			
	}
	
	@ExceptionHandler(ModeloNotFoundException.class)//solo cuando encuentre modelo tipo excepcion
	public final ResponseEntity<Object> manejarModeloExcepciones(ModeloNotFoundException ex,WebRequest request){
		//ExceptionResponse es una instancia
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),//se le manda el mensaje del error que depende del request
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);//retorn en formato de servicio rest la excepcion que acaba de instantiar la respuesta 
			
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status,WebRequest request) {
		String errores = "";
		for (ObjectError e : ex.getBindingResult().getAllErrors()) {
			errores += e.getObjectName();
		}
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), "Validate fallida",errores);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}
}
