package com.nguyendan.footballstanding.view.fragment;

import android.content.Context;
import android.net.Uri;
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
import android.widget.Toast;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.data.model.matches.Match;
import com.nguyendan.footballstanding.databinding.FragmentMatcheBinding;
import com.nguyendan.footballstanding.view.CompetitionActivity;
import com.nguyendan.footballstanding.view.adapter.MatcheAdapter;
import com.nguyendan.footballstanding.viewmodel.DataViewModel;

import java.util.List;

public class MatcheFragment extends Fragment {
    private FragmentMatcheBinding binding;
    private DataViewModel mDataViewModel;
    private MatcheAdapter mMatcheAdapter;
private String compeCode;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_matche, container, false);
        compeCode= getActivity().getIntent().getStringExtra("competitionCode");
        mDataViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        mMatcheAdapter = new MatcheAdapter();
        binding.container.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.container.setAdapter(mMatcheAdapter);
        String compeCode = getActivity().getIntent().getStringExtra("competitionCode");
        mDataViewModel.getListMatch(compeCode,getContext()).observe(getViewLifecycleOwner(), new Observer<List<Match>>() {
            @Override
            public void onChanged(List<Match> matches) {
                mMatcheAdapter.setMatches(matches);
               int currentMatchday =  matches.get(0).getSeason().getCurrentMatchday()-1;
               int pos = 0;
               int i = 1;
               while (true){
                   if(matches.get(i).getMatchday()>currentMatchday){
                       pos = i-1;
                       break;
                   }
                   i++;
               }
               binding.container.scrollToPosition(pos);
            }
        });

        return binding.getRoot();
    }


}
