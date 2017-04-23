package com.yoesuv.infomadiun.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yoesuv.infomadiun.R;
import com.yoesuv.infomadiun.models.ListPlace;

public class ListPlaceAdapter extends ArrayAdapter<ListPlace>{

    public ListPlaceAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list_place, parent, false);

            holder.nama = (AppCompatTextView) convertView.findViewById(R.id.textView_nama);
            holder.kategori = (AppCompatTextView) convertView.findViewById(R.id.textView_kategori);
            holder.thumbnail = (AppCompatImageView) convertView.findViewById(R.id.imageView_thumbnail);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nama.setText(getItem(position).getNama());
        holder.kategori.setText(getItem(position).getLokasi());

        final ViewHolder tmp = holder;
        Picasso.with(getContext()).load(getItem(position).getThumbnail()).placeholder(R.drawable.img_default).into(holder.thumbnail, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
                tmp.thumbnail.setImageResource(R.drawable.img_default);
            }
        });


        return convertView;
    }

    private class ViewHolder{
        AppCompatImageView thumbnail;
        AppCompatTextView nama;
        AppCompatTextView kategori;
    }
}
