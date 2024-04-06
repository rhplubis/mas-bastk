package com.tunasrent.auctionapps.bp;

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
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import com.tunasrent.auctionapps.dispatcher.DisInputSignActivity;
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
import java.util.Calendar;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

public class BpCekSignActivity extends AppCompatActivity implements AsyncTaskCompleteListener {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private static final String IMAGE_DIRECTORY = "/mas_temp";
    Dialog myDialog;
    JSONObject jsonObject;
    JSONArray data;
    JSONObject jo;
    String position;
    UserSessionManager session;

    public static final int REQUEST_IMAGE = 100;
    Toolbar toolbar;
    String message;
    String code;
    String _name, _fullname, _appid, _ccode, _token, _group;
    String picPengiriman;
    String konsumen;
    String nopol;
    String status;
    String picpenyimpanan;

    ImageView ivPicpenyimpanan;
    ImageView ivPicpengiriman;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_cek_sign);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tanda Tangan Serah Terima");
        toolbar.setTitleTextColor(Color.WHITE);

        ivPicpenyimpanan = findViewById(R.id.iv_picpenyimpanan);
        ivPicpengiriman = findViewById(R.id.iv_picpengiriman);
        btnSubmit = findViewById(R.id.btn_submit);

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
            status = b.getString("status");
            picpenyimpanan = b.getString("picpenyimpanan");

            Glide.with(this).load(picpenyimpanan).into(ivPicpenyimpanan);
        }

        myDialog = new Dialog(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivPicpengiriman.getDrawable() == null){
                    Toast.makeText(BpCekSignActivity.this,"Tanda tangan wajib diisi.",Toast.LENGTH_LONG).show();
                }else {
                    sendData();
                }

            }
        });
        getSign();

    }
    private void getSign(){
        class GetFoto extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BpCekSignActivity.this,"","Loading sign...",false,false);
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

            konsumen = jos.getString("konsumen");
            picPengiriman = jos.getString("picPengiriman");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(code.equals("200")){
            try {
                if (!picPengiriman.equals("null")){
                    Glide.with(this).load(picPengiriman).into(ivPicpengiriman);
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
                loading = ProgressDialog.show(BpCekSignActivity.this,"","Processing...",false,false);
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
                hashMap.put(configuration.KEY_PARENT_STATUS,"3");
                hashMap.put(configuration.KEY_NOPOL,nopol);
                hashMap.put(configuration.KEY_SEBAGAI,status);
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
                Toast.makeText(BpCekSignActivity.this,"Data Berhasil Di Verifikasi",Toast.LENGTH_SHORT).show();

                Intent i=new Intent(BpCekSignActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(BpCekSignActivity.this,"Data Gagal Di Verifikasi",Toast.LENGTH_SHORT).show();
            }
        }else if (code.equals("304")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
    public void ShowPopupPengiriman(View v) {
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

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    private void uploadImageToServer(final String path) throws IOException, JSONException {
        try {
            if (!YuliYanto.isNetworkAvailable(BpCekSignActivity.this)) {
                Toast.makeText(BpCekSignActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
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
            data.put(configuration.KEY_POSITION, "26");

            new MultiPartRequester(this, data, REQUEST_IMAGE, this);
            YuliYanto.showSimpleProgressDialog(this);
        }catch (Exception e){
            Toast.makeText(BpCekSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
        }
    }

    private void directSetting() {
        Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        startActivity(i);
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

                picPengiriman = jo.getString("picPengiriman");
                Glide.with(this).load(picPengiriman).into(ivPicpengiriman);
                myDialog.dismiss();

            }else if (code.equals("304")){
                Toast.makeText(BpCekSignActivity.this,message,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(BpCekSignActivity.this,message,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(BpCekSignActivity.this,"Please check permission storage",Toast.LENGTH_SHORT).show();
            directSetting();
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(BpCekSignActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
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
        BpCekSignActivity.this.sendBroadcast(mediaScanIntent);
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

    @Override
    public void onTaskCompleted1(String response, int serviceCode) {

    }
}
