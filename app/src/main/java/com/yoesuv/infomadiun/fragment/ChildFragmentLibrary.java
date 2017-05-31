package com.yoesuv.infomadiun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoesuv.infomadiun.R;

public class ChildFragmentLibrary extends Fragment{

    private View v;
    private TextView tvIcons8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab_fragment_library, container, false);

        tvIcons8 = (TextView) v.findViewById(R.id.textView_icons8_by);
        tvIcons8.setText(Html.fromHtml("<a href=\"http://icons8.com\">Icons8.com</a>"));
        tvIcons8.setMovementMethod(LinkMovementMethod.getInstance());
        return v;
    }
}
