package com.mitocode.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.ConsultaDTO;
import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
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
	
	@GetMapping(value = "/heteoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ConsultaDTO> listarHateoas(){
		List<Consulta> consultas = new ArrayList<>();
		List<ConsultaDTO> consultasDTO=new ArrayList<>();
		consultas =service.listar();
		
		//para cada obj consulta del arreglo consulta que viene una instancia dto
		for(Consulta c : consultas) {
			ConsultaDTO d = new ConsultaDTO();
			d.setIdConsulta(c.getIdConsulta());
			d.setMedico(c.getMedico());
			d.setPaciente(c.getPaciente());
			//construimos un link controllerlinkbuilder
			
			//consultas/1
			ControllerLinkBuilder linkTo = linkTo(
					methodOn(ConsultaController.class).listarPorId((c.getIdConsulta())));
			d.add(linkTo.withSelfRel());
			consultasDTO.add(d);
			
			
			//pacientes/1
			ControllerLinkBuilder linkTo1 = linkTo(
					methodOn(ConsultaController.class).listarPorId((c.getPaciente().getIdPaciente())));
			d.add(linkTo.withSelfRel());
			consultasDTO.add(d);
			
			//medico/1
			ControllerLinkBuilder linkTo2 = linkTo(
					methodOn(ConsultaController.class).listarPorId((c.getMedico().getIdMedico())));
			d.add(linkTo.withSelfRel());
			consultasDTO.add(d);
		}
		return consultasDTO;
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public Consulta listarPorId(@PathVariable("id")Integer id) {
		return service.listarId(id);
	}
 	
}
