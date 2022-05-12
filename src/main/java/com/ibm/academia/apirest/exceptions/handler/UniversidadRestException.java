package com.ibm.academia.apirest.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.apirest.exceptions.BadRequestException;

@ControllerAdvice
public class UniversidadRestException {

	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> formatoInvalidoException(BadRequestException exception){
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", exception.getMessage());
		return new ResponseEntity(respuesta, HttpStatus.BAD_REQUEST);
	}
	
}
