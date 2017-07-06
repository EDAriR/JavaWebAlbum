
package com.diary.model;

import java.util.List;


public class PhotoService {

    private PhotoDAO_interface dao;

    public PhotoService() {
        dao = new PhotoDAO();
    }

    public PhotoVO addPhoto(String baby_no, String pho_title,
                            String pho_annot, byte[] pho_file, String pho_shr) {

        PhotoVO photoVO = new PhotoVO();

//      baby_no, pho_title, pho_date, pho_annot, pho_file, pho_shr
        photoVO.setBaby_no(baby_no);
        photoVO.setPho_title(pho_title);
        photoVO.setPho_annot(pho_annot);
        photoVO.setPho_file(pho_file);
        photoVO.setPho_shr(pho_shr);
        dao.insert(photoVO);

        return photoVO;
    }

    public void deletePhoto(String pho_no) {

        PhotoVO photoVO = new PhotoVO();

//      baby_no, pho_title, pho_date, pho_annot, pho_file, pho_shr
        photoVO.setPho_no(pho_no);
        dao.delete(pho_no);
    }

    public PhotoVO getOnePhoto(String pho_no) {
        return dao.findByPrimary(pho_no);
    }

    public List<PhotoVO> findBybaby(String baby_no) { return dao.findBybaby(baby_no); }

    public List<PhotoVO> getAll() {
        return dao.getAll();
    }

    /*public PhotoVO updateAdmin(String baby_no, String pho_title, String pho_annot,
                               String pho_shr) {

        PhotoVO photoVO = new PhotoVO();

        photoVO.setBaby_no(baby_no);
        photoVO.setPho_title(pho_title);
        photoVO.setPho_annot(pho_annot);
        photoVO.setPho_shr(pho_shr);
        dao.update(photoVO);
        return photoVO;
    }*/

}
