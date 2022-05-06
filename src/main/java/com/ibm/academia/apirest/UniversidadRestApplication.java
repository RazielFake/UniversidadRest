package com.ibm.academia.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Direccion;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;

@SpringBootApplication
public class UniversidadRestApplication {

	@Autowired
	private AlumnoDAO alumnoDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(UniversidadRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return args ->{
			Direccion direccion = new Direccion("Calle 25", "36", "123", "algo", "algo2", "CDMX");
			Persona alumno = new Alumno(null, "Mario", "Moreno", "85471AD", direccion);
			
			Persona personaGuardada = alumnoDAO.guardar(alumno);
			System.out.println(personaGuardada.toString());
		};
	}
	
}
