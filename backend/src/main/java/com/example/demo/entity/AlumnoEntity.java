package com.example.demo.entity;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "alumno")
@Data // get y set  
@NoArgsConstructor  //constructor vacio
@AllArgsConstructor //constructor con args
@Builder //encabezado de entity
public class AlumnoEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String nombre;
	@NonNull
	private String curso;
}
