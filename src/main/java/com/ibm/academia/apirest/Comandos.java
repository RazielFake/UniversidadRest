package com.ibm.academia.apirest;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@Component
public class Comandos implements CommandLineRunner{

	@Autowired
	private CarreraDAO carreraDAO;
	
	@Autowired
	private PersonaDAO personaDAO;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		 * Carrera finanzas = new Carrera(null, "Ingenieria en finanzas", 25, 3);
		 * Carrera carreraGuardada = carreraDAO.guardar(finanzas);
		 * System.out.println(carreraGuardada.toString());
		 */
		
		/*Carrera carrera = null;
		
		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(3);
		if(oCarrera.isPresent()) {
			carrera = oCarrera.get();
			System.out.println(carrera.toString());
		}else {
			System.out.println("Carrera no encontrada");
		} 
		
		carrera.setCantidadAnios(7);
		carrera.setCantidadMaterias(66);
		carreraDAO.guardar(carrera);
		
		System.out.println(carreraDAO.buscarPorId(7).orElse(new Carrera()).toString());*/
		
		/*Carrera ingAlimentos = new Carrera(null, "Ingenieria en alimentos", 53, 5);
		Carrera ingElectronica = new Carrera(null, "Ingenieria en electronica", 45, 5);
		Carrera licSistemas = new Carrera(null, "Licenciatura en sistemas", 40, 4);
		Carrera licTurismo = new Carrera(null, "Licenciatura en turismo", 25, 3);
		Carrera licRecursos = new Carrera(null, "Licenciatura en recursos humanos", 35, 3);
		
		carreraDAO.guardar(ingAlimentos);
		carreraDAO.guardar(ingElectronica);
		carreraDAO.guardar(licSistemas);
		carreraDAO.guardar(licTurismo);
		carreraDAO.guardar(licRecursos);*/
		
		
		/*Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);
		
		Iterable<Persona> alumnos = personaDAO.buscarTodos();
		alumnos.forEach(alumno -> ((Alumno)alumno).setCarrera(ingSistemas.get()));
		alumnos.forEach(alumno -> personaDAO.guardar(alumno));*/
		
		/*
		Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);
		Iterable<Persona> alumnosCarrera = ((AlumnoDAO) personaDAO).buscarAlumnoPorNombreCarrera(ingSistemas.get().getNombre());
		alumnosCarrera.forEach(System.out::println);*/
		
		/*List<Carrera> carreras = (List<Carrera>) carreraDAO.findCarrerasByNombreContains("sistemas");
		carreras.forEach(System.out::println);*/
		
		/*List<Carrera> carrerasIgnoreCase1 = (List<Carrera>) carreraDAO.findCarrerasByNombreContainsIgnoreCase("SISTEMAS");
		carrerasIgnoreCase1.forEach(System.out::println);
		
		List<Carrera> carrerasPorAnio = (List<Carrera>) carreraDAO.findCarrerasByCantidadAniosAfter(4);
		carrerasPorAnio.forEach(System.out::println);*/
		
		Optional<Persona> persona = personaDAO.buscarPorId(15);
		System.out.println(persona.toString());
		
		//Consultas repositorio profesor
		
		
		
				
				
	}

}
