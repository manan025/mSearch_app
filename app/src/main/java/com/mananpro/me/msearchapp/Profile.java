package com.mananpro.me.msearchapp;

public class Profile {

    private int name;
    private String pic_url;

    public Profile(int name, String pic_url) {
        this.name = name;
        this.pic_url = pic_url;
    }

    public Profile() {

    }

    public String getName() {
        return String.valueOf(name);
    }

    public String getPic_url() {
        return pic_url;
    }


}
