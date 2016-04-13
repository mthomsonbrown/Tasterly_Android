package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.assertj.android.api.Assertions.assertThat;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowPreferenceManager;
import org.robolectric.util.ActivityController;

/**
 * Created by ookamijin on 9/15/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = TestConfig.roboSdk,
        manifest  = "app/src/main/AndroidManifest.xml")
public class AddBeerActivityTest {
    ActivityController activityController;
    Activity addBeerActivity;


    @Before
    public void setUp() {
        // Need to call start and resume to get the fragment that's been added
        activityController = Robolectric.buildActivity(AddBeerActivity.class).
                create().start().resume().visible();
        addBeerActivity = (Activity) activityController.get();
    }

    @Test
    public void addBeerActivityShouldExistAndHaveATitle() {
        assertThat(addBeerActivity).isNotNull();
        assertThat(addBeerActivity).hasTitle("AddBeerActivity");
    }

    @Test
    public void shouldBeAbleToGetWheelFragmentFromAddBeerFragmentIGuess() {
        Fragment fragAddBeer = addBeerActivity.getFragmentManager().
                findFragmentByTag(addBeerActivity.getString(R.string.AddBeerAlphaFragmentTag));
        assertThat(fragAddBeer).isNotNull();

        Button buttonFlavorWheel = (Button) addBeerActivity.
                findViewById(R.id.button_flavor_wheel);
        assertThat(buttonFlavorWheel).isNotNull();

        Fragment fragFlavorWheel = addBeerActivity.getFragmentManager().
                findFragmentByTag(addBeerActivity.getString(R.string.FlavorWheelAlphaFragment));
        assertThat(fragFlavorWheel).isNull();

        buttonFlavorWheel.performClick();
        fragFlavorWheel = addBeerActivity.getFragmentManager().
                findFragmentByTag(addBeerActivity.getString(R.string.FlavorWheelAlphaFragment));
        assertThat(fragFlavorWheel).isNotNull();
        
    }
}
