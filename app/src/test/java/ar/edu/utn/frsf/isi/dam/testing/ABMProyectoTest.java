package ar.edu.utn.frsf.isi.dam.testing;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.testing.dao.ProyectoDao;
import ar.edu.utn.frsf.isi.dam.testing.modelo.Proyecto;
import ar.edu.utn.frsf.isi.dam.testing.modelo.ProyectoConTareas;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ABMProyectoTest {

    ABMProyectoActivity  activityABMPry;

    public class ProyectoDaoFalso implements ProyectoDao {
        public void update(Proyecto p){
            System.out.println("EJECUTO UPDATE con "+p);
        }

        @Override
        public void delete(Proyecto r) {

        }

        @Override
        public List<Proyecto> getAll() {
            return null;
        }

        @Override
        public Proyecto getById(int pIdProyecto) {
            return null;
        }

        @Override
        public List<ProyectoConTareas> buscarPorIdConTareas(long pIdPry) {
            return null;
        }

        public long insert(Proyecto p){
            System.out.println("EJECUTO insert con "+p);
            return 1l;
        }
    }
    ProyectoDao miDaoFalso = new ProyectoDaoFalso();

    @Mock
    Proyecto proyectoMock;


    @Test
    public void proyecto_ratio_correcto() {
        when(proyectoMock.getHoras()).thenReturn(10);
        when(proyectoMock.getPresupuesto()).thenReturn(4000.0);
        activityABMPry = new ABMProyectoActivity();
        activityABMPry.setProyecto(proyectoMock);
        assertTrue(activityABMPry.ratioValido());
    }

    @Test
    public void proyecto_ratio_menor100() {
        activityABMPry = new ABMProyectoActivity();
        Proyecto unProyecto = new Proyecto();
        unProyecto.setPresupuesto(150.0);
        unProyecto.setHoras(100);
        activityABMPry.setProyecto(unProyecto);
        assertFalse(activityABMPry.ratioValido());
    }

    @Test
    public void proyecto_ratio_mayor1000() {
        activityABMPry = new ABMProyectoActivity();
        Proyecto unProyecto = new Proyecto();
        unProyecto.setPresupuesto(15000.0);
        unProyecto.setHoras(10);
        activityABMPry.setProyecto(unProyecto);
        assertFalse(activityABMPry.ratioValido());
    }

    @Test
    public void proyecto_ratio_ok() {
        activityABMPry = new ABMProyectoActivity();
        Proyecto unProyecto = new Proyecto();
        unProyecto.setPresupuesto(3500.0);
        unProyecto.setHoras(20);
        activityABMPry.setProyecto(unProyecto);
        assertTrue(activityABMPry.ratioValido());
    }

    @Test
    public void proyecto_update() {
        activityABMPry = new ABMProyectoActivity();
        Proyecto unProyecto = new Proyecto();
        unProyecto.setId(1);
        unProyecto.setPresupuesto(3500.0);
        unProyecto.setHoras(20);
        activityABMPry.setProyecto(unProyecto);
        activityABMPry.setDao(miDaoFalso);
        assertTrue(true);
    }

    @Test
    public void proyecto_insert() {
        activityABMPry = new ABMProyectoActivity();
        Proyecto unProyecto = new Proyecto();
        unProyecto.setId(0);
        unProyecto.setPresupuesto(3500.0);
        unProyecto.setHoras(20);
        activityABMPry.setProyecto(unProyecto);
        activityABMPry.setDao(miDaoFalso);
        activityABMPry.saveOrUpdate();
        assertTrue(true);
    }


}
