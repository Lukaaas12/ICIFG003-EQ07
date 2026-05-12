package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PersonaEntity;
import com.example.demo.interfaces.IPersonaService;
import com.example.demo.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private PersonaRepository repoPersona;
	
	@Override
	public List<PersonaEntity> findAll() {
		Iterable<PersonaEntity> ipe = repoPersona.findAll();
		return (List<PersonaEntity>) ipe;
	}

	@Override
	public PersonaEntity findById(Long id) {
		Optional<PersonaEntity> ope = repoPersona.findById(id);
		return ope.orElse(null);
	}

	@Override
	public PersonaEntity save(PersonaEntity persona) {
		return repoPersona.save(persona);
	}

	@Override
	public void deleteById(long id) {
	repoPersona.deleteById(id);
	} 

}
