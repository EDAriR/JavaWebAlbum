package com.diary.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Growing_DiaryJDBCDAO implements Growing_DiaryDAO_interface {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "ba101g3";
    String passwd = "baby";

    private static final String INSERT_STMT = "INSERT INTO growing_diary (gd_no, baby_no, gd_title, gd_date, gd_cnt, gd_shr) VALUES ('GD'||LPAD(to_char(gd_no_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT * FROM growing_diary";
    private static final String GET_ONE_STMT = "SELECT * FROM growing_diary WHERE gd_no = ?";
    private static final String UPDATE_STMT = "UPDATE growing_diary SET baby_no=?, gd_title=? , gd_date=?, gd_cnt=?, gd_shr=? WHERE gd_no= ?";
    private static final String DELETE_GROWING_DIARY = "DELETE FROM growing_diary WHERE gd_no= ?";

    @Override
    public void insert(Growing_DiaryVO growing_diaryVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, growing_diaryVO.getBaby_no());
            pstmt.setString(2, growing_diaryVO.getGd_title());
            pstmt.setTimestamp(3, growing_diaryVO.getGd_date());
            pstmt.setString(4, growing_diaryVO.getGd_cnt());
            pstmt.setString(5, growing_diaryVO.getGd_shr());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
    public void update(Growing_DiaryVO growing_diaryVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE_STMT);


            pstmt.setString(1, growing_diaryVO.getBaby_no());
            pstmt.setString(2, growing_diaryVO.getGd_title());
            pstmt.setTimestamp(3, growing_diaryVO.getGd_date());
            pstmt.setString(4, growing_diaryVO.getGd_cnt());
            pstmt.setString(5, growing_diaryVO.getGd_shr());
            pstmt.setString(6, growing_diaryVO.getGd_no());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
    public void delete(String gd_no) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE_GROWING_DIARY);

            pstmt.setString(1, gd_no);

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
    public Growing_DiaryVO findByPrimary(String gd_no) {
        Growing_DiaryVO growing_DiaryVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, gd_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                growing_DiaryVO = new Growing_DiaryVO();
                growing_DiaryVO.setGd_no(rs.getString("gd_no"));
                growing_DiaryVO.setBaby_no(rs.getString("baby_no"));
                growing_DiaryVO.setGd_title(rs.getString("gd_title"));
                growing_DiaryVO.setGd_date(rs.getTimestamp("gd_date"));
                growing_DiaryVO.setGd_cnt(rs.getString("gd_cnt"));
                growing_DiaryVO.setGd_shr(rs.getString("gd_shr"));
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
        return growing_DiaryVO;
    }

    @Override
    public List<Growing_DiaryVO> getAll() {
        List<Growing_DiaryVO> list = new ArrayList<Growing_DiaryVO>();
        Growing_DiaryVO growing_DiaryVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                growing_DiaryVO = new Growing_DiaryVO();
                growing_DiaryVO.setGd_no(rs.getString("gd_no"));
                growing_DiaryVO.setBaby_no(rs.getString("baby_no"));
                growing_DiaryVO.setGd_title(rs.getString("gd_title"));
                growing_DiaryVO.setGd_date(rs.getTimestamp("gd_date"));
                growing_DiaryVO.setGd_cnt(rs.getString("gd_cnt"));
                growing_DiaryVO.setGd_shr(rs.getString("gd_shr"));
                list.add(growing_DiaryVO);
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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

    public static void main(String[] args) {
        Growing_DiaryJDBCDAO dao = new Growing_DiaryJDBCDAO();
        // 新增 OK
//		Growing_DiaryVO growing_diaryVO1 = new Growing_DiaryVO();
//		growing_diaryVO1.setBaby_no("BABY0001");
//		growing_diaryVO1.setGd_title("寶寶第一次學會走路了");
//		growing_diaryVO1.setGd_date(java.sql.Timestamp.valueOf("2018-01-01 13:11:30"));
//		growing_diaryVO1.setGd_cnt("so cute");
//		growing_diaryVO1.setGd_shr("1");
//		dao.insert(growing_diaryVO1);
//		System.out.print("資料新增完成");

        // 修改 OK
//		 Growing_DiaryVO growing_diaryVO2 = new Growing_DiaryVO();
//		 growing_diaryVO2.setGd_no("GD000003");
//		 growing_diaryVO2.setBaby_no("BABY0001");
//		 growing_diaryVO2.setGd_title("寶寶第一次喝奶");
//		 growing_diaryVO2.setGd_date(java.sql.Timestamp.valueOf("2018-01-01 13:11:30"));
//		 growing_diaryVO2.setGd_cnt("喝很多呢");
//		 growing_diaryVO2.setGd_shr("1");
//		 dao.update(growing_diaryVO2);
//		 System.out.print("資料修改完成");

        // 刪除 OK
//		 dao.utility.delete("GD000002");
//		 System.out.println("刪除成功");

        // 查詢 OK
//		 Growing_DiaryVO growing_diaryVO3 = dao.findByPrimary("GD000002");
//		 System.out.print(growing_diaryVO3.getGd_no() + ",");
//		 System.out.print(growing_diaryVO3.getBaby_no() + ",");
//		 System.out.print(growing_diaryVO3.getGd_title() + ",");
//		 System.out.print(growing_diaryVO3.getGd_date() + ",");
//		 System.out.print(growing_diaryVO3.getGd_cnt() + ",");
//		 System.out.println(growing_diaryVO3.getGd_shr() + ",");
//		 System.out.println("---------------------");

        // 查詢全部 OK
//		 List<Growing_DiaryVO> list = dao.getAll();
//		 for (Growing_DiaryVO growing_diaryVO : list) {
//		 System.out.print(growing_diaryVO.getGd_no() + ",");
//		 System.out.print(growing_diaryVO.getBaby_no() + ",");
//		 System.out.print(growing_diaryVO.getGd_title() + ",");
//		 System.out.print(growing_diaryVO.getGd_date() + ",");
//		 System.out.print(growing_diaryVO.getGd_cnt() + ",");
//		 System.out.print(growing_diaryVO.getGd_shr() + ",");
//		 System.out.println();
//		 }
    }
}