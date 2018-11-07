package ar.edu.utn.frsf.isi.dam.testing.dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ar.edu.utn.frsf.isi.dam.testing.modelo.Proyecto;
import ar.edu.utn.frsf.isi.dam.testing.modelo.Tarea;

@Database(entities = {Proyecto.class,Tarea.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProyectoDao proyectoDao();
    public abstract TareaDao tareaDao();
}
