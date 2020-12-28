package com.example.manvaasam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class createpackage extends AsyncTask<String, Void,String> {
    AlertDialog dialog;
    Context context;
    ProgressDialog progressDialog;
    String msg;
    public Boolean login = false;
    public createpackage(Context context)
    {
        this.context = context;

    }


    @Override
    protected void onPostExecute(String s) {

        progressDialog.dismiss();
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Manvaasam Package");

        if(s.contains("successfully inserted"))
        {
            dialog.setMessage("Package Successfully Created");
            dialog.show();
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),sucess.class);
            context.startActivity(intent_name);
            ((Activity) context).finish();


        }
        else if(s.contains("not successfull")){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            dialog.setMessage("Enter the Values correctly");
            dialog.show();


        }

        else{
//            dialog.setMessage(s);
            dialog.setMessage("Error in Connection please Try after some time Thank you");
            dialog.show();
        }
    }



    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String id = voids[0];
        String fname = voids[1];
        String fmobile = voids[2];
        String fadd = voids[3];
        String fpin = voids[4];
        String tname = voids[5];
        String tmobile = voids[6];
        String tadd = voids[7];
        String tpin = voids[8];
        String amt = voids[9];

        String connstr = "http://192.168.29.180:80/manvaasam/create.php";

        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setDoOutput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("sid","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")
                    +"&&"+URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")
                    +"&&"+URLEncoder.encode("fmobile","UTF-8")+"="+URLEncoder.encode(fmobile,"UTF-8")
                    +"&&"+URLEncoder.encode("fadd","UTF-8")+"="+URLEncoder.encode(fadd,"UTF-8")
                    +"&&"+URLEncoder.encode("fpin","UTF-8")+"="+URLEncoder.encode(fpin,"UTF-8")
                    +"&&"+URLEncoder.encode("tname","UTF-8")+"="+URLEncoder.encode(tname,"UTF-8")
                    +"&&"+URLEncoder.encode("tmobile","UTF-8")+"="+URLEncoder.encode(tmobile,"UTF-8")
                    +"&&"+URLEncoder.encode("tadd","UTF-8")+"="+URLEncoder.encode(tadd,"UTF-8")
                    +"&&"+URLEncoder.encode("tpin","UTF-8")+"="+URLEncoder.encode(tpin,"UTF-8")
                    +"&&"+URLEncoder.encode("amt","UTF-8")+"="+URLEncoder.encode(amt,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }


        return result;
    }
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait.......Logging in");
        progressDialog.setMax(100);
        progressDialog.show();



    }


}

