package com.tunasrent.auctionapps.taksasi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.tunasrent.auctionapps.BuildConfig;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class TaksasiSetpriceActivity extends AppCompatActivity implements AsyncTaskCompleteListener {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private static final String IMAGE_DIRECTORY = "/mas_temp";
    public static final int REQUEST_IMAGE = 100;
    Toolbar toolbar;
    Button btnSave;
    Dialog myDialog;
    JSONObject jsonObject;
    JSONArray data;
    JSONObject jo;
    UserSessionManager session;
    ImageView ivPicpenjualan;

    EditText etPrice;
    EditText etPrice_recomendation;
    EditText etRepair_machine;
    EditText etRepair_interior;
    EditText etRepair_exterior;
    EditText etCost_document;
    EditText etMarginPersen;
    EditText etMargin;
    EditText etPembulatan;
    EditText etRemark;
    TextView tvRekomendasi;

    CheckBox cbPembulatan;

    String position;
    String message;
    String code;
    String _name, _fullname, _appid, _ccode, _token, _group;
    String picpenjualan;
    String nopol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taksasi_setprice);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Set Harga");
        toolbar.setTitleTextColor(Color.WHITE);

        etPrice = findViewById(R.id.et_price);
        etPrice_recomendation = findViewById(R.id.et_price_recomendation);
        etRepair_machine = findViewById(R.id.et_repair_machine);
        etRepair_interior = findViewById(R.id.et_repair_interior);
        etRepair_exterior = findViewById(R.id.et_repair_exterior);
        etCost_document = findViewById(R.id.et_cost_document);
        etMarginPersen = findViewById(R.id.et_margin_persen);
        etMargin = findViewById(R.id.et_margin);
        etPembulatan = findViewById(R.id.et_pembulatan);
        etRemark = findViewById(R.id.et_remark);
        cbPembulatan = findViewById(R.id.cb_pembulatan);
        tvRekomendasi = findViewById(R.id.tv_rekomendasi);
        ivPicpenjualan = findViewById(R.id.iv_picpenjualan);
        btnSave = findViewById(R.id.btn_save);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            nopol = b.getString("nopol");
        }

        myDialog = new Dialog(this);

        etPrice.addTextChangedListener(onTextChangedListener());
        etPrice_recomendation.addTextChangedListener(onTextChangedListener());
        etRepair_machine.addTextChangedListener(onTextChangedListener());
        etRepair_interior.addTextChangedListener(onTextChangedListener());
        etRepair_exterior.addTextChangedListener(onTextChangedListener());
        etCost_document.addTextChangedListener(onTextChangedListener());
        etMarginPersen.addTextChangedListener(onTextChangedListener());
        etPembulatan.addTextChangedListener(onTextChangedListener());

       etMarginPersen.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View v, int keyCode, KeyEvent event) {
               if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER)) {
                   if (etPrice.getText().toString().equals("") || etPrice.getText().toString().equals("0")){
                       etPrice.setError("Harga wajib diisi");
                       etMarginPersen.setText("");
                       etMarginPersen.requestFocus();
                       InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                       imm.showSoftInput(etMarginPersen, InputMethodManager.SHOW_IMPLICIT);
//                       imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                   }else {
                       int var_price = Integer.parseInt(etPrice.getText().toString().replaceAll(",",""));
                       int var_persen = Integer.parseInt(etMarginPersen.getText().toString());
                       BigDecimal var_value =  new BigDecimal(var_persen * 0.01);
                       BigDecimal conTobig = new BigDecimal(var_price);
                       BigDecimal kali = var_value.multiply(conTobig);
                       double con_hasil = kali.doubleValue();
                       int var_hasil = (int) Math.round(con_hasil);

                       String originalString = String.valueOf(var_hasil);
                       Long longval;
                       if (originalString.contains(",")) {
                           originalString = originalString.replaceAll(",", "");
                       }
                       longval = Long.parseLong(originalString);

                       DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                       formatter.applyPattern("#,###,###,###");
                       String formattedString = formatter.format(longval);

                       etMargin.setText(formattedString);
                       etPrice_recomendation.setText("");

                       return true;
                   }
               }
               return false;
           }
       });

       cbPembulatan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (cbPembulatan.isChecked() == true){
                   cbPembulatan.setText("Plus");
               }else {
                   cbPembulatan.setText("Minus");
               }
                etPrice_recomendation.setText("");
           }
       });

        tvRekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPrice.getText().toString().equals("") || etPrice.getText().toString().equals("0")) {
                    etPrice.setText("0");
                    etPrice_recomendation.setText("0");
                    Toast.makeText(TaksasiSetpriceActivity.this,"Harga pasar harus diisi",Toast.LENGTH_SHORT).show();
                }
                if (etRepair_machine.getText().toString().equals("")) {
                    etRepair_machine.setText("0");
                }
                if (etRepair_interior.getText().toString().equals("")) {
                    etRepair_interior.setText("0");
                }
                if (etRepair_exterior.getText().toString().equals("")) {
                    etRepair_exterior.setText("0");
                }
                if (etCost_document.getText().toString().equals("")) {
                    etCost_document.setText("0");
                }
                if (etMargin.getText().toString().equals("")) {
                    etMargin.setText("0");
                }
                if (etPembulatan.getText().toString().equals("")) {
                    etPembulatan.setText("0");
                }

                String prc = etPrice.getText().toString().replaceAll(",","");
                String rep_machine = etRepair_machine.getText().toString().replaceAll(",","");
                String rep_interior = etRepair_interior.getText().toString().replaceAll(",","");
                String rep_exterior = etRepair_exterior.getText().toString().replaceAll(",","");
                String rep_cost = etCost_document.getText().toString().replaceAll(",","");
                String rep_margin = etMargin.getText().toString().replaceAll(",","");
                String rep_pembualatan = etPembulatan.getText().toString().replaceAll(",","");

                String originalString = null;
                if (cbPembulatan.isChecked() == true){
                    int res = Integer.parseInt(prc) - (Integer.parseInt(rep_machine) + Integer.parseInt(rep_interior) + Integer.parseInt(rep_exterior)+ Integer.parseInt(rep_cost) + Integer.parseInt(rep_margin)) + Integer.parseInt(rep_pembualatan);
                    originalString = String.valueOf(res);
                }else {
                    int res = Integer.parseInt(prc) - (Integer.parseInt(rep_machine) + Integer.parseInt(rep_interior) + Integer.parseInt(rep_exterior)+ Integer.parseInt(rep_cost) + Integer.parseInt(rep_margin)) - Integer.parseInt(rep_pembualatan);
                    originalString = String.valueOf(res);
                }

                Long longval;
                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }
                longval = Long.parseLong(originalString);

                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);

                //setting text after format to EditText
                etPrice_recomendation.setText(formattedString);
                etPrice_recomendation.setSelection(etPrice_recomendation.getText().length());
            }
        });

        getSign();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPrice.getText().toString().equals("") || etPrice.getText().toString().equals("0")){
                    etPrice.setError("Harga pasar wajib diisi");
                }else if (etPrice_recomendation.getText().toString().equals("") || etPrice_recomendation.getText().toString().equals("0")){
                    etPrice_recomendation.setError("Harga rekomendasi wajib diisi");
                }else if (ivPicpenjualan.getDrawable() == null) {
                    Toast.makeText(TaksasiSetpriceActivity.this, "Tanda tangan wajib diisi.", Toast.LENGTH_LONG).show();
                }else {
                    sendData();
                }
            }
        });

    }
    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               if (s == etPrice.getEditableText()){
                   etPrice.removeTextChangedListener(this);
                   try {
                       String originalString = s.toString();

                       Long longval;
                       if (originalString.contains(",")) {
                           originalString = originalString.replaceAll(",", "");
                       }
                       longval = Long.parseLong(originalString);

                       DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                       formatter.applyPattern("#,###,###,###");
                       String formattedString = formatter.format(longval);

                       //setting text after format to EditText
                       etPrice.setText(formattedString);
                       etPrice.setSelection(etPrice.getText().length());
                       etPrice_recomendation.setText("");
                       etMarginPersen.setText("");
                       etMargin.setText("");
                   } catch (NumberFormatException nfe) {
                       nfe.printStackTrace();
                   }
                   etPrice.addTextChangedListener(this);

               }else if (s == etRepair_machine.getEditableText()){
                   etRepair_machine.removeTextChangedListener(this);
                   try {
                       String originalString = s.toString();

                       Long longval;
                       if (originalString.contains(",")) {
                           originalString = originalString.replaceAll(",", "");
                       }
                       longval = Long.parseLong(originalString);

                       DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                       formatter.applyPattern("#,###,###,###");
                       String formattedString = formatter.format(longval);

                       //setting text after format to EditText
                       etRepair_machine.setText(formattedString);
                       etRepair_machine.setSelection(etRepair_machine.getText().length());
                   } catch (NumberFormatException nfe) {
                       nfe.printStackTrace();
                   }
                   etRepair_machine.addTextChangedListener(this);
                   etPrice_recomendation.setText("");
               }else if (s == etRepair_interior.getEditableText()){
                   etRepair_interior.removeTextChangedListener(this);
                   try {
                       String originalString = s.toString();

                       Long longval;
                       if (originalString.contains(",")) {
                           originalString = originalString.replaceAll(",", "");
                       }
                       longval = Long.parseLong(originalString);

                       DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                       formatter.applyPattern("#,###,###,###");
                       String formattedString = formatter.format(longval);

                       //setting text after format to EditText
                       etRepair_interior.setText(formattedString);
                       etRepair_interior.setSelection(etRepair_interior.getText().length());
                   } catch (NumberFormatException nfe) {
                       nfe.printStackTrace();
                   }
                   etRepair_interior.addTextChangedListener(this);
                   etPrice_recomendation.setText("");
               }else if (s == etRepair_exterior.getEditableText()) {
                   etRepair_exterior.removeTextChangedListener(this);
                   try {
                       String originalString = s.toString();

                       Long longval;
                       if (originalString.contains(",")) {
                           originalString = originalString.replaceAll(",", "");
                       }
                       longval = Long.parseLong(originalString);

                       DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                       formatter.applyPattern("#,###,###,###");
                       String formattedString = formatter.format(longval);

                       //setting text after format to EditText
                       etRepair_exterior.setText(formattedString);
                       etRepair_exterior.setSelection(etRepair_exterior.getText().length());
                   } catch (NumberFormatException nfe) {
                       nfe.printStackTrace();
                   }
                   etRepair_exterior.addTextChangedListener(this);
                   etPrice_recomendation.setText("");
               }else if (s == etCost_document.getEditableText()) {
                   etCost_document.removeTextChangedListener(this);
                   try {
                       String originalString = s.toString();

                       Long longval;
                       if (originalString.contains(",")) {
                           originalString = originalString.replaceAll(",", "");
                       }
                       longval = Long.parseLong(originalString);

                       DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                       formatter.applyPattern("#,###,###,###");
                       String formattedString = formatter.format(longval);

                       //setting text after format to EditText
                       etCost_document.setText(formattedString);
                       etCost_document.setSelection(etCost_document.getText().length());
                   } catch (NumberFormatException nfe) {
                       nfe.printStackTrace();
                   }
                   etCost_document.addTextChangedListener(this);
                   etPrice_recomendation.setText("");
               }else if (s == etMarginPersen.getEditableText()) {
                   etMarginPersen.removeTextChangedListener(this);
//                   try {
//                       String originalString = s.toString();
//
//                       Long longval;
//                       if (originalString.contains(",")) {
//                           originalString = originalString.replaceAll(",", "");
//                       }
////                       longval = Long.parseLong(originalString);
//
////                       DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
////                       formatter.applyPattern("#,###,###,###");
////                       String formattedString = formatter.format(longval);
//
//
//
//                       //etMargin.setText((etMarginPersen.getText().toString() / 100) * etPrice.getText().toString());
//
////                       //setting text after format to EditText
////                       etPembulatan.setText(formattedString);
////                       etPembulatan.setSelection(etPembulatan.getText().length());
//                   } catch (NumberFormatException nfe) {
//                       nfe.printStackTrace();
//                   }
                   etMarginPersen.addTextChangedListener(this);
                   etMargin.setText("");
                   etPrice_recomendation.setText("");
               }else if (s == etPembulatan.getEditableText()) {
                   etPembulatan.removeTextChangedListener(this);
                   try {
                       String originalString = s.toString();

                       Long longval;
                       if (originalString.contains(",")) {
                           originalString = originalString.replaceAll(",", "");
                       }
                       longval = Long.parseLong(originalString);

                       DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                       formatter.applyPattern("#,###,###,###");
                       String formattedString = formatter.format(longval);

                       //setting text after format to EditText
                       etPembulatan.setText(formattedString);
                       etPembulatan.setSelection(etPembulatan.getText().length());
                   } catch (NumberFormatException nfe) {
                       nfe.printStackTrace();
                   }
                   etPembulatan.addTextChangedListener(this);
                   etPrice_recomendation.setText("");
               }
            }
        };
    }


    private void getSign(){
        class GetFoto extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TaksasiSetpriceActivity.this,"","Loading sign...",false,false);
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
                hashMap.put(configuration.KEY_STATUS,"2");
                hashMap.put(configuration.KEY_PARENT_STATUS,"3");
                hashMap.put(configuration.KEY_NOPOL,nopol);
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

            picpenjualan = jos.getString("picPenjualan");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(code.equals("200")){
            try {
                if (!picpenjualan.equals("null")){
                    Glide.with(this).load(picpenjualan).into(ivPicpenjualan);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
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
                loading = ProgressDialog.show(TaksasiSetpriceActivity.this,"","Processing...",false,false);
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
                hashMap.put(configuration.KEY_NOPOL,nopol);
                hashMap.put(configuration.KEY_PRICE,etPrice.getText().toString().replaceAll(",",""));
                hashMap.put(configuration.KEY_PRICE_REPAIR_MACHINE,etRepair_machine.getText().toString().replaceAll(",",""));
                hashMap.put(configuration.KEY_PRICE_REPAIR_INTERIOR,etRepair_interior.getText().toString().replaceAll(",",""));
                hashMap.put(configuration.KEY_PRICE_REPAIR_EXTERIOR,etRepair_exterior.getText().toString().replaceAll(",",""));
                hashMap.put(configuration.KEY_COST_DOCUMENT,etCost_document.getText().toString().replaceAll(",",""));
                hashMap.put(configuration.KEY_MARGIN,etMargin.getText().toString().replaceAll(",",""));
                hashMap.put(configuration.KEY_PEMBULATAN,etPembulatan.getText().toString().replaceAll(",",""));
                hashMap.put(configuration.KEY_PRICE_RECOMMENDATION,etPrice_recomendation.getText().toString().replaceAll(",",""));
                hashMap.put(configuration.KEY_UNIT_CONDITION,etRemark.getText().toString());
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_TAKSASI,hashMap);
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
                Toast.makeText(TaksasiSetpriceActivity.this,"Setting Harga Berhasil Di Simpan",Toast.LENGTH_SHORT).show();

                Intent i=new Intent(TaksasiSetpriceActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(TaksasiSetpriceActivity.this,"Setting Harga Gagal Di Simpan",Toast.LENGTH_SHORT).show();
            }
        }else if (code.equals("304")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void ShowPopupTaksasi(View v) {
        final TextView tvClose;
        final Button btnClear;
        final Button btnSave;
        myDialog.setContentView(R.layout.popup_sign_taksasi);
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
                    uploadImageToServer(paths);
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

        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void uploadImageToServer(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(TaksasiSetpriceActivity.this)) {
                Toast.makeText(TaksasiSetpriceActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, nopol);
            data.put(configuration.KEY_POSITION, "25");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(TaksasiSetpriceActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        try {
            YuliYanto.removeSimpleProgressDialog();
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

                picpenjualan = jo.getString("picPenjualan");
                Glide.with(this).load(picpenjualan).into(ivPicpenjualan);
                myDialog.dismiss();

            }else if (code.equals("304")){
                Toast.makeText(TaksasiSetpriceActivity.this,message,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(TaksasiSetpriceActivity.this,message,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(TaksasiSetpriceActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }

    private void directSetting() {
        Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        startActivity(i);
    }

    @Override
    public void onTaskCompleted1(String response, int serviceCode) {

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
                    Toast.makeText(TaksasiSetpriceActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
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
        TaksasiSetpriceActivity.this.sendBroadcast(mediaScanIntent);
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

    public static class CurrencyEditText extends android.support.v7.widget.AppCompatEditText {
        private static String prefix = "VND ";
        private static final int MAX_LENGTH = 20;
        private static final int MAX_DECIMAL = 3;
        private CurrencyTextWatcher currencyTextWatcher = new CurrencyTextWatcher(this, prefix);

        public CurrencyEditText(Context context) {
            this(context, null);
        }

        public CurrencyEditText(Context context, AttributeSet attrs) {
            this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle);
        }

        public CurrencyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            this.setHint(prefix);
            this.setFilters(new InputFilter[] { new InputFilter.LengthFilter(MAX_LENGTH) });
        }

        @Override
        protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
            if (focused) {
                this.addTextChangedListener(currencyTextWatcher);
            } else {
                this.removeTextChangedListener(currencyTextWatcher);
            }
            handleCaseCurrencyEmpty(focused);
        }

        /**
         * When currency empty <br/>
         * + When focus EditText, set the default text = prefix (ex: VND) <br/>
         * + When EditText lose focus, set the default text = "", EditText will display hint (ex:VND)
         */
        private void handleCaseCurrencyEmpty(boolean focused) {
            if (focused) {
                if (getText().toString().isEmpty()) {
                    setText(prefix);
                }
            } else {
                if (getText().toString().equals(prefix)) {
                    setText("");
                }
            }
        }

        private static class CurrencyTextWatcher implements TextWatcher {
            private final EditText editText;
            private String previousCleanString;
            private String prefix;

            CurrencyTextWatcher(EditText editText, String prefix) {
                this.editText = editText;
                this.prefix = prefix;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();
                if (str.length() < prefix.length()) {
                    editText.setText(prefix);
                    editText.setSelection(prefix.length());
                    return;
                }
                if (str.equals(prefix)) {
                    return;
                }
                // cleanString this the string which not contain prefix and ,
                String cleanString = str.replace(prefix, "").replaceAll("[,]", "");
                // for prevent afterTextChanged recursive call
                if (cleanString.equals(previousCleanString) || cleanString.isEmpty()) {
                    return;
                }
                previousCleanString = cleanString;

                String formattedString;
                if (cleanString.contains(".")) {
                    formattedString = formatDecimal(cleanString);
                } else {
                    formattedString = formatInteger(cleanString);
                }
                editText.removeTextChangedListener(this); // Remove listener
                editText.setText(formattedString);
                handleSelection();
                editText.addTextChangedListener(this); // Add back the listener
            }

            private String formatInteger(String str) {
                BigDecimal parsed = new BigDecimal(str);
                DecimalFormat formatter =
                        new DecimalFormat(prefix + "#,###", new DecimalFormatSymbols(Locale.US));
                return formatter.format(parsed);
            }

            private String formatDecimal(String str) {
                if (str.equals(".")) {
                    return prefix + ".";
                }
                BigDecimal parsed = new BigDecimal(str);
                // example pattern VND #,###.00
                DecimalFormat formatter = new DecimalFormat(prefix + "#,###." + getDecimalPattern(str),
                        new DecimalFormatSymbols(Locale.US));
                formatter.setRoundingMode(RoundingMode.DOWN);
                return formatter.format(parsed);
            }

            /**
             * It will return suitable pattern for format decimal
             * For example: 10.2 -> return 0 | 10.23 -> return 00, | 10.235 -> return 000
             */
            private String getDecimalPattern(String str) {
                int decimalCount = str.length() - str.indexOf(".") - 1;
                StringBuilder decimalPattern = new StringBuilder();
                for (int i = 0; i < decimalCount && i < MAX_DECIMAL; i++) {
                    decimalPattern.append("0");
                }
                return decimalPattern.toString();
            }

            private void handleSelection() {
                if (editText.getText().length() <= MAX_LENGTH) {
                    editText.setSelection(editText.getText().length());
                } else {
                    editText.setSelection(MAX_LENGTH);
                }
            }
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
