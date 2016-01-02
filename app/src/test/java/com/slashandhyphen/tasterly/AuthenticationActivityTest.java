package com.slashandhyphen.tasterly;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import static org.assertj.android.api.Assertions.assertThat;
import android.widget.Button;

import static org.junit.Assert.assertEquals;


/**
 * Created by ookamijin on 1/1/2016.
 * Tests for AuthenticationActivity
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = TestConfig.roboSdk, manifest = "app/src/main/AndroidManifest.xml")
public class AuthenticationActivityTest {
    TestData testData;
    AuthenticationActivity authActivity;

    @Before
    public void setUp() {
        authActivity = Robolectric.buildActivity(AuthenticationActivity.class).create().get();
        testData = TestData.getInstance();
    }

    // Convenience methods written after tests
    private RegisterFragment getRegisterFragment() {
        assertThat(authActivity.getCurrentFragment()).isInstanceOf(WelcomeFragment.class);
        Button button = (Button)authActivity.findViewById(R.id.beginRegisterButton);
        button.performClick();
        return (RegisterFragment)authActivity.getCurrentFragment();
    }

    @Test
    public void FirstFragmentShouldBeWelcomeFragment() {
        assertThat(authActivity).isNotNull();
        assertThat(authActivity.getCurrentFragment()).isInstanceOf(WelcomeFragment.class);
    }

    @Test
    public void PressingRegisterShouldInstantiateRegisterFragment() {
        assertThat(authActivity.getCurrentFragment()).isInstanceOf(WelcomeFragment.class);
        Button button = (Button)authActivity.findViewById(R.id.beginRegisterButton);
        button.performClick();
        assertThat(authActivity.getCurrentFragment()).isInstanceOf(RegisterFragment.class);
    }

    @Test
    public void ShouldBeAbleToRegisterNewUser() {
        RegisterFragment regFrag = getRegisterFragment();

        // Using epoch time for uniqueness
        String email = testData.getUserEmail() + System.currentTimeMillis();
        regFrag.userEmailField.setText(email);
        regFrag.userNameField.setText(testData.getUserUsername());
        regFrag.userPasswordField.setText(testData.getUserPassword());
        regFrag.userPasswordConfirmationField.setText(testData.getUserPasswordConfirmation());

        Button button = (Button)authActivity.findViewById(R.id.registerButton);
        button.performClick();

        /**
         * TODO This is acting weird.  It's not hitting the backend when I just run the tests
         * automatically.  It does hit the backend when I stop and walk through it, though.  I'm
         * hearing a lot about Fake Http on the net for Robolectric, like it intercepts your request
         * or something, but I can't seem to find how to instantiate that to turn it off, maybe it
         * was removed?
         *
         * Anyway, I'll call this depth good for covering the client, and move on for now.  I'll
         * have to figure this out though, for testing http negatives.
         *
         */
        assertEquals("Robolectric", "Doesn't intercept http requests");
    }


}
