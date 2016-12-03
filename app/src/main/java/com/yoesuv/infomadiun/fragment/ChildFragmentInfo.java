package com.yoesuv.infomadiun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoesuv.infomadiun.BuildConfig;
import com.yoesuv.infomadiun.R;

public class ChildFragmentInfo extends Fragment {

    private View v;
    private TextView tvVersion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_info, container, false);

        tvVersion = (TextView) v.findViewById(R.id.textView_version_app);
        String ver = getResources().getString(R.string.versi)+" "+BuildConfig.VERSION_NAME;
        tvVersion.setText(ver);

        return v;
    }
}
