package com.tunasrent.auctionapps.model;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.bp.BpListActivity;
import com.tunasrent.auctionapps.dispatcher.DisApprovalListActivity;
import com.tunasrent.auctionapps.dispatcher.DisCekBastkListActivity;
import com.tunasrent.auctionapps.dispatcher.DisInputFormActivity;
import com.tunasrent.auctionapps.mobilisasi.MobCekBastkListActivity;
import com.tunasrent.auctionapps.mobilisasi.MobListActivity;
import com.tunasrent.auctionapps.taksasi.TaksasiActivity;
import com.tunasrent.auctionapps.unitout.UnitoutListActivity;
import com.tunasrent.auctionapps.warehouse.WarehouseActivity;
import com.tunasrent.auctionapps.approvalunitout.ApprovalUnitoutListActivity;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> implements AdapterView.OnItemSelectedListener {
    public ArrayList<Menu> item_list;
    String[] jenis = {"-- Pilih Kategori --","RODA 2","RODA 4"};
    String[] jenis_khususmobilisasi = {"-- Pilih Kategori --","RODA 2","RODA 4"};
    String[] jenis_unit = {"-- Pilih Jenis --","SOLD","UNSOLD"};
    Dialog myDialog;

    public MenuAdapter(ArrayList<Menu> arrayList) {
        item_list = arrayList;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu, null);
        MenuAdapter.ViewHolder viewHolder = new MenuAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.nama.setText(item_list.get(position).getNama());
        holder.desc.setText(item_list.get(position).getDesc());

        Context context = holder.gambar.getContext().getApplicationContext();
        Glide.with(context).load(item_list.get(position).getGambar()).into(holder.gambar);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item_list.get(position).getId().equals("1")){
                    ShowPopupDispatcher(v);
                }else if (item_list.get(position).getId().equals("2")){
                    ShowPopupMobilisasi(v);
                }else if (item_list.get(position).getId().equals("3")){
                    ShowPopupBp(v);
//                    Intent i = new Intent(v.getContext(), BpListActivity.class);
//                    v.getContext().startActivity(i);
                }else if (item_list.get(position).getId().equals("4")){
                    ShowPopupTaksasi(v);
//                    Intent i = new Intent(v.getContext(), TaksasiActivity.class);
//                    v.getContext().startActivity(i);
                }else if (item_list.get(position).getId().equals("5")){
                    ShowPopupWarehouse(v);
//                    Intent i = new Intent(v.getContext(), WarehouseActivity.class);
//                    v.getContext().startActivity(i);
                }else if(item_list.get(position).getId().equals("6")){
                    ShowPopupUnitout(v);
                }else if(item_list.get(position).getId().equals("7")){
                    ShowPopupApprovalUnitout(v);
                }

                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void ShowPopupDispatcher(View v) {
        final TextView tvClose;
        final Button btnInput;
        final Button btnApprove;
        final Button btnCekbastk;
        myDialog = new Dialog(v.getContext());

        myDialog.setContentView(R.layout.popup_opsi_dispatcher);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnInput = (Button) myDialog.findViewById(R.id.btn_input);
        btnApprove = (Button) myDialog.findViewById(R.id.btn_approve);
        btnCekbastk = (Button) myDialog.findViewById(R.id.btn_cekbastk);
        Spinner spin = (Spinner) myDialog.findViewById(R.id.sp_list);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item,jenis);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
                   Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
                }else{
                   myDialog.dismiss();
                   Intent i = new Intent(v.getContext(), DisInputFormActivity.class);
                   Bundle b = new Bundle();
                   b.putString("parse_jenis",spin.getSelectedItem().toString());
                   i.putExtras(b);
                   v.getContext().startActivity(i);
                }
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
                    Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
                }else{
                    myDialog.dismiss();
                    Intent i = new Intent(v.getContext(), DisApprovalListActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        btnCekbastk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
                    Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
                }else{
                    myDialog.dismiss();
                    Intent i = new Intent(v.getContext(), DisCekBastkListActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public void ShowPopupBp(View v) {
        final TextView tvClose;
        final Button btnList;
        myDialog = new Dialog(v.getContext());

        myDialog.setContentView(R.layout.popup_opsi_bp);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnList = (Button) myDialog.findViewById(R.id.btn_list);
        Spinner spin = (Spinner) myDialog.findViewById(R.id.sp_list);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item,jenis);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
                    Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
                }else{
                    myDialog.dismiss();
                    Intent i = new Intent(v.getContext(), BpListActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowPopupTaksasi(View v) {
        final TextView tvClose;
        final Button btnList;
        myDialog = new Dialog(v.getContext());

        myDialog.setContentView(R.layout.popup_opsi_taksasi);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnList = (Button) myDialog.findViewById(R.id.btn_list);
        Spinner spin = (Spinner) myDialog.findViewById(R.id.sp_list);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item,jenis_khususmobilisasi);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
                    Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
                }else{
                    myDialog.dismiss();
                    //Intent i = new Intent(v.getContext(), TaksasiListActivity.class);
                    Intent i = new Intent(v.getContext(), TaksasiActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowPopupWarehouse(View v) {
        final TextView tvClose;
        final Button btnList;
        myDialog = new Dialog(v.getContext());

        myDialog.setContentView(R.layout.popup_opsi_warehouse);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnList = (Button) myDialog.findViewById(R.id.btn_list);
        Spinner spin = (Spinner) myDialog.findViewById(R.id.sp_list);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item,jenis_khususmobilisasi);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
                    Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
                }else{
                    myDialog.dismiss();
                    Intent i = new Intent(v.getContext(), WarehouseActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowPopupMobilisasi(View v) {
        final TextView tvClose;
        final Button btnInput;
        final Button btnApprove;
        final Button btnCekbastk;

        myDialog = new Dialog(v.getContext());

        myDialog.setContentView(R.layout.popup_opsi_mobilisasi);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnInput = (Button) myDialog.findViewById(R.id.btn_input);
        btnCekbastk = (Button) myDialog.findViewById(R.id.btn_cekbastk);
        Spinner spin = (Spinner) myDialog.findViewById(R.id.sp_list);

        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item,jenis_khususmobilisasi);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

//        btnInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//                Intent i = new Intent(v.getContext(), MobListActivity.class);
//                v.getContext().startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
                    Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
                } else {
                    myDialog.dismiss();
                    Intent i = new Intent(v.getContext(), MobListActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
//                else if (spin.getSelectedItem().toString() == "RODA 2") {
//                    myDialog.dismiss();
//                    Intent i = new Intent(v.getContext(), MobInputCeklistMotorActivity.class);
//                    Bundle b = new Bundle();
//                    b.putString("parse_jenis",spin.getSelectedItem().toString());
//                    i.putExtras(b);
//                    v.getContext().startActivity(i);
//                } else{
//                    myDialog.dismiss();
//                    Intent i = new Intent(v.getContext(), MobInputCeklistActivity.class);
//                    Bundle b = new Bundle();
//                    b.putString("parse_jenis",spin.getSelectedItem().toString());
//                    i.putExtras(b);
//                    v.getContext().startActivity(i);

                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

//        btnCekbastk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//                Intent i = new Intent(v.getContext(), MobCekBastkListActivity.class);
//                v.getContext().startActivity(i);
////                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });

        btnCekbastk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
                    Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
                }else{
                    myDialog.dismiss();
                    Intent i = new Intent(v.getContext(), MobCekBastkListActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowPopupUnitout(View v) {
        final TextView tvClose;
        final Button btnInput;
        //final Button btnApprove;
        final Button btnCekbastk;

        myDialog = new Dialog(v.getContext());

        myDialog.setContentView(R.layout.popup_opsi_unitout);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnInput = (Button) myDialog.findViewById(R.id.btn_input);
        btnCekbastk = (Button) myDialog.findViewById(R.id.btn_cekbastk);
        Spinner spin = (Spinner) myDialog.findViewById(R.id.sp_list);
        Spinner jenisunit = (Spinner) myDialog.findViewById(R.id.jenisunit);

        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item,jenis_khususmobilisasi);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        jenisunit.setOnItemSelectedListener(this);
        ArrayAdapter bb = new ArrayAdapter(v.getContext(),R.layout.support_simple_spinner_dropdown_item,jenis_unit);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisunit.setAdapter(bb);
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin.setAdapter(aa);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --" && jenisunit.getSelectedItem().toString() == "-- Pilih Jenis --"  ){
                    Toast.makeText(v.getContext(), "Mohon Lengkapi Form !!", Toast.LENGTH_SHORT).show();
                } else {
                    myDialog.dismiss();
                    //Log.d("send","Angga Love Erik dan Keong");
                    Intent i = new Intent(v.getContext(), UnitoutListActivity.class);
                    //Log.d("send","Angga buguru");
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    b.putString("parse_jenisunit",jenisunit.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
            }
        });

//        btnCekbastk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --"){
//                    Toast.makeText(v.getContext(), "Kategori harus dipilih !!", Toast.LENGTH_SHORT).show();
//                }else{
//                    myDialog.dismiss();
//                    Intent i = new Intent(v.getContext(), MobCekBastkListActivity.class);
//                    Bundle b = new Bundle();
//                    b.putString("parse_jenis",spin.getSelectedItem().toString());
//                    i.putExtras(b);
//                    v.getContext().startActivity(i);
//                }
//            }
//        });

        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public void ShowPopupApprovalUnitout(View v) {
        final TextView tvClose;
        final Button btnInput;
        //final Button btnApprove;
        final Button btnCekbastk;

        myDialog = new Dialog(v.getContext());

        myDialog.setContentView(R.layout.popup_opsi_unitout);
        tvClose =(TextView) myDialog.findViewById(R.id.tv_close);
        btnInput = (Button) myDialog.findViewById(R.id.btn_input);
        btnCekbastk = (Button) myDialog.findViewById(R.id.btn_cekbastk);
        Spinner spin = (Spinner) myDialog.findViewById(R.id.sp_list);
        Spinner jenisunit = (Spinner) myDialog.findViewById(R.id.jenisunit);

        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item,jenis_khususmobilisasi);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        jenisunit.setOnItemSelectedListener(this);
        ArrayAdapter bb = new ArrayAdapter(v.getContext(),R.layout.support_simple_spinner_dropdown_item,jenis_unit);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisunit.setAdapter(bb);
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin.setAdapter(aa);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spin.getSelectedItem().toString() == "-- Pilih Kategori --" && jenisunit.getSelectedItem().toString() == "-- Pilih Jenis --"  ){
                    Toast.makeText(v.getContext(), "Mohon Lengkapi Form !!", Toast.LENGTH_SHORT).show();
                } else {
                    myDialog.dismiss();
                    Intent i = new Intent(v.getContext(), ApprovalUnitoutListActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_jenis",spin.getSelectedItem().toString());
                    b.putString("parse_jenisunit",jenisunit.getSelectedItem().toString());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
            }
        });
        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    @Override
    public int getItemCount() {
        return item_list.size();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(view.getContext(), jenis[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView container;
        public TextView nama;
        public TextView desc;
        public ImageView gambar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container_menu);
            nama = (TextView) itemView.findViewById(R.id.tv_name);
            desc = (TextView) itemView.findViewById(R.id.tv_desc);
            gambar = (ImageView) itemView.findViewById(R.id.iv_gambar);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
