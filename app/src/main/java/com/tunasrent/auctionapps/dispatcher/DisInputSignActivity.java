package com.tunasrent.auctionapps.dispatcher;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.tunasrent.auctionapps.BuildConfig;
import com.tunasrent.auctionapps.DB.BodyKendaraan;
import com.tunasrent.auctionapps.DB.Ceklist;
import com.tunasrent.auctionapps.DB.InputData;
import com.tunasrent.auctionapps.DB.LinkFoto;
import com.tunasrent.auctionapps.DB.TandaTangan;
import com.tunasrent.auctionapps.LoginActivity;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.util.AsyncTaskCompleteListener;
import com.tunasrent.auctionapps.util.MultiPartRequester;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.YuliYanto;
import com.tunasrent.auctionapps.util.configuration;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class DisInputSignActivity extends AppCompatActivity implements AsyncTaskCompleteListener {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private static final String IMAGE_DIRECTORY = "/mas_temp";

    public static final int REQUEST_IMAGE = 100;
    JSONObject jsonObject;
    JSONArray data;
    JSONObject jo;

    Dialog myDialog;
    UserSessionManager session;
    Toolbar toolbar;
    Calendar myCalendar;

    EditText etTanggalawal;
    EditText etTanggalakhir;
    EditText etPembiayaan;
    Button btnSubmit;
    ImageView ivKonsumen;
    ImageView ivPicpenarikan_diterima;
    ImageView ivPicpenarikan;
    ImageView ivPicpenyimpanan;
    ImageView ivPicpenjualan;

    String position;
    String konsumen;
//    String picPenarikan_diterima;
//    String picPenarikan;
    String picPenyimpanan;
//    String picPenjualan;

    String message,token,username,full_name,email;
    String code;
    String _name, _fullname, _appid, _ccode, _token, _group;

    Boolean cbGrill1_fisikmuka, cbGrill2_fisikmuka;
    Boolean cbLampu1_fisikmuka, cbLampu2_fisikmuka;
    Boolean cbLampusen1_fisikmuka, cbLampusen2_fisikmuka;
    Boolean cbBamper1_fisikmuka, cbBamper2_fisikmuka;
    Boolean cbEmblem1_fisikmuka, cbEmblem2_fisikmuka;
    Boolean cbTanduk1_fisikmuka, cbTanduk2_fisikmuka;

    Boolean cbFootstep1_fisikkiri, cbFootstep2_fisikkiri;
    Boolean cbPintudpn1_fisikkiri, cbPintudpn2_fisikkiri;
    Boolean cbPintublk1_fisikkiri, cbPintublk2_fisikkiri;
    Boolean cbBamper1_fisikkiri,  cbBamper2_fisikkiri;
    Boolean cbFenderblk1_fisikkiri, cbFenderblk2_fisikkiri;
    Boolean cbSpion1_fisikkiri, cbSpion2_fisikkiri;
    Boolean cbEmblem1_fisikkiri, cbEmblem2_fisikkiri;
    Boolean cbBan1_fisikkiri;
    Boolean rbBanstandard1_fisikkiri, rbBanradial1_fisikkiri;

    AppCompatSpinner spBan2_fisikkiri;
    Boolean rbVelgstandard_fisikkiri, rbVelgracing_fisikkiri;
    Boolean cbVelg1_fisikkiri, cbVelg2_fisikkiri;
    Boolean cbDop1_fisikkiri, cbDop2_fisikkiri;

    Boolean cbSpoiler1_fisikblkg, cbSpoiler2_fisikblkg;
    Boolean cbLampu1_fisikblkg, cbLampu2_fisikblkg;
    Boolean cbLampusen1_fisikblkg, cbLampusen2_fisikblkg;
    Boolean cbBamper1_fisikblkg, cbBamper2_fisikblkg;
    Boolean cbEmblem1_fisikblkg, cbEmblem2_fisikblkg;
    Boolean cbKnalpot1_fisikblkg, cbKnalpot2_fisikblkg;

    Boolean cbFoot1_fisikkanan, cbFoot2_fisikkanan;
    Boolean cbPintudpn1_fisikkanan, cbPintudpn2_fisikkanan;
    Boolean cbPintublk1_fisikkanan, cbPintublk2_fisikkanan;
    Boolean cbBamper1_fisikkanan, cbBamper2_fisikkanan;
    Boolean cbFenderblk1_fisikkanan, cbFenderblk2_fisikkanan;
    Boolean cbSpion1_fisikkanan, cbSpion2_fisikkanan;
    Boolean cbEmblem1_fisikkanan, cbEmblem2_fisikkanan;
    Boolean rbBanstandard_fisikkanan, rbBanradial_fisikkanan;
    Boolean cbBan1_fisikkanan;
    AppCompatSpinner spBan2_fisikkanan;
    Boolean rbVelgstandard_fisikkanan, rbVelgracing_fisikkanan;
    Boolean cbVelg1_fisikkanan, cbVelg2_fisikkanan;
    Boolean cbDop1_fisikkanan, cbDop2_fisikkanan;

    Boolean cbKunciktk1_perlengkapan, cbKunciktk2_perlengkapan;
    Boolean cbSpion1_perlengkapan, cbSpion2_perlengkapan;
    Boolean cbJok1_perlengkapan, cbJok2_perlengkapan;
    Boolean cbSarung1_perlengkapan, cbSarung2_perlengkapan;
    Boolean cbSandaran1_perlengkapan, cbSandaran2_perlengkapan;
    Boolean cbKarpet1_perlengkapan, cbKarpet2_perlengkapan;
    Boolean cbPelindung1_perlengkapan, cbPelindung2_perlengkapan;
    Boolean cbSegitiga1_perlengkapan, cbSegitiga2_perlengkapan;
    Boolean cbTool1_perlengkapan, cbTool2_perlengkapan;
    Boolean cbCadangan1_perlengkapan, cbCadangan2_perlengkapan;
    Boolean cbKunciban1_perlengkapan, cbKunciban2_perlengkapan;
    Boolean cbDongkrak1_perlengkapan, cbDongkrak2_perlengkapan;
    Boolean cbAntena1_perlengkapan, cbAntena2_perlengkapan;
    Boolean cbAirbag1_perlengkapan, cbAirbag2_perlengkapan;

    Boolean cbLampukbt1_listrik, cbLampukbt2_listrik;
    Boolean cbWiperkacadpn1_listrik, cbWiperkacadpn2_listrik;
    Boolean cbWiperkacablk1_listrik, cbWiperkacablk2_listrik;
    Boolean cbKlakson1_listrik, cbKlakson2_listrik;
    Boolean cbAlarm1_listrik, cbAlarm2_listrik;
    Boolean cbJam1_listrik, cbJam2_listrik;
    Boolean cbLighter1_listrik, cbLighter2_listrik;
    Boolean rbRadio_listrik, rbTape_listrik, rbCd_listrik;
    EditText etMerk_listrik;
    Boolean cbRadio1_listrik, cbRadio2_listrik;
    Boolean cbPowersup1_listrik, cbPowersup2_listrik;
    Boolean cbSpeaker1_listrik, cbSpeaker2_listrik;
    Boolean cbAc1_listrik, cbAc2_listrik;
    Boolean cbPowerwin1_listrik, cbPowerwin2_listrik;
    Boolean cbCentral1_listrik, cbCentral2_listrik;
    Boolean cbRemote1_listrik, cbRemote2_listrik;
    Boolean cbSpeedo1_listrik, cbSpeedo2_listrik;
    Boolean cbOdometer1_listrik, cbOdometer2_listrik;
    Boolean cbTacho1_listrik,  cbTacho2_listrik;
    Boolean cbAccu1_listrik, cbAccu2_listrik;

    Boolean cbMesin1_lain, cbMesin2_lain;
    Boolean cbHidraulik1_lain, cbHidraulik2_lain;
    Boolean cbGardan1_lain, cbGardan2_lain;
    Boolean cbAs1_lain, cbAs2_lain;
    Boolean cbBak1_lain, cbBak2_lain;
    TextInputEditText etSd_stnk;
    Boolean cbStnk1_lain, cbStnk2_lain;
    TextInputEditText etSd_bukukir;
    Boolean cbBukukir1_lain, cbBukukir2_lain;
    TextInputEditText etSd_ijintrayek;
    Boolean cbTrayek1_lain, cbTrayek2_lain;
    TextInputEditText etSd_ijinusaha;
    Boolean cbUsaha1_lain, cbUsaha2_lain;
    Boolean cbBukumnl1_lain, cbBukumnl2_lain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_input_sign);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tanda Tangan Serah Terima");
        toolbar.setTitleTextColor(Color.WHITE);

//        etTanggalawal = findViewById(R.id.et_tanggalawal);
//        etTanggalakhir = findViewById(R.id.et_tanggalakhir);
        etPembiayaan = findViewById(R.id.et_pembiayaan);
        btnSubmit = findViewById(R.id.btn_submit);
//        ivKonsumen = findViewById(R.id.iv_konsumen);
//        ivPicpenarikan_diterima = findViewById(R.id.iv_picpenarikan_diterima);
//        ivPicpenarikan = findViewById(R.id.iv_picpenarikan);
        ivPicpenyimpanan = findViewById(R.id.iv_picpenyimpanan);
//        ivPicpenjualan = findViewById(R.id.iv_picpenjualan);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        myDialog = new Dialog(this);


//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance(); //creating  database oject

        myCalendar = Calendar.getInstance();
//        etTanggalawal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dpd_start = new DatePickerDialog(DisInputSignActivity.this, datestart, myCalendar.get(Calendar.YEAR),
//                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
////                DatePicker dpStart = dpd_start.getDatePicker();
//                dpd_start.show();
//            }
//        });
//        etTanggalakhir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dpd_end = new DatePickerDialog(DisInputSignActivity.this, dateend, myCalendar.get(Calendar.YEAR),
//                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
////                DatePicker dpStart = dpd_end.getDatePicker();
//                dpd_end.show();
//            }
//        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (etTanggalawal.getText().toString().equals("") || etTanggalakhir.getText().toString().equals("")){
//                    Toast.makeText(DisInputSignActivity.this,"Tanggal wajib diisi.",Toast.LENGTH_LONG).show();
//                }else if (etPembiayaan.getText().toString().equals("")){
//                    Toast.makeText(DisInputSignActivity.this,"No Pembiayaan wajib diisi.",Toast.LENGTH_LONG).show();
                if (ivPicpenyimpanan.getDrawable() == null){
                    Toast.makeText(DisInputSignActivity.this,"Tanda tangan wajib diisi.",Toast.LENGTH_LONG).show();
                }else {

                   sendData();
                }
            }
        });

        getSign();

    }

    private void sendData(){
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();
        String nop1 = results.first().getNopol1();
        String nop2 = results.first().getNopol2();
        String nop3 = results.first().getNopol3();
        final String nop = nop1 + "-" + nop2 + "-" + nop3;

//        String[] tgl1 = etTanggalawal.getText().toString().split("-");
//        final String tglawal = tgl1[2] + "-"+ tgl1[1] + "-" + tgl1[0];
//
//        String[] tgl2 = etTanggalakhir.getText().toString().split("-");
//        final String tglakhir = tgl2[2] + "-" + tgl2[1] + "-" + tgl2[0];

        class SendData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisInputSignActivity.this,"","Processing...",false,false);
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
                hashMap.put(configuration.KEY_STATUS,"1");
                hashMap.put(configuration.KEY_PARENT_STATUS,"3");
                hashMap.put(configuration.KEY_NOPOL,nop);
//                hashMap.put(configuration.KEY_TGLAWAL,tglawal);
//                hashMap.put(configuration.KEY_TGLAKHIR,tglakhir);
//                hashMap.put(configuration.KEY_PEMBIAYAAN,etPembiayaan.getText().toString());
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_NO_BIAYA,hashMap);
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
            message = jsonObject.getString(configuration.TAG_MESSAGE);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(code.equals("200")){
            try {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
                Toast.makeText(DisInputSignActivity.this,"Data Berhasil Di Input",Toast.LENGTH_SHORT).show();

                Intent i=new Intent(DisInputSignActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(DisInputSignActivity.this,"Data Gagal Di Input",Toast.LENGTH_SHORT).show();
            }
        }else if (code.equals("304")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
    private void getSign(){
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();
        String nop1 = results.first().getNopol1();
        String nop2 = results.first().getNopol2();
        String nop3 = results.first().getNopol3();
        final String nop = nop1 + "-" + nop2 + "-" + nop3;
        class GetFoto extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisInputSignActivity.this,"","Loading sign...",false,false);
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
                hashMap.put(configuration.KEY_NOPOL,nop);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_FOTO,hashMap);
                Log.wtf("kirim", s);
                return s;
            }
        }
        GetFoto ge = new GetFoto();
        ge.execute();
    }
    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getString(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
            data = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);
            JSONObject jos = data.getJSONObject(0);

            konsumen = jos.getString("konsumen");
//            picPenarikan_diterima = jos.getString("picPenarikan_diterima");
//            picPenarikan = jos.getString("picPenarikan");
            picPenyimpanan = jos.getString("picPenyimpanan");
//            picPenjualan = jos.getString("picPenjualan");
//            Toast.makeText(DisInputFotoActivity.this,""+jos,Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(code.equals("200")){
//            File dir = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
//            try {
//                FileUtils.deleteDirectory(dir);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (!konsumen.equals("null")){
////                ivFoto1.setBackground(null);
//                Glide.with(this).load(konsumen).into(ivKonsumen);
//            }
//            if (!picPenarikan_diterima.equals("null")){
//                ivPicpenarikan_diterima.setBackground(null);
//                Glide.with(this).load(picPenarikan_diterima).into(ivPicpenarikan_diterima);
//            }
//            if (!picPenarikan.equals("null")){
//                Glide.with(this).load(picPenarikan).into(ivPicpenarikan);
//            }
            try {
                if (!picPenyimpanan.equals("null")){
                    Glide.with(this).load(picPenyimpanan).into(ivPicpenyimpanan);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

//            if (!picPenjualan.equals("null")){
//                Glide.with(this).load(picPenjualan).into(ivPicpenjualan);
//            }

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

//    private DatePickerDialog.OnDateSetListener datestart = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, month);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updatePickerStart();
//        }
//    };
//    private void updatePickerStart() {
//        try {
//            String myFormat = "dd-MM-yyyy";
//            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//            etTanggalawal.setText(sdf.format(myCalendar.getTime()));
//
//        }catch (Exception e){
//
//        }
//    }
//
//    private DatePickerDialog.OnDateSetListener dateend = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, month);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updatePickerEnd();
//        }
//    };
//    private void updatePickerEnd() {
//        try {
//            String myFormat = "dd-MM-yyyy";
//            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//            etTanggalakhir.setText(sdf.format(myCalendar.getTime()));
//
//        }catch (Exception e){
//
//        }
//    }

    private void uploadImageToServer21(final String path) throws IOException, JSONException {
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();
        String nop1 = results.first().getNopol1();
        String nop2 = results.first().getNopol2();
        String nop3 = results.first().getNopol3();
        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(DisInputSignActivity.this)) {
                Toast.makeText(DisInputSignActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, nop);
            data.put(configuration.KEY_POSITION, "21");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(DisInputSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
            //e.printStackTrace();
        }
    }
    private void uploadImageToServer22(final String path) throws IOException, JSONException {
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();
        String nop1 = results.first().getNopol1();
        String nop2 = results.first().getNopol2();
        String nop3 = results.first().getNopol3();
        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(DisInputSignActivity.this)) {
                Toast.makeText(DisInputSignActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, nop);
            data.put(configuration.KEY_POSITION, "22");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(DisInputSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer23(final String path) throws IOException, JSONException {
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();
        String nop1 = results.first().getNopol1();
        String nop2 = results.first().getNopol2();
        String nop3 = results.first().getNopol3();
        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(DisInputSignActivity.this)) {
                Toast.makeText(DisInputSignActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, nop);
            data.put(configuration.KEY_POSITION, "23");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(DisInputSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer24(final String path) throws IOException, JSONException {
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();
        String nop1 = results.first().getNopol1();
        String nop2 = results.first().getNopol2();
        String nop3 = results.first().getNopol3();
        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(DisInputSignActivity.this)) {
                Toast.makeText(DisInputSignActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, nop);
            data.put(configuration.KEY_POSITION, "24");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(DisInputSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer25(final String path) throws IOException, JSONException {
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();
        String nop1 = results.first().getNopol1();
        String nop2 = results.first().getNopol2();
        String nop3 = results.first().getNopol3();
        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(DisInputSignActivity.this)) {
                Toast.makeText(DisInputSignActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, nop);
            data.put(configuration.KEY_POSITION, "25");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(DisInputSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        try {
            YuliYanto.removeSimpleProgressDialog();
//            Toast.makeText(DisInputSignActivity.this,response.toString(),Toast.LENGTH_LONG).show();
            try {
                jsonObject = new JSONObject(response);
                code = jsonObject.getString(configuration.TAG_CODE);
                message = jsonObject.getString(configuration.TAG_MESSAGE);
                data = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (code.equals("200")) {
                File dir = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
                FileUtils.deleteDirectory(dir);
                //Toast.makeText(DisInputSignActivity.this,"Sukses",Toast.LENGTH_SHORT).show();
                jo = data.getJSONObject(0);
                position = jo.getString("position");
                if (position.equals("21")){
//                    konsumen = jo.getString("konsumen");
////                    ivFoto1.setBackground(null);
//                    Glide.with(this).load(konsumen).into(ivKonsumen);
                }else if (position.equals("22")){
//                    picPenarikan_diterima = jo.getString("picPenarikan_diterima");
//                    Glide.with(this).load(picPenarikan_diterima).into(ivPicpenarikan_diterima);
                }else if (position.equals("23")){
//                    picPenarikan = jo.getString("picPenarikan");
//                    Glide.with(this).load(picPenarikan).into(ivPicpenarikan);
                }else if (position.equals("24")){
                    picPenyimpanan = jo.getString("picPenyimpanan");
                    Glide.with(this).load(picPenyimpanan).into(ivPicpenyimpanan);
                }else if (position.equals("25")){
//                    picPenjualan = jo.getString("picPenjualan");
//                    Glide.with(this).load(picPenjualan).into(ivPicpenjualan);
                }
                myDialog.dismiss();


            }else if (code.equals("304")){
                Toast.makeText(DisInputSignActivity.this,message,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(DisInputSignActivity.this,message,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(DisInputSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }

    @Override
    public void onTaskCompleted1(String response, int serviceCode) {

    }

    private void directSetting() {
        Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        startActivity(i);
    }

    public void ShowPopupKonsumen(View v) {
        final TextView tvClose;
        final Button btnClear;
        final Button btnSave;
        myDialog.setContentView(R.layout.popup_sign_dispatcher);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnClear = (Button) myDialog.findViewById(R.id.btn_clear);
        btnSave = (Button) myDialog.findViewById(R.id.btn_save);

        mSignaturePad = (SignaturePad) myDialog.findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                //Toast.makeText(DisInputSignActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                btnSave.setEnabled(true);
                btnClear.setEnabled(true);
            }

            @Override
            public void onClear() {
                btnSave.setEnabled(false);
                btnClear.setEnabled(false);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//                String path = saveImage(thumbnail);

                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                String paths = saveImage(signatureBitmap);

                Toast.makeText(DisInputSignActivity.this,paths,Toast.LENGTH_LONG).show();

                try {
                    uploadImageToServer21(paths);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                if (addJpgSignatureToGallery(signatureBitmap)) {
//                    Toast.makeText(DisInputSignActivity.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(DisInputSignActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
//                }
//                if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
//                    Toast.makeText(DisInputSignActivity.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(DisInputSignActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void ShowPopupPenarikanDiterima(View v) {
        final TextView tvClose;
        final Button btnClear;
        final Button btnSave;
        myDialog.setContentView(R.layout.popup_sign_dispatcher);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnClear = (Button) myDialog.findViewById(R.id.btn_clear);
        btnSave = (Button) myDialog.findViewById(R.id.btn_save);

        mSignaturePad = (SignaturePad) myDialog.findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                btnSave.setEnabled(true);
                btnClear.setEnabled(true);
            }

            @Override
            public void onClear() {
                btnSave.setEnabled(false);
                btnClear.setEnabled(false);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                String paths = saveImage(signatureBitmap);

                try {
                    uploadImageToServer22(paths);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void ShowPopupPenarikan(View v) {
        final TextView tvClose;
        final Button btnClear;
        final Button btnSave;
        myDialog.setContentView(R.layout.popup_sign_dispatcher);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnClear = (Button) myDialog.findViewById(R.id.btn_clear);
        btnSave = (Button) myDialog.findViewById(R.id.btn_save);

        mSignaturePad = (SignaturePad) myDialog.findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                btnSave.setEnabled(true);
                btnClear.setEnabled(true);
            }

            @Override
            public void onClear() {
                btnSave.setEnabled(false);
                btnClear.setEnabled(false);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                String paths = saveImage(signatureBitmap);

                try {
                    uploadImageToServer23(paths);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void ShowPopupPenyimpanan(View v) {
        final TextView tvClose;
        final Button btnClear;
        final Button btnSave;
        myDialog.setContentView(R.layout.popup_sign_dispatcher);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnClear = (Button) myDialog.findViewById(R.id.btn_clear);
        btnSave = (Button) myDialog.findViewById(R.id.btn_save);

        mSignaturePad = (SignaturePad) myDialog.findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                btnSave.setEnabled(true);
                btnClear.setEnabled(true);
            }

            @Override
            public void onClear() {
                btnSave.setEnabled(false);
                btnClear.setEnabled(false);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                String paths = saveImage(signatureBitmap);

                try {
                    uploadImageToServer24(paths);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void ShowPopupPenjualan(View v) {
        final TextView tvClose;
        final Button btnClear;
        final Button btnSave;
        myDialog.setContentView(R.layout.popup_sign_dispatcher);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnClear = (Button) myDialog.findViewById(R.id.btn_clear);
        btnSave = (Button) myDialog.findViewById(R.id.btn_save);

        mSignaturePad = (SignaturePad) myDialog.findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                btnSave.setEnabled(true);
                btnClear.setEnabled(true);
            }

            @Override
            public void onClear() {
                btnSave.setEnabled(false);
                btnClear.setEnabled(false);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                String paths = saveImage(signatureBitmap);

                try {
                    uploadImageToServer25(paths);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(DisInputSignActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        DisInputSignActivity.this.sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
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
