package com.mitocode.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Embeddable//habilita que la clase sea referenciada desde consulta examen
public class ConsultaExamenPK implements Serializable{
	@ManyToOne
	@JoinColumn(name = "idExamen",nullable = false)
	private Examen examen;
	
	@ManyToOne
	@JoinColumn(name = "idConsulta",nullable = false)
	private Consulta consulta;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consulta == null) ? 0 : consulta.hashCode());
		result = prime * result + ((examen == null) ? 0 : examen.hashCode());
		return result;
	}
//con esto ya puedo habilitar la comparacioon por objetos
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaExamenPK other = (ConsultaExamenPK) obj;
		if (consulta == null) {
			if (other.consulta != null)
				return false;
		} else if (!consulta.equals(other.consulta))
			return false;
		if (examen == null) {
			if (other.examen != null)
				return false;
		} else if (!examen.equals(other.examen))
			return false;
		return true;
	}
	
	//investiga equal() hashcode() :,v
	
//ObjA.edad = 28
	//ObjB.edad = 28
	//ObjA == ObjB | true | false --> esto compra referencia a memoria no valor dentro del objeto   y esto seria true
	
}
