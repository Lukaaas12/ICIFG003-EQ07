package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PersonaEntity;
import com.example.demo.interfaces.IPersonaService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping ("/api/v1/entities/personas") //http://localhost:8080//api/v1/entities/persona
public class PersonaController {

	@Autowired
	private IPersonaService service;
	
	@GetMapping
	public ResponseEntity<?> readPersonas(){
		try {
			return ResponseEntity.ok(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> readPersonabyId(@PathVariable Long id){
		try {
			if(service.findById(id)== null) {
				return ResponseEntity.status(404).body("Persona no encontrada. ID: "+ id +"\n");
			} else
			return ResponseEntity.ok(service.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e);
		}
	}
	@PostMapping
	public ResponseEntity<?> createPersona(@RequestBody PersonaEntity personaEntity){
		try {
			PersonaEntity nuevaPersona = service.save(personaEntity);
			return ResponseEntity.ok().body(nuevaPersona);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e);
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePersona(@PathVariable Long id, @RequestBody PersonaEntity PersonaActualizada){
		try {
			if (service.findById(id) == null) {
				return ResponseEntity.status(404).body("Persona no encontrada para actualizar. ID: "+ id +"\n");
			} else {
				PersonaEntity personaGuardada = service.save(PersonaEntity.builder().nombre(PersonaActualizada.getNombre()).id(id).build());
			    return ResponseEntity.ok().body(personaGuardada);
			}
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e);
		}	
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		try{
			service.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error al eliminar la persona. ID: "+ id + "\n");
		}
	}
}