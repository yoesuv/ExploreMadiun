package com.yoesuv.infomadiun.utils;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 *  Created by yusuf on 5/1/18.
 */

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView>{

    private int height;

    public BottomNavigationViewBehavior(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.height = AppHelper.getToolbarHeight(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        if(dependency instanceof AppBarLayout){
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            int bottomMargin = lp.bottomMargin;
            int distanceToScroll = child.getHeight() + bottomMargin;
            float ratio = dependency.getY()/ height;
            child.setTranslationY(-distanceToScroll * ratio);
        }
        return true;
    }
}
