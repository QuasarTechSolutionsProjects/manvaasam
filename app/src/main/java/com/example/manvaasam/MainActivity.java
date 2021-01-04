package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    TextView img1,img2,img3;
    Button logout;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        img1 = (TextView) findViewById(R.id.create);
        img2 = (TextView) findViewById(R.id.track);
        img3 = (TextView) findViewById(R.id.search);
        logout = (Button) findViewById(R.id.button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emptyuser = null;
                String emptypass = null;
                SharedPreferences preferences = getSharedPreferences("logindetails",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("usernameee",emptyuser);
                editor.putString("password",emptypass);
                editor.putString("type",emptypass);
                editor.apply();
                startActivity(new Intent(MainActivity.this,login_screen.class));
                finish();
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,createpack.class);
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,trackpack.class);
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(MainActivity.this,searchact.class));
            }
        });

        
    }
}