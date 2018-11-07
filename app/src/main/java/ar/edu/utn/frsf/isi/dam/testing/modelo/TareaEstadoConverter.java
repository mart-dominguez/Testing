package ar.edu.utn.frsf.isi.dam.testing.modelo;

import android.arch.persistence.room.TypeConverter;

public class TareaEstadoConverter {

    @TypeConverter
    public static Tarea.Estado  toEstado(String status) {
        return Tarea.Estado .valueOf(status);
    }

    @TypeConverter
    public static String  toString(Tarea.Estado status) {
        return status.toString();
    }
}
