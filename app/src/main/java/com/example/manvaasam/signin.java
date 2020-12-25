package com.example.manvaasam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class signin extends AppCompatActivity {
   EditText username,pass;
   ImageView signbut;
   String user,passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        username = (EditText) findViewById(R.id.username);
        pass  = (EditText) findViewById(R.id.password);
        signbut = (ImageView) findViewById(R.id.signinbut);
        signbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                passwd = pass.getText().toString();
                new AlertDialog.Builder(signin.this)
                        .setTitle("details")
                        .setMessage("Details = "+user+" "+passwd+"click ok to continue")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(signin.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();

            }
        });
    }
}