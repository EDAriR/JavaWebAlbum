package com.diary.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PhotoVO implements Serializable {
	private String pho_no; // Not Null (PK)
	private String baby_no; // Not Null (FK)
	private String pho_title;
	private Timestamp pho_date; // Not Null
	private String pho_annot;
	private byte[] pho_file; // Not Null
	private String pho_shr; // Not Null 0：僅限自己 1：僅限好友 2：公開

	public PhotoVO() {
		super();
	}

	public String getPho_no() {
		return pho_no;
	}

	public void setPho_no(String pho_no) {
		this.pho_no = pho_no;
	}

	public String getBaby_no() {
		return baby_no;
	}

	public void setBaby_no(String baby_no) {
		this.baby_no = baby_no;
	}

	public String getPho_title() {
		return pho_title;
	}

	public void setPho_title(String pho_title) {
		this.pho_title = pho_title;
	}

	public Timestamp getPho_date() {
		return pho_date;
	}

	public void setPho_date(Timestamp pho_date) {
		this.pho_date = pho_date;
	}

	public String getPho_annot() {
		return pho_annot;
	}

	public void setPho_annot(String pho_annot) {
		this.pho_annot = pho_annot;
	}

	public byte[] getPho_file() {
		return pho_file;
	}

	public void setPho_file(byte[] pho_file) {
		this.pho_file = pho_file;
	}

	public String getPho_shr() {
		return pho_shr;
	}

	public void setPho_shr(String pho_shr) {
		this.pho_shr = pho_shr;
	}

}
