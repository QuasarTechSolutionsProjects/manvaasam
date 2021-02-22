package com.quasar.manvaasam_logistics;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class createpackage extends AsyncTask<String, Void,String> {
    AlertDialog dialog;
    Context context;
    ProgressDialog progressDialog;
    String msg,id;
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

        if(s.matches("^[0-9].*"))
        {
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),sucess.class);
            intent_name.putExtra("manid",s);
            context.startActivity(intent_name);
            ((Activity) context).finish();


        }
        else if(s.contains("Enter the values properly")){
            dialog.setMessage("Enter the Values correctly");
            dialog.show();


        }

        else{
           //dialog.setMessage(s);
            dialog.setMessage("Error in Connection please Try after some time Thank you");
            dialog.show();
        }
    }



    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        id = voids[0];
        String fname = voids[1];
        String fmobile = voids[2];
        String fadd = voids[3];
        String fpin = voids[4];
        String tname = voids[5];
        String tmobile = voids[6];
        String tadd = voids[7];
        String tpin = voids[8];
        String amt = voids[9];
        String cname = voids[10];

        String connstr = "https://quasartechsolutions.in/manvaasam/create.php";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

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
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")
                    +"&&"+URLEncoder.encode("fmobile","UTF-8")+"="+URLEncoder.encode(fmobile,"UTF-8")
                    +"&&"+URLEncoder.encode("fadd","UTF-8")+"="+URLEncoder.encode(fadd,"UTF-8")
                    +"&&"+URLEncoder.encode("fpin","UTF-8")+"="+URLEncoder.encode(fpin,"UTF-8")
                    +"&&"+URLEncoder.encode("tname","UTF-8")+"="+URLEncoder.encode(tname,"UTF-8")
                    +"&&"+URLEncoder.encode("tmobile","UTF-8")+"="+URLEncoder.encode(tmobile,"UTF-8")
                    +"&&"+URLEncoder.encode("tadd","UTF-8")+"="+URLEncoder.encode(tadd,"UTF-8")
                    +"&&"+URLEncoder.encode("tpin","UTF-8")+"="+URLEncoder.encode(tpin,"UTF-8")
                    +"&&"+URLEncoder.encode("amt","UTF-8")+"="+URLEncoder.encode(amt,"UTF-8")
                    +"&&"+URLEncoder.encode("cname","UTF-8")+"="+URLEncoder.encode(cname,"UTF-8");
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
        progressDialog.setMessage("Please wait.......");
        progressDialog.setMax(100);
        progressDialog.show();



    }


}

