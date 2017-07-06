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


public class PhotoDAO implements PhotoDAO_interface {

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
            "INSERT INTO photo (pho_no, baby_no, pho_title, pho_date, pho_annot, pho_file, pho_shr) VALUES ('PHO'||LPAD(to_char(pho_no_seq.NEXTVAL), 5, '0'), ?, ?, SYSDATE, ?, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT * FROM photo ORDER BY pho_date DESC";
    private static final String GET_ONE_STMT =
            "SELECT * FROM photo WHERE pho_no = ?";
    private static final String GET_BABY_STMT =
            "SELECT * FROM photo WHERE baby_no = ?";
    private static final String DELETE_PHOTO = "DELETE FROM photo WHERE pho_no = ?";


    @Override
    public void insert(PhotoVO photoVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, photoVO.getBaby_no());
            pstmt.setString(2, photoVO.getPho_title());
            pstmt.setString(3, photoVO.getPho_annot());
            pstmt.setBytes(4, photoVO.getPho_file());
            pstmt.setString(5, photoVO.getPho_shr());

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
    public void delete(String pho_no) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE_PHOTO);

            pstmt.setString(1, pho_no);

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
    public PhotoVO findByPrimary(String pho_no) {
        PhotoVO photoVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, pho_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                photoVO = new PhotoVO();
                photoVO.setPho_no(rs.getString("pho_no"));
                photoVO.setBaby_no(rs.getString("baby_no"));
                photoVO.setPho_title(rs.getString("pho_title"));
                photoVO.setPho_date(rs.getTimestamp("pho_date"));
                photoVO.setPho_annot(rs.getString("pho_annot"));
                photoVO.setPho_file(rs.getBytes("pho_file"));
                photoVO.setPho_shr(rs.getString("pho_shr"));
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
        return photoVO;
    }

    @Override
    public List<PhotoVO> findBybaby(String baby_no) {

        List<PhotoVO> list = new ArrayList<PhotoVO>();
        PhotoVO photoVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_BABY_STMT);

            pstmt.setString(1, baby_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                photoVO = new PhotoVO();
                photoVO.setPho_no(rs.getString("pho_no"));
                photoVO.setBaby_no(rs.getString("baby_no"));
                photoVO.setPho_title(rs.getString("pho_title"));
                photoVO.setPho_date(rs.getTimestamp("pho_date"));
                photoVO.setPho_annot(rs.getString("pho_annot"));
                photoVO.setPho_file(rs.getBytes("pho_file"));
                photoVO.setPho_shr(rs.getString("pho_shr"));
                list.add(photoVO);
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

    @Override
    public List<PhotoVO> getAll() {
        List<PhotoVO> list = new ArrayList<PhotoVO>();
        PhotoVO photoVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                photoVO = new PhotoVO();
                photoVO.setPho_no(rs.getString("pho_no"));
                photoVO.setBaby_no(rs.getString("baby_no"));
                photoVO.setPho_title(rs.getString("pho_title"));
                photoVO.setPho_date(rs.getTimestamp("pho_date"));
                photoVO.setPho_annot(rs.getString("pho_annot"));
                photoVO.setPho_file(rs.getBytes("pho_file"));
                photoVO.setPho_shr(rs.getString("pho_shr"));
                list.add(photoVO);
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