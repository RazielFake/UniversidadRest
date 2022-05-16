package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	@Qualifier("alumnoDAOImpl")
	private PersonaDAO alumnoDao;
	
	@Autowired
	private CarreraDAO carreraDao;
	
	
	
	/**
	 * Endpint para crear un objeto Persona de tipo Alumno
	 * @param alumno Informacion necesaria para crear el objeto de tipo Alumno y guardarlo
	 * @return Retorna un objeto Persona de tipo Alumno con codigo httpstatus
	 * @author BRPI 12/05/22
	 */
	@PostMapping
	public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno){
		Persona alumnoGuardado = alumnoDao.guardar(alumno);
		
		return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
	}
	
	
	/**
	 * Endpoint para consultar la lista de alumnos
	 * @return Lista de alumnos
	 * @NotFoundException En caso de que no existan alumnos en la base de datos
	 * @author BRPI 12/05/22
	 */
	@GetMapping("/alumnos/lista")
	public ResponseEntity<?> obtenerTodos(){
		List<Persona> alumnos = (List<Persona>)alumnoDao.buscarTodos();
		if(alumnos.isEmpty())
			throw new NotFoundException("No existen alumnos");
		return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para obtener la informacion un alumno por Id
	 * @param alumnoId El id del alumno que se desea buscar
	 * @NotFoundException En caso de que falle y no encuentre el alumno solicitado
	 * @return Retorna un objeto Persona de tipo alumno con codigo httpstatus
	 * @author BRPI 12/05/22
	 */
	@GetMapping("/alumnoId/{alumnoId}")
	public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Integer alumnoId){
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		
		if(!oAlumno.isPresent())
			throw new NotFoundException(String.format(
					"Alumno con ID: %d no encontrado.", alumnoId));
		return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para actualizar los datos de un alumno
	 * @param alumnoId Recibe el id del alumno que se quiere actualizar
	 * @param alumno Recibe los datos que se quieren actualizar
	 * @NotFoundException En caso de que falle y no encuentre el alumno solicitado
	 * @return Retorna un objeto Persona de tipo alumno con codigo httpstatus
	 * @author BRPI 12/05/22
	 */
	@PutMapping("/upd/alumnoId/{alumnoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer alumnoId, @RequestBody Persona alumno){
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		
		if(!oAlumno.isPresent())
			throw new NotFoundException(String.format(
					"Alumno con ID: %d no encontrado.", alumnoId));
		
		Persona alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(oAlumno.get(), alumno);
		return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para borrar un alumno
	 * @param alumnoId Recibe el id de alumno que se quiere eliminar
	 * @NotFoundException En caso de que falle y no encuentre el alumno solicitado
	 * @return Confirmacion de que el alumno ha sido eliminado con codigo httpstatus
	 * @author BRPI 12/05/22
	 */
	@DeleteMapping("/alumnoId/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId){
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		
		if(!oAlumno.isPresent())
			throw new NotFoundException(String.format(
					"Alumno con ID: %d no encontrado.", alumnoId));
		
		alumnoDao.eliminarPorId(oAlumno.get().getId());
		
		return new ResponseEntity<String>("Alumno ID: " + alumnoId + " eliminado exitosamente.", HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Endpoint para asociar una carrera a un alumno
	 * @param carreraId Id de la carrera con la que se va asociar el alumno
	 * @param alumnoId Id del alumno al se asociara la carrera
	 * @NotFoundException En caso de que falle y no encuentre el alumno solicitado
	 * @return Confirmacion de que el alumno ha sido eliminado
	 * @author BRPI 12/05/22
	 */
	@PutMapping("/alumnoId/{alumnoId}/carrera/{carreraId}")
	public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer carreraId, @PathVariable Integer alumnoId){
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		if(!oAlumno.isPresent())
			throw new NotFoundException(String.format(
					"Alumno con ID: %d no encontrado.", alumnoId));
		
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
		if(!oCarrera.isPresent())
			throw new NotFoundException(String.format(
					"Carrera con ID: %d no encontrado.", carreraId));
		
		Persona alumno = ((AlumnoDAO)alumnoDao).asociarCarreraAlumno(oAlumno.get(), oCarrera.get());
		
		return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
	}
	
}
