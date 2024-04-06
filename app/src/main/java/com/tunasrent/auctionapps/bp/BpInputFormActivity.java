package com.tunasrent.auctionapps.bp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.dispatcher.DisInputCeklistActivity;
import com.tunasrent.auctionapps.dispatcher.DisInputFormActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

public class BpInputFormActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText etTanggal;
    EditText etPukul;
    EditText etLokasi_penarikan;
    EditText etMerktype;
    EditText etTahun;
    EditText etStnk_an;
    EditText etNopol;
    EditText etKmditarik;
    EditText etMeterbensin;
    EditText etCabang;
    EditText etNomesin;
    EditText etNorangka;
    EditText etWarna;
    EditText etKmditerima;
    EditText etLokasipool;
    Button btnNext;

    Realm realm;
    Calendar myCalendar;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_input_form);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Input Data Mobil");
        toolbar.setTitleTextColor(Color.WHITE);

        etTanggal = findViewById(R.id.et_tanggal);
        etPukul = findViewById(R.id.et_pukul);
        etLokasi_penarikan = findViewById(R.id.et_lokasi_penarikan);
        etMerktype = findViewById(R.id.et_merktype);
        etTahun = findViewById(R.id.et_tahun);
        etStnk_an = findViewById(R.id.et_stnk_an);
        etNopol = findViewById(R.id.et_nopol);
        etKmditarik = findViewById(R.id.et_kmditarik);
        etMeterbensin = findViewById(R.id.et_meterbensin);
        etCabang = findViewById(R.id.et_cabang);
        etNomesin = findViewById(R.id.et_nomesin);
        etNorangka = findViewById(R.id.et_norangka);
        etWarna = findViewById(R.id.et_warna);
        etKmditerima = findViewById(R.id.et_kmditerima);
        etLokasipool = findViewById(R.id.et_lokasipool);
        btnNext = findViewById(R.id.btn_next);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //tanggal
        long date =System.currentTimeMillis();
        SimpleDateFormat adf_date = new SimpleDateFormat("dd-MM-yyyy");
        String date_string = adf_date.format(date);
        etTanggal.setText(date_string);
        //jam
        SimpleDateFormat adf = new SimpleDateFormat("HH:mm");
        String datestring = adf.format(date);
        etPukul.setText(datestring);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTanggal.getText().toString().equals("")){
                    etTanggal.setError("Harap masukkan tanggal");
                } else if (etPukul.getText().toString().equals("")){
                    etPukul.setError("Harap masukkan jam");
                }else if (etLokasi_penarikan.getText().toString().equals("")){
                    etLokasi_penarikan.setError("Harap masukkan lokasi penarikan");
                }else if (etMerktype.getText().toString().equals("")){
                    etMerktype.setError("Harap masukkan merk/type");
                }else if (etTahun.getText().toString().equals("")){
                    etTahun.setError("Harap masukkan tahun");
                }else if (etStnk_an.getText().toString().equals("")){
                    etStnk_an.setError("Harap masukkan nama STNK");
                }else if (etNopol.getText().toString().equals("")){
                    etNopol.setError("Harap masukkan Nopol");
                }else if (etKmditarik.getText().toString().equals("")){
                    etKmditarik.setError("Harap masukkan KM ditarik");
                }else if (etMeterbensin.getText().toString().equals("")){
                    etMeterbensin.setError("Harap masukkan meter bensin");
                }else if (etCabang.getText().toString().equals("")){
                    etCabang.setError("Harap masukkan cabang");
                }else if (etNomesin.getText().toString().equals("")){
                    etNomesin.setError("Harap masukkan nomor mesin");
                }else if (etNorangka.getText().toString().equals("")){
                    etNorangka.setError("Harap masukkan nomor rangka");
                }else if (etWarna.getText().toString().equals("")){
                    etWarna.setError("Harap masukkan warna");
                }else if (etKmditerima.getText().toString().equals("")){
                    etKmditerima.setError("Harap masukkan KM diterima");
                }else if (etLokasipool.getText().toString().equals("")){
                    etLokasipool.setError("Harap masukkan lokasi pool");
                }else {
                    Intent i = new Intent(BpInputFormActivity.this, BpInputCeklistActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });


    }
}
