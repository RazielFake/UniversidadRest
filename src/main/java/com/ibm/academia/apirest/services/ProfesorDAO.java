package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;

public interface ProfesorDAO extends PersonaDAO{

	public Iterable<Persona> buscarProfesoresPorNombreCarrera(String carrera);
	
	public Persona actualizar(Persona profesorEncontrado, Persona profesor);
	
}
