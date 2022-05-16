package com.ibm.academia.apirest.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Integer>{
	
	public Iterable<Aula> findAulasByPizarron(Pizarron pizarron);
	
	public Iterable<Aula> findAulasByPabellonNombreIgnoreCase(String nombre);
	
	public Optional<Aula> findAulaByNumeroAula(Integer numeroAula);

}
