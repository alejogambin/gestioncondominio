package com.proyecto.app.entity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Set;
import java.util.HashSet;
@Entity
@Table(name = "user")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user; 
    @Column(unique = true  , length = 12)
    private String rut ;
    @Column(nullable = false, length = 100)
    private String nombre ;
    @Column(nullable = false, length = 100)
    private String apellido ;
    @Column(name = "correo", unique = true,nullable = false, length = 150)
    private String email ;
    @Column(name = "contraseña", nullable = false, length = 225)
    private String password ;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id")

    )
    @JsonIgnoreProperties("usuarios")
    private Set<Rol> roles = new HashSet<>();

    @Column(nullable = false)
    private boolean activo = true;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updated_at;

    @PrePersist
    protected void prePersist(){
        this.created_at = new Date();
        
    }
    @PreUpdate
    protected void preUpdate(){
        this.updated_at = new Date();
    }

    public Usuario() {
        this.roles = new HashSet<>();
        this.activo = true;
    }

    public Usuario(Long id_user,String nombre, String apellido,String rut ,String email, String password, boolean activo) {
        this.id_user = id_user;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.email = email;
        this.password = password;
        this.activo = activo;
    }

    public Usuario(String nombre, String apellido, String email, String password, Set<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.activo = true;
    }
    

   
    // Getters and Setters
    public long getId_user() {
        return id_user;
    }
    public void setId_user(long id_user) {
        this.id_user = id_user;
        this.updated_at = new Date();
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
        this.updated_at = new Date();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.updated_at = new Date();
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
        this.updated_at = new Date();
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        this.updated_at = new Date();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
        this.updated_at = new Date();
    }
    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
        this.updated_at = new Date();
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
        this.updated_at = new Date();
    }
    public Date getCreated_at() {
        return created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    

    public void addRol(Rol rol){
        this.roles.add(rol);
        rol.getUsuarios().add(this);
    }

    public void removeRol(Rol rol){
        this.roles.remove(rol);
        rol.getUsuarios().remove(this);
    }
    public String GetUsername(){
        return this.email;
    }
    @Override
    public String toString(){
        return " Usuario {"+
        "id_user="+ id_user + 
        ", nombre= '"+ nombre + 
        "'\'" + " apellido='" + apellido + '\'' +
        "', email='" + email + 
        "', activo='" + activo + ", roles=" + roles.size() + "'}";
    }
}
