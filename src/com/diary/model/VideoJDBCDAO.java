package com.diary.model;
/**
 * 相片，不能修改，可以刪除。*/

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VideoJDBCDAO implements VideoDAO_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String URL = "jdbc:oracle:thin:@localhost:1522:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, videoVO.getBaby_no());
			pstmt.setString(2, videoVO.getVid_title());
			pstmt.setString(3, videoVO.getVid_annot());
			pstmt.setBytes(4, videoVO.getVid_file());
			pstmt.setString(5, videoVO.getVid_shr());

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
	public void delete(String vid_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_VIDEO);

			pstmt.setString(1, vid_no);

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
	public VideoVO findByPrimary(String vid_no) {
		VideoVO videoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, vid_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				videoVO = new VideoVO();
				videoVO.setVid_no(rs.getString("vid_no"));
				videoVO.setBaby_no(rs.getString("baby_no"));
				videoVO.setVid_title(rs.getString("vid_title"));
				videoVO.setVid_date(rs.getTimestamp("vid_date"));
				videoVO.setVid_annot(rs.getString("vid_annot"));
				videoVO.setVid_file(rs.getBytes("vid_file"));
				videoVO.setVid_shr(rs.getString("vid_shr"));
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
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
	
	public static void main(String[] args){
		VideoJDBCDAO dao= new VideoJDBCDAO();
		// �s�W OK
		VideoVO videoVO1 = new VideoVO();
		videoVO1.setBaby_no("BABY0002");
		videoVO1.setVid_annot("�Y���P");
		videoVO1.setVid_shr("0");
		byte[] pic;
		try {
			pic = getPictureByteArray("WebContent/images/VIDEO0275.mp4");
			videoVO1.setVid_file(pic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dao.insert(videoVO1);
		System.out.println("�s�W��Ƨ���");
		
		

		// �R�� OK
//		dao.utility.delete("VID00002");
//		System.out.print("��ƧR������");
		
		// �d�߳浧 OK
//		VideoVO videoVO3 = dao.findByPrimary("VID00002");
//		System.out.print(videoVO3.getVid_no() + ",");
//		System.out.print(videoVO3.getBaby_no() + ",");
//		System.out.print(videoVO3.getVid_title() + ",");
//		System.out.print(videoVO3.getVid_date() + ",");
//		System.out.print(videoVO3.getVid_annot() + ",");
//		System.out.print(videoVO3.getVid_file() + ",");
//		System.out.println(videoVO3.getVid_shr());
//		System.out.println("---------------------");
		
		// �d�ߥ��� OK
//		List<VideoVO> list = dao.getAll();
//		for (VideoVO videoVO : list) {
//			System.out.print(videoVO.getVid_no() + ",");
//			System.out.print(videoVO.getBaby_no() + ",");
//			System.out.print(videoVO.getVid_title() + ",");
//			System.out.print(videoVO.getVid_date() + ",");
//			System.out.print(videoVO.getVid_annot() + ",");
//			System.out.print(videoVO.getVid_file() + ",");
//			System.out.print(videoVO.getVid_shr());
//			System.out.println();
//		}
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		//�s�W�@��file������åB�i�H�����Ƕi�Ӫ����|
		File file = new File(path);
		//�M�����Ƕi�Ӫ�file�e��inputSTream�B�z"�줸�ժ���J�u�@"
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//�B�z8192byte�����
		byte[] buffer = new byte[8192];
		int i;
		//�Q��read()Ū����@�r��
		//readLine();�O�Ψ�Ū���@��r��
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	
	}
	
}
