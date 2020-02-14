package com.nguyendan.footballstanding.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.data.model.Compe;
import com.nguyendan.footballstanding.databinding.CompetitionsItemBinding;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<Compe> list;
    private Handler handler;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    public void setList(ArrayList<Compe> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CompetitionsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.competitions_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Compe compe = list.get(position);
        holder.binding.setCompetition(compe);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CompetitionsItemBinding binding;

        public MyViewHolder(@NonNull CompetitionsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
