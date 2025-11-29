package com.example.myapplication;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addBrew_isDisplayedInList() {
        final String TEST_NAME = "Espresso Test Drink";
        final String TEST_NOTES = "Added via automated UI test.";

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.edit_brew_name))
                .perform(typeText(TEST_NAME), closeSoftKeyboard());

        onView(withId(R.id.edit_brew_rating))
                .perform(typeText("4.9"), closeSoftKeyboard());

        onView(withId(R.id.edit_brew_notes))
                .perform(typeText(TEST_NOTES), closeSoftKeyboard());

        onView(withId(R.id.button_save)).perform(click());

        onView(withText(TEST_NAME)).check(matches(isDisplayed()));

        onView(withText(TEST_NOTES)).check(matches(isDisplayed()));
    }
}