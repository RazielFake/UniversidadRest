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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.AulaDAO;

@RestController
@RequestMapping("/aula")
public class AulaController {

	@Autowired
	private AulaDAO aulaDao;
	
	
	/**
	 * Endpoint para crear una nueva Aula
	 * @param aula La informacion del aula que sera creada
	 * @param result 
	 * @return Objeto tipo aula con mensaje httpstatus
	 * @author BRPI 14/05/22
	 */
	@PostMapping
	public ResponseEntity<?> guardarAula(@Valid @RequestBody Aula aula, BindingResult result){
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: " + errores.getField() + " - " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		Aula aulaGuardada = aulaDao.guardar(aula);
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}
	
	
	/**
	 * Endpoint para listar las aulas existentes
	 * @return lista con aulas
	 * @NotFoundException en caso de que falle durante el proceso
	 * @author BRPI 14/05/22
	 */
	@GetMapping("/aulas/lista")
	public ResponseEntity<?> obtenerTodos(){
		List<Aula> aulas = (List<Aula>)aulaDao.buscarTodos();
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas");
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para actualizar la informacion de un Aula
	 * @param aulaId El id del aula que se quiere actualizar
	 * @param aula La informacion que se quiere actualizar
	 * @NotFoundException en caso de que falle durante el proceso
	 * @return Objeto aula actualizado
	 * @author BRPI 14/05/22
	 */
	@PutMapping("/upd/aulaId/{aulaId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer aulaId, @RequestBody Aula aula){
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format(
					"Aula con ID: %d no encontrada.", aulaId));
		
		Aula aulaActualizada = ((AulaDAO)aulaDao).actualizar(oAula.get(), aula);
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
	
	/**
	 * Endponint para eliminar un objeto de tipo Aula
	 * @param aulaId id del aula que se quiere eliminar
	 * @return
	 */
	@DeleteMapping("/aulaId/{aulaId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer aulaId){
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format(
					"Aula con ID: %d no encontrada.", aulaId));
		
		aulaDao.eliminarPorId(oAula.get().getId());
		
		return new ResponseEntity<String>("Aula ID: " + aulaId + " eliminada exitosamente.", HttpStatus.NO_CONTENT);
	}
	
	
	
	/**
	 * Endpoint para encontrar las aulas con un tipo de pizarron especifico
	 * @param tipoPizarron El tipo de pizarron que las aulas tienen
	 * @NotFoundException
	 * @return Lista con las aulas correspondientes
	 * @author BRPI
	 */
	@GetMapping("aulas/pizarron/{tipoPizarron}")
	public List<Aula> buscarPorTipoPizarron(@PathVariable String tipoPizarron){
		List<Aula> aulas = (List<Aula>) aulaDao.findAulasByPizarron(aulaDao.obtenerTipoPizarron(tipoPizarron));
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas del tipo: "+tipoPizarron);
		
		return aulas;
	}
	
	/**
	 * Endpoint para encontrar las aulas pertenecientes a un pabellon
	 * @param pabellon Recibe el nombre del pabellon 
	 * @NotFoundException en caso de que no encuentre aulas pertenecientes al pabellon
	 * @return Lista con aulas pertenecientes al pabellon requerido
	 * @author BRPI
	 */
	@GetMapping("aulas/pabellon/{pabellon}")
	public List<Aula> buscarPorNombrePabellon(@PathVariable String pabellon){
		List<Aula> aulas = (List<Aula>) aulaDao.findAulasByPabellonNombreIgnoreCase(pabellon);
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas pertenecientes a: "+pabellon);
		
		return aulas;
	}
	
	
	/**
	 * Endpoint para buscar un aula por su numero
	 * @param numero Recibe el numero del aula que se quiere buscar
	 * @NotFoundException En caso de que no encuentre el aula requerida
	 * @return Un objeto aula
	 * @author BRPI
	 */
	@GetMapping("numero/{numero}")
	public ResponseEntity<?> buscarPorNumeroAula(@PathVariable Integer numero){
		Optional<Aula> oAula = aulaDao.findAulaByNumeroAula(numero);
		if(!oAula.isPresent())
			throw new NotFoundException("No existen aulas con el numero: "+numero);
		
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}
	
}
