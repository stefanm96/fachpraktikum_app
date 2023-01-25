package de.fuh.michel.fachpraktikum_wi2022.configurationelements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.fail;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addFlowSource;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addFusion;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addParameter;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.resetProcessFlow;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fuh.michel.fachpraktikum_wi2022.MainActivity;
import de.fuh.michel.fachpraktikum_wi2022.R;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class RemoveConfigurationElementTest {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(MainActivity.class);
    }

    @After
    public void resetAppData() {
        resetProcessFlow();
    }

    @Test
    public void remove_parameter_test() {
        addParameter("remove_parameter", "this");

        onView(withText("remove_parameter")).perform(click());
        onView(ViewMatchers.withId(R.id.action_delete_configuration_element)).perform(click());
        onView(withText(R.string.accept)).perform(click());

        onView(withId(R.id.configuration_element_list))
                .check(matches(not(hasDescendant(withText(R.string.parameter)))));
    }

    @Test
    public void remove_flow_source_test() {
        addFlowSource("remove_flow_source");

        onView(withText("remove_flow_source")).perform(click());
        onView(ViewMatchers.withId(R.id.action_delete_configuration_element)).perform(click());
        onView(withText(R.string.accept)).perform(click());

        onView(withId(R.id.configuration_element_list))
                .check(matches(not(hasDescendant(withText(R.string.flow_source)))));
    }

    @Test
    public void remove_fusion_test() {
        addFusion("remove_fusion");

        onView(withText("remove_fusion")).perform(click());
        onView(ViewMatchers.withId(R.id.action_delete_configuration_element)).perform(click());
        onView(withText(R.string.accept)).perform(click());

        onView(withId(R.id.configuration_element_list))
                .check(matches(not(hasDescendant(withText(R.string.fusion)))));
    }

    @Test
    public void remove_mmfg_test() {
        // TODO:
        fail();
    }

    @Test
    public void remove_export_test() {
        // TODO:
        fail();
    }
}
