/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Calendar;
import javax.persistence.Column;

/**
 *
 * @author raul
 */

@Entity
@Table(name = "Proyectos")

@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p"),
    @NamedQuery(name = "Proyectos.findById", query = "SELECT p FROM Proyectos p WHERE p.id = :id"),
    @NamedQuery(name = "Proyectos.findByNombreProyecto", query = "SELECT p FROM Proyectos p WHERE p.nombreProyecto = :nombreProyecto"),
    @NamedQuery(name = "Proyectos.findByFechaInicio", query = "SELECT p FROM Proyectos p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Proyectos.findByFechaFin", query = "SELECT p FROM Proyectos p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "Proyectos.findByEstado", query = "SELECT p FROM Proyectos p WHERE p.estado = :estado")})

public class Proyectos implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    
    @Column(name = "descripcion")
    private String descripcion;
   
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    
    @Column(name = "fecha_fin")
    private Date fechaFin;
    
    @Column(name = "estado")
    private String estado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto")
    private Collection<Tareas> tareasCollection;

    public Proyectos() {
    }

     public Proyectos(String nombreProyecto, Date fechaInicio, String estado) {
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio); // Establecer la fecha de inicio
        calendar.add(Calendar.MONTH, 6); // Agregar 6 meses
        this.fechaFin = calendar.getTime();
    }
    public Proyectos(Integer id, String nombreProyecto, Date fechaInicio, String estado) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio); // Establecer la fecha de inicio
        calendar.add(Calendar.MONTH, 6); // Agregar 6 meses
        this.fechaFin = calendar.getTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio); // Establecer la fecha de inicio
        calendar.add(Calendar.MONTH, 6); // Agregar 6 meses
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

    public Collection<Tareas> getTareasCollection() {
        return tareasCollection;
    }

    public void setTareasCollection(Collection<Tareas> tareasCollection) {
        this.tareasCollection = tareasCollection;
    }


    @Override
    public String toString() {
        return "Proyectos{" + "id=" + id + ", nombreProyecto=" + nombreProyecto + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + ", tareasCollection=" + tareasCollection + '}';
    }
  
}
