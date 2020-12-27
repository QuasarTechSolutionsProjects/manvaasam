package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
                final ProgressDialog progressDialog = new ProgressDialog(createpack.this);
                progressDialog.setMessage("Validating.....Please wait");
                progressDialog.setCancelable(false);
                progressDialog.show();
                String result = "";
                String fname = fromname.getText().toString();
                String fmobile = frommob.getText().toString();
                String fadd = fromadd.getText().toString();
                String fpin = frompin.getText().toString();
                String tname = toname.getText().toString();
                String tmobile = tomob.getText().toString();
                String tadd = toadd.getText().toString();
                String tpin = topin.getText().toString();
                String amt = amountt.getText().toString();
                String id = "12345";
                createpackage cr = new createpackage(createpack.this);
                cr.execute(id,fname,fmobile,fadd,fpin,tname,tmobile,tadd,tpin,amt);
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