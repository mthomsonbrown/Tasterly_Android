package com.slashandhyphen.tasterly;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import static org.assertj.android.api.Assertions.assertThat;

/**
 * Created by ookamijin on 1/1/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = TestConfig.roboSdk, manifest = "app/src/main/AndroidManifest.xml")
public class RegisterFragmentTest {
    RegisterFragment regFrag;
    TestData testData;

    @Before
    public void setUp() {
        regFrag  = new RegisterFragment();
        testData = TestData.getInstance();
    }

    @Test
    public void ShouldBeAbleToRegister() {
//        assertThat(regFrag.ll).isNotNull();
//
//        EditText userEmailField = (EditText) regFrag.ll.findViewById(R.id.userEmail);
//        EditText userNameField = (EditText) regFrag.ll.findViewById(R.id.userName);
//        EditText userPasswordField = (EditText) regFrag.ll.findViewById(R.id.userPassword);
//        EditText userPasswordConfirmationField = (EditText) regFrag.ll.findViewById(R.id.userPasswordConfirmation);
//
//        userEmailField.setText(testData.getUserEmail());
//        userNameField.setText(testData.getUserUsername());
//        userPasswordField.setText(testData.getUserPassword());
//        userPasswordConfirmationField.setText(testData.getUserPasswordConfirmation());
//
//        //Try to register
//        regFrag.registerNewAccount(regFrag.ll.findViewById(R.id.registerButton));
//        System.out.println("About to Shadowloop");
//        ShadowLooper.getMainLooper();
//        System.out.println("Shadowlooping");
//
//        assertThat(ShadowToast.getLatestToast()).isEqualTo("Fucked up");

    }
}
