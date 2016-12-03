package com.yoesuv.infomadiun.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ListPlace implements Parcelable {

    private String nama,lokasi,kategori,deskripsi,thumbnail,gambar;

    public ListPlace(){

    }

    public ListPlace(String nama, String lokasi,String kategori, String deskripsi, String thumbnail, String gambar){
        this.nama = nama;
        this.lokasi = lokasi;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.thumbnail = thumbnail;
        this.gambar = gambar;
    }

   public ListPlace(Parcel source){
       nama = source.readString();
       lokasi = source.readString();
       kategori = source.readString();
       deskripsi = source.readString();
       thumbnail = source.readString();
       gambar = source.readString();
   }

    public static Creator<ListPlace> CREATOR = new Creator<ListPlace>() {
        @Override
        public ListPlace createFromParcel(Parcel in) {
            return new ListPlace(in);
        }

        @Override
        public ListPlace[] newArray(int size) {
            return new ListPlace[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        defaultValue();
        dest.writeString(nama);
        dest.writeString(lokasi);
        dest.writeString(kategori);
        dest.writeString(deskripsi);
        dest.writeString(thumbnail);
        dest.writeString(gambar);
    }

    private void defaultValue(){
        if(nama==null){
            nama = "";
        }
        if(kategori==null){
            kategori = "";
        }
        if(deskripsi==null){
            deskripsi = "";
        }
        if(thumbnail==null){
            thumbnail = "";
        }
        if(gambar==null){
            gambar = "";
        }
        if(lokasi==null){
            lokasi = "";
        }
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
