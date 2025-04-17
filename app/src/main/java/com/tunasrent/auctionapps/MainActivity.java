package com.tunasrent.auctionapps;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tunasrent.auctionapps.DB.BodyKendaraan;
import com.tunasrent.auctionapps.bp.BpInputFormActivity;
import com.tunasrent.auctionapps.bp.BpListActivity;
import com.tunasrent.auctionapps.dispatcher.DisApprovalListActivity;
import com.tunasrent.auctionapps.dispatcher.DisCekBastkListActivity;
import com.tunasrent.auctionapps.dispatcher.DisInputFormActivity;
import com.tunasrent.auctionapps.mobilisasi.MobCekBastkListActivity;
import com.tunasrent.auctionapps.mobilisasi.MobInputFormActivity;
import com.tunasrent.auctionapps.mobilisasi.MobListActivity;
import com.tunasrent.auctionapps.model.Bus;
import com.tunasrent.auctionapps.model.BusAdapter;
import com.tunasrent.auctionapps.model.Dispatcherlist;
import com.tunasrent.auctionapps.model.Menu;
import com.tunasrent.auctionapps.model.MenuAdapter;
import com.tunasrent.auctionapps.model.Merk;
import com.tunasrent.auctionapps.model.MerkAdapter;
import com.tunasrent.auctionapps.model.Pool;
import com.tunasrent.auctionapps.model.PoolAdapter;
import com.tunasrent.auctionapps.model.Truck;
import com.tunasrent.auctionapps.model.TruckAdapter;
import com.tunasrent.auctionapps.model.Vendor;
import com.tunasrent.auctionapps.model.VendorAdapter;
import com.tunasrent.auctionapps.taksasi.TaksasiActivity;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;
import com.tunasrent.auctionapps.warehouse.WarehouseActivity;
import com.tunasrent.auctionapps.warehouse.WarehouseCekPriceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.SSLSessionBindingListener;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    UserSessionManager session;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    Toolbar toolbar;

    TextView tvDate;
    TextView tvTime;
    CardView cvDispatcher;
    CardView cvMobilisasi;
    CardView cvBp;
    CardView cvTaksasi;
    CardView cvWarehouse;
    String versionName;

    ImageView ivLogo;
    Dialog myDialog;

    String _name;
    String _fullname;
    String _appid;
    String _ccode;
    String _token;
    String _group;
    String _pool;

    int code;
    String message;
    String var_page;

    private ArrayList<Menu> item_list = new ArrayList<>();
    private MenuAdapter mAdapter;
    private RecyclerView rvDetail;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("E-BASTK Online");
        toolbar.setTitleTextColor(Color.WHITE);

        ivLogo = findViewById(R.id.iv_logo);
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
//        cvDispatcher = findViewById(R.id.cv_dispatcher);
//        cvMobilisasi = findViewById(R.id.cv_mobilisasi);
//        cvBp = findViewById(R.id.cv_bp);
//        cvTaksasi = findViewById(R.id.cv_taksasi);
//        cvWarehouse = findViewById(R.id.cv_warehouse);

        rvDetail = (RecyclerView) findViewById(R.id.recycler_menu);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);
        _pool = user.get(UserSessionManager.SES_POOL);


        //paksa update
//        final String appPackageName = getPackageName();
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));

        myDialog = new Dialog(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //versi aplikasi HP
        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionName = pinfo.versionName;

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tv_account);
        TextView navVersion = (TextView) headerView.findViewById(R.id.tv_version);
        navUsername.setText(_fullname);
        navVersion.setText("Version " + versionName);

        //Glide.with(this).load("https://ss-tunasrental.tunasgroup.com/mas-api/images/icon/Logo-tunas-Auction_transparan.png").into(ivLogo);
        Glide.with(this).load("https://tunasauction.s3.ap-southeast-1.amazonaws.com/logo/3c6bb99737705ae69a2521af57698023.png");
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void run() {
                                long date =System.currentTimeMillis();
                                //jam
                                SimpleDateFormat adf = new SimpleDateFormat("HH:mm:ss");
                                String datestring = adf.format(date);
                                tvTime.setText(datestring);
                                //tanggal
                                SimpleDateFormat adf_date = new SimpleDateFormat("dd-MM-yyyy");
                                String date_string = adf_date.format(date);
                                tvDate.setText(date_string);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        getMenu();

        Bundle b = getIntent().getExtras();
        if (b != null){
            var_page= b.getString("page");
            Log.d("terima:",var_page);
            if (var_page.equals("mobilisasi")){ //notif mobilisasi
                Intent i = new Intent(MainActivity.this,MobListActivity.class);
                startActivity(i);
            }else if (var_page.equals("taksasi")){ //notif mobilisasi
                Intent i = new Intent(MainActivity.this,TaksasiActivity.class);
                startActivity(i);
            }
        }
//
//        cvBp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(MainActivity.this, BpListActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
//            }
//        });
//        cvTaksasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,TaksasiActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
//            }
//        });
//        cvWarehouse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,WarehouseActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });
    }


//    private void getMenu(){
//        class getMenu extends AsyncTask<Void,Void,String> {
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(MainActivity.this,"","Checking Username & Password...",false,false);
//            }
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                showData(s);
//            }
//            @SuppressLint("WrongThread")
//            @Override
//            protected String doInBackground(Void... params) {
//                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put(configuration.KEY_APPID,_appid);
//                hashMap.put(configuration.KEY_CCODE,_ccode);
//                hashMap.put(configuration.KEY_USERNAME,_name);
//                hashMap.put(configuration.KEY_GROUP,_group);
//                hashMap.put(configuration.KEY_TOKEN,_token);
//                RequestHandler rh = new RequestHandler();
//
//                String s = rh.sendPostRequest(configuration.URL_MENU,hashMap);
//                Log.wtf("kirim", s);
//                return s;
//            }
//        }
//        getMenu ge = new getMenu();
//        ge.execute();
//    }
//    private void showData(String json){
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            code = jsonObject.getInt(configuration.TAG_CODE);
//            message = jsonObject.getString(configuration.TAG_MESSAGE);
//
//            if(code == 200){
//
//
//            }else {
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//            }
//
//
//        } catch (JSONException e) {
//            //e.printStackTrace();
//            Toast.makeText(MainActivity.this,"Please Check Your Connection",Toast.LENGTH_SHORT).show();
//        }
//
//        Log.wtf("cekcodes","hasil" + code);
//
//    }

    private void getMenu() {
        class getMenu extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"","Please Wait..",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showMenu(s);
            }
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID, _appid);
                hashMap.put(configuration.KEY_CCODE, _ccode);
                hashMap.put(configuration.KEY_TOKEN, _token);
                hashMap.put(configuration.KEY_USERNAME, _name);
                hashMap.put(configuration.KEY_GROUP, _group);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_MENU,hashMap);
                return s;
            }
        }
        getMenu ge = new getMenu();
        ge.execute();
    }

    private void showMenu(String json){
        Log.d("menunya","- menu");
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            int code = jsonObject.getInt(configuration.TAG_CODE);
            Log.d("kode", "result: " + code);
            String message = jsonObject.getString(configuration.TAG_MESSAGE);
            Log.d("message", "result: " + message);

            if (code == 200) {
                JSONObject resultdata = jsonObject.getJSONObject(configuration.TAG_JSON_DATA);
                JSONArray resmenu = resultdata.getJSONArray(configuration.TAG_MENU);

                for (int i = 0; i < resmenu.length(); i++) {
                    JSONObject jo = resmenu.getJSONObject(i);

                    String menuId = jo.getString(configuration.KEY_ID);
                    String name = jo.getString(configuration.KEY_MENU_NAME);
                    String desc = jo.getString(configuration.KEY_MENU_DESC);
                    String img = jo.getString(configuration.KEY_MENU_IMG);

                    Menu menu = new Menu(menuId,name,desc,img);
                    item_list.add(menu);
                }

                rvDetail.setHasFixedSize(true);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                rvDetail.setLayoutManager(gridLayoutManager);
                mAdapter = new MenuAdapter(item_list);
                rvDetail.setAdapter(mAdapter);
                //-> pindah ke runAnimation
                // runAnimationprd(rvDetail,0);

            } else {
                Toast.makeText(MainActivity.this, "Data Not Found, " + message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Connection Problem", Toast.LENGTH_SHORT).show();
        }


    }

//    public void ShowPopupDispatcher(View v) {
//        final TextView tvClose;
//        final Button btnInput;
//        final Button btnApprove;
//        final Button btnCekbastk;
//
//        myDialog.setContentView(R.layout.popup_opsi_dispatcher);
//        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
//        btnInput = (Button) myDialog.findViewById(R.id.btn_input);
//        btnApprove = (Button) myDialog.findViewById(R.id.btn_approve);
//        btnCekbastk = (Button) myDialog.findViewById(R.id.btn_cekbastk);
//
//        tvClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//
//        btnInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//                Intent i = new Intent(MainActivity.this, DisInputFormActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });
//        btnApprove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//                Intent i = new Intent(MainActivity.this, DisApprovalListActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });
//        btnCekbastk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//                Intent i = new Intent(MainActivity.this, DisCekBastkListActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });
//
//        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }
//    public void ShowPopupMobilisasi(View v) {
//        final TextView tvClose;
//        final Button btnInput;
//        final Button btnApprove;
//        final Button btnCekbastk;
//
//        myDialog.setContentView(R.layout.popup_opsi_mobilisasi);
//        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
//        btnInput = (Button) myDialog.findViewById(R.id.btn_input);
//        btnCekbastk = (Button) myDialog.findViewById(R.id.btn_cekbastk);
//
//        tvClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//
//        btnInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//                Intent i = new Intent(MainActivity.this, MobListActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });
//        btnCekbastk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//                Intent i = new Intent(MainActivity.this, MobCekBastkListActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });
//
//        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.profile)
        {
            Intent i = new Intent(this, ProfileActivity.class);
            //Bundle b = new Bundle();
//            b.putString("parse_username",get_nama);
//            b.putString("parse_token",get_token);
//            i.putExtras(b);
            startActivity(i);
            //mDrawerLayout.closeDrawers();
            //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if(id == R.id.change_password)
        {
            Intent i = new Intent(this,ChangePasswordActivity.class);
            Bundle b = new Bundle();
//            b.putString("parse_username",get_nama);
//            b.putString("parse_token",get_token);
//            i.putExtras(b);
            startActivity(i);
            mDrawerLayout.closeDrawers();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        if(id == R.id.signout)
        {
            //Keluar dari program
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    logout();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        return false;
    }
    private void logout() {
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();

        session.logoutUser();
        Toast.makeText(this,"Thank You...",Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }

}
