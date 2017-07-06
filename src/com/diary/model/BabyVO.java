package com.diary.model;

import java.io.Serializable;
import java.sql.Date;

public class BabyVO implements Serializable {
	private String baby_no; //Not Null (PK)
	private String mem_no; //Not Null (FK) 
	private String baby_aka; //Not Null
	private Date baby_bday; //Not Null
	private String baby_gen; //Not Null 0：男性 1：女性
	private Double baby_hc; //Not Null
	private Double baby_ht; //Not Null
	private Double baby_wt; //Not Null
	private String baby_is_ar; //Not Null 0：否 1：是
	private String baby_is_ab; //Not Null 0：否 1：是
	private String baby_is_ac; //Not Null 0：否 1：是
	private String baby_is_sf; //Not Null 0：否 1：是
	private String baby_is_ad; //Not Null 0：否 1：是
	private String baby_rd; //其他罕見疾病
	
	public BabyVO() {
		super();
	}

	public String getBaby_no() {
		return baby_no;
	}

	public void setBaby_no(String baby_no) {
		this.baby_no = baby_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getBaby_aka() {
		return baby_aka;
	}

	public void setBaby_aka(String baby_aka) {
		this.baby_aka = baby_aka;
	}

	public Date getBaby_bday() {
		return baby_bday;
	}

	public void setBaby_bday(Date baby_bday) {
		this.baby_bday = baby_bday;
	}

	public String getBaby_gen() {
		return baby_gen;
	}

	public void setBaby_gen(String baby_gen) {
		this.baby_gen = baby_gen;
	}

	public Double getBaby_hc() {
		return baby_hc;
	}

	public void setBaby_hc(Double baby_hc) {
		this.baby_hc = baby_hc;
	}

	public Double getBaby_ht() {
		return baby_ht;
	}

	public void setBaby_ht(Double baby_ht) {
		this.baby_ht = baby_ht;
	}

	public Double getBaby_wt() {
		return baby_wt;
	}

	public void setBaby_wt(Double baby_wt) {
		this.baby_wt = baby_wt;
	}

	public String getBaby_is_ar() {
		return baby_is_ar;
	}

	public void setBaby_is_ar(String baby_is_ar) {
		this.baby_is_ar = baby_is_ar;
	}

	public String getBaby_is_ab() {
		return baby_is_ab;
	}

	public void setBaby_is_ab(String baby_is_ab) {
		this.baby_is_ab = baby_is_ab;
	}

	public String getBaby_is_ac() {
		return baby_is_ac;
	}

	public void setBaby_is_ac(String baby_is_ac) {
		this.baby_is_ac = baby_is_ac;
	}

	public String getBaby_is_sf() {
		return baby_is_sf;
	}

	public void setBaby_is_sf(String baby_is_sf) {
		this.baby_is_sf = baby_is_sf;
	}

	public String getBaby_is_ad() {
		return baby_is_ad;
	}

	public void setBaby_is_ad(String baby_is_ad) {
		this.baby_is_ad = baby_is_ad;
	}

	public String getBaby_rd() {
		return baby_rd;
	}

	public void setBaby_rd(String baby_rd) {
		this.baby_rd = baby_rd;
	}
	
}
