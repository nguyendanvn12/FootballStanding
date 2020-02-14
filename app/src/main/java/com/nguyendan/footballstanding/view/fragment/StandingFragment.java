package com.nguyendan.footballstanding.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.data.model.standing.Table;
import com.nguyendan.footballstanding.databinding.FragmentStandingBinding;
import com.nguyendan.footballstanding.view.adapter.StandingApdater;
import com.nguyendan.footballstanding.viewmodel.DataViewModel;

import java.util.List;


public class StandingFragment extends Fragment {
    private FragmentStandingBinding binding;
    private StandingApdater mApdater;
    private DataViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_standing,container,false);
        mViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        mApdater = new StandingApdater();

        binding.container.setAdapter(mApdater);
        binding.container.setLayoutManager(new LinearLayoutManager(getContext()));
        String compeCode= getActivity().getIntent().getStringExtra("competitionCode");
        binding.setLifecycleOwner(this);
        mViewModel.getListTable(compeCode,getContext()).observe(getViewLifecycleOwner(), new Observer<List<Table>>() {
            @Override
            public void onChanged(List<Table> tables) {
                if(tables.size()>0){
                    mApdater.setTables(tables);
                }
            }
        });
        return binding.getRoot();
    }
}
