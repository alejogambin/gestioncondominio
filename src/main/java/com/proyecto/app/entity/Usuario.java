package com.proyecto.app.entity;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user; 
    private String rut ;
    private String nombre ;
    private String apellido ;
    private String correo ;
    private String contraseña ;
    private String rol ;
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
    protected Usuario() {}
}
