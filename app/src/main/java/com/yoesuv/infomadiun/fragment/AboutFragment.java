package com.yoesuv.infomadiun.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoesuv.infomadiun.R;
import com.yoesuv.infomadiun.adapters.TabAdapter;
import com.yoesuv.infomadiun.utils.ZoomOutPageTransformer;

public class AboutFragment extends Fragment {

    private View v;
    private ViewPager vPager;
    private TabLayout tabLayout;


    public static AboutFragment getInstance(){
        AboutFragment af = new AboutFragment();
        return af;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_about, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        vPager = (ViewPager) v.findViewById(R.id.viewPager);

        vPager.setAdapter(new TabAdapter(v.getContext(), getChildFragmentManager()));
        vPager.setPageTransformer(true, new ZoomOutPageTransformer());
        tabLayout.setupWithViewPager(vPager);

        return v;
    }
}
