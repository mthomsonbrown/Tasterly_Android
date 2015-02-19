package com.slashandhyphen.tasterly;

import com.savagelook.android.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.GET;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TASKS_URL = "http://192.168.1.100:3000/api/v1/tasks.json";
    private SharedPreferences mPreferences;
    Button mAddBeerButton;
    Button mViewBeerButton;
    AuthenticationService mAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

        mAddBeerButton = (Button) this.findViewById(R.id.addBeerButton);
        mViewBeerButton = (Button) this.findViewById(R.id.viewBeerButton);

        mAddBeerButton.setOnClickListener(this);
        mViewBeerButton.setOnClickListener(this);
        mAuthService = new AuthenticationService();
    }

    @Override
    public void onClick(View v) {

        if (v == mAddBeerButton) {
            Toast.makeText(this, "Cannot add Beers", Toast.LENGTH_SHORT)
                    .show();

            Intent intent = new Intent(getApplicationContext(), AddBeerActivity.class);
            startActivity(intent);
            finish();
        }
        if (v == mViewBeerButton) {
            //enter retrofit...
            String response = "NOT DATA";
            response = mAuthService.getTasks(mPreferences.getString("AuthToken", ""));
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadTasksFromAPI(String url) {
        GetTasksTask getTasksTask = new GetTasksTask(HomeActivity.this);
        getTasksTask.setMessageLoading("Loading tasks...");
        getTasksTask.execute(url + "?auth_token=" + mPreferences.getString("AuthToken", ""));
    }


    private class GetTasksTask extends UrlJsonAsyncTask {
        public GetTasksTask(Context context) {
            super(context);
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                JSONArray jsonTasks = json.getJSONObject("data").getJSONArray("tasks");
                int length = jsonTasks.length();
                List<String> tasksTitles = new ArrayList<String>(length);

                for (int i = 0; i < length; i++) {
                    tasksTitles.add(jsonTasks.getJSONObject(i).getString("title"));
                }

                ListView tasksListView = (ListView) findViewById(R.id.tasks_list_view);
                if (tasksListView != null) {
                    tasksListView.setAdapter(new ArrayAdapter<String>(HomeActivity.this,
                            android.R.layout.simple_list_item_1, tasksTitles));
                }
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(),
                        Toast.LENGTH_LONG).show();
            } finally {
                super.onPostExecute(json);
            }
        }

    }

    // Authentication asserted before this activity can be viewed
    @Override
    public void onResume() {
        super.onResume();

        if (mPreferences.contains("AuthToken")) {
            loadTasksFromAPI(TASKS_URL);

        } else {
            Intent intent = new Intent(HomeActivity.this,AuthenticationActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

}
