package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera>{
	
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre);
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios); 
	
}
