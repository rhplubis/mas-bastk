package com.tunasrent.auctionapps.dispatcher;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.tunasrent.auctionapps.taksasi.TaksasiCekCeklistActivity;
import com.tunasrent.auctionapps.taksasi.TaksasiCekFotoActivity;
import com.tunasrent.auctionapps.util.UserSessionManager;
import com.tunasrent.auctionapps.util.configuration;

import java.util.HashMap;

public class DisApprovalCeklistActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnNext;

    UserSessionManager session;
    String _name, _fullname, _appid, _ccode, _token, _group;

    String message;
    String code;
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
    int var_spBan2_fisikkanan;
    Boolean var_cbVelg1_fisikkanan=false;
    Boolean var_cbVelg2_fisikkanan=false;
    Boolean var_cbDop1_fisikkanan=false;
    Boolean var_cbDop2_fisikkanan=false;
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

    String[] stnk;
    String[] bukukir;
    String[] trayek;
    String[] usaha;
    String nopol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_approval_ceklist);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ceklist Dispatcher & BP");
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
            var_cbGrill1_fisikmuka = (b.getInt("grill1_fisikmuka") == 0) ? false : true;
            var_cbGrill2_fisikmuka = (b.getInt("grill2_fisikmuka") == 0) ? false : true;
            var_cbLampu1_fisikmuka= (b.getInt("lampu1_fisikmuka") == 0) ? false : true;
            var_cbLampu2_fisikmuka = (b.getInt("lampu2_fisikmuka") == 0) ? false : true;
            var_cbLampusen1_fisikmuka= (b.getInt("lampusen1_fisikmuka") == 0) ? false : true;
            var_cbLampusen2_fisikmuka = (b.getInt("lampusen2_fisikmuka") == 0) ? false : true;
            var_cbBamper1_fisikmuka= (b.getInt("bamper1_fisikmuka") == 0) ? false : true;
            var_cbBamper2_fisikmuka = (b.getInt("bamper2_fisikmuka") == 0) ? false : true;
            var_cbEmblem1_fisikmuka= (b.getInt("emblem1_fisikmuka") == 0) ? false : true;
            var_cbEmblem2_fisikmuka = (b.getInt("emblem2_fisikmuka") == 0) ? false : true;
            var_cbTanduk1_fisikmuka= (b.getInt("tanduk1_fisikmuka") == 0) ? false : true;
            var_cbTanduk2_fisikmuka = (b.getInt("tanduk2_fisikmuka") == 0) ? false : true;

            var_cbFootstep1_fisikkiri= (b.getInt("footstep1_fisikkiri") == 0) ? false : true;
            var_cbFootstep2_fisikkiri= (b.getInt("footstep2_fisikkiri") == 0) ? false : true;
            var_cbPintudpn1_fisikkiri= (b.getInt("pintudpn1_fisikkiri") == 0) ? false : true;
            var_cbPintudpn2_fisikkiri= (b.getInt("pintudpn2_fisikkiri") == 0) ? false : true;
            var_cbPintublk1_fisikkiri= (b.getInt("pintublk1_fisikkiri") == 0) ? false : true;
            var_cbPintublk2_fisikkiri= (b.getInt("pintublk2_fisikkiri") == 0) ? false : true;
            var_cbBamper1_fisikkiri= (b.getInt("bamper1_fisikkiri") == 0) ? false : true;
            var_cbBamper2_fisikkiri= (b.getInt("bamper2_fisikkiri") == 0) ? false : true;
            var_cbFenderblk1_fisikkiri= (b.getInt("fenderblk1_fisikkiri") == 0) ? false : true;
            var_cbFenderblk2_fisikkiri= (b.getInt("fenderblk2_fisikkiri") == 0) ? false : true;
            var_cbSpion1_fisikkiri= (b.getInt("spion1_fisikkiri") == 0) ? false : true;
            var_cbSpion2_fisikkiri= (b.getInt("spion2_fisikkiri") == 0) ? false : true;
            var_cbEmblem1_fisikkiri= (b.getInt("emblem1_fisikkiri") == 0) ? false : true;
            var_cbEmblem2_fisikkiri= (b.getInt("emblem2_fisikkiri") == 0) ? false : true;
            var_rbBan_fisikkiri= (b.getInt("ban_fisikkiri") == 0) ? false : true;
            var_cbBan1_fisikkiri= (b.getInt("ban2_fisikkiri") == 0) ? false : true;
//            var_spBan2_fisikkiri = (b.getInt("grill1_fisikmuka") == 0) ? false : true;
//            var_vspBan2_fisikkiri = (b.getInt("grill1_fisikmuka") == 0) ? false : true;
            var_rbVelgstandard_fisikkiri= (b.getInt("velgstandard_fisikkiri") == 0) ? false : true;
            var_rbVelgracing_fisikkiri= (b.getInt("velgracing_fisikkiri") == 0) ? false : true;
            var_cbVelg1_fisikkiri= (b.getInt("velg1_fisikkiri") == 0) ? false : true;
            var_cbVelg2_fisikkiri= (b.getInt("velg2_fisikkiri") == 0) ? false : true;
            var_cbDop1_fisikkiri= (b.getInt("dop1_fisikkiri") == 0) ? false : true;
            var_cbDop2_fisikkiri= (b.getInt("dop2_fisikkiri") == 0) ? false : true;
//            var_cbDopBlk1_fisikkiri= (b.getInt("dopblk1_fisikkiri") == 0) ? false : true;
//            var_cbDopBlk2_fisikkiri= (b.getInt("dopblk2_fisikkiri") == 0) ? false : true;

            var_cbSpoiler1_fisikblkg= (b.getInt("spoiler1_fisikblkg") == 0) ? false : true;
            var_cbSpoiler2_fisikblkg= (b.getInt("spoiler2_fisikblkg") == 0) ? false : true;
            var_cbLampu1_fisikblkg= (b.getInt("lampu1_fisikblkg") == 0) ? false : true;
            var_cbLampu2_fisikblkg= (b.getInt("lampu2_fisikblkg") == 0) ? false : true;
            var_cbLampusen1_fisikblkg= (b.getInt("lampusen1_fisikblkg") == 0) ? false : true;
            var_cbLampusen2_fisikblkg= (b.getInt("lampusen2_fisikblkg") == 0) ? false : true;
            var_cbBamper1_fisikblkg= (b.getInt("bamper1_fisikblkg") == 0) ? false : true;
            var_cbBamper2_fisikblkg= (b.getInt("bamper2_fisikblkg") == 0) ? false : true;
            var_cbEmblem1_fisikblkg= (b.getInt("emblem1_fisikblkg") == 0) ? false : true;
            var_cbEmblem2_fisikblkg= (b.getInt("emblem2_fisikblkg") == 0) ? false : true;
            var_cbKnalpot1_fisikblkg= (b.getInt("knalpot1_fisikblkg") == 0) ? false : true;
            var_cbKnalpot2_fisikblkg= (b.getInt("knalpot2_fisikblkg") == 0) ? false : true;

            var_cbFoot1_fisikkanan= (b.getInt("foot1_fisikkanan") == 0) ? false : true;
            var_cbFoot2_fisikkanan= (b.getInt("foot2_fisikkanan") == 0) ? false : true;
            var_cbPintudpn1_fisikkanan= (b.getInt("pintudpn1_fisikkanan") == 0) ? false : true;
            var_cbPintudpn2_fisikkanan= (b.getInt("pintudpn2_fisikkanan") == 0) ? false : true;
            var_cbPintublk1_fisikkanan= (b.getInt("pintublk1_fisikkanan") == 0) ? false : true;
            var_cbPintublk2_fisikkanan= (b.getInt("pintublk2_fisikkanan") == 0) ? false : true;
            var_cbBamper1_fisikkanan= (b.getInt("bamper1_fisikkanan") == 0) ? false : true;
            var_cbBamper2_fisikkanan= (b.getInt("bamper2_fisikkanan") == 0) ? false : true;
            var_cbFenderblk1_fisikkanan= (b.getInt("fenderblk1_fisikkanan") == 0) ? false : true;
            var_cbFenderblk2_fisikkanan= (b.getInt("fenderblk2_fisikkanan") == 0) ? false : true;
            var_cbSpion1_fisikkanan= (b.getInt("spion1_fisikkanan") == 0) ? false : true;
            var_cbSpion2_fisikkanan= (b.getInt("spion2_fisikkanan") == 0) ? false : true;
            var_cbEmblem1_fisikkanan= (b.getInt("emblem1_fisikkanan") == 0) ? false : true;
            var_cbEmblem2_fisikkanan= (b.getInt("emblem2_fisikkanan") == 0) ? false : true;
            var_rbBan_fisikkanan= (b.getInt("ban_fisikkanan") == 0) ? false : true;
            var_cbBan1_fisikkanan= (b.getInt("ban2_fisikkanan") == 0) ? false : true;
            var_cbVelg1_fisikkanan= (b.getInt("velg1_fisikkanan") == 0) ? false : true;
            var_cbVelg2_fisikkanan= (b.getInt("velg2_fisikkanan") == 0) ? false : true;
            var_cbDop1_fisikkanan= (b.getInt("dop1_fisikkanan") == 0) ? false : true;
            var_cbDop2_fisikkanan= (b.getInt("dop2_fisikkanan") == 0) ? false : true;

//            var_cbDopBlk1_fisikkanan= (b.getInt("grill1_fisikmuka") == 0) ? false : true;
//            var_cbDopBlk2_fisikkanan= (b.getInt("grill1_fisikmuka") == 0) ? false : true;

            var_cbKunciktk1_perlengkapan= (b.getInt("kunciktk1_perlengkapan") == 0) ? false : true;
            var_cbKunciktk2_perlengkapan= (b.getInt("kunciktk2_perlengkapan") == 0) ? false : true;
            var_cbSpion1_perlengkapan= (b.getInt("spion1_perlengkapan") == 0) ? false : true;
            var_cbSpion2_perlengkapan= (b.getInt("spion2_perlengkapan") == 0) ? false : true;
            var_cbJok1_perlengkapan= (b.getInt("jok1_perlengkapan") == 0) ? false : true;
            var_cbJok2_perlengkapan= (b.getInt("jok2_perlengkapan") == 0) ? false : true;
            var_cbSarung1_perlengkapan= (b.getInt("sarung1_perlengkapan") == 0) ? false : true;
            var_cbSarung2_perlengkapan= (b.getInt("sarung2_perlengkapan") == 0) ? false : true;
            var_cbSandaran1_perlengkapan= (b.getInt("sandaran1_perlengkapan") == 0) ? false : true;
            var_cbSandaran2_perlengkapan= (b.getInt("sandaran2_perlengkapan") == 0) ? false : true;
            var_cbKarpet1_perlengkapan= (b.getInt("karpet1_perlengkapan") == 0) ? false : true;
            var_cbKarpet2_perlengkapan= (b.getInt("karpet2_perlengkapan") == 0) ? false : true;
            var_cbPelindung1_perlengkapan= (b.getInt("pelindung1_perlengkapan") == 0) ? false : true;
            var_cbPelindung2_perlengkapan= (b.getInt("pelindung2_perlengkapan") == 0) ? false : true;
            var_cbSegitiga1_perlengkapan= (b.getInt("segitiga1_perlengkapan") == 0) ? false : true;
            var_cbSegitiga2_perlengkapan= (b.getInt("segitiga2_perlengkapan") == 0) ? false : true;
            Log.wtf("segitiga",""+var_cbSegitiga2_perlengkapan);
            var_cbTool1_perlengkapan= (b.getInt("tool1_perlengkapan") == 0) ? false : true;
            var_cbTool2_perlengkapan= (b.getInt("tool2_perlengkapan") == 0) ? false : true;
            var_cbCadangan1_perlengkapan= (b.getInt("cadangan1_perlengkapan") == 0) ? false : true;
            var_cbCadangan2_perlengkapan= (b.getInt("cadangan2_perlengkapan") == 0) ? false : true;
            var_cbKunciban1_perlengkapan= (b.getInt("kunciban1_perlengkapan") == 0) ? false : true;
            var_cbKunciban2_perlengkapan= (b.getInt("kunciban2_perlengkapan") == 0) ? false : true;
            var_cbDongkrak1_perlengkapan= (b.getInt("dongkrak1_perlengkapan") == 0) ? false : true;
            var_cbDongkrak2_perlengkapan= (b.getInt("dongkrak2_perlengkapan") == 0) ? false : true;
            var_cbAntena1_perlengkapan= (b.getInt("antena1_perlengkapan") == 0) ? false : true;
            var_cbAntena2_perlengkapan= (b.getInt("antena2_perlengkapan") == 0) ? false : true;
            var_cbAirbag1_perlengkapan= (b.getInt("airbag1_perlengkapan") == 0) ? false : true;
            var_cbAirbag2_perlengkapan= (b.getInt("airbag2_perlengkapan") == 0) ? false : true;

            var_cbLampukbt1_listrik= (b.getInt("lampukbt1_listrik") == 0) ? false : true;
            var_cbLampukbt2_listrik= (b.getInt("lampukbt2_listrik") == 0) ? false : true;
            var_cbWiperkacadpn1_listrik= (b.getInt("wiperkacadpn1_listrik") == 0) ? false : true;
            var_cbWiperkacadpn2_listrik= (b.getInt("wiperkacadpn2_listrik") == 0) ? false : true;
            var_cbWiperkacablk1_listrik= (b.getInt("wiperkacablk1_listrik") == 0) ? false : true;
            var_cbWiperkacablk2_listrik= (b.getInt("wiperkacablk2_listrik") == 0) ? false : true;
            var_cbKlakson1_listrik= (b.getInt("klakson1_listrik") == 0) ? false : true;
            var_cbKlakson2_listrik= (b.getInt("klakson2_listrik") == 0) ? false : true;
            var_cbAlarm1_listrik= (b.getInt("alarm1_listrik") == 0) ? false : true;
            var_cbAlarm2_listrik= (b.getInt("alarm2_listrik") == 0) ? false : true;
            var_cbJam1_listrik= (b.getInt("jam1_listrik") == 0) ? false : true;
            var_cbJam2_listrik= (b.getInt("jam2_listrik") == 0) ? false : true;
            var_cbLighter1_listrik= (b.getInt("lighter1_listrik") == 0) ? false : true;
            var_cbLighter2_listrik= (b.getInt("lighter2_listrik") == 0) ? false : true;
            var_rbRadio_listrik= (b.getInt("radio_listrik") == 0) ? false : true;
            var_cbRadio1_listrik= (b.getInt("radio1_listrik") == 0) ? false : true;
            var_cbRadio2_listrik= (b.getInt("radio2_listrik") == 0) ? false : true;
            var_cbPowersup1_listrik= (b.getInt("powersup1_listrik") == 0) ? false : true;
            var_cbPowersup2_listrik= (b.getInt("powersup2_listrik") == 0) ? false : true;
            var_cbSpeaker1_listrik= (b.getInt("speaker1_listrik") == 0) ? false : true;
            var_cbSpeaker2_listrik= (b.getInt("speaker2_listrik") == 0) ? false : true;
            var_cbAc1_listrik= (b.getInt("ac1_listrik") == 0) ? false : true;
            var_cbAc2_listrik= (b.getInt("ac2_listrik") == 0) ? false : true;
            var_cbPowerwin1_listrik= (b.getInt("powerwin1_listrik") == 0) ? false : true;
            var_cbPowerwin2_listrik= (b.getInt("powerwin2_listrik") == 0) ? false : true;
            var_cbCentral1_listrik= (b.getInt("central1_listrik") == 0) ? false : true;
            var_cbCentral2_listrik= (b.getInt("central2_listrik") == 0) ? false : true;
            var_cbRemote1_listrik= (b.getInt("remote1_listrik") == 0) ? false : true;
            var_cbRemote2_listrik= (b.getInt("remote2_listrik") == 0) ? false : true;
            var_cbSpeedo1_listrik= (b.getInt("speedo1_listrik") == 0) ? false : true;
            var_cbSpeedo2_listrik= (b.getInt("speedo2_listrik") == 0) ? false : true;
            var_cbOdometer1_listrik= (b.getInt("odometer1_listrik") == 0) ? false : true;
            var_cbOdometer2_listrik= (b.getInt("odometer2_listrik") == 0) ? false : true;
            var_cbTacho1_listrik= (b.getInt("tacho1_listrik") == 0) ? false : true;
            var_cbTacho2_listrik= (b.getInt("tacho2_listrik") == 0) ? false : true;
            var_cbAccu1_listrik= (b.getInt("accu1_listrik") == 0) ? false : true;
            var_cbAccu2_listrik= (b.getInt("accu2_listrik") == 0) ? false : true;

            var_cbMesin1_lain= (b.getInt("mesin1_lain") == 0) ? false : true;
            var_cbMesin2_lain= (b.getInt("mesin2_lain") == 0) ? false : true;
            var_cbHidraulik1_lain= (b.getInt("hidraulik1_lain") == 0) ? false : true;
            var_cbHidraulik2_lain= (b.getInt("hidraulik2_lain") == 0) ? false : true;
            var_cbGardan1_lain= (b.getInt("gardan1_lain") == 0) ? false : true;
            var_cbGardan2_lain= (b.getInt("gardan2_lain") == 0) ? false : true;
            var_cbAs1_lain= (b.getInt("as1_lain") == 0) ? false : true;
            var_cbAs2_lain= (b.getInt("as2_lain") == 0) ? false : true;
            var_cbBak1_lain= (b.getInt("bak1_lain") == 0) ? false : true;
            var_cbBak2_lain= (b.getInt("bak2_lain") == 0) ? false : true;
            var_cbStnk1_lain= (b.getInt("stnk1_lain") == 0) ? false : true;
            var_cbStnk2_lain= (b.getInt("stnk2_lain") == 0) ? false : true;
            var_etStnk = b.getString("stnk_lain");
            stnk = var_etStnk.split("-");

            var_cbBukukir1_lain= (b.getInt("bukukir1_lain") == 0) ? false : true;
            var_cbBukukir2_lain= (b.getInt("bukukir2_lain") == 0) ? false : true;
            var_etBukukir = b.getString("bukukir_lain");
            bukukir = var_etBukukir.split("-");

            var_cbTrayek1_lain= (b.getInt("trayek1_lain") == 0) ? false : true;
            var_cbTrayek2_lain= (b.getInt("trayek2_lain") == 0) ? false : true;
            var_etTrayek = b.getString("ijintrayek_lain");
            trayek = var_etTrayek.split("-");

            var_cbUsaha1_lain= (b.getInt("usaha1_lain") == 0) ? false : true;
            var_cbUsaha2_lain= (b.getInt("usaha2_lain") == 0) ? false : true;
            var_etUsaha = b.getString("ijinusaha_lain");
            usaha = var_etUsaha.split("-");

            var_cbBukumnl1_lain= (b.getInt("bukumnl1_lain") == 0) ? false : true;
            var_cbBukumnl2_lain= (b.getInt("bukumnl2_lain") == 0) ? false : true;

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
        //------------------------------------------------------------------------
        //Dispatcher Checklist
        //-------------------------------------------------------------------------
        cbGrill1_fisikmuka.setChecked(var_cbGrill1_fisikmuka);
        cbGrill1_fisikmuka.setText((var_cbGrill1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbGrill2_fisikmuka.setChecked((var_cbGrill1_fisikmuka == true) ? var_cbGrill2_fisikmuka : false);
        cbGrill2_fisikmuka.setVisibility((var_cbGrill1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbGrill2_fisikmuka.setText((var_cbGrill2_fisikmuka == true) ? "Baik" : "Rusak");

        cbLampu1_fisikmuka.setChecked(var_cbLampu1_fisikmuka);
        cbLampu1_fisikmuka.setText((var_cbLampu1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbLampu2_fisikmuka.setChecked((var_cbLampu1_fisikmuka == true) ? var_cbLampu2_fisikmuka : false);
        cbLampu2_fisikmuka.setVisibility((var_cbLampu1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampu2_fisikmuka.setText((var_cbLampu2_fisikmuka == true) ? "Baik" : "Rusak");

        cbLampusen1_fisikmuka.setChecked(var_cbLampusen1_fisikmuka);
        cbLampusen1_fisikmuka.setText((var_cbLampusen1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbLampusen2_fisikmuka.setChecked(var_cbLampusen2_fisikmuka);
        cbLampusen2_fisikmuka.setVisibility((var_cbLampusen1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampusen2_fisikmuka.setText((var_cbLampusen2_fisikmuka == true) ? "Baik" : "Rusak");

        cbBamper1_fisikmuka.setChecked(var_cbBamper1_fisikmuka);
        cbBamper1_fisikmuka.setText((var_cbBamper1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbBamper2_fisikmuka.setChecked(var_cbBamper2_fisikmuka);
        cbBamper2_fisikmuka.setVisibility((var_cbBamper1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbBamper2_fisikmuka.setText((var_cbBamper2_fisikmuka == true) ? "Baik" : "Rusak");

        cbEmblem1_fisikmuka.setChecked(var_cbEmblem1_fisikmuka);
        cbEmblem1_fisikmuka.setText((var_cbEmblem1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbEmblem2_fisikmuka.setChecked(var_cbEmblem2_fisikmuka);
        cbEmblem2_fisikmuka.setVisibility((var_cbEmblem1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbEmblem2_fisikmuka.setText((var_cbEmblem2_fisikmuka == true) ? "Baik" : "Rusak");

        cbTanduk1_fisikmuka.setChecked(var_cbTanduk1_fisikmuka);
        cbTanduk1_fisikmuka.setText((var_cbTanduk1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbTanduk2_fisikmuka.setChecked(var_cbTanduk2_fisikmuka);
        cbTanduk2_fisikmuka.setVisibility((var_cbTanduk1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbTanduk2_fisikmuka.setText((var_cbTanduk2_fisikmuka == true) ? "Baik" : "Rusak");


        cbFootstep1_fisikkiri.setChecked(var_cbFootstep1_fisikkiri);
        cbFootstep1_fisikkiri.setText((var_cbFootstep1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbFootstep2_fisikkiri.setChecked(var_cbFootstep2_fisikkiri);
        cbFootstep2_fisikkiri.setVisibility((var_cbFootstep1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbFootstep2_fisikkiri.setText((var_cbFootstep2_fisikkiri == true) ? "Baik" : "Rusak");

        cbPintudpn1_fisikkiri.setChecked(var_cbPintudpn1_fisikkiri);
        cbPintudpn1_fisikkiri.setText((var_cbPintudpn1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbPintudpn2_fisikkiri.setChecked(var_cbPintudpn2_fisikkiri);
        cbPintudpn2_fisikkiri.setVisibility((var_cbPintudpn1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbPintudpn2_fisikkiri.setText((var_cbPintudpn2_fisikkiri == true) ? "Baik" : "Rusak");

        cbPintublk1_fisikkiri.setChecked(var_cbPintublk1_fisikkiri);
        cbPintublk1_fisikkiri.setText((var_cbPintublk1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbPintublk2_fisikkiri.setChecked(var_cbPintublk2_fisikkiri);
        cbPintublk2_fisikkiri.setVisibility((var_cbPintublk1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbPintublk2_fisikkiri.setText((var_cbPintublk2_fisikkiri == true) ? "Baik" : "Rusak");

        cbBamper1_fisikkiri.setChecked(var_cbBamper1_fisikkiri);
        cbBamper1_fisikkiri.setText((var_cbBamper1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbBamper2_fisikkiri.setChecked(var_cbBamper2_fisikkiri);
        cbBamper2_fisikkiri.setVisibility((var_cbBamper1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbBamper2_fisikkiri.setText((var_cbBamper2_fisikkiri == true) ? "Baik" : "Rusak");

        cbFenderblk1_fisikkiri.setChecked(var_cbFenderblk1_fisikkiri);
        cbFenderblk1_fisikkiri.setText((var_cbFenderblk1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbFenderblk2_fisikkiri.setChecked(var_cbFenderblk2_fisikkiri);
        cbFenderblk2_fisikkiri.setVisibility((var_cbFenderblk1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbFenderblk2_fisikkiri.setText((var_cbFenderblk2_fisikkiri == true) ? "Baik" : "Rusak");

        cbSpion1_fisikkiri.setChecked(var_cbSpion1_fisikkiri);
        cbSpion1_fisikkiri.setText((var_cbSpion1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbSpion2_fisikkiri.setChecked(var_cbSpion2_fisikkiri);
        cbSpion2_fisikkiri.setVisibility((var_cbSpion1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpion2_fisikkiri.setText((var_cbSpion2_fisikkiri == true) ? "Baik" : "Rusak");

        cbEmblem1_fisikkiri.setChecked(var_cbEmblem1_fisikkiri);
        cbEmblem1_fisikkiri.setText((var_cbEmblem1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbEmblem2_fisikkiri.setChecked(var_cbEmblem2_fisikkiri);
        cbEmblem2_fisikkiri.setVisibility((var_cbEmblem1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbEmblem2_fisikkiri.setText((var_cbEmblem2_fisikkiri == true) ? "Baik" : "Rusak");

        cbBan1_fisikkiri.setChecked(var_cbBan1_fisikkiri);
//        rbBanstandard1_fisikkiri.setChecked(var_cbFootstep1_fisikkiri);
//        rbBanradial1_fisikkiri.setChecked(var_cbFootstep1_fisikkiri);

        rbVelgstandard_fisikkiri.setChecked(var_rbVelgstandard_fisikkiri);
        rbVelgracing_fisikkiri.setChecked(var_rbVelgracing_fisikkiri);

        cbVelg1_fisikkiri.setChecked(var_cbVelg1_fisikkiri);
        cbVelg1_fisikkiri.setText((var_cbVelg1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbVelg2_fisikkiri.setChecked(var_cbVelg2_fisikkiri);
        cbVelg2_fisikkiri.setVisibility((var_cbVelg1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbVelg2_fisikkiri.setText((var_cbVelg2_fisikkiri == true) ? "Baik" : "Rusak");

        cbDop1_fisikkiri.setChecked(var_cbDop1_fisikkiri);
        cbDop1_fisikkiri.setText((var_cbDop1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbDop2_fisikkiri.setChecked(var_cbDop2_fisikkiri);
        cbDop2_fisikkiri.setVisibility((var_cbDop1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbDop2_fisikkiri.setText((var_cbDop2_fisikkiri == true) ? "Baik" : "Rusak");

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        cbDopBlk1_fisikkiri.setChecked(var_cbDop1_fisikkiri);
        cbDopBlk1_fisikkiri.setText((var_cbDop1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbDopBlk2_fisikkiri.setChecked(var_cbDop2_fisikkiri);
        cbDopBlk2_fisikkiri.setVisibility((var_cbDop1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbDopBlk2_fisikkiri.setText((var_cbDop2_fisikkiri == true) ? "Baik" : "Rusak");

        cbSpoiler1_fisikblkg.setChecked(var_cbSpoiler1_fisikblkg);
        cbSpoiler1_fisikblkg.setText((var_cbSpoiler1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbSpoiler2_fisikblkg.setChecked(var_cbSpoiler2_fisikblkg);
        cbSpoiler2_fisikblkg.setVisibility((var_cbSpoiler2_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpoiler2_fisikblkg.setText((var_cbSpoiler2_fisikblkg == true) ? "Baik" : "Rusak");

        cbLampu1_fisikblkg.setChecked(var_cbLampu1_fisikblkg);
        cbLampu1_fisikblkg.setText((var_cbLampu1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbLampu2_fisikblkg.setChecked(var_cbLampu2_fisikblkg);
        cbLampu2_fisikblkg.setVisibility((var_cbLampu1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampu2_fisikblkg.setText((var_cbLampu2_fisikblkg == true) ? "Baik" : "Rusak");

        cbLampusen1_fisikblkg.setChecked(var_cbLampusen1_fisikblkg);
        cbLampusen1_fisikblkg.setText((var_cbLampusen1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbLampusen2_fisikblkg.setChecked(var_cbLampusen2_fisikblkg);
        cbLampusen2_fisikblkg.setVisibility((var_cbLampusen1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampusen2_fisikblkg.setText((var_cbLampusen2_fisikblkg == true) ? "Baik" : "Rusak");

        cbBamper1_fisikblkg.setChecked(var_cbBamper1_fisikblkg);
        cbBamper1_fisikblkg.setText((var_cbBamper1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbBamper2_fisikblkg.setChecked(var_cbBamper2_fisikblkg);
        cbBamper2_fisikblkg.setVisibility((var_cbBamper1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbBamper2_fisikblkg.setText((var_cbBamper2_fisikblkg == true) ? "Baik" : "Rusak");

        cbEmblem1_fisikblkg.setChecked(var_cbEmblem1_fisikblkg);
        cbEmblem1_fisikblkg.setText((var_cbEmblem1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbEmblem2_fisikblkg.setChecked(var_cbEmblem2_fisikblkg);
        cbEmblem2_fisikblkg.setVisibility((var_cbEmblem1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbEmblem2_fisikblkg.setText((var_cbEmblem2_fisikblkg == true) ? "Baik" : "Rusak");

        cbKnalpot1_fisikblkg.setChecked(var_cbKnalpot1_fisikblkg);
        cbKnalpot1_fisikblkg.setText((var_cbKnalpot1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbKnalpot2_fisikblkg.setChecked(var_cbKnalpot2_fisikblkg);
        cbKnalpot2_fisikblkg.setVisibility((var_cbKnalpot1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbKnalpot2_fisikblkg.setText((var_cbKnalpot2_fisikblkg == true) ? "Baik" : "Rusak");

        cbFoot1_fisikkanan.setChecked(var_cbFoot1_fisikkanan);
        cbFoot1_fisikkanan.setText((var_cbFoot1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbFoot2_fisikkanan.setChecked(var_cbFoot2_fisikkanan);
        cbFoot2_fisikkanan.setVisibility((var_cbFoot1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbFoot2_fisikkanan.setText((var_cbFoot2_fisikkanan == true) ? "Baik" : "Rusak");

        cbPintudpn1_fisikkanan.setChecked(var_cbPintudpn1_fisikkanan);
        cbPintudpn1_fisikkanan.setText((var_cbPintudpn1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbPintudpn2_fisikkanan.setChecked(var_cbPintudpn2_fisikkanan);
        cbPintudpn2_fisikkanan.setVisibility((var_cbPintudpn1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbPintudpn2_fisikkanan.setText((var_cbPintudpn2_fisikkanan == true) ? "Baik" : "Rusak");

        cbPintublk1_fisikkanan.setChecked(var_cbPintublk1_fisikkanan);
        cbPintublk1_fisikkanan.setText((var_cbPintublk1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbPintublk2_fisikkanan.setChecked(var_cbPintublk2_fisikkanan);
        cbPintublk2_fisikkanan.setVisibility((var_cbPintublk1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbPintublk2_fisikkanan.setText((var_cbPintublk2_fisikkanan == true) ? "Baik" : "Rusak");

        cbBamper1_fisikkanan.setChecked(var_cbBamper1_fisikkanan);
        cbBamper1_fisikkanan.setText((var_cbBamper1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbBamper2_fisikkanan.setChecked(var_cbBamper2_fisikkanan);
        cbBamper2_fisikkanan.setVisibility((var_cbBamper1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbBamper2_fisikkanan.setText((var_cbBamper2_fisikkanan == true) ? "Baik" : "Rusak");

        cbFenderblk1_fisikkanan.setChecked(var_cbFenderblk1_fisikkanan);
        cbFenderblk1_fisikkanan.setText((var_cbFenderblk1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbFenderblk2_fisikkanan.setChecked(var_cbFenderblk2_fisikkanan);
        cbFenderblk2_fisikkanan.setVisibility((var_cbFenderblk1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbFenderblk2_fisikkanan.setText((var_cbFenderblk2_fisikkanan == true) ? "Baik" : "Rusak");

        cbSpion1_fisikkanan.setChecked(var_cbSpion1_fisikkanan);
        cbSpion1_fisikkanan.setText((var_cbSpion1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbSpion2_fisikkanan.setChecked(var_cbSpion2_fisikkanan);
        cbSpion2_fisikkanan.setVisibility((var_cbSpion1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpion2_fisikkanan.setText((var_cbSpion2_fisikkanan == true) ? "Baik" : "Rusak");

        cbEmblem1_fisikkanan.setChecked(var_cbEmblem1_fisikkanan);
        cbEmblem1_fisikkanan.setText((var_cbEmblem1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbEmblem2_fisikkanan.setChecked(var_cbEmblem2_fisikkanan);
        cbEmblem2_fisikkanan.setVisibility((var_cbEmblem1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbEmblem2_fisikkanan.setText((var_cbEmblem2_fisikkanan == true) ? "Baik" : "Rusak");

        cbBan1_fisikkanan.setChecked(var_cbBan1_fisikkanan);
        cbBan1_fisikkanan.setText((var_cbBan1_fisikkanan == true) ? "Ada" : "Tidak Ada");
//        cbFenderblk2_fisikkanan.setChecked(var_cbDop2_fisikkiri);
//        cbFenderblk2_fisikkanan.setVisibility((var_cbDop1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
//        cbFenderblk2_fisikkanan.setText((var_cbDop2_fisikkiri == true) ? "Baik" : "Rusak");

        cbVelg1_fisikkanan.setChecked(var_cbVelg1_fisikkanan);
        cbVelg1_fisikkanan.setText((var_cbVelg1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbVelg2_fisikkanan.setChecked(var_cbVelg2_fisikkanan);
        cbVelg2_fisikkanan.setVisibility((var_cbVelg1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbVelg2_fisikkanan.setText((var_cbVelg2_fisikkanan == true) ? "Baik" : "Rusak");

        cbDop1_fisikkanan.setChecked(var_cbDop1_fisikkanan);
        cbDop1_fisikkanan.setText((var_cbDop1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbDop2_fisikkanan.setChecked(var_cbDop2_fisikkanan);
        cbDop2_fisikkanan.setVisibility((var_cbDop1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbDop2_fisikkanan.setText((var_cbDop2_fisikkanan == true) ? "Baik" : "Rusak");

//        cbDopBlk1_fisikkanan.setChecked(var_cbDop1_fisikkiri);
//        cbDopBlk1_fisikkanan.setText((var_cbDop1_fisikkiri == true) ? "Ada" : "Tidak Ada");
//        cbDopBlk2_fisikkanan.setChecked(var_cbDop2_fisikkiri);
//        cbDopBlk2_fisikkanan.setVisibility((var_cbDop1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
//        cbDopBlk2_fisikkanan.setText((var_cbDop2_fisikkiri == true) ? "Baik" : "Rusak");

        cbKunciktk1_perlengkapan.setChecked(var_cbKunciktk1_perlengkapan);
        cbKunciktk1_perlengkapan.setText((var_cbKunciktk1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbKunciktk2_perlengkapan.setChecked(var_cbKunciktk2_perlengkapan);
        cbKunciktk2_perlengkapan.setVisibility((var_cbKunciktk1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbKunciktk2_perlengkapan.setText((var_cbKunciktk2_perlengkapan == true) ? "Baik" : "Rusak");

        cbSpion1_perlengkapan.setChecked(var_cbSpion1_perlengkapan);
        cbSpion1_perlengkapan.setText((var_cbSpion1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbSpion2_perlengkapan.setChecked(var_cbSpion2_perlengkapan);
        cbSpion2_perlengkapan.setVisibility((var_cbSpion1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpion2_perlengkapan.setText((var_cbSpion2_perlengkapan == true) ? "Baik" : "Rusak");

        cbJok1_perlengkapan.setChecked(var_cbJok1_perlengkapan);
        cbJok1_perlengkapan.setText((var_cbJok1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbJok2_perlengkapan.setChecked(var_cbJok2_perlengkapan);
        cbJok2_perlengkapan.setVisibility((var_cbJok1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbJok2_perlengkapan.setText((var_cbJok2_perlengkapan == true) ? "Baik" : "Rusak");

        cbSarung1_perlengkapan.setChecked(var_cbSarung1_perlengkapan);
        cbSarung1_perlengkapan.setText((var_cbSarung1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbSarung2_perlengkapan.setChecked(var_cbSarung2_perlengkapan);
        cbSarung2_perlengkapan.setVisibility((var_cbSarung1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSarung2_perlengkapan.setText((var_cbSarung2_perlengkapan == true) ? "Baik" : "Rusak");

        cbSandaran1_perlengkapan.setChecked(var_cbSandaran1_perlengkapan);
        cbSandaran1_perlengkapan.setText((var_cbSandaran1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbSandaran2_perlengkapan.setChecked(var_cbSandaran2_perlengkapan);
        cbSandaran2_perlengkapan.setVisibility((var_cbSandaran1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSandaran2_perlengkapan.setText((var_cbSandaran2_perlengkapan == true) ? "Baik" : "Rusak");

        cbKarpet1_perlengkapan.setChecked(var_cbKarpet1_perlengkapan);
        cbKarpet1_perlengkapan.setText((var_cbKarpet1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbKarpet2_perlengkapan.setChecked(var_cbKarpet2_perlengkapan);
        cbKarpet2_perlengkapan.setVisibility((var_cbKarpet1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbKarpet2_perlengkapan.setText((var_cbKarpet2_perlengkapan == true) ? "Baik" : "Rusak");

        cbPelindung1_perlengkapan.setChecked(var_cbPelindung1_perlengkapan);
        cbPelindung1_perlengkapan.setText((var_cbPelindung1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbPelindung2_perlengkapan.setChecked(var_cbPelindung2_perlengkapan);
        cbPelindung2_perlengkapan.setVisibility((var_cbPelindung1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbPelindung2_perlengkapan.setText((var_cbPelindung2_perlengkapan == true) ? "Baik" : "Rusak");

        cbSegitiga1_perlengkapan.setChecked(var_cbSegitiga1_perlengkapan);
        cbSegitiga1_perlengkapan.setText((var_cbSegitiga1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbSegitiga2_perlengkapan.setChecked(var_cbSegitiga2_perlengkapan);
        cbSegitiga2_perlengkapan.setVisibility((var_cbSegitiga1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSegitiga2_perlengkapan.setText((var_cbSegitiga2_perlengkapan == true) ? "Baik" : "Rusak");

        cbTool1_perlengkapan.setChecked(var_cbTool1_perlengkapan);
        cbTool1_perlengkapan.setText((var_cbTool1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbTool2_perlengkapan.setChecked(var_cbTool2_perlengkapan);
        cbTool2_perlengkapan.setVisibility((var_cbTool1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbTool2_perlengkapan.setText((var_cbTool2_perlengkapan == true) ? "Baik" : "Rusak");

        cbCadangan1_perlengkapan.setChecked(var_cbCadangan1_perlengkapan);
        cbCadangan1_perlengkapan.setText((var_cbCadangan1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbCadangan2_perlengkapan.setChecked(var_cbCadangan2_perlengkapan);
        cbCadangan2_perlengkapan.setVisibility((var_cbCadangan1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbCadangan2_perlengkapan.setText((var_cbCadangan2_perlengkapan == true) ? "Baik" : "Rusak");

        cbKunciban1_perlengkapan.setChecked(var_cbKunciban1_perlengkapan);
        cbKunciban1_perlengkapan.setText((var_cbKunciban1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbKunciban2_perlengkapan.setChecked(var_cbKunciban2_perlengkapan);
        cbKunciban2_perlengkapan.setVisibility((var_cbKunciban1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbKunciban2_perlengkapan.setText((var_cbKunciban2_perlengkapan == true) ? "Baik" : "Rusak");

        cbDongkrak1_perlengkapan.setChecked(var_cbDongkrak1_perlengkapan);
        cbDongkrak1_perlengkapan.setText((var_cbDongkrak1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbDongkrak2_perlengkapan.setChecked(var_cbDongkrak2_perlengkapan);
        cbDongkrak2_perlengkapan.setVisibility((var_cbDongkrak1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbDongkrak2_perlengkapan.setText((var_cbDongkrak2_perlengkapan == true) ? "Baik" : "Rusak");

        cbAntena1_perlengkapan.setChecked(var_cbAntena1_perlengkapan);
        cbAntena1_perlengkapan.setText((var_cbAntena1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbAntena2_perlengkapan.setChecked(var_cbAntena2_perlengkapan);
        cbAntena2_perlengkapan.setVisibility((var_cbAntena1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbAntena2_perlengkapan.setText((var_cbAntena2_perlengkapan == true) ? "Baik" : "Rusak");

        cbAirbag1_perlengkapan.setChecked(var_cbAirbag1_perlengkapan);
        cbAirbag1_perlengkapan.setText((var_cbAirbag1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbAirbag2_perlengkapan.setChecked(var_cbAirbag2_perlengkapan);
        cbAirbag2_perlengkapan.setVisibility((var_cbAirbag1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbAirbag2_perlengkapan.setText((var_cbAirbag2_perlengkapan == true) ? "Baik" : "Rusak");

        cbLampukbt1_listrik.setChecked(var_cbLampukbt1_listrik);
        cbLampukbt1_listrik.setText((var_cbLampukbt1_listrik == true) ? "Ada" : "Tidak Ada");
        cbLampukbt2_listrik.setChecked(var_cbLampukbt2_listrik);
        cbLampukbt2_listrik.setVisibility((var_cbLampukbt1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampukbt2_listrik.setText((var_cbLampukbt2_listrik == true) ? "Baik" : "Rusak");

        cbWiperkacadpn1_listrik.setChecked(var_cbWiperkacadpn1_listrik);
        cbWiperkacadpn1_listrik.setText((var_cbWiperkacadpn1_listrik == true) ? "Ada" : "Tidak Ada");
        cbWiperkacadpn2_listrik.setChecked(var_cbWiperkacadpn2_listrik);
        cbWiperkacadpn2_listrik.setVisibility((var_cbWiperkacadpn1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbWiperkacadpn2_listrik.setText((var_cbWiperkacadpn2_listrik == true) ? "Baik" : "Rusak");

        cbWiperkacablk1_listrik.setChecked(var_cbWiperkacablk1_listrik);
        cbWiperkacablk1_listrik.setText((var_cbWiperkacablk1_listrik == true) ? "Ada" : "Tidak Ada");
        cbWiperkacablk2_listrik.setChecked(var_cbWiperkacablk2_listrik);
        cbWiperkacablk2_listrik.setVisibility((var_cbWiperkacablk1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbWiperkacablk2_listrik.setText((var_cbWiperkacablk2_listrik == true) ? "Baik" : "Rusak");

        cbKlakson1_listrik.setChecked(var_cbKlakson1_listrik);
        cbKlakson1_listrik.setText((var_cbKlakson1_listrik == true) ? "Ada" : "Tidak Ada");
        cbKlakson2_listrik.setChecked(var_cbKlakson2_listrik);
        cbKlakson2_listrik.setVisibility((var_cbKlakson1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbKlakson2_listrik.setText((var_cbKlakson2_listrik == true) ? "Baik" : "Rusak");

        cbAlarm1_listrik.setChecked(var_cbAlarm1_listrik);
        cbAlarm1_listrik.setText((var_cbAlarm1_listrik == true) ? "Ada" : "Tidak Ada");
        cbAlarm2_listrik.setChecked(var_cbAlarm2_listrik);
        cbAlarm2_listrik.setVisibility((var_cbAlarm1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbAlarm2_listrik.setText((var_cbAlarm2_listrik == true) ? "Baik" : "Rusak");

        cbJam1_listrik.setChecked(var_cbJam1_listrik);
        cbJam1_listrik.setText((var_cbJam1_listrik == true) ? "Ada" : "Tidak Ada");
        cbJam2_listrik.setChecked(var_cbJam2_listrik);
        cbJam2_listrik.setVisibility((var_cbJam1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbJam2_listrik.setText((var_cbJam2_listrik == true) ? "Baik" : "Rusak");

        cbLighter1_listrik.setChecked(var_cbLighter1_listrik);
        cbLighter1_listrik.setText((var_cbLighter1_listrik == true) ? "Ada" : "Tidak Ada");
        cbLighter2_listrik.setChecked(var_cbLighter2_listrik);
        cbLighter2_listrik.setVisibility((var_cbLighter1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbLighter2_listrik.setText((var_cbLighter2_listrik == true) ? "Baik" : "Rusak");

        cbRadio1_listrik.setChecked(var_cbRadio1_listrik);
        cbRadio1_listrik.setText((var_cbRadio1_listrik == true) ? "Ada" : "Tidak Ada");
        cbRadio2_listrik.setChecked(var_cbRadio2_listrik);
        cbRadio2_listrik.setVisibility((var_cbRadio1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbRadio2_listrik.setText((var_cbRadio2_listrik == true) ? "Baik" : "Rusak");

        cbPowersup1_listrik.setChecked(var_cbPowersup1_listrik);
        cbPowersup1_listrik.setText((var_cbPowersup1_listrik == true) ? "Ada" : "Tidak Ada");
        cbPowersup2_listrik.setChecked(var_cbPowersup2_listrik);
        cbPowersup2_listrik.setVisibility((var_cbPowersup1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbPowersup2_listrik.setText((var_cbPowersup2_listrik == true) ? "Baik" : "Rusak");

        cbSpeaker1_listrik.setChecked(var_cbSpeaker1_listrik);
        cbSpeaker1_listrik.setText((var_cbSpeaker1_listrik == true) ? "Ada" : "Tidak Ada");
        cbSpeaker2_listrik.setChecked(var_cbSpeaker2_listrik);
        cbSpeaker2_listrik.setVisibility((var_cbSpeaker1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpeaker2_listrik.setText((var_cbSpeaker2_listrik == true) ? "Baik" : "Rusak");

        cbAc1_listrik.setChecked(var_cbAc1_listrik);
        cbAc1_listrik.setText((var_cbAc1_listrik == true) ? "Ada" : "Tidak Ada");
        cbAc2_listrik.setChecked(var_cbAc2_listrik);
        cbAc2_listrik.setVisibility((var_cbAc1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbAc2_listrik.setText((var_cbAc2_listrik == true) ? "Baik" : "Rusak");

        cbPowerwin1_listrik.setChecked(var_cbPowerwin1_listrik);
        cbPowerwin1_listrik.setText((var_cbPowerwin1_listrik == true) ? "Ada" : "Tidak Ada");
        cbPowerwin2_listrik.setChecked(var_cbPowerwin2_listrik);
        cbPowerwin2_listrik.setVisibility((var_cbPowerwin1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbPowerwin2_listrik.setText((var_cbPowerwin2_listrik == true) ? "Baik" : "Rusak");

        cbCentral1_listrik.setChecked(var_cbCentral1_listrik);
        cbCentral1_listrik.setText((var_cbCentral1_listrik == true) ? "Ada" : "Tidak Ada");
        cbCentral2_listrik.setChecked(var_cbCentral2_listrik);
        cbCentral2_listrik.setVisibility((var_cbCentral1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbCentral2_listrik.setText((var_cbCentral2_listrik == true) ? "Baik" : "Rusak");

        cbRemote1_listrik.setChecked(var_cbRemote1_listrik);
        cbRemote1_listrik.setText((var_cbRemote1_listrik == true) ? "Ada" : "Tidak Ada");
        cbRemote2_listrik.setChecked(var_cbRemote2_listrik);
        cbRemote2_listrik.setVisibility((var_cbRemote1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbRemote2_listrik.setText((var_cbRemote2_listrik == true) ? "Baik" : "Rusak");

        cbSpeedo1_listrik.setChecked(var_cbSpeedo1_listrik);
        cbSpeedo1_listrik.setText((var_cbSpeedo1_listrik == true) ? "Ada" : "Tidak Ada");
        cbSpeedo2_listrik.setChecked(var_cbSpeedo2_listrik);
        cbSpeedo2_listrik.setVisibility((var_cbSpeedo1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpeedo2_listrik.setText((var_cbSpeedo2_listrik == true) ? "Baik" : "Rusak");

        cbOdometer1_listrik.setChecked(var_cbOdometer1_listrik);
        cbOdometer1_listrik.setText((var_cbOdometer1_listrik == true) ? "Ada" : "Tidak Ada");
        cbOdometer2_listrik.setChecked(var_cbOdometer2_listrik);
        cbOdometer2_listrik.setVisibility((var_cbOdometer1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbOdometer2_listrik.setText((var_cbOdometer2_listrik == true) ? "Baik" : "Rusak");

        cbTacho1_listrik.setChecked(var_cbTacho1_listrik);
        cbTacho1_listrik.setText((var_cbTacho1_listrik == true) ? "Ada" : "Tidak Ada");
        cbTacho2_listrik.setChecked(var_cbTacho2_listrik);
        cbTacho2_listrik.setVisibility((var_cbTacho1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbTacho2_listrik.setText((var_cbTacho2_listrik == true) ? "Baik" : "Rusak");

        cbAccu1_listrik.setChecked(var_cbAccu1_listrik);
        cbAccu1_listrik.setText((var_cbAccu1_listrik == true) ? "Ada" : "Tidak Ada");
        cbAccu2_listrik.setChecked(var_cbAccu2_listrik);
        cbAccu2_listrik.setVisibility((var_cbAccu1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbAccu2_listrik.setText((var_cbAccu2_listrik == true) ? "Baik" : "Rusak");

        cbMesin1_lain.setChecked(var_cbMesin1_lain);
        cbMesin1_lain.setText((var_cbMesin1_lain == true) ? "Ada" : "Tidak Ada");
        cbMesin2_lain.setChecked(var_cbMesin2_lain);
        cbMesin2_lain.setVisibility((var_cbMesin1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbMesin2_lain.setText((var_cbMesin2_lain == true) ? "Baik" : "Rusak");

        cbHidraulik1_lain.setChecked(var_cbHidraulik1_lain);
        cbHidraulik1_lain.setText((var_cbHidraulik1_lain == true) ? "Ada" : "Tidak Ada");
        cbHidraulik2_lain.setChecked(var_cbHidraulik2_lain);
        cbHidraulik2_lain.setVisibility((var_cbHidraulik1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbHidraulik2_lain.setText((var_cbHidraulik2_lain == true) ? "Baik" : "Rusak");

        cbGardan1_lain.setChecked(var_cbGardan1_lain);
        cbGardan1_lain.setText((var_cbGardan1_lain == true) ? "Ada" : "Tidak Ada");
        cbGardan2_lain.setChecked(var_cbGardan2_lain);
        cbGardan2_lain.setVisibility((var_cbGardan1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbGardan2_lain.setText((var_cbGardan2_lain == true) ? "Baik" : "Rusak");

        cbAs1_lain.setChecked(var_cbAs1_lain);
        cbAs1_lain.setText((var_cbAs1_lain == true) ? "Ada" : "Tidak Ada");
        cbAs2_lain.setChecked(var_cbAs2_lain);
        cbAs2_lain.setVisibility((var_cbAs1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbAs2_lain.setText((var_cbAs2_lain == true) ? "Baik" : "Rusak");

        cbBak1_lain.setChecked(var_cbBak1_lain);
        cbBak1_lain.setText((var_cbBak1_lain == true) ? "Ada" : "Tidak Ada");
        cbBak2_lain.setChecked(var_cbBak2_lain);
        cbBak2_lain.setVisibility((var_cbBak1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbBak2_lain.setText((var_cbBak2_lain == true) ? "Baik" : "Rusak");

        etSd_stnk.setText(stnk[2] + "-" + stnk[1] + "-" + stnk[0]);
        cbStnk1_lain.setChecked(var_cbStnk1_lain);
        cbStnk1_lain.setText((var_cbStnk1_lain == true) ? "Ada" : "Tidak Ada");
        cbStnk2_lain.setChecked(var_cbStnk2_lain);
        cbStnk2_lain.setVisibility((var_cbStnk1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbStnk2_lain.setText((var_cbStnk2_lain == true) ? "Baik" : "Rusak");

        etSd_bukukir.setText(bukukir[2] + "-" + bukukir[1] + "-" + bukukir[0]);
        cbBukukir1_lain.setChecked(var_cbBukukir1_lain);
        cbBukukir1_lain.setText((var_cbBukukir1_lain == true) ? "Ada" : "Tidak Ada");
        cbBukukir2_lain.setChecked(var_cbBukukir2_lain);
        cbBukukir2_lain.setVisibility((var_cbBukukir1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbBukukir2_lain.setText((var_cbBukukir2_lain == true) ? "Baik" : "Rusak");


        etSd_ijintrayek.setText(trayek[2] + "-" + trayek[1] + "-" + trayek[0]);
        cbTrayek1_lain.setChecked(var_cbTrayek1_lain);
        cbTrayek1_lain.setText((var_cbTrayek1_lain == true) ? "Ada" : "Tidak Ada");
        cbTrayek2_lain.setChecked(var_cbTrayek2_lain);
        cbTrayek2_lain.setVisibility((var_cbTrayek1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbTrayek2_lain.setText((var_cbTrayek2_lain == true) ? "Baik" : "Rusak");

        etSd_ijinusaha.setText(usaha[2] + "-" + usaha[1] + "-" + usaha[0]);
        cbUsaha1_lain.setChecked(var_cbUsaha1_lain);
        cbUsaha1_lain.setText((var_cbUsaha1_lain == true) ? "Ada" : "Tidak Ada");
        cbUsaha2_lain.setChecked(var_cbUsaha2_lain);
        cbUsaha2_lain.setVisibility((var_cbUsaha1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbUsaha2_lain.setText((var_cbUsaha2_lain == true) ? "Baik" : "Rusak");

        cbBukumnl1_lain.setChecked(var_cbBukumnl1_lain);
        cbBukumnl1_lain.setText((var_cbBukumnl1_lain == true) ? "Ada" : "Tidak Ada");
        cbBukumnl2_lain.setChecked(var_cbBukumnl2_lain);
        cbBukumnl2_lain.setVisibility((var_cbBukumnl1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbBukumnl2_lain.setText((var_cbBukumnl2_lain == true) ? "Baik" : "Rusak");


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisApprovalCeklistActivity.this, DisApprovalFotoActivity.class);
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
