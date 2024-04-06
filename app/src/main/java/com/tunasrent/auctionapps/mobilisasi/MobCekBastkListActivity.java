package com.tunasrent.auctionapps.mobilisasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tunasrent.auctionapps.LoginActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.dispatcher.DisCekBastkListActivity;
import com.tunasrent.auctionapps.model.DispatcherlistBastk;
import com.tunasrent.auctionapps.model.DispatcherlistBastkAdapter;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MobCekBastkListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Toolbar toolbar;
    Toolbar searchtollbar;
    private static FrameLayout noItemDefault;
    private static LinearLayout defaultdata;
    SwipeRefreshLayout swipeRefreshLayout;
    UserSessionManager session;
    Menu search_menu;
    MenuItem item_search;

    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;
    String code,message;

    String _name;
    String _fullname;
    String _appid;
    String _ccode;
    String _token;
    String _group;
    JSONArray result;

    String vhcid;
    String vhctype;
    String vhcdate;
    String vendor;
    String status;
    String flag;

    private RecyclerView rvDetail;
    private ArrayList<DispatcherlistBastk> item_list = new ArrayList<>();
    private DispatcherlistBastkAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob_cek_bastk_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recheck BASTK");
        toolbar.setTitleTextColor(Color.WHITE);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);


        rvDetail = (RecyclerView) findViewById(R.id.recycler_view);
        noItemDefault = (FrameLayout) findViewById(R.id.default_nodata);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.swipe_1,R.color.swipe_2,R.color.swipe_3);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getBastk();
                    }
                },1000);
            }
        });

        getBastk();

    }

    public void getBastk(){
        class GetOrder extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                swipeRefreshLayout.setRefreshing(true);
                //loading = ProgressDialog.show(ListSoActivity.this,"","Loading data...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                swipeRefreshLayout.setRefreshing(false);
//                loading.dismiss();
                showDetail(s);
            }
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,appid);
                hashMap.put(configuration.KEY_CCODE,ccode);
                hashMap.put(configuration.TAG_USERNAME,_name);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_PARENT,"2");
                hashMap.put(configuration.KEY_FLAG,"2");
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_SHOW_CEKBASTK,hashMap);
                return s;
            }
        }
        GetOrder ge = new GetOrder();
        ge.execute();
    }
    private void showDetail(String json){
        item_list.clear();
        final ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getString(configuration.TAG_CODE);
            Log.d("kode","result: "+code);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
            Log.d("message","result: "+message);
            result = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);


        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText(this, "Not Connected, please try again.", Toast.LENGTH_SHORT).show();
        }

        //try {
        if (code.equals("200")) {
            for (int i=0;i < result.length(); i++) {
                JSONObject jo = null;
                try {
                    jo = result.getJSONObject(i);
                    vhcdate = jo.getString(configuration.KEY_VHC_DATE);
                    vhcid = jo.getString(configuration.KEY_VEHICLEID);
                    vhctype = jo.getString(configuration.KEY_VHC_TYPE);
                    vendor = jo.getString(configuration.KEY_VENDOR_NAME);
                    status = jo.getString(configuration.KEY_VHC_STATUS);
                    flag = jo.getString(configuration.KEY_FLAG);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                DispatcherlistBastk dispatcherlistBastk = new DispatcherlistBastk(vhcdate,vhcid,vhctype,vendor,status,flag);
                item_list.add(dispatcherlistBastk);

                rvDetail.setVisibility(VISIBLE);
                noItemDefault.setVisibility(GONE);

                rvDetail.setHasFixedSize(true);
                rvDetail.setLayoutManager(new LinearLayoutManager(this));
                mAdapter = new DispatcherlistBastkAdapter(item_list);
                rvDetail.setAdapter(mAdapter);
                rvDetail.getAdapter().notifyDataSetChanged();
            }

        }else if (code.equals("304")){
            Toast.makeText(MobCekBastkListActivity.this,message,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MobCekBastkListActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            session.logoutUser();
            finish();

        }else {
            rvDetail.setVisibility(GONE);
            noItemDefault.setVisibility(VISIBLE);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
