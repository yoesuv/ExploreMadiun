package com.yoesuv.infomadiun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.yoesuv.infomadiun.R;
import com.yoesuv.infomadiun.utils.ActivityHelper;

public class HomeFragment extends Fragment {

    private View v;
    private TextView tvHome;
    private AppCompatButton btnStart;
    private Drawer drawer;

    public void setDrawer(Drawer d){
        this.drawer = d;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        tvHome = (TextView) v.findViewById(R.id.textView_home);
        tvHome.setText(ActivityHelper.fromHtml(v.getResources().getString(R.string.home_text)));

        btnStart = (AppCompatButton) v.findViewById(R.id.buttonStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer();
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
