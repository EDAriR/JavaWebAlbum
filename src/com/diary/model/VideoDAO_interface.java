package com.diary.model;

import java.util.List;

public interface VideoDAO_interface {
	public void insert(VideoVO videoVO);
	public void delete(String vid_no);
	public VideoVO findByPrimary(String vid_no);
	public List<VideoVO>getAll();
}
