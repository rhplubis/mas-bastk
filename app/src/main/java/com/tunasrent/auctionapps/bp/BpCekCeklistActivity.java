package com.tunasrent.auctionapps.bp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.tunasrent.auctionapps.DB.Ceklist;
import com.tunasrent.auctionapps.MainActivity;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.dispatcher.DisInputCeklistActivity;
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

public class BpCekCeklistActivity extends AppCompatActivity {
    UserSessionManager session;
    String _name, _fullname, _appid, _ccode, _token, _group;

    Realm realm;
    Calendar myCalendar;
    Toolbar toolbar;

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

    //============Child=================================//

    CheckBox cbGrill1_fisikmukax, cbGrill2_fisikmukax;
    CheckBox cbLampu1_fisikmukax, cbLampu2_fisikmukax;
    CheckBox cbLampusen1_fisikmukax, cbLampusen2_fisikmukax;
    CheckBox cbBamper1_fisikmukax, cbBamper2_fisikmukax;
    CheckBox cbEmblem1_fisikmukax, cbEmblem2_fisikmukax;
    CheckBox cbTanduk1_fisikmukax, cbTanduk2_fisikmukax;

    CheckBox cbFootstep1_fisikkirix, cbFootstep2_fisikkirix;
    CheckBox cbPintudpn1_fisikkirix, cbPintudpn2_fisikkirix;
    CheckBox cbPintublk1_fisikkirix, cbPintublk2_fisikkirix;
    CheckBox cbBamper1_fisikkirix,  cbBamper2_fisikkirix;
    CheckBox cbFenderblk1_fisikkirix, cbFenderblk2_fisikkirix;
    CheckBox cbSpion1_fisikkirix, cbSpion2_fisikkirix;
    CheckBox cbEmblem1_fisikkirix, cbEmblem2_fisikkirix;
    CheckBox cbBan1_fisikkirix;
    RadioButton rbBanstandard1_fisikkirix, rbBanradial1_fisikkirix;
    //CheckBox cbBan1_fisikkirix;
    Spinner spBan2_fisikkirix;
    private String[] kondisix = {"Botak", "Sedang", "Baik"};

    RadioButton rbVelgstandard_fisikkirix, rbVelgracing_fisikkirix;
    CheckBox cbVelg1_fisikkirix, cbVelg2_fisikkirix;
    CheckBox cbDop1_fisikkirix, cbDop2_fisikkirix;
    CheckBox cbDopBlk1_fisikkirix, cbDopBlk2_fisikkirix;

    CheckBox cbSpoiler1_fisikblkgx, cbSpoiler2_fisikblkgx;
    CheckBox cbLampu1_fisikblkgx, cbLampu2_fisikblkgx;
    CheckBox cbLampusen1_fisikblkgx, cbLampusen2_fisikblkgx;
    CheckBox cbBamper1_fisikblkgx, cbBamper2_fisikblkgx;
    CheckBox cbEmblem1_fisikblkgx, cbEmblem2_fisikblkgx;
    CheckBox cbKnalpot1_fisikblkgx, cbKnalpot2_fisikblkgx;

    CheckBox cbFoot1_fisikkananx, cbFoot2_fisikkananx;
    CheckBox cbPintudpn1_fisikkananx, cbPintudpn2_fisikkananx;
    CheckBox cbPintublk1_fisikkananx, cbPintublk2_fisikkananx;
    CheckBox cbBamper1_fisikkananx, cbBamper2_fisikkananx;
    CheckBox cbFenderblk1_fisikkananx, cbFenderblk2_fisikkananx;
    CheckBox cbSpion1_fisikkananx, cbSpion2_fisikkananx;
    CheckBox cbEmblem1_fisikkananx, cbEmblem2_fisikkananx;
    RadioButton rbBanstandard_fisikkananx, rbBanradial_fisikkananx;
    CheckBox cbBan1_fisikkananx;
    Spinner spBan2_fisikkananx;

    RadioButton rbVelgstandard_fisikkananx, rbVelgracing_fisikkananx;
    CheckBox cbVelg1_fisikkananx, cbVelg2_fisikkananx;
    CheckBox cbDop1_fisikkananx, cbDop2_fisikkananx;
    CheckBox cbDopBlk1_fisikkananx, cbDopBlk2_fisikkananx;

    CheckBox cbKunciktk1_perlengkapanx, cbKunciktk2_perlengkapanx;
    CheckBox cbSpion1_perlengkapanx, cbSpion2_perlengkapanx;
    CheckBox cbJok1_perlengkapanx, cbJok2_perlengkapanx;
    CheckBox cbSarung1_perlengkapanx, cbSarung2_perlengkapanx;
    CheckBox cbSandaran1_perlengkapanx, cbSandaran2_perlengkapanx;
    CheckBox cbKarpet1_perlengkapanx, cbKarpet2_perlengkapanx;
    CheckBox cbPelindung1_perlengkapanx, cbPelindung2_perlengkapanx;
    CheckBox cbSegitiga1_perlengkapanx, cbSegitiga2_perlengkapanx;
    CheckBox cbTool1_perlengkapanx, cbTool2_perlengkapanx;
    CheckBox cbCadangan1_perlengkapanx, cbCadangan2_perlengkapanx;
    CheckBox cbKunciban1_perlengkapanx, cbKunciban2_perlengkapanx;
    CheckBox cbDongkrak1_perlengkapanx, cbDongkrak2_perlengkapanx;
    CheckBox cbAntena1_perlengkapanx, cbAntena2_perlengkapanx;
    CheckBox cbAirbag1_perlengkapanx, cbAirbag2_perlengkapanx;

    CheckBox cbLampukbt1_listrikx, cbLampukbt2_listrikx;
    CheckBox cbWiperkacadpn1_listrikx, cbWiperkacadpn2_listrikx;
    CheckBox cbWiperkacablk1_listrikx, cbWiperkacablk2_listrikx;
    CheckBox cbKlakson1_listrikx, cbKlakson2_listrikx;
    CheckBox cbAlarm1_listrikx, cbAlarm2_listrikx;
    CheckBox cbJam1_listrikx, cbJam2_listrikx;
    CheckBox cbLighter1_listrikx, cbLighter2_listrikx;
    RadioButton rbRadio_listrikx, rbTape_listrikx, rbCd_listrikx;
    EditText etMerk_listrikx;
    CheckBox cbRadio1_listrikx, cbRadio2_listrikx;
    CheckBox cbPowersup1_listrikx, cbPowersup2_listrikx;
    CheckBox cbSpeaker1_listrikx, cbSpeaker2_listrikx;
    CheckBox cbAc1_listrikx, cbAc2_listrikx;
    CheckBox cbPowerwin1_listrikx, cbPowerwin2_listrikx;
    CheckBox cbCentral1_listrikx, cbCentral2_listrikx;
    CheckBox cbRemote1_listrikx, cbRemote2_listrikx;
    CheckBox cbSpeedo1_listrikx, cbSpeedo2_listrikx;
    CheckBox cbOdometer1_listrikx, cbOdometer2_listrikx;
    CheckBox cbTacho1_listrikx,  cbTacho2_listrikx;
    CheckBox cbAccu1_listrikx, cbAccu2_listrikx;

    CheckBox cbMesin1_lainx, cbMesin2_lainx;
    CheckBox cbHidraulik1_lainx, cbHidraulik2_lainx;
    CheckBox cbGardan1_lainx, cbGardan2_lainx;
    CheckBox cbAs1_lainx, cbAs2_lainx;
    CheckBox cbBak1_lainx, cbBak2_lainx;
    TextInputEditText etSd_stnkx;
    CheckBox cbStnk1_lainx, cbStnk2_lainx;
    TextInputEditText etSd_bukukirx;
    CheckBox cbBukukir1_lainx, cbBukukir2_lainx;
    TextInputEditText etSd_ijintrayekx;
    CheckBox cbTrayek1_lainx, cbTrayek2_lainx;
    TextInputEditText etSd_ijinusahax;
    CheckBox cbUsaha1_lainx, cbUsaha2_lainx;
    CheckBox cbBukumnl1_lainx, cbBukumnl2_lainx;

    Button btnNext;
    ScrollView svParent, svChild;

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
    String var_note_picPenyimpanan;

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
    String status;

//    int var_cbGrill1_fisikmuka=0;
//    int var_cbGrill2_fisikmuka = 0;
//    int var_cbLampu1_fisikmuka=0;
//    int var_cbLampu2_fisikmuka = 0;
//    int var_cbLampusen1_fisikmuka=0;
//    int var_cbLampusen2_fisikmuka = 0;
//    int var_cbBamper1_fisikmuka=0;
//    int var_cbBamper2_fisikmuka = 0;
//    int var_cbEmblem1_fisikmuka=0;
//    int var_cbEmblem2_fisikmuka = 0;
//    int var_cbTanduk1_fisikmuka=0;
//    int var_cbTanduk2_fisikmuka = 0;
//
//    int var_cbFootstep1_fisikkiri=0;
//    int var_cbFootstep2_fisikkiri=0;
//    int var_cbPintudpn1_fisikkiri=0;
//    int var_cbPintudpn2_fisikkiri=0;
//    int var_cbPintublk1_fisikkiri=0;
//    int var_cbPintublk2_fisikkiri=0;
//    int var_cbBamper1_fisikkiri=0;
//    int var_cbBamper2_fisikkiri=0;
//    int var_cbFenderblk1_fisikkiri=0;
//    int var_cbFenderblk2_fisikkiri=0;
//    int var_cbSpion1_fisikkiri=0;
//    int var_cbSpion2_fisikkiri=0;
//    int var_cbEmblem1_fisikkiri=0;
//    int var_cbEmblem2_fisikkiri=0;
//    int var_rbBan_fisikkiri=0;
//    int var_cbBan1_fisikkiri=0;
//    int var_spBan2_fisikkiri;
//    int var_vspBan2_fisikkiri =0;
//    int var_rbVelgstandard_fisikkiri=0;
//    int var_rbVelgracing_fisikkiri=0;
//    int var_cbVelg1_fisikkiri=0;
//    int var_cbVelg2_fisikkiri=0;
//    int var_cbDop1_fisikkiri=0;
//    int var_cbDop2_fisikkiri=0;
//    int var_cbDopBlk1_fisikkiri=0;
//    int var_cbDopBlk2_fisikkiri=0;
//
//    int var_cbSpoiler1_fisikblkg=0;
//    int var_cbSpoiler2_fisikblkg=0;
//    int var_cbLampu1_fisikblkg=0;
//    int var_cbLampu2_fisikblkg=0;
//    int var_cbLampusen1_fisikblkg=0;
//    int var_cbLampusen2_fisikblkg=0;
//    int var_cbBamper1_fisikblkg=0;
//    int var_cbBamper2_fisikblkg=0;
//    int var_cbEmblem1_fisikblkg=0;
//    int var_cbEmblem2_fisikblkg=0;
//    int var_cbKnalpot1_fisikblkg=0;
//    int var_cbKnalpot2_fisikblkg=0;
//
//    int var_cbFoot1_fisikkanan=0;
//    int var_cbFoot2_fisikkanan=0;
//    int var_cbPintudpn1_fisikkanan=0;
//    int var_cbPintudpn2_fisikkanan=0;
//    int var_cbPintublk1_fisikkanan=0;
//    int var_cbPintublk2_fisikkanan=0;
//    int var_cbBamper1_fisikkanan=0;
//    int var_cbBamper2_fisikkanan=0;
//    int var_cbFenderblk1_fisikkanan=0;
//    int var_cbFenderblk2_fisikkanan=0;
//    int var_cbSpion1_fisikkanan=0;
//    int var_cbSpion2_fisikkanan=0;
//    int var_cbEmblem1_fisikkanan=0;
//    int var_cbEmblem2_fisikkanan=0;
//    int var_rbBan_fisikkanan=0;
//    int var_cbBan1_fisikkanan=0;
//    int var_cbVelg1_fisikkanan=0;
//    int var_cbVelg2_fisikkanan=0;
//    int var_cbDop1_fisikkanan=0;
//    int var_cbDop2_fisikkanan=0;
//    int var_cbDopBlk1_fisikkanan=0;
//    int var_cbDopBlk2_fisikkanan=0;
//    int var_cbKunciktk1_perlengkapan=0;
//    int var_cbKunciktk2_perlengkapan=0;
//    int var_cbSpion1_perlengkapan=0;
//    int var_cbSpion2_perlengkapan=0;
//    int var_cbJok1_perlengkapan=0;
//    int var_cbJok2_perlengkapan=0;
//    int var_cbSarung1_perlengkapan=0;
//    int var_cbSarung2_perlengkapan=0;
//    int var_cbSandaran1_perlengkapan=0;
//    int var_cbSandaran2_perlengkapan=0;
//    int var_cbKarpet1_perlengkapan=0;
//    int var_cbKarpet2_perlengkapan=0;
//    int var_cbPelindung1_perlengkapan=0;
//    int var_cbPelindung2_perlengkapan=0;
//    int var_cbSegitiga1_perlengkapan=0;
//    int var_cbSegitiga2_perlengkapan=0;
//    int var_cbTool1_perlengkapan=0;
//    int var_cbTool2_perlengkapan=0;
//    int var_cbCadangan1_perlengkapan=0;
//    int var_cbCadangan2_perlengkapan=0;
//    int var_cbKunciban1_perlengkapan=0;
//    int var_cbKunciban2_perlengkapan=0;
//    int var_cbDongkrak1_perlengkapan=0;
//    int var_cbDongkrak2_perlengkapan=0;
//    int var_cbAntena1_perlengkapan=0;
//    int var_cbAntena2_perlengkapan=0;
//    int var_cbAirbag1_perlengkapan=0;
//    int var_cbAirbag2_perlengkapan=0;
//
//    int var_cbLampukbt1_listrik=0;
//    int var_cbLampukbt2_listrik=0;
//    int var_cbWiperkacadpn1_listrik=0;
//    int var_cbWiperkacadpn2_listrik=0;
//    int var_cbWiperkacablk1_listrik=0;
//    int var_cbWiperkacablk2_listrik=0;
//    int var_cbKlakson1_listrik=0;
//    int var_cbKlakson2_listrik=0;
//    int var_cbAlarm1_listrik=0;
//    int var_cbAlarm2_listrik=0;
//    int var_cbJam1_listrik=0;
//    int var_cbJam2_listrik=0;
//    int var_cbLighter1_listrik=0;
//    int var_cbLighter2_listrik=0;
//    int var_rbRadio_listrik=0;
//    int var_cbRadio1_listrik=0;
//    int var_cbRadio2_listrik=0;
//    int var_cbPowersup1_listrik=0;
//    int var_cbPowersup2_listrik=0;
//    int var_cbSpeaker1_listrik=0;
//    int var_cbSpeaker2_listrik=0;
//    int var_cbAc1_listrik=0;
//    int var_cbAc2_listrik=0;
//    int var_cbPowerwin1_listrik=0;
//    int var_cbPowerwin2_listrik=0;
//    int var_cbCentral1_listrik=0;
//    int var_cbCentral2_listrik=0;
//    int var_cbRemote1_listrik=0;
//    int var_cbRemote2_listrik=0;
//    int var_cbSpeedo1_listrik=0;
//    int var_cbSpeedo2_listrik=0;
//    int var_cbOdometer1_listrik=0;
//    int var_cbOdometer2_listrik=0;
//    int var_cbTacho1_listrik=0;
//    int var_cbTacho2_listrik=0;
//    int var_cbAccu1_listrik=0;
//    int var_cbAccu2_listrik=0;
//
//    int var_cbMesin1_lain=0;
//    int var_cbMesin2_lain=0;
//    int var_cbHidraulik1_lain=0;
//    int var_cbHidraulik2_lain=0;
//    int var_cbGardan1_lain=0;
//    int var_cbGardan2_lain=0;
//    int var_cbAs1_lain=0;
//    int var_cbAs2_lain=0;
//    int var_cbBak1_lain=0;
//    int var_cbBak2_lain=0;
//    int var_cbStnk1_lain=0;
//    int var_cbStnk2_lain=0;
//    String var_etStnk;
//    int var_cbBukukir1_lain=0;
//    int var_cbBukukir2_lain=0;
//    String var_etBukukir;
//    int var_cbTrayek1_lain=0;
//    int var_cbTrayek2_lain=0;
//    String var_etTrayek;
//    int var_cbUsaha1_lain=0;
//    int var_cbUsaha2_lain=0;
//    String var_etUsaha;
//    int var_cbBukumnl1_lain=0;
//    int var_cbBukumnl2_lain=0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_cek_ceklist);

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

        //==========Scrollview Child========================================

        cbGrill1_fisikmukax = findViewById(R.id.cb_grill1_fisikmukax);
        cbGrill2_fisikmukax = findViewById(R.id.cb_grill2_fisikmukax);
        cbLampu1_fisikmukax = findViewById(R.id.cb_lampu1_fisikmukax);
        cbLampu2_fisikmukax = findViewById(R.id.cb_lampu2_fisikmukax);
        cbLampusen1_fisikmukax = findViewById(R.id.cb_lampusen1_fisikmukax);
        cbLampusen2_fisikmukax = findViewById(R.id.cb_lampusen2_fisikmukax);
        cbBamper1_fisikmukax = findViewById(R.id.cb_bamper1_fisikmukax);
        cbBamper2_fisikmukax = findViewById(R.id.cb_bamper2_fisikmukax);
        cbEmblem1_fisikmukax = findViewById(R.id.cb_emblem1_fisikmukax);
        cbEmblem2_fisikmukax = findViewById(R.id.cb_emblem2_fisikmukax);
        cbTanduk1_fisikmukax = findViewById(R.id.cb_tanduk1_fisikmukax);
        cbTanduk2_fisikmukax = findViewById(R.id.cb_tanduk2_fisikmukax);

        cbFootstep1_fisikkirix = findViewById(R.id.cb_footstep1_fisikkirix);
        cbFootstep2_fisikkirix = findViewById(R.id.cb_footstep2_fisikkirix);
        cbPintudpn1_fisikkirix = findViewById(R.id.cb_pintudpn1_fisikkirix);
        cbPintudpn2_fisikkirix = findViewById(R.id.cb_pintudpn2_fisikkirix);
        cbPintublk1_fisikkirix = findViewById(R.id.cb_pintublk1_fisikkirix);
        cbPintublk2_fisikkirix = findViewById(R.id.cb_pintublk2_fisikkirix);
        cbBamper1_fisikkirix = findViewById(R.id.cb_bamper1_fisikkirix);
        cbBamper2_fisikkirix = findViewById(R.id.cb_bamper2_fisikkirix);
        cbFenderblk1_fisikkirix = findViewById(R.id.cb_fenderblk1_fisikkirix);
        cbFenderblk2_fisikkirix = findViewById(R.id.cb_fenderblk2_fisikkirix);
        cbSpion1_fisikkirix = findViewById(R.id.cb_spion1_fisikkirix);
        cbSpion2_fisikkirix = findViewById(R.id.cb_spion2_fisikkirix);
        cbEmblem1_fisikkirix = findViewById(R.id.cb_emblem1_fisikkirix);
        cbEmblem2_fisikkirix = findViewById(R.id.cb_emblem2_fisikkirix);
        cbBan1_fisikkirix = findViewById(R.id.cb_ban1_fisikkirix);
        rbBanstandard1_fisikkirix = findViewById(R.id.rb_banstandard1_fisikkirix);
        rbBanradial1_fisikkirix = findViewById(R.id.rb_banradial1_fisikkirix);
        cbBan1_fisikkirix = findViewById(R.id.cb_ban1_fisikkirix);
        spBan2_fisikkirix = findViewById(R.id.sp_ban2_fisikkirix);
        rbVelgstandard_fisikkirix = findViewById(R.id.rb_velgstandard_fisikkirix);
        rbVelgracing_fisikkirix = findViewById(R.id.rb_velgracing_fisikkirix);
        cbVelg1_fisikkirix = findViewById(R.id.cb_velg1_fisikkirix);
        cbVelg2_fisikkirix = findViewById(R.id.cb_velg2_fisikkirix);
        cbDop1_fisikkirix = findViewById(R.id.cb_dop1_fisikkirix);
        cbDop2_fisikkirix = findViewById(R.id.cb_dop2_fisikkirix);
        cbDopBlk1_fisikkirix = findViewById(R.id.cb_dopblk1_fisikkirix);
        cbDopBlk2_fisikkirix = findViewById(R.id.cb_dopblk2_fisikkirix);

        cbSpoiler1_fisikblkgx = findViewById(R.id.cb_spoiler1_fisikblkgx);
        cbSpoiler2_fisikblkgx = findViewById(R.id.cb_spoiler2_fisikblkgx);
        cbLampu1_fisikblkgx = findViewById(R.id.cb_lampu1_fisikblkgx);
        cbLampu2_fisikblkgx = findViewById(R.id.cb_lampu2_fisikblkgx);
        cbLampusen1_fisikblkgx = findViewById(R.id.cb_lampusen1_fisikblkgx);
        cbLampusen2_fisikblkgx = findViewById(R.id.cb_lampusen2_fisikblkgx);
        cbBamper1_fisikblkgx = findViewById(R.id.cb_bamper1_fisikblkgx);
        cbBamper2_fisikblkgx = findViewById(R.id.cb_bamper2_fisikblkgx);
        cbEmblem1_fisikblkgx = findViewById(R.id.cb_emblem1_fisikblkgx);
        cbEmblem2_fisikblkgx = findViewById(R.id.cb_emblem2_fisikblkgx);
        cbKnalpot1_fisikblkgx = findViewById(R.id.cb_knalpot1_fisikblkgx);
        cbKnalpot2_fisikblkgx = findViewById(R.id.cb_knalpot2_fisikblkgx);

        cbFoot1_fisikkananx = findViewById(R.id.cb_foot1_fisikkananx);
        cbFoot2_fisikkananx = findViewById(R.id.cb_foot2_fisikkananx);
        cbPintudpn1_fisikkananx = findViewById(R.id.cb_pintudpn1_fisikkananx);
        cbPintudpn2_fisikkananx = findViewById(R.id.cb_pintudpn2_fisikkananx);
        cbPintublk1_fisikkananx = findViewById(R.id.cb_pintublk1_fisikkananx);
        cbPintublk2_fisikkananx = findViewById(R.id.cb_pintublk2_fisikkananx);
        cbBamper1_fisikkananx = findViewById(R.id.cb_bamper1_fisikkananx);
        cbBamper2_fisikkananx = findViewById(R.id.cb_bamper2_fisikkananx);
        cbFenderblk1_fisikkananx = findViewById(R.id.cb_fenderblk1_fisikkananx);
        cbFenderblk2_fisikkananx = findViewById(R.id.cb_fenderblk2_fisikkananx);
        cbSpion1_fisikkananx = findViewById(R.id.cb_spion1_fisikkananx);
        cbSpion2_fisikkananx = findViewById(R.id.cb_spion2_fisikkananx);
        cbEmblem1_fisikkananx = findViewById(R.id.cb_emblem1_fisikkananx);
        cbEmblem2_fisikkananx = findViewById(R.id.cb_emblem2_fisikkananx);
        rbBanstandard_fisikkananx = findViewById(R.id.rb_banstandard_fisikkananx);
        rbBanradial_fisikkananx = findViewById(R.id.rb_banradial_fisikkananx);
        cbBan1_fisikkananx = findViewById(R.id.cb_ban1_fisikkananx);
        spBan2_fisikkananx = findViewById(R.id.sp_ban2_fisikkananx);
        rbVelgstandard_fisikkananx = findViewById(R.id.rb_velgstandard_fisikkananx);
        rbVelgracing_fisikkananx = findViewById(R.id.rb_velgracing_fisikkananx);
        cbVelg1_fisikkananx = findViewById(R.id.cb_velg1_fisikkananx);
        cbVelg2_fisikkananx = findViewById(R.id.cb_velg2_fisikkananx);
        cbDop1_fisikkananx = findViewById(R.id.cb_dop1_fisikkananx);
        cbDop2_fisikkananx = findViewById(R.id.cb_dop2_fisikkananx);
        cbDopBlk1_fisikkananx = findViewById(R.id.cb_dopblk1_fisikkananx);
        cbDopBlk2_fisikkananx = findViewById(R.id.cb_dopblk2_fisikkananx);

        cbKunciktk1_perlengkapanx = findViewById(R.id.cb_kunciktk1_perlengkapanx);
        cbKunciktk2_perlengkapanx = findViewById(R.id.cb_kunciktk2_perlengkapanx);
        cbSpion1_perlengkapanx = findViewById(R.id.cb_spion1_perlengkapanx);
        cbSpion2_perlengkapanx = findViewById(R.id.cb_spion2_perlengkapanx);
        cbJok1_perlengkapanx = findViewById(R.id.cb_jok1_perlengkapanx);
        cbJok2_perlengkapanx = findViewById(R.id.cb_jok2_perlengkapanx);
        cbSarung1_perlengkapanx = findViewById(R.id.cb_sarung1_perlengkapanx);
        cbSarung2_perlengkapanx = findViewById(R.id.cb_sarung2_perlengkapanx);
        cbSandaran1_perlengkapanx = findViewById(R.id.cb_sandaran1_perlengkapanx);
        cbSandaran2_perlengkapanx = findViewById(R.id.cb_sandaran2_perlengkapanx);
        cbKarpet1_perlengkapanx = findViewById(R.id.cb_karpet1_perlengkapanx);
        cbKarpet2_perlengkapanx = findViewById(R.id.cb_karpet2_perlengkapanx);
        cbPelindung1_perlengkapanx = findViewById(R.id.cb_pelindung1_perlengkapanx);
        cbPelindung2_perlengkapanx = findViewById(R.id.cb_pelindung2_perlengkapanx);
        cbSegitiga1_perlengkapanx = findViewById(R.id.cb_segitiga1_perlengkapanx);
        cbSegitiga2_perlengkapanx = findViewById(R.id.cb_segitiga2_perlengkapanx);
        cbTool1_perlengkapanx = findViewById(R.id.cb_tool1_perlengkapanx);
        cbTool2_perlengkapanx = findViewById(R.id.cb_tool2_perlengkapanx);
        cbCadangan1_perlengkapanx = findViewById(R.id.cb_cadangan1_perlengkapanx);
        cbCadangan2_perlengkapanx = findViewById(R.id.cb_cadangan2_perlengkapanx);
        cbKunciban1_perlengkapanx = findViewById(R.id.cb_kunciban1_perlengkapanx);
        cbKunciban2_perlengkapanx = findViewById(R.id.cb_kunciban2_perlengkapanx);
        cbDongkrak1_perlengkapanx = findViewById(R.id.cb_dongkrak1_perlengkapanx);
        cbDongkrak2_perlengkapanx = findViewById(R.id.cb_dongkrak2_perlengkapanx);
        cbAntena1_perlengkapanx = findViewById(R.id.cb_antena1_perlengkapanx);
        cbAntena2_perlengkapanx = findViewById(R.id.cb_antena2_perlengkapanx);
        cbAirbag1_perlengkapanx = findViewById(R.id.cb_airbag1_perlengkapanx);
        cbAirbag2_perlengkapanx = findViewById(R.id.cb_airbag2_perlengkapanx);

        cbLampukbt1_listrikx = findViewById(R.id.cb_lampukbt1_listrikx);
        cbLampukbt2_listrikx = findViewById(R.id.cb_lampukbt2_listrikx);
        cbWiperkacadpn1_listrikx = findViewById(R.id.cb_wiperkacadpn1_listrikx);
        cbWiperkacadpn2_listrikx = findViewById(R.id.cb_wiperkacadpn2_listrikx);
        cbWiperkacablk1_listrikx = findViewById(R.id.cb_wiperkacablk1_listrikx);
        cbWiperkacablk2_listrikx = findViewById(R.id.cb_wiperkacablk2_listrikx);
        cbKlakson1_listrikx = findViewById(R.id.cb_klakson1_listrikx);
        cbKlakson2_listrikx = findViewById(R.id.cb_klakson2_listrikx);
        cbAlarm1_listrikx = findViewById(R.id.cb_alarm1_listrikx);
        cbAlarm2_listrikx = findViewById(R.id.cb_alarm2_listrikx);
        cbJam1_listrikx = findViewById(R.id.cb_jam1_listrikx);
        cbJam2_listrikx = findViewById(R.id.cb_jam2_listrikx);
        cbLighter1_listrikx = findViewById(R.id.cb_lighter1_listrikx);
        cbLighter2_listrikx = findViewById(R.id.cb_lighter2_listrikx);
        rbRadio_listrikx = findViewById(R.id.rb_radio_listrikx);
        rbTape_listrikx = findViewById(R.id.rb_tape_listrikx);
        rbCd_listrikx = findViewById(R.id.rb_cd_listrikx);
        etMerk_listrikx = findViewById(R.id.et_merk_listrikx);
        cbRadio1_listrikx = findViewById(R.id.cb_radio1_listrikx);
        cbRadio2_listrikx = findViewById(R.id.cb_radio2_listrikx);
        cbPowersup1_listrikx = findViewById(R.id.cb_powersup1_listrikx);
        cbPowersup2_listrikx = findViewById(R.id.cb_powersup2_listrikx);
        cbSpeaker1_listrikx = findViewById(R.id.cb_speaker1_listrikx);
        cbSpeaker2_listrikx = findViewById(R.id.cb_speaker2_listrikx);
        cbAc1_listrikx = findViewById(R.id.cb_ac1_listrikx);
        cbAc2_listrikx = findViewById(R.id.cb_ac2_listrikx);
        cbPowerwin1_listrikx = findViewById(R.id.cb_powerwin1_listrikx);
        cbPowerwin2_listrikx = findViewById(R.id.cb_powerwin2_listrikx);
        cbCentral1_listrikx = findViewById(R.id.cb_central1_listrikx);
        cbCentral2_listrikx = findViewById(R.id.cb_central2_listrikx);
        cbRemote1_listrikx = findViewById(R.id.cb_remote1_listrikx);
        cbRemote2_listrikx = findViewById(R.id.cb_remote2_listrikx);
        cbSpeedo1_listrikx = findViewById(R.id.cb_speedo1_listrikx);
        cbSpeedo2_listrikx = findViewById(R.id.cb_speedo2_listrikx);
        cbOdometer1_listrikx = findViewById(R.id.cb_odometer1_listrikx);
        cbOdometer2_listrikx = findViewById(R.id.cb_odometer2_listrikx);
        cbTacho1_listrikx = findViewById(R.id.cb_tacho1_listrikx);
        cbTacho2_listrikx = findViewById(R.id.cb_tacho2_listrikx);
        cbAccu1_listrikx = findViewById(R.id.cb_accu1_listrikx);
        cbAccu2_listrikx = findViewById(R.id.cb_accu2_listrikx);

        cbMesin1_lainx = findViewById(R.id.cb_mesin1_lainx);
        cbMesin2_lainx = findViewById(R.id.cb_mesin2_lainx);
        cbHidraulik1_lainx = findViewById(R.id.cb_hidraulik1_lainx);
        cbHidraulik2_lainx = findViewById(R.id.cb_hidraulik2_lainx);
        cbGardan1_lainx = findViewById(R.id.cb_gardan1_lainx);
        cbGardan2_lainx = findViewById(R.id.cb_gardan2_lainx);
        cbAs1_lainx = findViewById(R.id.cb_as1_lainx);
        cbAs2_lainx = findViewById(R.id.cb_as2_lainx);
        cbBak1_lainx = findViewById(R.id.cb_bak1_lainx);
        cbBak2_lainx = findViewById(R.id.cb_bak2_lainx);
        etSd_stnkx = findViewById(R.id.et_sd_stnkx);
        cbStnk1_lainx = findViewById(R.id.cb_stnk1_lainx);
        cbStnk2_lainx = findViewById(R.id.cb_stnk2_lainx);
        etSd_bukukirx = findViewById(R.id.et_sd_bukukirx);

        cbBukukir1_lainx = findViewById(R.id.cb_bukukir1_lainx);
        cbBukukir2_lainx = findViewById(R.id.cb_bukukir2_lainx);
        etSd_ijintrayekx = findViewById(R.id.et_sd_ijintrayekx);
        cbTrayek1_lainx = findViewById(R.id.cb_trayek1_lainx);
        cbTrayek2_lainx = findViewById(R.id.cb_trayek2_lainx);
        etSd_ijinusahax = findViewById(R.id.et_sd_ijinusahax);
        cbUsaha1_lainx = findViewById(R.id.cb_usaha1_lainx);
        cbUsaha2_lainx = findViewById(R.id.cb_usaha2_lainx);
        cbBukumnl1_lainx = findViewById(R.id.cb_bukumnl1_lainx);
        cbBukumnl2_lainx = findViewById(R.id.cb_bukumnl2_lainx);

        btnNext = findViewById(R.id.btn_next);
        svParent = findViewById(R.id.sv_parent);
        svChild = findViewById(R.id.sv_child);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        _name = user.get(UserSessionManager.SES_NAME);
        _fullname = user.get(UserSessionManager.SES_FULLNAME);
        _appid = user.get(UserSessionManager.SES_APPID);
        _ccode = user.get(UserSessionManager.SES_CCODE);
        _token = user.get(UserSessionManager.SES_TOKEN);
        _group = user.get(UserSessionManager.SES_GROUP);
        
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

        myCalendar = Calendar.getInstance();
        etSd_stnkx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_stnk = new DatePickerDialog(BpCekCeklistActivity.this, datestnk, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_stnk.show();
            }
        });
        etSd_bukukirx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_bukukir = new DatePickerDialog(BpCekCeklistActivity.this, datebukukir, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_bukukir.show();
            }
        });
        etSd_ijintrayekx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_ijintrayek = new DatePickerDialog(BpCekCeklistActivity.this, dateijintrayek, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_ijintrayek.show();
            }
        });
        etSd_ijinusahax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd_ijinusaha = new DatePickerDialog(BpCekCeklistActivity.this, dateijinusaha, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd_ijinusaha.show();
            }
        });


        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance(); //creating  database oject
        RealmResults<Ceklist> ceklists = realm.where(Ceklist.class).findAllAsync();
        //fetching the data
        //ceklists.load();

        Bundle b = getIntent().getExtras();
        Log.e("dapatkan",b.toString());
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
            Log.wtf("stnk:",var_etStnk);
            Log.wtf("var_cbMesin1:",var_cbMesin1_lain.toString());
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
            var_note_picPenyimpanan= b.getString("note_picpenyimpanan");

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
            status = b.getString("status");
//            Toast.makeText(BpCekCeklistActivity.this,var_picPenyimpanan,Toast.LENGTH_SHORT).show();
        }

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

      

        //---------------------------------------------------------------------------------------------
        //Bisnis Partner Checklist
        //---------------------------------------------------------------------------------------------
        cbGrill1_fisikmukax.setChecked(var_cbGrill1_fisikmuka);
        cbGrill1_fisikmukax.setText((var_cbGrill1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbGrill2_fisikmukax.setChecked((var_cbGrill1_fisikmuka == true) ? var_cbGrill2_fisikmuka : false);
        cbGrill2_fisikmukax.setVisibility((var_cbGrill1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbGrill2_fisikmukax.setText((var_cbGrill2_fisikmuka == true) ? "Baik" : "Rusak");

        cbLampu1_fisikmukax.setChecked(var_cbLampu1_fisikmuka);
        cbLampu1_fisikmukax.setText((var_cbLampu1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbLampu2_fisikmukax.setChecked((var_cbLampu1_fisikmuka == true) ? var_cbLampu2_fisikmuka : false);
        cbLampu2_fisikmukax.setVisibility((var_cbLampu1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampu2_fisikmukax.setText((var_cbLampu2_fisikmuka == true) ? "Baik" : "Rusak");

        cbLampusen1_fisikmukax.setChecked(var_cbLampusen1_fisikmuka);
        cbLampusen1_fisikmukax.setText((var_cbLampusen1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbLampusen2_fisikmukax.setChecked(var_cbLampusen2_fisikmuka);
        cbLampusen2_fisikmukax.setVisibility((var_cbLampusen1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampusen2_fisikmukax.setText((var_cbLampusen2_fisikmuka == true) ? "Baik" : "Rusak");

        cbBamper1_fisikmukax.setChecked(var_cbBamper1_fisikmuka);
        cbBamper1_fisikmukax.setText((var_cbBamper1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbBamper2_fisikmukax.setChecked(var_cbBamper2_fisikmuka);
        cbBamper2_fisikmukax.setVisibility((var_cbBamper1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbBamper2_fisikmukax.setText((var_cbBamper2_fisikmuka == true) ? "Baik" : "Rusak");

        cbEmblem1_fisikmukax.setChecked(var_cbEmblem1_fisikmuka);
        cbEmblem1_fisikmukax.setText((var_cbEmblem1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbEmblem2_fisikmukax.setChecked(var_cbEmblem2_fisikmuka);
        cbEmblem2_fisikmukax.setVisibility((var_cbEmblem1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbEmblem2_fisikmukax.setText((var_cbEmblem2_fisikmuka == true) ? "Baik" : "Rusak");

        cbTanduk1_fisikmukax.setChecked(var_cbTanduk1_fisikmuka);
        cbTanduk1_fisikmukax.setText((var_cbTanduk1_fisikmuka == true) ? "Ada" : "Tidak Ada");
        cbTanduk2_fisikmukax.setChecked(var_cbTanduk2_fisikmuka);
        cbTanduk2_fisikmukax.setVisibility((var_cbTanduk1_fisikmuka == true) ? View.VISIBLE : View.INVISIBLE);
        cbTanduk2_fisikmukax.setText((var_cbTanduk2_fisikmuka == true) ? "Baik" : "Rusak");


        cbFootstep1_fisikkirix.setChecked(var_cbFootstep1_fisikkiri);
        cbFootstep1_fisikkirix.setText((var_cbFootstep1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbFootstep2_fisikkirix.setChecked(var_cbFootstep2_fisikkiri);
        cbFootstep2_fisikkirix.setVisibility((var_cbFootstep1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbFootstep2_fisikkirix.setText((var_cbFootstep2_fisikkiri == true) ? "Baik" : "Rusak");

        cbPintudpn1_fisikkirix.setChecked(var_cbPintudpn1_fisikkiri);
        cbPintudpn1_fisikkirix.setText((var_cbPintudpn1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbPintudpn2_fisikkirix.setChecked(var_cbPintudpn2_fisikkiri);
        cbPintudpn2_fisikkirix.setVisibility((var_cbPintudpn1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbPintudpn2_fisikkirix.setText((var_cbPintudpn2_fisikkiri == true) ? "Baik" : "Rusak");

        cbPintublk1_fisikkirix.setChecked(var_cbPintublk1_fisikkiri);
        cbPintublk1_fisikkirix.setText((var_cbPintublk1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbPintublk2_fisikkirix.setChecked(var_cbPintublk2_fisikkiri);
        cbPintublk2_fisikkirix.setVisibility((var_cbPintublk1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbPintublk2_fisikkirix.setText((var_cbPintublk2_fisikkiri == true) ? "Baik" : "Rusak");

        cbBamper1_fisikkirix.setChecked(var_cbBamper1_fisikkiri);
        cbBamper1_fisikkirix.setText((var_cbBamper1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbBamper2_fisikkirix.setChecked(var_cbBamper2_fisikkiri);
        cbBamper2_fisikkirix.setVisibility((var_cbBamper1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbBamper2_fisikkirix.setText((var_cbBamper2_fisikkiri == true) ? "Baik" : "Rusak");

        cbFenderblk1_fisikkirix.setChecked(var_cbFenderblk1_fisikkiri);
        cbFenderblk1_fisikkirix.setText((var_cbFenderblk1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbFenderblk2_fisikkirix.setChecked(var_cbFenderblk2_fisikkiri);
        cbFenderblk2_fisikkirix.setVisibility((var_cbFenderblk1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbFenderblk2_fisikkirix.setText((var_cbFenderblk2_fisikkiri == true) ? "Baik" : "Rusak");

        cbSpion1_fisikkirix.setChecked(var_cbSpion1_fisikkiri);
        cbSpion1_fisikkirix.setText((var_cbSpion1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbSpion2_fisikkirix.setChecked(var_cbSpion2_fisikkiri);
        cbSpion2_fisikkirix.setVisibility((var_cbSpion1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpion2_fisikkirix.setText((var_cbSpion2_fisikkiri == true) ? "Baik" : "Rusak");

        cbEmblem1_fisikkirix.setChecked(var_cbEmblem1_fisikkiri);
        cbEmblem1_fisikkirix.setText((var_cbEmblem1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbEmblem2_fisikkirix.setChecked(var_cbEmblem2_fisikkiri);
        cbEmblem2_fisikkirix.setVisibility((var_cbEmblem1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbEmblem2_fisikkirix.setText((var_cbEmblem2_fisikkiri == true) ? "Baik" : "Rusak");

        cbBan1_fisikkirix.setChecked(var_cbBan1_fisikkiri);
//        rbBanstandard1_fisikkirix.setChecked(var_cbFootstep1_fisikkiri);
//        rbBanradial1_fisikkirix.setChecked(var_cbFootstep1_fisikkiri);

        rbVelgstandard_fisikkirix.setChecked(var_rbVelgstandard_fisikkiri);
        rbVelgracing_fisikkirix.setChecked(var_rbVelgracing_fisikkiri);

        cbVelg1_fisikkirix.setChecked(var_cbVelg1_fisikkiri);
        cbVelg1_fisikkirix.setText((var_cbVelg1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbVelg2_fisikkirix.setChecked(var_cbVelg2_fisikkiri);
        cbVelg2_fisikkirix.setVisibility((var_cbVelg1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbVelg2_fisikkirix.setText((var_cbVelg2_fisikkiri == true) ? "Baik" : "Rusak");

        cbDop1_fisikkirix.setChecked(var_cbDop1_fisikkiri);
        cbDop1_fisikkirix.setText((var_cbDop1_fisikkiri == true) ? "Ada" : "Tidak Ada");
        cbDop2_fisikkirix.setChecked(var_cbDop2_fisikkiri);
        cbDop2_fisikkirix.setVisibility((var_cbDop1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
        cbDop2_fisikkirix.setText((var_cbDop2_fisikkiri == true) ? "Baik" : "Rusak");

//        cbDopBlk1_fisikkirix.setChecked(var_cbDop1_fisikkiri);
//        cbDopBlk1_fisikkirix.setText((var_cbDop1_fisikkiri == true) ? "Ada" : "Tidak Ada");
//        cbDopBlk2_fisikkirix.setChecked(var_cbDop2_fisikkiri);
//        cbDopBlk2_fisikkirix.setVisibility((var_cbDop1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
//        cbDopBlk2_fisikkirix.setText((var_cbDop2_fisikkiri == true) ? "Baik" : "Rusak");

        cbSpoiler1_fisikblkgx.setChecked(var_cbSpoiler1_fisikblkg);
        cbSpoiler1_fisikblkgx.setText((var_cbSpoiler1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbSpoiler2_fisikblkgx.setChecked(var_cbSpoiler2_fisikblkg);
        cbSpoiler2_fisikblkgx.setVisibility((var_cbSpoiler2_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpoiler2_fisikblkgx.setText((var_cbSpoiler2_fisikblkg == true) ? "Baik" : "Rusak");

        cbLampu1_fisikblkgx.setChecked(var_cbLampu1_fisikblkg);
        cbLampu1_fisikblkgx.setText((var_cbLampu1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbLampu2_fisikblkgx.setChecked(var_cbLampu2_fisikblkg);
        cbLampu2_fisikblkgx.setVisibility((var_cbLampu1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampu2_fisikblkgx.setText((var_cbLampu2_fisikblkg == true) ? "Baik" : "Rusak");

        cbLampusen1_fisikblkgx.setChecked(var_cbLampusen1_fisikblkg);
        cbLampusen1_fisikblkgx.setText((var_cbLampusen1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbLampusen2_fisikblkgx.setChecked(var_cbLampusen2_fisikblkg);
        cbLampusen2_fisikblkgx.setVisibility((var_cbLampusen1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampusen2_fisikblkgx.setText((var_cbLampusen2_fisikblkg == true) ? "Baik" : "Rusak");

        cbBamper1_fisikblkgx.setChecked(var_cbBamper1_fisikblkg);
        cbBamper1_fisikblkgx.setText((var_cbBamper1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbBamper2_fisikblkgx.setChecked(var_cbBamper2_fisikblkg);
        cbBamper2_fisikblkgx.setVisibility((var_cbBamper1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbBamper2_fisikblkgx.setText((var_cbBamper2_fisikblkg == true) ? "Baik" : "Rusak");

        cbEmblem1_fisikblkgx.setChecked(var_cbEmblem1_fisikblkg);
        cbEmblem1_fisikblkgx.setText((var_cbEmblem1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbEmblem2_fisikblkgx.setChecked(var_cbEmblem2_fisikblkg);
        cbEmblem2_fisikblkgx.setVisibility((var_cbEmblem1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbEmblem2_fisikblkgx.setText((var_cbEmblem2_fisikblkg == true) ? "Baik" : "Rusak");

        cbKnalpot1_fisikblkgx.setChecked(var_cbKnalpot1_fisikblkg);
        cbKnalpot1_fisikblkgx.setText((var_cbKnalpot1_fisikblkg == true) ? "Ada" : "Tidak Ada");
        cbKnalpot2_fisikblkgx.setChecked(var_cbKnalpot2_fisikblkg);
        cbKnalpot2_fisikblkgx.setVisibility((var_cbKnalpot1_fisikblkg == true) ? View.VISIBLE : View.INVISIBLE);
        cbKnalpot2_fisikblkgx.setText((var_cbKnalpot2_fisikblkg == true) ? "Baik" : "Rusak");

        cbFoot1_fisikkananx.setChecked(var_cbFoot1_fisikkanan);
        cbFoot1_fisikkananx.setText((var_cbFoot1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbFoot2_fisikkananx.setChecked(var_cbFoot2_fisikkanan);
        cbFoot2_fisikkananx.setVisibility((var_cbFoot1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbFoot2_fisikkananx.setText((var_cbFoot2_fisikkanan == true) ? "Baik" : "Rusak");

        cbPintudpn1_fisikkananx.setChecked(var_cbPintudpn1_fisikkanan);
        cbPintudpn1_fisikkananx.setText((var_cbPintudpn1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbPintudpn2_fisikkananx.setChecked(var_cbPintudpn2_fisikkanan);
        cbPintudpn2_fisikkananx.setVisibility((var_cbPintudpn1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbPintudpn2_fisikkananx.setText((var_cbPintudpn2_fisikkanan == true) ? "Baik" : "Rusak");

        cbPintublk1_fisikkananx.setChecked(var_cbPintublk1_fisikkanan);
        cbPintublk1_fisikkananx.setText((var_cbPintublk1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbPintublk2_fisikkananx.setChecked(var_cbPintublk2_fisikkanan);
        cbPintublk2_fisikkananx.setVisibility((var_cbPintublk1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbPintublk2_fisikkananx.setText((var_cbPintublk2_fisikkanan == true) ? "Baik" : "Rusak");

        cbBamper1_fisikkananx.setChecked(var_cbBamper1_fisikkanan);
        cbBamper1_fisikkananx.setText((var_cbBamper1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbBamper2_fisikkananx.setChecked(var_cbBamper2_fisikkanan);
        cbBamper2_fisikkananx.setVisibility((var_cbBamper1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbBamper2_fisikkananx.setText((var_cbBamper2_fisikkanan == true) ? "Baik" : "Rusak");

        cbFenderblk1_fisikkananx.setChecked(var_cbFenderblk1_fisikkanan);
        cbFenderblk1_fisikkananx.setText((var_cbFenderblk1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbFenderblk2_fisikkananx.setChecked(var_cbFenderblk2_fisikkanan);
        cbFenderblk2_fisikkananx.setVisibility((var_cbFenderblk1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbFenderblk2_fisikkananx.setText((var_cbFenderblk2_fisikkanan == true) ? "Baik" : "Rusak");

        cbSpion1_fisikkananx.setChecked(var_cbSpion1_fisikkanan);
        cbSpion1_fisikkananx.setText((var_cbSpion1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbSpion2_fisikkananx.setChecked(var_cbSpion2_fisikkanan);
        cbSpion2_fisikkananx.setVisibility((var_cbSpion1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpion2_fisikkananx.setText((var_cbSpion2_fisikkanan == true) ? "Baik" : "Rusak");

        cbEmblem1_fisikkananx.setChecked(var_cbEmblem1_fisikkanan);
        cbEmblem1_fisikkananx.setText((var_cbEmblem1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbEmblem2_fisikkananx.setChecked(var_cbEmblem2_fisikkanan);
        cbEmblem2_fisikkananx.setVisibility((var_cbEmblem1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbEmblem2_fisikkananx.setText((var_cbEmblem2_fisikkanan == true) ? "Baik" : "Rusak");

        cbBan1_fisikkananx.setChecked(var_cbBan1_fisikkanan);
        cbBan1_fisikkananx.setText((var_cbBan1_fisikkanan == true) ? "Ada" : "Tidak Ada");
//        cbFenderblk2_fisikkananx.setChecked(var_cbDop2_fisikkiri);
//        cbFenderblk2_fisikkananx.setVisibility((var_cbDop1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
//        cbFenderblk2_fisikkananx.setText((var_cbDop2_fisikkiri == true) ? "Baik" : "Rusak");

        cbVelg1_fisikkananx.setChecked(var_cbVelg1_fisikkanan);
        cbVelg1_fisikkananx.setText((var_cbVelg1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbVelg2_fisikkananx.setChecked(var_cbVelg2_fisikkanan);
        cbVelg2_fisikkananx.setVisibility((var_cbVelg1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbVelg2_fisikkananx.setText((var_cbVelg2_fisikkanan == true) ? "Baik" : "Rusak");

        cbDop1_fisikkananx.setChecked(var_cbDop1_fisikkanan);
        cbDop1_fisikkananx.setText((var_cbDop1_fisikkanan == true) ? "Ada" : "Tidak Ada");
        cbDop2_fisikkananx.setChecked(var_cbDop2_fisikkanan);
        cbDop2_fisikkananx.setVisibility((var_cbDop1_fisikkanan == true) ? View.VISIBLE : View.INVISIBLE);
        cbDop2_fisikkananx.setText((var_cbDop2_fisikkanan == true) ? "Baik" : "Rusak");

//        cbDopBlk1_fisikkananx.setChecked(var_cbDop1_fisikkiri);
//        cbDopBlk1_fisikkananx.setText((var_cbDop1_fisikkiri == true) ? "Ada" : "Tidak Ada");
//        cbDopBlk2_fisikkananx.setChecked(var_cbDop2_fisikkiri);
//        cbDopBlk2_fisikkananx.setVisibility((var_cbDop1_fisikkiri == true) ? View.VISIBLE : View.INVISIBLE);
//        cbDopBlk2_fisikkananx.setText((var_cbDop2_fisikkiri == true) ? "Baik" : "Rusak");

        cbKunciktk1_perlengkapanx.setChecked(var_cbKunciktk1_perlengkapan);
        cbKunciktk1_perlengkapanx.setText((var_cbKunciktk1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbKunciktk2_perlengkapanx.setChecked(var_cbKunciktk2_perlengkapan);
        cbKunciktk2_perlengkapanx.setVisibility((var_cbKunciktk1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbKunciktk2_perlengkapanx.setText((var_cbKunciktk2_perlengkapan == true) ? "Baik" : "Rusak");

        cbSpion1_perlengkapanx.setChecked(var_cbSpion1_perlengkapan);
        cbSpion1_perlengkapanx.setText((var_cbSpion1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbSpion2_perlengkapanx.setChecked(var_cbSpion2_perlengkapan);
        cbSpion2_perlengkapanx.setVisibility((var_cbSpion1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpion2_perlengkapanx.setText((var_cbSpion2_perlengkapan == true) ? "Baik" : "Rusak");

        cbJok1_perlengkapanx.setChecked(var_cbJok1_perlengkapan);
        cbJok1_perlengkapanx.setText((var_cbJok1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbJok2_perlengkapanx.setChecked(var_cbJok2_perlengkapan);
        cbJok2_perlengkapanx.setVisibility((var_cbJok1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbJok2_perlengkapanx.setText((var_cbJok2_perlengkapan == true) ? "Baik" : "Rusak");

        cbSarung1_perlengkapanx.setChecked(var_cbSarung1_perlengkapan);
        cbSarung1_perlengkapanx.setText((var_cbSarung1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbSarung2_perlengkapanx.setChecked(var_cbSarung2_perlengkapan);
        cbSarung2_perlengkapanx.setVisibility((var_cbSarung1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSarung2_perlengkapanx.setText((var_cbSarung2_perlengkapan == true) ? "Baik" : "Rusak");

        cbSandaran1_perlengkapanx.setChecked(var_cbSandaran1_perlengkapan);
        cbSandaran1_perlengkapanx.setText((var_cbSandaran1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbSandaran2_perlengkapanx.setChecked(var_cbSandaran2_perlengkapan);
        cbSandaran2_perlengkapanx.setVisibility((var_cbSandaran1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSandaran2_perlengkapanx.setText((var_cbSandaran2_perlengkapan == true) ? "Baik" : "Rusak");

        cbKarpet1_perlengkapanx.setChecked(var_cbKarpet1_perlengkapan);
        cbKarpet1_perlengkapanx.setText((var_cbKarpet1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbKarpet2_perlengkapanx.setChecked(var_cbKarpet2_perlengkapan);
        cbKarpet2_perlengkapanx.setVisibility((var_cbKarpet1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbKarpet2_perlengkapanx.setText((var_cbKarpet2_perlengkapan == true) ? "Baik" : "Rusak");

        cbPelindung1_perlengkapanx.setChecked(var_cbPelindung1_perlengkapan);
        cbPelindung1_perlengkapanx.setText((var_cbPelindung1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbPelindung2_perlengkapanx.setChecked(var_cbPelindung2_perlengkapan);
        cbPelindung2_perlengkapanx.setVisibility((var_cbPelindung1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbPelindung2_perlengkapanx.setText((var_cbPelindung2_perlengkapan == true) ? "Baik" : "Rusak");

        cbSegitiga1_perlengkapanx.setChecked(var_cbSegitiga1_perlengkapan);
        cbSegitiga1_perlengkapanx.setText((var_cbSegitiga1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbSegitiga2_perlengkapanx.setChecked(var_cbSegitiga2_perlengkapan);
        cbSegitiga2_perlengkapanx.setVisibility((var_cbSegitiga1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbSegitiga2_perlengkapanx.setText((var_cbSegitiga2_perlengkapan == true) ? "Baik" : "Rusak");

        cbTool1_perlengkapanx.setChecked(var_cbTool1_perlengkapan);
        cbTool1_perlengkapanx.setText((var_cbTool1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbTool2_perlengkapanx.setChecked(var_cbTool2_perlengkapan);
        cbTool2_perlengkapanx.setVisibility((var_cbTool1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbTool2_perlengkapanx.setText((var_cbTool2_perlengkapan == true) ? "Baik" : "Rusak");

        cbCadangan1_perlengkapanx.setChecked(var_cbCadangan1_perlengkapan);
        cbCadangan1_perlengkapanx.setText((var_cbCadangan1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbCadangan2_perlengkapanx.setChecked(var_cbCadangan2_perlengkapan);
        cbCadangan2_perlengkapanx.setVisibility((var_cbCadangan1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbCadangan2_perlengkapanx.setText((var_cbCadangan2_perlengkapan == true) ? "Baik" : "Rusak");

        cbKunciban1_perlengkapanx.setChecked(var_cbKunciban1_perlengkapan);
        cbKunciban1_perlengkapanx.setText((var_cbKunciban1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbKunciban2_perlengkapanx.setChecked(var_cbKunciban2_perlengkapan);
        cbKunciban2_perlengkapanx.setVisibility((var_cbKunciban1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbKunciban2_perlengkapanx.setText((var_cbKunciban2_perlengkapan == true) ? "Baik" : "Rusak");

        cbDongkrak1_perlengkapanx.setChecked(var_cbDongkrak1_perlengkapan);
        cbDongkrak1_perlengkapanx.setText((var_cbDongkrak1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbDongkrak2_perlengkapanx.setChecked(var_cbDongkrak2_perlengkapan);
        cbDongkrak2_perlengkapanx.setVisibility((var_cbDongkrak1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbDongkrak2_perlengkapanx.setText((var_cbDongkrak2_perlengkapan == true) ? "Baik" : "Rusak");

        cbAntena1_perlengkapanx.setChecked(var_cbAntena1_perlengkapan);
        cbAntena1_perlengkapanx.setText((var_cbAntena1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbAntena2_perlengkapanx.setChecked(var_cbAntena2_perlengkapan);
        cbAntena2_perlengkapanx.setVisibility((var_cbAntena1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbAntena2_perlengkapanx.setText((var_cbAntena2_perlengkapan == true) ? "Baik" : "Rusak");

        cbAirbag1_perlengkapanx.setChecked(var_cbAirbag1_perlengkapan);
        cbAirbag1_perlengkapanx.setText((var_cbAirbag1_perlengkapan == true) ? "Ada" : "Tidak Ada");
        cbAirbag2_perlengkapanx.setChecked(var_cbAirbag2_perlengkapan);
        cbAirbag2_perlengkapanx.setVisibility((var_cbAirbag1_perlengkapan == true) ? View.VISIBLE : View.INVISIBLE);
        cbAirbag2_perlengkapanx.setText((var_cbAirbag2_perlengkapan == true) ? "Baik" : "Rusak");

        cbLampukbt1_listrikx.setChecked(var_cbLampukbt1_listrik);
        cbLampukbt1_listrikx.setText((var_cbLampukbt1_listrik == true) ? "Ada" : "Tidak Ada");
        cbLampukbt2_listrikx.setChecked(var_cbLampukbt2_listrik);
        cbLampukbt2_listrikx.setVisibility((var_cbLampukbt1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbLampukbt2_listrikx.setText((var_cbLampukbt2_listrik == true) ? "Baik" : "Rusak");

        cbWiperkacadpn1_listrikx.setChecked(var_cbWiperkacadpn1_listrik);
        cbWiperkacadpn1_listrikx.setText((var_cbWiperkacadpn1_listrik == true) ? "Ada" : "Tidak Ada");
        cbWiperkacadpn2_listrikx.setChecked(var_cbWiperkacadpn2_listrik);
        cbWiperkacadpn2_listrikx.setVisibility((var_cbWiperkacadpn1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbWiperkacadpn2_listrikx.setText((var_cbWiperkacadpn2_listrik == true) ? "Baik" : "Rusak");

        cbWiperkacablk1_listrikx.setChecked(var_cbWiperkacablk1_listrik);
        cbWiperkacablk1_listrikx.setText((var_cbWiperkacablk1_listrik == true) ? "Ada" : "Tidak Ada");
        cbWiperkacablk2_listrikx.setChecked(var_cbWiperkacablk2_listrik);
        cbWiperkacablk2_listrikx.setVisibility((var_cbWiperkacablk1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbWiperkacablk2_listrikx.setText((var_cbWiperkacablk2_listrik == true) ? "Baik" : "Rusak");

        cbKlakson1_listrikx.setChecked(var_cbKlakson1_listrik);
        cbKlakson1_listrikx.setText((var_cbKlakson1_listrik == true) ? "Ada" : "Tidak Ada");
        cbKlakson2_listrikx.setChecked(var_cbKlakson2_listrik);
        cbKlakson2_listrikx.setVisibility((var_cbKlakson1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbKlakson2_listrikx.setText((var_cbKlakson2_listrik == true) ? "Baik" : "Rusak");

        cbAlarm1_listrikx.setChecked(var_cbAlarm1_listrik);
        cbAlarm1_listrikx.setText((var_cbAlarm1_listrik == true) ? "Ada" : "Tidak Ada");
        cbAlarm2_listrikx.setChecked(var_cbAlarm2_listrik);
        cbAlarm2_listrikx.setVisibility((var_cbAlarm1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbAlarm2_listrikx.setText((var_cbAlarm2_listrik == true) ? "Baik" : "Rusak");

        cbJam1_listrikx.setChecked(var_cbJam1_listrik);
        cbJam1_listrikx.setText((var_cbJam1_listrik == true) ? "Ada" : "Tidak Ada");
        cbJam2_listrikx.setChecked(var_cbJam2_listrik);
        cbJam2_listrikx.setVisibility((var_cbJam1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbJam2_listrikx.setText((var_cbJam2_listrik == true) ? "Baik" : "Rusak");

        cbLighter1_listrikx.setChecked(var_cbLighter1_listrik);
        cbLighter1_listrikx.setText((var_cbLighter1_listrik == true) ? "Ada" : "Tidak Ada");
        cbLighter2_listrikx.setChecked(var_cbLighter2_listrik);
        cbLighter2_listrikx.setVisibility((var_cbLighter1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbLighter2_listrikx.setText((var_cbLighter2_listrik == true) ? "Baik" : "Rusak");

        cbRadio1_listrikx.setChecked(var_cbRadio1_listrik);
        cbRadio1_listrikx.setText((var_cbRadio1_listrik == true) ? "Ada" : "Tidak Ada");
        cbRadio2_listrikx.setChecked(var_cbRadio2_listrik);
        cbRadio2_listrikx.setVisibility((var_cbRadio1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbRadio2_listrikx.setText((var_cbRadio2_listrik == true) ? "Baik" : "Rusak");

        cbPowersup1_listrikx.setChecked(var_cbPowersup1_listrik);
        cbPowersup1_listrikx.setText((var_cbPowersup1_listrik == true) ? "Ada" : "Tidak Ada");
        cbPowersup2_listrikx.setChecked(var_cbPowersup2_listrik);
        cbPowersup2_listrikx.setVisibility((var_cbPowersup1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbPowersup2_listrikx.setText((var_cbPowersup2_listrik == true) ? "Baik" : "Rusak");

        cbSpeaker1_listrikx.setChecked(var_cbSpeaker1_listrik);
        cbSpeaker1_listrikx.setText((var_cbSpeaker1_listrik == true) ? "Ada" : "Tidak Ada");
        cbSpeaker2_listrikx.setChecked(var_cbSpeaker2_listrik);
        cbSpeaker2_listrikx.setVisibility((var_cbSpeaker1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpeaker2_listrikx.setText((var_cbSpeaker2_listrik == true) ? "Baik" : "Rusak");

        cbAc1_listrikx.setChecked(var_cbAc1_listrik);
        cbAc1_listrikx.setText((var_cbAc1_listrik == true) ? "Ada" : "Tidak Ada");
        cbAc2_listrikx.setChecked(var_cbAc2_listrik);
        cbAc2_listrikx.setVisibility((var_cbAc1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbAc2_listrikx.setText((var_cbAc2_listrik == true) ? "Baik" : "Rusak");

        cbPowerwin1_listrikx.setChecked(var_cbPowerwin1_listrik);
        cbPowerwin1_listrikx.setText((var_cbPowerwin1_listrik == true) ? "Ada" : "Tidak Ada");
        cbPowerwin2_listrikx.setChecked(var_cbPowerwin2_listrik);
        cbPowerwin2_listrikx.setVisibility((var_cbPowerwin1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbPowerwin2_listrikx.setText((var_cbPowerwin2_listrik == true) ? "Baik" : "Rusak");

        cbCentral1_listrikx.setChecked(var_cbCentral1_listrik);
        cbCentral1_listrikx.setText((var_cbCentral1_listrik == true) ? "Ada" : "Tidak Ada");
        cbCentral2_listrikx.setChecked(var_cbCentral2_listrik);
        cbCentral2_listrikx.setVisibility((var_cbCentral1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbCentral2_listrikx.setText((var_cbCentral2_listrik == true) ? "Baik" : "Rusak");

        cbRemote1_listrikx.setChecked(var_cbRemote1_listrik);
        cbRemote1_listrikx.setText((var_cbRemote1_listrik == true) ? "Ada" : "Tidak Ada");
        cbRemote2_listrikx.setChecked(var_cbRemote2_listrik);
        cbRemote2_listrikx.setVisibility((var_cbRemote1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbRemote2_listrikx.setText((var_cbRemote2_listrik == true) ? "Baik" : "Rusak");

        cbSpeedo1_listrikx.setChecked(var_cbSpeedo1_listrik);
        cbSpeedo1_listrikx.setText((var_cbSpeedo1_listrik == true) ? "Ada" : "Tidak Ada");
        cbSpeedo2_listrikx.setChecked(var_cbSpeedo2_listrik);
        cbSpeedo2_listrikx.setVisibility((var_cbSpeedo1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbSpeedo2_listrikx.setText((var_cbSpeedo2_listrik == true) ? "Baik" : "Rusak");

        cbOdometer1_listrikx.setChecked(var_cbOdometer1_listrik);
        cbOdometer1_listrikx.setText((var_cbOdometer1_listrik == true) ? "Ada" : "Tidak Ada");
        cbOdometer2_listrikx.setChecked(var_cbOdometer2_listrik);
        cbOdometer2_listrikx.setVisibility((var_cbOdometer1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbOdometer2_listrikx.setText((var_cbOdometer2_listrik == true) ? "Baik" : "Rusak");

        cbTacho1_listrikx.setChecked(var_cbTacho1_listrik);
        cbTacho1_listrikx.setText((var_cbTacho1_listrik == true) ? "Ada" : "Tidak Ada");
        cbTacho2_listrikx.setChecked(var_cbTacho2_listrik);
        cbTacho2_listrikx.setVisibility((var_cbTacho1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbTacho2_listrikx.setText((var_cbTacho2_listrik == true) ? "Baik" : "Rusak");

        cbAccu1_listrikx.setChecked(var_cbAccu1_listrik);
        cbAccu1_listrikx.setText((var_cbAccu1_listrik == true) ? "Ada" : "Tidak Ada");
        cbAccu2_listrikx.setChecked(var_cbAccu2_listrik);
        cbAccu2_listrikx.setVisibility((var_cbAccu1_listrik == true) ? View.VISIBLE : View.INVISIBLE);
        cbAccu2_listrikx.setText((var_cbAccu2_listrik == true) ? "Baik" : "Rusak");

        cbMesin1_lainx.setChecked(var_cbMesin1_lain);
        cbMesin1_lainx.setText((var_cbMesin1_lain == true) ? "Ada" : "Tidak Ada");
        cbMesin2_lainx.setChecked(var_cbMesin2_lain);
        cbMesin2_lainx.setVisibility((var_cbMesin1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbMesin2_lainx.setText((var_cbMesin2_lain == true) ? "Baik" : "Rusak");

        cbHidraulik1_lainx.setChecked(var_cbHidraulik1_lain);
        cbHidraulik1_lainx.setText((var_cbHidraulik1_lain == true) ? "Ada" : "Tidak Ada");
        cbHidraulik2_lainx.setChecked(var_cbHidraulik2_lain);
        cbHidraulik2_lainx.setVisibility((var_cbHidraulik1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbHidraulik2_lainx.setText((var_cbHidraulik2_lain == true) ? "Baik" : "Rusak");

        cbGardan1_lainx.setChecked(var_cbGardan1_lain);
        cbGardan1_lainx.setText((var_cbGardan1_lain == true) ? "Ada" : "Tidak Ada");
        cbGardan2_lainx.setChecked(var_cbGardan2_lain);
        cbGardan2_lainx.setVisibility((var_cbGardan1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbGardan2_lainx.setText((var_cbGardan2_lain == true) ? "Baik" : "Rusak");

        cbAs1_lainx.setChecked(var_cbAs1_lain);
        cbAs1_lainx.setText((var_cbAs1_lain == true) ? "Ada" : "Tidak Ada");
        cbAs2_lainx.setChecked(var_cbAs2_lain);
        cbAs2_lainx.setVisibility((var_cbAs1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbAs2_lainx.setText((var_cbAs2_lain == true) ? "Baik" : "Rusak");

        cbBak1_lainx.setChecked(var_cbBak1_lain);
        cbBak1_lainx.setText((var_cbBak1_lain == true) ? "Ada" : "Tidak Ada");
        cbBak2_lainx.setChecked(var_cbBak2_lain);
        cbBak2_lainx.setVisibility((var_cbBak1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbBak2_lainx.setText((var_cbBak2_lain == true) ? "Baik" : "Rusak");

        etSd_stnkx.setText(stnk[2] + "-" + stnk[1] + "-" + stnk[0]);
        cbStnk1_lainx.setChecked(var_cbStnk1_lain);
        cbStnk1_lainx.setText((var_cbStnk1_lain == true) ? "Ada" : "Tidak Ada");
        cbStnk2_lainx.setChecked(var_cbStnk2_lain);
        cbStnk2_lainx.setVisibility((var_cbStnk1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbStnk2_lainx.setText((var_cbStnk2_lain == true) ? "Baik" : "Rusak");

        etSd_bukukirx.setText(bukukir[2] + "-" + bukukir[1] + "-" + bukukir[0]);
        cbBukukir1_lainx.setChecked(var_cbBukukir1_lain);
        cbBukukir1_lainx.setText((var_cbBukukir1_lain == true) ? "Ada" : "Tidak Ada");
        cbBukukir2_lainx.setChecked(var_cbBukukir2_lain);
        cbBukukir2_lainx.setVisibility((var_cbBukukir1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbBukukir2_lainx.setText((var_cbBukukir2_lain == true) ? "Baik" : "Rusak");

        etSd_ijintrayekx.setText(trayek[2] + "-" + trayek[1] + "-" + trayek[0]);
        cbTrayek1_lainx.setChecked(var_cbTrayek1_lain);
        cbTrayek1_lainx.setText((var_cbTrayek1_lain == true) ? "Ada" : "Tidak Ada");
        cbTrayek2_lainx.setChecked(var_cbTrayek2_lain);
        cbTrayek2_lainx.setVisibility((var_cbTrayek1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbTrayek2_lainx.setText((var_cbTrayek2_lain == true) ? "Baik" : "Rusak");

        etSd_ijinusahax.setText(usaha[2] + "-" + usaha[1] + "-" + usaha[0]);
        cbUsaha1_lainx.setChecked(var_cbUsaha1_lain);
        cbUsaha1_lainx.setText((var_cbUsaha1_lain == true) ? "Ada" : "Tidak Ada");
        cbUsaha2_lainx.setChecked(var_cbUsaha2_lain);
        cbUsaha2_lainx.setVisibility((var_cbUsaha1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbUsaha2_lainx.setText((var_cbUsaha2_lain == true) ? "Baik" : "Rusak");

        cbBukumnl1_lainx.setChecked(var_cbBukumnl1_lain);
        cbBukumnl1_lainx.setText((var_cbBukumnl1_lain == true) ? "Ada" : "Tidak Ada");
        cbBukumnl2_lainx.setChecked(var_cbBukumnl2_lain);
        cbBukumnl2_lainx.setVisibility((var_cbBukumnl1_lain == true) ? View.VISIBLE : View.INVISIBLE);
        cbBukumnl2_lainx.setText((var_cbBukumnl2_lain == true) ? "Baik" : "Rusak");

        svChild.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                svParent.scrollTo(svChild.getScrollX(),svChild.getScrollY());
            }
        });
//        svParent.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//            }
//        });

        svParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, kondisi);
        spBan2_fisikkirix.setAdapter(adapter);

        spBan2_fisikkirix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spBan2_fisikkirix.getSelectedItem().equals("Botak")){
                    var_spBan2_fisikkiri = 0;
                }else if (spBan2_fisikkirix.getSelectedItem().equals("Sedang")){
                    var_spBan2_fisikkiri = 1;
                }else if (spBan2_fisikkirix.getSelectedItem().equals("Baik")){
                    var_spBan2_fisikkiri = 2;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spBan2_fisikkananx.setAdapter(adapter);
        spBan2_fisikkananx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spBan2_fisikkananx.getSelectedItem().equals("Botak")){
                    var_spBan2_fisikkanan = 0;
                }else if (spBan2_fisikkananx.getSelectedItem().equals("Sedang")){
                    var_spBan2_fisikkanan = 1;
                }else if (spBan2_fisikkananx.getSelectedItem().equals("Baik")){
                    var_spBan2_fisikkanan = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });


        cbGrill1_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbGrill1_fisikmukax.isChecked()){
                    cbGrill1_fisikmukax.setText("Ada");
                    cbGrill2_fisikmukax.setVisibility(View.VISIBLE);
                    cbGrill2_fisikmukax.setText("Rusak");
                    cbGrill2_fisikmukax.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbGrill1_fisikmuka(true);
//                    upd.first().setTdb_var_cbGrill1_fisikmuka("Ada");
//                    upd.first().setVdb_var_cbGrill2_fisikmuka(View.VISIBLE);
//                    upd.first().setTdb_var_cbGrill2_fisikmuka("Rusak");
//                    realm.commitTransaction();

                }else {
                    cbGrill2_fisikmukax.setVisibility(View.INVISIBLE);
                    cbGrill1_fisikmukax.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbGrill1_fisikmuka(false);
//                    upd.first().setTdb_var_cbGrill1_fisikmuka("Tidak Ada");
//                    upd.first().setDb_var_cbGrill2_fisikmuka(false);
//                    upd.first().setTdb_var_cbGrill2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbGrill2_fisikmuka(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbGrill2_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbGrill2_fisikmukax.isChecked()){
                    cbGrill2_fisikmukax.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbGrill2_fisikmuka(true);
//                    upd.first().setTdb_var_cbGrill2_fisikmuka("Baik");
//                    upd.first().setVdb_var_cbGrill2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbGrill2_fisikmukax.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbGrill2_fisikmuka(false);
//                    upd.first().setTdb_var_cbGrill2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbGrill2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbLampu1_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampu1_fisikmukax.isChecked()){
                    cbLampu1_fisikmukax.setText("Ada");
                    cbLampu2_fisikmukax.setVisibility(View.VISIBLE);
                    cbLampu2_fisikmukax.setText("Rusak");
                    cbLampu2_fisikmukax.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu1_fisikmuka(true);
//                    upd.first().setTdb_var_cbLampu1_fisikmuka("Ada");
////                    upd.first().setDb_var_cbLampu2_fisikmuka(false);
//                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.VISIBLE);
//                    upd.first().setTdb_var_cbLampu2_fisikmuka("Rusak");
//                    realm.commitTransaction();

                }else {
                    cbLampu2_fisikmukax.setVisibility(View.INVISIBLE);
                    cbLampu1_fisikmukax.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu1_fisikmuka(false);
//                    upd.first().setTdb_var_cbLampu1_fisikmuka("Tidak Ada");
////                    upd.first().setDb_var_cbLampu2_fisikmuka(false);
//                    upd.first().setTdb_var_cbLampu2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbLampu2_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampu2_fisikmukax.isChecked()){
                    cbLampu2_fisikmukax.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu2_fisikmuka(true);
//                    upd.first().setTdb_var_cbLampu2_fisikmuka("Baik");
//                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbLampu2_fisikmukax.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu2_fisikmuka(false);
//                    upd.first().setTdb_var_cbLampu2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbLampusen1_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampusen1_fisikmukax.isChecked()){
                    cbLampusen1_fisikmukax.setText("Ada");
                    cbLampusen2_fisikmukax.setVisibility(View.VISIBLE);
                    cbLampusen2_fisikmukax.setText("Rusak");
                    cbLampusen2_fisikmukax.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampusen1_fisikmuka(true);
//                    upd.first().setTdb_var_cbLampusen1_fisikmuka("Ada");
//                    upd.first().setDb_var_cbLampusen2_fisikmuka(false);
//                    upd.first().setTdb_var_cbLampusen2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbLampusen2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbLampusen2_fisikmukax.setVisibility(View.INVISIBLE);
                    cbLampusen1_fisikmukax.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampusen1_fisikmuka(false);
//                    upd.first().setTdb_var_cbLampusen1_fisikmuka("Tidak Ada");
//                    upd.first().setVdb_var_cbLampusen2_fisikmuka(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbLampusen2_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampusen2_fisikmukax.isChecked()){
                    cbLampusen2_fisikmukax.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampusen2_fisikmuka(true);
//                    upd.first().setTdb_var_cbLampusen2_fisikmuka("Baik");
//                    upd.first().setVdb_var_cbLampusen2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbLampusen2_fisikmukax.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampusen2_fisikmuka(false);
//                    upd.first().setTdb_var_cbLampusen2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbLampusen2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbBamper1_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper1_fisikmukax.isChecked()){
                    cbBamper1_fisikmukax.setText("Ada");
                    cbBamper2_fisikmukax.setVisibility(View.VISIBLE);
                    cbBamper2_fisikmukax.setText("Rusak");
                    cbBamper2_fisikmukax.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikmuka(true);
//                    upd.first().setTdb_var_cbBamper1_fisikmuka("Ada");
//                    upd.first().setDb_var_cbBamper2_fisikmuka(false);
//                    upd.first().setTdb_var_cbBamper2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikmukax.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikmukax.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikmuka(false);
//                    upd.first().setTdb_var_cbBamper1_fisikmuka("Tidak Ada");
//                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbBamper2_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper2_fisikmukax.isChecked()){
                    cbBamper2_fisikmukax.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikmuka(true);
//                    upd.first().setTdb_var_cbBamper2_fisikmuka("Baik");
//                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikmukax.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikmuka(false);
//                    upd.first().setTdb_var_cbBamper2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbEmblem1_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem1_fisikmukax.isChecked()){
                    cbEmblem1_fisikmukax.setText("Ada");
                    cbEmblem2_fisikmukax.setVisibility(View.VISIBLE);
                    cbEmblem2_fisikmukax.setText("Rusak");
                    cbEmblem2_fisikmukax.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem1_fisikmuka(true);
//                    upd.first().setTdb_var_cbEmblem1_fisikmuka("Ada");
//                    upd.first().setDb_var_cbEmblem2_fisikmuka(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikmukax.setVisibility(View.INVISIBLE);
                    cbEmblem1_fisikmukax.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem1_fisikmuka(false);
//                    upd.first().setTdb_var_cbEmblem1_fisikmuka("Tidak Ada");
//                    upd.first().setVdb_var_cbEmblem2_fisikmuka(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbEmblem2_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem2_fisikmukax.isChecked()){
                    cbEmblem2_fisikmukax.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem2_fisikmuka(true);
//                    upd.first().setTdb_var_cbEmblem2_fisikmuka("Baik");
//                    upd.first().setVdb_var_cbEmblem2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikmukax.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem2_fisikmuka(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbTanduk1_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTanduk1_fisikmukax.isChecked()){
                    cbTanduk1_fisikmukax.setText("Ada");
                    cbTanduk2_fisikmukax.setVisibility(View.VISIBLE);
                    cbTanduk2_fisikmukax.setText("Rusak");
                    cbTanduk2_fisikmukax.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTanduk1_fisikmuka(true);
//                    upd.first().setTdb_var_cbTanduk1_fisikmuka("Ada");
//                    upd.first().setDb_var_cbTanduk2_fisikmuka(false);
//                    upd.first().setTdb_var_cbTanduk2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbTanduk2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbTanduk2_fisikmukax.setVisibility(View.INVISIBLE);
                    cbTanduk1_fisikmukax.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTanduk1_fisikmuka(false);
//                    upd.first().setTdb_var_cbTanduk1_fisikmuka("Tidak Ada");
//                    upd.first().setVdb_var_cbTanduk2_fisikmuka(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbTanduk2_fisikmukax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTanduk2_fisikmukax.isChecked()){
                    cbTanduk2_fisikmukax.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTanduk2_fisikmuka(true);
//                    upd.first().setTdb_var_cbTanduk2_fisikmuka("Baik");
//                    upd.first().setVdb_var_cbTanduk2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbTanduk2_fisikmukax.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTanduk2_fisikmuka(false);
//                    upd.first().setTdb_var_cbTanduk2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbTanduk2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
//=========================================================================================

        cbFootstep1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFootstep1_fisikkirix.isChecked()){
                    cbFootstep1_fisikkirix.setText("Ada");
                    cbFootstep2_fisikkirix.setVisibility(View.VISIBLE);
                    cbFootstep2_fisikkirix.setText("Rusak");
                    cbFootstep2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFootstep1_fisikkiri(true);
//                    upd.first().setTdb_var_cbFootstep1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbFootstep2_fisikkiri(false);
//                    upd.first().setTdb_var_cbFootstep2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbFootstep2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbFootstep1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFootstep1_fisikkiri(false);
//                    upd.first().setTdb_var_cbFootstep1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbFootstep2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFootstep2_fisikkirix.isChecked()){
                    cbFootstep2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFootstep2_fisikkiri(true);
//                    upd.first().setTdb_var_cbFootstep2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbFootstep2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFootstep2_fisikkiri(false);
//                    upd.first().setTdb_var_cbFootstep2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbPintudpn1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintudpn1_fisikkirix.isChecked()){
                    cbPintudpn1_fisikkirix.setText("Ada");
                    cbPintudpn2_fisikkirix.setVisibility(View.VISIBLE);
                    cbPintudpn2_fisikkirix.setText("Rusak");
                    cbPintudpn2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintudpn1_fisikkiri(true);
//                    upd.first().setTdb_var_cbPintudpn1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbPintudpn2_fisikkiri(false);
//                    upd.first().setTdb_var_cbPintudpn2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbPintudpn2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbFootstep2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbFootstep1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFootstep1_fisikkiri(false);
//                    upd.first().setTdb_var_cbFootstep1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbFootstep2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPintudpn2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintudpn2_fisikkirix.isChecked()){
                    cbPintudpn2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintudpn2_fisikkiri(true);
//                    upd.first().setTdb_var_cbPintudpn2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbPintudpn2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPintudpn2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintudpn2_fisikkiri(false);
//                    upd.first().setTdb_var_cbPintudpn2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbPintudpn2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbPintublk1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintublk1_fisikkirix.isChecked()){
                    cbPintublk1_fisikkirix.setText("Ada");
                    cbPintublk2_fisikkirix.setVisibility(View.VISIBLE);
                    cbPintublk2_fisikkirix.setText("Rusak");
                    cbPintublk2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintublk1_fisikkiri(true);
//                    upd.first().setTdb_var_cbPintublk1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbPintublk2_fisikkiri(false);
//                    upd.first().setTdb_var_cbPintublk2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbPintublk2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPintublk2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbPintublk1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintublk1_fisikkiri(false);
//                    upd.first().setTdb_var_cbPintublk1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbPintublk2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPintublk2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintublk2_fisikkirix.isChecked()){
                    cbPintublk2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintublk2_fisikkiri(true);
//                    upd.first().setTdb_var_cbPintublk2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbPintublk2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPintublk2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintublk2_fisikkiri(false);
//                    upd.first().setTdb_var_cbPintublk2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbPintublk2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbBamper1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper1_fisikkirix.isChecked()){
                    cbBamper1_fisikkirix.setText("Ada");
                    cbBamper2_fisikkirix.setVisibility(View.VISIBLE);
                    cbBamper2_fisikkirix.setText("Rusak");
                    cbBamper2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikkiri(true);
//                    upd.first().setTdb_var_cbBamper1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbBamper2_fisikkiri(false);
//                    upd.first().setTdb_var_cbBamper2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBamper2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikkiri(false);
//                    upd.first().setTdb_var_cbBamper1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbBamper2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper2_fisikkirix.isChecked()){
                    cbBamper2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikkiri(true);
//                    upd.first().setTdb_var_cbBamper2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBamper2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikkiri(false);
//                    upd.first().setTdb_var_cbBamper2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbFenderblk1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFenderblk1_fisikkirix.isChecked()){
                    cbFenderblk1_fisikkirix.setText("Ada");
                    cbFenderblk2_fisikkirix.setVisibility(View.VISIBLE);
                    cbFenderblk2_fisikkirix.setText("Rusak");
                    cbFenderblk2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFenderblk1_fisikkiri(true);
//                    upd.first().setTdb_var_cbFenderblk1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbFenderblk2_fisikkiri(false);
//                    upd.first().setTdb_var_cbFenderblk2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbFenderblk2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBamper2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikkiri(false);
//                    upd.first().setTdb_var_cbBamper1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbFenderblk2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFenderblk2_fisikkirix.isChecked()){
                    cbFenderblk2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFenderblk2_fisikkiri(true);
//                    upd.first().setTdb_var_cbFenderblk2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbFenderblk2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbFenderblk2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFenderblk2_fisikkiri(false);
//                    upd.first().setTdb_var_cbFenderblk2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbFenderblk2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbSpion1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion1_fisikkirix.isChecked()){
                    cbSpion1_fisikkirix.setText("Ada");
                    cbSpion2_fisikkirix.setVisibility(View.VISIBLE);
                    cbSpion2_fisikkirix.setText("Rusak");
                    cbSpion2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion1_fisikkiri(true);
//                    upd.first().setTdb_var_cbSpion1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbSpion2_fisikkiri(false);
//                    upd.first().setTdb_var_cbSpion2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbSpion2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikkiri(false);
//                    upd.first().setTdb_var_cbBamper1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbBamper2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbSpion2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion2_fisikkirix.isChecked()){
                    cbSpion2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion2_fisikkiri(true);
//                    upd.first().setTdb_var_cbSpion2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbSpion2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbSpion2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion2_fisikkiri(false);
//                    upd.first().setTdb_var_cbSpion2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbSpion2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbEmblem1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem1_fisikkirix.isChecked()){
                    cbEmblem1_fisikkirix.setText("Ada");
                    cbEmblem2_fisikkirix.setVisibility(View.VISIBLE);
                    cbEmblem2_fisikkirix.setText("Rusak");
                    cbEmblem2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem1_fisikkiri(true);
//                    upd.first().setTdb_var_cbEmblem1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbEmblem2_fisikkiri(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbEmblem1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem1_fisikkiri(false);
//                    upd.first().setTdb_var_cbEmblem1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbEmblem2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem2_fisikkirix.isChecked()){
                    cbEmblem2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem2_fisikkiri(true);
//                    upd.first().setTdb_var_cbEmblem2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem2_fisikkiri(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbBan1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBan1_fisikkirix.isChecked()){
                    cbBan1_fisikkirix.setText("Ada");
                    spBan2_fisikkirix.setVisibility(View.VISIBLE);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBan1_fisikkiri(true);
//                    upd.first().setTdb_var_cbBan1_fisikkiri("Ada");
//                    upd.first().setDb_var_spBan2_fisikkiri(spBan2_fisikkiri.getSelectedItemPosition());
//                    upd.first().setVdb_var_spBan2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBan1_fisikkirix.setText("Tidak Ada");
                    spBan2_fisikkirix.setVisibility(View.INVISIBLE);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBan1_fisikkiri(false);
//                    upd.first().setTdb_var_cbBan1_fisikkiri("Tidak Ada");
//                    upd.first().setDb_var_spBan2_fisikkiri(0);
//                    upd.first().setVdb_var_spBan2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        rbBanstandard1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbBanstandard1_fisikkirix.isChecked()){
                    rbBanradial1_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbBanstandard_fisikkiri(true);
//                    upd.first().setDb_var_rbBanradial_fisikkiri(false);
//                    realm.commitTransaction();
                }
            }
        });
        rbBanradial1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbBanradial1_fisikkirix.isChecked()){
                    rbBanstandard1_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbBanradial_fisikkiri(true);
//                    upd.first().setDb_var_rbBanstandard_fisikkiri(false);
//                    realm.commitTransaction();
                }
            }
        });

        rbVelgstandard_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVelgstandard_fisikkirix.isChecked()){
                    rbVelgracing_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbVelgstandard_fisikkiri(true);
//                    upd.first().setDb_var_rbVelgracing_fisikkiri(false);
//                    realm.commitTransaction();
                }
            }
        });
        rbVelgracing_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVelgracing_fisikkirix.isChecked()){
                    rbVelgstandard_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbVelgracing_fisikkiri(true);
//                    upd.first().setDb_var_rbVelgstandard_fisikkiri(false);
//                    realm.commitTransaction();
                }
            }
        });

        cbVelg1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVelg1_fisikkirix.isChecked()){
                    cbVelg1_fisikkirix.setText("Ada");
                    cbVelg2_fisikkirix.setVisibility(View.VISIBLE);
                    cbVelg2_fisikkirix.setText("Rusak");
                    cbVelg2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbVelg1_fisikkiri(true);
//                    upd.first().setTdb_var_cbVelg1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbVelg2_fisikkiri(false);
//                    upd.first().setTdb_var_cbVelg2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbVelg2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();
                }else {
                    cbVelg2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbVelg1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbVelg1_fisikkiri(false);
//                    upd.first().setTdb_var_cbVelg1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbVelg2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbVelg2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVelg2_fisikkirix.isChecked()){
                    cbVelg2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbVelg2_fisikkiri(true);
//                    upd.first().setTdb_var_cbVelg2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbVelg2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbVelg2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbVelg2_fisikkiri(false);
//                    upd.first().setTdb_var_cbVelg2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbVelg2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbDop1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDop1_fisikkirix.isChecked()){
                    cbDop1_fisikkirix.setText("Ada");
                    cbDop2_fisikkirix.setVisibility(View.VISIBLE);
                    cbDop2_fisikkirix.setText("Rusak");
                    cbDop2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDop1_fisikkiri(true);
//                    upd.first().setTdb_var_cbDop1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbDop2_fisikkiri(false);
//                    upd.first().setTdb_var_cbDop2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbDop2_fisikkiri(View.VISIBLE);
//
//                    realm.commitTransaction();


                }else {
                    cbDop2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbDop1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDop1_fisikkiri(false);
//                    upd.first().setTdb_var_cbDop1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbDop2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbDop2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDop2_fisikkirix.isChecked()){
                    cbDop2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDop2_fisikkiri(true);
//                    upd.first().setTdb_var_cbDop2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbDop2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbDop2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDop2_fisikkiri(false);
//                    upd.first().setTdb_var_cbDop2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbDop2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbDopBlk1_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDopBlk1_fisikkirix.isChecked()){
                    cbDopBlk1_fisikkirix.setText("Ada");
                    cbDopBlk2_fisikkirix.setVisibility(View.VISIBLE);
                    cbDopBlk2_fisikkirix.setText("Rusak");
                    cbDopBlk2_fisikkirix.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDopBlk1_fisikkiri(true);
//                    upd.first().setTdb_var_cbDopBlk1_fisikkiri("Ada");
//                    upd.first().setDb_var_cbDopBlk2_fisikkiri(false);
//                    upd.first().setTdb_var_cbDopBlk2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbDopBlk2_fisikkiri(View.VISIBLE);

//                    realm.commitTransaction();


                }else {
                    cbDopBlk2_fisikkirix.setVisibility(View.INVISIBLE);
                    cbDopBlk1_fisikkirix.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDopBlk1_fisikkiri(false);
//                    upd.first().setTdb_var_cbDopBlk1_fisikkiri("Tidak Ada");
//                    upd.first().setVdb_var_cbDopBlk2_fisikkiri(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbDopBlk2_fisikkirix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDopBlk2_fisikkirix.isChecked()){
                    cbDopBlk2_fisikkirix.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDopBlk2_fisikkiri(true);
//                    upd.first().setTdb_var_cbDopBlk2_fisikkiri("Baik");
//                    upd.first().setVdb_var_cbDopBlk2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbDopBlk2_fisikkirix.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDopBlk2_fisikkiri(false);
//                    upd.first().setTdb_var_cbDopBlk2_fisikkiri("Rusak");
//                    upd.first().setVdb_var_cbDopBlk2_fisikkiri(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
//=====================================================================================

        cbSpoiler1_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpoiler1_fisikblkgx.isChecked()){
                    cbSpoiler1_fisikblkgx.setText("Ada");
                    cbSpoiler2_fisikblkgx.setVisibility(View.VISIBLE);
                    cbSpoiler2_fisikblkgx.setText("Rusak");
                    cbSpoiler2_fisikblkgx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpoiler1_fisikblkg(true);
//                    upd.first().setTdb_var_cbSpoiler1_fisikblkg("Ada");
//                    upd.first().setDb_var_cbSpoiler2_fisikblkg(false);
//                    upd.first().setTdb_var_cbSpoiler2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbSpoiler2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSpoiler2_fisikblkgx.setVisibility(View.INVISIBLE);
                    cbSpoiler1_fisikblkgx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpoiler1_fisikblkg(false);
//                    upd.first().setTdb_var_cbSpoiler1_fisikblkg("Tidak Ada");
//                    upd.first().setVdb_var_cbSpoiler2_fisikblkg(View.INVISIBLE);
//                    realm.commitTransaction();

                }

            }
        });
        cbSpoiler2_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpoiler2_fisikblkgx.isChecked()){
                    cbSpoiler2_fisikblkgx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpoiler2_fisikblkg(true);
//                    upd.first().setTdb_var_cbSpoiler2_fisikblkg("Baik");
//                    upd.first().setVdb_var_cbSpoiler2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSpoiler2_fisikblkgx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpoiler2_fisikblkg(false);
//                    upd.first().setTdb_var_cbSpoiler2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbSpoiler2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbLampu1_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampu1_fisikblkgx.isChecked()){
                    cbLampu1_fisikblkgx.setText("Ada");
                    cbLampu2_fisikblkgx.setVisibility(View.VISIBLE);
                    cbLampu2_fisikblkgx.setText("Rusak");
                    cbLampu2_fisikblkgx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu1_fisikblkg(true);
//                    upd.first().setTdb_var_cbLampu1_fisikblkg("Ada");
//                    upd.first().setDb_var_cbLampu2_fisikblkg(false);
//                    upd.first().setTdb_var_cbLampu2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbLampu2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbLampu2_fisikblkgx.setVisibility(View.INVISIBLE);
                    cbLampu1_fisikblkgx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu1_fisikblkg(false);
//                    upd.first().setTdb_var_cbLampu1_fisikblkg("Tidak Ada");
//                    upd.first().setVdb_var_cbLampu2_fisikblkg(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbLampu2_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampu2_fisikblkgx.isChecked()){
                    cbLampu2_fisikblkgx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu2_fisikblkg(true);
//                    upd.first().setTdb_var_cbLampu2_fisikblkg("Baik");
//                    upd.first().setVdb_var_cbLampu2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbLampu2_fisikblkgx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu2_fisikblkg(false);
//                    upd.first().setTdb_var_cbLampu2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbLampu2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbLampusen1_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampusen1_fisikblkgx.isChecked()){
                    cbLampusen1_fisikblkgx.setText("Ada");
                    cbLampusen2_fisikblkgx.setVisibility(View.VISIBLE);
                    cbLampusen2_fisikblkgx.setText("Rusak");
                    cbLampusen2_fisikblkgx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampusen1_fisikblkg(true);
//                    upd.first().setTdb_var_cbLampusen1_fisikblkg("Ada");
//                    upd.first().setDb_var_cbLampusen2_fisikblkg(false);
//                    upd.first().setTdb_var_cbLampusen2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbLampusen2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbLampusen2_fisikblkgx.setVisibility(View.INVISIBLE);
                    cbLampusen1_fisikblkgx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampusen1_fisikblkg(false);
//                    upd.first().setTdb_var_cbLampusen1_fisikblkg("Tidak Ada");
//                    upd.first().setVdb_var_cbLampusen2_fisikblkg(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbLampusen2_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampusen2_fisikblkgx.isChecked()){
                    cbLampusen2_fisikblkgx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampusen2_fisikblkg(true);
//                    upd.first().setTdb_var_cbLampusen2_fisikblkg("Baik");
//                    upd.first().setVdb_var_cbLampusen2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbLampusen2_fisikblkgx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampusen2_fisikblkg(false);
//                    upd.first().setTdb_var_cbLampusen2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbLampusen2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbBamper1_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper1_fisikblkgx.isChecked()){
                    cbBamper1_fisikblkgx.setText("Ada");
                    cbBamper2_fisikblkgx.setVisibility(View.VISIBLE);
                    cbBamper2_fisikblkgx.setText("Rusak");
                    cbBamper2_fisikblkgx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikblkg(true);
//                    upd.first().setTdb_var_cbBamper1_fisikblkg("Ada");
//                    upd.first().setDb_var_cbBamper2_fisikblkg(false);
//                    upd.first().setTdb_var_cbBamper2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbBamper2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikblkgx.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikblkgx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikblkg(false);
//                    upd.first().setTdb_var_cbBamper1_fisikblkg("Tidak Ada");
//                    upd.first().setVdb_var_cbBamper2_fisikblkg(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbBamper2_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper2_fisikblkgx.isChecked()){
                    cbBamper2_fisikblkgx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikblkg(true);
//                    upd.first().setTdb_var_cbBamper2_fisikblkg("Baik");
//                    upd.first().setVdb_var_cbBamper2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBamper2_fisikblkgx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikblkg(false);
//                    upd.first().setTdb_var_cbBamper2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbBamper2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbEmblem1_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem1_fisikblkgx.isChecked()){
                    cbEmblem1_fisikblkgx.setText("Ada");
                    cbEmblem2_fisikblkgx.setVisibility(View.VISIBLE);
                    cbEmblem2_fisikblkgx.setText("Rusak");
                    cbEmblem2_fisikblkgx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem1_fisikblkg(true);
//                    upd.first().setTdb_var_cbEmblem1_fisikblkg("Ada");
//                    upd.first().setDb_var_cbEmblem2_fisikblkg(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikblkgx.setVisibility(View.INVISIBLE);
                    cbEmblem1_fisikblkgx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem1_fisikblkg(false);
//                    upd.first().setTdb_var_cbEmblem1_fisikblkg("Tidak Ada");
//                    upd.first().setVdb_var_cbEmblem2_fisikblkg(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbEmblem2_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem2_fisikblkgx.isChecked()){
                    cbEmblem2_fisikblkgx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem2_fisikblkg(true);
//                    upd.first().setTdb_var_cbEmblem2_fisikblkg("Baik");
//                    upd.first().setVdb_var_cbEmblem2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbEmblem2_fisikblkgx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem2_fisikblkg(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbKnalpot1_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKnalpot1_fisikblkgx.isChecked()){
                    cbKnalpot1_fisikblkgx.setText("Ada");
                    cbKnalpot2_fisikblkgx.setVisibility(View.VISIBLE);
                    cbKnalpot2_fisikblkgx.setText("Rusak");
                    cbKnalpot2_fisikblkgx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKnalpot1_fisikblkg(true);
//                    upd.first().setTdb_var_cbKnalpot1_fisikblkg("Ada");
//                    upd.first().setDb_var_cbKnalpot2_fisikblkg(false);
//                    upd.first().setTdb_var_cbKnalpot2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbKnalpot2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbKnalpot2_fisikblkgx.setVisibility(View.INVISIBLE);
                    cbKnalpot1_fisikblkgx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKnalpot1_fisikblkg(false);
//                    upd.first().setTdb_var_cbKnalpot1_fisikblkg("Tidak Ada");
//                    upd.first().setVdb_var_cbKnalpot2_fisikblkg(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbKnalpot2_fisikblkgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKnalpot2_fisikblkgx.isChecked()){
                    cbKnalpot2_fisikblkgx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKnalpot2_fisikblkg(true);
//                    upd.first().setTdb_var_cbKnalpot2_fisikblkg("Baik");
//                    upd.first().setVdb_var_cbKnalpot2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbKnalpot2_fisikblkgx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKnalpot2_fisikblkg(false);
//                    upd.first().setTdb_var_cbKnalpot2_fisikblkg("Rusak");
//                    upd.first().setVdb_var_cbKnalpot2_fisikblkg(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        //=======================================================================

        cbFoot1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFoot1_fisikkananx.isChecked()){
                    cbFoot1_fisikkananx.setText("Ada");
                    cbFoot2_fisikkananx.setVisibility(View.VISIBLE);
                    cbFoot2_fisikkananx.setText("Rusak");
                    cbFoot2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFoot1_fisikkanan(true);
//                    upd.first().setTdb_var_cbFoot1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbFoot2_fisikkanan(false);
//                    upd.first().setTdb_var_cbFoot2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbFoot2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbFoot2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbFoot1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFoot1_fisikkanan(false);
//                    upd.first().setTdb_var_cbFoot1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbFoot2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }

            }
        });
        cbFoot2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFoot2_fisikkananx.isChecked()){
                    cbFoot2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFoot2_fisikkanan(true);
//                    upd.first().setTdb_var_cbFoot2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbFoot2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbFoot2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFoot2_fisikkanan(false);
//                    upd.first().setTdb_var_cbFoot2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbFoot2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPintudpn1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintudpn1_fisikkananx.isChecked()){
                    cbPintudpn1_fisikkananx.setText("Ada");
                    cbPintudpn2_fisikkananx.setVisibility(View.VISIBLE);
                    cbPintudpn2_fisikkananx.setText("Rusak");
                    cbPintudpn2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintudpn1_fisikkanan(true);
//                    upd.first().setTdb_var_cbPintudpn1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbPintudpn2_fisikkanan(false);
//                    upd.first().setTdb_var_cbPintudpn2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbPintudpn2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPintudpn2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbPintudpn1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintudpn1_fisikkanan(false);
//                    upd.first().setTdb_var_cbPintudpn1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbPintudpn2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPintudpn2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintudpn2_fisikkananx.isChecked()){
                    cbPintudpn2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintudpn2_fisikkanan(true);
//                    upd.first().setTdb_var_cbPintudpn2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbPintudpn2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbPintudpn2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintudpn2_fisikkanan(false);
//                    upd.first().setTdb_var_cbPintudpn2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbPintudpn2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPintublk1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintublk1_fisikkananx.isChecked()){
                    cbPintublk1_fisikkananx.setText("Ada");
                    cbPintublk2_fisikkananx.setVisibility(View.VISIBLE);
                    cbPintublk2_fisikkananx.setText("Rusak");
                    cbPintublk2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintublk1_fisikkanan(true);
//                    upd.first().setTdb_var_cbPintublk1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbPintublk2_fisikkanan(false);
//                    upd.first().setTdb_var_cbPintublk2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbPintublk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPintublk2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbPintublk1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintublk1_fisikkanan(false);
//                    upd.first().setTdb_var_cbPintublk1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbPintublk2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPintublk2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPintublk2_fisikkananx.isChecked()){
                    cbPintublk2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintublk2_fisikkanan(true);
//                    upd.first().setTdb_var_cbPintublk2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbPintublk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPintublk2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPintublk2_fisikkanan(false);
//                    upd.first().setTdb_var_cbPintublk2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbPintublk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbBamper1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper1_fisikkananx.isChecked()){
                    cbBamper1_fisikkananx.setText("Ada");
                    cbBamper2_fisikkananx.setVisibility(View.VISIBLE);
                    cbBamper2_fisikkananx.setText("Rusak");
                    cbBamper2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikkanan(true);
//                    upd.first().setTdb_var_cbBamper1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbBamper2_fisikkanan(false);
//                    upd.first().setTdb_var_cbBamper2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbBamper2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbBamper1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper1_fisikkanan(false);
//                    upd.first().setTdb_var_cbBamper1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbBamper2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbBamper2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBamper2_fisikkananx.isChecked()){
                    cbBamper2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikkanan(true);
//                    upd.first().setTdb_var_cbBamper2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbBamper2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBamper2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikkanan(false);
//                    upd.first().setTdb_var_cbBamper2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbBamper2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbFenderblk1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFenderblk1_fisikkananx.isChecked()){
                    cbFenderblk1_fisikkananx.setText("Ada");
                    cbFenderblk2_fisikkananx.setVisibility(View.VISIBLE);
                    cbFenderblk2_fisikkananx.setText("Rusak");
                    cbFenderblk2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFenderblk1_fisikkanan(true);
//                    upd.first().setTdb_var_cbFenderblk1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbFenderblk2_fisikkanan(false);
//                    upd.first().setTdb_var_cbFenderblk2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbFenderblk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbFenderblk2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbFenderblk1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFenderblk1_fisikkanan(false);
//                    upd.first().setTdb_var_cbFenderblk1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbFenderblk2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbFenderblk2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFenderblk2_fisikkananx.isChecked()){
                    cbFenderblk2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFenderblk2_fisikkanan(true);
//                    upd.first().setTdb_var_cbFenderblk2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbFenderblk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbFenderblk2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbFenderblk2_fisikkanan(false);
//                    upd.first().setTdb_var_cbFenderblk2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbFenderblk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSpion1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion1_fisikkananx.isChecked()){
                    cbSpion1_fisikkananx.setText("Ada");
                    cbSpion2_fisikkananx.setVisibility(View.VISIBLE);
                    cbSpion2_fisikkananx.setText("Rusak");
                    cbSpion2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion1_fisikkanan(true);
//                    upd.first().setTdb_var_cbSpion1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbSpion2_fisikkanan(false);
//                    upd.first().setTdb_var_cbSpion2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbSpion2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbSpion2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbSpion1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion1_fisikkanan(false);
//                    upd.first().setTdb_var_cbSpion1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbSpion2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSpion2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion2_fisikkananx.isChecked()){
                    cbSpion2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion2_fisikkanan(true);
//                    upd.first().setTdb_var_cbSpion2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbSpion2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSpion2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion2_fisikkanan(false);
//                    upd.first().setTdb_var_cbSpion2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbSpion2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbEmblem1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem1_fisikkananx.isChecked()){
                    cbEmblem1_fisikkananx.setText("Ada");
                    cbEmblem2_fisikkananx.setVisibility(View.VISIBLE);
                    cbEmblem2_fisikkananx.setText("Rusak");
                    cbEmblem2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem1_fisikkanan(true);
//                    upd.first().setTdb_var_cbEmblem1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbEmblem2_fisikkanan(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbEmblem1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem1_fisikkanan(false);
//                    upd.first().setTdb_var_cbEmblem1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbEmblem2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbEmblem2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmblem2_fisikkananx.isChecked()){
                    cbEmblem2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem2_fisikkanan(true);
//                    upd.first().setTdb_var_cbEmblem2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbEmblem2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbEmblem2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbEmblem2_fisikkanan(false);
//                    upd.first().setTdb_var_cbEmblem2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbEmblem2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        rbBanstandard_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbBanstandard_fisikkananx.isChecked()){
                    rbBanradial_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbBanstandard_fisikkanan(true);
//                    upd.first().setDb_var_rbBanradial_fisikkanan(false);
//                    realm.commitTransaction();
                }
            }
        });
        rbBanradial_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbBanradial_fisikkananx.isChecked()){
                    rbBanstandard_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbBanradial_fisikkanan(true);
//                    upd.first().setDb_var_rbBanstandard_fisikkanan(false);
//                    realm.commitTransaction();

                }
            }
        });
        cbBan1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBan1_fisikkananx.isChecked()){
                    cbBan1_fisikkananx.setText("Ada");
                    spBan2_fisikkananx.setVisibility(View.VISIBLE);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBan1_fisikkanan(true);
//                    upd.first().setTdb_var_cbBan1_fisikkanan("Ada");
//                    upd.first().setDb_var_spBan2_fisikkanan(spBan2_fisikkanan.getSelectedItemPosition());
//                    upd.first().setVdb_var_spBan2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBan1_fisikkananx.setText("Tidak Ada");
                    spBan2_fisikkananx.setVisibility(View.INVISIBLE);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBan1_fisikkanan(false);
//                    upd.first().setTdb_var_cbBan1_fisikkanan("Tidak Ada");
//                    upd.first().setDb_var_spBan2_fisikkanan(0);
//                    upd.first().setVdb_var_spBan2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }

            }
        });

        rbVelgstandard_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVelgstandard_fisikkananx.isChecked()){
                    rbVelgracing_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbVelgstandard_fisikkanan(true);
//                    upd.first().setDb_var_rbVelgracing_fisikkanan(false);
//                    realm.commitTransaction();
                }
            }
        });
        rbVelgracing_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVelgracing_fisikkananx.isChecked()){
                    rbVelgstandard_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbVelgracing_fisikkanan(true);
//                    upd.first().setDb_var_rbVelgstandard_fisikkanan(false);
//                    realm.commitTransaction();
                }
            }
        });
        cbVelg1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVelg1_fisikkananx.isChecked()){
                    cbVelg1_fisikkananx.setText("Ada");
                    cbVelg2_fisikkananx.setVisibility(View.VISIBLE);
                    cbVelg2_fisikkananx.setText("Rusak");
                    cbVelg2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbVelg1_fisikkanan(true);
//                    upd.first().setTdb_var_cbVelg1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbVelg2_fisikkanan(false);
//                    upd.first().setTdb_var_cbVelg2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbVelg2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbVelg2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbVelg1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbVelg1_fisikkanan(false);
//                    upd.first().setTdb_var_cbVelg1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbVelg2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbVelg2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbVelg2_fisikkananx.isChecked()){
                    cbVelg2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbVelg2_fisikkanan(true);
//                    upd.first().setTdb_var_cbVelg2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbVelg2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbVelg2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbVelg2_fisikkanan(false);
//                    upd.first().setTdb_var_cbVelg2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbVelg2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbDop1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDop1_fisikkananx.isChecked()){
                    cbDop1_fisikkananx.setText("Ada");
                    cbDop2_fisikkananx.setVisibility(View.VISIBLE);
                    cbDop2_fisikkananx.setText("Rusak");
                    cbDop2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDop1_fisikkanan(true);
//                    upd.first().setTdb_var_cbDop1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbDop2_fisikkanan(false);
//                    upd.first().setTdb_var_cbDop2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbDop2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbDop2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbDop1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDop1_fisikkanan(false);
//                    upd.first().setTdb_var_cbDop1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbDop2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbDop2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDop2_fisikkananx.isChecked()){
                    cbDop2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDop2_fisikkanan(true);
//                    upd.first().setTdb_var_cbDop2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbDop2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbDop2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDop2_fisikkanan(false);
//                    upd.first().setTdb_var_cbDop2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbDop2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });

        cbDopBlk1_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDopBlk1_fisikkananx.isChecked()){
                    cbDopBlk1_fisikkananx.setText("Ada");
                    cbDopBlk2_fisikkananx.setVisibility(View.VISIBLE);
                    cbDopBlk2_fisikkananx.setText("Rusak");
                    cbDopBlk2_fisikkananx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDopBlk1_fisikkanan(true);
//                    upd.first().setTdb_var_cbDopBlk1_fisikkanan("Ada");
//                    upd.first().setDb_var_cbDopBlk2_fisikkanan(false);
//                    upd.first().setTdb_var_cbDopBlk2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbDopBlk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbDopBlk2_fisikkananx.setVisibility(View.INVISIBLE);
                    cbDopBlk1_fisikkananx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDopBlk1_fisikkanan(false);
//                    upd.first().setTdb_var_cbDopBlk1_fisikkanan("Tidak Ada");
//                    upd.first().setVdb_var_cbDopBlk2_fisikkanan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbDopBlk2_fisikkananx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDopBlk2_fisikkananx.isChecked()){
                    cbDopBlk2_fisikkananx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDopBlk2_fisikkanan(true);
//                    upd.first().setTdb_var_cbDopBlk2_fisikkanan("Baik");
//                    upd.first().setVdb_var_cbDopBlk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbDopBlk2_fisikkananx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDopBlk2_fisikkanan(false);
//                    upd.first().setTdb_var_cbDopBlk2_fisikkanan("Rusak");
//                    upd.first().setVdb_var_cbDopBlk2_fisikkanan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        //====================================================================

        cbKunciktk1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKunciktk1_perlengkapanx.isChecked()){
                    cbKunciktk1_perlengkapanx.setText("Ada");
                    cbKunciktk2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbKunciktk2_perlengkapanx.setText("Rusak");
                    cbKunciktk2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKunciktk1_perlengkapan(true);
//                    upd.first().setTdb_var_cbKunciktk1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbKunciktk2_perlengkapan(false);
//                    upd.first().setTdb_var_cbKunciktk2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbKunciktk2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbKunciktk2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbKunciktk1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKunciktk1_perlengkapan(false);
//                    upd.first().setTdb_var_cbKunciktk1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbKunciktk2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbKunciktk2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKunciktk2_perlengkapanx.isChecked()){
                    cbKunciktk2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKunciktk2_perlengkapan(true);
//                    upd.first().setTdb_var_cbKunciktk2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbKunciktk2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbKunciktk2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKunciktk2_perlengkapan(false);
//                    upd.first().setTdb_var_cbKunciktk2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbKunciktk2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSpion1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion1_perlengkapanx.isChecked()){
                    cbSpion1_perlengkapanx.setText("Ada");
                    cbSpion2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbSpion2_perlengkapanx.setText("Rusak");
                    cbSpion2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion1_perlengkapan(true);
//                    upd.first().setTdb_var_cbSpion1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbSpion2_perlengkapan(false);
//                    upd.first().setTdb_var_cbSpion2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbSpion2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSpion2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbSpion1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion1_perlengkapan(false);
//                    upd.first().setTdb_var_cbSpion1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbSpion2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }

            }
        });
        cbSpion2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpion2_perlengkapanx.isChecked()){
                    cbSpion2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion2_perlengkapan(true);
//                    upd.first().setTdb_var_cbSpion2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbSpion2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSpion2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpion2_perlengkapan(false);
//                    upd.first().setTdb_var_cbSpion2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbSpion2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbJok1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJok1_perlengkapanx.isChecked()){
                    cbJok1_perlengkapanx.setText("Ada");
                    cbJok2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbJok2_perlengkapanx.setText("Rusak");
                    cbJok2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbJok1_perlengkapan(true);
//                    upd.first().setTdb_var_cbJok1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbJok2_perlengkapan(false);
//                    upd.first().setTdb_var_cbJok2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbJok2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbJok2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbJok1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbJok1_perlengkapan(false);
//                    upd.first().setTdb_var_cbJok1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbJok2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbJok2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJok2_perlengkapanx.isChecked()){
                    cbJok2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbJok2_perlengkapan(true);
//                    upd.first().setTdb_var_cbJok2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbJok2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbJok2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbJok2_perlengkapan(false);
//                    upd.first().setTdb_var_cbJok2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbJok2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSarung1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSarung1_perlengkapanx.isChecked()){
                    cbSarung1_perlengkapanx.setText("Ada");
                    cbSarung2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbSarung2_perlengkapanx.setText("Rusak");
                    cbSarung2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSarung1_perlengkapan(true);
//                    upd.first().setTdb_var_cbSarung1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbSarung2_perlengkapan(false);
//                    upd.first().setTdb_var_cbSarung2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbSarung2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSarung2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbSarung1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSarung1_perlengkapan(false);
//                    upd.first().setTdb_var_cbSarung1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbSarung2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSarung2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSarung2_perlengkapanx.isChecked()){
                    cbSarung2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSarung2_perlengkapan(true);
//                    upd.first().setTdb_var_cbSarung2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbSarung2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSarung2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSarung2_perlengkapan(false);
//                    upd.first().setTdb_var_cbSarung2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbSarung2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSandaran1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSandaran1_perlengkapanx.isChecked()){
                    cbSandaran1_perlengkapanx.setText("Ada");
                    cbSandaran2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbSandaran2_perlengkapanx.setText("Rusak");
                    cbSandaran2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSandaran1_perlengkapan(true);
//                    upd.first().setTdb_var_cbSandaran1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbSandaran2_perlengkapan(false);
//                    upd.first().setTdb_var_cbSandaran2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbSandaran2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSandaran2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbSandaran1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSandaran1_perlengkapan(false);
//                    upd.first().setTdb_var_cbSandaran1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbSandaran2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSandaran2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSandaran2_perlengkapanx.isChecked()){
                    cbSandaran2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSandaran2_perlengkapan(true);
//                    upd.first().setTdb_var_cbSandaran2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbSandaran2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSandaran2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSandaran2_perlengkapan(false);
//                    upd.first().setTdb_var_cbSandaran2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbSandaran2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbKarpet1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKarpet1_perlengkapanx.isChecked()){
                    cbKarpet1_perlengkapanx.setText("Ada");
                    cbKarpet2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbKarpet2_perlengkapanx.setText("Rusak");
                    cbKarpet2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKarpet1_perlengkapan(true);
//                    upd.first().setTdb_var_cbKarpet1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbKarpet2_perlengkapan(false);
//                    upd.first().setTdb_var_cbKarpet2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbKarpet2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbKarpet2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbKarpet1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKarpet1_perlengkapan(false);
//                    upd.first().setTdb_var_cbKarpet1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbKarpet2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbKarpet2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKarpet2_perlengkapanx.isChecked()){
                    cbKarpet2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKarpet2_perlengkapan(true);
//                    upd.first().setTdb_var_cbKarpet2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbKarpet2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbKarpet2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKarpet2_perlengkapan(false);
//                    upd.first().setTdb_var_cbKarpet2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbKarpet2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPelindung1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPelindung1_perlengkapanx.isChecked()){
                    cbPelindung1_perlengkapanx.setText("Ada");
                    cbPelindung2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbPelindung2_perlengkapanx.setText("Rusak");
                    cbPelindung2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPelindung1_perlengkapan(true);
//                    upd.first().setTdb_var_cbPelindung1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbPelindung2_perlengkapan(false);
//                    upd.first().setTdb_var_cbPelindung2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbPelindung2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPelindung2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbPelindung1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPelindung1_perlengkapan(false);
//                    upd.first().setTdb_var_cbPelindung1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbPelindung2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPelindung2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPelindung2_perlengkapanx.isChecked()){
                    cbPelindung2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPelindung2_perlengkapan(true);
//                    upd.first().setTdb_var_cbPelindung2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbPelindung2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPelindung2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPelindung2_perlengkapan(false);
//                    upd.first().setTdb_var_cbPelindung2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbPelindung2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSegitiga1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSegitiga1_perlengkapanx.isChecked()){
                    cbSegitiga1_perlengkapanx.setText("Ada");
                    cbSegitiga2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbSegitiga2_perlengkapanx.setText("Rusak");
                    cbSegitiga2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSegitiga1_perlengkapan(true);
//                    upd.first().setTdb_var_cbSegitiga1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbSegitiga2_perlengkapan(false);
//                    upd.first().setTdb_var_cbSegitiga2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbSegitiga2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSegitiga2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbSegitiga1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSegitiga1_perlengkapan(false);
//                    upd.first().setTdb_var_cbSegitiga1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbSegitiga2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSegitiga2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSegitiga2_perlengkapanx.isChecked()){
                    cbSegitiga2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSegitiga2_perlengkapan(true);
//                    upd.first().setTdb_var_cbSegitiga2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbSegitiga2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSegitiga2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSegitiga2_perlengkapan(false);
//                    upd.first().setTdb_var_cbSegitiga2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbSegitiga2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbTool1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTool1_perlengkapanx.isChecked()){
                    cbTool1_perlengkapanx.setText("Ada");
                    cbTool2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbTool2_perlengkapanx.setText("Rusak");
                    cbTool2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTool1_perlengkapan(true);
//                    upd.first().setTdb_var_cbTool1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbTool2_perlengkapan(false);
//                    upd.first().setTdb_var_cbTool2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbTool2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbTool2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbTool1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTool1_perlengkapan(false);
//                    upd.first().setTdb_var_cbTool1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbTool2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbTool2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTool2_perlengkapanx.isChecked()){
                    cbTool2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTool2_perlengkapan(true);
//                    upd.first().setTdb_var_cbTool2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbTool2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbTool2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTool2_perlengkapan(false);
//                    upd.first().setTdb_var_cbTool2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbTool2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbCadangan1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCadangan1_perlengkapanx.isChecked()){
                    cbCadangan1_perlengkapanx.setText("Ada");
                    cbCadangan2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbCadangan2_perlengkapanx.setText("Rusak");
                    cbCadangan2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbCadangan1_perlengkapan(true);
//                    upd.first().setTdb_var_cbCadangan1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbCadangan2_perlengkapan(false);
//                    upd.first().setTdb_var_cbCadangan2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbCadangan2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbCadangan2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbCadangan1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbCadangan1_perlengkapan(false);
//                    upd.first().setTdb_var_cbCadangan1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbCadangan2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbCadangan2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCadangan2_perlengkapanx.isChecked()){
                    cbCadangan2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbCadangan2_perlengkapan(true);
//                    upd.first().setTdb_var_cbCadangan2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbCadangan2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbCadangan2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbCadangan2_perlengkapan(false);
//                    upd.first().setTdb_var_cbCadangan2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbCadangan2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbKunciban1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKunciban1_perlengkapanx.isChecked()){
                    cbKunciban1_perlengkapanx.setText("Ada");
                    cbKunciban2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbKunciban2_perlengkapanx.setText("Rusak");
                    cbKunciban2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKunciban1_perlengkapan(true);
//                    upd.first().setTdb_var_cbKunciban1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbKunciban2_perlengkapan(false);
//                    upd.first().setTdb_var_cbKunciban2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbKunciban2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbKunciban2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbKunciban1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKunciban1_perlengkapan(false);
//                    upd.first().setTdb_var_cbKunciban1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbKunciban2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbKunciban2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKunciban2_perlengkapanx.isChecked()){
                    cbKunciban2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKunciban2_perlengkapan(true);
//                    upd.first().setTdb_var_cbKunciban2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbKunciban2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbKunciban2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKunciban2_perlengkapan(false);
//                    upd.first().setTdb_var_cbKunciban2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbKunciban2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbDongkrak1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDongkrak1_perlengkapanx.isChecked()){
                    cbDongkrak1_perlengkapanx.setText("Ada");
                    cbDongkrak2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbDongkrak2_perlengkapanx.setText("Rusak");
                    cbDongkrak2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDongkrak1_perlengkapan(true);
//                    upd.first().setTdb_var_cbDongkrak1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbDongkrak2_perlengkapan(false);
//                    upd.first().setTdb_var_cbDongkrak2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbDongkrak2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbDongkrak2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbDongkrak1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDongkrak1_perlengkapan(false);
//                    upd.first().setTdb_var_cbDongkrak1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbDongkrak2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbDongkrak2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDongkrak2_perlengkapanx.isChecked()){
                    cbDongkrak2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDongkrak2_perlengkapan(true);
//                    upd.first().setTdb_var_cbDongkrak2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbDongkrak2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbDongkrak2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbDongkrak2_perlengkapan(false);
//                    upd.first().setTdb_var_cbDongkrak2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbDongkrak2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbAntena1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAntena1_perlengkapanx.isChecked()){
                    cbAntena1_perlengkapanx.setText("Ada");
                    cbAntena2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbAntena2_perlengkapanx.setText("Rusak");
                    cbAntena2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAntena1_perlengkapan(true);
//                    upd.first().setTdb_var_cbAntena1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbAntena2_perlengkapan(false);
//                    upd.first().setTdb_var_cbAntena2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbAntena2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbAntena2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbAntena1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAntena1_perlengkapan(false);
//                    upd.first().setTdb_var_cbAntena1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbAntena2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbAntena2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAntena2_perlengkapanx.isChecked()){
                    cbAntena2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAntena2_perlengkapan(true);
//                    upd.first().setTdb_var_cbAntena2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbAntena2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbAntena2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAntena2_perlengkapan(false);
//                    upd.first().setTdb_var_cbAntena2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbAntena2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbAirbag1_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAirbag1_perlengkapanx.isChecked()){
                    cbAirbag1_perlengkapanx.setText("Ada");
                    cbAirbag2_perlengkapanx.setVisibility(View.VISIBLE);
                    cbAirbag2_perlengkapanx.setText("Rusak");
                    cbAirbag2_perlengkapanx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAirbag1_perlengkapan(true);
//                    upd.first().setTdb_var_cbAirbag1_perlengkapan("Ada");
//                    upd.first().setDb_var_cbAirbag2_perlengkapan(false);
//                    upd.first().setTdb_var_cbAirbag2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbAirbag2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbAirbag2_perlengkapanx.setVisibility(View.INVISIBLE);
                    cbAirbag1_perlengkapanx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAirbag1_perlengkapan(false);
//                    upd.first().setTdb_var_cbAirbag1_perlengkapan("Tidak Ada");
//                    upd.first().setVdb_var_cbAirbag2_perlengkapan(View.INVISIBLE);
//                    realm.commitTransaction();

                }

            }
        });
        cbAirbag2_perlengkapanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAirbag2_perlengkapanx.isChecked()){
                    cbAirbag2_perlengkapanx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAirbag2_perlengkapan(true);
//                    upd.first().setTdb_var_cbAirbag2_perlengkapan("Baik");
//                    upd.first().setVdb_var_cbAirbag2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbAirbag2_perlengkapanx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAirbag2_perlengkapan(false);
//                    upd.first().setTdb_var_cbAirbag2_perlengkapan("Rusak");
//                    upd.first().setVdb_var_cbAirbag2_perlengkapan(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        //=========================================================================

        cbLampukbt1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampukbt1_listrikx.isChecked()){
                    cbLampukbt1_listrikx.setText("Ada");
                    cbLampukbt2_listrikx.setVisibility(View.VISIBLE);
                    cbLampukbt2_listrikx.setText("Rusak");
                    cbLampukbt2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampukbt1_listrik(true);
//                    upd.first().setTdb_var_cbLampukbt1_listrik("Ada");
//                    upd.first().setDb_var_cbLampukbt2_listrik(false);
//                    upd.first().setTdb_var_cbLampukbt2_listrik("Rusak");
//                    upd.first().setVdb_var_cbLampukbt2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbLampukbt2_listrikx.setVisibility(View.INVISIBLE);
                    cbLampukbt1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampukbt1_listrik(false);
//                    upd.first().setTdb_var_cbLampukbt1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbLampukbt2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbLampukbt2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLampukbt2_listrikx.isChecked()){
                    cbLampukbt2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampukbt2_listrik(true);
//                    upd.first().setTdb_var_cbLampukbt2_listrik("Baik");
//                    upd.first().setVdb_var_cbLampukbt2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbLampukbt2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampukbt2_listrik(false);
//                    upd.first().setTdb_var_cbLampukbt2_listrik("Rusak");
//                    upd.first().setVdb_var_cbLampukbt2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbWiperkacadpn1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWiperkacadpn1_listrikx.isChecked()){
                    cbWiperkacadpn1_listrikx.setText("Ada");
                    cbWiperkacadpn2_listrikx.setVisibility(View.VISIBLE);
                    cbWiperkacadpn2_listrikx.setText("Rusak");
                    cbWiperkacadpn2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbWiperkacadpn1_listrik(true);
//                    upd.first().setTdb_var_cbWiperkacadpn1_listrik("Ada");
//                    upd.first().setDb_var_cbWiperkacadpn2_listrik(false);
//                    upd.first().setTdb_var_cbWiperkacadpn2_listrik("Rusak");
//                    upd.first().setVdb_var_cbWiperkacadpn2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbWiperkacadpn2_listrikx.setVisibility(View.INVISIBLE);
                    cbWiperkacadpn1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbWiperkacadpn1_listrik(false);
//                    upd.first().setTdb_var_cbWiperkacadpn1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbWiperkacadpn2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbWiperkacadpn2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWiperkacadpn2_listrikx.isChecked()){
                    cbWiperkacadpn2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbWiperkacadpn2_listrik(true);
//                    upd.first().setTdb_var_cbWiperkacadpn2_listrik("Baik");
//                    upd.first().setVdb_var_cbWiperkacadpn2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbWiperkacadpn2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbWiperkacadpn2_listrik(false);
//                    upd.first().setTdb_var_cbWiperkacadpn2_listrik("Rusak");
//                    upd.first().setVdb_var_cbWiperkacadpn2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbWiperkacablk1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWiperkacablk1_listrikx.isChecked()){
                    cbWiperkacablk1_listrikx.setText("Ada");
                    cbWiperkacablk2_listrikx.setVisibility(View.VISIBLE);
                    cbWiperkacablk2_listrikx.setText("Rusak");
                    cbWiperkacablk2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbWiperkacablk1_listrik(true);
//                    upd.first().setTdb_var_cbWiperkacablk1_listrik("Ada");
//                    upd.first().setDb_var_cbWiperkacablk2_listrik(false);
//                    upd.first().setTdb_var_cbWiperkacablk2_listrik("Rusak");
//                    upd.first().setVdb_var_cbWiperkacablk2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbWiperkacablk2_listrikx.setVisibility(View.INVISIBLE);
                    cbWiperkacablk1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbWiperkacablk1_listrik(false);
//                    upd.first().setTdb_var_cbWiperkacablk1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbWiperkacablk2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbWiperkacablk2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWiperkacablk2_listrikx.isChecked()){
                    cbWiperkacablk2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbWiperkacablk2_listrik(true);
//                    upd.first().setTdb_var_cbWiperkacablk2_listrik("Baik");
//                    upd.first().setVdb_var_cbWiperkacablk2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbWiperkacablk2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbWiperkacablk2_listrik(false);
//                    upd.first().setTdb_var_cbWiperkacablk2_listrik("Rusak");
//                    upd.first().setVdb_var_cbWiperkacablk2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbKlakson1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKlakson1_listrikx.isChecked()){
                    cbKlakson1_listrikx.setText("Ada");
                    cbKlakson2_listrikx.setVisibility(View.VISIBLE);
                    cbKlakson2_listrikx.setText("Rusak");
                    cbKlakson2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKlakson1_listrik(true);
//                    upd.first().setTdb_var_cbKlakson1_listrik("Ada");
//                    upd.first().setDb_var_cbKlakson2_listrik(false);
//                    upd.first().setTdb_var_cbKlakson2_listrik("Rusak");
//                    upd.first().setVdb_var_cbKlakson2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbKlakson2_listrikx.setVisibility(View.INVISIBLE);
                    cbKlakson1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKlakson1_listrik(false);
//                    upd.first().setTdb_var_cbKlakson1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbKlakson2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbKlakson2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKlakson2_listrikx.isChecked()){
                    cbKlakson2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbKlakson2_listrik(true);
//                    upd.first().setTdb_var_cbKlakson2_listrik("Baik");
//                    upd.first().setVdb_var_cbKlakson2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbKlakson2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLampu2_fisikmuka(false);
//                    upd.first().setTdb_var_cbLampu2_fisikmuka("Rusak");
//                    upd.first().setVdb_var_cbLampu2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbAlarm1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAlarm1_listrikx.isChecked()){
                    cbAlarm1_listrikx.setText("Ada");
                    cbAlarm2_listrikx.setVisibility(View.VISIBLE);
                    cbAlarm2_listrikx.setText("Rusak");
                    cbAlarm2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAlarm1_listrik(true);
//                    upd.first().setTdb_var_cbAlarm1_listrik("Ada");
//                    upd.first().setDb_var_cbAlarm2_listrik(false);
//                    upd.first().setTdb_var_cbAlarm2_listrik("Rusak");
//                    upd.first().setVdb_var_cbAlarm2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbAlarm2_listrikx.setVisibility(View.INVISIBLE);
                    cbAlarm1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAlarm1_listrik(false);
//                    upd.first().setTdb_var_cbAlarm1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbAlarm2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbAlarm2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAlarm2_listrikx.isChecked()){
                    cbAlarm2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAlarm2_listrik(true);
//                    upd.first().setTdb_var_cbAlarm2_listrik("Baik");
//                    upd.first().setVdb_var_cbAlarm2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbAlarm2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAlarm2_listrik(false);
//                    upd.first().setTdb_var_cbAlarm2_listrik("Rusak");
//                    upd.first().setVdb_var_cbAlarm2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbJam1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJam1_listrikx.isChecked()){
                    cbJam1_listrikx.setText("Ada");
                    cbJam2_listrikx.setVisibility(View.VISIBLE);
                    cbJam2_listrikx.setText("Rusak");
                    cbJam2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbJam1_listrik(true);
//                    upd.first().setTdb_var_cbJam1_listrik("Ada");
//                    upd.first().setDb_var_cbJam2_listrik(false);
//                    upd.first().setTdb_var_cbJam2_listrik("Rusak");
//                    upd.first().setVdb_var_cbJam2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbJam2_listrikx.setVisibility(View.INVISIBLE);
                    cbJam1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbJam1_listrik(false);
//                    upd.first().setTdb_var_cbJam1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbJam2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbJam2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJam2_listrikx.isChecked()){
                    cbJam2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbJam2_listrik(true);
//                    upd.first().setTdb_var_cbJam2_listrik("Baik");
//                    upd.first().setVdb_var_cbJam2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbJam2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbJam2_listrik(false);
//                    upd.first().setTdb_var_cbJam2_listrik("Rusak");
//                    upd.first().setVdb_var_cbJam2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbLighter1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLighter1_listrikx.isChecked()){
                    cbLighter1_listrikx.setText("Ada");
                    cbLighter2_listrikx.setVisibility(View.VISIBLE);
                    cbLighter2_listrikx.setText("Rusak");
                    cbLighter2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLighter1_listrik(true);
//                    upd.first().setTdb_var_cbLighter1_listrik("Ada");
//                    upd.first().setDb_var_cbLighter2_listrik(false);
//                    upd.first().setTdb_var_cbLighter2_listrik("Rusak");
//                    upd.first().setVdb_var_cbLighter2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbLighter2_listrikx.setVisibility(View.INVISIBLE);
                    cbLighter1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLighter1_listrik(false);
//                    upd.first().setTdb_var_cbLighter1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbLighter2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbLighter2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLighter2_listrikx.isChecked()){
                    cbLighter2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLighter2_listrik(true);
//                    upd.first().setTdb_var_cbLighter2_listrik("Baik");
//                    upd.first().setVdb_var_cbLighter2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbLighter2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbLighter2_listrik(false);
//                    upd.first().setTdb_var_cbLighter2_listrik("Rusak");
//                    upd.first().setVdb_var_cbLighter2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        rbRadio_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbRadio_listrikx.isChecked()){
                    rbTape_listrikx.setChecked(false);
                    rbCd_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbRadio_listrik(true);
//                    upd.first().setDb_var_rbTape_listrik(false);
//                    upd.first().setDb_var_rbCd_listrik(false);
//                    realm.commitTransaction();

                }
            }
        });
        rbTape_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbTape_listrikx.isChecked()){
                    rbRadio_listrikx.setChecked(false);
                    rbCd_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbRadio_listrik(false);
//                    upd.first().setDb_var_rbTape_listrik(true);
//                    upd.first().setDb_var_rbCd_listrik(false);
//                    realm.commitTransaction();

                }
            }
        });
        rbCd_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbCd_listrik.isChecked()){
                    rbRadio_listrikx.setChecked(false);
                    rbTape_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_rbRadio_listrik(false);
//                    upd.first().setDb_var_rbTape_listrik(false);
//                    upd.first().setDb_var_rbCd_listrik(true);
//                    realm.commitTransaction();

                }
            }
        });

        cbRadio1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRadio1_listrikx.isChecked()){
                    cbRadio1_listrikx.setText("Ada");
                    cbRadio2_listrikx.setVisibility(View.VISIBLE);
                    cbRadio2_listrikx.setText("Rusak");
                    cbRadio2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbRadio1_listrik(true);
//                    upd.first().setTdb_var_cbRadio1_listrik("Ada");
//                    upd.first().setDb_var_cbRadio2_listrik(false);
//                    upd.first().setTdb_var_cbRadio2_listrik("Rusak");
//                    upd.first().setVdb_var_cbRadio2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbRadio2_listrikx.setVisibility(View.INVISIBLE);
                    cbRadio1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbRadio1_listrik(false);
//                    upd.first().setTdb_var_cbRadio1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbRadio2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbRadio2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRadio2_listrikx.isChecked()){
                    cbRadio2_listrikx.setText("Baik");
                    //var_cbRadio2_listrik= true;

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbRadio2_listrik(true);
//                    upd.first().setTdb_var_cbRadio2_listrik("Baik");
//                    upd.first().setVdb_var_cbRadio2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbRadio2_listrikx.setText("Rusak");
                    //var_cbRadio2_listrik = false;
//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbRadio2_listrik(false);
//                    upd.first().setTdb_var_cbRadio2_listrik("Rusak");
//                    upd.first().setVdb_var_cbRadio2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbPowersup1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPowersup1_listrikx.isChecked()){
                    cbPowersup1_listrikx.setText("Ada");
                    cbPowersup2_listrikx.setVisibility(View.VISIBLE);
                    cbPowersup2_listrikx.setText("Rusak");
                    cbPowersup2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPowersup1_listrik(true);
//                    upd.first().setTdb_var_cbPowersup1_listrik("Ada");
//                    upd.first().setDb_var_cbPowersup2_listrik(false);
//                    upd.first().setTdb_var_cbPowersup2_listrik("Rusak");
//                    upd.first().setVdb_var_cbPowersup2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPowersup2_listrikx.setVisibility(View.INVISIBLE);
                    cbPowersup1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPowersup1_listrik(false);
//                    upd.first().setTdb_var_cbPowersup1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbPowersup2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbPowersup2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPowersup2_listrikx.isChecked()){
                    cbPowersup2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPowersup2_listrik(true);
//                    upd.first().setTdb_var_cbPowersup2_listrik("Baik");
//                    upd.first().setVdb_var_cbPowersup2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbPowersup2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPowersup2_listrik(false);
//                    upd.first().setTdb_var_cbPowersup2_listrik("Rusak");
//                    upd.first().setVdb_var_cbPowersup2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbSpeaker1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpeaker1_listrikx.isChecked()){
                    cbSpeaker1_listrikx.setText("Ada");
                    cbSpeaker2_listrikx.setVisibility(View.VISIBLE);
                    cbSpeaker2_listrikx.setText("Rusak");
                    cbSpeaker2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpeaker1_listrik(true);
//                    upd.first().setTdb_var_cbSpeaker1_listrik("Ada");
//                    upd.first().setDb_var_cbSpeaker2_listrik(false);
//                    upd.first().setTdb_var_cbSpeaker2_listrik("Rusak");
//                    upd.first().setVdb_var_cbSpeaker2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbSpeaker2_listrikx.setVisibility(View.INVISIBLE);
                    cbSpeaker1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpeaker1_listrik(false);
//                    upd.first().setTdb_var_cbSpeaker1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbSpeaker2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbSpeaker2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpeaker2_listrikx.isChecked()){
                    cbSpeaker2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpeaker2_listrik(true);
//                    upd.first().setTdb_var_cbSpeaker2_listrik("Baik");
//                    upd.first().setVdb_var_cbSpeaker2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbSpeaker2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpeaker2_listrik(false);
//                    upd.first().setTdb_var_cbSpeaker2_listrik("Rusak");
//                    upd.first().setVdb_var_cbSpeaker2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbAc1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAc1_listrikx.isChecked()){
                    cbAc1_listrikx.setText("Ada");
                    cbAc2_listrikx.setVisibility(View.VISIBLE);
                    cbAc2_listrikx.setText("Rusak");
                    cbAc2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAc1_listrik(true);
//                    upd.first().setTdb_var_cbAc1_listrik("Ada");
//                    upd.first().setDb_var_cbAc2_listrik(false);
//                    upd.first().setTdb_var_cbAc2_listrik("Rusak");
//                    upd.first().setVdb_var_cbAc2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbAc2_listrikx.setVisibility(View.INVISIBLE);
                    cbAc1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAc1_listrik(false);
//                    upd.first().setTdb_var_cbAc1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbAc2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbAc2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAc2_listrikx.isChecked()){
                    cbAc2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAc2_listrik(true);
//                    upd.first().setTdb_var_cbAc2_listrik("Baik");
//                    upd.first().setVdb_var_cbAc2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbAc2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAc2_listrik(false);
//                    upd.first().setTdb_var_cbAc2_listrik("Rusak");
//                    upd.first().setVdb_var_cbAc2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbPowerwin1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPowerwin1_listrikx.isChecked()){
                    cbPowerwin1_listrikx.setText("Ada");
                    cbPowerwin2_listrikx.setVisibility(View.VISIBLE);
                    cbPowerwin2_listrikx.setText("Rusak");
                    cbPowerwin2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPowerwin1_listrik(true);
//                    upd.first().setTdb_var_cbPowerwin1_listrik("Ada");
//                    upd.first().setDb_var_cbPowerwin2_listrik(false);
//                    upd.first().setTdb_var_cbPowerwin2_listrik("Rusak");
//                    upd.first().setVdb_var_cbPowerwin2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPowerwin2_listrikx.setVisibility(View.INVISIBLE);
                    cbPowerwin1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPowerwin1_listrik(false);
//                    upd.first().setTdb_var_cbPowerwin1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbPowerwin2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbPowerwin2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPowerwin2_listrikx.isChecked()){
                    cbPowerwin2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPowerwin2_listrik(true);
//                    upd.first().setTdb_var_cbPowerwin2_listrik("Baik");
//                    upd.first().setVdb_var_cbPowerwin2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbPowerwin2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbPowerwin2_listrik(false);
//                    upd.first().setTdb_var_cbPowerwin2_listrik("Rusak");
//                    upd.first().setVdb_var_cbPowerwin2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbCentral1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCentral1_listrikx.isChecked()){
                    cbCentral1_listrikx.setText("Ada");
                    cbCentral2_listrikx.setVisibility(View.VISIBLE);
                    cbCentral2_listrikx.setText("Rusak");
                    cbCentral2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbCentral1_listrik(true);
//                    upd.first().setTdb_var_cbCentral1_listrik("Ada");
//                    upd.first().setDb_var_cbCentral2_listrik(false);
//                    upd.first().setTdb_var_cbCentral2_listrik("Rusak");
//                    upd.first().setVdb_var_cbCentral2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbCentral2_listrikx.setVisibility(View.INVISIBLE);
                    cbCentral1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbCentral1_listrik(false);
//                    upd.first().setTdb_var_cbCentral1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbCentral2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbCentral2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCentral2_listrikx.isChecked()){
                    cbCentral2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbCentral2_listrik(true);
//                    upd.first().setTdb_var_cbCentral2_listrik("Baik");
//                    upd.first().setVdb_var_cbCentral2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbCentral2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbCentral2_listrik(false);
//                    upd.first().setTdb_var_cbCentral2_listrik("Rusak");
//                    upd.first().setVdb_var_cbCentral2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbRemote1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRemote1_listrikx.isChecked()){
                    cbRemote1_listrikx.setText("Ada");
                    cbRemote2_listrikx.setVisibility(View.VISIBLE);
                    cbRemote2_listrikx.setText("Rusak");
                    cbRemote2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbRemote1_listrik(true);
//                    upd.first().setTdb_var_cbRemote1_listrik("Ada");
//                    upd.first().setDb_var_cbRemote2_listrik(false);
//                    upd.first().setTdb_var_cbRemote2_listrik("Rusak");
//                    upd.first().setVdb_var_cbRemote2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbRemote2_listrikx.setVisibility(View.INVISIBLE);
                    cbRemote1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbRemote1_listrik(false);
//                    upd.first().setTdb_var_cbRemote1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbRemote2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbRemote2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRemote2_listrikx.isChecked()){
                    cbRemote2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbRemote2_listrik(true);
//                    upd.first().setTdb_var_cbRemote2_listrik("Baik");
//                    upd.first().setVdb_var_cbRemote2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbRemote2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbRemote2_listrik(false);
//                    upd.first().setTdb_var_cbRemote2_listrik("Rusak");
//                    upd.first().setVdb_var_cbRemote2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbSpeedo1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpeedo1_listrikx.isChecked()){
                    cbSpeedo1_listrikx.setText("Ada");
                    cbSpeedo2_listrikx.setVisibility(View.VISIBLE);
                    cbSpeedo2_listrikx.setText("Rusak");
                    cbSpeedo2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpeedo1_listrik(true);
//                    upd.first().setTdb_var_cbSpeedo1_listrik("Ada");
//                    upd.first().setDb_var_cbSpeedo2_listrik(false);
//                    upd.first().setTdb_var_cbSpeedo2_listrik("Rusak");
//                    upd.first().setVdb_var_cbSpeedo2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbSpeedo2_listrikx.setVisibility(View.INVISIBLE);
                    cbSpeedo1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpeedo1_listrik(false);
//                    upd.first().setTdb_var_cbSpeedo1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbSpeedo2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbSpeedo2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSpeedo2_listrikx.isChecked()){
                    cbSpeedo2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpeedo2_listrik(true);
//                    upd.first().setTdb_var_cbSpeedo2_listrik("Baik");
//                    upd.first().setVdb_var_cbSpeedo2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbSpeedo2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbSpeedo2_listrik(false);
//                    upd.first().setTdb_var_cbSpeedo2_listrik("Rusak");
//                    upd.first().setVdb_var_cbSpeedo2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbOdometer1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbOdometer1_listrikx.isChecked()){
                    cbOdometer1_listrikx.setText("Ada");
                    cbOdometer2_listrikx.setVisibility(View.VISIBLE);
                    cbOdometer2_listrikx.setText("Rusak");
                    cbOdometer2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbOdometer1_listrik(true);
//                    upd.first().setTdb_var_cbOdometer1_listrik("Ada");
//                    upd.first().setDb_var_cbOdometer2_listrik(false);
//                    upd.first().setTdb_var_cbOdometer2_listrik("Rusak");
//                    upd.first().setVdb_var_cbOdometer2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbOdometer2_listrikx.setVisibility(View.INVISIBLE);
                    cbOdometer1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbOdometer1_listrik(false);
//                    upd.first().setTdb_var_cbOdometer1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbOdometer2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbOdometer2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbOdometer2_listrikx.isChecked()){
                    cbOdometer2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbOdometer2_listrik(true);
//                    upd.first().setTdb_var_cbOdometer2_listrik("Baik");
//                    upd.first().setVdb_var_cbOdometer2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbOdometer2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbOdometer2_listrik(false);
//                    upd.first().setTdb_var_cbOdometer2_listrik("Rusak");
//                    upd.first().setVdb_var_cbOdometer2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbTacho1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTacho1_listrikx.isChecked()){
                    cbTacho1_listrikx.setText("Ada");
                    cbTacho2_listrikx.setVisibility(View.VISIBLE);
                    cbTacho2_listrikx.setText("Rusak");
                    cbTacho2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTacho1_listrik(true);
//                    upd.first().setTdb_var_cbTacho1_listrik("Ada");
//                    upd.first().setDb_var_cbTacho2_listrik(false);
//                    upd.first().setTdb_var_cbTacho2_listrik("Rusak");
//                    upd.first().setVdb_var_cbTacho2_listrik(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbTacho2_listrikx.setVisibility(View.INVISIBLE);
                    cbTacho1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTacho1_listrik(false);
//                    upd.first().setTdb_var_cbTacho1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbTacho2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbTacho2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTacho2_listrikx.isChecked()){
                    cbTacho2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBamper2_fisikmuka(true);
//                    upd.first().setTdb_var_cbBamper2_fisikmuka("Baik");
//                    upd.first().setVdb_var_cbBamper2_fisikmuka(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbTacho2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTacho2_listrik(false);
//                    upd.first().setTdb_var_cbTacho2_listrik("Rusak");
//                    upd.first().setVdb_var_cbTacho2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbAccu1_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAccu1_listrikx.isChecked()){
                    cbAccu1_listrikx.setText("Ada");
                    cbAccu2_listrikx.setVisibility(View.VISIBLE);
                    cbAccu2_listrikx.setText("Rusak");
                    cbAccu2_listrikx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAccu1_listrik(true);
//                    upd.first().setTdb_var_cbAccu1_listrik("Ada");
//                    upd.first().setDb_var_cbAccu2_listrik(false);
//                    upd.first().setTdb_var_cbAccu2_listrik("Rusak");
//                    upd.first().setVdb_var_cbAccu2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbAccu2_listrikx.setVisibility(View.INVISIBLE);
                    cbAccu1_listrikx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAccu1_listrik(false);
//                    upd.first().setTdb_var_cbAccu1_listrik("Tidak Ada");
//                    upd.first().setVdb_var_cbAccu2_listrik(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbAccu2_listrikx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAccu2_listrikx.isChecked()){
                    cbAccu2_listrikx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAccu2_listrik(true);
//                    upd.first().setTdb_var_cbAccu2_listrik("Baik");
//                    upd.first().setVdb_var_cbAccu2_listrik(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbAccu2_listrikx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAccu2_listrik(false);
//                    upd.first().setTdb_var_cbAccu2_listrik("Rusak");
//                    upd.first().setVdb_var_cbAccu2_listrik(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        //=======================================================================

        cbMesin1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbMesin1_lainx.isChecked()){
                    cbMesin1_lainx.setText("Ada");
                    cbMesin2_lainx.setVisibility(View.VISIBLE);
                    cbMesin2_lainx.setText("Rusak");
                    cbMesin2_lainx.setChecked(false);


//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbMesin1_lain(true);
//                    upd.first().setTdb_var_cbMesin1_lain("Ada");
//                    upd.first().setDb_var_cbMesin2_lain(false);
//                    upd.first().setTdb_var_cbMesin2_lain("Rusak");
//                    upd.first().setVdb_var_cbMesin2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbMesin2_lainx.setVisibility(View.INVISIBLE);
                    cbMesin1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbMesin1_lain(false);
//                    upd.first().setTdb_var_cbMesin1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbMesin2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbMesin2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbMesin2_lainx.isChecked()){
                    cbMesin2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbMesin2_lain(true);
//                    upd.first().setTdb_var_cbMesin2_lain("Baik");
//                    upd.first().setVdb_var_cbMesin2_lain(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbMesin2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbMesin2_lain(false);
//                    upd.first().setTdb_var_cbMesin2_lain("Rusak");
//                    upd.first().setVdb_var_cbMesin2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbHidraulik1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbHidraulik1_lainx.isChecked()){
                    cbHidraulik1_lainx.setText("Ada");
                    cbHidraulik2_lainx.setVisibility(View.VISIBLE);
                    cbHidraulik2_lainx.setText("Rusak");
                    cbHidraulik2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbHidraulik1_lain(true);
//                    upd.first().setTdb_var_cbHidraulik1_lain("Ada");
//                    upd.first().setDb_var_cbHidraulik2_lain(false);
//                    upd.first().setTdb_var_cbHidraulik2_lain("Rusak");
//                    upd.first().setVdb_var_cbHidraulik2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbHidraulik2_lainx.setVisibility(View.INVISIBLE);
                    cbHidraulik1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbHidraulik1_lain(false);
//                    upd.first().setTdb_var_cbHidraulik1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbHidraulik2_lain(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbHidraulik2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbHidraulik2_lainx.isChecked()){
                    cbHidraulik2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbHidraulik2_lain(true);
//                    upd.first().setTdb_var_cbHidraulik2_lain("Baik");
//                    upd.first().setVdb_var_cbHidraulik2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbHidraulik2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbHidraulik2_lain(false);
//                    upd.first().setTdb_var_cbHidraulik2_lain("Rusak");
//                    upd.first().setVdb_var_cbHidraulik2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbGardan1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbGardan1_lainx.isChecked()){
                    cbGardan1_lainx.setText("Ada");
                    cbGardan2_lainx.setVisibility(View.VISIBLE);
                    cbGardan2_lainx.setText("Rusak");
                    cbGardan2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbGardan1_lain(true);
//                    upd.first().setTdb_var_cbGardan1_lain("Ada");
//                    upd.first().setDb_var_cbGardan2_lain(false);
//                    upd.first().setTdb_var_cbGardan2_lain("Rusak");
//                    upd.first().setVdb_var_cbGardan2_lain(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbGardan2_lainx.setVisibility(View.INVISIBLE);
                    cbGardan1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbGardan1_lain(false);
//                    upd.first().setTdb_var_cbGardan1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbGardan2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbGardan2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbGardan2_lainx.isChecked()){
                    cbGardan2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbGardan2_lain(true);
//                    upd.first().setTdb_var_cbGardan2_lain("Baik");
//                    upd.first().setVdb_var_cbGardan2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbGardan2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbGardan2_lain(false);
//                    upd.first().setTdb_var_cbGardan2_lain("Rusak");
//                    upd.first().setVdb_var_cbGardan2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbAs1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAs1_lainx.isChecked()){
                    cbAs1_lainx.setText("Ada");
                    cbAs2_lainx.setVisibility(View.VISIBLE);
                    cbAs2_lainx.setText("Rusak");
                    cbAs2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAs1_lain(true);
//                    upd.first().setTdb_var_cbAs1_lain("Ada");
//                    upd.first().setDb_var_cbAs2_lain(false);
//                    upd.first().setTdb_var_cbAs2_lain("Rusak");
//                    upd.first().setVdb_var_cbAs2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbAs2_lainx.setVisibility(View.INVISIBLE);
                    cbAs1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAs1_lain(false);
//                    upd.first().setTdb_var_cbAs1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbAs2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbAs2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAs2_lainx.isChecked()){
                    cbAs2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAs2_lain(true);
//                    upd.first().setTdb_var_cbAs2_lain("Baik");
//                    upd.first().setVdb_var_cbAs2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbAs2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbAs2_lain(false);
//                    upd.first().setTdb_var_cbAs2_lain("Rusak");
//                    upd.first().setVdb_var_cbAs2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbBak1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBak1_lainx.isChecked()){
                    cbBak1_lainx.setText("Ada");
                    cbBak2_lainx.setVisibility(View.VISIBLE);
                    cbBak2_lainx.setText("Rusak");
                    cbBak2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBak1_lain(true);
//                    upd.first().setTdb_var_cbBak1_lain("Ada");
//                    upd.first().setDb_var_cbBak2_lain(false);
//                    upd.first().setTdb_var_cbBak2_lain("Rusak");
//                    upd.first().setVdb_var_cbBak2_lain(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBak2_lainx.setVisibility(View.INVISIBLE);
                    cbBak1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBak1_lain(false);
//                    upd.first().setTdb_var_cbBak1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbBak2_lain(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbBak2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBak2_lainx.isChecked()){
                    cbBak2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBak2_lain(true);
//                    upd.first().setTdb_var_cbBak2_lain("Baik");
//                    upd.first().setVdb_var_cbBak2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBak2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBak2_lain(false);
//                    upd.first().setTdb_var_cbBak2_lain("Rusak");
//                    upd.first().setVdb_var_cbBak2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });

        cbStnk1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbStnk1_lainx.isChecked()){
                    cbStnk1_lainx.setText("Ada");
                    cbStnk2_lainx.setVisibility(View.VISIBLE);
                    cbStnk2_lainx.setText("Rusak");
                    cbStnk2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbStnk1_lain(true);
//                    upd.first().setTdb_var_cbStnk1_lain("Ada");
//                    upd.first().setDb_var_cbStnk2_lain(false);
//                    upd.first().setTdb_var_cbStnk2_lain("Rusak");
//                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbStnk2_lainx.setVisibility(View.INVISIBLE);
                    cbStnk1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbStnk1_lain(false);
//                    upd.first().setTdb_var_cbStnk1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbStnk2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbStnk2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbStnk2_lainx.isChecked()){
                    cbStnk2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbStnk2_lain(true);
//                    upd.first().setTdb_var_cbStnk2_lain("Baik");
//                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbStnk2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbStnk2_lain(false);
//                    upd.first().setTdb_var_cbStnk2_lain("Rusak");
//                    upd.first().setVdb_var_cbStnk2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbBukukir1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukukir1_lainx.isChecked()){
                    cbBukukir1_lainx.setText("Ada");
                    cbBukukir2_lainx.setVisibility(View.VISIBLE);
                    cbBukukir2_lainx.setText("Rusak");
                    cbBukukir2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukukir1_lain(true);
//                    upd.first().setTdb_var_cbBukukir1_lain("Ada");
//                    upd.first().setDb_var_cbBukukir2_lain(false);
//                    upd.first().setTdb_var_cbBukukir2_lain("Rusak");
//                    upd.first().setVdb_var_cbBukukir2_lain(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBukukir2_lainx.setVisibility(View.INVISIBLE);
                    cbBukukir1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukukir1_lain(false);
//                    upd.first().setTdb_var_cbBukukir1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbBukukir2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbBukukir2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukukir2_lainx.isChecked()){
                    cbBukukir2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukukir2_lain(true);
//                    upd.first().setTdb_var_cbBukukir2_lain("Baik");
//                    upd.first().setVdb_var_cbBukukir2_lain(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbBukukir2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukukir2_lain(false);
//                    upd.first().setTdb_var_cbBukukir2_lain("Rusak");
//                    upd.first().setVdb_var_cbBukukir2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbTrayek1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTrayek1_lainx.isChecked()){
                    cbTrayek1_lainx.setText("Ada");
                    cbTrayek2_lainx.setVisibility(View.VISIBLE);
                    cbTrayek2_lainx.setText("Rusak");
                    cbTrayek2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTrayek1_lain(true);
//                    upd.first().setTdb_var_cbTrayek1_lain("Ada");
//                    upd.first().setDb_var_cbTrayek2_lain(false);
//                    upd.first().setTdb_var_cbTrayek2_lain("Rusak");
//                    upd.first().setVdb_var_cbTrayek2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbTrayek2_lainx.setVisibility(View.INVISIBLE);
                    cbTrayek1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTrayek1_lain(false);
//                    upd.first().setTdb_var_cbTrayek1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbTrayek2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbTrayek2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTrayek2_lainx.isChecked()){
                    cbTrayek2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbTrayek2_lain(true);
//                    upd.first().setTdb_var_cbTrayek2_lain("Baik");
//                    upd.first().setVdb_var_cbTrayek2_lain(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbTrayek2_lainx.setText("Rusak");
                    var_cbTrayek2_lain = false;
                }
            }
        });
        cbUsaha1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbUsaha1_lainx.isChecked()){
                    cbUsaha1_lainx.setText("Ada");
                    cbUsaha2_lainx.setVisibility(View.VISIBLE);
                    cbUsaha2_lainx.setText("Rusak");
                    cbUsaha2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbUsaha1_lain(true);
//                    upd.first().setTdb_var_cbUsaha1_lain("Ada");
//                    upd.first().setDb_var_cbUsaha2_lain(false);
//                    upd.first().setTdb_var_cbUsaha2_lain("Rusak");
//                    upd.first().setVdb_var_cbUsaha2_lain(View.VISIBLE);
//                    realm.commitTransaction();


                }else {
                    cbUsaha2_lainx.setVisibility(View.INVISIBLE);
                    cbUsaha1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbUsaha1_lain(false);
//                    upd.first().setTdb_var_cbUsaha1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbUsaha2_lain(View.INVISIBLE);
//                    realm.commitTransaction();

                }
            }
        });
        cbUsaha2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbUsaha2_lainx.isChecked()){
                    cbUsaha2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbUsaha2_lain(true);
//                    upd.first().setTdb_var_cbUsaha2_lain("Baik");
//                    upd.first().setVdb_var_cbUsaha2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbUsaha2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbUsaha2_lain(false);
//                    upd.first().setTdb_var_cbUsaha2_lain("Rusak");
//                    upd.first().setVdb_var_cbUsaha2_lain(View.VISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbBukumnl1_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukumnl1_lainx.isChecked()){
                    cbBukumnl1_lainx.setText("Ada");
                    cbBukumnl2_lainx.setVisibility(View.VISIBLE);
                    cbBukumnl2_lainx.setText("Rusak");
                    cbBukumnl2_lainx.setChecked(false);

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukumnl1_lain(true);
//                    upd.first().setTdb_var_cbBukumnl1_lain("Ada");
//                    upd.first().setDb_var_cbBukumnl2_lain(false);
//                    upd.first().setTdb_var_cbBukumnl2_lain("Rusak");
//                    upd.first().setVdb_var_cbBukumnl2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBukumnl2_lainx.setVisibility(View.INVISIBLE);
                    cbBukumnl1_lainx.setText("Tidak Ada");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukumnl1_lain(false);
//                    upd.first().setTdb_var_cbBukumnl1_lain("Tidak Ada");
//                    upd.first().setVdb_var_cbBukumnl2_lain(View.INVISIBLE);
//                    realm.commitTransaction();
                }
            }
        });
        cbBukumnl2_lainx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBukumnl2_lainx.isChecked()){
                    cbBukumnl2_lainx.setText("Baik");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
//                    upd.load();
//                    upd.first().setDb_var_cbBukumnl2_lain(true);
//                    upd.first().setTdb_var_cbBukumnl2_lain("Baik");
//                    upd.first().setVdb_var_cbBukumnl2_lain(View.VISIBLE);
//                    realm.commitTransaction();

                }else {
                    cbBukumnl2_lainx.setText("Rusak");

//                    realm.beginTransaction();
//                    RealmResults<Ceklist> upd = realm.where(Ceklist.class).findAllAsync();
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
        final String[] stnk = etSd_stnkx.getText().toString().split("-");
        final String[] bukukir = etSd_bukukirx.getText().toString().split("-");
        final String[] trayek = etSd_ijintrayekx.getText().toString().split("-");
        final String[] usaha = etSd_ijinusahax.getText().toString().split("-");

        class SendData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BpCekCeklistActivity.this,"","Processing...",false,false);
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

                hashMap.put(configuration.KEY_grill1_fisikmuka, (cbGrill1_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_grill2_fisikmuka, (cbGrill2_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampu1_fisikmuka, (cbLampu1_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampu2_fisikmuka, (cbLampu2_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampusen1_fisikmuka, (cbLampusen1_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampusen2_fisikmuka, (cbLampusen2_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bamper1_fisikmuka, (cbBamper1_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bamper2_fisikmuka, (cbBamper2_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_emblem1_fisikmuka, (cbEmblem1_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_emblem2_fisikmuka, (cbEmblem2_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_tanduk1_fisikmuka, (cbTanduk1_fisikmukax.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_tanduk2_fisikmuka, (cbTanduk2_fisikmukax.isChecked() == true) ? "true" : "false");

                hashMap.put(configuration.KEY_footstep1_fisikkiri,(cbFootstep1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_footstep2_fisikkiri,(cbFootstep2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pintudpn1_fisikkiri,(cbPintudpn1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pintudpn2_fisikkiri,(cbPintudpn2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pintublk1_fisikkiri,(cbPintublk1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pintublk2_fisikkiri,(cbPintublk2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bamper1_fisikkiri,(cbBamper1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bamper2_fisikkiri,(cbBamper2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_fenderblk1_fisikkiri,(cbFenderblk1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_fenderblk2_fisikkiri,(cbFenderblk2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_spion1_fisikkiri,(cbSpion1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_spion2_fisikkiri,(cbSpion2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_emblem1_fisikkiri,(cbEmblem1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_emblem2_fisikkiri,(cbEmblem2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_banstandard_fisikkiri,(rbBanstandard1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_banradial_fisikkiri,(rbBanradial1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_ban1_fisikkiri,(cbBan1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_ban2_fisikkiri, String.valueOf(spBan2_fisikkirix.getSelectedItemPosition()));

                hashMap.put(configuration.KEY_velgstandard_fisikkiri,(rbVelgstandard_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_velgracing_fisikkiri,(rbVelgracing_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_velg1_fisikkiri,(cbVelg1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_velg2_fisikkiri,(cbVelg2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dop1_fisikkiri,(cbDop1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dop2_fisikkiri,(cbDop2_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dopblk1_fisikkiri,(cbDopBlk1_fisikkirix.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dopblk2_fisikkiri,(cbDopBlk2_fisikkirix.isChecked() == true) ? "true" : "false");

                hashMap.put(configuration.KEY_spoiler1_fisikblkg,(cbSpoiler1_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_spoiler2_fisikblkg,(cbSpoiler2_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampu1_fisikblkg,(cbLampu1_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampu2_fisikblkg,(cbLampu2_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampusen1_fisikblkg,(cbLampusen1_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampusen2_fisikblkg,(cbLampusen2_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bamper1_fisikblkg,(cbBamper1_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bamper2_fisikblkg,(cbBamper2_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_emblem1_fisikblkg,(cbEmblem1_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_emblem2_fisikblkg,(cbEmblem2_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_knalpot1_fisikblkg,(cbKnalpot1_fisikblkgx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_knalpot2_fisikblkg,(cbKnalpot2_fisikblkgx.isChecked() == true) ? "true" : "false");
//
                hashMap.put(configuration.KEY_foot1_fisikkanan,(cbFoot1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_foot2_fisikkanan,(cbFoot2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pintudpn1_fisikkanan,(cbPintudpn1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pintudpn2_fisikkanan,(cbPintudpn2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pintublk1_fisikkanan,(cbPintublk1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pintublk2_fisikkanan,(cbPintublk2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bamper1_fisikkanan,(cbBamper1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bamper2_fisikkanan,(cbBamper2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_fenderblk1_fisikkanan,(cbFenderblk1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_fenderblk2_fisikkanan,(cbFenderblk2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_spion1_fisikkanan,(cbSpion1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_spion2_fisikkanan,(cbSpion2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_emblem1_fisikkanan,(cbEmblem1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_emblem2_fisikkanan,(cbEmblem2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_banstandard_fisikkanan,(rbBanstandard_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_banradial_fisikkanan,(rbBanradial_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_ban1_fisikkanan,(cbBan1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_ban2_fisikkanan,(String.valueOf(spBan2_fisikkananx.getSelectedItemPosition())));
//
                hashMap.put(configuration.KEY_velgstandard_fisikkanan,(rbVelgstandard_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_velgracing_fisikkanan,(rbVelgracing_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_velg1_fisikkanan,(cbVelg1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_velg2_fisikkanan,(cbVelg2_fisikkananx.isChecked() == true) ? "true" : "false");
//
                hashMap.put(configuration.KEY_dop1_fisikkanan,(cbDop1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dop2_fisikkanan,(cbDop2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dopblk1_fisikkanan,(cbDopBlk1_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dopblk2_fisikkanan,(cbDopBlk2_fisikkananx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_kunciktk1_perlengkapan,(cbKunciktk1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_kunciktk2_perlengkapan,(cbKunciktk2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_spion1_perlengkapan,(cbSpion1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_spion2_perlengkapan,(cbSpion2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_jok1_perlengkapan,(cbJok1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_jok2_perlengkapan,(cbJok2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_sarung1_perlengkapan,(cbSarung1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_sarung2_perlengkapan,(cbSarung2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_sandaran1_perlengkapan,(cbSandaran1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_sandaran2_perlengkapan,(cbSandaran2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_karpet1_perlengkapan,(cbKarpet1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_karpet2_perlengkapan,(cbKarpet2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pelindung1_perlengkapan,(cbPelindung1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_pelindung2_perlengkapan,(cbPelindung2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_segitiga1_perlengkapan,(cbSegitiga1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_segitiga2_perlengkapan,(cbSegitiga2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_tool1_perlengkapan,(cbTool1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_tool2_perlengkapan,(cbTool2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_cadangan1_perlengkapan,(cbCadangan1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_cadangan2_perlengkapan,(cbCadangan2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_kunciban1_perlengkapan,(cbKunciban1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_kunciban2_perlengkapan,(cbKunciban2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dongkrak1_perlengkapan,(cbDongkrak1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_dongkrak2_perlengkapan,(cbDongkrak2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_antena1_perlengkapan,(cbAntena1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_antena2_perlengkapan,(cbAntena2_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_airbag1_perlengkapan,(cbAirbag1_perlengkapanx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_airbag2_perlengkapan,(cbAirbag2_perlengkapanx.isChecked() == true) ? "true" : "false");
//
                hashMap.put(configuration.KEY_lampukbt1_listrik,(cbLampukbt1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lampukbt2_listrik,(cbLampukbt2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_wiperkacadpn1_listrik,(cbWiperkacadpn1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_wiperkacadpn2_listrik,(cbWiperkacadpn2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_wiperkacablk1_listrik,(cbWiperkacablk1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_wiperkacablk2_listrik,(cbWiperkacablk2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_klakson1_listrik,(cbKlakson1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_klakson2_listrik,(cbKlakson2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_alarm1_listrik,(cbAlarm1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_alarm2_listrik,(cbAlarm2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_jam1_listrik,(cbJam1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_jam2_listrik,(cbJam2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lighter1_listrik,(cbLighter1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_lighter2_listrik,(cbLighter2_listrikx.isChecked() == true) ? "true" : "false");

                hashMap.put(configuration.KEY_radio_listrik,(rbRadio_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_tape_listrik,(rbTape_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_cd_listrik,(rbCd_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_merk_listrik,(etMerk_listrikx.getText().toString()));

                hashMap.put(configuration.KEY_radio1_listrik,(cbRadio1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_radio2_listrik,(cbRadio2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_powersup1_listrik,(cbPowersup1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_powersup2_listrik,(cbPowersup2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_speaker1_listrik,(cbSpeaker1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_speaker2_listrik,(cbSpeaker2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_ac1_listrik,(cbAc1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_ac2_listrik,(cbAc2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_powerwin1_listrik,(cbPowerwin1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_powerwin2_listrik,(cbPowerwin2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_central1_listrik,(cbCentral1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_central2_listrik,(cbCentral2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_remote1_listrik,(cbRemote1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_remote2_listrik,(cbRemote2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_speedo1_listrik,(cbSpeedo1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_speedo2_listrik,(cbSpeedo2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_odometer1_listrik,(cbOdometer1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_odometer2_listrik,(cbOdometer2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_tacho1_listrik,(cbTacho1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_tacho2_listrik,(cbTacho2_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_accu1_listrik,(cbAccu1_listrikx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_accu2_listrik,(cbAccu2_listrikx.isChecked() == true) ? "true" : "false");

                hashMap.put(configuration.KEY_mesin1_lain,(cbMesin1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_mesin2_lain,(cbMesin2_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_hidraulik1_lain,(cbHidraulik1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_hidraulik2_lain,(cbHidraulik2_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_gardan1_lain,(cbGardan1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_gardan2_lain,(cbGardan2_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_as1_lain,(cbAs1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_as2_lain,(cbAs2_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bak1_lain,(cbBak1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bak2_lain,(cbBak2_lainx.isChecked() == true) ? "true" : "false");

                hashMap.put(configuration.KEY_stnk_lain, stnk[2] + "-" + stnk[1] + "-" + stnk[0]);
                hashMap.put(configuration.KEY_stnk1_lain,cbStnk1_lainx.getText().toString());
                hashMap.put(configuration.KEY_stnk2_lain,cbStnk2_lainx.getText().toString());
                hashMap.put(configuration.KEY_bukukir_lain, bukukir[2] + "-" + bukukir[1] + "-" + bukukir[0]);
                hashMap.put(configuration.KEY_bukukir1_lain,(cbBukukir1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bukukir2_lain,(cbBukukir2_lainx.isChecked() == true) ? "true" : "false");

                hashMap.put(configuration.KEY_trayek_lain, trayek[2] + "-" + trayek[1] + "-" + trayek[0]);
                hashMap.put(configuration.KEY_trayek1_lain,(cbTrayek1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_trayek2_lain,(cbTrayek2_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_usaha_lain, usaha[2] + "-" + usaha[1] + "-" + usaha[0]);
                hashMap.put(configuration.KEY_usaha1_lain,(cbUsaha1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_usaha2_lain,(cbUsaha2_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bukumnl1_lain,(cbBukumnl1_lainx.isChecked() == true) ? "true" : "false");
                hashMap.put(configuration.KEY_bukumnl2_lain,(cbBukumnl2_lainx.isChecked() == true) ? "true" : "false");

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(configuration.URL_UPD_BASTK_BP,hashMap);
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
            Intent i = new Intent(BpCekCeklistActivity.this,BpCekFotoActivity.class);
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
            etSd_stnkx.setText(sdf.format(myCalendar.getTime()));
        }catch (Exception e){

        }
    }
    private void updateBukukir() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etSd_bukukirx.setText(sdf.format(myCalendar.getTime()));
        }catch (Exception e){

        }
    }
    private void updateIjintrayek() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etSd_ijintrayekx.setText(sdf.format(myCalendar.getTime()));
        }catch (Exception e){

        }
    }
    private void updateIjinusaha() {
        try {
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etSd_ijinusahax.setText(sdf.format(myCalendar.getTime()));
        }catch (Exception e){

        }
    }
}
