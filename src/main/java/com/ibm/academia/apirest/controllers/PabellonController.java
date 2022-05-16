package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;

@RestController
@RequestMapping("/pabellon")
public class PabellonController {

	@Autowired
	private PabellonDAO pabellonDao;
	
	/**
	 * Endpoint para crear y guardar un nuevo pabellon
	 * @param pabellon Informacion necesaria para crear el objeto de tipo Pabellon y guardarlo
	 * @param result
	 * @return Retorna un objeto Carrera con codigo httpstatus 
	 * @author BRPI 14/05/22
	 */
	@PostMapping
	public ResponseEntity<?> guardarPabello(@Valid @RequestBody Pabellon pabellon, BindingResult result){
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: " + errores.getField() + " - " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		Pabellon pabellonGuardado = pabellonDao.guardar(pabellon);
		return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para listar los pabellones existentes
	 * @NotFoundException En caso de que no existan carreras en la base de datos
	 * @return Lista de pabellones
	 * @author BRPI 14/05/22
	 */
	@GetMapping("/pabellones/lista")
	public ResponseEntity<?> obtenerTodos(){
		List<Pabellon> pabellones = (List<Pabellon>)pabellonDao.buscarTodos();
		if(pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones");
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}
	
	
	
	/**
	 * Endpoint para borrar un pabellon
	 * @param pabellonId Recibe el id del pabellon que se quiere eliminar
	 * @NotFoundException En caso de que falle y no encuentre el pabellon solicitado
	 * @return @return Confirmacion de que el pabellon ha sido eliminado con codigo httpstatus
	 * @author BRPI 12/05/22
	 */
	@DeleteMapping("/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer pabellonId){
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format(
					"Pabellon con ID: %d no encontrado.", pabellonId));
		
		pabellonDao.eliminarPorId(oPabellon.get().getId());
		
		return new ResponseEntity<String>("Pabellon ID: " + pabellonId + " eliminada exitosamente.", HttpStatus.NO_CONTENT);
	}
	
	
	/**
	 * Endpoint para buscar pabellones dentro de un mismo local
	 * @param local Recibe el local por el cual se requiere buscar los pabellones
	 * @NotFoundException En caso de que falle y no se encuentren pabellones dentro del local solicitado
	 * @return Lista de pabellones encontrados pertenecientes al local solicitado
	 * @author BRPI 14/05/22
	 */
	@GetMapping("/pabellones/local/{local}")
	public List<Pabellon> buscarPabellonesPorLocal(@PathVariable String local){
		List<Pabellon> pabellones = (List<Pabellon>)pabellonDao.findPabellonesByDireccionLocalIgnoreCase(local);
		
		if(pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones de dentro de: "+local);
		
		return pabellones;
	}
	
	/**
	 * Endpoint para buscar pabellones por nombre
	 * @param nombre Recibe el nombre del pabellon que se requiere buscar
	 * @NotFoundException En caso de que falle y no se encuentren pabellones con el nombre solicitado
	 * @return Lista de pabellones
	 * @author BRPI 14/05/22
	 */
	@GetMapping("/pabellones/nombre/{nombre}")
	public List<Pabellon> buscarPabellonesPorNombre(@PathVariable String nombre){
		List<Pabellon> pabellones = (List<Pabellon>)pabellonDao.findPabellonesByNombreIgnoreCase(nombre);
		
		if(pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones con el nombre: "+nombre);
		
		return pabellones;
	}
	
	
	
}
