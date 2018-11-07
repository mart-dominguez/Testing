package ar.edu.utn.frsf.isi.dam.testing.dao;

import android.arch.persistence.room.Room;
import android.content.Context;

public class AppDatabaseFactory {
    // variable de clase privada que almacena una instancia unica de esta entidad
    private static AppDatabaseFactory _INSTANCIA_UNICA=null;

    // metodo static publico que retorna la unica instancia de esta clase
    // si no existe, cosa que ocurre la primera vez que se invoca
    // la crea, y si existe retorna la instancia existente.

    public static AppDatabaseFactory getInstance(Context ctx){
        if(_INSTANCIA_UNICA==null) _INSTANCIA_UNICA = new AppDatabaseFactory(ctx);
        return _INSTANCIA_UNICA;
    }

    private AppDatabase db;
    private ProyectoDao proyectoDao;
    private TareaDao tareaDao;

    // constructor privado para poder implementar SINGLETON
    // al ser privado solo puede ser invocado dentro de esta clase
    // el único lugar donde se invoca es en la linea 16 de esta clase
    // y se invocará UNA Y SOLO UNA VEZ, cuando _INSTANCIA_UNICA sea null
    // luego ya no se invoca nunca más. Nos aseguramos de que haya una
    // sola instancia en toda la aplicacion
    private AppDatabaseFactory(Context ctx){
        db = Room.databaseBuilder(ctx,
                AppDatabase.class, "database-name")
                .fallbackToDestructiveMigration()
                .build();
        proyectoDao = db.proyectoDao();
        tareaDao = db.tareaDao();

    }

    public ProyectoDao getProyectoDao() {
        return proyectoDao;
    }

    public TareaDao getTareaDao() {
        return tareaDao;
    }
}
