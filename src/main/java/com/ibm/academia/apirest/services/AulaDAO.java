package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;

public interface AulaDAO extends GenericoDAO<Aula>{
	
	public Iterable<Aula> findAulasByPizarron(Pizarron pizarron);
	
	public Iterable<Aula> findAulasByPabellonNombreIgnoreCase(String nombre);
	
	public Optional<Aula> findAulaByNumeroAula(Integer numeroAula);
	
	public Pizarron obtenerTipoPizarron(String pizarron);
	
	public Aula actualizar(Aula aulaEncontrada, Aula aula);

}
