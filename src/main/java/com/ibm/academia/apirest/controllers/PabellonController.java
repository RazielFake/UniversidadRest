package com.ibm.academia.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.PabellonDAO;

@RestController
@RequestMapping("/pabellon")
public class PabellonController {

	@Autowired
	private PabellonDAO pabellonDao;
	
	/**
	 * Endpoint para buscar pabellones dentro de un mismo local
	 * @param local Recibe el local por el cual se requiere buscar los pabellones
	 * @NotFoundException En caso de que no se encuentren pabellones
	 * @return Lista de pabellones encontrados
	 * @author BRPI
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
	 * @NotFoundException En caso de que no se encuentren pabellones
	 * @return Lista de pabellones encontrados
	 * @author BRPI
	 */
	@GetMapping("/pabellones/nombre/{nombre}")
	public List<Pabellon> buscarPabellonesPorNombre(@PathVariable String nombre){
		List<Pabellon> pabellones = (List<Pabellon>)pabellonDao.findPabellonesByNombreIgnoreCase(nombre);
		
		if(pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones con el nombre: "+nombre);
		
		return pabellones;
	}
	
	
	
}
