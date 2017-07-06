package com.diary.model;
/**
 * 相片，不能修改，可以刪除。
 */

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Voice_DiaryDAO implements Voice_DiaryDAO_interface {
    // 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
    private static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG3");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_STMT =
            "INSERT INTO voice_diary (vd_no, baby_no, vd_title, vd_date, vd_cnt, vd_shr) VALUES ('VD'||LPAD(to_char(vd_no_seq.NEXTVAL),6,'0'), ?, ?, SYSDATE, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT * FROM voice_diary";
    private static final String GET_ONE_STMT =
            "SELECT * FROM voice_diary WHERE vd_no = ?";
    private static final String DELETE_VOICE_DIARY = "DELETE FROM voice_diary WHERE vd_no = ?";


    @Override
    public void insert(Voice_DiaryVO voice_diaryVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, voice_diaryVO.getBaby_no());
            pstmt.setString(2, voice_diaryVO.getVd_title());
            pstmt.setBytes(3, voice_diaryVO.getVd_cnt());
            pstmt.setString(4, voice_diaryVO.getVd_shr());

            pstmt.executeUpdate();

            con = ds.getConnection();
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void delete(String vd_no) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE_VOICE_DIARY);

            pstmt.setString(1, vd_no);

            pstmt.executeUpdate();

            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public Voice_DiaryVO findByPrimary(String vd_no) {
        Voice_DiaryVO voice_diaryVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, vd_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo 也稱為 Domain objects
                voice_diaryVO = new Voice_DiaryVO();
                voice_diaryVO.setVd_no(rs.getString("vd_no"));
                voice_diaryVO.setBaby_no(rs.getString("baby_no"));
                voice_diaryVO.setVd_title(rs.getString("vd_title"));
                voice_diaryVO.setVd_date(rs.getTimestamp("vd_date"));
                voice_diaryVO.setVd_cnt(rs.getBytes("vd_cnt"));
                voice_diaryVO.setVd_shr(rs.getString("vd_shr"));
            }
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return voice_diaryVO;
    }

    @Override
    public List<Voice_DiaryVO> getAll() {
        List<Voice_DiaryVO> list = new ArrayList<Voice_DiaryVO>();
        Voice_DiaryVO voice_diaryVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo 也稱為 Domain objects
                voice_diaryVO = new Voice_DiaryVO();
                voice_diaryVO.setVd_no(rs.getString("vd_no"));
                voice_diaryVO.setBaby_no(rs.getString("baby_no"));
                voice_diaryVO.setVd_title(rs.getString("vd_title"));
                voice_diaryVO.setVd_date(rs.getTimestamp("vd_date"));
                voice_diaryVO.setVd_cnt(rs.getBytes("vd_cnt"));
                voice_diaryVO.setVd_shr(rs.getString("vd_shr"));
                list.add(voice_diaryVO);
            }
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }
}