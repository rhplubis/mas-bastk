package com.tunasrent.auctionapps.warehouse;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.os.Build;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.tunasrent.auctionapps.BuildConfig;
import com.tunasrent.auctionapps.DB.InputData;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.dispatcher.DisInputFormActivity;
import com.tunasrent.auctionapps.model.Bus;
import com.tunasrent.auctionapps.model.BusAdapter;
import com.tunasrent.auctionapps.model.Merk;
import com.tunasrent.auctionapps.model.MerkAdapter;
import com.tunasrent.auctionapps.model.Pool;
import com.tunasrent.auctionapps.model.PoolAdapter;
import com.tunasrent.auctionapps.model.SetPool;
import com.tunasrent.auctionapps.model.SetPoolAdapter;
import com.tunasrent.auctionapps.model.Truck;
import com.tunasrent.auctionapps.model.TruckAdapter;
import com.tunasrent.auctionapps.model.Vendor;
import com.tunasrent.auctionapps.model.VendorAdapter;
import com.tunasrent.auctionapps.taksasi.TaksasiSetpriceActivity;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class WarehouseCekPriceActivity extends AppCompatActivity implements AsyncTaskCompleteListener {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private static final String IMAGE_DIRECTORY = "/mas_temp";
    public static final int REQUEST_IMAGE = 100;
    Toolbar toolbar;
    Button btnSave;
    UserSessionManager session;
    ImageView ivPicpenjualan;
    ImageView ivWarehouse;

    Realm realm;

    JSONObject jsonObject;
    JSONArray data;
    JSONObject jo;

    EditText etPrice;
    EditText etPrice_recomendation;
    EditText etRepair_machine;
    EditText etRepair_interior;
    EditText etRepair_exterior;
    EditText etCost_document;
    EditText etMargin;
    EditText etPembulatan;
    EditText etRemark;

    String position;
    String message;
    String _name, _fullname, _appid, _ccode, _token, _group;
    String nopol;
    String vhc_cat_name;
    String var_picpenjualan;
    String var_market_price;
    String var_recomended_price;
    String var_repair_machine;
    String var_repair_interior;
    String var_repair_exterior;
    String var_cost_document;
    String var_margin;
    String var_pembulatan;
    String var_unit_condition;

    String poolId;
    String pool;
    String var_pool;
    String warehouse;

//    private List<SetPool> poolList = new ArrayList<>();
    private ArrayList<SetPool> setPoolArrayList;
    ArrayAdapter<String> spinnerAdapter;
    SetPoolAdapter poolAdapter;
//    Spinner spList;
    private ArrayList<String> arrayList;
    private ArrayList<SetPool> setarrayList;

    Dialog myDialog;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_cek_price);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Harga Jual");
        toolbar.setTitleTextColor(Color.WHITE);

        etPrice = findViewById(R.id.et_price);
        etPrice_recomendation = findViewById(R.id.et_price_recomendation);
        etRepair_machine = findViewById(R.id.et_repair_machine);
        etRepair_interior = findViewById(R.id.et_repair_interior);
        etRepair_exterior = findViewById(R.id.et_repair_exterior);
        etCost_document = findViewById(R.id.et_cost_document);
        etMargin = findViewById(R.id.et_margin);
        etPembulatan = findViewById(R.id.et_pembulatan);
        etRemark = findViewById(R.id.et_remark);
//        spList = findViewById(R.id.sp_list);
        ivPicpenjualan = findViewById(R.id.iv_picpenjualan);
        ivWarehouse = findViewById(R.id.iv_warehouse);
        btnSave = findViewById(R.id.btn_save);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        myDialog = new Dialog(this);
        setPoolArrayList = new ArrayList<SetPool>();
        poolList();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            nopol = b.getString("nopol");
            var_pool = b.getString("pool_id");
            Log.d("TAG-marketprice", "onCreate: " + var_pool);
            vhc_cat_name = b.getString("vhc_cat_name");
            var_picpenjualan = b.getString("picpenjualan");
            var_market_price= b.getString("market_price");

            var_recomended_price= b.getString("recomended_price");
            var_repair_machine= b.getString("repair_machine");
            var_repair_interior= b.getString("repair_interior");
            var_repair_exterior= b.getString("repair_exterior");
            var_cost_document= b.getString("cost_document");
            var_margin= b.getString("margin");
            var_pembulatan= b.getString("pembulatan");
            var_unit_condition= b.getString("unit_condition");

            etPrice.setText(var_market_price);
            etPrice_recomendation.setText(var_recomended_price);
            etRepair_machine.setText(var_repair_machine);
            etRepair_interior.setText(var_repair_interior);
            etRepair_exterior.setText(var_repair_exterior);
            etCost_document.setText(var_cost_document);
            etMargin.setText(var_margin);
            etPembulatan.setText(var_pembulatan);
            etRemark.setText(var_unit_condition);
            Glide.with(this).load(var_picpenjualan).into(ivPicpenjualan);

            try {
                String originalString = etPrice.getText().toString();
                Long longval,longval1,longval2,longval3,longval4,longval5,longval6,longval7;
//            if (originalString.contains(",")) {
//                originalString = originalString.replaceAll(",", "");
//            }
                longval = Long.parseLong(etPrice.getText().toString());
                longval1 = Long.parseLong(etPrice_recomendation.getText().toString());
                longval2 = Long.parseLong(etRepair_machine.getText().toString());
                longval3 = Long.parseLong(etRepair_interior.getText().toString());
                longval4 = Long.parseLong(etRepair_exterior.getText().toString());
                longval5 = Long.parseLong(etMargin.getText().toString());
                longval6 = Long.parseLong(etPembulatan.getText().toString());
                longval7 = Long.parseLong(etCost_document.getText().toString());

                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);
                String formattedString1 = formatter.format(longval1);
                String formattedString2 = formatter.format(longval2);
                String formattedString3 = formatter.format(longval3);
                String formattedString4 = formatter.format(longval4);
                String formattedString5 = formatter.format(longval5);
                String formattedString6 = formatter.format(longval6);
                String formattedString7 = formatter.format(longval7);

                //setting text after format to EditText
                etPrice.setText(formattedString);
                etPrice_recomendation.setText(formattedString1);
                etRepair_machine.setText(formattedString2);
                etRepair_interior.setText(formattedString3);
                etRepair_exterior.setText(formattedString4);
                etCost_document.setText(formattedString7);
                etMargin.setText(formattedString5);
                etPembulatan.setText(formattedString6);

            }catch (Exception e){

            }
        }
        Log.d("TAG-nopol", "onCreate: " + nopol);
        Log.d("TAG-pool", "onCreate: " + var_pool);
        Log.d("TAG-vhc_cat_name", "onCreate: " + vhc_cat_name);

//        spList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0){
//                    var_pool = "";
//                }else {
//                    position = position - 1;
//                    var_pool = setPoolArrayList.get(position).getPool_id();
//                    Log.d("var_pool",var_pool);
////                    Toast.makeText(WarehouseCekPriceActivity.this,setPoolArrayList.get(position).getPool_id(),Toast.LENGTH_SHORT).show();
//                }
//                Log.d("var_pool1",var_pool);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (spList.getSelectedItem().equals("")){
//                    Toast.makeText(WarehouseCekPriceActivity.this,"Pool wajib diisi !!",Toast.LENGTH_SHORT).show();
//                }else {
                    if (var_pool.equals("")){
                        Toast.makeText(WarehouseCekPriceActivity.this,"Lokasi Pool tidak boleh kosong",Toast.LENGTH_SHORT).show();
                    }else if (ivWarehouse.getDrawable() == null){
                        Toast.makeText(WarehouseCekPriceActivity.this,"Tanda tangan tidak boleh kosong",Toast.LENGTH_SHORT).show();
                    }else {
                        sendPool();
                    }
//                }
            }
        });


//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (var_pool == null || var_pool.equals("")) {
//                    Toast.makeText(WarehouseCekPriceActivity.this, "Lokasi Pool tidak boleh kosong", Toast.LENGTH_SHORT).show();
//                } else if (ivWarehouse.getDrawable() == null) {
//                    Toast.makeText(WarehouseCekPriceActivity.this, "Tanda tangan tidak boleh kosong", Toast.LENGTH_SHORT).show();
//                } else {
//                    sendPool();
//                }
//            }
//        });

    }

//    public void ShowPopupPool(View v) {
//        final TextView tvClose;
//        final Button btnSave;
//
//        myDialog.setContentView(R.layout.popup_set_pool);
//        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
//        spList = (Spinner) myDialog.findViewById(R.id.sp_list);
//        btnSave = (Button) myDialog.findViewById(R.id.btn_save);
//
//        arrayList = new ArrayList<String>();
//        poolList();
//
//        tvClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        spList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0){
//                    var_pool = "";
//                }else {
//                    position = position - 1;
//                    var_pool = setPoolArrayList.get(position).getPool_id();
//                    //Toast.makeText(WarehouseCekPriceActivity.this,setPoolArrayList.get(position).getPool_id(),Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (spList.getSelectedItem().equals("")){
//                    Toast.makeText(WarehouseCekPriceActivity.this,"Pool wajib diisi !!",Toast.LENGTH_SHORT).show();
//                }else {
//                    if (var_pool.equals("")){
//                        Toast.makeText(WarehouseCekPriceActivity.this,"Lokasi Pool tidak boleh kosong",Toast.LENGTH_SHORT).show();
//                    }else {
//                        sendPool();
//                    }
//                }
//            }
//        });
//
//        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }

    private void poolList() {

        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(WarehouseCekPriceActivity.this,"","Loading data",false,false);
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
                hashMap.put(configuration.KEY_APPID, _appid);
                hashMap.put(configuration.KEY_CCODE, _ccode);
                hashMap.put(configuration.KEY_TOKEN, _token);
                hashMap.put(configuration.KEY_USERNAME, _name);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_GET_POOL,hashMap);
                Log.wtf("vhc", s);
                return s;
            }
        }
        GetData ge = new GetData();
        ge.execute();
    }

    private void showData(String json){
        setPoolArrayList.clear();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String code = jsonObject.getString(configuration.TAG_CODE);
            Log.wtf("kode", "result: " + code);
            String message = jsonObject.getString(configuration.TAG_MESSAGE);
            Log.wtf("message", "result: " + message);

            if (code.equals("200")) {
                JSONArray result = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    poolId = jo.getString(configuration.KEY_POOL_ID);
                    pool = jo.getString(configuration.KEY_POOL_ADDRESS);

                    SetPool pool1 = new SetPool(poolId,pool);
                    setPoolArrayList.add(pool1);
                }

//                List<String> lables = new ArrayList<String>();
//                lables.add("-- Select Pool --");
//                for (int i = 0; i < setPoolArrayList.size(); i++) {
//                    lables.add(setPoolArrayList.get(i).getPool_address());
//                }
//                // Creating adapter for spinner
//                spinnerAdapter = new ArrayAdapter<String>(this,
//                        android.R.layout.simple_spinner_item, lables);
//                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spList.setAdapter(spinnerAdapter);

            } else {
                Toast.makeText(WarehouseCekPriceActivity.this, "Data Not Found, " + message, Toast.LENGTH_SHORT).show();
            }
//            Log.d("var_pool",var_pool);
        } catch (JSONException e) {
            //e.printStackTrace();
            Toast.makeText(WarehouseCekPriceActivity.this,"Please Check Your Connection",Toast.LENGTH_SHORT).show();
        }

    }

//    private void showData(String json) {
//        setPoolArrayList.clear();
//        String var_pool = ""; // Inisialisasi variabel var_pool
//
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            String code = jsonObject.getString(configuration.TAG_CODE);
//            Log.wtf("kode", "result: " + code);
//            String message = jsonObject.getString(configuration.TAG_MESSAGE);
//            Log.wtf("message", "result: " + message);
//
//            if (code.equals("200")) {
//                JSONArray result = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);
//
//                for (int i = 0; i < result.length(); i++) {
//                    JSONObject jo = result.getJSONObject(i);
//
//                    poolId = jo.getString(configuration.KEY_POOL_ID);
//                    pool = jo.getString(configuration.KEY_POOL_ADDRESS);
//
//                    SetPool pool1 = new SetPool(poolId, pool);
//                    setPoolArrayList.add(pool1);
//                }
//
//                // ... (kode lainnya)
//
//                // Inisialisasi atau memberi nilai pada var_pool di dalam blok if
//                var_pool = "some_value"; // Gantilah "some_value" dengan nilai yang sesuai
//
//            } else {
//                Toast.makeText(WarehouseCekPriceActivity.this, "Data Not Found, " + message, Toast.LENGTH_SHORT).show();
//            }
//
//            // Sekarang var_pool sudah diinisialisasi di luar blok if-else
//            Log.d("var_pool", var_pool);
//
//        } catch (JSONException e) {
//            //e.printStackTrace();
//            Toast.makeText(WarehouseCekPriceActivity.this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
//        }
//    }


    private void sendPool() {

        class SendPool extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(WarehouseCekPriceActivity.this,"","Processing data",false,false);
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
                hashMap.put(configuration.KEY_APPID, _appid);
                hashMap.put(configuration.KEY_CCODE, _ccode);
                hashMap.put(configuration.KEY_TOKEN, _token);
                hashMap.put(configuration.KEY_USERNAME, _name);
                hashMap.put(configuration.KEY_NOPOL, nopol);
                hashMap.put(configuration.KEY_POOL, var_pool);
                hashMap.put(configuration.KEY_VHC_CAT, vhc_cat_name);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_WAREHOUSE,hashMap);
//                Log.wtf("vhc", s);
                Log.d("TAG-s", "doInBackground: "+ s);
                return s;
            }
        }
        SendPool ge = new SendPool();
        ge.execute();
    }

    private void showRespon(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(WarehouseCekPriceActivity.this,"Please Check Your Connection",Toast.LENGTH_SHORT).show();
        }
        if (code == 200) {
            Toast.makeText(WarehouseCekPriceActivity.this, "Pool berhasil di setting", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(WarehouseCekPriceActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else {
            Toast.makeText(WarehouseCekPriceActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void PopupSignWirehouse(View v) {
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
            if (!YuliYanto.isNetworkAvailable(WarehouseCekPriceActivity.this)) {
                Toast.makeText(WarehouseCekPriceActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
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
            data.put(configuration.KEY_POSITION, "27");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(WarehouseCekPriceActivity.this,"Failed",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        try {
            YuliYanto.removeSimpleProgressDialog();
//            Log.wtf("res_ok", response.toString());
//            Toast.makeText(WarehouseCekPriceActivity.this,response.toString(),Toast.LENGTH_LONG).show();
            try {
                jsonObject = new JSONObject(response);
                code = jsonObject.getInt(configuration.TAG_CODE);
                message = jsonObject.getString(configuration.TAG_MESSAGE);
                data = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (code == 200) {
                File dir = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
                FileUtils.deleteDirectory(dir);
                //Toast.makeText(DisInputSignActivity.this,"Sukses",Toast.LENGTH_SHORT).show();
                jo = data.getJSONObject(0);
                position = jo.getString("position");

                warehouse = jo.getString("picGudang");

                Glide.with(this).load(warehouse).into(ivWarehouse);
                myDialog.dismiss();

            }else if (code == 304){
                Toast.makeText(WarehouseCekPriceActivity.this,message,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(WarehouseCekPriceActivity.this,message,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(WarehouseCekPriceActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(WarehouseCekPriceActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
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
        WarehouseCekPriceActivity.this.sendBroadcast(mediaScanIntent);
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
