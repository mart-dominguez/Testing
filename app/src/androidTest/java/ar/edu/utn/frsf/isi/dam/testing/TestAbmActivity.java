package ar.edu.utn.frsf.isi.dam.testing;

import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestAbmActivity {

        @Rule
        public ActivityTestRule<ABMProyectoActivity> mActivityRule =
                new ActivityTestRule<>(ABMProyectoActivity.class);

        @Test
        public void existeTextViewNombreProyecto() {
            onView(withText("Nombre del Proyecto")).check(matches(isDisplayed()));
        }

    @Test
    public void crearProyecto() {
        onView(withId(R.id.abmPryNombre)).perform(typeText("Proyecto espresso")  );
        onView(withId(R.id.abmPryPresup)).perform(typeText("4000.0")  );
        onView(withId(R.id.abmPryHoras)).perform(typeText("8")  );
        onView(withId(R.id.abmPryBtnGuardar)).perform(click());
        SystemClock.sleep(2500);
        onView(withId(R.id.abmPryNombre)).check(matches(withText("")));
    }
}
