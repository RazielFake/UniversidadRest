package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.services.CarreraDAO;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
	
	@Autowired
	private CarreraDAO carreraDao;
	
	@GetMapping("/lista/carreras")
	public List<Carrera> buscarTodas(){
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
		if(carreras.isEmpty()) {
			throw new BadRequestException("No existen carreras");
		}
		return carreras;
	}
	
	@GetMapping("/id/{carreraId}")
	public Carrera buscarCarreraPorId(@PathVariable Integer carreraId) {
		Carrera carrera = carreraDao.buscarPorId(carreraId).orElse(null);
		if(carrera == null)
			throw new BadRequestException(String.format(
					"La carrera con ID: %d no existe"
					, carreraId));
		return carrera;
	}
	
	@PostMapping
	public ResponseEntity<?> guararCarrera(@RequestBody Carrera carrera){
		/*
		if(carrera.getCantidadAnios() < 0)
			throw new BadRequestException("El campo cantidad de años debe ser mayor a 0");
		if(carrera.getCantidadMaterias() < 0)
			throw new BadRequestException("El campo cantidad de materias debe ser mayor a 0");
		*/
		Carrera carreraGuardada = carreraDao.guardar(carrera);
		return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
	}
	
	

}
