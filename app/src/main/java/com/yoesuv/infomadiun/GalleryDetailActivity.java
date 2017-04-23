package com.yoesuv.infomadiun;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class GalleryDetailActivity extends AppCompatActivity {

    public static String EXTRA_IMAGE = "extra_image";
    public static String EXTRA_DESC = "extra_description";

    private Toolbar toolbar;
    private TextView textView_desc,tvTitle;
    private AppCompatImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);

        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT) {
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setElevation(5);
        }
        tvTitle = (TextView) toolbar.findViewById(R.id.textView_title);
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(),"pacifico.ttf");
        tvTitle.setTypeface(tf);

        tvTitle.setText(R.string.gallery);

        textView_desc = (TextView) findViewById(R.id.textView_gallery_detail);
        img = (AppCompatImageView) findViewById(R.id.imageView_gallery_detail);

        textView_desc.setText(getIntent().getExtras().getString(EXTRA_DESC));
        Picasso.with(this).load(getIntent().getExtras().getString(EXTRA_IMAGE)).placeholder(R.drawable.img_default).into(img, new Callback() {
            @Override
            public void onSuccess() {
                img.setAdjustViewBounds(true);
            }

            @Override
            public void onError() {
                img.setAdjustViewBounds(false);
                img.setImageResource(R.drawable.img_default);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT) {
                    onBackPressed();
                }else{
                    supportFinishAfterTransition();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT) {
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
        }
    }
}
