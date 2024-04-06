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

public class MotorAdapter extends ArrayAdapter<Motor> {
    private List<Motor> motorListFull;

    public MotorAdapter(@NonNull Context context, @NonNull List<Motor> motorList) {
        super(context, 0, motorList);
        motorListFull = new ArrayList<>(motorList);

    }
    @NonNull
    @Override
    public Filter getFilter() {
        return motorFilter;
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

        Motor motor = getItem(position);

        if (motor != null) {
            tvItem_id.setText(((Motor) motor).getItem_id());
            tvItem_name.setText(((Motor) motor).getItem_name());
            tvItem_type_id.setText(((Motor) motor).getItem_type_id());
            tvItem_type_name.setText(((Motor) motor).getItem_type_name());
        }

        return convertView;
    }

    private Filter motorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Motor> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(motorListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Motor item : motorListFull) {
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
            return ((Motor) resultValue).getItem_type_name();
        }
    };
}
