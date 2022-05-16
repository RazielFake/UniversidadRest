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
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	
	
	@Autowired
	@Qualifier("empleadoDAOImpl")
	private PersonaDAO empleadoDao;
	
	
	
	
	/**
	 * Endpoint para crear objeto Persona de tipo Empleado
	 * @param empleado Informacion necesaria para crear el objeto de tipo Empleado y guardarlo
	 * @param result
	 * @return Retorna un objeto Persona de tipo Empleado con codigo httpstatus
	 * @author BRPI 14/05/22
	 */
	@PostMapping
	public ResponseEntity<?> guardarEmpleado(@Valid @RequestBody Empleado empleado, BindingResult result){
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: " + errores.getField() + " - " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		Persona empleadoGuardado = empleadoDao.guardar(empleado);
		return new ResponseEntity<Persona>(empleadoGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para obtener la informacion un Empleado por Id
	 * @param empleadoId El id del Empleado que se desea buscar
	 * @NotFoundException En caso de que falle y no encuentre el Empleado solicitado
	 * @return Retorna un objeto Persona de tipo Empleado con codigo httpstatus
	 * @author BRPI 14/05/22
	 */
	@GetMapping("/empleadoId/{empleadoId}")
	public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Integer empleadoId){
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format(
					"Empleadocon ID: %d no encontrado.", empleadoId));
		return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para actualizar los datos de un Empleado
	 * @param empleadoId Recibe el id del Empleado que se quiere actualizar
	 * @param empleado Recibe los datos que se quieren actualizar
	 * @NotFoundException En caso de que falle y no encuentre el Empleado solicitado
	 * @return Retorna un objeto Persona de tipo Empleado con codigo httpstatus
	 * @author BRPI 14/05/22
	 */
	@PutMapping("/upd/empleadoId/{empleadoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer empleadoId, @RequestBody Persona empleado){
		Optional<Persona> oEmpleado= empleadoDao.buscarPorId(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format(
					"Alumno con ID: %d no encontrado.", empleadoId));
		
		Persona empleadoActualizado = ((EmpleadoDAO)empleadoDao).actualizar(oEmpleado.get(), empleado);
		return new ResponseEntity<Persona>(empleadoActualizado, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para borrar un Empleado
	 * @param empleadoId Recibe el id del Empleado que se quiere eliminar
	 * @NotFoundException En caso de que falle y no encuentre el empleado solicitado
	 * @return Confirmacion de que el empleado ha sido eliminado con codigo httpstatus
	 * @author BRPI 14/05/22
	 */
	@DeleteMapping("/empleadoId/{empleadoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer empleadoId){
		Optional<Persona> oEmpleado= empleadoDao.buscarPorId(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format(
					"Empleado con ID: %d no encontrado.", empleadoId));
		
		empleadoDao.eliminarPorId(oEmpleado.get().getId());
		
		return new ResponseEntity<String>("Empleado ID: " + empleadoId + " eliminado exitosamente.", HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Endpoint que busca un empleado por su tipo de empleado
	 * @param String Requiere el tipo de empleado que se quiere buscar
	 * @NotFoundException En caso de que falle y no encuentre el empleado solicitado
	 * @return Lista con los empleados correspondienes al tipo solicitado
	 * @author BRPI 14/05/22
	 */
	@GetMapping("/tipo/{tipoEmpleado}")
	public ResponseEntity<?> obtenerEmpleadoPorTipo(@PathVariable String tipoEmpleado){
		List<Persona> empleados = (List<Persona>)((EmpleadoDAO)empleadoDao).buscarEmpleadoPorTipoEmpleado(((EmpleadoDAO)empleadoDao).obtenerTipoEmpleado(tipoEmpleado));
		
		if(empleados.isEmpty())
			throw new NotFoundException("No existen empleados del tipo: "+tipoEmpleado);
		
		return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
	}
	
}
