package com.diary.model;

import java.sql.Date;
import java.util.List;

public class BabyService {

    private BabyDAO_interface dao;

    public BabyService() {
        dao = new BabyDAO();
    }

    public BabyVO addBaby(String mem_no, String baby_aka, Date baby_bday,
                          String baby_gen, Double baby_hc, Double baby_ht, Double baby_wt, String baby_is_ar,
                          String baby_is_ab, String baby_is_ac, String baby_is_sf, String baby_is_ad, String baby_rd) {

        BabyVO babyVO = new BabyVO();

        babyVO.setMem_no(mem_no);
        babyVO.setBaby_aka(baby_aka);
        babyVO.setBaby_bday(baby_bday);
        babyVO.setBaby_gen(baby_gen);
        babyVO.setBaby_hc(baby_hc);
        babyVO.setBaby_ht(baby_ht);
        babyVO.setBaby_wt(baby_wt);
        babyVO.setBaby_is_ar(baby_is_ar);
        babyVO.setBaby_is_ab(baby_is_ab);
        babyVO.setBaby_is_ac(baby_is_ac);
        babyVO.setBaby_is_sf(baby_is_sf);
        babyVO.setBaby_is_ad(baby_is_ad);
        babyVO.setBaby_rd(baby_rd);

        dao.insert(babyVO);

        return babyVO;
    }

    public BabyVO updateBaby(String baby_no, String baby_aka, Date baby_bday,
                             String baby_gen, Double baby_hc, Double baby_ht, Double baby_wt, String baby_is_ar,
                             String baby_is_ab, String baby_is_ac, String baby_is_sf, String baby_is_ad, String baby_rd) {

        BabyVO babyVO = new BabyVO();

        babyVO.setBaby_no(baby_no);
        babyVO.setBaby_aka(baby_aka);
        babyVO.setBaby_bday(baby_bday);
        babyVO.setBaby_gen(baby_gen);
        babyVO.setBaby_hc(baby_hc);
        babyVO.setBaby_ht(baby_ht);
        babyVO.setBaby_wt(baby_wt);
        babyVO.setBaby_is_ar(baby_is_ar);
        babyVO.setBaby_is_ab(baby_is_ab);
        babyVO.setBaby_is_ac(baby_is_ac);
        babyVO.setBaby_is_sf(baby_is_sf);
        babyVO.setBaby_is_ad(baby_is_ad);
        babyVO.setBaby_rd(baby_rd);

        dao.update(babyVO);

        return babyVO;
    }

    public BabyVO getOneBaby(String baby_no) {
        return dao.findByPrimaryKey(baby_no);
    }

    public List<BabyVO> getByMemNo(String mem_no) {
        return dao.findByMemNo(mem_no);
    }

    public List<BabyVO> getAll() {
        return dao.getAll();
    }
}
