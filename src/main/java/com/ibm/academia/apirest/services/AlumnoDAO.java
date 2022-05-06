package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Persona;


public interface AlumnoDAO {
	
	public Optional<Persona> buscarPorId(Integer id);
	public Persona guardar(Persona persona);
	public Iterable<Persona> buscarTodos();
	public void eliminarPorId(Integer id);
	
}
