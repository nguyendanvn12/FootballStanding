package com.nguyendan.footballstanding.view.adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.SvgUtils;
import com.nguyendan.footballstanding.data.model.standing.Table;
import com.nguyendan.footballstanding.databinding.ItemStandingBinding;
import com.pixplicity.sharp.SharpPicture;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class StandingApdater extends RecyclerView.Adapter<StandingApdater.ViewHolder> {
    private ItemStandingBinding binding;
    private List<Table> tables;
    private Context context;
    public void setTables(List<Table> tables) {
        this.tables = tables;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_standing, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Table table = tables.get(position);
        holder.setIsRecyclable(false);
        binding.setTable(table);
        String s = table.getTeam().getCrestUrl();
       if(s!=null&&s.contains(".svg")){
           try {
               SvgUtils.fetchSvg(context,s,binding.teamIcon);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else if(s!=null&&s.contains(".png")){
           Picasso.get().load(s).into(binding.teamIcon);
       }else {
           Picasso.get().load(R.drawable.default_logo).into(binding.teamIcon);
       }

    }


    @Override
    public int getItemCount() {
            return tables == null ? 0 : tables.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemStandingBinding binding;

        public ViewHolder(@NonNull ItemStandingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
