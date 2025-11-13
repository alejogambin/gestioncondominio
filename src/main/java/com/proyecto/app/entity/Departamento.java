package com.proyecto.app.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Departamento {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Departamento;
    private Integer piso  ;
    private Integer num_departamento  ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Usuario id_propietario ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_torre")
    private Torre id_torre ;
    private Integer num_habitantes ;
    
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
    protected Departamento() {}
    // Getters and Setters
    public long getId_Departamento() {
        return id_Departamento;
    }
    public void setId_Departamento(long id_Departamento) {
        this.id_Departamento = id_Departamento;
    }
    public Integer getPiso() {
        return piso;
    }
    public void setPiso(Integer piso) {
        this.piso = piso;
    }
    public Integer getNum_departamento() {
        return num_departamento;
    }
    public void setNum_departamento(Integer num_departamento) {
        this.num_departamento = num_departamento;
    }
    public Usuario getId_propietario() {
        return id_propietario;
    }
    public void setId_propietario(Usuario id_user) {
        this.id_propietario = id_user;
    }
    public Torre getId_torre() {
        return id_torre;
    }
    public void setId_torre(Torre id_torre) {
        this.id_torre = id_torre;
    }
    public Integer getNum_habitantes() {
        return num_habitantes;
    }
    public void setNum_habitantes(Integer num_habitantes) {
        this.num_habitantes = num_habitantes;
    }
     public Date getCreated_at() {
        return created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    
}
