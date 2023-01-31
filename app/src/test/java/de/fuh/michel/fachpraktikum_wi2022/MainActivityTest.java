package de.fuh.michel.fachpraktikum_wi2022;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void testOnCreate() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class)) {
            MainActivity activity = controller.get();

            activity.onCreate(Bundle.EMPTY);

            // Test that the correct layout is inflated
            assertNotNull(activity.getBinding());

            // Test that the TabPagerAdapter and ViewPager are set up correctly
            assertNotNull(activity.getBinding().viewPager.getAdapter());
            assertEquals(activity.getBinding().viewPager.getAdapter().getCount(), 2);
            assertEquals(activity.getBinding().tabs.getTabCount(), 2);

            // Test that the processFlowViewModel is initialized correctly
            assertNotNull(activity.getProcessFlowViewModel());
        }
    }

    @Test
    public void testOnOptionsItemSelected() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class)) {
            controller.setup();
            MainActivity activity = controller.get();

            MenuItem addDefinitionItem = mock(MenuItem.class);
            when(addDefinitionItem.getItemId()).thenReturn(R.id.action_add_definition);
            assertTrue(activity.onOptionsItemSelected(addDefinitionItem));

            // Test that the correct activity is started when the "Add Definition" menu item is selected
            Intent expectedIntent = new Intent(activity, QrCodeScannerActivity.class);
            ShadowActivity shadowActivity = Shadows.shadowOf(activity);
            Intent actualIntent = shadowActivity.getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());

            // Test that the startCreateActivity method is called with the correct arguments when the other menu items are selected
            MenuItem addParameterItem = mock(MenuItem.class);
            when(addParameterItem.getItemId()).thenReturn(R.id.action_add_parameter);
            assertTrue(activity.onOptionsItemSelected(addParameterItem));
        }
    }

}