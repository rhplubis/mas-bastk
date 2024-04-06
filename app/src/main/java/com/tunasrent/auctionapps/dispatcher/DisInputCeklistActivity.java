package com.tunasrent.auctionapps.dispatcher;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.DB.BodyKendaraan;
import com.tunasrent.auctionapps.DB.Ceklist;
import com.tunasrent.auctionapps.DB.InputData;
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

public class DisInputCeklistActivity extends AppCompatActivity {
    UserSessionManager session;
    String _name, _fullname, _appid, _ccode, _token, _group;

    Realm realm;
    Calendar myCalendar;
    Toolbar toolbar;

    String message,token,username,full_name,email;
    int code;
    private String appid = configuration.APPID;
    private String ccode = configuration.CCODE;

    CheckBox cbGrill1_fisikmuka, cbGrill2_fisikmuka;
    CheckBox cbLampu1_fisikmuka, cbLampu2_fisikmuka;
    CheckBox cbLampusen1_fisikmuka, cbLampusen2_fisikmuka;
    CheckBox cbBamper1_fisikmuka, cbBamper2_fisikmuka;
    CheckBox cbEmblem1_fisikmuka, cbEmblem2_fisikmuka;
    CheckBox cbTanduk1_fisikmuka, cbTanduk2_fisikmuka;

    CheckBox cbFootstep1_fisikkiri, cbFootstep2_fisikkiri;
    CheckBox cbPintudpn1_fisikkiri, cbPintudpn2_fisikkiri;
    CheckBox cbPintublk1_fisikkiri, cbPintublk2_fisikkiri;
    CheckBox cbBamper1_fisikkiri,  cbBamper2_fisikkiri;
    CheckBox cbFenderblk1_fisikkiri, cbFenderblk2_fisikkiri;
    CheckBox cbSpion1_fisikkiri, cbSpion2_fisikkiri;
    CheckBox cbEmblem1_fisikkiri, cbEmblem2_fisikkiri;
    CheckBox cbBan1_fisikkiri;
    RadioButton rbBanstandard1_fisikkiri, rbBanradial1_fisikkiri;
    //CheckBox cbBan1_fisikkiri;
    Spinner spBan2_fisikkiri;
    private String[] kondisi = {"Botak", "Sedang", "Baik"};

    RadioButton rbVelgstandard_fisikkiri, rbVelgracing_fisikkiri;
    CheckBox cbVelg1_fisikkiri, cbVelg2_fisikkiri;
    CheckBox cbDop1_fisikkiri, cbDop2_fisikkiri;
    CheckBox cbDopBlk1_fisikkiri, cbDopBlk2_fisikkiri;

    CheckBox cbSpoiler1_fisikblkg, cbSpoiler2_fisikblkg;
    CheckBox cbLampu1_fisikblkg, cbLampu2_fisikblkg;
    CheckBox cbLampusen1_fisikblkg, cbLampusen2_fisikblkg;
    CheckBox cbBamper1_fisikblkg, cbBamper2_fisikblkg;
    CheckBox cbEmblem1_fisikblkg, cbEmblem2_fisikblkg;
    CheckBox cbKnalpot1_fisikblkg, cbKnalpot2_fisikblkg;

    CheckBox cbFoot1_fisikkanan, cbFoot2_fisikkanan;
    CheckBox cbPintudpn1_fisikkanan, cbPintudpn2_fisikkanan;
    CheckBox cbPintublk1_fisikkanan, cbPintublk2_fisikkanan;
    CheckBox cbBamper1_fisikkanan, cbBamper2_fisikkanan;
    CheckBox cbFenderblk1_fisikkanan, cbFenderblk2_fisikkanan;
    CheckBox cbSpion1_fisikkanan, cbSpion2_fisikkanan;
    CheckBox cbEmblem1_fisikkanan, cbEmblem2_fisikkanan;
    RadioButton rbBanstandard_fisikkanan, rbBanradial_fisikkanan;
    CheckBox cbBan1_fisikkanan;
    Spinner spBan2_fisikkanan;

    RadioButton rbVelgstandard_fisikkanan, rbVelgracing_fisikkanan;
    CheckBox cbVelg1_fisikkanan, cbVelg2_fisikkanan;
    CheckBox cbDop1_fisikkanan, cbDop2_fisikkanan;
    CheckBox cbDopBlk1_fisikkanan, cbDopBlk2_fisikkanan;

    CheckBox cbKunciktk1_perlengkapan, cbKunciktk2_perlengkapan;
    CheckBox cbSpion1_perlengkapan, cbSpion2_perlengkapan;
    CheckBox cbJok1_perlengkapan, cbJok2_perlengkapan;
    CheckBox cbSarung1_perlengkapan, cbSarung2_perlengkapan;
    CheckBox cbSandaran1_perlengkapan, cbSandaran2_perlengkapan;
    CheckBox cbKarpet1_perlengkapan, cbKarpet2_perlengkapan;
    CheckBox cbPelindung1_perlengkapan, cbPelindung2_perlengkapan;
    CheckBox cbSegitiga1_perlengkapan, cbSegitiga2_perlengkapan;
    CheckBox cbTool1_perlengkapan, cbTool2_perlengkapan;
    CheckBox cbCadangan1_perlengkapan, cbCadangan2_perlengkapan;
    CheckBox cbKunciban1_perlengkapan, cbKunciban2_perlengkapan;
    CheckBox cbDongkrak1_perlengkapan, cbDongkrak2_perlengkapan;
    CheckBox cbAntena1_perlengkapan, cbAntena2_perlengkapan;
    CheckBox cbAirbag1_perlengkapan, cbAirbag2_perlengkapan;

    CheckBox cbLampukbt1_listrik, cbLampukbt2_listrik;
    CheckBox cbWiperkacadpn1_listrik, cbWiperkacadpn2_listrik;
    CheckBox cbWiperkacablk1_listrik, cbWiperkacablk2_listrik;
    CheckBox cbKlakson1_listrik, cbKlakson2_listrik;
    CheckBox cbAlarm1_listrik, cbAlarm2_listrik;
    CheckBox cbJam1_listrik, cbJam2_listrik;
    CheckBox cbLighter1_listrik, cbLighter2_listrik;
    RadioButton rbRadio_listrik, rbTape_listrik, rbCd_listrik;
    EditText etMerk_listrik;
    CheckBox cbRadio1_listrik, cbRadio2_listrik;
    CheckBox cbPowersup1_listrik, cbPowersup2_listrik;
    CheckBox cbSpeaker1_listrik, cbSpeaker2_listrik;
    CheckBox cbAc1_listrik, cbAc2_listrik;
    CheckBox cbPowerwin1_listrik, cbPowerwin2_listrik;
    CheckBox cbCentral1_listrik, cbCentral2_listrik;
    CheckBox cbRemote1_listrik, cbRemote2_listrik;
    CheckBox cbSpeedo1_listrik, cbSpeedo2_listrik;
    CheckBox cbOdometer1_listrik, cbOdometer2_listrik;
    CheckBox cbTacho1_listrik,  cbTacho2_listrik;
    CheckBox cbAccu1_listrik, cbAccu2_listrik;

    CheckBox cbMesin1_lain, cbMesin2_lain;
    CheckBox cbHidraulik1_lain, cbHidraulik2_lain;
    CheckBox cbGardan1_lain, cbGardan2_lain;
    CheckBox cbAs1_lain, cbAs2_lain;
    CheckBox cbBak1_lain, cbBak2_lain;
    TextInputEditText etSd_stnk;
    CheckBox cbStnk1_lain, cbStnk2_lain;
    TextInputEditText etSd_bukukir;
    CheckBox cbBukukir1_lain, cbBukukir2_lain;
    TextInputEditText etSd_ijintrayek;
    CheckBox cbTrayek1_lain, cbTrayek2_lain;
    TextInputEditText etSd_ijinusaha;
    CheckBox cbUsaha1_lain, cbUsaha2_lain;
    CheckBox cbBukumnl1_lain, cbBukumnl2_lain;

    Button btnNextdispatcher;

    Boolean var_cbGrill1_fisikmuka=false;
    Boolean var_cbGrill2_fisikmuka = false;
    Boolean var_cbLampu1_fisikmuka=false;
    Boolean var_cbLampu2_fisikmuka = false;
    Boolean var_cbLampusen1_fisikmuka=false;
    Boolean var_cbLampusen2_fisikmuka = false;
    Boolean var_cbBamper1_fisikmuka=false;
    Boolean var_cbBamper2_fisikmuka = false;
    Boolean var_cbEmblem1_fisikmuka=false;
    Boolean var_cbEmblem2_fisikmuka = false;
    Boolean var_cbTanduk1_fisikmuka=false;
    Boolean var_cbTanduk2_fisikmuka = false;

    Boolean var_cbFootstep1_fisikkiri=false;
    Boolean var_cbFootstep2_fisikkiri=false;
    Boolean var_cbPintudpn1_fisikkiri=false;
    Boolean var_cbPintudpn2_fisikkiri=false;
    Boolean var_cbPintublk1_fisikkiri=false;
    Boolean var_cbPintublk2_fisikkiri=false;
    Boolean var_cbBamper1_fisikkiri=false;
    Boolean var_cbBamper2_fisikkiri=false;
    Boolean var_cbFenderblk1_fisikkiri=false;
    Boolean var_cbFenderblk2_fisikkiri=false;
    Boolean var_cbSpion1_fisikkiri=false;
    Boolean var_cbSpion2_fisikkiri=false;
    Boolean var_cbEmblem1_fisikkiri=false;
    Boolean var_cbEmblem2_fisikkiri=false;
    Boolean var_rbBan_fisikkiri=false;
    Boolean var_cbBan1_fisikkiri=false;
    int var_spBan2_fisikkiri;
    Boolean var_vspBan2_fisikkiri =false;
    Boolean var_rbVelgstandard_fisikkiri=false;
    Boolean var_rbVelgracing_fisikkiri=false;
    Boolean var_cbVelg1_fisikkiri=false;
    Boolean var_cbVelg2_fisikkiri=false;
    Boolean var_cbDop1_fisikkiri=false;
    Boolean var_cbDop2_fisikkiri=false;
    Boolean var_cbDopBlk1_fisikkiri=false;
    Boolean var_cbDopBlk2_fisikkiri=false;

    Boolean var_cbSpoiler1_fisikblkg=false;
    Boolean var_cbSpoiler2_fisikblkg=false;
    Boolean var_cbLampu1_fisikblkg=false;
    Boolean var_cbLampu2_fisikblkg=false;
    Boolean var_cbLampusen1_fisikblkg=false;
    Boolean var_cbLampusen2_fisikblkg=false;
    Boolean var_cbBamper1_fisikblkg=false;
    Boolean var_cbBamper2_fisikblkg=false;
    Boolean var_cbEmblem1_fisikblkg=false;
    Boolean var_cbEmblem2_fisikblkg=false;
    Boolean var_cbKnalpot1_fisikblkg=false;
    Boolean var_cbKnalpot2_fisikblkg=false;

    Boolean var_cbFoot1_fisikkanan=false;
    Boolean var_cbFoot2_fisikkanan=false;
    Boolean var_cbPintudpn1_fisikkanan=false;
    Boolean var_cbPintudpn2_fisikkanan=false;
    Boolean var_cbPintublk1_fisikkanan=false;
    Boolean var_cbPintublk2_fisikkanan=false;
    Boolean var_cbBamper1_fisikkanan=false;
    Boolean var_cbBamper2_fisikkanan=false;
    Boolean var_cbFenderblk1_fisikkanan=false;
    Boolean var_cbFenderblk2_fisikkanan=false;
    Boolean var_cbSpion1_fisikkanan=false;
    Boolean var_cbSpion2_fisikkanan=false;
    Boolean var_cbEmblem1_fisikkanan=false;
    Boolean var_cbEmblem2_fisikkanan=false;
    Boolean var_rbBan_fisikkanan=false;
    Boolean var_cbBan1_fisikkanan=false;
    Boolean var_cbVelg1_fisikkanan=false;
    Boolean var_cbVelg2_fisikkanan=false;
    Boolean var_cbDop1_fisikkanan=false;
    Boolean var_cbDop2_fisikkanan=false;
    Boolean var_cbDopBlk1_fisikkanan=false;
    Boolean var_cbDopBlk2_fisikkanan=false;
    Boolean var_cbKunciktk1_perlengkapan=false;
    Boolean var_cbKunciktk2_perlengkapan=false;
    Boolean var_cbSpion1_perlengkapan=false;
    Boolean var_cbSpion2_perlengkapan=false;
    Boolean var_cbJok1_perlengkapan=false;
    Boolean var_cbJok2_perlengkapan=false;
    Boolean var_cbSarung1_perlengkapan=false;
    Boolean var_cbSarung2_perlengkapan=false;
    Boolean var_cbSandaran1_perlengkapan=false;
    Boolean var_cbSandaran2_perlengkapan=false;
    Boolean var_cbKarpet1_perlengkapan=false;
    Boolean var_cbKarpet2_perlengkapan=false;
    Boolean var_cbPelindung1_perlengkapan=false;
    Boolean var_cbPelindung2_perlengkapan=false;
    Boolean var_cbSegitiga1_perlengkapan=false;
    Boolean var_cbSegitiga2_perlengkapan=false;
    Boolean var_cbTool1_perlengkapan=false;
    Boolean var_cbTool2_perlengkapan=false;
    Boolean var_cbCadangan1_perlengkapan=false;
    Boolean var_cbCadangan2_perlengkapan=false;
    Boolean var_cbKunciban1_perlengkapan=false;
    Boolean var_cbKunciban2_perlengkapan=false;
    Boolean var_cbDongkrak1_perlengkapan=false;
    Boolean var_cbDongkrak2_perlengkapan=false;
    Boolean var_cbAntena1_perlengkapan=false;
    Boolean var_cbAntena2_perlengkapan=false;
    Boolean var_cbAirbag1_perlengkapan=false;
    Boolean var_cbAirbag2_perlengkapan=false;

    Boolean var_cbLampukbt1_listrik=false;
    Boolean var_cbLampukbt2_listrik=false;
    Boolean var_cbWiperkacadpn1_listrik=false;
    Boolean var_cbWiperkacadpn2_listrik=false;
    Boolean var_cbWiperkacablk1_listrik=false;
    Boolean var_cbWiperkacablk2_listrik=false;
    Boolean var_cbKlakson1_listrik=false;
    Boolean var_cbKlakson2_listrik=false;
    Boolean var_cbAlarm1_listrik=false;
    Boolean var_cbAlarm2_listrik=false;
    Boolean var_cbJam1_listrik=false;
    Boolean var_cbJam2_listrik=false;
    Boolean var_cbLighter1_listrik=false;
    Boolean var_cbLighter2_listrik=false;
    Boolean var_rbRadio_listrik=false;
    Boolean var_cbRadio1_listrik=false;
    Boolean var_cbRadio2_listrik=false;
    Boolean var_cbPowersup1_listrik=false;
    Boolean var_cbPowersup2_listrik=false;
    Boolean var_cbSpeaker1_listrik=false;
    Boolean var_cbSpeaker2_listrik=false;
    Boolean var_cbAc1_listrik=false;
    Boolean var_cbAc2_listrik=false;
    Boolean var_cbPowerwin1_listrik=false;
    Boolean var_cbPowerwin2_listrik=false;
    Boolean var_cbCentral1_listrik=false;
    Boolean var_cbCentral2_listrik=false;
    Boolean var_cbRemote1_listrik=false;
    Boolean var_cbRemote2_listrik=false;
    Boolean var_cbSpeedo1_listrik=false;
    Boolean var_cbSpeedo2_listrik=false;
    Boolean var_cbOdometer1_listrik=false;
    Boolean var_cbOdometer2_listrik=false;
    Boolean var_cbTacho1_listrik=false;
    Boolean var_cbTacho2_listrik=false;
    Boolean var_cbAccu1_listrik=false;
    Boolean var_cbAccu2_listrik=false;

    Boolean var_cbMesin1_lain=false;
    Boolean var_cbMesin2_lain=false;
    Boolean var_cbHidraulik1_lain=false;
    Boolean var_cbHidraulik2_lain=false;
    Boolean var_cbGardan1_lain=false;
    Boolean var_cbGardan2_lain=false;
    Boolean var_cbAs1_lain=false;
    Boolean var_cbAs2_lain=false;
    Boolean var_cbBak1_lain=false;
    Boolean var_cbBak2_lain=false;
    Boolean var_cbStnk1_lain=false;
    Boolean var_cbStnk2_lain=false;
    String var_etStnk;
    Boolean var_cbBukukir1_lain=false;
    Boolean var_cbBukukir2_lain=false;
    String var_etBukukir;
    Boolean var_cbTrayek1_lain=false;
    Boolean var_cbTrayek2_lain=false;
    String var_etTrayek;
    Boolean var_cbUsaha1_lain=false;
    Boolean var_cbUsaha2_lain=false;
    String var_etUsaha;
    Boolean var_cbBukumnl1_lain=false;
    Boolean var_cbBukumnl2_lain=false;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_input_ceklist);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ceklist Unit");
        toolbar.setTitleTextColor(Color.WHITE);

        cbGrill1_fisikmuka = findViewById(R.id.cb_grill1_fisikmuka);
        cbGrill2_fisikmuka = findViewById(R.id.cb_grill2_fisikmuka);
        cbLampu1_fisikmuka = findViewById(R.id.cb_lampu1_fisikmuka);
        cbLampu2_fisikmuka = findViewById(R.id.cb_lampu2_fisikmuka);
        cbLampusen1_fisikmuka = findViewById(R.id.cb_lampusen1_fisikmuka);
        cbLampusen2_fisikmuka = findViewById(R.id.cb_lampusen2_fisikmuka);
        cbBamper1_fisikmuka = findViewById(R.id.cb_bamper1_fisikmuka);
        cbBamper2_fisikmuka = findViewById(R.id.cb_bamper2_fisikmuka);
        cbEmblem1_fisikmuka = findViewById(R.id.cb_emblem1_fisikmuka);
        cbEmblem2_fisikmuka = findViewById(R.id.cb_emblem2_fisikmuka);
        cbTanduk1_fisikmuka = findViewById(R.id.cb_tanduk1_fisikmuka);
        cbTanduk2_fisikmuka = findViewById(R.id.cb_tanduk2_fisikmuka);

        cbFootstep1_fisikkiri = findViewById(R.id.cb_footstep1_fisikkiri);
        cbFootstep2_fisikkiri = findViewById(R.id.cb_footstep2_fisikkiri);
        cbPintudpn1_fisikkiri = findViewById(R.id.cb_pintudpn1_fisikkiri);
        cbPintudpn2_fisikkiri = findViewById(R.id.cb_pintudpn2_fisikkiri);
        cbPintublk1_fisikkiri = findViewById(R.id.cb_pintublk1_fisikkiri);
        cbPintublk2_fisikkiri = findViewById(R.id.cb_pintublk2_fisikkiri);
        cbBamper1_fisikkiri = findViewById(R.id.cb_bamper1_fisikkiri);
        cbBamper2_fisikkiri = findViewById(R.id.cb_bamper2_fisikkiri);
        cbFenderblk1_fisikkiri = findViewById(R.id.cb_fenderblk1_fisikkiri);
        cbFenderblk2_fisikkiri = findViewById(R.id.cb_fenderblk2_fisikkiri);
        cbSpion1_fisikkiri = findViewById(R.id.cb_spion1_fisikkiri);
        cbSpion2_fisikkiri = findViewById(R.id.cb_spion2_fisikkiri);
        cbEmblem1_fisikkiri = findViewById(R.id.cb_emblem1_fisikkiri);
        cbEmblem2_fisikkiri = findViewById(R.id.cb_emblem2_fisikkiri);
        cbBan1_fisikkiri = findViewById(R.id.cb_ban1_fisikkiri);
        rbBanstandard1_fisikkiri = findViewById(R.id.rb_banstandard1_fisikkiri);
        rbBanradial1_fisikkiri = findViewById(R.id.rb_banradial1_fisikkiri);
        //cbBan1_fisikkiri = findViewById(R.id.cb_ban1_fisikkiri);
        spBan2_fisikkiri = findViewById(R.id.sp_ban2_fisikkiri);
        rbVelgstandard_fisikkiri = findViewById(R.id.rb_velgstandard_fisikkiri);
        rbVelgracing_fisikkiri = findViewById(R.id.rb_velgracing_fisikkiri);
        cbVelg1_fisikkiri = findViewById(R.id.cb_velg1_fisikkiri);
        cbVelg2_fisikkiri = findViewById(R.id.cb_velg2_fisikkiri);
        cbDop1_fisikkiri = findViewById(R.id.cb_dop1_fisikkiri);
        cbDop2_fisikkiri = findViewById(R.id.cb_dop2_fisikkiri);
        cbDopBlk1_fisikkiri = findViewById(R.id.cb_dopblk1_fisikkiri);
        cbDopBlk2_fisikkiri = findViewById(R.id.cb_dopblk2_fisikkiri);

        cbSpoiler1_fisikblkg = findViewById(R.id.cb_spoiler1_fisikblkg);
        cbSpoiler2_fisikblkg = findViewById(R.id.cb_spoiler2_fisikblkg);
        cbLampu1_fisikblkg = findViewById(R.id.cb_lampu1_fisikblkg);
        cbLampu2_fisikblkg = findViewById(R.id.cb_lampu2_fisikblkg);
        cbLampusen1_fisikblkg = findViewById(R.id.cb_lampusen1_fisikblkg);
        cbLampusen2_fisikblkg = findViewById(R.id.cb_lampusen2_fisikblkg);
        cbBamper1_fisikblkg = findViewById(R.id.cb_bamper1_fisikblkg);
        cbBamper2_fisikblkg = findViewById(R.id.cb_bamper2_fisikblkg);
        cbEmblem1_fisikblkg = findViewById(R.id.cb_emblem1_fisikblkg);
        cbEmblem2_fisikblkg = findViewById(R.id.cb_emblem2_fisikblkg);
        cbKnalpot1_fisikblkg = findViewById(R.id.cb_knalpot1_fisikblkg);
        cbKnalpot2_fisikblkg = findViewById(R.id.cb_knalpot2_fisikblkg);

        cbFoot1_fisikkanan = findViewById(R.id.cb_foot1_fisikkanan);
        cbFoot2_fisikkanan = findViewById(R.id.cb_foot2_fisikkanan);
        cbPintudpn1_fisikkanan = findViewById(R.id.cb_pintudpn1_fisikkanan);
        cbPintudpn2_fisikkanan = findViewById(R.id.cb_pintudpn2_fisikkanan);
        cbPintublk1_fisikkanan = findViewById(R.id.cb_pintublk1_fisikkanan);
        cbPintublk2_fisikkanan = findViewById(R.id.cb_pintublk2_fisikkanan);
        cbBamper1_fisikkanan = findViewById(R.id.cb_bamper1_fisikkanan);
        cbBamper2_fisikkanan = findViewById(R.id.cb_bamper2_fisikkanan);
        cbFenderblk1_fisikkanan = findViewById(R.id.cb_fenderblk1_fisikkanan);
        cbFenderblk2_fisikkanan = findViewById(R.id.cb_fenderblk2_fisikkanan);
        cbSpion1_fisikkanan = findViewById(R.id.cb_spion1_fisikkanan);
        cbSpion2_fisikkanan = findViewById(R.id.cb_spion2_fisikkanan);
        cbEmblem1_fisikkanan = findViewById(R.id.cb_emblem1_fisikkanan);
        cbEmblem2_fisikkanan = findViewById(R.id.cb_emblem2_fisikkanan);
        rbBanstandard_fisikkanan = findViewById(R.id.rb_banstandard_fisikkanan);
        rbBanradial_fisikkanan = findViewById(R.id.rb_banradial_fisikkanan);
        cbBan1_fisikkanan = findViewById(R.id.cb_ban1_fisikkanan);
        spBan2_fisikkanan = findViewById(R.id.sp_ban2_fisikkanan);
        rbVelgstandard_fisikkanan = findViewById(R.id.rb_velgstandard_fisikkanan);
        rbVelgracing_fisikkanan = findViewById(R.id.rb_velgracing_fisikkanan);
        cbVelg1_fisikkanan = findViewById(R.id.cb_velg1_fisikkanan);
        cbVelg2_fisikkanan = findViewById(R.id.cb_velg2_fisikkanan);
        cbDop1_fisikkanan = findViewById(R.id.cb_dop1_fisikkanan);
        cbDop2_fisikkanan = findViewById(R.id.cb_dop2_fisikkanan);
        cbDopBlk1_fisikkanan = findViewById(R.id.cb_dopblk1_fisikkanan);
        cbDopBlk2_fisikkanan = findViewById(R.id.cb_dopblk2_fisikkanan);

        cbKunciktk1_perlengkapan = findViewById(R.id.cb_kunciktk1_perlengkapan);
        cbKunciktk2_perlengkapan = findViewById(R.id.cb_kunciktk2_perlengkapan);
        cbSpion1_perlengkapan = findViewById(R.id.cb_spion1_perlengkapan);
        cbSpion2_perlengkapan = findViewById(R.id.cb_spion2_perlengkapan);
        cbJok1_perlengkapan = findViewById(R.id.cb_jok1_perlengkapan);
        cbJok2_perlengkapan = findViewById(R.id.cb_jok2_perlengkapan);
        cbSarung1_perlengkapan = findViewById(R.id.cb_sarung1_perlengkapan);
        cbSarung2_perlengkapan = findViewById(R.id.cb_sarung2_perlengkapan);
        cbSandaran1_perlengkapan = findViewById(R.id.cb_sandaran1_perlengkapan);
        cbSandaran2_perlengkapan = findViewById(R.id.cb_sandaran2_perlengkapan);
        cbKarpet1_perlengkapan = findViewById(R.id.cb_karpet1_perlengkapan);
        cbKarpet2_perlengkapan = findViewById(R.id.cb_karpet2_perlengkapan);
        cbPelindung1_perlengkapan = findViewById(R.id.cb_pelindung1_perlengkapan);
        cbPelindung2_perlengkapan = findViewById(R.id.cb_pelindung2_perlengkapan);
        cbSegitiga1_perlengkapan = findViewById(R.id.cb_segitiga1_perlengkapan);
        cbSegitiga2_perlengkapan = findViewById(R.id.cb_segitiga2_perlengkapan);
        cbTool1_perlengkapan = findViewById(R.id.cb_tool1_perlengkapan);
        cbTool2_perlengkapan = findViewById(R.id.cb_tool2_perlengkapan);
        cbCadangan1_perlengkapan = findViewById(R.id.cb_cadangan1_perlengkapan);
        cbCadangan2_perlengkapan = findViewById(R.id.cb_cadangan2_perlengkapan);
        cbKunciban1_perlengkapan = findViewById(R.id.cb_kunciban1_perlengkapan);
        cbKunciban2_perlengkapan = findViewById(R.id.cb_kunciban2_perlengkapan);
        cbDongkrak1_perlengkapan = findViewById(R.id.cb_dongkrak1_perlengkapan);
        cbDongkrak2_perlengkapan = findViewById(R.id.cb_dongkrak2_perlengkapan);
        cbAntena1_perlengkapan = findViewById(R.id.cb_antena1_perlengkapan);
        cbAntena2_perlengkapan = findViewById(R.id.cb_antena2_perlengkapan);
        cbAirbag1_perlengkapan = findViewById(R.id.cb_airbag1_perlengkapan);
        cbAirbag2_perlengkapan = findViewById(R.id.cb_airbag2_perlengkapan);

        cbLampukbt1_listrik = findViewById(R.id.cb_lampukbt1_listrik);
        cbLampukbt2_listrik = findViewById(R.id.cb_lampukbt2_listrik);
        cbWiperkacadpn1_listrik = findViewById(R.id.cb_wiperkacadpn1_listrik);
        cbWiperkacadpn2_listrik = findViewById(R.id.cb_wiperkacadpn2_listrik);
        cbWiperkacablk1_listrik = findViewById(R.id.cb_wiperkacablk1_listrik);
        cbWiperkacablk2_listrik = findViewById(R.id.cb_wiperkacablk2_listrik);
        cbKlakson1_listrik = findViewById(R.id.cb_klakson1_listrik);
        cbKlakson2_listrik = findViewById(R.id.cb_klakson2_listrik);
        cbAlarm1_listrik = findViewById(R.id.cb_alarm1_listrik);
        cbAlarm2_listrik = findViewById(R.id.cb_alarm2_listrik);
        cbJam1_listrik = findViewById(R.id.cb_jam1_listrik);
        cbJam2_listrik = findViewById(R.id.cb_jam2_listrik);
        cbLighter1_listrik = findViewById(R.id.cb_lighter1_listrik);
        cbLighter2_listrik = findViewById(R.id.cb_lighter2_listrik);
        rbRadio_listrik = findViewById(R.id.rb_radio_listrik);
        rbTape_listrik = findViewById(R.id.rb_tape_listrik);
        rbCd_listrik = findViewById(R.id.rb_cd_listrik);
        etMerk_listrik = findViewById(R.id.et_merk_listrik);
        cbRadio1_listrik = findViewById(R.id.cb_radio1_listrik);
        cbRadio2_listrik = findViewById(R.id.cb_radio2_listrik);
        cbPowersup1_listrik = findViewById(R.id.cb_powersup1_listrik);
        cbPowersup2_listrik = findViewById(R.id.cb_powersup2_listrik);
        cbSpeaker1_listrik = findViewById(R.id.cb_speaker1_listrik);
        cbSpeaker2_listrik = findViewById(R.id.cb_speaker2_listrik);
        cbAc1_listrik = findViewById(R.id.cb_ac1_listrik);
        cbAc2_listrik = findViewById(R.id.cb_ac2_listrik);
        cbPowerwin1_listrik = findViewById(R.id.cb_powerwin1_listrik);
        cbPowerwin2_listrik = findViewById(R.id.cb_powerwin2_listrik);
        cbCentral1_listrik = findViewById(R.id.cb_central1_listrik);
        cbCentral2_listrik = findViewById(R.id.cb_central2_listrik);
        cbRemote1_listrik = findViewById(R.id.cb_remote1_listrik);
        cbRemote2_listrik = findViewById(R.id.cb_remote2_listrik);
        cbSpeedo1_listrik = findViewById(R.id.cb_speedo1_listrik);
        cbSpeedo2_listrik = findViewById(R.id.cb_speedo2_listrik);
        cbOdometer1_listrik = findViewById(R.id.cb_odometer1_listrik);
        cbOdometer2_listrik = findViewById(R.id.cb_odometer2_listrik);
        cbTacho1_listrik = findViewById(R.id.cb_tacho1_listrik);
        cbTacho2_listrik = findViewById(R.id.cb_tacho2_listrik);
        cbAccu1_listrik = findViewById(R.id.cb_accu1_listrik);
        cbAccu2_listrik = findViewById(R.id.cb_accu2_listrik);

        cbMesin1_lain = findViewById(R.id.cb_mesin1_lain);
        cbMesin2_lain = findViewById(R.id.cb_mesin2_lain);
        cbHidraulik1_lain = findViewById(R.id.cb_hidraulik1_lain);
        cbHidraulik2_lain = findViewById(R.id.cb_hidraulik2_lain);
        cbGardan1_lain = findViewById(R.id.cb_gardan1_lain);
        cbGardan2_lain = findViewById(R.id.cb_gardan2_lain);
        cbAs1_lain = findViewById(R.id.cb_as1_lain);
        cbAs2_lain = findViewById(R.id.cb_as2_lain);
        cbBak1_lain = findViewById(R.id.cb_bak1_lain);
        cbBak2_lain = findViewById(R.id.cb_bak2_lain);
        etSd_stnk = findViewById(R.id.et_sd_stnk);
        cbStnk1_lain = findViewById(R.id.cb_stnk1_lain);
        cbStnk2_lain = findViewById(R.id.cb_stnk2_lain);
        etSd_bukukir = findViewById(R.id.et_sd_bukukir);

        cbBukukir1_lain = findViewById(R.id.cb_bukukir1_lain);
        cbBukukir2_lain = findViewById(R.id.cb_bukukir2_lain);
        etSd_ijintrayek = findViewById(R.id.et_sd_ijintrayek);
        cbTrayek1_lain = findViewById(R.id.cb_trayek1_lain);
        cbTrayek2_lain = findViewById(R.id.cb_trayek2_lain);
        etSd_ijinusaha = findViewById(R.id.et_sd_ijinusaha);
        cbUsaha1_lain = findViewById(R.id.cb_usaha1_lain);
        cbUsaha2_lain = findViewById(R.id.cb_usaha2_lain);
        cbBukumnl1_lain = findViewById(R.id.cb_bukumnl1_lain);
        cbBukumnl2_lain = findViewById(R.id.cb_bukumnl2_lain);

        btnNextdispatcher = findViewById(R.id.btn_nextdispatcher);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);

        Realm.init(this);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, kondisi);
        spBan2_fisikkiri.setAdapter(adapter);

        spBan2_fisikkiri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int var_sp = 0;
                if (spBan2_fisikkiri.getSelectedItem().equals("Botak")){
                    var_sp = 0;
                }else if (spBan2_fisikkiri.getSelectedItem().equals("Sedang")){
                    var_sp = 1;
                }else if (spBan2_fisikkiri.getSelectedItem().equals("Baik")){
                    var_sp = 2;
                }

                final Realm realm = Realm.getDefaultInstance(); //creating  database oject
                realm.beginTransaction();
                RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                upd.load();
                upd.first().setDb_var_spBan2_fisikkiri(var_sp);
                realm.commitTransaction();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spBan2_fisikkanan.setAdapter(adapter);
        spBan2_fisikkanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int var_sp = 0;
                if (spBan2_fisikkanan.getSelectedItem().equals("Botak")){
                    var_sp = 0;
                }else if (spBan2_fisikkanan.getSelectedItem().equals("Sedang")){
                    var_sp = 1;
                }else if (spBan2_fisikkanan.getSelectedItem().equals("Baik")){
                    var_sp = 2;
                }

                final Realm realm = Realm.getDefaultInstance(); //creating  database oject
                realm.beginTransaction();
                RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                upd.load();
                upd.first().setDb_var_spBan2_fisikkanan(var_sp);
                realm.commitTransaction();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final Realm realm = Realm.getDefaultInstance(); //creating  database oject
        RealmResults<Ceklist> ceklists = realm.where(Ceklist.class).findAllAsync();
        //fetching the data
        ceklists.load();
        if (ceklists.isEmpty()){
            //Toast.makeText(DisInputCeklistActivity.this,"if",Toast.LENGTH_SHORT).show();
            realm.beginTransaction();
            //insert data ke database
            Ceklist ckl = realm.createObject(Ceklist.class);
            ckl.setDb_var_cbGrill1_fisikmuka(false);
            ckl.setDb_var_cbGrill2_fisikmuka(false);
            ckl.setDb_var_cbLampu1_fisikmuka(false);
            ckl.setDb_var_cbLampu2_fisikmuka(false);
            ckl.setDb_var_cbLampusen1_fisikmuka(false);
            ckl.setDb_var_cbLampusen2_fisikmuka(false);
            ckl.setDb_var_cbBamper1_fisikmuka(false);
            ckl.setDb_var_cbBamper2_fisikmuka(false);
            ckl.setDb_var_cbEmblem1_fisikmuka(false);
            ckl.setDb_var_cbEmblem2_fisikmuka(false);
            ckl.setDb_var_cbTanduk1_fisikmuka(false);
            ckl.setDb_var_cbTanduk2_fisikmuka(false);

            ckl.setDb_var_cbFootstep1_fisikkiri(false);
            ckl.setDb_var_cbFootstep2_fisikkiri(false);
            ckl.setDb_var_cbPintudpn1_fisikkiri(false);
            ckl.setDb_var_cbPintudpn2_fisikkiri(false);
            ckl.setDb_var_cbPintublk1_fisikkiri(false);
            ckl.setDb_var_cbPintublk2_fisikkiri(false);
            ckl.setDb_var_cbBamper1_fisikkiri(false);
            ckl.setDb_var_cbBamper2_fisikkiri(false);
            ckl.setDb_var_cbFenderblk1_fisikkiri(false);
            ckl.setDb_var_cbFenderblk2_fisikkiri(false);
            ckl.setDb_var_cbSpion1_fisikkiri(false);
            ckl.setDb_var_cbSpion2_fisikkiri(false);
            ckl.setDb_var_cbEmblem1_fisikkiri(false);
            ckl.setDb_var_cbEmblem2_fisikkiri(false);
            ckl.setDb_var_cbBan1_fisikkiri(false);
            ckl.setDb_var_rbBanstandard_fisikkiri(false);
            ckl.setDb_var_rbBanradial_fisikkiri(false);
            ckl.setDb_var_spBan2_fisikkiri(0);
//            ckl.setTdb_var_spBan2_fisikkiri(false);
            ckl.setDb_var_rbVelgstandard_fisikkiri(false);
            ckl.setDb_var_rbVelgracing_fisikkiri(false);
            ckl.setDb_var_cbVelg1_fisikkiri(false);
            ckl.setDb_var_cbVelg2_fisikkiri(false);
            ckl.setDb_var_cbDop1_fisikkiri(false);
            ckl.setDb_var_cbDop2_fisikkiri(false);
            ckl.setDb_var_cbDopBlk1_fisikkiri(false);
            ckl.setDb_var_cbDopBlk2_fisikkiri(false);

            ckl.setDb_var_cbSpoiler1_fisikblkg(false);
            ckl.setDb_var_cbSpoiler2_fisikblkg(false);
            ckl.setDb_var_cbLampu1_fisikblkg(false);
            ckl.setDb_var_cbLampu2_fisikblkg(false);
            ckl.setDb_var_cbLampusen1_fisikblkg(false);
            ckl.setDb_var_cbLampusen2_fisikblkg(false);
            ckl.setDb_var_cbBamper1_fisikblkg(false);
            ckl.setDb_var_cbBamper2_fisikblkg(false);
            ckl.setDb_var_cbEmblem1_fisikblkg(false);
            ckl.setDb_var_cbEmblem2_fisikblkg(false);
            ckl.setDb_var_cbKnalpot1_fisikblkg(false);
            ckl.setDb_var_cbKnalpot2_fisikblkg(false);

            ckl.setDb_var_cbFoot1_fisikkanan(false);
            ckl.setDb_var_cbFoot2_fisikkanan(false);
            ckl.setDb_var_cbPintudpn1_fisikkanan(false);
            ckl.setDb_var_cbPintudpn2_fisikkanan(false);
            ckl.setDb_var_cbPintublk1_fisikkanan(false);
            ckl.setDb_var_cbPintublk2_fisikkanan(false);
            ckl.setDb_var_cbBamper1_fisikkanan(false);
            ckl.setDb_var_cbBamper2_fisikkanan(false);
            ckl.setDb_var_cbFenderblk1_fisikkanan(false);
            ckl.setDb_var_cbFenderblk2_fisikkanan(false);
            ckl.setDb_var_cbSpion1_fisikkanan(false);
            ckl.setDb_var_cbSpion2_fisikkanan(false);
            ckl.setDb_var_cbEmblem1_fisikkanan(false);
            ckl.setDb_var_cbEmblem2_fisikkanan(false);
            ckl.setDb_var_rbBanstandard_fisikkanan(false);
            ckl.setDb_var_rbBanradial_fisikkanan(false);
            ckl.setDb_var_cbBan1_fisikkanan(false);
            ckl.setDb_var_spBan2_fisikkanan(0);
            ckl.setDb_var_rbVelgstandard_fisikkanan(false);
            ckl.setDb_var_rbVelgracing_fisikkanan(false);
            ckl.setDb_var_cbVelg1_fisikkanan(false);
            ckl.setDb_var_cbVelg2_fisikkanan(false);
            ckl.setDb_var_cbDop1_fisikkanan(false);
            ckl.setDb_var_cbDop2_fisikkanan(false);
            ckl.setDb_var_cbDopBlk1_fisikkanan(false);
            ckl.setDb_var_cbDopBlk2_fisikkanan(false);

            ckl.setDb_var_cbKunciktk1_perlengkapan(false);
            ckl.setDb_var_cbKunciktk2_perlengkapan(false);
            ckl.setDb_var_cbSpion1_perlengkapan(false);
            ckl.setDb_var_cbSpion2_perlengkapan(false);
            ckl.setDb_var_cbJok1_perlengkapan(false);
            ckl.setDb_var_cbJok2_perlengkapan(false);
            ckl.setDb_var_cbSarung1_perlengkapan(false);
            ckl.setDb_var_cbSarung2_perlengkapan(false);
            ckl.setDb_var_cbSandaran1_perlengkapan(false);
            ckl.setDb_var_cbSandaran2_perlengkapan(false);
            ckl.setDb_var_cbKarpet1_perlengkapan(false);
            ckl.setDb_var_cbKarpet2_perlengkapan(false);
            ckl.setDb_var_cbPelindung1_perlengkapan(false);
            ckl.setDb_var_cbPelindung2_perlengkapan(false);
            ckl.setDb_var_cbSegitiga1_perlengkapan(false);
            ckl.setDb_var_cbSegitiga2_perlengkapan(false);
            ckl.setDb_var_cbTool1_perlengkapan(false);
            ckl.setDb_var_cbTool2_perlengkapan(false);
            ckl.setDb_var_cbCadangan1_perlengkapan(false);
            ckl.setDb_var_cbCadangan2_perlengkapan(false);
            ckl.setDb_var_cbKunciban1_perlengkapan(false);
            ckl.setDb_var_cbKunciban2_perlengkapan(false);
            ckl.setDb_var_cbDongkrak1_perlengkapan(false);
            ckl.setDb_var_cbDongkrak2_perlengkapan(false);
            ckl.setDb_var_cbAntena1_perlengkapan(false);
            ckl.setDb_var_cbAntena2_perlengkapan(false);
            ckl.setDb_var_cbAirbag1_perlengkapan(false);
            ckl.setDb_var_cbAirbag2_perlengkapan(false);

            ckl.setDb_var_cbLampukbt1_listrik(false);
            ckl.setDb_var_cbLampukbt2_listrik(false);
            ckl.setDb_var_cbWiperkacadpn1_listrik(false);
            ckl.setDb_var_cbWiperkacadpn2_listrik(false);
            ckl.setDb_var_cbWiperkacablk1_listrik(false);
            ckl.setDb_var_cbWiperkacablk2_listrik(false);
            ckl.setDb_var_cbKlakson1_listrik(false);
            ckl.setDb_var_cbKlakson2_listrik(false);
            ckl.setDb_var_cbAlarm1_listrik(false);
            ckl.setDb_var_cbAlarm2_listrik(false);
            ckl.setDb_var_cbJam1_listrik(false);
            ckl.setDb_var_cbJam2_listrik(false);
            ckl.setDb_var_cbLighter1_listrik(false);
            ckl.setDb_var_cbLighter2_listrik(false);
            ckl.setDb_var_rbRadio_listrik(false);
            ckl.setDb_var_rbTape_listrik(false);
            ckl.setDb_var_rbCd_listrik(false);
            ckl.setDb_var_cbRadio1_listrik(false);
            ckl.setDb_var_cbRadio2_listrik(false);
            ckl.setDb_var_cbPowersup1_listrik(false);
            ckl.setDb_var_cbPowersup2_listrik(false);
            ckl.setDb_var_cbSpeaker1_listrik(false);
            ckl.setDb_var_cbSpeaker2_listrik(false);
            ckl.setDb_var_cbAc1_listrik(false);
            ckl.setDb_var_cbAc2_listrik(false);
            ckl.setDb_var_cbPowerwin1_listrik(false);
            ckl.setDb_var_cbPowerwin2_listrik(false);
            ckl.setDb_var_cbCentral1_listrik(false);
            ckl.setDb_var_cbCentral2_listrik(false);
            ckl.setDb_var_cbRemote1_listrik(false);
            ckl.setDb_var_cbRemote2_listrik(false);
            ckl.setDb_var_cbSpeedo1_listrik(false);
            ckl.setDb_var_cbSpeedo2_listrik(false);
            ckl.setDb_var_cbOdometer1_listrik(false);
            ckl.setDb_var_cbOdometer2_listrik(false);
            ckl.setDb_var_cbTacho1_listrik(false);
            ckl.setDb_var_cbTacho2_listrik(false);
            ckl.setDb_var_cbAccu1_listrik(false);
            ckl.setDb_var_cbAccu2_listrik(false);

            ckl.setDb_var_cbMesin1_lain(false);
            ckl.setDb_var_cbMesin2_lain(false);
            ckl.setDb_var_cbHidraulik1_lain(false);
            ckl.setDb_var_cbHidraulik2_lain(false);
            ckl.setDb_var_cbGardan1_lain(false);
            ckl.setDb_var_cbGardan2_lain(false);
            ckl.setDb_var_cbAs1_lain(false);
            ckl.setDb_var_cbAs2_lain(false);
            ckl.setDb_var_cbBak1_lain(false);
            ckl.setDb_var_cbBak2_lain(false);
            ckl.setDb_var_etStnk_lain("00-00-0000");
            ckl.setDb_var_etBukukir_lain("00-00-0000");
            ckl.setDb_var_etIjintrayek_lain("00-00-0000");
            ckl.setDb_var_etIjinusaha_lain("00-00-0000");
            ckl.setDb_var_cbStnk1_lain(false);
            ckl.setDb_var_cbStnk2_lain(false);
            ckl.setDb_var_cbBukukir1_lain(false);
            ckl.setDb_var_cbBukukir2_lain(false);
            ckl.setDb_var_cbTrayek1_lain(false);
            ckl.setDb_var_cbTrayek2_lain(false);
            ckl.setDb_var_cbUsaha1_lain(false);
            ckl.setDb_var_cbUsaha2_lain(false);
            ckl.setDb_var_cbBukumnl1_lain(false);
            ckl.setDb_var_cbBukumnl2_lain(false);
            
            //============================================

            ckl.setTdb_var_cbGrill1_fisikmuka("Tidak Ada");
            ckl.setTdb_var_cbGrill2_fisikmuka("Rusak");
            ckl.setTdb_var_cbLampu1_fisikmuka("Tidak Ada");
            ckl.setTdb_var_cbLampu2_fisikmuka("Rusak");
            ckl.setTdb_var_cbLampusen1_fisikmuka("Tidak Ada");
            ckl.setTdb_var_cbLampusen2_fisikmuka("Rusak");
            ckl.setTdb_var_cbBamper1_fisikmuka("Tidak Ada");
            ckl.setTdb_var_cbBamper2_fisikmuka("Rusak");
            ckl.setTdb_var_cbEmblem1_fisikmuka("Tidak Ada");
            ckl.setTdb_var_cbEmblem2_fisikmuka("Rusak");
            ckl.setTdb_var_cbTanduk1_fisikmuka("Tidak Ada");
            ckl.setTdb_var_cbTanduk2_fisikmuka("Rusak");

            ckl.setTdb_var_cbFootstep1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbFootstep2_fisikkiri("Rusak");
            ckl.setTdb_var_cbPintudpn1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbPintudpn2_fisikkiri("Rusak");
            ckl.setTdb_var_cbPintublk1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbPintublk2_fisikkiri("Rusak");
            ckl.setTdb_var_cbBamper1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbBamper2_fisikkiri("Rusak");
            ckl.setTdb_var_cbFenderblk1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbFenderblk2_fisikkiri("Rusak");
            ckl.setTdb_var_cbSpion1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbSpion2_fisikkiri("Rusak");
            ckl.setTdb_var_cbEmblem1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbEmblem2_fisikkiri("Rusak");
            ckl.setTdb_var_cbBan1_fisikkiri("Tidak Ada");
//            ckl.setTdb_var_rbBan_fisikkiri("");
//            ckl.setTdb_var_spBan2_fisikkiri(false);
//            ckl.setTdb_var_rbVelgstandard_fisikkiri(false);
//            ckl.setTdb_var_rbVelgracing_fisikkiri(false);
            ckl.setTdb_var_cbVelg1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbVelg2_fisikkiri("Rusak");
            ckl.setTdb_var_cbDop1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbDop2_fisikkiri("Rusak");
            ckl.setTdb_var_cbDopBlk1_fisikkiri("Tidak Ada");
            ckl.setTdb_var_cbDopBlk2_fisikkiri("Rusak");

            ckl.setTdb_var_cbSpoiler1_fisikblkg("Tidak Ada");
            ckl.setTdb_var_cbSpoiler2_fisikblkg("Rusak");
            ckl.setTdb_var_cbLampu1_fisikblkg("Tidak Ada");
            ckl.setTdb_var_cbLampu2_fisikblkg("Rusak");
            ckl.setTdb_var_cbLampusen1_fisikblkg("Tidak Ada");
            ckl.setTdb_var_cbLampusen2_fisikblkg("Rusak");
            ckl.setTdb_var_cbBamper1_fisikblkg("Tidak Ada");
            ckl.setTdb_var_cbBamper2_fisikblkg("Rusak");
            ckl.setTdb_var_cbEmblem1_fisikblkg("Tidak Ada");
            ckl.setTdb_var_cbEmblem2_fisikblkg("Rusak");
            ckl.setTdb_var_cbKnalpot1_fisikblkg("Tidak Ada");
            ckl.setTdb_var_cbKnalpot2_fisikblkg("Rusak");

            ckl.setTdb_var_cbFoot1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbFoot2_fisikkanan("Rusak");
            ckl.setTdb_var_cbPintudpn1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbPintudpn2_fisikkanan("Rusak");
            ckl.setTdb_var_cbPintublk1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbPintublk2_fisikkanan("Rusak");
            ckl.setTdb_var_cbBamper1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbBamper2_fisikkanan("Rusak");
            ckl.setTdb_var_cbFenderblk1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbFenderblk2_fisikkanan("Rusak");
            ckl.setTdb_var_cbSpion1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbSpion2_fisikkanan("Rusak");
            ckl.setTdb_var_cbEmblem1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbEmblem2_fisikkanan("Rusak");
            ckl.setTdb_var_cbBan1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbVelg1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbVelg2_fisikkanan("Rusak");
            ckl.setTdb_var_cbDop1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbDop2_fisikkanan("Rusak");
            ckl.setTdb_var_cbDopBlk1_fisikkanan("Tidak Ada");
            ckl.setTdb_var_cbDopBlk2_fisikkanan("Rusak");

            ckl.setTdb_var_cbKunciktk1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbKunciktk2_perlengkapan("Rusak");
            ckl.setTdb_var_cbSpion1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbSpion2_perlengkapan("Rusak");
            ckl.setTdb_var_cbJok1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbJok2_perlengkapan("Rusak");
            ckl.setTdb_var_cbSarung1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbSarung2_perlengkapan("Rusak");
            ckl.setTdb_var_cbSandaran1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbSandaran2_perlengkapan("Rusak");
            ckl.setTdb_var_cbKarpet1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbKarpet2_perlengkapan("Rusak");
            ckl.setTdb_var_cbPelindung1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbPelindung2_perlengkapan("Rusak");
            ckl.setTdb_var_cbSegitiga1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbSegitiga2_perlengkapan("Rusak");
            ckl.setTdb_var_cbTool1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbTool2_perlengkapan("Rusak");
            ckl.setTdb_var_cbCadangan1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbCadangan2_perlengkapan("Rusak");
            ckl.setTdb_var_cbKunciban1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbKunciban2_perlengkapan("Rusak");
            ckl.setTdb_var_cbDongkrak1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbDongkrak2_perlengkapan("Rusak");
            ckl.setTdb_var_cbAntena1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbAntena2_perlengkapan("Rusak");
            ckl.setTdb_var_cbAirbag1_perlengkapan("Tidak Ada");
            ckl.setTdb_var_cbAirbag2_perlengkapan("Rusak");

            ckl.setTdb_var_cbLampukbt1_listrik("Tidak Ada");
            ckl.setTdb_var_cbLampukbt2_listrik("Rusak");
            ckl.setTdb_var_cbWiperkacadpn1_listrik("Tidak Ada");
            ckl.setTdb_var_cbWiperkacadpn2_listrik("Rusak");
            ckl.setTdb_var_cbWiperkacablk1_listrik("Tidak Ada");
            ckl.setTdb_var_cbWiperkacablk2_listrik("Rusak");
            ckl.setTdb_var_cbKlakson1_listrik("Tidak Ada");
            ckl.setTdb_var_cbKlakson2_listrik("Rusak");
            ckl.setTdb_var_cbAlarm1_listrik("Tidak Ada");
            ckl.setTdb_var_cbAlarm2_listrik("Rusak");
            ckl.setTdb_var_cbJam1_listrik("Tidak Ada");
            ckl.setTdb_var_cbJam2_listrik("Rusak");
            ckl.setTdb_var_cbLighter1_listrik("Tidak Ada");
            ckl.setTdb_var_cbLighter2_listrik("Rusak");
//            ckl.setTdb_var_rbRadio_listrik(false);
            ckl.setTdb_var_cbRadio1_listrik("Tidak Ada");
            ckl.setTdb_var_cbRadio2_listrik("Rusak");
            ckl.setTdb_var_cbPowersup1_listrik("Tidak Ada");
            ckl.setTdb_var_cbPowersup2_listrik("Rusak");
            ckl.setTdb_var_cbSpeaker1_listrik("Tidak Ada");
            ckl.setTdb_var_cbSpeaker2_listrik("Rusak");
            ckl.setTdb_var_cbAc1_listrik("Tidak Ada");
            ckl.setTdb_var_cbAc2_listrik("Rusak");
            ckl.setTdb_var_cbPowerwin1_listrik("Tidak Ada");
            ckl.setTdb_var_cbPowerwin2_listrik("Rusak");
            ckl.setTdb_var_cbCentral1_listrik("Tidak Ada");
            ckl.setTdb_var_cbCentral2_listrik("Rusak");
            ckl.setTdb_var_cbRemote1_listrik("Tidak Ada");
            ckl.setTdb_var_cbRemote2_listrik("Rusak");
            ckl.setTdb_var_cbSpeedo1_listrik("Tidak Ada");
            ckl.setTdb_var_cbSpeedo2_listrik("Rusak");
            ckl.setTdb_var_cbOdometer1_listrik("Tidak Ada");
            ckl.setTdb_var_cbOdometer2_listrik("Rusak");
            ckl.setTdb_var_cbTacho1_listrik("Tidak Ada");
            ckl.setTdb_var_cbTacho2_listrik("Rusak");
            ckl.setTdb_var_cbAccu1_listrik("Tidak Ada");
            ckl.setTdb_var_cbAccu2_listrik("Rusak");

            ckl.setTdb_var_cbMesin1_lain("Tidak Ada");
            ckl.setTdb_var_cbMesin2_lain("Rusak");
            ckl.setTdb_var_cbHidraulik1_lain("Tidak Ada");
            ckl.setTdb_var_cbHidraulik2_lain("Rusak");
            ckl.setTdb_var_cbGardan1_lain("Tidak Ada");
            ckl.setTdb_var_cbGardan2_lain("Rusak");
            ckl.setTdb_var_cbAs1_lain("Tidak Ada");
            ckl.setTdb_var_cbAs2_lain("Rusak");
            ckl.setTdb_var_cbBak1_lain("Tidak Ada");
            ckl.setTdb_var_cbBak2_lain("Rusak");
            ckl.setTdb_var_cbStnk1_lain("Tidak Ada");
            ckl.setTdb_var_cbStnk2_lain("Rusak");
            ckl.setTdb_var_cbBukukir1_lain("Tidak Ada");
            ckl.setTdb_var_cbBukukir2_lain("Rusak");
            ckl.setTdb_var_cbTrayek1_lain("Tidak Ada");
            ckl.setTdb_var_cbTrayek2_lain("Rusak");
            ckl.setTdb_var_cbUsaha1_lain("Tidak Ada");
            ckl.setTdb_var_cbUsaha2_lain("Rusak");
            ckl.setTdb_var_cbBukumnl1_lain("Tidak Ada");
            ckl.setTdb_var_cbBukumnl2_lain("Rusak");
            
            //=========================================
            
           
            ckl.setVdb_var_cbGrill2_fisikmuka(View.INVISIBLE);
            ckl.setVdb_var_cbLampu2_fisikmuka(View.INVISIBLE);
            ckl.setVdb_var_cbLampusen2_fisikmuka(View.INVISIBLE);
            ckl.setVdb_var_cbBamper2_fisikmuka(View.INVISIBLE);
            ckl.setVdb_var_cbEmblem2_fisikmuka(View.INVISIBLE);
            ckl.setVdb_var_cbTanduk2_fisikmuka(View.INVISIBLE);
            ckl.setVdb_var_cbFootstep2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbPintudpn2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbPintublk2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbBamper2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbFenderblk2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbSpion2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbEmblem2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_spBan2_fisikkiri(View.INVISIBLE);
//            ckl.setVdb_var_rbBan_fisikkiri("");
            ckl.setVdb_var_rbVelgstandard_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_rbVelgracing_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbVelg2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbDop2_fisikkiri(View.INVISIBLE);
            ckl.setVdb_var_cbDopBlk2_fisikkiri(View.INVISIBLE);
            
            ckl.setVdb_var_cbSpoiler2_fisikblkg(View.INVISIBLE);
            ckl.setVdb_var_cbLampu2_fisikblkg(View.INVISIBLE);
            ckl.setVdb_var_cbLampusen2_fisikblkg(View.INVISIBLE);
            ckl.setVdb_var_cbBamper2_fisikblkg(View.INVISIBLE);
            ckl.setVdb_var_cbEmblem2_fisikblkg(View.INVISIBLE);
            ckl.setVdb_var_cbKnalpot2_fisikblkg(View.INVISIBLE);
            
            ckl.setVdb_var_cbFoot2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbPintudpn2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbPintublk2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbBamper2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbFenderblk2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbSpion2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbEmblem2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_spBan2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_rbBan_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbVelg2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbDop2_fisikkanan(View.INVISIBLE);
            ckl.setVdb_var_cbDopBlk2_fisikkanan(View.INVISIBLE);

            ckl.setVdb_var_cbKunciktk2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbSpion2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbJok2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbSarung2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbSandaran2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbKarpet2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbPelindung2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbSegitiga2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbTool2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbCadangan2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbKunciban2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbDongkrak2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbAntena2_perlengkapan(View.INVISIBLE);
            ckl.setVdb_var_cbAirbag2_perlengkapan(View.INVISIBLE);
            
            ckl.setVdb_var_cbLampukbt2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbWiperkacadpn2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbWiperkacablk2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbKlakson2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbAlarm2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbJam2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbLighter2_listrik(View.INVISIBLE);
            ckl.setVdb_var_rbRadio_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbRadio2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbPowersup2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbSpeaker2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbAc2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbPowerwin2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbCentral2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbRemote2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbSpeedo2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbOdometer2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbTacho2_listrik(View.INVISIBLE);
            ckl.setVdb_var_cbAccu2_listrik(View.INVISIBLE);
            
            ckl.setVdb_var_cbMesin2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbHidraulik2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbGardan2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbAs2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbBak2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbStnk2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbBukukir2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbTrayek2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbUsaha2_lain(View.INVISIBLE);
            ckl.setVdb_var_cbBukumnl2_lain(View.INVISIBLE);

            realm.commitTransaction();

        }else {
            //Toast.makeText(DisInputCeklistActivity.this,"else",Toast.LENGTH_SHORT).show();

            cbGrill1_fisikmuka.setChecked(ceklists.first().getDb_var_cbGrill1_fisikmuka());
            cbGrill1_fisikmuka.setText(ceklists.first().getTdb_var_cbGrill1_fisikmuka());
            cbGrill2_fisikmuka.setChecked(ceklists.first().getDb_var_cbGrill2_fisikmuka());
            cbGrill2_fisikmuka.setText(ceklists.first().getTdb_var_cbGrill2_fisikmuka());
            cbGrill2_fisikmuka.setVisibility(ceklists.first().getVdb_var_cbGrill2_fisikmuka());

            cbLampu1_fisikmuka.setChecked(ceklists.first().getDb_var_cbLampu1_fisikmuka());
            cbLampu1_fisikmuka.setText(ceklists.first().getTdb_var_cbLampu1_fisikmuka());
            cbLampu2_fisikmuka.setChecked(ceklists.first().getDb_var_cbLampu2_fisikmuka());
            cbLampu2_fisikmuka.setText(ceklists.first().getTdb_var_cbLampu2_fisikmuka());
            cbLampu2_fisikmuka.setVisibility(ceklists.first().getVdb_var_cbLampu2_fisikmuka());

            cbLampusen1_fisikmuka.setChecked(ceklists.first().getDb_var_cbLampusen1_fisikmuka());
            cbLampusen1_fisikmuka.setText(ceklists.first().getTdb_var_cbLampusen1_fisikmuka());
            cbLampusen2_fisikmuka.setChecked(ceklists.first().getDb_var_cbLampusen2_fisikmuka());
            cbLampusen2_fisikmuka.setText(ceklists.first().getTdb_var_cbLampusen2_fisikmuka());
            cbLampusen2_fisikmuka.setVisibility(ceklists.first().getVdb_var_cbLampusen2_fisikmuka());

            cbBamper1_fisikmuka.setChecked(ceklists.first().getDb_var_cbBamper1_fisikmuka());
            cbBamper1_fisikmuka.setText(ceklists.first().getTdb_var_cbBamper1_fisikmuka());
            cbBamper2_fisikmuka.setChecked(ceklists.first().getDb_var_cbBamper2_fisikmuka());
            cbBamper2_fisikmuka.setText(ceklists.first().getTdb_var_cbBamper2_fisikmuka());
            cbBamper2_fisikmuka.setVisibility(ceklists.first().getVdb_var_cbBamper2_fisikmuka());

            cbEmblem1_fisikmuka.setChecked(ceklists.first().getDb_var_cbEmblem1_fisikmuka());
            cbEmblem1_fisikmuka.setText(ceklists.first().getTdb_var_cbEmblem1_fisikmuka());
            cbEmblem2_fisikmuka.setChecked(ceklists.first().getDb_var_cbEmblem2_fisikmuka());
            cbEmblem2_fisikmuka.setText(ceklists.first().getTdb_var_cbEmblem2_fisikmuka());
            cbEmblem2_fisikmuka.setVisibility(ceklists.first().getVdb_var_cbEmblem2_fisikmuka());

            cbTanduk1_fisikmuka.setChecked(ceklists.first().getDb_var_cbTanduk1_fisikmuka());
            cbTanduk1_fisikmuka.setText(ceklists.first().getTdb_var_cbTanduk1_fisikmuka());
            cbTanduk2_fisikmuka.setChecked(ceklists.first().getDb_var_cbTanduk2_fisikmuka());
            cbTanduk2_fisikmuka.setText(ceklists.first().getTdb_var_cbTanduk2_fisikmuka());
            cbTanduk2_fisikmuka.setVisibility(ceklists.first().getVdb_var_cbTanduk2_fisikmuka());

            cbFootstep1_fisikkiri.setChecked(ceklists.first().getDb_var_cbFootstep1_fisikkiri());
            cbFootstep1_fisikkiri.setText(ceklists.first().getTdb_var_cbFootstep1_fisikkiri());
            cbFootstep2_fisikkiri.setChecked(ceklists.first().getDb_var_cbFootstep2_fisikkiri());
            cbFootstep2_fisikkiri.setText(ceklists.first().getTdb_var_cbFootstep2_fisikkiri());
            cbFootstep2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbFootstep2_fisikkiri());

            cbPintudpn1_fisikkiri.setChecked(ceklists.first().getDb_var_cbPintudpn1_fisikkiri());
            cbPintudpn1_fisikkiri.setText(ceklists.first().getTdb_var_cbPintudpn1_fisikkiri());
            cbPintudpn2_fisikkiri.setChecked(ceklists.first().getDb_var_cbPintudpn2_fisikkiri());
            cbPintudpn2_fisikkiri.setText(ceklists.first().getTdb_var_cbPintudpn2_fisikkiri());
            cbPintudpn2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbPintudpn2_fisikkiri());

            cbPintublk1_fisikkiri.setChecked(ceklists.first().getDb_var_cbPintublk1_fisikkiri());
            cbPintublk1_fisikkiri.setText(ceklists.first().getTdb_var_cbPintublk1_fisikkiri());
            cbPintublk2_fisikkiri.setChecked(ceklists.first().getDb_var_cbPintublk2_fisikkiri());
            cbPintublk2_fisikkiri.setText(ceklists.first().getTdb_var_cbPintublk2_fisikkiri());
            cbPintublk2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbPintublk2_fisikkiri());

            cbBamper1_fisikkiri.setChecked(ceklists.first().getDb_var_cbBamper1_fisikkiri());
            cbBamper1_fisikkiri.setText(ceklists.first().getTdb_var_cbBamper1_fisikkiri());
            cbBamper2_fisikkiri.setChecked(ceklists.first().getDb_var_cbBamper2_fisikkiri());
            cbBamper2_fisikkiri.setText(ceklists.first().getTdb_var_cbBamper2_fisikkiri());
            cbBamper2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbBamper2_fisikkiri());

            cbFenderblk1_fisikkiri.setChecked(ceklists.first().getDb_var_cbFenderblk1_fisikkiri());
            cbFenderblk1_fisikkiri.setText(ceklists.first().getTdb_var_cbFenderblk1_fisikkiri());
            cbFenderblk2_fisikkiri.setChecked(ceklists.first().getDb_var_cbFenderblk2_fisikkiri());
            cbFenderblk2_fisikkiri.setText(ceklists.first().getTdb_var_cbFenderblk2_fisikkiri());
            cbFenderblk2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbFenderblk2_fisikkiri());

            cbSpion1_fisikkiri.setChecked(ceklists.first().getDb_var_cbSpion1_fisikkiri());
            cbSpion1_fisikkiri.setText(ceklists.first().getTdb_var_cbSpion1_fisikkiri());
            cbSpion2_fisikkiri.setChecked(ceklists.first().getDb_var_cbSpion2_fisikkiri());
            cbSpion2_fisikkiri.setText(ceklists.first().getTdb_var_cbSpion2_fisikkiri());
            cbSpion2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbSpion2_fisikkiri());

            cbEmblem1_fisikkiri.setChecked(ceklists.first().getDb_var_cbEmblem1_fisikkiri());
            cbEmblem1_fisikkiri.setText(ceklists.first().getTdb_var_cbEmblem1_fisikkiri());
            cbEmblem2_fisikkiri.setChecked(ceklists.first().getDb_var_cbEmblem2_fisikkiri());
            cbEmblem2_fisikkiri.setText(ceklists.first().getTdb_var_cbEmblem2_fisikkiri());
            cbEmblem2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbEmblem2_fisikkiri());

            rbBanstandard1_fisikkiri.setChecked(ceklists.first().getDb_var_rbBanstandard_fisikkiri());
            rbBanradial1_fisikkiri.setChecked(ceklists.first().getDb_var_rbBanradial_fisikkiri());
            cbBan1_fisikkiri.setChecked(ceklists.first().getDb_var_cbBan1_fisikkiri());
            cbBan1_fisikkiri.setText(ceklists.first().getTdb_var_cbBan1_fisikkiri());
            spBan2_fisikkiri.setSelection(ceklists.first().getDb_var_spBan2_fisikkiri(),true);
            spBan2_fisikkiri.setVisibility(ceklists.first().getVdb_var_spBan2_fisikkiri());

            Log.wtf("spban", String.valueOf(ceklists.first().getDb_var_spBan2_fisikkiri()));


//            AppCompatSpinner spBan2_fisikkiri;
//            RadioButton rbVelgstandard_fisikkiri, rbVelgracing_fisikkiri;

            rbVelgstandard_fisikkiri.setChecked(ceklists.first().getDb_var_rbVelgstandard_fisikkiri());
            rbVelgracing_fisikkiri.setChecked(ceklists.first().getDb_var_rbVelgracing_fisikkiri());
            cbVelg1_fisikkiri.setChecked(ceklists.first().getDb_var_cbVelg1_fisikkiri());
            cbVelg1_fisikkiri.setText(ceklists.first().getTdb_var_cbVelg1_fisikkiri());
            cbVelg2_fisikkiri.setChecked(ceklists.first().getDb_var_cbVelg2_fisikkiri());
            cbVelg2_fisikkiri.setText(ceklists.first().getTdb_var_cbVelg2_fisikkiri());
            cbVelg2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbVelg2_fisikkiri());

            cbDop1_fisikkiri.setChecked(ceklists.first().getDb_var_cbDop1_fisikkiri());
            cbDop1_fisikkiri.setText(ceklists.first().getTdb_var_cbDop1_fisikkiri());
            cbDop2_fisikkiri.setChecked(ceklists.first().getDb_var_cbDop2_fisikkiri());
            cbDop2_fisikkiri.setText(ceklists.first().getTdb_var_cbDop2_fisikkiri());
            cbDop2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbDop2_fisikkiri());

            cbDopBlk1_fisikkiri.setChecked(ceklists.first().getDb_var_cbDopBlk1_fisikkiri());
            cbDopBlk1_fisikkiri.setText(ceklists.first().getTdb_var_cbDopBlk1_fisikkiri());
            cbDopBlk2_fisikkiri.setChecked(ceklists.first().getDb_var_cbDopBlk2_fisikkiri());
            cbDopBlk2_fisikkiri.setText(ceklists.first().getTdb_var_cbDopBlk2_fisikkiri());
            cbDopBlk2_fisikkiri.setVisibility(ceklists.first().getVdb_var_cbDopBlk2_fisikkiri());

            cbSpoiler1_fisikblkg.setChecked(ceklists.first().getDb_var_cbSpoiler1_fisikblkg());
            cbSpoiler1_fisikblkg.setText(ceklists.first().getTdb_var_cbSpoiler1_fisikblkg());
            cbSpoiler2_fisikblkg.setChecked(ceklists.first().getDb_var_cbSpoiler2_fisikblkg());
            cbSpoiler2_fisikblkg.setText(ceklists.first().getTdb_var_cbSpoiler2_fisikblkg());
            cbSpoiler2_fisikblkg.setVisibility(ceklists.first().getVdb_var_cbSpoiler2_fisikblkg());

            cbLampu1_fisikblkg.setChecked(ceklists.first().getDb_var_cbLampu1_fisikblkg());
            cbLampu1_fisikblkg.setText(ceklists.first().getTdb_var_cbLampu1_fisikblkg());
            cbLampu2_fisikblkg.setChecked(ceklists.first().getDb_var_cbLampu2_fisikblkg());
            cbLampu2_fisikblkg.setText(ceklists.first().getTdb_var_cbLampu2_fisikblkg());
            cbLampu2_fisikblkg.setVisibility(ceklists.first().getVdb_var_cbLampu2_fisikblkg());

            cbLampusen1_fisikblkg.setChecked(ceklists.first().getDb_var_cbLampusen1_fisikblkg());
            cbLampusen1_fisikblkg.setText(ceklists.first().getTdb_var_cbLampusen1_fisikblkg());
            cbLampusen2_fisikblkg.setChecked(ceklists.first().getDb_var_cbLampusen2_fisikblkg());
            cbLampusen2_fisikblkg.setText(ceklists.first().getTdb_var_cbLampusen2_fisikblkg());
            cbLampusen2_fisikblkg.setVisibility(ceklists.first().getVdb_var_cbLampusen2_fisikblkg());

            cbBamper1_fisikblkg.setChecked(ceklists.first().getDb_var_cbBamper1_fisikblkg());
            cbBamper1_fisikblkg.setText(ceklists.first().getTdb_var_cbBamper1_fisikblkg());
            cbBamper2_fisikblkg.setChecked(ceklists.first().getDb_var_cbBamper2_fisikblkg());
            cbBamper2_fisikblkg.setText(ceklists.first().getTdb_var_cbBamper2_fisikblkg());
            cbBamper2_fisikblkg.setVisibility(ceklists.first().getVdb_var_cbBamper2_fisikblkg());

            cbEmblem1_fisikblkg.setChecked(ceklists.first().getDb_var_cbEmblem1_fisikblkg());
            cbEmblem1_fisikblkg.setText(ceklists.first().getTdb_var_cbEmblem1_fisikblkg());
            cbEmblem2_fisikblkg.setChecked(ceklists.first().getDb_var_cbEmblem2_fisikblkg());
            cbEmblem2_fisikblkg.setText(ceklists.first().getTdb_var_cbEmblem2_fisikblkg());
            cbEmblem2_fisikblkg.setVisibility(ceklists.first().getVdb_var_cbEmblem2_fisikblkg());

            cbBan1_fisikkiri.setChecked(ceklists.first().getDb_var_cbBan1_fisikkiri());
            cbBan1_fisikkiri.setText(ceklists.first().getTdb_var_cbBan1_fisikkiri());

            cbKnalpot1_fisikblkg.setChecked(ceklists.first().getDb_var_cbKnalpot1_fisikblkg());
            cbKnalpot1_fisikblkg.setText(ceklists.first().getTdb_var_cbKnalpot1_fisikblkg());
            cbKnalpot2_fisikblkg.setChecked(ceklists.first().getDb_var_cbKnalpot2_fisikblkg());
            cbKnalpot2_fisikblkg.setText(ceklists.first().getTdb_var_cbKnalpot2_fisikblkg());
            cbKnalpot2_fisikblkg.setVisibility(ceklists.first().getVdb_var_cbKnalpot2_fisikblkg());

            cbFoot1_fisikkanan.setChecked(ceklists.first().getDb_var_cbFoot1_fisikkanan());
            cbFoot1_fisikkanan.setText(ceklists.first().getTdb_var_cbFoot1_fisikkanan());
            cbFoot2_fisikkanan.setChecked(ceklists.first().getDb_var_cbFoot2_fisikkanan());
            cbFoot2_fisikkanan.setText(ceklists.first().getTdb_var_cbFoot2_fisikkanan());
            cbFoot2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbFoot2_fisikkanan());

            cbPintudpn1_fisikkanan.setChecked(ceklists.first().getDb_var_cbPintudpn1_fisikkanan());
            cbPintudpn1_fisikkanan.setText(ceklists.first().getTdb_var_cbPintudpn1_fisikkanan());
            cbPintudpn2_fisikkanan.setChecked(ceklists.first().getDb_var_cbPintudpn2_fisikkanan());
            cbPintudpn2_fisikkanan.setText(ceklists.first().getTdb_var_cbPintudpn2_fisikkanan());
            cbPintudpn2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbPintudpn2_fisikkanan());

            cbPintublk1_fisikkanan.setChecked(ceklists.first().getDb_var_cbPintublk1_fisikkanan());
            cbPintublk1_fisikkanan.setText(ceklists.first().getTdb_var_cbPintublk1_fisikkanan());
            cbPintublk2_fisikkanan.setChecked(ceklists.first().getDb_var_cbPintublk2_fisikkanan());
            cbPintublk2_fisikkanan.setText(ceklists.first().getTdb_var_cbPintublk2_fisikkanan());
            cbPintublk2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbPintublk2_fisikkanan());

            cbBamper1_fisikkanan.setChecked(ceklists.first().getDb_var_cbBamper1_fisikkanan());
            cbBamper1_fisikkanan.setText(ceklists.first().getTdb_var_cbBamper1_fisikkanan());
            cbBamper2_fisikkanan.setChecked(ceklists.first().getDb_var_cbBamper2_fisikkanan());
            cbBamper2_fisikkanan.setText(ceklists.first().getTdb_var_cbBamper2_fisikkanan());
            cbBamper2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbBamper2_fisikkanan());

            cbFenderblk1_fisikkanan.setChecked(ceklists.first().getDb_var_cbFenderblk1_fisikkanan());
            cbFenderblk1_fisikkanan.setText(ceklists.first().getTdb_var_cbFenderblk1_fisikkanan());
            cbFenderblk2_fisikkanan.setChecked(ceklists.first().getDb_var_cbFenderblk2_fisikkanan());
            cbFenderblk2_fisikkanan.setText(ceklists.first().getTdb_var_cbFenderblk2_fisikkanan());
            cbFenderblk2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbFenderblk2_fisikkanan());

            cbSpion1_fisikkanan.setChecked(ceklists.first().getDb_var_cbSpion1_fisikkanan());
            cbSpion1_fisikkanan.setText(ceklists.first().getTdb_var_cbSpion1_fisikkanan());
            cbSpion2_fisikkanan.setChecked(ceklists.first().getDb_var_cbSpion2_fisikkanan());
            cbSpion2_fisikkanan.setText(ceklists.first().getTdb_var_cbSpion2_fisikkanan());
            cbSpion2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbSpion2_fisikkanan());

            cbEmblem1_fisikkanan.setChecked(ceklists.first().getDb_var_cbEmblem1_fisikkanan());
            cbEmblem1_fisikkanan.setText(ceklists.first().getTdb_var_cbEmblem1_fisikkanan());
            cbEmblem2_fisikkanan.setChecked(ceklists.first().getDb_var_cbEmblem2_fisikkanan());
            cbEmblem2_fisikkanan.setText(ceklists.first().getTdb_var_cbEmblem2_fisikkanan());
            cbEmblem2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbEmblem2_fisikkanan());


            rbBanstandard_fisikkanan.setChecked(ceklists.first().getDb_var_rbBanstandard_fisikkanan());
            rbBanradial_fisikkanan.setChecked(ceklists.first().getDb_var_rbBanradial_fisikkanan());
            cbBan1_fisikkanan.setChecked(ceklists.first().getDb_var_cbBan1_fisikkanan());
            cbBan1_fisikkanan.setText(ceklists.first().getTdb_var_cbBan1_fisikkanan());
            spBan2_fisikkanan.setSelection(ceklists.first().getDb_var_spBan2_fisikkanan(),true);
            spBan2_fisikkanan.setVisibility(ceklists.first().getVdb_var_spBan2_fisikkanan());

            rbVelgstandard_fisikkanan.setChecked(ceklists.first().getDb_var_rbVelgstandard_fisikkanan());
            rbVelgracing_fisikkanan.setChecked(ceklists.first().getDb_var_rbVelgracing_fisikkanan());
            cbVelg1_fisikkanan.setChecked(ceklists.first().getDb_var_cbVelg1_fisikkanan());
            cbVelg1_fisikkanan.setText(ceklists.first().getTdb_var_cbVelg1_fisikkanan());
            cbVelg2_fisikkanan.setChecked(ceklists.first().getDb_var_cbVelg2_fisikkanan());
            cbVelg2_fisikkanan.setText(ceklists.first().getTdb_var_cbVelg2_fisikkanan());
            cbVelg2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbVelg2_fisikkanan());

            cbDop1_fisikkanan.setChecked(ceklists.first().getDb_var_cbDop1_fisikkanan());
            cbDop1_fisikkanan.setText(ceklists.first().getTdb_var_cbDop1_fisikkanan());
            cbDop2_fisikkanan.setChecked(ceklists.first().getDb_var_cbDop2_fisikkanan());
            cbDop2_fisikkanan.setText(ceklists.first().getTdb_var_cbDop2_fisikkanan());
            cbDop2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbDop2_fisikkanan());

            cbDopBlk1_fisikkanan.setChecked(ceklists.first().getDb_var_cbDopBlk1_fisikkanan());
            cbDopBlk1_fisikkanan.setText(ceklists.first().getTdb_var_cbDopBlk1_fisikkanan());
            cbDopBlk2_fisikkanan.setChecked(ceklists.first().getDb_var_cbDopBlk2_fisikkanan());
            cbDopBlk2_fisikkanan.setText(ceklists.first().getTdb_var_cbDopBlk2_fisikkanan());
            cbDopBlk2_fisikkanan.setVisibility(ceklists.first().getVdb_var_cbDopBlk2_fisikkanan());

            cbKunciktk1_perlengkapan.setChecked(ceklists.first().getDb_var_cbKunciktk1_perlengkapan());
            cbKunciktk1_perlengkapan.setText(ceklists.first().getTdb_var_cbKunciktk1_perlengkapan());
            cbKunciktk2_perlengkapan.setChecked(ceklists.first().getDb_var_cbKunciktk2_perlengkapan());
            cbKunciktk2_perlengkapan.setText(ceklists.first().getTdb_var_cbKunciktk2_perlengkapan());
            cbKunciktk2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbKunciktk2_perlengkapan());

            cbSpion1_perlengkapan.setChecked(ceklists.first().getDb_var_cbSpion1_perlengkapan());
            cbSpion1_perlengkapan.setText(ceklists.first().getTdb_var_cbSpion1_perlengkapan());
            cbSpion2_perlengkapan.setChecked(ceklists.first().getDb_var_cbSpion2_perlengkapan());
            cbSpion2_perlengkapan.setText(ceklists.first().getTdb_var_cbSpion2_perlengkapan());
            cbSpion2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbSpion2_perlengkapan());

            cbJok1_perlengkapan.setChecked(ceklists.first().getDb_var_cbJok1_perlengkapan());
            cbJok1_perlengkapan.setText(ceklists.first().getTdb_var_cbJok1_perlengkapan());
            cbJok2_perlengkapan.setChecked(ceklists.first().getDb_var_cbJok2_perlengkapan());
            cbJok2_perlengkapan.setText(ceklists.first().getTdb_var_cbJok2_perlengkapan());
            cbJok2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbJok2_perlengkapan());

            cbSarung1_perlengkapan.setChecked(ceklists.first().getDb_var_cbSarung1_perlengkapan());
            cbSarung1_perlengkapan.setText(ceklists.first().getTdb_var_cbSarung1_perlengkapan());
            cbSarung2_perlengkapan.setChecked(ceklists.first().getDb_var_cbSarung2_perlengkapan());
            cbSarung2_perlengkapan.setText(ceklists.first().getTdb_var_cbSarung2_perlengkapan());
            cbSarung2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbSarung2_perlengkapan());

            cbSandaran1_perlengkapan.setChecked(ceklists.first().getDb_var_cbSandaran1_perlengkapan());
            cbSandaran1_perlengkapan.setText(ceklists.first().getTdb_var_cbSandaran1_perlengkapan());
            cbSandaran2_perlengkapan.setChecked(ceklists.first().getDb_var_cbSandaran2_perlengkapan());
            cbSandaran2_perlengkapan.setText(ceklists.first().getTdb_var_cbSandaran2_perlengkapan());
            cbSandaran2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbSandaran2_perlengkapan());

            cbKarpet1_perlengkapan.setChecked(ceklists.first().getDb_var_cbKarpet1_perlengkapan());
            cbKarpet1_perlengkapan.setText(ceklists.first().getTdb_var_cbKarpet1_perlengkapan());
            cbKarpet2_perlengkapan.setChecked(ceklists.first().getDb_var_cbKarpet2_perlengkapan());
            cbKarpet2_perlengkapan.setText(ceklists.first().getTdb_var_cbKarpet2_perlengkapan());
            cbKarpet2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbKarpet2_perlengkapan());

            cbPelindung1_perlengkapan.setChecked(ceklists.first().getDb_var_cbPelindung1_perlengkapan());
            cbPelindung1_perlengkapan.setText(ceklists.first().getTdb_var_cbPelindung1_perlengkapan());
            cbPelindung2_perlengkapan.setChecked(ceklists.first().getDb_var_cbPelindung2_perlengkapan());
            cbPelindung2_perlengkapan.setText(ceklists.first().getTdb_var_cbPelindung2_perlengkapan());
            cbPelindung2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbPelindung2_perlengkapan());

            cbSegitiga1_perlengkapan.setChecked(ceklists.first().getDb_var_cbSegitiga1_perlengkapan());
            cbSegitiga1_perlengkapan.setText(ceklists.first().getTdb_var_cbSegitiga1_perlengkapan());
            cbSegitiga2_perlengkapan.setChecked(ceklists.first().getDb_var_cbSegitiga2_perlengkapan());
            cbSegitiga2_perlengkapan.setText(ceklists.first().getTdb_var_cbSegitiga2_perlengkapan());
            cbSegitiga2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbSegitiga2_perlengkapan());

            cbTool1_perlengkapan.setChecked(ceklists.first().getDb_var_cbTool1_perlengkapan());
            cbTool1_perlengkapan.setText(ceklists.first().getTdb_var_cbTool1_perlengkapan());
            cbTool2_perlengkapan.setChecked(ceklists.first().getDb_var_cbTool2_perlengkapan());
            cbTool2_perlengkapan.setText(ceklists.first().getTdb_var_cbTool2_perlengkapan());
            cbTool2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbTool2_perlengkapan());

            cbCadangan1_perlengkapan.setChecked(ceklists.first().getDb_var_cbCadangan1_perlengkapan());
            cbCadangan1_perlengkapan.setText(ceklists.first().getTdb_var_cbCadangan1_perlengkapan());
            cbCadangan2_perlengkapan.setChecked(ceklists.first().getDb_var_cbCadangan2_perlengkapan());
            cbCadangan2_perlengkapan.setText(ceklists.first().getTdb_var_cbCadangan2_perlengkapan());
            cbCadangan2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbCadangan2_perlengkapan());

            cbKunciban1_perlengkapan.setChecked(ceklists.first().getDb_var_cbKunciban1_perlengkapan());
            cbKunciban1_perlengkapan.setText(ceklists.first().getTdb_var_cbKunciban1_perlengkapan());
            cbKunciban2_perlengkapan.setChecked(ceklists.first().getDb_var_cbKunciban2_perlengkapan());
            cbKunciban2_perlengkapan.setText(ceklists.first().getTdb_var_cbKunciban2_perlengkapan());
            cbKunciban2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbKunciban2_perlengkapan());

            cbDongkrak1_perlengkapan.setChecked(ceklists.first().getDb_var_cbDongkrak1_perlengkapan());
            cbDongkrak1_perlengkapan.setText(ceklists.first().getTdb_var_cbDongkrak1_perlengkapan());
            cbDongkrak2_perlengkapan.setChecked(ceklists.first().getDb_var_cbDongkrak2_perlengkapan());
            cbDongkrak2_perlengkapan.setText(ceklists.first().getTdb_var_cbDongkrak2_perlengkapan());
            cbDongkrak2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbDongkrak2_perlengkapan());

            cbAntena1_perlengkapan.setChecked(ceklists.first().getDb_var_cbAntena1_perlengkapan());
            cbAntena1_perlengkapan.setText(ceklists.first().getTdb_var_cbAntena1_perlengkapan());
            cbAntena2_perlengkapan.setChecked(ceklists.first().getDb_var_cbAntena2_perlengkapan());
            cbAntena2_perlengkapan.setText(ceklists.first().getTdb_var_cbAntena2_perlengkapan());
            cbAntena2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbAntena2_perlengkapan());

            cbAirbag1_perlengkapan.setChecked(ceklists.first().getDb_var_cbAirbag1_perlengkapan());
            cbAirbag1_perlengkapan.setText(ceklists.first().getTdb_var_cbAirbag1_perlengkapan());
            cbAirbag2_perlengkapan.setChecked(ceklists.first().getDb_var_cbAirbag2_perlengkapan());
            cbAirbag2_perlengkapan.setText(ceklists.first().getTdb_var_cbAirbag2_perlengkapan());
            cbAirbag2_perlengkapan.setVisibility(ceklists.first().getVdb_var_cbAirbag2_perlengkapan());

            cbLampukbt1_listrik.setChecked(ceklists.first().getDb_var_cbLampukbt1_listrik());
            cbLampukbt1_listrik.setText(ceklists.first().getTdb_var_cbLampukbt1_listrik());
            cbLampukbt2_listrik.setChecked(ceklists.first().getDb_var_cbLampukbt2_listrik());
            cbLampukbt2_listrik.setText(ceklists.first().getTdb_var_cbLampukbt2_listrik());
            cbLampukbt2_listrik.setVisibility(ceklists.first().getVdb_var_cbLampukbt2_listrik());

            cbWiperkacadpn1_listrik.setChecked(ceklists.first().getDb_var_cbWiperkacadpn1_listrik());
            cbWiperkacadpn1_listrik.setText(ceklists.first().getTdb_var_cbWiperkacadpn1_listrik());
            cbWiperkacadpn2_listrik.setChecked(ceklists.first().getDb_var_cbWiperkacadpn2_listrik());
            cbWiperkacadpn2_listrik.setText(ceklists.first().getTdb_var_cbWiperkacadpn2_listrik());
            cbWiperkacadpn2_listrik.setVisibility(ceklists.first().getVdb_var_cbWiperkacadpn2_listrik());

            cbWiperkacablk1_listrik.setChecked(ceklists.first().getDb_var_cbWiperkacablk1_listrik());
            cbWiperkacablk1_listrik.setText(ceklists.first().getTdb_var_cbWiperkacablk1_listrik());
            cbWiperkacablk2_listrik.setChecked(ceklists.first().getDb_var_cbWiperkacablk2_listrik());
            cbWiperkacablk2_listrik.setText(ceklists.first().getTdb_var_cbWiperkacablk2_listrik());
            cbWiperkacablk2_listrik.setVisibility(ceklists.first().getVdb_var_cbWiperkacablk2_listrik());

            cbKlakson1_listrik.setChecked(ceklists.first().getDb_var_cbKlakson1_listrik());
            cbKlakson1_listrik.setText(ceklists.first().getTdb_var_cbKlakson1_listrik());
            cbKlakson2_listrik.setChecked(ceklists.first().getDb_var_cbKlakson2_listrik());
            cbKlakson2_listrik.setText(ceklists.first().getTdb_var_cbKlakson2_listrik());
            cbKlakson2_listrik.setVisibility(ceklists.first().getVdb_var_cbKlakson2_listrik());

            cbAlarm1_listrik.setChecked(ceklists.first().getDb_var_cbAlarm1_listrik());
            cbAlarm1_listrik.setText(ceklists.first().getTdb_var_cbAlarm1_listrik());
            cbAlarm2_listrik.setChecked(ceklists.first().getDb_var_cbAlarm2_listrik());
            cbAlarm2_listrik.setText(ceklists.first().getTdb_var_cbAlarm2_listrik());
            cbAlarm2_listrik.setVisibility(ceklists.first().getVdb_var_cbAlarm2_listrik());

            cbJam1_listrik.setChecked(ceklists.first().getDb_var_cbJam1_listrik());
            cbJam1_listrik.setText(ceklists.first().getTdb_var_cbJam1_listrik());
            cbJam2_listrik.setChecked(ceklists.first().getDb_var_cbJam2_listrik());
            cbJam2_listrik.setText(ceklists.first().getTdb_var_cbJam2_listrik());
            cbJam2_listrik.setVisibility(ceklists.first().getVdb_var_cbJam2_listrik());

            cbLighter1_listrik.setChecked(ceklists.first().getDb_var_cbLighter1_listrik());
            cbLighter1_listrik.setText(ceklists.first().getTdb_var_cbLighter1_listrik());
            cbLighter2_listrik.setChecked(ceklists.first().getDb_var_cbLighter2_listrik());
            cbLighter2_listrik.setText(ceklists.first().getTdb_var_cbLighter2_listrik());
            cbLighter2_listrik.setVisibility(ceklists.first().getVdb_var_cbLighter2_listrik());

            rbRadio_listrik.setChecked(ceklists.first().getDb_var_rbRadio_listrik());
            rbTape_listrik.setChecked(ceklists.first().getDb_var_rbTape_listrik());
            rbCd_listrik.setChecked(ceklists.first().getDb_var_rbCd_listrik());
            etMerk_listrik.setText(ceklists.first().getTdb_var_etMerk_listrik());

            cbRadio1_listrik.setChecked(ceklists.first().getDb_var_cbRadio1_listrik());
            cbRadio1_listrik.setText(ceklists.first().getTdb_var_cbRadio1_listrik());
            cbRadio2_listrik.setChecked(ceklists.first().getDb_var_cbRadio2_listrik());
            cbRadio2_listrik.setText(ceklists.first().getTdb_var_cbRadio2_listrik());
            cbRadio2_listrik.setVisibility(ceklists.first().getVdb_var_cbRadio2_listrik());

            cbPowersup1_listrik.setChecked(ceklists.first().getDb_var_cbPowersup1_listrik());
            cbPowersup1_listrik.setText(ceklists.first().getTdb_var_cbPowersup1_listrik());
            cbPowersup2_listrik.setChecked(ceklists.first().getDb_var_cbPowersup2_listrik());
            cbPowersup2_listrik.setText(ceklists.first().getTdb_var_cbPowersup2_listrik());
            cbPowersup2_listrik.setVisibility(ceklists.first().getVdb_var_cbPowersup2_listrik());

            cbSpeaker1_listrik.setChecked(ceklists.first().getDb_var_cbSpeaker1_listrik());
            cbSpeaker1_listrik.setText(ceklists.first().getTdb_var_cbSpeaker1_listrik());
            cbSpeaker2_listrik.setChecked(ceklists.first().getDb_var_cbSpeaker2_listrik());
            cbSpeaker2_listrik.setText(ceklists.first().getTdb_var_cbSpeaker2_listrik());
            cbSpeaker2_listrik.setVisibility(ceklists.first().getVdb_var_cbSpeaker2_listrik());

            cbAc1_listrik.setChecked(ceklists.first().getDb_var_cbAc1_listrik());
            cbAc1_listrik.setText(ceklists.first().getTdb_var_cbAc1_listrik());
            cbAc2_listrik.setChecked(ceklists.first().getDb_var_cbAc2_listrik());
            cbAc2_listrik.setText(ceklists.first().getTdb_var_cbAc2_listrik());
            cbAc2_listrik.setVisibility(ceklists.first().getVdb_var_cbAc2_listrik());

            cbPowerwin1_listrik.setChecked(ceklists.first().getDb_var_cbPowerwin1_listrik());
            cbPowerwin1_listrik.setText(ceklists.first().getTdb_var_cbPowerwin1_listrik());
            cbPowerwin2_listrik.setChecked(ceklists.first().getDb_var_cbPowerwin2_listrik());
            cbPowerwin2_listrik.setText(ceklists.first().getTdb_var_cbPowerwin2_listrik());
            cbPowerwin2_listrik.setVisibility(ceklists.first().getVdb_var_cbPowerwin2_listrik());

            cbCentral1_listrik.setChecked(ceklists.first().getDb_var_cbCentral1_listrik());
            cbCentral1_listrik.setText(ceklists.first().getTdb_var_cbCentral1_listrik());
            cbCentral2_listrik.setChecked(ceklists.first().getDb_var_cbCentral2_listrik());
            cbCentral2_listrik.setText(ceklists.first().getTdb_var_cbCentral2_listrik());
            cbCentral2_listrik.setVisibility(ceklists.first().getVdb_var_cbCentral2_listrik());

            cbRemote1_listrik.setChecked(ceklists.first().getDb_var_cbRemote1_listrik());
            cbRemote1_listrik.setText(ceklists.first().getTdb_var_cbRemote1_listrik());
            cbRemote2_listrik.setChecked(ceklists.first().getDb_var_cbRemote2_listrik());
            cbRemote2_listrik.setText(ceklists.first().getTdb_var_cbRemote2_listrik());
            cbRemote2_listrik.setVisibility(ceklists.first().getVdb_var_cbRemote2_listrik());

            cbSpeedo1_listrik.setChecked(ceklists.first().getDb_var_cbSpeedo1_listrik());
            cbSpeedo1_listrik.setText(ceklists.first().getTdb_var_cbSpeedo1_listrik());
            cbSpeedo2_listrik.setChecked(ceklists.first().getDb_var_cbSpeedo2_listrik());
            cbSpeedo2_listrik.setText(ceklists.first().getTdb_var_cbSpeedo2_listrik());
            cbSpeedo2_listrik.setVisibility(ceklists.first().getVdb_var_cbSpeedo2_listrik());

            cbOdometer1_listrik.setChecked(ceklists.first().getDb_var_cbOdometer1_listrik());
            cbOdometer1_listrik.setText(ceklists.first().getTdb_var_cbOdometer1_listrik());
            cbOdometer2_listrik.setChecked(ceklists.first().getDb_var_cbOdometer2_listrik());
            cbOdometer2_listrik.setText(ceklists.first().getTdb_var_cbOdometer2_listrik());
            cbOdometer2_listrik.setVisibility(ceklists.first().getVdb_var_cbOdometer2_listrik());

            cbTacho1_listrik.setChecked(ceklists.first().getDb_var_cbTacho1_listrik());
            cbTacho1_listrik.setText(ceklists.first().getTdb_var_cbTacho1_listrik());
            cbTacho2_listrik.setChecked(ceklists.first().getDb_var_cbTacho2_listrik());
            cbTacho2_listrik.setText(ceklists.first().getTdb_var_cbTacho2_listrik());
            cbTacho2_listrik.setVisibility(ceklists.first().getVdb_var_cbTacho2_listrik());

            cbAccu1_listrik.setChecked(ceklists.first().getDb_var_cbAccu1_listrik());
            cbAccu1_listrik.setText(ceklists.first().getTdb_var_cbAccu1_listrik());
            cbAccu2_listrik.setChecked(ceklists.first().getDb_var_cbAccu2_listrik());
            cbAccu2_listrik.setText(ceklists.first().getTdb_var_cbAccu2_listrik());
            cbAccu2_listrik.setVisibility(ceklists.first().getVdb_var_cbAccu2_listrik());

            cbMesin1_lain.setChecked(ceklists.first().getDb_var_cbMesin1_lain());
            cbMesin1_lain.setText(ceklists.first().getTdb_var_cbMesin1_lain());
            cbMesin2_lain.setChecked(ceklists.first().getDb_var_cbMesin2_lain());
            cbMesin2_lain.setText(ceklists.first().getTdb_var_cbMesin2_lain());
            cbMesin2_lain.setVisibility(ceklists.first().getVdb_var_cbMesin2_lain());

            cbHidraulik1_lain.setChecked(ceklists.first().getDb_var_cbHidraulik1_lain());
            cbHidraulik1_lain.setText(ceklists.first().getTdb_var_cbHidraulik1_lain());
            cbHidraulik2_lain.setChecked(ceklists.first().getDb_var_cbHidraulik2_lain());
            cbHidraulik2_lain.setText(ceklists.first().getTdb_var_cbHidraulik2_lain());
            cbHidraulik2_lain.setVisibility(ceklists.first().getVdb_var_cbHidraulik2_lain());

            cbGardan1_lain.setChecked(ceklists.first().getDb_var_cbGardan1_lain());
            cbGardan1_lain.setText(ceklists.first().getTdb_var_cbGardan1_lain());
            cbGardan2_lain.setChecked(ceklists.first().getDb_var_cbGardan2_lain());
            cbGardan2_lain.setText(ceklists.first().getTdb_var_cbGardan2_lain());
            cbGardan2_lain.setVisibility(ceklists.first().getVdb_var_cbGardan2_lain());

            cbAs1_lain.setChecked(ceklists.first().getDb_var_cbAs1_lain());
            cbAs1_lain.setText(ceklists.first().getTdb_var_cbAs1_lain());
            cbAs2_lain.setChecked(ceklists.first().getDb_var_cbAs2_lain());
            cbAs2_lain.setText(ceklists.first().getTdb_var_cbAs2_lain());
            cbAs2_lain.setVisibility(ceklists.first().getVdb_var_cbAs2_lain());

            cbBak1_lain.setChecked(ceklists.first().getDb_var_cbBak1_lain());
            cbBak1_lain.setText(ceklists.first().getTdb_var_cbBak1_lain());
            cbBak2_lain.setChecked(ceklists.first().getDb_var_cbBak2_lain());
            cbBak2_lain.setText(ceklists.first().getTdb_var_cbBak2_lain());
            cbBak2_lain.setVisibility(ceklists.first().getVdb_var_cbBak2_lain());


            etSd_stnk.setText(ceklists.first().getDb_var_etStnk_lain());
            cbStnk1_lain.setChecked(ceklists.first().getDb_var_cbStnk1_lain());
            cbStnk1_lain.setText(ceklists.first().getTdb_var_cbStnk1_lain());
            cbStnk2_lain.setChecked(ceklists.first().getDb_var_cbStnk2_lain());
            cbStnk2_lain.setText(ceklists.first().getTdb_var_cbStnk2_lain());
            cbStnk2_lain.setVisibility(ceklists.first().getVdb_var_cbStnk2_lain());

            etSd_bukukir.setText(ceklists.first().getDb_var_etBukukir_lain());
            cbBukukir1_lain.setChecked(ceklists.first().getDb_var_cbBukukir1_lain());
            cbBukukir1_lain.setText(ceklists.first().getTdb_var_cbBukukir1_lain());
            cbBukukir2_lain.setChecked(ceklists.first().getDb_var_cbBukukir2_lain());
            cbBukukir2_lain.setText(ceklists.first().getTdb_var_cbBukukir2_lain());
            cbBukukir2_lain.setVisibility(ceklists.first().getVdb_var_cbBukukir2_lain());

            etSd_ijintrayek.setText(ceklists.first().getDb_var_etIjintrayek_lain());
            cbTrayek1_lain.setChecked(ceklists.first().getDb_var_cbTrayek1_lain());
            cbTrayek1_lain.setText(ceklists.first().getTdb_var_cbTrayek1_lain());
            cbTrayek2_lain.setChecked(ceklists.first().getDb_var_cbTrayek2_lain());
            cbTrayek2_lain.setText(ceklists.first().getTdb_var_cbTrayek2_lain());
            cbTrayek2_lain.setVisibility(ceklists.first().getVdb_var_cbTrayek2_lain());

            etSd_ijinusaha.setText(ceklists.first().getDb_var_etIjinusaha_lain());
            cbUsaha1_lain.setChecked(ceklists.first().getDb_var_cbUsaha1_lain());
            cbUsaha1_lain.setText(ceklists.first().getTdb_var_cbUsaha1_lain());
            cbUsaha2_lain.setChecked(ceklists.first().getDb_var_cbUsaha2_lain());
            cbUsaha2_lain.setText(ceklists.first().getTdb_var_cbUsaha2_lain());
            cbUsaha2_lain.setVisibility(ceklists.first().getVdb_var_cbUsaha2_lain());

            cbBukumnl1_lain.setChecked(ceklists.first().getDb_var_cbBukumnl1_lain());
            cbBukumnl1_lain.setText(ceklists.first().getTdb_var_cbBukumnl1_lain());
            cbBukumnl2_lain.setChecked(ceklists.first().getDb_var_cbBukumnl2_lain());
            cbBukumnl2_lain.setText(ceklists.first().getTdb_var_cbBukumnl2_lain());
            cbBukumnl2_lain.setVisibility(ceklists.first().getVdb_var_cbBukumnl2_lain());

        }


        myCalendar = Calendar.getInstance();
        etSd_stnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_stnk = new DatePickerDialog(DisInputCeklistActivity.this, datestnk, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_stnk.show();
            }
        });
        etSd_bukukir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_bukukir = new DatePickerDialog(DisInputCeklistActivity.this, datebukukir, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_bukukir.show();
            }
        });
        etSd_ijintrayek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_ijintrayek = new DatePickerDialog(DisInputCeklistActivity.this, dateijintrayek, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_ijintrayek.show();
            }
        });
        etSd_ijinusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_ijinusaha = new DatePickerDialog(DisInputCeklistActivity.this, dateijinusaha, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_ijinusaha.show();
            }
        });

        String output="";
        for(Ceklist ceklist:ceklists){
            output+=ceklist.toString();
        }
        Log.d("isi-ceklist",output);


        btnNextdispatcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String stnk;
//                String bukukir;
//                String ijintrayek;
//                String ijinusaha;

//                if (etSd_stnk.getText().toString().equals("")){
//                    stnk = "00-00-0000";
//                }else {
//                    stnk = etSd_stnk.getText().toString();
//                }

//                if (etSd_bukukir.getText().toString().equals("")){
//                    bukukir = "00-00-0000";
//                }else {
//                    bukukir = etSd_bukukir.getText().toString();
//                }
//
//                if (etSd_ijintrayek.getText().toString().equals("")){
//                    ijintrayek = "00-00-0000";
//                }else {
//                    ijintrayek = etSd_ijintrayek.getText().toString();
//                }

//                if (etSd_ijinusaha.getText().toString().equals("")){
//                    ijinusaha = "00-00-0000";
//                }else {
//                    ijinusaha = etSd_ijinusaha.getText().toString();
//                }

                realm.beginTransaction();
                RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                upd.load();
                if (!etSd_stnk.getText().toString().equals("")){
                    upd.first().setDb_var_etStnk_lain(etSd_stnk.getText().toString());
                }
                if (!etSd_bukukir.getText().toString().equals("")){
                    upd.first().setDb_var_etBukukir_lain(etSd_bukukir.getText().toString());
                }
                if (!etSd_ijintrayek.getText().toString().equals("")){
                    upd.first().setDb_var_etIjintrayek_lain(etSd_ijintrayek.getText().toString());
                }
                if (!etSd_ijinusaha.getText().toString().equals("")){
                    upd.first().setDb_var_etIjinusaha_lain(etSd_ijinusaha.getText().toString());
                }

                realm.commitTransaction();

//                if (rbBanradial1_fisikkiri.isChecked() == false && rbBanstandard1_fisikkiri.isChecked() == false){
//                    Toast.makeText(DisInputCeklistActivity.this,"Please select Ban Fisik Kiri",Toast.LENGTH_SHORT).show();
//                }else if (rbVelgracing_fisikkiri.isChecked() == false && rbVelgstandard_fisikkiri.isChecked() == false){
//                    Toast.makeText(DisInputCeklistActivity.this,"Please select Velg Fisik Kiri",Toast.LENGTH_SHORT).show();
//                }else if (rbBanstandard_fisikkanan.isChecked() == false && rbBanradial_fisikkanan.isChecked() == false){
//                    Toast.makeText(DisInputCeklistActivity.this,"Please select Ban Fisik Kanan",Toast.LENGTH_SHORT).show();
//                }else if (rbVelgracing_fisikkanan.isChecked() == false && rbVelgstandard_fisikkanan.isChecked() == false){
//                    Toast.makeText(DisInputCeklistActivity.this,"Please select Velg Fisik Kanan",Toast.LENGTH_SHORT).show();
//                }else if (rbRadio_listrik.isChecked() == false && rbTape_listrik.isChecked() == false && rbCd_listrik.isChecked() == false){
//                    Toast.makeText(DisInputCeklistActivity.this,"Please select Radio/Tape/CD Listrik",Toast.LENGTH_SHORT).show();
//                }else {
//                    sendCeklist();
//                }
                sendCeklist();
            }
        });



        cbGrill1_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbGrill1_fisikmuka.isChecked()){
                    cbGrill1_fisikmuka.setText("Ada");
                    cbGrill2_fisikmuka.setVisibility(View.VISIBLE);
                    cbGrill2_fisikmuka.setText("Rusak");
                    cbGrill2_fisikmuka.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbGrill1_fisikmuka(true);
                    upd.first().setTdb_var_cbGrill1_fisikmuka("Ada");
                    upd.first().setVdb_var_cbGrill2_fisikmuka(View.VISIBLE);
                    upd.first().setTdb_var_cbGrill2_fisikmuka("Rusak");
                    realm.commitTransaction();

                }else {
                    cbGrill2_fisikmuka.setVisibility(View.INVISIBLE);
                    cbGrill1_fisikmuka.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbGrill1_fisikmuka(false);
                    upd.first().setTdb_var_cbGrill1_fisikmuka("Tidak Ada");
                    upd.first().setDb_var_cbGrill2_fisikmuka(false);
                    upd.first().setTdb_var_cbGrill2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbGrill2_fisikmuka(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbGrill2_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbGrill2_fisikmuka.isChecked()){
                    cbGrill2_fisikmuka.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbGrill2_fisikmuka(true);
                    upd.first().setTdb_var_cbGrill2_fisikmuka("Baik");
                    upd.first().setVdb_var_cbGrill2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbGrill2_fisikmuka.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbGrill2_fisikmuka(false);
                    upd.first().setTdb_var_cbGrill2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbGrill2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });

        cbLampu1_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampu1_fisikmuka.isChecked()){
                    cbLampu1_fisikmuka.setText("Ada");
                    cbLampu2_fisikmuka.setVisibility(View.VISIBLE);
                    cbLampu2_fisikmuka.setText("Rusak");
                    cbLampu2_fisikmuka.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu1_fisikmuka(true);
                    upd.first().setTdb_var_cbLampu1_fisikmuka("Ada");
//                    upd.first().setDb_var_cbLampu2_fisikmuka(false);
                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.VISIBLE);
                    upd.first().setTdb_var_cbLampu2_fisikmuka("Rusak");
                    realm.commitTransaction();

                }else {
                    cbLampu2_fisikmuka.setVisibility(View.INVISIBLE);
                    cbLampu1_fisikmuka.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu1_fisikmuka(false);
                    upd.first().setTdb_var_cbLampu1_fisikmuka("Tidak Ada");
//                    upd.first().setDb_var_cbLampu2_fisikmuka(false);
                    upd.first().setTdb_var_cbLampu2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbLampu2_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampu2_fisikmuka.isChecked()){
                    cbLampu2_fisikmuka.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu2_fisikmuka(true);
                    upd.first().setTdb_var_cbLampu2_fisikmuka("Baik");
                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbLampu2_fisikmuka.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu2_fisikmuka(false);
                    upd.first().setTdb_var_cbLampu2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });

        cbLampusen1_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampusen1_fisikmuka.isChecked()){
                    cbLampusen1_fisikmuka.setText("Ada");
                    cbLampusen2_fisikmuka.setVisibility(View.VISIBLE);
                    cbLampusen2_fisikmuka.setText("Rusak");
                    cbLampusen2_fisikmuka.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampusen1_fisikmuka(true);
                    upd.first().setTdb_var_cbLampusen1_fisikmuka("Ada");
                    upd.first().setDb_var_cbLampusen2_fisikmuka(false);
                    upd.first().setTdb_var_cbLampusen2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbLampusen2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbLampusen2_fisikmuka.setVisibility(View.INVISIBLE);
                    cbLampusen1_fisikmuka.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampusen1_fisikmuka(false);
                    upd.first().setTdb_var_cbLampusen1_fisikmuka("Tidak Ada");
                    upd.first().setVdb_var_cbLampusen2_fisikmuka(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbLampusen2_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampusen2_fisikmuka.isChecked()){
                    cbLampusen2_fisikmuka.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampusen2_fisikmuka(true);
                    upd.first().setTdb_var_cbLampusen2_fisikmuka("Baik");
                    upd.first().setVdb_var_cbLampusen2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbLampusen2_fisikmuka.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampusen2_fisikmuka(false);
                    upd.first().setTdb_var_cbLampusen2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbLampusen2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbBamper1_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper1_fisikmuka.isChecked()){
                    cbBamper1_fisikmuka.setText("Ada");
                    cbBamper2_fisikmuka.setVisibility(View.VISIBLE);
                    cbBamper2_fisikmuka.setText("Rusak");
                    cbBamper2_fisikmuka.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikmuka(true);
                    upd.first().setTdb_var_cbBamper1_fisikmuka("Ada");
                    upd.first().setDb_var_cbBamper2_fisikmuka(false);
                    upd.first().setTdb_var_cbBamper2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikmuka.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikmuka.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikmuka(false);
                    upd.first().setTdb_var_cbBamper1_fisikmuka("Tidak Ada");
                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbBamper2_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper2_fisikmuka.isChecked()){
                    cbBamper2_fisikmuka.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikmuka(true);
                    upd.first().setTdb_var_cbBamper2_fisikmuka("Baik");
                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikmuka.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikmuka(false);
                    upd.first().setTdb_var_cbBamper2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });

        cbEmblem1_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem1_fisikmuka.isChecked()){
                    cbEmblem1_fisikmuka.setText("Ada");
                    cbEmblem2_fisikmuka.setVisibility(View.VISIBLE);
                    cbEmblem2_fisikmuka.setText("Rusak");
                    cbEmblem2_fisikmuka.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem1_fisikmuka(true);
                    upd.first().setTdb_var_cbEmblem1_fisikmuka("Ada");
                    upd.first().setDb_var_cbEmblem2_fisikmuka(false);
                    upd.first().setTdb_var_cbEmblem2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbEmblem2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikmuka.setVisibility(View.INVISIBLE);
                    cbEmblem1_fisikmuka.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem1_fisikmuka(false);
                    upd.first().setTdb_var_cbEmblem1_fisikmuka("Tidak Ada");
                    upd.first().setVdb_var_cbEmblem2_fisikmuka(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbEmblem2_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem2_fisikmuka.isChecked()){
                    cbEmblem2_fisikmuka.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem2_fisikmuka(true);
                    upd.first().setTdb_var_cbEmblem2_fisikmuka("Baik");
                    upd.first().setVdb_var_cbEmblem2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikmuka.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem2_fisikmuka(false);
                    upd.first().setTdb_var_cbEmblem2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbEmblem2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbTanduk1_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTanduk1_fisikmuka.isChecked()){
                    cbTanduk1_fisikmuka.setText("Ada");
                    cbTanduk2_fisikmuka.setVisibility(View.VISIBLE);
                    cbTanduk2_fisikmuka.setText("Rusak");
                    cbTanduk2_fisikmuka.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTanduk1_fisikmuka(true);
                    upd.first().setTdb_var_cbTanduk1_fisikmuka("Ada");
                    upd.first().setDb_var_cbTanduk2_fisikmuka(false);
                    upd.first().setTdb_var_cbTanduk2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbTanduk2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbTanduk2_fisikmuka.setVisibility(View.INVISIBLE);
                    cbTanduk1_fisikmuka.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTanduk1_fisikmuka(false);
                    upd.first().setTdb_var_cbTanduk1_fisikmuka("Tidak Ada");
                    upd.first().setVdb_var_cbTanduk2_fisikmuka(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbTanduk2_fisikmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTanduk2_fisikmuka.isChecked()){
                    cbTanduk2_fisikmuka.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTanduk2_fisikmuka(true);
                    upd.first().setTdb_var_cbTanduk2_fisikmuka("Baik");
                    upd.first().setVdb_var_cbTanduk2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbTanduk2_fisikmuka.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTanduk2_fisikmuka(false);
                    upd.first().setTdb_var_cbTanduk2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbTanduk2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
//=========================================================================================

        cbFootstep1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFootstep1_fisikkiri.isChecked()){
                    cbFootstep1_fisikkiri.setText("Ada");
                    cbFootstep2_fisikkiri.setVisibility(View.VISIBLE);
                    cbFootstep2_fisikkiri.setText("Rusak");
                    cbFootstep2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFootstep1_fisikkiri(true);
                    upd.first().setTdb_var_cbFootstep1_fisikkiri("Ada");
                    upd.first().setDb_var_cbFootstep2_fisikkiri(false);
                    upd.first().setTdb_var_cbFootstep2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbFootstep2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbFootstep1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFootstep1_fisikkiri(false);
                    upd.first().setTdb_var_cbFootstep1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbFootstep2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFootstep2_fisikkiri.isChecked()){
                    cbFootstep2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFootstep2_fisikkiri(true);
                    upd.first().setTdb_var_cbFootstep2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbFootstep2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFootstep2_fisikkiri(false);
                    upd.first().setTdb_var_cbFootstep2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbPintudpn1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintudpn1_fisikkiri.isChecked()){
                    cbPintudpn1_fisikkiri.setText("Ada");
                    cbPintudpn2_fisikkiri.setVisibility(View.VISIBLE);
                    cbPintudpn2_fisikkiri.setText("Rusak");
                    cbPintudpn2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintudpn1_fisikkiri(true);
                    upd.first().setTdb_var_cbPintudpn1_fisikkiri("Ada");
                    upd.first().setDb_var_cbPintudpn2_fisikkiri(false);
                    upd.first().setTdb_var_cbPintudpn2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbPintudpn2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbFootstep2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbFootstep1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFootstep1_fisikkiri(false);
                    upd.first().setTdb_var_cbFootstep1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPintudpn2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintudpn2_fisikkiri.isChecked()){
                    cbPintudpn2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintudpn2_fisikkiri(true);
                    upd.first().setTdb_var_cbPintudpn2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbPintudpn2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPintudpn2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintudpn2_fisikkiri(false);
                    upd.first().setTdb_var_cbPintudpn2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbPintudpn2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbPintublk1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintublk1_fisikkiri.isChecked()){
                    cbPintublk1_fisikkiri.setText("Ada");
                    cbPintublk2_fisikkiri.setVisibility(View.VISIBLE);
                    cbPintublk2_fisikkiri.setText("Rusak");
                    cbPintublk2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintublk1_fisikkiri(true);
                    upd.first().setTdb_var_cbPintublk1_fisikkiri("Ada");
                    upd.first().setDb_var_cbPintublk2_fisikkiri(false);
                    upd.first().setTdb_var_cbPintublk2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbPintublk2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPintublk2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbPintublk1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintublk1_fisikkiri(false);
                    upd.first().setTdb_var_cbPintublk1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbPintublk2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPintublk2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintublk2_fisikkiri.isChecked()){
                    cbPintublk2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintublk2_fisikkiri(true);
                    upd.first().setTdb_var_cbPintublk2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbPintublk2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPintublk2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintublk2_fisikkiri(false);
                    upd.first().setTdb_var_cbPintublk2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbPintublk2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbBamper1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper1_fisikkiri.isChecked()){
                    cbBamper1_fisikkiri.setText("Ada");
                    cbBamper2_fisikkiri.setVisibility(View.VISIBLE);
                    cbBamper2_fisikkiri.setText("Rusak");
                    cbBamper2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikkiri(true);
                    upd.first().setTdb_var_cbBamper1_fisikkiri("Ada");
                    upd.first().setDb_var_cbBamper2_fisikkiri(false);
                    upd.first().setTdb_var_cbBamper2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbBamper2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikkiri(false);
                    upd.first().setTdb_var_cbBamper1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbBamper2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper2_fisikkiri.isChecked()){
                    cbBamper2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikkiri(true);
                    upd.first().setTdb_var_cbBamper2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbBamper2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikkiri(false);
                    upd.first().setTdb_var_cbBamper2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbFenderblk1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFenderblk1_fisikkiri.isChecked()){
                    cbFenderblk1_fisikkiri.setText("Ada");
                    cbFenderblk2_fisikkiri.setVisibility(View.VISIBLE);
                    cbFenderblk2_fisikkiri.setText("Rusak");
                    cbFenderblk2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFenderblk1_fisikkiri(true);
                    upd.first().setTdb_var_cbFenderblk1_fisikkiri("Ada");
                    upd.first().setDb_var_cbFenderblk2_fisikkiri(false);
                    upd.first().setTdb_var_cbFenderblk2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbFenderblk2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbBamper2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikkiri(false);
                    upd.first().setTdb_var_cbBamper1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbFenderblk2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFenderblk2_fisikkiri.isChecked()){
                    cbFenderblk2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFenderblk2_fisikkiri(true);
                    upd.first().setTdb_var_cbFenderblk2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbFenderblk2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbFenderblk2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFenderblk2_fisikkiri(false);
                    upd.first().setTdb_var_cbFenderblk2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbFenderblk2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbSpion1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion1_fisikkiri.isChecked()){
                    cbSpion1_fisikkiri.setText("Ada");
                    cbSpion2_fisikkiri.setVisibility(View.VISIBLE);
                    cbSpion2_fisikkiri.setText("Rusak");
                    cbSpion2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion1_fisikkiri(true);
                    upd.first().setTdb_var_cbSpion1_fisikkiri("Ada");
                    upd.first().setDb_var_cbSpion2_fisikkiri(false);
                    upd.first().setTdb_var_cbSpion2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbSpion2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikkiri(false);
                    upd.first().setTdb_var_cbBamper1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbSpion2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion2_fisikkiri.isChecked()){
                    cbSpion2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion2_fisikkiri(true);
                    upd.first().setTdb_var_cbSpion2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbSpion2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbSpion2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion2_fisikkiri(false);
                    upd.first().setTdb_var_cbSpion2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbSpion2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });

        cbEmblem1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem1_fisikkiri.isChecked()){
                    cbEmblem1_fisikkiri.setText("Ada");
                    cbEmblem2_fisikkiri.setVisibility(View.VISIBLE);
                    cbEmblem2_fisikkiri.setText("Rusak");
                    cbEmblem2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem1_fisikkiri(true);
                    upd.first().setTdb_var_cbEmblem1_fisikkiri("Ada");
                    upd.first().setDb_var_cbEmblem2_fisikkiri(false);
                    upd.first().setTdb_var_cbEmblem2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbEmblem1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem1_fisikkiri(false);
                    upd.first().setTdb_var_cbEmblem1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbEmblem2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem2_fisikkiri.isChecked()){
                    cbEmblem2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem2_fisikkiri(true);
                    upd.first().setTdb_var_cbEmblem2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem2_fisikkiri(false);
                    upd.first().setTdb_var_cbEmblem2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbBan1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBan1_fisikkiri.isChecked()){
                    cbBan1_fisikkiri.setText("Ada");
                    spBan2_fisikkiri.setVisibility(View.VISIBLE);
//                    cbEmblem2_fisikkiri.setVisibility(View.VISIBLE);
//                    cbEmblem2_fisikkiri.setText("Rusak");
//                    cbEmblem2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBan1_fisikkiri(true);
                    upd.first().setTdb_var_cbBan1_fisikkiri("Ada");
                    upd.first().setDb_var_spBan2_fisikkiri(spBan2_fisikkiri.getSelectedItemPosition());
                    upd.first().setVdb_var_spBan2_fisikkiri(View.VISIBLE);
//                    upd.first().setDb_var_cbEmblem2_fisikkiri(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();


                }else {
//                    cbEmblem2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbBan1_fisikkiri.setText("Tidak Ada");
                    spBan2_fisikkiri.setVisibility(View.INVISIBLE);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBan1_fisikkiri(false);
                    upd.first().setTdb_var_cbBan1_fisikkiri("Tidak Ada");
                    upd.first().setDb_var_spBan2_fisikkiri(0);
                    upd.first().setVdb_var_spBan2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        rbBanstandard1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbBanstandard1_fisikkiri.isChecked()){
                    rbBanradial1_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbBanstandard_fisikkiri(true);
                    upd.first().setDb_var_rbBanradial_fisikkiri(false);
                    realm.commitTransaction();
                }
            }
        });
        rbBanradial1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbBanradial1_fisikkiri.isChecked()){
                    rbBanstandard1_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbBanradial_fisikkiri(true);
                    upd.first().setDb_var_rbBanstandard_fisikkiri(false);
                    realm.commitTransaction();
                }
            }
        });

        rbVelgstandard_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVelgstandard_fisikkiri.isChecked()){
                    rbVelgracing_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbVelgstandard_fisikkiri(true);
                    upd.first().setDb_var_rbVelgracing_fisikkiri(false);
                    realm.commitTransaction();
                }
            }
        });
        rbVelgracing_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVelgracing_fisikkiri.isChecked()){
                    rbVelgstandard_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbVelgracing_fisikkiri(true);
                    upd.first().setDb_var_rbVelgstandard_fisikkiri(false);
                    realm.commitTransaction();
                }
            }
        });

        cbVelg1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVelg1_fisikkiri.isChecked()){
                    cbVelg1_fisikkiri.setText("Ada");
                    cbVelg2_fisikkiri.setVisibility(View.VISIBLE);
                    cbVelg2_fisikkiri.setText("Rusak");
                    cbVelg2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbVelg1_fisikkiri(true);
                    upd.first().setTdb_var_cbVelg1_fisikkiri("Ada");
                    upd.first().setDb_var_cbVelg2_fisikkiri(false);
                    upd.first().setTdb_var_cbVelg2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbVelg2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();
                }else {
                    cbVelg2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbVelg1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbVelg1_fisikkiri(false);
                    upd.first().setTdb_var_cbVelg1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbVelg2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbVelg2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVelg2_fisikkiri.isChecked()){
                    cbVelg2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbVelg2_fisikkiri(true);
                    upd.first().setTdb_var_cbVelg2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbVelg2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbVelg2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbVelg2_fisikkiri(false);
                    upd.first().setTdb_var_cbVelg2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbVelg2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });

        cbDop1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDop1_fisikkiri.isChecked()){
                    cbDop1_fisikkiri.setText("Ada");
                    cbDop2_fisikkiri.setVisibility(View.VISIBLE);
                    cbDop2_fisikkiri.setText("Rusak");
                    cbDop2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDop1_fisikkiri(true);
                    upd.first().setTdb_var_cbDop1_fisikkiri("Ada");
                    upd.first().setDb_var_cbDop2_fisikkiri(false);
                    upd.first().setTdb_var_cbDop2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbDop2_fisikkiri(View.VISIBLE);

                    realm.commitTransaction();


                }else {
                    cbDop2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbDop1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDop1_fisikkiri(false);
                    upd.first().setTdb_var_cbDop1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbDop2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbDop2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDop2_fisikkiri.isChecked()){
                    cbDop2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDop2_fisikkiri(true);
                    upd.first().setTdb_var_cbDop2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbDop2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbDop2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDop2_fisikkiri(false);
                    upd.first().setTdb_var_cbDop2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbDop2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });

        cbDopBlk1_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDopBlk1_fisikkiri.isChecked()){
                    cbDopBlk1_fisikkiri.setText("Ada");
                    cbDopBlk2_fisikkiri.setVisibility(View.VISIBLE);
                    cbDopBlk2_fisikkiri.setText("Rusak");
                    cbDopBlk2_fisikkiri.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDopBlk1_fisikkiri(true);
                    upd.first().setTdb_var_cbDopBlk1_fisikkiri("Ada");
                    upd.first().setDb_var_cbDopBlk2_fisikkiri(false);
                    upd.first().setTdb_var_cbDopBlk2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbDopBlk2_fisikkiri(View.VISIBLE);

                    realm.commitTransaction();


                }else {
                    cbDopBlk2_fisikkiri.setVisibility(View.INVISIBLE);
                    cbDopBlk1_fisikkiri.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDopBlk1_fisikkiri(false);
                    upd.first().setTdb_var_cbDopBlk1_fisikkiri("Tidak Ada");
                    upd.first().setVdb_var_cbDopBlk2_fisikkiri(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbDopBlk2_fisikkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDopBlk2_fisikkiri.isChecked()){
                    cbDopBlk2_fisikkiri.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDopBlk2_fisikkiri(true);
                    upd.first().setTdb_var_cbDopBlk2_fisikkiri("Baik");
                    upd.first().setVdb_var_cbDopBlk2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbDopBlk2_fisikkiri.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDopBlk2_fisikkiri(false);
                    upd.first().setTdb_var_cbDopBlk2_fisikkiri("Rusak");
                    upd.first().setVdb_var_cbDopBlk2_fisikkiri(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
//=====================================================================================

        cbSpoiler1_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpoiler1_fisikblkg.isChecked()){
                    cbSpoiler1_fisikblkg.setText("Ada");
                    cbSpoiler2_fisikblkg.setVisibility(View.VISIBLE);
                    cbSpoiler2_fisikblkg.setText("Rusak");
                    cbSpoiler2_fisikblkg.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpoiler1_fisikblkg(true);
                    upd.first().setTdb_var_cbSpoiler1_fisikblkg("Ada");
                    upd.first().setDb_var_cbSpoiler2_fisikblkg(false);
                    upd.first().setTdb_var_cbSpoiler2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbSpoiler2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSpoiler2_fisikblkg.setVisibility(View.INVISIBLE);
                    cbSpoiler1_fisikblkg.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpoiler1_fisikblkg(false);
                    upd.first().setTdb_var_cbSpoiler1_fisikblkg("Tidak Ada");
                    upd.first().setVdb_var_cbSpoiler2_fisikblkg(View.INVISIBLE);
                    realm.commitTransaction();

                }

            }
        });
        cbSpoiler2_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpoiler2_fisikblkg.isChecked()){
                    cbSpoiler2_fisikblkg.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpoiler2_fisikblkg(true);
                    upd.first().setTdb_var_cbSpoiler2_fisikblkg("Baik");
                    upd.first().setVdb_var_cbSpoiler2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSpoiler2_fisikblkg.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpoiler2_fisikblkg(false);
                    upd.first().setTdb_var_cbSpoiler2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbSpoiler2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbLampu1_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampu1_fisikblkg.isChecked()){
                    cbLampu1_fisikblkg.setText("Ada");
                    cbLampu2_fisikblkg.setVisibility(View.VISIBLE);
                    cbLampu2_fisikblkg.setText("Rusak");
                    cbLampu2_fisikblkg.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu1_fisikblkg(true);
                    upd.first().setTdb_var_cbLampu1_fisikblkg("Ada");
                    upd.first().setDb_var_cbLampu2_fisikblkg(false);
                    upd.first().setTdb_var_cbLampu2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbLampu2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbLampu2_fisikblkg.setVisibility(View.INVISIBLE);
                    cbLampu1_fisikblkg.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu1_fisikblkg(false);
                    upd.first().setTdb_var_cbLampu1_fisikblkg("Tidak Ada");
                    upd.first().setVdb_var_cbLampu2_fisikblkg(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbLampu2_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampu2_fisikblkg.isChecked()){
                    cbLampu2_fisikblkg.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu2_fisikblkg(true);
                    upd.first().setTdb_var_cbLampu2_fisikblkg("Baik");
                    upd.first().setVdb_var_cbLampu2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbLampu2_fisikblkg.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu2_fisikblkg(false);
                    upd.first().setTdb_var_cbLampu2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbLampu2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });

        cbLampusen1_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampusen1_fisikblkg.isChecked()){
                    cbLampusen1_fisikblkg.setText("Ada");
                    cbLampusen2_fisikblkg.setVisibility(View.VISIBLE);
                    cbLampusen2_fisikblkg.setText("Rusak");
                    cbLampusen2_fisikblkg.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampusen1_fisikblkg(true);
                    upd.first().setTdb_var_cbLampusen1_fisikblkg("Ada");
                    upd.first().setDb_var_cbLampusen2_fisikblkg(false);
                    upd.first().setTdb_var_cbLampusen2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbLampusen2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbLampusen2_fisikblkg.setVisibility(View.INVISIBLE);
                    cbLampusen1_fisikblkg.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampusen1_fisikblkg(false);
                    upd.first().setTdb_var_cbLampusen1_fisikblkg("Tidak Ada");
                    upd.first().setVdb_var_cbLampusen2_fisikblkg(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbLampusen2_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampusen2_fisikblkg.isChecked()){
                    cbLampusen2_fisikblkg.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampusen2_fisikblkg(true);
                    upd.first().setTdb_var_cbLampusen2_fisikblkg("Baik");
                    upd.first().setVdb_var_cbLampusen2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbLampusen2_fisikblkg.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampusen2_fisikblkg(false);
                    upd.first().setTdb_var_cbLampusen2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbLampusen2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbBamper1_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper1_fisikblkg.isChecked()){
                    cbBamper1_fisikblkg.setText("Ada");
                    cbBamper2_fisikblkg.setVisibility(View.VISIBLE);
                    cbBamper2_fisikblkg.setText("Rusak");
                    cbBamper2_fisikblkg.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikblkg(true);
                    upd.first().setTdb_var_cbBamper1_fisikblkg("Ada");
                    upd.first().setDb_var_cbBamper2_fisikblkg(false);
                    upd.first().setTdb_var_cbBamper2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbBamper2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikblkg.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikblkg.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikblkg(false);
                    upd.first().setTdb_var_cbBamper1_fisikblkg("Tidak Ada");
                    upd.first().setVdb_var_cbBamper2_fisikblkg(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbBamper2_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper2_fisikblkg.isChecked()){
                    cbBamper2_fisikblkg.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikblkg(true);
                    upd.first().setTdb_var_cbBamper2_fisikblkg("Baik");
                    upd.first().setVdb_var_cbBamper2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbBamper2_fisikblkg.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikblkg(false);
                    upd.first().setTdb_var_cbBamper2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbBamper2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbEmblem1_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem1_fisikblkg.isChecked()){
                    cbEmblem1_fisikblkg.setText("Ada");
                    cbEmblem2_fisikblkg.setVisibility(View.VISIBLE);
                    cbEmblem2_fisikblkg.setText("Rusak");
                    cbEmblem2_fisikblkg.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem1_fisikblkg(true);
                    upd.first().setTdb_var_cbEmblem1_fisikblkg("Ada");
                    upd.first().setDb_var_cbEmblem2_fisikblkg(false);
                    upd.first().setTdb_var_cbEmblem2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbEmblem2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikblkg.setVisibility(View.INVISIBLE);
                    cbEmblem1_fisikblkg.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem1_fisikblkg(false);
                    upd.first().setTdb_var_cbEmblem1_fisikblkg("Tidak Ada");
                    upd.first().setVdb_var_cbEmblem2_fisikblkg(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbEmblem2_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem2_fisikblkg.isChecked()){
                    cbEmblem2_fisikblkg.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem2_fisikblkg(true);
                    upd.first().setTdb_var_cbEmblem2_fisikblkg("Baik");
                    upd.first().setVdb_var_cbEmblem2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbEmblem2_fisikblkg.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem2_fisikblkg(false);
                    upd.first().setTdb_var_cbEmblem2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbEmblem2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });

        cbKnalpot1_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKnalpot1_fisikblkg.isChecked()){
                    cbKnalpot1_fisikblkg.setText("Ada");
                    cbKnalpot2_fisikblkg.setVisibility(View.VISIBLE);
                    cbKnalpot2_fisikblkg.setText("Rusak");
                    cbKnalpot2_fisikblkg.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKnalpot1_fisikblkg(true);
                    upd.first().setTdb_var_cbKnalpot1_fisikblkg("Ada");
                    upd.first().setDb_var_cbKnalpot2_fisikblkg(false);
                    upd.first().setTdb_var_cbKnalpot2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbKnalpot2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbKnalpot2_fisikblkg.setVisibility(View.INVISIBLE);
                    cbKnalpot1_fisikblkg.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKnalpot1_fisikblkg(false);
                    upd.first().setTdb_var_cbKnalpot1_fisikblkg("Tidak Ada");
                    upd.first().setVdb_var_cbKnalpot2_fisikblkg(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbKnalpot2_fisikblkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKnalpot2_fisikblkg.isChecked()){
                    cbKnalpot2_fisikblkg.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKnalpot2_fisikblkg(true);
                    upd.first().setTdb_var_cbKnalpot2_fisikblkg("Baik");
                    upd.first().setVdb_var_cbKnalpot2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbKnalpot2_fisikblkg.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKnalpot2_fisikblkg(false);
                    upd.first().setTdb_var_cbKnalpot2_fisikblkg("Rusak");
                    upd.first().setVdb_var_cbKnalpot2_fisikblkg(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        //=======================================================================

        cbFoot1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFoot1_fisikkanan.isChecked()){
                    cbFoot1_fisikkanan.setText("Ada");
                    cbFoot2_fisikkanan.setVisibility(View.VISIBLE);
                    cbFoot2_fisikkanan.setText("Rusak");
                    cbFoot2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFoot1_fisikkanan(true);
                    upd.first().setTdb_var_cbFoot1_fisikkanan("Ada");
                    upd.first().setDb_var_cbFoot2_fisikkanan(false);
                    upd.first().setTdb_var_cbFoot2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbFoot2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbFoot2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbFoot1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFoot1_fisikkanan(false);
                    upd.first().setTdb_var_cbFoot1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbFoot2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }

            }
        });
        cbFoot2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFoot2_fisikkanan.isChecked()){
                    cbFoot2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFoot2_fisikkanan(true);
                    upd.first().setTdb_var_cbFoot2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbFoot2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbFoot2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFoot2_fisikkanan(false);
                    upd.first().setTdb_var_cbFoot2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbFoot2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPintudpn1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintudpn1_fisikkanan.isChecked()){
                    cbPintudpn1_fisikkanan.setText("Ada");
                    cbPintudpn2_fisikkanan.setVisibility(View.VISIBLE);
                    cbPintudpn2_fisikkanan.setText("Rusak");
                    cbPintudpn2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintudpn1_fisikkanan(true);
                    upd.first().setTdb_var_cbPintudpn1_fisikkanan("Ada");
                    upd.first().setDb_var_cbPintudpn2_fisikkanan(false);
                    upd.first().setTdb_var_cbPintudpn2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbPintudpn2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPintudpn2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbPintudpn1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintudpn1_fisikkanan(false);
                    upd.first().setTdb_var_cbPintudpn1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbPintudpn2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPintudpn2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintudpn2_fisikkanan.isChecked()){
                    cbPintudpn2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintudpn2_fisikkanan(true);
                    upd.first().setTdb_var_cbPintudpn2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbPintudpn2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbPintudpn2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintudpn2_fisikkanan(false);
                    upd.first().setTdb_var_cbPintudpn2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbPintudpn2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPintublk1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintublk1_fisikkanan.isChecked()){
                    cbPintublk1_fisikkanan.setText("Ada");
                    cbPintublk2_fisikkanan.setVisibility(View.VISIBLE);
                    cbPintublk2_fisikkanan.setText("Rusak");
                    cbPintublk2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintublk1_fisikkanan(true);
                    upd.first().setTdb_var_cbPintublk1_fisikkanan("Ada");
                    upd.first().setDb_var_cbPintublk2_fisikkanan(false);
                    upd.first().setTdb_var_cbPintublk2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbPintublk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPintublk2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbPintublk1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintublk1_fisikkanan(false);
                    upd.first().setTdb_var_cbPintublk1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbPintublk2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPintublk2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintublk2_fisikkanan.isChecked()){
                    cbPintublk2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintublk2_fisikkanan(true);
                    upd.first().setTdb_var_cbPintublk2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbPintublk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPintublk2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPintublk2_fisikkanan(false);
                    upd.first().setTdb_var_cbPintublk2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbPintublk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbBamper1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper1_fisikkanan.isChecked()){
                    cbBamper1_fisikkanan.setText("Ada");
                    cbBamper2_fisikkanan.setVisibility(View.VISIBLE);
                    cbBamper2_fisikkanan.setText("Rusak");
                    cbBamper2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikkanan(true);
                    upd.first().setTdb_var_cbBamper1_fisikkanan("Ada");
                    upd.first().setDb_var_cbBamper2_fisikkanan(false);
                    upd.first().setTdb_var_cbBamper2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbBamper2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper1_fisikkanan(false);
                    upd.first().setTdb_var_cbBamper1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbBamper2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbBamper2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper2_fisikkanan.isChecked()){
                    cbBamper2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikkanan(true);
                    upd.first().setTdb_var_cbBamper2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbBamper2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikkanan(false);
                    upd.first().setTdb_var_cbBamper2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbBamper2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbFenderblk1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFenderblk1_fisikkanan.isChecked()){
                    cbFenderblk1_fisikkanan.setText("Ada");
                    cbFenderblk2_fisikkanan.setVisibility(View.VISIBLE);
                    cbFenderblk2_fisikkanan.setText("Rusak");
                    cbFenderblk2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFenderblk1_fisikkanan(true);
                    upd.first().setTdb_var_cbFenderblk1_fisikkanan("Ada");
                    upd.first().setDb_var_cbFenderblk2_fisikkanan(false);
                    upd.first().setTdb_var_cbFenderblk2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbFenderblk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbFenderblk2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbFenderblk1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFenderblk1_fisikkanan(false);
                    upd.first().setTdb_var_cbFenderblk1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbFenderblk2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbFenderblk2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFenderblk2_fisikkanan.isChecked()){
                    cbFenderblk2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFenderblk2_fisikkanan(true);
                    upd.first().setTdb_var_cbFenderblk2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbFenderblk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbFenderblk2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbFenderblk2_fisikkanan(false);
                    upd.first().setTdb_var_cbFenderblk2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbFenderblk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSpion1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion1_fisikkanan.isChecked()){
                    cbSpion1_fisikkanan.setText("Ada");
                    cbSpion2_fisikkanan.setVisibility(View.VISIBLE);
                    cbSpion2_fisikkanan.setText("Rusak");
                    cbSpion2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion1_fisikkanan(true);
                    upd.first().setTdb_var_cbSpion1_fisikkanan("Ada");
                    upd.first().setDb_var_cbSpion2_fisikkanan(false);
                    upd.first().setTdb_var_cbSpion2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbSpion2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbSpion2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbSpion1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion1_fisikkanan(false);
                    upd.first().setTdb_var_cbSpion1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbSpion2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSpion2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion2_fisikkanan.isChecked()){
                    cbSpion2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion2_fisikkanan(true);
                    upd.first().setTdb_var_cbSpion2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbSpion2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSpion2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion2_fisikkanan(false);
                    upd.first().setTdb_var_cbSpion2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbSpion2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbEmblem1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem1_fisikkanan.isChecked()){
                    cbEmblem1_fisikkanan.setText("Ada");
                    cbEmblem2_fisikkanan.setVisibility(View.VISIBLE);
                    cbEmblem2_fisikkanan.setText("Rusak");
                    cbEmblem2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem1_fisikkanan(true);
                    upd.first().setTdb_var_cbEmblem1_fisikkanan("Ada");
                    upd.first().setDb_var_cbEmblem2_fisikkanan(false);
                    upd.first().setTdb_var_cbEmblem2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbEmblem2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbEmblem1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem1_fisikkanan(false);
                    upd.first().setTdb_var_cbEmblem1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbEmblem2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbEmblem2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem2_fisikkanan.isChecked()){
                    cbEmblem2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem2_fisikkanan(true);
                    upd.first().setTdb_var_cbEmblem2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbEmblem2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbEmblem2_fisikkanan(false);
                    upd.first().setTdb_var_cbEmblem2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbEmblem2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        rbBanstandard_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbBanstandard_fisikkanan.isChecked()){
                    rbBanradial_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbBanstandard_fisikkanan(true);
                    upd.first().setDb_var_rbBanradial_fisikkanan(false);
                    realm.commitTransaction();
                }
            }
        });
        rbBanradial_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbBanradial_fisikkanan.isChecked()){
                    rbBanstandard_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbBanradial_fisikkanan(true);
                    upd.first().setDb_var_rbBanstandard_fisikkanan(false);
                    realm.commitTransaction();

                }
            }
        });
        cbBan1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBan1_fisikkanan.isChecked()){
                    cbBan1_fisikkanan.setText("Ada");
                    spBan2_fisikkanan.setVisibility(View.VISIBLE);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBan1_fisikkanan(true);
                    upd.first().setTdb_var_cbBan1_fisikkanan("Ada");
                    upd.first().setDb_var_spBan2_fisikkanan(spBan2_fisikkanan.getSelectedItemPosition());
                    upd.first().setVdb_var_spBan2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbBan1_fisikkanan.setText("Tidak Ada");
                    spBan2_fisikkanan.setVisibility(View.INVISIBLE);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBan1_fisikkanan(false);
                    upd.first().setTdb_var_cbBan1_fisikkanan("Tidak Ada");
                    upd.first().setDb_var_spBan2_fisikkanan(0);
                    upd.first().setVdb_var_spBan2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }

            }
        });

        rbVelgstandard_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVelgstandard_fisikkanan.isChecked()){
                    rbVelgracing_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbVelgstandard_fisikkanan(true);
                    upd.first().setDb_var_rbVelgracing_fisikkanan(false);
                    realm.commitTransaction();
                }
            }
        });
        rbVelgracing_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVelgracing_fisikkanan.isChecked()){
                    rbVelgstandard_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbVelgracing_fisikkanan(true);
                    upd.first().setDb_var_rbVelgstandard_fisikkanan(false);
                    realm.commitTransaction();
                }
            }
        });
        cbVelg1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVelg1_fisikkanan.isChecked()){
                    cbVelg1_fisikkanan.setText("Ada");
                    cbVelg2_fisikkanan.setVisibility(View.VISIBLE);
                    cbVelg2_fisikkanan.setText("Rusak");
                    cbVelg2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbVelg1_fisikkanan(true);
                    upd.first().setTdb_var_cbVelg1_fisikkanan("Ada");
                    upd.first().setDb_var_cbVelg2_fisikkanan(false);
                    upd.first().setTdb_var_cbVelg2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbVelg2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbVelg2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbVelg1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbVelg1_fisikkanan(false);
                    upd.first().setTdb_var_cbVelg1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbVelg2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbVelg2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVelg2_fisikkanan.isChecked()){
                    cbVelg2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbVelg2_fisikkanan(true);
                    upd.first().setTdb_var_cbVelg2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbVelg2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbVelg2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbVelg2_fisikkanan(false);
                    upd.first().setTdb_var_cbVelg2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbVelg2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbDop1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDop1_fisikkanan.isChecked()){
                    cbDop1_fisikkanan.setText("Ada");
                    cbDop2_fisikkanan.setVisibility(View.VISIBLE);
                    cbDop2_fisikkanan.setText("Rusak");
                    cbDop2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDop1_fisikkanan(true);
                    upd.first().setTdb_var_cbDop1_fisikkanan("Ada");
                    upd.first().setDb_var_cbDop2_fisikkanan(false);
                    upd.first().setTdb_var_cbDop2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbDop2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbDop2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbDop1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDop1_fisikkanan(false);
                    upd.first().setTdb_var_cbDop1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbDop2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbDop2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDop2_fisikkanan.isChecked()){
                    cbDop2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDop2_fisikkanan(true);
                    upd.first().setTdb_var_cbDop2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbDop2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbDop2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDop2_fisikkanan(false);
                    upd.first().setTdb_var_cbDop2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbDop2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });

        cbDopBlk1_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDopBlk1_fisikkanan.isChecked()){
                    cbDopBlk1_fisikkanan.setText("Ada");
                    cbDopBlk2_fisikkanan.setVisibility(View.VISIBLE);
                    cbDopBlk2_fisikkanan.setText("Rusak");
                    cbDopBlk2_fisikkanan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDopBlk1_fisikkanan(true);
                    upd.first().setTdb_var_cbDopBlk1_fisikkanan("Ada");
                    upd.first().setDb_var_cbDopBlk2_fisikkanan(false);
                    upd.first().setTdb_var_cbDopBlk2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbDopBlk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbDopBlk2_fisikkanan.setVisibility(View.INVISIBLE);
                    cbDopBlk1_fisikkanan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDopBlk1_fisikkanan(false);
                    upd.first().setTdb_var_cbDopBlk1_fisikkanan("Tidak Ada");
                    upd.first().setVdb_var_cbDopBlk2_fisikkanan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbDopBlk2_fisikkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDopBlk2_fisikkanan.isChecked()){
                    cbDopBlk2_fisikkanan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDopBlk2_fisikkanan(true);
                    upd.first().setTdb_var_cbDopBlk2_fisikkanan("Baik");
                    upd.first().setVdb_var_cbDopBlk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbDopBlk2_fisikkanan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDopBlk2_fisikkanan(false);
                    upd.first().setTdb_var_cbDopBlk2_fisikkanan("Rusak");
                    upd.first().setVdb_var_cbDopBlk2_fisikkanan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        //====================================================================

        cbKunciktk1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKunciktk1_perlengkapan.isChecked()){
                    cbKunciktk1_perlengkapan.setText("Ada");
                    cbKunciktk2_perlengkapan.setVisibility(View.VISIBLE);
                    cbKunciktk2_perlengkapan.setText("Rusak");
                    cbKunciktk2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKunciktk1_perlengkapan(true);
                    upd.first().setTdb_var_cbKunciktk1_perlengkapan("Ada");
                    upd.first().setDb_var_cbKunciktk2_perlengkapan(false);
                    upd.first().setTdb_var_cbKunciktk2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbKunciktk2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbKunciktk2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbKunciktk1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKunciktk1_perlengkapan(false);
                    upd.first().setTdb_var_cbKunciktk1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbKunciktk2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbKunciktk2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKunciktk2_perlengkapan.isChecked()){
                    cbKunciktk2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKunciktk2_perlengkapan(true);
                    upd.first().setTdb_var_cbKunciktk2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbKunciktk2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbKunciktk2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKunciktk2_perlengkapan(false);
                    upd.first().setTdb_var_cbKunciktk2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbKunciktk2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSpion1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion1_perlengkapan.isChecked()){
                    cbSpion1_perlengkapan.setText("Ada");
                    cbSpion2_perlengkapan.setVisibility(View.VISIBLE);
                    cbSpion2_perlengkapan.setText("Rusak");
                    cbSpion2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion1_perlengkapan(true);
                    upd.first().setTdb_var_cbSpion1_perlengkapan("Ada");
                    upd.first().setDb_var_cbSpion2_perlengkapan(false);
                    upd.first().setTdb_var_cbSpion2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbSpion2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSpion2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbSpion1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion1_perlengkapan(false);
                    upd.first().setTdb_var_cbSpion1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbSpion2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }

            }
        });
        cbSpion2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion2_perlengkapan.isChecked()){
                    cbSpion2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion2_perlengkapan(true);
                    upd.first().setTdb_var_cbSpion2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbSpion2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSpion2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpion2_perlengkapan(false);
                    upd.first().setTdb_var_cbSpion2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbSpion2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbJok1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJok1_perlengkapan.isChecked()){
                    cbJok1_perlengkapan.setText("Ada");
                    cbJok2_perlengkapan.setVisibility(View.VISIBLE);
                    cbJok2_perlengkapan.setText("Rusak");
                    cbJok2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbJok1_perlengkapan(true);
                    upd.first().setTdb_var_cbJok1_perlengkapan("Ada");
                    upd.first().setDb_var_cbJok2_perlengkapan(false);
                    upd.first().setTdb_var_cbJok2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbJok2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbJok2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbJok1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbJok1_perlengkapan(false);
                    upd.first().setTdb_var_cbJok1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbJok2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbJok2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJok2_perlengkapan.isChecked()){
                    cbJok2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbJok2_perlengkapan(true);
                    upd.first().setTdb_var_cbJok2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbJok2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbJok2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbJok2_perlengkapan(false);
                    upd.first().setTdb_var_cbJok2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbJok2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSarung1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSarung1_perlengkapan.isChecked()){
                    cbSarung1_perlengkapan.setText("Ada");
                    cbSarung2_perlengkapan.setVisibility(View.VISIBLE);
                    cbSarung2_perlengkapan.setText("Rusak");
                    cbSarung2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSarung1_perlengkapan(true);
                    upd.first().setTdb_var_cbSarung1_perlengkapan("Ada");
                    upd.first().setDb_var_cbSarung2_perlengkapan(false);
                    upd.first().setTdb_var_cbSarung2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbSarung2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSarung2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbSarung1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSarung1_perlengkapan(false);
                    upd.first().setTdb_var_cbSarung1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbSarung2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSarung2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSarung2_perlengkapan.isChecked()){
                    cbSarung2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSarung2_perlengkapan(true);
                    upd.first().setTdb_var_cbSarung2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbSarung2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSarung2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSarung2_perlengkapan(false);
                    upd.first().setTdb_var_cbSarung2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbSarung2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSandaran1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSandaran1_perlengkapan.isChecked()){
                    cbSandaran1_perlengkapan.setText("Ada");
                    cbSandaran2_perlengkapan.setVisibility(View.VISIBLE);
                    cbSandaran2_perlengkapan.setText("Rusak");
                    cbSandaran2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSandaran1_perlengkapan(true);
                    upd.first().setTdb_var_cbSandaran1_perlengkapan("Ada");
                    upd.first().setDb_var_cbSandaran2_perlengkapan(false);
                    upd.first().setTdb_var_cbSandaran2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbSandaran2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSandaran2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbSandaran1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSandaran1_perlengkapan(false);
                    upd.first().setTdb_var_cbSandaran1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbSandaran2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSandaran2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSandaran2_perlengkapan.isChecked()){
                    cbSandaran2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSandaran2_perlengkapan(true);
                    upd.first().setTdb_var_cbSandaran2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbSandaran2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSandaran2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSandaran2_perlengkapan(false);
                    upd.first().setTdb_var_cbSandaran2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbSandaran2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbKarpet1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKarpet1_perlengkapan.isChecked()){
                    cbKarpet1_perlengkapan.setText("Ada");
                    cbKarpet2_perlengkapan.setVisibility(View.VISIBLE);
                    cbKarpet2_perlengkapan.setText("Rusak");
                    cbKarpet2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKarpet1_perlengkapan(true);
                    upd.first().setTdb_var_cbKarpet1_perlengkapan("Ada");
                    upd.first().setDb_var_cbKarpet2_perlengkapan(false);
                    upd.first().setTdb_var_cbKarpet2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbKarpet2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbKarpet2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbKarpet1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKarpet1_perlengkapan(false);
                    upd.first().setTdb_var_cbKarpet1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbKarpet2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbKarpet2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKarpet2_perlengkapan.isChecked()){
                    cbKarpet2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKarpet2_perlengkapan(true);
                    upd.first().setTdb_var_cbKarpet2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbKarpet2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbKarpet2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKarpet2_perlengkapan(false);
                    upd.first().setTdb_var_cbKarpet2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbKarpet2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPelindung1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPelindung1_perlengkapan.isChecked()){
                    cbPelindung1_perlengkapan.setText("Ada");
                    cbPelindung2_perlengkapan.setVisibility(View.VISIBLE);
                    cbPelindung2_perlengkapan.setText("Rusak");
                    cbPelindung2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPelindung1_perlengkapan(true);
                    upd.first().setTdb_var_cbPelindung1_perlengkapan("Ada");
                    upd.first().setDb_var_cbPelindung2_perlengkapan(false);
                    upd.first().setTdb_var_cbPelindung2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbPelindung2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPelindung2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbPelindung1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPelindung1_perlengkapan(false);
                    upd.first().setTdb_var_cbPelindung1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbPelindung2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPelindung2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPelindung2_perlengkapan.isChecked()){
                    cbPelindung2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPelindung2_perlengkapan(true);
                    upd.first().setTdb_var_cbPelindung2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbPelindung2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPelindung2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPelindung2_perlengkapan(false);
                    upd.first().setTdb_var_cbPelindung2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbPelindung2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSegitiga1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSegitiga1_perlengkapan.isChecked()){
                    cbSegitiga1_perlengkapan.setText("Ada");
                    cbSegitiga2_perlengkapan.setVisibility(View.VISIBLE);
                    cbSegitiga2_perlengkapan.setText("Rusak");
                    cbSegitiga2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSegitiga1_perlengkapan(true);
                    upd.first().setTdb_var_cbSegitiga1_perlengkapan("Ada");
                    upd.first().setDb_var_cbSegitiga2_perlengkapan(false);
                    upd.first().setTdb_var_cbSegitiga2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbSegitiga2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSegitiga2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbSegitiga1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSegitiga1_perlengkapan(false);
                    upd.first().setTdb_var_cbSegitiga1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbSegitiga2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSegitiga2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSegitiga2_perlengkapan.isChecked()){
                    cbSegitiga2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSegitiga2_perlengkapan(true);
                    upd.first().setTdb_var_cbSegitiga2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbSegitiga2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSegitiga2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSegitiga2_perlengkapan(false);
                    upd.first().setTdb_var_cbSegitiga2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbSegitiga2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbTool1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTool1_perlengkapan.isChecked()){
                    cbTool1_perlengkapan.setText("Ada");
                    cbTool2_perlengkapan.setVisibility(View.VISIBLE);
                    cbTool2_perlengkapan.setText("Rusak");
                    cbTool2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTool1_perlengkapan(true);
                    upd.first().setTdb_var_cbTool1_perlengkapan("Ada");
                    upd.first().setDb_var_cbTool2_perlengkapan(false);
                    upd.first().setTdb_var_cbTool2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbTool2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbTool2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbTool1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTool1_perlengkapan(false);
                    upd.first().setTdb_var_cbTool1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbTool2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbTool2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTool2_perlengkapan.isChecked()){
                    cbTool2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTool2_perlengkapan(true);
                    upd.first().setTdb_var_cbTool2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbTool2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbTool2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTool2_perlengkapan(false);
                    upd.first().setTdb_var_cbTool2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbTool2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbCadangan1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCadangan1_perlengkapan.isChecked()){
                    cbCadangan1_perlengkapan.setText("Ada");
                    cbCadangan2_perlengkapan.setVisibility(View.VISIBLE);
                    cbCadangan2_perlengkapan.setText("Rusak");
                    cbCadangan2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbCadangan1_perlengkapan(true);
                    upd.first().setTdb_var_cbCadangan1_perlengkapan("Ada");
                    upd.first().setDb_var_cbCadangan2_perlengkapan(false);
                    upd.first().setTdb_var_cbCadangan2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbCadangan2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbCadangan2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbCadangan1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbCadangan1_perlengkapan(false);
                    upd.first().setTdb_var_cbCadangan1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbCadangan2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbCadangan2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCadangan2_perlengkapan.isChecked()){
                    cbCadangan2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbCadangan2_perlengkapan(true);
                    upd.first().setTdb_var_cbCadangan2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbCadangan2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbCadangan2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbCadangan2_perlengkapan(false);
                    upd.first().setTdb_var_cbCadangan2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbCadangan2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbKunciban1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKunciban1_perlengkapan.isChecked()){
                    cbKunciban1_perlengkapan.setText("Ada");
                    cbKunciban2_perlengkapan.setVisibility(View.VISIBLE);
                    cbKunciban2_perlengkapan.setText("Rusak");
                    cbKunciban2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKunciban1_perlengkapan(true);
                    upd.first().setTdb_var_cbKunciban1_perlengkapan("Ada");
                    upd.first().setDb_var_cbKunciban2_perlengkapan(false);
                    upd.first().setTdb_var_cbKunciban2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbKunciban2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbKunciban2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbKunciban1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKunciban1_perlengkapan(false);
                    upd.first().setTdb_var_cbKunciban1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbKunciban2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbKunciban2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKunciban2_perlengkapan.isChecked()){
                    cbKunciban2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKunciban2_perlengkapan(true);
                    upd.first().setTdb_var_cbKunciban2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbKunciban2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbKunciban2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKunciban2_perlengkapan(false);
                    upd.first().setTdb_var_cbKunciban2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbKunciban2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbDongkrak1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDongkrak1_perlengkapan.isChecked()){
                    cbDongkrak1_perlengkapan.setText("Ada");
                    cbDongkrak2_perlengkapan.setVisibility(View.VISIBLE);
                    cbDongkrak2_perlengkapan.setText("Rusak");
                    cbDongkrak2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDongkrak1_perlengkapan(true);
                    upd.first().setTdb_var_cbDongkrak1_perlengkapan("Ada");
                    upd.first().setDb_var_cbDongkrak2_perlengkapan(false);
                    upd.first().setTdb_var_cbDongkrak2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbDongkrak2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbDongkrak2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbDongkrak1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDongkrak1_perlengkapan(false);
                    upd.first().setTdb_var_cbDongkrak1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbDongkrak2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbDongkrak2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDongkrak2_perlengkapan.isChecked()){
                    cbDongkrak2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDongkrak2_perlengkapan(true);
                    upd.first().setTdb_var_cbDongkrak2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbDongkrak2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbDongkrak2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbDongkrak2_perlengkapan(false);
                    upd.first().setTdb_var_cbDongkrak2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbDongkrak2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbAntena1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAntena1_perlengkapan.isChecked()){
                    cbAntena1_perlengkapan.setText("Ada");
                    cbAntena2_perlengkapan.setVisibility(View.VISIBLE);
                    cbAntena2_perlengkapan.setText("Rusak");
                    cbAntena2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAntena1_perlengkapan(true);
                    upd.first().setTdb_var_cbAntena1_perlengkapan("Ada");
                    upd.first().setDb_var_cbAntena2_perlengkapan(false);
                    upd.first().setTdb_var_cbAntena2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbAntena2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbAntena2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbAntena1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAntena1_perlengkapan(false);
                    upd.first().setTdb_var_cbAntena1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbAntena2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbAntena2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAntena2_perlengkapan.isChecked()){
                    cbAntena2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAntena2_perlengkapan(true);
                    upd.first().setTdb_var_cbAntena2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbAntena2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbAntena2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAntena2_perlengkapan(false);
                    upd.first().setTdb_var_cbAntena2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbAntena2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbAirbag1_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAirbag1_perlengkapan.isChecked()){
                    cbAirbag1_perlengkapan.setText("Ada");
                    cbAirbag2_perlengkapan.setVisibility(View.VISIBLE);
                    cbAirbag2_perlengkapan.setText("Rusak");
                    cbAirbag2_perlengkapan.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAirbag1_perlengkapan(true);
                    upd.first().setTdb_var_cbAirbag1_perlengkapan("Ada");
                    upd.first().setDb_var_cbAirbag2_perlengkapan(false);
                    upd.first().setTdb_var_cbAirbag2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbAirbag2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbAirbag2_perlengkapan.setVisibility(View.INVISIBLE);
                    cbAirbag1_perlengkapan.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAirbag1_perlengkapan(false);
                    upd.first().setTdb_var_cbAirbag1_perlengkapan("Tidak Ada");
                    upd.first().setVdb_var_cbAirbag2_perlengkapan(View.INVISIBLE);
                    realm.commitTransaction();

                }

            }
        });
        cbAirbag2_perlengkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAirbag2_perlengkapan.isChecked()){
                    cbAirbag2_perlengkapan.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAirbag2_perlengkapan(true);
                    upd.first().setTdb_var_cbAirbag2_perlengkapan("Baik");
                    upd.first().setVdb_var_cbAirbag2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbAirbag2_perlengkapan.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAirbag2_perlengkapan(false);
                    upd.first().setTdb_var_cbAirbag2_perlengkapan("Rusak");
                    upd.first().setVdb_var_cbAirbag2_perlengkapan(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        //=========================================================================

        cbLampukbt1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampukbt1_listrik.isChecked()){
                    cbLampukbt1_listrik.setText("Ada");
                    cbLampukbt2_listrik.setVisibility(View.VISIBLE);
                    cbLampukbt2_listrik.setText("Rusak");
                    cbLampukbt2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampukbt1_listrik(true);
                    upd.first().setTdb_var_cbLampukbt1_listrik("Ada");
                    upd.first().setDb_var_cbLampukbt2_listrik(false);
                    upd.first().setTdb_var_cbLampukbt2_listrik("Rusak");
                    upd.first().setVdb_var_cbLampukbt2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbLampukbt2_listrik.setVisibility(View.INVISIBLE);
                    cbLampukbt1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampukbt1_listrik(false);
                    upd.first().setTdb_var_cbLampukbt1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbLampukbt2_listrik(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbLampukbt2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampukbt2_listrik.isChecked()){
                    cbLampukbt2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampukbt2_listrik(true);
                    upd.first().setTdb_var_cbLampukbt2_listrik("Baik");
                    upd.first().setVdb_var_cbLampukbt2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbLampukbt2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampukbt2_listrik(false);
                    upd.first().setTdb_var_cbLampukbt2_listrik("Rusak");
                    upd.first().setVdb_var_cbLampukbt2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbWiperkacadpn1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWiperkacadpn1_listrik.isChecked()){
                    cbWiperkacadpn1_listrik.setText("Ada");
                    cbWiperkacadpn2_listrik.setVisibility(View.VISIBLE);
                    cbWiperkacadpn2_listrik.setText("Rusak");
                    cbWiperkacadpn2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbWiperkacadpn1_listrik(true);
                    upd.first().setTdb_var_cbWiperkacadpn1_listrik("Ada");
                    upd.first().setDb_var_cbWiperkacadpn2_listrik(false);
                    upd.first().setTdb_var_cbWiperkacadpn2_listrik("Rusak");
                    upd.first().setVdb_var_cbWiperkacadpn2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbWiperkacadpn2_listrik.setVisibility(View.INVISIBLE);
                    cbWiperkacadpn1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbWiperkacadpn1_listrik(false);
                    upd.first().setTdb_var_cbWiperkacadpn1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbWiperkacadpn2_listrik(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbWiperkacadpn2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWiperkacadpn2_listrik.isChecked()){
                    cbWiperkacadpn2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbWiperkacadpn2_listrik(true);
                    upd.first().setTdb_var_cbWiperkacadpn2_listrik("Baik");
                    upd.first().setVdb_var_cbWiperkacadpn2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbWiperkacadpn2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbWiperkacadpn2_listrik(false);
                    upd.first().setTdb_var_cbWiperkacadpn2_listrik("Rusak");
                    upd.first().setVdb_var_cbWiperkacadpn2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbWiperkacablk1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWiperkacablk1_listrik.isChecked()){
                    cbWiperkacablk1_listrik.setText("Ada");
                    cbWiperkacablk2_listrik.setVisibility(View.VISIBLE);
                    cbWiperkacablk2_listrik.setText("Rusak");
                    cbWiperkacablk2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbWiperkacablk1_listrik(true);
                    upd.first().setTdb_var_cbWiperkacablk1_listrik("Ada");
                    upd.first().setDb_var_cbWiperkacablk2_listrik(false);
                    upd.first().setTdb_var_cbWiperkacablk2_listrik("Rusak");
                    upd.first().setVdb_var_cbWiperkacablk2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbWiperkacablk2_listrik.setVisibility(View.INVISIBLE);
                    cbWiperkacablk1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbWiperkacablk1_listrik(false);
                    upd.first().setTdb_var_cbWiperkacablk1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbWiperkacablk2_listrik(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbWiperkacablk2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWiperkacablk2_listrik.isChecked()){
                    cbWiperkacablk2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbWiperkacablk2_listrik(true);
                    upd.first().setTdb_var_cbWiperkacablk2_listrik("Baik");
                    upd.first().setVdb_var_cbWiperkacablk2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbWiperkacablk2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbWiperkacablk2_listrik(false);
                    upd.first().setTdb_var_cbWiperkacablk2_listrik("Rusak");
                    upd.first().setVdb_var_cbWiperkacablk2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbKlakson1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKlakson1_listrik.isChecked()){
                    cbKlakson1_listrik.setText("Ada");
                    cbKlakson2_listrik.setVisibility(View.VISIBLE);
                    cbKlakson2_listrik.setText("Rusak");
                    cbKlakson2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKlakson1_listrik(true);
                    upd.first().setTdb_var_cbKlakson1_listrik("Ada");
                    upd.first().setDb_var_cbKlakson2_listrik(false);
                    upd.first().setTdb_var_cbKlakson2_listrik("Rusak");
                    upd.first().setVdb_var_cbKlakson2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbKlakson2_listrik.setVisibility(View.INVISIBLE);
                    cbKlakson1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKlakson1_listrik(false);
                    upd.first().setTdb_var_cbKlakson1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbKlakson2_listrik(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbKlakson2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKlakson2_listrik.isChecked()){
                    cbKlakson2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbKlakson2_listrik(true);
                    upd.first().setTdb_var_cbKlakson2_listrik("Baik");
                    upd.first().setVdb_var_cbKlakson2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbKlakson2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLampu2_fisikmuka(false);
                    upd.first().setTdb_var_cbLampu2_fisikmuka("Rusak");
                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbAlarm1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAlarm1_listrik.isChecked()){
                    cbAlarm1_listrik.setText("Ada");
                    cbAlarm2_listrik.setVisibility(View.VISIBLE);
                    cbAlarm2_listrik.setText("Rusak");
                    cbAlarm2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAlarm1_listrik(true);
                    upd.first().setTdb_var_cbAlarm1_listrik("Ada");
                    upd.first().setDb_var_cbAlarm2_listrik(false);
                    upd.first().setTdb_var_cbAlarm2_listrik("Rusak");
                    upd.first().setVdb_var_cbAlarm2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbAlarm2_listrik.setVisibility(View.INVISIBLE);
                    cbAlarm1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAlarm1_listrik(false);
                    upd.first().setTdb_var_cbAlarm1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbAlarm2_listrik(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbAlarm2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAlarm2_listrik.isChecked()){
                    cbAlarm2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAlarm2_listrik(true);
                    upd.first().setTdb_var_cbAlarm2_listrik("Baik");
                    upd.first().setVdb_var_cbAlarm2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbAlarm2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAlarm2_listrik(false);
                    upd.first().setTdb_var_cbAlarm2_listrik("Rusak");
                    upd.first().setVdb_var_cbAlarm2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbJam1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJam1_listrik.isChecked()){
                    cbJam1_listrik.setText("Ada");
                    cbJam2_listrik.setVisibility(View.VISIBLE);
                    cbJam2_listrik.setText("Rusak");
                    cbJam2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbJam1_listrik(true);
                    upd.first().setTdb_var_cbJam1_listrik("Ada");
                    upd.first().setDb_var_cbJam2_listrik(false);
                    upd.first().setTdb_var_cbJam2_listrik("Rusak");
                    upd.first().setVdb_var_cbJam2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbJam2_listrik.setVisibility(View.INVISIBLE);
                    cbJam1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbJam1_listrik(false);
                    upd.first().setTdb_var_cbJam1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbJam2_listrik(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbJam2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJam2_listrik.isChecked()){
                    cbJam2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbJam2_listrik(true);
                    upd.first().setTdb_var_cbJam2_listrik("Baik");
                    upd.first().setVdb_var_cbJam2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbJam2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbJam2_listrik(false);
                    upd.first().setTdb_var_cbJam2_listrik("Rusak");
                    upd.first().setVdb_var_cbJam2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbLighter1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLighter1_listrik.isChecked()){
                    cbLighter1_listrik.setText("Ada");
                    cbLighter2_listrik.setVisibility(View.VISIBLE);
                    cbLighter2_listrik.setText("Rusak");
                    cbLighter2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLighter1_listrik(true);
                    upd.first().setTdb_var_cbLighter1_listrik("Ada");
                    upd.first().setDb_var_cbLighter2_listrik(false);
                    upd.first().setTdb_var_cbLighter2_listrik("Rusak");
                    upd.first().setVdb_var_cbLighter2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbLighter2_listrik.setVisibility(View.INVISIBLE);
                    cbLighter1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLighter1_listrik(false);
                    upd.first().setTdb_var_cbLighter1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbLighter2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbLighter2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLighter2_listrik.isChecked()){
                    cbLighter2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLighter2_listrik(true);
                    upd.first().setTdb_var_cbLighter2_listrik("Baik");
                    upd.first().setVdb_var_cbLighter2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbLighter2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbLighter2_listrik(false);
                    upd.first().setTdb_var_cbLighter2_listrik("Rusak");
                    upd.first().setVdb_var_cbLighter2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        rbRadio_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbRadio_listrik.isChecked()){
                    rbTape_listrik.setChecked(false);
                    rbCd_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbRadio_listrik(true);
                    upd.first().setDb_var_rbTape_listrik(false);
                    upd.first().setDb_var_rbCd_listrik(false);
                    realm.commitTransaction();

                }
            }
        });
        rbTape_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbTape_listrik.isChecked()){
                    rbRadio_listrik.setChecked(false);
                    rbCd_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbRadio_listrik(false);
                    upd.first().setDb_var_rbTape_listrik(true);
                    upd.first().setDb_var_rbCd_listrik(false);
                    realm.commitTransaction();

                }
            }
        });
        rbCd_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbCd_listrik.isChecked()){
                    rbRadio_listrik.setChecked(false);
                    rbTape_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_rbRadio_listrik(false);
                    upd.first().setDb_var_rbTape_listrik(false);
                    upd.first().setDb_var_rbCd_listrik(true);
                    realm.commitTransaction();

                }
            }
        });

        cbRadio1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRadio1_listrik.isChecked()){
                    cbRadio1_listrik.setText("Ada");
                    cbRadio2_listrik.setVisibility(View.VISIBLE);
                    cbRadio2_listrik.setText("Rusak");
                    cbRadio2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbRadio1_listrik(true);
                    upd.first().setTdb_var_cbRadio1_listrik("Ada");
                    upd.first().setDb_var_cbRadio2_listrik(false);
                    upd.first().setTdb_var_cbRadio2_listrik("Rusak");
                    upd.first().setVdb_var_cbRadio2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbRadio2_listrik.setVisibility(View.INVISIBLE);
                    cbRadio1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbRadio1_listrik(false);
                    upd.first().setTdb_var_cbRadio1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbRadio2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbRadio2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRadio2_listrik.isChecked()){
                    cbRadio2_listrik.setText("Baik");
                    //var_cbRadio2_listrik= true;

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbRadio2_listrik(true);
                    upd.first().setTdb_var_cbRadio2_listrik("Baik");
                    upd.first().setVdb_var_cbRadio2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbRadio2_listrik.setText("Rusak");
                    //var_cbRadio2_listrik = false;
                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbRadio2_listrik(false);
                    upd.first().setTdb_var_cbRadio2_listrik("Rusak");
                    upd.first().setVdb_var_cbRadio2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbPowersup1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPowersup1_listrik.isChecked()){
                    cbPowersup1_listrik.setText("Ada");
                    cbPowersup2_listrik.setVisibility(View.VISIBLE);
                    cbPowersup2_listrik.setText("Rusak");
                    cbPowersup2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPowersup1_listrik(true);
                    upd.first().setTdb_var_cbPowersup1_listrik("Ada");
                    upd.first().setDb_var_cbPowersup2_listrik(false);
                    upd.first().setTdb_var_cbPowersup2_listrik("Rusak");
                    upd.first().setVdb_var_cbPowersup2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPowersup2_listrik.setVisibility(View.INVISIBLE);
                    cbPowersup1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPowersup1_listrik(false);
                    upd.first().setTdb_var_cbPowersup1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbPowersup2_listrik(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbPowersup2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPowersup2_listrik.isChecked()){
                    cbPowersup2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPowersup2_listrik(true);
                    upd.first().setTdb_var_cbPowersup2_listrik("Baik");
                    upd.first().setVdb_var_cbPowersup2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbPowersup2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPowersup2_listrik(false);
                    upd.first().setTdb_var_cbPowersup2_listrik("Rusak");
                    upd.first().setVdb_var_cbPowersup2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbSpeaker1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpeaker1_listrik.isChecked()){
                    cbSpeaker1_listrik.setText("Ada");
                    cbSpeaker2_listrik.setVisibility(View.VISIBLE);
                    cbSpeaker2_listrik.setText("Rusak");
                    cbSpeaker2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpeaker1_listrik(true);
                    upd.first().setTdb_var_cbSpeaker1_listrik("Ada");
                    upd.first().setDb_var_cbSpeaker2_listrik(false);
                    upd.first().setTdb_var_cbSpeaker2_listrik("Rusak");
                    upd.first().setVdb_var_cbSpeaker2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbSpeaker2_listrik.setVisibility(View.INVISIBLE);
                    cbSpeaker1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpeaker1_listrik(false);
                    upd.first().setTdb_var_cbSpeaker1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbSpeaker2_listrik(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbSpeaker2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpeaker2_listrik.isChecked()){
                    cbSpeaker2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpeaker2_listrik(true);
                    upd.first().setTdb_var_cbSpeaker2_listrik("Baik");
                    upd.first().setVdb_var_cbSpeaker2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbSpeaker2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpeaker2_listrik(false);
                    upd.first().setTdb_var_cbSpeaker2_listrik("Rusak");
                    upd.first().setVdb_var_cbSpeaker2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbAc1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAc1_listrik.isChecked()){
                    cbAc1_listrik.setText("Ada");
                    cbAc2_listrik.setVisibility(View.VISIBLE);
                    cbAc2_listrik.setText("Rusak");
                    cbAc2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAc1_listrik(true);
                    upd.first().setTdb_var_cbAc1_listrik("Ada");
                    upd.first().setDb_var_cbAc2_listrik(false);
                    upd.first().setTdb_var_cbAc2_listrik("Rusak");
                    upd.first().setVdb_var_cbAc2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbAc2_listrik.setVisibility(View.INVISIBLE);
                    cbAc1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAc1_listrik(false);
                    upd.first().setTdb_var_cbAc1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbAc2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbAc2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAc2_listrik.isChecked()){
                    cbAc2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAc2_listrik(true);
                    upd.first().setTdb_var_cbAc2_listrik("Baik");
                    upd.first().setVdb_var_cbAc2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbAc2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAc2_listrik(false);
                    upd.first().setTdb_var_cbAc2_listrik("Rusak");
                    upd.first().setVdb_var_cbAc2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbPowerwin1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPowerwin1_listrik.isChecked()){
                    cbPowerwin1_listrik.setText("Ada");
                    cbPowerwin2_listrik.setVisibility(View.VISIBLE);
                    cbPowerwin2_listrik.setText("Rusak");
                    cbPowerwin2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPowerwin1_listrik(true);
                    upd.first().setTdb_var_cbPowerwin1_listrik("Ada");
                    upd.first().setDb_var_cbPowerwin2_listrik(false);
                    upd.first().setTdb_var_cbPowerwin2_listrik("Rusak");
                    upd.first().setVdb_var_cbPowerwin2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPowerwin2_listrik.setVisibility(View.INVISIBLE);
                    cbPowerwin1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPowerwin1_listrik(false);
                    upd.first().setTdb_var_cbPowerwin1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbPowerwin2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbPowerwin2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPowerwin2_listrik.isChecked()){
                    cbPowerwin2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPowerwin2_listrik(true);
                    upd.first().setTdb_var_cbPowerwin2_listrik("Baik");
                    upd.first().setVdb_var_cbPowerwin2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbPowerwin2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbPowerwin2_listrik(false);
                    upd.first().setTdb_var_cbPowerwin2_listrik("Rusak");
                    upd.first().setVdb_var_cbPowerwin2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbCentral1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCentral1_listrik.isChecked()){
                    cbCentral1_listrik.setText("Ada");
                    cbCentral2_listrik.setVisibility(View.VISIBLE);
                    cbCentral2_listrik.setText("Rusak");
                    cbCentral2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbCentral1_listrik(true);
                    upd.first().setTdb_var_cbCentral1_listrik("Ada");
                    upd.first().setDb_var_cbCentral2_listrik(false);
                    upd.first().setTdb_var_cbCentral2_listrik("Rusak");
                    upd.first().setVdb_var_cbCentral2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbCentral2_listrik.setVisibility(View.INVISIBLE);
                    cbCentral1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbCentral1_listrik(false);
                    upd.first().setTdb_var_cbCentral1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbCentral2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbCentral2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCentral2_listrik.isChecked()){
                    cbCentral2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbCentral2_listrik(true);
                    upd.first().setTdb_var_cbCentral2_listrik("Baik");
                    upd.first().setVdb_var_cbCentral2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbCentral2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbCentral2_listrik(false);
                    upd.first().setTdb_var_cbCentral2_listrik("Rusak");
                    upd.first().setVdb_var_cbCentral2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbRemote1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRemote1_listrik.isChecked()){
                    cbRemote1_listrik.setText("Ada");
                    cbRemote2_listrik.setVisibility(View.VISIBLE);
                    cbRemote2_listrik.setText("Rusak");
                    cbRemote2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbRemote1_listrik(true);
                    upd.first().setTdb_var_cbRemote1_listrik("Ada");
                    upd.first().setDb_var_cbRemote2_listrik(false);
                    upd.first().setTdb_var_cbRemote2_listrik("Rusak");
                    upd.first().setVdb_var_cbRemote2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbRemote2_listrik.setVisibility(View.INVISIBLE);
                    cbRemote1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbRemote1_listrik(false);
                    upd.first().setTdb_var_cbRemote1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbRemote2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbRemote2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRemote2_listrik.isChecked()){
                    cbRemote2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbRemote2_listrik(true);
                    upd.first().setTdb_var_cbRemote2_listrik("Baik");
                    upd.first().setVdb_var_cbRemote2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbRemote2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbRemote2_listrik(false);
                    upd.first().setTdb_var_cbRemote2_listrik("Rusak");
                    upd.first().setVdb_var_cbRemote2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbSpeedo1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpeedo1_listrik.isChecked()){
                    cbSpeedo1_listrik.setText("Ada");
                    cbSpeedo2_listrik.setVisibility(View.VISIBLE);
                    cbSpeedo2_listrik.setText("Rusak");
                    cbSpeedo2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpeedo1_listrik(true);
                    upd.first().setTdb_var_cbSpeedo1_listrik("Ada");
                    upd.first().setDb_var_cbSpeedo2_listrik(false);
                    upd.first().setTdb_var_cbSpeedo2_listrik("Rusak");
                    upd.first().setVdb_var_cbSpeedo2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbSpeedo2_listrik.setVisibility(View.INVISIBLE);
                    cbSpeedo1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpeedo1_listrik(false);
                    upd.first().setTdb_var_cbSpeedo1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbSpeedo2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbSpeedo2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpeedo2_listrik.isChecked()){
                    cbSpeedo2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpeedo2_listrik(true);
                    upd.first().setTdb_var_cbSpeedo2_listrik("Baik");
                    upd.first().setVdb_var_cbSpeedo2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbSpeedo2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbSpeedo2_listrik(false);
                    upd.first().setTdb_var_cbSpeedo2_listrik("Rusak");
                    upd.first().setVdb_var_cbSpeedo2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbOdometer1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbOdometer1_listrik.isChecked()){
                    cbOdometer1_listrik.setText("Ada");
                    cbOdometer2_listrik.setVisibility(View.VISIBLE);
                    cbOdometer2_listrik.setText("Rusak");
                    cbOdometer2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbOdometer1_listrik(true);
                    upd.first().setTdb_var_cbOdometer1_listrik("Ada");
                    upd.first().setDb_var_cbOdometer2_listrik(false);
                    upd.first().setTdb_var_cbOdometer2_listrik("Rusak");
                    upd.first().setVdb_var_cbOdometer2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbOdometer2_listrik.setVisibility(View.INVISIBLE);
                    cbOdometer1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbOdometer1_listrik(false);
                    upd.first().setTdb_var_cbOdometer1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbOdometer2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbOdometer2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbOdometer2_listrik.isChecked()){
                    cbOdometer2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbOdometer2_listrik(true);
                    upd.first().setTdb_var_cbOdometer2_listrik("Baik");
                    upd.first().setVdb_var_cbOdometer2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbOdometer2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbOdometer2_listrik(false);
                    upd.first().setTdb_var_cbOdometer2_listrik("Rusak");
                    upd.first().setVdb_var_cbOdometer2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbTacho1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTacho1_listrik.isChecked()){
                    cbTacho1_listrik.setText("Ada");
                    cbTacho2_listrik.setVisibility(View.VISIBLE);
                    cbTacho2_listrik.setText("Rusak");
                    cbTacho2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTacho1_listrik(true);
                    upd.first().setTdb_var_cbTacho1_listrik("Ada");
                    upd.first().setDb_var_cbTacho2_listrik(false);
                    upd.first().setTdb_var_cbTacho2_listrik("Rusak");
                    upd.first().setVdb_var_cbTacho2_listrik(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbTacho2_listrik.setVisibility(View.INVISIBLE);
                    cbTacho1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTacho1_listrik(false);
                    upd.first().setTdb_var_cbTacho1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbTacho2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbTacho2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTacho2_listrik.isChecked()){
                    cbTacho2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBamper2_fisikmuka(true);
                    upd.first().setTdb_var_cbBamper2_fisikmuka("Baik");
                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbTacho2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTacho2_listrik(false);
                    upd.first().setTdb_var_cbTacho2_listrik("Rusak");
                    upd.first().setVdb_var_cbTacho2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbAccu1_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAccu1_listrik.isChecked()){
                    cbAccu1_listrik.setText("Ada");
                    cbAccu2_listrik.setVisibility(View.VISIBLE);
                    cbAccu2_listrik.setText("Rusak");
                    cbAccu2_listrik.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAccu1_listrik(true);
                    upd.first().setTdb_var_cbAccu1_listrik("Ada");
                    upd.first().setDb_var_cbAccu2_listrik(false);
                    upd.first().setTdb_var_cbAccu2_listrik("Rusak");
                    upd.first().setVdb_var_cbAccu2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbAccu2_listrik.setVisibility(View.INVISIBLE);
                    cbAccu1_listrik.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAccu1_listrik(false);
                    upd.first().setTdb_var_cbAccu1_listrik("Tidak Ada");
                    upd.first().setVdb_var_cbAccu2_listrik(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbAccu2_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAccu2_listrik.isChecked()){
                    cbAccu2_listrik.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAccu2_listrik(true);
                    upd.first().setTdb_var_cbAccu2_listrik("Baik");
                    upd.first().setVdb_var_cbAccu2_listrik(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbAccu2_listrik.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAccu2_listrik(false);
                    upd.first().setTdb_var_cbAccu2_listrik("Rusak");
                    upd.first().setVdb_var_cbAccu2_listrik(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        //=======================================================================

        cbMesin1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbMesin1_lain.isChecked()){
                    cbMesin1_lain.setText("Ada");
                    cbMesin2_lain.setVisibility(View.VISIBLE);
                    cbMesin2_lain.setText("Rusak");
                    cbMesin2_lain.setChecked(false);


                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbMesin1_lain(true);
                    upd.first().setTdb_var_cbMesin1_lain("Ada");
                    upd.first().setDb_var_cbMesin2_lain(false);
                    upd.first().setTdb_var_cbMesin2_lain("Rusak");
                    upd.first().setVdb_var_cbMesin2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbMesin2_lain.setVisibility(View.INVISIBLE);
                    cbMesin1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbMesin1_lain(false);
                    upd.first().setTdb_var_cbMesin1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbMesin2_lain(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbMesin2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbMesin2_lain.isChecked()){
                    cbMesin2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbMesin2_lain(true);
                    upd.first().setTdb_var_cbMesin2_lain("Baik");
                    upd.first().setVdb_var_cbMesin2_lain(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbMesin2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbMesin2_lain(false);
                    upd.first().setTdb_var_cbMesin2_lain("Rusak");
                    upd.first().setVdb_var_cbMesin2_lain(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbHidraulik1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbHidraulik1_lain.isChecked()){
                    cbHidraulik1_lain.setText("Ada");
                    cbHidraulik2_lain.setVisibility(View.VISIBLE);
                    cbHidraulik2_lain.setText("Rusak");
                    cbHidraulik2_lain.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbHidraulik1_lain(true);
                    upd.first().setTdb_var_cbHidraulik1_lain("Ada");
                    upd.first().setDb_var_cbHidraulik2_lain(false);
                    upd.first().setTdb_var_cbHidraulik2_lain("Rusak");
                    upd.first().setVdb_var_cbHidraulik2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbHidraulik2_lain.setVisibility(View.INVISIBLE);
                    cbHidraulik1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbHidraulik1_lain(false);
                    upd.first().setTdb_var_cbHidraulik1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbHidraulik2_lain(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbHidraulik2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbHidraulik2_lain.isChecked()){
                    cbHidraulik2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbHidraulik2_lain(true);
                    upd.first().setTdb_var_cbHidraulik2_lain("Baik");
                    upd.first().setVdb_var_cbHidraulik2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbHidraulik2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbHidraulik2_lain(false);
                    upd.first().setTdb_var_cbHidraulik2_lain("Rusak");
                    upd.first().setVdb_var_cbHidraulik2_lain(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbGardan1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbGardan1_lain.isChecked()){
                    cbGardan1_lain.setText("Ada");
                    cbGardan2_lain.setVisibility(View.VISIBLE);
                    cbGardan2_lain.setText("Rusak");
                    cbGardan2_lain.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbGardan1_lain(true);
                    upd.first().setTdb_var_cbGardan1_lain("Ada");
                    upd.first().setDb_var_cbGardan2_lain(false);
                    upd.first().setTdb_var_cbGardan2_lain("Rusak");
                    upd.first().setVdb_var_cbGardan2_lain(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbGardan2_lain.setVisibility(View.INVISIBLE);
                    cbGardan1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbGardan1_lain(false);
                    upd.first().setTdb_var_cbGardan1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbGardan2_lain(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbGardan2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbGardan2_lain.isChecked()){
                    cbGardan2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbGardan2_lain(true);
                    upd.first().setTdb_var_cbGardan2_lain("Baik");
                    upd.first().setVdb_var_cbGardan2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbGardan2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbGardan2_lain(false);
                    upd.first().setTdb_var_cbGardan2_lain("Rusak");
                    upd.first().setVdb_var_cbGardan2_lain(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbAs1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAs1_lain.isChecked()){
                    cbAs1_lain.setText("Ada");
                    cbAs2_lain.setVisibility(View.VISIBLE);
                    cbAs2_lain.setText("Rusak");
                    cbAs2_lain.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAs1_lain(true);
                    upd.first().setTdb_var_cbAs1_lain("Ada");
                    upd.first().setDb_var_cbAs2_lain(false);
                    upd.first().setTdb_var_cbAs2_lain("Rusak");
                    upd.first().setVdb_var_cbAs2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbAs2_lain.setVisibility(View.INVISIBLE);
                    cbAs1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAs1_lain(false);
                    upd.first().setTdb_var_cbAs1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbAs2_lain(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbAs2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAs2_lain.isChecked()){
                    cbAs2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAs2_lain(true);
                    upd.first().setTdb_var_cbAs2_lain("Baik");
                    upd.first().setVdb_var_cbAs2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbAs2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbAs2_lain(false);
                    upd.first().setTdb_var_cbAs2_lain("Rusak");
                    upd.first().setVdb_var_cbAs2_lain(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbBak1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBak1_lain.isChecked()){
                    cbBak1_lain.setText("Ada");
                    cbBak2_lain.setVisibility(View.VISIBLE);
                    cbBak2_lain.setText("Rusak");
                    cbBak2_lain.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBak1_lain(true);
                    upd.first().setTdb_var_cbBak1_lain("Ada");
                    upd.first().setDb_var_cbBak2_lain(false);
                    upd.first().setTdb_var_cbBak2_lain("Rusak");
                    upd.first().setVdb_var_cbBak2_lain(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbBak2_lain.setVisibility(View.INVISIBLE);
                    cbBak1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBak1_lain(false);
                    upd.first().setTdb_var_cbBak1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbBak2_lain(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbBak2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBak2_lain.isChecked()){
                    cbBak2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBak2_lain(true);
                    upd.first().setTdb_var_cbBak2_lain("Baik");
                    upd.first().setVdb_var_cbBak2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBak2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBak2_lain(false);
                    upd.first().setTdb_var_cbBak2_lain("Rusak");
                    upd.first().setVdb_var_cbBak2_lain(View.VISIBLE);
                    realm.commitTransaction();
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

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbStnk1_lain(true);
                    upd.first().setTdb_var_cbStnk1_lain("Ada");
                    upd.first().setDb_var_cbStnk2_lain(false);
                    upd.first().setTdb_var_cbStnk2_lain("Rusak");
                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbStnk2_lain.setVisibility(View.INVISIBLE);
                    cbStnk1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbStnk1_lain(false);
                    upd.first().setTdb_var_cbStnk1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbStnk2_lain(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbStnk2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbStnk2_lain.isChecked()){
                    cbStnk2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbStnk2_lain(true);
                    upd.first().setTdb_var_cbStnk2_lain("Baik");
                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbStnk2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbStnk2_lain(false);
                    upd.first().setTdb_var_cbStnk2_lain("Rusak");
                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbBukukir1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukukir1_lain.isChecked()){
                    cbBukukir1_lain.setText("Ada");
                    cbBukukir2_lain.setVisibility(View.VISIBLE);
                    cbBukukir2_lain.setText("Rusak");
                    cbBukukir2_lain.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBukukir1_lain(true);
                    upd.first().setTdb_var_cbBukukir1_lain("Ada");
                    upd.first().setDb_var_cbBukukir2_lain(false);
                    upd.first().setTdb_var_cbBukukir2_lain("Rusak");
                    upd.first().setVdb_var_cbBukukir2_lain(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbBukukir2_lain.setVisibility(View.INVISIBLE);
                    cbBukukir1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBukukir1_lain(false);
                    upd.first().setTdb_var_cbBukukir1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbBukukir2_lain(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbBukukir2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukukir2_lain.isChecked()){
                    cbBukukir2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBukukir2_lain(true);
                    upd.first().setTdb_var_cbBukukir2_lain("Baik");
                    upd.first().setVdb_var_cbBukukir2_lain(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbBukukir2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBukukir2_lain(false);
                    upd.first().setTdb_var_cbBukukir2_lain("Rusak");
                    upd.first().setVdb_var_cbBukukir2_lain(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbTrayek1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTrayek1_lain.isChecked()){
                    cbTrayek1_lain.setText("Ada");
                    cbTrayek2_lain.setVisibility(View.VISIBLE);
                    cbTrayek2_lain.setText("Rusak");
                    cbTrayek2_lain.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTrayek1_lain(true);
                    upd.first().setTdb_var_cbTrayek1_lain("Ada");
                    upd.first().setDb_var_cbTrayek2_lain(false);
                    upd.first().setTdb_var_cbTrayek2_lain("Rusak");
                    upd.first().setVdb_var_cbTrayek2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbTrayek2_lain.setVisibility(View.INVISIBLE);
                    cbTrayek1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTrayek1_lain(false);
                    upd.first().setTdb_var_cbTrayek1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbTrayek2_lain(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbTrayek2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTrayek2_lain.isChecked()){
                    cbTrayek2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbTrayek2_lain(true);
                    upd.first().setTdb_var_cbTrayek2_lain("Baik");
                    upd.first().setVdb_var_cbTrayek2_lain(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbTrayek2_lain.setText("Rusak");
                    var_cbTrayek2_lain = false;
                }
            }
        });
        cbUsaha1_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbUsaha1_lain.isChecked()){
                    cbUsaha1_lain.setText("Ada");
                    cbUsaha2_lain.setVisibility(View.VISIBLE);
                    cbUsaha2_lain.setText("Rusak");
                    cbUsaha2_lain.setChecked(false);

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbUsaha1_lain(true);
                    upd.first().setTdb_var_cbUsaha1_lain("Ada");
                    upd.first().setDb_var_cbUsaha2_lain(false);
                    upd.first().setTdb_var_cbUsaha2_lain("Rusak");
                    upd.first().setVdb_var_cbUsaha2_lain(View.VISIBLE);
                    realm.commitTransaction();


                }else {
                    cbUsaha2_lain.setVisibility(View.INVISIBLE);
                    cbUsaha1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbUsaha1_lain(false);
                    upd.first().setTdb_var_cbUsaha1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbUsaha2_lain(View.INVISIBLE);
                    realm.commitTransaction();

                }
            }
        });
        cbUsaha2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbUsaha2_lain.isChecked()){
                    cbUsaha2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbUsaha2_lain(true);
                    upd.first().setTdb_var_cbUsaha2_lain("Baik");
                    upd.first().setVdb_var_cbUsaha2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbUsaha2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbUsaha2_lain(false);
                    upd.first().setTdb_var_cbUsaha2_lain("Rusak");
                    upd.first().setVdb_var_cbUsaha2_lain(View.VISIBLE);
                    realm.commitTransaction();
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

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBukumnl1_lain(true);
                    upd.first().setTdb_var_cbBukumnl1_lain("Ada");
                    upd.first().setDb_var_cbBukumnl2_lain(false);
                    upd.first().setTdb_var_cbBukumnl2_lain("Rusak");
                    upd.first().setVdb_var_cbBukumnl2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBukumnl2_lain.setVisibility(View.INVISIBLE);
                    cbBukumnl1_lain.setText("Tidak Ada");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBukumnl1_lain(false);
                    upd.first().setTdb_var_cbBukumnl1_lain("Tidak Ada");
                    upd.first().setVdb_var_cbBukumnl2_lain(View.INVISIBLE);
                    realm.commitTransaction();
                }
            }
        });
        cbBukumnl2_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukumnl2_lain.isChecked()){
                    cbBukumnl2_lain.setText("Baik");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBukumnl2_lain(true);
                    upd.first().setTdb_var_cbBukumnl2_lain("Baik");
                    upd.first().setVdb_var_cbBukumnl2_lain(View.VISIBLE);
                    realm.commitTransaction();

                }else {
                    cbBukumnl2_lain.setText("Rusak");

                    realm.beginTransaction();
                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
                    upd.load();
                    upd.first().setDb_var_cbBukumnl2_lain(false);
                    upd.first().setTdb_var_cbBukumnl2_lain("Rusak");
                    upd.first().setVdb_var_cbBukumnl2_lain(View.VISIBLE);
                    realm.commitTransaction();
                }
            }
        });



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
    private DatePickerDialog.OnDateSetListener datebukukir = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateBukukir();
        }
    };
    private DatePickerDialog.OnDateSetListener dateijintrayek = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateIjintrayek();
        }
    };
    private DatePickerDialog.OnDateSetListener dateijinusaha = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateIjinusaha();
        }
    };

    private void updatePickerStnk() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etSd_stnk.setText(sdf.format(myCalendar.getTime()));

//            realm.beginTransaction();
//            RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//            upd.load();
//            upd.first().setDb_var_etStnk_lain(sdf.format(myCalendar.getTime()));
//            upd.first().setDb_var_etBukukir_lain(sdf.format(myCalendar.getTime()));
//            upd.first().setDb_var_etIjintrayek_lain(sdf.format(myCalendar.getTime()));
//            upd.first().setDb_var_etIjinusaha_lain(sdf.format(myCalendar.getTime()));
//            realm.commitTransaction();
        }catch (Exception e){

        }
    }
    private void updateBukukir() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etSd_bukukir.setText(sdf.format(myCalendar.getTime()));

//            realm.beginTransaction();
//            RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//            upd.load();
//            upd.first().setDb_var_etBukukir_lain(sdf.format(myCalendar.getTime()));
//            realm.commitTransaction();
        }catch (Exception e){

        }
    }
    private void updateIjintrayek() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etSd_ijintrayek.setText(sdf.format(myCalendar.getTime()));

//            realm.beginTransaction();
//            RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//            upd.load();
//            upd.first().setDb_var_etIjintrayek_lain(sdf.format(myCalendar.getTime()));
//            realm.commitTransaction();
        }catch (Exception e){

        }
    }
    private void updateIjinusaha() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etSd_ijinusaha.setText(sdf.format(myCalendar.getTime()));

//            realm.beginTransaction();
//            RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//            upd.load();
//            upd.first().setDb_var_etIjinusaha_lain(sdf.format(myCalendar.getTime()));
//            realm.commitTransaction();
        }catch (Exception e){

        }
    }

    private void sendCeklist() {
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance(); //creating  database oject

        final RealmResults<InputData> results = realm.where(InputData.class).findAllAsync();
        results.load();

        final RealmResults<Ceklist> ceklists = realm.where(Ceklist.class).findAllAsync();
        ceklists.load();

        final RealmResults<BodyKendaraan> bodyKendaraan = realm.where(BodyKendaraan.class).findAllAsync();
        bodyKendaraan.load();

        String tgl = results.first().getTanggal();
        String[] splits = tgl.split("-");
        String hr = splits[0];
        String bln = splits[1];
        String thn = splits[2];

        final String tanggal = thn + "-" + bln + "-" + hr;


        final String vendor = results.first().getVendorid();
        final String pukul = results.first().getPukul();
        final String lokasi = results.first().getLokasi_penarikan();
        final int type = results.first().getType();
        String strType = null;
        if (type == 0){
            strType = "SUV/MVP";
        }else if (type == 1){
            strType = "TRUCK";
        }else if (type == 2){
            strType = "BUS";
        }
        final String merk = results.first().getMerk();
        final String typeid = results.first().getVhcTypeid();
        final String typename = results.first().getVhcTypename();
        final String itemid = results.first().getVhcItemid();
        final String tahun = results.first().getTahun();
        final String stnk = results.first().getStnk();
        final String nopol = results.first().getNopol1() + "-" + results.first().getNopol2() + "-" + results.first().getNopol3();
        final String kmditarik = results.first().getKmditarik();
        final String meterbensin = results.first().getMeterbensin();
        final String cabang = results.first().getCabang();
        final String nomesin = results.first().getNomesin();
        final String norangka = results.first().getNorangka();
        final String warna = results.first().getWarna();
        final String kmditerima = results.first().getKmditerima();
//        final String cficabang = results.first().getCficabang();
        final String lokasipool = results.first().getLokasi_pool();

        var_cbGrill1_fisikmuka = ceklists.first().getDb_var_cbGrill1_fisikmuka();
        var_cbGrill2_fisikmuka = ceklists.first().getDb_var_cbGrill2_fisikmuka();
        var_cbLampu1_fisikmuka = ceklists.first().getDb_var_cbLampu1_fisikmuka();
        var_cbLampu2_fisikmuka = ceklists.first().getDb_var_cbLampu2_fisikmuka();
        var_cbLampusen1_fisikmuka = ceklists.first().getDb_var_cbLampusen1_fisikmuka();
        var_cbLampusen2_fisikmuka = ceklists.first().getDb_var_cbLampusen2_fisikmuka();
        var_cbBamper1_fisikmuka = ceklists.first().getDb_var_cbBamper1_fisikmuka();

        var_cbBamper2_fisikmuka = ceklists.first().getDb_var_cbBamper2_fisikmuka();
        var_cbEmblem1_fisikmuka =  ceklists.first().getDb_var_cbEmblem1_fisikmuka();
        var_cbEmblem2_fisikmuka = ceklists.first().getDb_var_cbEmblem2_fisikmuka();
        var_cbTanduk1_fisikmuka = ceklists.first().getDb_var_cbTanduk1_fisikmuka();
        var_cbTanduk2_fisikmuka = ceklists.first().getDb_var_cbTanduk2_fisikmuka();

        var_cbFootstep1_fisikkiri = ceklists.first().getDb_var_cbFootstep1_fisikkiri();
        var_cbFootstep2_fisikkiri = ceklists.first().getDb_var_cbFootstep2_fisikkiri();
        var_cbPintudpn1_fisikkiri = ceklists.first().getDb_var_cbPintudpn1_fisikkiri();
        var_cbPintudpn2_fisikkiri = ceklists.first().getDb_var_cbPintudpn2_fisikkiri();
        var_cbPintublk1_fisikkiri = ceklists.first().getDb_var_cbPintublk1_fisikkiri();
        var_cbPintublk2_fisikkiri = ceklists.first().getDb_var_cbPintublk2_fisikkiri();
        var_cbBamper1_fisikkiri = ceklists.first().getDb_var_cbBamper1_fisikkiri();
        var_cbBamper2_fisikkiri = ceklists.first().getDb_var_cbBamper2_fisikkiri();
        var_cbFenderblk1_fisikkiri = ceklists.first().getDb_var_cbFenderblk1_fisikkiri();
        var_cbFenderblk2_fisikkiri = ceklists.first().getDb_var_cbFenderblk2_fisikkiri();
        var_cbSpion1_fisikkiri = ceklists.first().getDb_var_cbSpion1_fisikkiri();
        var_cbSpion2_fisikkiri = ceklists.first().getDb_var_cbSpion2_fisikkiri();
        var_cbEmblem1_fisikkiri = ceklists.first().getDb_var_cbEmblem1_fisikkiri();
        var_cbEmblem2_fisikkiri = ceklists.first().getDb_var_cbEmblem2_fisikkiri();

        //ceklists.first().getDb_var_rbBanstandard_fisikkiri() == false) ? ceklists.first().getDb_var_rbBanradial_fisikkiri()) : ceklists.first().getDb_var_rbBanstandard_fisikkiri()));
        var_cbBan1_fisikkiri = ceklists.first().getDb_var_cbBan1_fisikkiri();
        var_spBan2_fisikkiri = ceklists.first().getDb_var_spBan2_fisikkiri();

        var_rbVelgstandard_fisikkiri = ceklists.first().getDb_var_rbVelgstandard_fisikkiri();
        var_rbVelgracing_fisikkiri = ceklists.first().getDb_var_rbVelgracing_fisikkiri();
        var_cbVelg1_fisikkiri = ceklists.first().getDb_var_cbVelg1_fisikkiri();
        var_cbVelg2_fisikkiri = ceklists.first().getDb_var_cbVelg2_fisikkiri();
        var_cbDop1_fisikkiri = ceklists.first().getDb_var_cbDop1_fisikkiri();
        var_cbDop2_fisikkiri = ceklists.first().getDb_var_cbDop2_fisikkiri();
        var_cbDopBlk1_fisikkiri = ceklists.first().getDb_var_cbDopBlk1_fisikkiri();
        var_cbDopBlk2_fisikkiri = ceklists.first().getDb_var_cbDopBlk2_fisikkiri();

        var_cbSpoiler1_fisikblkg = ceklists.first().getDb_var_cbSpoiler1_fisikblkg();
        var_cbSpoiler2_fisikblkg = ceklists.first().getDb_var_cbSpoiler2_fisikblkg();
        var_cbLampu1_fisikblkg = ceklists.first().getDb_var_cbLampu1_fisikblkg();
        var_cbLampu2_fisikblkg = ceklists.first().getDb_var_cbLampu2_fisikblkg();
        var_cbLampusen1_fisikblkg = ceklists.first().getDb_var_cbLampusen1_fisikblkg();
        var_cbLampusen2_fisikblkg = ceklists.first().getDb_var_cbLampusen2_fisikblkg();
        var_cbBamper1_fisikblkg = ceklists.first().getDb_var_cbBamper1_fisikblkg();
        var_cbBamper2_fisikblkg = ceklists.first().getDb_var_cbBamper2_fisikblkg();
        var_cbEmblem1_fisikblkg = ceklists.first().getDb_var_cbEmblem1_fisikblkg();
        var_cbEmblem2_fisikblkg = ceklists.first().getDb_var_cbEmblem2_fisikblkg();
        var_cbKnalpot1_fisikblkg = ceklists.first().getDb_var_cbKnalpot1_fisikblkg();
        var_cbKnalpot2_fisikblkg = ceklists.first().getDb_var_cbKnalpot2_fisikblkg();

        var_cbFoot1_fisikkanan = ceklists.first().getDb_var_cbFoot1_fisikkanan();
        var_cbFoot2_fisikkanan = ceklists.first().getDb_var_cbFoot2_fisikkanan();
        var_cbPintudpn1_fisikkanan = ceklists.first().getDb_var_cbPintudpn1_fisikkanan();
        var_cbPintudpn2_fisikkanan = ceklists.first().getDb_var_cbPintudpn2_fisikkanan();
        var_cbPintublk1_fisikkanan = ceklists.first().getDb_var_cbPintublk1_fisikkanan();
        var_cbPintublk2_fisikkanan = ceklists.first().getDb_var_cbPintublk2_fisikkanan();
        var_cbBamper1_fisikkanan = ceklists.first().getDb_var_cbBamper1_fisikkanan();
        var_cbBamper2_fisikkanan = ceklists.first().getDb_var_cbBamper2_fisikkanan();
        var_cbFenderblk1_fisikkanan = ceklists.first().getDb_var_cbFenderblk1_fisikkanan();
        var_cbFenderblk2_fisikkanan = ceklists.first().getDb_var_cbFenderblk2_fisikkanan();
        var_cbSpion1_fisikkanan = ceklists.first().getDb_var_cbSpion1_fisikkanan();
        var_cbSpion2_fisikkanan = ceklists.first().getDb_var_cbSpion2_fisikkanan();
        var_cbEmblem1_fisikkanan = ceklists.first().getDb_var_cbEmblem1_fisikkanan();
        var_cbEmblem2_fisikkanan = ceklists.first().getDb_var_cbEmblem2_fisikkanan();
//
//                ceklists.first().getDb_var_rbBanstandard_fisikkanan() == false) ? ceklists.first().getDb_var_rbBanradial_fisikkanan()) : String.valueOf(ceklists.first().getDb_var_rbBanstandard_fisikkanan()));
//                ceklists.first().getDb_var_rbBanstandard_fisikkanan() == false) ? ceklists.first().getDb_var_rbBanradial_fisikkanan()) : String.valueOf(ceklists.first().getDb_var_rbBanstandard_fisikkanan()));
        var_cbBan1_fisikkanan = ceklists.first().getDb_var_cbBan1_fisikkanan();
//
//                ceklists.first().getDb_var_rbVelgstandard_fisikkanan() == false) ? String.valueOf(ceklists.first().getDb_var_rbVelgracing_fisikkanan()) : String.valueOf(ceklists.first().getDb_var_rbVelgstandard_fisikkanan()));
        var_cbVelg1_fisikkanan = ceklists.first().getDb_var_cbVelg1_fisikkanan();
        var_cbVelg2_fisikkanan = ceklists.first().getDb_var_cbVelg2_fisikkanan();
//
        var_cbDop1_fisikkanan = ceklists.first().getDb_var_cbDop1_fisikkanan();
        var_cbDop2_fisikkanan = ceklists.first().getDb_var_cbDop2_fisikkanan();
        var_cbDopBlk1_fisikkanan = ceklists.first().getDb_var_cbDopBlk1_fisikkanan();
        var_cbDopBlk2_fisikkanan = ceklists.first().getDb_var_cbDopBlk2_fisikkanan();
        var_cbKunciktk1_perlengkapan = ceklists.first().getDb_var_cbKunciktk1_perlengkapan();
        var_cbKunciktk2_perlengkapan = ceklists.first().getDb_var_cbKunciktk2_perlengkapan();
        var_cbSpion1_perlengkapan = ceklists.first().getDb_var_cbSpion1_perlengkapan();
        var_cbSpion2_perlengkapan = ceklists.first().getDb_var_cbSpion2_perlengkapan();
        var_cbJok1_perlengkapan = ceklists.first().getDb_var_cbJok1_perlengkapan();
        var_cbJok2_perlengkapan = ceklists.first().getDb_var_cbJok2_perlengkapan();
        var_cbSarung1_perlengkapan = ceklists.first().getDb_var_cbSarung1_perlengkapan();
        var_cbSarung2_perlengkapan = ceklists.first().getDb_var_cbSarung2_perlengkapan();
        var_cbSandaran1_perlengkapan = ceklists.first().getDb_var_cbSandaran1_perlengkapan();
        var_cbSandaran2_perlengkapan = ceklists.first().getDb_var_cbSandaran2_perlengkapan();
        var_cbKarpet1_perlengkapan = ceklists.first().getDb_var_cbKarpet1_perlengkapan();
        var_cbKarpet2_perlengkapan = ceklists.first().getDb_var_cbKarpet2_perlengkapan();
        var_cbPelindung1_perlengkapan = ceklists.first().getDb_var_cbPelindung1_perlengkapan();
        var_cbPelindung2_perlengkapan = ceklists.first().getDb_var_cbPelindung2_perlengkapan();
        var_cbSegitiga1_perlengkapan = ceklists.first().getDb_var_cbSegitiga1_perlengkapan();
        var_cbSegitiga2_perlengkapan = ceklists.first().getDb_var_cbSegitiga2_perlengkapan();
        var_cbTool1_perlengkapan = ceklists.first().getDb_var_cbTool1_perlengkapan();
        var_cbTool2_perlengkapan = ceklists.first().getDb_var_cbTool2_perlengkapan();
        var_cbCadangan1_perlengkapan = ceklists.first().getDb_var_cbCadangan1_perlengkapan();
        var_cbCadangan2_perlengkapan = ceklists.first().getDb_var_cbCadangan2_perlengkapan();
        var_cbKunciban1_perlengkapan = ceklists.first().getDb_var_cbKunciban1_perlengkapan();
        var_cbKunciban2_perlengkapan = ceklists.first().getDb_var_cbKunciban2_perlengkapan();
        var_cbDongkrak1_perlengkapan = ceklists.first().getDb_var_cbDongkrak1_perlengkapan();
        var_cbDongkrak2_perlengkapan = ceklists.first().getDb_var_cbDongkrak2_perlengkapan();
        var_cbAntena1_perlengkapan = ceklists.first().getDb_var_cbAntena1_perlengkapan();
        var_cbAntena2_perlengkapan = ceklists.first().getDb_var_cbAntena2_perlengkapan();
        var_cbAirbag1_perlengkapan = ceklists.first().getDb_var_cbAirbag1_perlengkapan();
        var_cbAirbag2_perlengkapan = ceklists.first().getDb_var_cbAirbag2_perlengkapan();

        var_cbLampukbt1_listrik = ceklists.first().getDb_var_cbLampukbt1_listrik();
        var_cbLampukbt2_listrik = ceklists.first().getDb_var_cbLampukbt2_listrik();
        var_cbWiperkacadpn1_listrik = ceklists.first().getDb_var_cbWiperkacadpn1_listrik();
        var_cbWiperkacadpn2_listrik = ceklists.first().getDb_var_cbWiperkacadpn2_listrik();
        var_cbWiperkacablk1_listrik = ceklists.first().getDb_var_cbWiperkacablk1_listrik();
        var_cbWiperkacablk2_listrik = ceklists.first().getDb_var_cbWiperkacablk2_listrik();
        var_cbKlakson1_listrik = ceklists.first().getDb_var_cbKlakson1_listrik();
        var_cbKlakson2_listrik = ceklists.first().getDb_var_cbKlakson2_listrik();
        var_cbAlarm1_listrik = ceklists.first().getDb_var_cbAlarm1_listrik();
        var_cbAlarm2_listrik = ceklists.first().getDb_var_cbAlarm2_listrik();
        var_cbJam1_listrik = ceklists.first().getDb_var_cbJam1_listrik();
        var_cbJam2_listrik = ceklists.first().getDb_var_cbJam2_listrik();
        var_cbLighter1_listrik = ceklists.first().getDb_var_cbLighter1_listrik();
        var_cbLighter2_listrik = ceklists.first().getDb_var_cbLighter2_listrik();
//
//                ceklists.first().getDb_var_rbRadio_listrik() == false) ? String.valueOf(ceklists.first().getDb_var_rbTape_listrik()) : (ceklists.first().getDb_var_rbTape_listrik() == false) ? String.valueOf(ceklists.first().getDb_var_rbCd_listrik()) : String.valueOf(false));
        var_cbRadio1_listrik = ceklists.first().getDb_var_cbRadio1_listrik();
        var_cbRadio2_listrik = ceklists.first().getDb_var_cbRadio2_listrik();
        var_cbPowersup1_listrik = ceklists.first().getDb_var_cbPowersup1_listrik();
        var_cbPowersup2_listrik = ceklists.first().getDb_var_cbPowersup2_listrik();
        var_cbSpeaker1_listrik = ceklists.first().getDb_var_cbSpeaker1_listrik();
        var_cbSpeaker2_listrik = ceklists.first().getDb_var_cbSpeaker2_listrik();
        var_cbAc1_listrik = ceklists.first().getDb_var_cbAc1_listrik();
        var_cbAc2_listrik = ceklists.first().getDb_var_cbAc2_listrik();
        var_cbPowerwin1_listrik = ceklists.first().getDb_var_cbPowerwin1_listrik();
        var_cbPowerwin2_listrik = ceklists.first().getDb_var_cbPowerwin2_listrik();
        var_cbCentral1_listrik = ceklists.first().getDb_var_cbCentral1_listrik();
        var_cbCentral2_listrik = ceklists.first().getDb_var_cbCentral2_listrik();
        var_cbRemote1_listrik = ceklists.first().getDb_var_cbRemote1_listrik();
        var_cbRemote2_listrik = ceklists.first().getDb_var_cbRemote2_listrik();
        var_cbSpeedo1_listrik = ceklists.first().getDb_var_cbSpeedo1_listrik();
        var_cbSpeedo2_listrik = ceklists.first().getDb_var_cbSpeedo2_listrik();
        var_cbOdometer1_listrik = ceklists.first().getDb_var_cbOdometer1_listrik();
        var_cbOdometer2_listrik = ceklists.first().getDb_var_cbOdometer2_listrik();
        var_cbTacho1_listrik = ceklists.first().getDb_var_cbTacho1_listrik();
        var_cbTacho2_listrik = ceklists.first().getDb_var_cbTacho2_listrik();
        var_cbAccu1_listrik = ceklists.first().getDb_var_cbAccu1_listrik();
        var_cbAccu2_listrik = ceklists.first().getDb_var_cbAccu2_listrik();
//
        var_cbMesin1_lain = ceklists.first().getDb_var_cbMesin1_lain();
        var_cbMesin2_lain = ceklists.first().getDb_var_cbMesin2_lain();
        var_cbHidraulik1_lain = ceklists.first().getDb_var_cbHidraulik1_lain();
        var_cbHidraulik2_lain = ceklists.first().getDb_var_cbHidraulik2_lain();
        var_cbGardan1_lain = ceklists.first().getDb_var_cbGardan1_lain();
        var_cbGardan2_lain = ceklists.first().getDb_var_cbGardan2_lain();
        var_cbAs1_lain = ceklists.first().getDb_var_cbAs1_lain();
        var_cbAs2_lain = ceklists.first().getDb_var_cbAs2_lain();
        var_cbBak1_lain = ceklists.first().getDb_var_cbBak1_lain();
        var_cbBak2_lain = ceklists.first().getDb_var_cbBak2_lain();
        var_cbStnk1_lain = ceklists.first().getDb_var_cbStnk1_lain();
        var_cbStnk2_lain = ceklists.first().getDb_var_cbStnk2_lain();
//                //etsd_bukukir
        var_cbBukukir1_lain = ceklists.first().getDb_var_cbBukukir1_lain();
        var_cbBukukir2_lain = ceklists.first().getDb_var_cbBukukir2_lain();
//                //etSd_ijintrayek
        var_cbTrayek1_lain = ceklists.first().getDb_var_cbTrayek1_lain();
        var_cbTrayek2_lain = ceklists.first().getDb_var_cbTrayek2_lain();
//                //etSd_ijinusaha
        var_cbUsaha1_lain = ceklists.first().getDb_var_cbUsaha1_lain();
        var_cbUsaha2_lain = ceklists.first().getDb_var_cbUsaha2_lain();
        var_cbBukumnl1_lain = ceklists.first().getDb_var_cbBukumnl1_lain();
        var_cbBukumnl2_lain = ceklists.first().getDb_var_cbBukumnl2_lain();

        final Boolean rb = (ceklists.first().getDb_var_rbRadio_listrik() == false) ? ceklists.first().getDb_var_rbTape_listrik() : (ceklists.first().getDb_var_rbTape_listrik() == false) ? ceklists.first().getDb_var_rbCd_listrik() : false;

        String varStnk = "";
        //STNK
        if (!etSd_stnk.getText().toString().equals("")) {
            String etstnk = etSd_stnk.getText().toString();
            String[] splitStnk = etstnk.split("-");
            String stnk_hr = splitStnk[0];
            String stnk_bln = splitStnk[1];
            String stnk_thn = splitStnk[2];
            varStnk = stnk_thn + "-" + stnk_bln + "-" + stnk_hr;
        }

        String varKir = "";
        if (!etSd_bukukir.getText().toString().equals("")) {
            //BUKU KIR
            String etkir = etSd_bukukir.getText().toString();
            String[] splitKir = etkir.split("-");
            String kir_hr = splitKir[0];
            String kir_bln = splitKir[1];
            String kir_thn = splitKir[2];
            varKir = kir_thn + "-" + kir_bln + "-" + kir_hr;
        }

        String varTrayek = "";
        if (!etSd_ijintrayek.getText().toString().equals("")) {
            //TRAYEK
            String ettrayek = etSd_ijintrayek.getText().toString();
            String[] splitTrayek = ettrayek.split("-");
            String trayek_hr = splitTrayek[0];
            String trayek_bln = splitTrayek[1];
            String trayek_thn = splitTrayek[2];
            varTrayek = trayek_thn + "-" + trayek_bln + "-" + trayek_hr;
        }

        String varUsaha = "";
        if (!etSd_ijinusaha.getText().toString().equals("")) {
            //USAHA
            String etusaha = etSd_ijinusaha.getText().toString();
            String[] splitUsaha = etusaha.split("-");
            String usaha_hr = splitUsaha[0];
            String usaha_bln = splitUsaha[1];
            String usaha_thn = splitUsaha[2];
            varUsaha = usaha_thn + "-" + usaha_bln + "-" + usaha_hr;
        }


////
//                bodyKendaraan.first().getPicPenarikan();
//                bodyKendaraan.first().getPicPenyimpanan();
//                bodyKendaraan.first().getPicPenjualan();
//                etTanggalawal.getText().toString();
//                etTanggalakhir.getText().toString();
//                etPembiayaan.getText().toString();

        final String finalStrType = strType;

        final String finalVarStnk = varStnk;
        final String finalVarKir = varKir;
        final String finalVarTrayek = varTrayek;
        final String finalVarUsaha = varUsaha;
        class sendCeklist extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DisInputCeklistActivity.this,"","Processing...",false,false);
            }
            @Override
            protected void onPostExecute(String res) {
                super.onPostExecute(res);
                loading.dismiss();
                showData(res);
            }
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(configuration.KEY_APPID,appid);
                hashMap.put(configuration.KEY_CCODE,ccode);
                hashMap.put(configuration.KEY_TOKEN,_token);

                hashMap.put(configuration.KEY_VENDORID,vendor);
                hashMap.put(configuration.KEY_STATUS,"1");
                hashMap.put(configuration.KEY_PARENT_STATUS,"1");
                hashMap.put(configuration.KEY_TANGGAL,tanggal);
                hashMap.put(configuration.KEY_PUKUL,pukul);
//                hashMap.put(configuration.KEY_lokasi_penarikan,lokasi);
                hashMap.put(configuration.KEY_TYPE, finalStrType);
                hashMap.put(configuration.KEY_MERk,merk);
                hashMap.put(configuration.KEY_TYPE_ID,typeid);
                hashMap.put(configuration.KEY_TYPE_NAME,typename);
                hashMap.put(configuration.KEY_ITEM_ID,itemid);
                hashMap.put(configuration.KEY_TAHUN,tahun);
                hashMap.put(configuration.KEY_STNK,stnk);
                hashMap.put(configuration.KEY_nopol,nopol);
                hashMap.put(configuration.KEY_kmditarik,kmditarik);
                hashMap.put(configuration.KEY_meterbensin,meterbensin);
                hashMap.put(configuration.KEY_cabang,cabang);
                hashMap.put(configuration.KEY_nomesin,nomesin);
                hashMap.put(configuration.KEY_norangka,norangka);
                hashMap.put(configuration.KEY_warna,warna);
                hashMap.put(configuration.KEY_kmditerima,kmditerima);
                hashMap.put(configuration.KEY_lokasi_pool,lokasipool);

                hashMap.put(configuration.KEY_grill1_fisikmuka, String.valueOf(var_cbGrill1_fisikmuka));
                hashMap.put(configuration.KEY_grill2_fisikmuka, String.valueOf(var_cbGrill2_fisikmuka));
                hashMap.put(configuration.KEY_lampu1_fisikmuka,String.valueOf(var_cbLampu1_fisikmuka));
                hashMap.put(configuration.KEY_lampu2_fisikmuka,String.valueOf(var_cbLampu2_fisikmuka));
                hashMap.put(configuration.KEY_lampusen1_fisikmuka,String.valueOf(var_cbLampusen1_fisikmuka));
                hashMap.put(configuration.KEY_lampusen2_fisikmuka,String.valueOf(var_cbLampusen2_fisikmuka));
                hashMap.put(configuration.KEY_bamper1_fisikmuka,String.valueOf(var_cbBamper1_fisikmuka));
                hashMap.put(configuration.KEY_bamper2_fisikmuka,String.valueOf(var_cbBamper2_fisikmuka));
                hashMap.put(configuration.KEY_emblem1_fisikmuka,String.valueOf(var_cbEmblem1_fisikmuka));
                hashMap.put(configuration.KEY_emblem2_fisikmuka,String.valueOf(var_cbEmblem2_fisikmuka));
                hashMap.put(configuration.KEY_tanduk1_fisikmuka,String.valueOf(var_cbTanduk1_fisikmuka));
                hashMap.put(configuration.KEY_tanduk2_fisikmuka,String.valueOf(var_cbTanduk2_fisikmuka));

                hashMap.put(configuration.KEY_footstep1_fisikkiri,String.valueOf(var_cbFootstep1_fisikkiri));
                hashMap.put(configuration.KEY_footstep2_fisikkiri,String.valueOf(var_cbFootstep2_fisikkiri));
                hashMap.put(configuration.KEY_pintudpn1_fisikkiri,String.valueOf(var_cbPintudpn1_fisikkiri));
                hashMap.put(configuration.KEY_pintudpn2_fisikkiri,String.valueOf(var_cbPintudpn2_fisikkiri));
                hashMap.put(configuration.KEY_pintublk1_fisikkiri,String.valueOf(var_cbPintublk1_fisikkiri));
                hashMap.put(configuration.KEY_pintublk2_fisikkiri,String.valueOf(var_cbPintublk2_fisikkiri));
                hashMap.put(configuration.KEY_bamper1_fisikkiri,String.valueOf(var_cbBamper1_fisikkiri));
                hashMap.put(configuration.KEY_bamper2_fisikkiri,String.valueOf(var_cbBamper2_fisikkiri));
                hashMap.put(configuration.KEY_fenderblk1_fisikkiri,String.valueOf(var_cbFenderblk1_fisikkiri));
                hashMap.put(configuration.KEY_fenderblk2_fisikkiri,String.valueOf(var_cbFenderblk2_fisikkiri));
                hashMap.put(configuration.KEY_spion1_fisikkiri,String.valueOf(var_cbSpion1_fisikkiri));
                hashMap.put(configuration.KEY_spion2_fisikkiri,String.valueOf(var_cbSpion2_fisikkiri));
                hashMap.put(configuration.KEY_emblem1_fisikkiri,String.valueOf(var_cbEmblem1_fisikkiri));
                hashMap.put(configuration.KEY_emblem2_fisikkiri,String.valueOf(var_cbEmblem2_fisikkiri));
//                hashMap.put(configuration.KEY_banstandard_fisikkanan,(var_rbBan_fisikkiri);
//                hashMap.put(configuration.KEY_banradial_fisikkanan,(rbBanradial1_fisikkiri.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_ban1_fisikkiri,String.valueOf(var_cbBan1_fisikkiri));
                hashMap.put(configuration.KEY_ban2_fisikkiri,String.valueOf(var_spBan2_fisikkiri));
//
                //hashMap.put(configuration.KEY_ban_fisikkiri,(ceklists.first().getDb_var_rbBanstandard_fisikkiri() == false) ? String.valueOf(ceklists.first().getDb_var_rbBanradial_fisikkiri()) : String.valueOf(ceklists.first().getDb_var_rbBanstandard_fisikkiri()));
//                hashMap.put(configuration.KEY_ban_fisikkiri,String.valueOf(rb));
                hashMap.put(configuration.KEY_ban2_fisikkiri,String.valueOf(var_cbBan1_fisikkiri));

                hashMap.put(configuration.KEY_velgstandard_fisikkiri,String.valueOf(var_rbVelgstandard_fisikkiri));
                hashMap.put(configuration.KEY_velgracing_fisikkiri,String.valueOf(var_rbVelgracing_fisikkiri));
                hashMap.put(configuration.KEY_velg1_fisikkiri,String.valueOf(var_cbVelg1_fisikkiri));
                hashMap.put(configuration.KEY_velg2_fisikkiri,String.valueOf(var_cbVelg2_fisikkiri));
                hashMap.put(configuration.KEY_dop1_fisikkiri,String.valueOf(var_cbDop1_fisikkiri));
                hashMap.put(configuration.KEY_dop2_fisikkiri,String.valueOf(var_cbDop2_fisikkiri));
                hashMap.put(configuration.KEY_dopblk1_fisikkiri,String.valueOf(var_cbDopBlk1_fisikkiri));
                hashMap.put(configuration.KEY_dopblk2_fisikkiri,String.valueOf(var_cbDopBlk2_fisikkiri));

                hashMap.put(configuration.KEY_spoiler1_fisikblkg,String.valueOf(var_cbSpoiler1_fisikblkg));
                hashMap.put(configuration.KEY_spoiler2_fisikblkg,String.valueOf(var_cbSpoiler2_fisikblkg));
                hashMap.put(configuration.KEY_lampu1_fisikblkg,String.valueOf(var_cbLampu1_fisikblkg));
                hashMap.put(configuration.KEY_lampu2_fisikblkg,String.valueOf(var_cbLampu2_fisikblkg));
                hashMap.put(configuration.KEY_lampusen1_fisikblkg,String.valueOf(var_cbLampusen1_fisikblkg));
                hashMap.put(configuration.KEY_lampusen2_fisikblkg,String.valueOf(var_cbLampusen2_fisikblkg));
                hashMap.put(configuration.KEY_bamper1_fisikblkg,String.valueOf(var_cbBamper1_fisikblkg));
                hashMap.put(configuration.KEY_bamper2_fisikblkg,String.valueOf(var_cbBamper2_fisikblkg));
                hashMap.put(configuration.KEY_emblem1_fisikblkg,String.valueOf(var_cbEmblem1_fisikblkg));
                hashMap.put(configuration.KEY_emblem2_fisikblkg,String.valueOf(var_cbEmblem2_fisikblkg));
                hashMap.put(configuration.KEY_knalpot1_fisikblkg,String.valueOf(var_cbKnalpot1_fisikblkg));
                hashMap.put(configuration.KEY_knalpot2_fisikblkg,String.valueOf(var_cbKnalpot2_fisikblkg));
//
                hashMap.put(configuration.KEY_foot1_fisikkanan,String.valueOf(var_cbFoot1_fisikkanan));
                hashMap.put(configuration.KEY_foot2_fisikkanan,String.valueOf(var_cbFoot2_fisikkanan));
                hashMap.put(configuration.KEY_pintudpn1_fisikkanan,String.valueOf(var_cbPintudpn1_fisikkanan));
                hashMap.put(configuration.KEY_pintudpn2_fisikkanan,String.valueOf(var_cbPintudpn2_fisikkanan));
                hashMap.put(configuration.KEY_pintublk1_fisikkanan,String.valueOf(var_cbPintublk1_fisikkanan));
                hashMap.put(configuration.KEY_pintublk2_fisikkanan,String.valueOf(var_cbPintublk2_fisikkanan));
                hashMap.put(configuration.KEY_bamper1_fisikkanan,String.valueOf(var_cbBamper1_fisikkanan));
                hashMap.put(configuration.KEY_bamper2_fisikkanan,String.valueOf(var_cbBamper2_fisikkanan));
                hashMap.put(configuration.KEY_fenderblk1_fisikkanan,String.valueOf(var_cbFenderblk1_fisikkanan));
                hashMap.put(configuration.KEY_fenderblk2_fisikkanan,String.valueOf(var_cbFenderblk2_fisikkanan));
                hashMap.put(configuration.KEY_spion1_fisikkanan,String.valueOf(var_cbSpion1_fisikkanan));
                hashMap.put(configuration.KEY_spion2_fisikkanan,String.valueOf(var_cbSpion2_fisikkanan));
                hashMap.put(configuration.KEY_emblem1_fisikkanan,String.valueOf(var_cbEmblem1_fisikkanan));
                hashMap.put(configuration.KEY_emblem2_fisikkanan,String.valueOf(var_cbEmblem2_fisikkanan));
//
//                hashMap.put(configuration.KEY_ban_fisikkanan,(ceklists.first().getDb_var_rbBanstandard_fisikkanan() == false) ? String.valueOf(ceklists.first().getDb_var_rbBanradial_fisikkanan()) : String.valueOf(ceklists.first().getDb_var_rbBanstandard_fisikkanan()));
//                hashMap.put(configuration.KEY_ban_fisikkanan,(ceklists.first().getDb_var_rbBanstandard_fisikkanan() == false) ? String.valueOf(ceklists.first().getDb_var_rbBanradial_fisikkanan()) : String.valueOf(ceklists.first().getDb_var_rbBanstandard_fisikkanan()));
//                hashMap.put(configuration.KEY_ban2_fisikkanan,String.valueOf(ceklists.first().getDb_var_cbBan1_fisikkanan()));
//
//                hashMap.put(configuration.KEY_velg_fisikkanan,(ceklists.first().getDb_var_rbVelgstandard_fisikkanan() == false) ? String.valueOf(ceklists.first().getDb_var_rbVelgracing_fisikkanan()) : String.valueOf(ceklists.first().getDb_var_rbVelgstandard_fisikkanan()));
                hashMap.put(configuration.KEY_velg1_fisikkanan,String.valueOf(var_cbVelg1_fisikkanan));
                hashMap.put(configuration.KEY_velg2_fisikkanan,String.valueOf(var_cbVelg2_fisikkanan));
//
                hashMap.put(configuration.KEY_dop1_fisikkanan,String.valueOf(var_cbDop1_fisikkanan));
                hashMap.put(configuration.KEY_dop2_fisikkanan,String.valueOf(var_cbDop2_fisikkanan));
                hashMap.put(configuration.KEY_kunciktk1_perlengkapan,String.valueOf(var_cbKunciktk1_perlengkapan));
                hashMap.put(configuration.KEY_kunciktk2_perlengkapan,String.valueOf(var_cbKunciktk2_perlengkapan));
                hashMap.put(configuration.KEY_spion1_perlengkapan,String.valueOf(var_cbSpion1_perlengkapan));
                hashMap.put(configuration.KEY_spion2_perlengkapan,String.valueOf(var_cbSpion2_perlengkapan));
                hashMap.put(configuration.KEY_jok1_perlengkapan,String.valueOf(var_cbJok1_perlengkapan));
                hashMap.put(configuration.KEY_jok2_perlengkapan,String.valueOf(var_cbJok2_perlengkapan));
                hashMap.put(configuration.KEY_sarung1_perlengkapan,String.valueOf(var_cbSarung1_perlengkapan));
                hashMap.put(configuration.KEY_sarung2_perlengkapan,String.valueOf(var_cbSarung2_perlengkapan));
                hashMap.put(configuration.KEY_sandaran1_perlengkapan,String.valueOf(var_cbSandaran1_perlengkapan));
                hashMap.put(configuration.KEY_sandaran2_perlengkapan,String.valueOf(var_cbSandaran2_perlengkapan));
                hashMap.put(configuration.KEY_karpet1_perlengkapan,String.valueOf(var_cbKarpet1_perlengkapan));
                hashMap.put(configuration.KEY_karpet2_perlengkapan,String.valueOf(var_cbKarpet2_perlengkapan));
                hashMap.put(configuration.KEY_pelindung1_perlengkapan,String.valueOf(var_cbPelindung1_perlengkapan));
                hashMap.put(configuration.KEY_pelindung2_perlengkapan,String.valueOf(var_cbPelindung2_perlengkapan));
                hashMap.put(configuration.KEY_segitiga1_perlengkapan,String.valueOf(var_cbSegitiga1_perlengkapan));
                hashMap.put(configuration.KEY_segitiga2_perlengkapan,String.valueOf(var_cbSegitiga2_perlengkapan));
                hashMap.put(configuration.KEY_tool1_perlengkapan,String.valueOf(var_cbTool1_perlengkapan));
                hashMap.put(configuration.KEY_tool2_perlengkapan,String.valueOf(var_cbTool2_perlengkapan));
                hashMap.put(configuration.KEY_cadangan1_perlengkapan,String.valueOf(var_cbCadangan1_perlengkapan));
                hashMap.put(configuration.KEY_cadangan2_perlengkapan,String.valueOf(var_cbCadangan2_perlengkapan));
                hashMap.put(configuration.KEY_kunciban1_perlengkapan,String.valueOf(var_cbKunciban1_perlengkapan));
                hashMap.put(configuration.KEY_kunciban2_perlengkapan,String.valueOf(var_cbKunciban2_perlengkapan));
                hashMap.put(configuration.KEY_dongkrak1_perlengkapan,String.valueOf(var_cbDongkrak1_perlengkapan));
                hashMap.put(configuration.KEY_dongkrak2_perlengkapan,String.valueOf(var_cbDongkrak2_perlengkapan));
                hashMap.put(configuration.KEY_antena1_perlengkapan,String.valueOf(var_cbAntena1_perlengkapan));
                hashMap.put(configuration.KEY_antena2_perlengkapan,String.valueOf(var_cbAntena2_perlengkapan));
                hashMap.put(configuration.KEY_airbag1_perlengkapan,String.valueOf(var_cbAirbag1_perlengkapan));
                hashMap.put(configuration.KEY_airbag2_perlengkapan,String.valueOf(var_cbAirbag2_perlengkapan));

                hashMap.put(configuration.KEY_lampukbt1_listrik,String.valueOf(var_cbLampukbt1_listrik));
                hashMap.put(configuration.KEY_lampukbt2_listrik,String.valueOf(var_cbLampukbt2_listrik));
                hashMap.put(configuration.KEY_wiperkacadpn1_listrik,String.valueOf(var_cbWiperkacadpn1_listrik));
                hashMap.put(configuration.KEY_wiperkacadpn2_listrik,String.valueOf(var_cbWiperkacadpn2_listrik));
                hashMap.put(configuration.KEY_wiperkacablk1_listrik,String.valueOf(var_cbWiperkacablk1_listrik));
                hashMap.put(configuration.KEY_wiperkacablk2_listrik,String.valueOf(var_cbWiperkacablk2_listrik));
                hashMap.put(configuration.KEY_klakson1_listrik,String.valueOf(var_cbKlakson1_listrik));
                hashMap.put(configuration.KEY_klakson2_listrik,String.valueOf(var_cbKlakson2_listrik));
                hashMap.put(configuration.KEY_alarm1_listrik,String.valueOf(var_cbAlarm1_listrik));
                hashMap.put(configuration.KEY_alarm2_listrik,String.valueOf(var_cbAlarm2_listrik));
                hashMap.put(configuration.KEY_jam1_listrik,String.valueOf(var_cbJam1_listrik));
                hashMap.put(configuration.KEY_jam2_listrik,String.valueOf(var_cbJam2_listrik));
                hashMap.put(configuration.KEY_lighter1_listrik,String.valueOf(var_cbLighter1_listrik));
                hashMap.put(configuration.KEY_lighter2_listrik,String.valueOf(var_cbLighter2_listrik));

                //hashMap.put(configuration.KEY_radio_listrik,(ceklists.first().getDb_var_rbRadio_listrik() == false) ? String.valueOf(ceklists.first().getDb_var_rbTape_listrik()) : (ceklists.first().getDb_var_rbTape_listrik() == false) ? String.valueOf(ceklists.first().getDb_var_rbCd_listrik()) : String.valueOf(false));
                hashMap.put(configuration.KEY_radio1_listrik,String.valueOf(var_cbRadio1_listrik));
                hashMap.put(configuration.KEY_radio2_listrik,String.valueOf(var_cbRadio2_listrik));
                hashMap.put(configuration.KEY_powersup1_listrik,String.valueOf(var_cbPowersup1_listrik));
                hashMap.put(configuration.KEY_powersup2_listrik,String.valueOf(var_cbPowersup2_listrik));
                hashMap.put(configuration.KEY_speaker1_listrik,String.valueOf(var_cbSpeaker1_listrik));
                hashMap.put(configuration.KEY_speaker2_listrik,String.valueOf(var_cbSpeaker2_listrik));
                hashMap.put(configuration.KEY_ac1_listrik,String.valueOf(var_cbAc1_listrik));
                hashMap.put(configuration.KEY_ac2_listrik,String.valueOf(var_cbAc2_listrik));
                hashMap.put(configuration.KEY_powerwin1_listrik,String.valueOf(var_cbPowerwin1_listrik));
                hashMap.put(configuration.KEY_powerwin2_listrik,String.valueOf(var_cbPowerwin2_listrik));
                hashMap.put(configuration.KEY_central1_listrik,String.valueOf(var_cbCentral1_listrik));
                hashMap.put(configuration.KEY_central2_listrik,String.valueOf(var_cbCentral2_listrik));
                hashMap.put(configuration.KEY_remote1_listrik,String.valueOf(var_cbRemote1_listrik));
                hashMap.put(configuration.KEY_remote2_listrik,String.valueOf(var_cbRemote2_listrik));
                hashMap.put(configuration.KEY_speedo1_listrik,String.valueOf(var_cbSpeedo1_listrik));
                hashMap.put(configuration.KEY_speedo2_listrik,String.valueOf(var_cbSpeedo2_listrik));
                hashMap.put(configuration.KEY_odometer1_listrik,String.valueOf(var_cbOdometer1_listrik));
                hashMap.put(configuration.KEY_odometer2_listrik,String.valueOf(var_cbOdometer2_listrik));
                hashMap.put(configuration.KEY_tacho1_listrik,String.valueOf(var_cbTacho1_listrik));
                hashMap.put(configuration.KEY_tacho2_listrik,String.valueOf(var_cbTacho2_listrik));
                hashMap.put(configuration.KEY_accu1_listrik,String.valueOf(var_cbAccu1_listrik));
                hashMap.put(configuration.KEY_accu2_listrik,String.valueOf(var_cbAccu2_listrik));

                hashMap.put(configuration.KEY_mesin1_lain,String.valueOf(var_cbMesin1_lain));
                hashMap.put(configuration.KEY_mesin2_lain,String.valueOf(var_cbMesin2_lain));
                hashMap.put(configuration.KEY_hidraulik1_lain,String.valueOf(var_cbHidraulik1_lain));
                hashMap.put(configuration.KEY_hidraulik2_lain,String.valueOf(var_cbHidraulik2_lain));
                hashMap.put(configuration.KEY_gardan1_lain,String.valueOf(var_cbGardan1_lain));
                hashMap.put(configuration.KEY_gardan2_lain,String.valueOf(var_cbGardan2_lain));
                hashMap.put(configuration.KEY_as1_lain,String.valueOf(var_cbAs1_lain));
                hashMap.put(configuration.KEY_as2_lain,String.valueOf(var_cbAs2_lain));
                hashMap.put(configuration.KEY_bak1_lain,String.valueOf(var_cbBak1_lain));
                hashMap.put(configuration.KEY_bak2_lain,String.valueOf(var_cbBak2_lain));

                if (!etSd_stnk.getText().toString().equals("")) {
                    hashMap.put(configuration.KEY_stnk_lain, finalVarStnk);
                }
                hashMap.put(configuration.KEY_stnk1_lain,String.valueOf(var_cbStnk1_lain));
                hashMap.put(configuration.KEY_stnk2_lain,String.valueOf(var_cbStnk2_lain));

                if (!etSd_bukukir.getText().toString().equals("")) {
                    hashMap.put(configuration.KEY_bukukir_lain, finalVarKir);
                }
                hashMap.put(configuration.KEY_bukukir1_lain,String.valueOf(var_cbBukukir1_lain));
                hashMap.put(configuration.KEY_bukukir2_lain,String.valueOf(var_cbBukukir2_lain));

                if (!etSd_ijintrayek.getText().toString().equals("")) {
                    hashMap.put(configuration.KEY_trayek_lain, finalVarTrayek);
                }
                hashMap.put(configuration.KEY_trayek1_lain,String.valueOf(var_cbTrayek1_lain));
                hashMap.put(configuration.KEY_trayek2_lain,String.valueOf(var_cbTrayek2_lain));

                if (!etSd_ijinusaha.getText().toString().equals("")) {
                    hashMap.put(configuration.KEY_usaha_lain, finalVarUsaha);
                }
                hashMap.put(configuration.KEY_usaha1_lain,String.valueOf(var_cbUsaha1_lain));
                hashMap.put(configuration.KEY_usaha2_lain,String.valueOf(var_cbUsaha2_lain));
                hashMap.put(configuration.KEY_bukumnl1_lain,String.valueOf(var_cbBukumnl1_lain));
                hashMap.put(configuration.KEY_bukumnl2_lain,String.valueOf(var_cbBukumnl2_lain));

//                hashMap.put(configuration.KEY_picPenarikan,bodyKendaraan.first().getPicPenarikan());
//                hashMap.put(configuration.KEY_picPenyimpanan,bodyKendaraan.first().getPicPenyimpanan());
//                hashMap.put(configuration.KEY_picPenjualan,bodyKendaraan.first().getPicPenjualan());
//                hashMap.put(configuration.KEY_startDate,etTanggalawal.getText().toString());
//                hashMap.put(configuration.KEY_endDate,etTanggalakhir.getText().toString());
//                hashMap.put(configuration.KEY_noPembiayaan,etPembiayaan.getText().toString());

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_BASTK,hashMap);
                Log.d("kirim", s);
                return s;
            }
        }
        sendCeklist ge = new sendCeklist();
        ge.execute();
    }
    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.wtf("cekjson","hasil" + jsonObject);
            code = jsonObject.getInt(configuration.TAG_CODE);
            message = jsonObject.getString(configuration.TAG_MESSAGE);
            Log.wtf("cekmessage","hasil" + message);
            if(code == 200){
                Intent i = new Intent(DisInputCeklistActivity.this, DisInputFotoActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            Intent i = new Intent(this, MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
//            finish();

            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            //e.printStackTrace();
            Toast.makeText(DisInputCeklistActivity.this,"Check Your Connection",Toast.LENGTH_SHORT).show();
        }
    }



    private void saveCeklist(){

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
