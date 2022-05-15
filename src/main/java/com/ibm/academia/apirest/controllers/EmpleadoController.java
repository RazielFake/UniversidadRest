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

import com.ibm.academia.apirest.entities.Empleado;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	
	
	@Autowired
	@Qualifier("empleadoDAOImpl")
	private PersonaDAO empleadoDao;
	
	
	
	
	/**
	 * Endpoint para crear un nuevo empleado
	 * @param empleado Informacion del empleado que se quiere crear
	 * @param result
	 * @return Objeto Persona tipo empleado
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
	 * Endpoint para obtener un empleado por Id
	 * @param empleadoId El id del empleado que se desea obtener
	 * @return Retorna un objeto de tipo persona con la informacion del empleado
	 * @NotFoundException En caso de que falle 
	 * @author BRPI
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
	 * Endpoint para actualizar los datos de un empleado
	 * @param empleadoId Recibe el id del empleado que se quiere actualizar
	 * @param empleadoRecibe el objeto Persona con los datos nuevos
	 * @NotFoundException En caso de que falle la actualizacion
	 * @return Retorna un objeto Persona de tipo empleado con la informacion actualizada
	 * @author BRPI
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
	 * Endpoint para borrar un empleado
	 * @param empleadoId Recibe el id de empleado que sera borrado
	 * @NotFoundException En caso de que falle durante el proceso
	 * @return Confirmacion de que el empleado ha sido eliminado
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
	 * Endpoint que busca un empleado por su tipo
	 * @param String Requiere el tipo de empleado que se quiere buscar
	 * @NotFoundException En caso de que falle el proceso
	 * @return Un objeto Persona de tipo empleado
	 * @author BRPI
	 */
	@GetMapping("/tipo/{tipoEmpleado}")
	public ResponseEntity<?> obtenerEmpleadoPorTipo(@PathVariable String tipoEmpleado){
		List<Persona> empleados = (List<Persona>)((EmpleadoDAO)empleadoDao).buscarEmpleadoPorTipoEmpleado(((EmpleadoDAO)empleadoDao).obtenerTipoEmpleado(tipoEmpleado));
		
		if(empleados.isEmpty())
			throw new NotFoundException("No existen empleados del tipo: "+tipoEmpleado);
		
		return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
	}
	
}
