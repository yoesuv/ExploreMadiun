package com.yoesuv.infomadiun.fragment;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.yoesuv.infomadiun.R;

public class HomeFragment extends Fragment {

    private View v;
    private TextView tvHome;
    //private Button btnStart;
    private AppCompatButton btnStart;
    private Drawer drawer;

    public void setDrawer(Drawer d){
        this.drawer = d;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_home, container, false);
        tvHome = (TextView) v.findViewById(R.id.textView_home);
        tvHome.setText(Html.fromHtml(v.getResources().getString(R.string.home_text)));

        btnStart = (AppCompatButton) v.findViewById(R.id.buttonStart);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            btnStart.setSupportBackgroundTintList(ContextCompat.getColorStateList(v.getContext(), R.color.colorPrimary));
            btnStart.setCompoundDrawablePadding(20);
            btnStart.setTextSize(16f);
        }else{
            btnStart.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.colorPrimary));
        }
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer();
            }
        });

        return v;
    }
}
