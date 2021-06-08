package com.quasar.manvaasam_logistics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

public class createpack extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AlertDialog dialog;
    TextInputEditText fromname,toname,frommob,tomob,toadd,topin,parcdet,id,amountt;
    TextView submit,cancel;
    String username;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpack);
        id = (TextInputEditText) findViewById(R.id.sid);
        fromname = (TextInputEditText) findViewById(R.id.name);
        toname = (TextInputEditText) findViewById(R.id.toname);
        frommob = (TextInputEditText) findViewById(R.id.mobile);
        toadd = (TextInputEditText) findViewById(R.id.address);
        topin = (TextInputEditText) findViewById(R.id.pincode);
        tomob = (TextInputEditText) findViewById(R.id.tomobile);
        parcdet = (TextInputEditText) findViewById(R.id.parcdet);
        submit = (TextView) findViewById(R.id.submit);
        cancel = (TextView) findViewById(R.id.goback);
        amountt = (TextInputEditText) findViewById(R.id.amount);
        spinner = findViewById(R.id.packselect);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.packageselect, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        SharedPreferences preferences = getSharedPreferences("logindetails",MODE_PRIVATE);
        username = preferences.getString("usernameee","");
        if(
                TextUtils.isEmpty(username) &&
                        username.length() < 0 &&
                        username == null
        ){
            username = "overridded";
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String result = "";
                String fname = fromname.getText().toString();
                String fmobile = frommob.getText().toString();
                String tadd = toadd.getText().toString();
                String tpin = topin.getText().toString();
                String tname = toname.getText().toString();
                String tmobile = tomob.getText().toString();
                String packitem = spinner.getSelectedItem().toString();
                int fp = tpin.length();
                String amt = amountt.getText().toString();
                String parcdett="";
                String id = "12345";
                if(!packitem.equals("Select the Courier Details"))
                {
                if(packitem.equals("Parcel"))
                {
                    parcdett = parcdet.getText().toString();
                }
                else if(packitem.equals("Courier"))
                {
                    parcdett = "NA";
                }
                if(!TextUtils.isEmpty(fname)){
                    if((!TextUtils.isEmpty(fmobile)) && ((fmobile.length() == 10))  ){
                        if (!TextUtils.isEmpty(tadd)){
                            if(!TextUtils.isEmpty(tpin) &&  fp == 6  ){
                                if(!TextUtils.isEmpty(tname)){
                                    if ((!TextUtils.isEmpty(tmobile)) && ((tmobile.length() == 10)) ){
                                                if(!TextUtils.isEmpty(amt)){
                                                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(createpack.this);
                                                    builder.setMessage("Please Confirm before proceed");
                                                    builder.setTitle("Confirmation");
                                                    String finalParcdett = parcdett;
                                                    builder.setPositiveButton("Check Again", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                        }
                                                    }).setNegativeButton("Confirm and Proceed", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            createpackage cr = new createpackage(createpack.this);
                                                            cr.execute(id, fname, fmobile, tname, tmobile, tadd, tpin, packitem, finalParcdett, amt,username);

                                                        }
                                                    });
                                                    androidx.appcompat.app.AlertDialog dialog = builder.create();
                                                    dialog.show();

                                                }
                                                else{
                                                    amountt.setError("Amount should not be empty");
                                                    amountt.requestFocus();
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
                                topin.setError("From Pincode cannot be empty and cannot be greater than 6");
                                topin.requestFocus();
                            }
                        }
                        else{
                            toadd.setError("From Address should not be empty");
                            toadd.requestFocus();
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
                }}
                else{
                    Toast.makeText(getApplicationContext(),"Select a Package Type to Proceed", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text.equals("Parcel"))
        {
            parcdet.setVisibility((View.VISIBLE));
            parcdet.setHint("Enter the Type of Parcel");
        }
        if(text.equals("Courier"))
        {
            parcdet.setVisibility((View.GONE));
        }
        if(text.equals("Select the Courier Details"))
        {
            parcdet.setVisibility((View.GONE));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}