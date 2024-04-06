package com.tunasrent.auctionapps.bp;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.realm.Realm;

public class BpCekFotoActivity extends AppCompatActivity {
    UserSessionManager session;

    Toolbar toolbar;
    Button btnNextnote;
    EditText etPicpenyimpanan;
    EditText etPicPengiriman;

    ImageView ivFoto1;
    ImageView ivFoto2;
    ImageView ivFoto3;
    ImageView ivFoto4;
    ImageView ivFoto5;
    ImageView ivFoto6;
    ImageView ivFoto7;
    ImageView ivFoto8;
    ImageView ivFoto9;
    ImageView ivFoto10;
    ImageView ivFoto11;
    ImageView ivFoto12;
    ImageView ivFoto13;
    ImageView ivFoto14;

    String _name, _fullname, _appid, _ccode, _token, _group;
    String message;
    String code;
    String var_picPenarikanDiterima;
    String var_picPenarikan;
    String var_picPenyimpanan;
    String var_picPenjualan;
    String var_note_picpenarikan;
    String var_note_picpenyimpanan;

    String var_img1;
    String var_img2;
    String var_img3;
    String var_img4;
    String var_img5;
    String var_img6;
    String var_img7;
    String var_img8;
    String var_img9;
    String var_img10;
    String var_img11;
    String var_img12;
    String var_img13;
    String var_img14;
    String nopol;
    String status;

    JSONArray data;
    String note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_cek_foto);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Foto Unit");
        toolbar.setTitleTextColor(Color.WHITE);

        etPicpenyimpanan = findViewById(R.id.et_pic_penyimpanan);
        etPicPengiriman = findViewById(R.id.et_pic_pengiriman);
        btnNextnote = findViewById(R.id.btn_nextnote);
        ivFoto1 = findViewById(R.id.iv_foto1);
        ivFoto2 = findViewById(R.id.iv_foto2);
        ivFoto3 = findViewById(R.id.iv_foto3);
        ivFoto4 = findViewById(R.id.iv_foto4);
        ivFoto5 = findViewById(R.id.iv_foto5);
        ivFoto6 = findViewById(R.id.iv_foto6);
        ivFoto7 = findViewById(R.id.iv_foto7);
        ivFoto8 = findViewById(R.id.iv_foto8);
        ivFoto9 = findViewById(R.id.iv_foto9);
        ivFoto10 = findViewById(R.id.iv_foto10);
        ivFoto11 = findViewById(R.id.iv_foto11);
        ivFoto12 = findViewById(R.id.iv_foto12);
        ivFoto13 = findViewById(R.id.iv_foto13);
        ivFoto14 = findViewById(R.id.iv_foto14);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        Bundle b = getIntent().getExtras();
        if (b != null){
            var_picPenarikanDiterima= b.getString("picpenarikan_diterima");
            var_picPenarikan= b.getString("picpenarikan");
            var_picPenyimpanan= b.getString("picpenyimpanan");
            var_picPenjualan= b.getString("picpenjualan");
            var_note_picpenyimpanan = b.getString("note_picpenyimpanan");

            var_img1= b.getString("img1");
            var_img2= b.getString("img2");
            var_img3= b.getString("img3");
            var_img4= b.getString("img4");
            var_img5= b.getString("img5");
            var_img6= b.getString("img6");
            var_img7= b.getString("img7");
            var_img8= b.getString("img8");
            var_img9= b.getString("img9");
            var_img10= b.getString("img10");
            var_img11= b.getString("img11");
            var_img12= b.getString("img12");
            var_img13= b.getString("img13");
            var_img14= b.getString("img14");
            nopol = b.getString("nopol");
            status = b.getString("status");
//            Toast.makeText(BpCekFotoActivity.this,var_picPenyimpanan,Toast.LENGTH_SHORT).show();

            try {
                if (!var_img1.equals("null")){
                    ivFoto1.setBackground(null);
                    Glide.with(this).load(var_img1).into(ivFoto1);
                }
                if (!var_img2.equals("null")){
                    ivFoto2.setBackground(null);
                    Glide.with(this).load(var_img2).into(ivFoto2);
                }
                if (!var_img3.equals("null")){
                    ivFoto3.setBackground(null);
                    Glide.with(this).load(var_img3).into(ivFoto3);
                }
                if (!var_img4.equals("null")){
                    ivFoto4.setBackground(null);
                    Glide.with(this).load(var_img4).into(ivFoto4);
                }
                if (!var_img5.equals("null")){
                    ivFoto5.setBackground(null);
                    Glide.with(this).load(var_img5).into(ivFoto5);
                }
                if (!var_img6.equals("null")){
                    ivFoto6.setBackground(null);
                    Glide.with(this).load(var_img6).into(ivFoto6);
                }
                if (!var_img7.equals("null")){
                    ivFoto7.setBackground(null);
                    Glide.with(this).load(var_img7).into(ivFoto7);
                }
                if (!var_img8.equals("null")){
                    ivFoto8.setBackground(null);
                    Glide.with(this).load(var_img8).into(ivFoto8);
                }
                if (!var_img9.equals("null")){
                    ivFoto9.setBackground(null);
                    Glide.with(this).load(var_img9).into(ivFoto9);
                }
                if (!var_img10.equals("null")){
                    ivFoto10.setBackground(null);
                    Glide.with(this).load(var_img10).into(ivFoto10);
                }
                if (!var_img11.equals("null")){
                    ivFoto11.setBackground(null);
                    Glide.with(this).load(var_img11).into(ivFoto11);
                }
                if (!var_img12.equals("null")){
                    ivFoto12.setBackground(null);
                    Glide.with(this).load(var_img12).into(ivFoto12);
                }
                if (!var_img13.equals("null")){
                    ivFoto13.setBackground(null);
                    Glide.with(this).load(var_img13).into(ivFoto13);
                }
                if (!var_img14.equals("null")){
                    ivFoto14.setBackground(null);
                    Glide.with(this).load(var_img14).into(ivFoto14);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            etPicpenyimpanan.setText(var_note_picpenyimpanan);
        }
        getData();

        btnNextnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    private void getData(){
        class SendData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BpCekFotoActivity.this,"","Loading data...",false,false);
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
                hashMap.put(configuration.KEY_APPID,_appid);
                hashMap.put(configuration.KEY_CCODE,_ccode);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_NOPOL,nopol);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_SHOW_NOTE,hashMap);
                return s;
            }
        }
        SendData ge = new SendData();
        ge.execute();
    }
    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getString(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
            data = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);
            JSONObject jos = data.getJSONObject(0);

            note = jos.getString("note_picpenarikan");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(code.equals("200")){
            if (note.equals("null") || note.equals("") || note == ""){
                etPicPengiriman.setText("");
                etPicPengiriman.setHint("Enter note here..");
            }else {
                etPicPengiriman.setText(note);
            }

        }else if (code.equals("304")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void sendData(){
        class SendData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BpCekFotoActivity.this,"","Processing...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showRespon(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,_appid);
                hashMap.put(configuration.KEY_CCODE,_ccode);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_STATUS,"2");
                hashMap.put(configuration.KEY_PARENT_STATUS,"2");
                hashMap.put(configuration.KEY_NOPOL,nopol);
                hashMap.put(configuration.KEY_NOTE_PICPENGIRIMAN,etPicPengiriman.getText().toString());
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_UPDATE_NOTE,hashMap);
                return s;
            }
        }
        SendData ge = new SendData();
        ge.execute();
    }
    private void showRespon(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getString(configuration.TAG_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(code.equals("200")){
            Intent i = new Intent(BpCekFotoActivity.this,BpCekSignActivity.class);
            Bundle b = new Bundle();
            b.putString("nopol",nopol);
            b.putString("status",status);
            b.putString("picpenyimpanan",var_picPenyimpanan);
            i.putExtras(b);
            startActivity(i);
        }else if (code.equals("304")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
