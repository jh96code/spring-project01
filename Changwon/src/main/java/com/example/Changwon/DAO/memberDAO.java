package com.example.Changwon.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.util.ConcurrentDateFormat;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.example.Changwon.DTO.memberDTO;


public class memberDAO {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost:3306/travelDB";

	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "root", "0000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 로그인된 회원정보 들고오기
	public memberDTO getMember(String id) throws SQLException {
		Connection conn = open();

		memberDTO dto = new memberDTO();
		String sql = "select * from member where id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		try {
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			dto.setName(rs.getString("name"));
			dto.setGender(rs.getString("gender"));
			dto.setBirth(rs.getString("birth"));
			dto.setMail(rs.getString("mail"));
			dto.setPhone(rs.getString("phone"));
			dto.setAddress(rs.getString("address"));
			dto.setRegist_day(rs.getString("regist_day"));
			pstmt.executeQuery();
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
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
		return null;
	}

	// 회원 정보 수정 메소드
	public void updateMember(memberDTO dto) {
		Connection conn = open();
		PreparedStatement pstmt = null;

		try {
			String sql = "update member set id=?, password=?, name=?, gender=?, birth=?, mail=?, phone=?, address=? where id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getBirth());
			pstmt.setString(6, dto.getMail());
			pstmt.setString(7, dto.getPhone());
			pstmt.setString(8, dto.getAddress());
			pstmt.setString(9, dto.getId());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("updateMember() 오류 : " + ex);
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

	// 회원가입 메소드
	public void addmember(memberDTO mem) throws Exception {
		Connection conn = open();
		mem.setBirth(mem.getYear() + "/" + mem.getMonth() + "/" + mem.getDay());
		mem.setMail(mem.getMail1() + "@" + mem.getMail2());
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, current_date(),?)";
			
			if(mem.getAdmincheck()==null) {
				mem.setAdmincheck(false);}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPassword());
			pstmt.setString(3, mem.getName());
			pstmt.setString(4, mem.getGender());
			pstmt.setString(5, mem.getBirth());
			pstmt.setString(6, mem.getMail());
			pstmt.setString(7, mem.getPhone());
			pstmt.setString(8, mem.getAddress());
	        pstmt.setBoolean(9, mem.getAdmincheck());
	        
	        pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("addMember() 에러 : " + ex);
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

	// 로그인 회원여부 메소드
	public memberDTO loginCheck(String loginid, String loginpassword) {
		Connection conn = open();
		PreparedStatement pstmt = null;
		memberDTO member = new memberDTO();

		try {
			String sql = "SELECT * FROM MEMBER WHERE ID=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginid);
			pstmt.setString(2, loginpassword);
			ResultSet rs = pstmt.executeQuery();			
			if (rs.next()) {
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setAdmincheck(rs.getBoolean("admincheck"));
				System.out.println("null일 경우");
				return member;
			}		

		} catch (Exception ex) {
			System.out.println("로그인 체크 에러 :" + ex);
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
		return member = null;
	}

	// 회원탈퇴 메소드
	public void delMember(String id) {
		Connection conn = open();
		PreparedStatement pstmt = null;
		System.out.println("dao탈퇴 아이디: " + id);

		String sql = "delete from member where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("delMember() 오류 : " + ex);
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

	// (회원 이름으로)아이디 찾기 메소드
	public memberDTO getId(String name) throws SQLException {
		Connection conn = open();
		memberDTO dto = new memberDTO();
		String sql = "select id, regist_day from member where name=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		try {
			dto.setId(rs.getString("id"));
			dto.setRegist_day(rs.getString("regist_day"));
			pstmt.executeQuery();
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
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
		return null;
	}

	// (회원 아이디로)비밀번호 찾기 메소드
	public memberDTO getPw(String id) throws SQLException {
		Connection conn = open();
		memberDTO dto = new memberDTO();
		String sql = "select id, password from member where id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		try {
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			pstmt.executeQuery();
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
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
		return null;
	}

	// 아이디 중복확인 메소드
	public String idCheck(String id) throws SQLException {
		Connection conn = open();
		String memberid;
		String sql = "select id from member where id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		try {
			memberid = rs.getString("id");
			return memberid;
		} catch (Exception e) {
			e.printStackTrace();
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
		return null;
	}

}
