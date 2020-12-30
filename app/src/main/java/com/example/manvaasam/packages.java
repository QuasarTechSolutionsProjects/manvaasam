package com.example.manvaasam;

public class packages {

    String man_id, stc_id;

    public packages(String man_id, String stc_id) {
        this.man_id = man_id;
        this.stc_id = stc_id;
    }

    public String getMid() {
        return man_id;
    }

    public String getSid() {
        return stc_id;
    }

}