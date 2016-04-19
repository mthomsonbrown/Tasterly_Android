package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.support.test.espresso.NoActivityResumedException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static junit.framework.Assert.assertTrue;

/**
 * Created by ookamijin on 4/18/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityTestFlow {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void sanityTest() {
        onView(withId(R.id.activity_home)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void homeToAddBeerToBackShouldShowHome() {
        onView(withId(R.id.activity_home)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.addBeerButton)).perform(click());
        onView(withId(R.id.fragment_add_beer_alpha)).check(ViewAssertions.matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.activity_home)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void pressingBackInHomeActivityAfterCallingAddBeerActivityShouldExitApp() {
        onView(withId(R.id.addBeerButton)).perform(click());
        onView(withId(R.id.fragment_add_beer_alpha)).check(ViewAssertions.matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.activity_home)).check(ViewAssertions.matches(isDisplayed()));
        try {
            pressBack();
        }
        catch (NoActivityResumedException e) {
            assertTrue(e.getMessage().equals("Pressed back and killed the app"));
        }
    }
}
