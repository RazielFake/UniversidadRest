package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
	
	@Autowired 
	@Qualifier("profesorDAOImpl")
	private PersonaDAO profesorDao;
	
	/**
	 * Endpoint que lista los profesores existentes
	 * @NotFoundException En caso de que no existan profesores en la base de datos
	 * @return Lista de los profesores
	 * @author BRPI 12/05/22
	 */
	@GetMapping("/profesores/lista")
	public ResponseEntity<?> listarProfesores(){
		List<Persona> profesores = (List<Persona>)profesorDao.buscarTodos();
		if(profesores.isEmpty())
			throw new NotFoundException("No existen alumnos");
		return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para crear objeto Persona de tipo Profesor
	 * @param empleado Informacion necesaria para crear el objeto de tipo Profesor y guardarlo
	 * @param result
	 * @return Retorna un objeto Persona de tipo Profesor con codigo httpstatus
	 * @author BRPI 15/05/22
	 */
	@PostMapping
	public ResponseEntity<?> guardarProfesor(@Valid @RequestBody Profesor profesor, BindingResult result){
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: " + errores.getField() + " - " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		Persona profesorGuardado = profesorDao.guardar(profesor);
		return new ResponseEntity<Persona>(profesorGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para obtener la informacion un Profesor por Id
	 * @param profesorId El id del profesor que se desea buscar
	 * @NotFoundException En caso de que falle y no encuentre el profesor solicitado
	 * @return Retorna un objeto Persona de tipo Profesor con codigo httpstatus
	 * @author BRPI 15/05/22
	 */
	@GetMapping("/profesorId/{profesorId}")
	public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Integer profesorId){
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format(
					"Profesor con ID: %d no encontrado.", profesorId));
		return new ResponseEntity<Persona>(oProfesor.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para actualizar los datos de un Profesor
	 * @param profesorId Recibe el id del Profesor que se quiere actualizar
	 * @param profesor Recibe los datos que se quieren actualizar
	 * @NotFoundException En caso de que falle y no encuentre el profesor solicitado
	 * @return Retorna un objeto Persona de tipo Profesor con codigo httpstatus
	 * @author BRPI 15/05/22
	 */
	@PutMapping("/upd/profesorId/{profesorId}")
	public ResponseEntity<?> actualizarProfesor(@PathVariable Integer profesorId, @RequestBody Persona profesor){
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format(
					"Profesor con ID: %d no encontrado.", profesorId));
		
		Persona profesorActualizado = ((ProfesorDAO)profesorDao).actualizar(oProfesor.get(), profesor);
		return new ResponseEntity<Persona>(profesorActualizado, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para borrar un Profesor
	 * @param profesorId Recibe el id del Profesor que se quiere eliminar
	 * @NotFoundException En caso de que falle y no encuentre el profesor solicitado
	 * @return Confirmacion de que el profesor ha sido eliminado con codigo httpstatus
	 * @author BRPI 15/05/22
	 */
	@DeleteMapping("/profesorId/{profesorId}")
	public ResponseEntity<?> eliminarProfesor(@PathVariable Integer profesorId){
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format(
					"Profesor con ID: %d no encontrado.", profesorId));
		
		profesorDao.eliminarPorId(oProfesor.get().getId());
		
		return new ResponseEntity<String>("Profesor ID: " + profesorId + " eliminado exitosamente.", HttpStatus.NO_CONTENT);
	}

}
