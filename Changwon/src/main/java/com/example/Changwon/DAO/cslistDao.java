package com.example.Changwon.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Changwon.DTO.BoardDto;
import com.example.Changwon.DTO.board_replyDto;
import com.example.Changwon.DTO.cslistDto;

public class cslistDao {
	//DB연결은 위한 변수 생성
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String JDBC_URL = "jdbc:mysql://localhost:3306/traveldb";
		
	//DB연결 메소드
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"root","0000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//관리자회원 문의목록(전체) 불러오기 메소드
	public List<cslistDto> getAllCSlist() throws SQLException{
		List<cslistDto> cslist = new ArrayList<>();
		Connection conn = open();
		String sql = "select * from cslist";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
				
		try{ 
			while(rs.next()) { //while문을 써서 DB에 있는값 전체를 ResultSet값에 들어간 값을 차례로 dto에 담는 과정
				cslistDto dto = new cslistDto();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setSend_day(rs.getDate("send_day"));
				dto.setReply_day(rs.getDate("reply_day"));
				dto.setReply_content(rs.getString("reply_content"));
				dto.setReply_member_id(rs.getString("reply_member_id"));
				//dto에 차례대로 넣은값을 cslist(dto형식의 List배열)에 담는다
				cslist.add(dto);
			}
			return cslist;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
				if (rs != null) 
					rs.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}	
		}
		return null;
	}
	
	//일반회원 문의목록 불러오기 메소드
	public List<cslistDto> getCSlist(String id) throws SQLException{
		List<cslistDto> cslist = new ArrayList<>();
		Connection conn = open();
		String sql = "select * from cslist where member_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
				
		try{ 
			while(rs.next()) { //while문을 써서 DB에 있는값 전체를 ResultSet값에 들어간 값을 차례로 dto에 담는 과정
				cslistDto dto = new cslistDto();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setSend_day(rs.getDate("send_day"));
				dto.setReply_day(rs.getDate("reply_day"));
				dto.setReply_content(rs.getString("reply_content"));
				dto.setReply_member_id(rs.getString("reply_member_id"));
				//dto에 차례대로 넣은값을 cslist(dto형식의 List배열)에 담는다
				cslist.add(dto);
			}
			return cslist;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
				if (rs != null) 
					rs.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}	
		}
		return null;
	}
	
	//문의내용 삭제 메소드
	public void delCS(int num){
		Connection conn = open();
		PreparedStatement pstmt = null;
				
		String sql = "delete from cslist where num=?";
				
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 삭제된 문의 내용이 없을 경우
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}catch (Exception ex) {
			System.out.println("delBoard() 오류 : " + ex);
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
	
	//문의보내기 메소드
	public void sendCS(cslistDto dto) throws Exception {
		Connection conn = open();
		
		String sql = "insert into cslist(title, member_id, category, content, send_day) values(?,?,?,?,CURRENT_TIMESTAMP())";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try{ // sql문의 파라미타(?)값에 데이터 대입
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getMember_id());
			pstmt.setString(3, dto.getCategory());
			pstmt.setString(4, dto.getContent());
			//exectueUpdate()문을 사용
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
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
	
	//문의 답변 달기 메소드
		public void replyCS(cslistDto dto) throws Exception {
			Connection conn = open();
			
			String sql = "update cslist set reply_member_id=?, reply_content=?, reply_day=CURRENT_TIMESTAMP() where num=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			try{ // sql문의 파라미타(?)값에 데이터 대입
				pstmt.setString(1, dto.getReply_member_id());
				pstmt.setString(2, dto.getReply_content());
				pstmt.setInt(3, dto.getNum());
				//exectueUpdate()문을 사용
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
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

	
}
