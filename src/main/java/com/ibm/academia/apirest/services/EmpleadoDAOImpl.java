package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO{

	@Autowired
	public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados") PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> buscarEmpleadoPorTipoEmpleado(TipoEmpleado tipoEmpleado) {
		return ((EmpleadoRepository)this.repository).buscarEmpleadoPorTipoEmpleado(tipoEmpleado);
	}

	@Override
	public Persona actualizar(Persona empleadoEncontrado, Persona empleado) {
		Persona empleadoActualizado = null;
		empleadoEncontrado.setNombre(empleado.getNombre());
		empleadoEncontrado.setApellido(empleado.getApellido());
		empleadoActualizado = this.repository.save(empleadoEncontrado);
		return empleadoActualizado;
	}

	@Override
	public TipoEmpleado obtenerTipoEmpleado(String empleado) {
		if(empleado.equalsIgnoreCase("ADMINISTRATIVO"))
			return TipoEmpleado.ADMINISTRATIVO;
		else if(empleado.equalsIgnoreCase("MANTENIMIENTO"))
			return TipoEmpleado.MANTENIMIENTO;
		else return null;
	}
	
	

}
