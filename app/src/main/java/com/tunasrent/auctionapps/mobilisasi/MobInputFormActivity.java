package com.tunasrent.auctionapps.mobilisasi;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tunasrent.auctionapps.DB.InputData;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.bp.BpCekFormActivity;
import com.tunasrent.auctionapps.dispatcher.DisInputSignActivity;
import com.tunasrent.auctionapps.model.Bus;
import com.tunasrent.auctionapps.model.BusAdapter;
import com.tunasrent.auctionapps.model.Merk;
import com.tunasrent.auctionapps.model.MerkAdapter;
import com.tunasrent.auctionapps.model.Motor;
import com.tunasrent.auctionapps.model.MotorAdapter;
import com.tunasrent.auctionapps.model.Pool;
import com.tunasrent.auctionapps.model.PoolAdapter;
import com.tunasrent.auctionapps.model.Truck;
import com.tunasrent.auctionapps.model.TruckAdapter;
import com.tunasrent.auctionapps.model.Vendor;
import com.tunasrent.auctionapps.model.VendorAdapter;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.ViewDialog;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class MobInputFormActivity extends AppCompatActivity {
    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;
    UserSessionManager session;
    Toolbar toolbar;
    ViewDialog viewDialog;
    ViewDialog viewDialog2;
    String nopol;
    String status;
    EditText etTanggal;
    EditText etPukul;
    EditText etBiaya;
    //    EditText etLokasi_penarikan;
    EditText etTahun;
    EditText etStnk_an;

    EditText etKunci;
    EditText etNopol;
    EditText etNopol1;
    EditText etNopol2;
    EditText etNopol3;
    EditText etKmditarik;
//    EditText etMeterbensin;
    EditText etCabang;
    EditText etNomesin;
    EditText etNorangka;
    EditText etWarna;
    EditText etKmditerima;

    Button btnNext;
    AutoCompleteTextView etVendor;
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView etLokasipool;

    Realm realm;
    Calendar myCalendar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int code;
    String message;
    String _name;
    String _fullname;
    String _appid;
    String _ccode;
    String _token;
    String _group;
    String vendorid;
    String vendor;
    String vhc_type_id;
    String vhc_type_name;
    String vhc_item_id;
    String pool_id = "";
    String pool_address;

    String type_mobilisasi;
    String cost_mobilisasi;
    String vehicle_id;
    String vehicle_year;
    String vehicle_color;
    String vehicle_engine_no;
    String vehicle_chasis_no;
    String vhc_loc_penarikan;
    String vhc_loc_pool;
    String vhc_date_in;
    String vhc_stnk;
    String vhc_kmditarik;
    String vhc_kmditerima;
    String vhc_meterbensin;
    String vhc_cabang;
    String vendor_name;
    String pool;
    String pool_name;
    String vhc_cat_name;

    int flagType = 0;
    private List<Merk> merkList = new ArrayList<>();
    private List<Truck> truckList = new ArrayList<>();
    private List<Bus> busList = new ArrayList<>();
    private List<Motor> motorList = new ArrayList<>();
    private List<Pool> poolList = new ArrayList<>();
    private List<Vendor> vendorList = new ArrayList<>();
    MerkAdapter merkAdapter;
    TruckAdapter truckAdapter;
    MotorAdapter motorAdapter;
    BusAdapter busAdapter;
    PoolAdapter poolAdapter;
    VendorAdapter vendorAdapter;
    String jenis;
    Spinner spType;
    Spinner spTypeMobilisasi;
    private String[] type = {"SUV/MVP", "TRUCK", "BUS","RODA 2"};
//    private String[] type = {"SUV/MVP", "TRUCK", "BUS",};
    private String[] typeMobilisasi = {"SELF-SERVICE", "TOWING"};
    private String[] mobilisasi = {"NON-DEREK", "DEREK"};
//    private String[] type_mtr = {"RODA 2"};
    String var_nopol;
    String var_type;
    String var_vendor;
    String var_pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob_input_form);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Input Data Mobil");
        getSupportActionBar().setTitle("Input Data");
        toolbar.setTitleTextColor(Color.WHITE);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);


        etVendor = findViewById(R.id.et_vendor);
        etTanggal = findViewById(R.id.et_tanggal);
        etPukul = findViewById(R.id.et_pukul);
        etBiaya = findViewById(R.id.et_biaya);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.et_merktype);
        etTahun = findViewById(R.id.et_tahun);
        etStnk_an = findViewById(R.id.et_stnk_an);
        etNopol1 = findViewById(R.id.et_nopol1);
        etNopol2 = findViewById(R.id.et_nopol2);
        etNopol3 = findViewById(R.id.et_nopol3);
        etKmditarik = findViewById(R.id.et_kmditarik);
//        etMeterbensin = findViewById(R.id.et_meterbensin);
        etCabang = findViewById(R.id.et_cabang);
        etNomesin = findViewById(R.id.et_nomesin);
        etNorangka = findViewById(R.id.et_norangka);
        etWarna = findViewById(R.id.et_warna);
        etKmditerima = findViewById(R.id.et_kmditerima);
        etLokasipool = findViewById(R.id.et_lokasipool);
        spType = findViewById(R.id.sp_typeunit);
        spTypeMobilisasi = findViewById(R.id.sp_type_mobilisasi);
        btnNext = findViewById(R.id.btn_next);

        Bundle b = getIntent().getExtras();
        if (b != null){
            var_nopol = b.getString("parse_nopol");
            status = b.getString("parse_status");
            jenis = b.getString("parse_bar");
        }

        viewDialog = new ViewDialog(this);
        viewDialog2 = new ViewDialog(this);

        //tanggal
        long date =System.currentTimeMillis();
        SimpleDateFormat adf_date = new SimpleDateFormat("dd-MM-yyyy");
        String date_string = adf_date.format(date);
        etTanggal.setText(date_string);
        //jam
        SimpleDateFormat adf = new SimpleDateFormat("HH:mm");
        String datestring = adf.format(date);
        etPukul.setText(datestring);

        etBiaya.addTextChangedListener(onTextChangedListener());




        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, type);

//      final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//            android.R.layout.simple_spinner_item, (jenis.equals("RODA 2")) ? type_mtr : type);

        spType.setAdapter(adapter);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spType.getSelectedItem().equals("SUV/MPV")){
                    flagType = 0;
                    autoCompleteTextView.setAdapter(merkAdapter);
                }else if (spType.getSelectedItem().equals("TRUCK")){
                    flagType = 1;
                    autoCompleteTextView.setAdapter(truckAdapter);
                }else if (spType.getSelectedItem().equals("BUS")){
                    flagType = 2;
                    autoCompleteTextView.setAdapter(busAdapter);
                }else if (spType.getSelectedItem().equals("RODA 2")){
                    flagType = 3;
                    autoCompleteTextView.setAdapter(motorAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final ArrayAdapter<String> adapterTypeMobilisasi = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, typeMobilisasi);
        spTypeMobilisasi.setAdapter(adapterTypeMobilisasi);
        spTypeMobilisasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spTypeMobilisasi.getSelectedItem().equals("SELF-SERVICE")){
                    etBiaya.setText("0");
                }else if (spTypeMobilisasi.getSelectedItem().equals("TOWING")){
                    etBiaya.setText("0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fillMerkList();

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        etVendor.setOnItemClickListener(vendorAutocompleteClickListener);
        autoCompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        etLokasipool.setOnItemClickListener(poolAutocompleteClickListener);

        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();
        if (!results.isEmpty()){
            String output="";
            for(InputData inputData:results){
                output+=inputData.toString();
            }

            etVendor.setText(results.first().getVendor());
            vendorid = results.first().getVendorid();
            etTanggal.setText(results.first().getTanggal());
            etPukul.setText(results.first().getPukul());
//            etLokasi_penarikan.setText(results.first().getLokasi_penarikan());
            spType.setSelection(results.first().getType(),true);
            autoCompleteTextView.setText(results.first().getVhcTypename());

            vhc_item_id = results.first().getVhcItemid();
            vhc_type_id = results.first().getVhcTypeid();
            vhc_type_name = results.first().getVhcTypename();

            etTahun.setText(results.first().getTahun());
            etStnk_an.setText(results.first().getStnk());
            etNopol1.setText(results.first().getNopol1());
            etNopol2.setText(results.first().getNopol2());
            etNopol3.setText(results.first().getNopol3());
            etKmditarik.setText(results.first().getKmditarik());
//            etMeterbensin.setText(results.first().getMeterbensin());
            etCabang.setText(results.first().getCabang());
            etNomesin.setText(results.first().getNomesin());
            etNorangka.setText(results.first().getNorangka());
            etWarna.setText(results.first().getWarna());
            etKmditerima.setText(results.first().getKmditerima());
            etLokasipool.setText(results.first().getLokasi_pool());
            pool_id = results.first().getLokasi_poolid();

            etNopol1.setEnabled(false);
            etNopol2.setEnabled(false);
            etNopol3.setEnabled(false);
        }

        //if (etNopol1.getText().toString().equals("") && etNopol2.getText().toString().equals("") && etNopol3.getText().toString().equals("")){
                getBastk();
        //}


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etVendor.getText().toString().equals("")){
                    etVendor.setError("Harap masukkan vendor");
                } else if (etTanggal.getText().toString().equals("")){
                    etTanggal.setError("Harap masukkan tanggal");
                } else if (etPukul.getText().toString().equals("")){
                    etPukul.setError("Harap masukkan jam");
//                }else if (etLokasi_penarikan.getText().toString().equals("")){
//                    etLokasi_penarikan.setError("Harap masukkan lokasi penarikan");
                }else if (autoCompleteTextView.getText().toString().equals("")){
                    autoCompleteTextView.setError("Harap masukkan merk/type");
                }else if (etTahun.getText().toString().equals("")){
                    etTahun.setError("Harap masukkan tahun");
                }else if (etStnk_an.getText().toString().equals("")){
                    etStnk_an.setError("Harap masukkan nama STNK");
                }else if (etNopol1.getText().toString().equals("") && etNopol2.getText().toString().equals("") && etNopol3.getText().toString().equals("")){
                    etNopol1.setError("Harap masukkan Nopol");
                }else if (etKmditarik.getText().toString().equals("")){
                    etKmditarik.setError("Harap masukkan KM ditarik");
//                }else if (etMeterbensin.getText().toString().equals("")){
//                    etMeterbensin.setError("Harap masukkan meter bensin");
                }else if (etCabang.getText().toString().equals("")){
                    etCabang.setError("Harap masukkan cabang");
                }else if (etNomesin.getText().toString().equals("")){
                    etNomesin.setError("Harap masukkan nomor mesin");
                }else if (etNorangka.getText().toString().equals("")){
                    etNorangka.setError("Harap masukkan nomor rangka");
                }else if (etWarna.getText().toString().equals("")){
                    etWarna.setError("Harap masukkan warna");
//                }else if (etKmditerima.getText().toString().equals("")){
//                    etKmditerima.setError("Harap masukkan KM diterima");
                }else if (pool_id.equals("") || etLokasipool.getText().toString().equals("")){
                    etLokasipool.setError("Harap masukkan lokasi pool");
                }else {

//                    realm.beginTransaction();
//                    // hapus database terlebih dahulu
//                    results.deleteAllFromRealm();
//                    //  insert data ke database
//
//                    InputData obj = realm.createObject(InputData.class);
//                    obj.setVendor(etVendor.getText().toString());
//                    obj.setVendorid(vendorid);
//                    obj.setTanggal(etTanggal.getText().toString());
//                    obj.setPukul(etPukul.getText().toString());
////                    obj.setLokasi_penarikan(etLokasi_penarikan.getText().toString());
//                    obj.setMerk(autoCompleteTextView.getText().toString());
//                    obj.setType(flagType);
//                    obj.setVhcItemid(vhc_item_id);
//                    obj.setVhcTypeid(vhc_type_id);
//                    obj.setVhcTypename(vhc_type_name);
//                    obj.setTahun(etTahun.getText().toString());
//                    obj.setStnk(etStnk_an.getText().toString());
//                    obj.setNopol1(etNopol1.getText().toString());
//                    obj.setNopol2(etNopol2.getText().toString());
//                    obj.setNopol3(etNopol3.getText().toString());
//                    obj.setKmditarik(etKmditarik.getText().toString());
//                    obj.setMeterbensin(etMeterbensin.getText().toString());
//                    obj.setCabang(etCabang.getText().toString());
//                    obj.setNomesin(etNomesin.getText().toString());
//                    obj.setNorangka(etNorangka.getText().toString());
//                    obj.setWarna(etWarna.getText().toString());
//                    obj.setKmditerima(etKmditerima.getText().toString());
//                    obj.setLokasi_pool(etLokasipool.getText().toString());
//                    obj.setLokasi_poolid(pool_id);
//                    realm.commitTransaction();

                    sendForm();

//                    Intent i = new Intent(MobInputFormActivity.this, MobInputCeklistActivity.class);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });

        //menu dialog DatePicker
        myCalendar = Calendar.getInstance();
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_start = new DatePickerDialog(MobInputFormActivity.this, datestart, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                DatePicker dpStart = dpd_start.getDatePicker();
                //dpStart.setMinDate(myCalendar.getTimeInMillis());
                dpd_start.show();
            }
        });

        etPukul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHour = myCalendar.get(Calendar.HOUR_OF_DAY);
                mMinute = myCalendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MobInputFormActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                etPukul.setText(String.format("%02d:%02d",hourOfDay,minute));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

    }

    private void fillMerkList() {
        class GetData extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                viewDialog.showDialog();
                //loading = ProgressDialog.show(MobInputFormActivity.this,"","Loading data",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewDialog.hideDialog();
                //loading.dismiss();
                showData(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID, _appid);
                hashMap.put(configuration.KEY_CCODE, _ccode);
                hashMap.put(configuration.KEY_TOKEN, _token);
                RequestHandler rh = new RequestHandler();

                String s;
                if(jenis.equals("RODA 2")){
                    s = rh.sendPostRequest(configuration.URL_LIST_MTR,hashMap);
//                    s = rh.sendPostRequest(configuration.URL_LIST_VHC,hashMap);
                }else {
                    s = rh.sendPostRequest(configuration.URL_LIST_VHC,hashMap);
                }
                Log.wtf("vhc RODA TES ANGGA", s);
                return s;

//                String s = "";
////              String s = rh.sendPostRequest(configuration.URL_LIST_VHC,hashMap);
//                Log.wtf("vhc", s);
//                return s;
            }
        }
        GetData ge = new GetData();
        ge.execute();
    }

    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            String code = jsonObject.getString(configuration.TAG_CODE);
            Log.wtf("kode", "result: " + code);
            String message = jsonObject.getString(configuration.TAG_MESSAGE);
            Log.wtf("message", "result: " + message);
            JSONObject resultdata = jsonObject.getJSONObject(configuration.TAG_JSON_DATA);

            if (code.equals("200")) {
                if(jenis.equals("RODA 2")){
                    JSONArray resultvhc = resultdata.getJSONArray(configuration.TAG_JSON_VHC);
                    JSONArray resultpool = resultdata.getJSONArray(configuration.TAG_JSON_POOL);
                    JSONArray resultvendor = resultdata.getJSONArray(configuration.TAG_JSON_VENDOR);

                    for (int i = 0; i < resultvhc.length(); i++) {
                        JSONObject jo = resultvhc.getJSONObject(i);

                        String itemId = jo.getString(configuration.KEY_ITEM_ID);
                        Log.wtf("itemId", ":"+itemId);
                        String itemName = jo.getString(configuration.KEY_ITEM_NAME);
                        Log.wtf("itemName", ":"+itemName);
                        String itemTypeid = jo.getString(configuration.KEY_TYPE_ID);
                        Log.wtf("itemTypeid", ":"+itemTypeid);
                        String itemTypename = jo.getString(configuration.KEY_TYPE_NAME);
                        Log.wtf("itemTypename", ":"+itemTypename);

                        Motor motor = new Motor(itemId,itemName,itemTypeid,itemTypename);
                        motorList.add(motor);
                    }
                    motorAdapter = new MotorAdapter(this, motorList);
                    autoCompleteTextView.setAdapter(motorAdapter);

                    for (int i = 0; i < resultpool.length(); i++) {
                        JSONObject jo = resultpool.getJSONObject(i);

                        String poolId = jo.getString(configuration.KEY_POOL_ID);
//                    String poolName = jo.getString(configuration.KEY_POOL_NAME);
                        String poolAddress = jo.getString(configuration.KEY_POOL_ADDRESS);

                        Pool pool = new Pool(poolId,poolAddress);
                        poolList.add(pool);
                    }
                    poolAdapter = new PoolAdapter(this, poolList);
                    etLokasipool.setAdapter(poolAdapter);

                    for (int i = 0; i < resultvendor.length(); i++) {
                        JSONObject jo = resultvendor.getJSONObject(i);

                        String vendorId = jo.getString(configuration.KEY_VENDOR_ID);
                        String vendor = jo.getString(configuration.KEY_VENDOR_NAME);

                        Vendor vendor1 = new Vendor(vendorId,vendor);
                        vendorList.add(vendor1);
                    }
                    vendorAdapter = new VendorAdapter(this, vendorList);
                    etVendor.setAdapter(vendorAdapter);

                }else {
                    JSONArray resultvhc = resultdata.getJSONArray(configuration.TAG_JSON_VHC);
                    JSONArray resulttruck = resultdata.getJSONArray(configuration.TAG_TRUCK);
                    JSONArray resultbus = resultdata.getJSONArray(configuration.TAG_BUS);
                    JSONArray resultpool = resultdata.getJSONArray(configuration.TAG_JSON_POOL);
                    JSONArray resultvendor = resultdata.getJSONArray(configuration.TAG_JSON_VENDOR);

                    for (int i = 0; i < resultvhc.length(); i++) {
                        JSONObject jo = resultvhc.getJSONObject(i);

                        String itemId = jo.getString(configuration.KEY_ITEM_ID);
                        Log.wtf("itemId", ":"+itemId);
                        String itemName = jo.getString(configuration.KEY_ITEM_NAME);
                        Log.wtf("itemName", ":"+itemName);
                        String itemTypeid = jo.getString(configuration.KEY_TYPE_ID);
                        Log.wtf("itemTypeid", ":"+itemTypeid);
                        String itemTypename = jo.getString(configuration.KEY_TYPE_NAME);
                        Log.wtf("itemTypename", ":"+itemTypename);

                        Merk merk = new Merk(itemId,itemName,itemTypeid,itemTypename);
                        merkList.add(merk);
                    }

                    merkAdapter = new MerkAdapter(this, merkList);
                    autoCompleteTextView.setAdapter(merkAdapter);

                    for (int i = 0; i < resulttruck.length(); i++) {
                        JSONObject jotruck = resulttruck.getJSONObject(i);

                        String itemId = jotruck.getString(configuration.KEY_ITEM_ID);
                        Log.wtf("itemId", ":"+itemId);
                        String itemName = jotruck.getString(configuration.KEY_ITEM_NAME);
                        Log.wtf("itemName", ":"+itemName);
                        String itemTypeid = jotruck.getString(configuration.KEY_TYPE_ID);
                        Log.wtf("itemTypeid", ":"+itemTypeid);
                        String itemTypename = jotruck.getString(configuration.KEY_TYPE_NAME);
                        Log.wtf("itemTypename", ":"+itemTypename);

                        Truck truck = new Truck(itemId,itemName,itemTypeid,itemTypename);
                        truckList.add(truck);
                    }
                    truckAdapter = new TruckAdapter(this, truckList);

                    for (int i = 0; i < resultbus.length(); i++) {
                        JSONObject jobus = resultbus.getJSONObject(i);

                        String itemId = jobus.getString(configuration.KEY_ITEM_ID);
                        Log.wtf("itemId", ":"+itemId);
                        String itemName = jobus.getString(configuration.KEY_ITEM_NAME);
                        Log.wtf("itemName", ":"+itemName);
                        String itemTypeid = jobus.getString(configuration.KEY_TYPE_ID);
                        Log.wtf("itemTypeid", ":"+itemTypeid);
                        String itemTypename = jobus.getString(configuration.KEY_TYPE_NAME);
                        Log.wtf("itemTypename", ":"+itemTypename);

                        Bus bus = new Bus(itemId,itemName,itemTypeid,itemTypename);
                        busList.add(bus);
                    }
                    busAdapter = new BusAdapter(this, busList);

                    for (int i = 0; i < resultpool.length(); i++) {
                        JSONObject jo = resultpool.getJSONObject(i);

                        String poolId = jo.getString(configuration.KEY_POOL_ID);
//                    String poolName = jo.getString(configuration.KEY_POOL_NAME);
                        String poolAddress = jo.getString(configuration.KEY_POOL_ADDRESS);

                        Pool pool = new Pool(poolId,poolAddress);
                        poolList.add(pool);
                    }
                    poolAdapter = new PoolAdapter(this, poolList);
                    etLokasipool.setAdapter(poolAdapter);

                    for (int i = 0; i < resultvendor.length(); i++) {
                        JSONObject jo = resultvendor.getJSONObject(i);

                        String vendorId = jo.getString(configuration.KEY_VENDOR_ID);
                        String vendor = jo.getString(configuration.KEY_VENDOR_NAME);

                        Vendor vendor1 = new Vendor(vendorId,vendor);
                        vendorList.add(vendor1);
                    }
                    vendorAdapter = new VendorAdapter(this, vendorList);
                    etVendor.setAdapter(vendorAdapter);
                }
            } else {
                Toast.makeText(MobInputFormActivity.this, "Data Not Found, " + message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            //e.printStackTrace();
            Toast.makeText(MobInputFormActivity.this,"Please Check Your Connection",Toast.LENGTH_SHORT).show();
        }

    }

    private void getBastk(){
        class GetBastk extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                viewDialog2.showDialog();
//                loading = ProgressDialog.show(MobInputFormActivity.this,"","Loading...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewDialog2.hideDialog();
                //loading.dismiss();
                showBastk(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,appid);
                hashMap.put(configuration.KEY_CCODE,ccode);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_PARENT,"1");
                hashMap.put(configuration.KEY_NOPOL,var_nopol);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_DETAIL_BASTK,hashMap);
                Log.wtf("kirim", s);
                return s;
            }
        }
        GetBastk ge = new GetBastk();
        ge.execute();
    }

    private void showBastk(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
            JSONArray result = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);

            if(code == 200){
                JSONObject user = result.getJSONObject(0);
                type_mobilisasi = user.getString(configuration.KEY_TYPE_MOBILISASI);
                cost_mobilisasi = user.getString(configuration.KEY_COST_MOBILISASI);
                vehicle_id = user.getString(configuration.KEY_VEHICLEID);
                vhc_type_name = user.getString(configuration.KEY_VHC_TYPE);
                vehicle_year = user.getString(configuration.KEY_VHC_YEAR);
                vehicle_color = user.getString(configuration.KEY_VHC_COLOR);
                vehicle_engine_no = user.getString(configuration.KEY_VHC_ENGINE);
                vehicle_chasis_no = user.getString(configuration.KEY_VHC_CHASIS);
                vhc_loc_penarikan = user.getString(configuration.KEY_VHC_PENARIKAN);
                vhc_loc_pool = user.getString(configuration.KEY_LOC_POOL);
                vhc_date_in = user.getString(configuration.KEY_VHC_DATE);
                vhc_stnk = user.getString(configuration.KEY_VHC_STNK);
                vhc_kmditarik = user.getString(configuration.KEY_KM_DITARIK);
                vhc_kmditerima = user.getString(configuration.KEY_VHC_DITERIMA);
                vhc_meterbensin = user.getString(configuration.KEY_METER_BENSIN);
                vhc_cabang = user.getString(configuration.KEY_VHC_CABANG);
                vendor_name = user.getString(configuration.KEY_VENDOR_NAME);
                pool = user.getString(configuration.KEY_POOL);
                pool_name = user.getString(configuration.KEY_POOL_NAME);
                vhc_cat_name = user.getString(configuration.KEY_VHC_CAT);

                String[] tgl = vhc_date_in.split(" ");
                String[] tgl_res = tgl[0].split("-");
                etTanggal.setText(tgl_res[2] + "-" + tgl_res[1] + "-" + tgl_res[0]);
//            etTanggal.setText(tgl[0]);
                etPukul.setText(tgl[1]);
                //etMerk.setText(vhc_type_name);
                autoCompleteTextView.setText(vhc_type_name);

                if (type_mobilisasi.equals("null") || type_mobilisasi.equals("")){
                    spTypeMobilisasi.setSelection(0);
                }else {
                    if (type_mobilisasi.equals("SELF-SERVICE")){
                        spTypeMobilisasi.setSelection(0);
                    }else if (type_mobilisasi.equals("TOWING")){
                        spTypeMobilisasi.setSelection(1);
                    }
                }

                if (vhc_cat_name.equals("null") || vhc_cat_name.equals("")){
                    spType.setSelection(0);
                }else {
                    if (vhc_cat_name.equals("SUV/MVP")){
                        spType.setSelection(0);
                    }else if (vhc_cat_name.equals("TRUCK")){
                        spType.setSelection(1);
                    }else if (vhc_cat_name.equals("BUS")){
                        spType.setSelection(2);
                    }else if (vhc_cat_name.equals("MTR")){
                        spType.setSelection(3);
                    }
                }

                if (cost_mobilisasi.equals("null") || cost_mobilisasi.equals("")){
                    etBiaya.setText("");
                }else {
                    etBiaya.setText(cost_mobilisasi);
                }

                if (vehicle_year.equals("null") || vehicle_year.equals("")){
                    etTahun.setText("");
                }else {
                    etTahun.setText(vehicle_year);
                }

                if (vhc_stnk.equals("null")){
                    etStnk_an.setText("");
                }else {
                    etStnk_an.setText(vhc_stnk);
                }

                String[] vehicle = vehicle_id.split("-");
                etNopol1.setText(vehicle[0]);
                etNopol2.setText(vehicle[1]);
                etNopol3.setText(vehicle[2]);

                if (vhc_kmditarik.equals("null")){
                    etKmditarik.setText("");
                }else {
                    etKmditarik.setText(vhc_kmditarik);
                }

//                if (vhc_meterbensin.equals("null")){
//                    etMeterbensin.setText("");
//                }else {
//                    etMeterbensin.setText(vhc_meterbensin);
//                }

                if (vhc_cabang.equals("null")){
                    etCabang.setText("");
                }else {
                    etCabang.setText(vhc_cabang);
                }

                if (vehicle_engine_no.equals("null")){
                    etNomesin.setText("");
                }else {
                    etNomesin.setText(vehicle_engine_no);
                }

                if (vehicle_chasis_no.equals("null")){
                    etNorangka.setText("");
                }else {
                    etNorangka.setText(vehicle_chasis_no);
                }

                if (vehicle_color.equals("null")){
                    etWarna.setText("");
                }else {
                    etWarna.setText(vehicle_color);
                }

                if (vhc_kmditerima.equals("null")){
                    etKmditerima.setText("");
                }else {
                    etKmditerima.setText(vhc_kmditerima);
                }

                etVendor.setText(vendor_name);

                if (pool.equals("null")){
                    etLokasipool.setText("");
                }else {
                    etLokasipool.setText(pool_name);
                    var_pool = pool;
                }

//                spType .setText(vhc_cat_name);

            }else if (code == 304){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendForm(){
        String[] var_tgl = etTanggal.getText().toString().split("-");
        final String tgl = var_tgl[2] + "-" + var_tgl[1] + "-" + var_tgl[0];

        class SendData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MobInputFormActivity.this,"","Processing...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showForm(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,_appid);
                hashMap.put(configuration.KEY_CCODE,_ccode);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_STATUS,"0");
                hashMap.put(configuration.KEY_PARENT_STATUS,"1");
                hashMap.put(configuration.KEY_NOPOL,var_nopol);
                hashMap.put(configuration.KEY_VENDOR_ID,etVendor.getText().toString());
                hashMap.put(configuration.KEY_TANGGAL,tgl);
                hashMap.put(configuration.KEY_TYPE_MOBILISASI,spTypeMobilisasi.getSelectedItem().toString());
                hashMap.put(configuration.KEY_COST_MOBILISASI,etBiaya.getText().toString().replace(",",""));
                hashMap.put(configuration.KEY_PUKUL,etPukul.getText().toString());
                hashMap.put(configuration.KEY_TYPE, spType.getSelectedItem().toString());
                hashMap.put(configuration.KEY_MERk,autoCompleteTextView.getText().toString());
                hashMap.put(configuration.KEY_TAHUN,etTahun.getText().toString());
                hashMap.put(configuration.KEY_STNK,etStnk_an.getText().toString());
                hashMap.put(configuration.KEY_kmditarik,etKmditarik.getText().toString());
//                hashMap.put(configuration.KEY_meterbensin,etMeterbensin.getText().toString());
                hashMap.put(configuration.KEY_cabang,etCabang.getText().toString());
                hashMap.put(configuration.KEY_nomesin,etNomesin.getText().toString());
                hashMap.put(configuration.KEY_norangka,etNorangka.getText().toString());
                hashMap.put(configuration.KEY_warna,etWarna.getText().toString());
                hashMap.put(configuration.KEY_kmditerima,etKmditerima.getText().toString());
                hashMap.put(configuration.KEY_POOL_ID,pool);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_INSERT_FORM,hashMap);
                return s;
            }
        }
        SendData ge = new SendData();
        ge.execute();
    }

    private void showForm(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);

            if(code == 200 ){
                if(jenis.equals("RODA 2")){
                    Intent i = new Intent(MobInputFormActivity.this, MobInputCeklistMotorActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_nopol",var_nopol);
                    i.putExtras(b);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }else {
                    Intent i = new Intent(MobInputFormActivity.this, MobInputCeklistActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_nopol",var_nopol);
                    i.putExtras(b);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }else if (code == 304){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == etBiaya.getEditableText()){
                    etBiaya.removeTextChangedListener(this);
                    try {
                        String originalString = s.toString();

                        Long longval;
                        if (originalString.contains(",")) {
                            originalString = originalString.replaceAll(",", "");
                        }
                        longval = Long.parseLong(originalString);

                        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                        formatter.applyPattern("#,###,###,###");
                        String formattedString = formatter.format(longval);

                        //setting text after format to EditText
                        etBiaya.setText(formattedString);
                        etBiaya.setSelection(etBiaya.getText().length());
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }
                    etBiaya.addTextChangedListener(this);

                }
            }
        };
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (flagType == 0){
                autoCompleteTextView.setAdapter(merkAdapter);
                vhc_type_id = merkAdapter.getItem(position).getItem_type_id();
                vhc_type_name = merkAdapter.getItem(position).getItem_type_name();
                vhc_item_id = merkAdapter.getItem(position).getItem_id();
                Log.wtf("item",vhc_item_id + "-" + vhc_type_name);
            }else if (flagType == 1){
                autoCompleteTextView.setAdapter(truckAdapter);
                vhc_type_id = truckAdapter.getItem(position).getItem_type_id();
                vhc_type_name = truckAdapter.getItem(position).getItem_type_name();
                vhc_item_id = truckAdapter.getItem(position).getItem_id();
                Log.wtf("item",vhc_item_id + "-" + vhc_type_name);
            }else if (flagType == 2){
                autoCompleteTextView.setAdapter(busAdapter);
                vhc_type_id = busAdapter.getItem(position).getItem_type_id();
                vhc_type_name = busAdapter.getItem(position).getItem_type_name();
                vhc_item_id = busAdapter.getItem(position).getItem_id();
                Log.wtf("item",vhc_item_id + "-" + vhc_type_name);
            }else if (flagType == 3){
                autoCompleteTextView.setAdapter(motorAdapter);
                vhc_type_id = motorAdapter.getItem(position).getItem_type_id();
                vhc_type_name = motorAdapter.getItem(position).getItem_type_name();
                vhc_item_id = motorAdapter.getItem(position).getItem_id();
                Log.wtf("item",vhc_item_id + "-" + vhc_type_name);
            }
        }
    };
    private AdapterView.OnItemClickListener poolAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            pool_id = poolAdapter.getItem(position).getPool_id();
            pool_address = poolAdapter.getItem(position).getPool_address();
        }
    };
    private AdapterView.OnItemClickListener vendorAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            vendorid = vendorAdapter.getItem(position).getVendor_id();
            vendor = vendorAdapter.getItem(position).getVendor();
        }
    };

    private DatePickerDialog.OnDateSetListener datestart = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updatePickerStart();
        }
    };
    private void updatePickerStart() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            etTanggal.setText(sdf.format(myCalendar.getTime()));

        }catch (Exception e){

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
}
