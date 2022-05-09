package com.ibm.academia.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversidadRestApplication {

	/*@Autowired
	private AlumnoDAO alumnoDAO;*/
	
	public static void main(String[] args) {
		SpringApplication.run(UniversidadRestApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner runner() {
		return args ->{
			Direccion direccion = new Direccion("Calle 77", "48", "321", "13", "algo2", "CDMX");
			Persona alumno = new Alumno(null, "George", "Orwell", "4314BD", direccion);
			
			Persona personaGuardada = alumnoDAO.guardar(alumno);
			System.out.println(personaGuardada.toString());*/
			
			/*List<Persona> alumnos = (List<Persona>) alumnoDAO.buscarTodos();
			alumnos.forEach(System.out::println);
		};
	}*/
	
}
