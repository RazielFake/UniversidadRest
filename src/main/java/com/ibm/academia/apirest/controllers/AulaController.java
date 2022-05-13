package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.AulaDAO;

@RestController
@RequestMapping("/aula")
public class AulaController {

	@Autowired
	private AulaDAO aulaDao;
	
	/**
	 * Endpoint para encontrar las aulas con un tipo de pizarron especifico
	 * @param tipoPizarron El tipo de pizarron que las aulas tienen
	 * @NotFoundException
	 * @return Lista con las aulas correspondientes
	 * @author BRPI
	 */
	@GetMapping("aulas/pizarron/{tipoPizarron}")
	public List<Aula> buscarPorTipoPizarron(@PathVariable Pizarron tipoPizarron){
		List<Aula> aulas = (List<Aula>) aulaDao.findAulasByPizarron(tipoPizarron);
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
