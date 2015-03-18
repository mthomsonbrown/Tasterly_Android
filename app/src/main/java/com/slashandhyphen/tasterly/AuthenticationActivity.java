package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class AuthenticationActivity extends ActionBarActivity
    implements WelcomeFragment.onLoginSelectedListener, WelcomeFragment.onRegisterSelectedListener,
    RegisterFragment.onRegisterSuccessfulListener, LoginFragment.onLoginSuccessfulListener {

    FragmentManager fm = getFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.fragment_content);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        if (fragment == null) {

            Log.d("Authentication Acctiviy", "fragment was null");
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_content, new WelcomeFragment());
            ft.commit();
        }
    }

    public void onLoginSelected() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "In welcome fragment clicky button login thingy",
                Toast.LENGTH_SHORT);
        toast.show();

        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_content, new LoginFragment());
        ft.commit();
    }

    public void onRegisterSelected() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "In welcome fragment clicky button register thingy",
                Toast.LENGTH_SHORT);
        toast.show();

        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_content, new RegisterFragment());
        ft.commit();
    }

    public void onRegisterSuccessful() {
        Toast toast = Toast.makeText(getApplicationContext(),
            "In welcome fragment registerated thingy",
            Toast.LENGTH_SHORT);
        toast.show();

        // Finished authentication, go to home
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
        }

    public void  onLoginSuccessful() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "In welcome fragment loginterated thingy",
                Toast.LENGTH_SHORT);
        toast.show();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_authentication, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
