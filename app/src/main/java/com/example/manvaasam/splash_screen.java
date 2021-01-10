package com.example.manvaasam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class splash_screen extends AppCompatActivity {
   Handler handler;
   Boolean checknet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        checknet = checkconnection();
        if(checknet == Boolean.TRUE){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {



                    Intent intent = new Intent(splash_screen.this, login_screen.class);
                    startActivity(intent);
                    finish();


            }
        },3000);

        }
        else{
           new AlertDialog.Builder(this)
                   .setTitle("Connection Failure")
                   .setMessage("Please connect to internet")
                   .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
//                          Intent intent = new Intent(splash_screen.this,splash_screen.class);
//                          startActivity(intent);
//                          finish();
                           System.exit(0);
                           finish();
                       }
                   })
                   .show();
        }

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