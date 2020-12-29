package com.example.manvaasam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

public class seecorudet extends AppCompatActivity {
   Button scabtn;
   TextView tv,submit,goback,fname,fmobile,faddr,fcode,tname,tmobile,taddr,tcode;
   TextInputEditText scanid;
   String w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seecorudet);
        scabtn = (Button) findViewById(R.id.scanbtn);
        scanid = (TextInputEditText) findViewById(R.id.scanid);
        scabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
       goback = (TextView) findViewById(R.id.goback);
       goback.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(seecorudet.this,courtrack.class));
               finish();
           }
       });
     submit = (TextView) findViewById(R.id.submit);
     submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

         }
     });
     fname = (TextView) findViewById(R.id.fromname);
     fmobile = (TextView) findViewById(R.id.mobile);
     faddr = (TextView) findViewById(R.id.address);
     fcode = (TextView) findViewById(R.id.pincode);
     tname = (TextView) findViewById(R.id.toname);
     tmobile = (TextView) findViewById(R.id.tomobile);
     taddr = (TextView) findViewById(R.id.toaddr);
     tcode = (TextView) findViewById(R.id.topin);

    }
    private void scanCode()
    {
        IntentIntegrator integrator=new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(result.getContents());
            builder.setTitle("Scanned Result:");
            builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    scanCode();
                }
            }).setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(seecorudet.this, result.getContents(), Toast.LENGTH_SHORT).show();
                    scanid.setText(result.getContents());

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            Toast.makeText(this, "No Results Found", Toast.LENGTH_SHORT).show();
        }


    }
}