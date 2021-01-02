package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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

public class createpack extends AppCompatActivity {
    AlertDialog dialog;
    TextInputEditText fromname,toname,frommob,tomob,fromadd,toadd,frompin,topin,id,amountt;
    TextView submit,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpack);
        id = (TextInputEditText) findViewById(R.id.sid);
        fromname = (TextInputEditText) findViewById(R.id.name);
        toname = (TextInputEditText) findViewById(R.id.toname);
        frommob = (TextInputEditText) findViewById(R.id.mobile);
        fromadd = (TextInputEditText) findViewById(R.id.address);
        frompin = (TextInputEditText) findViewById(R.id.pincode);
        tomob = (TextInputEditText) findViewById(R.id.tomobile);
        toadd = (TextInputEditText) findViewById(R.id.toadd);
        topin = (TextInputEditText) findViewById(R.id.topin);
        submit = (TextView) findViewById(R.id.submit);
        cancel = (TextView) findViewById(R.id.goback);
        amountt = (TextInputEditText) findViewById(R.id.amount);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String result = "";
                String fname = fromname.getText().toString();
                String fmobile = frommob.getText().toString();
                String fadd = fromadd.getText().toString();
                String fpin = frompin.getText().toString();
                String tname = toname.getText().toString();
                String tmobile = tomob.getText().toString();
                String tadd = toadd.getText().toString();
                String tpin = topin.getText().toString();
                int tp = tpin.length();
                int fp = fpin.length();
                String amt = amountt.getText().toString();
                String id = "12345";
                if(!TextUtils.isEmpty(fname)){
                    if((!TextUtils.isEmpty(fmobile)) && (fmobile.length() <= 12)  ){
                        if (!TextUtils.isEmpty(fadd)){
                            if(!TextUtils.isEmpty(fpin) && ( fp == 6 || fp <= 6 || fp > 0) ){
                                if(!TextUtils.isEmpty(tname)){
                                    if ((!TextUtils.isEmpty(tmobile)) && (fmobile.length() <= 12) ){
                                        if(!TextUtils.isEmpty(tadd)){
                                            if (!TextUtils.isEmpty(tpin) &&  tp == 6 ){
                                                if(!TextUtils.isEmpty(amt)){
                                                    createpackage cr = new createpackage(createpack.this);
                                                    cr.execute(id, fname, fmobile, fadd, fpin, tname, tmobile, tadd, tpin, amt);
                                                }
                                                else{
                                                    amountt.setError("Amount should not be empty");
                                                    amountt.requestFocus();
                                                }
                                            }else{
                                                topin.setError("To pin cannot be empty and cannot be greater than 6");
                                                topin.requestFocus();
                                            }
                                        }
                                        else{
                                            toadd.setError("To address cannot be empty");
                                            toadd.requestFocus();
                                        }
                                    }else{
                                        tomob.setError("To mobile should not be empty and it cannot be less than 10");
                                        tomob.requestFocus();
                                    }
                                }else{
                                    toname.setError("To Name should not be empty");
                                    toname.requestFocus();
                                }

                            }
                            else{
                                frompin.setError("From Pincode cannot be empty and cannot be greater than 6");
                                frompin.requestFocus();
                            }
                        }
                        else{
                            fromadd.setError("From Address should not be empty");
                            fromadd.requestFocus();
                        }

                    }
                    else{
                        frommob.setError("From Mobile should not be empty and it cannot be less than 10");
                        frommob.requestFocus();
                    }
                }
                else {
                     fromname.setError("From Name should not be empty");
                     fromname.requestFocus();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(createpack.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}