package de.fuh.michel.fachpraktikum_wi2022.configurationelements;

import static org.junit.Assert.fail;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addFlowSource;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addFusion;
import static de.fuh.michel.fachpraktikum_wi2022.configurationelements.ConfigurationElementTestUtils.addParameter;
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
public class AddConfigurationElementTest {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(MainActivity.class);
    }

    @After
    public void resetAppData() {
        resetProcessFlow();
    }

    @Test
    public void add_parameter_test() {
        addParameter("plugin1.someParameter", "someValue");
    }

    @Test
    public void add_flow_source_test() {
        addFlowSource("flow-source-name");
    }

    @Test
    public void add_fusion_test() {
        addFusion("processor_name");
    }

    @Test
    public void add_mmfg_test() {
        // TODO: this need a way to setup plugin data first
        fail();
    }

    @Test
    public void add_export_test() {
        // TODO
        fail();
    }
}
