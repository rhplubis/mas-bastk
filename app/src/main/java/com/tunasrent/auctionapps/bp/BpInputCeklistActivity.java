package com.tunasrent.auctionapps.bp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.tunasrent.auctionapps.R;

import java.util.Calendar;

import io.realm.Realm;

public class BpInputCeklistActivity extends AppCompatActivity {
    Realm realm;
    Calendar myCalendar;
    Toolbar toolbar;

    Button btnNextdispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_input_ceklist);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ceklist Unit");
        toolbar.setTitleTextColor(Color.WHITE);

        btnNextdispatcher = findViewById(R.id.btn_nextdispatcher);

        myCalendar = Calendar.getInstance();

        btnNextdispatcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                realm.beginTransaction();
//                RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                upd.load();
//                upd.first().setDb_var_etStnk_lain(etSd_stnk.getText().toString());
//                upd.first().setDb_var_etBukukir_lain(etSd_bukukir.getText().toString());
//                upd.first().setDb_var_etIjintrayek_lain(etSd_ijintrayek.getText().toString());
//                upd.first().setDb_var_etIjinusaha_lain(etSd_ijinusaha.getText().toString());
//                realm.commitTransaction();

                Intent i = new Intent(BpInputCeklistActivity.this, BpInputFotoActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }
}
