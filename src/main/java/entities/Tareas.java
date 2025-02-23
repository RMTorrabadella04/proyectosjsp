/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author raul
 */
@Entity
@Table(name = "Tareas")

@NamedQueries({
    @NamedQuery(name = "Tareas.findAll", query = "SELECT t FROM Tareas t"),
    @NamedQuery(name = "Tareas.findById", query = "SELECT t FROM Tareas t WHERE t.id = :id"),
    @NamedQuery(name = "Tareas.findByResponsable", query = "SELECT t FROM Tareas t WHERE t.responsable = :responsable"),
    @NamedQuery(name = "Tareas.findByFechaInicio", query = "SELECT t FROM Tareas t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Tareas.findByFechaFin", query = "SELECT t FROM Tareas t WHERE t.fechaFin = :fechaFin"),
    @NamedQuery(name = "Tareas.findByEstado", query = "SELECT t FROM Tareas t WHERE t.estado = :estado")})
public class Tareas implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "descripcion_tarea")
    private String descripcionTarea;
    
    @Column(name = "responsable")
    private String responsable;
    
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    
    @Column(name = "fecha_fin")
    private Date fechaFin;
    
    @Column(name = "estado")
    private String estado;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id")
    private Proyectos idProyecto;

    public Tareas() {
    }

    public Tareas(String descripcionTarea, String responsable, Date fechaInicio, String estado) {
        this.descripcionTarea = descripcionTarea;
        this.responsable = responsable;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }

    public Tareas(Integer id, String descripcionTarea, String responsable, Date fechaInicio, String estado) {
        this.id = id;
        this.descripcionTarea = descripcionTarea;
        this.responsable = responsable;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio); // Establecer la fecha de inicio
        calendar.add(Calendar.MONTH, 1); // Agregar 6 meses
        this.fechaFin = calendar.getTime();
    }
    
    public Tareas(Integer id, String descripcionTarea, String responsable, Date fechaInicio, Date fechaFin, String estado, Proyectos idProyecto) {
        this.id = id;
        this.descripcionTarea = descripcionTarea;
        this.responsable = responsable;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idProyecto = idProyecto;
    }  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio); // Establecer la fecha de inicio
        calendar.add(Calendar.MONTH, 1); // Agregar 6 meses
        this.fechaFin = calendar.getTime();
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proyectos getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return "Tareas{" + "id=" + id + ", descripcionTarea=" + descripcionTarea + ", responsable=" + responsable + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + ", idProyecto=" + idProyecto + '}';
    }

}
