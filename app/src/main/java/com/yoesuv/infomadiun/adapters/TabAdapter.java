package com.yoesuv.infomadiun.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yoesuv.infomadiun.R;
import com.yoesuv.infomadiun.fragment.ChildFragmentChangelog;
import com.yoesuv.infomadiun.fragment.ChildFragmentInfo;
import com.yoesuv.infomadiun.fragment.ChildFragmentLibrary;
import com.yoesuv.infomadiun.fragment.ChildFragmentThanks;

public class TabAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public TabAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        this.titles = ctx.getResources().getStringArray(R.array.tab_titles);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new ChildFragmentInfo();
        }else if(position==1){
            return new ChildFragmentChangelog();
        }else if(position==2){
            return new ChildFragmentThanks();
        }else{
            return new ChildFragmentLibrary();
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
