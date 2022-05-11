package com.ibm.academia.apirest.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "profesores", schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Profesor extends Persona{

	@Column(name = "sueldo")
	private BigDecimal sueldo;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "profesor_carrera", schema = "universidad",
			joinColumns = @JoinColumn(name = "profesor_id"),
			inverseJoinColumns = @JoinColumn(name = "carrera_id")
	)
	private Set<Carrera> carreras;
	
	public Profesor(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo) {
		super(id, nombre, apellido, dni, direccion);
		this.sueldo = sueldo;
	}

	
	@Override
	public String toString() {
		return super.toString() + 
				"\nProfesor: "
				+ "\nSueldo = " + sueldo;
	}


	private static final long serialVersionUID = 6212454084535480124L;
}
