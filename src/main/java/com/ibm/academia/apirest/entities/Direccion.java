package com.ibm.academia.apirest.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable{

	private String calle;
	private String mumero;
	private String codigoPostal;
	private String departamento;
	private String piso;
	private String local;
	
	
	
	public Direccion(String calle, String mumero, String codigoPostal, String departamento, String piso, String local) {
		this.calle = calle;
		this.mumero = mumero;
		this.codigoPostal = codigoPostal;
		this.departamento = departamento;
		this.piso = piso;
		this.local = local;
	}

	

	private static final long serialVersionUID = 8500313989796186076L;

}
