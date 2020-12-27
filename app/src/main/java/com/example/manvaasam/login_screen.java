package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class login_screen extends AppCompatActivity {
    ImageView adminbut,courbut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        adminbut = (ImageView) findViewById(R.id.adminlogbut);
        courbut = (ImageView) findViewById(R.id.signinbut);
        adminbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_screen.this,signin.class);
                intent.putExtra("choice","12345");
                startActivity(intent);
                finish();
            }
        });
        courbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_screen.this,signin.class);
                intent.putExtra("choice","98765");
                startActivity(intent);
                finish();
            }
        });
    }
}