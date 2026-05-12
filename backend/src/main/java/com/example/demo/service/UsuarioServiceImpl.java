package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.interfaces.IUsuarioService;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Override
	public List<UsuarioEntity> findAll() {
		return (List<UsuarioEntity>) repoUsuario.findAll();
	}

	@Override
	public UsuarioEntity findById(Long id) {
		return repoUsuario.findById(id).orElse(null);
	}

	@Override
	public UsuarioEntity save(UsuarioEntity usuario) {
		return repoUsuario.save(usuario);
	}

	@Override
	public void deleteById(long id) {
		repoUsuario.deleteById(id);
	} 
}