package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.entity.PersonaEntity;

public interface IPersonaService {

	public List<PersonaEntity> findAll();
	public PersonaEntity findById(Long id);
	public PersonaEntity save(PersonaEntity persona);
	void deleteById(long id);
}
