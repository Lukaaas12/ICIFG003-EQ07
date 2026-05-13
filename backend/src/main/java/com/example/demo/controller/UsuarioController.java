package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.interfaces.IUsuarioService;
import com.example.demo.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping ("/api/v1/entities/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;
    
    // Inyectamos el repo directo para el login rápido
    @Autowired
    private UsuarioRepository usuarioRepository; 
    
    @GetMapping
    public ResponseEntity<?> readUsuarios(){
        try { return ResponseEntity.ok(service.findAll()); } 
        catch (Exception e) { return ResponseEntity.status(404).body(e); }
    }

    // REGISTRO (Este ya lo tenías, guarda el usuario en la BD)
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

    // NUEVO LOGIN (Recibe las credenciales y pregunta a la BD)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity userDB = usuarioRepository.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword());
        if (userDB != null) {
            return ResponseEntity.ok(userDB); // Pasa la validación
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas"); // Error de login
        }
    }
}