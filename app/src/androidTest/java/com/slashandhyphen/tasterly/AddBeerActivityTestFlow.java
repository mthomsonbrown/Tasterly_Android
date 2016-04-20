package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.widget.Toast;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddBeerActivityTestFlow {
    String TAG = "~~*~~AddBeerActivityTestFlow~~*~~";
    @Rule
    public ActivityTestRule<AddBeerActivity> mActivityRule = new ActivityTestRule<>(AddBeerActivity.class);

    @Test
    public void sanityTest() {
        onView(withId(R.id.add_beer_fragment_container)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void pressingBackInWheelFragmentGoesBackToAddBeerFragment() {
        ViewInteraction addBeerFragment = onView(withId(R.id.fragment_add_beer_alpha));
        addBeerFragment.check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.button_flavor_wheel)).perform(click());
        addBeerFragment.check(ViewAssertions.doesNotExist());
        ViewInteraction wheelFragment = onView(withId(R.id.fragment_flavor_wheel_alpha));
        wheelFragment.check(ViewAssertions.matches(isDisplayed()));
        pressBack();
        wheelFragment.check(ViewAssertions.doesNotExist());
        addBeerFragment.check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void changingOrientationShouldNotCreateMoreInstancesOfFragments() throws InterruptedException {
        // This was failing, but passing before I wrote the test, so I don't know if it exercises
        // the issue correctly.  It's something at least...jeeze

        AddBeerActivity addBeerActivity = mActivityRule.getActivity();
        int backStack = addBeerActivity.getFragmentManager().getBackStackEntryCount();
        for(int i = 0; i < 5; ++i) {
            addBeerActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            addBeerActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        assertEquals(backStack, addBeerActivity.getFragmentManager().getBackStackEntryCount());
    }
}