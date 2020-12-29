package com.example.manvaasam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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


public class getpackagedetails extends AsyncTask<String, Void,String> {
    AlertDialog dialog;
    Context context;
    ProgressDialog progressDialog;
    String msg,name;
    public Boolean login = false;
    public getpackagedetails(Context context)
    {
        this.context = context;

    }


    @Override
    protected void onPostExecute(String s) {

        progressDialog.dismiss();
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Welcome To Manvaasam");
        if(s.contains("successfully login"))
        {
            dialog.setMessage(s);
            dialog.show();
//            Intent intent_name = new Intent();
//            intent_name.setClass(context.getApplicationContext(),MainActivity.class);
//            context.startActivity(intent_name);
//            ((Activity) context).finish();


        }
        else if(s.contains("incorrect id")){
            dialog.setMessage("Incorrect Id");
            dialog.show();

        }

        else{

            dialog.setMessage(name);
            dialog.show();
            //dialog.setMessage(h);

            //dialog.setMessage("Error in Connection please Try after some time Thank you");

        }
    }



    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String id = voids[0];


        String connstr = "http://192.168.29.180:80/manvaasam/fetch.php";

        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);


            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("manid","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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

