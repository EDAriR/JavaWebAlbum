package com.diary.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class VideoVO implements Serializable {
	private String vid_no; // Not Null (PK)
	private String baby_no; // Not Null (FK)
	private String vid_title;
	private Timestamp vid_date; // Not Null
	private String vid_annot;
	private byte[] vid_file; // Not Null
	private String vid_shr; // Not Null 0：僅限自己 1：僅限好友 2：公開

	public VideoVO() {
		super();
	}

	public String getVid_no() {
		return vid_no;
	}

	public void setVid_no(String vid_no) {
		this.vid_no = vid_no;
	}

	public String getBaby_no() {
		return baby_no;
	}

	public void setBaby_no(String baby_no) {
		this.baby_no = baby_no;
	}

	public String getVid_title() {
		return vid_title;
	}

	public void setVid_title(String vid_title) {
		this.vid_title = vid_title;
	}

	public Timestamp getVid_date() {
		return vid_date;
	}

	public void setVid_date(Timestamp vid_date) {
		this.vid_date = vid_date;
	}

	public String getVid_annot() {
		return vid_annot;
	}

	public void setVid_annot(String vid_annot) {
		this.vid_annot = vid_annot;
	}

	public byte[] getVid_file() {
		return vid_file;
	}

	public void setVid_file(byte[] vid_file) {
		this.vid_file = vid_file;
	}

	public String getVid_shr() {
		return vid_shr;
	}

	public void setVid_shr(String vid_shr) {
		this.vid_shr = vid_shr;
	}
}