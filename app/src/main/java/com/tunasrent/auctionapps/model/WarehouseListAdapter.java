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
import com.tunasrent.auctionapps.taksasi.TaksasiCekFormActivity;
import com.tunasrent.auctionapps.warehouse.WarehouseCekFormActivity;

import java.util.ArrayList;

public class WarehouseListAdapter extends RecyclerView.Adapter<WarehouseListAdapter.ViewHolder> {
    public ArrayList<WarehouseList> item_list;
    public WarehouseListAdapter(ArrayList<WarehouseList> arrayList) {
        item_list = arrayList;
    }
    @NonNull
    @Override
    public WarehouseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_warehouse, null);
        WarehouseListAdapter.ViewHolder viewHolder = new WarehouseListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseListAdapter.ViewHolder holder, final int position) {
        holder.date.setText(item_list.get(position).getDate());
        holder.nopol.setText(item_list.get(position).getNopol());
        holder.type.setText(item_list.get(position).getType());
        holder.vendor.setText(item_list.get(position).getVendor());

//        if (item_list.get(position).getStatus().equals("1") || item_list.get(position).getStatus().equals("2")){
//            holder.status.setText("     INPUT     ");
//            holder.status.setBackgroundResource(R.drawable.background_gradient_orange);
//        }else if (item_list.get(position).getStatus().equals("3")){
//            holder.status.setText("     PROSES     ");
//            holder.status.setBackgroundResource(R.drawable.background_gradient_blue);
//        }
        holder.status.setText("     TAKSASI     ");
        holder.status.setBackgroundResource(R.drawable.background_gradient_blue);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), WarehouseCekFormActivity.class);
                Bundle b = new Bundle();
                b.putString("parse_nopol",item_list.get(position).getNopol());
                i.putExtras(b);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }
    public void setFilter(ArrayList<WarehouseList> newList) {
        item_list = new ArrayList<>();
        item_list.addAll(newList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView container;
        public TextView date;
        public TextView nopol;
        public TextView type;
        public TextView vendor;
        public TextView status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            date = (TextView) itemView.findViewById(R.id.tv_date);
            nopol = (TextView) itemView.findViewById(R.id.tv_nopol);
            type = (TextView) itemView.findViewById(R.id.tv_type);
            vendor = (TextView) itemView.findViewById(R.id.tv_vendor);
            status = (TextView) itemView.findViewById(R.id.tv_status);

        }
    }
}
