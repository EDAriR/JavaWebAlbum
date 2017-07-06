
package com.diary.model;

import java.util.List;


public class VideoService {

    private VideoDAO_interface dao;

    public VideoService() {
        dao = new VideoDAO();
    }

    public VideoVO addVideo(String baby_no,  String vid_title,
                            String vid_annot, byte[] vid_file,
                            String vid_shr) {

        VideoVO videoVO = new VideoVO();

//      vid_no, baby_no, vid_title, vid_date, vid_annot, vid_file, vid_shr
        videoVO.setBaby_no(baby_no);
        videoVO.setVid_title(vid_title);
        videoVO.setVid_annot(vid_annot);
        videoVO.setVid_file(vid_file);
        videoVO.setVid_shr(vid_shr);
        dao.insert(videoVO);

        return videoVO;
    }

    /*public VideoVO updateVideo(String baby_no,  String vid_title,
                            String vid_annot, byte[] vid_file,
                            String vid_shr) {

        PhotoVO videoVO = new PhotoVO();

        videoVO.setBaby_no(baby_no);
        videoVO.setPho_title(pho_title);
        videoVO.setPho_annot(pho_annot);
        videoVO.setPho_shr(videoVO);
        dao.update(videoVO);
        return photoVO;
    }*/
    public void deleteVideo(String vid_no) {

        VideoVO videoVO = new VideoVO();

//      baby_no, pho_title, pho_date, pho_annot, pho_file, pho_shr
        videoVO.setVid_no(vid_no);
        dao.delete(vid_no);
    }

    public VideoVO getOneVideo(String vid_no) {
        return dao.findByPrimary(vid_no);
    }

    public List<VideoVO> getAll() {
        return dao.getAll();
    }
}
