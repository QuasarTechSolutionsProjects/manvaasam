package com.quasar.manvaasam_logistics;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class insertcourierid extends AsyncTask<String, Void,String> {
    AlertDialog dialog;
    Context context;
    ProgressDialog progressDialog;
    String scanid,id;
    public Boolean login = false;
    public insertcourierid(Context context)
    {
        this.context = context;

    }


    @Override
    protected void onPostExecute(String s) {

        progressDialog.dismiss();
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Welcome To Manvaasam");
        if(s.matches("[0-9]+"))
        {
//            dialog.setMessage(s);
//            dialog.show();
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),coursuccess.class);
            intent_name.putExtra("manid",id);
            context.startActivity(intent_name);
            ((Activity) context).finish();


        }
        else if(s.contains("failed to update")){
            dialog.setMessage("Please try again");
            dialog.show();

        }

        else if(s.contains("Already updated")){
            dialog.setMessage("Already Id updated");
            dialog.show();

   }
        else if(s.contains("enter the values")){
            dialog.setMessage("Please enter the values properly");
            dialog.show();
        }
        else {
            dialog.setMessage("check the internet connection and try again");
            dialog.show();
        }
    }



    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        id = voids[0];
        scanid = voids[1];


        String connstr = "https://manvaasam.com/logistics/fetchinsert.php";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
       
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoInput(true);


            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("manid","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")
                    +"&&"+URLEncoder.encode("scanid","UTF-8")+"="+URLEncoder.encode(scanid,"UTF-8");
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
        progressDialog.setMessage("Please wait.......Fetching details");
        progressDialog.setMax(100);
        progressDialog.show();


    }

}

