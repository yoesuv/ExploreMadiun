package com.yoesuv.infomadiun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yoesuv.infomadiun.models.ListPlace;

public class MainPlaceDetailActivity extends AppCompatActivity {

    public static String EXTRA_PLACE = "extra_place";
    private Toolbar toolbar;
    private ImageView imgPlaceDetail;
    private TextView textViewPlace, tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvTitle = (TextView) toolbar.findViewById(R.id.textView_title);

        ListPlace lp = getIntent().getExtras().getParcelable(EXTRA_PLACE);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setElevation(5);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if(lp!=null) {
                tvTitle.setText(lp.getNama());
            }
        }

        textViewPlace = (TextView) findViewById(R.id.textView_place_detail);
        imgPlaceDetail = (ImageView) findViewById(R.id.imageView_place_detail);

        if(lp!=null) {
            textViewPlace.setText(lp.getDeskripsi());
            Picasso.with(this).load(lp.getGambar()).placeholder(R.drawable.img_default).into(imgPlaceDetail, new Callback() {
                @Override
                public void onSuccess() {
                    imgPlaceDetail.setAdjustViewBounds(true);
                }

                @Override
                public void onError() {
                    imgPlaceDetail.setAdjustViewBounds(false);
                    imgPlaceDetail.setImageResource(R.drawable.img_default);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                MainPlaceDetailActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
