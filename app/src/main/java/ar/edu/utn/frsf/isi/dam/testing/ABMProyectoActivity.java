package ar.edu.utn.frsf.isi.dam.testing;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ar.edu.utn.frsf.isi.dam.testing.dao.AppDatabaseFactory;
import ar.edu.utn.frsf.isi.dam.testing.dao.ProyectoDao;
import ar.edu.utn.frsf.isi.dam.testing.modelo.Proyecto;

public class ABMProyectoActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtPresup;
    private EditText edtHoras;
    private Button btnGuardar;
    private ProyectoDao dao;

    private Proyecto proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abmproyecto);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dao = AppDatabaseFactory.getInstance(this).getProyectoDao();

        edtNombre = (EditText) findViewById(R.id.abmPryNombre);
        edtPresup = (EditText) findViewById(R.id.abmPryPresup);
        edtHoras = (EditText) findViewById(R.id.abmPryHoras);
        btnGuardar = (Button) findViewById(R.id.abmPryBtnGuardar);

        cargarDatos();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrUpdate();
            }
        });

    }

    public boolean ratioValido(){
        System.out.println(" - - - test ");
        double ratio = proyecto.getPresupuesto()/proyecto.getHoras();
        System.out.println(" - - - "+ratio);
        return ratio<1000 && ratio > 100;
    }

    public void saveOrUpdate(){
        bindEditToProyecto();

        if(!ratioValido()){
            mostrarMensajeError("El ratio Presupuesto/hora debe estar en un valor entre 100 y 1000");
            return;
        }

        Runnable hiloGuardar = new Runnable() {
            @Override
            public void run() {
                if(proyecto.getId()>0) dao.update(proyecto);
                else dao.insert(proyecto);
                msgHandler.sendEmptyMessage(2);
            }
        };
        Thread t1 = new Thread(hiloGuardar);
        t1.start();
    }

    private void cargarDatos(){
        final Bundle args = getIntent().getExtras();
        if(args!=null && args.getInt("id_proyecto",0)>0) {
            Runnable hiloCargarProyecto = new Runnable() {
                @Override
                public void run() {
                    proyecto = dao.getById(args.getInt("id_proyecto"));
                    msgHandler.sendEmptyMessage(1);
                }
            };
            Thread t1 = new Thread(hiloCargarProyecto);
            t1.start();
        }else{
            proyecto = new Proyecto();
        }

    }

    private void bindProyectoToEdt(){
            edtNombre.setText(proyecto.getNombre());
            edtHoras.setText(proyecto.getHoras()+"");
            edtPresup.setText(proyecto.getPresupuesto()+"");
    }

    private void clearEdt(){
        edtNombre.setText("");
        edtHoras.setText("");
        edtPresup.setText("");
        proyecto = new Proyecto();
    }

    private void bindEditToProyecto(){
        proyecto.setNombre(edtNombre.getText().toString());
        proyecto.setHoras(Integer.valueOf(edtHoras.getText().toString()));
        proyecto.setPresupuesto(Double.valueOf(edtPresup.getText().toString()));
    }

    Handler msgHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    bindProyectoToEdt();
                    break;
                case 2:
                    clearEdt();
                    break;
            }
        }
    };

    private void mostrarMensajeError(String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(mensaje)
                    .setTitle("ERROR - Operacion Invalida")
                    .setIcon(android.R.drawable.stat_notify_error)
                    .setNegativeButton(R.string.btnError, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog dialogo = builder.create();
            dialogo.show();
   }

    public EditText getEdtNombre() {
        return edtNombre;
    }

    public void setEdtNombre(EditText edtNombre) {
        this.edtNombre = edtNombre;
    }

    public EditText getEdtPresup() {
        return edtPresup;
    }

    public void setEdtPresup(EditText edtPresup) {
        this.edtPresup = edtPresup;
    }

    public EditText getEdtHoras() {
        return edtHoras;
    }

    public void setEdtHoras(EditText edtHoras) {
        this.edtHoras = edtHoras;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(Button btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public ProyectoDao getDao() {
        return dao;
    }

    public void setDao(ProyectoDao dao) {
        this.dao = dao;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
