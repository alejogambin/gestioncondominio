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
public class Bodega {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Bodega;
    private String num_bodega;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_torre") 
    private Torre id_torre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Usuario id_propietario; 
    
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
    protected Bodega() {}
    
    // Getters and Setters
    public long getId_Bodega() {
        return id_Bodega;
    }

    public void setId_Bodega(long id_Bodega) {
        this.id_Bodega = id_Bodega;
    }

    public String getNum_bodega() {
        return num_bodega;
    }

    public void setNum_bodega(String num_bodega) {
        this.num_bodega = num_bodega;
    }

    public Torre getId_torre() {
        return id_torre;
    }

    public void setId_torre(Torre id_torre) {
        this.id_torre = id_torre;
    }

    public Usuario getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(Usuario id_propietario) {
        this.id_propietario = id_propietario;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
}
