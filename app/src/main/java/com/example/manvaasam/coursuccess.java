package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class coursuccess extends AppCompatActivity {
   TextView pac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursuccess);
        pac = (TextView) findViewById(R.id.pac);
        String manid = getIntent().getExtras().getString("manid");
    }
}