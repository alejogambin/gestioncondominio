package com.proyecto.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false , length = 50)
    private String Nombre;

    @Column(length = 200)
    private String descripcion;

    @manytomany(mappedBy = "roles")
    private Set<Usuario> usuairos = new HashSet<>();

    public Rol(){

    }
    
}
