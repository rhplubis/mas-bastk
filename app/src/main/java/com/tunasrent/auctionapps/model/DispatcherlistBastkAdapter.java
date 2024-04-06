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
import com.tunasrent.auctionapps.dispatcher.DisApprovalFormActivity;
import com.tunasrent.auctionapps.dispatcher.DisCekBastkCeklistActivity;
import com.tunasrent.auctionapps.mobilisasi.MobCekBastkCeklistActivity;

import java.util.ArrayList;

public class DispatcherlistBastkAdapter extends RecyclerView.Adapter<DispatcherlistBastkAdapter.ViewHolder> {
    public ArrayList<DispatcherlistBastk> item_list;
    public DispatcherlistBastkAdapter(ArrayList<DispatcherlistBastk> arrayList) {
        item_list = arrayList;
    }

    @NonNull
    @Override
    public DispatcherlistBastkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_approval, null);
        DispatcherlistBastkAdapter.ViewHolder viewHolder = new DispatcherlistBastkAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DispatcherlistBastkAdapter.ViewHolder holder, final int position) {
        holder.date.setText(item_list.get(position).getDate());
        holder.nopol.setText(item_list.get(position).getNopol());
        holder.type.setText(item_list.get(position).getType());
        holder.vendor.setText(item_list.get(position).getVendor());

        if (item_list.get(position).getStatus().equals("1")){
            holder.status.setText("     DISPATCHER     ");
            holder.status.setBackgroundResource(R.drawable.background_gradient_orange);
        }else if (item_list.get(position).getStatus().equals("2")){
            holder.status.setText("     BISNIS PARTNER     ");
            holder.status.setBackgroundResource(R.drawable.background_gradient_blue);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item_list.get(position).getFlag().equals("2")){
                    Intent i = new Intent(v.getContext(), MobCekBastkCeklistActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_nopol",item_list.get(position).getNopol());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }else {
                    Intent i = new Intent(v.getContext(), DisCekBastkCeklistActivity.class);
                    Bundle b = new Bundle();
                    b.putString("parse_nopol",item_list.get(position).getNopol());
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }
    public void setFilter(ArrayList<DispatcherlistBastk> newList) {
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
