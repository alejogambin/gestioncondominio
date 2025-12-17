package com.proyecto.app.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

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

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    private Set<Usuario> usuarios = new HashSet<>();

    public Rol(){

    }
    // getter and setter
    public Long getId() {
        return id;
    }  
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }
    public void setUsuarios(Set<Usuario> Usuarios) {
        this.usuarios = Usuarios;
    }

    @Override
    public String toString(){
        return " Rol {"+"id="+ id + ", Nombre= '"+ Nombre + "'," + " descripcion='" + descripcion + "'}";
    }
    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if (!(o instanceof Rol )) return false;
        Rol rol = (Rol) o;
        return Nombre != null && Nombre.equals(rol.Nombre);
    }
    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
}
