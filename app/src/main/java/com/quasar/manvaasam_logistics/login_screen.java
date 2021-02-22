package com.quasar.manvaasam_logistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class login_screen extends AppCompatActivity {
    TextView adminbut,courbut;
    ImageView quasar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        adminbut = (TextView) findViewById(R.id.adminlogbut);
        courbut = (TextView) findViewById(R.id.signinbut);
        quasar  =  (ImageView) findViewById(R.id.quasartech1);
        quasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_screen.this,quasartechweb.class));
            }
        });
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