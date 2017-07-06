package com.diary.model;
/**
 * 相片，不能修改，可以刪除。
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Voice_DiaryJDBCDAO implements Voice_DiaryDAO_interface {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    //	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String URL = "jdbc:oracle:thin:@localhost:1522:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";

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

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, voice_diaryVO.getBaby_no());
            pstmt.setString(2, voice_diaryVO.getVd_title());
            pstmt.setBytes(3, voice_diaryVO.getVd_cnt());
            pstmt.setString(4, voice_diaryVO.getVd_shr());

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
    public void delete(String vd_no) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(DELETE_VOICE_DIARY);

            pstmt.setString(1, vd_no);

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
    public Voice_DiaryVO findByPrimary(String vd_no) {
        Voice_DiaryVO voice_diaryVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, vd_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo ?]??? Domain objects
                voice_diaryVO = new Voice_DiaryVO();
                voice_diaryVO.setVd_no(rs.getString("vd_no"));
                voice_diaryVO.setBaby_no(rs.getString("baby_no"));
                voice_diaryVO.setVd_title(rs.getString("vd_title"));
                voice_diaryVO.setVd_date(rs.getTimestamp("vd_date"));
                voice_diaryVO.setVd_cnt(rs.getBytes("vd_cnt"));
                voice_diaryVO.setVd_shr(rs.getString("vd_shr"));
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

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                voice_diaryVO = new Voice_DiaryVO();
                voice_diaryVO.setVd_no(rs.getString("vd_no"));
                voice_diaryVO.setBaby_no(rs.getString("baby_no"));
                voice_diaryVO.setVd_title(rs.getString("vd_title"));
                voice_diaryVO.setVd_date(rs.getTimestamp("vd_date"));
                voice_diaryVO.setVd_cnt(rs.getBytes("vd_cnt"));
                voice_diaryVO.setVd_shr(rs.getString("vd_shr"));
                list.add(voice_diaryVO);
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
        Voice_DiaryJDBCDAO dao = new Voice_DiaryJDBCDAO();
        // ?s?W OK
        Voice_DiaryVO voice_diaryVO1 = new Voice_DiaryVO();
        voice_diaryVO1.setBaby_no("BABY0002");
        voice_diaryVO1.setVd_title("?p?o?Y?????n");
        voice_diaryVO1.setVd_shr("0");
        byte[] pic;
        try {
            pic = getPictureByteArray("WebContent/images/VIDEO0275.mp4");
            voice_diaryVO1.setVd_cnt(pic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao.insert(voice_diaryVO1);
        System.out.println("?s?W??????");


        // ?R?? OK
//		dao.utility.delete("VD000001");
//		System.out.print("???R??????");

        // ?d??浧 OK
//		Voice_DiaryVO voice_diaryVO3 = dao.findByPrimary("VD000001");
//		System.out.print(voice_diaryVO3.getVd_no() + ",");
//		System.out.print(voice_diaryVO3.getBaby_no() + ",");
//		System.out.print(voice_diaryVO3.getVd_title() + ",");
//		System.out.print(voice_diaryVO3.getVd_date() + ",");
//		System.out.print(voice_diaryVO3.getVd_cnt() + ",");
//		System.out.println(voice_diaryVO3.getVd_shr());
//		System.out.println("---------------------");

        // ?d????? OK
//		List<Voice_DiaryVO> list = dao.getAll();
//		for (Voice_DiaryVO voice_diaryVO : list) {
//			System.out.print(voice_diaryVO.getVd_no() + ",");
//			System.out.print(voice_diaryVO.getBaby_no() + ",");
//			System.out.print(voice_diaryVO.getVd_title() + ",");
//			System.out.print(voice_diaryVO.getVd_date() + ",");
//			System.out.print(voice_diaryVO.getVd_cnt() + ",");
//			System.out.print(voice_diaryVO.getVd_shr());
//			System.out.println();
//		}
    }

    public static byte[] getPictureByteArray(String path) throws IOException {
        //?s?W?@??file???????B?i?H??????i??????|
        File file = new File(path);
        //?M??????i???file?e??inputSTream?B?z"???????J?u?@"
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //?B?z8192byte?????
        byte[] buffer = new byte[8192];
        int i;
        //?Q??read()?????@?r??
        //readLine();?O?Ψ?????@??r??
        while ((i = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, i);
        }
        baos.close();
        fis.close();

        return baos.toByteArray();

    }

}