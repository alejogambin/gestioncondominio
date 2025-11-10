package com.proyecto.app.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Usuario id_arrendatario  ;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario id_propietario ;
    @ManyToOne(fetch = FetchType.LAZY)
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
}
