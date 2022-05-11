package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;

public interface AulaDAO extends GenericoDAO<Aula>{
	
	public Iterable<Aula> findAulasByPizarron(Pizarron pizarron);
	
	public Iterable<Aula> findAulasByPabellonNombreIgnoreCase(String nombre);
	
	public Optional<Aula> findAulaByNumeroAula(Integer numeroAula);

}
