package com.senyan.moron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    void post(String json) throws IOException {
        Log.e("Test", "HD2");
        OkHttpClient client = new OkHttpClient();
        Log.e("Test", "HD3");
        String url = "http://demo4322901.mockable.io/";
        //String json = "{ \"Test\":\"Dummy\" }";


        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("OHNO", "BOOHOO:(");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                Log.e("Response", res);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.e("Test", "HD");
//        try {
//          //  post();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String message) throws IOException {

                //From the received text string you may do string operations to get the required OTP
                //It depends on your SMS format
                Log.e("Message",message);
                Toast.makeText(MainActivity.this,"Message: "+message,Toast.LENGTH_LONG).show();
                post(message);

//                String url = "http://demo4322901.mockable.io/";
//                String json = message;
//
//
//                RequestBody body = RequestBody.create(JSON, json);
//                Request request = new Request.Builder()
//                        .url(url)
//                        .post(body)
//                        .build();
//                Response response = client.newCall(request).execute();
//                String res = response.body().string();
//                Log.e("Response", res);
            }
        });
    }
}
