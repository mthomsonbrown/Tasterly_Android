package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.slashandhyphen.tasterly.Models.SessionResponse;
import com.slashandhyphen.tasterly.Models.User;
import com.slashandhyphen.tasterly.Models.UserData;

import retrofit.*;
import retrofit.client.Response;

public class LoginFragment extends Fragment {

    private SharedPreferences mPreferences;
    Button mLoginButton;

    LinearLayout ll;
    FragmentActivity fa;

    onLoginSuccessfulListener loginSuccessful;

    public interface onLoginSuccessfulListener {
        void onLoginSuccessful();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fa = (FragmentActivity) super.getActivity();
        ll = (LinearLayout) inflater.inflate(R.layout.fragment_login, container, false);

        mPreferences = getActivity().getSharedPreferences("CurrentUser", AuthenticationActivity.MODE_PRIVATE);

        mLoginButton = (Button) ll.findViewById(R.id.loginButton);

        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                login(mLoginButton);
            }
        });
        return ll;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
           loginSuccessful = (onLoginSuccessfulListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onLoginSuccessfulListener");
        }
    }

    public void login(View button) {

        User mUser = new User();
        UserData mData = new UserData();

        EditText userEmailField = (EditText) ll.findViewById(R.id.userEmail);
        mData.setEmail(userEmailField.getText().toString());
        EditText userPasswordField = (EditText) ll.findViewById(R.id.userPassword);
        mData.setPassword(userPasswordField.getText().toString());

        if (mData.getEmail().length() == 0 || mData.getPassword().length() == 0) {
            // input fields are empty
            Toast toast = Toast.makeText(getActivity(), "Please complete all the fields",
                    Toast.LENGTH_LONG);
            toast.show();
        } else {
            mUser.setData(mData);

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(getString(R.string.railsEndpoint))
                    .build();
            AuthenticationService service =
                    restAdapter.create(AuthenticationService.class);

            service.loginUser(mUser, new Callback<SessionResponse>() {
                @Override
                public void success(SessionResponse result, Response response){

                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString("AuthToken", result.getData().getAuth_token());
                    editor.apply();

                    // launch the HomeActivity and close this one
                    loginSuccessful.onLoginSuccessful();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(getActivity(),
                            "Got Error from Server: " + retrofitError.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            });
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
