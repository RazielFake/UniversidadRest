package com.ibm.academia.apirest;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Direccion;
import com.ibm.academia.apirest.entities.Empleado;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.AulaDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PabellonDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;

@Component

public class Comandos implements CommandLineRunner{

	@Autowired
	private CarreraDAO carreraDAO;
	
	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO personaDAO;
	
	@Autowired
	private ProfesorDAO profesorDAO;
	
	@Autowired
	private EmpleadoDAO empleadoDAO;
	
	@Autowired
	private AulaDAO aulaDAO;
	
	@Autowired
	private PabellonDAO pabellonDAO;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		 * Carrera finanzas = new Carrera(null, "Ingenieria en finanzas", 25, 3);
		 * Carrera carreraGuardada = carreraDAO.guardar(finanzas);
		 * System.out.println(carreraGuardada.toString());
		 */
		
		/*Carrera carrera = null;
		
		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(3);
		if(oCarrera.isPresent()) {
			carrera = oCarrera.get();
			System.out.println(carrera.toString());
		}else {
			System.out.println("Carrera no encontrada");
		} 
		
		carrera.setCantidadAnios(7);
		carrera.setCantidadMaterias(66);
		carreraDAO.guardar(carrera);
		
		System.out.println(carreraDAO.buscarPorId(7).orElse(new Carrera()).toString());*/
		//AÑADIR CARRERAS
		/*Carrera ingAlimentos = new Carrera(null, "Ingenieria en alimentos", 53, 5);
		Carrera ingElectronica = new Carrera(null, "Ingenieria en electronica", 45, 5);
		Carrera licSistemas = new Carrera(null, "Licenciatura en sistemas", 40, 4);
		Carrera licTurismo = new Carrera(null, "Licenciatura en turismo", 25, 3);
		Carrera licRecursos = new Carrera(null, "Licenciatura en recursos humanos", 35, 3);
		
		carreraDAO.guardar(ingAlimentos);
		carreraDAO.guardar(ingElectronica);
		carreraDAO.guardar(licSistemas);
		carreraDAO.guardar(licTurismo);
		carreraDAO.guardar(licRecursos);*/
		
		
		/*Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);
		
		Iterable<Persona> alumnos = personaDAO.buscarTodos();
		alumnos.forEach(alumno -> ((Alumno)alumno).setCarrera(ingSistemas.get()));
		alumnos.forEach(alumno -> personaDAO.guardar(alumno));*/
		
		/*
		Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);
		Iterable<Persona> alumnosCarrera = ((AlumnoDAO) personaDAO).buscarAlumnoPorNombreCarrera(ingSistemas.get().getNombre());
		alumnosCarrera.forEach(System.out::println);*/
		
		/*List<Carrera> carreras = (List<Carrera>) carreraDAO.findCarrerasByNombreContains("sistemas");
		carreras.forEach(System.out::println);*/
		
		/*List<Carrera> carrerasIgnoreCase1 = (List<Carrera>) carreraDAO.findCarrerasByNombreContainsIgnoreCase("SISTEMAS");
		carrerasIgnoreCase1.forEach(System.out::println);
		
		List<Carrera> carrerasPorAnio = (List<Carrera>) carreraDAO.findCarrerasByCantidadAniosAfter(4);
		carrerasPorAnio.forEach(System.out::println);*/
		
		/*Optional<Persona> persona = personaDAO.buscarPorId(15);
		System.out.println(persona.toString());*/
		
		
		
		
		//AÑADIR PROFESORES
		/*
		Direccion direccion1 = new Direccion("Calle 88", "56", "321", "Algo", "Colonia", "CDMX");
		Persona profesorMatematicas = new Profesor(null, "Salvador", "Montiel", "SM8978", direccion1, BigDecimal.valueOf(5314.58));
		
		Direccion direccion2 = new Direccion("Calle 13", "46", "351", "AlgoMas", "Colonia2", "CDMX");
		Persona profesorProgra = new Profesor(null, "Olga", "Kolesnikova", "OK7536", direccion1, BigDecimal.valueOf(4314.58));
		
		Persona personaGuardada = profesorDAO.guardar(profesorMatematicas);
		Persona personaGuardada2 = profesorDAO.guardar(profesorProgra);
		*/
		//-----------------------------------------------------------------
		
		//AÑADID CARRERAS A PROFESORES
		/*
		Set<Carrera> carreras = new HashSet<Carrera>();
		Optional<Carrera> carreraSistemas = carreraDAO.buscarPorId(1);
		Carrera ingSistemas = carreraSistemas.get();
		Optional<Carrera> carreraElectronica = carreraDAO.buscarPorId(5);
		Carrera electronica = carreraElectronica.get();
		carreras.add(ingSistemas);
		carreras.add(electronica);
		Optional<Persona> personaProfesor = profesorDAO.buscarPorId(23);
		Profesor profesor = (Profesor)personaProfesor.get();
		profesor.setCarreras(carreras);
		Persona profesorGuardar = profesorDAO.guardar(profesor);
		System.out.println(profesorGuardar.toString());
		
		Set<Carrera> carreras = new HashSet<Carrera>();
		Optional<Carrera> carreraElectronica = carreraDAO.buscarPorId(4);
		Carrera ingElectronica = carreraElectronica.get();
		Optional<Carrera> carreraTurismo = carreraDAO.buscarPorId(6);
		Carrera turismo = carreraTurismo.get();
		carreras.add(ingElectronica);
		carreras.add(turismo);
		Optional<Persona> personaProfesor = profesorDAO.buscarPorId(22);
		Profesor profesor = (Profesor)personaProfesor.get();
		profesor.setCarreras(carreras);
		Persona profesorGuardar = profesorDAO.guardar(profesor);
		System.out.println(profesorGuardar.toString());
		*/
		
		//CONSULTAS PROFESOR
		//CONSULTAR PROFESORES POR NOMBRE DE CARRERA
		/*
		Optional<Carrera> sistemas = carreraDAO.buscarPorId(1);
		Carrera carreraSistemas = sistemas.get();
		List<Persona> profesores = (List<Persona>)profesorDAO.buscarProfesoresPorNombreCarrera(carreraSistemas.getNombre());
		profesores.forEach(System.out::println);
		*/
		//-----------------------------------------------------------------
		
		//AÑADIR EMPLEADOS
		/*
		Direccion direccion1 = new Direccion("Calle 001", "001", "100", "Departamento verde", "1", "CDMX");
		Persona mantenimiento1 = new Empleado(null, "Kevin", "Patiplus", "kp123", direccion1, BigDecimal.valueOf(3220), TipoEmpleado.MANTENIMIENTO);
		Persona personaSave1 = empleadoDAO.guardar(mantenimiento1);
		
		Direccion direccion2 = new Direccion("Calle 002", "002", "200", "Maceta", "2", "Desertica");
		Persona mantenimiento2 = new Empleado(null, "Lisa", "Cactus", "lc123", direccion2, BigDecimal.valueOf(3210), TipoEmpleado.MANTENIMIENTO);
		Persona personaSave2 = empleadoDAO.guardar(mantenimiento2);
		
		Direccion direccion3 = new Direccion("Calle 003", "003", "300", "Roca", "3", "Fondo de Bikini");
		Persona administrativo1 = new Empleado(null, "Patricio", "Estrella", "pe1234", direccion3, BigDecimal.valueOf(4210), TipoEmpleado.ADMINISTRATIVO);
		Persona personaSave3 = empleadoDAO.guardar(administrativo1);
		
		Direccion direccion4 = new Direccion("Calle 005", "005", "500", "Tienda", "5", "Muy lejano");
		Persona administrativo2 = new Empleado(null, "Alexander", "Almeja", "aa1234", direccion4, BigDecimal.valueOf(4220), TipoEmpleado.ADMINISTRATIVO);
		Persona personaSave4 = empleadoDAO.guardar(administrativo2);
		*/
		
		
		//Optional<Carrera> sistemas = carreraDAO.buscarPorId(1);
		//Carrera carreraSistemas = sistemas.get();
		
		//CONSULTAR EMPLEADOS POR EL TIPO DE EMPLEADO
		//List<Persona> empleadosMantenimiento = (List<Persona>)empleadoDAO.buscarEmpleadoPorTipoEmpleado(TipoEmpleado.MANTENIMIENTO);
		//empleadosMantenimiento.forEach(System.out::println);
		
		//List<Persona> empleadosAdministrativo = (List<Persona>)empleadoDAO.buscarEmpleadoPorTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
		//empleadosAdministrativo.forEach(System.out::println);
		

		//-----------------------------------------------------------------

		//BUSCAR CARRERAS POR NOMBRE Y APELLIDO DE PROFESOR
		/*
		Optional<Persona> personaProfesor = profesorDAO.buscarPorId(22);
		Profesor profesor = (Profesor)personaProfesor.get();
		List<Carrera> carreras = (List<Carrera>)carreraDAO.buscarCarrerasPorProfesoresNombreYProfesoresApellido(profesor.getNombre(), profesor.getApellido());
		carreras.forEach(System.out::println);
		*/
		//-----------------------------------------------------------------

		//AÑADIR AULAS
		/*
		Aula aula01 = new Aula(null, 1, "10x10", 35, Pizarron.PIZARRA_BLANCA);
		Aula aula02 = new Aula(null, 2, "10x10", 35, Pizarron.PIZARRA_BLANCA);
		Aula aula03 = new Aula(null, 3, "10x10", 35, Pizarron.PIZARRA_BLANCA);
		
		Aula aula04 = new Aula(null, 4, "20x20", 40, Pizarron.PIZARRA_TIZA);
		Aula aula05 = new Aula(null, 5, "20x20", 40, Pizarron.PIZARRA_TIZA);
		Aula aula06 = new Aula(null, 6, "20x20", 40, Pizarron.PIZARRA_TIZA);
		
		Aula aula07 = new Aula(null, 7, "30x30", 45, Pizarron.PIZARRA_TIZA);
		Aula aula08 = new Aula(null, 8, "30x30", 45, Pizarron.PIZARRA_BLANCA);
		Aula aula09 = new Aula(null, 9, "30x30", 45, Pizarron.PIZARRA_BLANCA);
		
		List<Aula> aulas = Arrays.asList(aula01, aula02, aula03, aula04, aula05, aula06, aula07, aula08, aula09);
		aulas.forEach(aula -> aulaDAO.guardar(aula));
		aulas.forEach(System.out::println);
		*/
		
		//AÑADIR PABELLONES
		/*
		Set<Aula> aulasSet = new HashSet<Aula>();
		Direccion direccion1 = new Direccion("Calle 100", "100", "101", "Central", "1", "CDMX");
		Pabellon pabellon01 = new Pabellon(null, Double.valueOf(400), "Pabellon Centro", direccion1);
		aulasSet.addAll(Arrays.asList((Aula)aulaDAO.findAulaByNumeroAula(1).get(),
				(Aula)aulaDAO.findAulaByNumeroAula(2).get(), (Aula)aulaDAO.findAulaByNumeroAula(3).get()
				));
		pabellon01.setAulas(aulasSet);
		pabellonDAO.guardar(pabellon01);
	
		aulasSet.clear();
		Direccion direccion2 = new Direccion("Calle 200", "200", "202", "Norte", "1", "CDMX");
		Pabellon pabellon02 = new Pabellon(null, Double.valueOf(600), "Pabellon Norte", direccion2);
		aulasSet.addAll(Arrays.asList((Aula)aulaDAO.findAulaByNumeroAula(4).get(),
				(Aula)aulaDAO.findAulaByNumeroAula(5).get(), (Aula)aulaDAO.findAulaByNumeroAula(6).get()
				));
		pabellon02.setAulas(aulasSet);
		pabellonDAO.guardar(pabellon02);
		aulasSet.clear();
		
		Direccion direccion3 = new Direccion("Calle 300", "300", "303", "Oriente", "1", "CDMX");
		Pabellon pabellon03 = new Pabellon(null, Double.valueOf(700), "Pabellon Oriente", direccion3);
		aulasSet.addAll(Arrays.asList((Aula)aulaDAO.findAulaByNumeroAula(7).get(),
				(Aula)aulaDAO.findAulaByNumeroAula(8).get(), (Aula)aulaDAO.findAulaByNumeroAula(9).get()
				));
		pabellon03.setAulas(aulasSet);
		pabellonDAO.guardar(pabellon03);

		*/
		
		
		//ASIGNAR PABELLON A AULAS
		/*
		List<Aula> aulas = (List<Aula>)Arrays.asList(aulaDAO.findAulaByNumeroAula(1).get(),
				aulaDAO.findAulaByNumeroAula(2).get(), aulaDAO.findAulaByNumeroAula(3).get()
				);
		aulas.forEach(aula -> aula.setPabellon(pabellonDAO.buscarPorId(3).get()));
		aulas.forEach(aula -> aulaDAO.guardar(aula));
		/*
		
		//COMANDOS DE AULA
		//BUSCAR POR TIPO DE PIZARRON
		/*
		List<Aula> aulasPizarron = (List<Aula>)aulaDAO.findAulasByPizarron(Pizarron.PIZARRA_BLANCA);
		aulasPizarron.forEach(System.out::println);
		*/
		//BUSCAR POR NOMBRE DE PABELLON//<----------------?????????????
		/*
		List<Aula> aulasNombrePabellon = (List<Aula>)aulaDAO.findAulasByPabellonNombreIgnoreCase(pabellonDAO.buscarPorId(1).get().getNombre());
		aulasNombrePabellon.forEach(System.out::println);
		*/
		
		//BUSCAR POR NUMERO DE AULA
		/*
		Optional<Aula> aula = aulaDAO.findAulaByNumeroAula(3);
		System.out.println(aula.toString());
		*/
		
		
		
		//COMANDOS DE PABELLON
		//BUSCAR PABELLONES POR NOMBRE DE LOCAL
		//List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.findPabellonesByDireccionLocalIgnoreCase("cdmx");
		//pabellones.forEach(System.out::println);
		
		//BUSCAR PABELLONES POR POR NOMBRE 
		//List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.findPabellonesByNombreIgnoreCase("pabellon centro");
		//pabellones.forEach(System.out::println);
		
		
		
		
	}

}
