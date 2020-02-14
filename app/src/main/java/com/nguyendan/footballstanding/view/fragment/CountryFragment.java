package com.nguyendan.footballstanding.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.data.model.Compe;
import com.nguyendan.footballstanding.databinding.FragmentFranBinding;
import com.nguyendan.footballstanding.view.CompetitionActivity;
import com.nguyendan.footballstanding.view.adapter.Handler;
import com.nguyendan.footballstanding.view.adapter.RecyclerViewAdapter;
import com.nguyendan.footballstanding.viewmodel.ShareModel;

import java.util.ArrayList;


public class CountryFragment extends Fragment {
    private RecyclerView mContainer;
    private ArrayList<Compe> list;
    private ShareModel shareModel;
    private RecyclerViewAdapter adapter;
    private Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentFranBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_fran,container,false);
        shareModel = new ViewModelProvider(requireActivity()).get(ShareModel.class);
        list = shareModel.getSelected().getValue();
        shareModel.getSelected().observe(getViewLifecycleOwner(), new Observer<ArrayList<Compe>>() {
            @Override
            public void onChanged(ArrayList<Compe> compes) {
                list = compes;
                adapter.setList(compes);
            }
        });


        mContainer = binding.rv;
        mContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewAdapter();
        handler = new Handler() {
            @Override
            public void onClick(int pos) {
                //todo start Activity
                Intent intent = new Intent(getActivity(), CompetitionActivity.class);
                intent.putExtra("competitionCode",list.get(pos).getCode());
                getActivity().startActivity(intent);
            }
        };
        adapter.setHandler(handler);
        mContainer.setAdapter(adapter);
        adapter.setList(list);
        return binding.getRoot();

    }


}
