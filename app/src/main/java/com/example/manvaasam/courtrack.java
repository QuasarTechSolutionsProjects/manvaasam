package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class courtrack extends AppCompatActivity {
    TextInputEditText sid;
    FloatingActionButton flobar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courtrack);
        sid = (TextInputEditText) findViewById(R.id.sid);
        flobar = (FloatingActionButton) findViewById(R.id.fbar);
        flobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = sid.getText().toString();
                if(TextUtils.isEmpty(id)){
                    sid.setError("enter the id");
                    sid.requestFocus();

                }
                else{
                    getpackagedetails gpd = new getpackagedetails(courtrack.this);
                    gpd.execute(id);
                }

            }
        });

    }
}