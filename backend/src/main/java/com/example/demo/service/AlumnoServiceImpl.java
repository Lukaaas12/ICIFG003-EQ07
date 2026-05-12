package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AlumnoEntity;
import com.example.demo.interfaces.IAlumnoService;
import com.example.demo.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private AlumnoRepository repoAlumno;
	
	@Override
	public List<AlumnoEntity> findAll() {
		Iterable<AlumnoEntity> ipe = repoAlumno.findAll();
		return (List<AlumnoEntity>) ipe;
	}

	@Override
	public AlumnoEntity findById(Long id) {
		Optional<AlumnoEntity> ope = repoAlumno.findById(id);
		return ope.orElse(null);
	}

	@Override
	public AlumnoEntity save(AlumnoEntity alumno) {
		return repoAlumno.save(alumno);
	}

	@Override
	public void deleteById(long id) {
		repoAlumno.deleteById(id);
	} 

}
