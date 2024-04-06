package com.tunasrent.auctionapps.bp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Toast;

import com.tunasrent.auctionapps.DB.Ceklist;
import com.tunasrent.auctionapps.DB.CeklistMotor;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.util.RequestHandler;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class BpCekCeklistMotorActivity extends AppCompatActivity {
    UserSessionManager session;
    String _name, _fullname, _appid, _ccode, _token, _group;

    Realm realm;
    Calendar myCalendar;
    Toolbar toolbar;

    String message;
    String code;
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

    CheckBox cb_jok1, cb_jok2;
    CheckBox cb_Stripping1, cb_Stripping2;
    CheckBox cb_Tombol_navigasi1, cb_Tombol_navigasi2;
    TextInputEditText etSd_stnk;
    CheckBox cbStnk1_lain, cbStnk2_lain;
    CheckBox cbBukumnl1_lain, cbBukumnl2_lain;

    Button btnNext;
    //  # BODY #
    Boolean var_cover_kepala1,var_cover_kepala2=false;
    Boolean var_cover_speedo1,var_cover_speedo2 = false;
    Boolean var_cover_depan1,var_cover_depan2=false;
    Boolean var_sayap_kanan1,var_sayap_kanan2 = false;
    Boolean var_sayap_kiri1,var_sayap_kiri2 = false;
    Boolean var_spak_depan1,var_spak_depan2 = false;
    Boolean var_cover_tengah1, var_cover_tengah2=false;
    Boolean var_cover_spgkanan1,var_cover_spgkanan2=false;
    Boolean var_cover_spgkiri1,var_cover_spgkiri2=false;
    Boolean var_rantai_depan1,var_rantai_depan2=false;
    Boolean var_rantai_blkg1,var_rantai_blkg2=false;
    Boolean var_spak_blkg1,var_spak_blkg2=false;
    Boolean var_cover_accu1,var_cover_accu2=false;
    Boolean var_cover_lampurem1,var_cover_lampurem2=false;
    Boolean var_tangki_bensin1,var_tangki_bensin2=false;
    Boolean var_cover_bawah1,var_cover_bawah2=false;
    Boolean var_dek_desk1,var_dek_desk2=false;
    Boolean var_cover_mesin1,var_cover_mesin2=false;
    Boolean var_emblem1,var_emblem2=false;

    //    # MESIN #
    Boolean var_blok_mesin1,var_blok_mesin2=false;
    Boolean var_carburator1,var_carburator2=false;
    Boolean var_filter_carbu1,var_filter_carbu2=false;
    Boolean var_busi1, var_busi2 =false;
    Boolean var_cdi1,var_cdi2=false;
    Boolean var_accu1,var_accu2=false;
    Boolean var_kick_starter1, var_kick_starter2=false;
    Boolean var_perseneling1, var_perseneling2=false;
    Boolean var_knalpot1, var_knalpot2=false;
    Boolean var_coil1, var_coil2=false;
    Boolean var_blok1, var_blok2=false;
    Boolean var_tutupcvt1, var_tutupcvt2=false;
    Boolean var_radiator1, var_radiator2=false;
    Boolean var_lampu_depan1, var_lampu_depan2=false;
    Boolean var_sein_depan1, var_sein_depan2=false;
    Boolean var_sein_blkg1, var_sein_blkg2=false;
    Boolean var_lampu_rem1, var_lampu_rem2=false;
    //    # KAKI-KAKI #
    Boolean var_handlerem_depan1, var_handlerem_depan2=false;
    Boolean var_pedalrem_blkg1, var_pedalrem_blkg2=false;
    Boolean var_handlerem_blkg1, var_handlerem_blkg2=false;
    Boolean var_handle_kopling1, var_handle_kopling2=false;
    Boolean var_master_rem1,var_master_rem2=false;
    Boolean var_caliper1, var_caliper2=false;
    Boolean var_cakram1, var_cakram2=false;
    Boolean var_shock_depan1, var_shock_depan2=false;
    Boolean var_shock_blkg1, var_shock_blkg2=false;
    Boolean var_tromol_depan1, var_tromol_depan2=false;
    Boolean var_tromol_blkg1, var_tromol_blkg2=false;
    Boolean var_footstep_depan1, var_footstep_depan2=false;
    Boolean var_footstep_blkg1, var_footstep_blkg2=false;
    Boolean var_swing_arm1, var_swing_arm2=false;
    Boolean var_rantai_fanbelt1, var_rantai_fanbelt2=false;
    Boolean var_gear_depan1, var_gear_depan2=false;
    Boolean var_gear_blkg1, var_gear_blkg2=false;
    Boolean var_standar_tengah1, var_standar_tengah2=false;
    Boolean var_standar_samping1, var_standar_samping2=false;
    Boolean var_ban_depan1, var_ban_depan2=false;
    Boolean var_ban_blkg1, var_ban_blkg2=false;
    Boolean var_velg_depan1, var_velg_depan2=false;
    Boolean var_velg_blkg1, var_velg_blkg2=false;
    //    # PERLENGKAPAN #
    Boolean var_kaca_spion1, var_kaca_spion2=false;
    Boolean var_speedometer1, var_speedometer2=false;
    Boolean var_Grip_stang1, var_Grip_stang2=false;
    Boolean var_Rumah_kunci1, var_Rumah_kunci2=false;
    Boolean var_Klakson1, var_Klakson2=false;
    Boolean var_Behel1, var_Behel2=false;
    Boolean var_Tutup_knalpot1, var_Tutup_knalpot2=false;
    Boolean var_jok1, var_jok2=false;
    Boolean var_Stripping1, var_Stripping2=false;
    Boolean var_Tombol_navigasi1, var_Tombol_navigasi2=false;
    Boolean var_cbStnk1_lain, var_cbStnk2_lain=false;
    String var_etStnk;
    Boolean var_cbBukumnl1_lain, var_cbBukumnl2_lain=false;
    Boolean var_grip_stang1, var_grip_stang2=false;
    Boolean var_rumah_kunci1, var_rumah_kunci2=false;
    Boolean var_klakson1, var_klakson2=false;
    Boolean var_behel1, var_behel2=false;
    Boolean var_tutup_knalpot1, var_tutup_knalpot2=false;
    Boolean var_stripping1, var_stripping2=false;
    Boolean var_tombol_navigasi1, var_tombol_navigasi2=false;
    String[] stnk;
    Boolean var_stnk_lain1, var_stnk_lain2=false;
    Boolean var_bukumnl1, var_bukumnl2=false;
    String vhc_cat_name;
    String var_picPenarikanDiterima;
    String var_picPenarikan;
    String var_picPenyimpanan;
    String var_picPenjualan;
    String var_picPengiriman;
    String var_note_picPenarikan;
    String var_note_picPenyimpanan;
    String var_note_picPengiriman;

    String var_img1;
    String var_img2;
    String var_img3;
    String var_img4;
    String var_img5;
    String var_img6;
    String var_img7;
    String var_img8;
    String var_img9;
    String var_img10;
    String var_img11;
    String var_img12;
    String var_img13;
    String var_img14;
    String var_market_price;
    String var_recomended_price;
    String var_repair_machine;
    String var_repair_interior;
    String var_repair_exterior;
    String var_cost_document;
    String var_margin;
    String var_pembulatan;
    String var_unit_condition;

    String nopol;
    String pool_id;
    String status;
    ScrollView svParent, svChild;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_cek_ceklist_motor);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ceklist Unit");
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
        etSd_stnk = findViewById(R.id.et_sd_stnk);
        cbStnk1_lain = findViewById(R.id.cb_stnk1_lain);
        cbStnk2_lain = findViewById(R.id.cb_stnk2_lain);
        cbBukumnl1_lain = findViewById(R.id.cb_bukumnl1_lain);
        cbBukumnl2_lain = findViewById(R.id.cb_bukumnl2_lain);

        btnNext = findViewById(R.id.btn_next);
        svParent = findViewById(R.id.sv_parent);
        svChild = findViewById(R.id.sv_child);
        myCalendar = Calendar.getInstance();

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance(); //creating  database oject
        RealmResults<CeklistMotor> ceklists = realm.where(CeklistMotor.class).findAllAsync();
        //ceklists.load();

        Bundle b = getIntent().getExtras();
        if (b != null){
            vhc_cat_name = b.getString("vhc_cat_name");
            var_etStnk = b.getString(configuration.KEY_stnk_lain);
//            Log.d("TAGstnk", "onCreate: "+var_etStnk);
            if(var_etStnk.equals("null")){
                var_etStnk = "0000-00-00";
            }else{
                var_etStnk = b.getString("stnk_lain");
            }
            stnk = var_etStnk.split("-");
            var_cover_kepala1 = (b.getInt(configuration.MOBMOTOR_cover_kepala1) == 0) ? false : true;
            var_cover_kepala2 = (b.getInt(configuration.MOBMOTOR_cover_kepala2) == 0) ? false : true;
            var_cover_speedo1 = (b.getInt(configuration.MOBMOTOR_cover_speedo1) == 0) ? false : true;
            var_cover_speedo2 = (b.getInt(configuration.MOBMOTOR_cover_speedo2) == 0) ? false : true;
            var_cover_depan1 = (b.getInt(configuration.MOBMOTOR_cover_depan1) == 0) ? false : true;
            var_cover_depan2 = (b.getInt(configuration.MOBMOTOR_cover_depan2) == 0) ? false : true;
            var_sayap_kanan1 = (b.getInt(configuration.MOBMOTOR_sayap_kanan1) == 0) ? false : true;
            var_sayap_kanan2 = (b.getInt(configuration.MOBMOTOR_sayap_kanan2) == 0) ? false : true;
            var_sayap_kiri1 = (b.getInt(configuration.MOBMOTOR_sayap_kiri1) == 0) ? false : true;
            var_sayap_kiri2 = (b.getInt(configuration.MOBMOTOR_sayap_kiri2) == 0) ? false : true;
            var_spak_depan1 = (b.getInt(configuration.MOBMOTOR_spak_depan1) == 0) ? false : true;
            var_spak_depan2 = (b.getInt(configuration.MOBMOTOR_spak_depan2) == 0) ? false : true;

            var_cover_tengah1 = (b.getInt(configuration.MOBMOTOR_cover_tengah1) == 0) ? false : true;
            var_cover_tengah2 = (b.getInt(configuration.MOBMOTOR_cover_tengah2) == 0) ? false : true;
            var_cover_spgkanan1 = (b.getInt(configuration.MOBMOTOR_cover_spgkanan1) == 0) ? false : true;
            var_cover_spgkanan2 = (b.getInt(configuration.MOBMOTOR_cover_spgkanan2) == 0) ? false : true;
            var_cover_spgkiri1 = (b.getInt(configuration.MOBMOTOR_cover_spgkiri1) == 0) ? false : true;
            var_cover_spgkiri2 = (b.getInt(configuration.MOBMOTOR_cover_spgkiri2) == 0) ? false : true;
            var_rantai_depan1 = (b.getInt(configuration.MOBMOTOR_rantai_depan1) == 0) ? false : true;
            var_rantai_depan2 = (b.getInt(configuration.MOBMOTOR_rantai_depan2) == 0) ? false : true;
            var_rantai_blkg1 = (b.getInt(configuration.MOBMOTOR_rantai_blkg1) == 0) ? false : true;
            var_rantai_blkg2 = (b.getInt(configuration.MOBMOTOR_rantai_blkg2) == 0) ? false : true;
            var_spak_blkg1 = (b.getInt(configuration.MOBMOTOR_spak_blkg1) == 0) ? false : true;
            var_spak_blkg2 = (b.getInt(configuration.MOBMOTOR_spak_blkg2) == 0) ? false : true;
            var_cover_accu1 = (b.getInt(configuration.MOBMOTOR_cover_accu1) == 0) ? false : true;
            var_cover_accu2 = (b.getInt(configuration.MOBMOTOR_cover_accu2) == 0) ? false : true;
            var_cover_lampurem1 = (b.getInt(configuration.MOBMOTOR_cover_lampurem1) == 0) ? false : true;
            var_cover_lampurem2 = (b.getInt(configuration.MOBMOTOR_cover_lampurem2) == 0) ? false : true;
            var_tangki_bensin1 = (b.getInt(configuration.MOBMOTOR_tangki_bensin1) == 0) ? false : true;
            var_tangki_bensin2 = (b.getInt(configuration.MOBMOTOR_tangki_bensin2) == 0) ? false : true;
            var_cover_bawah1 = (b.getInt(configuration.MOBMOTOR_cover_bawah1) == 0) ? false : true;
            var_cover_bawah2 = (b.getInt(configuration.MOBMOTOR_cover_bawah2) == 0) ? false : true;
            var_dek_desk1 = (b.getInt(configuration.MOBMOTOR_dek_desk1) == 0) ? false : true;
            var_dek_desk2 = (b.getInt(configuration.MOBMOTOR_dek_desk2) == 0) ? false : true;
            var_cover_mesin1 = (b.getInt(configuration.MOBMOTOR_cover_mesin1) == 0) ? false : true;
            var_cover_mesin2 = (b.getInt(configuration.MOBMOTOR_cover_mesin2) == 0) ? false : true;
            var_emblem1 = (b.getInt(configuration.MOBMOTOR_emblem1) == 0) ? false : true;
            var_emblem2 = (b.getInt(configuration.MOBMOTOR_emblem2) == 0) ? false : true;
            var_blok_mesin1 = (b.getInt(configuration.MOBMOTOR_blok_mesin1) == 0) ? false : true;

            var_blok_mesin2 = (b.getInt(configuration.MOBMOTOR_blok_mesin2) == 0) ? false : true;
            var_carburator1 = (b.getInt(configuration.MOBMOTOR_carburator1) == 0) ? false : true;
            var_carburator2 = (b.getInt(configuration.MOBMOTOR_carburator2) == 0) ? false : true;
            var_filter_carbu1 = (b.getInt(configuration.MOBMOTOR_filter_carbu1) == 0) ? false : true;
            var_filter_carbu2 = (b.getInt(configuration.MOBMOTOR_filter_carbu2) == 0) ? false : true;
            var_busi1 = (b.getInt(configuration.MOBMOTOR_busi1) == 0) ? false : true;
            var_busi2 = (b.getInt(configuration.MOBMOTOR_busi2) == 0) ? false : true;
            var_cdi1 = (b.getInt(configuration.MOBMOTOR_cdi1) == 0) ? false : true;
            var_cdi2 = (b.getInt(configuration.MOBMOTOR_cdi2) == 0) ? false : true;
            var_accu1 = (b.getInt(configuration.MOBMOTOR_accu1) == 0) ? false : true;
            var_accu2 = (b.getInt(configuration.MOBMOTOR_accu2) == 0) ? false : true;
            var_kick_starter1 = (b.getInt(configuration.MOBMOTOR_kick_starter1) == 0) ? false : true;
            var_kick_starter2 = (b.getInt(configuration.MOBMOTOR_kick_starter2) == 0) ? false : true;
            var_perseneling1 = (b.getInt(configuration.MOBMOTOR_perseneling1) == 0) ? false : true;
            var_perseneling2 = (b.getInt(configuration.MOBMOTOR_perseneling2) == 0) ? false : true;
            var_knalpot1 = (b.getInt(configuration.MOBMOTOR_knalpot1) == 0) ? false : true;
            var_knalpot2 = (b.getInt(configuration.MOBMOTOR_knalpot2) == 0) ? false : true;
            var_coil1 = (b.getInt(configuration.MOBMOTOR_coil1) == 0) ? false : true;
            var_coil2 = (b.getInt(configuration.MOBMOTOR_coil2) == 0) ? false : true;
            var_blok1 = (b.getInt(configuration.MOBMOTOR_blok1) == 0) ? false : true;
            var_blok2 = (b.getInt(configuration.MOBMOTOR_blok2) == 0) ? false : true;
            var_tutupcvt1 = (b.getInt(configuration.MOBMOTOR_tutupcvt1) == 0) ? false : true;
            var_tutupcvt2 = (b.getInt(configuration.MOBMOTOR_tutupcvt2) == 0) ? false : true;
            var_radiator1 = (b.getInt(configuration.MOBMOTOR_radiator1) == 0) ? false : true;
            var_radiator2 = (b.getInt(configuration.MOBMOTOR_radiator2) == 0) ? false : true;
            var_lampu_depan1 = (b.getInt(configuration.MOBMOTOR_lampu_depan1) == 0) ? false : true;
            var_lampu_depan2 = (b.getInt(configuration.MOBMOTOR_lampu_depan2) == 0) ? false : true;
            var_sein_depan1 = (b.getInt(configuration.MOBMOTOR_sein_depan1) == 0) ? false : true;
            var_sein_depan2 = (b.getInt(configuration.MOBMOTOR_sein_depan2) == 0) ? false : true;
            var_sein_blkg1 = (b.getInt(configuration.MOBMOTOR_sein_blkg1) == 0) ? false : true;
            var_sein_blkg2 = (b.getInt(configuration.MOBMOTOR_sein_blkg2) == 0) ? false : true;
            var_lampu_rem1 = (b.getInt(configuration.MOBMOTOR_lampu_rem1) == 0) ? false : true;
            var_lampu_rem2 = (b.getInt(configuration.MOBMOTOR_lampu_rem2) == 0) ? false : true;
            var_handlerem_depan1 = (b.getInt(configuration.MOBMOTOR_handlerem_depan1) == 0) ? false : true;
            var_handlerem_depan2 = (b.getInt(configuration.MOBMOTOR_handlerem_depan2) == 0) ? false : true;
            var_pedalrem_blkg1 = (b.getInt(configuration.MOBMOTOR_pedalrem_blkg1) == 0) ? false : true;
            var_pedalrem_blkg2 = (b.getInt(configuration.MOBMOTOR_pedalrem_blkg2) == 0) ? false : true;
            var_handlerem_blkg1 = (b.getInt(configuration.MOBMOTOR_handlerem_blkg1) == 0) ? false : true;
            var_handlerem_blkg2 = (b.getInt(configuration.MOBMOTOR_handlerem_blkg2) == 0) ? false : true;
            var_handle_kopling1 = (b.getInt(configuration.MOBMOTOR_handle_kopling1) == 0) ? false : true;
            var_handle_kopling2 = (b.getInt(configuration.MOBMOTOR_handle_kopling2) == 0) ? false : true;
            var_master_rem1 = (b.getInt(configuration.MOBMOTOR_master_rem1) == 0) ? false : true;
            var_master_rem2 = (b.getInt(configuration.MOBMOTOR_master_rem2) == 0) ? false : true;
            var_caliper1 = (b.getInt(configuration.MOBMOTOR_caliper1) == 0) ? false : true;
            var_caliper2 = (b.getInt(configuration.MOBMOTOR_caliper2) == 0) ? false : true;
            var_cakram1 = (b.getInt(configuration.MOBMOTOR_cakram1) == 0) ? false : true;
            var_cakram2 = (b.getInt(configuration.MOBMOTOR_cakram2) == 0) ? false : true;
            var_shock_depan1 = (b.getInt(configuration.MOBMOTOR_shock_depan1) == 0) ? false : true;
            var_shock_depan2 = (b.getInt(configuration.MOBMOTOR_shock_depan2) == 0) ? false : true;
            var_shock_blkg1 = (b.getInt(configuration.MOBMOTOR_shock_blkg1) == 0) ? false : true;
            var_shock_blkg2 = (b.getInt(configuration.MOBMOTOR_shock_blkg2) == 0) ? false : true;
            var_tromol_depan1 = (b.getInt(configuration.MOBMOTOR_tromol_depan1) == 0) ? false : true;
            var_tromol_depan2 = (b.getInt(configuration.MOBMOTOR_tromol_depan2) == 0) ? false : true;
            var_tromol_blkg1 = (b.getInt(configuration.MOBMOTOR_tromol_blkg1) == 0) ? false : true;
            var_tromol_blkg2 = (b.getInt(configuration.MOBMOTOR_tromol_blkg2) == 0) ? false : true;
            var_footstep_depan1 = (b.getInt(configuration.MOBMOTOR_footstep_depan1) == 0) ? false : true;
            var_footstep_depan2 = (b.getInt(configuration.MOBMOTOR_footstep_depan2) == 0) ? false : true;
            var_footstep_blkg1 = (b.getInt(configuration.MOBMOTOR_footstep_blkg1) == 0) ? false : true;
            var_footstep_blkg2 = (b.getInt(configuration.MOBMOTOR_footstep_blkg2) == 0) ? false : true;
            var_swing_arm1 = (b.getInt(configuration.MOBMOTOR_swing_arm1) == 0) ? false : true;
            var_swing_arm2 = (b.getInt(configuration.MOBMOTOR_swing_arm2) == 0) ? false : true;
            var_rantai_fanbelt1 = (b.getInt(configuration.MOBMOTOR_rantai_fanbelt1) == 0) ? false : true;
            var_rantai_fanbelt2 = (b.getInt(configuration.MOBMOTOR_rantai_fanbelt2) == 0) ? false : true;
            var_gear_depan1 = (b.getInt(configuration.MOBMOTOR_gear_depan1) == 0) ? false : true;
            var_gear_depan2 = (b.getInt(configuration.MOBMOTOR_gear_depan2) == 0) ? false : true;
            var_gear_blkg1 = (b.getInt(configuration.MOBMOTOR_gear_blkg1) == 0) ? false : true;
            var_gear_blkg2 = (b.getInt(configuration.MOBMOTOR_gear_blkg2) == 0) ? false : true;
            var_standar_tengah1 = (b.getInt(configuration.MOBMOTOR_standar_tengah1) == 0) ? false : true;

            var_standar_tengah2 = (b.getInt(configuration.MOBMOTOR_standar_tengah2) == 0) ? false : true;
            var_standar_samping1 = (b.getInt(configuration.MOBMOTOR_standar_samping1) == 0) ? false : true;
            var_standar_samping2 = (b.getInt(configuration.MOBMOTOR_standar_samping2) == 0) ? false : true;
            var_ban_depan1 = (b.getInt(configuration.MOBMOTOR_ban_depan1) == 0) ? false : true;
            var_ban_depan2 = (b.getInt(configuration.MOBMOTOR_ban_depan2) == 0) ? false : true;
            var_ban_blkg1 = (b.getInt(configuration.MOBMOTOR_ban_blkg1) == 0) ? false : true;
            var_ban_blkg2 = (b.getInt(configuration.MOBMOTOR_ban_blkg2) == 0) ? false : true;
            var_velg_depan1 = (b.getInt(configuration.MOBMOTOR_velg_depan1) == 0) ? false : true;
            var_velg_depan2 = (b.getInt(configuration.MOBMOTOR_velg_depan2) == 0) ? false : true;
            var_velg_blkg1 = (b.getInt(configuration.MOBMOTOR_velg_blkg1) == 0) ? false : true;
            var_velg_blkg2 = (b.getInt(configuration.MOBMOTOR_velg_blkg2) == 0) ? false : true;
            var_kaca_spion1 = (b.getInt(configuration.MOBMOTOR_kaca_spion1) == 0) ? false : true;
            var_kaca_spion2 = (b.getInt(configuration.MOBMOTOR_kaca_spion2) == 0) ? false : true;
            var_speedometer1 = (b.getInt(configuration.MOBMOTOR_speedometer1) == 0) ? false : true;
            var_speedometer2 = (b.getInt(configuration.MOBMOTOR_speedometer2) == 0) ? false : true;
            var_grip_stang1 = (b.getInt(configuration.MOBMOTOR_Grip_stang1) == 0) ? false : true;
            var_grip_stang2 = (b.getInt(configuration.MOBMOTOR_Grip_stang2) == 0) ? false : true;
            var_rumah_kunci1 = (b.getInt(configuration.MOBMOTOR_Rumah_kunci1) == 0) ? false : true;
            var_rumah_kunci2 = (b.getInt(configuration.MOBMOTOR_Rumah_kunci2) == 0) ? false : true;
            var_klakson1 = (b.getInt(configuration.MOBMOTOR_Klakson1) == 0) ? false : true;
            var_klakson2 = (b.getInt(configuration.MOBMOTOR_Klakson2) == 0) ? false : true;
            var_behel1 = (b.getInt(configuration.MOBMOTOR_Behel1) == 0) ? false : true;
            var_behel2 = (b.getInt(configuration.MOBMOTOR_Behel2) == 0) ? false : true;
            var_tutup_knalpot1 = (b.getInt(configuration.MOBMOTOR_Tutup_knalpot1) == 0) ? false : true;
            var_tutup_knalpot2 = (b.getInt(configuration.MOBMOTOR_Tutup_knalpot2) == 0) ? false : true;
            var_jok1 = (b.getInt(configuration.MOBMOTOR_jok1) == 0) ? false : true;
            var_jok2 = (b.getInt(configuration.MOBMOTOR_jok1) == 0) ? false : true;  // Perhatikan bahwa ini mungkin perlu diubah menjadi jok2 sesuai kebutuhan Anda
            var_stripping1 = (b.getInt(configuration.MOBMOTOR_Stripping1) == 0) ? false : true;
            var_stripping2 = (b.getInt(configuration.MOBMOTOR_Stripping2) == 0) ? false : true;
            var_tombol_navigasi1 = (b.getInt(configuration.MOBMOTOR_Tombol_navigasi1) == 0) ? false : true;
            var_tombol_navigasi2 = (b.getInt(configuration.MOBMOTOR_Tombol_navigasi2) == 0) ? false : true;
            var_stnk_lain1 = (b.getInt(configuration.KEY_stnk1_lain) == 0) ? false : true;
            var_stnk_lain2 = (b.getInt(configuration.KEY_stnk2_lain) == 0) ? false : true;
            var_bukumnl1 = (b.getInt(configuration.KEY_bukumnl1_lain) == 0) ? false : true;
            var_bukumnl2 = (b.getInt(configuration.KEY_bukumnl2_lain) == 0) ? false : true;

            var_picPenarikanDiterima= b.getString("picpenarikan_diterima");
            var_picPenarikan= b.getString("picpenarikan");
            var_picPenyimpanan= b.getString("picpenyimpanan");
            var_picPenjualan= b.getString("picpenjualan");
            var_picPengiriman= b.getString("picpengiriman");
            var_note_picPenarikan= b.getString("note_picpenarikan");
            var_note_picPenyimpanan= b.getString("note_picpenyimpanan");
            var_note_picPengiriman= b.getString("note_picpengiriman");

            var_market_price= b.getString("market_price");
            Log.d("TAG-cekprice", "onCreate: " + var_market_price);
            var_recomended_price= b.getString("recomended_price");
            var_repair_machine= b.getString("repair_machine");
            var_repair_interior= b.getString("repair_interior");
            var_repair_exterior= b.getString("repair_exterior");
            var_cost_document= b.getString("cost_document");
            var_margin= b.getString("margin");
            var_pembulatan= b.getString("pembulatan");
            var_unit_condition= b.getString("unit_condition");

            var_img1= b.getString("img1");
            var_img2= b.getString("img2");
            var_img3= b.getString("img3");
            var_img4= b.getString("img4");
            var_img5= b.getString("img5");
            var_img6= b.getString("img6");
            var_img7= b.getString("img7");
            var_img8= b.getString("img8");
            var_img9= b.getString("img9");
            var_img10= b.getString("img10");
            var_img11= b.getString("img11");
            var_img12= b.getString("img12");
            var_img13= b.getString("img13");
            var_img14= b.getString("img14");
            nopol = b.getString("nopol");
            pool_id = b.getString("pool_id");
            status = b.getString("status");
        }

        cb_cover_kepala1.setChecked(var_cover_kepala1);
        cb_cover_kepala1.setText((var_cover_kepala1 == true) ? "Ada" : "Tidak Ada");
        cb_cover_kepala2.setChecked((var_cover_kepala1 == true) ? var_cover_kepala2 : false);
        cb_cover_kepala2.setVisibility((var_cover_kepala1 == true) ? View.VISIBLE : View.INVISIBLE);
        cb_cover_kepala2.setText((var_cover_kepala2 == true) ? "Baik" : "Rusak");

        cb_cover_speedo1.setChecked(var_cover_speedo1);
        cb_cover_speedo1.setText(var_cover_speedo1 ? "Ada" : "Tidak Ada");
        cb_cover_speedo2.setChecked(var_cover_speedo1 && var_cover_speedo2);
        cb_cover_speedo2.setVisibility(var_cover_speedo1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_speedo2.setText(var_cover_speedo2 ? "Baik" : "Rusak");

        cb_cover_depan1.setChecked(var_cover_depan1);
        cb_cover_depan1.setText(var_cover_depan1 ? "Ada" : "Tidak Ada");
        cb_cover_depan2.setChecked(var_cover_depan1 && var_cover_depan2);
        cb_cover_depan2.setVisibility(var_cover_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_depan2.setText(var_cover_depan2 ? "Baik" : "Rusak");

        // Melanjutkan dengan variabel lainnya...
        cb_sayap_kanan1.setChecked(var_sayap_kanan1);
        cb_sayap_kanan1.setText(var_sayap_kanan1 ? "Ada" : "Tidak Ada");
        cb_sayap_kanan2.setChecked(var_sayap_kanan1 && var_sayap_kanan2);
        cb_sayap_kanan2.setVisibility(var_sayap_kanan1 ? View.VISIBLE : View.INVISIBLE);
        cb_sayap_kanan2.setText(var_sayap_kanan2 ? "Baik" : "Rusak");

        cb_sayap_kiri1.setChecked(var_sayap_kiri1);
        cb_sayap_kiri1.setText(var_sayap_kiri1 ? "Ada" : "Tidak Ada");
        cb_sayap_kiri2.setChecked(var_sayap_kiri1 && var_sayap_kiri2);
        cb_sayap_kiri2.setVisibility(var_sayap_kiri1 ? View.VISIBLE : View.INVISIBLE);
        cb_sayap_kiri2.setText(var_sayap_kiri2 ? "Baik" : "Rusak");

        cb_spak_depan1.setChecked(var_spak_depan1);
        cb_spak_depan1.setText(var_spak_depan1 ? "Ada" : "Tidak Ada");
        cb_spak_depan2.setChecked(var_spak_depan1 && var_spak_depan2);
        cb_spak_depan2.setVisibility(var_spak_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_spak_depan2.setText(var_spak_depan2 ? "Baik" : "Rusak");

        cb_cover_tengah1.setChecked(var_cover_tengah1);
        cb_cover_tengah1.setText(var_cover_tengah1 ? "Ada" : "Tidak Ada");
        cb_cover_tengah2.setChecked(var_cover_tengah1 && var_cover_tengah2);
        cb_cover_tengah2.setVisibility(var_cover_tengah1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_tengah2.setText(var_cover_tengah2 ? "Baik" : "Rusak");

        cb_cover_spgkanan1.setChecked(var_cover_spgkanan1);
        cb_cover_spgkanan1.setText(var_cover_spgkanan1 ? "Ada" : "Tidak Ada");
        cb_cover_spgkanan2.setChecked(var_cover_spgkanan1 && var_cover_spgkanan2);
        cb_cover_spgkanan2.setVisibility(var_cover_spgkanan1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_spgkanan2.setText(var_cover_spgkanan2 ? "Baik" : "Rusak");

        cb_cover_spgkiri1.setChecked(var_cover_spgkiri1);
        cb_cover_spgkiri1.setText(var_cover_spgkiri1 ? "Ada" : "Tidak Ada");
        cb_cover_spgkiri2.setChecked(var_cover_spgkiri1 && var_cover_spgkiri2);
        cb_cover_spgkiri2.setVisibility(var_cover_spgkiri1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_spgkiri2.setText(var_cover_spgkiri2 ? "Baik" : "Rusak");

        cb_rantai_depan1.setChecked(var_rantai_depan1);
        cb_rantai_depan1.setText(var_rantai_depan1 ? "Ada" : "Tidak Ada");
        cb_rantai_depan2.setChecked(var_rantai_depan1 && var_rantai_depan2);
        cb_rantai_depan2.setVisibility(var_rantai_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_rantai_depan2.setText(var_rantai_depan2 ? "Baik" : "Rusak");

        cb_rantai_blkg1.setChecked(var_rantai_blkg1);
        cb_rantai_blkg1.setText(var_rantai_blkg1 ? "Ada" : "Tidak Ada");
        cb_rantai_blkg2.setChecked(var_rantai_blkg1 && var_rantai_blkg2);
        cb_rantai_blkg2.setVisibility(var_rantai_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_rantai_blkg2.setText(var_rantai_blkg2 ? "Baik" : "Rusak");

        cb_spak_blkg1.setChecked(var_spak_blkg1);
        cb_spak_blkg1.setText(var_spak_blkg1 ? "Ada" : "Tidak Ada");
        cb_spak_blkg2.setChecked(var_spak_blkg1 && var_spak_blkg2);
        cb_spak_blkg2.setVisibility(var_spak_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_spak_blkg2.setText(var_spak_blkg2 ? "Baik" : "Rusak");

        cb_cover_accu1.setChecked(var_cover_accu1);
        cb_cover_accu1.setText(var_cover_accu1 ? "Ada" : "Tidak Ada");
        cb_cover_accu2.setChecked(var_cover_accu1 && var_cover_accu2);
        cb_cover_accu2.setVisibility(var_cover_accu1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_accu2.setText(var_cover_accu2 ? "Baik" : "Rusak");

        cb_cover_lampurem1.setChecked(var_cover_lampurem1);
        cb_cover_lampurem1.setText(var_cover_lampurem1 ? "Ada" : "Tidak Ada");
        cb_cover_lampurem2.setChecked(var_cover_lampurem1 && var_cover_lampurem2);
        cb_cover_lampurem2.setVisibility(var_cover_lampurem1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_lampurem2.setText(var_cover_lampurem2 ? "Baik" : "Rusak");

        cb_tangki_bensin1.setChecked(var_tangki_bensin1);
        cb_tangki_bensin1.setText(var_tangki_bensin1 ? "Ada" : "Tidak Ada");
        cb_tangki_bensin2.setChecked(var_tangki_bensin1 && var_tangki_bensin2);
        cb_tangki_bensin2.setVisibility(var_tangki_bensin1 ? View.VISIBLE : View.INVISIBLE);
        cb_tangki_bensin2.setText(var_tangki_bensin2 ? "Baik" : "Rusak");

        cb_cover_bawah1.setChecked(var_cover_bawah1);
        cb_cover_bawah1.setText(var_cover_bawah1 ? "Ada" : "Tidak Ada");
        cb_cover_bawah2.setChecked(var_cover_bawah1 && var_cover_bawah2);
        cb_cover_bawah2.setVisibility(var_cover_bawah1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_bawah2.setText(var_cover_bawah2 ? "Baik" : "Rusak");

        cb_dek_desk1.setChecked(var_dek_desk1);
        cb_dek_desk1.setText(var_dek_desk1 ? "Ada" : "Tidak Ada");
        cb_dek_desk2.setChecked(var_dek_desk1 && var_dek_desk2);
        cb_dek_desk2.setVisibility(var_dek_desk1 ? View.VISIBLE : View.INVISIBLE);
        cb_dek_desk2.setText(var_dek_desk2 ? "Baik" : "Rusak");

        cb_cover_mesin1.setChecked(var_cover_mesin1);
        cb_cover_mesin1.setText(var_cover_mesin1 ? "Ada" : "Tidak Ada");
        cb_cover_mesin2.setChecked(var_cover_mesin1 && var_cover_mesin2);
        cb_cover_mesin2.setVisibility(var_cover_mesin1 ? View.VISIBLE : View.INVISIBLE);
        cb_cover_mesin2.setText(var_cover_mesin2 ? "Baik" : "Rusak");

        cb_emblem1.setChecked(var_emblem1);
        cb_emblem1.setText(var_emblem1 ? "Ada" : "Tidak Ada");
        cb_emblem2.setChecked(var_emblem1 && var_emblem2);
        cb_emblem2.setVisibility(var_emblem1 ? View.VISIBLE : View.INVISIBLE);
        cb_emblem2.setText(var_emblem2 ? "Baik" : "Rusak");

        cb_blok_mesin1.setChecked(var_blok_mesin1);
        cb_blok_mesin1.setText(var_blok_mesin1 ? "Ada" : "Tidak Ada");
        cb_blok_mesin2.setChecked(var_blok_mesin1 && var_blok_mesin2);
        cb_blok_mesin2.setVisibility(var_blok_mesin1 ? View.VISIBLE : View.INVISIBLE);
        cb_blok_mesin2.setText(var_blok_mesin2 ? "Baik" : "Rusak");

        cb_carburator1.setChecked(var_carburator1);
        cb_carburator1.setText(var_carburator1 ? "Ada" : "Tidak Ada");
        cb_carburator2.setChecked(var_carburator1 && var_carburator2);
        cb_carburator2.setVisibility(var_carburator1 ? View.VISIBLE : View.INVISIBLE);
        cb_carburator2.setText(var_carburator2 ? "Baik" : "Rusak");

        cb_filter_carbu1.setChecked(var_filter_carbu1);
        cb_filter_carbu1.setText(var_filter_carbu1 ? "Ada" : "Tidak Ada");
        cb_filter_carbu2.setChecked(var_filter_carbu1 && var_filter_carbu2);
        cb_filter_carbu2.setVisibility(var_filter_carbu1 ? View.VISIBLE : View.INVISIBLE);
        cb_filter_carbu2.setText(var_filter_carbu2 ? "Baik" : "Rusak");

        cb_busi1.setChecked(var_busi1);
        cb_busi1.setText(var_busi1 ? "Ada" : "Tidak Ada");
        cb_busi2.setChecked(var_busi1 && var_busi2);
        cb_busi2.setVisibility(var_busi1 ? View.VISIBLE : View.INVISIBLE);
        cb_busi2.setText(var_busi2 ? "Baik" : "Rusak");

        cb_cdi1.setChecked(var_cdi1);
        cb_cdi1.setText(var_cdi1 ? "Ada" : "Tidak Ada");
        cb_cdi2.setChecked(var_cdi1 && var_cdi2);
        cb_cdi2.setVisibility(var_cdi1 ? View.VISIBLE : View.INVISIBLE);
        cb_cdi2.setText(var_cdi2 ? "Baik" : "Rusak");

        cb_accu1.setChecked(var_accu1);
        cb_accu1.setText(var_accu1 ? "Ada" : "Tidak Ada");
        cb_accu2.setChecked(var_accu1 && var_accu2);
        cb_accu2.setVisibility(var_accu1 ? View.VISIBLE : View.INVISIBLE);
        cb_accu2.setText(var_accu2 ? "Baik" : "Rusak");

        cb_kick_starter1.setChecked(var_kick_starter1);
        cb_kick_starter1.setText(var_kick_starter1 ? "Ada" : "Tidak Ada");
        cb_kick_starter2.setChecked(var_kick_starter1 && var_kick_starter2);
        cb_kick_starter2.setVisibility(var_kick_starter1 ? View.VISIBLE : View.INVISIBLE);
        cb_kick_starter2.setText(var_kick_starter2 ? "Baik" : "Rusak");

        cb_perseneling1.setChecked(var_perseneling1);
        cb_perseneling1.setText(var_perseneling1 ? "Ada" : "Tidak Ada");
        cb_perseneling2.setChecked(var_perseneling1 && var_perseneling2);
        cb_perseneling2.setVisibility(var_perseneling1 ? View.VISIBLE : View.INVISIBLE);
        cb_perseneling2.setText(var_perseneling2 ? "Baik" : "Rusak");

        cb_knalpot1.setChecked(var_knalpot1);
        cb_knalpot1.setText(var_knalpot1 ? "Ada" : "Tidak Ada");
        cb_knalpot2.setChecked(var_knalpot1 && var_knalpot2);
        cb_knalpot2.setVisibility(var_knalpot1 ? View.VISIBLE : View.INVISIBLE);
        cb_knalpot2.setText(var_knalpot2 ? "Baik" : "Rusak");

        cb_coil1.setChecked(var_coil1);
        cb_coil1.setText(var_coil1 ? "Ada" : "Tidak Ada");
        cb_coil2.setChecked(var_coil1 && var_coil2);
        cb_coil2.setVisibility(var_coil1 ? View.VISIBLE : View.INVISIBLE);
        cb_coil2.setText(var_coil2 ? "Baik" : "Rusak");

        cb_blok1.setChecked(var_blok1);
        cb_blok1.setText(var_blok1 ? "Ada" : "Tidak Ada");
        cb_blok2.setChecked(var_blok1 && var_blok2);
        cb_blok2.setVisibility(var_blok1 ? View.VISIBLE : View.INVISIBLE);
        cb_blok2.setText(var_blok2 ? "Baik" : "Rusak");

        cb_tutupcvt1.setChecked(var_tutupcvt1);
        cb_tutupcvt1.setText(var_tutupcvt1 ? "Ada" : "Tidak Ada");
        cb_tutupcvt2.setChecked(var_tutupcvt1 && var_tutupcvt2);
        cb_tutupcvt2.setVisibility(var_tutupcvt1 ? View.VISIBLE : View.INVISIBLE);
        cb_tutupcvt2.setText(var_tutupcvt2 ? "Baik" : "Rusak");

        cb_radiator1.setChecked(var_radiator1);
        cb_radiator1.setText(var_radiator1 ? "Ada" : "Tidak Ada");
        cb_radiator2.setChecked(var_radiator1 && var_radiator2);
        cb_radiator2.setVisibility(var_radiator1 ? View.VISIBLE : View.INVISIBLE);
        cb_radiator2.setText(var_radiator2 ? "Baik" : "Rusak");

        // Melanjutkan dengan variabel lainnya...
        cb_lampu_depan1.setChecked(var_lampu_depan1);
        cb_lampu_depan1.setText(var_lampu_depan1 ? "Ada" : "Tidak Ada");
        cb_lampu_depan2.setChecked(var_lampu_depan1 && var_lampu_depan2);
        cb_lampu_depan2.setVisibility(var_lampu_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_lampu_depan2.setText(var_lampu_depan2 ? "Baik" : "Rusak");

        cb_sein_depan1.setChecked(var_sein_depan1);
        cb_sein_depan1.setText(var_sein_depan1 ? "Ada" : "Tidak Ada");
        cb_sein_depan2.setChecked(var_sein_depan1 && var_sein_depan2);
        cb_sein_depan2.setVisibility(var_sein_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_sein_depan2.setText(var_sein_depan2 ? "Baik" : "Rusak");

        cb_sein_blkg1.setChecked(var_sein_blkg1);
        cb_sein_blkg1.setText(var_sein_blkg1 ? "Ada" : "Tidak Ada");
        cb_sein_blkg2.setChecked(var_sein_blkg1 && var_sein_blkg2);
        cb_sein_blkg2.setVisibility(var_sein_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_sein_blkg2.setText(var_sein_blkg2 ? "Baik" : "Rusak");

        cb_lampu_rem1.setChecked(var_lampu_rem1);
        cb_lampu_rem1.setText(var_lampu_rem1 ? "Ada" : "Tidak Ada");
        cb_lampu_rem2.setChecked(var_lampu_rem1 && var_lampu_rem2);
        cb_lampu_rem2.setVisibility(var_lampu_rem1 ? View.VISIBLE : View.INVISIBLE);
        cb_lampu_rem2.setText(var_lampu_rem2 ? "Baik" : "Rusak");

        cb_handlerem_depan1.setChecked(var_handlerem_depan1);
        cb_handlerem_depan1.setText(var_handlerem_depan1 ? "Ada" : "Tidak Ada");
        cb_handlerem_depan2.setChecked(var_handlerem_depan1 && var_handlerem_depan2);
        cb_handlerem_depan2.setVisibility(var_handlerem_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_handlerem_depan2.setText(var_handlerem_depan2 ? "Baik" : "Rusak");

        cb_pedalrem_blkg1.setChecked(var_pedalrem_blkg1);
        cb_pedalrem_blkg1.setText(var_pedalrem_blkg1 ? "Ada" : "Tidak Ada");
        cb_pedalrem_blkg2.setChecked(var_pedalrem_blkg1 && var_pedalrem_blkg2);
        cb_pedalrem_blkg2.setVisibility(var_pedalrem_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_pedalrem_blkg2.setText(var_pedalrem_blkg2 ? "Baik" : "Rusak");

        cb_handlerem_blkg1.setChecked(var_handlerem_blkg1);
        cb_handlerem_blkg1.setText(var_handlerem_blkg1 ? "Ada" : "Tidak Ada");
        cb_handlerem_blkg2.setChecked(var_handlerem_blkg1 && var_handlerem_blkg2);
        cb_handlerem_blkg2.setVisibility(var_handlerem_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_handlerem_blkg2.setText(var_handlerem_blkg2 ? "Baik" : "Rusak");

        cb_handle_kopling1.setChecked(var_handle_kopling1);
        cb_handle_kopling1.setText(var_handle_kopling1 ? "Ada" : "Tidak Ada");
        cb_handle_kopling2.setChecked(var_handle_kopling1 && var_handle_kopling2);
        cb_handle_kopling2.setVisibility(var_handle_kopling1 ? View.VISIBLE : View.INVISIBLE);
        cb_handle_kopling2.setText(var_handle_kopling2 ? "Baik" : "Rusak");

        cb_master_rem1.setChecked(var_master_rem1);
        cb_master_rem1.setText(var_master_rem1 ? "Ada" : "Tidak Ada");
        cb_master_rem2.setChecked(var_master_rem1 && var_master_rem2);
        cb_master_rem2.setVisibility(var_master_rem1 ? View.VISIBLE : View.INVISIBLE);
        cb_master_rem2.setText(var_master_rem2 ? "Baik" : "Rusak");

        cb_caliper1.setChecked(var_caliper1);
        cb_caliper1.setText(var_caliper1 ? "Ada" : "Tidak Ada");
        cb_caliper2.setChecked(var_caliper1 && var_caliper2);
        cb_caliper2.setVisibility(var_caliper1 ? View.VISIBLE : View.INVISIBLE);
        cb_caliper2.setText(var_caliper2 ? "Baik" : "Rusak");

        cb_cakram1.setChecked(var_cakram1);
        cb_cakram1.setText(var_cakram1 ? "Ada" : "Tidak Ada");
        cb_cakram2.setChecked(var_cakram1 && var_cakram2);
        cb_cakram2.setVisibility(var_cakram1 ? View.VISIBLE : View.INVISIBLE);
        cb_cakram2.setText(var_cakram2 ? "Baik" : "Rusak");

        cb_shock_depan1.setChecked(var_shock_depan1);
        cb_shock_depan1.setText(var_shock_depan1 ? "Ada" : "Tidak Ada");
        cb_shock_depan2.setChecked(var_shock_depan1 && var_shock_depan2);
        cb_shock_depan2.setVisibility(var_shock_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_shock_depan2.setText(var_shock_depan2 ? "Baik" : "Rusak");

        cb_shock_blkg1.setChecked(var_shock_blkg1);
        cb_shock_blkg1.setText(var_shock_blkg1 ? "Ada" : "Tidak Ada");
        cb_shock_blkg2.setChecked(var_shock_blkg1 && var_shock_blkg2);
        cb_shock_blkg2.setVisibility(var_shock_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_shock_blkg2.setText(var_shock_blkg2 ? "Baik" : "Rusak");

        cb_tromol_depan1.setChecked(var_tromol_depan1);
        cb_tromol_depan1.setText(var_tromol_depan1 ? "Ada" : "Tidak Ada");
        cb_tromol_depan2.setChecked(var_tromol_depan1 && var_tromol_depan2);
        cb_tromol_depan2.setVisibility(var_tromol_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_tromol_depan2.setText(var_tromol_depan2 ? "Baik" : "Rusak");

        cb_tromol_blkg1.setChecked(var_tromol_blkg1);
        cb_tromol_blkg1.setText(var_tromol_blkg1 ? "Ada" : "Tidak Ada");
        cb_tromol_blkg2.setChecked(var_tromol_blkg1 && var_tromol_blkg2);
        cb_tromol_blkg2.setVisibility(var_tromol_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_tromol_blkg2.setText(var_tromol_blkg2 ? "Baik" : "Rusak");

        cb_footstep_depan1.setChecked(var_footstep_depan1);
        cb_footstep_depan1.setText(var_footstep_depan1 ? "Ada" : "Tidak Ada");
        cb_footstep_depan2.setChecked(var_footstep_depan1 && var_footstep_depan2);
        cb_footstep_depan2.setVisibility(var_footstep_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_footstep_depan2.setText(var_footstep_depan2 ? "Baik" : "Rusak");

        cb_footstep_blkg1.setChecked(var_footstep_blkg1);
        cb_footstep_blkg1.setText(var_footstep_blkg1 ? "Ada" : "Tidak Ada");
        cb_footstep_blkg2.setChecked(var_footstep_blkg1 && var_footstep_blkg2);
        cb_footstep_blkg2.setVisibility(var_footstep_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_footstep_blkg2.setText(var_footstep_blkg2 ? "Baik" : "Rusak");

        cb_swing_arm1.setChecked(var_swing_arm1);
        cb_swing_arm1.setText(var_swing_arm1 ? "Ada" : "Tidak Ada");
        cb_swing_arm2.setChecked(var_swing_arm1 && var_swing_arm2);
        cb_swing_arm2.setVisibility(var_swing_arm1 ? View.VISIBLE : View.INVISIBLE);
        cb_swing_arm2.setText(var_swing_arm2 ? "Baik" : "Rusak");

        cb_rantai_fanbelt1.setChecked(var_rantai_fanbelt1);
        cb_rantai_fanbelt1.setText(var_rantai_fanbelt1 ? "Ada" : "Tidak Ada");
        cb_rantai_fanbelt2.setChecked(var_rantai_fanbelt1 && var_rantai_fanbelt2);
        cb_rantai_fanbelt2.setVisibility(var_rantai_fanbelt1 ? View.VISIBLE : View.INVISIBLE);
        cb_rantai_fanbelt2.setText(var_rantai_fanbelt2 ? "Baik" : "Rusak");

        cb_gear_depan1.setChecked(var_gear_depan1);
        cb_gear_depan1.setText(var_gear_depan1 ? "Ada" : "Tidak Ada");
        cb_gear_depan2.setChecked(var_gear_depan1 && var_gear_depan2);
        cb_gear_depan2.setVisibility(var_gear_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_gear_depan2.setText(var_gear_depan2 ? "Baik" : "Rusak");

        cb_gear_blkg1.setChecked(var_gear_blkg1);
        cb_gear_blkg1.setText(var_gear_blkg1 ? "Ada" : "Tidak Ada");
        cb_gear_blkg2.setChecked(var_gear_blkg1 && var_gear_blkg2);
        cb_gear_blkg2.setVisibility(var_gear_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_gear_blkg2.setText(var_gear_blkg2 ? "Baik" : "Rusak");

        cb_standar_tengah1.setChecked(var_standar_tengah1);
        cb_standar_tengah1.setText(var_standar_tengah1 ? "Ada" : "Tidak Ada");
        cb_standar_tengah2.setChecked(var_standar_tengah1 && var_standar_tengah2);
        cb_standar_tengah2.setVisibility(var_standar_tengah1 ? View.VISIBLE : View.INVISIBLE);
        cb_standar_tengah2.setText(var_standar_tengah2 ? "Baik" : "Rusak");

        cb_standar_samping1.setChecked(var_standar_samping1);
        cb_standar_samping1.setText(var_standar_samping1 ? "Ada" : "Tidak Ada");
        cb_standar_samping2.setChecked(var_standar_samping1 && var_standar_samping2);
        cb_standar_samping2.setVisibility(var_standar_samping1 ? View.VISIBLE : View.INVISIBLE);
        cb_standar_samping2.setText(var_standar_samping2 ? "Baik" : "Rusak");

        cb_ban_depan1.setChecked(var_ban_depan1);
        cb_ban_depan1.setText(var_ban_depan1 ? "Ada" : "Tidak Ada");
        cb_ban_depan2.setChecked(var_ban_depan1 && var_ban_depan2);
        cb_ban_depan2.setVisibility(var_ban_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_ban_depan2.setText(var_ban_depan2 ? "Baik" : "Rusak");

        cb_ban_blkg1.setChecked(var_ban_blkg1);
        cb_ban_blkg1.setText(var_ban_blkg1 ? "Ada" : "Tidak Ada");
        cb_ban_blkg2.setChecked(var_ban_blkg1 && var_ban_blkg2);
        cb_ban_blkg2.setVisibility(var_ban_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_ban_blkg2.setText(var_ban_blkg2 ? "Baik" : "Rusak");

        cb_velg_depan1.setChecked(var_velg_depan1);
        cb_velg_depan1.setText(var_velg_depan1 ? "Ada" : "Tidak Ada");
        cb_velg_depan2.setChecked(var_velg_depan1 && var_velg_depan2);
        cb_velg_depan2.setVisibility(var_velg_depan1 ? View.VISIBLE : View.INVISIBLE);
        cb_velg_depan2.setText(var_velg_depan2 ? "Baik" : "Rusak");

        cb_velg_blkg1.setChecked(var_velg_blkg1);
        cb_velg_blkg1.setText(var_velg_blkg1 ? "Ada" : "Tidak Ada");
        cb_velg_blkg2.setChecked(var_velg_blkg1 && var_velg_blkg2);
        cb_velg_blkg2.setVisibility(var_velg_blkg1 ? View.VISIBLE : View.INVISIBLE);
        cb_velg_blkg2.setText(var_velg_blkg2 ? "Baik" : "Rusak");

        cb_kaca_spion1.setChecked(var_kaca_spion1);
        cb_kaca_spion1.setText(var_kaca_spion1 ? "Ada" : "Tidak Ada");
        cb_kaca_spion2.setChecked(var_kaca_spion1 && var_kaca_spion2);
        cb_kaca_spion2.setVisibility(var_kaca_spion1 ? View.VISIBLE : View.INVISIBLE);
        cb_kaca_spion2.setText(var_kaca_spion2 ? "Baik" : "Rusak");

        cb_speedometer1.setChecked(var_speedometer1);
        cb_speedometer1.setText(var_speedometer1 ? "Ada" : "Tidak Ada");
        cb_speedometer2.setChecked(var_speedometer1 && var_speedometer2);
        cb_speedometer2.setVisibility(var_speedometer1 ? View.VISIBLE : View.INVISIBLE);
        cb_speedometer2.setText(var_speedometer2 ? "Baik" : "Rusak");

        cb_Grip_stang1.setChecked(var_grip_stang1);
        cb_Grip_stang1.setText(var_grip_stang1 ? "Ada" : "Tidak Ada");
        cb_Grip_stang2.setChecked(var_grip_stang1 && var_grip_stang2);
        cb_Grip_stang2.setVisibility(var_grip_stang1 ? View.VISIBLE : View.INVISIBLE);
        cb_Grip_stang2.setText(var_grip_stang2 ? "Baik" : "Rusak");

        cb_Rumah_kunci1.setChecked(var_rumah_kunci1);
        cb_Rumah_kunci1.setText(var_rumah_kunci1 ? "Ada" : "Tidak Ada");
        cb_Rumah_kunci2.setChecked(var_rumah_kunci1 && var_rumah_kunci2);
        cb_Rumah_kunci2.setVisibility(var_rumah_kunci1 ? View.VISIBLE : View.INVISIBLE);
        cb_Rumah_kunci2.setText(var_rumah_kunci2 ? "Baik" : "Rusak");

        cb_Klakson1.setChecked(var_klakson1);
        cb_Klakson1.setText(var_klakson1 ? "Ada" : "Tidak Ada");
        cb_Klakson2.setChecked(var_klakson1 && var_klakson2);
        cb_Klakson2.setVisibility(var_klakson1 ? View.VISIBLE : View.INVISIBLE);
        cb_Klakson2.setText(var_klakson2 ? "Baik" : "Rusak");

        cb_Behel1.setChecked(var_behel1);
        cb_Behel1.setText(var_behel1 ? "Ada" : "Tidak Ada");
        cb_Behel2.setChecked(var_behel1 && var_behel2);
        cb_Behel2.setVisibility(var_behel1 ? View.VISIBLE : View.INVISIBLE);
        cb_Behel2.setText(var_behel2 ? "Baik" : "Rusak");

        cb_Tutup_knalpot1.setChecked(var_tutup_knalpot1);
        cb_Tutup_knalpot1.setText(var_tutup_knalpot1 ? "Ada" : "Tidak Ada");
        cb_Tutup_knalpot2.setChecked(var_tutup_knalpot1 && var_tutup_knalpot2);
        cb_Tutup_knalpot2.setVisibility(var_tutup_knalpot1 ? View.VISIBLE : View.INVISIBLE);
        cb_Tutup_knalpot2.setText(var_tutup_knalpot2 ? "Baik" : "Rusak");

        cb_jok1.setChecked(var_jok1);
        cb_jok1.setText(var_jok1 ? "Ada" : "Tidak Ada");
        cb_jok2.setChecked(var_jok1 && var_jok2);
        cb_jok2.setVisibility(var_jok1 ? View.VISIBLE : View.INVISIBLE);
        cb_jok2.setText(var_jok2 ? "Baik" : "Rusak");

        cb_Stripping1.setChecked(var_stripping1);
        cb_Stripping1.setText(var_stripping1 ? "Ada" : "Tidak Ada");
        cb_Stripping2.setChecked(var_stripping1 && var_stripping2);
        cb_Stripping2.setVisibility(var_stripping1 ? View.VISIBLE : View.INVISIBLE);
        cb_Stripping2.setText(var_stripping2 ? "Baik" : "Rusak");

        cb_Tombol_navigasi1.setChecked(var_tombol_navigasi1);
        cb_Tombol_navigasi1.setText(var_tombol_navigasi1 ? "Ada" : "Tidak Ada");
        cb_Tombol_navigasi2.setChecked(var_tombol_navigasi1 && var_tombol_navigasi2);
        cb_Tombol_navigasi2.setVisibility(var_tombol_navigasi1 ? View.VISIBLE : View.INVISIBLE);
        cb_Tombol_navigasi2.setText(var_tombol_navigasi2 ? "Baik" : "Rusak");

        etSd_stnk.setText(stnk[2] + "-" + stnk[1] + "-" + stnk[0]);
        cbStnk1_lain.setChecked(var_stnk_lain1);
        cbStnk1_lain.setText((var_stnk_lain1 == true) ? "Ada" : "Tidak Ada");
        cbStnk2_lain.setChecked(var_stnk_lain2);
        cbStnk2_lain.setVisibility((var_stnk_lain1 == true) ? View.VISIBLE : View.INVISIBLE);
        cbStnk2_lain.setText((var_stnk_lain2 == true) ? "Baik" : "Rusak");

        cbBukumnl1_lain.setChecked(var_bukumnl1);
        cbBukumnl1_lain.setText((var_bukumnl1 == true) ? "Ada" : "Tidak Ada");
        cbBukumnl2_lain.setChecked(var_bukumnl2);
        cbBukumnl2_lain.setVisibility((var_bukumnl1 == true) ? View.VISIBLE : View.INVISIBLE);
        cbBukumnl2_lain.setText((var_bukumnl2 == true) ? "Baik" : "Rusak");

        etSd_stnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_stnk = new DatePickerDialog(BpCekCeklistMotorActivity.this, datestnk, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_stnk.show();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

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
        cbStnk1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbStnk1_lain.isChecked()){
                    cbStnk1_lain.setText("Ada");
                    cbStnk2_lain.setVisibility(View.VISIBLE);
                    cbStnk2_lain.setText("Rusak");
                    cbStnk2_lain.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbStnk1_lain(true);
//                    upd.first().setTdb_var_cbStnk1_lain("Ada");
//                    upd.first().setDb_var_cbStnk2_lain(false);
//                    upd.first().setTdb_var_cbStnk2_lain("Rusak");
//                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbStnk2_lain.setVisibility(View.INVISIBLE);
                    cbStnk1_lain.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbStnk1_lain(false);
//                    upd.first().setTdb_var_cbStnk1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbStnk2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbStnk2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbStnk2_lain.isChecked()){
                    cbStnk2_lain.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbStnk2_lain(true);
//                    upd.first().setTdb_var_cbStnk2_lain("Baik");
//                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbStnk2_lain.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbStnk2_lain(false);
//                    upd.first().setTdb_var_cbStnk2_lain("Rusak");
//                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbBukumnl1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukumnl1_lain.isChecked()){
                    cbBukumnl1_lain.setText("Ada");
                    cbBukumnl2_lain.setVisibility(View.VISIBLE);
                    cbBukumnl2_lain.setText("Rusak");
                    cbBukumnl2_lain.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukumnl1_lain(true);
//                    upd.first().setTdb_var_cbBukumnl1_lain("Ada");
//                    upd.first().setDb_var_cbBukumnl2_lain(false);
//                    upd.first().setTdb_var_cbBukumnl2_lain("Rusak");
//                    upd.first().setVdb_var_cbBukumnl2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBukumnl2_lain.setVisibility(View.INVISIBLE);
                    cbBukumnl1_lain.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukumnl1_lain(false);
//                    upd.first().setTdb_var_cbBukumnl1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbBukumnl2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbBukumnl2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukumnl2_lain.isChecked()){
                    cbBukumnl2_lain.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukumnl2_lain(true);
//                    upd.first().setTdb_var_cbBukumnl2_lain("Baik");
//                    upd.first().setVdb_var_cbBukumnl2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBukumnl2_lain.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<CeklistMotor> upd = realm.where(CeklistMotor.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukumnl2_lain(false);
//                    upd.first().setTdb_var_cbBukumnl2_lain("Rusak");
//                    upd.first().setVdb_var_cbBukumnl2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
    }

    private void sendData(){
        final String[] stnk = etSd_stnk.getText().toString().split("-");

        class SendData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BpCekCeklistMotorActivity.this,"","Processing...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showRespon(s);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,_appid);
                hashMap.put(configuration.KEY_CCODE,_ccode);
                hashMap.put(configuration.KEY_TOKEN,_token);
                hashMap.put(configuration.KEY_USERNAME,_name);
                hashMap.put(configuration.KEY_STATUS,"2");
                hashMap.put(configuration.KEY_PARENT_STATUS,"1");
                hashMap.put(configuration.KEY_NOPOL,nopol);

//                hashMap.put("cover_kepala1", String.valueOf(var_cover_kepala1));
//                hashMap.put("cover_kepala2", String.valueOf(var_cover_kepala2));
//                hashMap.put("cover_speedo1",String.valueOf(var_cover_speedo1));
//                hashMap.put("cover_speedo2",String.valueOf(var_cover_speedo2));
//                hashMap.put("cover_depan1",String.valueOf(var_cover_depan1));
//                hashMap.put("cover_depan2",String.valueOf(var_cover_depan2));
//                hashMap.put("sayap_kanan1",String.valueOf(var_sayap_kanan1));
//                hashMap.put("sayap_kanan2",String.valueOf(var_sayap_kanan2));
//                hashMap.put("sayap_kiri1",String.valueOf(var_sayap_kiri1));
//                hashMap.put("sayap_kiri2",String.valueOf(var_sayap_kiri2));
//                hashMap.put("spak_depan1",String.valueOf(var_spak_depan1));
//                hashMap.put("spak_depan2",String.valueOf(var_spak_depan2));
//
//                hashMap.put("cover_tengah1",String.valueOf(var_cover_tengah1));
//                hashMap.put("cover_tengah2",String.valueOf(var_cover_tengah2));
//                hashMap.put("cover_spgkanan1",String.valueOf(var_cover_spgkanan1));
//                hashMap.put("cover_spgkanan2",String.valueOf(var_cover_spgkanan2));
//                hashMap.put("cover_spgkiri1",String.valueOf(var_cover_spgkiri1));
//                hashMap.put("cover_spgkiri2",String.valueOf(var_cover_spgkiri2));
//                hashMap.put("rantai_depan1",String.valueOf(var_rantai_depan1));
//                hashMap.put("rantai_depan2",String.valueOf(var_rantai_depan2));
//                hashMap.put("rantai_blkg1",String.valueOf(var_rantai_blkg1));
//                hashMap.put("rantai_blkg2",String.valueOf(var_rantai_blkg2));
//                hashMap.put("spak_blkg1",String.valueOf(var_spak_blkg1));
//                hashMap.put("spak_blkg2",String.valueOf(var_spak_blkg2));
//                hashMap.put("cover_accu1",String.valueOf(var_cover_accu1));
//                hashMap.put("cover_accu2",String.valueOf(var_cover_accu2));
//                hashMap.put("cover_lampurem1",String.valueOf(var_cover_lampurem1));
//                hashMap.put("cover_lampurem2",String.valueOf(var_cover_lampurem2));
//                hashMap.put("tangki_bensin1",String.valueOf(var_tangki_bensin1));
//                hashMap.put("tangki_bensin2",String.valueOf(var_tangki_bensin2));
//                hashMap.put("cover_bawah1",String.valueOf(var_cover_bawah1));
//                hashMap.put("cover_bawah2",String.valueOf(var_cover_bawah2));
//                hashMap.put("dek_desk1",String.valueOf(var_dek_desk1));
//                hashMap.put("dek_desk2",String.valueOf(var_dek_desk2));
//                hashMap.put("cover_mesin1",String.valueOf(var_cover_mesin1));
//                hashMap.put("cover_mesin2",String.valueOf(var_cover_mesin2));
//                hashMap.put("emblem1",String.valueOf(var_emblem1));
//                hashMap.put("emblem2",String.valueOf(var_emblem2));
//                hashMap.put("blok_mesin1",String.valueOf(var_blok_mesin1));
//                hashMap.put("blok_mesin2",String.valueOf(var_blok_mesin2));
//                hashMap.put("carburator1",String.valueOf(var_carburator1));
//                hashMap.put("carburator2",String.valueOf(var_carburator2));
//                hashMap.put("filter_carbu1",String.valueOf(var_filter_carbu1));
//                hashMap.put("filter_carbu2",String.valueOf(var_filter_carbu2));
//                hashMap.put("busi1",String.valueOf(var_busi1));
//                hashMap.put("busi2",String.valueOf(var_busi2));
//                hashMap.put("cdi1",String.valueOf(var_cdi1));
//                hashMap.put("cdi2",String.valueOf(var_cdi2));
//                hashMap.put("accu1",String.valueOf(var_accu1));
//                hashMap.put("accu2",String.valueOf(var_accu2));
//                hashMap.put("kick_starter1",String.valueOf(var_kick_starter1));
//                hashMap.put("kick_starter2",String.valueOf(var_kick_starter2));
//                hashMap.put("perseneling1",String.valueOf(var_perseneling1));
//                hashMap.put("perseneling2",String.valueOf(var_perseneling2));
//                hashMap.put("knalpot1",String.valueOf(var_knalpot1));
//                hashMap.put("knalpot2",String.valueOf(var_knalpot2));
//                hashMap.put("coil1",String.valueOf(var_coil1));
//                hashMap.put("coil2",String.valueOf(var_coil2));
//                hashMap.put("blok1",String.valueOf(var_blok1));
//                hashMap.put("blok2",String.valueOf(var_blok2));
//                hashMap.put("tutupcvt1",String.valueOf(var_tutupcvt1));
//                hashMap.put("tutupcvt2",String.valueOf(var_tutupcvt2));
//                hashMap.put("radiator1",String.valueOf(var_radiator1));
//                hashMap.put("radiator2",String.valueOf(var_radiator2));
//                hashMap.put("lampu_depan1",String.valueOf(var_lampu_depan1));
//                hashMap.put("lampu_depan2",String.valueOf(var_lampu_depan2));
//                hashMap.put("sein_depan1",String.valueOf(var_sein_depan1));
//                hashMap.put("sein_depan2",String.valueOf(var_sein_depan2));
//                hashMap.put("sein_blkg1",String.valueOf(var_sein_blkg1));
//                hashMap.put("sein_blkg2",String.valueOf(var_sein_blkg2));
//                hashMap.put("lampu_rem1",String.valueOf(var_lampu_rem1));
//                hashMap.put("lampu_rem2",String.valueOf(var_lampu_rem2));
//                hashMap.put("handlerem_depan1",String.valueOf(var_handlerem_depan1));
//                hashMap.put("handlerem_depan2",String.valueOf(var_handlerem_depan2));
//                hashMap.put("pedalrem_blkg1",String.valueOf(var_pedalrem_blkg1));
//                hashMap.put("pedalrem_blkg2",String.valueOf(var_pedalrem_blkg2));
//                hashMap.put("handlerem_blkg1",String.valueOf(var_handlerem_blkg1));
//                hashMap.put("handlerem_blkg2",String.valueOf(var_handlerem_blkg2));
//                hashMap.put("handle_kopling1",String.valueOf(var_handle_kopling1));
//                hashMap.put("handle_kopling2",String.valueOf(var_handle_kopling2));
//                hashMap.put("master_rem1",String.valueOf(var_master_rem1));
//                hashMap.put("master_rem2",String.valueOf(var_master_rem2));
//                hashMap.put("caliper1",String.valueOf(var_caliper1));
//                hashMap.put("caliper2",String.valueOf(var_caliper2));
//                hashMap.put("cakram1",String.valueOf(var_cakram1));
//                hashMap.put("cakram2",String.valueOf(var_cakram2));
//                hashMap.put("shock_depan1",String.valueOf(var_shock_depan1));
//                hashMap.put("shock_depan2",String.valueOf(var_shock_depan2));
//                hashMap.put("shock_blkg1",String.valueOf(var_shock_blkg1));
//                hashMap.put("shock_blkg2",String.valueOf(var_shock_blkg2));
//                hashMap.put("tromol_depan1",String.valueOf(var_tromol_depan1));
//                hashMap.put("tromol_depan2",String.valueOf(var_tromol_depan2));
//                hashMap.put("tromol_blkg1",String.valueOf(var_tromol_blkg1));
//                hashMap.put("tromol_blkg2",String.valueOf(var_tromol_blkg2));
//                hashMap.put("footstep_depan1",String.valueOf(var_footstep_depan1));
//                hashMap.put("footstep_depan2",String.valueOf(var_footstep_depan2));
//                hashMap.put("footstep_blkg1",String.valueOf(var_footstep_blkg1));
//                hashMap.put("footstep_blkg2",String.valueOf(var_footstep_blkg2));
//                hashMap.put("swing_arm1",String.valueOf(var_swing_arm1));
//                hashMap.put("swing_arm2",String.valueOf(var_swing_arm2));
//                hashMap.put("rantai_fanbelt1",String.valueOf(var_rantai_fanbelt1));
//                hashMap.put("rantai_fanbelt2",String.valueOf(var_rantai_fanbelt2));
//                hashMap.put("gear_depan1",String.valueOf(var_gear_depan1));
//                hashMap.put("gear_depan2",String.valueOf(var_gear_depan2));
//                hashMap.put("gear_blkg1",String.valueOf(var_gear_blkg1));
//                hashMap.put("gear_blkg2",String.valueOf(var_gear_blkg2));
//                hashMap.put("standar_tengah1",String.valueOf(var_standar_tengah1));
//                hashMap.put("standar_tengah2",String.valueOf(var_standar_tengah2));
//                hashMap.put("standar_samping1",String.valueOf(var_standar_samping1));
//                hashMap.put("standar_samping2",String.valueOf(var_standar_samping2));
//                hashMap.put("ban_depan1",String.valueOf(var_ban_depan1));
//                hashMap.put("ban_depan2",String.valueOf(var_ban_depan2));
//                hashMap.put("ban_blkg1",String.valueOf(var_ban_blkg1));
//                hashMap.put("ban_blkg2",String.valueOf(var_ban_blkg2));
//                hashMap.put("velg_depan1",String.valueOf(var_velg_depan1));
//                hashMap.put("velg_depan2",String.valueOf(var_velg_depan2));
//                hashMap.put("velg_blkg1",String.valueOf(var_velg_blkg1));
//                hashMap.put("velg_blkg2",String.valueOf(var_velg_blkg2));
//                hashMap.put("kaca_spion1",String.valueOf(var_kaca_spion1));
//                hashMap.put("kaca_spion2",String.valueOf(var_kaca_spion2));
//                hashMap.put("speedometer1",String.valueOf(var_speedometer1));
//                hashMap.put("speedometer2",String.valueOf(var_speedometer2));
//                hashMap.put("Grip_stang1",String.valueOf(var_Grip_stang1));
//                hashMap.put("Grip_stang2",String.valueOf(var_Grip_stang2));
//                hashMap.put("Rumah_kunci1",String.valueOf(var_Rumah_kunci1));
//                hashMap.put("Rumah_kunci2",String.valueOf(var_Rumah_kunci2));
//                hashMap.put("Klakson1",String.valueOf(var_Klakson1));
//                hashMap.put("Klakson2",String.valueOf(var_Klakson2));
//                hashMap.put("Behel1",String.valueOf(var_Behel1));
//                hashMap.put("Behel2",String.valueOf(var_Behel2));
//                hashMap.put("Tutup_knalpot1",String.valueOf(var_Tutup_knalpot1));
//                hashMap.put("Tutup_knalpot2",String.valueOf(var_Tutup_knalpot2));
//                hashMap.put("jok1",String.valueOf(var_jok1));
//                hashMap.put("jok2",String.valueOf(var_jok2));
//                hashMap.put("Stripping1",String.valueOf(var_Stripping1));
//                hashMap.put("Stripping2",String.valueOf(var_Stripping2));
//                hashMap.put("Tombol_navigasi1",String.valueOf(var_Tombol_navigasi1));
//                hashMap.put("Tombol_navigasi2",String.valueOf(var_Tombol_navigasi2));
//
//                if (!etSd_stnk.getText().toString().equals("")) {
//                    final String[] stnk = etSd_stnk.getText().toString().split("-");
//                    hashMap.put("stnk_lain", stnk[2] + "-" + stnk[1] + "-" + stnk[0]);
//                }
//                hashMap.put("stnk1_lain",String.valueOf(var_cbStnk1_lain));
//                hashMap.put("stnk2_lain",String.valueOf(var_cbStnk2_lain));
//                hashMap.put("bukumnl1_lain",String.valueOf(var_cbBukumnl1_lain));
//                hashMap.put("bukumnl2_lain",String.valueOf(var_cbBukumnl2_lain));

                hashMap.put("cover_kepala1", String.valueOf(cb_cover_kepala1.isChecked()));
                hashMap.put("cover_kepala2", String.valueOf(cb_cover_kepala2.isChecked()));
                hashMap.put("cover_speedo1", String.valueOf(cb_cover_speedo1.isChecked()));
                hashMap.put("cover_speedo2", String.valueOf(cb_cover_speedo2.isChecked()));
                hashMap.put("cover_depan1", String.valueOf(cb_cover_depan1.isChecked()));
                hashMap.put("cover_depan2", String.valueOf(cb_cover_depan2.isChecked()));
                hashMap.put("sayap_kanan1", String.valueOf(cb_sayap_kanan1.isChecked()));
                hashMap.put("sayap_kanan2", String.valueOf(cb_sayap_kanan2.isChecked()));
                hashMap.put("sayap_kiri1", String.valueOf(cb_sayap_kiri1.isChecked()));
                hashMap.put("sayap_kiri2", String.valueOf(cb_sayap_kiri2.isChecked()));
                hashMap.put("spak_depan1", String.valueOf(cb_spak_depan1.isChecked()));
                hashMap.put("spak_depan2", String.valueOf(cb_spak_depan2.isChecked()));
                hashMap.put("cover_tengah1", String.valueOf(cb_cover_tengah1.isChecked()));
                hashMap.put("cover_tengah2", String.valueOf(cb_cover_tengah2.isChecked()));
                hashMap.put("cover_spgkanan1", String.valueOf(cb_cover_spgkanan1.isChecked()));
                hashMap.put("cover_spgkanan2", String.valueOf(cb_cover_spgkanan2.isChecked()));
                hashMap.put("cover_spgkiri1", String.valueOf(cb_cover_spgkiri1.isChecked()));
                hashMap.put("cover_spgkiri2", String.valueOf(cb_cover_spgkiri2.isChecked()));
                hashMap.put("rantai_depan1", String.valueOf(cb_rantai_depan1.isChecked()));
                hashMap.put("rantai_depan2", String.valueOf(cb_rantai_depan2.isChecked()));
                hashMap.put("rantai_blkg1", String.valueOf(cb_rantai_blkg1.isChecked()));
                hashMap.put("rantai_blkg2", String.valueOf(cb_rantai_blkg2.isChecked()));
                hashMap.put("spak_blkg1", String.valueOf(cb_spak_blkg1.isChecked()));
                hashMap.put("spak_blkg2", String.valueOf(cb_spak_blkg2.isChecked()));
                hashMap.put("cover_accu1", String.valueOf(cb_cover_accu1.isChecked()));
                hashMap.put("cover_accu2", String.valueOf(cb_cover_accu2.isChecked()));
                hashMap.put("cover_lampurem1", String.valueOf(cb_cover_lampurem1.isChecked()));
                hashMap.put("cover_lampurem2", String.valueOf(cb_cover_lampurem2.isChecked()));
                hashMap.put("tangki_bensin1", String.valueOf(cb_tangki_bensin1.isChecked()));
                hashMap.put("tangki_bensin2", String.valueOf(cb_tangki_bensin2.isChecked()));
                hashMap.put("cover_bawah1", String.valueOf(cb_cover_bawah1.isChecked()));
                hashMap.put("cover_bawah2", String.valueOf(cb_cover_bawah2.isChecked()));
                hashMap.put("dek_desk1", String.valueOf(cb_dek_desk1.isChecked()));
                hashMap.put("dek_desk2", String.valueOf(cb_dek_desk2.isChecked()));
                hashMap.put("cover_mesin1", String.valueOf(cb_cover_mesin1.isChecked()));
                hashMap.put("cover_mesin2", String.valueOf(cb_cover_mesin2.isChecked()));
                hashMap.put("emblem1", String.valueOf(cb_emblem1.isChecked()));
                hashMap.put("emblem2", String.valueOf(cb_emblem2.isChecked()));
                hashMap.put("blok_mesin1", String.valueOf(cb_blok_mesin1.isChecked()));
                hashMap.put("blok_mesin2", String.valueOf(cb_blok_mesin2.isChecked()));
                hashMap.put("carburator1", String.valueOf(cb_carburator1.isChecked()));
                hashMap.put("carburator2", String.valueOf(cb_carburator2.isChecked()));
                hashMap.put("filter_carbu1", String.valueOf(cb_filter_carbu1.isChecked()));
                hashMap.put("filter_carbu2", String.valueOf(cb_filter_carbu2.isChecked()));
                hashMap.put("busi1", String.valueOf(cb_busi1.isChecked()));
                hashMap.put("busi2", String.valueOf(cb_busi2.isChecked()));
                hashMap.put("cdi1", String.valueOf(cb_cdi1.isChecked()));
                hashMap.put("cdi2", String.valueOf(cb_cdi2.isChecked()));
                hashMap.put("accu1", String.valueOf(cb_accu1.isChecked()));
                hashMap.put("accu2", String.valueOf(cb_accu2.isChecked()));
                hashMap.put("kick_starter1", String.valueOf(cb_kick_starter1.isChecked()));
                hashMap.put("kick_starter2", String.valueOf(cb_kick_starter2.isChecked()));
                hashMap.put("perseneling1", String.valueOf(cb_perseneling1.isChecked()));
                hashMap.put("perseneling2", String.valueOf(cb_perseneling2.isChecked()));
                hashMap.put("knalpot1", String.valueOf(cb_knalpot1.isChecked()));
                hashMap.put("knalpot2", String.valueOf(cb_knalpot2.isChecked()));
                hashMap.put("coil1", String.valueOf(cb_coil1.isChecked()));
                hashMap.put("coil2", String.valueOf(cb_coil2.isChecked()));
                hashMap.put("blok1", String.valueOf(cb_blok1.isChecked()));
                hashMap.put("blok2", String.valueOf(cb_blok2.isChecked()));
                hashMap.put("tutupcvt1", String.valueOf(cb_tutupcvt1.isChecked()));
                hashMap.put("tutupcvt2", String.valueOf(cb_tutupcvt2.isChecked()));
                hashMap.put("radiator1", String.valueOf(cb_radiator1.isChecked()));
                hashMap.put("radiator2", String.valueOf(cb_radiator2.isChecked()));
                hashMap.put("lampu_depan1", String.valueOf(cb_lampu_depan1.isChecked()));
                hashMap.put("lampu_depan2", String.valueOf(cb_lampu_depan2.isChecked()));
                hashMap.put("sein_depan1", String.valueOf(cb_sein_depan1.isChecked()));
                hashMap.put("sein_depan2", String.valueOf(cb_sein_depan2.isChecked()));
                hashMap.put("sein_blkg1", String.valueOf(cb_sein_blkg1.isChecked()));
                hashMap.put("sein_blkg2", String.valueOf(cb_sein_blkg2.isChecked()));
                hashMap.put("lampu_rem1", String.valueOf(cb_lampu_rem1.isChecked()));
                hashMap.put("lampu_rem2", String.valueOf(cb_lampu_rem2.isChecked()));
                hashMap.put("handlerem_depan1", String.valueOf(cb_handlerem_depan1.isChecked()));
                hashMap.put("handlerem_depan2", String.valueOf(cb_handlerem_depan2.isChecked()));
                hashMap.put("pedalrem_blkg1", String.valueOf(cb_pedalrem_blkg1.isChecked()));
                hashMap.put("pedalrem_blkg2", String.valueOf(cb_pedalrem_blkg2.isChecked()));
                hashMap.put("handlerem_blkg1", String.valueOf(cb_handlerem_blkg1.isChecked()));
                hashMap.put("handlerem_blkg2", String.valueOf(cb_handlerem_blkg2.isChecked()));
                hashMap.put("handle_kopling1", String.valueOf(cb_handle_kopling1.isChecked()));
                hashMap.put("handle_kopling2", String.valueOf(cb_handle_kopling2.isChecked()));
                hashMap.put("master_rem1", String.valueOf(cb_master_rem1.isChecked()));
                hashMap.put("master_rem2", String.valueOf(cb_master_rem2.isChecked()));
                hashMap.put("caliper1", String.valueOf(cb_caliper1.isChecked()));
                hashMap.put("caliper2", String.valueOf(cb_caliper2.isChecked()));
                hashMap.put("cakram1", String.valueOf(cb_cakram1.isChecked()));
                hashMap.put("cakram2", String.valueOf(cb_cakram2.isChecked()));
                hashMap.put("shock_depan1", String.valueOf(cb_shock_depan1.isChecked()));
                hashMap.put("shock_depan2", String.valueOf(cb_shock_depan2.isChecked()));
                hashMap.put("shock_blkg1", String.valueOf(cb_shock_blkg1.isChecked()));
                hashMap.put("shock_blkg2", String.valueOf(cb_shock_blkg2.isChecked()));
                hashMap.put("tromol_depan1", String.valueOf(cb_tromol_depan1.isChecked()));
                hashMap.put("tromol_depan2", String.valueOf(cb_tromol_depan2.isChecked()));
                hashMap.put("tromol_blkg1", String.valueOf(cb_tromol_blkg1.isChecked()));
                hashMap.put("tromol_blkg2", String.valueOf(cb_tromol_blkg2.isChecked()));
                hashMap.put("footstep_depan1", String.valueOf(cb_footstep_depan1.isChecked()));
                hashMap.put("footstep_depan2", String.valueOf(cb_footstep_depan2.isChecked()));
                hashMap.put("footstep_blkg1", String.valueOf(cb_footstep_blkg1.isChecked()));
                hashMap.put("footstep_blkg2", String.valueOf(cb_footstep_blkg2.isChecked()));
                hashMap.put("swing_arm1", String.valueOf(cb_swing_arm1.isChecked()));
                hashMap.put("swing_arm2", String.valueOf(cb_swing_arm2.isChecked()));
                hashMap.put("rantai_fanbelt1", String.valueOf(cb_rantai_fanbelt1.isChecked()));
                hashMap.put("rantai_fanbelt2", String.valueOf(cb_rantai_fanbelt2.isChecked()));
                hashMap.put("gear_depan1", String.valueOf(cb_gear_depan1.isChecked()));
                hashMap.put("gear_depan2", String.valueOf(cb_gear_depan2.isChecked()));
                hashMap.put("gear_blkg1", String.valueOf(cb_gear_blkg1.isChecked()));
                hashMap.put("gear_blkg2", String.valueOf(cb_gear_blkg2.isChecked()));
                hashMap.put("standar_tengah1", String.valueOf(cb_standar_tengah1.isChecked()));
                hashMap.put("standar_tengah2", String.valueOf(cb_standar_tengah2.isChecked()));
                hashMap.put("standar_samping1", String.valueOf(cb_standar_samping1.isChecked()));
                hashMap.put("standar_samping2", String.valueOf(cb_standar_samping2.isChecked()));
                hashMap.put("ban_depan1", String.valueOf(cb_ban_depan1.isChecked()));
                hashMap.put("ban_depan2", String.valueOf(cb_ban_depan2.isChecked()));
                hashMap.put("ban_blkg1", String.valueOf(cb_ban_blkg1.isChecked()));
                hashMap.put("ban_blkg2", String.valueOf(cb_ban_blkg2.isChecked()));
                hashMap.put("velg_depan1", String.valueOf(cb_velg_depan1.isChecked()));
                hashMap.put("velg_depan2", String.valueOf(cb_velg_depan2.isChecked()));
                hashMap.put("velg_blkg1", String.valueOf(cb_velg_blkg1.isChecked()));
                hashMap.put("velg_blkg2", String.valueOf(cb_velg_blkg2.isChecked()));
                hashMap.put("kaca_spion1", String.valueOf(cb_kaca_spion1.isChecked()));
                hashMap.put("kaca_spion2", String.valueOf(cb_kaca_spion2.isChecked()));
                hashMap.put("speedometer1", String.valueOf(cb_speedometer1.isChecked()));
                hashMap.put("speedometer2", String.valueOf(cb_speedometer2.isChecked()));
                hashMap.put("Grip_stang1", String.valueOf(cb_Grip_stang1.isChecked()));
                hashMap.put("Grip_stang2", String.valueOf(cb_Grip_stang2.isChecked()));
                hashMap.put("Rumah_kunci1", String.valueOf(cb_Rumah_kunci1.isChecked()));
                hashMap.put("Rumah_kunci2", String.valueOf(cb_Rumah_kunci2.isChecked()));
                hashMap.put("Klakson1", String.valueOf(cb_Klakson1.isChecked()));
                hashMap.put("Klakson2", String.valueOf(cb_Klakson2.isChecked()));
                hashMap.put("Behel1", String.valueOf(cb_Behel1.isChecked()));
                hashMap.put("Behel2", String.valueOf(cb_Behel2.isChecked()));
                hashMap.put("Tutup_knalpot1", String.valueOf(cb_Tutup_knalpot1.isChecked()));
                hashMap.put("Tutup_knalpot2", String.valueOf(cb_Tutup_knalpot2.isChecked()));
                hashMap.put("jok1", String.valueOf(cb_jok1.isChecked()));
                hashMap.put("jok2", String.valueOf(cb_jok2.isChecked()));
                hashMap.put("Stripping1", String.valueOf(cb_Stripping1.isChecked()));
                hashMap.put("Stripping2", String.valueOf(cb_Stripping2.isChecked()));
                hashMap.put("Tombol_navigasi1", String.valueOf(cb_Tombol_navigasi1.isChecked()));
                hashMap.put("Tombol_navigasi2", String.valueOf(cb_Tombol_navigasi2.isChecked()));
                hashMap.put("stnk_lain", stnk[2] + "-" + stnk[1] + "-" + stnk[0]);
                hashMap.put("stnk1_lain", String.valueOf(cbStnk1_lain.isChecked()));
                hashMap.put("stnk1_lai2", String.valueOf(cbStnk2_lain.isChecked()));
                hashMap.put("bukumnl1_lain", String.valueOf(cbBukumnl1_lain.isChecked()));
                hashMap.put("bukumnl2_lain", String.valueOf(cbBukumnl2_lain.isChecked()));

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_UPD_BASTK_BP_MOTOR,hashMap);
                return s;
            }
        }
        SendData ge = new SendData();
        ge.execute();
    }
    private void showRespon(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getString(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(code.equals("200")){
            Intent i = new Intent(BpCekCeklistMotorActivity.this,BpCekFotoActivity.class);
            Bundle b = new Bundle();
            b.putString("picpenarikan_diterima",var_picPenarikanDiterima);
            b.putString("picpenarikan",var_picPenarikanDiterima);
            b.putString("picpenyimpanan",var_picPenyimpanan);
            b.putString("picpenjualan",var_picPenarikanDiterima);
            b.putString("note_picpenyimpanan",var_note_picPenyimpanan);

            b.putString("img1", var_img1);
            b.putString("img2", var_img2);
            b.putString("img3", var_img3);
            b.putString("img4", var_img4);
            b.putString("img5", var_img5);
            b.putString("img6", var_img6);
            b.putString("img7", var_img7);
            b.putString("img8", var_img8);
            b.putString("img9", var_img9);
            b.putString("img10", var_img10);
            b.putString("img11", var_img11);
            b.putString("img12", var_img12);
            b.putString("nopol", nopol);
            b.putString("status",status);
            i.putExtras(b);
            startActivity(i);

        }else if (code.equals("304")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
    private DatePickerDialog.OnDateSetListener datestnk = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updatePickerStnk();
        }
    };
    private void updatePickerStnk() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etSd_stnk.setText(sdf.format(myCalendar.getTime()));
        }catch (Exception e){

        }
    }
}