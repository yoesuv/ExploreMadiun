package com.yoesuv.infomadiun.models;


public class InfoGallery {

    private String thumbnail,image,caption;

    public InfoGallery(){

    }

    public InfoGallery(String thumbnail, String image, String caption){
        this.thumbnail = thumbnail;
        this.image = image;
        this.caption = caption;
    }

    public String getThumbnail() {
        if(thumbnail!=null) {
            return thumbnail;
        }else{
            return "";
        }
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
