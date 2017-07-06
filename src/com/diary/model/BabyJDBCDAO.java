package com.diary.model;
/**
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BabyJDBCDAO implements BabyDAO_interface {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String URL = "jdbc:oracle:thin:@localhost:1522:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";

    private static final String INSERT_STMT =
            "INSERT INTO baby (baby_no, mem_no, baby_aka, baby_bday ,baby_gen ,baby_hc ,baby_ht ,baby_wt ,baby_is_ar ,baby_is_ab ,baby_is_ac ,baby_is_sf ,baby_is_ad ,baby_rd) VALUES "
                    + "('BABY'||LPAD(to_char(baby_no_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT * FROM baby";
    private static final String GET_ONE_STMT =
            "SELECT * FROM baby WHERE baby_no = ?";

    private static final String GET_BY_MEM_NO_STMT =
            "SELECT * FROM baby WHERE mem_no = ?";


    private static final String UPDATE_STMT =
            "UPDATE baby SET baby_aka=?, baby_bday=?, baby_gen=?, baby_hc=?, baby_ht=?, baby_wt=?, baby_is_ar=?, baby_is_ab=?, baby_is_ac=?, baby_is_sf=?, baby_is_ad=?, baby_rd=? WHERE baby_no = ?";


    @Override
    public void insert(BabyVO babyVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, babyVO.getMem_no());
            pstmt.setString(2, babyVO.getBaby_aka());
            pstmt.setDate(3, babyVO.getBaby_bday());
            pstmt.setString(4, babyVO.getBaby_gen());
            pstmt.setDouble(5, babyVO.getBaby_hc());
            pstmt.setDouble(6, babyVO.getBaby_ht());
            pstmt.setDouble(7, babyVO.getBaby_wt());
            pstmt.setString(8, babyVO.getBaby_is_ar());
            pstmt.setString(9, babyVO.getBaby_is_ab());
            pstmt.setString(10, babyVO.getBaby_is_ac());
            pstmt.setString(11, babyVO.getBaby_is_sf());
            pstmt.setString(12, babyVO.getBaby_is_ad());
            pstmt.setString(13, babyVO.getBaby_rd());

            pstmt.executeUpdate();            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
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
    public void update(BabyVO babyVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setString(1, babyVO.getBaby_aka());
            pstmt.setDate(2, babyVO.getBaby_bday());
            pstmt.setString(3, babyVO.getBaby_gen());
            pstmt.setDouble(4, babyVO.getBaby_hc());
            pstmt.setDouble(5, babyVO.getBaby_ht());
            pstmt.setDouble(6, babyVO.getBaby_wt());
            pstmt.setString(7, babyVO.getBaby_is_ar());
            pstmt.setString(8, babyVO.getBaby_is_ab());
            pstmt.setString(9, babyVO.getBaby_is_ac());
            pstmt.setString(10, babyVO.getBaby_is_sf());
            pstmt.setString(11, babyVO.getBaby_is_ad());
            pstmt.setString(12, babyVO.getBaby_rd());
            pstmt.setString(13, babyVO.getBaby_no());


            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
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
    public BabyVO findByPrimaryKey(String baby_no) {
        BabyVO babyVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, baby_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo 也稱為 Domain objects
                babyVO = new BabyVO();
                babyVO.setBaby_no(rs.getString("baby_no"));
                babyVO.setMem_no(rs.getString("mem_no"));
                babyVO.setBaby_aka(rs.getString("baby_aka"));
                babyVO.setBaby_bday(rs.getDate("baby_bday"));
                babyVO.setBaby_gen(rs.getString("baby_gen"));
                babyVO.setBaby_hc(rs.getDouble("baby_hc"));
                babyVO.setBaby_ht(rs.getDouble("baby_ht"));
                babyVO.setBaby_wt(rs.getDouble("baby_wt"));
                babyVO.setBaby_is_ar(rs.getString("baby_is_ar"));
                babyVO.setBaby_is_ab(rs.getString("baby_is_ab"));
                babyVO.setBaby_is_ac(rs.getString("baby_is_ac"));
                babyVO.setBaby_is_sf(rs.getString("baby_is_sf"));
                babyVO.setBaby_is_ad(rs.getString("baby_is_ad"));
                babyVO.setBaby_rd(rs.getString("baby_rd"));

            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
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
        return babyVO;
    }

    @Override
    public List<BabyVO> findByMemNo(String mem_no) {

        List<BabyVO> list = new ArrayList<BabyVO>();
        BabyVO babyVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

        	Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);            
        	pstmt = con.prepareStatement(GET_BY_MEM_NO_STMT);

            pstmt.setString(1, mem_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo 也稱為 Domain objects
                babyVO = new BabyVO();
                babyVO.setBaby_no(rs.getString("baby_no"));
                babyVO.setMem_no(rs.getString("mem_no"));
                babyVO.setBaby_aka(rs.getString("baby_aka"));
                babyVO.setBaby_bday(rs.getDate("baby_bday"));
                babyVO.setBaby_gen(rs.getString("baby_gen"));
                babyVO.setBaby_hc(rs.getDouble("baby_hc"));
                babyVO.setBaby_ht(rs.getDouble("baby_ht"));
                babyVO.setBaby_wt(rs.getDouble("baby_wt"));
                babyVO.setBaby_is_ar(rs.getString("baby_is_ar"));
                babyVO.setBaby_is_ab(rs.getString("baby_is_ab"));
                babyVO.setBaby_is_ac(rs.getString("baby_is_ac"));
                babyVO.setBaby_is_sf(rs.getString("baby_is_sf"));
                babyVO.setBaby_is_ad(rs.getString("baby_is_ad"));
                babyVO.setBaby_rd(rs.getString("baby_rd"));
                list.add(babyVO);

            }

            // Handle any SQL errors
        } catch (Exception se) {
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
    public List<BabyVO> getAll() {
        List<BabyVO> list = new ArrayList<BabyVO>();
        BabyVO babyVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo 也稱為 Domain objects
                babyVO = new BabyVO();
                babyVO.setBaby_no(rs.getString("baby_no"));
                babyVO.setMem_no(rs.getString("mem_no"));
                babyVO.setBaby_aka(rs.getString("baby_aka"));
                babyVO.setBaby_bday(rs.getDate("baby_bday"));
                babyVO.setBaby_gen(rs.getString("baby_gen"));
                babyVO.setBaby_hc(rs.getDouble("baby_hc"));
                babyVO.setBaby_ht(rs.getDouble("baby_ht"));
                babyVO.setBaby_wt(rs.getDouble("baby_wt"));
                babyVO.setBaby_is_ar(rs.getString("baby_is_ar"));
                babyVO.setBaby_is_ab(rs.getString("baby_is_ab"));
                babyVO.setBaby_is_ac(rs.getString("baby_is_ac"));
                babyVO.setBaby_is_sf(rs.getString("baby_is_sf"));
                babyVO.setBaby_is_ad(rs.getString("baby_is_ad"));
                babyVO.setBaby_rd(rs.getString("baby_rd"));
                list.add(babyVO);

            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
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

    public static void main(String[] args) {
        BabyJDBCDAO dao = new BabyJDBCDAO();
        // 新增 OK
//		BabyVO babyVO1 = new BabyVO();
//		babyVO1.setMem_no("M0000001");
//		babyVO1.setBaby_aka("新生寶寶2");
//		babyVO1.setBaby_bday(java.sql.Date.valueOf("2017-03-04"));
//		babyVO1.setBaby_gen("1");
//		babyVO1.setBaby_hc(30.66);
//		babyVO1.setBaby_ht(70.06);
//		babyVO1.setBaby_wt(7.6);
//		babyVO1.setBaby_is_ar("0");
//		babyVO1.setBaby_is_ab("0");
//		babyVO1.setBaby_is_ac("0");
//		babyVO1.setBaby_is_sf("0");
//		babyVO1.setBaby_is_ad("0");
//		babyVO1.setBaby_rd("0");
//		dao.insert(babyVO1);
//		System.out.println("---新增資料成功---");

        // 修改 OK
//		BabyVO babyVO2 = new BabyVO();
//		babyVO2.setBaby_no("BABY0001");
//		babyVO2.setBaby_aka("小BB");
//		babyVO2.setBaby_bday(java.sql.Date.valueOf("2017-01-09"));
//		babyVO2.setBaby_gen("0");
//		babyVO2.setBaby_hc(41.9);
//		babyVO2.setBaby_ht(66.7);
//		babyVO2.setBaby_wt(8.0);
//		babyVO2.setBaby_is_ar("0");
//		babyVO2.setBaby_is_ab("0");
//		babyVO2.setBaby_is_ac("0");
//		babyVO2.setBaby_is_sf("0");
//		babyVO2.setBaby_is_ad("0");
//		babyVO2.setBaby_rd("0");
//		dao.update(babyVO2);
//		System.out.println("---資料成功---");

        // 查詢單筆 OK
//		BabyVO babyVO3 = dao.findByPrimaryKey("BABY0001");
//		System.out.print(babyVO3.getBaby_no() + ",");
//		System.out.print(babyVO3.getMem_no() + ",");
//		System.out.print(babyVO3.getBaby_aka() + ",");
//		System.out.print(babyVO3.getBaby_bday() + ",");
//		System.out.print(babyVO3.getBaby_gen() + ",");
//		System.out.print(babyVO3.getBaby_hc() + ",");
//		System.out.print(babyVO3.getBaby_ht() + ",");
//		System.out.print(babyVO3.getBaby_wt() + ",");
//		System.out.print(babyVO3.getBaby_is_ar() + ",");
//		System.out.print(babyVO3.getBaby_is_ab() + ",");
//		System.out.print(babyVO3.getBaby_is_ac() + ",");
//		System.out.print(babyVO3.getBaby_is_sf() + ",");
//		System.out.print(babyVO3.getBaby_is_ad() + ",");
//		System.out.println(babyVO3.getBaby_rd());
//		System.out.println("---------------------");
        
        List<BabyVO> list = dao.findByMemNo("M0000001");
		for (BabyVO babyVO : list) {			
			System.out.print(babyVO.getBaby_no() + ",");
		}

        // 查詢全部 OK
//		List<BabyVO> list = dao.getAll();
//		for (BabyVO babyVO : list) {
//			System.out.print(babyVO.getBaby_no() + ",");
//			System.out.print(babyVO.getMem_no() + ",");
//			System.out.print(babyVO.getBaby_aka() + ",");
//			System.out.print(babyVO.getBaby_bday() + ",");
//			System.out.print(babyVO.getBaby_gen() + ",");
//			System.out.print(babyVO.getBaby_hc() + ",");
//			System.out.print(babyVO.getBaby_ht() + ",");
//			System.out.print(babyVO.getBaby_wt() + ",");
//			System.out.print(babyVO.getBaby_is_ar() + ",");
//			System.out.print(babyVO.getBaby_is_ab() + ",");
//			System.out.print(babyVO.getBaby_is_ac() + ",");
//			System.out.print(babyVO.getBaby_is_sf() + ",");
//			System.out.print(babyVO.getBaby_is_ad() + ",");
//			System.out.print(babyVO.getBaby_rd());
//			System.out.println();
//		}
    }

}
