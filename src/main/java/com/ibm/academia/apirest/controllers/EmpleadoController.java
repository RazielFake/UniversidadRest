package com.ibm.academia.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	
	
	@Autowired
	@Qualifier("empleadoDAOImpl")
	private PersonaDAO empleadoDao;
	
	/**
	 * Endpoint que busca un empleado por su tipo
	 * @param tipoEmpleado Requiere el tipo de empleado que se quiere buscar
	 * @NotFoundException En caso de que falle el proceso
	 * @return Un objeto Persona de tipo empleado
	 * @author BRPI
	 */
	@GetMapping("/tipo/{tipoEmpleado}")
	public ResponseEntity<?> obtenerEmpleadoPorTipo(@PathVariable TipoEmpleado tipoEmpleado){
		List<Persona> empleados = (List<Persona>)((EmpleadoDAO)empleadoDao).buscarEmpleadoPorTipoEmpleado(tipoEmpleado);
		
		if(empleados.isEmpty())
			throw new NotFoundException("No existen empleados del tipo: "+tipoEmpleado);
		
		return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
	}
	
}
