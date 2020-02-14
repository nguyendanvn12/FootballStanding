package com.nguyendan.footballstanding.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.data.model.scorers.Scorer;
import com.nguyendan.footballstanding.databinding.FragmentScorersBinding;
import com.nguyendan.footballstanding.view.adapter.ScorersAdapter;
import com.nguyendan.footballstanding.viewmodel.DataViewModel;
import com.nguyendan.footballstanding.viewmodel.ShareModel;

import java.util.List;


public class ScorersFragment extends Fragment {
    private FragmentScorersBinding binding;
    private ScorersAdapter mScorersAdapter;
    private DataViewModel mViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scorers, container, false);
        mScorersAdapter = new ScorersAdapter();
        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        String compeCode= getActivity().getIntent().getStringExtra("competitionCode");
        binding.setLifecycleOwner(this);
        binding.container.setAdapter(mScorersAdapter);
        binding.container.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getListScorer(compeCode,getContext()).observe(getViewLifecycleOwner(), new Observer<List<Scorer>>() {
            @Override
            public void onChanged(List<Scorer> scorers) {
                mScorersAdapter.setScorers(scorers);
            }
        });
        return binding.getRoot();
    }
}
