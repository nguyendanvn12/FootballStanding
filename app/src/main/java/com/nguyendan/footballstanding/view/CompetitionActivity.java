package com.nguyendan.footballstanding.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.databinding.ActivityCompetitionBinding;
import com.nguyendan.footballstanding.databinding.ActivityMainBinding;
import com.nguyendan.footballstanding.view.adapter.PagerAdapter;
import com.nguyendan.footballstanding.view.fragment.MatcheFragment;
import com.nguyendan.footballstanding.view.fragment.ScorersFragment;
import com.nguyendan.footballstanding.view.fragment.StandingFragment;

import java.util.ArrayList;
import java.util.List;

public class CompetitionActivity extends AppCompatActivity {
    private PagerAdapter adapter;
    private ActivityCompetitionBinding binding;
    private AnimationDrawable anim;
    private List<Fragment> fragmentList;
    private String compeCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_competition);
        initData();
        FragmentManager manager = getSupportFragmentManager();
        adapter = new PagerAdapter(manager, fragmentList);
        binding.viewpager.setAdapter(adapter);
        anim = (AnimationDrawable) binding.container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new StandingFragment());
        fragmentList.add(new MatcheFragment());
        fragmentList.add(new ScorersFragment());
    }

}
