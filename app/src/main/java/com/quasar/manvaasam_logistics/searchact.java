package com.quasar.manvaasam_logistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class searchact extends AppCompatActivity {
    FloatingActionButton flobar;
    TextInputEditText tx1;
    String mid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchact);
        tx1=(TextInputEditText) findViewById(R.id.sid);
        
        flobar = (FloatingActionButton) findViewById(R.id.fbar);
        flobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mid=tx1.getText().toString();
                Intent i = new Intent(searchact.this, searchpackt.class);
                i.putExtra("M_id",mid);
                startActivity(i);

            }
        });
    }
}