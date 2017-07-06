package com.diary.model;

import java.util.List;

public interface Voice_DiaryDAO_interface {
	public void insert(Voice_DiaryVO voice_DiaryVO);
	public void delete(String vd_no);
	public Voice_DiaryVO findByPrimary(String vd_no);
	public List<Voice_DiaryVO>getAll();
}
