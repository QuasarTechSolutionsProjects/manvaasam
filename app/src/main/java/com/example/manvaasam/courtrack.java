package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class courtrack extends AppCompatActivity {
    TextInputEditText sid;
    FloatingActionButton flobar;
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courtrack);
        sid = (TextInputEditText) findViewById(R.id.sid);
        flobar = (FloatingActionButton) findViewById(R.id.fbar);
        but = (Button) findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emptyuser = " ";
                String emptypass = " ";
                SharedPreferences preferences = getSharedPreferences("logindetails",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("usernameee",emptyuser);
                editor.putString("password",emptypass);
                editor.apply();
                startActivity(new Intent(courtrack.this,login_screen.class));
                finish();
            }
        });
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