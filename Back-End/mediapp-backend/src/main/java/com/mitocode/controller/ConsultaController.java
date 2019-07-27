package com.mitocode.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	@Autowired
	private IConsultaService service;
	//la entidad consulta examen es insependiente
	@PostMapping(produces = "application/json", consumes = "application/json" )//los registrar deben ser en post
	public ResponseEntity<Object> registrar(@RequestBody ConsultaListaExamenDTO consDTO) {//@requestbody ->el json que viene transforma a un objeto Paciente vuelve la respuesta de eso
		Consulta consulta=new Consulta();
		consulta= service.registrarTransaccional(consDTO);
		// /pacientes/{id} se convierte e 2
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id)").buildAndExpand(consulta.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();		
	}
 	
}
