package com.nguyendan.footballstanding.view.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.data.model.matches.Match;
import com.nguyendan.footballstanding.databinding.ItemMatchesBinding;

import java.util.List;
import java.util.regex.Matcher;

public class MatcheAdapter extends RecyclerView.Adapter<MatcheAdapter.ViewHolder> {
    private List<Match> matches;
    private ItemMatchesBinding binding;
    public void setMatches(List<Match> matches) {
        this.matches = matches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_matches,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding.setMatch(matches.get(position));
        if(matches.get(position).getStatus().equals("SCHEDULED")){
            binding.mdate.setTextSize(14);
            binding.mdate.setTypeface(Typeface.DEFAULT_BOLD);
            binding.mtime.setTextSize(14);
            binding.mtime.setTypeface(Typeface.DEFAULT_BOLD);
        }
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return matches==null?0:matches.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemMatchesBinding binding;
        public ViewHolder(@NonNull ItemMatchesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
