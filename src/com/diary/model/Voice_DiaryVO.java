package com.diary.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Voice_DiaryVO implements Serializable {
    private String vd_no; // Not Null (PK)
    private String baby_no; // Not Null (FK)
    private String vd_title; // Not Null
    private Timestamp vd_date; // Not Null
    private byte[] vd_cnt; // Not Null
    private String vd_shr; // Not Null 0：僅限自己 1：僅限好友 2：公開

    public Voice_DiaryVO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getVd_no() {
        return vd_no;
    }

    public void setVd_no(String vd_no) {
        this.vd_no = vd_no;
    }

    public String getBaby_no() {
        return baby_no;
    }

    public void setBaby_no(String baby_no) {
        this.baby_no = baby_no;
    }

    public String getVd_title() {
        return vd_title;
    }

    public void setVd_title(String vd_title) {
        this.vd_title = vd_title;
    }

    public Timestamp getVd_date() {
        return vd_date;
    }

    public void setVd_date(Timestamp vd_date) {
        this.vd_date = vd_date;
    }

    public byte[] getVd_cnt() {
        return vd_cnt;
    }

    public void setVd_cnt(byte[] vd_cnt) {
        this.vd_cnt = vd_cnt;
    }

    public String getVd_shr() {
        return vd_shr;
    }

    public void setVd_shr(String vd_shr) {
        this.vd_shr = vd_shr;
    }

}