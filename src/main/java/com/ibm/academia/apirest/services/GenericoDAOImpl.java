package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public class GenericoDAOImpl <E , R extends CrudRepository<E, Integer>> implements GenericoDAO<E>{

	protected final R repository;
	
	public GenericoDAOImpl(R repository){
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<E> buscarPorId(Integer id) {
		return this.repository.findById(id);
	}

	@Override
	@Transactional
	public E guardar(E entidad) {
		return this.repository.save(entidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> buscarTodos() {
		return this.repository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Integer id) {
		this.repository.deleteById(id);
	}
	
}
