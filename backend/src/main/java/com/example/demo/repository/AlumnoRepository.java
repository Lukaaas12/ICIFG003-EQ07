package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AlumnoEntity;

@Repository
public interface AlumnoRepository extends CrudRepository <AlumnoEntity, Long> {

}
