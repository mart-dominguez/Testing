package ar.edu.utn.frsf.isi.dam.testing;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ar.edu.utn.frsf.isi.dam.testing.modelo.Proyecto;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ABMProyectoTest {

    @Mock
    ABMProyectoActivity  activityABMPry;

    @Mock
    Proyecto proyeco;


    @Test
    public void proyecto_ratio_correcto() {
        when(proyeco.getHoras()).thenReturn(10);
        when(proyeco.getPresupuesto()).thenReturn(4000.0);
        activityABMPry.setProyecto(proyeco);

        assertTrue(activityABMPry.ratioValido());
    }
}
