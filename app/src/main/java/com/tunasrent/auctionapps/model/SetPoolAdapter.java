package com.tunasrent.auctionapps.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.tunasrent.auctionapps.R;

import java.util.ArrayList;
import java.util.List;

public class SetPoolAdapter extends ArrayAdapter<SetPool> {
    private LayoutInflater inflater;
    private List<SetPool> poolListFull;
    public SetPoolAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        inflater = LayoutInflater.from(context);
    }


    public SetPoolAdapter(@NonNull Context context, @NonNull List<SetPool> poolList) {
        super(context, 0, poolList);
        poolListFull = new ArrayList<>(poolList);
    }
//
//    @NonNull
//    @Override
//    public Filter getFilter() {
//        return poolFilter;
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_text_layout, parent, false
            );
        }
        TextView tvPool_id = convertView.findViewById(R.id.tv_pool_id);
        TextView tvPool_address = convertView.findViewById(R.id.tv_pool_address);

        SetPool pool = getItem(position);
        if (pool != null) {
            tvPool_id.setText(((SetPool) pool).getPool_id());
            tvPool_address.setText(((SetPool) pool).getPool_address());
        }
        return convertView;
    }
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder2 holder;

        if(convertView == null){
            holder = new ViewHolder2();
            convertView = inflater.inflate(R.layout.spinner_text_layout, null);
            holder.text1 = (TextView)convertView.findViewById(R.id.tv_pool_id);
            holder.text2 = (TextView)convertView.findViewById(R.id.tv_pool_address);
            convertView.setTag(R.layout.spinner_text_layout, holder);
        } else{
            holder = (ViewHolder2)convertView.getTag(R.layout.spinner_text_layout);
        }

        holder.text1.setText("Position: " );
        holder.text2.setText(position);

        return convertView;
    }

    static class ViewHolder{
        TextView text1;
        TextView text2;
    }

    static class ViewHolder2{
        TextView text1;
        TextView text2;
    }

//    private Filter poolFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults results = new FilterResults();
//            List<SetPool> suggestions = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0) {
//                suggestions.addAll(poolListFull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (SetPool item : poolListFull) {
//                    if (item.getPool_address().toLowerCase().contains(filterPattern)) {
//                        suggestions.add(item);
//                    }
//                }
//            }
//
//            results.values = suggestions;
//            results.count = suggestions.size();
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            clear();
//            addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public CharSequence convertResultToString(Object resultValue) {
//            return ((SetPool) resultValue).getPool_address();
//        }
//    };
}
