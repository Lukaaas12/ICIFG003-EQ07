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

import com.example.demo.entity.AlumnoEntity;
import com.example.demo.interfaces.IAlumnoService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping ("/api/v1/entities/alumnos") //http://localhost:8080//api/v1/entities/persona
public class AlumnoController {

	@Autowired
	private IAlumnoService service;
	
	@GetMapping
	public ResponseEntity<?> readAlumnos(){
		try {
			return ResponseEntity.ok(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> readAlumnobyId(@PathVariable Long id){
		try {
			if(service.findById(id)== null) {
				return ResponseEntity.status(404).body("Alumno no encontrada. ID: "+ id +"\n");
			} else
			return ResponseEntity.ok(service.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e);
		}
	}
	@PostMapping
	public ResponseEntity<?> createAlumno(@RequestBody AlumnoEntity personaEntity){
		try {
			AlumnoEntity nuevoAlumno = service.save(personaEntity);
			return ResponseEntity.ok().body(nuevoAlumno);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e);
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAlumno(@PathVariable Long id, @RequestBody AlumnoEntity AlumnoActualizado){
		try {
			if (service.findById(id) == null) {
				return ResponseEntity.status(404).body("Persona no encontrada para actualizar. ID: "+ id +"\n");
			} else {
				AlumnoEntity alumnoGuardado = service.save(AlumnoEntity.builder().nombre(AlumnoActualizado.getNombre()).id(id).build());
			    return ResponseEntity.ok().body(alumnoGuardado);
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
			return ResponseEntity.status(500).body("Error al eliminar al alumno. ID: "+ id + "\n");
		}
	}
}