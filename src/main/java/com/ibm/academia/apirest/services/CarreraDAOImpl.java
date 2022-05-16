package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;

@Service
public class CarreraDAOImpl extends GenericoDAOImpl<Carrera, CarreraRepository> implements CarreraDAO{

	@Autowired
	public CarreraDAOImpl(CarreraRepository repository) {
		super(repository);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre) {
		return this.repository.findCarrerasByNombreContains(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre) {
		
		return this.repository.findCarrerasByNombreContainsIgnoreCase(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios) {
		return this.repository.findCarrerasByCantidadAniosAfter(cantidadAnios);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> buscarCarrerasPorProfesoresNombreYProfesoresApellido(String nombre, String apellido) {
		return this.repository.buscarCarrerasPorProfesoresNombreYProfesoresApellido(nombre, apellido);
	}

	@Override
	@Transactional
	public Carrera actualizar(Carrera carreraEncontrada, Carrera carrera) {
		Carrera carreraActualizada = null;
		carreraEncontrada.setCantidadAnios(carrera.getCantidadAnios());
		carreraEncontrada.setCantidadMaterias(carrera.getCantidadMaterias());
		carreraActualizada = this.repository.save(carreraEncontrada);
		return carreraActualizada;
	}


}
