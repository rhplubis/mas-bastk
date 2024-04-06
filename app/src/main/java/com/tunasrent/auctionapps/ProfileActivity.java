package com.tunasrent.auctionapps;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.tunasrent.auctionapps.util.configuration;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    UserSessionManager session;
    Toolbar toolbar;
    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;

    TextView tvUsername;
    EditText etFullname;
    EditText etPhone;
    EditText etEmail;
    EditText etAddress;
    Button btnUpdate;

    String _name;
    String _fullname;
    String _appid;
    String _ccode;
    String _token;
    String _group;

    int code;
    String message;
    String token;
    String username;
    String full_name;
    String phone;
    String email;
    String address;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");
        toolbar.setTitleTextColor(Color.WHITE);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        tvUsername = findViewById(R.id.tv_username);
        etFullname = findViewById(R.id.et_fullname);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        btnUpdate = findViewById(R.id.btn_update);

        getProfile();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void getProfile(){
        class GetProfile extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ProfileActivity.this,"","Loading profile...",false,false);
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
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_TOKEN,_token);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_PROFILE,hashMap);
                Log.wtf("kirim", s);
                return s;
            }
        }
        GetProfile ge = new GetProfile();
        ge.execute();
    }
    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
            JSONObject result = jsonObject.getJSONObject(configuration.TAG_JSON_DATA);
            JSONObject user = result.getJSONObject(configuration.TAG_DATAUSER);
            username = user.getString(configuration.TAG_USERNAME);
            full_name = user.getString(configuration.TAG_FULLNAME);
            phone = user.getString(configuration.TAG_PHONE);
            email = user.getString(configuration.TAG_EMAIL);
            address = user.getString(configuration.TAG_ADDRESS);
            Log.wtf("cek_msg","hasil" + email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.wtf("codeprof","hasil:" + code);
        if(code == 200){
            tvUsername.setText(username);
            etFullname.setText(full_name);
            etPhone.setText(phone);
            etEmail.setText(email);
            etAddress.setText(address);

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
