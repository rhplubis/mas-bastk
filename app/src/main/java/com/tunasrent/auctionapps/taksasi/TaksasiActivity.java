package com.tunasrent.auctionapps.taksasi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tunasrent.auctionapps.LoginActivity;
import com.tunasrent.auctionapps.R;

import com.tunasrent.auctionapps.model.TaksasiList;
import com.tunasrent.auctionapps.model.TaksasiListAdapter;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TaksasiActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static FrameLayout noItemDefault;
    private static LinearLayout defaultdata;
    SwipeRefreshLayout swipeRefreshLayout;
    UserSessionManager session;
    Toolbar searchtollbar;
    Toolbar toolbar;
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
    String by;

    String type_aset;

    private RecyclerView rvDetail;
    private ArrayList<TaksasiList> item_list = new ArrayList<>();
    private TaksasiListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taksasi);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Taksasi");
        toolbar.setTitleTextColor(Color.WHITE);

        setSearchtollbar();

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        Bundle b = getIntent().getExtras();
        if (b != null){
            type_aset = b.getString("parse_jenis");
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
                hashMap.put(configuration.KEY_TYPE,type_aset);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(configuration.URL_SHOW_BASTK,hashMap);
                Log.d("send","hasil: " + s);
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
                    Log.wtf("vhcdate",vhcdate);
                    Log.wtf("vhcid",vhcid);
                    Log.wtf("vhctype",vhctype);
                    Log.wtf("vendor",vendor);
                    Log.wtf("status",status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                TaksasiList taksasiList = new TaksasiList(vhcdate,vhcid,vhctype,vendor,status,by);
                item_list.add(taksasiList);

                rvDetail.setVisibility(VISIBLE);
                noItemDefault.setVisibility(GONE);

                rvDetail.setHasFixedSize(true);
                rvDetail.setLayoutManager(new LinearLayoutManager(this));
                mAdapter = new TaksasiListAdapter(item_list);
                rvDetail.setAdapter(mAdapter);
                rvDetail.getAdapter().notifyDataSetChanged();
            }


        }else if (code.equals("304")){
            Toast.makeText(TaksasiActivity.this,message,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(TaksasiActivity.this, LoginActivity.class);
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
    public void setSearchtollbar()
    {
        searchtollbar = (Toolbar) findViewById(R.id.searchtoolbar);
        if (searchtollbar != null) {
            searchtollbar.inflateMenu(R.menu.menu_search);
            search_menu=searchtollbar.getMenu();

            searchtollbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(R.id.searchtoolbar,1,true,false);
                    else
                        searchtollbar.setVisibility(View.GONE);
                }
            });

            item_search = search_menu.findItem(R.id.action_filter_search);

            MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    // Do something when collapsed
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(R.id.searchtoolbar,1,true,false);
                    }
                    else
                        searchtollbar.setVisibility(View.GONE);
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    // Do something when expanded
                    return true;
                }
            });

            initSearchView();
        } else
            Log.d("toolbar", "setSearchtollbar: NULL");
    }
    
    
    public void initSearchView()
    {
        final SearchView searchView =
                (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();

        // Enable/Disable Submit button in the keyboard
        searchView.setSubmitButtonEnabled(false);

        // Change search close button image
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close);


        // set hint and the text colors
        EditText txtSearch = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint("Search..");
        txtSearch.setHintTextColor(Color.DKGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.colorPrimary));

        // set the cursor
        AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                callSearch(newText);
//                return true;
                //search
                newText = newText.toLowerCase();
                ArrayList<TaksasiList> newList  = new ArrayList<>();
                for (TaksasiList taksasiList : item_list){
                    String name = taksasiList.getNopol().toLowerCase();
                    String type = taksasiList.getVendor().toLowerCase();
                    if (name.contains(newText)) {
                        newList.add(taksasiList);
                    }else if (type.contains(newText)){
                        newList.add(taksasiList);
                    }
                }
                if (mAdapter != null) {
                    mAdapter.setFilter(newList);
                }
                return true;
            }

            public void callSearch(String query) {
                //Do searching
                Log.i("query", "" + query);
            }
        });
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void circleReveal(int viewID, int posFromRight, boolean containsOverflow, final boolean isShow)
    {
        final View myView = findViewById(viewID);

        int width=myView.getWidth();

        if(posFromRight>0)
            width-=(posFromRight*getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material))-(getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)/ 2);
        if(containsOverflow)
            width-=getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx=width;
        int cy=myView.getHeight()/2;

        Animator anim;
        if(isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0,(float)width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float)width, 0);

        anim.setDuration((long)220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(!isShow)
                {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });
        // make the view visible and start the animation
        if(isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
