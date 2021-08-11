package com.quasar.manvaasam_logistics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    TextView img1,img2,img3,totamt,amt;
    Button logout;
    private FirebaseAnalytics mFirebaseAnalytics;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        img1 = (TextView) findViewById(R.id.create);
        img2 = (TextView) findViewById(R.id.track);
        img3 = (TextView) findViewById(R.id.search);
        logout = (Button) findViewById(R.id.button);
        totamt=(TextView) findViewById(R.id.totalamt);
        amt=(TextView) findViewById(R.id.amt);
        mQueue = Volley.newRequestQueue(this);

//       Code to Get the Total Amount gathered by the user from the database through Volley API
        SharedPreferences shared = getSharedPreferences("logindetails", MODE_PRIVATE);
        String channel = (shared.getString("usernameee", ""));
        String URL = new StringBuilder().append("https://www.manvaasam.com/logistics/mtotamt.php?logid=").append(channel).toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            JSONArray jsonArray = response.getJSONArray("packages");
                                JSONObject employee = jsonArray.getJSONObject(0);
                                int totalamt = employee.getInt("totpack");
                                totamt.append( "Total Packages Collected By "+channel + "\n\n" );
                                amt.append(" "+String.valueOf(totalamt) + "\n\n");
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emptyuser = null;
                String emptypass = null;
                SharedPreferences preferences = getSharedPreferences("logindetails",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("usernameee",emptyuser);
                editor.putString("password",emptypass);
                editor.putString("type",emptypass);
                editor.apply();
                startActivity(new Intent(MainActivity.this,login_screen.class));
                finish();
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,createpack.class);
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,trackpack.class);
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(MainActivity.this,searchact.class));
            }
        });

        
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);

        urlConnection.setDoOutput(true);

        urlConnection.connect();

        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);
        urlConnection.disconnect();

        return new JSONObject(jsonString);
    }

}