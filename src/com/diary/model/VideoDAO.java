package com.diary.model;
/**
 * 相片，不能修改，可以刪除。*/

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


public class VideoDAO implements VideoDAO_interface {

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
            "INSERT INTO video (vid_no, baby_no, vid_title, vid_date, vid_annot, vid_file, vid_shr) VALUES ('VID'||LPAD(to_char(vid_no_seq.NEXTVAL), 5, '0'), ?, ?, SYSDATE, ?, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT * FROM video";
    private static final String GET_ONE_STMT =
            "SELECT * FROM video WHERE vid_no = ?";
    private static final String DELETE_VIDEO = "DELETE FROM video WHERE vid_no = ?";


    @Override
    public void insert(VideoVO videoVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, videoVO.getBaby_no());
            pstmt.setString(2, videoVO.getVid_title());
            pstmt.setString(3, videoVO.getVid_annot());
            pstmt.setBytes(4, videoVO.getVid_file());
            pstmt.setString(5, videoVO.getVid_shr());

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
    public void delete(String vid_no) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE_VIDEO);

            pstmt.setString(1, vid_no);

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
    public VideoVO findByPrimary(String vid_no) {
        VideoVO videoVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, vid_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo ?]??? Domain objects
                videoVO = new VideoVO();
                videoVO.setVid_no(rs.getString("vid_no"));
                videoVO.setBaby_no(rs.getString("baby_no"));
                videoVO.setVid_title(rs.getString("vid_title"));
                videoVO.setVid_date(rs.getTimestamp("vid_date"));
                videoVO.setVid_annot(rs.getString("vid_annot"));
                videoVO.setVid_file(rs.getBytes("vid_file"));
                videoVO.setVid_shr(rs.getString("vid_shr"));
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
        return videoVO;
    }

    @Override
    public List<VideoVO> getAll() {
        List<VideoVO> list = new ArrayList<VideoVO>();
        VideoVO videoVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo ?]??? Domain objects
                videoVO = new VideoVO();
                videoVO.setVid_no(rs.getString("vid_no"));
                videoVO.setBaby_no(rs.getString("baby_no"));
                videoVO.setVid_title(rs.getString("vid_title"));
                videoVO.setVid_date(rs.getTimestamp("vid_date"));
                videoVO.setVid_annot(rs.getString("vid_annot"));
                videoVO.setVid_file(rs.getBytes("vid_file"));
                videoVO.setVid_shr(rs.getString("vid_shr"));
                list.add(videoVO);
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
