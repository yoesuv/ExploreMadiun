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


public class ChildFragmentThanks extends Fragment {

    private View v;
    private TextView tvThanks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_thanks, container, false);

        tvThanks = (TextView) v.findViewById(R.id.textView_terima_kasih);
        tvThanks.setText(Html.fromHtml(getResources().getString(R.string.trims)));
        return v;
    }
}
