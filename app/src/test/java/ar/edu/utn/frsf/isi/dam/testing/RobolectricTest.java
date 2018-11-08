package ar.edu.utn.frsf.isi.dam.testing;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class RobolectricTest {

    private ABMProyectoActivity activity;

    @Before
    public void setUp() throws Exception    {
        activity = Robolectric.buildActivity( ABMProyectoActivity.class )
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void debeGrabar() throws Exception {
        assertNotNull( activity.getBtnGuardar() );
        EditText txtNombre = (EditText) activity.findViewById(R.id.abmPryNombre);
        EditText txtHoras = (EditText) activity.findViewById(R.id.abmPryHoras);
        EditText txtPresup = (EditText) activity.findViewById(R.id.abmPryPresup);
        txtNombre.setText("Proyecto ABC123");
        txtHoras.setText("8");
        txtPresup.setText("2000.0");
        Button button = (Button) activity.findViewById(R.id.abmPryBtnGuardar);
        button.performClick();
        Thread.sleep(1500);
        //Robolectric.flushBackgroundThreadScheduler();
        Robolectric.flushForegroundThreadScheduler();
        assertEquals("",txtNombre.getText().toString());
        assertEquals("",txtHoras.getText().toString());
        assertEquals("",txtPresup.getText().toString());
        List<Toast> toasts = Shadows.shadowOf(activity.getApplication()).getShownToasts();
        assertEquals(1,toasts.size());
    }

    /*@Test
    public void shouldHaveDefaultMargin() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.hello_textview);
        int bottomMargin = ((LinearLayout.LayoutParams) textView.getLayoutParams()).bottomMargin;
        assertEquals(5, bottomMargin);
        int topMargin = ((LinearLayout.LayoutParams) textView.getLayoutParams()).topMargin;
        assertEquals(5, topMargin);
        int rightMargin = ((LinearLayout.LayoutParams) textView.getLayoutParams()).rightMargin;
        assertEquals(10, rightMargin);
        int leftMargin = ((LinearLayout.LayoutParams) textView.getLayoutParams()).leftMargin;
        assertEquals(10, leftMargin);
    }
    */
}
