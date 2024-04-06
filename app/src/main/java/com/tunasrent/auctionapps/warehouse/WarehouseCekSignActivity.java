package com.tunasrent.auctionapps.warehouse;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.taksasi.TaksasiCekSignActivity;
import com.tunasrent.auctionapps.taksasi.TaksasiSetpriceActivity;
import com.tunasrent.auctionapps.util.UserSessionManager;

import java.util.HashMap;

public class WarehouseCekSignActivity extends AppCompatActivity {
    UserSessionManager session;
    Toolbar toolbar;
    Button btnNext;

    ImageView ivPicpengiriman;
    ImageView ivPicpenyimpanan;
    String vhc_cat_name;
    String var_picpengiriman;
    String var_picpenyimpanan;
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
    String var_nopol;
    String var_pool;
    String _name, _fullname, _appid, _ccode, _token, _group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_cek_sign);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign");
        toolbar.setTitleTextColor(Color.WHITE);

        ivPicpengiriman = findViewById(R.id.iv_picpengiriman);
        ivPicpenyimpanan = findViewById(R.id.iv_picpenyimpanan);
        btnNext = findViewById(R.id.btn_setprice);

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
            vhc_cat_name = b.getString("vhc_cat_name");
            var_picpengiriman = b.getString("picpengiriman");
            var_picpenyimpanan = b.getString("picpenyimpanan");
            var_picpenjualan = b.getString("picpenjualan");
            var_market_price= b.getString("market_price");
            var_recomended_price= b.getString("recomended_price");
            var_repair_machine= b.getString("repair_machine");
            var_repair_interior= b.getString("repair_interior");
            var_repair_exterior= b.getString("repair_exterior");
            var_cost_document= b.getString("cost_document");
            var_margin = b.getString("margin");
            var_pembulatan = b.getString("pembulatan");
            var_unit_condition= b.getString("unit_condition");
            var_nopol = b.getString("nopol");
            var_pool = b.getString("pool_id");

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
                Intent intent = new Intent(WarehouseCekSignActivity.this, WarehouseCekPriceActivity.class);
                Bundle b = new Bundle();
                b.putString("nopol",var_nopol);
                b.putString("vhc_cat_name",vhc_cat_name);
                b.putString("picpenjualan",var_picpenjualan);
                b.putString("market_price",var_market_price);
                Log.d("TAG-marketsign", "onCreate: " + var_market_price);
                b.putString("recomended_price",var_recomended_price);
                b.putString("repair_machine",var_repair_machine);
                b.putString("repair_interior",var_repair_interior);
                b.putString("repair_exterior",var_repair_exterior);
                b.putString("cost_document",var_cost_document);
                b.putString("margin",var_margin);
                b.putString("pembulatan",var_pembulatan);
                b.putString("unit_condition",var_unit_condition);
                b.putString("pool",var_unit_condition);
                b.putString("pool_id",var_pool);
                intent.putExtras(b);
                startActivity(intent);

            }
        });

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
