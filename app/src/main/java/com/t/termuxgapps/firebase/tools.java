package com.t.termuxgapps.firebase;

public class tools {
    private String title;
    private String desc;
    private String image;
    private String url;



    public tools(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.url = url;


    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public tools() {
    }
}
