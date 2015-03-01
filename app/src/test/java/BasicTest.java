package com.slashandhyphen.tasterly;

import android.widget.Button;

import com.slashandhyphen.tasterly.HomeActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.Exception;
import java.lang.String;

import static org.assertj.android.api.Assertions.assertThat;

    @RunWith(RobolectricTestRunner.class)
    public class BasicTest {


        private HomeActivity homeActivity;

        @Before
        public void setup () {
            homeActivity = Robolectric.buildActivity(HomeActivity.class).create().get();
        }

        @Test
        public void TestTest() throws Exception {
            Button testView = (Button) homeActivity.findViewById(R.id.viewBeerButton);
            assertThat(testView).hasText("View Beerss");
        }
}