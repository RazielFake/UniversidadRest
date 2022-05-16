package com.ibm.academia.apirest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Pabellon;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Integer>{

	public Iterable<Pabellon> findPabellonesByDireccionLocalIgnoreCase(String local);
	
	public Iterable<Pabellon> findPabellonesByNombreIgnoreCase(String nombre);
	
}
