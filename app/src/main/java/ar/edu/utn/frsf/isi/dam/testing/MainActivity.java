package ar.edu.utn.frsf.isi.dam.testing;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optNuevoProyecto:
                Intent i1 = new Intent(this,ABMProyectoActivity.class);
                startActivity(i1);
                return true;
            case R.id.optListaProyecto:
                Intent i2 = new Intent(this,ListaProyectoActivity.class);
                startActivity(i2);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(this,"ESTA OPCION NO TIENE ACCION ASOCIADA",Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }
}
