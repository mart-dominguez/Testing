package ar.edu.utn.frsf.isi.dam.testing.modelo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class ProyectoConTareas {
    @Embedded
    public Proyecto proyecto;

    @Relation(parentColumn = "id", entityColumn = "pry_id", entity = Tarea.class)
    public List<Tarea> tareas;
}
