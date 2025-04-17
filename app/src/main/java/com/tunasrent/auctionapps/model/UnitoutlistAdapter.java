package com.tunasrent.auctionapps.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunasrent.auctionapps.R;
import com.tunasrent.auctionapps.unitout.UnitoutCekFormActivity;

import java.util.ArrayList;

public class UnitoutlistAdapter extends RecyclerView.Adapter<UnitoutlistAdapter.ViewHolder> {
    public ArrayList<Unitoutlist> item_list;
    public UnitoutlistAdapter(ArrayList<Unitoutlist> arrayList) {
        item_list = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_unitout_checker, null);
        UnitoutlistAdapter.ViewHolder viewHolder = new UnitoutlistAdapter.ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String[] sub = item_list.get(position).getDate().split(" ");
//        holder.date.setText(sub[0]);
        //item_list item = item_list.get(position);
        int currentPosition = holder.getAdapterPosition();
        String jenisunit = item_list.get(currentPosition).getJenisunit();

        Unitoutlist item = item_list.get(currentPosition);
        //holder.vhccolor.setText(item.getVhccolor());
     //   holder.vhcyear.setText(item.getVhcyear());
    //    holder.procby.setText(item.getBy());
       // holder.date.setText(item.getDate());
        holder.nopol.setText(item.getNopol());
        holder.type.setText(item.getType());
        holder.vendor.setText(item.getVendor());


//        holder.date.setText(item_list.get(position).getDate());
//        holder.nopol.setText(item_list.get(position).getNopol());
//        holder.type.setText(item_list.get(position).getType());
//        holder.vendor.setText(item_list.get(position).getVendor());

        if ("SOLD".equals(jenisunit)) {
            holder.jenisunit.setText("     SOLD     ");
            holder.jenisunit.setBackgroundResource(R.drawable.background_gradient_orange);
        } else {
            holder.jenisunit.setText("     UNSOLD     ");
            holder.jenisunit.setBackgroundResource(R.drawable.background_gradient_blue);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UnitoutCekFormActivity.class);
                Bundle b = new Bundle();
                b.putString("parse_nopol",item_list.get(currentPosition).getNopol());
                i.putExtras(b);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }
    public void setFilter(ArrayList<Unitoutlist> newList) {
        item_list = new ArrayList<>();
        item_list.addAll(newList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView container;
       // public TextView vhccolor;
       // public TextView vhcyear;
        //public TextView procby;
        public TextView nopol;
        public TextView type;
        public TextView vendor;
       // public TextView status;
        public TextView jenisunit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
           // vhccolor = itemView.findViewById(R.id.tv_color);
            //vhcyear =  itemView.findViewById(R.id.tv_year);
            nopol =  itemView.findViewById(R.id.tv_nopol);
            type =  itemView.findViewById(R.id.tv_type);
            vendor =  itemView.findViewById(R.id.tv_vendor);
            //status = itemView.findViewById(R.id.tv_status);
            jenisunit = itemView.findViewById(R.id.tv_jenisunit);

        }
    }
}
