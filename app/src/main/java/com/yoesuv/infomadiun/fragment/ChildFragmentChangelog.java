package com.yoesuv.infomadiun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoesuv.infomadiun.R;

public class ChildFragmentChangelog extends Fragment {

    private View v;
    private TextView tv1,tv2,tv3,tv4,tv5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_changelog, container, false);

        tv1 = (TextView) v.findViewById(R.id.textView_ver_1);
        tv1.setText(Html.fromHtml(getResources().getString(R.string.ver_1_info)));

        tv2 = (TextView) v.findViewById(R.id.textView_ketver_2);
        tv2.setText(Html.fromHtml(getResources().getString(R.string.ver_2_info)));

        tv3 = (TextView) v.findViewById(R.id.textView_ketver_3);
        tv3.setText(Html.fromHtml(getResources().getString(R.string.ver_3_info)));

        tv4 = (TextView) v.findViewById(R.id.textView_ketver_4);
        tv4.setText(Html.fromHtml(getResources().getString(R.string.ver_4_info)));

        tv5 = (TextView) v.findViewById(R.id.textView_ketver_5);
        tv5.setText(Html.fromHtml(getResources().getString(R.string.ver_5_info)));
        return v;
    }
}
