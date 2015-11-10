package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.slashandhyphen.tasterly.Database.BeerDB;
import com.slashandhyphen.tasterly.Models.Beer;
import com.slashandhyphen.tasterly.Models.SessionResponse;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ookamijin on 11/9/2015.
 */
public class ViewBeersFragment extends Fragment {
    BeerDB beerDB;
    SimpleCursorAdapter curseAdapter;
    Button uploadButton;
    RelativeLayout rl;
    ButtonHandlerView buttonHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_view_beer, container, false);
        buttonHandler = new ButtonHandlerView();

        beerDB = new BeerDB(getActivity());
        displayListView();

        uploadButton = (Button) rl.findViewById(R.id.upload);
        uploadButton.setOnClickListener(buttonHandler);

        return rl;
    }

    /**
     * I have no idea what this does, but it does it poorly.  I'm calling a DB field in order to
     * generate a cursor to data, rather than having a function inside the DB class that uses
     * the BEER_NAME to generate a cursor
     */
    private void displayListView() {
        String[] columns = new String[] {
                // TODO this is bad
                BeerDB.getBeerName()
        };

        int[] views = new int[] {
                R.id.text_beer_name
        };

        Cursor curse = beerDB.getAllBeers();
        curseAdapter = new SimpleCursorAdapter(
                getActivity(), R.layout.list_item_beer,
                curse,
                columns,
                views,
                0
        );

        ListView listView = (ListView) rl.findViewById(R.id.beer_list);
        listView.setAdapter(curseAdapter);
    }

    class ButtonHandlerView implements  View.OnClickListener {

        /**
         * Right now, this is just uploading beer 1 in the database
         *
         * @param v The button I clicked
         */
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Hit the button", Toast.LENGTH_SHORT).show();

            Beer beer = beerDB.getBeer(1);         // Hardcoded sanity check

            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(getString(R.string.railsEndpoint))
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestInterceptor.RequestFacade request) {
                            String token = getActivity().getSharedPreferences("CurrentUser",
                                    getActivity().MODE_PRIVATE).getString("AuthToken", null);

                            request.addHeader("Authorization", "Token token=" + token);
                        }
                    });

            RestAdapter restAdapter = builder.build();

            AuthenticationService service =
                    restAdapter.create(AuthenticationService.class);

            service.addBeer(beer, new Callback<SessionResponse>() {
                @Override
                public void success(SessionResponse result, Response response) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            result.getSuccess(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            retrofitError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
