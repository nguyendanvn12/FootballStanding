package com.nguyendan.footballstanding.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.data.model.scorers.Scorer;
import com.nguyendan.footballstanding.data.model.scorers.Scorers;
import com.nguyendan.footballstanding.databinding.ItemScorerBinding;

import java.util.List;

public class ScorersAdapter extends RecyclerView.Adapter<ScorersAdapter.ViewHolder> {
    private Context mcontext;
    private List<Scorer> scorers;
    private ItemScorerBinding binding;
    public void setScorers(List<Scorer> scorers) {
        this.scorers = scorers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mcontext = parent.getContext();
       binding = DataBindingUtil.inflate(LayoutInflater.from(mcontext), R.layout.item_scorer,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding.setScorer(scorers.get(position));
        if(position==0){
            binding.score.setTextSize(42);
            binding.score.setTextColor(Color.RED);
        }
        holder.setIsRecyclable(false);
        binding.pos.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return scorers==null?0:scorers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemScorerBinding binding;
        public ViewHolder(@NonNull ItemScorerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
