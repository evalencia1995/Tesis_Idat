package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private IPacienteService service;
	
	@GetMapping(produces = "application/json")//A NIVEL STIRN EL JSON
	public ResponseEntity<List<Paciente>> listar(){
		return new ResponseEntity<List<Paciente>>(service.listar(),HttpStatus.OK);		
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)//expone la opercion get//APPLICATION_JSON_VALUE A NIVEL CONTANTE
	public Resource<Paciente> listPorId(@PathVariable("id") Integer id){
		Paciente pac=service.listarId(id);
		if(pac==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+id);//lanza una excepcion personalizada
		}
		Resource<Paciente> resource = new Resource<Paciente>(pac);
		//construyendo url tipo madurez strez 3
		ControllerLinkBuilder linkTo  = linkTo(methodOn(this.getClass()).listPorId(id));
		resource.add(linkTo.withRel("paciente resource"));
		return resource;
		//psar el id por la ruta con el atribut value 
		//el id es capturada por pathvariable
	}
	/*public void saludar() {
		//Paciente p=new Paciente();
		//p.setId(1);
		//p.setNombre("esteban");
		//return p;
		service.crear();
	}*/
	@PostMapping(produces = "application/json", consumes = "application/json" )//los registrar deben ser en post
	public ResponseEntity<Object> registrar(@Valid @RequestBody Paciente pac) {//@requestbody ->el json que viene transforma a un objeto Paciente vuelve la respuesta de eso
		//obliga a que el json respete los constrain --> @Valid
		Paciente paciente=new Paciente();
		paciente= service.registrar(pac);
		// /pacientes/{id} se convierte e 2
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id)").buildAndExpand(paciente.getIdPaciente()).toUri();
		return ResponseEntity.created(location).build();		
	}
	@PutMapping(produces = "application/json", consumes = "application/json" )
	public ResponseEntity<Object> modificar(@RequestBody Paciente pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	//controller  / service logica de negocio  / dao logica de datos --> referpetticion http el primer a interseptar -- 
	//controller se comunicac con la capa servise y este se comunico para lo datos con la capa dao
	//null pointer exception para evitar = new PacienteImpl por eso usamos inyeccion de dependencias
	//contenedor de beans -- espacio de memoria para las instancias
	//model es idependiente solo sirve para la presentacion de entidades
	
	@DeleteMapping(value = "/{id}")//expone la opercion get
	public void eliminar(@PathVariable("id") Integer id){
		Paciente pac=service.listarId(id);
		if(pac==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+id);//lanza una excepcion personalizada
		}else {
			service.eliminar(id);
		}
		
		//psar el id por la ruta con el atribut value 
		//el id es capturada por pathvariable y lo elimina
	}

}

///dolor de cabeza esta wa xd
