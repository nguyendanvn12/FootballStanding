package com.nguyendan.footballstanding.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;

import com.nguyendan.footballstanding.R;
import com.nguyendan.footballstanding.data.model.Compe;
import com.nguyendan.footballstanding.databinding.ActivityMainBinding;
import com.nguyendan.footballstanding.view.adapter.PagerAdapter;
import com.nguyendan.footballstanding.view.fragment.CountryFragment;
import com.nguyendan.footballstanding.viewmodel.ShareModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private PagerAdapter adapter;
    private ActivityMainBinding binding;
    private AnimationDrawable anim;
    private List<Fragment> fragmentList;
    private ShareModel shareModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
         shareModel = new ViewModelProvider(this).get(ShareModel.class);
        FragmentManager manager = getSupportFragmentManager();
        initdata();
        adapter = new PagerAdapter(manager,fragmentList);
        binding.viewpager.setAdapter(adapter);
        binding.tabs.setupWithViewPager(binding.viewpager);
        setIconTitle();
        anim = (AnimationDrawable) binding.container.getBackground();
        anim.setEnterFadeDuration(2000);
        anim.setExitFadeDuration(1000);
        anim.start();
    }

    private void initdata() {
        fragmentList = new ArrayList<>();
        ArrayList<Compe> compesUK = new ArrayList<>();
        ArrayList<Compe> compesGer = new ArrayList<>();
        ArrayList<Compe> compesIta = new ArrayList<>();
        ArrayList<Compe> compesFra = new ArrayList<>();
        compesUK.add(new Compe("PL","Premier League"));
        compesUK.add(new Compe("ELC","EFL Championship"));
        compesFra.add(new Compe("FL1","Ligue 1"));
        compesGer.add(new Compe("BL1","Bundesliga"));
        compesIta.add(new Compe("SA","Serie A"));
        shareModel.selectCompe(compesUK);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        shareModel.selectCompe(compesUK);
                        break;
                    case 1:
                        shareModel.selectCompe(compesGer);
                        break;
                    case 2:
                        shareModel.selectCompe(compesIta);
                        break;
                    case 3:
                        shareModel.selectCompe(compesFra);
                        break;
                }
                Log.d("cccc",""+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentList.add(new CountryFragment());
        fragmentList.add(new CountryFragment());
        fragmentList.add(new CountryFragment());
        fragmentList.add(new CountryFragment());
    }

    private void setIconTitle() {
        binding.tabs.getTabAt(0).setIcon(R.drawable.ic_uk);
        binding.tabs.getTabAt(1).setIcon(R.drawable.ic_germany);
        binding.tabs.getTabAt(2).setIcon(R.drawable.ic_italy);
        binding.tabs.getTabAt(3).setIcon(R.drawable.ic_france);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && !anim.isRunning())
            anim.stop();
    }
}
