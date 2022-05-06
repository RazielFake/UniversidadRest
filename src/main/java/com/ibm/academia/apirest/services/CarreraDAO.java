package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Carrera;

public interface CarreraDAO {

	public Optional<Carrera> buscarPorId(Integer id);
	public Carrera guardar(Carrera carrera);
	public Iterable<Carrera> buscarTodos();
	public void eliminarPorId(Integer id);
	
}
