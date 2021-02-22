package com.quasar.manvaasam_logistics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class adminseepackdet extends AppCompatActivity {
    TextView tv,submit,goback,fname,fmobile,faddr,fcode,tname,tmobile,taddr,tcode,cid;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminseepackdet);
        fname = (TextView) findViewById(R.id.fromname);
        fmobile = (TextView) findViewById(R.id.mobile);
        faddr = (TextView) findViewById(R.id.add);
        fcode = (TextView) findViewById(R.id.pin);
        tname = (TextView) findViewById(R.id.toname);
        tmobile = (TextView) findViewById(R.id.tomobile);
        taddr = (TextView) findViewById(R.id.toaddr);
        tcode = (TextView) findViewById(R.id.topin);
        cid = (TextView) findViewById(R.id.cid);
        progressDialog = new ProgressDialog(adminseepackdet.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait.......Fetching details");
        progressDialog.setMax(100);
        progressDialog.show();
        fname.setText(getIntent().getExtras().getString("fname"));
        //Toast.makeText(seecorudet.this,getIntent().getExtras().getString("fname"),Toast.LENGTH_SHORT).show();
        fmobile.setText(getIntent().getExtras().getString("fmobile"));
        faddr.setText(getIntent().getExtras().getString("faddr"));
        fcode.setText(getIntent().getExtras().getString("fcode"));
        tname.setText(getIntent().getExtras().getString("tname"));
        tmobile.setText(getIntent().getExtras().getString("tmobile"));
        taddr.setText(getIntent().getExtras().getString("taddr"));
        tcode.setText(getIntent().getExtras().getString("tcode"));
        cid.setText(getIntent().getExtras().getString("cid"));
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        submit = (TextView) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip= ClipData.newPlainText("StcourierId",cid.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(adminseepackdet.this, "Id Copied",Toast.LENGTH_LONG).show();
                startActivity(new Intent(adminseepackdet.this,trackweb.class));
                finish();
            }
        });
    }
}