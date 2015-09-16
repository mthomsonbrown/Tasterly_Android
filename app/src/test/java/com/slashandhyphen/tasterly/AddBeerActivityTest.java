package com.slashandhyphen.tasterly;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static org.assertj.android.api.Assertions.assertThat;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by ookamijin on 9/15/2015.
 *
 * Trying to create an initial test to get the bugs worked out with testing...ugh
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, manifest = "app/src/main/AndroidManifest.xml")
public class AddBeerActivityTest {
    AddBeerActivity addBeerActivity;

    @Before
    public void setUp() {
        addBeerActivity  = Robolectric.setupActivity(AddBeerActivity.class);
    }

    @Test
    public void AddBeerActivityShouldExist() {
        assertNotNull(addBeerActivity);


    }
}
