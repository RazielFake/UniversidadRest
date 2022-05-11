package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.repositories.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO{

	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> findAulasByPizarron(Pizarron pizarron) {
		return this.repository.findAulasByPizarron(pizarron);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> findAulasByPabellonNombreIgnoreCase(String nombre) {
		return this.repository.findAulasByPabellonNombreIgnoreCase(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Aula> findAulaByNumeroAula(Integer numeroAula) {
		return this.repository.findAulaByNumeroAula(numeroAula);
	}
	
	

}