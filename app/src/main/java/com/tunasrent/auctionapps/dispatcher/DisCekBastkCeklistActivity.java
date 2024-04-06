package com.tunasrent.auctionapps.dispatcher;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.bp.BpCekFormActivity;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DisCekBastkCeklistActivity extends AppCompatActivity {
    UserSessionManager session;
    String _name, _fullname, _appid, _ccode, _token, _group;
    Toolbar toolbar;

    String data;
    String message;
    int code;
    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;

    LinearLayout layout;
    LinearLayout lyName;
    LinearLayout layout2;
    LinearLayout lyName2;

    String nopol;
    String rename;

    Button btnNext;
    ScrollView svParent, svChild;

    JSONObject result;
    String cekHasil = "";
    String cekData = "not";

    int con,con_bp;
    ArrayList str;
    ArrayList strCheckbox;
    Dialog myDialog;
    WebView web_view;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_cek_bastk_ceklist);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        getSupportActionBar().setTitle("Ceklist Unit");
        toolbar.setTitleTextColor(Color.WHITE);

        layout = findViewById(R.id.layout_name);
        lyName = findViewById(R.id.ly_name);
        layout2 = findViewById(R.id.layout_name2);
        lyName2 = findViewById(R.id.ly_name2);
        svParent = findViewById(R.id.sv_parent);
        svChild = findViewById(R.id.sv_child);

        btnNext = findViewById(R.id.btn_next);
//        svParent = findViewById(R.id.sv_parent);
//        svChild = findViewById(R.id.sv_child);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        Bundle b = getIntent().getExtras();
        nopol = b.getString("parse_nopol");
        getSupportActionBar().setTitle("Ceklist Unit (" + nopol + ")");

        myDialog = new Dialog(this);
        web_view = new WebView(this);


        svChild.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                svParent.scrollTo(svChild.getScrollX(),svChild.getScrollY());
            }
        });
        svParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("kirim ",cekData);
                if (cekData.equals("ada")){
                    sendUpdate();
                }else {
                    sendUpdate2();
                    Log.d("nama",_name + " " +_token + " " + nopol);
                }
//                String dikirim = null;
//                for (int i = 0; i < str.size();i++){
//                    dikirim = str.get(i).toString();
//                    Toast.makeText(DisCekBastkCeklistActivity.this,"params : " + dikirim,Toast.LENGTH_LONG).show();
//                }
//                Toast.makeText(DisCekBastkCeklistActivity.this,"status : " + strCheckbox,Toast.LENGTH_SHORT).show();
            }
        });

        getBastk();
//        Log.d("cekya",cekData);
//        if (cekData.contains("")){
//            Toast.makeText(DisCekBastkCeklistActivity.this,"Kosong",Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(DisCekBastkCeklistActivity.this,"Isi",Toast.LENGTH_SHORT).show();
//        }
    }

    private void sendUpdate(){
        class SendUpdate extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisCekBastkCeklistActivity.this,"","Updating...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showUpdate(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,appid);
                hashMap.put(configuration.KEY_CCODE,ccode);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_VEHICLEID,nopol);
                hashMap.put(configuration.KEY_KODE,"DIS");

//                ArrayList array1 = null;
                String dikirim = null;

                for (int i = 0; i < str.size();i++){
                    dikirim = str.get(i).toString();
                    String[] seplit = dikirim.split("-");
//                    array1.add(seplit[0]);

                    hashMap.put(seplit[0],seplit[1]);
//                    Toast.makeText(DisCekBastkCeklistActivity.this,"params : " + dikirim,Toast.LENGTH_LONG).show();
                }
                Log.d("datanya", str.toString());

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_RECHECK_DISP,hashMap);
                Log.wtf("dikirimya", s);
                return s;
            }
        }
        SendUpdate ge = new SendUpdate();
        ge.execute();
    }
    private void sendUpdate2(){
        class SendUpdate2 extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisCekBastkCeklistActivity.this,"","Updating...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showUpdate2(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,appid);
                hashMap.put(configuration.KEY_CCODE,ccode);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_VEHICLEID,nopol);
                hashMap.put(configuration.KEY_KODE,"DIS");

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_RECHECK_DISP,hashMap);
                Log.wtf("dikirimya", s);
                return s;
            }
        }
        SendUpdate2 ge = new SendUpdate2();
        ge.execute();
    }
    private void showUpdate(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);

            if(code == 200){
//                data = jsonObject.getString(configuration.TAG_JSON_DATA);
                Log.d("datanya", "showUpdate: "+data);
                Toast.makeText(DisCekBastkCeklistActivity.this,"Update Sukses",Toast.LENGTH_LONG).show();

//              ShowPopupPdf();
//
                Intent i=new Intent(DisCekBastkCeklistActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }else if (code == 304){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(BpCekFormActivity.this,"catch",Toast.LENGTH_LONG).show();
        }
    }
    private void showUpdate2(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);

            if(code == 200){
//                data = jsonObject.getString(configuration.TAG_JSON_DATA);
                Log.d("datanya", "showUpdate: "+data);
                Toast.makeText(DisCekBastkCeklistActivity.this,"Update Sukses",Toast.LENGTH_LONG).show();

//                ShowPopupPdf();
                Intent i=new Intent(DisCekBastkCeklistActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }else if (code == 304){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(BpCekFormActivity.this,"catch",Toast.LENGTH_LONG).show();
        }
    }


    private void ShowPopupPdf() {
        final TextView tvClose;
        myDialog.setContentView(R.layout.popup_showpdf);
        web_view = myDialog.findViewById(R.id.web_view);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading PDF...");
        progressDialog.setCancelable(false);

        web_view.requestFocus();

        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setAllowFileAccess(true);
//        web_view.getSettings().setSupportZoom(true);
//        web_view.getSettings().setBuiltInZoomControls(true);
//        web_view.getSettings().setUseWideViewPort(true);
//        web_view.getSettings().setLoadWithOverviewMode(false);


//        String doc="<iframe src='http://docs.google.com/viewer?url=http://www.iasted.org/conferences/formatting/presentations-tips.ppt&embedded=true' width='100%' height='100%' style='border: none;'></iframe>";
//        String myPdfUrl = "https://ss-tunasrental.tunasgroup.com/mas-api/images/files/BASTK_DIS_D-1638-VCB_2019-11-05 21:11:34.pdf";
        String myPdfUrl = data;
        String url = "https://docs.google.com/viewer?embedded=true&url="+myPdfUrl;
        //String url = myPdfUrl;

        web_view.loadUrl(url);
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        web_view.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                Intent i=new Intent(DisCekBastkCeklistActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void getBastk(){
        class GetBastk extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisCekBastkCeklistActivity.this,"","Loading...",false,false);
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
                hashMap.put(configuration.KEY_VEHICLEID,nopol);
                hashMap.put(configuration.KEY_MODE,"1");

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_GETDETAIL_BASTK,hashMap);
                Log.wtf("kirim", s);
                return s;
            }
        }
        GetBastk ge = new GetBastk();
        ge.execute();
    }
    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);

            if(code == 200){
                result = jsonObject.getJSONObject(configuration.TAG_JSON_DATA);
                cekHasil = result.toString();
                Log.d("cekhasil1 ",cekHasil);

                if (cekHasil.contains("{\"dispatcher\":[],\"bp\":[]}") || cekHasil.contains("{\"dispatcher\":\"match\",\"bp\":\"match\"}") || cekHasil.contains("{\"dispatcher\":null,\"bp\":null}") ){
                    cekData = "not";
                }else {
                    cekData = "ada";
                }

                JSONObject dispatcher = result.getJSONObject(configuration.TAG_JSON_DISPATCHER);
                JSONObject bp = result.getJSONObject(configuration.TAG_JSON_BP);

//              =================================== Checklist BP =======================================================================
                con_bp = bp.length();
                LinearLayout checkBoxLayout2 = new LinearLayout(this);
                checkBoxLayout2.setOrientation(LinearLayout.VERTICAL);

                LinearLayout textViewLayout2 = new LinearLayout(this);
                textViewLayout2.setOrientation(LinearLayout.VERTICAL);

                layout2.addView(checkBoxLayout2);
                lyName2.addView(textViewLayout2);

                for (int i =0; i < con_bp; i++) {
                    CheckBox checkBox1 = new CheckBox(this);
                    CheckBox checkBox2 = new CheckBox(this);
                    TextView textView2 = new TextView(this);

                    String upperNama1;
                    String upperNama2;
                    String upperNama;
                    String namaCekbox = null;
                    int namaColor1 = 0;
                    int namaColor2 = 0;
                    int namaColor3 = 0;

                    String nama = bp.names().get(i).toString();

                    if (nama.indexOf("_") != -1){
                        String[] split_nama = nama.split("_");
                        //res_split = split_nama[0] + " " + split_nama[1];
                        upperNama1 = split_nama[0].substring(0,1).toUpperCase() + split_nama[0].substring(1);
                        upperNama2 = split_nama[1].substring(0,1).toUpperCase() + split_nama[1].substring(1);
                        upperNama = upperNama1 + " " + "- (" + upperNama2 + ")";
                    }else {
                        upperNama = nama;
                    }

                    switch (nama){
                        case "grill1_fisikmuka":
                            rename = "Grill (Fisik Muka)";
                            break;
                        case "grill2_fisikmuka":
                            rename = "Grill (Fisik Muka)";
                            break;
                        case "lampu1_fisikmuka":
                            rename = "Lampu Kiri/Kanan (Fisik Muka)";
                            break;
                        case "lampu2_fisikmuka":
                            rename = "Lampu Kiri/Kanan (Fisik Muka)";
                            break;
                        case "lampusen1_fisikmuka":
                            rename = "Lampu Sen Kiri/Kanan (Fisik Muka)";
                            break;
                        case "lampusen2_fisikmuka":
                            rename = "Lampu Sen Kiri/Kanan (Fisik Muka)";
                            break;
                        case "bamper1_fisikmuka":
                            rename = "Bamper (Fisik Muka)";
                            break;
                        case "bamper2_fisikmuka":
                            rename = "Bamper (Fisik Muka)";
                            break;
                        case "emblem1_fisikmuka":
                            rename = "Emblem (Fisik Muka)";
                            break;
                        case "emblem2_fisikmuka":
                            rename = "Emblem (Fisik Muka)";
                            break;
                        case "tanduk1_fisikmuka":
                            rename = "Tanduk (Fisik Muka)";
                            break;
                        case "tanduk2_fisikmuka":
                            rename = "Tanduk (Fisik Muka)";
                            break;

                        case "footstep1_fisikkiri":
                            rename = "Foot Step (Fisik Kiri)";
                            break;
                        case "footstep2_fisikkiri":
                            rename = "Foot Step (Fisik Kiri)";
                            break;
                        case "pintudpn1_fisikkiri":
                            rename = "Pintu Depan (Fisik Kiri)";
                            break;
                        case "pintudpn2_fisikkiri":
                            rename = "Pintu Depan (Fisik Kiri)";
                            break;
                        case "pintublk1_fisikkiri":
                            rename = "Pintu Belakang (Fisik Kiri)";
                            break;
                        case "pintublk2_fisikkiri":
                            rename = "Pintu Belakang (Fisik Kiri)";
                            break;
                        case "bamper1_fisikkiri":
                            rename = "Bamper (Fisik Kiri)";
                            break;
                        case "bamper2_fisikkiri":
                            rename = "Bamper (Fisik Kiri)";
                            break;
                        case "fenderblk1_fisikkiri":
                            rename = "Fender Belakang (Fisik Kiri)";
                            break;
                        case "fenderblk2_fisikkiri":
                            rename = "Fender Belakang (Fisik Kiri)";
                            break;
                        case "spion1_fisikkiri":
                            rename = "Spion (Fisik Kiri)";
                            break;
                        case "spion2_fisikkiri":
                            rename = "Spion (Fisik Kiri)";
                            break;
                        case "emblem1_fisikkiri":
                            rename = "Emblem (Fisik Kiri)";
                            break;
                        case "emblem2_fisikkiri":
                            rename = "Emblem (Fisik Kiri)";
                            break;
                        case "ban1_fisikkiri":
                            rename = "Ban (Fisik Kiri)";
                            break;
                        case "ban2_fisikkiri":
                            rename = "Ban (Fisik Kiri)";
                            break;
                        case "banstandard_fisikkiri":
                            rename = "Ban Standard (Fisik Kiri)";
                            break;
                        case "banradial_fisikkiri":
                            rename = "Ban Radial (Fisik Kiri)";
                            break;
                        case "velgstandard_fisikkiri":
                            rename = "Velg Standard (Fisik Kiri)";
                            break;
                        case "velgracing_fisikkiri":
                            rename = "Velg Racing (Fisik Kiri)";
                            break;
                        case "velg1_fisikkiri":
                            rename = "Velg (Fisik Kiri)";
                            break;
                        case "velg2_fisikkiri":
                            rename = "Velg (Fisik Kiri)";
                            break;
                        case "dop1_fisikkiri":
                            rename = "Dop Roda Depan (Fisik Kiri)";
                            break;
                        case "dop2_fisikkiri":
                            rename = "Dop Roda Depan (Fisik Kiri)";
                            break;
                        case "dopblk1_fisikkiri":
                            rename = "Dop Roda Belakang (Fisik Kiri)";
                            break;
                        case "dopblk2_fisikkiri":
                            rename = "Dop Roda Belakang (Fisik Kiri)";
                            break;
                        case "spoiler1_fisikblkg":
                            rename = "Spoiler (Fisik Belakang)";
                            break;
                        case "spoiler2_fisikblkg":
                            rename = "Spoiler (Fisik Belakang)";
                            break;
                        case "lampu1_fisikblkg":
                            rename = "Lampu (Fisik Belakang)";
                            break;
                        case "lampu2_fisikblkg":
                            rename = "Lampu (Fisik Belakang)";
                            break;
                        case "lampusen1_fisikblkg":
                            rename = "Lampu Sen Kiri/Kanan (Fisik Belakang)";
                            break;
                        case "lampusen2_fisikblkg":
                            rename = "Lampu Sen Kiri/Kanan (Fisik Belakang)";
                            break;
                        case "bamper1_fisikblkg":
                            rename = "Bamper (Fisik Belakang)";
                            break;
                        case "bamper2_fisikblkg":
                            rename = "Bamper (Fisik Belakang)";
                            break;
                        case "emblem1_fisikblkg":
                            rename = "Emblem (Fisik Belakang)";
                            break;
                        case "emblem2_fisikblkg":
                            rename = "Emblem (Fisik Belakang)";
                            break;
                        case "knalpot1_fisikblkg":
                            rename = "Knalpot (Fisik Belakang)";
                            break;
                        case "knalpot2_fisikblkg":
                            rename = "Knalpot (Fisik Belakang)";
                            break;

                        case "foot1_fisikkanan":
                            rename = "Foot Step (Fisik Kanan)";
                            break;
                        case "foot2_fisikkanan":
                            rename = "Foot Step (Fisik Kanan)";
                            break;
                        case "pintudpn1_fisikkanan":
                            rename = "Pintu Depan (Fisik Kanan)";
                            break;
                        case "pintudpn2_fisikkanan":
                            rename = "Pintu Depan (Fisik Kanan)";
                            break;
                        case "pintublk1_fisikkanan":
                            rename = "Pintu Belakang (Fisik Kanan)";
                            break;
                        case "pintublk2_fisikkanan":
                            rename = "Pintu Belakang (Fisik Kanan)";
                            break;
                        case "bamper1_fisikkanan":
                            rename = "Bamper (Fisik Kanan)";
                            break;
                        case "bamper2_fisikkanan":
                            rename = "Bamper (Fisik Kanan)";
                            break;
                        case "fenderblk1_fisikkanan":
                            rename = "Fender Belakang (Fisik Kanan)";
                            break;
                        case "fenderblk2_fisikkanan":
                            rename = "Fender Belakang (Fisik Kanan)";
                            break;
                        case "spion1_fisikkanan":
                            rename = "Spion (Fisik Kanan)";
                            break;
                        case "spion2_fisikkanan":
                            rename = "Spion (Fisik Kanan)";
                            break;
                        case "emblem1_fisikkanan":
                            rename = "Emblem (Fisik Kanan)";
                            break;
                        case "emblem2_fisikkanan":
                            rename = "Emblem (Fisik Kanan)";
                            break;
                        case "ban1_fisikkanan":
                            rename = "Ban (Fisik Kanan)";
                            break;
                        case "ban2_fisikkanan":
                            rename = "Ban (Fisik Kanan)";
                            break;
//                        case "banstandard_fisikkanan":
//                                rename = "Ban Standard (Fisik Kanan)";
//                                break;
//                        case "banradial_fisikkanan":
//                                rename = "Ban Radial (Fisik Kanan)";
//                                break;
//                        case "velgstandard_fisikkanan":
//                            rename = "Velg Standard (Fisik Kanan)";
//                            break;
//                         case "velgracing_fisikkanan":
//                             rename = "Velg Racing (Fisik Kanan)";
//                             break;
                        case "velg_fisikkanan":
                            rename = "Velg (Fisik Kanan)";
                            break;
                        case "velg1_fisikkanan":
                            rename = "Velg (Fisik Kanan)";
                            break;
                        case "velg2_fisikkanan":
                            rename = "Velg (Fisik Kanan)";
                            break;
                        case "dop1_fisikkanan":
                            rename = "Dop Roda Depan (Fisik Kanan)";
                            break;
                        case "dop2_fisikkanan":
                            rename = "Dop Roda Depan (Fisik Kanan)";
                            break;
                        case "dopblk1_fisikkanan":
                            rename = "Dop Roda Belakang (Fisik Kanan)";
                            break;
                        case "dopblk2_fisikkanan":
                            rename = "Dop Roda Belakang (Fisik Kanan)";
                            break;

                        case "kunciktk1_perlengkapan":
                            rename = "Kunci Kontak (Perlengkapan)";
                            break;
                        case "kunciktk2_perlengkapan":
                            rename = "Kunci Kontak (Perlengkapan)";
                            break;
                        case "spion1_perlengkapan":
                            rename = "Spion Dalam (Perlengkapan)";
                            break;
                        case "spion2_perlengkapan":
                            rename = "Spion Dalam (Perlengkapan)";
                            break;
                        case "jok1_perlengkapan":
                            rename = "Jok (Perlengkapan)";
                            break;
                        case "jok2_perlengkapan":
                            rename = "Jok (Perlengkapan)";
                            break;
                        case "sarung1_perlengkapan":
                            rename = "Sarung Jok (Perlengkapan)";
                            break;
                        case "sarung2_perlengkapan":
                            rename = "Sarung Jok (Perlengkapan)";
                            break;
                        case "sandaran1_perlengkapan":
                            rename = "Sandaran Kepala (Perlengkapan)";
                            break;
                        case "sandaran2_perlengkapan":
                            rename = "Sandaran Kepala (Perlengkapan)";
                            break;
                        case "karpet1_perlengkapan":
                            rename = "Karpet (Perlengkapan)";
                            break;
                        case "karpet2_perlengkapan":
                            rename = "Karpet (Perlengkapan)";
                            break;
                        case "pelindung1_perlengkapan":
                            rename = "Pelindung (Perlengkapan)";
                            break;
                        case "pelindung2_perlengkapan":
                            rename = "Pelindung (Perlengkapan)";
                            break;
                        case "segitiga1_perlengkapan":
                            rename = "Segitiga (Perlengkapan)";
                            break;
                        case "segitiga2_perlengkapan":
                            rename = "Segitiga (Perlengkapan)";
                            break;
                        case "tool1_perlengkapan":
                            rename = "Tool Kit (Perlengkapan)";
                            break;
                        case "tool2_perlengkapan":
                            rename = "Tool Kit (Perlengkapan)";
                            break;
                        case "cadangan1_perlengkapan":
                            rename = "Ban Cadangan (Perlengkapan)";
                            break;
                        case "cadangan2_perlengkapan":
                            rename = "Ban Cadangan (Perlengkapan)";
                            break;
                        case "kunciban1_perlengkapan":
                            rename = "Kunci Ban (Perlengkapan)";
                            break;
                        case "kunciban2_perlengkapan":
                            rename = "Kunci Ban (Perlengkapan)";
                            break;
                        case "dongkrak1_perlengkapan":
                            rename = "Dongkrak + Gagang (Perlengkapan)";
                            break;
                        case "dongkrak2_perlengkapan":
                            rename = "Dongkrak + Gagang (Perlengkapan)";
                            break;
                        case "antena1_perlengkapan":
                            rename = "Antena (Perlengkapan)";
                            break;
                        case "antena2_perlengkapan":
                            rename = "Antena (Perlengkapan)";
                            break;
                        case "airbag1_perlengkapan":
                            rename = "Airbag (Perlengkapan)";
                            break;
                        case "airbag2_perlengkapan":
                            rename = "Airbag (Perlengkapan)";
                            break;

                        case "lampukbt1_listrik":
                            rename = "Lampu Kabut (Listrik)";
                            break;
                        case "lampukbt2_listrik":
                            rename = "Lampu Kabut (Listrik)";
                            break;
                        case "wiperkacadpn1_listrik":
                            rename = "Wiper Kaca Depan (Listrik)";
                            break;
                        case "wiperkacadpn2_listrik":
                            rename = "Wiper Kaca Depan (Listrik)";
                            break;
                        case "wiperkacablk1_listrik":
                            rename = "Wiper Kaca Belakang (Listrik)";
                            break;
                        case "wiperkacablk2_listrik":
                            rename = "Wiper Kaca Belakang (Listrik)";
                            break;
                        case "klakson1_listrik":
                            rename = "Klakson (Listrik)";
                            break;
                        case "klakson2_listrik":
                            rename = "Klakson (Listrik)";
                            break;
                        case "alarm1_listrik":
                            rename = "Alarm (Listrik)";
                            break;
                        case "alarm2_listrik":
                            rename = "Alarm (Listrik)";
                            break;
                        case "jam1_listrik":
                            rename = "Jam (Listrik)";
                            break;
                        case "jam2_listrik":
                            rename = "Jam (Listrik)";
                            break;
                        case "lighter1_listrik":
                            rename = "Lighter (Listrik)";
                            break;
                        case "lighter2_listrik":
                            rename = "Lighter (Listrik)";
                            break;
//                         case "radio_listrik":
//                             rename = "Radio (Listrik)";
//                             break;
//                         case "tape_listrik":
//                             rename = "Tape (Listrik)";
//                             break;
//                         case "cd_listrik":
//                             rename = "CD (Listrik)";
//                             break;
//                         case "merk_listrik":
//                             rename = "Lampu Kabut (Listrik)";
//                             break;
                        case "radio1_listrik":
                            rename = "Radio (Listrik)";
                            break;
                        case "radio2_listrik":
                            rename = "Radio (Listrik)";
                            break;
                        case "powersup1_listrik":
                            rename = "Power Supply (Listrik)";
                            break;
                        case "powersup2_listrik":
                            rename = "Power Supply (Listrik)";
                            break;
                        case "speaker1_listrik":
                            rename = "Speaker (Listrik)";
                            break;
                        case "speaker2_listrik":
                            rename = "Speaker (Listrik)";
                            break;
                        case "ac1_listrik":
                            rename = "AC (Listrik)";
                            break;
                        case "ac2_listrik":
                            rename = "AC (Listrik)";
                            break;
                        case "powerwin1_listrik":
                            rename = "Power Window (Listrik)";
                            break;
                        case "powerwin2_listrik":
                            rename = "Power Window (Listrik)";
                            break;
                        case "central1_listrik":
                            rename = "Central (Listrik)";
                            break;
                        case "central2_listrik":
                            rename = "Central (Listrik)";
                            break;
                        case "remote1_listrik":
                            rename = "Remote (Listrik)";
                            break;
                        case "remote2_listrik":
                            rename = "Remote (Listrik)";
                            break;
                        case "speedo1_listrik":
                            rename = "Speedometer (Listrik)";
                            break;
                        case "speedo2_listrik":
                            rename = "Speedometer (Listrik)";
                            break;
                        case "odometer1_listrik":
                            rename = "Odometer (Listrik)";
                            break;
                        case "odometer2_listrik":
                            rename = "Odometer (Listrik)";
                            break;
                        case "tacho1_listrik":
                            rename = "Tachometer (Listrik)";
                            break;
                        case "tacho2_listrik":
                            rename = "Tachometer (Listrik)";
                            break;
                        case "accu1_listrik":
                            rename = "Accu (Listrik)";
                            break;
                        case "accu2_listrik":
                            rename = "Accu (Listrik)";
                            break;

                        case "mesin1_lain":
                            rename = "Mesin & Kelengkapan (Lain)";
                            break;
                        case "mesin2_lain":
                            rename = "Mesin & Kelengkapan (Lain)";
                            break;
                        case "hidraulik1_lain":
                            rename = "Hidraulik (Lain)";
                            break;
                        case "hidraulik2_lain":
                            rename = "Hidraulik (Lain)";
                            break;
                        case "gardan1_lain":
                            rename = "Gardan (Lain)";
                            break;
                        case "gardan2_lain":
                            rename = "Gardan (Lain)";
                            break;
                        case "as1_lain":
                            rename = "As Kopel (Lain)";
                            break;
                        case "as2_lain":
                            rename = "As Kopel (Lain)";
                            break;
                        case "bak1_lain":
                            rename = "Bak/Tangki/Box (Lain)";
                            break;
                        case "bak2_lain":
                            rename = "Bak/Tangki/Box (Lain)";
                            break;
                        case "stnk1_lain":
                            rename = "STNK (Lain)";
                            break;
                        case "stnk2_lain":
                            rename = "STNK (Lain)";
                            break;
//                         case "stnk_lain":
//                             rename = "Mesin (Lain)";
//                             break;
                        case "bukukir1_lain":
                            rename = "Buku Kir (Lain)";
                            break;
                        case "bukukir2_lain":
                            rename = "Buku Kir (Lain)";
                            break;
//                         case "bukukir_lain":
//                             rename = "Buku Kir (Lain)";
//                             break;
                        case "trayek1_lain":
                            rename = "Trayek (Lain)";
                            break;
                        case "trayek2_lain":
                            rename = "Trayek (Lain)";
                            break;
//                         case "ijintrayek_lain":
//                             rename = "Trayek (Lain)";
//                             break;
                        case "usaha1_lain":
                            rename = "Usaha (Lain)";
                            break;
                        case "usaha2_lain":
                            rename = "Usaha (Lain)";
                            break;
//                         case "ijinusaha_lain":
//                             rename = "Ijin Usaha (Lain)";
//                             break;
                        case "bukumnl1_lain":
                            rename = "Buku Manual (Lain)";
                            break;
                        case "bukumnl2_lain":
                            rename = "Buku Manual (Lain)";
                            break;

                    }

                    String isibp = bp.getString(nama);
                    //checkBox1.setText(upperNama);
                    if (isibp.equals("1")){
                        checkBox1.setChecked(true);

                        if (nama.indexOf("1") != -1){
                            namaCekbox = "Ada";
                        }else if (nama.indexOf("2") != -1){
                            namaCekbox = "Baik";
                            namaColor1 = 0;
                            namaColor2 = 102;
                            namaColor3 = 255;
                        }
                    }else {
                        checkBox1.setChecked(false);
                        if (nama.indexOf("1") != -1){
                            namaCekbox = "Tidak Ada";
                        }else if (nama.indexOf("2") != -1){
                            namaCekbox = "Rusak";
                            namaColor1 = 0;
                            namaColor2 = 102;
                            namaColor3 = 255;
                        }
                    }
                    checkBox1.setClickable(false);

                    checkBox1.setText(namaCekbox);
                    checkBox1.setTextColor(Color.rgb(namaColor1,namaColor2,namaColor3));
                    textView2.setTextColor(Color.rgb(namaColor1,namaColor2,namaColor3));

                    textView2.setText(rename);
                    setTextViewAttributes(textView2);
                    textViewLayout2.addView(textView2);

                    setCheckBoxAttributes(checkBox1);
                    checkBoxLayout2.addView(checkBox1);

//                    setCheckBoxAttributes(checkBox2);
//                    checkBoxLayout2.addView(checkBox2);
                }

//              =================================== Checklist DISPATCHER =======================================================================
                con = dispatcher.length();
                str = new ArrayList();
                strCheckbox = new ArrayList();

                LinearLayout checkBoxLayout = new LinearLayout(this);
                checkBoxLayout.setOrientation(LinearLayout.VERTICAL);

                LinearLayout textViewLayout = new LinearLayout(this);
                textViewLayout.setOrientation(LinearLayout.VERTICAL);

                layout.addView(checkBoxLayout);
                lyName.addView(textViewLayout);

                ArrayList<String> res = new ArrayList<String>();
                for (int i =0; i < con; i++){
                    final CheckBox checkBox1 = new CheckBox(this);
                    CheckBox checkBox2 = new CheckBox(this);
                    TextView textView = new TextView(this);

                    String upperNama1;
                    String upperNama2;
                    String upperNama;
                    String namaCekbox = null;
                    int namaColor1 = 0;
                    int namaColor2 = 0;
                    int namaColor3 = 0;

                    final String nama = dispatcher.names().get(i).toString();
                    if (nama.indexOf("_") != -1){
                        String[] split_nama = nama.split("_");
                        upperNama1 = split_nama[0].substring(0,1).toUpperCase() + split_nama[0].substring(1);
                        upperNama2 = split_nama[1].substring(0,1).toUpperCase() + split_nama[1].substring(1);
                        upperNama = upperNama1 + " " + upperNama2;
                    }else {
                        upperNama = nama;
                    }
//                   ------------------------------------------------------------------------------------

                    switch (nama){
                        case "grill1_fisikmuka":
                            rename = "Grill (Fisik Muka)";
                            break;
                        case "grill2_fisikmuka":
                            rename = "Grill (Fisik Muka)";
                            break;
                        case "lampu1_fisikmuka":
                            rename = "Lampu Kiri/Kanan (Fisik Muka)";
                            break;
                        case "lampu2_fisikmuka":
                            rename = "Lampu Kiri/Kanan (Fisik Muka)";
                            break;
                        case "lampusen1_fisikmuka":
                            rename = "Lampu Sen Kiri/Kanan (Fisik Muka)";
                            break;
                        case "lampusen2_fisikmuka":
                            rename = "Lampu Sen Kiri/Kanan (Fisik Muka)";
                            break;
                        case "bamper1_fisikmuka":
                            rename = "Bamper (Fisik Muka)";
                            break;
                        case "bamper2_fisikmuka":
                            rename = "Bamper (Fisik Muka)";
                            break;
                        case "emblem1_fisikmuka":
                            rename = "Emblem (Fisik Muka)";
                            break;
                        case "emblem2_fisikmuka":
                            rename = "Emblem (Fisik Muka)";
                            break;
                        case "tanduk1_fisikmuka":
                            rename = "Tanduk (Fisik Muka)";
                            break;
                        case "tanduk2_fisikmuka":
                            rename = "Tanduk (Fisik Muka)";
                            break;

                        case "footstep1_fisikkiri":
                            rename = "Foot Step (Fisik Kiri)";
                            break;
                        case "footstep2_fisikkiri":
                            rename = "Foot Step (Fisik Kiri)";
                            break;
                        case "pintudpn1_fisikkiri":
                                rename = "Pintu Depan (Fisik Kiri)";
                                break;
                        case "pintudpn2_fisikkiri":
                            rename = "Pintu Depan (Fisik Kiri)";
                            break;
                        case "pintublk1_fisikkiri":
                                rename = "Pintu Belakang (Fisik Kiri)";
                                break;
                        case "pintublk2_fisikkiri":
                                rename = "Pintu Belakang (Fisik Kiri)";
                                break;
                        case "bamper1_fisikkiri":
                                rename = "Bamper (Fisik Kiri)";
                                break;
                        case "bamper2_fisikkiri":
                                rename = "Bamper (Fisik Kiri)";
                                break;
                        case "fenderblk1_fisikkiri":
                                rename = "Fender Belakang (Fisik Kiri)";
                                break;
                        case "fenderblk2_fisikkiri":
                                rename = "Fender Belakang (Fisik Kiri)";
                                break;
                        case "spion1_fisikkiri":
                                rename = "Spion (Fisik Kiri)";
                                break;
                        case "spion2_fisikkiri":
                                rename = "Spion (Fisik Kiri)";
                                break;
                        case "emblem1_fisikkiri":
                                rename = "Emblem (Fisik Kiri)";
                                break;
                        case "emblem2_fisikkiri":
                                rename = "Emblem (Fisik Kiri)";
                                break;
                        case "ban1_fisikkiri":
                                rename = "Ban (Fisik Kiri)";
                                break;
                        case "ban2_fisikkiri":
                                rename = "Ban (Fisik Kiri)";
                                break;
                        case "banstandard_fisikkiri":
                                rename = "Ban Standard (Fisik Kiri)";
                                break;
                        case "banradial_fisikkiri":
                                rename = "Ban Radial (Fisik Kiri)";
                                break;
                        case "velgstandard_fisikkiri":
                                rename = "Velg Standard (Fisik Kiri)";
                                break;
                        case "velgracing_fisikkiri":
                                rename = "Velg Racing (Fisik Kiri)";
                                break;
                        case "velg1_fisikkiri":
                                rename = "Velg (Fisik Kiri)";
                                break;
                        case "velg2_fisikkiri":
                                rename = "Velg (Fisik Kiri)";
                                break;
                        case "dop1_fisikkiri":
                                rename = "Dop Roda Depan (Fisik Kiri)";
                                break;
                        case "dop2_fisikkiri":
                                rename = "Dop Roda Depan (Fisik Kiri)";
                                break;
                        case "dopblk1_fisikkiri":
                                rename = "Dop Roda Belakang (Fisik Kiri)";
                                break;
                        case "dopblk2_fisikkiri":
                                rename = "Dop Roda Belakang (Fisik Kiri)";
                                break;
                        case "spoiler1_fisikblkg":
                                rename = "Spoiler (Fisik Belakang)";
                                break;
                        case "spoiler2_fisikblkg":
                                rename = "Spoiler (Fisik Belakang)";
                                break;
                        case "lampu1_fisikblkg":
                                rename = "Lampu (Fisik Belakang)";
                                break;
                        case "lampu2_fisikblkg":
                                rename = "Lampu (Fisik Belakang)";
                                break;
                        case "lampusen1_fisikblkg":
                                rename = "Lampu Sen Kiri/Kanan (Fisik Belakang)";
                                break;
                        case "lampusen2_fisikblkg":
                                rename = "Lampu Sen Kiri/Kanan (Fisik Belakang)";
                                break;
                        case "bamper1_fisikblkg":
                                rename = "Bamper (Fisik Belakang)";
                                break;
                        case "bamper2_fisikblkg":
                                rename = "Bamper (Fisik Belakang)";
                                break;
                        case "emblem1_fisikblkg":
                                rename = "Emblem (Fisik Belakang)";
                                break;
                        case "emblem2_fisikblkg":
                                rename = "Emblem (Fisik Belakang)";
                                break;
                        case "knalpot1_fisikblkg":
                                rename = "Knalpot (Fisik Belakang)";
                                break;
                        case "knalpot2_fisikblkg":
                                rename = "Knalpot (Fisik Belakang)";
                                break;

                        case "foot1_fisikkanan":
                                rename = "Foot Step (Fisik Kanan)";
                                break;
                        case "foot2_fisikkanan":
                                rename = "Foot Step (Fisik Kanan)";
                                break;
                        case "pintudpn1_fisikkanan":
                                rename = "Pintu Depan (Fisik Kanan)";
                                break;
                        case "pintudpn2_fisikkanan":
                                rename = "Pintu Depan (Fisik Kanan)";
                                break;
                        case "pintublk1_fisikkanan":
                                rename = "Pintu Belakang (Fisik Kanan)";
                                break;
                        case "pintublk2_fisikkanan":
                                rename = "Pintu Belakang (Fisik Kanan)";
                                break;
                        case "bamper1_fisikkanan":
                                rename = "Bamper (Fisik Kanan)";
                                break;
                        case "bamper2_fisikkanan":
                                rename = "Bamper (Fisik Kanan)";
                                break;
                        case "fenderblk1_fisikkanan":
                                rename = "Fender Belakang (Fisik Kanan)";
                                break;
                        case "fenderblk2_fisikkanan":
                                rename = "Fender Belakang (Fisik Kanan)";
                                break;
                        case "spion1_fisikkanan":
                                rename = "Spion (Fisik Kanan)";
                                break;
                        case "spion2_fisikkanan":
                                rename = "Spion (Fisik Kanan)";
                                break;
                        case "emblem1_fisikkanan":
                                rename = "Emblem (Fisik Kanan)";
                                break;
                        case "emblem2_fisikkanan":
                                rename = "Emblem (Fisik Kanan)";
                                break;
                        case "ban1_fisikkanan":
                                rename = "Ban (Fisik Kanan)";
                                break;
                        case "ban2_fisikkanan":
                                rename = "Ban (Fisik Kanan)";
                                break;
//                        case "banstandard_fisikkanan":
//                                rename = "Ban Standard (Fisik Kanan)";
//                                break;
//                        case "banradial_fisikkanan":
//                                rename = "Ban Radial (Fisik Kanan)";
//                                break;
//                        case "velgstandard_fisikkanan":
//                            rename = "Velg Standard (Fisik Kanan)";
//                            break;
//                         case "velgracing_fisikkanan":
//                             rename = "Velg Racing (Fisik Kanan)";
//                             break;
                         case "velg_fisikkanan":
                             rename = "Velg (Fisik Kanan)";
                             break;
                         case "velg1_fisikkanan":
                             rename = "Velg (Fisik Kanan)";
                             break;
                         case "velg2_fisikkanan":
                             rename = "Velg (Fisik Kanan)";
                             break;
                         case "dop1_fisikkanan":
                             rename = "Dop Roda Depan (Fisik Kanan)";
                             break;
                         case "dop2_fisikkanan":
                             rename = "Dop Roda Depan (Fisik Kanan)";
                             break;
                         case "dopblk1_fisikkanan":
                             rename = "Dop Roda Belakang (Fisik Kanan)";
                             break;
                         case "dopblk2_fisikkanan":
                             rename = "Dop Roda Belakang (Fisik Kanan)";
                             break;

                         case "kunciktk1_perlengkapan":
                             rename = "Kunci Kontak (Perlengkapan)";
                             break;
                         case "kunciktk2_perlengkapan":
                             rename = "Kunci Kontak (Perlengkapan)";
                             break;
                         case "spion1_perlengkapan":
                             rename = "Spion Dalam (Perlengkapan)";
                             break;
                         case "spion2_perlengkapan":
                             rename = "Spion Dalam (Perlengkapan)";
                             break;
                         case "jok1_perlengkapan":
                             rename = "Jok (Perlengkapan)";
                             break;
                         case "jok2_perlengkapan":
                             rename = "Jok (Perlengkapan)";
                             break;
                         case "sarung1_perlengkapan":
                             rename = "Sarung Jok (Perlengkapan)";
                             break;
                         case "sarung2_perlengkapan":
                             rename = "Sarung Jok (Perlengkapan)";
                             break;
                         case "sandaran1_perlengkapan":
                             rename = "Sandaran Kepala (Perlengkapan)";
                             break;
                         case "sandaran2_perlengkapan":
                             rename = "Sandaran Kepala (Perlengkapan)";
                             break;
                         case "karpet1_perlengkapan":
                             rename = "Karpet (Perlengkapan)";
                             break;
                         case "karpet2_perlengkapan":
                             rename = "Karpet (Perlengkapan)";
                             break;
                         case "pelindung1_perlengkapan":
                             rename = "Pelindung (Perlengkapan)";
                             break;
                         case "pelindung2_perlengkapan":
                             rename = "Pelindung (Perlengkapan)";
                             break;
                         case "segitiga1_perlengkapan":
                             rename = "Segitiga (Perlengkapan)";
                             break;
                         case "segitiga2_perlengkapan":
                             rename = "Segitiga (Perlengkapan)";
                             break;
                         case "tool1_perlengkapan":
                             rename = "Tool Kit (Perlengkapan)";
                             break;
                         case "tool2_perlengkapan":
                             rename = "Tool Kit (Perlengkapan)";
                             break;
                         case "cadangan1_perlengkapan":
                             rename = "Ban Cadangan (Perlengkapan)";
                             break;
                         case "cadangan2_perlengkapan":
                             rename = "Ban Cadangan (Perlengkapan)";
                             break;
                         case "kunciban1_perlengkapan":
                             rename = "Kunci Ban (Perlengkapan)";
                             break;
                         case "kunciban2_perlengkapan":
                             rename = "Kunci Ban (Perlengkapan)";
                             break;
                         case "dongkrak1_perlengkapan":
                             rename = "Dongkrak + Gagang (Perlengkapan)";
                             break;
                         case "dongkrak2_perlengkapan":
                             rename = "Dongkrak + Gagang (Perlengkapan)";
                             break;
                         case "antena1_perlengkapan":
                             rename = "Antena (Perlengkapan)";
                             break;
                         case "antena2_perlengkapan":
                             rename = "Antena (Perlengkapan)";
                             break;
                         case "airbag1_perlengkapan":
                             rename = "Airbag (Perlengkapan)";
                             break;
                         case "airbag2_perlengkapan":
                             rename = "Airbag (Perlengkapan)";
                             break;

                         case "lampukbt1_listrik":
                             rename = "Lampu Kabut (Listrik)";
                             break;
                         case "lampukbt2_listrik":
                             rename = "Lampu Kabut (Listrik)";
                             break;
                         case "wiperkacadpn1_listrik":
                             rename = "Wiper Kaca Depan (Listrik)";
                             break;
                         case "wiperkacadpn2_listrik":
                             rename = "Wiper Kaca Depan (Listrik)";
                             break;
                         case "wiperkacablk1_listrik":
                             rename = "Wiper Kaca Belakang (Listrik)";
                             break;
                         case "wiperkacablk2_listrik":
                             rename = "Wiper Kaca Belakang (Listrik)";
                             break;
                         case "klakson1_listrik":
                             rename = "Klakson (Listrik)";
                             break;
                         case "klakson2_listrik":
                             rename = "Klakson (Listrik)";
                             break;
                         case "alarm1_listrik":
                             rename = "Alarm (Listrik)";
                             break;
                         case "alarm2_listrik":
                             rename = "Alarm (Listrik)";
                             break;
                         case "jam1_listrik":
                             rename = "Jam (Listrik)";
                             break;
                         case "jam2_listrik":
                             rename = "Jam (Listrik)";
                             break;
                         case "lighter1_listrik":
                             rename = "Lighter (Listrik)";
                             break;
                         case "lighter2_listrik":
                             rename = "Lighter (Listrik)";
                             break;
//                         case "radio_listrik":
//                             rename = "Radio (Listrik)";
//                             break;
//                         case "tape_listrik":
//                             rename = "Tape (Listrik)";
//                             break;
//                         case "cd_listrik":
//                             rename = "CD (Listrik)";
//                             break;
//                         case "merk_listrik":
//                             rename = "Lampu Kabut (Listrik)";
//                             break;
                         case "radio1_listrik":
                             rename = "Radio (Listrik)";
                             break;
                         case "radio2_listrik":
                             rename = "Radio (Listrik)";
                             break;
                         case "powersup1_listrik":
                             rename = "Power Supply (Listrik)";
                             break;
                         case "powersup2_listrik":
                             rename = "Power Supply (Listrik)";
                             break;
                         case "speaker1_listrik":
                             rename = "Speaker (Listrik)";
                             break;
                         case "speaker2_listrik":
                             rename = "Speaker (Listrik)";
                             break;
                         case "ac1_listrik":
                             rename = "AC (Listrik)";
                             break;
                         case "ac2_listrik":
                             rename = "AC (Listrik)";
                             break;
                         case "powerwin1_listrik":
                             rename = "Power Window (Listrik)";
                             break;
                         case "powerwin2_listrik":
                             rename = "Power Window (Listrik)";
                             break;
                         case "central1_listrik":
                             rename = "Central (Listrik)";
                             break;
                         case "central2_listrik":
                             rename = "Central (Listrik)";
                             break;
                         case "remote1_listrik":
                             rename = "Remote (Listrik)";
                             break;
                         case "remote2_listrik":
                             rename = "Remote (Listrik)";
                             break;
                         case "speedo1_listrik":
                             rename = "Speedometer (Listrik)";
                             break;
                         case "speedo2_listrik":
                             rename = "Speedometer (Listrik)";
                             break;
                         case "odometer1_listrik":
                             rename = "Odometer (Listrik)";
                             break;
                         case "odometer2_listrik":
                             rename = "Odometer (Listrik)";
                             break;
                         case "tacho1_listrik":
                             rename = "Tachometer (Listrik)";
                             break;
                         case "tacho2_listrik":
                             rename = "Tachometer (Listrik)";
                             break;
                         case "accu1_listrik":
                             rename = "Accu (Listrik)";
                             break;
                         case "accu2_listrik":
                             rename = "Accu (Listrik)";
                             break;

                         case "mesin1_lain":
                             rename = "Mesin & Kelengkapan (Lain)";
                             break;
                         case "mesin2_lain":
                             rename = "Mesin & Kelengkapan (Lain)";
                             break;
                         case "hidraulik1_lain":
                             rename = "Hidraulik (Lain)";
                             break;
                         case "hidraulik2_lain":
                             rename = "Hidraulik (Lain)";
                             break;
                         case "gardan1_lain":
                             rename = "Gardan (Lain)";
                             break;
                         case "gardan2_lain":
                             rename = "Gardan (Lain)";
                             break;
                         case "as1_lain":
                             rename = "As Kopel (Lain)";
                             break;
                         case "as2_lain":
                             rename = "As Kopel (Lain)";
                             break;
                         case "bak1_lain":
                             rename = "Bak/Tangki/Box (Lain)";
                             break;
                         case "bak2_lain":
                             rename = "Bak/Tangki/Box (Lain)";
                             break;
                         case "stnk1_lain":
                             rename = "STNK (Lain)";
                             break;
                         case "stnk2_lain":
                             rename = "STNK (Lain)";
                             break;
//                         case "stnk_lain":
//                             rename = "Mesin (Lain)";
//                             break;
                         case "bukukir1_lain":
                             rename = "Buku Kir (Lain)";
                             break;
                         case "bukukir2_lain":
                             rename = "Buku Kir (Lain)";
                             break;
//                         case "bukukir_lain":
//                             rename = "Buku Kir (Lain)";
//                             break;
                         case "trayek1_lain":
                             rename = "Trayek (Lain)";
                             break;
                         case "trayek2_lain":
                             rename = "Trayek (Lain)";
                             break;
//                         case "ijintrayek_lain":
//                             rename = "Trayek (Lain)";
//                             break;
                         case "usaha1_lain":
                             rename = "Usaha (Lain)";
                             break;
                         case "usaha2_lain":
                             rename = "Usaha (Lain)";
                             break;
//                         case "ijinusaha_lain":
//                             rename = "Ijin Usaha (Lain)";
//                             break;
                         case "bukumnl1_lain":
                             rename = "Buku Manual (Lain)";
                             break;
                         case "bukumnl2_lain":
                             rename = "Buku Manual (Lain)";
                             break;

                    }
//                  ---------------------------------------------------------------------------------------
                    String isidisc = dispatcher.getString(nama);
                    //checkBox1.setText(upperNama);
                    if (isidisc.equals("1")){
                        checkBox1.setChecked(true);
                        if (nama.indexOf("1") != -1){
                            namaCekbox = "Ada";
                        }else if (nama.indexOf("2") != -1){
                            namaCekbox = "Baik";
                            namaColor1 = 0;
                            namaColor2 = 102;
                            namaColor3 = 255;
                        }
                    }else {
                        checkBox1.setChecked(false);
                        if (nama.indexOf("1") != -1){
                            namaCekbox = "Tidak Ada";
                        }else if (nama.indexOf("2") != -1){
                            namaCekbox = "Rusak";
                            namaColor1 = 0;
                            namaColor2 = 102;
                            namaColor3 = 255;
                        }
                    }
                    checkBox1.setText(namaCekbox);
                    checkBox1.setTextColor(Color.rgb(namaColor1,namaColor2,namaColor3));
                    final int finalI = i;

                    checkBox1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Toast.makeText(DisCekBastkCeklistActivity.this,"index ke : " + finalI,Toast.LENGTH_SHORT).show();
//                            Toast.makeText(DisCekBastkCeklistActivity.this,"deskripsi index : " + nama,Toast.LENGTH_SHORT).show();

                            if (checkBox1.getText().toString() == "Ada"){
                                checkBox1.setChecked(false);
                                checkBox1.setText("Tidak Ada");

                                str.remove(nama + "-" + 1);
                                str.add(nama + "-" + 0);
                            }else if (checkBox1.getText().toString() == "Tidak Ada"){
                                checkBox1.setChecked(true);
                                checkBox1.setText("Ada");

                                str.remove(nama + "-" + 0);
                                str.add(nama + "-" + 1);
                            }else if (checkBox1.getText().toString() == "Baik"){
                                checkBox1.setChecked(false);
                                checkBox1.setText("Rusak");

                                str.remove(nama + "-" + 1);
                                str.add(nama + "-" + 0);
                            }else if (checkBox1.getText().toString() == "Rusak"){
                                checkBox1.setChecked(true);
                                checkBox1.setText("Baik");

                                str.remove(nama + "-" + 0);
                                str.add(nama + "-" + 1);
                            }
                        }
                    });

                    textView.setTextColor(Color.rgb(namaColor1,namaColor2,namaColor3));
                    textView.setText(rename);
                    setTextViewAttributes(textView);
                    textViewLayout.addView(textView);

                    setCheckBoxAttributes(checkBox1);
                    checkBoxLayout.addView(checkBox1);
                }

            }else if (code == 304){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            
        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(BpCekFormActivity.this,"catch",Toast.LENGTH_LONG).show();
        }

       
    }

    private void setCheckBoxAttributes(CheckBox checkBox) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                convertDpToPixel(16),
                0
        );

        checkBox.setLayoutParams(params);

        //This is used to place the checkbox on the right side of the textview
        //By default, the checkbox is placed at the left side
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple,
                typedValue, true);

        checkBox.setButtonDrawable(null);
        checkBox.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                typedValue.resourceId, 0);
    }
    private void setTextViewAttributes(TextView textView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(25),
                convertDpToPixel(16),
                4
        );

        textView.setLayoutParams(params);

        //This is used to place the checkbox on the right side of the textview
        //By default, the checkbox is placed at the left side
        TypedValue typedValue = new TypedValue();
        //getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, typedValue, true);

        //textView.setButtonDrawable(null);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                typedValue.resourceId, 0);
    }

    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
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
}
