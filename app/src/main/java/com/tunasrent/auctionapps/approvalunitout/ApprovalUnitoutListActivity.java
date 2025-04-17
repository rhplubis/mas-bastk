package com.tunasrent.auctionapps.approvalunitout;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.tunasrent.auctionapps.model.ApprovalUnitoutlist;
import com.tunasrent.auctionapps.model.ApprovalUnitoutlistAdapter;
import com.tunasrent.auctionapps.model.Unitoutlist;
import com.tunasrent.auctionapps.model.UnitoutlistAdapter;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ApprovalUnitoutListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static FrameLayout noItemDefault;
    private static LinearLayout defaultdata;
    SwipeRefreshLayout swipeRefreshLayout;
    UserSessionManager session;
    Toolbar toolbar,searchtollbar;
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
    String _pool;
    JSONArray result;

    String vhcid;
    String vhctype , vhcyear , vhccolor , vhcseries ;
    String vhcdate;
    String vendor;
    String status;
    String procby;
    String type_aset, jenisunit;

    private RecyclerView rvDetail;
    private ArrayList<ApprovalUnitoutlist> item_list = new ArrayList<>();
    private ApprovalUnitoutlistAdapter  mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_unitout_list);
        setContentView(R.layout.activity_approvalunitout_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List Unit");
        toolbar.setTitleTextColor(Color.WHITE);


        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);
        _pool = user.get(UserSessionManager.SES_POOL);


        Bundle b = getIntent().getExtras();
        if (b != null){
            type_aset = b.getString("parse_jenis");
            jenisunit = b.getString("parse_jenisunit");
        }


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
                        getOrder();
                    }
                },1000);
            }
        });
        getOrder();
    }

    public void getOrder(){
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
                Log.d("list clicked","hasil: " + s);
                showDetail(s);
            }
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,appid);
                hashMap.put(configuration.KEY_CCODE,ccode);
                hashMap.put(configuration.TAG_USERNAME,_name);
                hashMap.put(configuration.KEY_TOKEN,_token);
//                hashMap.put(configuration.KEY_PARENT,"0");
                hashMap.put(configuration.KEY_status_approval,"0");
                hashMap.put(configuration.KEY_TYPE,type_aset);
                hashMap.put(configuration.KEY_POOL_ID,_pool);
                hashMap.put(configuration.KEY_JENIS,jenisunit);
                RequestHandler rh = new RequestHandler();
                Log.d("paramnya","hasil: " + hashMap);
                String s = rh.sendPostRequest(configuration.URL_LIST_APPROVAL_UNIT_OUT,hashMap);
                Log.d("data list approval","hasil: " + s);
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

        try {
            if (code.equals("200")) {
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = null;
                    try {
                        jo = result.getJSONObject(i);
                        vhcid = jo.getString(configuration.KEY_VEHICLEID);
                        vhctype = jo.getString(configuration.KEY_VHC_TYPE);
                        vhccolor = jo.getString(configuration.KEY_VHC_COLOR);
                        vhcyear = jo.getString(configuration.KEY_VHC_YEAR);
                        vendor = jo.getString(configuration.KEY_VENDOR_NAME);
                        status = jo.getString(configuration.KEY_VHC_STATUS);
                        procby = jo.getString(configuration.KEY_BY);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ApprovalUnitoutlist approvalUnitoutlist = new ApprovalUnitoutlist(vhcid, vhctype, vhccolor, vhcyear, vendor, status,procby,type_aset, jenisunit);

                    //Unitoutlist unitoutlist = new Unitoutlist(vhcid, vhctype, vhccolor, vhcyear, vendor, status,procby,type_aset, jenisunit);

                    item_list.add(approvalUnitoutlist);

                    rvDetail.setVisibility(VISIBLE);
                    noItemDefault.setVisibility(GONE);

                    rvDetail.setHasFixedSize(true);
                    rvDetail.setLayoutManager(new LinearLayoutManager(this));
                    //mAdapter = new ApprovalUnitoutlistAdapter(item_list);
                    mAdapter = new ApprovalUnitoutlistAdapter(item_list);
                    rvDetail.setAdapter(mAdapter);
                    rvDetail.getAdapter().notifyDataSetChanged();
                }

            } else if (code.equals("304")) {
                Toast.makeText(ApprovalUnitoutListActivity.this, message, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ApprovalUnitoutListActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                session.logoutUser();
                finish();

            } else {
                rvDetail.setVisibility(GONE);
                noItemDefault.setVisibility(VISIBLE);

            }
        }catch (Exception e){
            Toast.makeText(this, "Not Connected, check your connection.", Toast.LENGTH_SHORT).show();
        }


    }

//    public void setSearchtollbar()
//    {
//        searchtollbar = (Toolbar) findViewById(R.id.searchtoolbar);
//        if (searchtollbar != null) {
//            searchtollbar.inflateMenu(R.menu.menu_search);
//            search_menu=searchtollbar.getMenu();
//
//            searchtollbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//                        circleReveal(R.id.searchtoolbar,1,true,false);
//                    else
//                        searchtollbar.setVisibility(View.GONE);
//                }
//            });
//
//            item_search = search_menu.findItem(R.id.action_filter_search);
//
//            MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
//                @Override
//                public boolean onMenuItemActionCollapse(MenuItem item) {
//                    // Do something when collapsed
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        circleReveal(R.id.searchtoolbar,1,true,false);
//                    }
//                    else
//                        searchtollbar.setVisibility(View.GONE);
//                    return true;
//                }
//
//                @Override
//                public boolean onMenuItemActionExpand(MenuItem item) {
//                    // Do something when expanded
//                    return true;
//                }
//            });
//
//            initSearchView();
//        } else
//            Log.d("toolbar", "setSearchtollbar: NULL");
//    }


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
        Log.d("onItemclick ini", "diklik ini card nya");
    }
}
