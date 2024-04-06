package com.tunasrent.auctionapps.dispatcher;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tunasrent.auctionapps.LoginActivity;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    UserSessionManager session;
    Toolbar toolbar;
    Calendar myCalendar;
    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;

    EditText etTanggalawal;
    EditText etTanggalakhir;
    EditText etPembiayaan;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Send");
        toolbar.setTitleTextColor(Color.WHITE);

        etTanggalawal = findViewById(R.id.et_tanggalawal);
        etTanggalakhir = findViewById(R.id.et_tanggalakhir);
        etPembiayaan = findViewById(R.id.et_pembiayaan);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Main2Activity.this,"","Checking Username & Password...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showData(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,appid);
                hashMap.put(configuration.KEY_CCODE,ccode);
//                hashMap.put(configuration.KEY_TOKEN,_token);
//                hashMap.put(configuration.KEY_USERNAME,etUsername.getText().toString());
//                hashMap.put(configuration.KEY_PASSWORD,etPassword.getText().toString());
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_LOGIN,hashMap);
                Log.wtf("kirim", s);
                return s;
            }
        }
        GetData ge = new GetData();
        ge.execute();
    }
    private void showData(String json){
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            code = jsonObject.getInt(configuration.TAG_CODE);
//            message = jsonObject.getString(configuration.TAG_MESSAGE);
//            JSONObject result = jsonObject.getJSONObject(configuration.TAG_JSON_DATA);
//            token = result.getString(configuration.TAG_TOKEN);
//            JSONObject user = result.getJSONObject(configuration.TAG_DATA_USER);
//            username = user.getString(configuration.TAG_USER);
//            full_name = user.getString(configuration.TAG_NAME);
//            email = user.getString(configuration.TAG_USER_EMAIL);
//            Log.wtf("cek_msg","hasil" + email);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            //Toast.makeText(LoginActivity.this,"Not Connected",Toast.LENGTH_SHORT).show();
//        }
//
//        Log.wtf("cekcode","hasil" + code);
//        if(code == 200){
//
//
//        }else {
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//        }
    }
}
