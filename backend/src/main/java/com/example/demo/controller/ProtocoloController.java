package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.ProtocoloEntity;
import com.example.demo.interfaces.IProtocoloService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping ("/api/v1/entities/protocolos") 
public class ProtocoloController {

	@Autowired
	private IProtocoloService service;
	
	@GetMapping
	public ResponseEntity<?> readProtocolos(){
		try { return ResponseEntity.ok(service.findAll()); } 
		catch (Exception e) { return ResponseEntity.status(404).body(e); }
	}

	@PostMapping
	public ResponseEntity<?> createProtocolo(@RequestBody ProtocoloEntity protocoloEntity){
		try {
			return ResponseEntity.ok().body(service.save(protocoloEntity));
		} catch (Exception e) { return ResponseEntity.status(404).body(e); }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		try{
			service.deleteById(id);
			return ResponseEntity.ok().build(); // Importante para que Angular no dé error de JSON
		} catch (Exception e) { return ResponseEntity.status(500).body("Error"); }
	}
}