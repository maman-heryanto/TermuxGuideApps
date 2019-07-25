package com.t.termuxgapps.firebase;

public class advance {
    private String title;
    private String desc;

    public advance(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
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


    public advance() {
    }
}
