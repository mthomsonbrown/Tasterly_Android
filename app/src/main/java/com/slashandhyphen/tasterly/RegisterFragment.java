package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
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

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RegisterFragment extends Fragment {
    protected String TAG = "RegisterFragment Log";
    private SharedPreferences mPreferences;
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
                    + " must implement onRegisterSuccessfulListener");
        }
    }

    public void registerNewAccount(View button) {

        User mUser = new User();
        UserData mData = new UserData();

        EditText userEmailField = (EditText) ll.findViewById(R.id.userEmail);
        mData.setEmail(userEmailField.getText().toString());
        EditText userNameField = (EditText) ll.findViewById(R.id.userName);
        mData.setName(userNameField.getText().toString());
        EditText userPasswordField = (EditText) ll.findViewById(R.id.userPassword);
        mData.setPassword(userPasswordField.getText().toString());
        EditText userPasswordConfirmationField = (EditText) ll.findViewById(R.id.userPasswordConfirmation);
        mData.setPasswordConfirmation(userPasswordConfirmationField.getText().toString());

        boolean freaky = false;

        for (int i = 0; i < mData.getEmail().length(); ++i) {
            if((int) mData.getEmail().charAt(i) > 0x7E) {
                freaky = true;
            }
        }

        for (int i = 0; i < mData.getName().length(); ++i) {
            if((int) mData.getName().charAt(i) > 0x7E) {
                freaky = true;
            }
        }

        for (int i = 0; i < mData.getPassword().length(); ++i) {
            if((int) mData.getPassword().charAt(i) > 0x7E) {
                freaky = true;
            }
        }

        //remove Test Params
        mData.setName("Mike");
        mData.setEmail("mikeTest@brown.com");
        mData.setPassword("qwertyui");
        mData.setPasswordConfirmation("qwertyui");

        // User input error checking junk
        if (mData.getEmail().length() == 0 || mData.getName().length() == 0 ||
                mData.getPassword().length() == 0 || mData.getPasswordConfirmation().length() == 0) {
            // input fields are empty
            Toast.makeText(getActivity().getApplicationContext(),
                    "Please complete all the fields",
                    Toast.LENGTH_LONG).show();
        } else if (!mData.getPassword().equals(mData.getPasswordConfirmation())) {
                // password doesn't match confirmation
                Toast.makeText(getActivity().getApplicationContext(),
                        "Your password doesn't match confirmation, check again",
                        Toast.LENGTH_LONG).show();
        } else if (freaky) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Sorry, this app can't handle \"freaky deaky\" characters",
                    Toast.LENGTH_LONG).show();
        }
        else {
            // everything is ok!
            mUser.setData(mData);

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://www.slashandhyphen.com")
                    .build();
            AuthenticationService service =
                    restAdapter.create(AuthenticationService.class);

            service.registerUser(mUser, new Callback<SessionResponse>() {

                @Override
                public void success(SessionResponse result, Response response){

                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString("AuthToken", result.getData().getAuth_token());
                    editor.apply();

                    // launch the HomeActivity and close this one
                    registerSuccessful.onRegisterSuccessful();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(getActivity(), "Probably a 401...", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Retrofit threw error: " + retrofitError.getMessage());
                    Log.d(TAG, "Just printed the error");
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
