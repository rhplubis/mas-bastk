package com.tunasrent.auctionapps.dispatcher;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.taksasi.TaksasiCekFotoActivity;
import com.tunasrent.auctionapps.taksasi.TaksasiCekSignActivity;
import com.tunasrent.auctionapps.util.UserSessionManager;

import java.util.HashMap;

public class DisApprovalFotoActivity extends AppCompatActivity {
    private static final String TAG = TaksasiCekFotoActivity.class.getSimpleName();

    Toolbar toolbar;
    UserSessionManager session;

    EditText etPicpenyimpanan;
    EditText etPicPengiriman;

    ImageView ivFoto1;
    ImageView ivFoto2;
    ImageView ivFoto3;
    ImageView ivFoto4;
    ImageView ivFoto5;
    ImageView ivFoto6;
    ImageView ivFoto7;
    ImageView ivFoto8;
    ImageView ivFoto9;
    ImageView ivFoto10;
    ImageView ivFoto11;
    ImageView ivFoto12;
    ImageView ivFoto13;
    ImageView ivFoto14;


    Button btnNextnote;
    String _name, _fullname, _appid, _ccode, _token, _group;
    String var_picPenarikanDiterima;
    String var_picPenarikan;
    String var_picPenyimpanan;
    String var_picPengiriman;
    String var_picPenjualan;
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
    String nopol;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_approval_foto);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Foto Unit");
        toolbar.setTitleTextColor(Color.WHITE);

        ivFoto1 = findViewById(R.id.iv_foto1);
        ivFoto2 = findViewById(R.id.iv_foto2);
        ivFoto3 = findViewById(R.id.iv_foto3);
        ivFoto4 = findViewById(R.id.iv_foto4);
        ivFoto5 = findViewById(R.id.iv_foto5);
        ivFoto6 = findViewById(R.id.iv_foto6);
        ivFoto7 = findViewById(R.id.iv_foto7);
        ivFoto8 = findViewById(R.id.iv_foto8);
        ivFoto9 = findViewById(R.id.iv_foto9);
        ivFoto10 = findViewById(R.id.iv_foto10);
        ivFoto11 = findViewById(R.id.iv_foto11);
        ivFoto12 = findViewById(R.id.iv_foto12);
        ivFoto13 = findViewById(R.id.iv_foto13);
        ivFoto14 = findViewById(R.id.iv_foto14);
        btnNextnote = findViewById(R.id.btn_nextnote);
        etPicpenyimpanan = findViewById(R.id.et_pic_penyimpanan);
        etPicPengiriman = findViewById(R.id.et_pic_pengiriman);


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
            var_picPenarikanDiterima= b.getString("picpenarikan_diterima");
            var_picPenarikan= b.getString("picpenarikan");
            var_picPenyimpanan= b.getString("picpenyimpanan");
            var_picPengiriman= b.getString("picpengiriman");
            var_picPenjualan= b.getString("picpenjualan");
            var_note_picPenyimpanan= b.getString("note_picpenyimpanan");
            var_note_picPengiriman= b.getString("note_picpengiriman");

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

            try {
                if (!var_img1.equals("null")){
                    ivFoto1.setBackground(null);
                    Glide.with(this).load(var_img1).into(ivFoto1);
                }
                if (!var_img2.equals("null")){
                    ivFoto2.setBackground(null);
                    Glide.with(this).load(var_img2).into(ivFoto2);
                }
                if (!var_img3.equals("null")){
                    ivFoto3.setBackground(null);
                    Glide.with(this).load(var_img3).into(ivFoto3);
                }
                if (!var_img4.equals("null")){
                    ivFoto4.setBackground(null);
                    Glide.with(this).load(var_img4).into(ivFoto4);
                }
                if (!var_img5.equals("null")){
                    ivFoto5.setBackground(null);
                    Glide.with(this).load(var_img5).into(ivFoto5);
                }
                if (!var_img6.equals("null")){
                    ivFoto6.setBackground(null);
                    Glide.with(this).load(var_img6).into(ivFoto6);
                }
                if (!var_img7.equals("null")){
                    ivFoto7.setBackground(null);
                    Glide.with(this).load(var_img7).into(ivFoto7);
                }
                if (!var_img8.equals("null")){
                    ivFoto8.setBackground(null);
                    Glide.with(this).load(var_img8).into(ivFoto8);
                }
                if (!var_img9.equals("null")){
                    ivFoto9.setBackground(null);
                    Glide.with(this).load(var_img9).into(ivFoto9);
                }
                if (!var_img10.equals("null")){
                    ivFoto10.setBackground(null);
                    Glide.with(this).load(var_img10).into(ivFoto10);
                }
                if (!var_img11.equals("null")){
                    ivFoto11.setBackground(null);
                    Glide.with(this).load(var_img11).into(ivFoto11);
                }
                if (!var_img12.equals("null")){
                    ivFoto12.setBackground(null);
                    Glide.with(this).load(var_img12).into(ivFoto12);
                }
                if (!var_img13.equals("null")){
                    ivFoto13.setBackground(null);
                    Glide.with(this).load(var_img13).into(ivFoto13);
                }
                if (!var_img14.equals("null")){
                    ivFoto14.setBackground(null);
                    Glide.with(this).load(var_img14).into(ivFoto14);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            etPicpenyimpanan.setText(var_note_picPenyimpanan);
            etPicPengiriman.setText(var_note_picPengiriman);
        }
        myDialog = new Dialog(this);

        ivFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img1.equals("null") || var_img1.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img1).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img2.equals("null") || var_img2.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img2).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img3.equals("null") || var_img3.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img3).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img4.equals("null") || var_img4.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img4).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }

            }
        });
        ivFoto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img5.equals("null") || var_img5.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img5).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }

            }
        });
        ivFoto6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img6.equals("null") || var_img6.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img6).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img7.equals("null") || var_img7.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img7).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img8.equals("null") || var_img8.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img8).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img9.equals("null") || var_img9.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img9).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img10.equals("null") || var_img10.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img10).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img11.equals("null") || var_img11.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img11).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img12.equals("null") || var_img12.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img12).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img13.equals("null") || var_img13.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img13).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });
        ivFoto14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (var_img14.equals("null") || var_img14.equals("")){
                    Toast.makeText(DisApprovalFotoActivity.this,"Tidak ada foto",Toast.LENGTH_SHORT).show();
                }else {
                    final TextView tvClose;
                    final ZoomageView ivZoom;
                    myDialog.setContentView(R.layout.popup_zoom);
                    tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
                    ivZoom = (ZoomageView) myDialog.findViewById(R.id.iv_zoom);

                    Glide.with(DisApprovalFotoActivity.this).load(var_img14).into(ivZoom);

                    tvClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }
        });


        btnNextnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisApprovalFotoActivity.this, DisApprovalSignActivity.class);
                Bundle b = new Bundle();
                b.putString("picpenyimpanan",var_picPenyimpanan);
                b.putString("picpengiriman",var_picPengiriman);
                b.putString("nopol",nopol);
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
