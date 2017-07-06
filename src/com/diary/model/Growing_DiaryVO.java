package com.diary.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Growing_DiaryVO implements Serializable {
	private String gd_no; // Not Null (PK)
	private String baby_no; // Not Null (FK)
	private String gd_title; // Not Null
	private Timestamp gd_date; // Not Null
	private String gd_cnt; // Not Null
	private String gd_shr;  // Not Null 0：僅限自己 1：僅限好友 2：公開

	public Growing_DiaryVO() {
		super();
	}

	public String getGd_no() {
		return gd_no;
	}

	public void setGd_no(String gd_no) {
		this.gd_no = gd_no;
	}

	public String getBaby_no() {
		return baby_no;
	}

	public void setBaby_no(String baby_no) {
		this.baby_no = baby_no;
	}

	public String getGd_title() {
		return gd_title;
	}

	public void setGd_title(String gd_title) {
		this.gd_title = gd_title;
	}

	public Timestamp getGd_date() {
		return gd_date;
	}

	public void setGd_date(Timestamp gd_date) {
		this.gd_date = gd_date;
	}

	public String getGd_cnt() {
		return gd_cnt;
	}

	public void setGd_cnt(String gd_cnt) {
		this.gd_cnt = gd_cnt;
	}

	public String getGd_shr() {
		return gd_shr;
	}

	public void setGd_shr(String gd_shr) {
		this.gd_shr = gd_shr;
	}

}
