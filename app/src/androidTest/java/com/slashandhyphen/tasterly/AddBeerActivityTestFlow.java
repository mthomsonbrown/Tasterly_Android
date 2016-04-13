package com.slashandhyphen.tasterly;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddBeerActivityTestFlow {

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
}