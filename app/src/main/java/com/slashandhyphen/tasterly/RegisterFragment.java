package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.savagelook.android.UrlJsonAsyncTask;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class RegisterFragment extends Fragment {

    private final static String REGISTER_API_ENDPOINT_URL = "http://192.168.1.100:3000/api/v1/registrations";
    private SharedPreferences mPreferences;
    private String mUserEmail;
    private String mUserName;
    private String mUserPassword;
    private String mUserPasswordConfirmation;
    Button mRegisterButton;

    LinearLayout ll;
    FragmentActivity fa;
    onRegisterSuccessfulListener registerSuccessful;

    public interface onRegisterSuccessfulListener {
        public void onRegisterSuccessful();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fa = (FragmentActivity) super.getActivity();
        ll = (LinearLayout) inflater.inflate(R.layout.fragment_register, container, false);

        mPreferences = getActivity().getSharedPreferences("CurrentUser", getActivity().MODE_PRIVATE);

        mRegisterButton = (Button) ll.findViewById(R.id.registerButton);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                registerNewAccount(mRegisterButton);
            }
        });
        return ll;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            registerSuccessful = (onRegisterSuccessfulListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onRegisterSelectedListener");
        }
    }

    public void registerNewAccount(View button) {
        EditText userEmailField = (EditText) ll.findViewById(R.id.userEmail);
        mUserEmail = userEmailField.getText().toString();
        EditText userNameField = (EditText) ll.findViewById(R.id.userName);
        mUserName = userNameField.getText().toString();
        EditText userPasswordField = (EditText) ll.findViewById(R.id.userPassword);
        mUserPassword = userPasswordField.getText().toString();
        EditText userPasswordConfirmationField = (EditText) ll.findViewById(R.id.userPasswordConfirmation);
        mUserPasswordConfirmation = userPasswordConfirmationField.getText().toString();

        boolean freaky = false;

        for (int i = 0; i < mUserEmail.length(); ++i) {
            if((int) mUserEmail.charAt(i) > 0x7E) {
                freaky = true;
            }
        }

        for (int i = 0; i < mUserName.length(); ++i) {
            if((int) mUserName.charAt(i) > 0x7E) {
                freaky = true;
            }
        }

        for (int i = 0; i < mUserPassword.length(); ++i) {
            if((int) mUserPassword.charAt(i) > 0x7E) {
                freaky = true;
            }
        }


        // User input error checking junk
        if (mUserEmail.length() == 0 || mUserName.length() == 0 ||
                mUserPassword.length() == 0 || mUserPasswordConfirmation.length() == 0) {
            // input fields are empty
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                    "Please complete all the fields",
                    Toast.LENGTH_LONG);
            toast.show();
        } else if (!mUserPassword.equals(mUserPasswordConfirmation)) {
                // password doesn't match confirmation
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Your password doesn't match confirmation, check again",
                        Toast.LENGTH_LONG);
                toast.show();
        } else if (freaky) {
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                    "Sorry, this app can't handle \"freaky deaky\" characters",
                    Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            // everything is ok!
            RegisterTask registerTask = new RegisterTask(getActivity());
            registerTask.setMessageLoading("Registering new account...");
            registerTask.execute(REGISTER_API_ENDPOINT_URL);
        }
    }

    private class RegisterTask extends UrlJsonAsyncTask {
        public RegisterTask(Context context) {
            super(context);
        }

        @Override
        protected JSONObject doInBackground(String... urls) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(urls[0]);
            JSONObject holder = new JSONObject();
            JSONObject userObj = new JSONObject();
            String response = null;
            JSONObject json = new JSONObject();

            try {
                try {
                    // setup the returned values in case
                    // something goes wrong
                    json.put("success", false);
                    json.put("info", "Something went wrong. Retry!");

                    // add the users' info to the post params
                    userObj.put("email", mUserEmail);
                    userObj.put("name", mUserName);
                    userObj.put("password", mUserPassword);
                    userObj.put("password_confirmation", mUserPasswordConfirmation);
                    holder.put("user", userObj);
                    StringEntity se = new StringEntity(holder.toString());
                    post.setEntity(se);

                    // setup the request headers
                    post.setHeader("Accept", "application/json");
                    post.setHeader("Content-Type", "application/json");

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    response = client.execute(post, responseHandler);
                    json = new JSONObject(response);

                } catch (HttpResponseException e) {
                    e.printStackTrace();
                    Log.e("ClientProtocol", "" + e);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("IO", "" + e);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("JSON", "" + e);
            }

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                if (json.getBoolean("success")) {
                    // everything is ok
                    SharedPreferences.Editor editor = mPreferences.edit();
                    // save the returned auth_token into
                    // the SharedPreferences
                    editor.putString("AuthToken", json.getJSONObject("data").getString("auth_token"));
                    editor.apply();

                    // return to calling activity
                    registerSuccessful.onRegisterSuccessful();
                }
                Toast.makeText(context, json.getString("info"), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                // something went wrong: show a Toast
                // with the exception message
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                super.onPostExecute(json);
            }
        }
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
