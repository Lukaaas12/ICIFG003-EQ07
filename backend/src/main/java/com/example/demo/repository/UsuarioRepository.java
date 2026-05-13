package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
    // ESTA LÍNEA ES LA CLAVE PARA EL LOGIN
    UsuarioEntity findByUsernameAndPassword(String username, String password); 
}