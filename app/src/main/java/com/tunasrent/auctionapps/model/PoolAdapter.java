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

public class PoolAdapter extends ArrayAdapter<Pool> {
    private List<Pool> poolListFull;

    public PoolAdapter(@NonNull Context context, @NonNull List<Pool> poolList) {
        super(context, 0, poolList);
        poolListFull = new ArrayList<>(poolList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return poolFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.pool_autocomplete, parent, false
            );
        }
        TextView tvPool_id = convertView.findViewById(R.id.tv_pool_id);
        TextView tvPool_address = convertView.findViewById(R.id.tv_pool_address);

        Pool pool = getItem(position);
        if (pool != null) {
            tvPool_id.setText(((Pool) pool).getPool_id());
            tvPool_address.setText(((Pool) pool).getPool_address());
        }
        return convertView;
    }

    private Filter poolFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Pool> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(poolListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Pool item : poolListFull) {
                    if (item.getPool_address().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Pool) resultValue).getPool_address();
        }
    };
}
