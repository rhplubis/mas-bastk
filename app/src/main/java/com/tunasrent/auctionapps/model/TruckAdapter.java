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

public class TruckAdapter extends ArrayAdapter<Truck>{
    private List<Truck> truckListFull;

    public TruckAdapter(@NonNull Context context, @NonNull List<Truck> truckList) {
        super(context, 0, truckList);
        truckListFull = new ArrayList<>(truckList);

    }
    @NonNull
    @Override
    public Filter getFilter() {
        return truckFilter;
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

        Truck truck = getItem(position);

        if (truck != null) {
            tvItem_id.setText(((Truck) truck).getItem_id());
            tvItem_name.setText(((Truck) truck).getItem_name());
            tvItem_type_id.setText(((Truck) truck).getItem_type_id());
            tvItem_type_name.setText(((Truck) truck).getItem_type_name());
        }

        return convertView;
    }

    private Filter truckFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Truck> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(truckListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Truck item : truckListFull) {
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
            return ((Truck) resultValue).getItem_type_name();
        }
    };

}
