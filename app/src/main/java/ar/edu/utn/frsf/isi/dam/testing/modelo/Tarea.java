package ar.edu.utn.frsf.isi.dam.testing.modelo;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

@Entity
public class Tarea {

    public enum Estado { PLANIFICADA,EN_CURSO,TERMINADA}

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descripcion;
    private int horas;
    private double costoHora;
    @Embedded(prefix = "pry_")
    private Proyecto proyecto;

    @TypeConverters(TareaEstadoConverter.class)
    private Estado estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
