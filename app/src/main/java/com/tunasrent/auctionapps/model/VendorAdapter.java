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

public class VendorAdapter extends ArrayAdapter<Vendor> {
    private List<Vendor> vendorListFull;

    public VendorAdapter(@NonNull Context context, @NonNull List<Vendor> vendorList) {
        super(context, 0, vendorList);
        vendorListFull = new ArrayList<>(vendorList);

    }

    @NonNull
    @Override
    public Filter getFilter() {
        return vendorFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.vendor_autocomplete, parent, false
            );
        }

        TextView tvVendor_id = convertView.findViewById(R.id.tv_vendor_id);
        TextView tvVendor = convertView.findViewById(R.id.tv_vendor);

        Vendor vendor = getItem(position);

        if (vendor != null) {
            tvVendor_id.setText(((Vendor) vendor).getVendor_id());
            tvVendor.setText(((Vendor) vendor).getVendor());
        }

        return convertView;
    }

    private Filter vendorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Vendor> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(vendorListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Vendor item : vendorListFull) {
                    if (item.getVendor().toLowerCase().contains(filterPattern)) {
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
            return ((Vendor) resultValue).getVendor();
        }
    };
}
