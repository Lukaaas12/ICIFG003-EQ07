package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.entity.AlumnoEntity;

public interface IAlumnoService {

	public List<AlumnoEntity> findAll();
	public AlumnoEntity findById(Long id);
	public AlumnoEntity save(AlumnoEntity alumno);
	void deleteById(long id);
}
