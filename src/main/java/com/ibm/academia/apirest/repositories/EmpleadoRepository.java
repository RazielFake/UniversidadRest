package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;

@Repository("repositorioEmpleados")
public interface EmpleadoRepository extends PersonaRepository{
	
	//@Query("select e from Empleado e where e.tipoEmpleado like %?1%")
	@Query("select e from Empleado e where e.tipoEmpleado = ?1")
	public Iterable<Persona> buscarEmpleadoPorTipoEmpleado(TipoEmpleado tipoEmpleado);

}
