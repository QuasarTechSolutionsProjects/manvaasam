package com.example.manvaasam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class searchact extends AppCompatActivity {
    FloatingActionButton flobar;
    TextInputEditText tx1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchact);
        tx1=(TextInputEditText) findViewById(R.id.sid);
        flobar = (FloatingActionButton) findViewById(R.id.fbar);
        flobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id  = tx1.getText().toString();
                if(!TextUtils.isEmpty(id)){
                    Intent intent = new Intent(searchact.this,searchback.class);
                    intent.putExtra("manid",id);
                    startActivity(intent);
                }else{
                    tx1.setError("Searh Id should not be empty");
                    tx1.requestFocus();
                }

            }
        });
    }
}