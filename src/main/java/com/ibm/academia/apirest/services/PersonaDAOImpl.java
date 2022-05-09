package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;

public class PersonaDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements PersonaDAO{

	public PersonaDAOImpl(PersonaRepository repository) {
		super(repository);
	}

	@Override
	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
		return this.repository.buscarPorNombreYApellido(nombre, apellido);
	}

	@Override
	public Optional<Persona> buscarPorDni(String dni) {
		return this.repository.buscarPorDni(dni);
	}

	@Override
	public Iterable<Persona> buscarPersonaPorApellido(String apellido) {
		return this.repository.buscarPersonaPorApellido(apellido);
	}

}
