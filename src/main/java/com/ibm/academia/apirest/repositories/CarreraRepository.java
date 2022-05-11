package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.entities.Carrera;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Integer>{

	public Iterable<Carrera> findCarrerasByCantidadAnios(Integer cantidadAnios);
	
	
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre);
	
	//@Query("select c from Carrera c where upper(c.nombre) like upper(%?1%)")
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
	
	//@Query("select c from Carrera c where c.cantidadAnios > ?1")
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
	
	//@Query("select c from Carrera c join fetch c.profesores p where p.nombre like %?1%")
    @Query("select c from Carrera c join fetch c.profesores p where upper(p.nombre) like upper(?1) and upper(p.apellido) like upper(?2)")
	public Iterable<Carrera> buscarCarrerasPorProfesoresNombreYProfesoresApellido(String nombre, String apellido);
}
