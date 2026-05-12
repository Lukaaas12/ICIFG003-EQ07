package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.interfaces.IUsuarioService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping ("/api/v1/entities/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	
	@GetMapping
	public ResponseEntity<?> readUsuarios(){
		try { return ResponseEntity.ok(service.findAll()); } 
		catch (Exception e) { return ResponseEntity.status(404).body(e); }
	}

	@PostMapping
	public ResponseEntity<?> createUsuario(@RequestBody UsuarioEntity entity){
		try { return ResponseEntity.ok().body(service.save(entity)); } 
		catch (Exception e) { return ResponseEntity.status(404).body(e); }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		try {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) { return ResponseEntity.status(500).body("Error"); }
	}
}