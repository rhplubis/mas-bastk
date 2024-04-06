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

public class MerkAdapter extends ArrayAdapter<Merk> {
    private List<Merk> merkListFull;

    public MerkAdapter(@NonNull Context context, @NonNull List<Merk> merkList) {
        super(context, 0, merkList);
        merkListFull = new ArrayList<>(merkList);

    }

    @NonNull
    @Override
    public Filter getFilter() {
        return merkFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.merk_autocomplete, parent, false
            );
        }

        TextView tvItem_id = convertView.findViewById(R.id.tv_item_id);
        TextView tvItem_name = convertView.findViewById(R.id.tv_item_name);
        TextView tvItem_type_id = convertView.findViewById(R.id.tv_item_type_id);
        TextView tvItem_type_name = convertView.findViewById(R.id.tv_item_type_name);

        Merk merk = getItem(position);

        if (merk != null) {
            tvItem_id.setText(((Merk) merk).getItem_id());
            tvItem_name.setText(((Merk) merk).getItem_name());
            tvItem_type_id.setText(((Merk) merk).getItem_type_id());
            tvItem_type_name.setText(((Merk) merk).getItem_type_name());
        }

        return convertView;
    }

    private Filter merkFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Merk> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(merkListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Merk item : merkListFull) {
                    if (item.getItem_type_name().toLowerCase().contains(filterPattern)) {
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
            return ((Merk) resultValue).getItem_type_name();
        }
    };
}
