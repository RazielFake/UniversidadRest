package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;

public interface ProfesorDAO extends PersonaDAO{

	public Iterable<Persona> buscarProfesoresPorNombreCarrera(String carrera);
	
}
