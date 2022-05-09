package com.ibm.academia.apirest.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO{

	@Autowired
	public AlumnoDAOImpl(@Qualifier("repositorioAlumnos")PersonaRepository repository) {
		super(repository);
	}

	@Override
	public Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre) {
		return ((AlumnoRepository)this.repository).buscarAlumnoPorNombreCarrera(nombre);
	}

}
