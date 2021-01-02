package com.example.manvaasam;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class sucess extends AppCompatActivity {
   TextView pac,gohome,invoice;
   String manid;
   String pdf_url;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);
        pac = (TextView) findViewById(R.id.pac);
        gohome = (TextView) findViewById(R.id.gohome);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sucess.this,MainActivity.class));
                finish();
            }
        });
        manid = getIntent().getExtras().getString("manid");
        if (manid != null){
            pac.setText("Package ID: "+manid);
        }
        invoice = (TextView) findViewById(R.id.invoice);
        invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                downloadpdf(manid);

            }
        });


    }

    public void downloadpdf(String manid)
   {

       pdf_url=new StringBuilder().append("https://quasartechsolutions.in/manvaasam/invoice.php?manid=").append(manid).toString();
       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
       Toast.makeText(sucess.this,"Opening Invoice....",Toast.LENGTH_SHORT).show();
       startActivity(browserIntent);
       finish();
   }
 
}