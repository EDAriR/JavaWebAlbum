
package com.diary.model;

import java.util.List;


public class Voice_DiaryService {

    private Voice_DiaryDAO_interface dao;

    public Voice_DiaryService() {
        dao = new Voice_DiaryDAO();
    }

    public Voice_DiaryVO addVoice(String baby_no,  String vd_title,
                                  byte[] vd_cnt,String vd_shr) {

        Voice_DiaryVO voice_DiaryVO = new Voice_DiaryVO();

//      vd_no, baby_no, vd_title, vd_date, vd_cnt, vd_shr
        voice_DiaryVO.setBaby_no(baby_no);
        voice_DiaryVO.setVd_title(vd_title);
        voice_DiaryVO.setVd_cnt(vd_cnt);
        voice_DiaryVO.setVd_shr(vd_shr);
        dao.insert(voice_DiaryVO);

        return voice_DiaryVO;
    }

    /*public Voice_Diary updateVoice(String baby_no,  String vd_title,
                                  byte[] vd_cnt,String vd_shr) {

        Voice_DiaryVO voice_DiaryVO = new Voice_DiaryVO();

        voice_DiaryVO.setBaby_no(baby_no);
        voice_DiaryVO.setPho_title(pho_title);
        voice_DiaryVO.setPho_annot(pho_annot);
        voice_DiaryVO.setPho_shr(videoVO);
        dao.update(voice_DiaryVO);
        return photoVO;
    }*/
    public void deleteVoice(String vd_no) {

        Voice_DiaryVO voice_DiaryVO = new Voice_DiaryVO();

//      baby_no, pho_title, pho_date, pho_annot, pho_file, pho_shr
        voice_DiaryVO.setVd_no(vd_no);
        dao.delete(vd_no);
    }

    public Voice_DiaryVO getOneVoice(String vd_no) {
        return dao.findByPrimary(vd_no);
    }

    public List<Voice_DiaryVO> getAll() {
        return dao.getAll();
    }
}
