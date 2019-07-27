package com.mitocode.dto;

import java.util.List;

import com.mitocode.model.Consulta;
import com.mitocode.model.Examen;

public class ConsultaListaExamenDTO {
//esto no necesita jpa por que es una clase auxiliar
	private Consulta consulta;
	private List<Examen> lstExamen;
	//esto es crear una tabla en la bd pero al ventaja es que puedes implementar en la logica de programacion
	//puede servir para las consultas
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public List<Examen> getLstExamen() {
		return lstExamen;
	}
	public void setLstExamen(List<Examen> lstExamen) {
		this.lstExamen = lstExamen;
	}
	/*{
	"consulta" :{
	"paciente" : {
		"idPaciente" : 1
	},
	"medico"  : {
		"idMedico" : 1
	},
	"especialidad"  : {
		"idEspecialidad" : 1
	},
	"fecha" : "2019-07-25T05:00:00.000Z",
	"detalleConsulta" : [
		{"diagnostico" : "FIEBRE", "tratamiento" : "PARACETAMOL"},
		{"diagnostico" : "AMIGOALATIS", "tratamiento" : "ANTIBIOTICOS"}]
	},
	"lstExamen" : [
		{"idExamen" : 1},
		{"idExamen" : 2}
		]

}*/
}
