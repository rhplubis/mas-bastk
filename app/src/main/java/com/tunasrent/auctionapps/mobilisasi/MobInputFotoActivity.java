package com.tunasrent.auctionapps.mobilisasi;

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
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tunasrent.auctionapps.BuildConfig;
import com.tunasrent.auctionapps.DB.BodyKendaraan;
import com.tunasrent.auctionapps.DB.InputData;
import com.tunasrent.auctionapps.R;
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
import io.realm.RealmResults;

public class MobInputFotoActivity extends AppCompatActivity implements AsyncTaskCompleteListener {
    UserSessionManager session;
    Realm realm;
    Toolbar toolbar;

    Button btnNextnote;
    //    EditText etPicpenarikan;
    EditText etPicpenarikan;
//    EditText etPicpenjualan;

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
    String image5;
    String image6;
    String image7;
    String image8;
    String image9;
    String image10;
    String image11;
    String image12;
    String image13;
    String image14;

    String foto1;
    String foto2;
    String foto3;
    String foto4;
    String foto5;
    String foto6;
    String foto7;
    String foto8;
    String foto9;
    String foto10;
    String foto11;
    String foto12;
    String foto13;
    String foto14;

    private int GALLERY1 = 1;
    private int GALLERY2 = 2;
    private int GALLERY3 = 3;
    private int GALLERY4 = 4;
    private int GALLERY5 = 5;
    private int GALLERY6 = 6;
    private int GALLERY7 = 7;
    private int GALLERY8 = 8;
    private int GALLERY9 = 9;
    private int GALLERY10 = 10;
    private int GALLERY11 = 11;
    private int GALLERY12 = 12;
    private int GALLERY13 = 13;
    private int GALLERY14 = 14;

    public static final int REQUEST_IMAGE = 100;
    public static final int REQUEST_IMAGE_1 = 101;
    public static final int REQUEST_IMAGE_2 = 102;
    public static final int REQUEST_IMAGE_3 = 103;
    public static final int REQUEST_IMAGE_4 = 104;
    public static final int REQUEST_IMAGE_5 = 105;
    public static final int REQUEST_IMAGE_6 = 106;
    public static final int REQUEST_IMAGE_7 = 107;
    public static final int REQUEST_IMAGE_8 = 108;
    public static final int REQUEST_IMAGE_9 = 109;
    public static final int REQUEST_IMAGE_10 = 110;
    public static final int REQUEST_IMAGE_11 = 111;
    public static final int REQUEST_IMAGE_12 = 112;
    public static final int REQUEST_IMAGE_13 = 113;
    public static final int REQUEST_IMAGE_14 = 114;

    public static final int REQUEST_PERMISSION = 200;
    private static final String IMAGE_DIRECTORY = "/mas_temp";
    private String imageFilePath = "";

    String var_nopol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob_input_foto);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kondisi Body Kendaraan");
        toolbar.setTitleTextColor(Color.WHITE);

        session = new UserSessionManager(getApplicationContext());
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
//        etPicpenarikan = findViewById(R.id.et_pic_penarikan);
        etPicpenarikan = findViewById(R.id.et_pic_penarikan);
//        etPicpenjualan = findViewById(R.id.et_pic_penjualan);
        btnNextnote = findViewById(R.id.btn_nextnote);

        Bundle b = getIntent().getExtras();
        if (b != null){
            var_nopol = b.getString("parse_nopol");
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

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
//                PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    REQUEST_PERMISSION);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                    != PackageManager.PERMISSION_GRANTED ||
//                    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                            != PackageManager.PERMISSION_GRANTED) {
//                //request permission on the fly
//                ActivityCompat.requestPermissions(this, new String[]{
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE
//                }, 1);
//            }
//        }

//        directSetting();


//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance(); //creating  database oject
//        RealmResults<BodyKendaraan> bodyKendaraan = realm.where(BodyKendaraan.class).findAllAsync();
//        bodyKendaraan.load();
//        if (bodyKendaraan.isEmpty()){
//            realm.beginTransaction();
//            BodyKendaraan bodys = realm.createObject(BodyKendaraan.class);
//
//            bodys.setPicPenarikan("");
//            bodys.setPicPenyimpanan("");
//            bodys.setPicPenjualan("");
//            realm.commitTransaction();
//
////            etPicpenarikan.setText("");
//            etPicpenyimpanan.setText("");
////            etPicpenjualan.setText("");
//            //realm.commitTransaction();
//
//        }else {
//            //show data
////            etPicpenarikan.setText(bodyKendaraan.first().getPicPenarikan());
//            etPicpenyimpanan.setText(bodyKendaraan.first().getPicPenyimpanan());
////            etPicpenjualan.setText(bodyKendaraan.first().getPicPenjualan());
//        }

//        final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//        linkFotos.load();
//        if (!linkFotos.isEmpty()){
//
//            if (linkFotos.first().getFoto1() != null){
//                ivFoto1.setBackground(null);
//                ivFoto1.setImageURI(Uri.parse(linkFotos.first().getFoto1()));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(linkFotos.first().getFoto1())),Toast.LENGTH_LONG).show();
//            }
//            if (linkFotos.first().getFoto2() != null){
//                ivFoto2.setImageURI(Uri.parse(linkFotos.first().getFoto2()));
//            }
//            if (linkFotos.first().getFoto3() != null) {
//                ivFoto3.setImageURI(Uri.parse(linkFotos.first().getFoto3()));
//            }
//            if (linkFotos.first().getFoto4() != null) {
//                ivFoto4.setImageURI(Uri.parse(linkFotos.first().getFoto4()));
//            }
//            if (linkFotos.first().getFoto5() != null) {
//                ivFoto5.setImageURI(Uri.parse(linkFotos.first().getFoto5()));
//            }
//            if (linkFotos.first().getFoto6() != null) {
//                ivFoto6.setImageURI(Uri.parse(linkFotos.first().getFoto6()));
//            }
//            if (linkFotos.first().getFoto7() != null) {
//                ivFoto7.setImageURI(Uri.parse(linkFotos.first().getFoto7()));
//            }
//            if (linkFotos.first().getFoto8() != null) {
//                ivFoto8.setImageURI(Uri.parse(linkFotos.first().getFoto8()));
//            }
//            if (linkFotos.first().getFoto9() != null) {
//                ivFoto9.setImageURI(Uri.parse(linkFotos.first().getFoto9()));
//            }
//            if (linkFotos.first().getFoto10() != null) {
//                ivFoto10.setImageURI(Uri.parse(linkFotos.first().getFoto10()));
//            }
//            if (linkFotos.first().getFoto11() != null) {
//                ivFoto11.setImageURI(Uri.parse(linkFotos.first().getFoto11()));
//            }
//            if (linkFotos.first().getFoto12() != null) {
//                ivFoto12.setImageURI(Uri.parse(linkFotos.first().getFoto12()));
//            }
//        }

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
        ivFoto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog5();
            }
        });
        ivFoto6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog6();
            }
        });
        ivFoto7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog7();
            }
        });
        ivFoto8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog8();
            }
        });
        ivFoto9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog9();
            }
        });
        ivFoto10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog10();
            }
        });
        ivFoto11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog11();
            }
        });
        ivFoto12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog12();
            }
        });
        ivFoto13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog13();
            }
        });
        ivFoto14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog14();
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
                updNote();
                //}
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
                loading = ProgressDialog.show(MobInputFotoActivity.this,"","Updating...",false,false);
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
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,_appid);
                hashMap.put(configuration.KEY_CCODE,_ccode);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_STATUS,"0");
                hashMap.put(configuration.KEY_PARENT_STATUS,"2");
                hashMap.put(configuration.KEY_NOPOL,var_nopol);
//                hashMap.put(configuration.KEY_picPenarikan,etPicpenarikan.getText().toString());
                hashMap.put(configuration.KEY_picPenarikan,etPicpenarikan.getText().toString());
//                hashMap.put(configuration.KEY_picPenjualan,etPicpenjualan.getText().toString());
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_UPD_NOTE,hashMap);
                Log.wtf("kirim", s);
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

        if(code.equals("200")){
            Toast.makeText(MobInputFotoActivity.this,"Note diupdate",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MobInputFotoActivity.this, MobInputSignActivity.class);
            Bundle b = new Bundle();
            b.putString("parse_nopol",var_nopol);
            i.putExtras(b);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                loading = ProgressDialog.show(MobInputFotoActivity.this,"","Loading foto...",false,false);
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
                hashMap.put(configuration.KEY_NOPOL,var_nopol);
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


            foto1 = jos.getString("img1");
            foto2 = jos.getString("img2");
            foto3 = jos.getString("img3");
            foto4 = jos.getString("img4");
            foto5 = jos.getString("img5");
            foto6 = jos.getString("img6");
            foto7 = jos.getString("img7");
            foto8 = jos.getString("img8");
            foto9 = jos.getString("img9");
            foto10 = jos.getString("img10");
            foto11 = jos.getString("img11");
            foto12 = jos.getString("img12");
            foto12 = jos.getString("img12");
//            Toast.makeText(MobInputFotoActivity.this,""+jos,Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                if (!foto5.equals("null")){
                    ivFoto5.setBackground(null);
                    Glide.with(this).load(foto5).into(ivFoto5);
                }
                if (!foto6.equals("null")){
                    ivFoto6.setBackground(null);
                    Glide.with(this).load(foto6).into(ivFoto6);
                }
                if (!foto7.equals("null")){
                    ivFoto7.setBackground(null);
                    Glide.with(this).load(foto7).into(ivFoto7);
                }
                if (!foto8.equals("null")){
                    ivFoto8.setBackground(null);
                    Glide.with(this).load(foto8).into(ivFoto8);
                }
                if (!foto9.equals("null")){
                    ivFoto9.setBackground(null);
                    Glide.with(this).load(foto9).into(ivFoto9);
                }
                if (!foto10.equals("null")){
                    ivFoto10.setBackground(null);
                    Glide.with(this).load(foto10).into(ivFoto10);
                }
                if (!foto11.equals("null")){
                    ivFoto11.setBackground(null);
                    Glide.with(this).load(foto11).into(ivFoto11);
                }
                if (!foto12.equals("null")){
                    ivFoto12.setBackground(null);
                    Glide.with(this).load(foto12).into(ivFoto12);
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
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void uploadImageToServer1(final String path) throws IOException, JSONException {
        Log.d("upload","1");
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "1");

            new MultiPartRequester(this, data, REQUEST_IMAGE_1, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
//        try {
//            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
//                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            HashMap<String, String> data = new HashMap<>();
//            data.put("url", configuration.URL_INSERT_FOTO);
//            data.put("filename", path);
//            data.put(configuration.KEY_APPID, _appid);
//            data.put(configuration.KEY_CCODE, _ccode);
//            data.put(configuration.KEY_TOKEN, _token);
//            data.put(configuration.KEY_USERNAME, _name);
//            data.put(configuration.KEY_NOPOL, var_nopol);
//            data.put(configuration.KEY_POSITION, "1");
//
//            new MultiPartRequester(this, data, REQUEST_IMAGE_1, this);
//            YuliYanto.showSimpleProgressDialog(this);
//        }catch (Exception e){
//            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
//            directSetting();
//        }
    }
    private void uploadImageToServer2(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "2");

            new MultiPartRequester(this, data, REQUEST_IMAGE_2, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer3(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "3");

            new MultiPartRequester(this, data, REQUEST_IMAGE_3, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer4(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "4");

            new MultiPartRequester(this, data, REQUEST_IMAGE_4, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer5(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "5");

            new MultiPartRequester(this, data, REQUEST_IMAGE_5, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer6(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "6");

            new MultiPartRequester(this, data, REQUEST_IMAGE_6, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer7(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "7");

            new MultiPartRequester(this, data, REQUEST_IMAGE_7, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer8(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "8");

            new MultiPartRequester(this, data, REQUEST_IMAGE_8, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer9(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "9");

            new MultiPartRequester(this, data, REQUEST_IMAGE_9, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer10(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "10");

            new MultiPartRequester(this, data, REQUEST_IMAGE_10, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer11(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "11");

            new MultiPartRequester(this, data, REQUEST_IMAGE_11, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer12(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "12");

            new MultiPartRequester(this, data, REQUEST_IMAGE_12, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer13(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "13");

            new MultiPartRequester(this, data, REQUEST_IMAGE_13, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }
    private void uploadImageToServer14(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(MobInputFotoActivity.this)) {
                Toast.makeText(MobInputFotoActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("url", configuration.URL_INSERT_FOTO);
            data.put("filename", path);
            data.put(configuration.KEY_APPID, _appid);
            data.put(configuration.KEY_CCODE, _ccode);
            data.put(configuration.KEY_TOKEN, _token);
            data.put(configuration.KEY_USERNAME, _name);
            data.put(configuration.KEY_NOPOL, var_nopol);
            data.put(configuration.KEY_POSITION, "14");

            new MultiPartRequester(this, data, REQUEST_IMAGE_14, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }

    private void directSetting() {
        Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_1) {
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer1(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFoto1 = realm.where(LinkFoto.class).findAllAsync();
//                linkFoto1.load();
//                if (!linkFoto1.isEmpty()){
//                    if (ivFoto1.getDrawable() != null){
//                        File file = new File(linkFoto1.first().getFoto1());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> data1 = realm.where(LinkFoto.class)
//                                .equalTo("foto1", linkFoto1.first().getFoto1())
//                                .findAll();
//                        data1.deleteAllFromRealm();
//                        realm.commitTransaction();
//
//                        ivFoto1.setImageResource(0);
//
//                        Toast.makeText(MobInputFotoActivity.this,"Delete Sukses",Toast.LENGTH_LONG).show();
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto1.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto_1 = realm.createObject(LinkFoto.class);
//                linkFoto_1.setFoto1(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();

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

//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFoto2 = realm.where(LinkFoto.class).findAllAsync();
//                linkFoto2.load();
//                if (!linkFoto2.isEmpty()){
//                    if (ivFoto2.getDrawable() != null){
//                        File file = new File(linkFoto2.first().getFoto2());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> data2 = realm.where(LinkFoto.class)
//                                .equalTo("foto2", linkFoto2.first().getFoto2())
//                                .findAll();
//                        data2.deleteAllFromRealm();
//                        realm.commitTransaction();
//
//                        ivFoto2.setImageResource(0);
//
//                        Toast.makeText(MobInputFotoActivity.this,"Delete Sukses",Toast.LENGTH_LONG).show();
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto2.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto_2 = realm.createObject(LinkFoto.class);
//                linkFoto_2.setFoto2(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
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
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFoto3 = realm.where(LinkFoto.class).findAllAsync();
//                linkFoto3.load();
//                if (!linkFoto3.isEmpty()){
//                    if (ivFoto3.getDrawable() != null){
//                        File file = new File(linkFoto3.first().getFoto3());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto3", linkFoto3.first().getFoto3())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto3.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto3.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto3(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
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
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto4.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto4());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto4", linkFotos.first().getFoto4())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto4.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto4.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto4(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_5){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer5(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto5.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto5());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto5", linkFotos.first().getFoto5())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto5.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto5.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto5(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_6){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer6(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto6.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto6());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto6", linkFotos.first().getFoto6())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto6.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto6.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto6(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_7){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer7(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto7.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto7());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto7", linkFotos.first().getFoto7())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto7.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto7.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto7(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_8){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer8(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto8.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto8());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto8", linkFotos.first().getFoto8())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto8.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto8.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto8(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_9){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer9(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto9.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto9());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto9", linkFotos.first().getFoto9())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto9.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto9.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto9(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_10){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer10(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto10.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto10());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto10", linkFotos.first().getFoto10())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto10.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto10.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto10(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_11){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer11(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto11.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto11());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto11", linkFotos.first().getFoto11())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto11.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto11.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto11(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_12){
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer12(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                final RealmResults<LinkFoto> linkFotos = realm.where(LinkFoto.class).findAllAsync();
//                linkFotos.load();
//                if (!linkFotos.isEmpty()){
//                    if (ivFoto12.getDrawable() != null){
//                        File file = new File(linkFotos.first().getFoto5());
//                        file.delete();
//
//                        realm.beginTransaction();
//                        RealmResults<LinkFoto> datos = realm.where(LinkFoto.class)
//                                .equalTo("foto12", linkFotos.first().getFoto12())
//                                .findAll();
//                        datos.deleteAllFromRealm();
//                        realm.commitTransaction();
//                        ivFoto12.setImageResource(0);
//                    }else {
//                        Toast.makeText(getApplication(),"File not found",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                ivFoto12.setImageURI(Uri.parse(imageFilePath));
//                Toast.makeText(MobInputFotoActivity.this,String.valueOf(Uri.parse(imageFilePath)),Toast.LENGTH_LONG).show();
//
//                Realm.init(this);
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                LinkFoto linkFoto = realm.createObject(LinkFoto.class);
//                linkFoto.setFoto12(String.valueOf(Uri.parse(imageFilePath)));
//                realm.commitTransaction();
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_13) {
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer13(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_IMAGE_14) {
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                try {
                    uploadImageToServer14(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == GALLERY1) {
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

        }else if (requestCode == GALLERY4) {
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
                    uploadImageToServer4(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY5) {
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
                    uploadImageToServer5(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY6) {
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
                    uploadImageToServer6(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY7) {
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
                    uploadImageToServer7(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY8) {
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
                    uploadImageToServer8(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY9) {
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
                    uploadImageToServer9(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY10) {
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
                    uploadImageToServer10(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY11) {
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
                    uploadImageToServer11(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY12) {
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
                    uploadImageToServer12(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY13) {
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
                    uploadImageToServer13(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode == GALLERY14) {
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
                    uploadImageToServer14(path);
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
    private void showPictureDialog5(){
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
                                choosePhotoFromGallery5();
                                break;
                            case 1:
                                openCameraIntent5();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog6(){
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
                                choosePhotoFromGallery6();
                                break;
                            case 1:
                                openCameraIntent6();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog7(){
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
                                choosePhotoFromGallery7();
                                break;
                            case 1:
                                openCameraIntent7();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog8(){
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
                                choosePhotoFromGallery8();
                                break;
                            case 1:
                                openCameraIntent8();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog9(){
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
                                choosePhotoFromGallery9();
                                break;
                            case 1:
                                openCameraIntent9();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog10(){
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
                                choosePhotoFromGallery10();
                                break;
                            case 1:
                                openCameraIntent10();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog11(){
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
                                choosePhotoFromGallery11();
                                break;
                            case 1:
                                openCameraIntent11();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog12(){
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
                                choosePhotoFromGallery12();
                                break;
                            case 1:
                                openCameraIntent12();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog13(){
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
                                choosePhotoFromGallery13();
                                break;
                            case 1:
                                openCameraIntent13();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void showPictureDialog14(){
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
                                choosePhotoFromGallery14();
                                break;
                            case 1:
                                openCameraIntent14();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery1() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY1);
    }
    public void choosePhotoFromGallery2() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY2);
    }
    public void choosePhotoFromGallery3() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY3);
    }
    public void choosePhotoFromGallery4() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY4);
    }
    public void choosePhotoFromGallery5() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY5);
    }
    public void choosePhotoFromGallery6() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY6);
    }
    public void choosePhotoFromGallery7() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY7);
    }
    public void choosePhotoFromGallery8() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY8);
    }
    public void choosePhotoFromGallery9() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY9);
    }
    public void choosePhotoFromGallery10() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY10);
    }
    public void choosePhotoFromGallery11() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY11);
    }
    public void choosePhotoFromGallery12() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY12);
    }
    public void choosePhotoFromGallery13() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY13);
    }
    public void choosePhotoFromGallery14() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY14);
    }

    private void openCameraIntent1() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_1);

//        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
//
//            File photoFile = null;
//            try {
//                photoFile = createImageFile1();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//            Uri photoUri = FileProvider.getUriForFile(this, getPackageName() +".provider", photoFile);
//            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//            startActivityForResult(pictureIntent, REQUEST_IMAGE_1);
//        }
    }
    private void openCameraIntent2() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_2);
//        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
//
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//            Uri photoUri = FileProvider.getUriForFile(this, getPackageName() +".provider", photoFile);
//            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//            startActivityForResult(pictureIntent, REQUEST_IMAGE_2);
//        }
    }
    private void openCameraIntent3() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_3);
    }
    private void openCameraIntent4() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_4);
    }
    private void openCameraIntent5() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_5);
    }
    private void openCameraIntent6() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_6);
    }
    private void openCameraIntent7() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_7);
    }
    private void openCameraIntent8() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_8);
    }
    private void openCameraIntent9() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_9);
    }
    private void openCameraIntent10() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_10);
    }
    private void openCameraIntent11() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_11);
    }
    private void openCameraIntent12() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_12);
    }
    private void openCameraIntent13() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_13);
    }
    private void openCameraIntent14() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_14);
    }
    private File createImageFile1() throws IOException{
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;

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
            //Toast.makeText(MobInputFotoActivity.this,response.toString(),Toast.LENGTH_LONG).show();
//            try {
            jsonObject = new JSONObject(response);
            code = jsonObject.getString(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            if (code.equals("200")) {
                data = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);
                File dir = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
                FileUtils.deleteDirectory(dir);

                jo = data.getJSONObject(0);
                position = jo.getString("position");
                if (position.equals("1")){
                    image1 = jo.getString("img1");
                    ivFoto1.setBackground(null);
                    Glide.with(this).load(image1).into(ivFoto1);
                }else if (position.equals("2")){
                    image2 = jo.getString("img2");
                    ivFoto2.setBackground(null);
                    Glide.with(this).load(image2).into(ivFoto2);
                }else if (position.equals("3")){
                    image3 = jo.getString("img3");
                    ivFoto3.setBackground(null);
                    Glide.with(this).load(image3).into(ivFoto3);
                }else if (position.equals("4")){
                    image4 = jo.getString("img4");
                    ivFoto4.setBackground(null);
                    Glide.with(this).load(image4).into(ivFoto4);
                }else if (position.equals("5")){
                    image5 = jo.getString("img5");
                    ivFoto5.setBackground(null);
                    Glide.with(this).load(image5).into(ivFoto5);
                }else if (position.equals("6")){
                    image6 = jo.getString("img6");
                    ivFoto6.setBackground(null);
                    Glide.with(this).load(image6).into(ivFoto6);
                }else if (position.equals("7")){
                    image7 = jo.getString("img7");
                    ivFoto7.setBackground(null);
                    Glide.with(this).load(image7).into(ivFoto7);
                }else if (position.equals("8")){
                    image8 = jo.getString("img8");
                    ivFoto8.setBackground(null);
                    Glide.with(this).load(image8).into(ivFoto8);
                }else if (position.equals("9")){
                    image9 = jo.getString("img9");
                    ivFoto9.setBackground(null);
                    Glide.with(this).load(image9).into(ivFoto9);
                }else if (position.equals("10")){
                    image10 = jo.getString("img10");
                    ivFoto10.setBackground(null);
                    Glide.with(this).load(image10).into(ivFoto10);
                }else if (position.equals("11")){
                    image11 = jo.getString("img11");
                    ivFoto11.setBackground(null);
                    Glide.with(this).load(image11).into(ivFoto11);
                }else if (position.equals("12")){
                    image12 = jo.getString("img12");
                    ivFoto12.setBackground(null);
                    Glide.with(this).load(image12).into(ivFoto12);
                }else if (position.equals("13")){
                    image13 = jo.getString("img13");
                    ivFoto13.setBackground(null);
                    Glide.with(this).load(image13).into(ivFoto13);
                }else if (position.equals("14")){
                    image14 = jo.getString("img14");
                    ivFoto14.setBackground(null);
                    Glide.with(this).load(image14).into(ivFoto14);
                }
//            }else if (code.equals("304")){
//                Toast.makeText(MobInputFotoActivity.this,message,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MobInputFotoActivity.this,message,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(MobInputFotoActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
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
