package com.tunasrent.auctionapps.taksasi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.util.UserSessionManager;

import java.util.HashMap;

public class TaksasiCekSignActivity extends AppCompatActivity {
    UserSessionManager session;
    Toolbar toolbar;
    Button btnNext;

    ImageView ivPicpengiriman;
    ImageView ivPicpenyimpanan;
    String var_picpengiriman;
    String var_picpenyimpanan;
    String var_nopol;
    String _name, _fullname, _appid, _ccode, _token, _group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taksasi_cek_sign);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Set Price");
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
            var_picpengiriman = b.getString("picpengiriman");
            var_picpenyimpanan = b.getString("picpenyimpanan");
            var_nopol = b.getString("nopol");

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
                Intent intent = new Intent(TaksasiCekSignActivity.this,TaksasiSetpriceActivity.class);
                Bundle b = new Bundle();
                b.putString("nopol",var_nopol);
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
