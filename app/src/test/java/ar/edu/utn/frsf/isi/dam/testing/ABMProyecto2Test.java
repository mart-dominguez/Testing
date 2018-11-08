package ar.edu.utn.frsf.isi.dam.testing;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.testing.dao.ProyectoDao;
import ar.edu.utn.frsf.isi.dam.testing.modelo.Proyecto;
import ar.edu.utn.frsf.isi.dam.testing.modelo.ProyectoConTareas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ABMProyecto2Test {

    ABMProyectoActivity  activityABMPry;

    @Mock Context ctx;

    @Mock
    ProyectoDao daoMock;

    @Mock
    Proyecto proyectoMock;

    @Mock
    EditText edtHorasMock;
    @Mock
    EditText edtNombreMock;
    @Mock
    EditText edtPresupMock;

    @Mock
    Editable edtMock;
    @Mock
    Handler msgHandler;

    /*
    @Before
    public void init(){
        activityABMPry = spy(ABMProyectoActivity.class);
        doReturn(edtHorasMock).when(activityABMPry).findViewById(R.id.abmPryHoras);
        doReturn(edtNombreMock).when(activityABMPry).findViewById(R.id.abmPryNombre);
        doReturn(edtPresupMock).when(activityABMPry).findViewById(R.id.abmPryPresup);
    }
//        doNothing().when(daoMock).update(proyectoMock);

 */

    @Test
    public void proyecto_insert() {
        activityABMPry = new ABMProyectoActivity();
        doReturn("4").when(edtMock).toString();
        doReturn(edtMock).when(edtNombreMock).getText();
        doReturn(edtMock).when(edtHorasMock).getText();
        doReturn(edtMock).when(edtPresupMock).getText();
        when(proyectoMock.getHoras()).thenReturn(10);
        when(proyectoMock.getPresupuesto()).thenReturn(4000.0);
        doNothing().when(proyectoMock).setNombre(anyString());

        doReturn(1l).when(daoMock).insert(proyectoMock);
        activityABMPry.setProyecto(proyectoMock);
        activityABMPry.setDao(daoMock);
        activityABMPry.setEdtHoras(edtHorasMock);
        activityABMPry.setEdtNombre(edtNombreMock);
        activityABMPry.setEdtPresup(edtPresupMock);
        activityABMPry.msgHandler =msgHandler;
        activityABMPry.saveOrUpdate();
        verify(daoMock, times(1)).insert(proyectoMock);
        verify(daoMock, times(0)).update(proyectoMock);


    }


}
