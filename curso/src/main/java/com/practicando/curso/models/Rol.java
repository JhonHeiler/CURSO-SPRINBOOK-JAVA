package com.practicando.curso.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="rol")
@ToString @EqualsAndHashCode
public class Rol {
    @Id
    @Getter @Setter @Column(name="id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
    @Getter @Setter @Column(name="nombre")
    private String nombre;

}
