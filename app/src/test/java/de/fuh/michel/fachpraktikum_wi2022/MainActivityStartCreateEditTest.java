package de.fuh.michel.fachpraktikum_wi2022;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.ParameterizedRobolectricTestRunner;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;

import java.util.Arrays;
import java.util.Collection;

import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;

@RunWith(ParameterizedRobolectricTestRunner.class)
public class MainActivityStartCreateEditTest {

    @ParameterizedRobolectricTestRunner.Parameter
    public int menuItemId;

    @ParameterizedRobolectricTestRunner.Parameter(1)
    public String configurationElementType;

    @ParameterizedRobolectricTestRunner.Parameters
    public static Collection<Object[]> startCreateActivityTestData() {
        return Arrays.asList(new Object[][]{
                {R.id.action_add_parameter, Parameter.CONFIGURATION_ELEMENT_TYPE},
                {R.id.action_add_flow_source, FlowSource.CONFIGURATION_ELEMENT_TYPE},
                {R.id.action_add_fusion, Fusion.CONFIGURATION_ELEMENT_TYPE},
                {R.id.action_add_mmfg, Mmfg.CONFIGURATION_ELEMENT_TYPE},
                {R.id.action_add_export, Export.CONFIGURATION_ELEMENT_TYPE}
        });
    }

    private MainActivity activity;

    @Before
    public void setUp() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class)) {
            controller.setup();
            activity = controller.get();
        }
    }

    @Test
    public void testStartCreateActivity() {
        Class<?> expectedActivity = CreateEditConfigurationElementActivity.class;
        MenuItem menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(menuItemId);

        activity.onOptionsItemSelected(menuItem);

        // Test that the correct Intent is created and started
        Intent expectedIntent = new Intent(activity, expectedActivity);
        expectedIntent.putExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT_TYPE, configurationElementType);

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
        assertEquals(expectedIntent.getStringExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT_TYPE),
                actualIntent.getStringExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT_TYPE));
    }

}