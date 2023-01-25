package de.fuh.michel.fachpraktikum_wi2022.configurationelements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.fail;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addFlowSource;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addFusion;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addParameter;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.enterFlowSourceInput;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.enterFusionInput;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.enterParameterInput;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.resetProcessFlow;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fuh.michel.fachpraktikum_wi2022.MainActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditConfigurationElementTest {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(MainActivity.class);
    }

    @After
    public void resetAppData() {
        resetProcessFlow();
    }

    @Test
    public void edit_parameter_test() {
        addParameter("edit_parameter_name", "edit_parameter_value");
        onView(withText("edit_parameter_name")).perform(click());
        enterParameterInput("new_parameter_name", "new_parameter_value");
    }

    @Test
    public void edit_flow_source_test() {
        addFlowSource("edit_flow_source_name");
        onView(withText("edit_flow_source_name")).perform(click());
        enterFlowSourceInput("new_flow_source_name");
    }

    @Test
    public void edit_fusion_test() {
        addFusion("edit_processor_name");
        onView(withText("edit_processor_name")).perform(click());
        enterFusionInput("new_processor_name");
    }

    @Test
    public void edit_mmfg_test() {
        // TODO:
        fail();
    }

    @Test
    public void edit_export_test() {
        // TODO:
        fail();
    }
}
