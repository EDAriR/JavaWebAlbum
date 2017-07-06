package com.diary.model;

import java.util.List;

public interface Growing_DiaryDAO_interface {
	public void insert(Growing_DiaryVO growing_diaryVO);

	public void update(Growing_DiaryVO growing_diaryVO);

	public void delete(String gd_no);

	public Growing_DiaryVO findByPrimary(String gd_no);

	public List<Growing_DiaryVO> getAll();
}
