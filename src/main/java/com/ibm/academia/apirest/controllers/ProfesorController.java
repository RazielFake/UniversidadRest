package com.ibm.academia.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
	
	@Autowired 
	@Qualifier("profesorDAOImpl")
	private PersonaDAO profesorDao;
	
	/**
	 * Endpoint que lista los profesores existentes
	 * @return Lista de los profesores
	 * @author BRPI
	 */
	@GetMapping("/profesores/lista")
	public ResponseEntity<?> listarProfesores(){
		List<Persona> profesores = (List<Persona>)profesorDao.buscarTodos();
		if(profesores.isEmpty())
			throw new NotFoundException("No existen alumnos");
		return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
	}

}
