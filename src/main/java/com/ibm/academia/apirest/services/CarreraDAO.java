package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera>{
	
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre);
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios); 
	public Iterable<Carrera> buscarCarrerasPorProfesoresNombreYProfesoresApellido(String nombre, String apellido);
	public Carrera actualizar(Carrera carreraEncontrada, Carrera carrera);
	
}
