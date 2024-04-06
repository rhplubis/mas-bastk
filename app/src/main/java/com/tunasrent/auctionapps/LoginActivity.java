package com.tunasrent.auctionapps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tunasrent.auctionapps.mobilisasi.MobListActivity;
import com.tunasrent.auctionapps.util.configuration;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends Activity {

    UserSessionManager session;
    EditText etUsername;
    EditText etPassword;
    TextView tvVersion;
    Button btnLogin;
    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;
    String message,token,group,username,full_name,email,pool;
    int code;
    String versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String _name = user.get(UserSessionManager.SES_NAME);
        String _fullname = user.get(UserSessionManager.SES_FULLNAME);
        String _appid = user.get(UserSessionManager.SES_APPID);
        String _ccode = user.get(UserSessionManager.SES_CCODE);
        String _token = user.get(UserSessionManager.SES_TOKEN);
        String _group = user.get(UserSessionManager.SES_GROUP);

        //jika session TRUE, maka akan langsung masuk ke MainActivity
        if (session.isUserLoggedIn()==true){
//            if (getIntent().getExtras() != null){
//                Toast.makeText(LoginActivity.this,"ada",Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(LoginActivity.this,"tidak ada",Toast.LENGTH_SHORT).show();
//            }
            try {
                //jika dari notif
                int ver = 0;
                for (String key : getIntent().getExtras().keySet()){
                    if (key.equals("type")){
                        Log.d("key_title:","masuk type");
                        String jenis = getIntent().getExtras().getString(key);
                        if (jenis.toString().equals("3")){
                            ver = 1;
                            Log.d("key_title:","sukses taksasi");
                            Intent i =new Intent(LoginActivity.this,MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Bundle b = new Bundle();
                            b.putString("page","taksasi");
                            i.putExtras(b);
                            startActivity(i);
                            finish();
                        }else if (jenis.toString().equals("5")){
                            ver = 1;
                            Log.d("key_title:","sukses monilisasi");
                            Intent i =new Intent(LoginActivity.this,MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Bundle b = new Bundle();
                            b.putString("page","mobilisasi");
                            i.putExtras(b);
                            startActivity(i);
                            finish();
                        }
                    }
                }
                if (ver == 0){
                    Log.d("key_title:","0");
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }catch (Exception e){
                //bukan dari notif
                Log.d("key_title:","else");
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }

        tvVersion = (TextView) findViewById(R.id.tv_version);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        //versi aplikasi HP
        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionName = pinfo.versionName;
        tvVersion.setText("Version " + versionName);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().equals("")){
                    etUsername.setError("Please enter username");
                }else if (etPassword.getText().toString().equals("")){
                    etPassword.setError("Please enter password");
                }else {
                    getData();
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
//                    finish();
                }
            }
        });

//        String tes = "B-2345-NMK";
//        String[] tes_ = tes.split("-");
//        String tes1 = tes_[0];
//        String tes2 = tes_[1];
//        String tes3 = tes_[2];
//
//        Toast.makeText(LoginActivity.this,"tes1="+tes1+", Tes2="+tes2 + ", Tes3="+tes3,Toast.LENGTH_LONG).show();
    }

    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this,"","Checking Username & Password...",false,false);
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
                hashMap.put(configuration.KEY_USERNAME,etUsername.getText().toString());
                hashMap.put(configuration.KEY_PASSWORD,etPassword.getText().toString());
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
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);

            if(code == 200){
                JSONObject result = jsonObject.getJSONObject(configuration.TAG_JSON_DATA);
                token = result.getString(configuration.TAG_TOKEN);
                JSONObject user = result.getJSONObject(configuration.TAG_DATA_USER);
                username = user.getString(configuration.TAG_USER);
                full_name = user.getString(configuration.TAG_NAME);
                email = user.getString(configuration.TAG_USER_EMAIL);
                group = user.getString(configuration.TAG_USER_GROUP);
                pool = user.getString(configuration.KEY_POOL_ID);
                Log.wtf("cek_msg","hasil" + email);

                //get token Firebase
                FirebaseMessaging.getInstance().subscribeToTopic("Tunas");
                FirebaseInstanceId.getInstance().getToken();
                final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                Log.wtf("cektoken", "Refreshed token: " + refreshedToken);
                sendRegistrationToServer(refreshedToken);

                class sendTokenFirebase extends AsyncTask<Void,Void,String>{
                    ProgressDialog loading;
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(LoginActivity.this,"","Verifying...",false,false);
                    }
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();

                    }
                    @SuppressLint("WrongThread")
                    @Override
                    protected String doInBackground(Void... params) {
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put(configuration.KEY_APPID,appid);
                        hashMap.put(configuration.KEY_CCODE,ccode);
                        hashMap.put(configuration.KEY_TOKEN,token);
                        hashMap.put(configuration.TAG_USER_ID,etUsername.getText().toString());
                        hashMap.put(configuration.TAG_TOKEN_FIREBASE,refreshedToken);
                        RequestHandler rh = new RequestHandler();

                        String s = rh.sendPostRequest(configuration.URL_REQDEVICE,hashMap);
                        Log.wtf("sendfirebase","hasil" + s);
                        return s;
                    }
                }
                sendTokenFirebase ge = new sendTokenFirebase();
                ge.execute();


                session.createUserLoginSession(username,full_name,appid,ccode,token,group,pool);
                Toast.makeText(this, "Welcome to Tunas Auction", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();

            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(LoginActivity.this,"Please Check Your Connection",Toast.LENGTH_SHORT).show();
        }

        Log.wtf("cekcode","hasil" + code);

    }

    private void sendRegistrationToServer(String refreshedToken) {
    }
}
