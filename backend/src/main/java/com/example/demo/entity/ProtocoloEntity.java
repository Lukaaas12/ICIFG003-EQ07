package com.example.demo.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "protocolo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProtocoloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date fecha;
    
    private String observaciones;

    // Relación 1:1 con la Razón de desregulación
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "razon_id", referencedColumnName = "id")
    private RazonEntity razon;

    // Relación 1:N con Alumnos
    // Corregido: Alu     mnoEntity -> AlumnoEntity
    @OneToMany
    @JoinColumn(name = "protocolo_id")
    private List<AlumnoEntity> alumnos;
}