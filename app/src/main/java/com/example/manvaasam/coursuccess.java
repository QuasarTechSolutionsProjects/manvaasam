package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class coursuccess extends AppCompatActivity {
   TextView pac;
   TextView gohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursuccess);
        pac = (TextView) findViewById(R.id.pac);
        String manid = getIntent().getExtras().getString("manid");
        if(manid != null){
            pac.setText("Package ID : "+manid);
        }
        else{
            pac.setVisibility(View.INVISIBLE);
        }
        gohome = (TextView) findViewById(R.id.gohome);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(coursuccess.this,courtrack.class));
                finish();
            }
        });
    }
}