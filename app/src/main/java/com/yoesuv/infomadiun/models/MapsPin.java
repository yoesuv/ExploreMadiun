package com.yoesuv.infomadiun.models;

public class MapsPin {

    private String name;
    private int lokasi;
    private double longitude,latitude;

    public MapsPin(String name, int lokasi , double longitude, double latitude){
        this.name = name;
        this.lokasi = lokasi;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double logitude) {
        this.longitude = logitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLokasi() {
        return lokasi;
    }

    public void setLokasi(int lokasi) {
        this.lokasi = lokasi;
    }
}
