package com.tunasrent.auctionapps.dispatcher;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.tunasrent.auctionapps.BuildConfig;
import com.tunasrent.auctionapps.DB.InputData;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.bp.BpCekSignActivity;
import com.tunasrent.auctionapps.taksasi.TaksasiCekSignActivity;
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
import java.util.Calendar;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

public class DisApprovalSignActivity extends AppCompatActivity implements AsyncTaskCompleteListener {
    UserSessionManager session;
    Toolbar toolbar;
    Dialog myDialog;
    private SignaturePad mSignaturePad;
    private static final String IMAGE_DIRECTORY = "/mas_temp";
    public static final int REQUEST_IMAGE = 100;
    JSONObject jsonObject;
    JSONArray data;
    JSONObject jo;

    Button btnNext;

    ImageView ivPicpengiriman;
    ImageView ivPicpenyimpanan;
    ImageView ivApprover;

    String position;
    String var_picpengiriman;
    String var_picpenyimpanan;
    String var_approver;
    String var_nopol;
    String _name, _fullname, _appid, _ccode, _token, _group;
    String message;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_approval_sign);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PIC Serah Terima");
        toolbar.setTitleTextColor(Color.WHITE);

        ivPicpengiriman = findViewById(R.id.iv_picpengiriman);
        ivPicpenyimpanan = findViewById(R.id.iv_picpenyimpanan);
        ivApprover = findViewById(R.id.iv_approver);

        btnNext = findViewById(R.id.btn_next);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        myDialog = new Dialog(this);

        Bundle b = getIntent().getExtras();
        if (b != null){
            var_picpengiriman = b.getString("picpengiriman");
            var_picpenyimpanan = b.getString("picpenyimpanan");
            var_nopol = b.getString("nopol");

            Log.d("terima_nopol",var_nopol);

            try {
                if (!var_picpengiriman.equals("null")){
                    ivPicpengiriman.setBackground(null);
                    Glide.with(this).load(var_picpengiriman).into(ivPicpengiriman);
                }
                if (!var_picpenyimpanan.equals("null")){
                    ivPicpenyimpanan.setBackground(null);
                    Glide.with(this).load(var_picpenyimpanan).into(ivPicpenyimpanan);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DisApprovalSignActivity.this, TaksasiSetpriceActivity.class);
//                Bundle b = new Bundle();
//                b.putString("nopol",var_nopol);
//                intent.putExtras(b);
//                startActivity(intent);
                sendApprove();

            }
        });
    }

    private void sendApprove(){
        class SendData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisApprovalSignActivity.this,"","Processing...",false,false);
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
                hashMap.put(configuration.KEY_PARENT_STATUS,"4");
                hashMap.put(configuration.KEY_NOPOL,var_nopol);
//                hashMap.put(configuration.KEY_SEBAGAI,status);
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
                Toast.makeText(DisApprovalSignActivity.this,"Data Berhasil Di Approve",Toast.LENGTH_SHORT).show();

                Intent i=new Intent(DisApprovalSignActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(DisApprovalSignActivity.this,"Data Gagal Di Approve",Toast.LENGTH_SHORT).show();
            }
        }else if (code.equals("304")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void ShowPopupApprover(View v) {
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
                    uploadImageToServer28(paths);
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

    private void uploadImageToServer28(final String path) throws IOException, JSONException {
//        Realm.init(this);
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
//        results.load();
//        String nop1 = results.first().getNopol1();
//        String nop2 = results.first().getNopol2();
//        String nop3 = results.first().getNopol3();
//        String nop = nop1 + "-" + nop2 + "-" + nop3;
//
//        Log.d("nopol ", nop);
        try {
            if (!YuliYanto.isNetworkAvailable(DisApprovalSignActivity.this)) {
                Toast.makeText(DisApprovalSignActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
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
            data.put(configuration.KEY_POSITION, "28");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(DisApprovalSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }

    private void directSetting() {
        Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        startActivity(i);
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

                var_approver = jo.getString("konsumen");
                ivApprover.setBackground(null);
                Glide.with(this).load(var_approver).into(ivApprover);

                myDialog.dismiss();

            }else if (code.equals("304")){
                Toast.makeText(DisApprovalSignActivity.this,message,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(DisApprovalSignActivity.this,message,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(DisApprovalSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }

    @Override
    public void onTaskCompleted1(String response, int serviceCode) {

    }
}
