package de.fuh.michel.fachpraktikum_wi2022.configurationelements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import de.fuh.michel.fachpraktikum_wi2022.R;

public class ConfigurationElementTestUtils {

    public static void resetProcessFlow() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.action_edit)).perform(click());
        onView(withId(R.id.action_new_process_flow)).perform(click());
        onView(withText(R.string.accept)).perform(click());
        pressBack();
    }

    public static void addParameter(String parameterName, String parameterValue) {
        onView(withId(R.id.action_add_configuration_element)).perform(click());
        onView(withText(R.string.add_parameter)).perform(click());

        enterParameterInput(parameterName, parameterValue);
    }

    public static void enterParameterInput(String parameterName, String parameterValue) {
        onView(withId(R.id.name_input_edit_text)).perform(clearText(), typeText(parameterName));
        onView(withId(R.id.value_input_edit_text)).perform(clearText(), typeText(parameterValue));
        onView(withId(R.id.action_create_edit_configuration_element)).perform(click());

        onView(withText(R.string.tab_text_2)).perform(click());

        onView(withId(R.id.parameter)).check(matches(withText(R.string.parameter)));
        onView(withText(parameterName)).check(matches(isDisplayed()));
        onView(withText(parameterName)).check(matches(isDisplayed()));
    }

    public static void addFlowSource(String name) {
        onView(withId(R.id.action_add_configuration_element)).perform(click());
        onView(withText(R.string.add_flow_source)).perform(click());

        enterFlowSourceInput(name);
    }

    public static void enterFlowSourceInput(String name) {
        onView(withId(R.id.name_input_edit_text)).perform(clearText(), typeText(name));
        onView(withId(R.id.action_create_edit_configuration_element)).perform(click());

        onView(withText(R.string.tab_text_2)).perform(click());

        onView(withText(name)).check(matches(isDisplayed()));
    }

    public static void addFusion(String processor) {
        onView(withId(R.id.action_add_configuration_element)).perform(click());
        onView(withText(R.string.add_fusion)).perform(click());

        enterFusionInput(processor);
    }

    public static void enterFusionInput(String processor) {
        onView(withId(R.id.processor_input_edit_text)).perform(clearText(), typeText(processor));
        onView(withId(R.id.action_create_edit_configuration_element)).perform(click());

        onView(withText(R.string.tab_text_2)).perform(click());

        onView(withText(processor)).check(matches(isDisplayed()));
    }
}
