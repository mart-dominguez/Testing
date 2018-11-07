package ar.edu.utn.frsf.isi.dam.testing;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.testing.dao.AppDatabaseFactory;
import ar.edu.utn.frsf.isi.dam.testing.dao.ProyectoDao;
import ar.edu.utn.frsf.isi.dam.testing.modelo.Proyecto;

public class ListaProyectoActivity extends AppCompatActivity {

    private RecyclerView listaProyecto;
    private ProyectoDao dao;
    private List<Proyecto> proyectos;
    private ProyectoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proyecto);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dao = AppDatabaseFactory.getInstance(this).getProyectoDao();
        listaProyecto = (RecyclerView) findViewById(R.id.lista_proyecto);

        Runnable cargarLista = new Runnable() {
            @Override
            public void run() {
                proyectos = dao.getAll();
                msgHandler.sendEmptyMessage(1);
            }
        };
        Thread t1 = new Thread(cargarLista);
        t1.start();
    }

    private void inicializarLista(){
        adapter = new ProyectoAdapter(proyectos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listaProyecto.setLayoutManager(llm);
        listaProyecto.setAdapter(adapter);
    }

    Handler msgHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    inicializarLista();
                    break;
            }
        }
    };
}
