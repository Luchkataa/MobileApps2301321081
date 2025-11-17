package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BrewListAdapter extends RecyclerView.Adapter<BrewListAdapter.BrewViewHolder> {

    private final LayoutInflater mInflater;
    private List<Brew> mBrews;

    public BrewListAdapter(View view) {
        mInflater = LayoutInflater.from(view.getContext());
    }

    @NonNull
    @Override
    public BrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new BrewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BrewViewHolder holder, int position) {
        if (mBrews != null) {
            Brew current = mBrews.get(position);
            holder.textViewName.setText(current.getName());
            holder.textViewNotes.setText(current.getNotes());
            holder.textViewRating.setText(String.valueOf(current.getRating()));
        } else {
            holder.textViewName.setText("Няма запис");
        }
    }

    @Override
    public int getItemCount() {
        if (mBrews != null)
            return mBrews.size();
        else return 0;
    }

    public void setBrews(List<Brew> brews) {
        mBrews = brews;
        notifyDataSetChanged();
    }

    static class BrewViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewNotes;
        private final TextView textViewRating;

        private BrewViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewNotes = itemView.findViewById(R.id.textViewNotes);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}