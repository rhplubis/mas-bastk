package com.tunasrent.auctionapps.dispatcher;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tunasrent.auctionapps.DB.Ceklist;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.DB.InputData;
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
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;
import com.tunasrent.auctionapps.util.RequestHandler;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class DisInputFormActivity extends AppCompatActivity {
    UserSessionManager session;
    Toolbar toolbar;
    EditText etTanggal;
    EditText etPukul;
//    EditText etLokasi_penarikan;
    EditText etTahun;
    EditText etStnk_an;
    EditText etNopol;
    EditText etNopol1;
    EditText etNopol2;
    EditText etNopol3;
    EditText etKmditarik;
    EditText etMeterbensin;
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

    String _name;
    String _fullname;
    String _appid;
    String _ccode;
    String _token;
    String _group;

    int flagType = 0, flagStatusKunci =0, flagKondisiKunci = 0;
    private List<Merk> merkList = new ArrayList<>();
    private List<Truck> truckList = new ArrayList<>();
    private List<Bus> busList = new ArrayList<>();
    private List<Motor> motorList = new ArrayList<>();
    private List<Pool> poolList = new ArrayList<>();
    private List<Vendor> vendorList = new ArrayList<>();
    MerkAdapter merkAdapter;
    TruckAdapter truckAdapter;
    BusAdapter busAdapter;
    MotorAdapter motorAdapter;
    PoolAdapter poolAdapter;
    VendorAdapter vendorAdapter;

    String vendorid;
    String vendor;
    String vhc_type_id;
    String vhc_type_name;
    String vhc_item_id;
    String pool_id;
    String pool_address;
    String pool_name;
    String jenis;

    Spinner spType;

    private Spinner spinnerStatusKunci, spinnerKondisiKunci;
    private LinearLayout layoutKondisiKunci, layoutTotalKunci;
    private EditText editTextTotalKunci;


    private String[] type = {"SUV/MPV", "TRUCK", "BUS"};
    private String[] type_mtr = {"RODA 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_input_form);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Input Data Mobil");
        toolbar.setTitleTextColor(Color.WHITE);

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
            jenis = b.getString("parse_jenis");
        }

        etVendor = findViewById(R.id.et_vendor);
        etTanggal = findViewById(R.id.et_tanggal);
        etPukul = findViewById(R.id.et_pukul);
//        etLokasi_penarikan = findViewById(R.id.et_lokasi_penarikan);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.et_merktype);
        etTahun = findViewById(R.id.et_tahun);
        etStnk_an = findViewById(R.id.et_stnk_an);
//        etNopol = findViewById(R.id.et_nopol);
        etNopol1 = findViewById(R.id.et_nopol1);
        etNopol2 = findViewById(R.id.et_nopol2);
        etNopol3 = findViewById(R.id.et_nopol3);
        etKmditarik = findViewById(R.id.et_kmditarik);
        etMeterbensin = findViewById(R.id.et_meterbensin);
        etCabang = findViewById(R.id.et_cabang);
        etNomesin = findViewById(R.id.et_nomesin);
        etNorangka = findViewById(R.id.et_norangka);
        etWarna = findViewById(R.id.et_warna);
        etKmditerima = findViewById(R.id.et_kmditerima);
//        etCficabang = findViewById(R.id.et_cficabang);
        etLokasipool = findViewById(R.id.et_lokasipool);
        spType = findViewById(R.id.sp_typeunit);
        spinnerStatusKunci = findViewById(R.id.spinnerStatusKunci);
        spinnerKondisiKunci = findViewById(R.id.spinnerKondisiKunci);
        layoutKondisiKunci = findViewById(R.id.layoutKondisiKunci);
        layoutTotalKunci = findViewById(R.id.layoutTotalKunci);
        editTextTotalKunci = findViewById(R.id.editTextTotalKunci);


        // Data untuk Spinner Status Kunci
        String[] statusKunciOptions = {"Tidak Ada", "Ada"};
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusKunciOptions);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusKunci.setAdapter(statusAdapter);

        // Data untuk Spinner Kondisi Kunci
        String[] kondisiKunciOptions = {"Baik", "Tidak Baik"};
        ArrayAdapter<String> kondisiAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kondisiKunciOptions);
        kondisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKondisiKunci.setAdapter(kondisiAdapter);

        // Saat pertama kali dibuka, sembunyikan Kondisi Kunci & Total Kunci
        layoutKondisiKunci.setVisibility(View.GONE);
        layoutTotalKunci.setVisibility(View.GONE);

        spinnerStatusKunci.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { // Jika "Tidak Ada" dipilih

                    layoutKondisiKunci.setVisibility(View.GONE);
                    layoutTotalKunci.setVisibility(View.GONE);
                } else { // Jika "Ada" dipilih
                    layoutKondisiKunci.setVisibility(View.VISIBLE);
                    layoutTotalKunci.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                layoutKondisiKunci.setVisibility(View.GONE);
                layoutTotalKunci.setVisibility(View.GONE);
            }
        });


        btnNext = findViewById(R.id.btn_next);

        //tanggal
        long date =System.currentTimeMillis();
        SimpleDateFormat adf_date = new SimpleDateFormat("dd-MM-yyyy");
        String date_string = adf_date.format(date);
        etTanggal.setText(date_string);
        //jam
        SimpleDateFormat adf = new SimpleDateFormat("HH:mm");
        String datestring = adf.format(date);
        etPukul.setText(datestring);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, (jenis.equals("RODA 2")) ? type_mtr : type);
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


        Realm.init(this);
        realm = Realm.getDefaultInstance();

        fillMerkList();

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
            spinnerStatusKunci.setSelection(results.first().getStatusKunci(),true);
            spinnerKondisiKunci.setSelection(results.first().getKondisiKunci(),true);

            autoCompleteTextView.setText(results.first().getVhcTypename());

            vhc_item_id = results.first().getVhcItemid();
            vhc_type_id = results.first().getVhcTypeid();
            vhc_type_name = results.first().getVhcTypename();

            etTahun.setText(results.first().getTahun());
            editTextTotalKunci.setText(results.first().getTotalKunci());
            etStnk_an.setText(results.first().getStnk());
            etNopol1.setText(results.first().getNopol1());
            etNopol2.setText(results.first().getNopol2());
            etNopol3.setText(results.first().getNopol3());
            etKmditarik.setText(results.first().getKmditarik());
            etMeterbensin.setText(results.first().getMeterbensin());
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

                    realm.beginTransaction();
                   // hapus database terlebih dahulu
                    results.deleteAllFromRealm();
                  //  insert data ke database

                    InputData obj = realm.createObject(InputData.class);
                    obj.setVendor(etVendor.getText().toString());
                    obj.setVendorid(vendorid);
                    obj.setTanggal(etTanggal.getText().toString());
                    obj.setPukul(etPukul.getText().toString());
//                    obj.setLokasi_penarikan(etLokasi_penarikan.getText().toString());
                    obj.setMerk(autoCompleteTextView.getText().toString());
                    obj.setType(flagType);
                    obj.setStatusKunci(flagStatusKunci);
                    obj.setKondisiKunci(flagKondisiKunci);
                    obj.setVhcItemid(vhc_item_id);
                    obj.setVhcTypeid(vhc_type_id);
                    obj.setVhcTypename(vhc_type_name);
                    obj.setTahun(etTahun.getText().toString());
                    obj.setTotalKunci(editTextTotalKunci.getText().toString());
                    obj.setStnk(etStnk_an.getText().toString());
                    obj.setNopol1(etNopol1.getText().toString());
                    obj.setNopol2(etNopol2.getText().toString());
                    obj.setNopol3(etNopol3.getText().toString());
                    obj.setKmditarik(etKmditarik.getText().toString());
                    obj.setMeterbensin(etMeterbensin.getText().toString());
                    obj.setCabang(etCabang.getText().toString());
                    obj.setNomesin(etNomesin.getText().toString());
                    obj.setNorangka(etNorangka.getText().toString());
                    obj.setWarna(etWarna.getText().toString());
                    obj.setKmditerima(etKmditerima.getText().toString());
                    obj.setLokasi_pool(etLokasipool.getText().toString());
                    obj.setLokasi_poolid(pool_id);
                    realm.commitTransaction();

                    if (flagType == 3){

                        Intent i = new Intent(DisInputFormActivity.this, DisInputCeklistMotorActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    } else{
                        Intent i = new Intent(DisInputFormActivity.this, DisInputCeklistActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }

                }
            }
        });

        //menu dialog DatePicker
        myCalendar = Calendar.getInstance();
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_start = new DatePickerDialog(DisInputFormActivity.this, datestart, myCalendar.get(Calendar.YEAR),
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

                TimePickerDialog timePickerDialog = new TimePickerDialog(DisInputFormActivity.this,
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
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisInputFormActivity.this,"","Loading data",false,false);
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
                hashMap.put(configuration.KEY_APPID, _appid);
                hashMap.put(configuration.KEY_CCODE, _ccode);
                hashMap.put(configuration.KEY_TOKEN, _token);
                RequestHandler rh = new RequestHandler();
                String s;
                if(jenis.equals("RODA 2")){
                    s = rh.sendPostRequest(configuration.URL_LIST_MTR,hashMap);
                }else {
                    s = rh.sendPostRequest(configuration.URL_LIST_VHC,hashMap);
                }

                Log.wtf("vhc", s);
                return s;
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
                Toast.makeText(DisInputFormActivity.this, "Data Not Found, " + message, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            //e.printStackTrace();
            Toast.makeText(DisInputFormActivity.this,"Please Check Your Connection",Toast.LENGTH_SHORT).show();
        }
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
