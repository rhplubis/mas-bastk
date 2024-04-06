package com.tunasrent.auctionapps.taksasi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import java.util.HashMap;

public class TaksasiCekCeklistMotorActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnNext;
    UserSessionManager session;
    String _name, _fullname, _appid, _ccode, _token, _group;
    String message;
    String code;
    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;
    CheckBox cb_jok1, cb_jok2;
    CheckBox cb_Stripping1, cb_Stripping2;
    CheckBox cb_Tombol_navigasi1, cb_Tombol_navigasi2;
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
    CheckBox cbStnk1_lain, cbStnk2_lain;
    CheckBox cbBukumnl1_lain, cbBukumnl2_lain;

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

    Boolean var_kaca_spion1, var_kaca_spion2=false;
    Boolean var_speedometer1, var_speedometer2=false;
    Boolean var_grip_stang1, var_grip_stang2=false;
    Boolean var_rumah_kunci1, var_rumah_kunci2=false;
    Boolean var_klakson1, var_klakson2=false;
    Boolean var_behel1, var_behel2=false;
    Boolean var_tutup_knalpot1, var_tutup_knalpot2=false;
    Boolean var_jok1, var_jok2=false;
    Boolean var_stripping1, var_stripping2=false;
    Boolean var_tombol_navigasi1, var_tombol_navigasi2=false;
    Boolean var_stnk_lain1, var_stnk_lain2=false;
    Boolean var_bukumnl1, var_bukumnl2=false;

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

    String[] stnk;
    String var_etStnk;
    String[] bukukir;
    String[] trayek;
    String[] usaha;
    String nopol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taksasi_cek_ceklist_motor);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ceklist Dispatcher & BP");
        toolbar.setTitleTextColor(Color.WHITE);
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
            var_etStnk = b.getString(configuration.KEY_stnk_lain);
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

//            Toast.makeText(TaksasiCekCeklistActivity.this,var_picPenyimpanan,Toast.LENGTH_SHORT).show();

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
            nopol = b.getString("nopol");
        }

//        Toast.makeText(TaksasiCekCeklistActivity.this,var_picPengiriman,Toast.LENGTH_SHORT).show();

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



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaksasiCekCeklistMotorActivity.this,TaksasiCekFotoActivity.class);
                Bundle b = new Bundle();
                b.putString("picpenarikan_diterima",var_picPenarikanDiterima);
                b.putString("picpenarikan",var_picPenarikanDiterima);
                b.putString("picpenyimpanan",var_picPenarikanDiterima);
                b.putString("picpenjualan",var_picPenarikanDiterima);
                b.putString("picpengiriman",var_picPengiriman);
                b.putString("picpenyimpanan",var_picPenyimpanan);
                b.putString("note_picpenarikan",var_note_picPenarikan);
                b.putString("note_picpenyimpanan",var_note_picPenyimpanan);
                b.putString("note_picpengiriman",var_note_picPengiriman);

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
                b.putString("img13", var_img13);
                b.putString("img14", var_img14);
                b.putString("nopol", nopol);
                i.putExtras(b);
                startActivity(i);
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
