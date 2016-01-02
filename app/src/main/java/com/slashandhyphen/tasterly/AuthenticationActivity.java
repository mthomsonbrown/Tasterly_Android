package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class AuthenticationActivity extends ActionBarActivity
    implements WelcomeFragment.onLoginSelectedListener, WelcomeFragment.onRegisterSelectedListener,
    RegisterFragment.onRegisterSuccessfulListener, LoginFragment.onLoginSuccessfulListener {

    private FragmentManager fm = getFragmentManager();
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, new WelcomeFragment());
            ft.commit();
        }
    }

    public void onLoginSelected() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, new LoginFragment());
        ft.commit();
    }

    public void onRegisterSelected() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, new RegisterFragment());
        ft.commit();
    }

    public void onRegisterSuccessful() {
        // Finished authentication, go to home
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
        }

    public void  onLoginSuccessful() {
        // Finished authentication, go to home
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 0) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            finish();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }

    public Fragment getCurrentFragment() {
        fm.executePendingTransactions();
        return fragment = fm.findFragmentById(R.id.fragment_container);
    }
}
