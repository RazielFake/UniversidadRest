package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;

@Service
public class CarreraDAOImpl implements CarreraDAO{

	@Autowired
	private CarreraRepository carreraRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Carrera> buscarPorId(Integer id) {
		return carreraRepository.findById(id);
	}

	@Override
	@Transactional
	public Carrera guardar(Carrera carrera) {
		return carreraRepository.save(carrera);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> buscarTodos() {
		return carreraRepository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Integer id) {
		carreraRepository.deleteById(id);
	}

}
