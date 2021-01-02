package com.example.manvaasam;

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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class getpackagedetails extends AsyncTask<String, Void,String> {
    AlertDialog dialog;
    Context context;
    String id;
    ProgressDialog progressDialog;
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
        if(s.contains("incorrect"))
        {
            dialog.setMessage("Id is incorrect");
            dialog.show();
        }
        else if(s.contains("no record")){
            dialog.setMessage("Check the id");
            dialog.show();
        }


        else{
            try {
                   JSONObject jsonresult = new JSONObject(s);
                   String fname = jsonresult.getString("fname");
                   String fmobile = jsonresult.getString("fmobile");
                   String faddr = jsonresult.getString("faddr");
                   String fcode = jsonresult.getString("fcode");
                   String toname = jsonresult.getString("tname");
                   String tomobile = jsonresult.getString("tmobile");
                   String taddr = jsonresult.getString("taddr");
                   String tcode = jsonresult.getString("tcode");
                   if(fname.contains("false") &&
                           fmobile.contains("false") &&
                           faddr.contains("false") &&
                           fcode.contains("false") &&
                           toname.contains("false") &&
                           tomobile.contains("false") ){
                       dialog.setMessage("Please Enter the Correct Id or Package Already Updated");
                       dialog.show();

                   }

                   else if(
                           fname != null  &&
                           fmobile != null &&
                           faddr !=null &&
                           fcode != null &&
                           toname != null &&
                           tomobile != null &&
                           taddr != null &&
                           tcode != null
                   )
                   {
                      Intent intent = new Intent(context.getApplicationContext(),seecorudet.class);
                      intent.putExtra("fname",fname);
                       intent.putExtra("fmobile",fmobile);
                       intent.putExtra("faddr",faddr);
                       intent.putExtra("fcode",fcode);
                       intent.putExtra("tname",toname);
                       intent.putExtra("tmobile",tomobile);
                       intent.putExtra("taddr",taddr);
                       intent.putExtra("tcode",tcode);
                       intent.putExtra("manid",id);
                       context.startActivity(intent);
                       ((Activity) context).finish();

                   }
                   else{
                       dialog.setMessage("Contact Manvaasam team Fields are Missing");
                       dialog.show();
                   }



            }catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }



    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        id = voids[0];


        String connstr = "https://quasartechsolutions.in/manvaasam/fetch.php";
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

