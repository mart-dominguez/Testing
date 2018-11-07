package ar.edu.utn.frsf.isi.dam.testing.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.testing.modelo.Proyecto;
import ar.edu.utn.frsf.isi.dam.testing.modelo.ProyectoConTareas;

@Dao
public interface ProyectoDao {

    @Query("SELECT * FROM Proyecto")
    List<Proyecto> getAll();

    @Query("SELECT * FROM Proyecto WHERE id = :pIdProyecto")
    Proyecto getById(int pIdProyecto);

    @Query("SELECT * FROM Proyecto WHERE id = :pIdPry")
    List<ProyectoConTareas> buscarPorIdConTareas(long pIdPry);

    @Insert
    long insert(Proyecto r);

    @Insert

    void update(Proyecto r);

    @Delete
    void delete(Proyecto r);
}
