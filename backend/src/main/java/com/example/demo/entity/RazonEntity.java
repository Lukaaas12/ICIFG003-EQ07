package com.example.demo.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "razon")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RazonEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String descripcion; 
}