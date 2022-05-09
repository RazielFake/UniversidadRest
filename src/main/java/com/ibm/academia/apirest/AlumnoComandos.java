package com.ibm.academia.apirest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@Component
public class AlumnoComandos implements CommandLineRunner{

	@Autowired
	private CarreraDAO carreraDao;
	
	@Autowired AlumnoDAO alumnoDao;
	
	@Autowired
	@Qualifier("alumnoDAOImpl")//clases padre cuando utilizaremos sus clases hija
	private PersonaDAO personaDao;
	
	@Override
	public void run(String... args) throws Exception {
		/*Optional<Carrera> sistemas = carreraDao.buscarPorId(1);
		
		Iterable<Persona> alumnos = personaDao.buscarTodos();
		alumnos.forEach(alumno -> ((Alumno)alumno).setCarrera(sistemas.get()));
		alumnos.forEach(alumno -> personaDao.guardar(alumno));*/
		
		Optional<Persona> alumno = alumnoDao.buscarPorId(15);
		
		/*Optional<Persona> personaDni = personaDao.buscarPorDni(alumno.get().getDni());
		System.out.println(personaDni.toString());*/
		
		Iterable<Persona> personasApellido = personaDao.buscarPersonaPorApellido(alumno.get().getApellido());
		personasApellido.forEach(System.out::println);
		
		
	}

}
