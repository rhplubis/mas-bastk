package com.tunasrent.auctionapps.mobilisasi;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.tunasrent.auctionapps.DB.BodyKendaraan;
import com.tunasrent.auctionapps.DB.CeklistMotor;
import com.tunasrent.auctionapps.DB.InputData;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.dispatcher.DisInputFotoActivity;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class MobInputCeklistMotorActivity extends AppCompatActivity {
    UserSessionManager session;

    Realm realm;
    Calendar myCalendar;
    Toolbar toolbar;

    String _name, _fullname, _appid, _ccode, _token, _group;
    String message,token,username,full_name,email;
    int code;
    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;

//    # BODY #
    CheckBox cb_cover_kepala1, cb_cover_kepala2;
    CheckBox cb_cover_speedo1, cb_cover_speedo2;
    CheckBox cb_cover_depan1, cb_cover_depan2;
    CheckBox cb_sayap_kanan1, cb_sayap_kanan2;
    CheckBox cb_sayap_kiri1, cb_sayap_kiri2;
    CheckBox cb_spak_depan1, cb_spak_depan2;
    CheckBox cb_cover_tengah1, cb_cover_tengah2;
    CheckBox cb_cover_spgkanan1, cb_cover_spgkanan2;
    CheckBox cb_cover_spgkiri1, cb_cover_spgkiri2;
    CheckBox cb_rantai_depan1,  cb_rantai_depan2;
    CheckBox cb_rantai_blkg1, cb_rantai_blkg2;
    CheckBox cb_spak_blkg1, cb_spak_blkg2;
    CheckBox cb_cover_accu1, cb_cover_accu2;
    CheckBox cb_cover_lampurem1,cb_cover_lampurem2;
//    Spinner spBan2_fisikkiri;
//    private String[] kondisi = {"Botak", "Sedang", "Baik"};

    CheckBox cb_tangki_bensin1, cb_tangki_bensin2;
    CheckBox cb_cover_bawah1, cb_cover_bawah2;
    CheckBox cb_dek_desk1, cb_dek_desk2;
    CheckBox cb_cover_mesin1, cb_cover_mesin2;
    CheckBox cb_emblem1, cb_emblem2;

//  # MESIN #
    CheckBox cb_blok_mesin1, cb_blok_mesin2;
    CheckBox cb_carburator1, cb_carburator2;
    CheckBox cb_filter_carbu1, cb_filter_carbu2;
    CheckBox cb_busi1, cb_busi2;

    CheckBox cb_cdi1, cb_cdi2;
    CheckBox cb_accu1, cb_accu2;
    CheckBox cb_kick_starter1, cb_kick_starter2;
    CheckBox cb_perseneling1, cb_perseneling2;
    CheckBox cb_knalpot1, cb_knalpot2;
    CheckBox cb_coil1, cb_coil2;
    CheckBox cb_blok1, cb_blok2;
    CheckBox cb_tutupcvt1,cb_tutupcvt2;
    CheckBox cb_radiator1, cb_radiator2;
    CheckBox cb_lampu_depan1, cb_lampu_depan2;
    CheckBox cb_sein_depan1, cb_sein_depan2;

    CheckBox cb_sein_blkg1, cb_sein_blkg2;
    CheckBox cb_lampu_rem1, cb_lampu_rem2;

//    # KAKI-KAKI #
    CheckBox cb_handlerem_depan1, cb_handlerem_depan2;
    CheckBox cb_pedalrem_blkg1, cb_pedalrem_blkg2;
    CheckBox cb_handlerem_blkg1, cb_handlerem_blkg2;
    CheckBox cb_handle_kopling1, cb_handle_kopling2;
    CheckBox cb_master_rem1, cb_master_rem2;
    CheckBox cb_caliper1, cb_caliper2;
    CheckBox cb_cakram1, cb_cakram2;
    CheckBox cb_shock_depan1, cb_shock_depan2;
    CheckBox cb_shock_blkg1, cb_shock_blkg2;
    CheckBox cb_tromol_depan1, cb_tromol_depan2;
    CheckBox cb_tromol_blkg1, cb_tromol_blkg2;
    CheckBox cb_footstep_depan1, cb_footstep_depan2;
    CheckBox cb_footstep_blkg1, cb_footstep_blkg2;
    CheckBox cb_swing_arm1, cb_swing_arm2;
    CheckBox cb_rantai_fanbelt1, cb_rantai_fanbelt2;
    CheckBox cb_gear_depan1, cb_gear_depan2;
    CheckBox cb_gear_blkg1, cb_gear_blkg2;
    CheckBox cb_standar_tengah1, cb_standar_tengah2;
    CheckBox cb_standar_samping1, cb_standar_samping2;
//    RadioButton rbRadio_listrik, rbTape_listrik, rbCd_listrik;
//    EditText etMerk_listrik;
    CheckBox cb_ban_depan1, cb_ban_depan2;
    CheckBox cb_ban_blkg1, cb_ban_blkg2;
    CheckBox cb_velg_depan1, cb_velg_depan2;
    CheckBox cb_velg_blkg1, cb_velg_blkg2;

//    # PERLENGKAPAN #
    CheckBox cb_kaca_spion1, cb_kaca_spion2;
    CheckBox cb_speedometer1, cb_speedometer2;
    CheckBox cb_Grip_stang1, cb_Grip_stang2;
    CheckBox cb_Rumah_kunci1, cb_Rumah_kunci2;
    CheckBox cb_Klakson1, cb_Klakson2;
    CheckBox cb_Behel1,  cb_Behel2;
    CheckBox cb_Tutup_knalpot1, cb_Tutup_knalpot2;

    TextInputEditText etSd_stnk;
    TextInputEditText etSd_bukukir;
    TextInputEditText etSd_ijintrayek;
    TextInputEditText etSd_ijinusaha;

    CheckBox cb_jok1, cb_jok2;
    CheckBox cb_Stripping1, cb_Stripping2;
    CheckBox cb_Tombol_navigasi1, cb_Tombol_navigasi2;

    Button btnNextdispatcher;

//  # BODY #
//    Boolean var_cover_kepala1,var_cover_kepala2=false;
//    Boolean var_cover_speedo1,var_cover_speedo2 = false;
//    Boolean var_cover_depan1,var_cover_depan2=false;
//    Boolean var_sayap_kanan1,var_sayap_kanan2 = false;
//    Boolean var_sayap_kiri1,var_sayap_kiri2 = false;
//    Boolean var_spak_depan1,var_spak_depan2 = false;
//    Boolean var_cover_tengah1, var_cover_tengah2=false;
//    Boolean var_cover_spgkanan1,var_cover_spgkanan2=false;
//    Boolean var_cover_spgkiri1,var_cover_spgkiri2=false;
//    Boolean var_rantai_depan1,var_rantai_depan2=false;
//    Boolean var_rantai_blkg1,var_rantai_blkg2=false;
//    Boolean var_spak_blkg1,var_spak_blkg2=false;
//    Boolean var_cover_accu1,var_cover_accu2=false;
//    Boolean var_cover_lampurem1,var_cover_lampurem2=false;
//    Boolean var_tangki_bensin1,var_tangki_bensin2=false;
//    Boolean var_cover_bawah1,var_cover_bawah2=false;
//    Boolean var_dek_desk1,var_dek_desk2=false;
//    Boolean var_cover_mesin1,var_cover_mesin2=false;
//    Boolean var_emblem1,var_emblem2=false;

    int var_cover_kepala1 = 0;
    int var_cover_kepala2 = 0;
    int var_cover_speedo1 = 0;
    int var_cover_speedo2 = 0;
    int var_cover_depan1 = 0;
    int var_cover_depan2 = 0;
    int var_sayap_kanan1 = 0;
    int var_sayap_kanan2 = 0;
    int var_sayap_kiri1 = 0;
    int var_sayap_kiri2 = 0;
    int var_spak_depan1 = 0;
    int var_spak_depan2 = 0;
    int var_cover_tengah1 = 0;
    int var_cover_tengah2 = 0;
    int var_cover_spgkanan1 = 0;
    int var_cover_spgkanan2 = 0;
    int var_cover_spgkiri1 = 0;
    int var_cover_spgkiri2 = 0;
    int var_rantai_depan1 = 0;
    int var_rantai_depan2 = 0;
    int var_rantai_blkg1 = 0;
    int var_rantai_blkg2 = 0;
    int var_spak_blkg1 = 0;
    int var_spak_blkg2 = 0;
    int var_cover_accu1 = 0;
    int var_cover_accu2 = 0;
    int var_cover_lampurem1 = 0;
    int var_cover_lampurem2 = 0;
    int var_tangki_bensin1 = 0;
    int var_tangki_bensin2 = 0;
    int var_cover_bawah1 = 0;
    int var_cover_bawah2 = 0;
    int var_dek_desk1 = 0;
    int var_dek_desk2 = 0;
    int var_cover_mesin1 = 0;
    int var_cover_mesin2 = 0;
    int var_emblem1 = 0;
    int var_emblem2 = 0;

//    # MESIN #
//    Boolean var_blok_mesin1,var_blok_mesin2=false;
//    Boolean var_carburator1,var_carburator2=false;
//    Boolean var_filter_carbu1,var_filter_carbu2=false;
//    Boolean var_busi1, var_busi2 =false;
//    Boolean var_cdi1,var_cdi2=false;
//    Boolean var_accu1,var_accu2=false;
//    Boolean var_kick_starter1, var_kick_starter2=false;
//    Boolean var_perseneling1, var_perseneling2=false;
//    Boolean var_knalpot1, var_knalpot2=false;
//    Boolean var_coil1, var_coil2=false;
//    Boolean var_blok1, var_blok2=false;
//    Boolean var_tutupcvt1, var_tutupcvt2=false;
//    Boolean var_radiator1, var_radiator2=false;
//    Boolean var_lampu_depan1, var_lampu_depan2=false;
//    Boolean var_sein_depan1, var_sein_depan2=false;
//    Boolean var_sein_blkg1, var_sein_blkg2=false;
//    Boolean var_lampu_rem1, var_lampu_rem2=false;

    int var_blok_mesin1 = 0;
    int var_blok_mesin2 = 0;
    int var_carburator1 = 0;
    int var_carburator2 = 0;
    int var_filter_carbu1 = 0;
    int var_filter_carbu2 = 0;
    int var_busi1 = 0;
    int var_busi2 = 0;
    int var_cdi1 = 0;
    int var_cdi2 = 0;
    int var_accu1 = 0;
    int var_accu2 = 0;
    int var_kick_starter1 = 0;
    int var_kick_starter2 = 0;
    int var_perseneling1 = 0;
    int var_perseneling2 = 0;
    int var_knalpot1 = 0;
    int var_knalpot2 = 0;
    int var_coil1 = 0;
    int var_coil2 = 0;
    int var_blok1 = 0;
    int var_blok2 = 0;
    int var_tutupcvt1 = 0;
    int var_tutupcvt2 = 0;
    int var_radiator1 = 0;
    int var_radiator2 = 0;
    int var_lampu_depan1 = 0;
    int var_lampu_depan2 = 0;
    int var_sein_depan1 = 0;
    int var_sein_depan2 = 0;
    int var_sein_blkg1 = 0;
    int var_sein_blkg2 = 0;
    int var_lampu_rem1 = 0;
    int var_lampu_rem2 = 0;


    //    # KAKI-KAKI #
//    Boolean var_handlerem_depan1, var_handlerem_depan2=false;
//    Boolean var_pedalrem_blkg1, var_pedalrem_blkg2=false;
//    Boolean var_handlerem_blkg1, var_handlerem_blkg2=false;
//    Boolean var_handle_kopling1, var_handle_kopling2=false;
//    Boolean var_master_rem1,var_master_rem2=false;
//    Boolean var_caliper1, var_caliper2=false;
//    Boolean var_cakram1, var_cakram2=false;
//    Boolean var_shock_depan1, var_shock_depan2=false;
//    Boolean var_shock_blkg1, var_shock_blkg2=false;
//    Boolean var_tromol_depan1, var_tromol_depan2=false;
//    Boolean var_tromol_blkg1, var_tromol_blkg2=false;
//    Boolean var_footstep_depan1, var_footstep_depan2=false;
//    Boolean var_footstep_blkg1, var_footstep_blkg2=false;
//    Boolean var_swing_arm1, var_swing_arm2=false;
//    Boolean var_rantai_fanbelt1, var_rantai_fanbelt2=false;
//    Boolean var_gear_depan1, var_gear_depan2=false;
//    Boolean var_gear_blkg1, var_gear_blkg2=false;
//    Boolean var_standar_tengah1, var_standar_tengah2=false;
//    Boolean var_standar_samping1, var_standar_samping2=false;
//    Boolean var_ban_depan1, var_ban_depan2=false;
//    Boolean var_ban_blkg1, var_ban_blkg2=false;
//    Boolean var_velg_depan1, var_velg_depan2=false;
//    Boolean var_velg_blkg1, var_velg_blkg2=false;

    int var_handlerem_depan1 = 0;
    int var_handlerem_depan2 = 0;
    int var_pedalrem_blkg1 = 0;
    int var_pedalrem_blkg2 = 0;
    int var_handlerem_blkg1 = 0;
    int var_handlerem_blkg2 = 0;
    int var_handle_kopling1 = 0;
    int var_handle_kopling2 = 0;
    int var_master_rem1 = 0;
    int var_master_rem2 = 0;
    int var_caliper1 = 0;
    int var_caliper2 = 0;
    int var_cakram1 = 0;
    int var_cakram2 = 0;
    int var_shock_depan1 = 0;
    int var_shock_depan2 = 0;
    int var_shock_blkg1 = 0;
    int var_shock_blkg2 = 0;
    int var_tromol_depan1 = 0;
    int var_tromol_depan2 = 0;
    int var_tromol_blkg1 = 0;
    int var_tromol_blkg2 = 0;
    int var_footstep_depan1 = 0;
    int var_footstep_depan2 = 0;
    int var_footstep_blkg1 = 0;
    int var_footstep_blkg2 = 0;
    int var_swing_arm1 = 0;
    int var_swing_arm2 = 0;
    int var_rantai_fanbelt1 = 0;
    int var_rantai_fanbelt2 = 0;
    int var_gear_depan1 = 0;
    int var_gear_depan2 = 0;
    int var_gear_blkg1 = 0;
    int var_gear_blkg2 = 0;
    int var_standar_tengah1 = 0;
    int var_standar_tengah2 = 0;
    int var_standar_samping1 = 0;
    int var_standar_samping2 = 0;
    int var_ban_depan1 = 0;
    int var_ban_depan2 = 0;
    int var_ban_blkg1 = 0;
    int var_ban_blkg2 = 0;
    int var_velg_depan1 = 0;
    int var_velg_depan2 = 0;
    int var_velg_blkg1 = 0;
    int var_velg_blkg2 = 0;


    //    # PERLENGKAPAN #
//    Boolean var_kaca_spion1, var_kaca_spion2=false;
//    Boolean var_speedometer1, var_speedometer2=false;
//    Boolean var_Grip_stang1, var_Grip_stang2=false;
//    Boolean var_Rumah_kunci1, var_Rumah_kunci2=false;
//    Boolean var_Klakson1, var_Klakson2=false;
//    Boolean var_Behel1, var_Behel2=false;
//    Boolean var_Tutup_knalpot1, var_Tutup_knalpot2=false;
//    Boolean var_jok1, var_jok2=false;
//    Boolean var_Stripping1, var_Stripping2=false;
//    Boolean var_Tombol_navigasi1, var_Tombol_navigasi2=false;

    int var_kaca_spion1 = 0;
    int var_kaca_spion2 = 0;
    int var_speedometer1 = 0;
    int var_speedometer2 = 0;
    int var_grip_stang1 = 0;
    int var_grip_stang2 = 0;
    int var_rumah_kunci1 = 0;
    int var_rumah_kunci2 = 0;
    int var_klakson1 = 0;
    int var_klakson2 = 0;
    int var_behel1 = 0;
    int var_behel2 = 0;
    int var_tutup_knalpot1 = 0;
    int var_tutup_knalpot2 = 0;
    int var_jok1 = 0;
    int var_jok2 = 0;
    int var_stripping1 = 0;
    int var_stripping2 = 0;
    int var_tombol_navigasi1 = 0;
    int var_tombol_navigasi2 = 0;


    String var_nopol;

//    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_input_ceklist_motor);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mobilisasi Ceklist Unit Motor");
        toolbar.setTitleTextColor(Color.WHITE);

//        # BODY #
        cb_cover_kepala1 = findViewById(R.id.cb_cover_kepala1);
        cb_cover_kepala2 = findViewById(R.id.cb_cover_kepala2);
        cb_cover_speedo1 = findViewById(R.id.cb_cover_speedo1);
        cb_cover_speedo2 = findViewById(R.id.cb_cover_speedo2);
        cb_cover_depan1 = findViewById(R.id.cb_cover_depan1);
        cb_cover_depan2 = findViewById(R.id.cb_cover_depan2);
        cb_sayap_kanan1 = findViewById(R.id.cb_sayap_kanan1);
        cb_sayap_kanan2 = findViewById(R.id.cb_sayap_kanan2);
        cb_sayap_kiri1 = findViewById(R.id.cb_sayap_kiri1);
        cb_sayap_kiri2 = findViewById(R.id.cb_sayap_kiri2);
        cb_spak_depan1 = findViewById(R.id.cb_spak_depan1);
        cb_spak_depan2 = findViewById(R.id.cb_spak_depan2);
        cb_cover_tengah1 = findViewById(R.id.cb_cover_tengah1);
        cb_cover_tengah2 = findViewById(R.id.cb_cover_tengah2);
        cb_cover_spgkanan1 = findViewById(R.id.cb_cover_spgkanan1);
        cb_cover_spgkanan2 = findViewById(R.id.cb_cover_spgkanan2);
        cb_cover_spgkiri1 = findViewById(R.id.cb_cover_spgkiri1);
        cb_cover_spgkiri2 = findViewById(R.id.cb_cover_spgkiri2);
        cb_rantai_depan1 = findViewById(R.id.cb_rantai_depan1);
        cb_rantai_depan2 = findViewById(R.id.cb_rantai_depan2);
        cb_rantai_blkg1 = findViewById(R.id.cb_rantai_blkg1);
        cb_rantai_blkg2 = findViewById(R.id.cb_rantai_blkg2);
        cb_spak_blkg1 = findViewById(R.id.cb_spak_blkg1);
        cb_spak_blkg2 = findViewById(R.id.cb_spak_blkg2);
        cb_cover_accu1 = findViewById(R.id.cb_cover_accu1);
        cb_cover_accu2 = findViewById(R.id.cb_cover_accu2);
        cb_cover_lampurem1 = findViewById(R.id.cb_cover_lampurem1);
        cb_cover_lampurem2 = findViewById(R.id.cb_cover_lampurem2);
        cb_tangki_bensin1 = findViewById(R.id.cb_tangki_bensin1);
        cb_tangki_bensin2 = findViewById(R.id.cb_tangki_bensin2);
        cb_cover_bawah1 = findViewById(R.id.cb_cover_bawah1);
        cb_cover_bawah2 = findViewById(R.id.cb_cover_bawah2);
        cb_dek_desk1 = findViewById(R.id.cb_dek_desk1);
        cb_dek_desk2 = findViewById(R.id.cb_dek_desk2);
        cb_cover_mesin1 = findViewById(R.id.cb_cover_mesin1);
        cb_cover_mesin2 = findViewById(R.id.cb_cover_mesin2);
        cb_emblem1 = findViewById(R.id.cb_emblem1);
        cb_emblem2 = findViewById(R.id.cb_emblem2);
//        # MESIN #
        cb_blok_mesin1 = findViewById(R.id.cb_blok_mesin1);
        cb_blok_mesin2 = findViewById(R.id.cb_blok_mesin2);
        cb_carburator1 = findViewById(R.id.cb_carburator1);
        cb_carburator2 = findViewById(R.id.cb_carburator2);
        cb_filter_carbu1 = findViewById(R.id.cb_filter_carbu1);
        cb_filter_carbu2 = findViewById(R.id.cb_filter_carbu2);
        cb_busi1 = findViewById(R.id.cb_busi1);
        cb_busi2 = findViewById(R.id.cb_busi2);
        cb_cdi1 = findViewById(R.id.cb_cdi1);
        cb_cdi2 = findViewById(R.id.cb_cdi2);
        cb_accu1 = findViewById(R.id.cb_accu1);
        cb_accu2 = findViewById(R.id.cb_accu2);
        cb_kick_starter1 = findViewById(R.id.cb_kick_starter1);
        cb_kick_starter2 = findViewById(R.id.cb_kick_starter2);

        cb_perseneling1 = findViewById(R.id.cb_perseneling1);
        cb_perseneling2 = findViewById(R.id.cb_perseneling2);
        cb_knalpot1 = findViewById(R.id.cb_knalpot1);
        cb_knalpot2 = findViewById(R.id.cb_knalpot2);
        cb_coil1 = findViewById(R.id.cb_coil1);
        cb_coil2 = findViewById(R.id.cb_coil2);
        cb_blok1 = findViewById(R.id.cb_blok1);
        cb_blok2 = findViewById(R.id.cb_blok2);
        cb_tutupcvt1 = findViewById(R.id.cb_tutupcvt1);
        cb_tutupcvt2 = findViewById(R.id.cb_tutupcvt2);
        cb_radiator1 = findViewById(R.id.cb_radiator1);
        cb_radiator2 = findViewById(R.id.cb_radiator2);
        cb_lampu_depan1 = findViewById(R.id.cb_lampu_depan1);
        cb_lampu_depan2 = findViewById(R.id.cb_lampu_depan2);
        cb_sein_depan1 = findViewById(R.id.cb_sein_depan1);
        cb_sein_depan2 = findViewById(R.id.cb_sein_depan2);
        cb_sein_blkg1 = findViewById(R.id.cb_sein_blkg1);
        cb_sein_blkg2 = findViewById(R.id.cb_sein_blkg2);
        cb_lampu_rem1 = findViewById(R.id.cb_lampu_rem1);
        cb_lampu_rem2 = findViewById(R.id.cb_lampu_rem2);
//        # KAKI-KAKI #
        cb_handlerem_depan1 = findViewById(R.id.cb_handlerem_depan1);
        cb_handlerem_depan2 = findViewById(R.id.cb_handlerem_depan2);
        cb_pedalrem_blkg1 = findViewById(R.id.cb_pedalrem_blkg1);
        cb_pedalrem_blkg2 = findViewById(R.id.cb_pedalrem_blkg2);
        cb_handlerem_blkg1 = findViewById(R.id.cb_handlerem_blkg1);
        cb_handlerem_blkg2 = findViewById(R.id.cb_handlerem_blkg2);
        cb_handle_kopling1 = findViewById(R.id.cb_handle_kopling1);
        cb_handle_kopling2 = findViewById(R.id.cb_handle_kopling2);
        cb_master_rem1 = findViewById(R.id.cb_master_rem1);
        cb_master_rem2 = findViewById(R.id.cb_master_rem2);
        cb_caliper1 = findViewById(R.id.cb_caliper1);
        cb_caliper2 = findViewById(R.id.cb_caliper2);
        cb_cakram1 = findViewById(R.id.cb_cakram1);
        cb_cakram2 = findViewById(R.id.cb_cakram2);
        cb_shock_depan1 = findViewById(R.id.cb_shock_depan1);
        cb_shock_depan2 = findViewById(R.id.cb_shock_depan2);
        cb_shock_blkg1 = findViewById(R.id.cb_shock_blkg1);
        cb_shock_blkg2 = findViewById(R.id.cb_shock_blkg2);
        cb_tromol_depan1 = findViewById(R.id.cb_tromol_depan1);
        cb_tromol_depan2 = findViewById(R.id.cb_tromol_depan2);
        cb_tromol_blkg1 = findViewById(R.id.cb_tromol_blkg1);
        cb_tromol_blkg2 = findViewById(R.id.cb_tromol_blkg2);
        cb_footstep_depan1 = findViewById(R.id.cb_footstep_depan1);
        cb_footstep_depan2 = findViewById(R.id.cb_footstep_depan2);
        cb_footstep_blkg1 = findViewById(R.id.cb_footstep_blkg1);
        cb_footstep_blkg2 = findViewById(R.id.cb_footstep_blkg2);
        cb_swing_arm1 = findViewById(R.id.cb_swing_arm1);
        cb_swing_arm2 = findViewById(R.id.cb_swing_arm2);
        cb_rantai_fanbelt1 = findViewById(R.id.cb_rantai_fanbelt1);
        cb_rantai_fanbelt2 = findViewById(R.id.cb_rantai_fanbelt2);
        cb_gear_depan1 = findViewById(R.id.cb_gear_depan1);
        cb_gear_depan2 = findViewById(R.id.cb_gear_depan2);
        cb_gear_blkg1 = findViewById(R.id.cb_gear_blkg1);
        cb_gear_blkg2 = findViewById(R.id.cb_gear_blkg2);
        cb_standar_tengah1 = findViewById(R.id.cb_standar_tengah1);
        cb_standar_tengah2 = findViewById(R.id.cb_standar_tengah2);
        cb_standar_samping1 = findViewById(R.id.cb_standar_samping1);
        cb_standar_samping2 = findViewById(R.id.cb_standar_samping2);
        cb_ban_depan1 = findViewById(R.id.cb_ban_depan1);
        cb_ban_depan2 = findViewById(R.id.cb_ban_depan2);
        cb_ban_blkg1 = findViewById(R.id.cb_ban_blkg1);
        cb_ban_blkg2 = findViewById(R.id.cb_ban_blkg2);
        cb_velg_depan1 = findViewById(R.id.cb_velg_depan1);
        cb_velg_depan2 = findViewById(R.id.cb_velg_depan2);
        cb_velg_blkg1 = findViewById(R.id.cb_velg_blkg1);
        cb_velg_blkg2 = findViewById(R.id.cb_velg_blkg2);
//        # PERLENGKAPAN #
        cb_kaca_spion1 = findViewById(R.id.cb_kaca_spion1);
        cb_kaca_spion2 = findViewById(R.id.cb_kaca_spion2);
        cb_speedometer1 = findViewById(R.id.cb_speedometer1);
        cb_speedometer2 = findViewById(R.id.cb_speedometer2);
        cb_Grip_stang1 = findViewById(R.id.cb_Grip_stang1);
        cb_Grip_stang2 = findViewById(R.id.cb_Grip_stang2);
        cb_Rumah_kunci1 = findViewById(R.id.cb_Rumah_kunci1);
        cb_Rumah_kunci2 = findViewById(R.id.cb_Rumah_kunci2);
        cb_Klakson1 = findViewById(R.id.cb_Klakson1);
        cb_Klakson2 = findViewById(R.id.cb_Klakson2);
        cb_Behel1 = findViewById(R.id.cb_Behel1);
        cb_Behel2 = findViewById(R.id.cb_Behel2);
        cb_Tutup_knalpot1 = findViewById(R.id.cb_Tutup_knalpot1);
        cb_Tutup_knalpot2 = findViewById(R.id.cb_Tutup_knalpot2);
        cb_jok1 = findViewById(R.id.cb_jok1);
        cb_jok2 = findViewById(R.id.cb_jok2);
        cb_Stripping1 = findViewById(R.id.cb_Stripping1);
        cb_Stripping2 = findViewById(R.id.cb_Stripping2);
        cb_Tombol_navigasi1 = findViewById(R.id.cb_Tombol_navigasi1);
        cb_Tombol_navigasi2 = findViewById(R.id.cb_Tombol_navigasi2);

        btnNextdispatcher = findViewById(R.id.btn_nextdispatcher);

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
            var_nopol = b.getString("parse_nopol");
        }

        getBastk();


//        Realm.init(this);
//
//        final Realm realm = Realm.getDefaultInstance(); //creating  database oject
//        RealmResults<CeklistMotor> ceklistMotors = realm.where(CeklistMotor.class).findAllAsync();
        //fetching the data
//        ceklistMotors.load();
//        if (ceklistMotors.isEmpty()){
//            //Toast.makeText(DisInputCeklistActivity.this,"if",Toast.LENGTH_SHORT).show();
//            realm.beginTransaction();
//            //insert data ke database
////          # BODY #
//            CeklistMotor ckl = realm.createObject(CeklistMotor.class);
//            ckl.setDb_var_cover_kepala1(false);
//            ckl.setDb_var_cover_kepala2(false);
//            ckl.setDb_var_cover_speedo1(false);
//            ckl.setDb_var_cover_speedo2(false);
//            ckl.setDb_var_cover_depan1(false);
//            ckl.setDb_var_cover_depan2(false);
//            ckl.setDb_var_sayap_kanan1(false);
//            ckl.setDb_var_sayap_kanan2(false);
//            ckl.setDb_var_sayap_kiri1(false);
//            ckl.setDb_var_sayap_kiri2(false);
//            ckl.setDb_var_spak_depan1(false);
//            ckl.setDb_var_spak_depan2(false);
//            ckl.setDb_var_cover_tengah1(false);
//            ckl.setDb_var_cover_tengah2(false);
//            ckl.setDb_var_cover_spgkanan1(false);
//            ckl.setDb_var_cover_spgkanan2(false);
//            ckl.setDb_var_cover_spgkiri1(false);
//            ckl.setDb_var_cover_spgkiri2(false);
//            ckl.setDb_var_rantai_depan1(false);
//            ckl.setDb_var_rantai_depan2(false);
//            ckl.setDb_var_rantai_blkg1(false);
//            ckl.setDb_var_rantai_blkg2(false);
//            ckl.setDb_var_spak_blkg1(false);
//            ckl.setDb_var_spak_blkg2(false);
//            ckl.setDb_var_cover_accu1(false);
//            ckl.setDb_var_cover_accu2(false);
//            ckl.setDb_var_cover_lampurem1(false);
//            ckl.setDb_var_cover_lampurem2(false);
//            ckl.setDb_var_tangki_bensin1(false);
//            ckl.setDb_var_tangki_bensin2(false);
//            ckl.setDb_var_cover_bawah1(false);
//            ckl.setDb_var_cover_bawah2(false);
//            ckl.setDb_var_dek_desk1(false);
//            ckl.setDb_var_dek_desk2(false);
//            ckl.setDb_var_cover_mesin1(false);
//            ckl.setDb_var_cover_mesin2(false);
//            ckl.setDb_var_emblem1(false);
//            ckl.setDb_var_emblem2(false);
////          # MESIN #
//            ckl.setDb_var_blok_mesin1(false);
//            ckl.setDb_var_blok_mesin2(false);
//            ckl.setDb_var_carburator1(false);
//            ckl.setDb_var_carburator2(false);
//            ckl.setDb_var_filter_carbu1(false);
//            ckl.setDb_var_filter_carbu2(false);
//            ckl.setDb_var_busi1(false);
//            ckl.setDb_var_busi2(false);
//            ckl.setDb_var_cdi1(false);
//            ckl.setDb_var_cdi2(false);
//            ckl.setDb_var_accu1(false);
//            ckl.setDb_var_accu2(false);
//            ckl.setDb_var_kick_starter1(false);
//            ckl.setDb_var_kick_starter2(false);
//            ckl.setDb_var_perseneling1(false);
//            ckl.setDb_var_perseneling2(false);
//            ckl.setDb_var_knalpot1(false);
//            ckl.setDb_var_knalpot2(false);
//            ckl.setDb_var_coil1(false);
//            ckl.setDb_var_coil2(false);
//            ckl.setDb_var_blok1(false);
//            ckl.setDb_var_blok2(false);
//            ckl.setDb_var_tutupcvt1(false);
//            ckl.setDb_var_tutupcvt2(false);
//            ckl.setDb_var_radiator1(false);
//            ckl.setDb_var_radiator2(false);
//            ckl.setDb_var_lampu_depan1(false);
//            ckl.setDb_var_lampu_depan2(false);
//            ckl.setDb_var_sein_depan1(false);
//            ckl.setDb_var_sein_depan2(false);
//            ckl.setDb_var_sein_blkg1(false);
//            ckl.setDb_var_sein_blkg2(false);
//            ckl.setDb_var_lampu_rem1(false);
//            ckl.setDb_var_lampu_rem2(false);
////          # KAKI-KAKI #
//            ckl.setDb_var_handlerem_depan1(false);
//            ckl.setDb_var_handlerem_depan2(false);
//            ckl.setDb_var_pedalrem_blkg1(false);
//            ckl.setDb_var_pedalrem_blkg2(false);
//            ckl.setDb_var_handlerem_blkg1(false);
//            ckl.setDb_var_handlerem_blkg2(false);
//            ckl.setDb_var_handle_kopling1(false);
//            ckl.setDb_var_handle_kopling2(false);
//            ckl.setDb_var_master_rem1(false);
//            ckl.setDb_var_master_rem2(false);
//            ckl.setDb_var_caliper1(false);
//            ckl.setDb_var_caliper2(false);
//            ckl.setDb_var_cakram1(false);
//            ckl.setDb_var_cakram2(false);
//            ckl.setDb_var_shock_depan1(false);
//            ckl.setDb_var_shock_depan2(false);
//            ckl.setDb_var_shock_blkg1(false);
//            ckl.setDb_var_shock_blkg2(false);
//            ckl.setDb_var_tromol_depan1(false);
//            ckl.setDb_var_tromol_depan2(false);
//            ckl.setDb_var_tromol_blkg1(false);
//            ckl.setDb_var_tromol_blkg2(false);
//            ckl.setDb_var_footstep_depan1(false);
//            ckl.setDb_var_footstep_depan2(false);
//            ckl.setDb_var_footstep_blkg1(false);
//            ckl.setDb_var_footstep_blkg2(false);
//            ckl.setDb_var_swing_arm1(false);
//            ckl.setDb_var_swing_arm2(false);
//            ckl.setDb_var_rantai_fanbelt1(false);
//            ckl.setDb_var_rantai_fanbelt2(false);
//            ckl.setDb_var_gear_depan1(false);
//            ckl.setDb_var_gear_depan2(false);
//            ckl.setDb_var_gear_blkg1(false);
//            ckl.setDb_var_gear_blkg2(false);
//            ckl.setDb_var_standar_tengah1(false);
//            ckl.setDb_var_standar_tengah2(false);
//            ckl.setDb_var_standar_samping1(false);
//            ckl.setDb_var_standar_samping2(false);
//            ckl.setDb_var_ban_depan1(false);
//            ckl.setDb_var_ban_depan2(false);
//            ckl.setDb_var_ban_blkg1(false);
//            ckl.setDb_var_ban_blkg2(false);
//            ckl.setDb_var_velg_depan1(false);
//            ckl.setDb_var_velg_depan2(false);
//            ckl.setDb_var_velg_blkg1(false);
//            ckl.setDb_var_velg_blkg2(false);
////          # PERLENGKAPAN #
//            ckl.setDb_var_kaca_spion1(false);
//            ckl.setDb_var_kaca_spion2(false);
//            ckl.setDb_var_speedometer1(false);
//            ckl.setDb_var_speedometer2(false);
//            ckl.setDb_var_Grip_stang1(false);
//            ckl.setDb_var_Grip_stang2(false);
//            ckl.setDb_var_Rumah_kunci1(false);
//            ckl.setDb_var_Rumah_kunci2(false);
//            ckl.setDb_var_Klakson1(false);
//            ckl.setDb_var_Klakson2(false);
//            ckl.setDb_var_Behel1(false);
//            ckl.setDb_var_Behel2(false);
//            ckl.setDb_var_Tutup_knalpot1(false);
//            ckl.setDb_var_Tutup_knalpot2(false);
//            ckl.setDb_var_jok1(false);
//            ckl.setDb_var_jok2(false);
//            ckl.setDb_var_Stripping1(false);
//            ckl.setDb_var_Stripping2(false);
//            ckl.setDb_var_Tombol_navigasi1(false);
//            ckl.setDb_var_Tombol_navigasi2(false);
//            //============================================
////          # BODY #
//            ckl.setTdb_var_cover_kepala1("Tidak Ada");
//            ckl.setTdb_var_cover_kepala2("Rusak");
//            ckl.setTdb_var_cover_speedo1("Tidak Ada");
//            ckl.setTdb_var_cover_speedo2("Rusak");
//            ckl.setTdb_var_cover_depan1("Tidak Ada");
//            ckl.setTdb_var_cover_depan2("Rusak");
//            ckl.setTdb_var_sayap_kanan1("Tidak Ada");
//            ckl.setTdb_var_sayap_kanan2("Rusak");
//            ckl.setTdb_var_sayap_kiri1("Tidak Ada");
//            ckl.setTdb_var_sayap_kiri2("Rusak");
//            ckl.setTdb_var_spak_depan1("Tidak Ada");
//            ckl.setTdb_var_spak_depan2("Rusak");
//            ckl.setTdb_var_cover_tengah1("Tidak Ada");
//            ckl.setTdb_var_cover_tengah2("Rusak");
//            ckl.setTdb_var_cover_spgkanan1("Tidak Ada");
//            ckl.setTdb_var_cover_spgkanan2("Rusak");
//            ckl.setTdb_var_cover_spgkiri1("Tidak Ada");
//            ckl.setTdb_var_cover_spgkiri2("Rusak");
//            ckl.setTdb_var_rantai_depan1("Tidak Ada");
//            ckl.setTdb_var_rantai_depan2("Rusak");
//            ckl.setTdb_var_rantai_blkg1("Tidak Ada");
//            ckl.setTdb_var_rantai_blkg2("Rusak");
//            ckl.setTdb_var_spak_blkg1("Tidak Ada");
//            ckl.setTdb_var_spak_blkg2("Rusak");
//            ckl.setTdb_var_cover_accu1("Tidak Ada");
//            ckl.setTdb_var_cover_accu2("Rusak");
//            ckl.setTdb_var_cover_lampurem1("Tidak Ada");
//            ckl.setTdb_var_cover_lampurem2("Rusak");
//            ckl.setTdb_var_tangki_bensin1("Tidak Ada");
//            ckl.setTdb_var_tangki_bensin2("Rusak");
//            ckl.setTdb_var_cover_bawah1("Tidak Ada");
//            ckl.setTdb_var_cover_bawah2("Rusak");
//            ckl.setTdb_var_dek_desk1("Tidak Ada");
//            ckl.setTdb_var_dek_desk2("Rusak");
//            ckl.setTdb_var_cover_mesin1("Tidak Ada");
//            ckl.setTdb_var_cover_mesin2("Rusak");
//            ckl.setTdb_var_emblem1("Tidak Ada");
//            ckl.setTdb_var_emblem2("Rusak");
////          # MESIN #
//            ckl.setTdb_var_blok_mesin1("Tidak Ada");
//            ckl.setTdb_var_blok_mesin2("Rusak");
//            ckl.setTdb_var_carburator1("Tidak Ada");
//            ckl.setTdb_var_carburator2("Rusak");
//            ckl.setTdb_var_filter_carbu1("Tidak Ada");
//            ckl.setTdb_var_filter_carbu2("Rusak");
//            ckl.setTdb_var_busi1("Tidak Ada");
//            ckl.setTdb_var_busi2("Rusak");
//            ckl.setTdb_var_cdi1("Tidak Ada");
//            ckl.setTdb_var_cdi2("Rusak");
//            ckl.setTdb_var_accu1("Tidak Ada");
//            ckl.setTdb_var_accu2("Rusak");
//            ckl.setTdb_var_kick_starter1("Tidak Ada");
//            ckl.setTdb_var_kick_starter2("Rusak");
//            ckl.setTdb_var_perseneling1("Tidak Ada");
//            ckl.setTdb_var_perseneling2("Rusak");
//            ckl.setTdb_var_knalpot1("Tidak Ada");
//            ckl.setTdb_var_knalpot2("Rusak");
//            ckl.setTdb_var_coil1("Tidak Ada");
//            ckl.setTdb_var_coil2("Rusak");
//            ckl.setTdb_var_blok1("Tidak Ada");
//            ckl.setTdb_var_blok2("Rusak");
//            ckl.setTdb_var_tutupcvt1("Tidak Ada");
//            ckl.setTdb_var_tutupcvt2("Rusak");
//            ckl.setTdb_var_radiator1("Tidak Ada");
//            ckl.setTdb_var_radiator2("Rusak");
//            ckl.setTdb_var_lampu_depan1("Tidak Ada");
//            ckl.setTdb_var_lampu_depan2("Rusak");
//            ckl.setTdb_var_sein_depan1("Tidak Ada");
//            ckl.setTdb_var_sein_depan2("Rusak");
//            ckl.setTdb_var_sein_blkg1("Tidak Ada");
//            ckl.setTdb_var_sein_blkg2("Rusak");
//            ckl.setTdb_var_lampu_rem1("Tidak Ada");
//            ckl.setTdb_var_lampu_rem2("Rusak");
////          # KAKI-KAKI #
//            ckl.setTdb_var_handlerem_depan1("Tidak Ada");
//            ckl.setTdb_var_handlerem_depan2("Rusak");
//            ckl.setTdb_var_pedalrem_blkg1("Tidak Ada");
//            ckl.setTdb_var_pedalrem_blkg2("Rusak");
//            ckl.setTdb_var_handlerem_blkg1("Tidak Ada");
//            ckl.setTdb_var_handlerem_blkg2("Rusak");
//            ckl.setTdb_var_handle_kopling1("Tidak Ada");
//            ckl.setTdb_var_handle_kopling2("Rusak");
//            ckl.setTdb_var_master_rem1("Tidak Ada");
//            ckl.setTdb_var_master_rem2("Rusak");
//            ckl.setTdb_var_caliper1("Tidak Ada");
//            ckl.setTdb_var_caliper2("Rusak");
//            ckl.setTdb_var_cakram1("Tidak Ada");
//            ckl.setTdb_var_cakram2("Rusak");
//            ckl.setTdb_var_shock_depan1("Tidak Ada");
//            ckl.setTdb_var_shock_depan2("Rusak");
//            ckl.setTdb_var_shock_blkg1("Tidak Ada");
//            ckl.setTdb_var_shock_blkg2("Rusak");
//            ckl.setTdb_var_tromol_depan1("Tidak Ada");
//            ckl.setTdb_var_tromol_depan2("Rusak");
//            ckl.setTdb_var_tromol_blkg1("Tidak Ada");
//            ckl.setTdb_var_tromol_blkg2("Rusak");
//            ckl.setTdb_var_footstep_depan1("Tidak Ada");
//            ckl.setTdb_var_footstep_depan2("Rusak");
//            ckl.setTdb_var_footstep_blkg1("Tidak Ada");
//            ckl.setTdb_var_footstep_blkg2("Rusak");
//            ckl.setTdb_var_swing_arm1("Tidak Ada");
//            ckl.setTdb_var_swing_arm2("Rusak");
//            ckl.setTdb_var_rantai_fanbelt1("Tidak Ada");
//            ckl.setTdb_var_rantai_fanbelt2("Rusak");
//            ckl.setTdb_var_gear_depan1("Tidak Ada");
//            ckl.setTdb_var_gear_depan2("Rusak");
//            ckl.setTdb_var_gear_blkg1("Tidak Ada");
//            ckl.setTdb_var_gear_blkg2("Rusak");
//            ckl.setTdb_var_standar_tengah1("Tidak Ada");
//            ckl.setTdb_var_standar_tengah2("Rusak");
//            ckl.setTdb_var_standar_samping1("Tidak Ada");
//            ckl.setTdb_var_standar_samping2("Rusak");
//            ckl.setTdb_var_ban_depan1("Tidak Ada");
//            ckl.setTdb_var_ban_depan2("Rusak");
//            ckl.setTdb_var_ban_blkg1("Tidak Ada");
//            ckl.setTdb_var_ban_blkg2("Rusak");
//            ckl.setTdb_var_velg_depan1("Tidak Ada");
//            ckl.setTdb_var_velg_depan2("Rusak");
//            ckl.setTdb_var_velg_blkg1("Tidak Ada");
//            ckl.setTdb_var_velg_blkg2("Rusak");
////          # PERLENGKAPAN #
//            ckl.setTdb_var_kaca_spion1("Tidak Ada");
//            ckl.setTdb_var_kaca_spion2("Rusak");
//            ckl.setTdb_var_speedometer1("Tidak Ada");
//            ckl.setTdb_var_speedometer2("Rusak");
//            ckl.setTdb_var_Grip_stang1("Tidak Ada");
//            ckl.setTdb_var_Grip_stang2("Rusak");
//            ckl.setTdb_var_Rumah_kunci1("Tidak Ada");
//            ckl.setTdb_var_Rumah_kunci2("Rusak");
//            ckl.setTdb_var_Klakson1("Tidak Ada");
//            ckl.setTdb_var_Klakson2("Rusak");
//            ckl.setTdb_var_Behel1("Tidak Ada");
//            ckl.setTdb_var_Behel2("Rusak");
//            ckl.setTdb_var_Tutup_knalpot1("Tidak Ada");
//            ckl.setTdb_var_Tutup_knalpot2("Rusak");
//            ckl.setTdb_var_jok1("Tidak Ada");
//            ckl.setTdb_var_jok2("Rusak");
//            ckl.setTdb_var_Stripping1("Tidak Ada");
//            ckl.setTdb_var_Stripping2("Rusak");
//            ckl.setTdb_var_Tombol_navigasi1("Tidak Ada");
//            ckl.setTdb_var_Tombol_navigasi2("Rusak");
//
//            //=========================================
//
//            ckl.setVdb_var_cover_kepala2(View.INVISIBLE);
//            ckl.setVdb_var_cover_speedo2(View.INVISIBLE);
//            ckl.setVdb_var_cover_depan2(View.INVISIBLE);
//            ckl.setVdb_var_sayap_kanan2(View.INVISIBLE);
//            ckl.setVdb_var_sayap_kiri2(View.INVISIBLE);
//            ckl.setVdb_var_spak_depan2(View.INVISIBLE);
//            ckl.setVdb_var_cover_tengah2(View.INVISIBLE);
//            ckl.setVdb_var_cover_spgkanan2(View.INVISIBLE);
//            ckl.setVdb_var_cover_spgkiri2(View.INVISIBLE);
//            ckl.setVdb_var_rantai_depan2(View.INVISIBLE);
//            ckl.setVdb_var_rantai_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_spak_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_cover_accu2(View.INVISIBLE);
//            ckl.setVdb_var_cover_lampurem2(View.INVISIBLE);
//            ckl.setVdb_var_tangki_bensin2(View.INVISIBLE);
//            ckl.setVdb_var_cover_bawah2(View.INVISIBLE);
//            ckl.setVdb_var_dek_desk2(View.INVISIBLE);
//            ckl.setVdb_var_cover_mesin2(View.INVISIBLE);
//            ckl.setVdb_var_emblem2(View.INVISIBLE);
////          # MESIN #
//            ckl.setVdb_var_blok_mesin2(View.INVISIBLE);
//            ckl.setVdb_var_carburator2(View.INVISIBLE);
//            ckl.setVdb_var_filter_carbu2(View.INVISIBLE);
//            ckl.setVdb_var_busi2(View.INVISIBLE);
//            ckl.setVdb_var_cdi2(View.INVISIBLE);
//            ckl.setVdb_var_accu2(View.INVISIBLE);
//            ckl.setVdb_var_kick_starter2(View.INVISIBLE);
//            ckl.setVdb_var_perseneling2(View.INVISIBLE);
//            ckl.setVdb_var_knalpot2(View.INVISIBLE);
//            ckl.setVdb_var_coil2(View.INVISIBLE);
//            ckl.setVdb_var_blok2(View.INVISIBLE);
//            ckl.setVdb_var_tutupcvt2(View.INVISIBLE);
//            ckl.setVdb_var_radiator2(View.INVISIBLE);
//            ckl.setVdb_var_lampu_depan2(View.INVISIBLE);
//            ckl.setVdb_var_sein_depan2(View.INVISIBLE);
//            ckl.setVdb_var_sein_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_lampu_rem2(View.INVISIBLE);
////          # KAKI-KAKI #
//            ckl.setVdb_var_handlerem_depan2(View.INVISIBLE);
//            ckl.setVdb_var_pedalrem_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_handlerem_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_handle_kopling2(View.INVISIBLE);
//            ckl.setVdb_var_master_rem2(View.INVISIBLE);
//            ckl.setVdb_var_caliper2(View.INVISIBLE);
//            ckl.setVdb_var_cakram2(View.INVISIBLE);
//            ckl.setVdb_var_shock_depan2(View.INVISIBLE);
//            ckl.setVdb_var_shock_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_tromol_depan2(View.INVISIBLE);
//            ckl.setVdb_var_tromol_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_footstep_depan2(View.INVISIBLE);
//            ckl.setVdb_var_footstep_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_swing_arm2(View.INVISIBLE);
//            ckl.setVdb_var_rantai_fanbelt2(View.INVISIBLE);
//            ckl.setVdb_var_gear_depan2(View.INVISIBLE);
//            ckl.setVdb_var_gear_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_standar_tengah2(View.INVISIBLE);
//            ckl.setVdb_var_standar_samping2(View.INVISIBLE);
//            ckl.setVdb_var_ban_depan2(View.INVISIBLE);
//            ckl.setVdb_var_ban_blkg2(View.INVISIBLE);
//            ckl.setVdb_var_velg_depan2(View.INVISIBLE);
//            ckl.setVdb_var_velg_blkg2(View.INVISIBLE);
////          # PERLENGKAPAN #
//            ckl.setVdb_var_kaca_spion2(View.INVISIBLE);
//            ckl.setVdb_var_speedometer2(View.INVISIBLE);
//            ckl.setVdb_var_Grip_stang2(View.INVISIBLE);
//            ckl.setVdb_var_Rumah_kunci2(View.INVISIBLE);
//            ckl.setVdb_var_Klakson2(View.INVISIBLE);
//            ckl.setVdb_var_Behel2(View.INVISIBLE);
//            ckl.setVdb_var_Tutup_knalpot2(View.INVISIBLE);
//            ckl.setVdb_var_jok2(View.INVISIBLE);
//            ckl.setVdb_var_Stripping2(View.INVISIBLE);
//            ckl.setVdb_var_Tombol_navigasi2(View.INVISIBLE);
//
//            realm.commitTransaction();
//
//        }else {
//            //Toast.makeText(DisInputCeklistActivity.this,"else",Toast.LENGTH_SHORT).show();
//            cb_cover_kepala1.setChecked(ceklistMotors.first().getDb_var_cover_kepala1());
//            cb_cover_kepala1.setText(ceklistMotors.first().getTdb_var_cover_kepala1());
//            cb_cover_kepala2.setChecked(ceklistMotors.first().getDb_var_cover_kepala2());
//            cb_cover_kepala2.setText(ceklistMotors.first().getTdb_var_cover_kepala2());
//            cb_cover_kepala2.setVisibility(ceklistMotors.first().getVdb_var_cover_kepala2());
//
//            cb_cover_speedo1.setChecked(ceklistMotors.first().getDb_var_cover_speedo1());
//            cb_cover_speedo1.setText(ceklistMotors.first().getTdb_var_cover_speedo1());
//            cb_cover_speedo2.setChecked(ceklistMotors.first().getDb_var_cover_speedo2());
//            cb_cover_speedo2.setText(ceklistMotors.first().getTdb_var_cover_speedo2());
//            cb_cover_speedo2.setVisibility(ceklistMotors.first().getVdb_var_cover_speedo2());
//
//            cb_cover_depan1.setChecked(ceklistMotors.first().getDb_var_cover_depan1());
//            cb_cover_depan1.setText(ceklistMotors.first().getTdb_var_cover_depan1());
//            cb_cover_depan2.setChecked(ceklistMotors.first().getDb_var_cover_depan2());
//            cb_cover_depan2.setText(ceklistMotors.first().getTdb_var_cover_depan2());
//            cb_cover_depan2.setVisibility(ceklistMotors.first().getVdb_var_cover_depan2());
//
//            cb_sayap_kanan1.setChecked(ceklistMotors.first().getDb_var_sayap_kanan1());
//            cb_sayap_kanan1.setText(ceklistMotors.first().getTdb_var_sayap_kanan1());
//            cb_sayap_kanan2.setChecked(ceklistMotors.first().getDb_var_sayap_kanan2());
//            cb_sayap_kanan2.setText(ceklistMotors.first().getTdb_var_sayap_kanan2());
//            cb_sayap_kanan2.setVisibility(ceklistMotors.first().getVdb_var_sayap_kanan2());
//
//            cb_sayap_kiri1.setChecked(ceklistMotors.first().getDb_var_sayap_kiri1());
//            cb_sayap_kiri1.setText(ceklistMotors.first().getTdb_var_sayap_kiri1());
//            cb_sayap_kiri2.setChecked(ceklistMotors.first().getDb_var_sayap_kiri2());
//            cb_sayap_kiri2.setText(ceklistMotors.first().getTdb_var_sayap_kiri2());
//            cb_sayap_kiri2.setVisibility(ceklistMotors.first().getVdb_var_sayap_kiri2());
//
//            cb_spak_depan1.setChecked(ceklistMotors.first().getDb_var_spak_depan1());
//            cb_spak_depan1.setText(ceklistMotors.first().getTdb_var_spak_depan1());
//            cb_spak_depan2.setChecked(ceklistMotors.first().getDb_var_spak_depan2());
//            cb_spak_depan2.setText(ceklistMotors.first().getTdb_var_spak_depan2());
//            cb_spak_depan2.setVisibility(ceklistMotors.first().getVdb_var_spak_depan2());
//
//            cb_cover_tengah1.setChecked(ceklistMotors.first().getDb_var_cover_tengah1());
//            cb_cover_tengah1.setText(ceklistMotors.first().getTdb_var_cover_tengah1());
//            cb_cover_tengah2.setChecked(ceklistMotors.first().getDb_var_cover_tengah2());
//            cb_cover_tengah2.setText(ceklistMotors.first().getTdb_var_cover_tengah2());
//            cb_cover_tengah2.setVisibility(ceklistMotors.first().getVdb_var_cover_tengah2());
//
//            cb_cover_spgkanan1.setChecked(ceklistMotors.first().getDb_var_cover_spgkanan1());
//            cb_cover_spgkanan1.setText(ceklistMotors.first().getTdb_var_cover_spgkanan1());
//            cb_cover_spgkanan2.setChecked(ceklistMotors.first().getDb_var_cover_spgkanan2());
//            cb_cover_spgkanan2.setText(ceklistMotors.first().getTdb_var_cover_spgkanan2());
//            cb_cover_spgkanan2.setVisibility(ceklistMotors.first().getVdb_var_cover_spgkanan2());
//
//            cb_cover_spgkiri1.setChecked(ceklistMotors.first().getDb_var_cover_spgkiri1());
//            cb_cover_spgkiri1.setText(ceklistMotors.first().getTdb_var_cover_spgkiri1());
//            cb_cover_spgkiri2.setChecked(ceklistMotors.first().getDb_var_cover_spgkiri2());
//            cb_cover_spgkiri2.setText(ceklistMotors.first().getTdb_var_cover_spgkiri2());
//            cb_cover_spgkiri2.setVisibility(ceklistMotors.first().getVdb_var_cover_spgkiri2());
//
//            cb_rantai_depan1.setChecked(ceklistMotors.first().getDb_var_rantai_depan1());
//            cb_rantai_depan1.setText(ceklistMotors.first().getTdb_var_rantai_depan1());
//            cb_rantai_depan2.setChecked(ceklistMotors.first().getDb_var_rantai_depan2());
//            cb_rantai_depan2.setText(ceklistMotors.first().getTdb_var_rantai_depan2());
//            cb_rantai_depan2.setVisibility(ceklistMotors.first().getVdb_var_rantai_depan2());
//
//            cb_rantai_blkg1.setChecked(ceklistMotors.first().getDb_var_rantai_blkg1());
//            cb_rantai_blkg1.setText(ceklistMotors.first().getTdb_var_rantai_blkg1());
//            cb_rantai_blkg2.setChecked(ceklistMotors.first().getDb_var_rantai_blkg2());
//            cb_rantai_blkg2.setText(ceklistMotors.first().getTdb_var_rantai_blkg2());
//            cb_rantai_blkg2.setVisibility(ceklistMotors.first().getVdb_var_rantai_blkg2());
//
//            cb_spak_blkg1.setChecked(ceklistMotors.first().getDb_var_spak_blkg1());
//            cb_spak_blkg1.setText(ceklistMotors.first().getTdb_var_spak_blkg1());
//            cb_spak_blkg2.setChecked(ceklistMotors.first().getDb_var_spak_blkg2());
//            cb_spak_blkg2.setText(ceklistMotors.first().getTdb_var_spak_blkg2());
//            cb_spak_blkg2.setVisibility(ceklistMotors.first().getVdb_var_spak_blkg2());
//
//            cb_cover_accu1.setChecked(ceklistMotors.first().getDb_var_cover_accu1());
//            cb_cover_accu1.setText(ceklistMotors.first().getTdb_var_cover_accu1());
//            cb_cover_accu2.setChecked(ceklistMotors.first().getDb_var_cover_accu2());
//            cb_cover_accu2.setText(ceklistMotors.first().getTdb_var_cover_accu2());
//            cb_cover_accu2.setVisibility(ceklistMotors.first().getVdb_var_cover_accu2());
//
//            cb_cover_lampurem1.setChecked(ceklistMotors.first().getDb_var_cover_lampurem1());
//            cb_cover_lampurem1.setText(ceklistMotors.first().getTdb_var_cover_lampurem1());
//            cb_cover_lampurem2.setChecked(ceklistMotors.first().getDb_var_cover_lampurem2());
//            cb_cover_lampurem2.setText(ceklistMotors.first().getTdb_var_cover_lampurem2());
//            cb_cover_lampurem2.setVisibility(ceklistMotors.first().getVdb_var_cover_lampurem2());
//
//            cb_tangki_bensin1.setChecked(ceklistMotors.first().getDb_var_tangki_bensin1());
//            cb_tangki_bensin1.setText(ceklistMotors.first().getTdb_var_tangki_bensin1());
//            cb_tangki_bensin2.setChecked(ceklistMotors.first().getDb_var_tangki_bensin2());
//            cb_tangki_bensin2.setText(ceklistMotors.first().getTdb_var_tangki_bensin2());
//            cb_tangki_bensin2.setVisibility(ceklistMotors.first().getVdb_var_tangki_bensin2());
//
//            cb_cover_bawah1.setChecked(ceklistMotors.first().getDb_var_cover_bawah1());
//            cb_cover_bawah1.setText(ceklistMotors.first().getTdb_var_cover_bawah1());
//            cb_cover_bawah2.setChecked(ceklistMotors.first().getDb_var_cover_bawah2());
//            cb_cover_bawah2.setText(ceklistMotors.first().getTdb_var_cover_bawah2());
//            cb_cover_bawah2.setVisibility(ceklistMotors.first().getVdb_var_cover_bawah2());
//
//            cb_dek_desk1.setChecked(ceklistMotors.first().getDb_var_dek_desk1());
//            cb_dek_desk1.setText(ceklistMotors.first().getTdb_var_dek_desk1());
//            cb_dek_desk2.setChecked(ceklistMotors.first().getDb_var_dek_desk2());
//            cb_dek_desk2.setText(ceklistMotors.first().getTdb_var_dek_desk2());
//            cb_dek_desk2.setVisibility(ceklistMotors.first().getVdb_var_dek_desk2());
//
//            cb_cover_mesin1.setChecked(ceklistMotors.first().getDb_var_cover_mesin1());
//            cb_cover_mesin1.setText(ceklistMotors.first().getTdb_var_cover_mesin1());
//            cb_cover_mesin2.setChecked(ceklistMotors.first().getDb_var_cover_mesin2());
//            cb_cover_mesin2.setText(ceklistMotors.first().getTdb_var_cover_mesin2());
//            cb_cover_mesin2.setVisibility(ceklistMotors.first().getVdb_var_cover_mesin2());
//
//            cb_emblem1.setChecked(ceklistMotors.first().getDb_var_emblem1());
//            cb_emblem1.setText(ceklistMotors.first().getTdb_var_emblem1());
//            cb_emblem2.setChecked(ceklistMotors.first().getDb_var_emblem2());
//            cb_emblem2.setText(ceklistMotors.first().getTdb_var_emblem2());
//            cb_emblem2.setVisibility(ceklistMotors.first().getVdb_var_emblem2());
////          # MESIN #
//            cb_blok_mesin1.setChecked(ceklistMotors.first().getDb_var_blok_mesin1());
//            cb_blok_mesin1.setText(ceklistMotors.first().getTdb_var_blok_mesin1());
//            cb_blok_mesin2.setChecked(ceklistMotors.first().getDb_var_blok_mesin2());
//            cb_blok_mesin2.setText(ceklistMotors.first().getTdb_var_blok_mesin2());
//            cb_blok_mesin2.setVisibility(ceklistMotors.first().getVdb_var_blok_mesin2());
//
//            cb_carburator1.setChecked(ceklistMotors.first().getDb_var_carburator1());
//            cb_carburator1.setText(ceklistMotors.first().getTdb_var_carburator1());
//            cb_carburator2.setChecked(ceklistMotors.first().getDb_var_carburator2());
//            cb_carburator2.setText(ceklistMotors.first().getTdb_var_carburator2());
//            cb_carburator2.setVisibility(ceklistMotors.first().getVdb_var_carburator2());
//
//            cb_filter_carbu1.setChecked(ceklistMotors.first().getDb_var_filter_carbu1());
//            cb_filter_carbu1.setText(ceklistMotors.first().getTdb_var_filter_carbu1());
//            cb_filter_carbu2.setChecked(ceklistMotors.first().getDb_var_filter_carbu2());
//            cb_filter_carbu2.setText(ceklistMotors.first().getTdb_var_filter_carbu2());
//            cb_filter_carbu2.setVisibility(ceklistMotors.first().getVdb_var_filter_carbu2());
//
//            cb_busi1.setChecked(ceklistMotors.first().getDb_var_busi1());
//            cb_busi1.setText(ceklistMotors.first().getTdb_var_busi1());
//            cb_busi2.setChecked(ceklistMotors.first().getDb_var_busi2());
//            cb_busi2.setText(ceklistMotors.first().getTdb_var_busi2());
//            cb_busi2.setVisibility(ceklistMotors.first().getVdb_var_busi2());
//
//            cb_cdi1.setChecked(ceklistMotors.first().getDb_var_cdi1());
//            cb_cdi1.setText(ceklistMotors.first().getTdb_var_cdi1());
//            cb_cdi2.setChecked(ceklistMotors.first().getDb_var_cdi2());
//            cb_cdi2.setText(ceklistMotors.first().getTdb_var_cdi2());
//            cb_cdi2.setVisibility(ceklistMotors.first().getVdb_var_cdi2());
//
//            cb_accu1.setChecked(ceklistMotors.first().getDb_var_accu1());
//            cb_accu1.setText(ceklistMotors.first().getTdb_var_accu1());
//            cb_accu2.setChecked(ceklistMotors.first().getDb_var_accu2());
//            cb_accu2.setText(ceklistMotors.first().getTdb_var_accu2());
//            cb_accu2.setVisibility(ceklistMotors.first().getVdb_var_accu2());
//
//            cb_kick_starter1.setChecked(ceklistMotors.first().getDb_var_kick_starter1());
//            cb_kick_starter1.setText(ceklistMotors.first().getTdb_var_kick_starter1());
//            cb_kick_starter2.setChecked(ceklistMotors.first().getDb_var_kick_starter2());
//            cb_kick_starter2.setText(ceklistMotors.first().getTdb_var_kick_starter2());
//            cb_kick_starter2.setVisibility(ceklistMotors.first().getVdb_var_kick_starter2());
//
//            cb_perseneling1.setChecked(ceklistMotors.first().getDb_var_perseneling1());
//            cb_perseneling1.setText(ceklistMotors.first().getTdb_var_perseneling1());
//            cb_perseneling2.setChecked(ceklistMotors.first().getDb_var_perseneling2());
//            cb_perseneling2.setText(ceklistMotors.first().getTdb_var_perseneling2());
//            cb_perseneling2.setVisibility(ceklistMotors.first().getVdb_var_perseneling2());
//
//            cb_knalpot1.setChecked(ceklistMotors.first().getDb_var_knalpot1());
//            cb_knalpot1.setText(ceklistMotors.first().getTdb_var_knalpot1());
//            cb_knalpot2.setChecked(ceklistMotors.first().getDb_var_knalpot2());
//            cb_knalpot2.setText(ceklistMotors.first().getTdb_var_knalpot2());
//            cb_knalpot2.setVisibility(ceklistMotors.first().getVdb_var_knalpot2());
//
//            cb_coil1.setChecked(ceklistMotors.first().getDb_var_coil1());
//            cb_coil1.setText(ceklistMotors.first().getTdb_var_coil1());
//            cb_coil2.setChecked(ceklistMotors.first().getDb_var_coil2());
//            cb_coil2.setText(ceklistMotors.first().getTdb_var_coil2());
//            cb_coil2.setVisibility(ceklistMotors.first().getVdb_var_coil2());
//
//            cb_blok1.setChecked(ceklistMotors.first().getDb_var_blok1());
//            cb_blok1.setText(ceklistMotors.first().getTdb_var_blok1());
//            cb_blok2.setChecked(ceklistMotors.first().getDb_var_blok2());
//            cb_blok2.setText(ceklistMotors.first().getTdb_var_blok2());
//            cb_blok2.setVisibility(ceklistMotors.first().getVdb_var_blok2());
//
//            cb_tutupcvt1.setChecked(ceklistMotors.first().getDb_var_tutupcvt1());
//            cb_tutupcvt1.setText(ceklistMotors.first().getTdb_var_tutupcvt1());
//            cb_tutupcvt2.setChecked(ceklistMotors.first().getDb_var_tutupcvt2());
//            cb_tutupcvt2.setText(ceklistMotors.first().getTdb_var_tutupcvt2());
//            cb_tutupcvt2.setVisibility(ceklistMotors.first().getVdb_var_tutupcvt2());
//
//            cb_radiator1.setChecked(ceklistMotors.first().getDb_var_radiator1());
//            cb_radiator1.setText(ceklistMotors.first().getTdb_var_radiator1());
//            cb_radiator2.setChecked(ceklistMotors.first().getDb_var_radiator2());
//            cb_radiator2.setText(ceklistMotors.first().getTdb_var_radiator2());
//            cb_radiator2.setVisibility(ceklistMotors.first().getVdb_var_radiator2());
//
//            cb_lampu_depan1.setChecked(ceklistMotors.first().getDb_var_lampu_depan1());
//            cb_lampu_depan1.setText(ceklistMotors.first().getTdb_var_lampu_depan1());
//            cb_lampu_depan2.setChecked(ceklistMotors.first().getDb_var_lampu_depan2());
//            cb_lampu_depan2.setText(ceklistMotors.first().getTdb_var_lampu_depan2());
//            cb_lampu_depan2.setVisibility(ceklistMotors.first().getVdb_var_lampu_depan2());
//
//            cb_sein_depan1.setChecked(ceklistMotors.first().getDb_var_sein_depan1());
//            cb_sein_depan1.setText(ceklistMotors.first().getTdb_var_sein_depan1());
//            cb_sein_depan2.setChecked(ceklistMotors.first().getDb_var_sein_depan2());
//            cb_sein_depan2.setText(ceklistMotors.first().getTdb_var_sein_depan2());
//            cb_sein_depan2.setVisibility(ceklistMotors.first().getVdb_var_sein_depan2());
//
//            cb_sein_blkg1.setChecked(ceklistMotors.first().getDb_var_sein_blkg1());
//            cb_sein_blkg1.setText(ceklistMotors.first().getTdb_var_sein_blkg1());
//            cb_sein_blkg2.setChecked(ceklistMotors.first().getDb_var_sein_blkg2());
//            cb_sein_blkg2.setText(ceklistMotors.first().getTdb_var_sein_blkg2());
//            cb_sein_blkg2.setVisibility(ceklistMotors.first().getVdb_var_sein_blkg2());
//
//            cb_lampu_rem1.setChecked(ceklistMotors.first().getDb_var_lampu_rem1());
//            cb_lampu_rem1.setText(ceklistMotors.first().getTdb_var_lampu_rem1());
//            cb_lampu_rem2.setChecked(ceklistMotors.first().getDb_var_lampu_rem2());
//            cb_lampu_rem2.setText(ceklistMotors.first().getTdb_var_lampu_rem2());
//            cb_lampu_rem2.setVisibility(ceklistMotors.first().getVdb_var_lampu_rem2());
////          # KAKI-KAKI #
//            cb_handlerem_depan1.setChecked(ceklistMotors.first().getDb_var_handlerem_depan1());
//            cb_handlerem_depan1.setText(ceklistMotors.first().getTdb_var_handlerem_depan1());
//            cb_handlerem_depan2.setChecked(ceklistMotors.first().getDb_var_handlerem_depan2());
//            cb_handlerem_depan2.setText(ceklistMotors.first().getTdb_var_handlerem_depan2());
//            cb_handlerem_depan2.setVisibility(ceklistMotors.first().getVdb_var_handlerem_depan2());
//
//            cb_pedalrem_blkg1.setChecked(ceklistMotors.first().getDb_var_pedalrem_blkg1());
//            cb_pedalrem_blkg1.setText(ceklistMotors.first().getTdb_var_pedalrem_blkg1());
//            cb_pedalrem_blkg2.setChecked(ceklistMotors.first().getDb_var_pedalrem_blkg2());
//            cb_pedalrem_blkg2.setText(ceklistMotors.first().getTdb_var_pedalrem_blkg2());
//            cb_pedalrem_blkg2.setVisibility(ceklistMotors.first().getVdb_var_pedalrem_blkg2());
//
//            cb_handlerem_blkg1.setChecked(ceklistMotors.first().getDb_var_handlerem_blkg1());
//            cb_handlerem_blkg1.setText(ceklistMotors.first().getTdb_var_handlerem_blkg1());
//            cb_handlerem_blkg2.setChecked(ceklistMotors.first().getDb_var_handlerem_blkg2());
//            cb_handlerem_blkg2.setText(ceklistMotors.first().getTdb_var_handlerem_blkg2());
//            cb_handlerem_blkg2.setVisibility(ceklistMotors.first().getVdb_var_handlerem_blkg2());
//
//            cb_handle_kopling1.setChecked(ceklistMotors.first().getDb_var_handle_kopling1());
//            cb_handle_kopling1.setText(ceklistMotors.first().getTdb_var_handle_kopling1());
//            cb_handle_kopling2.setChecked(ceklistMotors.first().getDb_var_handle_kopling2());
//            cb_handle_kopling2.setText(ceklistMotors.first().getTdb_var_handle_kopling2());
//            cb_handle_kopling2.setVisibility(ceklistMotors.first().getVdb_var_handle_kopling2());
//
//            cb_master_rem1.setChecked(ceklistMotors.first().getDb_var_master_rem1());
//            cb_master_rem1.setText(ceklistMotors.first().getTdb_var_master_rem1());
//            cb_master_rem2.setChecked(ceklistMotors.first().getDb_var_master_rem2());
//            cb_master_rem2.setText(ceklistMotors.first().getTdb_var_master_rem2());
//            cb_master_rem2.setVisibility(ceklistMotors.first().getVdb_var_master_rem2());
//
//            cb_caliper1.setChecked(ceklistMotors.first().getDb_var_caliper1());
//            cb_caliper1.setText(ceklistMotors.first().getTdb_var_caliper1());
//            cb_caliper2.setChecked(ceklistMotors.first().getDb_var_caliper2());
//            cb_caliper2.setText(ceklistMotors.first().getTdb_var_caliper2());
//            cb_caliper2.setVisibility(ceklistMotors.first().getVdb_var_caliper2());
//
//            cb_cakram1.setChecked(ceklistMotors.first().getDb_var_cakram1());
//            cb_cakram1.setText(ceklistMotors.first().getTdb_var_cakram1());
//            cb_cakram2.setChecked(ceklistMotors.first().getDb_var_cakram2());
//            cb_cakram2.setText(ceklistMotors.first().getTdb_var_cakram2());
//            cb_cakram2.setVisibility(ceklistMotors.first().getVdb_var_cakram2());
//
//            cb_shock_depan1.setChecked(ceklistMotors.first().getDb_var_shock_depan1());
//            cb_shock_depan1.setText(ceklistMotors.first().getTdb_var_shock_depan1());
//            cb_shock_depan2.setChecked(ceklistMotors.first().getDb_var_shock_depan2());
//            cb_shock_depan2.setText(ceklistMotors.first().getTdb_var_shock_depan2());
//            cb_shock_depan2.setVisibility(ceklistMotors.first().getVdb_var_shock_depan2());
//
//            cb_shock_blkg1.setChecked(ceklistMotors.first().getDb_var_shock_blkg1());
//            cb_shock_blkg1.setText(ceklistMotors.first().getTdb_var_shock_blkg1());
//            cb_shock_blkg2.setChecked(ceklistMotors.first().getDb_var_shock_blkg2());
//            cb_shock_blkg2.setText(ceklistMotors.first().getTdb_var_shock_blkg2());
//            cb_shock_blkg2.setVisibility(ceklistMotors.first().getVdb_var_shock_blkg2());
//
//            cb_tromol_depan1.setChecked(ceklistMotors.first().getDb_var_tromol_depan1());
//            cb_tromol_depan1.setText(ceklistMotors.first().getTdb_var_tromol_depan1());
//            cb_tromol_depan2.setChecked(ceklistMotors.first().getDb_var_tromol_depan2());
//            cb_tromol_depan2.setText(ceklistMotors.first().getTdb_var_tromol_depan2());
//            cb_tromol_depan2.setVisibility(ceklistMotors.first().getVdb_var_tromol_depan2());
//
//            cb_tromol_blkg1.setChecked(ceklistMotors.first().getDb_var_tromol_blkg1());
//            cb_tromol_blkg1.setText(ceklistMotors.first().getTdb_var_tromol_blkg1());
//            cb_tromol_blkg2.setChecked(ceklistMotors.first().getDb_var_tromol_blkg2());
//            cb_tromol_blkg2.setText(ceklistMotors.first().getTdb_var_tromol_blkg2());
//            cb_tromol_blkg2.setVisibility(ceklistMotors.first().getVdb_var_tromol_blkg2());
//
//            cb_footstep_depan1.setChecked(ceklistMotors.first().getDb_var_footstep_depan1());
//            cb_footstep_depan1.setText(ceklistMotors.first().getTdb_var_footstep_depan1());
//            cb_footstep_depan2.setChecked(ceklistMotors.first().getDb_var_footstep_depan2());
//            cb_footstep_depan2.setText(ceklistMotors.first().getTdb_var_footstep_depan2());
//            cb_footstep_depan2.setVisibility(ceklistMotors.first().getVdb_var_footstep_depan2());
//
//            cb_footstep_blkg1.setChecked(ceklistMotors.first().getDb_var_footstep_blkg1());
//            cb_footstep_blkg1.setText(ceklistMotors.first().getTdb_var_footstep_blkg1());
//            cb_footstep_blkg2.setChecked(ceklistMotors.first().getDb_var_footstep_blkg2());
//            cb_footstep_blkg2.setText(ceklistMotors.first().getTdb_var_footstep_blkg2());
//            cb_footstep_blkg2.setVisibility(ceklistMotors.first().getVdb_var_footstep_blkg2());
//
//            cb_swing_arm1.setChecked(ceklistMotors.first().getDb_var_swing_arm1());
//            cb_swing_arm1.setText(ceklistMotors.first().getTdb_var_swing_arm1());
//            cb_swing_arm2.setChecked(ceklistMotors.first().getDb_var_swing_arm2());
//            cb_swing_arm2.setText(ceklistMotors.first().getTdb_var_swing_arm2());
//            cb_swing_arm2.setVisibility(ceklistMotors.first().getVdb_var_swing_arm2());
//
//            cb_rantai_fanbelt1.setChecked(ceklistMotors.first().getDb_var_rantai_fanbelt1());
//            cb_rantai_fanbelt1.setText(ceklistMotors.first().getTdb_var_rantai_fanbelt1());
//            cb_rantai_fanbelt2.setChecked(ceklistMotors.first().getDb_var_rantai_fanbelt2());
//            cb_rantai_fanbelt2.setText(ceklistMotors.first().getTdb_var_rantai_fanbelt2());
//            cb_rantai_fanbelt2.setVisibility(ceklistMotors.first().getVdb_var_rantai_fanbelt2());
//
//            cb_gear_depan1.setChecked(ceklistMotors.first().getDb_var_gear_depan1());
//            cb_gear_depan1.setText(ceklistMotors.first().getTdb_var_gear_depan1());
//            cb_gear_depan2.setChecked(ceklistMotors.first().getDb_var_gear_depan2());
//            cb_gear_depan2.setText(ceklistMotors.first().getTdb_var_gear_depan2());
//            cb_gear_depan2.setVisibility(ceklistMotors.first().getVdb_var_gear_depan2());
//
//            cb_gear_blkg1.setChecked(ceklistMotors.first().getDb_var_gear_blkg1());
//            cb_gear_blkg1.setText(ceklistMotors.first().getTdb_var_gear_blkg1());
//            cb_gear_blkg2.setChecked(ceklistMotors.first().getDb_var_gear_blkg2());
//            cb_gear_blkg2.setText(ceklistMotors.first().getTdb_var_gear_blkg2());
//            cb_gear_blkg2.setVisibility(ceklistMotors.first().getVdb_var_gear_blkg2());
//
//            cb_standar_tengah1.setChecked(ceklistMotors.first().getDb_var_standar_tengah1());
//            cb_standar_tengah1.setText(ceklistMotors.first().getTdb_var_standar_tengah1());
//            cb_standar_tengah2.setChecked(ceklistMotors.first().getDb_var_standar_tengah2());
//            cb_standar_tengah2.setText(ceklistMotors.first().getTdb_var_standar_tengah2());
//            cb_standar_tengah2.setVisibility(ceklistMotors.first().getVdb_var_standar_tengah2());
//
//            cb_standar_samping1.setChecked(ceklistMotors.first().getDb_var_standar_samping1());
//            cb_standar_samping1.setText(ceklistMotors.first().getTdb_var_standar_samping1());
//            cb_standar_samping2.setChecked(ceklistMotors.first().getDb_var_standar_samping2());
//            cb_standar_samping2.setText(ceklistMotors.first().getTdb_var_standar_samping2());
//            cb_standar_samping2.setVisibility(ceklistMotors.first().getVdb_var_standar_samping2());
//
//            cb_ban_depan1.setChecked(ceklistMotors.first().getDb_var_ban_depan1());
//            cb_ban_depan1.setText(ceklistMotors.first().getTdb_var_ban_depan1());
//            cb_ban_depan2.setChecked(ceklistMotors.first().getDb_var_ban_depan2());
//            cb_ban_depan2.setText(ceklistMotors.first().getTdb_var_ban_depan2());
//            cb_ban_depan2.setVisibility(ceklistMotors.first().getVdb_var_ban_depan2());
//
//            cb_ban_blkg1.setChecked(ceklistMotors.first().getDb_var_ban_blkg1());
//            cb_ban_blkg1.setText(ceklistMotors.first().getTdb_var_ban_blkg1());
//            cb_ban_blkg2.setChecked(ceklistMotors.first().getDb_var_ban_blkg2());
//            cb_ban_blkg2.setText(ceklistMotors.first().getTdb_var_ban_blkg2());
//            cb_ban_blkg2.setVisibility(ceklistMotors.first().getVdb_var_ban_blkg2());
//
//            cb_velg_depan1.setChecked(ceklistMotors.first().getDb_var_velg_depan1());
//            cb_velg_depan1.setText(ceklistMotors.first().getTdb_var_velg_depan1());
//            cb_velg_depan2.setChecked(ceklistMotors.first().getDb_var_velg_depan2());
//            cb_velg_depan2.setText(ceklistMotors.first().getTdb_var_velg_depan2());
//            cb_velg_depan2.setVisibility(ceklistMotors.first().getVdb_var_velg_depan2());
//
//            cb_velg_blkg1.setChecked(ceklistMotors.first().getDb_var_velg_blkg1());
//            cb_velg_blkg1.setText(ceklistMotors.first().getTdb_var_velg_blkg1());
//            cb_velg_blkg2.setChecked(ceklistMotors.first().getDb_var_velg_blkg2());
//            cb_velg_blkg2.setText(ceklistMotors.first().getTdb_var_velg_blkg2());
//            cb_velg_blkg2.setVisibility(ceklistMotors.first().getVdb_var_velg_blkg2());
////          # PERLENGKAPAN #
//            cb_kaca_spion1.setChecked(ceklistMotors.first().getDb_var_kaca_spion1());
//            cb_kaca_spion1.setText(ceklistMotors.first().getTdb_var_kaca_spion1());
//            cb_kaca_spion2.setChecked(ceklistMotors.first().getDb_var_kaca_spion2());
//            cb_kaca_spion2.setText(ceklistMotors.first().getTdb_var_kaca_spion2());
//            cb_kaca_spion2.setVisibility(ceklistMotors.first().getVdb_var_kaca_spion2());
//
//            cb_speedometer1.setChecked(ceklistMotors.first().getDb_var_speedometer1());
//            cb_speedometer1.setText(ceklistMotors.first().getTdb_var_speedometer1());
//            cb_speedometer2.setChecked(ceklistMotors.first().getDb_var_speedometer2());
//            cb_speedometer2.setText(ceklistMotors.first().getTdb_var_speedometer2());
//            cb_speedometer2.setVisibility(ceklistMotors.first().getVdb_var_speedometer2());
//
//            cb_Grip_stang1.setChecked(ceklistMotors.first().getDb_var_Grip_stang1());
//            cb_Grip_stang1.setText(ceklistMotors.first().getTdb_var_Grip_stang1());
//            cb_Grip_stang2.setChecked(ceklistMotors.first().getDb_var_Grip_stang2());
//            cb_Grip_stang2.setText(ceklistMotors.first().getTdb_var_Grip_stang2());
//            cb_Grip_stang2.setVisibility(ceklistMotors.first().getVdb_var_Grip_stang2());
//
//            cb_Rumah_kunci1.setChecked(ceklistMotors.first().getDb_var_Rumah_kunci1());
//            cb_Rumah_kunci1.setText(ceklistMotors.first().getTdb_var_Rumah_kunci1());
//            cb_Rumah_kunci2.setChecked(ceklistMotors.first().getDb_var_Rumah_kunci2());
//            cb_Rumah_kunci2.setText(ceklistMotors.first().getTdb_var_Rumah_kunci2());
//            cb_Rumah_kunci2.setVisibility(ceklistMotors.first().getVdb_var_Rumah_kunci2());
//
//            cb_Klakson1.setChecked(ceklistMotors.first().getDb_var_Klakson1());
//            cb_Klakson1.setText(ceklistMotors.first().getTdb_var_Klakson1());
//            cb_Klakson2.setChecked(ceklistMotors.first().getDb_var_Klakson2());
//            cb_Klakson2.setText(ceklistMotors.first().getTdb_var_Klakson2());
//            cb_Klakson2.setVisibility(ceklistMotors.first().getVdb_var_Klakson2());
//
//            cb_Behel1.setChecked(ceklistMotors.first().getDb_var_Behel1());
//            cb_Behel1.setText(ceklistMotors.first().getTdb_var_Behel1());
//            cb_Behel2.setChecked(ceklistMotors.first().getDb_var_Behel2());
//            cb_Behel2.setText(ceklistMotors.first().getTdb_var_Behel2());
//            cb_Behel2.setVisibility(ceklistMotors.first().getVdb_var_Behel2());
//
//            cb_Tutup_knalpot1.setChecked(ceklistMotors.first().getDb_var_Tutup_knalpot1());
//            cb_Tutup_knalpot1.setText(ceklistMotors.first().getTdb_var_Tutup_knalpot1());
//            cb_Tutup_knalpot2.setChecked(ceklistMotors.first().getDb_var_Tutup_knalpot2());
//            cb_Tutup_knalpot2.setText(ceklistMotors.first().getTdb_var_Tutup_knalpot2());
//            cb_Tutup_knalpot2.setVisibility(ceklistMotors.first().getVdb_var_Tutup_knalpot2());
//
//            cb_jok1.setChecked(ceklistMotors.first().getDb_var_jok1());
//            cb_jok1.setText(ceklistMotors.first().getTdb_var_jok1());
//            cb_jok2.setChecked(ceklistMotors.first().getDb_var_jok2());
//            cb_jok2.setText(ceklistMotors.first().getTdb_var_jok2());
//            cb_jok2.setVisibility(ceklistMotors.first().getVdb_var_jok2());
//
//            cb_Stripping1.setChecked(ceklistMotors.first().getDb_var_Stripping1());
//            cb_Stripping1.setText(ceklistMotors.first().getTdb_var_Stripping1());
//            cb_Stripping2.setChecked(ceklistMotors.first().getDb_var_Stripping2());
//            cb_Stripping2.setText(ceklistMotors.first().getTdb_var_Stripping2());
//            cb_Stripping2.setVisibility(ceklistMotors.first().getVdb_var_Stripping2());
//
//            cb_Tombol_navigasi1.setChecked(ceklistMotors.first().getDb_var_Tombol_navigasi1());
//            cb_Tombol_navigasi1.setText(ceklistMotors.first().getTdb_var_Tombol_navigasi1());
//            cb_Tombol_navigasi2.setChecked(ceklistMotors.first().getDb_var_Tombol_navigasi2());
//            cb_Tombol_navigasi2.setText(ceklistMotors.first().getTdb_var_Tombol_navigasi2());
//            cb_Tombol_navigasi2.setVisibility(ceklistMotors.first().getVdb_var_Tombol_navigasi2());
//
//        }

//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, kondisi);

        btnNextdispatcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                realm.beginTransaction();
//                RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                upd.load();
//
//
//                realm.commitTransaction();
                sendCeklist();
            }
        });

//        cb_cover_kepala1.setAdapter(adapter);

        cb_cover_kepala1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_kepala1.isChecked()){
                    cb_cover_kepala1.setText("Ada");
                    cb_cover_kepala2.setVisibility(View.VISIBLE);
                    cb_cover_kepala2.setText("Rusak");
                    cb_cover_kepala2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_kepala1(true);
//                    upd.first().setTdb_var_cover_kepala1("Ada");
//                    upd.first().setVdb_var_cover_kepala2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_kepala2("Rusak");
//                    realm.commitTransaction();

                }else {
                    cb_cover_kepala2.setVisibility(View.INVISIBLE);
                    cb_cover_kepala1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_kepala1(false);
//                    upd.first().setTdb_var_cover_kepala1("Tidak Ada");
//                    upd.first().setDb_var_cover_kepala2(false);
//                    upd.first().setTdb_var_cover_kepala2("Rusak");
//                    upd.first().setVdb_var_cover_kepala2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_kepala2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_kepala2.isChecked()){
                    cb_cover_kepala2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_kepala2(true);
//                    upd.first().setTdb_var_cover_kepala2("Baik");
//                    upd.first().setVdb_var_cover_kepala2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_kepala2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_kepala2(false);
//                    upd.first().setTdb_var_cover_kepala2("Rusak");
//                    upd.first().setVdb_var_cover_kepala2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_speedo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_speedo1.isChecked()){
                    cb_cover_speedo1.setText("Ada");
                    cb_cover_speedo2.setVisibility(View.VISIBLE);
                    cb_cover_speedo2.setText("Rusak");
                    cb_cover_speedo2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_speedo1(true);
//                    upd.first().setTdb_var_cover_speedo1("Ada");
//                    upd.first().setVdb_var_cover_speedo2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_speedo2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_speedo2.setVisibility(View.INVISIBLE);
                    cb_cover_speedo1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_speedo1(false);
//                    upd.first().setTdb_var_cover_speedo1("Tidak Ada");
//                    upd.first().setDb_var_cover_speedo2(false);
//                    upd.first().setTdb_var_cover_speedo2("Rusak");
//                    upd.first().setVdb_var_cover_speedo2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_speedo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_speedo2.isChecked()){
                    cb_cover_speedo2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_speedo2(true);
//                    upd.first().setTdb_var_cover_speedo2("Baik");
//                    upd.first().setVdb_var_cover_speedo2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_speedo2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_speedo2(false);
//                    upd.first().setTdb_var_cover_speedo2("Rusak");
//                    upd.first().setVdb_var_cover_speedo2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_depan1.isChecked()){
                    cb_cover_depan1.setText("Ada");
                    cb_cover_depan2.setVisibility(View.VISIBLE);
                    cb_cover_depan2.setText("Rusak");
                    cb_cover_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_depan1(true);
//                    upd.first().setTdb_var_cover_depan1("Ada");
//                    upd.first().setVdb_var_cover_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_depan2.setVisibility(View.INVISIBLE);
                    cb_cover_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_depan1(false);
//                    upd.first().setTdb_var_cover_depan1("Tidak Ada");
//                    upd.first().setDb_var_cover_depan2(false);
//                    upd.first().setTdb_var_cover_depan2("Rusak");
//                    upd.first().setVdb_var_cover_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_depan2.isChecked()){
                    cb_cover_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_depan2(true);
//                    upd.first().setTdb_var_cover_depan2("Baik");
//                    upd.first().setVdb_var_cover_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_depan2(false);
//                    upd.first().setTdb_var_cover_depan2("Rusak");
//                    upd.first().setVdb_var_cover_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_sayap_kanan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_sayap_kanan1.isChecked()){
                    cb_sayap_kanan1.setText("Ada");
                    cb_sayap_kanan2.setVisibility(View.VISIBLE);
                    cb_sayap_kanan2.setText("Rusak");
                    cb_sayap_kanan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sayap_kanan1(true);
//                    upd.first().setTdb_var_sayap_kanan1("Ada");
//                    upd.first().setVdb_var_sayap_kanan2(View.VISIBLE);
//                    upd.first().setTdb_var_sayap_kanan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_sayap_kanan2.setVisibility(View.INVISIBLE);
                    cb_sayap_kanan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sayap_kanan1(false);
//                    upd.first().setTdb_var_sayap_kanan1("Tidak Ada");
//                    upd.first().setDb_var_sayap_kanan2(false);
//                    upd.first().setTdb_var_sayap_kanan2("Rusak");
//                    upd.first().setVdb_var_sayap_kanan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_sayap_kanan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_sayap_kanan2.isChecked()){
                    cb_sayap_kanan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sayap_kanan2(true);
//                    upd.first().setTdb_var_sayap_kanan2("Baik");
//                    upd.first().setVdb_var_sayap_kanan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_sayap_kanan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sayap_kanan2(false);
//                    upd.first().setTdb_var_sayap_kanan2("Rusak");
//                    upd.first().setVdb_var_sayap_kanan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_sayap_kiri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_sayap_kiri1.isChecked()){
                    cb_sayap_kiri1.setText("Ada");
                    cb_sayap_kiri2.setVisibility(View.VISIBLE);
                    cb_sayap_kiri2.setText("Rusak");
                    cb_sayap_kiri2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sayap_kiri1(true);
//                    upd.first().setTdb_var_sayap_kiri1("Ada");
//                    upd.first().setVdb_var_sayap_kiri2(View.VISIBLE);
//                    upd.first().setTdb_var_sayap_kiri2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_sayap_kiri2.setVisibility(View.INVISIBLE);
                    cb_sayap_kiri1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sayap_kiri1(false);
//                    upd.first().setTdb_var_sayap_kiri1("Tidak Ada");
//                    upd.first().setDb_var_sayap_kiri2(false);
//                    upd.first().setTdb_var_sayap_kiri2("Rusak");
//                    upd.first().setVdb_var_sayap_kiri2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_sayap_kiri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_sayap_kiri2.isChecked()){
                    cb_sayap_kiri2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sayap_kiri2(true);
//                    upd.first().setTdb_var_sayap_kiri2("Baik");
//                    upd.first().setVdb_var_sayap_kiri2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_sayap_kiri2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sayap_kiri2(false);
//                    upd.first().setTdb_var_sayap_kiri2("Rusak");
//                    upd.first().setVdb_var_sayap_kiri2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_spak_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_spak_depan1.isChecked()){
                    cb_spak_depan1.setText("Ada");
                    cb_spak_depan2.setVisibility(View.VISIBLE);
                    cb_spak_depan2.setText("Rusak");
                    cb_spak_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_spak_depan1(true);
//                    upd.first().setTdb_var_spak_depan1("Ada");
//                    upd.first().setVdb_var_spak_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_spak_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_spak_depan2.setVisibility(View.INVISIBLE);
                    cb_spak_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_spak_depan1(false);
//                    upd.first().setTdb_var_spak_depan1("Tidak Ada");
//                    upd.first().setDb_var_spak_depan2(false);
//                    upd.first().setTdb_var_spak_depan2("Rusak");
//                    upd.first().setVdb_var_spak_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_spak_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_spak_depan2.isChecked()){
                    cb_spak_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_spak_depan2(true);
//                    upd.first().setTdb_var_spak_depan2("Baik");
//                    upd.first().setVdb_var_spak_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_spak_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_spak_depan2(false);
//                    upd.first().setTdb_var_spak_depan2("Rusak");
//                    upd.first().setVdb_var_spak_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cb_cover_tengah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_tengah1.isChecked()){
                    cb_cover_tengah1.setText("Ada");
                    cb_cover_tengah2.setVisibility(View.VISIBLE);
                    cb_cover_tengah2.setText("Rusak");
                    cb_cover_tengah2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_tengah1(true);
//                    upd.first().setTdb_var_cover_tengah1("Ada");
//                    upd.first().setVdb_var_cover_tengah2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_tengah2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_tengah2.setVisibility(View.INVISIBLE);
                    cb_cover_tengah1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_tengah1(false);
//                    upd.first().setTdb_var_cover_tengah1("Tidak Ada");
//                    upd.first().setDb_var_cover_tengah2(false);
//                    upd.first().setTdb_var_cover_tengah2("Rusak");
//                    upd.first().setVdb_var_cover_tengah2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_tengah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_tengah2.isChecked()){
                    cb_cover_tengah2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_tengah2(true);
//                    upd.first().setTdb_var_cover_tengah2("Baik");
//                    upd.first().setVdb_var_cover_tengah2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_tengah2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_tengah2(false);
//                    upd.first().setTdb_var_cover_tengah2("Rusak");
//                    upd.first().setVdb_var_cover_tengah2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_spgkanan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_spgkanan1.isChecked()){
                    cb_cover_spgkanan1.setText("Ada");
                    cb_cover_spgkanan2.setVisibility(View.VISIBLE);
                    cb_cover_spgkanan2.setText("Rusak");
                    cb_cover_spgkanan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_spgkanan1(true);
//                    upd.first().setTdb_var_cover_spgkanan1("Ada");
//                    upd.first().setVdb_var_cover_spgkanan2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_spgkanan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_spgkanan2.setVisibility(View.INVISIBLE);
                    cb_cover_spgkanan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_spgkanan1(false);
//                    upd.first().setTdb_var_cover_spgkanan1("Tidak Ada");
//                    upd.first().setDb_var_cover_spgkanan2(false);
//                    upd.first().setTdb_var_cover_spgkanan2("Rusak");
//                    upd.first().setVdb_var_cover_spgkanan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_spgkanan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_spgkanan2.isChecked()){
                    cb_cover_spgkanan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_spgkanan2(true);
//                    upd.first().setTdb_var_cover_spgkanan2("Baik");
//                    upd.first().setVdb_var_cover_spgkanan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_spgkanan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_spgkanan2(false);
//                    upd.first().setTdb_var_cover_spgkanan2("Rusak");
//                    upd.first().setVdb_var_cover_spgkanan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_spgkiri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_spgkiri1.isChecked()){
                    cb_cover_spgkiri1.setText("Ada");
                    cb_cover_spgkiri2.setVisibility(View.VISIBLE);
                    cb_cover_spgkiri2.setText("Rusak");
                    cb_cover_spgkiri2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_spgkiri1(true);
//                    upd.first().setTdb_var_cover_spgkiri1("Ada");
//                    upd.first().setVdb_var_cover_spgkiri2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_spgkiri2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_spgkiri2.setVisibility(View.INVISIBLE);
                    cb_cover_spgkiri1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_spgkiri1(false);
//                    upd.first().setTdb_var_cover_spgkiri1("Tidak Ada");
//                    upd.first().setDb_var_cover_spgkiri2(false);
//                    upd.first().setTdb_var_cover_spgkiri2("Rusak");
//                    upd.first().setVdb_var_cover_spgkiri2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_spgkiri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_spgkiri2.isChecked()){
                    cb_cover_spgkiri2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_spgkiri2(true);
//                    upd.first().setTdb_var_cover_spgkiri2("Baik");
//                    upd.first().setVdb_var_cover_spgkiri2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_spgkiri2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_spgkiri2(false);
//                    upd.first().setTdb_var_cover_spgkiri2("Rusak");
//                    upd.first().setVdb_var_cover_spgkiri2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_rantai_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_rantai_depan1.isChecked()){
                    cb_rantai_depan1.setText("Ada");
                    cb_rantai_depan2.setVisibility(View.VISIBLE);
                    cb_rantai_depan2.setText("Rusak");
                    cb_rantai_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_depan1(true);
//                    upd.first().setTdb_var_rantai_depan1("Ada");
//                    upd.first().setVdb_var_rantai_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_rantai_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_rantai_depan2.setVisibility(View.INVISIBLE);
                    cb_rantai_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_depan1(false);
//                    upd.first().setTdb_var_rantai_depan1("Tidak Ada");
//                    upd.first().setDb_var_rantai_depan2(false);
//                    upd.first().setTdb_var_rantai_depan2("Rusak");
//                    upd.first().setVdb_var_rantai_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_rantai_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_rantai_depan2.isChecked()){
                    cb_rantai_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_depan2(true);
//                    upd.first().setTdb_var_rantai_depan2("Baik");
//                    upd.first().setVdb_var_rantai_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_rantai_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_depan2(false);
//                    upd.first().setTdb_var_rantai_depan2("Rusak");
//                    upd.first().setVdb_var_rantai_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_rantai_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_rantai_blkg1.isChecked()){
                    cb_rantai_blkg1.setText("Ada");
                    cb_rantai_blkg2.setVisibility(View.VISIBLE);
                    cb_rantai_blkg2.setText("Rusak");
                    cb_rantai_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_blkg1(true);
//                    upd.first().setTdb_var_rantai_blkg1("Ada");
//                    upd.first().setVdb_var_rantai_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_rantai_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_rantai_blkg2.setVisibility(View.INVISIBLE);
                    cb_rantai_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_blkg1(false);
//                    upd.first().setTdb_var_rantai_blkg1("Tidak Ada");
//                    upd.first().setDb_var_rantai_blkg2(false);
//                    upd.first().setTdb_var_rantai_blkg2("Rusak");
//                    upd.first().setVdb_var_rantai_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_rantai_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_rantai_blkg2.isChecked()){
                    cb_rantai_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_blkg2(true);
//                    upd.first().setTdb_var_rantai_blkg2("Baik");
//                    upd.first().setVdb_var_rantai_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_rantai_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_blkg2(false);
//                    upd.first().setTdb_var_rantai_blkg2("Rusak");
//                    upd.first().setVdb_var_rantai_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_spak_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_spak_blkg1.isChecked()){
                    cb_spak_blkg1.setText("Ada");
                    cb_spak_blkg2.setVisibility(View.VISIBLE);
                    cb_spak_blkg2.setText("Rusak");
                    cb_spak_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_spak_blkg1(true);
//                    upd.first().setTdb_var_spak_blkg1("Ada");
//                    upd.first().setVdb_var_spak_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_spak_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_spak_blkg2.setVisibility(View.INVISIBLE);
                    cb_spak_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_spak_blkg1(false);
//                    upd.first().setTdb_var_spak_blkg1("Tidak Ada");
//                    upd.first().setDb_var_spak_blkg2(false);
//                    upd.first().setTdb_var_spak_blkg2("Rusak");
//                    upd.first().setVdb_var_spak_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_spak_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_spak_blkg2.isChecked()){
                    cb_spak_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_spak_blkg2(true);
//                    upd.first().setTdb_var_spak_blkg2("Baik");
//                    upd.first().setVdb_var_spak_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_spak_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_spak_blkg2(false);
//                    upd.first().setTdb_var_spak_blkg2("Rusak");
//                    upd.first().setVdb_var_spak_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_accu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_accu1.isChecked()){
                    cb_cover_accu1.setText("Ada");
                    cb_cover_accu2.setVisibility(View.VISIBLE);
                    cb_cover_accu2.setText("Rusak");
                    cb_cover_accu2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_accu1(true);
//                    upd.first().setTdb_var_cover_accu1("Ada");
//                    upd.first().setVdb_var_cover_accu2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_accu2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_accu2.setVisibility(View.INVISIBLE);
                    cb_cover_accu1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_accu1(false);
//                    upd.first().setTdb_var_cover_accu1("Tidak Ada");
//                    upd.first().setDb_var_cover_accu2(false);
//                    upd.first().setTdb_var_cover_accu2("Rusak");
//                    upd.first().setVdb_var_cover_accu2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_accu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_accu2.isChecked()){
                    cb_cover_accu2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_accu2(true);
//                    upd.first().setTdb_var_cover_accu2("Baik");
//                    upd.first().setVdb_var_cover_accu2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_accu2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_accu2(false);
//                    upd.first().setTdb_var_cover_accu2("Rusak");
//                    upd.first().setVdb_var_cover_accu2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_lampurem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_lampurem1.isChecked()){
                    cb_cover_lampurem1.setText("Ada");
                    cb_cover_lampurem2.setVisibility(View.VISIBLE);
                    cb_cover_lampurem2.setText("Rusak");
                    cb_cover_lampurem2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_lampurem1(true);
//                    upd.first().setTdb_var_cover_lampurem1("Ada");
//                    upd.first().setVdb_var_cover_lampurem2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_lampurem2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_lampurem2.setVisibility(View.INVISIBLE);
                    cb_cover_lampurem1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_lampurem1(false);
//                    upd.first().setTdb_var_cover_lampurem1("Tidak Ada");
//                    upd.first().setDb_var_cover_lampurem2(false);
//                    upd.first().setTdb_var_cover_lampurem2("Rusak");
//                    upd.first().setVdb_var_cover_lampurem2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_lampurem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_lampurem2.isChecked()){
                    cb_cover_lampurem2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_lampurem2(true);
//                    upd.first().setTdb_var_cover_lampurem2("Baik");
//                    upd.first().setVdb_var_cover_lampurem2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_lampurem2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_lampurem2(false);
//                    upd.first().setTdb_var_cover_lampurem2("Rusak");
//                    upd.first().setVdb_var_cover_lampurem2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_tangki_bensin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_tangki_bensin1.isChecked()){
                    cb_tangki_bensin1.setText("Ada");
                    cb_tangki_bensin2.setVisibility(View.VISIBLE);
                    cb_tangki_bensin2.setText("Rusak");
                    cb_tangki_bensin2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tangki_bensin1(true);
//                    upd.first().setTdb_var_tangki_bensin1("Ada");
//                    upd.first().setVdb_var_tangki_bensin2(View.VISIBLE);
//                    upd.first().setTdb_var_tangki_bensin2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_tangki_bensin2.setVisibility(View.INVISIBLE);
                    cb_tangki_bensin1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tangki_bensin1(false);
//                    upd.first().setTdb_var_tangki_bensin1("Tidak Ada");
//                    upd.first().setDb_var_tangki_bensin2(false);
//                    upd.first().setTdb_var_tangki_bensin2("Rusak");
//                    upd.first().setVdb_var_tangki_bensin2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_tangki_bensin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_tangki_bensin2.isChecked()){
                    cb_tangki_bensin2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tangki_bensin2(true);
//                    upd.first().setTdb_var_tangki_bensin2("Baik");
//                    upd.first().setVdb_var_tangki_bensin2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_tangki_bensin2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tangki_bensin2(false);
//                    upd.first().setTdb_var_tangki_bensin2("Rusak");
//                    upd.first().setVdb_var_tangki_bensin2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_bawah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_bawah1.isChecked()){
                    cb_cover_bawah1.setText("Ada");
                    cb_cover_bawah2.setVisibility(View.VISIBLE);
                    cb_cover_bawah2.setText("Rusak");
                    cb_cover_bawah2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_bawah1(true);
//                    upd.first().setTdb_var_cover_bawah1("Ada");
//                    upd.first().setVdb_var_cover_bawah2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_bawah2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_bawah2.setVisibility(View.INVISIBLE);
                    cb_cover_bawah1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_bawah1(false);
//                    upd.first().setTdb_var_cover_bawah1("Tidak Ada");
//                    upd.first().setDb_var_cover_bawah2(false);
//                    upd.first().setTdb_var_cover_bawah2("Rusak");
//                    upd.first().setVdb_var_cover_bawah2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_bawah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_bawah2.isChecked()){
                    cb_cover_bawah2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_bawah2(true);
//                    upd.first().setTdb_var_cover_bawah2("Baik");
//                    upd.first().setVdb_var_cover_bawah2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_bawah2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_bawah2(false);
//                    upd.first().setTdb_var_cover_bawah2("Rusak");
//                    upd.first().setVdb_var_cover_bawah2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_dek_desk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_dek_desk1.isChecked()){
                    cb_dek_desk1.setText("Ada");
                    cb_dek_desk2.setVisibility(View.VISIBLE);
                    cb_dek_desk2.setText("Rusak");
                    cb_dek_desk2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_dek_desk1(true);
//                    upd.first().setTdb_var_dek_desk1("Ada");
//                    upd.first().setVdb_var_dek_desk2(View.VISIBLE);
//                    upd.first().setTdb_var_dek_desk2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_dek_desk2.setVisibility(View.INVISIBLE);
                    cb_dek_desk1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_dek_desk1(false);
//                    upd.first().setTdb_var_dek_desk1("Tidak Ada");
//                    upd.first().setDb_var_dek_desk2(false);
//                    upd.first().setTdb_var_dek_desk2("Rusak");
//                    upd.first().setVdb_var_dek_desk2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_dek_desk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_dek_desk2.isChecked()){
                    cb_dek_desk2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_dek_desk2(true);
//                    upd.first().setTdb_var_dek_desk2("Baik");
//                    upd.first().setVdb_var_dek_desk2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_dek_desk2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_dek_desk2(false);
//                    upd.first().setTdb_var_dek_desk2("Rusak");
//                    upd.first().setVdb_var_dek_desk2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_mesin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_mesin1.isChecked()){
                    cb_cover_mesin1.setText("Ada");
                    cb_cover_mesin2.setVisibility(View.VISIBLE);
                    cb_cover_mesin2.setText("Rusak");
                    cb_cover_mesin2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_mesin1(true);
//                    upd.first().setTdb_var_cover_mesin1("Ada");
//                    upd.first().setVdb_var_cover_mesin2(View.VISIBLE);
//                    upd.first().setTdb_var_cover_mesin2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cover_mesin2.setVisibility(View.INVISIBLE);
                    cb_cover_mesin1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_mesin1(false);
//                    upd.first().setTdb_var_cover_mesin1("Tidak Ada");
//                    upd.first().setDb_var_cover_mesin2(false);
//                    upd.first().setTdb_var_cover_mesin2("Rusak");
//                    upd.first().setVdb_var_cover_mesin2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cover_mesin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cover_mesin2.isChecked()){
                    cb_cover_mesin2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_mesin2(true);
//                    upd.first().setTdb_var_cover_mesin2("Baik");
//                    upd.first().setVdb_var_cover_mesin2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cover_mesin2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cover_mesin2(false);
//                    upd.first().setTdb_var_cover_mesin2("Rusak");
//                    upd.first().setVdb_var_cover_mesin2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_emblem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_emblem1.isChecked()){
                    cb_emblem1.setText("Ada");
                    cb_emblem2.setVisibility(View.VISIBLE);
                    cb_emblem2.setText("Rusak");
                    cb_emblem2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_emblem1(true);
//                    upd.first().setTdb_var_emblem1("Ada");
//                    upd.first().setVdb_var_emblem2(View.VISIBLE);
//                    upd.first().setTdb_var_emblem2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_emblem2.setVisibility(View.INVISIBLE);
                    cb_emblem1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_emblem1(false);
//                    upd.first().setTdb_var_emblem1("Tidak Ada");
//                    upd.first().setDb_var_emblem2(false);
//                    upd.first().setTdb_var_emblem2("Rusak");
//                    upd.first().setVdb_var_emblem2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_emblem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_emblem2.isChecked()){
                    cb_emblem2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_emblem2(true);
//                    upd.first().setTdb_var_emblem2("Baik");
//                    upd.first().setVdb_var_emblem2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_emblem2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_emblem2(false);
//                    upd.first().setTdb_var_emblem2("Rusak");
//                    upd.first().setVdb_var_emblem2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_blok_mesin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_blok_mesin1.isChecked()){
                    cb_blok_mesin1.setText("Ada");
                    cb_blok_mesin2.setVisibility(View.VISIBLE);
                    cb_blok_mesin2.setText("Rusak");
                    cb_blok_mesin2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_blok_mesin1(true);
//                    upd.first().setTdb_var_blok_mesin1("Ada");
//                    upd.first().setVdb_var_blok_mesin2(View.VISIBLE);
//                    upd.first().setTdb_var_blok_mesin2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_blok_mesin2.setVisibility(View.INVISIBLE);
                    cb_blok_mesin1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_blok_mesin1(false);
//                    upd.first().setTdb_var_blok_mesin1("Tidak Ada");
//                    upd.first().setDb_var_blok_mesin2(false);
//                    upd.first().setTdb_var_blok_mesin2("Rusak");
//                    upd.first().setVdb_var_blok_mesin2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_blok_mesin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_blok_mesin2.isChecked()){
                    cb_blok_mesin2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_blok_mesin2(true);
//                    upd.first().setTdb_var_blok_mesin2("Baik");
//                    upd.first().setVdb_var_blok_mesin2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_blok_mesin2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_blok_mesin2(false);
//                    upd.first().setTdb_var_blok_mesin2("Rusak");
//                    upd.first().setVdb_var_blok_mesin2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_carburator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_carburator1.isChecked()){
                    cb_carburator1.setText("Ada");
                    cb_carburator2.setVisibility(View.VISIBLE);
                    cb_carburator2.setText("Rusak");
                    cb_carburator2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_carburator1(true);
//                    upd.first().setTdb_var_carburator1("Ada");
//                    upd.first().setVdb_var_carburator2(View.VISIBLE);
//                    upd.first().setTdb_var_carburator2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_carburator2.setVisibility(View.INVISIBLE);
                    cb_carburator1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_carburator1(false);
//                    upd.first().setTdb_var_carburator1("Tidak Ada");
//                    upd.first().setDb_var_carburator2(false);
//                    upd.first().setTdb_var_carburator2("Rusak");
//                    upd.first().setVdb_var_carburator2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_carburator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_carburator2.isChecked()){
                    cb_carburator2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_carburator2(true);
//                    upd.first().setTdb_var_carburator2("Baik");
//                    upd.first().setVdb_var_carburator2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_carburator2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_carburator2(false);
//                    upd.first().setTdb_var_carburator2("Rusak");
//                    upd.first().setVdb_var_carburator2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_filter_carbu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_filter_carbu1.isChecked()){
                    cb_filter_carbu1.setText("Ada");
                    cb_filter_carbu2.setVisibility(View.VISIBLE);
                    cb_filter_carbu2.setText("Rusak");
                    cb_filter_carbu2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_filter_carbu1(true);
//                    upd.first().setTdb_var_filter_carbu1("Ada");
//                    upd.first().setVdb_var_filter_carbu2(View.VISIBLE);
//                    upd.first().setTdb_var_filter_carbu2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_filter_carbu2.setVisibility(View.INVISIBLE);
                    cb_filter_carbu1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_filter_carbu1(false);
//                    upd.first().setTdb_var_filter_carbu1("Tidak Ada");
//                    upd.first().setDb_var_filter_carbu2(false);
//                    upd.first().setTdb_var_filter_carbu2("Rusak");
//                    upd.first().setVdb_var_filter_carbu2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_filter_carbu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_filter_carbu2.isChecked()){
                    cb_filter_carbu2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_filter_carbu2(true);
//                    upd.first().setTdb_var_filter_carbu2("Baik");
//                    upd.first().setVdb_var_filter_carbu2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_filter_carbu2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_filter_carbu2(false);
//                    upd.first().setTdb_var_filter_carbu2("Rusak");
//                    upd.first().setVdb_var_filter_carbu2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_busi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_busi1.isChecked()){
                    cb_busi1.setText("Ada");
                    cb_busi2.setVisibility(View.VISIBLE);
                    cb_busi2.setText("Rusak");
                    cb_busi2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_busi1(true);
//                    upd.first().setTdb_var_busi1("Ada");
//                    upd.first().setVdb_var_busi2(View.VISIBLE);
//                    upd.first().setTdb_var_busi2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_busi2.setVisibility(View.INVISIBLE);
                    cb_busi1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_busi1(false);
//                    upd.first().setTdb_var_busi1("Tidak Ada");
//                    upd.first().setDb_var_busi2(false);
//                    upd.first().setTdb_var_busi2("Rusak");
//                    upd.first().setVdb_var_busi2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_busi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_busi2.isChecked()){
                    cb_busi2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_busi2(true);
//                    upd.first().setTdb_var_busi2("Baik");
//                    upd.first().setVdb_var_busi2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_busi2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_busi2(false);
//                    upd.first().setTdb_var_busi2("Rusak");
//                    upd.first().setVdb_var_busi2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cdi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cdi1.isChecked()){
                    cb_cdi1.setText("Ada");
                    cb_cdi2.setVisibility(View.VISIBLE);
                    cb_cdi2.setText("Rusak");
                    cb_cdi2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cdi1(true);
//                    upd.first().setTdb_var_cdi1("Ada");
//                    upd.first().setVdb_var_cdi2(View.VISIBLE);
//                    upd.first().setTdb_var_cdi2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cdi2.setVisibility(View.INVISIBLE);
                    cb_cdi1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cdi1(false);
//                    upd.first().setTdb_var_cdi1("Tidak Ada");
//                    upd.first().setDb_var_cdi2(false);
//                    upd.first().setTdb_var_cdi2("Rusak");
//                    upd.first().setVdb_var_cdi2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cdi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cdi2.isChecked()){
                    cb_cdi2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cdi2(true);
//                    upd.first().setTdb_var_cdi2("Baik");
//                    upd.first().setVdb_var_cdi2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cdi2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cdi2(false);
//                    upd.first().setTdb_var_cdi2("Rusak");
//                    upd.first().setVdb_var_cdi2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_accu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_accu1.isChecked()){
                    cb_accu1.setText("Ada");
                    cb_accu2.setVisibility(View.VISIBLE);
                    cb_accu2.setText("Rusak");
                    cb_accu2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_accu1(true);
//                    upd.first().setTdb_var_accu1("Ada");
//                    upd.first().setVdb_var_accu2(View.VISIBLE);
//                    upd.first().setTdb_var_accu2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_accu2.setVisibility(View.INVISIBLE);
                    cb_accu1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_accu1(false);
//                    upd.first().setTdb_var_accu1("Tidak Ada");
//                    upd.first().setDb_var_accu2(false);
//                    upd.first().setTdb_var_accu2("Rusak");
//                    upd.first().setVdb_var_accu2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_accu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_accu2.isChecked()){
                    cb_accu2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_accu2(true);
//                    upd.first().setTdb_var_accu2("Baik");
//                    upd.first().setVdb_var_accu2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_accu2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_accu2(false);
//                    upd.first().setTdb_var_accu2("Rusak");
//                    upd.first().setVdb_var_accu2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_kick_starter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_kick_starter1.isChecked()){
                    cb_kick_starter1.setText("Ada");
                    cb_kick_starter2.setVisibility(View.VISIBLE);
                    cb_kick_starter2.setText("Rusak");
                    cb_kick_starter2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_kick_starter1(true);
//                    upd.first().setTdb_var_kick_starter1("Ada");
//                    upd.first().setVdb_var_kick_starter2(View.VISIBLE);
//                    upd.first().setTdb_var_kick_starter2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_kick_starter2.setVisibility(View.INVISIBLE);
                    cb_kick_starter1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_kick_starter1(false);
//                    upd.first().setTdb_var_kick_starter1("Tidak Ada");
//                    upd.first().setDb_var_kick_starter2(false);
//                    upd.first().setTdb_var_kick_starter2("Rusak");
//                    upd.first().setVdb_var_kick_starter2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_kick_starter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_kick_starter2.isChecked()){
                    cb_kick_starter2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_kick_starter2(true);
//                    upd.first().setTdb_var_kick_starter2("Baik");
//                    upd.first().setVdb_var_kick_starter2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_kick_starter2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_kick_starter2(false);
//                    upd.first().setTdb_var_kick_starter2("Rusak");
//                    upd.first().setVdb_var_kick_starter2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_perseneling1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_perseneling1.isChecked()){
                    cb_perseneling1.setText("Ada");
                    cb_perseneling2.setVisibility(View.VISIBLE);
                    cb_perseneling2.setText("Rusak");
                    cb_perseneling2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_perseneling1(true);
//                    upd.first().setTdb_var_perseneling1("Ada");
//                    upd.first().setVdb_var_perseneling2(View.VISIBLE);
//                    upd.first().setTdb_var_perseneling2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_perseneling2.setVisibility(View.INVISIBLE);
                    cb_perseneling1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_perseneling1(false);
//                    upd.first().setTdb_var_perseneling1("Tidak Ada");
//                    upd.first().setDb_var_perseneling2(false);
//                    upd.first().setTdb_var_perseneling2("Rusak");
//                    upd.first().setVdb_var_perseneling2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_perseneling2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_perseneling2.isChecked()){
                    cb_perseneling2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_perseneling2(true);
//                    upd.first().setTdb_var_perseneling2("Baik");
//                    upd.first().setVdb_var_perseneling2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_perseneling2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_perseneling2(false);
//                    upd.first().setTdb_var_perseneling2("Rusak");
//                    upd.first().setVdb_var_perseneling2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_knalpot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_knalpot1.isChecked()){
                    cb_knalpot1.setText("Ada");
                    cb_knalpot2.setVisibility(View.VISIBLE);
                    cb_knalpot2.setText("Rusak");
                    cb_knalpot2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_knalpot1(true);
//                    upd.first().setTdb_var_knalpot1("Ada");
//                    upd.first().setVdb_var_knalpot2(View.VISIBLE);
//                    upd.first().setTdb_var_knalpot2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_knalpot2.setVisibility(View.INVISIBLE);
                    cb_knalpot1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_knalpot1(false);
//                    upd.first().setTdb_var_knalpot1("Tidak Ada");
//                    upd.first().setDb_var_knalpot2(false);
//                    upd.first().setTdb_var_knalpot2("Rusak");
//                    upd.first().setVdb_var_knalpot2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_knalpot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_knalpot2.isChecked()){
                    cb_knalpot2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_knalpot2(true);
//                    upd.first().setTdb_var_knalpot2("Baik");
//                    upd.first().setVdb_var_knalpot2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_knalpot2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_knalpot2(false);
//                    upd.first().setTdb_var_knalpot2("Rusak");
//                    upd.first().setVdb_var_knalpot2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_coil1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_coil1.isChecked()){
                    cb_coil1.setText("Ada");
                    cb_coil2.setVisibility(View.VISIBLE);
                    cb_coil2.setText("Rusak");
                    cb_coil2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_coil1(true);
//                    upd.first().setTdb_var_coil1("Ada");
//                    upd.first().setVdb_var_coil2(View.VISIBLE);
//                    upd.first().setTdb_var_coil2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_coil2.setVisibility(View.INVISIBLE);
                    cb_coil1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_coil1(false);
//                    upd.first().setTdb_var_coil1("Tidak Ada");
//                    upd.first().setDb_var_coil2(false);
//                    upd.first().setTdb_var_coil2("Rusak");
//                    upd.first().setVdb_var_coil2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_coil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_coil2.isChecked()){
                    cb_coil2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_coil2(true);
//                    upd.first().setTdb_var_coil2("Baik");
//                    upd.first().setVdb_var_coil2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_coil2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_coil2(false);
//                    upd.first().setTdb_var_coil2("Rusak");
//                    upd.first().setVdb_var_coil2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_blok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_blok1.isChecked()){
                    cb_blok1.setText("Ada");
                    cb_blok2.setVisibility(View.VISIBLE);
                    cb_blok2.setText("Rusak");
                    cb_blok2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_blok1(true);
//                    upd.first().setTdb_var_blok1("Ada");
//                    upd.first().setVdb_var_blok2(View.VISIBLE);
//                    upd.first().setTdb_var_blok2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_blok2.setVisibility(View.INVISIBLE);
                    cb_blok1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_blok1(false);
//                    upd.first().setTdb_var_blok1("Tidak Ada");
//                    upd.first().setDb_var_blok2(false);
//                    upd.first().setTdb_var_blok2("Rusak");
//                    upd.first().setVdb_var_blok2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_blok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_blok2.isChecked()){
                    cb_blok2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_blok2(true);
//                    upd.first().setTdb_var_blok2("Baik");
//                    upd.first().setVdb_var_blok2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_blok2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_blok2(false);
//                    upd.first().setTdb_var_blok2("Rusak");
//                    upd.first().setVdb_var_blok2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_tutupcvt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_tutupcvt1.isChecked()){
                    cb_tutupcvt1.setText("Ada");
                    cb_tutupcvt2.setVisibility(View.VISIBLE);
                    cb_tutupcvt2.setText("Rusak");
                    cb_tutupcvt2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tutupcvt1(true);
//                    upd.first().setTdb_var_tutupcvt1("Ada");
//                    upd.first().setVdb_var_tutupcvt2(View.VISIBLE);
//                    upd.first().setTdb_var_tutupcvt2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_tutupcvt2.setVisibility(View.INVISIBLE);
                    cb_tutupcvt1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tutupcvt1(false);
//                    upd.first().setTdb_var_tutupcvt1("Tidak Ada");
//                    upd.first().setDb_var_tutupcvt2(false);
//                    upd.first().setTdb_var_tutupcvt2("Rusak");
//                    upd.first().setVdb_var_tutupcvt2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_tutupcvt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_tutupcvt2.isChecked()){
                    cb_tutupcvt2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tutupcvt2(true);
//                    upd.first().setTdb_var_tutupcvt2("Baik");
//                    upd.first().setVdb_var_tutupcvt2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_tutupcvt2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tutupcvt2(false);
//                    upd.first().setTdb_var_tutupcvt2("Rusak");
//                    upd.first().setVdb_var_tutupcvt2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_radiator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_radiator1.isChecked()){
                    cb_radiator1.setText("Ada");
                    cb_radiator2.setVisibility(View.VISIBLE);
                    cb_radiator2.setText("Rusak");
                    cb_radiator2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_radiator1(true);
//                    upd.first().setTdb_var_radiator1("Ada");
//                    upd.first().setVdb_var_radiator2(View.VISIBLE);
//                    upd.first().setTdb_var_radiator2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_radiator2.setVisibility(View.INVISIBLE);
                    cb_radiator1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_radiator1(false);
//                    upd.first().setTdb_var_radiator1("Tidak Ada");
//                    upd.first().setDb_var_radiator2(false);
//                    upd.first().setTdb_var_radiator2("Rusak");
//                    upd.first().setVdb_var_radiator2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_radiator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_radiator2.isChecked()){
                    cb_radiator2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_radiator2(true);
//                    upd.first().setTdb_var_radiator2("Baik");
//                    upd.first().setVdb_var_radiator2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_radiator2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_radiator2(false);
//                    upd.first().setTdb_var_radiator2("Rusak");
//                    upd.first().setVdb_var_radiator2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_lampu_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_lampu_depan1.isChecked()){
                    cb_lampu_depan1.setText("Ada");
                    cb_lampu_depan2.setVisibility(View.VISIBLE);
                    cb_lampu_depan2.setText("Rusak");
                    cb_lampu_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_lampu_depan1(true);
//                    upd.first().setTdb_var_lampu_depan1("Ada");
//                    upd.first().setVdb_var_lampu_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_lampu_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_lampu_depan2.setVisibility(View.INVISIBLE);
                    cb_lampu_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_lampu_depan1(false);
//                    upd.first().setTdb_var_lampu_depan1("Tidak Ada");
//                    upd.first().setDb_var_lampu_depan2(false);
//                    upd.first().setTdb_var_lampu_depan2("Rusak");
//                    upd.first().setVdb_var_lampu_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_lampu_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_lampu_depan2.isChecked()){
                    cb_lampu_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_lampu_depan2(true);
//                    upd.first().setTdb_var_lampu_depan2("Baik");
//                    upd.first().setVdb_var_lampu_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_lampu_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_lampu_depan2(false);
//                    upd.first().setTdb_var_lampu_depan2("Rusak");
//                    upd.first().setVdb_var_lampu_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_sein_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_sein_depan1.isChecked()){
                    cb_sein_depan1.setText("Ada");
                    cb_sein_depan2.setVisibility(View.VISIBLE);
                    cb_sein_depan2.setText("Rusak");
                    cb_sein_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sein_depan1(true);
//                    upd.first().setTdb_var_sein_depan1("Ada");
//                    upd.first().setVdb_var_sein_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_sein_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_sein_depan2.setVisibility(View.INVISIBLE);
                    cb_sein_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sein_depan1(false);
//                    upd.first().setTdb_var_sein_depan1("Tidak Ada");
//                    upd.first().setDb_var_sein_depan2(false);
//                    upd.first().setTdb_var_sein_depan2("Rusak");
//                    upd.first().setVdb_var_sein_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_sein_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_sein_depan2.isChecked()){
                    cb_sein_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sein_depan2(true);
//                    upd.first().setTdb_var_sein_depan2("Baik");
//                    upd.first().setVdb_var_sein_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_sein_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sein_depan2(false);
//                    upd.first().setTdb_var_sein_depan2("Rusak");
//                    upd.first().setVdb_var_sein_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_sein_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_sein_blkg1.isChecked()){
                    cb_sein_blkg1.setText("Ada");
                    cb_sein_blkg2.setVisibility(View.VISIBLE);
                    cb_sein_blkg2.setText("Rusak");
                    cb_sein_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sein_blkg1(true);
//                    upd.first().setTdb_var_sein_blkg1("Ada");
//                    upd.first().setVdb_var_sein_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_sein_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_sein_blkg2.setVisibility(View.INVISIBLE);
                    cb_sein_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sein_blkg1(false);
//                    upd.first().setTdb_var_sein_blkg1("Tidak Ada");
//                    upd.first().setDb_var_sein_blkg2(false);
//                    upd.first().setTdb_var_sein_blkg2("Rusak");
//                    upd.first().setVdb_var_sein_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_sein_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_sein_blkg2.isChecked()){
                    cb_sein_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sein_blkg2(true);
//                    upd.first().setTdb_var_sein_blkg2("Baik");
//                    upd.first().setVdb_var_sein_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_sein_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_sein_blkg2(false);
//                    upd.first().setTdb_var_sein_blkg2("Rusak");
//                    upd.first().setVdb_var_sein_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_lampu_rem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_lampu_rem1.isChecked()){
                    cb_lampu_rem1.setText("Ada");
                    cb_lampu_rem2.setVisibility(View.VISIBLE);
                    cb_lampu_rem2.setText("Rusak");
                    cb_lampu_rem2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_lampu_rem1(true);
//                    upd.first().setTdb_var_lampu_rem1("Ada");
//                    upd.first().setVdb_var_lampu_rem2(View.VISIBLE);
//                    upd.first().setTdb_var_lampu_rem2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_lampu_rem2.setVisibility(View.INVISIBLE);
                    cb_lampu_rem1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_lampu_rem1(false);
//                    upd.first().setTdb_var_lampu_rem1("Tidak Ada");
//                    upd.first().setDb_var_lampu_rem2(false);
//                    upd.first().setTdb_var_lampu_rem2("Rusak");
//                    upd.first().setVdb_var_lampu_rem2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_lampu_rem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_lampu_rem2.isChecked()){
                    cb_lampu_rem2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_lampu_rem2(true);
//                    upd.first().setTdb_var_lampu_rem2("Baik");
//                    upd.first().setVdb_var_lampu_rem2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_lampu_rem2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_lampu_rem2(false);
//                    upd.first().setTdb_var_lampu_rem2("Rusak");
//                    upd.first().setVdb_var_lampu_rem2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_handlerem_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_handlerem_depan1.isChecked()){
                    cb_handlerem_depan1.setText("Ada");
                    cb_handlerem_depan2.setVisibility(View.VISIBLE);
                    cb_handlerem_depan2.setText("Rusak");
                    cb_handlerem_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handlerem_depan1(true);
//                    upd.first().setTdb_var_handlerem_depan1("Ada");
//                    upd.first().setVdb_var_handlerem_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_handlerem_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_handlerem_depan2.setVisibility(View.INVISIBLE);
                    cb_handlerem_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handlerem_depan1(false);
//                    upd.first().setTdb_var_handlerem_depan1("Tidak Ada");
//                    upd.first().setDb_var_handlerem_depan2(false);
//                    upd.first().setTdb_var_handlerem_depan2("Rusak");
//                    upd.first().setVdb_var_handlerem_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_handlerem_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_handlerem_depan2.isChecked()){
                    cb_handlerem_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handlerem_depan2(true);
//                    upd.first().setTdb_var_handlerem_depan2("Baik");
//                    upd.first().setVdb_var_handlerem_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_handlerem_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handlerem_depan2(false);
//                    upd.first().setTdb_var_handlerem_depan2("Rusak");
//                    upd.first().setVdb_var_handlerem_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_pedalrem_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_pedalrem_blkg1.isChecked()){
                    cb_pedalrem_blkg1.setText("Ada");
                    cb_pedalrem_blkg2.setVisibility(View.VISIBLE);
                    cb_pedalrem_blkg2.setText("Rusak");
                    cb_pedalrem_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_pedalrem_blkg1(true);
//                    upd.first().setTdb_var_pedalrem_blkg1("Ada");
//                    upd.first().setVdb_var_pedalrem_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_pedalrem_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_pedalrem_blkg2.setVisibility(View.INVISIBLE);
                    cb_pedalrem_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_pedalrem_blkg1(false);
//                    upd.first().setTdb_var_pedalrem_blkg1("Tidak Ada");
//                    upd.first().setDb_var_pedalrem_blkg2(false);
//                    upd.first().setTdb_var_pedalrem_blkg2("Rusak");
//                    upd.first().setVdb_var_pedalrem_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_pedalrem_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_pedalrem_blkg2.isChecked()){
                    cb_pedalrem_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_pedalrem_blkg2(true);
//                    upd.first().setTdb_var_pedalrem_blkg2("Baik");
//                    upd.first().setVdb_var_pedalrem_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_pedalrem_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_pedalrem_blkg2(false);
//                    upd.first().setTdb_var_pedalrem_blkg2("Rusak");
//                    upd.first().setVdb_var_pedalrem_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_handlerem_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_handlerem_blkg1.isChecked()){
                    cb_handlerem_blkg1.setText("Ada");
                    cb_handlerem_blkg2.setVisibility(View.VISIBLE);
                    cb_handlerem_blkg2.setText("Rusak");
                    cb_handlerem_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handlerem_blkg1(true);
//                    upd.first().setTdb_var_handlerem_blkg1("Ada");
//                    upd.first().setVdb_var_handlerem_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_handlerem_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_handlerem_blkg2.setVisibility(View.INVISIBLE);
                    cb_handlerem_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handlerem_blkg1(false);
//                    upd.first().setTdb_var_handlerem_blkg1("Tidak Ada");
//                    upd.first().setDb_var_handlerem_blkg2(false);
//                    upd.first().setTdb_var_handlerem_blkg2("Rusak");
//                    upd.first().setVdb_var_handlerem_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_handlerem_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_handlerem_blkg2.isChecked()){
                    cb_handlerem_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handlerem_blkg2(true);
//                    upd.first().setTdb_var_handlerem_blkg2("Baik");
//                    upd.first().setVdb_var_handlerem_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_handlerem_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handlerem_blkg2(false);
//                    upd.first().setTdb_var_handlerem_blkg2("Rusak");
//                    upd.first().setVdb_var_handlerem_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_handle_kopling1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_handle_kopling1.isChecked()){
                    cb_handle_kopling1.setText("Ada");
                    cb_handle_kopling2.setVisibility(View.VISIBLE);
                    cb_handle_kopling2.setText("Rusak");
                    cb_handle_kopling2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handle_kopling1(true);
//                    upd.first().setTdb_var_handle_kopling1("Ada");
//                    upd.first().setVdb_var_handle_kopling2(View.VISIBLE);
//                    upd.first().setTdb_var_handle_kopling2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_handle_kopling2.setVisibility(View.INVISIBLE);
                    cb_handle_kopling1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handle_kopling1(false);
//                    upd.first().setTdb_var_handle_kopling1("Tidak Ada");
//                    upd.first().setDb_var_handle_kopling2(false);
//                    upd.first().setTdb_var_handle_kopling2("Rusak");
//                    upd.first().setVdb_var_handle_kopling2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_handle_kopling2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_handle_kopling2.isChecked()){
                    cb_handle_kopling2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handle_kopling2(true);
//                    upd.first().setTdb_var_handle_kopling2("Baik");
//                    upd.first().setVdb_var_handle_kopling2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_handle_kopling2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_handle_kopling2(false);
//                    upd.first().setTdb_var_handle_kopling2("Rusak");
//                    upd.first().setVdb_var_handle_kopling2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cb_master_rem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_master_rem1.isChecked()){
                    cb_master_rem1.setText("Ada");
                    cb_master_rem2.setVisibility(View.VISIBLE);
                    cb_master_rem2.setText("Rusak");
                    cb_master_rem2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_master_rem1(true);
//                    upd.first().setTdb_var_master_rem1("Ada");
//                    upd.first().setVdb_var_master_rem2(View.VISIBLE);
//                    upd.first().setTdb_var_master_rem2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_master_rem2.setVisibility(View.INVISIBLE);
                    cb_master_rem1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_master_rem1(false);
//                    upd.first().setTdb_var_master_rem1("Tidak Ada");
//                    upd.first().setDb_var_master_rem2(false);
//                    upd.first().setTdb_var_master_rem2("Rusak");
//                    upd.first().setVdb_var_master_rem2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cb_master_rem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_master_rem2.isChecked()){
                    cb_master_rem2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_master_rem2(true);
//                    upd.first().setTdb_var_master_rem2("Baik");
//                    upd.first().setVdb_var_master_rem2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_master_rem2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_master_rem2(false);
//                    upd.first().setTdb_var_master_rem2("Rusak");
//                    upd.first().setVdb_var_master_rem2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_caliper1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_caliper1.isChecked()){
                    cb_caliper1.setText("Ada");
                    cb_caliper2.setVisibility(View.VISIBLE);
                    cb_caliper2.setText("Rusak");
                    cb_caliper2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_caliper1(true);
//                    upd.first().setTdb_var_caliper1("Ada");
//                    upd.first().setVdb_var_caliper2(View.VISIBLE);
//                    upd.first().setTdb_var_caliper2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_caliper2.setVisibility(View.INVISIBLE);
                    cb_caliper1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_caliper1(false);
//                    upd.first().setTdb_var_caliper1("Tidak Ada");
//                    upd.first().setDb_var_caliper2(false);
//                    upd.first().setTdb_var_caliper2("Rusak");
//                    upd.first().setVdb_var_caliper2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_caliper2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_caliper2.isChecked()){
                    cb_caliper2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_caliper2(true);
//                    upd.first().setTdb_var_caliper2("Baik");
//                    upd.first().setVdb_var_caliper2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_caliper2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_caliper2(false);
//                    upd.first().setTdb_var_caliper2("Rusak");
//                    upd.first().setVdb_var_caliper2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cakram1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cakram1.isChecked()){
                    cb_cakram1.setText("Ada");
                    cb_cakram2.setVisibility(View.VISIBLE);
                    cb_cakram2.setText("Rusak");
                    cb_cakram2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cakram1(true);
//                    upd.first().setTdb_var_cakram1("Ada");
//                    upd.first().setVdb_var_cakram2(View.VISIBLE);
//                    upd.first().setTdb_var_cakram2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_cakram2.setVisibility(View.INVISIBLE);
                    cb_cakram1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cakram1(false);
//                    upd.first().setTdb_var_cakram1("Tidak Ada");
//                    upd.first().setDb_var_cakram2(false);
//                    upd.first().setTdb_var_cakram2("Rusak");
//                    upd.first().setVdb_var_cakram2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_cakram2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_cakram2.isChecked()){
                    cb_cakram2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cakram2(true);
//                    upd.first().setTdb_var_cakram2("Baik");
//                    upd.first().setVdb_var_cakram2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_cakram2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cakram2(false);
//                    upd.first().setTdb_var_cakram2("Rusak");
//                    upd.first().setVdb_var_cakram2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_shock_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_shock_depan1.isChecked()){
                    cb_shock_depan1.setText("Ada");
                    cb_shock_depan2.setVisibility(View.VISIBLE);
                    cb_shock_depan2.setText("Rusak");
                    cb_shock_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_shock_depan1(true);
//                    upd.first().setTdb_var_shock_depan1("Ada");
//                    upd.first().setVdb_var_shock_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_shock_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_shock_depan2.setVisibility(View.INVISIBLE);
                    cb_shock_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_shock_depan1(false);
//                    upd.first().setTdb_var_shock_depan1("Tidak Ada");
//                    upd.first().setDb_var_shock_depan2(false);
//                    upd.first().setTdb_var_shock_depan2("Rusak");
//                    upd.first().setVdb_var_shock_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_shock_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_shock_depan2.isChecked()){
                    cb_shock_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_shock_depan2(true);
//                    upd.first().setTdb_var_shock_depan2("Baik");
//                    upd.first().setVdb_var_shock_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_shock_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_shock_depan2(false);
//                    upd.first().setTdb_var_shock_depan2("Rusak");
//                    upd.first().setVdb_var_shock_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_shock_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_shock_blkg1.isChecked()){
                    cb_shock_blkg1.setText("Ada");
                    cb_shock_blkg2.setVisibility(View.VISIBLE);
                    cb_shock_blkg2.setText("Rusak");
                    cb_shock_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_shock_blkg1(true);
//                    upd.first().setTdb_var_shock_blkg1("Ada");
//                    upd.first().setVdb_var_shock_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_shock_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_shock_blkg2.setVisibility(View.INVISIBLE);
                    cb_shock_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_shock_blkg1(false);
//                    upd.first().setTdb_var_shock_blkg1("Tidak Ada");
//                    upd.first().setDb_var_shock_blkg2(false);
//                    upd.first().setTdb_var_shock_blkg2("Rusak");
//                    upd.first().setVdb_var_shock_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_shock_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_shock_blkg2.isChecked()){
                    cb_shock_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_shock_blkg2(true);
//                    upd.first().setTdb_var_shock_blkg2("Baik");
//                    upd.first().setVdb_var_shock_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_shock_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_shock_blkg2(false);
//                    upd.first().setTdb_var_shock_blkg2("Rusak");
//                    upd.first().setVdb_var_shock_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_tromol_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_tromol_depan1.isChecked()){
                    cb_tromol_depan1.setText("Ada");
                    cb_tromol_depan2.setVisibility(View.VISIBLE);
                    cb_tromol_depan2.setText("Rusak");
                    cb_tromol_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tromol_depan1(true);
//                    upd.first().setTdb_var_tromol_depan1("Ada");
//                    upd.first().setVdb_var_tromol_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_tromol_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_tromol_depan2.setVisibility(View.INVISIBLE);
                    cb_tromol_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tromol_depan1(false);
//                    upd.first().setTdb_var_tromol_depan1("Tidak Ada");
//                    upd.first().setDb_var_tromol_depan2(false);
//                    upd.first().setTdb_var_tromol_depan2("Rusak");
//                    upd.first().setVdb_var_tromol_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_tromol_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_tromol_depan2.isChecked()){
                    cb_tromol_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tromol_depan2(true);
//                    upd.first().setTdb_var_tromol_depan2("Baik");
//                    upd.first().setVdb_var_tromol_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_tromol_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tromol_depan2(false);
//                    upd.first().setTdb_var_tromol_depan2("Rusak");
//                    upd.first().setVdb_var_tromol_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_tromol_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_tromol_blkg1.isChecked()){
                    cb_tromol_blkg1.setText("Ada");
                    cb_tromol_blkg2.setVisibility(View.VISIBLE);
                    cb_tromol_blkg2.setText("Rusak");
                    cb_tromol_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tromol_blkg1(true);
//                    upd.first().setTdb_var_tromol_blkg1("Ada");
//                    upd.first().setVdb_var_tromol_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_tromol_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_tromol_blkg2.setVisibility(View.INVISIBLE);
                    cb_tromol_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tromol_blkg1(false);
//                    upd.first().setTdb_var_tromol_blkg1("Tidak Ada");
//                    upd.first().setDb_var_tromol_blkg2(false);
//                    upd.first().setTdb_var_tromol_blkg2("Rusak");
//                    upd.first().setVdb_var_tromol_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_tromol_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_tromol_blkg2.isChecked()){
                    cb_tromol_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tromol_blkg2(true);
//                    upd.first().setTdb_var_tromol_blkg2("Baik");
//                    upd.first().setVdb_var_tromol_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_tromol_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_tromol_blkg2(false);
//                    upd.first().setTdb_var_tromol_blkg2("Rusak");
//                    upd.first().setVdb_var_tromol_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_footstep_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_footstep_depan1.isChecked()){
                    cb_footstep_depan1.setText("Ada");
                    cb_footstep_depan2.setVisibility(View.VISIBLE);
                    cb_footstep_depan2.setText("Rusak");
                    cb_footstep_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_footstep_depan1(true);
//                    upd.first().setTdb_var_footstep_depan1("Ada");
//                    upd.first().setVdb_var_footstep_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_footstep_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_footstep_depan2.setVisibility(View.INVISIBLE);
                    cb_footstep_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_footstep_depan1(false);
//                    upd.first().setTdb_var_footstep_depan1("Tidak Ada");
//                    upd.first().setDb_var_footstep_depan2(false);
//                    upd.first().setTdb_var_footstep_depan2("Rusak");
//                    upd.first().setVdb_var_footstep_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_footstep_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_footstep_depan2.isChecked()){
                    cb_footstep_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_footstep_depan2(true);
//                    upd.first().setTdb_var_footstep_depan2("Baik");
//                    upd.first().setVdb_var_footstep_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_footstep_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_footstep_depan2(false);
//                    upd.first().setTdb_var_footstep_depan2("Rusak");
//                    upd.first().setVdb_var_footstep_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_footstep_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_footstep_blkg1.isChecked()){
                    cb_footstep_blkg1.setText("Ada");
                    cb_footstep_blkg2.setVisibility(View.VISIBLE);
                    cb_footstep_blkg2.setText("Rusak");
                    cb_footstep_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_footstep_blkg1(true);
//                    upd.first().setTdb_var_footstep_blkg1("Ada");
//                    upd.first().setVdb_var_footstep_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_footstep_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_footstep_blkg2.setVisibility(View.INVISIBLE);
                    cb_footstep_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_footstep_blkg1(false);
//                    upd.first().setTdb_var_footstep_blkg1("Tidak Ada");
//                    upd.first().setDb_var_footstep_blkg2(false);
//                    upd.first().setTdb_var_footstep_blkg2("Rusak");
//                    upd.first().setVdb_var_footstep_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_footstep_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_footstep_blkg2.isChecked()){
                    cb_footstep_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_footstep_blkg2(true);
//                    upd.first().setTdb_var_footstep_blkg2("Baik");
//                    upd.first().setVdb_var_footstep_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_footstep_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_footstep_blkg2(false);
//                    upd.first().setTdb_var_footstep_blkg2("Rusak");
//                    upd.first().setVdb_var_footstep_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_swing_arm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_swing_arm1.isChecked()){
                    cb_swing_arm1.setText("Ada");
                    cb_swing_arm2.setVisibility(View.VISIBLE);
                    cb_swing_arm2.setText("Rusak");
                    cb_swing_arm2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_swing_arm1(true);
//                    upd.first().setTdb_var_swing_arm1("Ada");
//                    upd.first().setVdb_var_swing_arm2(View.VISIBLE);
//                    upd.first().setTdb_var_swing_arm2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_swing_arm2.setVisibility(View.INVISIBLE);
                    cb_swing_arm1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_swing_arm1(false);
//                    upd.first().setTdb_var_swing_arm1("Tidak Ada");
//                    upd.first().setDb_var_swing_arm2(false);
//                    upd.first().setTdb_var_swing_arm2("Rusak");
//                    upd.first().setVdb_var_swing_arm2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_swing_arm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_swing_arm2.isChecked()){
                    cb_swing_arm2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_swing_arm2(true);
//                    upd.first().setTdb_var_swing_arm2("Baik");
//                    upd.first().setVdb_var_swing_arm2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_swing_arm2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_swing_arm2(false);
//                    upd.first().setTdb_var_swing_arm2("Rusak");
//                    upd.first().setVdb_var_swing_arm2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_rantai_fanbelt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_rantai_fanbelt1.isChecked()){
                    cb_rantai_fanbelt1.setText("Ada");
                    cb_rantai_fanbelt2.setVisibility(View.VISIBLE);
                    cb_rantai_fanbelt2.setText("Rusak");
                    cb_rantai_fanbelt2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_fanbelt1(true);
//                    upd.first().setTdb_var_rantai_fanbelt1("Ada");
//                    upd.first().setVdb_var_rantai_fanbelt2(View.VISIBLE);
//                    upd.first().setTdb_var_rantai_fanbelt2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_rantai_fanbelt2.setVisibility(View.INVISIBLE);
                    cb_rantai_fanbelt1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_fanbelt1(false);
//                    upd.first().setTdb_var_rantai_fanbelt1("Tidak Ada");
//                    upd.first().setDb_var_rantai_fanbelt2(false);
//                    upd.first().setTdb_var_rantai_fanbelt2("Rusak");
//                    upd.first().setVdb_var_rantai_fanbelt2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_rantai_fanbelt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_rantai_fanbelt2.isChecked()){
                    cb_rantai_fanbelt2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_fanbelt2(true);
//                    upd.first().setTdb_var_rantai_fanbelt2("Baik");
//                    upd.first().setVdb_var_rantai_fanbelt2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_rantai_fanbelt2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rantai_fanbelt2(false);
//                    upd.first().setTdb_var_rantai_fanbelt2("Rusak");
//                    upd.first().setVdb_var_rantai_fanbelt2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_gear_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_gear_depan1.isChecked()){
                    cb_gear_depan1.setText("Ada");
                    cb_gear_depan2.setVisibility(View.VISIBLE);
                    cb_gear_depan2.setText("Rusak");
                    cb_gear_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_gear_depan1(true);
//                    upd.first().setTdb_var_gear_depan1("Ada");
//                    upd.first().setVdb_var_gear_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_gear_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_gear_depan2.setVisibility(View.INVISIBLE);
                    cb_gear_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_gear_depan1(false);
//                    upd.first().setTdb_var_gear_depan1("Tidak Ada");
//                    upd.first().setDb_var_gear_depan2(false);
//                    upd.first().setTdb_var_gear_depan2("Rusak");
//                    upd.first().setVdb_var_gear_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_gear_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_gear_depan2.isChecked()){
                    cb_gear_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_gear_depan2(true);
//                    upd.first().setTdb_var_gear_depan2("Baik");
//                    upd.first().setVdb_var_gear_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_gear_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_gear_depan2(false);
//                    upd.first().setTdb_var_gear_depan2("Rusak");
//                    upd.first().setVdb_var_gear_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_gear_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_gear_blkg1.isChecked()){
                    cb_gear_blkg1.setText("Ada");
                    cb_gear_blkg2.setVisibility(View.VISIBLE);
                    cb_gear_blkg2.setText("Rusak");
                    cb_gear_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_gear_blkg1(true);
//                    upd.first().setTdb_var_gear_blkg1("Ada");
//                    upd.first().setVdb_var_gear_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_gear_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_gear_blkg2.setVisibility(View.INVISIBLE);
                    cb_gear_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_gear_blkg1(false);
//                    upd.first().setTdb_var_gear_blkg1("Tidak Ada");
//                    upd.first().setDb_var_gear_blkg2(false);
//                    upd.first().setTdb_var_gear_blkg2("Rusak");
//                    upd.first().setVdb_var_gear_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_gear_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_gear_blkg2.isChecked()){
                    cb_gear_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_gear_blkg2(true);
//                    upd.first().setTdb_var_gear_blkg2("Baik");
//                    upd.first().setVdb_var_gear_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_gear_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_gear_blkg2(false);
//                    upd.first().setTdb_var_gear_blkg2("Rusak");
//                    upd.first().setVdb_var_gear_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_standar_tengah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_standar_tengah1.isChecked()){
                    cb_standar_tengah1.setText("Ada");
                    cb_standar_tengah2.setVisibility(View.VISIBLE);
                    cb_standar_tengah2.setText("Rusak");
                    cb_standar_tengah2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_standar_tengah1(true);
//                    upd.first().setTdb_var_standar_tengah1("Ada");
//                    upd.first().setVdb_var_standar_tengah2(View.VISIBLE);
//                    upd.first().setTdb_var_standar_tengah2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_standar_tengah2.setVisibility(View.INVISIBLE);
                    cb_standar_tengah1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_standar_tengah1(false);
//                    upd.first().setTdb_var_standar_tengah1("Tidak Ada");
//                    upd.first().setDb_var_standar_tengah2(false);
//                    upd.first().setTdb_var_standar_tengah2("Rusak");
//                    upd.first().setVdb_var_standar_tengah2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_standar_tengah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_standar_tengah2.isChecked()){
                    cb_standar_tengah2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_standar_tengah2(true);
//                    upd.first().setTdb_var_standar_tengah2("Baik");
//                    upd.first().setVdb_var_standar_tengah2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_standar_tengah2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_standar_tengah2(false);
//                    upd.first().setTdb_var_standar_tengah2("Rusak");
//                    upd.first().setVdb_var_standar_tengah2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_standar_samping1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_standar_samping1.isChecked()){
                    cb_standar_samping1.setText("Ada");
                    cb_standar_samping2.setVisibility(View.VISIBLE);
                    cb_standar_samping2.setText("Rusak");
                    cb_standar_samping2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_standar_samping1(true);
//                    upd.first().setTdb_var_standar_samping1("Ada");
//                    upd.first().setVdb_var_standar_samping2(View.VISIBLE);
//                    upd.first().setTdb_var_standar_samping2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_standar_samping2.setVisibility(View.INVISIBLE);
                    cb_standar_samping1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_standar_samping1(false);
//                    upd.first().setTdb_var_standar_samping1("Tidak Ada");
//                    upd.first().setDb_var_standar_samping2(false);
//                    upd.first().setTdb_var_standar_samping2("Rusak");
//                    upd.first().setVdb_var_standar_samping2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_standar_samping2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_standar_samping2.isChecked()){
                    cb_standar_samping2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_standar_samping2(true);
//                    upd.first().setTdb_var_standar_samping2("Baik");
//                    upd.first().setVdb_var_standar_samping2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_standar_samping2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_standar_samping2(false);
//                    upd.first().setTdb_var_standar_samping2("Rusak");
//                    upd.first().setVdb_var_standar_samping2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_ban_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_ban_depan1.isChecked()){
                    cb_ban_depan1.setText("Ada");
                    cb_ban_depan2.setVisibility(View.VISIBLE);
                    cb_ban_depan2.setText("Rusak");
                    cb_ban_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_ban_depan1(true);
//                    upd.first().setTdb_var_ban_depan1("Ada");
//                    upd.first().setVdb_var_ban_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_ban_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_ban_depan2.setVisibility(View.INVISIBLE);
                    cb_ban_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_ban_depan1(false);
//                    upd.first().setTdb_var_ban_depan1("Tidak Ada");
//                    upd.first().setDb_var_ban_depan2(false);
//                    upd.first().setTdb_var_ban_depan2("Rusak");
//                    upd.first().setVdb_var_ban_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_ban_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_ban_depan2.isChecked()){
                    cb_ban_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_ban_depan2(true);
//                    upd.first().setTdb_var_ban_depan2("Baik");
//                    upd.first().setVdb_var_ban_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_ban_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_ban_depan2(false);
//                    upd.first().setTdb_var_ban_depan2("Rusak");
//                    upd.first().setVdb_var_ban_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_ban_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_ban_blkg1.isChecked()){
                    cb_ban_blkg1.setText("Ada");
                    cb_ban_blkg2.setVisibility(View.VISIBLE);
                    cb_ban_blkg2.setText("Rusak");
                    cb_ban_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_ban_blkg1(true);
//                    upd.first().setTdb_var_ban_blkg1("Ada");
//                    upd.first().setVdb_var_ban_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_ban_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_ban_blkg2.setVisibility(View.INVISIBLE);
                    cb_ban_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_ban_blkg1(false);
//                    upd.first().setTdb_var_ban_blkg1("Tidak Ada");
//                    upd.first().setDb_var_ban_blkg2(false);
//                    upd.first().setTdb_var_ban_blkg2("Rusak");
//                    upd.first().setVdb_var_ban_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_ban_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_ban_blkg2.isChecked()){
                    cb_ban_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_ban_blkg2(true);
//                    upd.first().setTdb_var_ban_blkg2("Baik");
//                    upd.first().setVdb_var_ban_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_ban_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_ban_blkg2(false);
//                    upd.first().setTdb_var_ban_blkg2("Rusak");
//                    upd.first().setVdb_var_ban_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_velg_depan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_velg_depan1.isChecked()){
                    cb_velg_depan1.setText("Ada");
                    cb_velg_depan2.setVisibility(View.VISIBLE);
                    cb_velg_depan2.setText("Rusak");
                    cb_velg_depan2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_velg_depan1(true);
//                    upd.first().setTdb_var_velg_depan1("Ada");
//                    upd.first().setVdb_var_velg_depan2(View.VISIBLE);
//                    upd.first().setTdb_var_velg_depan2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_velg_depan2.setVisibility(View.INVISIBLE);
                    cb_velg_depan1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_velg_depan1(false);
//                    upd.first().setTdb_var_velg_depan1("Tidak Ada");
//                    upd.first().setDb_var_velg_depan2(false);
//                    upd.first().setTdb_var_velg_depan2("Rusak");
//                    upd.first().setVdb_var_velg_depan2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_velg_depan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_velg_depan2.isChecked()){
                    cb_velg_depan2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_velg_depan2(true);
//                    upd.first().setTdb_var_velg_depan2("Baik");
//                    upd.first().setVdb_var_velg_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_velg_depan2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_velg_depan2(false);
//                    upd.first().setTdb_var_velg_depan2("Rusak");
//                    upd.first().setVdb_var_velg_depan2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_velg_blkg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_velg_blkg1.isChecked()){
                    cb_velg_blkg1.setText("Ada");
                    cb_velg_blkg2.setVisibility(View.VISIBLE);
                    cb_velg_blkg2.setText("Rusak");
                    cb_velg_blkg2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_velg_blkg1(true);
//                    upd.first().setTdb_var_velg_blkg1("Ada");
//                    upd.first().setVdb_var_velg_blkg2(View.VISIBLE);
//                    upd.first().setTdb_var_velg_blkg2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_velg_blkg2.setVisibility(View.INVISIBLE);
                    cb_velg_blkg1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_velg_blkg1(false);
//                    upd.first().setTdb_var_velg_blkg1("Tidak Ada");
//                    upd.first().setDb_var_velg_blkg2(false);
//                    upd.first().setTdb_var_velg_blkg2("Rusak");
//                    upd.first().setVdb_var_velg_blkg2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_velg_blkg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_velg_blkg2.isChecked()){
                    cb_velg_blkg2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_velg_blkg2(true);
//                    upd.first().setTdb_var_velg_blkg2("Baik");
//                    upd.first().setVdb_var_velg_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_velg_blkg2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_velg_blkg2(false);
//                    upd.first().setTdb_var_velg_blkg2("Rusak");
//                    upd.first().setVdb_var_velg_blkg2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_kaca_spion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_kaca_spion1.isChecked()){
                    cb_kaca_spion1.setText("Ada");
                    cb_kaca_spion2.setVisibility(View.VISIBLE);
                    cb_kaca_spion2.setText("Rusak");
                    cb_kaca_spion2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_kaca_spion1(true);
//                    upd.first().setTdb_var_kaca_spion1("Ada");
//                    upd.first().setVdb_var_kaca_spion2(View.VISIBLE);
//                    upd.first().setTdb_var_kaca_spion2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_kaca_spion2.setVisibility(View.INVISIBLE);
                    cb_kaca_spion1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_kaca_spion1(false);
//                    upd.first().setTdb_var_kaca_spion1("Tidak Ada");
//                    upd.first().setDb_var_kaca_spion2(false);
//                    upd.first().setTdb_var_kaca_spion2("Rusak");
//                    upd.first().setVdb_var_kaca_spion2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_kaca_spion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_kaca_spion2.isChecked()){
                    cb_kaca_spion2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_kaca_spion2(true);
//                    upd.first().setTdb_var_kaca_spion2("Baik");
//                    upd.first().setVdb_var_kaca_spion2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_kaca_spion2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_kaca_spion2(false);
//                    upd.first().setTdb_var_kaca_spion2("Rusak");
//                    upd.first().setVdb_var_kaca_spion2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_speedometer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_speedometer1.isChecked()){
                    cb_speedometer1.setText("Ada");
                    cb_speedometer2.setVisibility(View.VISIBLE);
                    cb_speedometer2.setText("Rusak");
                    cb_speedometer2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_speedometer1(true);
//                    upd.first().setTdb_var_speedometer1("Ada");
//                    upd.first().setVdb_var_speedometer2(View.VISIBLE);
//                    upd.first().setTdb_var_speedometer2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_speedometer2.setVisibility(View.INVISIBLE);
                    cb_speedometer1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_speedometer1(false);
//                    upd.first().setTdb_var_speedometer1("Tidak Ada");
//                    upd.first().setDb_var_speedometer2(false);
//                    upd.first().setTdb_var_speedometer2("Rusak");
//                    upd.first().setVdb_var_speedometer2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_speedometer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_speedometer2.isChecked()){
                    cb_speedometer2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_speedometer2(true);
//                    upd.first().setTdb_var_speedometer2("Baik");
//                    upd.first().setVdb_var_speedometer2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_speedometer2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_speedometer2(false);
//                    upd.first().setTdb_var_speedometer2("Rusak");
//                    upd.first().setVdb_var_speedometer2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Grip_stang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Grip_stang1.isChecked()){
                    cb_Grip_stang1.setText("Ada");
                    cb_Grip_stang2.setVisibility(View.VISIBLE);
                    cb_Grip_stang2.setText("Rusak");
                    cb_Grip_stang2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Grip_stang1(true);
//                    upd.first().setTdb_var_Grip_stang1("Ada");
//                    upd.first().setVdb_var_Grip_stang2(View.VISIBLE);
//                    upd.first().setTdb_var_Grip_stang2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_Grip_stang2.setVisibility(View.INVISIBLE);
                    cb_Grip_stang1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Grip_stang1(false);
//                    upd.first().setTdb_var_Grip_stang1("Tidak Ada");
//                    upd.first().setDb_var_Grip_stang2(false);
//                    upd.first().setTdb_var_Grip_stang2("Rusak");
//                    upd.first().setVdb_var_Grip_stang2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Grip_stang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Grip_stang2.isChecked()){
                    cb_Grip_stang2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Grip_stang2(true);
//                    upd.first().setTdb_var_Grip_stang2("Baik");
//                    upd.first().setVdb_var_Grip_stang2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_Grip_stang2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Grip_stang2(false);
//                    upd.first().setTdb_var_Grip_stang2("Rusak");
//                    upd.first().setVdb_var_Grip_stang2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Rumah_kunci1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Rumah_kunci1.isChecked()){
                    cb_Rumah_kunci1.setText("Ada");
                    cb_Rumah_kunci2.setVisibility(View.VISIBLE);
                    cb_Rumah_kunci2.setText("Rusak");
                    cb_Rumah_kunci2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Rumah_kunci1(true);
//                    upd.first().setTdb_var_Rumah_kunci1("Ada");
//                    upd.first().setVdb_var_Rumah_kunci2(View.VISIBLE);
//                    upd.first().setTdb_var_Rumah_kunci2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_Rumah_kunci2.setVisibility(View.INVISIBLE);
                    cb_Rumah_kunci1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Rumah_kunci1(false);
//                    upd.first().setTdb_var_Rumah_kunci1("Tidak Ada");
//                    upd.first().setDb_var_Rumah_kunci2(false);
//                    upd.first().setTdb_var_Rumah_kunci2("Rusak");
//                    upd.first().setVdb_var_Rumah_kunci2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Rumah_kunci2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Rumah_kunci2.isChecked()){
                    cb_Rumah_kunci2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Rumah_kunci2(true);
//                    upd.first().setTdb_var_Rumah_kunci2("Baik");
//                    upd.first().setVdb_var_Rumah_kunci2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_Rumah_kunci2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Rumah_kunci2(false);
//                    upd.first().setTdb_var_Rumah_kunci2("Rusak");
//                    upd.first().setVdb_var_Rumah_kunci2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Klakson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Klakson1.isChecked()){
                    cb_Klakson1.setText("Ada");
                    cb_Klakson2.setVisibility(View.VISIBLE);
                    cb_Klakson2.setText("Rusak");
                    cb_Klakson2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Klakson1(true);
//                    upd.first().setTdb_var_Klakson1("Ada");
//                    upd.first().setVdb_var_Klakson2(View.VISIBLE);
//                    upd.first().setTdb_var_Klakson2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_Klakson2.setVisibility(View.INVISIBLE);
                    cb_Klakson1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Klakson1(false);
//                    upd.first().setTdb_var_Klakson1("Tidak Ada");
//                    upd.first().setDb_var_Klakson2(false);
//                    upd.first().setTdb_var_Klakson2("Rusak");
//                    upd.first().setVdb_var_Klakson2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Klakson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Klakson2.isChecked()){
                    cb_Klakson2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Klakson2(true);
//                    upd.first().setTdb_var_Klakson2("Baik");
//                    upd.first().setVdb_var_Klakson2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_Klakson2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Klakson2(false);
//                    upd.first().setTdb_var_Klakson2("Rusak");
//                    upd.first().setVdb_var_Klakson2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Behel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Behel1.isChecked()){
                    cb_Behel1.setText("Ada");
                    cb_Behel2.setVisibility(View.VISIBLE);
                    cb_Behel2.setText("Rusak");
                    cb_Behel2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Behel1(true);
//                    upd.first().setTdb_var_Behel1("Ada");
//                    upd.first().setVdb_var_Behel2(View.VISIBLE);
//                    upd.first().setTdb_var_Behel2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_Behel2.setVisibility(View.INVISIBLE);
                    cb_Behel1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Behel1(false);
//                    upd.first().setTdb_var_Behel1("Tidak Ada");
//                    upd.first().setDb_var_Behel2(false);
//                    upd.first().setTdb_var_Behel2("Rusak");
//                    upd.first().setVdb_var_Behel2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Behel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Behel2.isChecked()){
                    cb_Behel2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Behel2(true);
//                    upd.first().setTdb_var_Behel2("Baik");
//                    upd.first().setVdb_var_Behel2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_Behel2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Behel2(false);
//                    upd.first().setTdb_var_Behel2("Rusak");
//                    upd.first().setVdb_var_Behel2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Tutup_knalpot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Tutup_knalpot1.isChecked()){
                    cb_Tutup_knalpot1.setText("Ada");
                    cb_Tutup_knalpot2.setVisibility(View.VISIBLE);
                    cb_Tutup_knalpot2.setText("Rusak");
                    cb_Tutup_knalpot2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Tutup_knalpot1(true);
//                    upd.first().setTdb_var_Tutup_knalpot1("Ada");
//                    upd.first().setVdb_var_Tutup_knalpot2(View.VISIBLE);
//                    upd.first().setTdb_var_Tutup_knalpot2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_Tutup_knalpot2.setVisibility(View.INVISIBLE);
                    cb_Tutup_knalpot1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Tutup_knalpot1(false);
//                    upd.first().setTdb_var_Tutup_knalpot1("Tidak Ada");
//                    upd.first().setDb_var_Tutup_knalpot2(false);
//                    upd.first().setTdb_var_Tutup_knalpot2("Rusak");
//                    upd.first().setVdb_var_Tutup_knalpot2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Tutup_knalpot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Tutup_knalpot2.isChecked()){
                    cb_Tutup_knalpot2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Tutup_knalpot2(true);
//                    upd.first().setTdb_var_Tutup_knalpot2("Baik");
//                    upd.first().setVdb_var_Tutup_knalpot2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_Tutup_knalpot2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Tutup_knalpot2(false);
//                    upd.first().setTdb_var_Tutup_knalpot2("Rusak");
//                    upd.first().setVdb_var_Tutup_knalpot2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_jok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_jok1.isChecked()){
                    cb_jok1.setText("Ada");
                    cb_jok2.setVisibility(View.VISIBLE);
                    cb_jok2.setText("Rusak");
                    cb_jok2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_jok1(true);
//                    upd.first().setTdb_var_jok1("Ada");
//                    upd.first().setVdb_var_jok2(View.VISIBLE);
//                    upd.first().setTdb_var_jok2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_jok2.setVisibility(View.INVISIBLE);
                    cb_jok1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_jok1(false);
//                    upd.first().setTdb_var_jok1("Tidak Ada");
//                    upd.first().setDb_var_jok2(false);
//                    upd.first().setTdb_var_jok2("Rusak");
//                    upd.first().setVdb_var_jok2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_jok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_jok2.isChecked()){
                    cb_jok2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_jok2(true);
//                    upd.first().setTdb_var_jok2("Baik");
//                    upd.first().setVdb_var_jok2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_jok2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_jok2(false);
//                    upd.first().setTdb_var_jok2("Rusak");
//                    upd.first().setVdb_var_jok2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Stripping1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Stripping1.isChecked()){
                    cb_Stripping1.setText("Ada");
                    cb_Stripping2.setVisibility(View.VISIBLE);
                    cb_Stripping2.setText("Rusak");
                    cb_Stripping2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Stripping1(true);
//                    upd.first().setTdb_var_Stripping1("Ada");
//                    upd.first().setVdb_var_Stripping2(View.VISIBLE);
//                    upd.first().setTdb_var_Stripping2("Rusak");
//                    realm.commitTransaction();
                }else {
                    cb_Stripping2.setVisibility(View.INVISIBLE);
                    cb_Stripping1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Stripping1(false);
//                    upd.first().setTdb_var_Stripping1("Tidak Ada");
//                    upd.first().setDb_var_Stripping2(false);
//                    upd.first().setTdb_var_Stripping2("Rusak");
//                    upd.first().setVdb_var_Stripping2(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Stripping2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Stripping2.isChecked()){
                    cb_Stripping2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Stripping2(true);
//                    upd.first().setTdb_var_Stripping2("Baik");
//                    upd.first().setVdb_var_Stripping2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_Stripping2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Stripping2(false);
//                    upd.first().setTdb_var_Stripping2("Rusak");
//                    upd.first().setVdb_var_Stripping2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cb_Tombol_navigasi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Tombol_navigasi1.isChecked()){
                    cb_Tombol_navigasi1.setText("Ada");
                    cb_Tombol_navigasi2.setVisibility(View.VISIBLE);
                    cb_Tombol_navigasi2.setText("Rusak");
                    cb_Tombol_navigasi2.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Tombol_navigasi1(true);
//                    upd.first().setTdb_var_Tombol_navigasi1("Ada");
//                    upd.first().setVdb_var_Tombol_navigasi2(View.VISIBLE);
//                    upd.first().setTdb_var_Tombol_navigasi2("Rusak");
//                    realm.commitTransaction();

                }else {
                    cb_Tombol_navigasi2.setVisibility(View.INVISIBLE);
                    cb_Tombol_navigasi1.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Tombol_navigasi1(false);
//                    upd.first().setTdb_var_Tombol_navigasi1("Tidak Ada");
//                    upd.first().setDb_var_Tombol_navigasi2(false);
//                    upd.first().setTdb_var_Tombol_navigasi2("Rusak");
//                    upd.first().setVdb_var_Tombol_navigasi2(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cb_Tombol_navigasi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Tombol_navigasi2.isChecked()){
                    cb_Tombol_navigasi2.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Tombol_navigasi2(true);
//                    upd.first().setTdb_var_Tombol_navigasi2("Baik");
//                    upd.first().setVdb_var_Tombol_navigasi2(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cb_Tombol_navigasi2.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_Tombol_navigasi2(false);
//                    upd.first().setTdb_var_Tombol_navigasi2("Rusak");
//                    upd.first().setVdb_var_Tombol_navigasi2(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

    }



    private void sendCeklist() {
        class sendCeklist extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MobInputCeklistMotorActivity.this,"","Processing...",false,false);
            }
            @Override
            protected void onPostExecute(String res) {
                super.onPostExecute(res);
                loading.dismiss();
                Log.d("onPostExecute", "Response: " + res);
                showData(res);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,appid);
                hashMap.put(configuration.KEY_CCODE,ccode);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_STATUS,"0");
                hashMap.put(configuration.KEY_PARENT_STATUS,"1");

                hashMap.put(configuration.KEY_nopol,var_nopol);

                hashMap.put(configuration.MOBMOTOR_cover_kepala1, String.valueOf(cb_cover_kepala1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_kepala2, String.valueOf(cb_cover_kepala2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_speedo1, String.valueOf(cb_cover_speedo1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_speedo2, String.valueOf(cb_cover_speedo2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_depan1, String.valueOf(cb_cover_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_depan2, String.valueOf(cb_cover_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_sayap_kanan1, String.valueOf(cb_sayap_kanan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_sayap_kanan2, String.valueOf(cb_sayap_kanan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_sayap_kiri1, String.valueOf(cb_sayap_kiri1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_sayap_kiri2, String.valueOf(cb_sayap_kiri2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_spak_depan1, String.valueOf(cb_spak_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_spak_depan2, String.valueOf(cb_spak_depan2.isChecked()));


                hashMap.put(configuration.MOBMOTOR_cover_tengah1, String.valueOf(cb_cover_tengah1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_tengah2, String.valueOf(cb_cover_tengah2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_spgkanan1, String.valueOf(cb_cover_spgkanan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_spgkanan2, String.valueOf(cb_cover_spgkanan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_spgkiri1, String.valueOf(cb_cover_spgkiri1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_spgkiri2, String.valueOf(cb_cover_spgkiri2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_rantai_depan1, String.valueOf(cb_rantai_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_rantai_depan2, String.valueOf(cb_rantai_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_rantai_blkg1, String.valueOf(cb_rantai_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_rantai_blkg2, String.valueOf(cb_rantai_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_spak_blkg1, String.valueOf(cb_spak_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_spak_blkg2, String.valueOf(cb_spak_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_accu1, String.valueOf(cb_cover_accu1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_accu2, String.valueOf(cb_cover_accu2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_lampurem1, String.valueOf(cb_cover_lampurem1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_lampurem2, String.valueOf(cb_cover_lampurem2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_tangki_bensin1, String.valueOf(cb_tangki_bensin1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_tangki_bensin2, String.valueOf(cb_tangki_bensin2.isChecked()));


                hashMap.put(configuration.MOBMOTOR_cover_bawah1, String.valueOf(cb_cover_bawah1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_bawah2, String.valueOf(cb_cover_bawah2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_dek_desk1, String.valueOf(cb_dek_desk1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_dek_desk2, String.valueOf(cb_dek_desk2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_mesin1, String.valueOf(cb_cover_mesin1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cover_mesin2, String.valueOf(cb_cover_mesin2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_emblem1, String.valueOf(cb_emblem1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_emblem2, String.valueOf(cb_emblem2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_blok_mesin1, String.valueOf(cb_blok_mesin1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_blok_mesin2, String.valueOf(cb_blok_mesin2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_carburator1, String.valueOf(cb_carburator1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_carburator2, String.valueOf(cb_carburator2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_filter_carbu1, String.valueOf(cb_filter_carbu1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_filter_carbu2, String.valueOf(cb_filter_carbu2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_busi1, String.valueOf(cb_busi1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_busi2, String.valueOf(cb_busi2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cdi1, String.valueOf(cb_cdi1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cdi2, String.valueOf(cb_cdi2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_accu1, String.valueOf(cb_accu1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_accu2, String.valueOf(cb_accu2.isChecked()));


                hashMap.put(configuration.MOBMOTOR_kick_starter1, String.valueOf(cb_kick_starter1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_kick_starter2, String.valueOf(cb_kick_starter2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_perseneling1, String.valueOf(cb_perseneling1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_perseneling2, String.valueOf(cb_perseneling2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_knalpot1, String.valueOf(cb_knalpot1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_knalpot2, String.valueOf(cb_knalpot2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_coil1, String.valueOf(cb_coil1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_coil2, String.valueOf(cb_coil2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_blok1, String.valueOf(cb_blok1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_blok2, String.valueOf(cb_blok2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_tutupcvt1, String.valueOf(cb_tutupcvt1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_tutupcvt2, String.valueOf(cb_tutupcvt2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_radiator1, String.valueOf(cb_radiator1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_radiator2, String.valueOf(cb_radiator2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_lampu_depan1, String.valueOf(cb_lampu_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_lampu_depan2, String.valueOf(cb_lampu_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_sein_depan1, String.valueOf(cb_sein_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_sein_depan2, String.valueOf(cb_sein_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_sein_blkg1, String.valueOf(cb_sein_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_sein_blkg2, String.valueOf(cb_sein_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_lampu_rem1, String.valueOf(cb_lampu_rem1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_lampu_rem2, String.valueOf(cb_lampu_rem2.isChecked()));



                hashMap.put(configuration.MOBMOTOR_handlerem_depan1, String.valueOf(cb_handlerem_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_handlerem_depan2, String.valueOf(cb_handlerem_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_pedalrem_blkg1, String.valueOf(cb_pedalrem_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_pedalrem_blkg2, String.valueOf(cb_pedalrem_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_handlerem_blkg1, String.valueOf(cb_handlerem_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_handlerem_blkg2, String.valueOf(cb_handlerem_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_handle_kopling1, String.valueOf(cb_handle_kopling1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_handle_kopling2, String.valueOf(cb_handle_kopling2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_master_rem1, String.valueOf(cb_master_rem1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_master_rem2, String.valueOf(cb_master_rem2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_caliper1, String.valueOf(cb_caliper1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_caliper2, String.valueOf(cb_caliper2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cakram1, String.valueOf(cb_cakram1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_cakram2, String.valueOf(cb_cakram2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_shock_depan1, String.valueOf(cb_shock_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_shock_depan2, String.valueOf(cb_shock_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_shock_blkg1, String.valueOf(cb_shock_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_shock_blkg2, String.valueOf(cb_shock_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_tromol_depan1, String.valueOf(cb_tromol_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_tromol_depan2, String.valueOf(cb_tromol_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_tromol_blkg1, String.valueOf(cb_tromol_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_tromol_blkg2, String.valueOf(cb_tromol_blkg2.isChecked()));


                hashMap.put(configuration.MOBMOTOR_footstep_depan1, String.valueOf(cb_footstep_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_footstep_depan2, String.valueOf(cb_footstep_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_footstep_blkg1, String.valueOf(cb_footstep_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_footstep_blkg2, String.valueOf(cb_footstep_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_swing_arm1, String.valueOf(cb_swing_arm1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_swing_arm2, String.valueOf(cb_swing_arm2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_rantai_fanbelt1, String.valueOf(cb_rantai_fanbelt1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_rantai_fanbelt2, String.valueOf(cb_rantai_fanbelt2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_gear_depan1, String.valueOf(cb_gear_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_gear_depan2, String.valueOf(cb_gear_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_gear_blkg1, String.valueOf(cb_gear_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_gear_blkg2, String.valueOf(cb_gear_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_standar_tengah1, String.valueOf(cb_standar_tengah1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_standar_tengah2, String.valueOf(cb_standar_tengah2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_standar_samping1, String.valueOf(cb_standar_samping1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_standar_samping2, String.valueOf(cb_standar_samping2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_ban_depan1, String.valueOf(cb_ban_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_ban_depan2, String.valueOf(cb_ban_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_ban_blkg1, String.valueOf(cb_ban_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_ban_blkg2, String.valueOf(cb_ban_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_velg_depan1, String.valueOf(cb_velg_depan1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_velg_depan2, String.valueOf(cb_velg_depan2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_velg_blkg1, String.valueOf(cb_velg_blkg1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_velg_blkg2, String.valueOf(cb_velg_blkg2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_kaca_spion1, String.valueOf(cb_kaca_spion1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_kaca_spion2, String.valueOf(cb_kaca_spion2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_speedometer1, String.valueOf(cb_speedometer1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_speedometer2, String.valueOf(cb_speedometer2.isChecked()));


                hashMap.put(configuration.MOBMOTOR_Grip_stang1, String.valueOf(cb_Grip_stang1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Grip_stang2, String.valueOf(cb_Grip_stang2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Rumah_kunci1, String.valueOf(cb_Rumah_kunci1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Rumah_kunci2, String.valueOf(cb_Rumah_kunci2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Klakson1, String.valueOf(cb_Klakson1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Klakson2, String.valueOf(cb_Klakson2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Behel1, String.valueOf(cb_Behel1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Behel2, String.valueOf(cb_Behel2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Tutup_knalpot1, String.valueOf(cb_Tutup_knalpot1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Tutup_knalpot2, String.valueOf(cb_Tutup_knalpot2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_jok1, String.valueOf(cb_jok1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_jok2, String.valueOf(cb_jok2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Stripping1, String.valueOf(cb_Stripping1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Stripping2, String.valueOf(cb_Stripping2.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Tombol_navigasi1, String.valueOf(cb_Tombol_navigasi1.isChecked()));
                hashMap.put(configuration.MOBMOTOR_Tombol_navigasi2, String.valueOf(cb_Tombol_navigasi2.isChecked()));


                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(configuration.URL_BASTK_MOB_MOTOR,hashMap);
                Log.d("kirims", s);
                return s;
            }
        }
        sendCeklist ge = new sendCeklist();
        ge.execute();
    }
    
//    private void showData(String json){
//        try {
//            if (json != null && !json.isEmpty()) { // Periksa apakah string JSON tidak kosong
//                JSONObject jsonObject = new JSONObject(json);
//                Log.d("cekjson", "hasil" + jsonObject);
//                code = jsonObject.getInt(configuration.TAG_CODE);
//                message = jsonObject.getString(configuration.TAG_MESSAGE);
//                Log.wtf("cekmessage", "hasil" + message);
//                if (code == 200) {
//                    Intent i = new Intent(MobInputCeklistMotorActivity.this, MobInputFotoActivity.class);
//
//                    Bundle b = new Bundle();
//                    b.putString("parse_nopol", var_nopol);
//                    i.putExtras(b);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                } else {
//                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//                }
//            } catch (JSONException e) {
//            e.printStackTrace();
//            Toast.makeText(MobInputCeklistMotorActivity.this,"Check Your Connection",Toast.LENGTH_SHORT).show();
//        }
//    }

    private void getBastk(){
        class GetBastk extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MobInputCeklistMotorActivity.this,"","Loading...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showCeklist(s);
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
                return s;
            }
        }
        GetBastk ge = new GetBastk();
        ge.execute();
    }

    private void showCeklist(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
            JSONArray result = jsonObject.getJSONArray(configuration.TAG_JSON_DATA);
            JSONObject user = result.getJSONObject(0);
//            Log.wtf("user",":" + user);

//            //================= Variable Ceklist ======================================
            var_cover_kepala1 = user.getInt(configuration.MOBMOTOR_cover_kepala1);
            var_cover_kepala2 = user.getInt(configuration.MOBMOTOR_cover_kepala2);
            var_cover_speedo1 = user.getInt(configuration.MOBMOTOR_cover_speedo1);
            var_cover_speedo2 = user.getInt(configuration.MOBMOTOR_cover_speedo2);
            var_cover_depan1 = user.getInt(configuration.MOBMOTOR_cover_depan1);
            var_cover_depan2 = user.getInt(configuration.MOBMOTOR_cover_depan2);
            var_sayap_kanan1 = user.getInt(configuration.MOBMOTOR_sayap_kanan1);
            var_sayap_kanan2 = user.getInt(configuration.MOBMOTOR_sayap_kanan2);
            var_sayap_kiri1 = user.getInt(configuration.MOBMOTOR_sayap_kiri1);
            var_sayap_kiri2 = user.getInt(configuration.MOBMOTOR_sayap_kiri2);
            var_spak_depan1 = user.getInt(configuration.MOBMOTOR_spak_depan1);
            var_spak_depan2 = user.getInt(configuration.MOBMOTOR_spak_depan2);

            var_cover_tengah1 = user.getInt(configuration.MOBMOTOR_cover_tengah1);
            var_cover_tengah2 = user.getInt(configuration.MOBMOTOR_cover_tengah2);
            var_cover_spgkanan1 = user.getInt(configuration.MOBMOTOR_cover_spgkanan1);
            var_cover_spgkanan2 = user.getInt(configuration.MOBMOTOR_cover_spgkanan2);
            var_cover_spgkiri1 = user.getInt(configuration.MOBMOTOR_cover_spgkiri1);
            var_cover_spgkiri2 = user.getInt(configuration.MOBMOTOR_cover_spgkiri2);
            var_rantai_depan1 = user.getInt(configuration.MOBMOTOR_rantai_depan1);
            var_rantai_depan2 = user.getInt(configuration.MOBMOTOR_rantai_depan2);
            var_rantai_blkg1 = user.getInt(configuration.MOBMOTOR_rantai_blkg1);
            var_rantai_blkg2 = user.getInt(configuration.MOBMOTOR_rantai_blkg2);
            var_spak_blkg1 = user.getInt(configuration.MOBMOTOR_spak_blkg1);
            var_spak_blkg2 = user.getInt(configuration.MOBMOTOR_spak_blkg2);
            var_cover_accu1 = user.getInt(configuration.MOBMOTOR_cover_accu1);
            var_cover_accu2 = user.getInt(configuration.MOBMOTOR_cover_accu2);
            var_cover_lampurem1 = user.getInt(configuration.MOBMOTOR_cover_lampurem1);
            var_cover_lampurem2 = user.getInt(configuration.MOBMOTOR_cover_lampurem2);
            var_tangki_bensin1 = user.getInt(configuration.MOBMOTOR_tangki_bensin1);
            var_tangki_bensin2 = user.getInt(configuration.MOBMOTOR_tangki_bensin2);
            var_cover_bawah1 = user.getInt(configuration.MOBMOTOR_cover_bawah1);
            var_cover_bawah2 = user.getInt(configuration.MOBMOTOR_cover_bawah2);
            var_dek_desk1 = user.getInt(configuration.MOBMOTOR_dek_desk1);
            var_dek_desk2 = user.getInt(configuration.MOBMOTOR_dek_desk2);
            var_cover_mesin1 = user.getInt(configuration.MOBMOTOR_cover_mesin1);
            var_cover_mesin2 = user.getInt(configuration.MOBMOTOR_cover_mesin2);
            var_emblem1 = user.getInt(configuration.MOBMOTOR_emblem1);
            var_emblem2 = user.getInt(configuration.MOBMOTOR_emblem2);
            var_blok_mesin1 = user.getInt(configuration.MOBMOTOR_blok_mesin1);
            var_blok_mesin2 = user.getInt(configuration.MOBMOTOR_blok_mesin2);
            var_carburator1 = user.getInt(configuration.MOBMOTOR_carburator1);
            var_carburator2 = user.getInt(configuration.MOBMOTOR_carburator2);
            var_filter_carbu1 = user.getInt(configuration.MOBMOTOR_filter_carbu1);
            var_filter_carbu2 = user.getInt(configuration.MOBMOTOR_filter_carbu2);
            var_busi1 = user.getInt(configuration.MOBMOTOR_busi1);
            var_busi2 = user.getInt(configuration.MOBMOTOR_busi2);
            var_cdi1 = user.getInt(configuration.MOBMOTOR_cdi1);
            var_cdi2 = user.getInt(configuration.MOBMOTOR_cdi2);
            var_accu1 = user.getInt(configuration.MOBMOTOR_accu1);
            var_accu2 = user.getInt(configuration.MOBMOTOR_accu2);
            var_kick_starter1 = user.getInt(configuration.MOBMOTOR_kick_starter1);
            var_kick_starter2 = user.getInt(configuration.MOBMOTOR_kick_starter2);
            var_perseneling1 = user.getInt(configuration.MOBMOTOR_perseneling1);
            var_perseneling2 = user.getInt(configuration.MOBMOTOR_perseneling2);
            var_knalpot1 = user.getInt(configuration.MOBMOTOR_knalpot1);
            var_knalpot2 = user.getInt(configuration.MOBMOTOR_knalpot2);
            var_coil1 = user.getInt(configuration.MOBMOTOR_coil1);
            var_coil2 = user.getInt(configuration.MOBMOTOR_coil2);
            var_blok1 = user.getInt(configuration.MOBMOTOR_blok1);
            var_blok2 = user.getInt(configuration.MOBMOTOR_blok2);
            var_tutupcvt1 = user.getInt(configuration.MOBMOTOR_tutupcvt1);
            var_tutupcvt2 = user.getInt(configuration.MOBMOTOR_tutupcvt2);
            var_radiator1 = user.getInt(configuration.MOBMOTOR_radiator1);
            var_radiator2 = user.getInt(configuration.MOBMOTOR_radiator2);
            var_lampu_depan1 = user.getInt(configuration.MOBMOTOR_lampu_depan1);
            var_lampu_depan2 = user.getInt(configuration.MOBMOTOR_lampu_depan2);
            var_sein_depan1 = user.getInt(configuration.MOBMOTOR_sein_depan1);
            var_sein_depan2 = user.getInt(configuration.MOBMOTOR_sein_depan2);
            var_sein_blkg1 = user.getInt(configuration.MOBMOTOR_sein_blkg1);
            var_sein_blkg2 = user.getInt(configuration.MOBMOTOR_sein_blkg2);
            var_lampu_rem1 = user.getInt(configuration.MOBMOTOR_lampu_rem1);
            var_lampu_rem2 = user.getInt(configuration.MOBMOTOR_lampu_rem2);
            var_handlerem_depan1 = user.getInt(configuration.MOBMOTOR_handlerem_depan1);
            var_handlerem_depan2 = user.getInt(configuration.MOBMOTOR_handlerem_depan2);
            var_pedalrem_blkg1 = user.getInt(configuration.MOBMOTOR_pedalrem_blkg1);
            var_pedalrem_blkg2 = user.getInt(configuration.MOBMOTOR_pedalrem_blkg2);
            var_handlerem_blkg1 = user.getInt(configuration.MOBMOTOR_handlerem_blkg1);
            var_handlerem_blkg2 = user.getInt(configuration.MOBMOTOR_handlerem_blkg2);
            var_handle_kopling1 = user.getInt(configuration.MOBMOTOR_handle_kopling1);
            var_handle_kopling2 = user.getInt(configuration.MOBMOTOR_handle_kopling2);
            var_master_rem1 = user.getInt(configuration.MOBMOTOR_master_rem1);
            var_master_rem2 = user.getInt(configuration.MOBMOTOR_master_rem2);
            var_caliper1 = user.getInt(configuration.MOBMOTOR_caliper1);
            var_caliper2 = user.getInt(configuration.MOBMOTOR_caliper2);
            var_cakram1 = user.getInt(configuration.MOBMOTOR_cakram1);
            var_cakram2 = user.getInt(configuration.MOBMOTOR_cakram2);
            var_shock_depan1 = user.getInt(configuration.MOBMOTOR_shock_depan1);
            var_shock_depan2 = user.getInt(configuration.MOBMOTOR_shock_depan2);
            var_shock_blkg1 = user.getInt(configuration.MOBMOTOR_shock_blkg1);
            var_shock_blkg2 = user.getInt(configuration.MOBMOTOR_shock_blkg2);
            var_tromol_depan1 = user.getInt(configuration.MOBMOTOR_tromol_depan1);
            var_tromol_depan2 = user.getInt(configuration.MOBMOTOR_tromol_depan2);
            var_tromol_blkg1 = user.getInt(configuration.MOBMOTOR_tromol_blkg1);
            var_tromol_blkg2 = user.getInt(configuration.MOBMOTOR_tromol_blkg2);
            var_footstep_depan1 = user.getInt(configuration.MOBMOTOR_footstep_depan1);
            var_footstep_depan2 = user.getInt(configuration.MOBMOTOR_footstep_depan2);
            var_footstep_blkg1 = user.getInt(configuration.MOBMOTOR_footstep_blkg1);
            var_footstep_blkg2 = user.getInt(configuration.MOBMOTOR_footstep_blkg2);
            var_swing_arm1 = user.getInt(configuration.MOBMOTOR_swing_arm1);
            var_swing_arm2 = user.getInt(configuration.MOBMOTOR_swing_arm2);
            var_rantai_fanbelt1 = user.getInt(configuration.MOBMOTOR_rantai_fanbelt1);
            var_rantai_fanbelt2 = user.getInt(configuration.MOBMOTOR_rantai_fanbelt2);
            var_gear_depan1 = user.getInt(configuration.MOBMOTOR_gear_depan1);
            var_gear_depan2 = user.getInt(configuration.MOBMOTOR_gear_depan2);
            var_gear_blkg1 = user.getInt(configuration.MOBMOTOR_gear_blkg1);
            var_gear_blkg2 = user.getInt(configuration.MOBMOTOR_gear_blkg2);
            var_standar_tengah1 = user.getInt(configuration.MOBMOTOR_standar_tengah1);
            var_standar_tengah2 = user.getInt(configuration.MOBMOTOR_standar_tengah2);
            var_standar_samping1 = user.getInt(configuration.MOBMOTOR_standar_samping1);
            var_standar_samping2 = user.getInt(configuration.MOBMOTOR_standar_samping2);
            var_ban_depan1 = user.getInt(configuration.MOBMOTOR_ban_depan1);
            var_ban_depan2 = user.getInt(configuration.MOBMOTOR_ban_depan2);
            var_ban_blkg1 = user.getInt(configuration.MOBMOTOR_ban_blkg1);
            var_ban_blkg2 = user.getInt(configuration.MOBMOTOR_ban_blkg2);
            var_velg_depan1 = user.getInt(configuration.MOBMOTOR_velg_depan1);
            var_velg_depan2 = user.getInt(configuration.MOBMOTOR_velg_depan2);
            var_velg_blkg1 = user.getInt(configuration.MOBMOTOR_velg_blkg1);
            var_velg_blkg2 = user.getInt(configuration.MOBMOTOR_velg_blkg2);
            var_kaca_spion1 = user.getInt(configuration.MOBMOTOR_kaca_spion1);
            var_kaca_spion2 = user.getInt(configuration.MOBMOTOR_kaca_spion2);
            var_speedometer1 = user.getInt(configuration.MOBMOTOR_speedometer1);
            var_speedometer2 = user.getInt(configuration.MOBMOTOR_speedometer2);
            var_grip_stang1 = user.getInt(configuration.MOBMOTOR_Grip_stang1);
            var_grip_stang2 = user.getInt(configuration.MOBMOTOR_Grip_stang2);
            var_rumah_kunci1 = user.getInt(configuration.MOBMOTOR_Rumah_kunci1);
            var_rumah_kunci2 = user.getInt(configuration.MOBMOTOR_Rumah_kunci2);
            var_klakson1 = user.getInt(configuration.MOBMOTOR_Klakson1);
            var_klakson2 = user.getInt(configuration.MOBMOTOR_Klakson2);
            var_behel1 = user.getInt(configuration.MOBMOTOR_Behel1);
            var_behel2 = user.getInt(configuration.MOBMOTOR_Behel2);
            var_tutup_knalpot1 = user.getInt(configuration.MOBMOTOR_Tutup_knalpot1);
            var_tutup_knalpot2 = user.getInt(configuration.MOBMOTOR_Tutup_knalpot2);
            var_jok1 = user.getInt(configuration.MOBMOTOR_jok1);
            var_jok2 = user.getInt(configuration.MOBMOTOR_jok1);
            var_stripping1 = user.getInt(configuration.MOBMOTOR_Stripping1);
            var_stripping2 = user.getInt(configuration.MOBMOTOR_Stripping2);
            var_tombol_navigasi1 = user.getInt(configuration.MOBMOTOR_Tombol_navigasi1);
            var_tombol_navigasi2 = user.getInt(configuration.MOBMOTOR_Tombol_navigasi2);

            //================== set checkbox ====================================
            cb_cover_kepala1.setChecked(var_cover_kepala1 == 0 ? false : true);
            cb_cover_kepala2.setChecked(var_cover_kepala2 == 0 ? false : true);
            cb_cover_speedo1.setChecked(var_cover_speedo1 == 0 ? false : true);
            cb_cover_speedo2.setChecked(var_cover_speedo2 == 0 ? false : true);
            cb_cover_depan1.setChecked(var_cover_depan1 == 0 ? false : true);
            cb_cover_depan2.setChecked(var_cover_depan2 == 0 ? false : true);
            cb_sayap_kanan1.setChecked(var_sayap_kanan1 == 0 ? false : true);
            cb_sayap_kanan2.setChecked(var_sayap_kanan2 == 0 ? false : true);
            cb_sayap_kiri1.setChecked(var_sayap_kiri1 == 0 ? false : true);
            cb_sayap_kiri2.setChecked(var_sayap_kiri2 == 0 ? false : true);
            cb_spak_depan1.setChecked(var_spak_depan1 == 0 ? false : true);
            cb_spak_depan2.setChecked(var_spak_depan2 == 0 ? false : true);
            cb_cover_tengah1.setChecked(var_cover_tengah1 == 0 ? false : true);
            cb_cover_tengah2.setChecked(var_cover_tengah2 == 0 ? false : true);
            cb_cover_spgkanan1.setChecked(var_cover_spgkanan1 == 0 ? false : true);
            cb_cover_spgkanan2.setChecked(var_cover_spgkanan2 == 0 ? false : true);
            cb_cover_spgkiri1.setChecked(var_cover_spgkiri1 == 0 ? false : true);
            cb_cover_spgkiri2.setChecked(var_cover_spgkiri2 == 0 ? false : true);
            cb_rantai_depan1.setChecked(var_rantai_depan1 == 0 ? false : true);
            cb_rantai_depan2.setChecked(var_rantai_depan2 == 0 ? false : true);
            cb_rantai_blkg1.setChecked(var_rantai_blkg1 == 0 ? false : true);
            cb_rantai_blkg2.setChecked(var_rantai_blkg2 == 0 ? false : true);
            cb_spak_blkg1.setChecked(var_spak_blkg1 == 0 ? false : true);
            cb_spak_blkg2.setChecked(var_spak_blkg2 == 0 ? false : true);
            cb_cover_accu1.setChecked(var_cover_accu1 == 0 ? false : true);
            cb_cover_accu2.setChecked(var_cover_accu2 == 0 ? false : true);
            cb_cover_lampurem1.setChecked(var_cover_lampurem1 == 0 ? false : true);
            cb_cover_lampurem2.setChecked(var_cover_lampurem2 == 0 ? false : true);
            cb_tangki_bensin1.setChecked(var_tangki_bensin1 == 0 ? false : true);
            cb_tangki_bensin2.setChecked(var_tangki_bensin2 == 0 ? false : true);
            cb_cover_bawah1.setChecked(var_cover_bawah1 == 0 ? false : true);
            cb_cover_bawah2.setChecked(var_cover_bawah2 == 0 ? false : true);
            cb_dek_desk1.setChecked(var_dek_desk1 == 0 ? false : true);
            cb_dek_desk2.setChecked(var_dek_desk2 == 0 ? false : true);
            cb_cover_mesin1.setChecked(var_cover_mesin1 == 0 ? false : true);
            cb_cover_mesin2.setChecked(var_cover_mesin2 == 0 ? false : true);
            cb_emblem1.setChecked(var_emblem1 == 0 ? false : true);
            cb_emblem2.setChecked(var_emblem2 == 0 ? false : true);
            cb_blok_mesin1.setChecked(var_blok_mesin1 == 0 ? false : true);
            cb_blok_mesin2.setChecked(var_blok_mesin2 == 0 ? false : true);
            cb_carburator1.setChecked(var_carburator1 == 0 ? false : true);
            cb_carburator2.setChecked(var_carburator2 == 0 ? false : true);
            cb_filter_carbu1.setChecked(var_filter_carbu1 == 0 ? false : true);
            cb_filter_carbu2.setChecked(var_filter_carbu2 == 0 ? false : true);
            cb_busi1.setChecked(var_busi1 == 0 ? false : true);
            cb_busi2.setChecked(var_busi2 == 0 ? false : true);
            cb_cdi1.setChecked(var_cdi1 == 0 ? false : true);
            cb_cdi2.setChecked(var_cdi2 == 0 ? false : true);
            cb_accu1.setChecked(var_accu1 == 0 ? false : true);
            cb_accu2.setChecked(var_accu2 == 0 ? false : true);
            cb_kick_starter1.setChecked(var_kick_starter1 == 0 ? false : true);
            cb_kick_starter2.setChecked(var_kick_starter2 == 0 ? false : true);
            cb_perseneling1.setChecked(var_perseneling1 == 0 ? false : true);
            cb_perseneling2.setChecked(var_perseneling2 == 0 ? false : true);
            cb_knalpot1.setChecked(var_knalpot1 == 0 ? false : true);
            cb_knalpot2.setChecked(var_knalpot2 == 0 ? false : true);
            cb_coil1.setChecked(var_coil1 == 0 ? false : true);
            cb_coil2.setChecked(var_coil2 == 0 ? false : true);
            cb_blok1.setChecked(var_blok1 == 0 ? false : true);
            cb_blok2.setChecked(var_blok2 == 0 ? false : true);
            cb_tutupcvt1.setChecked(var_tutupcvt1 == 0 ? false : true);
            cb_tutupcvt2.setChecked(var_tutupcvt2 == 0 ? false : true);
            cb_radiator1.setChecked(var_radiator1 == 0 ? false : true);
            cb_radiator2.setChecked(var_radiator2 == 0 ? false : true);
            cb_lampu_depan1.setChecked(var_lampu_depan1 == 0 ? false : true);
            cb_lampu_depan2.setChecked(var_lampu_depan2 == 0 ? false : true);
            cb_sein_depan1.setChecked(var_sein_depan1 == 0 ? false : true);
            cb_sein_depan2.setChecked(var_sein_depan2 == 0 ? false : true);
            cb_sein_blkg1.setChecked(var_sein_blkg1 == 0 ? false : true);
            cb_sein_blkg2.setChecked(var_sein_blkg2 == 0 ? false : true);
            cb_lampu_rem1.setChecked(var_lampu_rem1 == 0 ? false : true);
            cb_lampu_rem2.setChecked(var_lampu_rem2 == 0 ? false : true);
            cb_handlerem_depan1.setChecked(var_handlerem_depan1 == 0 ? false : true);
            cb_handlerem_depan2.setChecked(var_handlerem_depan2 == 0 ? false : true);
            cb_pedalrem_blkg1.setChecked(var_pedalrem_blkg1 == 0 ? false : true);
            cb_pedalrem_blkg2.setChecked(var_pedalrem_blkg2 == 0 ? false : true);
            cb_handlerem_blkg1.setChecked(var_handlerem_blkg1 == 0 ? false : true);
            cb_handlerem_blkg2.setChecked(var_handlerem_blkg2 == 0 ? false : true);
            cb_handle_kopling1.setChecked(var_handle_kopling1 == 0 ? false : true);
            cb_handle_kopling2.setChecked(var_handle_kopling2 == 0 ? false : true);
            cb_master_rem1.setChecked(var_master_rem1 == 0 ? false : true);
            cb_master_rem2.setChecked(var_master_rem2 == 0 ? false : true);
            cb_caliper1.setChecked(var_caliper1 == 0 ? false : true);
            cb_caliper2.setChecked(var_caliper2 == 0 ? false : true);
            cb_cakram1.setChecked(var_cakram1 == 0 ? false : true);
            cb_cakram2.setChecked(var_cakram2 == 0 ? false : true);
            cb_shock_depan1.setChecked(var_shock_depan1 == 0 ? false : true);
            cb_shock_depan2.setChecked(var_shock_depan2 == 0 ? false : true);
            cb_shock_blkg1.setChecked(var_shock_blkg1 == 0 ? false : true);
            cb_shock_blkg2.setChecked(var_shock_blkg2 == 0 ? false : true);
            cb_tromol_depan1.setChecked(var_tromol_depan1 == 0 ? false : true);
            cb_tromol_depan2.setChecked(var_tromol_depan2 == 0 ? false : true);
            cb_tromol_blkg1.setChecked(var_tromol_blkg1 == 0 ? false : true);
            cb_tromol_blkg2.setChecked(var_tromol_blkg2 == 0 ? false : true);
            cb_footstep_depan1.setChecked(var_footstep_depan1 == 0 ? false : true);
            cb_footstep_depan2.setChecked(var_footstep_depan2 == 0 ? false : true);
            cb_footstep_blkg1.setChecked(var_footstep_blkg1 == 0 ? false : true);
            cb_footstep_blkg2.setChecked(var_footstep_blkg2 == 0 ? false : true);
            cb_swing_arm1.setChecked(var_swing_arm1 == 0 ? false : true);
            cb_swing_arm2.setChecked(var_swing_arm2 == 0 ? false : true);
            cb_rantai_fanbelt1.setChecked(var_rantai_fanbelt1 == 0 ? false : true);
            cb_rantai_fanbelt2.setChecked(var_rantai_fanbelt2 == 0 ? false : true);
            cb_gear_depan1.setChecked(var_gear_depan1 == 0 ? false : true);
            cb_gear_depan2.setChecked(var_gear_depan2 == 0 ? false : true);
            cb_gear_blkg1.setChecked(var_gear_blkg1 == 0 ? false : true);
            cb_gear_blkg2.setChecked(var_gear_blkg2 == 0 ? false : true);
            cb_standar_tengah1.setChecked(var_standar_tengah1 == 0 ? false : true);
            cb_standar_tengah2.setChecked(var_standar_tengah2 == 0 ? false : true);
            cb_standar_samping1.setChecked(var_standar_samping1 == 0 ? false : true);
            cb_standar_samping2.setChecked(var_standar_samping2 == 0 ? false : true);
            cb_ban_depan1.setChecked(var_ban_depan1 == 0 ? false : true);
            cb_ban_depan2.setChecked(var_ban_depan2 == 0 ? false : true);
            cb_ban_blkg1.setChecked(var_ban_blkg1 == 0 ? false : true);
            cb_ban_blkg2.setChecked(var_ban_blkg2 == 0 ? false : true);
            cb_velg_depan1.setChecked(var_velg_depan1 == 0 ? false : true);
            cb_velg_depan2.setChecked(var_velg_depan2 == 0 ? false : true);
            cb_velg_blkg1.setChecked(var_velg_blkg1 == 0 ? false : true);
            cb_velg_blkg2.setChecked(var_velg_blkg2 == 0 ? false : true);
            cb_kaca_spion1.setChecked(var_kaca_spion1 == 0 ? false : true);
            cb_kaca_spion2.setChecked(var_kaca_spion2 == 0 ? false : true);
            cb_speedometer1.setChecked(var_speedometer1 == 0 ? false : true);
            cb_speedometer2.setChecked(var_speedometer2 == 0 ? false : true);
            cb_Grip_stang1.setChecked(var_grip_stang1 == 0 ? false : true);
            cb_Grip_stang2.setChecked(var_grip_stang2 == 0 ? false : true);
            cb_Rumah_kunci1.setChecked(var_rumah_kunci1 == 0 ? false : true);
            cb_Rumah_kunci2.setChecked(var_rumah_kunci2 == 0 ? false : true);
            cb_Klakson1.setChecked(var_klakson1 == 0 ? false : true);
            cb_Klakson2.setChecked(var_klakson2 == 0 ? false : true);
            cb_Behel1.setChecked(var_behel1 == 0 ? false : true);
            cb_Behel2.setChecked(var_behel2 == 0 ? false : true);
            cb_Tutup_knalpot1.setChecked(var_tutup_knalpot1 == 0 ? false : true);
            cb_Tutup_knalpot2.setChecked(var_tutup_knalpot2 == 0 ? false : true);
            cb_jok1.setChecked(var_jok1 == 0 ? false : true);
            cb_jok2.setChecked(var_jok2 == 0 ? false : true);
            cb_Stripping1.setChecked(var_stripping1 == 0 ? false : true);
            cb_Stripping2.setChecked(var_stripping2 == 0 ? false : true);
            cb_Tombol_navigasi1.setChecked(var_tombol_navigasi1 == 0 ? false : true);
            cb_Tombol_navigasi2.setChecked(var_tombol_navigasi2 == 0 ? false : true);



            //================== set Text ====================================
//            cbGrill1_fisikmuka.setText(((var_cbGrill1_fisikmuka == 0) ? "Tidak Ada" : "Ada" ));

            cb_cover_kepala1.setText(((var_cover_kepala1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_kepala2.setText(((var_cover_kepala2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_speedo1.setText(((var_cover_speedo1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_speedo2.setText(((var_cover_speedo2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_depan1.setText(((var_cover_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_depan2.setText(((var_cover_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_sayap_kanan1.setText(((var_sayap_kanan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_sayap_kanan2.setText(((var_sayap_kanan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_sayap_kiri1.setText(((var_sayap_kiri1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_sayap_kiri2.setText(((var_sayap_kiri2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_spak_depan1.setText(((var_spak_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_spak_depan2.setText(((var_spak_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_tengah1.setText(((var_cover_tengah1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_tengah2.setText(((var_cover_tengah2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_spgkanan1.setText(((var_cover_spgkanan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_spgkanan2.setText(((var_cover_spgkanan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_spgkiri1.setText(((var_cover_spgkiri1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_spgkiri2.setText(((var_cover_spgkiri2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_rantai_depan1.setText(((var_rantai_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_rantai_depan2.setText(((var_rantai_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_rantai_blkg1.setText(((var_rantai_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_rantai_blkg2.setText(((var_rantai_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_spak_blkg1.setText(((var_spak_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_spak_blkg2.setText(((var_spak_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_accu1.setText(((var_cover_accu1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_accu2.setText(((var_cover_accu2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_lampurem1.setText(((var_cover_lampurem1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_lampurem2.setText(((var_cover_lampurem2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_tangki_bensin1.setText(((var_tangki_bensin1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_tangki_bensin2.setText(((var_tangki_bensin2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_bawah1.setText(((var_cover_bawah1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_bawah2.setText(((var_cover_bawah2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_dek_desk1.setText(((var_dek_desk1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_dek_desk2.setText(((var_dek_desk2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_mesin1.setText(((var_cover_mesin1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cover_mesin2.setText(((var_cover_mesin2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_emblem1.setText(((var_emblem1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_emblem2.setText(((var_emblem2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_blok_mesin1.setText(((var_blok_mesin1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_blok_mesin2.setText(((var_blok_mesin2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_carburator1.setText(((var_carburator1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_carburator2.setText(((var_carburator2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_filter_carbu1.setText(((var_filter_carbu1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_filter_carbu2.setText(((var_filter_carbu2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_busi1.setText(((var_busi1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_busi2.setText(((var_busi2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cdi1.setText(((var_cdi1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cdi2.setText(((var_cdi2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_accu1.setText(((var_accu1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_accu2.setText(((var_accu2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_kick_starter1.setText(((var_kick_starter1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_kick_starter2.setText(((var_kick_starter2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_perseneling1.setText(((var_perseneling1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_perseneling2.setText(((var_perseneling2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_knalpot1.setText(((var_knalpot1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_knalpot2.setText(((var_knalpot2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_coil1.setText(((var_coil1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_coil2.setText(((var_coil2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_blok1.setText(((var_blok1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_blok2.setText(((var_blok2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_tutupcvt1.setText(((var_tutupcvt1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_tutupcvt2.setText(((var_tutupcvt2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_radiator1.setText(((var_radiator1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_radiator2.setText(((var_radiator2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_lampu_depan1.setText(((var_lampu_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_lampu_depan2.setText(((var_lampu_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_sein_depan1.setText(((var_sein_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_sein_depan2.setText(((var_sein_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_sein_blkg1.setText(((var_sein_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_sein_blkg2.setText(((var_sein_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_lampu_rem1.setText(((var_lampu_rem1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_lampu_rem2.setText(((var_lampu_rem2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_handlerem_depan1.setText(((var_handlerem_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_handlerem_depan2.setText(((var_handlerem_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_pedalrem_blkg1.setText(((var_pedalrem_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_pedalrem_blkg2.setText(((var_pedalrem_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_handlerem_blkg1.setText(((var_handlerem_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_handlerem_blkg2.setText(((var_handlerem_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_handle_kopling1.setText(((var_handle_kopling1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_handle_kopling2.setText(((var_handle_kopling2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_master_rem1.setText(((var_master_rem1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_master_rem2.setText(((var_master_rem2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_caliper1.setText(((var_caliper1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_caliper2.setText(((var_caliper2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cakram1.setText(((var_cakram1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_cakram2.setText(((var_cakram2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_shock_depan1.setText(((var_shock_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_shock_depan2.setText(((var_shock_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_shock_blkg1.setText(((var_shock_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_shock_blkg2.setText(((var_shock_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_tromol_depan1.setText(((var_tromol_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_tromol_depan2.setText(((var_tromol_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_tromol_blkg1.setText(((var_tromol_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_tromol_blkg2.setText(((var_tromol_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_footstep_depan1.setText(((var_footstep_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_footstep_depan2.setText(((var_footstep_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_footstep_blkg1.setText(((var_footstep_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_footstep_blkg2.setText(((var_footstep_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_swing_arm1.setText(((var_swing_arm1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_swing_arm2.setText(((var_swing_arm2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_rantai_fanbelt1.setText(((var_rantai_fanbelt1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_rantai_fanbelt2.setText(((var_rantai_fanbelt2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_gear_depan1.setText(((var_gear_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_gear_depan2.setText(((var_gear_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_gear_blkg1.setText(((var_gear_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_gear_blkg2.setText(((var_gear_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_standar_tengah1.setText(((var_standar_tengah1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_standar_tengah2.setText(((var_standar_tengah2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_standar_samping1.setText(((var_standar_samping1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_standar_samping2.setText(((var_standar_samping2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_ban_depan1.setText(((var_ban_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_ban_depan2.setText(((var_ban_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_ban_blkg1.setText(((var_ban_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_ban_blkg2.setText(((var_ban_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_velg_depan1.setText(((var_velg_depan1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_velg_depan2.setText(((var_velg_depan2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_velg_blkg1.setText(((var_velg_blkg1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_velg_blkg2.setText(((var_velg_blkg2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_kaca_spion1.setText(((var_kaca_spion1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_kaca_spion2.setText(((var_kaca_spion2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_speedometer1.setText(((var_speedometer1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_speedometer2.setText(((var_speedometer2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Grip_stang1.setText(((var_grip_stang1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Grip_stang2.setText(((var_grip_stang2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Rumah_kunci1.setText(((var_rumah_kunci1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Rumah_kunci2.setText(((var_rumah_kunci2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Klakson1.setText(((var_klakson1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Klakson2.setText(((var_klakson2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Behel1.setText(((var_behel1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Behel2.setText(((var_behel2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Tutup_knalpot1.setText(((var_tutup_knalpot1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Tutup_knalpot2.setText(((var_tutup_knalpot2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_jok1.setText(((var_jok1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_jok2.setText(((var_jok2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Stripping1.setText(((var_stripping1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Stripping2.setText(((var_stripping2 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Tombol_navigasi1.setText(((var_tombol_navigasi1 == 0) ? "Tidak Ada" : "Ada" ));
            cb_Tombol_navigasi2.setText(((var_tombol_navigasi2 == 0) ? "Tidak Ada" : "Ada" ));


        } catch (JSONException e) {
            e.printStackTrace();
//            Toast.makeText(BpCekFormActivity.this,"catch",Toast.LENGTH_LONG).show();
        }

        if(code == 200){
//



        }else if (code == 304){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

        }
    }


    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
            if(code == 200){
                Intent i = new Intent(MobInputCeklistMotorActivity.this, MobInputFotoActivity.class);
                Bundle b = new Bundle();
                b.putString("parse_nopol",var_nopol);
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(MobInputCeklistMotorActivity.this,"Check Your Connection",Toast.LENGTH_SHORT).show();
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