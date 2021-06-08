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
    TextView tv,goback,fname,fmobile,tname,tmobile,taddr,tcode,ptype,amt,cid,stcour,proff,mettur;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminseepackdet);
        fname = (TextView) findViewById(R.id.fromname);
        fmobile = (TextView) findViewById(R.id.mobile);
        taddr = (TextView) findViewById(R.id.add);
        tcode = (TextView) findViewById(R.id.pin);
        tname = (TextView) findViewById(R.id.toname);
        tmobile = (TextView) findViewById(R.id.tomobile);
        ptype = (TextView) findViewById(R.id.ptype);
        amt = (TextView) findViewById(R.id.amt);
        cid = (TextView) findViewById(R.id.courid);
        progressDialog = new ProgressDialog(adminseepackdet.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait.......Fetching details");
        progressDialog.setMax(100);
        progressDialog.show();
        fname.setText(getIntent().getExtras().getString("fname"));
        //Toast.makeText(seecorudet.this,getIntent().getExtras().getString("fname"),Toast.LENGTH_SHORT).show();
        fmobile.setText(getIntent().getExtras().getString("fmobile"));
        tname.setText(getIntent().getExtras().getString("tname"));
        tmobile.setText(getIntent().getExtras().getString("tmobile"));
        taddr.setText(getIntent().getExtras().getString("taddr"));
        tcode.setText(getIntent().getExtras().getString("tcode"));
        ptype.setText(getIntent().getExtras().getString("packtype"));
        amt.setText("Rs "+getIntent().getExtras().getString("amt"));
        cid.setText(getIntent().getExtras().getString("cid"));
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        stcour = (TextView) findViewById(R.id.stcour);
        stcour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip= ClipData.newPlainText("CourierId",cid.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(adminseepackdet.this, "Id Copied",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(adminseepackdet.this,trackweb.class);
                String URL="http://www.erpstcourier.com/awb_tracking2.php";
                intent.putExtra("url",URL);
                startActivity(intent);
                finish();
            }
        });

        proff = (TextView) findViewById(R.id.professional);
        proff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip= ClipData.newPlainText("CourierId",cid.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(adminseepackdet.this, "Id Copied",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(adminseepackdet.this,trackweb.class);
                String URL="https://www.tpcglobe.com/";
                intent.putExtra("url",URL);
                startActivity(intent);
                finish();
            }
        });

        mettur= (TextView) findViewById(R.id.mettur);
        mettur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip= ClipData.newPlainText("CourierId",cid.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(adminseepackdet.this, "Id Copied",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(adminseepackdet.this,trackweb.class);
                String URL="https://www.metturtransports.com/";
                intent.putExtra("url",URL);
                startActivity(intent);
                finish();
            }
        });
    }
}