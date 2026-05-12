package com.example.demo.interfaces;

import java.util.List;
import com.example.demo.entity.UsuarioEntity;

public interface IUsuarioService {
	public List<UsuarioEntity> findAll();
	public UsuarioEntity findById(Long id);
	public UsuarioEntity save(UsuarioEntity usuario);
	void deleteById(long id);
}