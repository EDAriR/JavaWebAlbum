package com.diary.model;

import java.util.List;

public interface PhotoDAO_interface {
	public void insert(PhotoVO photoVO);
	public void delete(String pho_no);
	public PhotoVO findByPrimary(String pho_no);
	public List<PhotoVO> findBybaby(String baby_no);
	public List<PhotoVO>getAll();

}
