package com.example.Changwon.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartRequest;

import com.example.Changwon.DTO.Spots;
import com.example.Changwon.DTO.score;

public class SpotsDAO {

	private ArrayList<Spots> Spots = new ArrayList<Spots>();
	public static SpotsDAO SpotDao = new SpotsDAO();

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost:3306/traveldb";

	public static SpotsDAO getInstance() {
		return SpotDao;
	}

	public Connection con() {
		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "root", "0000");
			System.out.println("conn : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}		

	public int getListCount(String item, String search) {

		int searchcount = 0;
		Connection conn = con();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		if (item == null && search == null)
			sql = "SELECT   count(*) FROM spots";
		else
			sql = "SELECT   count(*) FROM spots where " + item + " like '%" + search + "%'";

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				searchcount = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("getListCount()를 가져오지 못했습니다 : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return searchcount;
	}

	public List<Spots> getAll(int page, int limit, String item, String text) throws Exception {
		Connection conn = con();
		List<Spots> SpotsList = new ArrayList<>();

		String sql = null;
		int total_record = getListCount(item, text);
		int start = (page - 1) * limit;
		int index = start + 1;

		System.out.println("getAll로 page넘어왔나? : " + page);
		System.out.println("getAll 아이템 넘어왔나? " + item);
		System.out.println("getAll text는 넘어왔나? " + text);
		System.out.println("getAll total_record는 넘어왔나? " + total_record);
		System.out.println("getAll index는 넘어왔나? " + index);
		System.out.println("getAll start는 넘어왔나? " + start);

		if (item == null && text == null)
			sql = "select aid, name, location, img from spots";
		else
			sql = "select aid, name, location, img from spots where " + item + " like '%" + text + "%'";

		System.out.println("sql문 확인 " + sql);

		PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = pstmt.executeQuery();

		try {
			while (rs.absolute(index)) {
				Spots s = new Spots();
				s.setImg(rs.getString("img"));
				s.setAid(rs.getInt("aid"));
				s.setName(rs.getString("name"));
				s.setAddress(rs.getString("location"));
				SpotsList.add(s);
				System.out.println("List : " + SpotsList);

				if (index < (start + limit) && index <= total_record)
					index++;
				else
					break;
			}
			return SpotsList;

		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return SpotsList;
	}

	public Spots getSpot(int aid) throws Exception {
		System.out.println("DAO getSpot.");
		Connection conn = con();

		Spots s = new Spots();
		String sql = "select aid, name,location,description,number,time, img from spots where aid=? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		try {
			s.setAid(rs.getInt("aid"));
			s.setName(rs.getString("name"));
			s.setAddress(rs.getString("location"));
			s.setDesc(rs.getString("description"));
			s.setNumber(rs.getString("number"));
			s.setTime(rs.getString("time"));
			s.setImg(rs.getString("img"));
			System.out.println("데이터 받아옴 dao 주소값" + s);
			return s;

		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return s;
	}

	public void addspots(Spots s) throws Exception {
		System.out.println("addapots를 넘어옴");
		Connection conn = con();
		String sql = "insert into spots(name, location, description,number,time,img) value(?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try {
			pstmt.setString(1, s.getName());
			pstmt.setString(2, s.getAddress());
			pstmt.setString(3, s.getDesc());
			pstmt.setString(4, s.getNumber());
			pstmt.setString(5, s.getTime());
			pstmt.setString(6, s.getImg());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}

	public void updatespots(Spots s) throws SQLException {
		Connection conn = con();
		System.out.println("DAO데이터 가지고 와져? " + s.getAid());
		String sql = "";
		PreparedStatement pstmt = null;
		
		try {			
			
			if (s.getImg()==null) {
				System.out.println("Img가 비었을 때 들어와져?");
				sql = "select img from spots where aid=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, s.getAid());
				ResultSet rs = pstmt.executeQuery();
				System.out.println("updatespots"+rs);
				if(rs.next()) {
					s.setImg(rs.getString("img"));
				}
			}
			
			sql = "update spots set name=?, location=?, description=?, number=?, time=?,img=? where aid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getName());
			pstmt.setString(2, s.getAddress());
			pstmt.setString(3, s.getDesc());
			pstmt.setString(4, s.getNumber());
			pstmt.setString(5, s.getTime());
			pstmt.setString(6, s.getImg());
			pstmt.setInt(7, s.getAid());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}

	}

	public void delspots(int aid) throws SQLException {
		Connection conn = con();

		String sql = "delete from spots where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try {
			pstmt.setInt(1, aid);
			if (pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}

	
	public void score(score s)throws SQLException {
		Connection conn = con();
		String sql = "";
		PreparedStatement pstmt = null;
		
		try {						
			sql = "insert into score(name,score,Questions) value(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getName());
			pstmt.setInt(2, s.getScore());
			pstmt.setString(3, s.getQuestions());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		
		
	}
	
	public List<score> Questionview(String name) {

		String sql;
		List<score> scoreList = new ArrayList<>();
		Connection conn = con();
		PreparedStatement pstmt = null;
		try {						
			sql = "select * from score where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				score s = new score();
				s.setName(rs.getString("name"));
				s.setScore(rs.getInt("score"));
				s.setQuestions(rs.getString("Questions"));
				scoreList.add(s);				
			}
			return scoreList;
		} 
		catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return scoreList;
	}
}
