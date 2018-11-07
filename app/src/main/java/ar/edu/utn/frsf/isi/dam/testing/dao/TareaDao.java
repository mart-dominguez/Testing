package ar.edu.utn.frsf.isi.dam.testing.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.testing.modelo.Tarea;
@Dao
public interface TareaDao {
    
    @Query("SELECT * FROM Tarea")
    List<Tarea> getAll();

    @Query("SELECT * FROM Tarea WHERE id = :pIdTarea")
    Tarea getById(int pIdTarea);

    @Insert
    long insert(Tarea r);

    @Insert

    void update(Tarea r);

    @Delete
    void delete(Tarea r);
}
