package com.tunasrent.auctionapps.unitout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.mobilisasi.MobListActivity;
import com.tunasrent.auctionapps.unitout.UnitoutCekSignActivity;
import com.tunasrent.auctionapps.util.AsyncTaskCompleteListener;
import com.tunasrent.auctionapps.util.MultiPartRequester;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.Utils;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import io.realm.Realm;

public class UnitoutReasonReturnActivity extends AppCompatActivity implements AsyncTaskCompleteListener {
    UserSessionManager session;
    Realm realm;
    Toolbar toolbar;

    Button btnNextnote;
    //    EditText etPicpenarikan;
    EditText etPicpenarikan;
    EditText etReason;
    EditText etNopol;
//    EditText etPicpenjualan;

    ImageView ivFoto1;
    ImageView ivFoto2;
    ImageView ivFoto3;
    ImageView ivFoto4;


    JSONObject jsonObject;
    JSONArray data;
    JSONObject jo;
    JSONArray user;

    String _name;
    String _fullname;
    String _appid;
    String _ccode;
    String _token;
    String _group;
    String code;
    String message;
    String position;
    String image1;
    String image2;
    String image3;
    String image4;
    String foto1;
    String foto2;
    String foto3;
    String foto4;
    private int GALLERY1 = 1;
    private int GALLERY2 = 2;
    private int GALLERY3 = 3;
    private int GALLERY4 = 4;
    public static final int REQUEST_IMAGE = 100;
    public static final int REQUEST_IMAGE_1 = 101;
    public static final int REQUEST_IMAGE_2 = 102;
    public static final int REQUEST_IMAGE_3 = 103;
    public static final int REQUEST_IMAGE_4 = 104;
    public static final int REQUEST_PERMISSION = 200;
    private static final String IMAGE_DIRECTORY = "/mas_temp";
    private String imageFilePath = "";

    String nopol;
    Spinner spReasonUnitout;

    private String[] reasonunitout = {"Tebus Debitur", "Return", "Pindah BLS", "Laku BLS Lain"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitout_input_reason);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reason Reject");
        toolbar.setTitleTextColor(Color.WHITE);

        session = new UserSessionManager(getApplicationContext());
        spReasonUnitout = findViewById(R.id.sp_opsi_reason);
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        ivFoto1 = findViewById(R.id.iv_foto1);
        ivFoto2 = findViewById(R.id.iv_foto2);
        ivFoto3 = findViewById(R.id.iv_foto3);
        ivFoto4 = findViewById(R.id.iv_foto4);
        btnNextnote = findViewById(R.id.btn_nextnote);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reasonunitout);
        spReasonUnitout.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        if (b != null){
            nopol = b.getString("nopol");

            String urlOutKtp = b.getString("url_out_ktp");
            String urlOutUnitDriver = b.getString("url_out_unit_driver");
            String urlOutGatepass = b.getString("url_out_gatepass");
            String urlOutReasonfile = b.getString("url_reason_file");

            if (urlOutKtp != null && !urlOutKtp.isEmpty()) {
                // Load image using Glide
                Glide.with(this)
                        .load(urlOutKtp) // URL of the image
                        .into(ivFoto1); // Your ImageView
            }
            if (urlOutUnitDriver != null && !urlOutUnitDriver.isEmpty()) {
                // Load image using Glide
                Glide.with(this)
                        .load(urlOutUnitDriver) // URL of the image
                        .into(ivFoto2); // Your ImageView
            }

            if (urlOutGatepass != null && !urlOutGatepass.isEmpty()) {
                // Load image using Glide
                Glide.with(this)
                        .load(urlOutGatepass) // URL of the image
                        .into(ivFoto3); // Your ImageView
            }
            if (urlOutReasonfile != null && !urlOutReasonfile.isEmpty()) {
                // Load image using Glide
                Glide.with(this)
                        .load(urlOutReasonfile) // URL of the image
                        .into(ivFoto4); // Your ImageView
            }



            Log.wtf("nopol upload =", nopol);
            Log.wtf("url out ktp =", b.getString("url_out_ktp"));
            Log.wtf("url out unit drv =", b.getString("url_out_unit_driver"));
            Log.wtf("url out gatepass =", b.getString("url_out_gatepass"));
            Log.wtf("url out reason file =", b.getString("url_reason_file"));
        }






        if (!Utils.isPermissionGranted(this)) {
            new AlertDialog.Builder(this)
                    .setTitle("All File Permission")
                    .setMessage("Android 11+, Buka Semua Permission?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            takePermission();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else{
            Toast.makeText(this,"Permission Sudah Dibuka, Silahkan Dicoba Kembali", Toast.LENGTH_LONG).show();
        }

        ivFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog1();
            }
        });
        ivFoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog2();
            }
        });
        ivFoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog3();
            }
        });
        ivFoto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog4();
            }
        });



        btnNextnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                realm.beginTransaction();
//                RealmResults<BodyKendaraan> upd = realm.where(BodyKendaraan.class).findAllAsync();
//                upd.load();
////                upd.first().setPicPenarikan(etPicpenarikan.getText().toString());
//                upd.first().setPicPenyimpanan(etPicpenyimpanan.getText().toString());
////                upd.first().setPicPenjualan(etPicpenjualan.getText().toString());
//                realm.commitTransaction();

                //if (!etPicpenarikan.getText().toString().equals("") || !etPicpenyimpanan.getText().toString().equals("") || !etPicpenjualan.getText().toString().equals("")){
                //updNote();
                //}
                Log.d("masuk sini", "succes berarti");
                updNote();

                // buat pengecekan dulu ini file tidak boleh kosong
//                if (etPrice.getText().toString().equals("") || etPrice.getText().toString().equals("0")){
//                    etPrice.setError("Harga pasar wajib diisi");
//                }
               // if(ivFoto1 == null || ivFoto1.isEmpty()) ||




            }
        });

        getFoto();
    }

    private void updNote(){
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        final String nop = nop1 + "-" + nop2 + "-" + nop3;
        class GetFoto extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UnitoutReasonReturnActivity.this,"","Updating...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showStatus(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
               // String selectedReasonUnitout = spReasonUnitout.getSelectedItem().toString();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,_appid);
                hashMap.put(configuration.KEY_CCODE,_ccode);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_STATUS,"0");
                hashMap.put(configuration.KEY_PARENT_STATUS,"6");
                hashMap.put(configuration.KEY_POLICE_NO,nopol);
                hashMap.put(configuration.KEY_status_unit , spReasonUnitout.getSelectedItem().toString());
                //hashMap.put(configuration.KEY_picPenarikan,etPicpenarikan.getText().toString());
                //hashMap.put(configuration.KEY_picPenarikan,etPicpenarikan.getText().toString());
//                hashMap.put(configuration.KEY_picPenjualan,etPicpenjualan.getText().toString());
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_UPDATE_UNITOUT_REASON,hashMap);
                Log.wtf("data return unit out reason", s);
                return s;
            }
        }

        GetFoto ge = new GetFoto();
        ge.execute();
    }
    private void showStatus(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getString(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.wtf("hasil respon nya ",code);
        if(code.equals("200")){
            Toast.makeText(UnitoutReasonReturnActivity.this,"Unit Berhasil keluar",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UnitoutReasonReturnActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();


            //Bundle b = new Bundle();
            //b.putString("nopol",nopol);
            //i.putExtras(b);
            //startActivity(i);
            //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else if (code.equals("304")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void getFoto(){
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        final String nop = nop1 + "-" + nop2 + "-" + nop3;
        class GetFoto extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UnitoutReasonReturnActivity.this,"","Loading foto...",false,false);
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
                hashMap.put(configuration.KEY_VEHICLEID,nopol);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_FOTO,hashMap);
                Log.wtf("kirim return unit out", s);
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


            foto1 = jos.getString("url_out_ktp");
            foto2 = jos.getString("url_out_unit_driver");
            foto3 = jos.getString("url_out_gatepass");
            foto4 = jos.getString("url_reason_file");

//            Toast.makeText(MobInputFotoActivity.this,""+jos,Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.wtf("angga erik code", code);
        Log.wtf("angga erik entotan", message);
        if(code.equals("200")){
            File dir = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
            try {
                FileUtils.deleteDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (!foto1.equals("null")){
                    ivFoto1.setBackground(null);
                    Glide.with(this).load(foto1).into(ivFoto1);
                    Log.wtf("masuknya","foto1");
                }
                if (!foto2.equals("null")){
                    ivFoto2.setBackground(null);
                    Glide.with(this).load(foto2).into(ivFoto2);
                    Log.wtf("masuknya","foto2");
                }
                if (!foto3.equals("null")){
                    ivFoto3.setBackground(null);
                    Glide.with(this).load(foto3).into(ivFoto3);
                    Log.wtf("masuknya","foto3");
                }
                if (!foto4.equals("null")){
                    ivFoto4.setBackground(null);
                    Glide.with(this).load(foto4).into(ivFoto4);
                    Log.wtf("masuknya","foto4");
                }
            }catch (Exception e){
                e.printStackTrace();
            }



        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Thanks for granting Permission", Toast.LENGTH_SHORT).show();
            }
        }
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
            Log.d("lokasi foto di save = ", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void uploadImageToServer1(final String path) throws IOException, JSONException {
        Log.d("upload","foto ktp");
        Log.d("nama pathnya", path);
        if (path == null || path.isEmpty()) {
            Toast.makeText(UnitoutReasonReturnActivity.this, "Path gambar tidak valid!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            if (!YuliYanto.isNetworkAvailable(UnitoutReasonReturnActivity.this)) {
                Toast.makeText(UnitoutReasonReturnActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_UPDATE_UNITOUT);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_POLICE_NO, nopol);
            data.put(configuration.KEY_POSITION, "1");
            Log.d("ini data nopolnya = ",nopol);
            Log.d("masuk ke put data",configuration.URL_UPDATE_UNITOUT);
            Log.wtf("data yang dikirim", data.toString());
            new MultiPartRequester(this, data, REQUEST_IMAGE_1, this);
           //new MultiPartRequester(, data, REQUEST_IMAGE_1, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Log.e("UploadError", "Error uploading image", e);
            Toast.makeText(UnitoutReasonReturnActivity.this, "Foto 1 gagal di load boss..", Toast.LENGTH_SHORT).show();
            directSetting();

            //Toast.makeText(UnitoutReasonReturnActivity.this,"foto gagal di load boss..",Toast.LENGTH_SHORT).show();
            //directSetting();
        }
    }
    private void uploadImageToServer2(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(UnitoutReasonReturnActivity.this)) {
                Toast.makeText(UnitoutReasonReturnActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_UPDATE_UNITOUT);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_POLICE_NO, nopol);
            data.put(configuration.KEY_POSITION, "2");

            new MultiPartRequester(this, data, REQUEST_IMAGE_2, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(UnitoutReasonReturnActivity.this,"foto 2 Perizinan ada yang salah, tolong diberikan izinnya yaa..",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer3(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(UnitoutReasonReturnActivity.this)) {
                Toast.makeText(UnitoutReasonReturnActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_UPDATE_UNITOUT);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_POLICE_NO, nopol);
            data.put(configuration.KEY_POSITION, "3");

            new MultiPartRequester(this, data, REQUEST_IMAGE_3, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(UnitoutReasonReturnActivity.this,"foto 3 Perizinan ada yang salah, tolong diberikan izinnya yaa..",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }

    private void uploadImageToServer4(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(UnitoutReasonReturnActivity.this)) {
                Toast.makeText(UnitoutReasonReturnActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_UPDATE_UNITOUT);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_POLICE_NO, nopol);
            data.put(configuration.KEY_POSITION, "4");

            new MultiPartRequester(this, data, REQUEST_IMAGE_4, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(UnitoutReasonReturnActivity.this,"foto 4 Perizinan ada yang salah, tolong diberikan izinnya yaa..",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }

    private void directSetting() {
        //Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        //startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.wtf("yang di request", String.valueOf(requestCode));
        Log.wtf("di request fotonya =" , String.valueOf(resultCode));
        if (requestCode == REQUEST_IMAGE_1) {
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                Log.wtf("di request fotonya =" , String.valueOf(path));
                try {
                    Log.wtf("nyoba proses upload to server 1=" , String.valueOf(path));
                    uploadImageToServer1(path);
                } catch (IOException e) {
                    Log.wtf("stacktrace IO to server 1=" , String.valueOf(e));
                    e.printStackTrace();
                } catch (JSONException e) {
                    Log.wtf("stacktrace JSONException to server 1=" , String.valueOf(e));
                    e.printStackTrace();
                }
            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_2){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer2(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_3){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer3(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_4){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer4(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == GALLERY1) {
            if (resultCode == RESULT_OK) {
                Uri contentURI = data.getData();
                Bitmap thumbnail = null;
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer1(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY2) {
            if (resultCode == RESULT_OK) {
                Uri contentURI = data.getData();
                Bitmap thumbnail = null;
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer2(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY3) {
            if (resultCode == RESULT_OK) {
                Uri contentURI = data.getData();
                Bitmap thumbnail = null;
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer3(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }
    }
    private void showPictureDialog1(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Photo Gallery",
                "Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery1();
                                break;
                            case 1:
                                openCameraIntent1();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog2(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Photo Gallery",
                "Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery2();
                                break;
                            case 1:
                                openCameraIntent2();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog3(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Photo Gallery",
                "Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery3();
                                break;
                            case 1:
                                openCameraIntent3();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void showPictureDialog4(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Photo Gallery",
                "Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery4();
                                break;
                            case 1:
                                openCameraIntent4();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery1() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY1);
    }
    public void choosePhotoFromGallery2() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY2);
    }
    public void choosePhotoFromGallery3() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY3);
    }

    public void choosePhotoFromGallery4() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY4);
    }
    private void openCameraIntent1() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_1);
    }
    private void openCameraIntent2() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_2);

    }
    private void openCameraIntent3() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_3);
    }

    private void openCameraIntent4() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_4);
    }

    private File createImageFile1() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        // String imageFileName = nop + "_" + timeStamp + "_";
        String imageFileName = "MAS_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imageFilePath = image.getAbsolutePath();

        return image;
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

    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        try {
            YuliYanto.removeSimpleProgressDialog();
            Log.wtf("res_ok", response.toString());
            if (response == null || response.isEmpty()) {
                Log.e("API Error", "Respon API kosong");
                Toast.makeText(UnitoutReasonReturnActivity.this, "Tidak ada respon dari server!", Toast.LENGTH_SHORT).show();
                return; // Menghentikan eksekusi jika respon kosong
            }

            //Log.wtf("res_ok", response);
            Log.wtf("API Response", response);
            //Toast.makeText(MobInputFotoActivity.this,response.toString(),Toast.LENGTH_LONG).show();
//            try {
            jsonObject = new JSONObject(response);
            Log.wtf("Parsed JSON", jsonObject.toString());
            code = jsonObject.getString(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            Log.wtf("API Code response", code);
            if (code.equals("200")) {
                data = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);
                File dir = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
                FileUtils.deleteDirectory(dir);

                jo = data.getJSONObject(0);
                Log.wtf("JSONObject data foto", jo.toString());

                position = jo.getString("position");
                if (position.equals("1")){
                    image1 = jo.getString("url_out_ktp");
                    ivFoto1.setBackground(null);
                    Glide.with(this).load(image1).into(ivFoto1);
                }else if (position.equals("2")){
                    image2 = jo.getString("url_out_unit_driver");
                    ivFoto2.setBackground(null);
                    Glide.with(this).load(image2).into(ivFoto2);
                }else if (position.equals("3")){
                    image3 = jo.getString("url_out_gatepass");
                    ivFoto3.setBackground(null);
                    Glide.with(this).load(image3).into(ivFoto3);
                }else if (position.equals("4")){
                    image4 = jo.getString("url_reason_file");
                    ivFoto4.setBackground(null);
                    Glide.with(this).load(image4).into(ivFoto4);
                }
//            }else if (code.equals("304")){
//                Toast.makeText(MobInputFotoActivity.this,message,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(UnitoutReasonReturnActivity.this,message,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Log.e("UploadError", "Erro upload ontask completed", e);
            Toast.makeText(UnitoutReasonReturnActivity.this,"tidak bisa upload: " + e.getMessage(),Toast.LENGTH_SHORT).show();
            //Toast.makeText(UnitoutReasonReturnActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void takePermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                Uri uri = Uri.fromParts("package",getPackageName(),null);
                intent.setData(uri);
                startActivityForResult(intent, 101);
            }catch (Exception e){
                e.printStackTrace();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 101);
            }

        }else{
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 101);
        }
    }

    @Override
    public void onTaskCompleted1(String response, int serviceCode) {

    }
}
