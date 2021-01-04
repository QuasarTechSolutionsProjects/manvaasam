package com.example.manvaasam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class signin extends AppCompatActivity {
   EditText username,pass;
   ImageView signbut,quasar;
   String user,passwd;
   String value;
   Boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        value = getIntent().getExtras().getString("choice");
        username = (EditText) findViewById(R.id.username);
        pass  = (EditText) findViewById(R.id.password);
        quasar = (ImageView) findViewById(R.id.quasartech1);
        SharedPreferences preferences = getSharedPreferences("logindetails",MODE_PRIVATE);
        String usernamee = preferences.getString("usernameee","");
        String password =  preferences.getString("password","");
        if(!TextUtils.isEmpty(usernamee) && !TextUtils.isEmpty(password)){
            Toast.makeText(signin.this,"Welcome Back" ,Toast.LENGTH_SHORT).show();
            background bg = new background(signin.this);
            bg.execute(usernamee,password,value);
        }


        quasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin.this,quasartechweb.class));
            }
        });
        signbut = (ImageView) findViewById(R.id.signinbut);
        signbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                passwd = pass.getText().toString();
                check = checkconnection();
                if(check == Boolean.TRUE){
                    if(!TextUtils.isEmpty(user)){
                        if(!TextUtils.isEmpty(passwd)){
                            SharedPreferences preferences = getSharedPreferences("logindetails",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("usernameee",user);
                            editor.putString("password",passwd);
                            editor.apply();
                            background bg = new background(signin.this);
                            bg.execute(user,passwd,value);
                        }
                        else{
                            pass.setError("password field cannot be Empty");
                            pass.requestFocus();
                        }
                    }else{
                        username.setError("Username field cannot be empty");
                        username.requestFocus();
                    }

                }
                else{
                     new AlertDialog.Builder(signin.this)
                            .setTitle("Connection Failure")
                            .setMessage("Please connect to internet")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }

            }
        });

    }
    public  boolean checkconnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo network = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wifi.isConnected() || network.isConnected()){
            return true;
        }
        else{
            return false;
        }

    }
}