package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class searchback extends AppCompatActivity {

    //the URL having the json data
    private static String JSON_URL;

    //listview object
    ListView listView;
    String manid;
    //the package list where we will store all the package objects after parsing json
    List<packages> packagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchback);
        manid = getIntent().getExtras().getString("manid");
        JSON_URL = "http://192.168.29.180:80/manvaasam/search.php?manid="+manid;
        listView = (ListView) findViewById(R.id.listView);
        packagesList = new ArrayList<>();





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                packages packages = packagesList.get(i);
                Toast.makeText(searchback.this, packages.getMid(),Toast.LENGTH_LONG).show();
                trackpackgetdetails tc = new trackpackgetdetails(searchback.this);
                tc.execute(packages.getMid());
            }
        });
        loadHeroList();
    }
    private void loadHeroList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("packages");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a package object and giving them the values from json object
                                packages packages = new packages(heroObject.getString("man_id"), heroObject.getString("stc_id"));

                                //adding the package to packageslist
                                packagesList.add(packages);
                            }

                            //creating custom adapter object
                            ListViewAdapter adapter = new ListViewAdapter(packagesList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}