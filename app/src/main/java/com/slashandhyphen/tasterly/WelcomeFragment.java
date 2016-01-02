package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.app.Activity;



public class WelcomeFragment extends Fragment {

    LinearLayout ll;
    FragmentActivity fa;
    onLoginSelectedListener loginSelected;
    onRegisterSelectedListener registerSelected;

    public interface onLoginSelectedListener {
        void onLoginSelected();
    }

    public interface onRegisterSelectedListener {
        void onRegisterSelected();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fa = (FragmentActivity) super.getActivity();
        ll = (LinearLayout) inflater.inflate(R.layout.fragment_welcome, container, false);

        ll.findViewById(R.id.beginRegisterButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // No account, load new account view
                        registerSelected.onRegisterSelected();
                    }
                });

        ll.findViewById(R.id.loginButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Existing Account, load login view
                        loginSelected.onLoginSelected();
                    }
                });

        return ll;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            loginSelected = (onLoginSelectedListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onLoginSelectedListener");
        }
        try {
            registerSelected = (onRegisterSelectedListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onRegisterSelectedListener");
        }
    }

}
